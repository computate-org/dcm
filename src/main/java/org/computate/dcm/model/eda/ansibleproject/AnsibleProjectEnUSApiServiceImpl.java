package org.computate.dcm.model.eda.ansibleproject;

import io.vertx.ext.auth.authorization.AuthorizationProvider;
import io.vertx.ext.auth.oauth2.OAuth2Auth;
import io.vertx.ext.web.client.WebClient;
import io.vertx.core.Vertx;
import io.vertx.core.WorkerExecutor;
import io.vertx.core.eventbus.DeliveryOptions;
import io.vertx.core.json.JsonObject;
import io.vertx.sqlclient.Pool;

import org.computate.vertx.config.ComputateConfigKeys;
import org.computate.vertx.openapi.ComputateOAuth2AuthHandlerImpl;
import io.vertx.kafka.client.producer.KafkaProducer;
import io.vertx.mqtt.MqttClient;
import io.vertx.amqp.AmqpSender;
import io.vertx.rabbitmq.RabbitMQClient;
import com.hubspot.jinjava.Jinjava;
import io.vertx.ext.web.api.service.ServiceRequest;
import io.vertx.ext.web.api.service.ServiceResponse;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Promise;
import io.vertx.core.http.HttpResponseExpectation;
import io.vertx.core.json.JsonArray;
import java.util.Optional;
import org.apache.commons.lang3.StringUtils;
import org.computate.dcm.config.ConfigKeys;
import org.computate.dcm.model.eda.tenant.Tenant;
import org.computate.dcm.request.SiteRequest;

/**
 * Translate: false
 **/
public class AnsibleProjectEnUSApiServiceImpl extends AnsibleProjectEnUSGenApiServiceImpl {

