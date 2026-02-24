package org.computate.dcm.model.eda.tenant;

import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.api.service.ServiceRequest;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.http.HttpResponseExpectation;
import io.vertx.core.json.JsonArray;

import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.computate.dcm.config.ConfigKeys;
import org.computate.dcm.request.SiteRequest;

/**
 * Translate: false
 **/
public class TenantEnUSApiServiceImpl extends TenantEnUSGenApiServiceImpl {

  public Future<JsonObject> aapUpsertParams(Tenant o, Boolean inheritPrimaryKey, Boolean patch) {
    Promise<JsonObject> promise = Promise.promise();
    try {
      SiteRequest siteRequest = o.getSiteRequest_();
      ServiceRequest serviceRequest = siteRequest.getServiceRequest();
      if(Optional.ofNullable(serviceRequest.getParams()).map(p -> p.getJsonObject("query")).map( q -> q.getJsonArray("var")).orElse(new JsonArray()).stream().filter(s -> "refresh:false".equals(s)).count() > 0L) {
        promise.complete();
      } else {
        JsonObject tenantJson = o.getSiteRequest_().getJsonObject();
        String tenantName = Optional.ofNullable(tenantJson.getString(Tenant.varJsonTenant(Tenant.VAR_tenantName, patch))).orElse(o.getTenantName());
        tenantJson.put(Tenant.varJsonTenant(Tenant.VAR_tenantName, patch), tenantName);
        String tenantDescription = Optional.ofNullable(tenantJson.getString(patch ? "setTenantDescription": "tenantDescription")).orElse(o.getTenantDescription());
        tenantJson.put(Tenant.varJsonTenant(Tenant.VAR_tenantDescription, patch), tenantDescription);
        String tenantId = Optional.ofNullable(tenantJson.getString(Tenant.varJsonTenant(Tenant.VAR_tenantId, patch))).orElse(Tenant.toId(tenantName));
        tenantJson.put(Tenant.varJsonTenant(Tenant.VAR_tenantId, patch), tenantId);
        String tenantResource = String.format("%s-%s", Tenant.CLASS_AUTH_RESOURCE, tenantId);
        tenantJson.put(Tenant.varJsonTenant(Tenant.VAR_tenantResource, patch), tenantResource);
        promise.complete(tenantJson);
      }
    } catch(Exception ex) {
      LOG.error(String.format("Updating AAP tenant failed. "), ex);
      promise.fail(ex);
    }
    return promise.future();
  }

  public Future<Void> aapUpsertOrganization(Tenant o, Boolean inheritPrimaryKey, Boolean patch, JsonObject tenantJson) {
    Promise<Void> promise = Promise.promise();
    try {
      if(tenantJson == null) {
        promise.complete();
      } else {
        String tenantName = tenantJson.getString(Tenant.varJsonTenant(Tenant.VAR_tenantName, patch));
        String tenantDescription = tenantJson.getString(Tenant.varJsonTenant(Tenant.VAR_tenantDescription, patch));

        Integer aapPort = Integer.parseInt(config.getString(ConfigKeys.AAP_PORT));
        String aapTenantName = config.getString(ConfigKeys.AAP_HOST_NAME);
        Boolean aapSsl = Boolean.parseBoolean(config.getString(ConfigKeys.AAP_SSL));
        String aapUri = patch ? String.format("/api/controller/v2/tenants/%s/", o.getAapOrganizationId()) : "/api/controller/v2/organizations/";
        String aapUserName = config.getString(ConfigKeys.AAP_USER_NAME);
        String aapPassword = config.getString(ConfigKeys.AAP_PASSWORD);

        JsonObject body = new JsonObject();
        body.put("name", tenantName);
        if(tenantDescription != null)
          body.put("description", Optional.ofNullable(tenantDescription).orElse(tenantName));

        if(patch) {
          promise.complete();
        } else {
          webClient.post(aapPort, aapTenantName, aapUri).ssl(aapSsl)
              .putHeader("Content-Type", "application/json")
              .basicAuthentication(aapUserName, aapPassword)
              .sendJsonObject(body)
              .expecting(HttpResponseExpectation.SC_CREATED)
              .onSuccess(tenantResponse -> {
            JsonObject responseBody = tenantResponse.bodyAsJsonObject();
            String aapOrganizationId = responseBody.getString("id");
            tenantJson.put(Tenant.VAR_aapOrganizationId, aapOrganizationId);
            promise.complete();
          }).onFailure(ex -> {
            LOG.error(String.format("Updating AAP tenant failed. "), ex);
            promise.fail(ex);
          });
        }
      }
    } catch(Exception ex) {
      LOG.error(String.format("Updating AAP tenant failed. "), ex);
      promise.fail(ex);
    }
    return promise.future();
  }

