package org.computate.dcm.model.k8s;

import io.vertx.ext.auth.authorization.AuthorizationProvider;
import io.vertx.ext.auth.oauth2.OAuth2Auth;
import io.vertx.ext.web.client.WebClient;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.WorkerExecutor;
import io.vertx.core.json.JsonObject;
import io.vertx.sqlclient.Pool;

import org.computate.dcm.request.SiteRequest;
import org.computate.vertx.openapi.ComputateOAuth2AuthHandlerImpl;
import io.vertx.kafka.client.producer.KafkaProducer;
import io.vertx.mqtt.MqttClient;
import io.kubernetes.client.openapi.ApiCallback;
import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.Configuration;
import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.openapi.apis.CoreV1Api.APIcreateNamespaceRequest;
import io.kubernetes.client.openapi.models.V1Namespace;
import io.kubernetes.client.openapi.models.V1ObjectMeta;
import io.kubernetes.client.proto.V1.Namespace;
import io.kubernetes.client.util.ClientBuilder;
import io.kubernetes.client.util.Config;
import io.vertx.amqp.AmqpSender;
import io.vertx.rabbitmq.RabbitMQClient;
import com.hubspot.jinjava.Jinjava;

/**
 * Translate: false
 **/
public class ProjectEnUSApiServiceImpl extends ProjectEnUSGenApiServiceImpl {

  @Override
  public Future<Project> sqlPOSTProject(Project project, Boolean inheritPrimaryKey) {
    Promise<Project> promise = Promise.promise();
    super.sqlPOSTProject(project, inheritPrimaryKey).onSuccess(project2 -> {
      try {
        ApiClient client;
        try {
          client = ClientBuilder.cluster().build();
        } catch(Throwable ex) {
          client = Config.defaultClient();
        }
        Configuration.setDefaultApiClient(client);
        CoreV1Api api = new CoreV1Api();
        V1Namespace namespace = new V1Namespace();
        V1ObjectMeta metadata = new V1ObjectMeta();
        metadata.setName(project2.getProjectName());
        namespace.setMetadata(metadata);
        namespace.setApiVersion("v1");
        namespace.setKind("Namespace");
        APIcreateNamespaceRequest request = api.createNamespace(namespace);
        request.execute();
        promise.complete(project2);
      } catch(ApiException ex) {
        if(ex.getCode() == 409) {
          promise.complete(project2);
        } else {
          LOG.error(String.format("Creating kubernetes namespace %s failed. ", project.getProjectName()), ex);
          promise.fail(ex);
        }
      } catch(Throwable ex) {
        LOG.error(String.format("Creating kubernetes namespace %s failed. ", project.getProjectName()), ex);
        promise.fail(ex);
      }
    }).onFailure(ex -> {
      promise.fail(ex);
    });
    return promise.future();
  }

  // @Override
  // public Future<Project> sqlPATCHProject(Project project, Boolean inheritPrimaryKey) {
  //   Promise<Project> promise = Promise.promise();
  //   super.sqlPATCHProject(project, inheritPrimaryKey).onSuccess(project2 -> {
  //     try {
  //       ApiClient client;
  //       try {
  //         client = ClientBuilder.cluster().build();
  //       } catch(Throwable ex) {
  //         client = Config.defaultClient();
  //       }
  //       Configuration.setDefaultApiClient(client);
  //       CoreV1Api api = new CoreV1Api();
  //       V1Namespace namespace = new V1Namespace();
  //       V1ObjectMeta metadata = new V1ObjectMeta();
  //       metadata.setName(project.getProjectName());
  //       namespace.setMetadata(metadata);
  //       namespace.setApiVersion("v1");
  //       namespace.setKind("Namespace");
  //       APIcreateNamespaceRequest request = api.createNamespace(namespace);
  //       request.execute();
  //       promise.complete(project2);
  //     } catch(ApiException ex) {
  //       if(ex.getCode() == 409) {
  //         promise.complete(project2);
  //       } else {
  //         LOG.error(String.format("Creating kubernetes namespace %s failed. ", project.getProjectName()), ex);
  //         promise.fail(ex);
  //       }
  //     } catch(Throwable ex) {
  //       LOG.error(String.format("Creating kubernetes namespace %s failed. ", project.getProjectName()), ex);
  //       promise.fail(ex);
  //     }
  //   }).onFailure(ex -> {
  //     promise.fail(ex);
  //   });
  //   return promise.future();
  // }
}
