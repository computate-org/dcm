package org.computate.dcm.model.eda.hostinventory;

import io.vertx.ext.web.api.service.ServiceRequest;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.http.HttpResponseExpectation;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import java.util.Optional;
import org.apache.commons.lang3.StringUtils;
import org.computate.dcm.config.ConfigKeys;
import org.computate.dcm.model.eda.tenant.Tenant;
import org.computate.dcm.request.SiteRequest;

/**
 * Translate: false
 **/
public class HostInventoryEnUSApiServiceImpl extends HostInventoryEnUSGenApiServiceImpl {

  public Future<Void> aapUpsertHostInventory(HostInventory o, Boolean inheritPrimaryKey, Boolean patch) {
    Promise<Void> promise = Promise.promise();
    try {
      SiteRequest siteRequest = o.getSiteRequest_();
      ServiceRequest serviceRequest = siteRequest.getServiceRequest();
      if(Optional.ofNullable(serviceRequest.getParams()).map(p -> p.getJsonObject("query")).map( q -> q.getJsonArray("var")).orElse(new JsonArray()).stream().filter(s -> "refresh:false".equals(s)).count() > 0L) {
        promise.complete();
      } else {
        JsonObject inventoryJson = o.getSiteRequest_().getJsonObject();
        String tenantResource = Optional.ofNullable(inventoryJson.getString(HostInventory.varJsonHostInventory(HostInventory.VAR_tenantResource, patch))).orElse(o.getTenantResource());
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
              String tenantId = Optional.ofNullable(Optional.ofNullable(inventoryJson.getString(HostInventory.varJsonHostInventory(HostInventory.VAR_tenantId, patch))).orElse(o.getTenantId())).orElse(tenant.getTenantId());
              inventoryJson.put(HostInventory.varJsonHostInventory(HostInventory.VAR_tenantId, patch), tenantId);

              Long aapOrganizationId = Optional.ofNullable(Optional.ofNullable(inventoryJson.getLong(HostInventory.varJsonHostInventory(HostInventory.VAR_aapOrganizationId, patch))).orElse(o.getAapOrganizationId()))
                  .orElse(Optional.ofNullable(tenant).map(t -> t.getAapOrganizationId()).orElse(1L));
              inventoryJson.put(HostInventory.varJsonHostInventory(HostInventory.VAR_aapOrganizationId, patch), aapOrganizationId.toString());

              String inventoryName = Optional.ofNullable(inventoryJson.getString(HostInventory.varJsonHostInventory(HostInventory.VAR_inventoryName, patch))).orElse(o.getInventoryName());
              String inventoryId = Optional.ofNullable(inventoryJson.getString(HostInventory.varJsonHostInventory(HostInventory.VAR_inventoryId, patch))).orElse(HostInventory.toId(inventoryName));
              inventoryJson.put(HostInventory.varJsonHostInventory(HostInventory.VAR_inventoryId, patch), inventoryId);
              String inventoryResource = String.format("%s-%s-%s", tenantResource, HostInventory.CLASS_AUTH_RESOURCE, inventoryId);
              inventoryJson.put(HostInventory.varJsonHostInventory(HostInventory.VAR_inventoryResource, patch), inventoryResource);
              Long aapInventoryId = Optional.ofNullable(inventoryJson.getString(HostInventory.varJsonHostInventory(HostInventory.VAR_aapInventoryId, patch))).map(s -> Long.parseLong(s)).orElse(o.getAapInventoryId());
              String inventoryDescription = Optional.ofNullable(inventoryJson.getString(HostInventory.varJsonHostInventory(HostInventory.VAR_inventoryDescription, patch))).orElse(o.getInventoryDescription());
              String inventoryKind = Optional.ofNullable(Optional.ofNullable(inventoryJson.getString(HostInventory.varJsonHostInventory(HostInventory.VAR_inventoryKind, patch))).orElse(o.getInventoryKind())).orElse("");

              Integer aapPort = Integer.parseInt(config.getString(ConfigKeys.AAP_PORT));
              String aapInventoryName = config.getString(ConfigKeys.AAP_HOST_NAME);
              Boolean aapSsl = Boolean.parseBoolean(config.getString(ConfigKeys.AAP_SSL));
              String aapUri = patch ? String.format("/api/controller/v2/inventories/%s/", aapInventoryId) : "/api/controller/v2/inventories/";
              String aapUserName = config.getString(ConfigKeys.AAP_USER_NAME);
              String aapPassword = config.getString(ConfigKeys.AAP_PASSWORD);

              JsonObject body = new JsonObject();
              body.put("name", inventoryName);
              body.put("kind", inventoryKind);
              if(inventoryDescription != null)
                body.put("description", inventoryDescription);
              body.put("organization", aapOrganizationId);

              if(StringUtils.isEmpty(inventoryName)) {
                RuntimeException ex = new RuntimeException("Missing inventory name");
                LOG.error(ex.getMessage(), ex);
                promise.fail(ex);
              } else {
                if(patch) {
                  webClient.patch(aapPort, aapInventoryName, aapUri).ssl(aapSsl)
                      .putHeader("Content-Type", "application/json")
                      .basicAuthentication(aapUserName, aapPassword)
                      .sendJsonObject(body)
                      .expecting(HttpResponseExpectation.SC_OK)
                      .onSuccess(inventoryResponse -> {
                    promise.complete();
                  }).onFailure(ex -> {
                    LOG.error(String.format("Updating AAP inventory failed. "), ex);
                    promise.fail(ex);
                  });
                } else {
                  webClient.post(aapPort, aapInventoryName, aapUri).ssl(aapSsl)
                      .putHeader("Content-Type", "application/json")
                      .basicAuthentication(aapUserName, aapPassword)
                      .sendJsonObject(body)
                      .expecting(HttpResponseExpectation.SC_CREATED)
                      .onSuccess(inventoryResponse -> {
                    JsonObject responseBody = inventoryResponse.bodyAsJsonObject();
                    String aapInventoryId2 = responseBody.getString("id");
                    inventoryJson.put(HostInventory.VAR_aapInventoryId, aapInventoryId2);
                    promise.complete();
                  }).onFailure(ex -> {
                    LOG.error(String.format("Updating AAP inventory failed. "), ex);
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
      LOG.error(String.format("Updating AAP inventory failed. "), ex);
      promise.fail(ex);
    }
    return promise.future();
  }

  @Override
  public Future<HostInventory> sqlPOSTHostInventory(HostInventory o, Boolean inheritPrimaryKey) {
    Promise<HostInventory> promise = Promise.promise();
    aapUpsertHostInventory(o, inheritPrimaryKey, false).onSuccess(a -> {
      super.sqlPOSTHostInventory(o, inheritPrimaryKey).onSuccess(o2 -> {
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
  public Future<HostInventory> sqlPATCHHostInventory(HostInventory o, Boolean inheritPrimaryKey) {
    Promise<HostInventory> promise = Promise.promise();
    aapUpsertHostInventory(o, inheritPrimaryKey, true).onSuccess(a -> {
      super.sqlPATCHHostInventory(o, inheritPrimaryKey).onSuccess(o2 -> {
        promise.complete(o2);
      }).onFailure(ex -> {
        promise.fail(ex);
      });
    }).onFailure(ex -> {
      promise.fail(ex);
    });
    return promise.future();
  }

  public Future<Void> aapDeleteHostInventory(HostInventory o) {
    Promise<Void> promise = Promise.promise();
    try {
      SiteRequest siteRequest = o.getSiteRequest_();
      ServiceRequest serviceRequest = siteRequest.getServiceRequest();
      if(Optional.ofNullable(serviceRequest.getParams()).map(p -> p.getJsonObject("query")).map( q -> q.getJsonArray("var")).orElse(new JsonArray()).stream().filter(s -> "refresh:false".equals(s)).count() > 0L) {
        promise.complete();
      } else {
        Long aapInventoryId = o.getAapInventoryId();

        Integer aapPort = Integer.parseInt(config.getString(ConfigKeys.AAP_PORT));
        String aapInventoryName = config.getString(ConfigKeys.AAP_HOST_NAME);
        Boolean aapSsl = Boolean.parseBoolean(config.getString(ConfigKeys.AAP_SSL));
        String aapUri = String.format("/api/controller/v2/inventories/%s/", aapInventoryId);
        String aapUserName = config.getString(ConfigKeys.AAP_USER_NAME);
        String aapPassword = config.getString(ConfigKeys.AAP_PASSWORD);

        if(aapInventoryId == null) {
          RuntimeException ex = new RuntimeException("Missing inventory ID");
          LOG.error(ex.getMessage(), ex);
          promise.fail(ex);
        } else {
          webClient.delete(aapPort, aapInventoryName, aapUri).ssl(aapSsl)
              .putHeader("Content-Type", "application/json")
              .basicAuthentication(aapUserName, aapPassword)
              .send()
              .expecting(HttpResponseExpectation.SC_NO_CONTENT.or(HttpResponseExpectation.SC_ACCEPTED).or(HttpResponseExpectation.SC_NOT_FOUND))
              .onSuccess(inventoryResponse -> {
            promise.complete();
          }).onFailure(ex -> {
            LOG.error(String.format("Deleting AAP inventory failed. "), ex);
            promise.fail(ex);
          });
        }
      }
    } catch(Exception ex) {
      LOG.error(String.format("Deleting AAP inventory failed. "), ex);
      promise.fail(ex);
    }
    return promise.future();
  }

  @Override
  public Future<Void> sqlDELETEFilterHostInventory(HostInventory o) {
    Promise<Void> promise = Promise.promise();
    aapDeleteHostInventory(o).onSuccess(a -> {
      super.sqlDELETEFilterHostInventory(o).onSuccess(o2 -> {
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
  public Future<Void> sqlDELETEHostInventory(HostInventory o) {
    Promise<Void> promise = Promise.promise();
    aapDeleteHostInventory(o).onSuccess(a -> {
      super.sqlDELETEHostInventory(o).onSuccess(o2 -> {
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