  public Future<Void> sensuUpsertNamespace(Tenant o, Boolean inheritPrimaryKey, Boolean patch, JsonObject tenantJson) {
    Promise<Void> promise = Promise.promise();
    try {
      if(tenantJson == null) {
        promise.complete();
      } else {
        String tenantId = tenantJson.getString(Tenant.varJsonTenant(Tenant.VAR_tenantId, patch));

        Integer sensuPort = Integer.parseInt(config.getString(ConfigKeys.SENSU_PORT));
        String sensuTenantName = config.getString(ConfigKeys.SENSU_HOST_NAME);
        Boolean sensuSsl = Boolean.parseBoolean(config.getString(ConfigKeys.SENSU_SSL));
        String sensuUri = String.format("/api/core/v2/namespaces/%s", urlEncode(tenantId));
        String accessToken = config.getString(ConfigKeys.SENSU_TOKEN);

        JsonObject body = new JsonObject();
        body.put("name", tenantId);

        if(StringUtils.isEmpty(tenantId)) {
          RuntimeException ex = new RuntimeException("Missing tenant ID");
          LOG.error(ex.getMessage(), ex);
          promise.fail(ex);
        } else {
          webClient.put(sensuPort, sensuTenantName, sensuUri).ssl(sensuSsl)
              .putHeader("Authorization", String.format("Key %s", accessToken))
              .sendJsonObject(body)
              .expecting(HttpResponseExpectation.SC_OK.or(HttpResponseExpectation.SC_CREATED))
              .onSuccess(TenantResponse -> {
            promise.complete();
          }).onFailure(ex -> {
            LOG.error(String.format("Updating Sensu namespace failed. "), ex);
            promise.fail(ex);
          });
        }
      }
    } catch(Exception ex) {
      LOG.error(String.format("Updating Sensu namespace failed. "), ex);
      promise.fail(ex);
    }
    return promise.future();
  }

