package org.computate.dcm.model.eda.tenant;

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
import io.vertx.core.json.JsonObject;

import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.computate.dcm.config.ConfigKeys;
import org.computate.dcm.request.SiteRequest;
import org.computate.vertx.search.list.SearchList;

/**
 * Translate: false
 **/
public class TenantEnUSApiServiceImpl extends TenantEnUSGenApiServiceImpl {

  public Future<Void> aapUpsertOrganization(Tenant o, Boolean inheritPrimaryKey, Boolean patch) {
    Promise<Void> promise = Promise.promise();
    try {
      SiteRequest siteRequest = o.getSiteRequest_();
      ServiceRequest serviceRequest = siteRequest.getServiceRequest();
      if(Optional.ofNullable(serviceRequest.getParams()).map(p -> p.getJsonObject("query")).map( q -> q.getJsonArray("var")).orElse(new JsonArray()).stream().filter(s -> "refresh:false".equals(s)).count() > 0L) {
        promise.complete();
      } else {
        JsonObject tenantJson = o.getSiteRequest_().getJsonObject();
        String tenantName = Optional.ofNullable(tenantJson.getString(patch ? "setTenantName": "tenantName")).orElse(o.getTenantName());
        String tenantId = Optional.ofNullable(tenantJson.getString(patch ? "setTenantId": "tenantId")).orElse(Tenant.toId(tenantName));
        tenantJson.put(Tenant.VAR_tenantId, tenantId);
        String tenantResource = String.format("%s-%s", Tenant.CLASS_AUTH_RESOURCE, tenantId);
        tenantJson.put(Tenant.VAR_tenantResource, tenantResource);

        Integer aapPort = Integer.parseInt(config.getString(ConfigKeys.AAP_PORT));
        String aapTenantName = config.getString(ConfigKeys.AAP_HOST_NAME);
        Boolean aapSsl = Boolean.parseBoolean(config.getString(ConfigKeys.AAP_SSL));
        String aapUri = patch ? String.format("/api/controller/v2/tenants/%s/", o.getAapOrganizationId()) : "/api/controller/v2/organizations/";
        String aapUserName = config.getString(ConfigKeys.AAP_USER_NAME);
        String aapPassword = config.getString(ConfigKeys.AAP_PASSWORD);

        JsonObject body = new JsonObject();
        String tenantDescription = Optional.ofNullable(tenantJson.getString(patch ? "setTenantDescription": "tenantDescription")).orElse(o.getTenantDescription());
        body.put("name", tenantName);
        if(tenantDescription != null)
          body.put("description", Optional.ofNullable(tenantDescription).orElse(tenantName));

        if(patch) {
          promise.complete();
        } else {
          webClient.post(aapPort, aapTenantName, aapUri).ssl(aapSsl)
              .putHeader("Content-Type", "application/json")
              .basicAuthentication(aapUserName, aapPassword)
              .sendJsonObject(body)
              .expecting(HttpResponseExpectation.SC_CREATED)
              .onSuccess(tenantResponse -> {
            JsonObject responseBody = tenantResponse.bodyAsJsonObject();
            String aapOrganizationId = responseBody.getString("id");
            tenantJson.put(Tenant.VAR_aapOrganizationId, aapOrganizationId);
            promise.complete();
          }).onFailure(ex -> {
            LOG.error(String.format("Updating AAP tenant failed. "), ex);
            promise.fail(ex);
          });
        }
      }
    } catch(Exception ex) {
      LOG.error(String.format("Updating AAP tenant failed. "), ex);
      promise.fail(ex);
    }
    return promise.future();
  }

  public Future<Void> sensuUpsertNamespace(Tenant o, Boolean inheritPrimaryKey, Boolean patch) {
    Promise<Void> promise = Promise.promise();
    try {
      SiteRequest siteRequest = o.getSiteRequest_();
      ServiceRequest serviceRequest = siteRequest.getServiceRequest();
      if(Optional.ofNullable(serviceRequest.getParams()).map(p -> p.getJsonObject("query")).map( q -> q.getJsonArray("var")).orElse(new JsonArray()).stream().filter(s -> "refresh:false".equals(s)).count() > 0L) {
        promise.complete();
      } else {
        JsonObject tenantJson = o.getSiteRequest_().getJsonObject();
        String tenantName = Optional.ofNullable(tenantJson.getString(patch ? "setTenantName": "tenantName")).orElse(o.getTenantName());
        String tenantId = Optional.ofNullable(tenantJson.getString(patch ? "setTenantId": "tenantId")).orElse(Tenant.toId(tenantName));
        tenantJson.put(Tenant.VAR_tenantId, tenantId);

        Integer sensuPort = Integer.parseInt(config.getString(ConfigKeys.SENSU_PORT));
        String sensuTenantName = config.getString(ConfigKeys.SENSU_HOST_NAME);
        Boolean sensuSsl = Boolean.parseBoolean(config.getString(ConfigKeys.SENSU_SSL));
        String sensuUri = String.format("/api/core/v2/namespaces/%s", urlEncode(tenantId));
        String accessToken = config.getString(ConfigKeys.SENSU_TOKEN);

        JsonObject body = new JsonObject();
        body.put("name", tenantId);

        if(StringUtils.isEmpty(tenantId)) {
          RuntimeException ex = new RuntimeException("Missing tenant ID");
          LOG.error(ex.getMessage(), ex);
          promise.fail(ex);
        } else {
          webClient.put(sensuPort, sensuTenantName, sensuUri).ssl(sensuSsl)
              .putHeader("Authorization", String.format("Key %s", accessToken))
              .sendJsonObject(body)
              .expecting(HttpResponseExpectation.SC_OK.or(HttpResponseExpectation.SC_CREATED))
              .onSuccess(TenantResponse -> {
            promise.complete();
          }).onFailure(ex -> {
            LOG.error(String.format("Updating Sensu namespace failed. "), ex);
            promise.fail(ex);
          });
        }
      }
    } catch(Exception ex) {
      LOG.error(String.format("Updating Sensu namespace failed. "), ex);
      promise.fail(ex);
    }
    return promise.future();
  }

