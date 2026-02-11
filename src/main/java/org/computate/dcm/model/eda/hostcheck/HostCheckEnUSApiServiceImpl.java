package org.computate.dcm.model.eda.hostcheck;

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
import org.computate.dcm.model.eda.hostinventory.HostInventory;
import org.computate.dcm.request.SiteRequest;
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
public class HostCheckEnUSApiServiceImpl extends HostCheckEnUSGenApiServiceImpl {

  public Future<Void> sensuUpsertHostCheck(HostCheck o, Boolean inheritPrimaryKey, Boolean patch) {
    Promise<Void> promise = Promise.promise();
    try {
      SiteRequest siteRequest = o.getSiteRequest_();
      ServiceRequest serviceRequest = siteRequest.getServiceRequest();
      if(Optional.ofNullable(serviceRequest.getParams()).map(p -> p.getJsonObject("query")).map( q -> q.getJsonArray("var")).orElse(new JsonArray()).stream().filter(s -> "refresh:false".equals(s)).count() > 0L) {
        promise.complete();
      } else {
        JsonObject hostJson = o.getSiteRequest_().getJsonObject();
        String checkName = Optional.ofNullable(hostJson.getString(patch ? "setCheckName": "checkName")).orElse(o.getCheckName());
        String checkNamespace = Optional.ofNullable(hostJson.getString(patch ? "setHostCheckNamespace": "hostNamespace")).orElse(Optional.ofNullable(o.getCheckNamespace()).orElse("default"));
        String checkCommand = Optional.ofNullable(hostJson.getString(patch ? "setCheckCommand": "checkCommand")).orElse(o.getCheckCommand());
        Integer checkInterval = Optional.ofNullable(hostJson.getString(patch ? "setCheckInterval": "checkInterval")).map(s -> Integer.parseInt(s)).orElse(Optional.ofNullable(o.getCheckInterval()).orElse(30));
        Boolean checkPublished = Optional.ofNullable(hostJson.getString(patch ? "setCheckPublished": "checkPublished")).map(s -> Boolean.parseBoolean(s)).orElse(Optional.ofNullable(o.getCheckPublished()).orElse(true));
        JsonArray eventSubscriptions = Optional.ofNullable(hostJson.getJsonArray(patch ? "setEventSubscriptions": "eventSubscriptions")).orElse(Optional.ofNullable(o.getEventSubscriptions()).map(l -> new JsonArray(l)).orElse(new JsonArray().add(checkName)));
        JsonArray eventHandlers = Optional.ofNullable(hostJson.getJsonArray(patch ? "setEventHandlers": "eventHandlers")).orElse(Optional.ofNullable(o.getEventHandlers()).map(l -> new JsonArray(l)).orElse(new JsonArray().add("sensu-kafka-handler")));

        Integer sensuPort = Integer.parseInt(config.getString(ConfigKeys.SENSU_PORT));
        String sensuHostCheckName = config.getString(ConfigKeys.SENSU_HOST_NAME);
        Boolean sensuSsl = Boolean.parseBoolean(config.getString(ConfigKeys.SENSU_SSL));
        String sensuUri = String.format("/api/core/v2/namespaces/%s/checks/%s", urlEncode(checkNamespace), urlEncode(checkName));
        String accessToken = config.getString(ConfigKeys.SENSU_TOKEN);

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
          webClient.put(sensuPort, sensuHostCheckName, sensuUri).ssl(sensuSsl)
              .putHeader("Authorization", String.format("Key %s", accessToken))
              .sendJsonObject(body)
              .expecting(HttpResponseExpectation.SC_OK.or(HttpResponseExpectation.SC_CREATED))
              .onSuccess(HostCheckResponse -> {
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
  public Future<HostCheck> sqlPOSTHostCheck(HostCheck o, Boolean inheritPrimaryKey) {
    Promise<HostCheck> promise = Promise.promise();
    sensuUpsertHostCheck(o, inheritPrimaryKey, false).onSuccess(b -> {
      super.sqlPOSTHostCheck(o, inheritPrimaryKey).onSuccess(o2 -> {
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
  public Future<HostCheck> sqlPATCHHostCheck(HostCheck o, Boolean inheritPrimaryKey) {
    Promise<HostCheck> promise = Promise.promise();
    sensuUpsertHostCheck(o, inheritPrimaryKey, true).onSuccess(a -> {
      super.sqlPATCHHostCheck(o, inheritPrimaryKey).onSuccess(o2 -> {
        promise.complete(o2);
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
      String sensuHostCheckName = config.getString(ConfigKeys.SENSU_HOST_NAME);
      Boolean sensuSsl = Boolean.parseBoolean(config.getString(ConfigKeys.SENSU_SSL));
      String sensuUri = String.format("/api/core/v2/namespaces/default/checks/%s", checkName);
      String accessToken = config.getString(ConfigKeys.SENSU_TOKEN);

      webClient.delete(sensuPort, sensuHostCheckName, sensuUri).ssl(sensuSsl)
          .putHeader("Authorization", String.format("Key %s", accessToken))
          .send()
          .expecting(HttpResponseExpectation.SC_NO_CONTENT.or(HttpResponseExpectation.SC_NOT_FOUND))
          .onSuccess(HostCheckResponse -> {
        promise.complete();
      }).onFailure(ex -> {
        LOG.error(String.format("Deleting Sensu host failed. "), ex);
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