  public Future<Void> sensuUpsertKafkaHandler(Tenant o, Boolean inheritPrimaryKey, Boolean patch, JsonObject tenantJson) {
    Promise<Void> promise = Promise.promise();
    try {
      if(tenantJson == null) {
        promise.complete();
      } else {
        String tenantId = tenantJson.getString(Tenant.varJsonTenant(Tenant.VAR_tenantId, patch));
        String handlerName = "sensu-kafka-handler";
        String kafkaHost = config.getString(ConfigKeys.KAFKA_BROKERS);
        String kafkaTopic = config.getString(ConfigKeys.KAFKA_TOPIC_SENSU_EVENT);
        String handlerCommand = String.format("sensu-kafka-handler --host %s --topic %s", kafkaHost, kafkaTopic);

        Integer sensuPort = Integer.parseInt(config.getString(ConfigKeys.SENSU_PORT));
        String sensuTenantName = config.getString(ConfigKeys.SENSU_HOST_NAME);
        Boolean sensuSsl = Boolean.parseBoolean(config.getString(ConfigKeys.SENSU_SSL));
        String sensuUri = String.format("/api/core/v2/namespaces/%s/handlers/%s", urlEncode(tenantId), urlEncode(handlerName));
        String accessToken = config.getString(ConfigKeys.SENSU_TOKEN);

        JsonObject body = new JsonObject();
        body.put("command", handlerCommand);
        body.put("type", "pipe");
        body.put("filters", new JsonArray().add(""));
        body.put("handlers", new JsonArray().add(""));
        body.put("runtime_assets", new JsonArray().add("sensu/sensu-kafka-handler"));
        body.put("timeout", 0);
        JsonObject metadata = new JsonObject();
        metadata.put("name", handlerName);
        metadata.put("namespace", tenantId);
        metadata.put("labels", null);
        metadata.put("annotations", null);
        body.put("metadata", metadata);

        if(StringUtils.isEmpty(tenantId)) {
          RuntimeException ex = new RuntimeException("Missing tenant ID");
          LOG.error(ex.getMessage(), ex);
          promise.fail(ex);
        } else {
          webClient.put(sensuPort, sensuTenantName, sensuUri).ssl(sensuSsl)
              .putHeader("Authorization", String.format("Key %s", accessToken))
              .sendJsonObject(body)
              .expecting(HttpResponseExpectation.SC_OK.or(HttpResponseExpectation.SC_CREATED))
              .onSuccess(TenantResponse -> {
            promise.complete();
          }).onFailure(ex -> {
            LOG.error(String.format("Updating Sensu namespace failed. "), ex);
            promise.fail(ex);
          });
        }
      }
    } catch(Exception ex) {
      LOG.error(String.format("Updating Sensu namespace failed. "), ex);
      promise.fail(ex);
    }
    return promise.future();
  }

  @Override
  public Future<Tenant> sqlPOSTTenant(Tenant o, Boolean inheritPrimaryKey) {
    Promise<Tenant> promise = Promise.promise();
    aapUpsertParams(o, inheritPrimaryKey, false).onSuccess(tenantJson -> {
      aapUpsertOrganization(o, inheritPrimaryKey, false, tenantJson).onSuccess(a -> {
        sensuUpsertNamespace(o, inheritPrimaryKey, false, tenantJson).onSuccess(b -> {
          sensuUpsertKafkaHandler(o, inheritPrimaryKey, false, tenantJson).onSuccess(c -> {
            super.sqlPOSTTenant(o, inheritPrimaryKey).onSuccess(o2 -> {
              promise.complete(o2);
            }).onFailure(ex -> {
              promise.fail(ex);
            });
          }).onFailure(ex -> {
            promise.fail(ex);
          });
        }).onFailure(ex -> {
          promise.fail(ex);
        });
      }).onFailure(ex -> {
        promise.fail(ex);
      });
    }).onFailure(ex -> {
      promise.fail(ex);
    });
    return promise.future();
  }

  @Override
  public Future<Tenant> sqlPATCHTenant(Tenant o, Boolean inheritPrimaryKey) {
    Promise<Tenant> promise = Promise.promise();
    aapUpsertParams(o, inheritPrimaryKey, true).onSuccess(tenantJson -> {
      aapUpsertOrganization(o, inheritPrimaryKey, true, tenantJson).onSuccess(a -> {
        sensuUpsertNamespace(o, inheritPrimaryKey, true, tenantJson).onSuccess(b -> {
          sensuUpsertKafkaHandler(o, inheritPrimaryKey, true, tenantJson).onSuccess(c -> {
            super.sqlPATCHTenant(o, inheritPrimaryKey).onSuccess(o2 -> {
              promise.complete(o2);
            }).onFailure(ex -> {
              promise.fail(ex);
            });
          }).onFailure(ex -> {
            LOG.error(String.format("Updating Sensu namespace failed. "), ex);
            promise.fail(ex);
          });
        }).onFailure(ex -> {
          LOG.error(String.format("Updating Sensu namespace failed. "), ex);
          promise.fail(ex);
        });
      }).onFailure(ex -> {
        promise.fail(ex);
      });
    }).onFailure(ex -> {
      promise.fail(ex);
    });
    return promise.future();
  }

