package org.computate.dcm.model.eda.tenant.requested;

import io.vertx.ext.auth.authorization.AuthorizationProvider;
import io.vertx.ext.auth.oauth2.OAuth2Auth;
import io.vertx.ext.web.api.service.ServiceRequest;
import io.vertx.ext.web.client.WebClient;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.WorkerExecutor;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.sqlclient.Pool;

import java.util.Optional;

import org.computate.dcm.model.eda.tenant.intent.TenantIntent;
import org.computate.dcm.request.SiteRequest;
import org.computate.search.tool.SearchTool;
import org.computate.vertx.openapi.ComputateOAuth2AuthHandlerImpl;
import org.computate.vertx.search.list.SearchList;

import io.vertx.kafka.client.producer.KafkaProducer;
import io.vertx.mqtt.MqttClient;
import io.vertx.amqp.AmqpSender;
import io.vertx.rabbitmq.RabbitMQClient;
import com.hubspot.jinjava.Jinjava;

/**
 * Translate: false
 **/
public class TenantRequestedEnUSApiServiceImpl extends TenantRequestedEnUSGenApiServiceImpl {

  @Override
  public Future<JsonObject> upsertTenantRequested(TenantRequested o, Boolean inheritPrimaryKey, Boolean patch) {
    Promise<JsonObject> promise = Promise.promise();
    try {
      SiteRequest siteRequest = o.getSiteRequest_();
      ServiceRequest serviceRequest = siteRequest.getServiceRequest();
      if(Optional.ofNullable(serviceRequest.getParams()).map(p -> p.getJsonObject("query")).map( q -> q.getJsonArray("var")).orElse(new JsonArray()).stream().filter(s -> "refresh:false".equals(s)).count() > 0L) {
        promise.complete();
      } else {
        JsonObject json = o.getSiteRequest_().getJsonObject();

        String old_tenantResource = TenantRequested.staticJsonTenantResource(o.getTenantResource());
        String new_tenantResource = json.getString(TenantIntent.varJson(TenantIntent.VAR_tenantResource, patch));
        String tenantResource = Optional.ofNullable(new_tenantResource).orElse(old_tenantResource);
        TenantIntent.fqTenantIntent(siteRequest, TenantIntent.VAR_tenantResource, tenantResource).onSuccess(oTenantIntent -> {
          try {
            if(oTenantIntent == null) {
              RuntimeException ex = new RuntimeException(String.format("Could not find a matching TenantIntent %s", tenantResource));
              LOG.error(ex.getMessage(), ex);
              promise.fail(ex);
            } else {
              json.put(TenantIntent.varJson(TenantIntent.VAR_tenantResource, patch), tenantResource);

              SearchList<TenantRequested> searchList = new SearchList<TenantRequested>();
              searchList.setStore(true);
              searchList.q("*:*");
              searchList.setC(TenantRequested.class);
              searchList.fq(String.format("%s:", TenantRequested.varIndexedTenantRequested(TenantRequested.VAR_tenantResource)) + SearchTool.escapeQueryChars(tenantResource));
              searchList.statsField("requestedNumber_docvalues_int");
              searchList.rows(0);
              searchList.promiseDeepForClass(siteRequest).onSuccess(a -> {
                try {
                  String old_requestedNumber = TenantRequested.staticJsonRequestedNumber(o.getRequestedNumber());
                  String new_requestedNumber = Integer.toString((Optional.ofNullable(searchList.getResponse().getStats().get("requestedNumber_docvalues_int")).map(stats -> (Double)stats.getMax()).map(max -> max.intValue() + 1).orElse(1)));
                  String requestedNumber = Optional.ofNullable(old_requestedNumber).orElse(new_requestedNumber);
                  json.put(TenantRequested.varJson(TenantRequested.VAR_requestedNumber, patch), requestedNumber.toString());

                  Boolean old_locked = TenantRequested.staticJsonLocked(o.getLocked());
                  Boolean new_locked = json.getBoolean(TenantRequested.varJson(TenantRequested.VAR_locked, patch));
                  Boolean locked = Optional.ofNullable(Optional.ofNullable(new_locked).orElse(old_locked)).orElse(false);
                  if(locked)
                    throw new RuntimeException(String.format("Cannot update this %s %s, because it has already been requested and locked. ", TenantRequested.SingularName_enUS, o.getTenantResource()));

                  super.upsertTenantRequested(o, inheritPrimaryKey, patch).onSuccess(json2 -> {
                    promise.complete(json2);
                  }).onFailure(ex -> promise.fail(ex));
                } catch(Exception ex) {
                  LOG.error(String.format("upsertTenantRequested failed. "), ex);
                  promise.tryFail(ex);
                }
              }).onFailure(ex -> {
                promise.fail(ex);
              });
            }
          } catch(Exception ex) {
            LOG.error(String.format("upsertTenantRequested failed. "), ex);
            promise.tryFail(ex);
          }
        }).onFailure(ex -> {
          promise.fail(ex);
        });
      }
    } catch(Exception ex) {
      LOG.error(String.format("searchTenantRequested failed. "), ex);
      promise.tryFail(ex);
    }
    return promise.future();
  }

  @Override
  public Future<TenantRequested> sqlPOSTTenantRequested(TenantRequested o, Boolean inheritPrimaryKey) {
    Promise<TenantRequested> promise = Promise.promise();
    upsertTenantRequested(o, inheritPrimaryKey, false).onSuccess(hostCheckJson -> {
      super.sqlPOSTTenantRequested(o, inheritPrimaryKey).onSuccess(o2 -> {
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
  public Future<TenantRequested> sqlPATCHTenantRequested(TenantRequested o, Boolean inheritPrimaryKey) {
    Promise<TenantRequested> promise = Promise.promise();
    upsertTenantRequested(o, inheritPrimaryKey, true).onSuccess(hostCheckJson -> {
      super.sqlPATCHTenantRequested(o, inheritPrimaryKey).onSuccess(o2 -> {
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
