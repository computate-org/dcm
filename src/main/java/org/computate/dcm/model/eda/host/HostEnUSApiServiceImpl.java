package org.computate.dcm.model.eda.host;

import io.vertx.ext.auth.authorization.AuthorizationProvider;
import io.vertx.ext.auth.oauth2.OAuth2Auth;
import io.vertx.ext.web.api.service.ServiceRequest;
import io.vertx.ext.web.client.WebClient;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.WorkerExecutor;
import io.vertx.core.http.HttpResponseExpectation;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.sqlclient.Pool;

import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.computate.dcm.config.ConfigKeys;
import org.computate.dcm.request.SiteRequest;
import org.computate.vertx.openapi.ComputateOAuth2AuthHandlerImpl;
import io.vertx.kafka.client.producer.KafkaProducer;
import io.vertx.mqtt.MqttClient;
import io.vertx.amqp.AmqpSender;
import io.vertx.rabbitmq.RabbitMQClient;
import com.hubspot.jinjava.Jinjava;

/**
 * Translate: false
 **/
public class HostEnUSApiServiceImpl extends HostEnUSGenApiServiceImpl {

  public Future<Host> sqlUpsertHost(Host o, Boolean inheritPrimaryKey, Boolean patch) {
    Promise<Host> promise = Promise.promise();
    try {
      SiteRequest siteRequest = o.getSiteRequest_();
      ServiceRequest serviceRequest = siteRequest.getServiceRequest();
      if(Optional.ofNullable(serviceRequest.getParams()).map(p -> p.getJsonObject("query")).map( q -> q.getJsonArray("var")).orElse(new JsonArray()).stream().filter(s -> "refresh:false".equals(s)).count() > 0L) {
        if(patch) {
          super.sqlPOSTHost(o, inheritPrimaryKey).onSuccess(o2 -> {
            promise.complete(o2);
          }).onFailure(ex -> {
            promise.fail(ex);
          });
        } else {
          super.sqlPOSTHost(o, inheritPrimaryKey).onSuccess(o2 -> {
            promise.complete(o2);
          }).onFailure(ex -> {
            promise.fail(ex);
          });
        }
      } else {
        JsonObject hostJson = o.getSiteRequest_().getJsonObject();
        String hostName = hostJson.getString(patch ? "setHostName": "hostName");
        JsonArray subscriptions = hostJson.getJsonArray(patch ? "setEventSubscriptions" : "eventSubscriptions");

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
            if(patch) {
              super.sqlPOSTHost(o, inheritPrimaryKey).onSuccess(o2 -> {
                promise.complete(o2);
              }).onFailure(ex -> {
                promise.fail(ex);
              });
            } else {
              super.sqlPOSTHost(o, inheritPrimaryKey).onSuccess(o2 -> {
                promise.complete(o2);
              }).onFailure(ex -> {
                promise.fail(ex);
              });
            }
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
    return sqlUpsertHost(o, inheritPrimaryKey, false);
  }

  @Override
  public Future<Host> sqlPATCHHost(Host o, Boolean inheritPrimaryKey) {
    return sqlUpsertHost(o, inheritPrimaryKey, true);
  }

  public Future<Void> sqlDeleteHost(Host o) {
    Promise<Void> promise = Promise.promise();
    try {
      String hostName = o.getHostName();

      Integer sensuPort = Integer.parseInt(config.getString(ConfigKeys.SENSU_PORT));
      String sensuHostName = config.getString(ConfigKeys.SENSU_HOST_NAME);
      Boolean sensuSsl = Boolean.parseBoolean(config.getString(ConfigKeys.SENSU_SSL));
      String sensuUri = String.format("/api/core/v2/namespaces/default/entities/%s", hostName);
      String accessToken = config.getString(ConfigKeys.SENSU_TOKEN);

      if(StringUtils.isEmpty(hostName)) {
        RuntimeException ex = new RuntimeException("Missing host name");
        LOG.error(ex.getMessage(), ex);
        promise.fail(ex);
      } else {
        webClient.delete(sensuPort, sensuHostName, sensuUri).ssl(sensuSsl)
            .putHeader("Authorization", String.format("Key %s", accessToken))
            .send()
            .expecting(HttpResponseExpectation.SC_NO_CONTENT.or(HttpResponseExpectation.SC_NOT_FOUND))
            .onSuccess(HostResponse -> {
          super.sqlDELETEHost(o).onSuccess(a -> {
            promise.complete();
          }).onFailure(ex -> {
            LOG.error(String.format("Updating Sensu host failed. "), ex);
            promise.fail(ex);
          });
        }).onFailure(ex -> {
          LOG.error(String.format("Updating Sensu host failed. "), ex);
          promise.fail(ex);
        });
      }
    } catch(Exception ex) {
      LOG.error(String.format("Updating Sensu host failed. "), ex);
      promise.fail(ex);
    }
    return promise.future();
  }

  @Override
  public Future<Void> sqlDELETEFilterHost(Host o) {
    return sqlDeleteHost(o);
  }

  @Override
  public Future<Void> sqlDELETEHost(Host o) {
    return sqlDeleteHost(o);
  }
}
