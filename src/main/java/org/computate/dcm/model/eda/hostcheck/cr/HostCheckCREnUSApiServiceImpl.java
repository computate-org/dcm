package org.computate.dcm.model.eda.hostcheck.cr;

import io.vertx.ext.auth.authorization.AuthorizationProvider;
import io.vertx.ext.auth.oauth2.OAuth2Auth;
import io.vertx.ext.web.client.WebClient;
import io.vertx.core.Future;
import io.vertx.core.Promise;
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

/**
 * Translate: false
 **/
public class HostCheckCREnUSApiServiceImpl extends HostCheckCREnUSGenApiServiceImpl {

  @Override
  public Future<HostCheckCR> sqlPOSTHostCheckCR(HostCheckCR o, Boolean inheritPrimaryKey) {
    Promise<HostCheckCR> promise = Promise.promise();
    upsertHostCheckCR(o, inheritPrimaryKey, false).onSuccess(diagnosticJson -> {
      super.sqlPOSTHostCheckCR(o, inheritPrimaryKey).onSuccess(o2 -> {
        promise.complete(o2);
      }).onFailure(ex -> promise.fail(ex));
    }).onFailure(ex -> promise.fail(ex));
    return promise.future();
  }

  @Override
  public Future<HostCheckCR> sqlPATCHHostCheckCR(HostCheckCR o, Boolean inheritPrimaryKey) {
    Promise<HostCheckCR> promise = Promise.promise();
    upsertHostCheckCR(o, inheritPrimaryKey, true).onSuccess(diagnosticJson -> {
      super.sqlPATCHHostCheckCR(o, inheritPrimaryKey).onSuccess(o2 -> {
        promise.complete(o2);
      }).onFailure(ex -> promise.fail(ex));
    }).onFailure(ex -> promise.fail(ex));
    return promise.future();
  }
}
