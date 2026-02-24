package org.computate.dcm.model.eda.ansibleproject;

import io.vertx.ext.auth.authorization.AuthorizationProvider;
import io.vertx.ext.auth.oauth2.OAuth2Auth;
import io.vertx.ext.web.client.WebClient;
import io.vertx.core.Vertx;
import io.vertx.core.WorkerExecutor;
import io.vertx.core.json.JsonObject;
import io.vertx.sqlclient.Pool;
import org.computate.vertx.openapi.ComputateOAuth2AuthHandlerImpl;
import io.vertx.kafka.client.producer.KafkaProducer;
import io.vertx.mqtt.MqttClient;
import io.vertx.amqp.AmqpSender;
import io.vertx.rabbitmq.RabbitMQClient;
import com.hubspot.jinjava.Jinjava;
import io.vertx.ext.web.api.service.ServiceRequest;
import io.vertx.core.Future;
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
        Tenant.fq(siteRequest, Tenant.VAR_tenantResource, tenantResource).onSuccess(tenant -> {
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

  public Future<Void> aapUpsertAnsibleProject(AnsibleProject o, Boolean inheritPrimaryKey, Boolean patch, JsonObject ansibleProjectJson) {
    Promise<Void> promise = Promise.promise();
    try {
      if(ansibleProjectJson == null) {
        promise.complete();
      } else {
        String ansibleProjectName = ansibleProjectJson.getString(AnsibleProject.varJsonAnsibleProject(AnsibleProject.VAR_ansibleProjectName, patch));
        String ansibleProjectDescription = ansibleProjectJson.getString(AnsibleProject.varJsonAnsibleProject(AnsibleProject.VAR_ansibleProjectDescription, patch));
        Long aapProjectId = Optional.ofNullable(ansibleProjectJson.getString(AnsibleProject.varJsonAnsibleProject(AnsibleProject.VAR_aapProjectId, patch))).map(id -> Long.parseLong(id)).orElse(null);
        Long aapOrganizationId = Optional.ofNullable(ansibleProjectJson.getString(AnsibleProject.varJsonAnsibleProject(AnsibleProject.VAR_aapOrganizationId, patch))).map(id -> Long.parseLong(id)).orElse(null);
        String sourceControlType = ansibleProjectJson.getString(AnsibleProject.varJsonAnsibleProject(AnsibleProject.VAR_sourceControlType, patch));
        String sourceControlUrl = ansibleProjectJson.getString(AnsibleProject.varJsonAnsibleProject(AnsibleProject.VAR_sourceControlUrl, patch));
        String sourceControlBranch = ansibleProjectJson.getString(AnsibleProject.varJsonAnsibleProject(AnsibleProject.VAR_sourceControlBranch, patch));

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
              ansibleProjectJson.put(AnsibleProject.VAR_aapProjectId, aapProjectId2);
              promise.complete();
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

  @Override
  public Future<AnsibleProject> sqlPOSTAnsibleProject(AnsibleProject o, Boolean inheritPrimaryKey) {
    Promise<AnsibleProject> promise = Promise.promise();
    aapUpsertParams(o, inheritPrimaryKey, false).onSuccess(ansibleProjectJson -> {
      aapUpsertAnsibleProject(o, inheritPrimaryKey, false, ansibleProjectJson).onSuccess(a -> {
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
    return promise.future();
  }

  @Override
  public Future<AnsibleProject> sqlPATCHAnsibleProject(AnsibleProject o, Boolean inheritPrimaryKey) {
    Promise<AnsibleProject> promise = Promise.promise();
    aapUpsertParams(o, inheritPrimaryKey, false).onSuccess(ansibleProjectJson -> {
      aapUpsertAnsibleProject(o, inheritPrimaryKey, true, ansibleProjectJson).onSuccess(a -> {
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
          LOG.error(ex.getMessage(), ex);
          promise.fail(ex);
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
