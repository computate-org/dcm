package org.computate.dcm.model.eda.tenant;

import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.authentication.UsernamePasswordCredentials;
import io.vertx.ext.web.api.service.ServiceRequest;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.http.HttpResponseExpectation;
import io.vertx.core.json.JsonArray;

import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.computate.dcm.config.ConfigKeys;
import org.computate.dcm.model.eda.ansibleproject.AnsibleProject;
import org.computate.dcm.model.eda.ansibleproject.AnsibleProjectEnUSApiServiceImpl;
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

  public Future<Void> aapUpsertAnsibleGalaxyCredential(Tenant tenant) {
    Promise<Void> promise = Promise.promise();
    try {
      Integer aapPort = Integer.parseInt(config.getString(ConfigKeys.AAP_PORT));
      String aapHostName = config.getString(ConfigKeys.AAP_HOST_NAME);
      Boolean aapSsl = Boolean.parseBoolean(config.getString(ConfigKeys.AAP_SSL));
      String aapUserName = config.getString(ConfigKeys.AAP_USER_NAME);
      String aapPassword = config.getString(ConfigKeys.AAP_PASSWORD);

      webClient.get(aapPort, aapHostName, "/api/controller/v2/credentials/?name=" + urlEncode("Ansible Galaxy")).ssl(aapSsl)
          .putHeader("Content-Type", "application/json")
          .basicAuthentication(aapUserName, aapPassword)
          .send()
          .expecting(HttpResponseExpectation.SC_OK)
          .onSuccess(queryAnsibleGalaxyResponse -> {
        try {
          JsonObject ansibleGalaxyCredential = queryAnsibleGalaxyResponse.bodyAsJsonObject().getJsonArray("results").stream().findFirst().map(cred -> (JsonObject)cred).orElse(null);
          if(ansibleGalaxyCredential == null) {
            promise.complete();
          } else {
            webClient.post(aapPort, aapHostName, String.format("/api/controller/v2/organizations/%s/galaxy_credentials/", tenant.getAapOrganizationId())).ssl(aapSsl)
                .putHeader("Content-Type", "application/json")
                .basicAuthentication(aapUserName, aapPassword)
                .sendJsonObject(new JsonObject().put("id", Long.parseLong(ansibleGalaxyCredential.getString("id"))))
                .expecting(HttpResponseExpectation.SC_CREATED.or(HttpResponseExpectation.SC_NO_CONTENT).or(HttpResponseExpectation.SC_BAD_REQUEST))
                .onSuccess(createCredentialResponse -> {
              promise.complete();
            }).onFailure(ex -> {
              LOG.error(String.format("Updating the Ansible Galaxy credential for the organization failed. "), ex);
              promise.fail(ex);
            });
          }
        } catch(Exception ex) {
          LOG.error(String.format("Querying Ansible Galaxy credential failed. "), ex);
          promise.fail(ex);
        }
      }).onFailure(ex -> {
        LOG.error(String.format("Querying Ansible Galaxy credential failed. "), ex);
        promise.fail(ex);
      });
    } catch(Exception ex) {
      LOG.error(String.format("Updating AAP organization failed. "), ex);
      promise.fail(ex);
    }
    return promise.future();
  }

  public Future<JsonObject> aapUpsertAnsibleProjectSensu(Tenant o) {
    Promise<JsonObject> promise = Promise.promise();
    try {
      JsonObject ansibleProjectRequestBody = new JsonObject();
      ansibleProjectRequestBody.put(AnsibleProject.VAR_ansibleProjectName, "dcm_ansible");
      ansibleProjectRequestBody.put(AnsibleProject.VAR_tenantResource, o.getTenantResource());
      ansibleProjectRequestBody.put(AnsibleProject.VAR_sourceControlUrl, "https://github.com/computate-org/dcm_ansible.git");
      ansibleProjectRequestBody.put(AnsibleProject.VAR_aapOrganizationId, o.getAapOrganizationId());
      AnsibleProjectEnUSApiServiceImpl.putimportAnsibleProjectAsync(config, vertx, ansibleProjectRequestBody).onSuccess(ansibleProjectResponseBody -> {
        promise.complete(ansibleProjectResponseBody);
      }).onFailure(ex -> {
        promise.fail(ex);
      });
    } catch(Exception ex) {
      LOG.error(String.format("Creating default Ansible template for Sensu Agent failed. "), ex);
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
        String aapHostName = config.getString(ConfigKeys.AAP_HOST_NAME);
        Boolean aapSsl = Boolean.parseBoolean(config.getString(ConfigKeys.AAP_SSL));
        String aapUri = patch ? String.format("/api/controller/v2/organizations/%s/", o.getAapOrganizationId()) : "/api/controller/v2/organizations/";
        String aapUserName = config.getString(ConfigKeys.AAP_USER_NAME);
        String aapPassword = config.getString(ConfigKeys.AAP_PASSWORD);

        JsonObject body = new JsonObject();
        body.put("name", tenantName);
        if(tenantDescription != null)
          body.put("description", Optional.ofNullable(tenantDescription).orElse(tenantName));

        if(patch) {
          promise.complete();
        } else {
          webClient.post(aapPort, aapHostName, aapUri).ssl(aapSsl)
              .putHeader("Content-Type", "application/json")
              .basicAuthentication(aapUserName, aapPassword)
              .sendJsonObject(body)
              .expecting(HttpResponseExpectation.SC_CREATED)
              .onSuccess(tenantResponse -> {
            try {
              JsonObject responseBody = tenantResponse.bodyAsJsonObject();
              String aapOrganizationId = responseBody.getString("id");
              tenantJson.put(Tenant.VAR_aapOrganizationId, aapOrganizationId);
              promise.complete();
            } catch(Exception ex) {
              LOG.error(String.format("Post AAP host creation failed. "), ex);
              promise.fail(ex);
            }
          }).onFailure(ex -> {
            LOG.error(String.format("Updating AAP organization failed. "), ex);
            promise.fail(ex);
          });
        }
      }
    } catch(Exception ex) {
      LOG.error(String.format("Updating AAP organization failed. "), ex);
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
        String sensuHostName = config.getString(ConfigKeys.SENSU_HOST_NAME);
        Boolean sensuSsl = Boolean.parseBoolean(config.getString(ConfigKeys.SENSU_SSL));
        String sensuUri = String.format("/api/core/v2/namespaces/%s", urlEncode(tenantId));
        String sensuUserName = config.getString(ConfigKeys.SENSU_USER_NAME);
        String sensuPassword = config.getString(ConfigKeys.SENSU_PASSWORD);

        JsonObject body = new JsonObject();
        body.put("name", tenantId);

        if(StringUtils.isEmpty(tenantId)) {
          RuntimeException ex = new RuntimeException("Missing tenant ID");
          LOG.error(ex.getMessage(), ex);
          promise.fail(ex);
        } else {
          webClient.get(sensuPort, sensuHostName, "/auth").ssl(sensuSsl)
              .authentication(new UsernamePasswordCredentials(sensuUserName, sensuPassword))
              .send()
              .expecting(HttpResponseExpectation.SC_OK)
              .onSuccess(auth -> {
            webClient.put(sensuPort, sensuHostName, sensuUri).ssl(sensuSsl)
                .putHeader("Authorization", String.format("Bearer %s", auth.bodyAsJsonObject().getString("access_token")))
                .sendJsonObject(body)
                .expecting(HttpResponseExpectation.SC_OK.or(HttpResponseExpectation.SC_CREATED))
                .onSuccess(TenantResponse -> {
              promise.complete();
            }).onFailure(ex -> {
              LOG.error(String.format("Updating Sensu namespace failed. "), ex);
              promise.fail(ex);
            });
          }).onFailure(ex -> {
            LOG.error(String.format("Requesting Sensu admin token failed. "), ex);
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

  public Future<Void> sensuUpsertKafkaAsset(Tenant o, Boolean inheritPrimaryKey, Boolean patch, JsonObject tenantJson) {
    Promise<Void> promise = Promise.promise();
    try {
      if(tenantJson == null) {
        promise.complete();
      } else {
        String tenantId = tenantJson.getString(Tenant.varJsonTenant(Tenant.VAR_tenantId, patch));
        String assetName = "sensu/sensu-kafka-handler";

        Integer sensuPort = Integer.parseInt(config.getString(ConfigKeys.SENSU_PORT));
        String sensuHostName = config.getString(ConfigKeys.SENSU_HOST_NAME);
        Boolean sensuSsl = Boolean.parseBoolean(config.getString(ConfigKeys.SENSU_SSL));
        String sensuUri = String.format("/api/core/v2/namespaces/%s/assets/%s", urlEncode(tenantId), urlEncode(assetName));
        String sensuUserName = config.getString(ConfigKeys.SENSU_USER_NAME);
        String sensuPassword = config.getString(ConfigKeys.SENSU_PASSWORD);

        JsonObject body = new JsonObject();
        body.put("builds", new JsonArray()
          .add(new JsonObject()
            .put("url", "https://assets.bonsai.sensu.io/a17d204ee9e3d09552dc0f1dd59ec3c85812979b/sensu-kafka-handler_0.0.4_linux_amd64.tar.gz")
            .put("sha512", "6f345ce94c501742ff3907e0f2bc78525b36b9d35fecd2b2a284a87003e39333f60e7fddde9a17f3356e8919b874988c752400c43604cf6f430c2680b51cc64c")
            .put("filters", new JsonArray().add("entity.system.os == 'linux'").add("entity.system.arch == 'amd64'"))
          )
        );
        JsonObject metadata = new JsonObject();
        metadata.put("name", assetName);
        // metadata.put("name", String.format("sensu/%s", assetName));
        metadata.put("namespace", tenantId);
        // body.put("headers", null);
        body.put("metadata", metadata);
        JsonObject annotations = new JsonObject();
        annotations.put("io.sensu.bonsai.api_url", "https://bonsai.sensu.io/api/v1/assets/sensu/sensu-kafka-handler");
        annotations.put("io.sensu.bonsai.name", "sensu-kafka-handler");
        annotations.put("io.sensu.bonsai.namespace", "sensu");
        annotations.put("io.sensu.bonsai.tier", "Community");
        annotations.put("io.sensu.bonsai.url", "https://bonsai.sensu.io/assets/sensu/sensu-kafka-handler");
        annotations.put("io.sensu.bonsai.version", "0.0.4");
        annotations.put("io.sensu.bonsai.tags", "");
        metadata.put("annotations", annotations);

        if(StringUtils.isEmpty(tenantId)) {
          RuntimeException ex = new RuntimeException("Missing tenant ID");
          LOG.error(ex.getMessage(), ex);
          promise.fail(ex);
        } else {
          webClient.get(sensuPort, sensuHostName, "/auth").ssl(sensuSsl)
              .authentication(new UsernamePasswordCredentials(sensuUserName, sensuPassword))
              .send()
              .expecting(HttpResponseExpectation.SC_OK)
              .onSuccess(auth -> {
            webClient.put(sensuPort, sensuHostName, sensuUri).ssl(sensuSsl)
                .putHeader("Authorization", String.format("Bearer %s", auth.bodyAsJsonObject().getString("access_token")))
                .sendJsonObject(body)
                .expecting(HttpResponseExpectation.SC_OK.or(HttpResponseExpectation.SC_CREATED))
                .onSuccess(TenantResponse -> {
              promise.complete();
            }).onFailure(ex -> {
              LOG.error(String.format("Updating Sensu asset failed. "), ex);
              promise.fail(ex);
            });
          }).onFailure(ex -> {
            LOG.error(String.format("Requesting Sensu admin token failed. "), ex);
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
        String sensuHostName = config.getString(ConfigKeys.SENSU_HOST_NAME);
        Boolean sensuSsl = Boolean.parseBoolean(config.getString(ConfigKeys.SENSU_SSL));
        String sensuUri = String.format("/api/core/v2/namespaces/%s/handlers/%s", urlEncode(tenantId), urlEncode(handlerName));
        String sensuUserName = config.getString(ConfigKeys.SENSU_USER_NAME);
        String sensuPassword = config.getString(ConfigKeys.SENSU_PASSWORD);

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
          webClient.get(sensuPort, sensuHostName, "/auth").ssl(sensuSsl)
              .authentication(new UsernamePasswordCredentials(sensuUserName, sensuPassword))
              .send()
              .expecting(HttpResponseExpectation.SC_OK)
              .onSuccess(auth -> {
            webClient.put(sensuPort, sensuHostName, sensuUri).ssl(sensuSsl)
                .putHeader("Authorization", String.format("Bearer %s", auth.bodyAsJsonObject().getString("access_token")))
                .sendJsonObject(body)
                .expecting(HttpResponseExpectation.SC_OK.or(HttpResponseExpectation.SC_CREATED))
                .onSuccess(TenantResponse -> {
              promise.complete();
            }).onFailure(ex -> {
              LOG.error(String.format("Updating Sensu namespace failed. "), ex);
              promise.fail(ex);
            });
          }).onFailure(ex -> {
            LOG.error(String.format("Requesting Sensu admin token failed. "), ex);
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
          sensuUpsertKafkaAsset(o, inheritPrimaryKey, false, tenantJson).onSuccess(c -> {
            sensuUpsertKafkaHandler(o, inheritPrimaryKey, false, tenantJson).onSuccess(d -> {
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
    }).onFailure(ex -> {
      promise.fail(ex);
    });
    return promise.future();
  }

  @Override
  public Future<Tenant> postTenantFuture(SiteRequest siteRequest, Boolean tenantResource) {
    Promise<Tenant> promise = Promise.promise();
    super.postTenantFuture(siteRequest, tenantResource).onSuccess(tenant -> {
      aapUpsertAnsibleProjectSensu(tenant).onSuccess(a -> {
        aapUpsertAnsibleGalaxyCredential(tenant).onSuccess(b -> {
          promise.complete(tenant);
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
          sensuUpsertKafkaAsset(o, inheritPrimaryKey, true, tenantJson).onSuccess(c -> {
            sensuUpsertKafkaHandler(o, inheritPrimaryKey, true, tenantJson).onSuccess(d -> {
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
      String sensuHostName = config.getString(ConfigKeys.SENSU_HOST_NAME);
      Boolean sensuSsl = Boolean.parseBoolean(config.getString(ConfigKeys.SENSU_SSL));
      String sensuUri = String.format("/api/core/v2/namespaces/%s", o.getTenantId());
      String sensuUserName = config.getString(ConfigKeys.SENSU_USER_NAME);
      String sensuPassword = config.getString(ConfigKeys.SENSU_PASSWORD);

      webClient.get(sensuPort, sensuHostName, "/auth").ssl(sensuSsl)
          .authentication(new UsernamePasswordCredentials(sensuUserName, sensuPassword))
          .send()
          .expecting(HttpResponseExpectation.SC_OK)
          .onSuccess(auth -> {
        webClient.delete(sensuPort, sensuHostName, sensuUri).ssl(sensuSsl)
            .putHeader("Authorization", String.format("Bearer %s", auth.bodyAsJsonObject().getString("access_token")))
            .send()
            .expecting(HttpResponseExpectation.SC_NO_CONTENT.or(HttpResponseExpectation.SC_NOT_FOUND))
            .onSuccess(TenantResponse -> {
          promise.complete();
        }).onFailure(ex -> {
          LOG.error(String.format("Deleting Sensu tenant failed. "), ex);
          promise.fail(ex);
        });
      }).onFailure(ex -> {
        LOG.error(String.format("Requesting Sensu admin token failed. "), ex);
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
    // sensuDeleteTenant(o).onSuccess(a -> {
      aapDeleteTenant(o).onSuccess(b -> {
        super.sqlDELETEFilterTenant(o).onSuccess(c -> {
          promise.complete();
        }).onFailure(ex -> {
          promise.fail(ex);
        });
      }).onFailure(ex -> {
        promise.fail(ex);
      });
    // }).onFailure(ex -> {
    //   promise.fail(ex);
    // });
    return promise.future();
  }

  @Override
  public Future<Void> sqlDELETETenant(Tenant o) {
    Promise<Void> promise = Promise.promise();
    // sensuDeleteTenant(o).onSuccess(a -> {
      aapDeleteTenant(o).onSuccess(b -> {
        super.sqlDELETETenant(o).onSuccess(c -> {
          promise.complete();
        }).onFailure(ex -> {
          promise.fail(ex);
        });
      }).onFailure(ex -> {
        promise.fail(ex);
      });
    // }).onFailure(ex -> {
    //   promise.fail(ex);
    // });
    return promise.future();
  }
}
