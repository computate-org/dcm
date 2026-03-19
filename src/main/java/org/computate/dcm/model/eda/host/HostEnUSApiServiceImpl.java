package org.computate.dcm.model.eda.host;

import io.vertx.ext.auth.authentication.UsernamePasswordCredentials;
import io.vertx.ext.web.api.service.ServiceRequest;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.http.HttpResponseExpectation;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.computate.dcm.config.ConfigKeys;
import org.computate.dcm.model.eda.hostinventory.HostInventory;
import org.computate.dcm.model.eda.jobtemplate.JobTemplate;
import org.computate.dcm.model.eda.tenant.Tenant;
import org.computate.dcm.request.SiteRequest;
import org.computate.vertx.search.list.SearchList;

/**
 * Translate: false
 **/
public class HostEnUSApiServiceImpl extends HostEnUSGenApiServiceImpl {

  public Future<JsonObject> aapUpsertParams(Host o, Boolean inheritPrimaryKey, Boolean patch) {
    Promise<JsonObject> promise = Promise.promise();
    try {
      SiteRequest siteRequest = o.getSiteRequest_();
      ServiceRequest serviceRequest = siteRequest.getServiceRequest();
      if(Optional.ofNullable(serviceRequest.getParams()).map(p -> p.getJsonObject("query")).map( q -> q.getJsonArray("var")).orElse(new JsonArray()).stream().filter(s -> "refresh:false".equals(s)).count() > 0L) {
        promise.complete();
      } else {
        JsonObject hostJson = o.getSiteRequest_().getJsonObject();
        String inventoryResource = Optional.ofNullable(hostJson.getString(Host.varJsonHost(Host.VAR_inventoryResource, patch))).orElse(o.getInventoryResource());
        HostInventory.fqHostInventory(siteRequest, HostInventory.VAR_inventoryResource, inventoryResource).onSuccess(inventory -> {
          try {
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
              hostJson.put(Host.varJsonHost(Host.VAR_aapInventoryId, patch), aapInventoryId.toString());
              String tenantResource = Optional.ofNullable(Optional.ofNullable(hostJson.getString(Host.varJsonHost(Host.VAR_tenantResource, patch))).orElse(o.getTenantResource())).orElse(inventory.getTenantResource());
              hostJson.put(Host.varJsonHost(Host.VAR_tenantResource, patch), tenantResource);
              String tenantId = Optional.ofNullable(Optional.ofNullable(hostJson.getString(Host.varJsonHost(Host.VAR_tenantId, patch))).orElse(o.getTenantId())).orElse(inventory.getTenantId());
              hostJson.put(Host.varJsonHost(Host.VAR_tenantId, patch), tenantId);
              String hostName = Optional.ofNullable(hostJson.getString(Host.varJsonHost(Host.VAR_hostName, patch))).orElse(o.getHostName());
              hostJson.put(Host.varJsonHost(Host.VAR_hostName, patch), hostName);
              String hostDescription = Optional.ofNullable(hostJson.getString(Host.varJsonHost(Host.VAR_hostDescription, patch) )).orElse(o.getHostDescription());
              hostJson.put(Host.varJsonHost(Host.VAR_hostDescription, patch), hostDescription);
              String ipAddress = Optional.ofNullable(hostJson.getString(Host.varJsonHost(Host.VAR_ipAddress, patch))).orElse(o.getIpAddress());
              hostJson.put(Host.varJsonHost(Host.VAR_ipAddress, patch), ipAddress);
              String hostId = Optional.ofNullable(hostJson.getString(Host.varJsonHost(Host.VAR_hostId, patch))).orElse(Host.toId(hostName));
              hostJson.put(Host.varJsonHost(Host.VAR_hostId, patch), hostId);
              String hostResource = String.format("%s-%s-%s", tenantResource, Host.CLASS_AUTH_RESOURCE, hostId);
              hostJson.put(Host.varJsonHost(Host.VAR_hostResource, patch), hostResource);
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
                    hostJson.put(Host.VAR_tenantResource, tenantResource);
                    hostJson.put(Host.VAR_aapOrganizationId, tenant.getAapOrganizationId());
                    promise.complete(hostJson);
                  }
                } catch(Exception ex) {
                  LOG.error(String.format("Updating Sensu host failed. "), ex);
                  promise.fail(ex);
                }
              }).onFailure(ex -> {
                promise.tryFail(ex);
              });
            }
          } catch(Exception ex) {
            LOG.error(String.format("Updating Sensu host failed. "), ex);
            promise.fail(ex);
          }
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