  public Future<JsonObject> aapUpsertParams(AnsibleProject o, Boolean inheritPrimaryKey, Boolean patch) {
    Promise<JsonObject> promise = Promise.promise();
    try {
      SiteRequest siteRequest = o.getSiteRequest_();
      ServiceRequest serviceRequest = siteRequest.getServiceRequest();
      if(Optional.ofNullable(serviceRequest.getParams()).map(p -> p.getJsonObject("query")).map( q -> q.getJsonArray("var")).orElse(new JsonArray()).stream().filter(s -> "refresh:false".equals(s)).count() > 0L) {
        promise.complete();
      } else {
        JsonObject ansibleProjectJson = o.getSiteRequest_().getJsonObject();
        String tenantResource = Optional.ofNullable(ansibleProjectJson.getString(AnsibleProject.varJsonAnsibleProject(AnsibleProject.VAR_tenantResource, patch))).orElse(o.getTenantResource());
        Tenant.fqTenant(siteRequest, Tenant.VAR_tenantResource, tenantResource).onSuccess(tenant -> {
          try {
            if(tenant == null) {
              RuntimeException ex = new RuntimeException(String.format("Could not find a matching tenant %s", tenantResource));
              LOG.error(ex.getMessage(), ex);
              promise.fail(ex);
            } else if(tenant.getAapOrganizationId() == null) {
              RuntimeException ex = new RuntimeException(String.format("The tenant %s doesn't have an AAP Organization ID number", tenantResource));
              LOG.error(ex.getMessage(), ex);
              promise.fail(ex);
            } else {
              Long aapOrganizationId = Optional.ofNullable(Optional.ofNullable(ansibleProjectJson.getLong(AnsibleProject.varJsonAnsibleProject(AnsibleProject.VAR_aapOrganizationId, patch))).orElse(o.getAapOrganizationId()))
                  .orElse(Optional.ofNullable(tenant).map(t -> t.getAapOrganizationId()).orElse(1L));
              ansibleProjectJson.put(AnsibleProject.varJsonAnsibleProject(AnsibleProject.VAR_aapOrganizationId, patch), aapOrganizationId.toString());

              String sourceControlType = Optional.ofNullable(Optional.ofNullable(ansibleProjectJson.getString(AnsibleProject.varJsonAnsibleProject(AnsibleProject.VAR_sourceControlType, patch))).orElse(o.getSourceControlType())).orElse("git");
              ansibleProjectJson.put(AnsibleProject.varJsonAnsibleProject(AnsibleProject.VAR_sourceControlType, patch), sourceControlType);
              String sourceControlUrl = Optional.ofNullable(Optional.ofNullable(ansibleProjectJson.getString(AnsibleProject.varJsonAnsibleProject(AnsibleProject.VAR_sourceControlUrl, patch))).orElse(o.getSourceControlUrl())).orElse("");
              ansibleProjectJson.put(AnsibleProject.varJsonAnsibleProject(AnsibleProject.VAR_sourceControlUrl, patch), sourceControlUrl);
              String sourceControlBranch = Optional.ofNullable(Optional.ofNullable(ansibleProjectJson.getString(AnsibleProject.varJsonAnsibleProject(AnsibleProject.VAR_sourceControlBranch, patch))).orElse(o.getSourceControlBranch())).orElse("");
              ansibleProjectJson.put(AnsibleProject.varJsonAnsibleProject(AnsibleProject.VAR_sourceControlBranch, patch), sourceControlBranch);
              String ansibleProjectName = Optional.ofNullable(Optional.ofNullable(ansibleProjectJson.getString(AnsibleProject.varJsonAnsibleProject(AnsibleProject.VAR_ansibleProjectName, patch))).orElse(o.getAnsibleProjectName()))
                  .orElse(Optional.ofNullable(sourceControlUrl).map(url -> StringUtils.substringBefore(StringUtils.substringAfterLast(url, "/"), ".")).orElse(null));
              ansibleProjectJson.put(AnsibleProject.varJsonAnsibleProject(AnsibleProject.VAR_ansibleProjectName, patch), ansibleProjectName);
              String ansibleProjectId = Optional.ofNullable(ansibleProjectJson.getString(AnsibleProject.varJsonAnsibleProject(AnsibleProject.VAR_ansibleProjectId, patch))).orElse(AnsibleProject.toId(ansibleProjectName));
              ansibleProjectJson.put(AnsibleProject.varJsonAnsibleProject(AnsibleProject.VAR_ansibleProjectId, patch), ansibleProjectId);
              String ansibleProjectResource = String.format("%s-%s-%s", tenantResource, AnsibleProject.CLASS_AUTH_RESOURCE, ansibleProjectId);
              ansibleProjectJson.put(AnsibleProject.varJsonAnsibleProject(AnsibleProject.VAR_ansibleProjectResource, patch), ansibleProjectResource);
              Long aapProjectId = Optional.ofNullable(ansibleProjectJson.getString(AnsibleProject.varJsonAnsibleProject(AnsibleProject.VAR_aapProjectId, patch))).map(s -> Long.parseLong(s)).orElse(o.getAapProjectId());
              ansibleProjectJson.put(AnsibleProject.varJsonAnsibleProject(AnsibleProject.VAR_aapProjectId, patch), Optional.ofNullable(aapProjectId).map(id -> id.toString()).orElse(null));
              String ansibleProjectDescription = Optional.ofNullable(ansibleProjectJson.getString(AnsibleProject.varJsonAnsibleProject(AnsibleProject.VAR_ansibleProjectDescription, patch))).orElse(o.getAnsibleProjectDescription());
              ansibleProjectJson.put(AnsibleProject.varJsonAnsibleProject(AnsibleProject.VAR_ansibleProjectDescription, patch), ansibleProjectDescription);
              promise.complete(ansibleProjectJson);
            }
          } catch(Exception ex) {
            LOG.error(String.format("Updating Sensu host failed. "), ex);
            promise.fail(ex);
          }
        }).onFailure(ex -> {
          promise.fail(ex);
        });
      }
    } catch(Exception ex) {
      LOG.error(String.format("Updating AAP ansibleProject failed. "), ex);
      promise.fail(ex);
    }
    return promise.future();
  }

  public static Future<JsonObject> putimportAnsibleProjectAsync(JsonObject config, Vertx vertx, JsonObject body) {
    Promise<JsonObject> promise = Promise.promise();
    try {
      JsonObject pageParams = new JsonObject();
      pageParams.put("scopes", new JsonArray().add("GET").add("POST").add("PATCH"));
      pageParams.put("body", body);
      pageParams.put("path", new JsonObject());
      pageParams.put("cookie", new JsonObject());
      pageParams.put("query", new JsonObject().put("softCommit", true).put("q", "*:*"));
      JsonObject pageContext = new JsonObject().put("params", pageParams);
      JsonObject pageRequest = new JsonObject().put("context", pageContext);
      String ansibleProjectResource = body.getString(AnsibleProject.VAR_ansibleProjectResource);

      vertx.eventBus().request(AnsibleProject.CLASS_API_ADDRESS_AnsibleProject, pageRequest, new DeliveryOptions()
          .setSendTimeout(config.getLong(ComputateConfigKeys.VERTX_MAX_EVENT_LOOP_EXECUTE_TIME) * 1000)
          .addHeader("action", String.format("putimport%sFuture", AnsibleProject.CLASS_SIMPLE_NAME))
          ).onSuccess(message -> {
        LOG.info(String.format("Imported %s Ansible project", ansibleProjectResource));
        promise.complete();
      }).onFailure(ex -> {
        LOG.error(String.format(importDataFail, AnsibleProject.CLASS_SIMPLE_NAME), ex);
        promise.fail(ex);
      });
    } catch(Exception ex) {
      LOG.error(String.format(importDataFail, AnsibleProject.CLASS_SIMPLE_NAME), ex);
      promise.fail(ex);
    }
    return promise.future();
  }

