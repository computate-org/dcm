package org.computate.dcm.model.eda.hostcheck;

import io.vertx.ext.web.client.WebClient;
import io.vertx.codegen.annotations.ProxyGen;
import io.vertx.serviceproxy.ServiceBinder;
import io.vertx.core.AsyncResult;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.core.json.JsonArray;
import io.vertx.ext.web.api.service.WebApiServiceGen;
import io.vertx.ext.web.api.service.ServiceRequest;
import io.vertx.ext.web.api.service.ServiceResponse;
import com.hubspot.jinjava.Jinjava;
import io.vertx.core.WorkerExecutor;
import io.vertx.sqlclient.Pool;
import org.computate.vertx.openapi.ComputateOAuth2AuthHandlerImpl;
import io.vertx.kafka.client.producer.KafkaProducer;
import io.vertx.mqtt.MqttClient;
import io.vertx.amqp.AmqpSender;
import io.vertx.rabbitmq.RabbitMQClient;
import io.vertx.ext.auth.oauth2.OAuth2Auth;
import io.vertx.ext.auth.authorization.AuthorizationProvider;

/**
 * Translate: false
 * Gen: false
 * Generated: true
 **/
@WebApiServiceGen
@ProxyGen
public interface HostCheckEnUSGenApiService {

  public void searchHostCheck(ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler);
  public void getHostCheck(ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler);
  public void patchHostCheck(JsonObject body, ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler);
  public void patchHostCheckFuture(JsonObject body, ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler);
  public void postHostCheck(JsonObject body, ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler);
  public void postHostCheckFuture(JsonObject body, ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler);
  public void deleteHostCheck(JsonObject body, ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler);
  public void deleteHostCheckFuture(JsonObject body, ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler);
  public void putimportHostCheck(JsonObject body, ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler);
  public void putimportHostCheckFuture(JsonObject body, ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler);
  public void searchpageHostCheck(ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler);
  public void editpageHostCheck(ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler);
  public void userpageHostCheck(ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler);
  public void deletefilterHostCheck(JsonObject body, ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler);
  public void deletefilterHostCheckFuture(JsonObject body, ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler);
}
