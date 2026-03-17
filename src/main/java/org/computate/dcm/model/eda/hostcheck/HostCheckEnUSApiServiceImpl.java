package org.computate.dcm.model.eda.hostcheck;

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
import org.computate.dcm.model.eda.jobtemplate.JobTemplate;
import org.computate.dcm.request.SiteRequest;

/**
 * Translate: false
 **/
public class HostCheckEnUSApiServiceImpl extends HostCheckEnUSGenApiServiceImpl {

  public Future<JsonObject> upsertParams(HostCheck o, Boolean inheritPrimaryKey, Boolean patch) {
    Promise<JsonObject> promise = Promise.promise();
    try {
      SiteRequest siteRequest = o.getSiteRequest_();
      ServiceRequest serviceRequest = siteRequest.getServiceRequest();
      if(Optional.ofNullable(serviceRequest.getParams()).map(p -> p.getJsonObject("query")).map( q -> q.getJsonArray("var")).orElse(new JsonArray()).stream().filter(s -> "refresh:false".equals(s)).count() > 0L) {
        promise.complete();
      } else {
        JsonObject hostCheckJson = o.getSiteRequest_().getJsonObject();
        String jobTemplateResource = Optional.ofNullable(hostCheckJson.getString(HostCheck.varJsonHostCheck(HostCheck.VAR_jobTemplateResource, patch))).orElse(o.getJobTemplateResource());
        JobTemplate.fqJobTemplate(siteRequest, JobTemplate.VAR_jobTemplateResource, jobTemplateResource).onSuccess(jobTemplate -> {
          try {
            if(jobTemplate == null) {
              RuntimeException ex = new RuntimeException(String.format("Could not find a matching job template %s", jobTemplateResource));
              LOG.error(ex.getMessage(), ex);
              promise.fail(ex);
            } else if(jobTemplate.getAapTemplateId() == null) {
              RuntimeException ex = new RuntimeException(String.format("The job template %s doesn't have an AAP Template ID number", jobTemplateResource));
              LOG.error(ex.getMessage(), ex);
              promise.fail(ex);
            } else {
              String tenantResource = Optional.ofNullable(Optional.ofNullable(hostCheckJson.getString(HostCheck.varJsonHostCheck(HostCheck.VAR_tenantResource, patch))).orElse(o.getTenantResource())).orElse(jobTemplate.getTenantResource());
              hostCheckJson.put(HostCheck.varJsonHostCheck(HostCheck.VAR_tenantResource, patch), tenantResource);
              String tenantId = Optional.ofNullable(Optional.ofNullable(hostCheckJson.getString(HostCheck.varJsonHostCheck(HostCheck.VAR_tenantId, patch))).orElse(o.getTenantId())).orElse(jobTemplate.getTenantId());
              hostCheckJson.put(HostCheck.varJsonHostCheck(HostCheck.VAR_tenantId, patch), tenantId);
              Long aapOrganizationId = jobTemplate.getAapOrganizationId();
              hostCheckJson.put(HostCheck.varJsonHostCheck(HostCheck.VAR_aapOrganizationId, patch), aapOrganizationId.toString());

              String jobTemplateId = Optional.ofNullable(Optional.ofNullable(hostCheckJson.getString(HostCheck.varJsonHostCheck(HostCheck.VAR_jobTemplateId, patch))).orElse(o.getJobTemplateId())).orElse(jobTemplate.getJobTemplateId());
              hostCheckJson.put(HostCheck.varJsonHostCheck(HostCheck.VAR_jobTemplateId, patch), jobTemplateId);
              Long aapTemplateId = jobTemplate.getAapTemplateId();
              hostCheckJson.put(HostCheck.varJsonHostCheck(HostCheck.VAR_aapTemplateId, patch), aapTemplateId.toString());

              String checkName = Optional.ofNullable(hostCheckJson.getString(HostCheck.varJsonHostCheck(HostCheck.VAR_checkName, patch))).orElse(o.getCheckName());
              hostCheckJson.put(HostCheck.varJsonHostCheck(HostCheck.VAR_checkName, patch), checkName);
              String checkId = Optional.ofNullable(hostCheckJson.getString(HostCheck.varJsonHostCheck(HostCheck.VAR_checkId, patch))).orElse(HostCheck.toId(checkName));
              hostCheckJson.put(HostCheck.varJsonHostCheck(HostCheck.VAR_checkId, patch), checkId);
              String checkResource = String.format("%s-%s-%s", tenantResource, HostCheck.CLASS_AUTH_RESOURCE, checkId);
              hostCheckJson.put(HostCheck.varJsonHostCheck(HostCheck.VAR_checkResource, patch), checkResource);
              String checkDescription = Optional.ofNullable(hostCheckJson.getString(HostCheck.varJsonHostCheck(HostCheck.VAR_checkDescription, patch) )).orElse(o.getCheckDescription());
              hostCheckJson.put(HostCheck.varJsonHostCheck(HostCheck.VAR_checkDescription, patch), checkDescription);
              String checkNamespace = Optional.ofNullable(Optional.ofNullable(hostCheckJson.getString(HostCheck.varJsonHostCheck(HostCheck.VAR_checkNamespace, patch))).orElse(o.getCheckNamespace())).orElse(tenantId);
              hostCheckJson.put(HostCheck.varJsonHostCheck(HostCheck.VAR_checkNamespace, patch), checkNamespace);
              String checkCommand = Optional.ofNullable(hostCheckJson.getString(HostCheck.varJsonHostCheck(HostCheck.VAR_checkCommand, patch))).orElse(o.getCheckCommand());
              hostCheckJson.put(HostCheck.varJsonHostCheck(HostCheck.VAR_checkCommand, patch), checkCommand);
              Integer checkInterval = Integer.parseInt(Optional.ofNullable(hostCheckJson.getString(HostCheck.varJsonHostCheck(HostCheck.VAR_checkInterval, patch))).orElse(Optional.ofNullable(o.getCheckInterval()).map(interval -> interval.toString()).orElse("300")));
              hostCheckJson.put(HostCheck.varJsonHostCheck(HostCheck.VAR_checkInterval, patch), checkInterval.toString());
              Boolean checkPublished = Optional.ofNullable(Optional.ofNullable(hostCheckJson.getBoolean(HostCheck.varJsonHostCheck(HostCheck.VAR_checkPublished, patch))).orElse(o.getCheckPublished())).orElse(true);
              hostCheckJson.put(HostCheck.varJsonHostCheck(HostCheck.VAR_checkPublished, patch), checkPublished);
              JsonArray eventSubscriptions = Optional.ofNullable(Optional.ofNullable(hostCheckJson.getJsonArray(HostCheck.varJsonHostCheck(HostCheck.VAR_eventSubscriptions, patch))).orElse(new JsonArray(o.getEventSubscriptions()))).map(array -> array.size() == 0 ? null : array).orElse(new JsonArray().add(checkId));
              hostCheckJson.put(HostCheck.varJsonHostCheck(HostCheck.VAR_eventSubscriptions, patch), eventSubscriptions);
              JsonArray eventHandlers = Optional.ofNullable(Optional.ofNullable(hostCheckJson.getJsonArray(HostCheck.varJsonHostCheck(HostCheck.VAR_eventHandlers, patch))).orElse(new JsonArray(o.getEventHandlers()))).map(array -> array.size() == 0 ? null : array).orElse(new JsonArray().add("sensu-kafka-handler"));
              hostCheckJson.put(HostCheck.varJsonHostCheck(HostCheck.VAR_eventHandlers, patch), eventHandlers);

              promise.complete(hostCheckJson);
            }
          } catch(Exception ex) {
            LOG.error(String.format("Updating Sensu host check failed. "), ex);
            promise.fail(ex);
          }
        }).onFailure(ex -> {
          promise.tryFail(ex);
        });
      }
    } catch(Exception ex) {
      LOG.error(String.format("Updating AAP host check failed. "), ex);
      promise.fail(ex);
    }
    return promise.future();
  }