  public static Future<JsonObject> aapUpsertAnsibleProject(JsonObject config, WebClient webClient, AnsibleProject project, Boolean inheritPrimaryKey, Boolean patch, JsonObject ansibleProjectJson) {
    Promise<JsonObject> promise = Promise.promise();
    try {
      if(ansibleProjectJson == null) {
        promise.complete();
      } else {
        String ansibleProjectName = Optional.ofNullable(ansibleProjectJson.getString(AnsibleProject.varJsonAnsibleProject(AnsibleProject.VAR_ansibleProjectName, patch))).orElse(Optional.ofNullable(project).map(p -> p.getAnsibleProjectName()).orElse(null));
        String ansibleProjectDescription = Optional.ofNullable(ansibleProjectJson.getString(AnsibleProject.varJsonAnsibleProject(AnsibleProject.VAR_ansibleProjectDescription, patch))).orElse(Optional.ofNullable(project).map(p -> p.getAnsibleProjectDescription()).orElse(null));
        Long aapProjectId = Optional.ofNullable(ansibleProjectJson.getString(AnsibleProject.varJsonAnsibleProject(AnsibleProject.VAR_aapProjectId, patch))).map(id -> Long.parseLong(id)).orElse(Optional.ofNullable(project).map(p -> p.getAapProjectId()).orElse(null));
        Long aapOrganizationId = Optional.ofNullable(ansibleProjectJson.getString(AnsibleProject.varJsonAnsibleProject(AnsibleProject.VAR_aapOrganizationId, patch))).map(id -> Long.parseLong(id)).orElse(Optional.ofNullable(project).map(p -> p.getAapOrganizationId()).orElse(null));
        String sourceControlType = Optional.ofNullable(ansibleProjectJson.getString(AnsibleProject.varJsonAnsibleProject(AnsibleProject.VAR_sourceControlType, patch))).orElse(Optional.ofNullable(project).map(p -> p.getSourceControlType()).orElse(null));
        String sourceControlUrl = Optional.ofNullable(ansibleProjectJson.getString(AnsibleProject.varJsonAnsibleProject(AnsibleProject.VAR_sourceControlUrl, patch))).orElse(Optional.ofNullable(project).map(p -> p.getSourceControlUrl()).orElse(null));
        String sourceControlBranch = Optional.ofNullable(ansibleProjectJson.getString(AnsibleProject.varJsonAnsibleProject(AnsibleProject.VAR_sourceControlBranch, patch))).orElse(Optional.ofNullable(project).map(p -> p.getSourceControlBranch()).orElse(null));

        Integer aapPort = Integer.parseInt(config.getString(ConfigKeys.AAP_PORT));
        String aapHostName = config.getString(ConfigKeys.AAP_HOST_NAME);
        Boolean aapSsl = Boolean.parseBoolean(config.getString(ConfigKeys.AAP_SSL));
        String aapUri = patch ? String.format("/api/controller/v2/projects/%s/", aapProjectId) : "/api/controller/v2/projects/";
        String aapUserName = config.getString(ConfigKeys.AAP_USER_NAME);
        String aapPassword = config.getString(ConfigKeys.AAP_PASSWORD);

        JsonObject body = new JsonObject();
        body.put("name", ansibleProjectName);
        if(ansibleProjectDescription != null)
          body.put("description", ansibleProjectDescription);
        body.put("organization", aapOrganizationId);
        body.put("scm_type", sourceControlType);
        body.put("scm_url", sourceControlUrl);
        body.put("scm_branch", sourceControlBranch);
        body.put("scm_update_on_launch", true);

        if(StringUtils.isEmpty(ansibleProjectName)) {
          RuntimeException ex = new RuntimeException("Missing ansibleProject name");
          LOG.error(ex.getMessage(), ex);
          promise.fail(ex);
        } else {
          if(patch) {
            webClient.patch(aapPort, aapHostName, aapUri).ssl(aapSsl)
                .putHeader("Content-Type", "application/json")
                .basicAuthentication(aapUserName, aapPassword)
                .sendJsonObject(body)
                .expecting(HttpResponseExpectation.SC_OK)
                .onSuccess(ansibleProjectResponse -> {
              JsonObject responseBody = ansibleProjectResponse.bodyAsJsonObject();
              String aapProjectId2 = responseBody.getString("id");
              ansibleProjectJson.put(AnsibleProject.varJsonAnsibleProject(AnsibleProject.VAR_aapProjectId, patch), aapProjectId2);
              promise.complete();
            }).onFailure(ex -> {
              LOG.error(String.format("Updating AAP ansibleProject failed. "), ex);
              promise.fail(ex);
            });
          } else {
            webClient.post(aapPort, aapHostName, aapUri).ssl(aapSsl)
                .putHeader("Content-Type", "application/json")
                .basicAuthentication(aapUserName, aapPassword)
                .sendJsonObject(body)
                .expecting(HttpResponseExpectation.SC_CREATED)
                .onSuccess(ansibleProjectResponse -> {
              JsonObject responseBody = ansibleProjectResponse.bodyAsJsonObject();
              String aapProjectId2 = responseBody.getString("id");
              ansibleProjectJson.put(AnsibleProject.varJsonAnsibleProject(AnsibleProject.VAR_aapProjectId, patch), aapProjectId2);
              promise.complete(responseBody);
            }).onFailure(ex -> {
              LOG.error(String.format("Updating AAP ansibleProject failed. "), ex);
              promise.fail(ex);
            });
          }
        }
      }
    } catch(Exception ex) {
      LOG.error(String.format("Updating AAP ansibleProject failed. "), ex);
      promise.fail(ex);
    }
    return promise.future();
  }

