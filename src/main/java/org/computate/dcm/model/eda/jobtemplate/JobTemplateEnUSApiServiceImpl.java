package org.computate.dcm.model.eda.jobtemplate;

import io.vertx.ext.web.api.service.ServiceRequest;
import io.vertx.ext.web.client.WebClient;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.http.HttpResponseExpectation;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import java.util.Optional;
import org.apache.commons.lang3.StringUtils;
import org.computate.dcm.config.ConfigKeys;
import org.computate.dcm.model.eda.ansibleproject.AnsibleProject;
import org.computate.dcm.model.eda.hostcredential.HostCredential;
import org.computate.dcm.model.eda.hostinventory.HostInventory;
import org.computate.dcm.model.eda.tenant.Tenant;
import org.computate.dcm.request.SiteRequest;
import org.computate.vertx.search.list.SearchList;

/**
 * Translate: false
 **/
public class JobTemplateEnUSApiServiceImpl extends JobTemplateEnUSGenApiServiceImpl {

  public Future<JsonObject> aapUpsertParams(JobTemplate o, Boolean inheritPrimaryKey, Boolean patch) {
    Promise<JsonObject> promise = Promise.promise();
    try {
      SiteRequest siteRequest = o.getSiteRequest_();
      ServiceRequest serviceRequest = siteRequest.getServiceRequest();
      if(Optional.ofNullable(serviceRequest.getParams()).map(p -> p.getJsonObject("query")).map( q -> q.getJsonArray("var")).orElse(new JsonArray()).stream().filter(s -> "refresh:false".equals(s)).count() > 0L) {
        promise.complete();
      } else {
        JsonObject jobTemplateJson = o.getSiteRequest_().getJsonObject();
        String inventoryResource = Optional.ofNullable(jobTemplateJson.getString(JobTemplate.varJsonJobTemplate(JobTemplate.VAR_inventoryResource, patch))).orElse(o.getInventoryResource());
        HostInventory.fqHostInventory(siteRequest, HostInventory.VAR_inventoryResource, inventoryResource).onSuccess(inventory -> {
          String credentialResource = Optional.ofNullable(jobTemplateJson.getString(JobTemplate.varJsonJobTemplate(JobTemplate.VAR_credentialResource, patch))).orElse(o.getCredentialResource());
          HostCredential.fqHostCredential(siteRequest, HostCredential.VAR_credentialResource, credentialResource).onSuccess(credential -> {
            String ansibleProjectResource = Optional.ofNullable(jobTemplateJson.getString(JobTemplate.varJsonJobTemplate(JobTemplate.VAR_ansibleProjectResource, patch))).orElse(o.getAnsibleProjectResource());
            jobTemplateJson.put(JobTemplate.varJsonJobTemplate(JobTemplate.VAR_ansibleProjectResource, patch), ansibleProjectResource);

            AnsibleProject.fqAnsibleProject(siteRequest, AnsibleProject.VAR_ansibleProjectResource, ansibleProjectResource).onSuccess(project -> {
              try {
                if(inventory == null) {
                  RuntimeException ex = new RuntimeException(String.format("Could not find a matching host inventory %s", inventoryResource));
                  LOG.error(ex.getMessage(), ex);
                  promise.fail(ex);
                } else if(inventory.getAapInventoryId() == null) {
                  RuntimeException ex = new RuntimeException(String.format("The host inventory %s doesn't have an AAP Inventory ID number", inventoryResource));
                  LOG.error(ex.getMessage(), ex);
                  promise.fail(ex);
                } else if(credential == null) {
                  RuntimeException ex = new RuntimeException(String.format("Could not find a matching host credential %s", credentialResource));
                  LOG.error(ex.getMessage(), ex);
                  promise.fail(ex);
                } else if(credential.getAapCredentialId() == null) {
                  RuntimeException ex = new RuntimeException(String.format("The host credential %s doesn't have an AAP credential ID number", credentialResource));
                  LOG.error(ex.getMessage(), ex);
                  promise.fail(ex);
                } else if(project == null) {
                  RuntimeException ex = new RuntimeException(String.format("Could not find a matching project %s", ansibleProjectResource));
                  LOG.error(ex.getMessage(), ex);
                  promise.fail(ex);
                } else if(project.getAapProjectId() == null) {
                  RuntimeException ex = new RuntimeException(String.format("The Ansible project %s doesn't have an AAP Project ID number", ansibleProjectResource));
                  LOG.error(ex.getMessage(), ex);
                  promise.fail(ex);
                } else {
                  Long aapInventoryId = inventory.getAapInventoryId();
                  jobTemplateJson.put(JobTemplate.varJsonJobTemplate(JobTemplate.VAR_aapInventoryId, patch), aapInventoryId.toString());
                  Long aapHostCredentialId = credential.getAapCredentialId();
                  jobTemplateJson.put(JobTemplate.varJsonJobTemplate(JobTemplate.VAR_aapHostCredentialId, patch), aapHostCredentialId.toString());
                  Long aapOrganizationId = Optional.ofNullable(Optional.ofNullable(jobTemplateJson.getLong(JobTemplate.varJsonJobTemplate(JobTemplate.VAR_aapOrganizationId, patch))).orElse(o.getAapOrganizationId())).orElse(inventory.getAapOrganizationId());
                  jobTemplateJson.put(JobTemplate.varJsonJobTemplate(JobTemplate.VAR_aapOrganizationId, patch), aapOrganizationId.toString());
                  String tenantResource = Optional.ofNullable(Optional.ofNullable(Optional.ofNullable(jobTemplateJson.getString(JobTemplate.varJsonJobTemplate(JobTemplate.VAR_tenantResource, patch))).orElse(o.getTenantResource())).orElse(inventory.getTenantResource())).orElse(inventory.getTenantResource());
                  jobTemplateJson.put(JobTemplate.varJsonJobTemplate(JobTemplate.VAR_tenantResource, patch), tenantResource);
                  String tenantId = Optional.ofNullable(Optional.ofNullable(jobTemplateJson.getString(JobTemplate.varJsonJobTemplate(JobTemplate.VAR_tenantId, patch))).orElse(o.getTenantId())).orElse(inventory.getTenantId());
                  jobTemplateJson.put(JobTemplate.varJsonJobTemplate(JobTemplate.VAR_tenantId, patch), tenantId);
                  Long aapProjectId = Optional.ofNullable(Optional.ofNullable(jobTemplateJson.getString(JobTemplate.varJsonJobTemplate(JobTemplate.VAR_aapProjectId, patch))).map(s -> Long.parseLong(s)).orElse(o.getAapProjectId())).orElse(project.getAapProjectId());
                  jobTemplateJson.put(JobTemplate.varJsonJobTemplate(JobTemplate.VAR_aapProjectId, patch), Optional.ofNullable(aapProjectId).map(v -> v.toString()).orElse(null));
                  String ansiblePlaybook = Optional.ofNullable(jobTemplateJson.getString(JobTemplate.varJsonJobTemplate(JobTemplate.VAR_ansiblePlaybook, patch))).orElse(o.getAnsiblePlaybook());
                  jobTemplateJson.put(JobTemplate.varJsonJobTemplate(JobTemplate.VAR_ansiblePlaybook, patch), ansiblePlaybook);
                  String jobTemplateName = Optional.ofNullable(Optional.ofNullable(jobTemplateJson.getString(JobTemplate.varJsonJobTemplate(JobTemplate.VAR_jobTemplateName, patch))).orElse(o.getJobTemplateName())).orElse(Optional.ofNullable(ansiblePlaybook).map(s -> StringUtils.substringBeforeLast(s, ".")).orElse(null));
                  jobTemplateJson.put(JobTemplate.varJsonJobTemplate(JobTemplate.VAR_jobTemplateName, patch), jobTemplateName);
                  String jobTemplateId = Optional.ofNullable(jobTemplateJson.getString(JobTemplate.varJsonJobTemplate(JobTemplate.VAR_jobTemplateId, patch))).orElse(JobTemplate.toId(jobTemplateName));
                  jobTemplateJson.put(JobTemplate.varJsonJobTemplate(JobTemplate.VAR_jobTemplateId, patch), jobTemplateId);
                  String jobTemplateResource = String.format("%s-%s-%s", tenantResource, JobTemplate.CLASS_AUTH_RESOURCE, jobTemplateId);
                  jobTemplateJson.put(JobTemplate.varJsonJobTemplate(JobTemplate.VAR_jobTemplateResource, patch), jobTemplateResource);
                  String jobType = Optional.ofNullable(Optional.ofNullable(jobTemplateJson.getString(JobTemplate.varJsonJobTemplate(JobTemplate.VAR_jobType, patch))).orElse(o.getJobType())).orElse("run");
                  jobTemplateJson.put(JobTemplate.varJsonJobTemplate(JobTemplate.VAR_jobType, patch), jobType);
                  JsonObject extraVars = Optional.ofNullable(Optional.ofNullable(jobTemplateJson.getJsonObject(JobTemplate.varJsonJobTemplate(JobTemplate.VAR_extraVars, patch))).orElse(o.getExtraVars())).orElse(new JsonObject().put("APP_USER", "ctate").put("APP_PREFIX", "/usr/local"));
                  jobTemplateJson.put(JobTemplate.varJsonJobTemplate(JobTemplate.VAR_extraVars, patch), extraVars);
                  Long aapTemplateId = Optional.ofNullable(jobTemplateJson.getString(JobTemplate.varJsonJobTemplate(JobTemplate.VAR_aapTemplateId, patch))).map(s -> Long.parseLong(s)).orElse(o.getAapTemplateId());
                  jobTemplateJson.put(JobTemplate.varJsonJobTemplate(JobTemplate.VAR_aapTemplateId, patch), Optional.ofNullable(aapTemplateId).map(v -> v.toString()).orElse(null));
                  String jobTemplateDescription = Optional.ofNullable(jobTemplateJson.getString(JobTemplate.varJsonJobTemplate(JobTemplate.VAR_jobTemplateDescription, patch))).orElse(o.getJobTemplateDescription());
                  jobTemplateJson.put(JobTemplate.varJsonJobTemplate(JobTemplate.VAR_jobTemplateDescription, patch), jobTemplateDescription);
                  promise.complete(jobTemplateJson);
                }
              } catch(Exception ex) {
                LOG.error(String.format("Updating Sensu host failed. "), ex);
                promise.fail(ex);
              }
            }).onFailure(ex -> {
              promise.tryFail(ex);
            });
          }).onFailure(ex -> {
            promise.tryFail(ex);
          });
        }).onFailure(ex -> {
          promise.tryFail(ex);
        });
      }
    } catch(Exception ex) {
      LOG.error(String.format("Updating AAP host failed. "), ex);
      promise.fail(ex);
    }
    return promise.future();
  }

