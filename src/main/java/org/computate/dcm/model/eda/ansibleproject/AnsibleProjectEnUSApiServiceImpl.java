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
import org.computate.dcm.request.SiteRequest;

/**
 * Translate: false
 **/
public class AnsibleProjectEnUSApiServiceImpl extends AnsibleProjectEnUSGenApiServiceImpl {

  public Future<Void> aapUpsertAnsibleProject(AnsibleProject o, Boolean inheritPrimaryKey, Boolean patch) {
    Promise<Void> promise = Promise.promise();
    try {
      SiteRequest siteRequest = o.getSiteRequest_();
      ServiceRequest serviceRequest = siteRequest.getServiceRequest();
      if(Optional.ofNullable(serviceRequest.getParams()).map(p -> p.getJsonObject("query")).map( q -> q.getJsonArray("var")).orElse(new JsonArray()).stream().filter(s -> "refresh:false".equals(s)).count() > 0L) {
        promise.complete();
      } else {
        JsonObject ansibleProjectJson = o.getSiteRequest_().getJsonObject();
        Long aapOrganizationId = Optional.ofNullable(Optional.ofNullable(ansibleProjectJson.getLong(patch ? "setAapOrganizationId": "aapOrganizationId")).orElse(o.getAapOrganizationId())).orElse(1L);
        String organizationId = Optional.ofNullable(Optional.ofNullable(ansibleProjectJson.getString(patch ? "setOrganizationId": "organizationId")).orElse(o.getOrganizationId())).orElse("");
        String sourceControlType = Optional.ofNullable(Optional.ofNullable(ansibleProjectJson.getString(patch ? "setSourceControlType": "sourceControlType")).orElse(o.getSourceControlType())).orElse("git");
        ansibleProjectJson.put(AnsibleProject.VAR_sourceControlType, sourceControlType);
        String sourceControlUrl = Optional.ofNullable(Optional.ofNullable(ansibleProjectJson.getString(patch ? "setSourceControlUrl": "sourceControlUrl")).orElse(o.getSourceControlUrl())).orElse("");
        String sourceControlBranch = Optional.ofNullable(Optional.ofNullable(ansibleProjectJson.getString(patch ? "setSourceControlBranch": "sourceControlBranch")).orElse(o.getSourceControlBranch())).orElse("");
        ansibleProjectJson.put(AnsibleProject.VAR_sourceControlBranch, sourceControlBranch);
        String ansibleProjectName = Optional.ofNullable(Optional.ofNullable(ansibleProjectJson.getString(patch ? "setAnsibleProjectName": "ansibleProjectName")).orElse(o.getAnsibleProjectName()))
            .orElse(Optional.ofNullable(sourceControlUrl).map(url -> StringUtils.substringBefore(StringUtils.substringAfterLast(url, "/"), ".")).orElse(null));
        ansibleProjectJson.put(AnsibleProject.VAR_ansibleProjectName, ansibleProjectName);
        String ansibleProjectId = Optional.ofNullable(ansibleProjectJson.getString(patch ? "setAnsibleProjectId": "ansibleProjectId")).orElse(AnsibleProject.toId(ansibleProjectName));
        ansibleProjectJson.put(AnsibleProject.VAR_ansibleProjectId, ansibleProjectId);
        Long aapProjectId = Optional.ofNullable(ansibleProjectJson.getString(patch ? "setAapProjectId": "aapProjectId")).map(s -> Long.parseLong(s)).orElse(o.getAapProjectId());
        String ansibleProjectDescription = Optional.ofNullable(ansibleProjectJson.getString(patch ? "setAnsibleProjectDescription": "ansibleProjectDescription")).orElse(o.getAnsibleProjectDescription());

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
    aapUpsertAnsibleProject(o, inheritPrimaryKey, false).onSuccess(a -> {
      super.sqlPOSTAnsibleProject(o, inheritPrimaryKey).onSuccess(o2 -> {
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
  public Future<AnsibleProject> sqlPATCHAnsibleProject(AnsibleProject o, Boolean inheritPrimaryKey) {
    Promise<AnsibleProject> promise = Promise.promise();
    aapUpsertAnsibleProject(o, inheritPrimaryKey, true).onSuccess(a -> {
      super.sqlPATCHAnsibleProject(o, inheritPrimaryKey).onSuccess(o2 -> {
        promise.complete(o2);
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