  public static Future<JsonArray> aapQueryProjectPlaybooks(JsonObject config, WebClient webClient, AnsibleProject o, Boolean inheritPrimaryKey, Boolean patch, JsonObject ansibleProjectJson) {
    Promise<JsonArray> promise = Promise.promise();
    try {
      if(ansibleProjectJson == null) {
        promise.complete();
      } else {
        Long aapProjectId = Optional.ofNullable(ansibleProjectJson.getString(AnsibleProject.varJsonAnsibleProject(AnsibleProject.VAR_aapProjectId, patch))).map(id -> Long.parseLong(id)).orElse(null);

        Integer aapPort = Integer.parseInt(config.getString(ConfigKeys.AAP_PORT));
        String aapHostName = config.getString(ConfigKeys.AAP_HOST_NAME);
        Boolean aapSsl = Boolean.parseBoolean(config.getString(ConfigKeys.AAP_SSL));
        String aapUri = String.format("/api/controller/v2/projects/%s/playbooks/", aapProjectId);
        String aapUserName = config.getString(ConfigKeys.AAP_USER_NAME);
        String aapPassword = config.getString(ConfigKeys.AAP_PASSWORD);

        if(aapProjectId == null) {
          RuntimeException ex = new RuntimeException("Missing aapProjectId");
          LOG.error(ex.getMessage(), ex);
          promise.fail(ex);
        } else {
          webClient.get(aapPort, aapHostName, aapUri).ssl(aapSsl)
              .putHeader("Content-Type", "application/json")
              .basicAuthentication(aapUserName, aapPassword)
              .send()
              .expecting(HttpResponseExpectation.SC_OK)
              .onSuccess(ansibleProjectResponse -> {
            JsonArray ansiblePlaybooks = ansibleProjectResponse.bodyAsJsonArray();
            ansibleProjectJson.put(AnsibleProject.varJsonAnsibleProject(AnsibleProject.VAR_ansiblePlaybooks, patch), ansiblePlaybooks);
            promise.complete(ansiblePlaybooks);
          }).onFailure(ex -> {
            LOG.error(String.format("Updating AAP ansibleProject failed. "), ex);
            promise.fail(ex);
          });
        }
      }
    } catch(Exception ex) {
      LOG.error(String.format("Updating AAP ansibleProject failed. "), ex);
      promise.fail(ex);
    }
    return promise.future();
  }