  public static Future<JsonObject> aapUpsertJobTemplate(JsonObject config, WebClient webClient, SiteRequest siteRequest, Boolean inheritPrimaryKey, Boolean patch, JsonObject jobTemplateJson) {
    Promise<JsonObject> promise = Promise.promise();
    try {
      ServiceRequest serviceRequest = siteRequest.getServiceRequest();
      if(Optional.ofNullable(serviceRequest.getParams()).map(p -> p.getJsonObject("query")).map( q -> q.getJsonArray("var")).orElse(new JsonArray()).stream().filter(s -> "refresh:false".equals(s)).count() > 0L) {
        promise.complete();
      } else {
        Long aapProjectId = Optional.ofNullable(jobTemplateJson.getString(JobTemplate.varJsonJobTemplate(JobTemplate.VAR_aapProjectId, patch))).map(t -> Long.parseLong(t)).orElse(null);
        Long aapTemplateId = Optional.ofNullable(jobTemplateJson.getString(JobTemplate.varJsonJobTemplate(JobTemplate.VAR_aapTemplateId, patch))).map(t -> Long.parseLong(t)).orElse(null);
        Long aapOrganizationId = Optional.ofNullable(jobTemplateJson.getString(JobTemplate.varJsonJobTemplate(JobTemplate.VAR_aapOrganizationId, patch))).map(t -> Long.parseLong(t)).orElse(null);
        Long aapInventoryId = Optional.ofNullable(jobTemplateJson.getString(JobTemplate.varJsonJobTemplate(JobTemplate.VAR_aapInventoryId, patch))).map(t -> Long.parseLong(t)).orElse(null);
        String ansiblePlaybook = jobTemplateJson.getString(JobTemplate.varJsonJobTemplate(JobTemplate.VAR_ansiblePlaybook, patch));
        String jobTemplateName = Optional.ofNullable(jobTemplateJson.getString(JobTemplate.varJsonJobTemplate(JobTemplate.VAR_jobTemplateName, patch))).orElse(Optional.ofNullable(ansiblePlaybook).map(s -> StringUtils.substringBeforeLast(s, ".")).orElse(null));
        String jobTemplateDescription = jobTemplateJson.getString(JobTemplate.varJsonJobTemplate(JobTemplate.VAR_jobTemplateDescription, patch));
        String jobType = Optional.ofNullable(jobTemplateJson.getString(JobTemplate.varJsonJobTemplate(JobTemplate.VAR_jobType, patch))).orElse("run");
        JsonObject extraVars = jobTemplateJson.getJsonObject(JobTemplate.varJsonJobTemplate(JobTemplate.VAR_extraVars, patch));

        Integer aapPort = Integer.parseInt(config.getString(ConfigKeys.AAP_PORT));
        String aapHostName = config.getString(ConfigKeys.AAP_HOST_NAME);
        Boolean aapSsl = Boolean.parseBoolean(config.getString(ConfigKeys.AAP_SSL));
        String aapUri = patch ? String.format("/api/controller/v2/job_templates/%s/", aapTemplateId) : "/api/controller/v2/job_templates/";
        String aapUserName = config.getString(ConfigKeys.AAP_USER_NAME);
        String aapPassword = config.getString(ConfigKeys.AAP_PASSWORD);

        JsonObject body = new JsonObject();
        body.put("name", jobTemplateName);
        body.put("job_type", jobType);
        body.put("inventory", aapInventoryId);
        body.put("project", aapProjectId);
        body.put("playbook", ansiblePlaybook);
        body.put("organization", aapOrganizationId);
        body.put("ask_limit_on_launch", true);
        if(jobTemplateDescription != null)
          body.put("description", jobTemplateDescription);
        if(extraVars != null)
          body.put("extra_vars", extraVars.toString());

        if(StringUtils.isEmpty(jobTemplateName)) {
          RuntimeException ex = new RuntimeException("Missing template name");
          LOG.error(ex.getMessage(), ex);
          promise.fail(ex);
        } else {
          if(patch) {
            webClient.patch(aapPort, aapHostName, aapUri).ssl(aapSsl)
                .putHeader("Content-Type", "application/json")
                .basicAuthentication(aapUserName, aapPassword)
                .sendJsonObject(body)
                .expecting(HttpResponseExpectation.SC_OK)
                .onSuccess(templateResponse -> {
              promise.complete();
            }).onFailure(ex -> {
              LOG.error(String.format("Updating AAP template failed. "), ex);
              promise.fail(ex);
            });
          } else {
            webClient.post(aapPort, aapHostName, aapUri).ssl(aapSsl)
                .putHeader("Content-Type", "application/json")
                .basicAuthentication(aapUserName, aapPassword)
                .sendJsonObject(body)
                .expecting(HttpResponseExpectation.SC_CREATED)
                .onSuccess(templateResponse -> {
              JsonObject responseBody = templateResponse.bodyAsJsonObject();
              Long aapTemplateId2 = responseBody.getLong("id");
              jobTemplateJson.put(JobTemplate.VAR_aapTemplateId, aapTemplateId2.toString());
              promise.complete(jobTemplateJson);
            }).onFailure(ex -> {
              LOG.error(String.format("Updating AAP template failed. "), ex);
              promise.fail(ex);
            });
          }
        }
      }
    } catch(Exception ex) {
      LOG.error(String.format("Updating AAP template failed. "), ex);
      promise.fail(ex);
    }
    return promise.future();
  }