  public Future<Void> sensuUpsertHostCheck(HostCheck o, Boolean inheritPrimaryKey, Boolean patch, JsonObject hostCheckJson) {
    Promise<Void> promise = Promise.promise();
    try {
      SiteRequest siteRequest = o.getSiteRequest_();
      ServiceRequest serviceRequest = siteRequest.getServiceRequest();
      if(Optional.ofNullable(serviceRequest.getParams()).map(p -> p.getJsonObject("query")).map( q -> q.getJsonArray("var")).orElse(new JsonArray()).stream().filter(s -> "refresh:false".equals(s)).count() > 0L) {
        promise.complete();
      } else {
        String checkName = hostCheckJson.getString(HostCheck.varJsonHostCheck(HostCheck.VAR_checkName, patch));
        String checkNamespace = hostCheckJson.getString(HostCheck.varJsonHostCheck(HostCheck.VAR_checkNamespace, patch));
        String checkCommand = hostCheckJson.getString(HostCheck.varJsonHostCheck(HostCheck.VAR_checkCommand, patch));
        Integer checkInterval = Integer.parseInt(hostCheckJson.getString(HostCheck.varJsonHostCheck(HostCheck.VAR_checkInterval, patch)));
        Boolean checkPublished = Boolean.parseBoolean(hostCheckJson.getString(HostCheck.varJsonHostCheck(HostCheck.VAR_checkPublished, patch)));
        JsonArray eventSubscriptions = hostCheckJson.getJsonArray(HostCheck.varJsonHostCheck(HostCheck.VAR_eventSubscriptions, patch));
        JsonArray eventHandlers = hostCheckJson.getJsonArray(HostCheck.varJsonHostCheck(HostCheck.VAR_eventHandlers, patch));

        Integer sensuPort = Integer.parseInt(config.getString(ConfigKeys.SENSU_PORT));
        String sensuHostName = config.getString(ConfigKeys.SENSU_HOST_NAME);
        Boolean sensuSsl = Boolean.parseBoolean(config.getString(ConfigKeys.SENSU_SSL));
        String sensuUri = String.format("/api/core/v2/namespaces/%s/checks/%s", urlEncode(checkNamespace), urlEncode(checkName));
        String sensuUserName = config.getString(ConfigKeys.SENSU_USER_NAME);
        String sensuPassword = config.getString(ConfigKeys.SENSU_PASSWORD);

        JsonObject body = new JsonObject();
        body.put("command", checkCommand);
        body.put("interval", checkInterval);
        body.put("publish", checkPublished);
        body.put("handlers", eventHandlers);
        body.put("subscriptions", eventSubscriptions);
        JsonObject metadata = new JsonObject();
        metadata.put("name", checkName);
        metadata.put("namespace", checkNamespace);
        body.put("metadata", metadata);

        if(StringUtils.isEmpty(checkName)) {
          RuntimeException ex = new RuntimeException("Missing host name");
          LOG.error(ex.getMessage(), ex);
          promise.fail(ex);
        } else {
          webClient.get(sensuPort, sensuHostName, "/auth").ssl(sensuSsl)
              .authentication(new UsernamePasswordCredentials(sensuUserName, sensuPassword))
              .send()
              .expecting(HttpResponseExpectation.SC_OK)
              .onSuccess(auth -> {
            webClient.put(sensuPort, sensuHostName, sensuUri).ssl(sensuSsl)
                .putHeader("Authorization", String.format("Key %s", auth.bodyAsJsonObject().getString("access_token")))
                .sendJsonObject(body)
                .expecting(HttpResponseExpectation.SC_OK.or(HttpResponseExpectation.SC_CREATED))
                .onSuccess(HostCheckResponse -> {
              promise.complete();
            }).onFailure(ex -> {
              LOG.error(String.format("Updating Sensu host failed. "), ex);
              promise.fail(ex);
            });
          }).onFailure(ex -> {
            LOG.error(String.format("Requesting Sensu admin token failed. "), ex);
            promise.fail(ex);
          });
        }
      }
    } catch(Exception ex) {
      LOG.error(String.format("Updating Sensu host failed. "), ex);
      promise.fail(ex);
    }
    return promise.future();
  }