  public Future<Void> aapDeleteTenant(Tenant o) {
    Promise<Void> promise = Promise.promise();
    try {
      Integer aapPort = Integer.parseInt(config.getString(ConfigKeys.AAP_PORT));
      String aapTenantName = config.getString(ConfigKeys.AAP_HOST_NAME);
      Boolean aapSsl = Boolean.parseBoolean(config.getString(ConfigKeys.AAP_SSL));
      String aapUri = String.format("/api/controller/v2/organizations/%s/", o.getAapOrganizationId());
      String aapUserName = config.getString(ConfigKeys.AAP_USER_NAME);
      String aapPassword = config.getString(ConfigKeys.AAP_PASSWORD);

      webClient.delete(aapPort, aapTenantName, aapUri).ssl(aapSsl)
          .basicAuthentication(aapUserName, aapPassword)
          .send()
          .expecting(HttpResponseExpectation.SC_NO_CONTENT.or(HttpResponseExpectation.SC_NOT_FOUND))
          .onSuccess(TenantResponse -> {
        promise.complete();
      }).onFailure(ex -> {
        LOG.error(String.format("Deleting Sensu tenant failed. "), ex);
        promise.fail(ex);
      });
    } catch(Exception ex) {
      LOG.error(String.format("Deleting Sensu tenant failed. "), ex);
      promise.fail(ex);
    }
    return promise.future();
  }

  public Future<Void> sensuDeleteTenant(Tenant o) {
    Promise<Void> promise = Promise.promise();
    try {
      Integer sensuPort = Integer.parseInt(config.getString(ConfigKeys.SENSU_PORT));
      String sensuTenantName = config.getString(ConfigKeys.SENSU_HOST_NAME);
      Boolean sensuSsl = Boolean.parseBoolean(config.getString(ConfigKeys.SENSU_SSL));
      String sensuUri = String.format("/api/core/v2/namespaces/%s", o.getTenantId());
      String accessToken = config.getString(ConfigKeys.SENSU_TOKEN);

      webClient.delete(sensuPort, sensuTenantName, sensuUri).ssl(sensuSsl)
          .putHeader("Authorization", String.format("Key %s", accessToken))
          .send()
          .expecting(HttpResponseExpectation.SC_NO_CONTENT.or(HttpResponseExpectation.SC_NOT_FOUND))
          .onSuccess(TenantResponse -> {
        promise.complete();
      }).onFailure(ex -> {
        LOG.error(String.format("Deleting Sensu tenant failed. "), ex);
        promise.fail(ex);
      });
    } catch(Exception ex) {
      LOG.error(String.format("Deleting Sensu tenant failed. "), ex);
      promise.fail(ex);
    }
    return promise.future();
  }

  @Override
  public Future<Void> sqlDELETEFilterTenant(Tenant o) {
    Promise<Void> promise = Promise.promise();
    sensuDeleteTenant(o).onSuccess(a -> {
      aapDeleteTenant(o).onSuccess(b -> {
        super.sqlDELETEFilterTenant(o).onSuccess(c -> {
          promise.complete();
        }).onFailure(ex -> {
          promise.fail(ex);
        });
      }).onFailure(ex -> {
        promise.fail(ex);
      });
    }).onFailure(ex -> {
      promise.fail(ex);
    });
    return promise.future();
  }

  @Override
  public Future<Void> sqlDELETETenant(Tenant o) {
    Promise<Void> promise = Promise.promise();
    sensuDeleteTenant(o).onSuccess(a -> {
      aapDeleteTenant(o).onSuccess(b -> {
        super.sqlDELETETenant(o).onSuccess(c -> {
          promise.complete();
        }).onFailure(ex -> {
          promise.fail(ex);
        });
      }).onFailure(ex -> {
        promise.fail(ex);
      });
    }).onFailure(ex -> {
      promise.fail(ex);
    });
    return promise.future();
  }
}
