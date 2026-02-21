package org.computate.dcm.model.eda.host;

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
import org.computate.dcm.request.SiteRequest;
import org.computate.vertx.search.list.SearchList;

/**
 * Translate: false
 **/
public class HostEnUSApiServiceImpl extends HostEnUSGenApiServiceImpl {

  public Future<Void> aapUpsertHost(Host o, Boolean inheritPrimaryKey, Boolean patch) {
    Promise<Void> promise = Promise.promise();
    try {
      SiteRequest siteRequest = o.getSiteRequest_();
      ServiceRequest serviceRequest = siteRequest.getServiceRequest();
      if(Optional.ofNullable(serviceRequest.getParams()).map(p -> p.getJsonObject("query")).map( q -> q.getJsonArray("var")).orElse(new JsonArray()).stream().filter(s -> "refresh:false".equals(s)).count() > 0L) {
        promise.complete();
      } else {
        JsonObject hostJson = o.getSiteRequest_().getJsonObject();
        String inventoryResource = Optional.ofNullable(hostJson.getString(patch ? "setInventoryResource": "inventoryResource")).orElse(o.getInventoryResource());
        String hostName = Optional.ofNullable(hostJson.getString(patch ? "setHostName": "hostName")).orElse(o.getHostName());
        String ipAddress = Optional.ofNullable(hostJson.getString(patch ? "setIpAddress": "ipAddress")).orElse(o.getIpAddress());
        String hostId = Optional.ofNullable(hostJson.getString(patch ? "setHostId": "hostId")).orElse(Host.toId(hostName));
        hostJson.put(Host.VAR_hostId, hostId);
        String hostResource = String.format("%s-%s", Host.CLASS_AUTH_RESOURCE, hostId);
        hostJson.put(Host.VAR_hostResource, hostResource);

        HostInventory.fq(siteRequest, HostInventory.VAR_inventoryResource, inventoryResource).onSuccess(inventory -> {
          try {
            if(inventory == null) {
              RuntimeException ex = new RuntimeException(String.format("Could not find a matching host inventory %s", inventoryResource));
              LOG.error(ex.getMessage(), ex);
              promise.fail(ex);
            } else if(inventory.getAapInventoryId() == null) {
              RuntimeException ex = new RuntimeException(String.format("The host inventory %s doesn't have an AAP Inventory ID number", inventoryResource));
              LOG.error(ex.getMessage(), ex);
              promise.fail(ex);
            } else if(hostName == null) {
              RuntimeException ex = new RuntimeException(String.format("The host name cannot be null"));
              LOG.error(ex.getMessage(), ex);
              promise.fail(ex);
            } else {
              Long aapInventoryId = inventory.getAapInventoryId();
              Integer aapPort = Integer.parseInt(config.getString(ConfigKeys.AAP_PORT));
              String aapHostName = config.getString(ConfigKeys.AAP_HOST_NAME);
              Boolean aapSsl = Boolean.parseBoolean(config.getString(ConfigKeys.AAP_SSL));
              String aapUri = patch ? String.format("/api/controller/v2/hosts/%s/", o.getAapHostId()) : String.format("/api/controller/v2/inventories/%s/hosts/", aapInventoryId);
              String aapUserName = config.getString(ConfigKeys.AAP_USER_NAME);
              String aapPassword = config.getString(ConfigKeys.AAP_PASSWORD);

              JsonObject body = new JsonObject();
              String hostDescription = Optional.ofNullable(hostJson.getString(patch ? "setHostDescription": "hostDescription")).orElse(o.getHostDescription());
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
          } catch(Exception ex) {
            LOG.error(String.format("Updating Sensu host failed. "), ex);
            promise.fail(ex);
          }
        }).onFailure(ex -> {
          LOG.error(String.format("search HostInventory failed. "), ex);
          promise.tryFail(ex);
        });
      }
    } catch(Exception ex) {
      LOG.error(String.format("Updating AAP host failed. "), ex);
      promise.fail(ex);
    }
    return promise.future();
  }

  public Future<Void> sensuUpsertHost(Host o, Boolean inheritPrimaryKey, Boolean patch) {
    Promise<Void> promise = Promise.promise();
    try {
      SiteRequest siteRequest = o.getSiteRequest_();
      ServiceRequest serviceRequest = siteRequest.getServiceRequest();
      if(Optional.ofNullable(serviceRequest.getParams()).map(p -> p.getJsonObject("query")).map( q -> q.getJsonArray("var")).orElse(new JsonArray()).stream().filter(s -> "refresh:false".equals(s)).count() > 0L) {
        promise.complete();
      } else {
        JsonObject hostJson = o.getSiteRequest_().getJsonObject();
        String inventoryResource = Optional.ofNullable(hostJson.getString(patch ? "setInventoryResource": "inventoryResource")).orElse(o.getInventoryResource());
        String hostName = Optional.ofNullable(hostJson.getString(patch ? "setHostName": "hostName")).orElse(o.getHostName());
        String ipAddress = Optional.ofNullable(hostJson.getString(patch ? "setIpAddress": "ipAddress")).orElse(o.getIpAddress());
        String hostId = Optional.ofNullable(hostJson.getString(patch ? "setHostId": "hostId")).orElse(HostInventory.toId(hostName));
        hostJson.put(Host.VAR_hostId, hostId);
        String hostResource = String.format("%s-%s", HostInventory.CLASS_AUTH_RESOURCE, hostId);
        hostJson.put(Host.VAR_hostResource, hostResource);
        JsonArray subscriptions = Optional.ofNullable(hostJson.getJsonArray(patch ? "setEventSubscriptions" : "eventSubscriptions")).orElse(new JsonArray(o.getEventSubscriptions()));

        Integer sensuPort = Integer.parseInt(config.getString(ConfigKeys.SENSU_PORT));
        String sensuHostName = config.getString(ConfigKeys.SENSU_HOST_NAME);
        Boolean sensuSsl = Boolean.parseBoolean(config.getString(ConfigKeys.SENSU_SSL));
        String sensuUri = String.format("/api/core/v2/namespaces/default/entities/%s", urlEncode(hostName));
        String accessToken = config.getString(ConfigKeys.SENSU_TOKEN);

        JsonObject body = new JsonObject();
        body.put("entity_class", "proxy");
        body.put("sensu_agent_version", "1.0.0");
        body.put("subscriptions", subscriptions);
        body.put("deregister", false);
        body.put("deregistration", new JsonObject());
        JsonObject metadata = new JsonObject();
        metadata.put("name", hostName);
        metadata.put("namespace", "default");
        metadata.put("labels", null);
        metadata.put("annotations", null);
        body.put("metadata", metadata);

        if(StringUtils.isEmpty(hostName)) {
          RuntimeException ex = new RuntimeException("Missing host name");
          LOG.error(ex.getMessage(), ex);
          promise.fail(ex);
        } else {
          webClient.put(sensuPort, sensuHostName, sensuUri).ssl(sensuSsl)
              .putHeader("Authorization", String.format("Key %s", accessToken))
              .sendJsonObject(body)
              .expecting(HttpResponseExpectation.SC_OK)
              .onSuccess(HostResponse -> {
            promise.complete();
          }).onFailure(ex -> {
            LOG.error(String.format("Updating Sensu host failed. "), ex);
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
  public Future<Host> sqlPOSTHost(Host o, Boolean inheritPrimaryKey) {
    Promise<Host> promise = Promise.promise();
    aapUpsertHost(o, inheritPrimaryKey, false).onSuccess(a -> {
      sensuUpsertHost(o, inheritPrimaryKey, false).onSuccess(b -> {
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
    return promise.future();
  }

  @Override
  public Future<Host> sqlPATCHHost(Host o, Boolean inheritPrimaryKey) {
    Promise<Host> promise = Promise.promise();
    sensuUpsertHost(o, inheritPrimaryKey, true).onSuccess(a -> {
      super.sqlPATCHHost(o, inheritPrimaryKey).onSuccess(o2 -> {
        promise.complete(o2);
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
            .expecting(HttpResponseExpectation.SC_NO_CONTENT.or(HttpResponseExpectation.SC_NOT_FOUND))
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

      Integer sensuPort = Integer.parseInt(config.getString(ConfigKeys.SENSU_PORT));
      String sensuHostName = config.getString(ConfigKeys.SENSU_HOST_NAME);
      Boolean sensuSsl = Boolean.parseBoolean(config.getString(ConfigKeys.SENSU_SSL));
      String sensuUri = String.format("/api/core/v2/namespaces/default/entities/%s", ipAddress);
      String accessToken = config.getString(ConfigKeys.SENSU_TOKEN);

      // if(StringUtils.isEmpty(hostName)) {
      //   RuntimeException ex = new RuntimeException("Missing host name");
      //   LOG.error(ex.getMessage(), ex);
      //   promise.fail(ex);
      // } else {
        webClient.delete(sensuPort, sensuHostName, sensuUri).ssl(sensuSsl)
            .putHeader("Authorization", String.format("Key %s", accessToken))
            .send()
            .expecting(HttpResponseExpectation.SC_NO_CONTENT.or(HttpResponseExpectation.SC_NOT_FOUND))
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