  @Override
  public Future<Tenant> sqlPOSTTenant(Tenant o, Boolean inheritPrimaryKey) {
    Promise<Tenant> promise = Promise.promise();
    aapUpsertOrganization(o, inheritPrimaryKey, false).onSuccess(a -> {
      sensuUpsertNamespace(o, inheritPrimaryKey, false).onSuccess(b -> {
        super.sqlPOSTTenant(o, inheritPrimaryKey).onSuccess(o2 -> {
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
  public Future<Tenant> sqlPATCHTenant(Tenant o, Boolean inheritPrimaryKey) {
    Promise<Tenant> promise = Promise.promise();
    sensuUpsertNamespace(o, inheritPrimaryKey, true).onSuccess(a -> {
      super.sqlPATCHTenant(o, inheritPrimaryKey).onSuccess(o2 -> {
        promise.complete(o2);
      }).onFailure(ex -> {
        promise.fail(ex);
      });
    }).onFailure(ex -> {
      promise.fail(ex);
    });
    return promise.future();
  }

  public Future<Void> aapDeleteTenant(Tenant o) {
    Promise<Void> promise = Promise.promise();
    try {
      Integer aapPort = Integer.parseInt(config.getString(ConfigKeys.AAP_PORT));
      String aapTenantName = config.getString(ConfigKeys.AAP_HOST_NAME);
      Boolean aapSsl = Boolean.parseBoolean(config.getString(ConfigKeys.AAP_SSL));
      String aapUri = String.format("/api/controller/v2/organizations/%s/", o.getAapOrganizationId());
      String aapUserName = config.getString(ConfigKeys.AAP_USER_NAME);
      String aapPassword = config.getString(ConfigKeys.AAP_PASSWORD);

      webClient.delete(aapPort, aapTenantName, aapUri).ssl(aapSsl)
          .basicAuthentication(aapUserName, aapPassword)
          .send()
          .expecting(HttpResponseExpectation.SC_NO_CONTENT.or(HttpResponseExpectation.SC_NOT_FOUND))
          .onSuccess(TenantResponse -> {
        promise.complete();
      }).onFailure(ex -> {
        LOG.error(String.format("Deleting Sensu tenant failed. "), ex);
        promise.fail(ex);
      });
    } catch(Exception ex) {
      LOG.error(String.format("Deleting Sensu tenant failed. "), ex);
      promise.fail(ex);
    }
    return promise.future();
  }

  public Future<Void> sensuDeleteTenant(Tenant o) {
    Promise<Void> promise = Promise.promise();
    try {
      Integer sensuPort = Integer.parseInt(config.getString(ConfigKeys.SENSU_PORT));
      String sensuTenantName = config.getString(ConfigKeys.SENSU_HOST_NAME);
      Boolean sensuSsl = Boolean.parseBoolean(config.getString(ConfigKeys.SENSU_SSL));
      String sensuUri = String.format("/api/core/v2/namespaces/%s", o.getTenantId());
      String accessToken = config.getString(ConfigKeys.SENSU_TOKEN);

      webClient.delete(sensuPort, sensuTenantName, sensuUri).ssl(sensuSsl)
          .putHeader("Authorization", String.format("Key %s", accessToken))
          .send()
          .expecting(HttpResponseExpectation.SC_NO_CONTENT.or(HttpResponseExpectation.SC_NOT_FOUND))
          .onSuccess(TenantResponse -> {
        promise.complete();
      }).onFailure(ex -> {
        LOG.error(String.format("Deleting Sensu tenant failed. "), ex);
        promise.fail(ex);
      });
    } catch(Exception ex) {
      LOG.error(String.format("Deleting Sensu tenant failed. "), ex);
      promise.fail(ex);
    }
    return promise.future();
  }

  @Override
  public Future<Void> sqlDELETEFilterTenant(Tenant o) {
    Promise<Void> promise = Promise.promise();
    sensuDeleteTenant(o).onSuccess(a -> {
      aapDeleteTenant(o).onSuccess(b -> {
        super.sqlDELETEFilterTenant(o).onSuccess(c -> {
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
  public Future<Void> sqlDELETETenant(Tenant o) {
    Promise<Void> promise = Promise.promise();
    sensuDeleteTenant(o).onSuccess(a -> {
      aapDeleteTenant(o).onSuccess(b -> {
        super.sqlDELETETenant(o).onSuccess(c -> {
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