  @Override
  public Future<HostCheck> sqlPOSTHostCheck(HostCheck o, Boolean inheritPrimaryKey) {
    Promise<HostCheck> promise = Promise.promise();
    upsertParams(o, inheritPrimaryKey, false).onSuccess(hostCheckJson -> {
      sensuUpsertHostCheck(o, inheritPrimaryKey, false, hostCheckJson).onSuccess(b -> {
        super.sqlPOSTHostCheck(o, inheritPrimaryKey).onSuccess(o2 -> {
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
  public Future<HostCheck> sqlPATCHHostCheck(HostCheck o, Boolean inheritPrimaryKey) {
    Promise<HostCheck> promise = Promise.promise();
    upsertParams(o, inheritPrimaryKey, true).onSuccess(hostCheckJson -> {
      sensuUpsertHostCheck(o, inheritPrimaryKey, true, hostCheckJson).onSuccess(a -> {
        super.sqlPATCHHostCheck(o, inheritPrimaryKey).onSuccess(o2 -> {
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

  public Future<Void> sensuDeleteHostCheck(HostCheck o) {
    Promise<Void> promise = Promise.promise();
    try {
      String checkName = o.getCheckName();

      Integer sensuPort = Integer.parseInt(config.getString(ConfigKeys.SENSU_PORT));
      String sensuHostName = config.getString(ConfigKeys.SENSU_HOST_NAME);
      Boolean sensuSsl = Boolean.parseBoolean(config.getString(ConfigKeys.SENSU_SSL));
      String sensuUri = String.format("/api/core/v2/namespaces/default/checks/%s", checkName);
      String sensuUserName = config.getString(ConfigKeys.SENSU_USER_NAME);
      String sensuPassword = config.getString(ConfigKeys.SENSU_PASSWORD);

      webClient.get(sensuPort, sensuHostName, "/auth").ssl(sensuSsl)
          .authentication(new UsernamePasswordCredentials(sensuUserName, sensuPassword))
          .send()
          .expecting(HttpResponseExpectation.SC_OK)
          .onSuccess(auth -> {
        webClient.delete(sensuPort, sensuHostName, sensuUri).ssl(sensuSsl)
            .putHeader("Authorization", String.format("Key %s", auth.bodyAsJsonObject().getString("access_token")))
            .send()
            .expecting(HttpResponseExpectation.SC_NO_CONTENT.or(HttpResponseExpectation.SC_NOT_FOUND))
            .onSuccess(HostCheckResponse -> {
          promise.complete();
        }).onFailure(ex -> {
          LOG.error(String.format("Deleting Sensu host failed. "), ex);
          promise.fail(ex);
        });
      }).onFailure(ex -> {
        LOG.error(String.format("Requesting Sensu admin token failed. "), ex);
        promise.fail(ex);
      });
    } catch(Exception ex) {
      LOG.error(String.format("Deleting Sensu host failed. "), ex);
      promise.fail(ex);
    }
    return promise.future();
  }

  @Override
  public Future<Void> sqlDELETEFilterHostCheck(HostCheck o) {
    Promise<Void> promise = Promise.promise();
    sensuDeleteHostCheck(o).onSuccess(a -> {
      super.sqlDELETEFilterHostCheck(o).onSuccess(c -> {
        promise.complete();
      }).onFailure(ex -> {
        promise.fail(ex);
      });
    }).onFailure(ex -> {
      promise.fail(ex);
    });
    return promise.future();
  }

  @Override
  public Future<Void> sqlDELETEHostCheck(HostCheck o) {
    Promise<Void> promise = Promise.promise();
    sensuDeleteHostCheck(o).onSuccess(a -> {
      super.sqlDELETEHostCheck(o).onSuccess(c -> {
        promise.complete();
      }).onFailure(ex -> {
        promise.fail(ex);
      });
    }).onFailure(ex -> {
      promise.fail(ex);
    });
    return promise.future();
  }
}
