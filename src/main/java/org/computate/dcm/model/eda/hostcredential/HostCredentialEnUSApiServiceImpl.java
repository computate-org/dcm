package org.computate.dcm.model.eda.hostcredential;

import io.vertx.ext.web.api.service.ServiceRequest;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.http.HttpResponseExpectation;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import java.util.Optional;
import org.apache.commons.lang3.StringUtils;
import org.computate.dcm.config.ConfigKeys;
import org.computate.dcm.model.eda.ansibleproject.AnsibleProject;
import org.computate.dcm.model.eda.jobtemplate.JobTemplate;
import org.computate.dcm.model.eda.jobtemplate.JobTemplateEnUSApiServiceImpl;
import org.computate.dcm.model.eda.tenant.Tenant;
import org.computate.dcm.request.SiteRequest;

/**
 * Translate: false
 **/
public class HostCredentialEnUSApiServiceImpl extends HostCredentialEnUSGenApiServiceImpl {

  public Future<Void> aapUpsertHostCredential(HostCredential o, Boolean inheritPrimaryKey, Boolean patch) {
    Promise<Void> promise = Promise.promise();
    try {
      SiteRequest siteRequest = o.getSiteRequest_();
      ServiceRequest serviceRequest = siteRequest.getServiceRequest();
      if(Optional.ofNullable(serviceRequest.getParams()).map(p -> p.getJsonObject("query")).map( q -> q.getJsonArray("var")).orElse(new JsonArray()).stream().filter(s -> "refresh:false".equals(s)).count() > 0L) {
        promise.complete();
      } else {
        JsonObject credentialJson = o.getSiteRequest_().getJsonObject();
        String tenantResource = Optional.ofNullable(credentialJson.getString(HostCredential.varJsonHostCredential(HostCredential.VAR_tenantResource, patch))).orElse(o.getTenantResource());
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
              String tenantId = Optional.ofNullable(Optional.ofNullable(credentialJson.getString(HostCredential.varJsonHostCredential(HostCredential.VAR_tenantId, patch))).orElse(o.getTenantId())).orElse(tenant.getTenantId());
              credentialJson.put(HostCredential.varJsonHostCredential(HostCredential.VAR_tenantId, patch), tenantId);

              Long aapOrganizationId = Optional.ofNullable(Optional.ofNullable(credentialJson.getLong(HostCredential.varJsonHostCredential(HostCredential.VAR_aapOrganizationId, patch))).orElse(o.getAapOrganizationId()))
                  .orElse(Optional.ofNullable(tenant).map(t -> t.getAapOrganizationId()).orElse(1L));
              credentialJson.put(HostCredential.varJsonHostCredential(HostCredential.VAR_aapOrganizationId, patch), aapOrganizationId.toString());

              String credentialName = Optional.ofNullable(credentialJson.getString(HostCredential.varJsonHostCredential(HostCredential.VAR_credentialName, patch))).orElse(o.getCredentialName());
              String credentialId = Optional.ofNullable(credentialJson.getString(HostCredential.varJsonHostCredential(HostCredential.VAR_credentialId, patch))).orElse(HostCredential.toId(credentialName));
              credentialJson.put(HostCredential.varJsonHostCredential(HostCredential.VAR_credentialId, patch), credentialId);
              String credentialResource = String.format("%s-%s-%s", tenantResource, HostCredential.CLASS_AUTH_RESOURCE, credentialId);
              credentialJson.put(HostCredential.varJsonHostCredential(HostCredential.VAR_credentialResource, patch), credentialResource);
              Long aapCredentialId = Optional.ofNullable(credentialJson.getString(HostCredential.varJsonHostCredential(HostCredential.VAR_aapCredentialId, patch))).map(s -> Long.parseLong(s)).orElse(o.getAapCredentialId());
              Long aapCredentialTypeId = Optional.ofNullable(credentialJson.getString(HostCredential.varJsonHostCredential(HostCredential.VAR_aapCredentialTypeId, patch))).map(s -> Long.parseLong(s)).orElse(Optional.ofNullable(o.getAapCredentialTypeId()).orElse(1L));
              String credentialDescription = Optional.ofNullable(credentialJson.getString(HostCredential.varJsonHostCredential(HostCredential.VAR_credentialDescription, patch))).orElse(o.getCredentialDescription());
            //   Integer aapCredentialTypeId = Integer.parseInt(Optional.ofNullable(Optional.ofNullable(credentialJson.getString(HostCredential.varJsonHostCredential(HostCredential.VAR_aapCredentialTypeId, patch))).orElse(Optional.ofNullable(o.getAapCredentialTypeId()).map(v -> v.toString()).orElse(null))).orElse("1"));
              String userName = Optional.ofNullable(Optional.ofNullable(credentialJson.getString(HostCredential.varJsonHostCredential(HostCredential.VAR_userName, patch))).orElse(o.getUserName())).orElse(null);
              String password = Optional.ofNullable(Optional.ofNullable(credentialJson.getString(HostCredential.varJsonHostCredential(HostCredential.VAR_password, patch))).orElse(o.getPassword())).orElse(null);
              String becomeMethod = Optional.ofNullable(Optional.ofNullable(credentialJson.getString(HostCredential.varJsonHostCredential(HostCredential.VAR_becomeMethod, patch))).orElse(o.getBecomeMethod())).orElse("sudo");
              String becomePassword = Optional.ofNullable(Optional.ofNullable(credentialJson.getString(HostCredential.varJsonHostCredential(HostCredential.VAR_becomePassword, patch))).orElse(o.getBecomePassword())).orElse(null);
              credentialJson.remove(HostCredential.varJsonHostCredential(HostCredential.VAR_password, patch));
              credentialJson.remove(HostCredential.varJsonHostCredential(HostCredential.VAR_becomePassword, patch));

              Integer aapPort = Integer.parseInt(config.getString(ConfigKeys.AAP_PORT));
              String aapCredentialName = config.getString(ConfigKeys.AAP_HOST_NAME);
              Boolean aapSsl = Boolean.parseBoolean(config.getString(ConfigKeys.AAP_SSL));
              String aapUri = patch ? String.format("/api/controller/v2/credentials/%s/", aapCredentialId) : "/api/controller/v2/credentials/";
              String aapUserName = config.getString(ConfigKeys.AAP_USER_NAME);
              String aapPassword = config.getString(ConfigKeys.AAP_PASSWORD);

              JsonObject body = new JsonObject();
              body.put("credential_type", aapCredentialTypeId);
              body.put("name", credentialId);
              body.put("organization", tenantId);
              JsonObject inputs = new JsonObject();
              if(becomeMethod != null)
                inputs.put("become_method", becomeMethod);
              if(becomePassword != null)
                inputs.put("become_password", becomePassword);
              if(userName != null)
                inputs.put("username", userName);
              if(password != null)
                inputs.put("password", password);
              body.put("inputs", inputs);
              if(credentialDescription != null)
                body.put("description", credentialDescription);
              body.put("organization", aapOrganizationId);

              if(StringUtils.isEmpty(credentialName)) {
                RuntimeException ex = new RuntimeException("Missing credential name");
                LOG.error(ex.getMessage(), ex);
                promise.fail(ex);
              } else {
                if(patch) {
                  webClient.patch(aapPort, aapCredentialName, aapUri).ssl(aapSsl)
                      .putHeader("Content-Type", "application/json")
                      .basicAuthentication(aapUserName, aapPassword)
                      .sendJsonObject(body)
                      .expecting(HttpResponseExpectation.SC_OK)
                      .onSuccess(credentialResponse -> {
                    promise.complete();
                  }).onFailure(ex -> {
                    LOG.error(String.format("Updating AAP credential failed. "), ex);
                    promise.fail(ex);
                  });
                } else {
                  webClient.post(aapPort, aapCredentialName, aapUri).ssl(aapSsl)
                      .putHeader("Content-Type", "application/json")
                      .basicAuthentication(aapUserName, aapPassword)
                      .sendJsonObject(body)
                      .expecting(HttpResponseExpectation.SC_CREATED)
                      .onSuccess(credentialResponse -> {
                    try {
                      JsonObject responseBody = credentialResponse.bodyAsJsonObject();
                      String aapCredentialId2 = responseBody.getString("id");
                      credentialJson.put(HostCredential.VAR_aapCredentialId, aapCredentialId2);
                      promise.complete();
                    } catch(Throwable ex) {
                      LOG.error(String.format("Post AAP Host Credential creation failed. "), ex);
                      promise.fail(ex);
                    }
                  }).onFailure(ex -> {
                    LOG.error(String.format("Updating AAP credential failed. "), ex);
                    promise.fail(ex);
                  });
                }
              }
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
      LOG.error(String.format("Updating AAP credential failed. "), ex);
      promise.fail(ex);
    }
    return promise.future();
  }

  @Override
  public Future<HostCredential> sqlPOSTHostCredential(HostCredential o, Boolean inheritPrimaryKey) {
    Promise<HostCredential> promise = Promise.promise();
    aapUpsertHostCredential(o, inheritPrimaryKey, false).onSuccess(a -> {
      super.sqlPOSTHostCredential(o, inheritPrimaryKey).onSuccess(o2 -> {
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
  public Future<HostCredential> sqlPATCHHostCredential(HostCredential o, Boolean inheritPrimaryKey) {
    Promise<HostCredential> promise = Promise.promise();
    aapUpsertHostCredential(o, inheritPrimaryKey, true).onSuccess(a -> {
      super.sqlPATCHHostCredential(o, inheritPrimaryKey).onSuccess(o2 -> {
        promise.complete(o2);
      }).onFailure(ex -> {
        promise.fail(ex);
      });
    }).onFailure(ex -> {
      promise.fail(ex);
    });
    return promise.future();
  }

  public Future<Void> aapDeleteHostCredential(HostCredential o) {
    Promise<Void> promise = Promise.promise();
    try {
      SiteRequest siteRequest = o.getSiteRequest_();
      ServiceRequest serviceRequest = siteRequest.getServiceRequest();
      if(Optional.ofNullable(serviceRequest.getParams()).map(p -> p.getJsonObject("query")).map( q -> q.getJsonArray("var")).orElse(new JsonArray()).stream().filter(s -> "refresh:false".equals(s)).count() > 0L) {
        promise.complete();
      } else {
        Long aapCredentialId = o.getAapCredentialId();

        Integer aapPort = Integer.parseInt(config.getString(ConfigKeys.AAP_PORT));
        String aapCredentialName = config.getString(ConfigKeys.AAP_HOST_NAME);
        Boolean aapSsl = Boolean.parseBoolean(config.getString(ConfigKeys.AAP_SSL));
        String aapUri = String.format("/api/controller/v2/credentials/%s/", aapCredentialId);
        String aapUserName = config.getString(ConfigKeys.AAP_USER_NAME);
        String aapPassword = config.getString(ConfigKeys.AAP_PASSWORD);

        if(aapCredentialId == null) {
          RuntimeException ex = new RuntimeException("Missing credential ID");
          LOG.error(ex.getMessage(), ex);
          promise.fail(ex);
        } else {
          webClient.delete(aapPort, aapCredentialName, aapUri).ssl(aapSsl)
              .putHeader("Content-Type", "application/json")
              .basicAuthentication(aapUserName, aapPassword)
              .send()
              .expecting(HttpResponseExpectation.SC_NO_CONTENT.or(HttpResponseExpectation.SC_ACCEPTED).or(HttpResponseExpectation.SC_NOT_FOUND))
              .onSuccess(credentialResponse -> {
            promise.complete();
          }).onFailure(ex -> {
            LOG.error(String.format("Deleting AAP credential failed. "), ex);
            promise.fail(ex);
          });
        }
      }
    } catch(Exception ex) {
      LOG.error(String.format("Deleting AAP credential failed. "), ex);
      promise.fail(ex);
    }
    return promise.future();
  }

  @Override
  public Future<Void> sqlDELETEFilterHostCredential(HostCredential o) {
    Promise<Void> promise = Promise.promise();
    aapDeleteHostCredential(o).onSuccess(a -> {
      super.sqlDELETEFilterHostCredential(o).onSuccess(o2 -> {
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
  public Future<Void> sqlDELETEHostCredential(HostCredential o) {
    Promise<Void> promise = Promise.promise();
    aapDeleteHostCredential(o).onSuccess(a -> {
      super.sqlDELETEHostCredential(o).onSuccess(o2 -> {
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