  @Override
  public Future<JobTemplate> sqlPOSTJobTemplate(JobTemplate o, Boolean inheritPrimaryKey) {
    Promise<JobTemplate> promise = Promise.promise();
    aapUpsertParams(o, inheritPrimaryKey, false).onSuccess(jobTemplateJson -> {
      aapUpsertJobTemplate(config, webClient, o.getSiteRequest_(), inheritPrimaryKey, false, jobTemplateJson).onSuccess(a -> {
        super.sqlPOSTJobTemplate(o, inheritPrimaryKey).onSuccess(o2 -> {
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
  public Future<JobTemplate> sqlPATCHJobTemplate(JobTemplate o, Boolean inheritPrimaryKey) {
    Promise<JobTemplate> promise = Promise.promise();
    aapUpsertParams(o, inheritPrimaryKey, true).onSuccess(jobTemplateJson -> {
      aapUpsertJobTemplate(config, webClient, o.getSiteRequest_(), inheritPrimaryKey, true, jobTemplateJson).onSuccess(a -> {
        super.sqlPATCHJobTemplate(o, inheritPrimaryKey).onSuccess(o2 -> {
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

  public Future<Void> aapDeleteJobTemplate(JobTemplate o) {
    Promise<Void> promise = Promise.promise();
    try {
      SiteRequest siteRequest = o.getSiteRequest_();
      ServiceRequest serviceRequest = siteRequest.getServiceRequest();
      if(Optional.ofNullable(serviceRequest.getParams()).map(p -> p.getJsonObject("query")).map( q -> q.getJsonArray("var")).orElse(new JsonArray()).stream().filter(s -> "refresh:false".equals(s)).count() > 0L) {
        promise.complete();
      } else {
        Long aapTemplateId = o.getAapTemplateId();

        Integer aapPort = Integer.parseInt(config.getString(ConfigKeys.AAP_PORT));
        String aapHostName = config.getString(ConfigKeys.AAP_HOST_NAME);
        Boolean aapSsl = Boolean.parseBoolean(config.getString(ConfigKeys.AAP_SSL));
        String aapUri = String.format("/api/controller/v2/job_templates/%s/", aapTemplateId);
        String aapUserName = config.getString(ConfigKeys.AAP_USER_NAME);
        String aapPassword = config.getString(ConfigKeys.AAP_PASSWORD);

        if(aapTemplateId == null) {
          RuntimeException ex = new RuntimeException("Missing template ID");
          LOG.error(ex.getMessage(), ex);
          promise.fail(ex);
        } else {
          webClient.delete(aapPort, aapHostName, aapUri).ssl(aapSsl)
              .putHeader("Content-Type", "application/json")
              .basicAuthentication(aapUserName, aapPassword)
              .send()
              .expecting(HttpResponseExpectation.SC_NO_CONTENT.or(HttpResponseExpectation.SC_NOT_FOUND))
              .onSuccess(templateResponse -> {
            promise.complete();
          }).onFailure(ex -> {
            LOG.error(String.format("Deleting AAP template failed. "), ex);
            promise.fail(ex);
          });
        }
      }
    } catch(Exception ex) {
      LOG.error(String.format("Deleting AAP template failed. "), ex);
      promise.fail(ex);
    }
    return promise.future();
  }

  @Override
  public Future<Void> sqlDELETEFilterJobTemplate(JobTemplate o) {
    Promise<Void> promise = Promise.promise();
    aapDeleteJobTemplate(o).onSuccess(a -> {
      super.sqlDELETEFilterJobTemplate(o).onSuccess(o2 -> {
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
  public Future<Void> sqlDELETEJobTemplate(JobTemplate o) {
    Promise<Void> promise = Promise.promise();
    aapDeleteJobTemplate(o).onSuccess(a -> {
      super.sqlDELETEJobTemplate(o).onSuccess(o2 -> {
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