  @Override
  public Future<AnsibleProject> sqlPOSTAnsibleProject(AnsibleProject o, Boolean inheritPrimaryKey) {
    Promise<AnsibleProject> promise = Promise.promise();
    aapUpsertParams(o, inheritPrimaryKey, false).onSuccess(ansibleProjectJson -> {
      aapUpsertAnsibleProject(config, webClient, o, inheritPrimaryKey, false, ansibleProjectJson).onSuccess(a -> {
        aapQueryProjectPlaybooks(config, webClient, o, inheritPrimaryKey, false, ansibleProjectJson).onSuccess(ansiblePlaybooks -> {
          super.sqlPOSTAnsibleProject(o, inheritPrimaryKey).onSuccess(o2 -> {
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
    return promise.future();
  }

  @Override
  public Future<AnsibleProject> sqlPATCHAnsibleProject(AnsibleProject o, Boolean inheritPrimaryKey) {
    Promise<AnsibleProject> promise = Promise.promise();
    aapUpsertParams(o, inheritPrimaryKey, false).onSuccess(ansibleProjectJson -> {
      aapUpsertAnsibleProject(config, webClient, o, inheritPrimaryKey, true, ansibleProjectJson).onSuccess(a -> {
        aapQueryProjectPlaybooks(config, webClient, o, inheritPrimaryKey, true, ansibleProjectJson).onSuccess(ansiblePlaybooks -> {
          super.sqlPATCHAnsibleProject(o, inheritPrimaryKey).onSuccess(o2 -> {
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
    return promise.future();
  }

  public Future<Void> aapDeleteAnsibleProject(AnsibleProject o) {
    Promise<Void> promise = Promise.promise();
    try {
      SiteRequest siteRequest = o.getSiteRequest_();
      ServiceRequest serviceRequest = siteRequest.getServiceRequest();
      if(Optional.ofNullable(serviceRequest.getParams()).map(p -> p.getJsonObject("query")).map( q -> q.getJsonArray("var")).orElse(new JsonArray()).stream().filter(s -> "refresh:false".equals(s)).count() > 0L) {
        promise.complete();
      } else {
        Long aapProjectId = o.getAapProjectId();

        Integer aapPort = Integer.parseInt(config.getString(ConfigKeys.AAP_PORT));
        String aapHostName = config.getString(ConfigKeys.AAP_HOST_NAME);
        Boolean aapSsl = Boolean.parseBoolean(config.getString(ConfigKeys.AAP_SSL));
        String aapUri = String.format("/api/controller/v2/projects/%s/", aapProjectId);
        String aapUserName = config.getString(ConfigKeys.AAP_USER_NAME);
        String aapPassword = config.getString(ConfigKeys.AAP_PASSWORD);

        if(aapProjectId == null) {
          RuntimeException ex = new RuntimeException("Missing AAP project ID");
          LOG.warn(ex.getMessage(), ex);
          promise.complete();
        } else {
          webClient.delete(aapPort, aapHostName, aapUri).ssl(aapSsl)
              .putHeader("Content-Type", "application/json")
              .basicAuthentication(aapUserName, aapPassword)
              .send()
              .expecting(HttpResponseExpectation.SC_NO_CONTENT.or(HttpResponseExpectation.SC_NOT_FOUND))
              .onSuccess(ansibleProjectResponse -> {
            promise.complete();
          }).onFailure(ex -> {
            LOG.error(String.format("Deleting AAP ansibleProject failed. "), ex);
            promise.fail(ex);
          });
        }
      }
    } catch(Exception ex) {
      LOG.error(String.format("Deleting AAP ansibleProject failed. "), ex);
      promise.fail(ex);
    }
    return promise.future();
  }

  @Override
  public Future<Void> sqlDELETEFilterAnsibleProject(AnsibleProject o) {
    Promise<Void> promise = Promise.promise();
    aapDeleteAnsibleProject(o).onSuccess(a -> {
      super.sqlDELETEFilterAnsibleProject(o).onSuccess(o2 -> {
        promise.complete(o2);
      }).onFailure(ex -> {
        promise.fail(ex);
      });
    }).onFailure(ex -> {
      promise.fail(ex);
    });
    return promise.future();
  }

  @Override
  public Future<Void> sqlDELETEAnsibleProject(AnsibleProject o) {
    Promise<Void> promise = Promise.promise();
    aapDeleteAnsibleProject(o).onSuccess(a -> {
      super.sqlDELETEAnsibleProject(o).onSuccess(o2 -> {
        promise.complete(o2);
      }).onFailure(ex -> {
        promise.fail(ex);
      });
    }).onFailure(ex -> {
      promise.fail(ex);
    });
    return promise.future();
  }
}