  public Future<Void> aapUpsertHost(Host o, Boolean inheritPrimaryKey, Boolean patch, JsonObject hostJson) {
    Promise<Void> promise = Promise.promise();
    try {
      if(hostJson == null) {
        promise.complete();
      } else {
        String hostName = hostJson.getString(Host.varJsonHost(Host.VAR_hostName, patch));
        String ipAddress = hostJson.getString(Host.varJsonHost(Host.VAR_ipAddress, patch));
        Long aapInventoryId = Long.parseLong(hostJson.getString(Host.varJsonHost(Host.VAR_aapInventoryId, patch)));
        String hostDescription = hostJson.getString(Host.varJsonHost(Host.VAR_hostDescription, patch));
        if(hostName == null) {
          RuntimeException ex = new RuntimeException(String.format("The host name cannot be null"));
          LOG.error(ex.getMessage(), ex);
          promise.fail(ex);
        } else {
          Integer aapPort = Integer.parseInt(config.getString(ConfigKeys.AAP_PORT));
          String aapHostName = config.getString(ConfigKeys.AAP_HOST_NAME);
          Boolean aapSsl = Boolean.parseBoolean(config.getString(ConfigKeys.AAP_SSL));
          String aapUri = patch ? String.format("/api/controller/v2/hosts/%s/", o.getAapHostId()) : String.format("/api/controller/v2/inventories/%s/hosts/", aapInventoryId);
          String aapUserName = config.getString(ConfigKeys.AAP_USER_NAME);
          String aapPassword = config.getString(ConfigKeys.AAP_PASSWORD);

          JsonObject body = new JsonObject();
          body.put("name", ipAddress == null ? hostName : ipAddress);
          body.put("instance_id", hostName);
          if(hostDescription != null)
            body.put("description", Optional.ofNullable(hostDescription).orElse(hostName));

          if(patch) {
            promise.complete();
          } else {
            webClient.post(aapPort, aapHostName, aapUri).ssl(aapSsl)
                .putHeader("Content-Type", "application/json")
                .basicAuthentication(aapUserName, aapPassword)
                .sendJsonObject(body)
                .expecting(HttpResponseExpectation.SC_CREATED)
                .onSuccess(hostResponse -> {
              JsonObject responseBody = hostResponse.bodyAsJsonObject();
              String aapHostId = responseBody.getString("id");
              hostJson.put(Host.VAR_aapHostId, aapHostId);
              promise.complete();
            }).onFailure(ex -> {
              LOG.error(String.format("Updating AAP host failed. "), ex);
              promise.fail(ex);
            });
          }
        }
      }
    } catch(Exception ex) {
      LOG.error(String.format("Updating AAP host failed. "), ex);
      promise.fail(ex);
    }
    return promise.future();
  }

