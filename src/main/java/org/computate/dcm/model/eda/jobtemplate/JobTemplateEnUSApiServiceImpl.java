package org.computate.dcm.model.eda.jobtemplate;

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
import org.computate.dcm.model.eda.hostinventory.HostInventory;
import org.computate.dcm.request.SiteRequest;
import org.computate.vertx.search.list.SearchList;

/**
 * Translate: false
 **/
public class JobTemplateEnUSApiServiceImpl extends JobTemplateEnUSGenApiServiceImpl {

  public Future<Void> aapUpsertJobTemplate(JobTemplate o, Boolean inheritPrimaryKey, Boolean patch) {
    Promise<Void> promise = Promise.promise();
    try {
      SiteRequest siteRequest = o.getSiteRequest_();
      ServiceRequest serviceRequest = siteRequest.getServiceRequest();
      if(Optional.ofNullable(serviceRequest.getParams()).map(p -> p.getJsonObject("query")).map( q -> q.getJsonArray("var")).orElse(new JsonArray()).stream().filter(s -> "refresh:false".equals(s)).count() > 0L) {
        promise.complete();
      } else {
        JsonObject jobTemplateJson = o.getSiteRequest_().getJsonObject();
        // String tenantResource = Optional.ofNullable(jobTemplateJson.getString(patch ? "setTenantResource": "tenantResource")).orElse(o.getTenantResource());
        String inventoryResource = Optional.ofNullable(jobTemplateJson.getString(patch ? "setInventoryResource": "inventoryResource")).orElse(o.getInventoryResource());
        String jobTemplateName = Optional.ofNullable(jobTemplateJson.getString(patch ? "setJobTemplateName": "jobTemplateName")).orElse(o.getJobTemplateName());
        String jobTemplateId = Optional.ofNullable(jobTemplateJson.getString(patch ? "setJobTemplateId": "jobTemplateId")).orElse(JobTemplate.toId(jobTemplateName));
        String jobType = Optional.ofNullable(Optional.ofNullable(jobTemplateJson.getString(patch ? "setJobType": "jobType")).orElse(o.getJobType())).orElse("run");
        Long aapOrganizationId = Optional.ofNullable(Optional.ofNullable(jobTemplateJson.getLong(patch ? "setAapOrganizationId": "aapOrganizationId")).orElse(o.getAapOrganizationId())).orElse(1L);
        String organizationId = Optional.ofNullable(Optional.ofNullable(jobTemplateJson.getString(patch ? "setOrganizationId": "organizationId")).orElse(o.getOrganizationId())).orElse("");
        jobTemplateJson.put(JobTemplate.VAR_jobType, jobType);
        String ansibleProjectId = Optional.ofNullable(jobTemplateJson.getString(patch ? "setAnsibleProjectId": "ansibleProjectId")).orElse(o.getAnsibleProjectId());
        String ansiblePlaybook = Optional.ofNullable(jobTemplateJson.getString(patch ? "setAnsiblePlaybook": "ansiblePlaybook")).orElse(o.getAnsiblePlaybook());
        jobTemplateJson.put(JobTemplate.VAR_jobTemplateId, jobTemplateId);
        Long aapTemplateId = Optional.ofNullable(jobTemplateJson.getString(patch ? "setAapTemplateId": "aapTemplateId")).map(s -> Long.parseLong(s)).orElse(o.getAapTemplateId());
        String jobTemplateDescription = Optional.ofNullable(jobTemplateJson.getString(patch ? "setJobTemplateDescription": "templateDescription")).orElse(o.getJobTemplateDescription());


        SearchList<HostInventory> inventorySearchList = new SearchList<HostInventory>();
        inventorySearchList.setStore(true);
        inventorySearchList.q("*:*");
        inventorySearchList.fq(String.format("inventoryResource_docvalues_string:%s", inventoryResource));
        inventorySearchList.setC(HostInventory.class);
        inventorySearchList.setSiteRequest_(o.getSiteRequest_());
        inventorySearchList.promiseDeepForClass(siteRequest).onSuccess(inventorySearchList2 -> {
          try {
            HostInventory inventory = inventorySearchList.first();
            if(inventory == null) {
              RuntimeException ex = new RuntimeException(String.format("Could not find a matching host inventory %s", inventoryResource));
              LOG.error(ex.getMessage(), ex);
              promise.fail(ex);
            } else if(inventory.getAapInventoryId() == null) {
              RuntimeException ex = new RuntimeException(String.format("The host inventory %s doesn't have an AAP Inventory ID number", inventoryResource));
              LOG.error(ex.getMessage(), ex);
              promise.fail(ex);
            } else {
              Long aapInventoryId = inventory.getAapInventoryId();
              jobTemplateJson.put(JobTemplate.VAR_aapInventoryId, aapInventoryId.toString());

              SearchList<AnsibleProject> projectSearchList = new SearchList<AnsibleProject>();
              projectSearchList.setStore(true);
              projectSearchList.q("*:*");
              projectSearchList.fq(String.format("ansibleProjectId_docvalues_string:%s", ansibleProjectId));
              projectSearchList.setC(AnsibleProject.class);
              projectSearchList.setSiteRequest_(o.getSiteRequest_());
              projectSearchList.promiseDeepForClass(siteRequest).onSuccess(projectSearchList2 -> {
                try {
                  AnsibleProject project = projectSearchList.first();
                  if(project == null) {
                    RuntimeException ex = new RuntimeException(String.format("Could not find a matching Ansible project %s", ansibleProjectId));
                    LOG.error(ex.getMessage(), ex);
                    promise.fail(ex);
                  } else if(project.getAapProjectId() == null) {
                    RuntimeException ex = new RuntimeException(String.format("The Ansible project %s doesn't have an AAP project ID number", ansibleProjectId));
                    LOG.error(ex.getMessage(), ex);
                    promise.fail(ex);
                  } else {
                    Long aapProjectId = project.getAapProjectId();

                    Integer aapPort = Integer.parseInt(config.getString(ConfigKeys.AAP_PORT));
                    String aapHostName = config.getString(ConfigKeys.AAP_HOST_NAME);
                    Boolean aapSsl = Boolean.parseBoolean(config.getString(ConfigKeys.AAP_SSL));
                    String aapUri = patch ? String.format("/api/controller/v2/job_templates/%s/", aapTemplateId) : "/api/controller/v2/job_templates/";
                    String aapUserName = config.getString(ConfigKeys.AAP_USER_NAME);
                    String aapPassword = config.getString(ConfigKeys.AAP_PASSWORD);
                    jobTemplateJson.put(JobTemplate.VAR_aapProjectId, aapProjectId.toString());

                    JsonObject body = new JsonObject();
                    body.put("name", jobTemplateName);
                    body.put("job_type", jobType);
                    body.put("inventory", aapInventoryId);
                    body.put("project", aapProjectId);
                    body.put("playbook", ansiblePlaybook);
                    body.put("organization", aapOrganizationId);
                    if(jobTemplateDescription != null)
                      body.put("description", jobTemplateDescription);

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
                          promise.complete();
                        }).onFailure(ex -> {
                          LOG.error(String.format("Updating AAP template failed. "), ex);
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
                LOG.error(String.format("search JobTemplate failed. "), ex);
                promise.tryFail(ex);
              });
            }
          } catch(Exception ex) {
            LOG.error(String.format("Updating Sensu host failed. "), ex);
            promise.fail(ex);
          }
        }).onFailure(ex -> {
          LOG.error(String.format("search JobTemplate failed. "), ex);
          promise.tryFail(ex);
        });
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
    aapUpsertJobTemplate(o, inheritPrimaryKey, false).onSuccess(a -> {
      super.sqlPOSTJobTemplate(o, inheritPrimaryKey).onSuccess(o2 -> {
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
  public Future<JobTemplate> sqlPATCHJobTemplate(JobTemplate o, Boolean inheritPrimaryKey) {
    Promise<JobTemplate> promise = Promise.promise();
    aapUpsertJobTemplate(o, inheritPrimaryKey, true).onSuccess(a -> {
      super.sqlPATCHJobTemplate(o, inheritPrimaryKey).onSuccess(o2 -> {
        promise.complete(o2);
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