  public Future<Void> sensuUpsertHost(Host o, Boolean inheritPrimaryKey, Boolean patch, JsonObject hostJson) {
    Promise<Void> promise = Promise.promise();
    try {
      if(hostJson == null) {
        promise.complete();
      } else {
        String hostName = hostJson.getString(Host.varJsonHost(Host.VAR_hostName, patch));
        String hostId = hostJson.getString(Host.varJsonHost(Host.VAR_hostId, patch));
        String tenantId = hostJson.getString(Host.varJsonHost(Host.VAR_tenantId, patch));
        hostJson.put(Host.VAR_hostId, hostId);
        JsonArray subscriptions = Optional.ofNullable(hostJson.getJsonArray(patch ? "setEventSubscriptions" : "eventSubscriptions")).orElse(new JsonArray(o.getEventSubscriptions()));

        Integer sensuPort = Integer.parseInt(config.getString(ConfigKeys.SENSU_PORT));
        String sensuHostName = config.getString(ConfigKeys.SENSU_HOST_NAME);
        Boolean sensuSsl = Boolean.parseBoolean(config.getString(ConfigKeys.SENSU_SSL));
        String sensuUri = String.format("/api/core/v2/namespaces/%s/entities/%s", urlEncode(tenantId), urlEncode(hostName));
        String sensuUserName = config.getString(ConfigKeys.SENSU_USER_NAME);
        String sensuPassword = config.getString(ConfigKeys.SENSU_PASSWORD);

        JsonObject body = new JsonObject();
        body.put("entity_class", "proxy");
        body.put("sensu_agent_version", "1.0.0");
        body.put("subscriptions", subscriptions);
        body.put("deregister", false);
        body.put("deregistration", new JsonObject());
        JsonObject metadata = new JsonObject();
        metadata.put("name", hostName);
        metadata.put("namespace", tenantId);
        metadata.put("labels", null);
        metadata.put("annotations", null);
        body.put("metadata", metadata);

        if(StringUtils.isEmpty(hostName)) {
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
                .putHeader("Authorization", String.format("Bearer %s", auth.bodyAsJsonObject().getString("access_token")))
                .sendJsonObject(body)
                .expecting(HttpResponseExpectation.SC_OK)
                .onSuccess(hostResponse -> {
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

  public Future<Void> installSensuAgent(Host host, Boolean inheritPrimaryKey, Boolean patch, JsonObject hostJson) {
    Promise<Void> promise = Promise.promise();
    try {
      String hostName = hostJson.getString(Host.varJsonHost(Host.VAR_hostName, patch));
      String ipAddress = hostJson.getString(Host.varJsonHost(Host.VAR_ipAddress, patch));
      String tenantResource = hostJson.getString(Host.varJsonHost(Host.VAR_tenantResource, patch));
      String jobTemplateResource = String.format("%s-%s-%s", tenantResource, JobTemplate.CLASS_AUTH_RESOURCE, "install-sensu-agent");
      JobTemplate.fqJobTemplate(host.getSiteRequest_(), JobTemplate.VAR_jobTemplateResource, jobTemplateResource).onSuccess(jobTemplate -> {
        try {
          if(jobTemplate == null) {
            RuntimeException ex = new RuntimeException(String.format("Could not find a matching jobTemplate %s", jobTemplateResource));
            LOG.error(ex.getMessage(), ex);
          } else {
            JsonObject config = host.getSiteRequest_().getConfig();
            Long aapTemplateId = jobTemplate.getAapTemplateId();
            String jobTemplateId = jobTemplate.getJobTemplateId();

            Integer aapPort = Integer.parseInt(config.getString(ConfigKeys.AAP_PORT));
            String aapHostName = config.getString(ConfigKeys.AAP_HOST_NAME);
            Boolean aapSsl = Boolean.parseBoolean(config.getString(ConfigKeys.AAP_SSL));
            String aapUri = String.format("/api/controller/v2/job_templates/%s/launch/", aapTemplateId);
            String aapUriRunningJob = String.format("/api/controller/v2/jobs/?status__in=running,pending,waiting&job_template=%s&limit__exact=%s", aapTemplateId, ipAddress);
            String aapUserName = config.getString(ConfigKeys.AAP_USER_NAME);
            String aapPassword = config.getString(ConfigKeys.AAP_PASSWORD);

            webClient.get(aapPort, aapHostName, aapUriRunningJob).ssl(aapSsl)
                .putHeader("Content-Type", "application/json")
                .basicAuthentication(aapUserName, aapPassword)
                .send()
                .expecting(HttpResponseExpectation.SC_OK)
                .onSuccess(runningJobResponse -> {
              JsonObject runningJobResponseBody = runningJobResponse.bodyAsJsonObject();
              if(runningJobResponseBody.getJsonArray("results").size() > 0) {
                LOG.info(String.format("AAP job template %s is already running a job on host %s. ", jobTemplateId, hostName));
              } else {
                JsonObject body = new JsonObject();
                body.put("limit", ipAddress);

                webClient.post(aapPort, aapHostName, aapUri).ssl(aapSsl)
                    .putHeader("Content-Type", "application/json")
                    .basicAuthentication(aapUserName, aapPassword)
                    .sendJsonObject(body)
                    .expecting(HttpResponseExpectation.SC_CREATED)
                    .onSuccess(hostResponse -> {
                  JsonObject responseBody = hostResponse.bodyAsJsonObject();
                  LOG.info(String.format("AAP %s job %s submitted with job template %s on host %s", jobTemplateId, responseBody.getString("job"), responseBody.getString("name"), hostName));
                  promise.complete();
                }).onFailure(ex -> {
                  LOG.error(String.format("Updating AAP host failed. "), ex);
                  promise.fail(ex);
                });
              }
            }).onFailure(ex -> {
              LOG.error(String.format("Updating AAP host failed. "), ex);
              promise.fail(ex);
            });
          }
        } catch(Exception ex) {
          LOG.error("Unable to configure Kafka consumers. ", ex);
          promise.fail(ex);
        }
      }).onFailure(ex -> {
        LOG.error(String.format("Updating AAP host failed. "), ex);
        promise.fail(ex);
      });
    } catch(Exception ex) {
      LOG.error("Unable to configure Kafka consumers. ", ex);
      promise.fail(ex);
    }

    return promise.future();
  }

  @Override
  public Future<Host> sqlPOSTHost(Host o, Boolean inheritPrimaryKey) {
    Promise<Host> promise = Promise.promise();
    aapUpsertParams(o, inheritPrimaryKey, false).onSuccess(hostJson -> {
      aapUpsertHost(o, inheritPrimaryKey, false, hostJson).onSuccess(a -> {
        sensuUpsertHost(o, inheritPrimaryKey, false, hostJson).onSuccess(b -> {
          installSensuAgent(o, inheritPrimaryKey, false, hostJson).onSuccess(c -> {
            super.sqlPOSTHost(o, inheritPrimaryKey).onSuccess(o2 -> {
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
  public Future<Host> sqlPATCHHost(Host o, Boolean inheritPrimaryKey) {
    Promise<Host> promise = Promise.promise();
    aapUpsertParams(o, inheritPrimaryKey, true).onSuccess(hostJson -> {
      aapUpsertHost(o, inheritPrimaryKey, true, hostJson).onSuccess(a -> {
        sensuUpsertHost(o, inheritPrimaryKey, true, hostJson).onSuccess(b -> {
          super.sqlPATCHHost(o, inheritPrimaryKey).onSuccess(o2 -> {
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

  public Future<Void> aapDeleteHost(Host o) {
    Promise<Void> promise = Promise.promise();
    try {
      String hostName = o.getHostName();

      Integer aapPort = Integer.parseInt(config.getString(ConfigKeys.AAP_PORT));
      String aapHostName = config.getString(ConfigKeys.AAP_HOST_NAME);
      Boolean aapSsl = Boolean.parseBoolean(config.getString(ConfigKeys.AAP_SSL));
      String aapUri = String.format("/api/controller/v2/hosts/%s/", o.getAapHostId());
      String aapUserName = config.getString(ConfigKeys.AAP_USER_NAME);
      String aapPassword = config.getString(ConfigKeys.AAP_PASSWORD);

      // if(StringUtils.isEmpty(hostName)) {
      //   RuntimeException ex = new RuntimeException("Missing host name");
      //   LOG.error(ex.getMessage(), ex);
      //   promise.fail(ex);
      // } else {
        webClient.delete(aapPort, aapHostName, aapUri).ssl(aapSsl)
            .basicAuthentication(aapUserName, aapPassword)
            .send()
            .expecting(HttpResponseExpectation.SC_NO_CONTENT.or(HttpResponseExpectation.SC_ACCEPTED).or(HttpResponseExpectation.SC_NOT_FOUND))
            .onSuccess(HostResponse -> {
          promise.complete();
        }).onFailure(ex -> {
          LOG.error(String.format("Deleting Sensu host failed. "), ex);
          promise.fail(ex);
        });
      // }
    } catch(Exception ex) {
      LOG.error(String.format("Deleting Sensu host failed. "), ex);
      promise.fail(ex);
    }
    return promise.future();
  }

  public Future<Void> sensuDeleteHost(Host o) {
    Promise<Void> promise = Promise.promise();
    try {
      String ipAddress = o.getIpAddress();
      String tenantId = o.getTenantId();

      Integer sensuPort = Integer.parseInt(config.getString(ConfigKeys.SENSU_PORT));
      String sensuHostName = config.getString(ConfigKeys.SENSU_HOST_NAME);
      Boolean sensuSsl = Boolean.parseBoolean(config.getString(ConfigKeys.SENSU_SSL));
      String sensuUri = String.format("/api/core/v2/namespaces/%s/entities/%s", urlEncode(tenantId), urlEncode(ipAddress));
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
            .onSuccess(HostResponse -> {
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
  public Future<Void> sqlDELETEFilterHost(Host o) {
    Promise<Void> promise = Promise.promise();
    sensuDeleteHost(o).onSuccess(a -> {
      aapDeleteHost(o).onSuccess(b -> {
        super.sqlDELETEFilterHost(o).onSuccess(c -> {
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
  public Future<Void> sqlDELETEHost(Host o) {
    Promise<Void> promise = Promise.promise();
    sensuDeleteHost(o).onSuccess(a -> {
      aapDeleteHost(o).onSuccess(b -> {
        super.sqlDELETEHost(o).onSuccess(c -> {
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
