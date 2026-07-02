package org.computate.dcm.model.eda.requestapproval;

import org.computate.dcm.request.SiteRequest;
import org.computate.dcm.user.SiteUser;
import org.computate.vertx.api.ApiRequest;
import org.computate.vertx.search.list.SearchResult;
import org.computate.vertx.verticle.EmailVerticle;
import org.computate.dcm.config.ConfigKeys;
import org.computate.vertx.api.BaseApiServiceImpl;
import io.vertx.ext.web.client.WebClient;
import java.util.Objects;
import io.vertx.core.WorkerExecutor;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.EventBus;
import org.computate.vertx.openapi.ComputateOAuth2AuthHandlerImpl;
import io.vertx.kafka.client.producer.KafkaProducer;
import io.vertx.mqtt.MqttClient;
import io.vertx.amqp.AmqpSender;
import io.vertx.rabbitmq.RabbitMQClient;
import io.vertx.core.json.impl.JsonUtil;
import io.vertx.ext.auth.authorization.AuthorizationProvider;
import com.hubspot.jinjava.Jinjava;
import io.vertx.core.eventbus.DeliveryOptions;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.time.Instant;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import org.computate.search.response.solr.SolrResponse.StatsField;
import java.util.stream.Collectors;
import io.vertx.core.json.Json;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Strings;
import java.security.Principal;
import org.apache.commons.lang3.exception.ExceptionUtils;
import java.io.PrintWriter;
import java.util.Collection;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import org.computate.search.serialize.ComputateZonedDateTimeSerializer;
import java.time.format.DateTimeFormatter;
import java.time.ZoneId;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;
import io.vertx.core.Handler;
import io.vertx.ext.web.RoutingContext;
import org.apache.commons.lang3.math.NumberUtils;
import io.vertx.ext.web.Router;
import java.nio.file.Path;
import java.nio.file.Files;
import com.google.common.io.Resources;
import java.nio.charset.StandardCharsets;
import org.computate.vertx.request.ComputateSiteRequest;
import org.computate.vertx.config.ComputateConfigKeys;
import io.vertx.ext.reactivestreams.ReactiveReadStream;
import io.vertx.ext.reactivestreams.ReactiveWriteStream;
import io.vertx.core.MultiMap;
import org.computate.i18n.I18n;
import org.yaml.snakeyaml.Yaml;
import io.vertx.ext.auth.oauth2.OAuth2Auth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.vertx.sqlclient.Transaction;
import io.vertx.sqlclient.SqlConnection;
import io.vertx.sqlclient.Tuple;
import io.vertx.sqlclient.Row;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.sql.Timestamp;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.AsyncResult;
import java.net.URLEncoder;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.CompositeFuture;
import io.vertx.core.http.HttpHeaders;
import io.vertx.core.http.HttpResponseExpectation;
import java.nio.charset.Charset;
import io.vertx.ext.auth.authorization.RoleBasedAuthorization;
import io.vertx.ext.web.api.service.ServiceRequest;
import io.vertx.ext.web.api.service.ServiceResponse;
import io.vertx.ext.web.client.HttpResponse;
import java.util.HashMap;
import io.vertx.ext.auth.User;
import io.vertx.ext.auth.authentication.UsernamePasswordCredentials;
import java.util.Optional;
import java.util.stream.Stream;
import java.net.URLDecoder;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.Map.Entry;
import java.util.Iterator;
import org.computate.search.tool.SearchTool;
import org.computate.search.response.solr.SolrResponse;
import java.util.Base64;
import java.time.ZonedDateTime;
import org.apache.commons.lang3.BooleanUtils;
import org.computate.vertx.search.list.SearchList;
import org.computate.dcm.model.eda.requestapproval.RequestApprovalPage;


/**
 * Translate: false
 * Generated: true
 **/
public class RequestApprovalEnUSGenApiServiceImpl extends BaseApiServiceImpl implements RequestApprovalEnUSGenApiService {

  protected static final Logger LOG = LoggerFactory.getLogger(RequestApprovalEnUSGenApiServiceImpl.class);

  // Search //

  @Override
  public void searchRequestApproval(ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler) {
    Boolean classPublicRead = false;
    user(serviceRequest, SiteRequest.class, SiteUser.class, SiteUser.getClassApiAddress(), "postSiteUserFuture", "patchSiteUserFuture", classPublicRead).onSuccess(siteRequest -> {
      try {
        siteRequest.setLang("enUS");
        String approvalId = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("approvalId");
        String REQUESTAPPROVAL = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("REQUESTAPPROVAL");
        List<String> groups = Optional.ofNullable(siteRequest.getGroups()).orElse(new ArrayList<>());
        MultiMap form = MultiMap.caseInsensitiveMultiMap();
        form.add("grant_type", "urn:ietf:params:oauth:grant-type:uma-ticket");
        form.add("audience", config.getString(ComputateConfigKeys.AUTH_CLIENT));
        form.add("response_mode", "permissions");
        form.add("permission", String.format("%s#%s", RequestApproval.CLASS_AUTH_RESOURCE, "GET"));
        form.add("permission", String.format("%s#%s", RequestApproval.CLASS_AUTH_RESOURCE, "POST"));
        form.add("permission", String.format("%s#%s", RequestApproval.CLASS_AUTH_RESOURCE, "PATCH"));
        form.add("permission", String.format("%s#%s", RequestApproval.CLASS_AUTH_RESOURCE, "DELETE"));
        form.add("permission", String.format("%s#%s", RequestApproval.CLASS_AUTH_RESOURCE, "Admin"));
        form.add("permission", String.format("%s#%s", RequestApproval.CLASS_AUTH_RESOURCE, "SuperAdmin"));
        if(approvalId != null)
          form.add("permission", String.format("%s-%s#%s", RequestApproval.CLASS_AUTH_RESOURCE, approvalId, "GET"));
        webClient.post(
            config.getInteger(ComputateConfigKeys.AUTH_PORT)
            , config.getString(ComputateConfigKeys.AUTH_HOST_NAME)
            , config.getString(ComputateConfigKeys.AUTH_TOKEN_URI)
            )
            .ssl(config.getBoolean(ComputateConfigKeys.AUTH_SSL))
            .putHeader("Authorization", String.format("Bearer %s", Optional.ofNullable(siteRequest.getUser()).map(u -> u.principal().getString("access_token")).orElse("")))
            .sendForm(form)
            .expecting(HttpResponseExpectation.SC_OK)
        .onComplete(authorizationDecisionResponse -> {
          try {
            HttpResponse<Buffer> authorizationDecision = authorizationDecisionResponse.result();
            JsonArray authorizationDecisionBody = authorizationDecisionResponse.failed() ? new JsonArray() : authorizationDecision.bodyAsJsonArray();
            JsonArray scopes = authorizationDecisionBody.stream().map(o -> (JsonObject)o).filter(o -> "REQUESTAPPROVAL".equals(o.getString("rsname"))).findFirst().map(decision -> ((JsonObject)decision).getJsonArray("scopes")).orElse(new JsonArray());
            {
              siteRequest.setScopes(scopes.stream().map(o -> o.toString()).collect(Collectors.toList()));
              List<String> scopes2 = siteRequest.getScopes();
              searchRequestApprovalList(siteRequest, false, true, false, "GET").onSuccess(listRequestApproval -> {
                response200SearchRequestApproval(listRequestApproval).onSuccess(response -> {
                  eventHandler.handle(Future.succeededFuture(response));
                  LOG.debug(String.format("searchRequestApproval succeeded. "));
                }).onFailure(ex -> {
                  LOG.error(String.format("searchRequestApproval failed. "), ex);
                  error(siteRequest, eventHandler, ex);
                });
              }).onFailure(ex -> {
                LOG.error(String.format("searchRequestApproval failed. "), ex);
                error(siteRequest, eventHandler, ex);
              });
            }
          } catch(Exception ex) {
            LOG.error(String.format("searchRequestApproval failed. "), ex);
            error(null, eventHandler, ex);
          }
        });
      } catch(Exception ex) {
        LOG.error(String.format("searchRequestApproval failed. "), ex);
        error(null, eventHandler, ex);
      }
    }).onFailure(ex -> {
      if("Inactive Token".equals(ex.getMessage()) || StringUtils.startsWith(ex.getMessage(), "invalid_grant:")) {
        try {
          eventHandler.handle(Future.succeededFuture(new ServiceResponse(302, "Found", null, MultiMap.caseInsensitiveMultiMap().add(HttpHeaders.LOCATION, "/logout?redirect_uri=" + URLEncoder.encode(serviceRequest.getExtra().getString("uri"), "UTF-8")))));
        } catch(Exception ex2) {
          LOG.error(String.format("searchRequestApproval failed. ", ex2));
          error(null, eventHandler, ex2);
        }
      } else if(StringUtils.startsWith(ex.getMessage(), "401 UNAUTHORIZED ")) {
        eventHandler.handle(Future.succeededFuture(
          new ServiceResponse(401, "UNAUTHORIZED",
            Buffer.buffer().appendString(
              new JsonObject()
                .put("errorCode", "401")
                .put("errorMessage", "SSO Resource Permission check returned DENY")
                .encodePrettily()
              ), MultiMap.caseInsensitiveMultiMap()
              )
          ));
      } else {
        LOG.error(String.format("searchRequestApproval failed. "), ex);
        error(null, eventHandler, ex);
      }
    });
  }

  public Future<ServiceResponse> response200SearchRequestApproval(SearchList<RequestApproval> listRequestApproval) {
    Promise<ServiceResponse> promise = Promise.promise();
    try {
      SiteRequest siteRequest = listRequestApproval.getSiteRequest_(SiteRequest.class);
      List<String> fls = listRequestApproval.getRequest().getFields();
      JsonObject json = new JsonObject();
      JsonArray l = new JsonArray();
      List<String> scopes = siteRequest.getScopes();
      listRequestApproval.getList().stream().forEach(o -> {
        JsonObject json2 = JsonObject.mapFrom(o);
        if(fls.size() > 0) {
          Set<String> fieldNames = new HashSet<String>();
          for(String fieldName : json2.fieldNames()) {
            String v = RequestApproval.varIndexedRequestApproval(fieldName);
            if(v != null)
              fieldNames.add(RequestApproval.varIndexedRequestApproval(fieldName));
          }
          if(fls.size() == 1 && fls.stream().findFirst().orElse(null).equals("saves_docvalues_strings")) {
            fieldNames.removeAll(Optional.ofNullable(json2.getJsonArray("saves_docvalues_strings")).orElse(new JsonArray()).stream().map(s -> s.toString()).collect(Collectors.toList()));
            fieldNames.remove("pk_docvalues_long");
            fieldNames.remove("created_docvalues_date");
          }
          else if(fls.size() >= 1) {
            fieldNames.removeAll(fls);
          }
          for(String fieldName : fieldNames) {
            if(!fls.contains(fieldName))
              json2.remove(fieldName);
          }
        }
        l.add(json2);
      });
      json.put("list", l);
      response200Search(listRequestApproval.getRequest(), listRequestApproval.getResponse(), json);
      promise.complete(ServiceResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily())));
    } catch(Exception ex) {
      LOG.error(String.format("response200SearchRequestApproval failed. "), ex);
      promise.tryFail(ex);
    }
    return promise.future();
  }
  public void responsePivotSearchRequestApproval(List<SolrResponse.Pivot> pivots, JsonArray pivotArray) {
    if(pivots != null) {
      for(SolrResponse.Pivot pivotField : pivots) {
        String entityIndexed = pivotField.getField();
        String entityVar = StringUtils.substringBefore(entityIndexed, "_docvalues_");
        JsonObject pivotJson = new JsonObject();
        pivotArray.add(pivotJson);
        pivotJson.put("field", entityVar);
        pivotJson.put("value", pivotField.getValue());
        pivotJson.put("count", pivotField.getCount());
        Collection<SolrResponse.PivotRange> pivotRanges = pivotField.getRanges().values();
        List<SolrResponse.Pivot> pivotFields2 = pivotField.getPivotList();
        if(pivotRanges != null) {
          JsonObject rangeJson = new JsonObject();
          pivotJson.put("ranges", rangeJson);
          for(SolrResponse.PivotRange rangeFacet : pivotRanges) {
            JsonObject rangeFacetJson = new JsonObject();
            String rangeFacetVar = StringUtils.substringBefore(rangeFacet.getName(), "_docvalues_");
            rangeJson.put(rangeFacetVar, rangeFacetJson);
            JsonObject rangeFacetCountsObject = new JsonObject();
            rangeFacetJson.put("counts", rangeFacetCountsObject);
            rangeFacet.getCounts().forEach((value, count) -> {
              rangeFacetCountsObject.put(value, count);
            });
          }
        }
        if(pivotFields2 != null) {
          JsonArray pivotArray2 = new JsonArray();
          pivotJson.put("pivot", pivotArray2);
          responsePivotSearchRequestApproval(pivotFields2, pivotArray2);
        }
      }
    }
  }

  // GET //

  @Override
  public void getRequestApproval(ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler) {
    Boolean classPublicRead = false;
    user(serviceRequest, SiteRequest.class, SiteUser.class, SiteUser.getClassApiAddress(), "postSiteUserFuture", "patchSiteUserFuture", classPublicRead).onSuccess(siteRequest -> {
      try {
        siteRequest.setLang("enUS");
        String approvalId = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("approvalId");
        String REQUESTAPPROVAL = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("REQUESTAPPROVAL");
        List<String> groups = Optional.ofNullable(siteRequest.getGroups()).orElse(new ArrayList<>());
        MultiMap form = MultiMap.caseInsensitiveMultiMap();
        form.add("grant_type", "urn:ietf:params:oauth:grant-type:uma-ticket");
        form.add("audience", config.getString(ComputateConfigKeys.AUTH_CLIENT));
        form.add("response_mode", "permissions");
        form.add("permission", String.format("%s#%s", RequestApproval.CLASS_AUTH_RESOURCE, "GET"));
        form.add("permission", String.format("%s#%s", RequestApproval.CLASS_AUTH_RESOURCE, "POST"));
        form.add("permission", String.format("%s#%s", RequestApproval.CLASS_AUTH_RESOURCE, "PATCH"));
        form.add("permission", String.format("%s#%s", RequestApproval.CLASS_AUTH_RESOURCE, "DELETE"));
        form.add("permission", String.format("%s#%s", RequestApproval.CLASS_AUTH_RESOURCE, "Admin"));
        form.add("permission", String.format("%s#%s", RequestApproval.CLASS_AUTH_RESOURCE, "SuperAdmin"));
        if(approvalId != null)
          form.add("permission", String.format("%s-%s#%s", RequestApproval.CLASS_AUTH_RESOURCE, approvalId, "GET"));
        webClient.post(
            config.getInteger(ComputateConfigKeys.AUTH_PORT)
            , config.getString(ComputateConfigKeys.AUTH_HOST_NAME)
            , config.getString(ComputateConfigKeys.AUTH_TOKEN_URI)
            )
            .ssl(config.getBoolean(ComputateConfigKeys.AUTH_SSL))
            .putHeader("Authorization", String.format("Bearer %s", Optional.ofNullable(siteRequest.getUser()).map(u -> u.principal().getString("access_token")).orElse("")))
            .sendForm(form)
            .expecting(HttpResponseExpectation.SC_OK)
        .onComplete(authorizationDecisionResponse -> {
          try {
            HttpResponse<Buffer> authorizationDecision = authorizationDecisionResponse.result();
            JsonArray authorizationDecisionBody = authorizationDecisionResponse.failed() ? new JsonArray() : authorizationDecision.bodyAsJsonArray();
            JsonArray scopes = authorizationDecisionBody.stream().map(o -> (JsonObject)o).filter(o -> "REQUESTAPPROVAL".equals(o.getString("rsname"))).findFirst().map(decision -> ((JsonObject)decision).getJsonArray("scopes")).orElse(new JsonArray());
            {
              siteRequest.setScopes(scopes.stream().map(o -> o.toString()).collect(Collectors.toList()));
              List<String> scopes2 = siteRequest.getScopes();
              searchRequestApprovalList(siteRequest, false, true, false, "GET").onSuccess(listRequestApproval -> {
                response200GETRequestApproval(listRequestApproval).onSuccess(response -> {
                  eventHandler.handle(Future.succeededFuture(response));
                  LOG.debug(String.format("getRequestApproval succeeded. "));
                }).onFailure(ex -> {
                  LOG.error(String.format("getRequestApproval failed. "), ex);
                  error(siteRequest, eventHandler, ex);
                });
              }).onFailure(ex -> {
                LOG.error(String.format("getRequestApproval failed. "), ex);
                error(siteRequest, eventHandler, ex);
              });
            }
          } catch(Exception ex) {
            LOG.error(String.format("getRequestApproval failed. "), ex);
            error(null, eventHandler, ex);
          }
        });
      } catch(Exception ex) {
        LOG.error(String.format("getRequestApproval failed. "), ex);
        error(null, eventHandler, ex);
      }
    }).onFailure(ex -> {
      if("Inactive Token".equals(ex.getMessage()) || StringUtils.startsWith(ex.getMessage(), "invalid_grant:")) {
        try {
          eventHandler.handle(Future.succeededFuture(new ServiceResponse(302, "Found", null, MultiMap.caseInsensitiveMultiMap().add(HttpHeaders.LOCATION, "/logout?redirect_uri=" + URLEncoder.encode(serviceRequest.getExtra().getString("uri"), "UTF-8")))));
        } catch(Exception ex2) {
          LOG.error(String.format("getRequestApproval failed. ", ex2));
          error(null, eventHandler, ex2);
        }
      } else if(StringUtils.startsWith(ex.getMessage(), "401 UNAUTHORIZED ")) {
        eventHandler.handle(Future.succeededFuture(
          new ServiceResponse(401, "UNAUTHORIZED",
            Buffer.buffer().appendString(
              new JsonObject()
                .put("errorCode", "401")
                .put("errorMessage", "SSO Resource Permission check returned DENY")
                .encodePrettily()
              ), MultiMap.caseInsensitiveMultiMap()
              )
          ));
      } else {
        LOG.error(String.format("getRequestApproval failed. "), ex);
        error(null, eventHandler, ex);
      }
    });
  }

  public Future<ServiceResponse> response200GETRequestApproval(SearchList<RequestApproval> listRequestApproval) {
    Promise<ServiceResponse> promise = Promise.promise();
    try {
      SiteRequest siteRequest = listRequestApproval.getSiteRequest_(SiteRequest.class);
      JsonObject json = JsonObject.mapFrom(listRequestApproval.getList().stream().findFirst().orElse(null));
      promise.complete(ServiceResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily())));
    } catch(Exception ex) {
      LOG.error(String.format("response200GETRequestApproval failed. "), ex);
      promise.tryFail(ex);
    }
    return promise.future();
  }

  // PATCH //

  @Override
  public void patchRequestApproval(JsonObject body, ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler) {
    LOG.debug(String.format("patchRequestApproval started. "));
    Boolean classPublicRead = false;
    user(serviceRequest, SiteRequest.class, SiteUser.class, SiteUser.getClassApiAddress(), "postSiteUserFuture", "patchSiteUserFuture", classPublicRead).onSuccess(siteRequest -> {
      try {
        siteRequest.setLang("enUS");
        String approvalId = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("approvalId");
        String REQUESTAPPROVAL = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("REQUESTAPPROVAL");
        List<String> groups = Optional.ofNullable(siteRequest.getGroups()).orElse(new ArrayList<>());
        MultiMap form = MultiMap.caseInsensitiveMultiMap();
        form.add("grant_type", "urn:ietf:params:oauth:grant-type:uma-ticket");
        form.add("audience", config.getString(ComputateConfigKeys.AUTH_CLIENT));
        form.add("response_mode", "permissions");
        form.add("permission", String.format("%s#%s", RequestApproval.CLASS_AUTH_RESOURCE, "GET"));
        form.add("permission", String.format("%s#%s", RequestApproval.CLASS_AUTH_RESOURCE, "POST"));
        form.add("permission", String.format("%s#%s", RequestApproval.CLASS_AUTH_RESOURCE, "PATCH"));
        form.add("permission", String.format("%s#%s", RequestApproval.CLASS_AUTH_RESOURCE, "DELETE"));
        form.add("permission", String.format("%s#%s", RequestApproval.CLASS_AUTH_RESOURCE, "Admin"));
        form.add("permission", String.format("%s#%s", RequestApproval.CLASS_AUTH_RESOURCE, "SuperAdmin"));
        if(approvalId != null)
          form.add("permission", String.format("%s-%s#%s", RequestApproval.CLASS_AUTH_RESOURCE, approvalId, "PATCH"));
        webClient.post(
            config.getInteger(ComputateConfigKeys.AUTH_PORT)
            , config.getString(ComputateConfigKeys.AUTH_HOST_NAME)
            , config.getString(ComputateConfigKeys.AUTH_TOKEN_URI)
            )
            .ssl(config.getBoolean(ComputateConfigKeys.AUTH_SSL))
            .putHeader("Authorization", String.format("Bearer %s", Optional.ofNullable(siteRequest.getUser()).map(u -> u.principal().getString("access_token")).orElse("")))
            .sendForm(form)
            .expecting(HttpResponseExpectation.SC_OK)
        .onComplete(authorizationDecisionResponse -> {
          try {
            HttpResponse<Buffer> authorizationDecision = authorizationDecisionResponse.result();
            JsonArray authorizationDecisionBody = authorizationDecisionResponse.failed() ? new JsonArray() : authorizationDecision.bodyAsJsonArray();
            JsonArray scopes = authorizationDecisionBody.stream().map(o -> (JsonObject)o).filter(o -> "REQUESTAPPROVAL".equals(o.getString("rsname"))).findFirst().map(decision -> ((JsonObject)decision).getJsonArray("scopes")).orElse(new JsonArray());
            if(authorizationDecisionResponse.failed() || !scopes.contains("PATCH")) {
              String msg = String.format("403 FORBIDDEN user %s to %s %s", siteRequest.getUser().attributes().getJsonObject("accessToken").getString("preferred_username"), serviceRequest.getExtra().getString("method"), serviceRequest.getExtra().getString("uri"));
              eventHandler.handle(Future.succeededFuture(
                new ServiceResponse(403, "FORBIDDEN",
                  Buffer.buffer().appendString(
                    new JsonObject()
                      .put("errorCode", "403")
                      .put("errorMessage", msg)
                      .encodePrettily()
                    ), MultiMap.caseInsensitiveMultiMap()
                )
              ));
            } else {
              siteRequest.setScopes(scopes.stream().map(o -> o.toString()).collect(Collectors.toList()));
              List<String> scopes2 = siteRequest.getScopes();
              searchRequestApprovalList(siteRequest, false, true, true, "PATCH").onSuccess(listRequestApproval -> {
                try {
                  ApiRequest apiRequest = new ApiRequest();
                  apiRequest.setRows(listRequestApproval.getRequest().getRows());
                  apiRequest.setNumFound(listRequestApproval.getResponse().getResponse().getNumFound());
                  apiRequest.setNumPATCH(0L);
                  apiRequest.initDeepApiRequest(siteRequest);
                  siteRequest.setApiRequest_(apiRequest);
                  if(apiRequest.getNumFound() == 1L)
                    apiRequest.setOriginal(listRequestApproval.first());
                  apiRequest.setId(Optional.ofNullable(listRequestApproval.first()).map(o2 -> o2.getApprovalId().toString()).orElse(null));
                  apiRequest.setSolrId(Optional.ofNullable(listRequestApproval.first()).map(o2 -> o2.getSolrId()).orElse(null));
                  eventBus.publish("websocketRequestApproval", JsonObject.mapFrom(apiRequest).toString());

                  listPATCHRequestApproval(apiRequest, listRequestApproval).onSuccess(e -> {
                    response200PATCHRequestApproval(siteRequest).onSuccess(response -> {
                      LOG.debug(String.format("patchRequestApproval succeeded. "));
                      eventHandler.handle(Future.succeededFuture(response));
                    }).onFailure(ex -> {
                      LOG.error(String.format("patchRequestApproval failed. "), ex);
                      error(siteRequest, eventHandler, ex);
                    });
                  }).onFailure(ex -> {
                    LOG.error(String.format("patchRequestApproval failed. "), ex);
                    error(siteRequest, eventHandler, ex);
                  });
                } catch(Exception ex) {
                  LOG.error(String.format("patchRequestApproval failed. "), ex);
                  error(siteRequest, eventHandler, ex);
                }
              }).onFailure(ex -> {
                LOG.error(String.format("patchRequestApproval failed. "), ex);
                error(siteRequest, eventHandler, ex);
              });
            }
          } catch(Exception ex) {
            LOG.error(String.format("patchRequestApproval failed. "), ex);
            error(null, eventHandler, ex);
          }
        });
      } catch(Exception ex) {
        LOG.error(String.format("patchRequestApproval failed. "), ex);
        error(null, eventHandler, ex);
      }
    }).onFailure(ex -> {
      if("Inactive Token".equals(ex.getMessage()) || StringUtils.startsWith(ex.getMessage(), "invalid_grant:")) {
        try {
          eventHandler.handle(Future.succeededFuture(new ServiceResponse(302, "Found", null, MultiMap.caseInsensitiveMultiMap().add(HttpHeaders.LOCATION, "/logout?redirect_uri=" + URLEncoder.encode(serviceRequest.getExtra().getString("uri"), "UTF-8")))));
        } catch(Exception ex2) {
          LOG.error(String.format("patchRequestApproval failed. ", ex2));
          error(null, eventHandler, ex2);
        }
      } else if(StringUtils.startsWith(ex.getMessage(), "401 UNAUTHORIZED ")) {
        eventHandler.handle(Future.succeededFuture(
          new ServiceResponse(401, "UNAUTHORIZED",
            Buffer.buffer().appendString(
              new JsonObject()
                .put("errorCode", "401")
                .put("errorMessage", "SSO Resource Permission check returned DENY")
                .encodePrettily()
              ), MultiMap.caseInsensitiveMultiMap()
              )
          ));
      } else {
        LOG.error(String.format("patchRequestApproval failed. "), ex);
        error(null, eventHandler, ex);
      }
    });
  }

  public Future<Void> listPATCHRequestApproval(ApiRequest apiRequest, SearchList<RequestApproval> listRequestApproval) {
    Promise<Void> promise = Promise.promise();
    List<Future> futures = new ArrayList<>();
    SiteRequest siteRequest = listRequestApproval.getSiteRequest_(SiteRequest.class);
    listRequestApproval.getList().forEach(o -> {
      SiteRequest siteRequest2 = generateSiteRequest(siteRequest.getUser(), siteRequest.getUserPrincipal(), siteRequest.getServiceRequest(), siteRequest.getJsonObject(), SiteRequest.class);
      siteRequest2.setScopes(siteRequest.getScopes());
      o.setSiteRequest_(siteRequest2);
      siteRequest2.setApiRequest_(siteRequest.getApiRequest_());
      JsonObject jsonObject = JsonObject.mapFrom(o);
      RequestApproval o2 = jsonObject.mapTo(RequestApproval.class);
      o2.setSiteRequest_(siteRequest2);
      futures.add(Future.future(promise1 -> {
        patchRequestApprovalFuture(o2, false).onSuccess(a -> {
          promise1.complete();
        }).onFailure(ex -> {
          LOG.error(String.format("listPATCHRequestApproval failed. "), ex);
          promise1.tryFail(ex);
        });
      }));
    });
    CompositeFuture.all(futures).onSuccess( a -> {
      listRequestApproval.next().onSuccess(next -> {
        if(next) {
          listPATCHRequestApproval(apiRequest, listRequestApproval).onSuccess(b -> {
            promise.complete();
          }).onFailure(ex -> {
            LOG.error(String.format("listPATCHRequestApproval failed. "), ex);
            promise.tryFail(ex);
          });
        } else {
          promise.complete();
        }
      }).onFailure(ex -> {
        LOG.error(String.format("listPATCHRequestApproval failed. "), ex);
        promise.tryFail(ex);
      });
    }).onFailure(ex -> {
      LOG.error(String.format("listPATCHRequestApproval failed. "), ex);
      promise.tryFail(ex);
    });
    return promise.future();
  }

  @Override
  public void patchRequestApprovalFuture(JsonObject body, ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler) {
    Boolean classPublicRead = false;
    user(serviceRequest, SiteRequest.class, SiteUser.class, SiteUser.getClassApiAddress(), "postSiteUserFuture", "patchSiteUserFuture", classPublicRead).onSuccess(siteRequest -> {
      try {
        siteRequest.setLang("enUS");
        siteRequest.setJsonObject(body);
        serviceRequest.getParams().getJsonObject("query").put("rows", 1);
        Optional.ofNullable(serviceRequest.getParams().getJsonArray("scopes")).ifPresent(scopes -> {
          scopes.stream().map(v -> v.toString()).forEach(scope -> {
            siteRequest.addScopes(scope);
          });
        });
        searchRequestApprovalList(siteRequest, false, true, true, "PATCH").onSuccess(listRequestApproval -> {
          try {
            RequestApproval o = listRequestApproval.first();
            ApiRequest apiRequest = new ApiRequest();
            apiRequest.setRows(1L);
            apiRequest.setNumFound(1L);
            apiRequest.setNumPATCH(0L);
            apiRequest.initDeepApiRequest(siteRequest);
            siteRequest.setApiRequest_(apiRequest);
            if(Optional.ofNullable(serviceRequest.getParams()).map(p -> p.getJsonObject("query")).map( q -> q.getJsonArray("var")).orElse(new JsonArray()).stream().filter(s -> "refresh:false".equals(s)).count() > 0L) {
              siteRequest.getRequestVars().put( "refresh", "false" );
            }
            RequestApproval o2;
            if(o != null) {
              if(apiRequest.getNumFound() == 1L)
                apiRequest.setOriginal(o);
              apiRequest.setId(Optional.ofNullable(listRequestApproval.first()).map(o3 -> o3.getApprovalId().toString()).orElse(null));
              apiRequest.setSolrId(Optional.ofNullable(listRequestApproval.first()).map(o3 -> o3.getSolrId()).orElse(null));
              JsonObject jsonObject = JsonObject.mapFrom(o);
              o2 = jsonObject.mapTo(RequestApproval.class);
              o2.setSiteRequest_(siteRequest);
              patchRequestApprovalFuture(o2, false).onSuccess(o3 -> {
                eventHandler.handle(Future.succeededFuture(ServiceResponse.completedWithJson(Buffer.buffer(new JsonObject().encodePrettily()))));
              }).onFailure(ex -> {
                eventHandler.handle(Future.failedFuture(ex));
              });
            } else {
              String m = String.format("%s %s not found", "request approval", null);
              eventHandler.handle(Future.failedFuture(m));
            }
          } catch(Exception ex) {
            LOG.error(String.format("patchRequestApproval failed. "), ex);
            error(siteRequest, eventHandler, ex);
          }
        }).onFailure(ex -> {
          LOG.error(String.format("patchRequestApproval failed. "), ex);
          error(siteRequest, eventHandler, ex);
        });
      } catch(Exception ex) {
        LOG.error(String.format("patchRequestApproval failed. "), ex);
        error(null, eventHandler, ex);
      }
    }).onFailure(ex -> {
      LOG.error(String.format("patchRequestApproval failed. "), ex);
      error(null, eventHandler, ex);
    });
  }

  public Future<RequestApproval> patchRequestApprovalFuture(RequestApproval o, Boolean inheritPrimaryKey) {
    SiteRequest siteRequest = o.getSiteRequest_();
    Promise<RequestApproval> promise = Promise.promise();

    try {
      ApiRequest apiRequest = siteRequest.getApiRequest_();
      Promise<RequestApproval> promise1 = Promise.promise();
      pgPool.withTransaction(sqlConnection -> {
        siteRequest.setSqlConnection(sqlConnection);
        varsRequestApproval(siteRequest).onSuccess(a -> {
          sqlPATCHRequestApproval(o, inheritPrimaryKey).onSuccess(requestApproval -> {
            persistRequestApproval(requestApproval, true).onSuccess(c -> {
              relateRequestApproval(requestApproval).onSuccess(d -> {
                indexRequestApproval(requestApproval).onSuccess(o2 -> {
                  if(apiRequest != null) {
                    apiRequest.setNumPATCH(apiRequest.getNumPATCH() + 1);
                    if(apiRequest.getNumFound() == 1L && Optional.ofNullable(siteRequest.getJsonObject()).map(json -> json.size() > 0).orElse(false)) {
                      o2.apiRequestRequestApproval();
                      if(apiRequest.getVars().size() > 0 && Optional.ofNullable(siteRequest.getRequestVars().get("refresh")).map(refresh -> !refresh.equals("false")).orElse(true))
                        eventBus.publish("websocketRequestApproval", JsonObject.mapFrom(apiRequest).toString());
                    }
                  }
                  promise1.complete(requestApproval);
                }).onFailure(ex -> {
                  promise1.tryFail(ex);
                });
              }).onFailure(ex -> {
                promise1.tryFail(ex);
              });
            }).onFailure(ex -> {
              promise1.tryFail(ex);
            });
          }).onFailure(ex -> {
            promise1.tryFail(ex);
          });
        }).onFailure(ex -> {
          promise1.tryFail(ex);
        });
        return promise1.future();
      }).onSuccess(a -> {
        siteRequest.setSqlConnection(null);
      }).onFailure(ex -> {
        siteRequest.setSqlConnection(null);
        promise.tryFail(ex);
      }).compose(requestApproval -> {
        Promise<RequestApproval> promise2 = Promise.promise();
        refreshRequestApproval(requestApproval).onSuccess(a -> {
          promise2.complete(requestApproval);
        }).onFailure(ex -> {
          promise2.tryFail(ex);
        });
        return promise2.future();
      }).onSuccess(requestApproval -> {
        promise.complete(requestApproval);
      }).onFailure(ex -> {
        promise.tryFail(ex);
      });
    } catch(Exception ex) {
      LOG.error(String.format("patchRequestApprovalFuture failed. "), ex);
      promise.tryFail(ex);
    }
    return promise.future();
  }

  public Future<RequestApproval> sqlPATCHRequestApproval(RequestApproval o, Boolean inheritPrimaryKey) {
    Promise<RequestApproval> promise = Promise.promise();
    try {
      SiteRequest siteRequest = o.getSiteRequest_();
      ApiRequest apiRequest = siteRequest.getApiRequest_();
      List<String> solrIds = Optional.ofNullable(apiRequest).map(r -> r.getSolrIds()).orElse(new ArrayList<>());
      List<String> classes = Optional.ofNullable(apiRequest).map(r -> r.getClasses()).orElse(new ArrayList<>());
      SqlConnection sqlConnection = siteRequest.getSqlConnection();
      Integer num = 1;
      StringBuilder bSql = new StringBuilder("UPDATE RequestApproval SET ");
      List<Object> bParams = new ArrayList<Object>();
      Long pk = o.getPk();
      JsonObject jsonObject = siteRequest.getJsonObject();
      Set<String> methodNames = jsonObject.fieldNames();
      RequestApproval o2 = new RequestApproval();
      o2.setSiteRequest_(siteRequest);
      List<Future> futures1 = new ArrayList<>();
      List<Future> futures2 = new ArrayList<>();

      for(String entityVar : methodNames) {
        switch(entityVar) {
          case "setApprovalName":
              o2.setApprovalName(jsonObject.getString(entityVar));
              if(bParams.size() > 0)
                bSql.append(", ");
              bSql.append(RequestApproval.VAR_approvalName + "=$" + num);
              num++;
              bParams.add(o2.sqlApprovalName());
            break;
          case "setApprovalId":
              o2.setApprovalId(jsonObject.getString(entityVar));
              if(bParams.size() > 0)
                bSql.append(", ");
              bSql.append(RequestApproval.VAR_approvalId + "=$" + num);
              num++;
              bParams.add(o2.sqlApprovalId());
            break;
          case "setCreated":
              o2.setCreated(jsonObject.getString(entityVar));
              if(bParams.size() > 0)
                bSql.append(", ");
              bSql.append(RequestApproval.VAR_created + "=$" + num);
              num++;
              bParams.add(o2.sqlCreated());
            break;
          case "setApprovedByEmail":
              o2.setApprovedByEmail(jsonObject.getString(entityVar));
              if(bParams.size() > 0)
                bSql.append(", ");
              bSql.append(RequestApproval.VAR_approvedByEmail + "=$" + num);
              num++;
              bParams.add(o2.sqlApprovedByEmail());
            break;
          case "setApprovedByUserId":
              o2.setApprovedByUserId(jsonObject.getString(entityVar));
              if(bParams.size() > 0)
                bSql.append(", ");
              bSql.append(RequestApproval.VAR_approvedByUserId + "=$" + num);
              num++;
              bParams.add(o2.sqlApprovedByUserId());
            break;
          case "setArchived":
              o2.setArchived(jsonObject.getString(entityVar));
              if(bParams.size() > 0)
                bSql.append(", ");
              bSql.append(RequestApproval.VAR_archived + "=$" + num);
              num++;
              bParams.add(o2.sqlArchived());
            break;
          case "setApprovedByFullName":
              o2.setApprovedByFullName(jsonObject.getString(entityVar));
              if(bParams.size() > 0)
                bSql.append(", ");
              bSql.append(RequestApproval.VAR_approvedByFullName + "=$" + num);
              num++;
              bParams.add(o2.sqlApprovedByFullName());
            break;
          case "setApprovalNote":
              o2.setApprovalNote(jsonObject.getString(entityVar));
              if(bParams.size() > 0)
                bSql.append(", ");
              bSql.append(RequestApproval.VAR_approvalNote + "=$" + num);
              num++;
              bParams.add(o2.sqlApprovalNote());
            break;
          case "setApproved":
              o2.setApproved(jsonObject.getString(entityVar));
              if(bParams.size() > 0)
                bSql.append(", ");
              bSql.append(RequestApproval.VAR_approved + "=$" + num);
              num++;
              bParams.add(o2.sqlApproved());
            break;
          case "setModelType":
              o2.setModelType(jsonObject.getString(entityVar));
              if(bParams.size() > 0)
                bSql.append(", ");
              bSql.append(RequestApproval.VAR_modelType + "=$" + num);
              num++;
              bParams.add(o2.sqlModelType());
            break;
          case "setSessionId":
              o2.setSessionId(jsonObject.getString(entityVar));
              if(bParams.size() > 0)
                bSql.append(", ");
              bSql.append(RequestApproval.VAR_sessionId + "=$" + num);
              num++;
              bParams.add(o2.sqlSessionId());
            break;
          case "setModelResource":
              o2.setModelResource(jsonObject.getString(entityVar));
              if(bParams.size() > 0)
                bSql.append(", ");
              bSql.append(RequestApproval.VAR_modelResource + "=$" + num);
              num++;
              bParams.add(o2.sqlModelResource());
            break;
          case "setUserKey":
              o2.setUserKey(jsonObject.getString(entityVar));
              if(bParams.size() > 0)
                bSql.append(", ");
              bSql.append(RequestApproval.VAR_userKey + "=$" + num);
              num++;
              bParams.add(o2.sqlUserKey());
            break;
          case "setApprovalTitle":
              o2.setApprovalTitle(jsonObject.getString(entityVar));
              if(bParams.size() > 0)
                bSql.append(", ");
              bSql.append(RequestApproval.VAR_approvalTitle + "=$" + num);
              num++;
              bParams.add(o2.sqlApprovalTitle());
            break;
          case "setObjectTitle":
              o2.setObjectTitle(jsonObject.getString(entityVar));
              if(bParams.size() > 0)
                bSql.append(", ");
              bSql.append(RequestApproval.VAR_objectTitle + "=$" + num);
              num++;
              bParams.add(o2.sqlObjectTitle());
            break;
          case "setDisplayPage":
              o2.setDisplayPage(jsonObject.getString(entityVar));
              if(bParams.size() > 0)
                bSql.append(", ");
              bSql.append(RequestApproval.VAR_displayPage + "=$" + num);
              num++;
              bParams.add(o2.sqlDisplayPage());
            break;
          case "setEditPage":
              o2.setEditPage(jsonObject.getString(entityVar));
              if(bParams.size() > 0)
                bSql.append(", ");
              bSql.append(RequestApproval.VAR_editPage + "=$" + num);
              num++;
              bParams.add(o2.sqlEditPage());
            break;
          case "setUserPage":
              o2.setUserPage(jsonObject.getString(entityVar));
              if(bParams.size() > 0)
                bSql.append(", ");
              bSql.append(RequestApproval.VAR_userPage + "=$" + num);
              num++;
              bParams.add(o2.sqlUserPage());
            break;
          case "setDownload":
              o2.setDownload(jsonObject.getString(entityVar));
              if(bParams.size() > 0)
                bSql.append(", ");
              bSql.append(RequestApproval.VAR_download + "=$" + num);
              num++;
              bParams.add(o2.sqlDownload());
            break;
        }
      }
      bSql.append(" WHERE pk=$" + num);
      if(bParams.size() > 0) {
        bParams.add(pk);
        num++;
        futures2.add(0, Future.future(a -> {
          sqlConnection.preparedQuery(bSql.toString())
              .execute(Tuple.tuple(bParams)
              ).onSuccess(b -> {
            a.handle(Future.succeededFuture());
          }).onFailure(ex -> {
            RuntimeException ex2 = new RuntimeException("value RequestApproval failed", ex);
            LOG.error(String.format("relateRequestApproval failed. "), ex2);
            a.handle(Future.failedFuture(ex2));
          });
        }));
      }
      CompositeFuture.all(futures1).onSuccess(a -> {
        CompositeFuture.all(futures2).onSuccess(b -> {
          RequestApproval o3 = new RequestApproval();
          o3.setSiteRequest_(o.getSiteRequest_());
          o3.setPk(pk);
          promise.complete(o3);
        }).onFailure(ex -> {
          LOG.error(String.format("sqlPATCHRequestApproval failed. "), ex);
          promise.tryFail(ex);
        });
      }).onFailure(ex -> {
        LOG.error(String.format("sqlPATCHRequestApproval failed. "), ex);
        promise.tryFail(ex);
      });
    } catch(Exception ex) {
      LOG.error(String.format("sqlPATCHRequestApproval failed. "), ex);
      promise.tryFail(ex);
    }
    return promise.future();
  }

  public Future<ServiceResponse> response200PATCHRequestApproval(SiteRequest siteRequest) {
    Promise<ServiceResponse> promise = Promise.promise();
    try {
      JsonObject json = new JsonObject();
      promise.complete(ServiceResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily())));
    } catch(Exception ex) {
      LOG.error(String.format("response200PATCHRequestApproval failed. "), ex);
      promise.tryFail(ex);
    }
    return promise.future();
  }

  // POST //

  @Override
  public void postRequestApproval(JsonObject body, ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler) {
    LOG.debug(String.format("postRequestApproval started. "));
    Boolean classPublicRead = false;
    user(serviceRequest, SiteRequest.class, SiteUser.class, SiteUser.getClassApiAddress(), "postSiteUserFuture", "patchSiteUserFuture", classPublicRead).onSuccess(siteRequest -> {
      try {
        siteRequest.setLang("enUS");
        String approvalId = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("approvalId");
        String REQUESTAPPROVAL = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("REQUESTAPPROVAL");
        List<String> groups = Optional.ofNullable(siteRequest.getGroups()).orElse(new ArrayList<>());
        MultiMap form = MultiMap.caseInsensitiveMultiMap();
        form.add("grant_type", "urn:ietf:params:oauth:grant-type:uma-ticket");
        form.add("audience", config.getString(ComputateConfigKeys.AUTH_CLIENT));
        form.add("response_mode", "permissions");
        form.add("permission", String.format("%s#%s", RequestApproval.CLASS_AUTH_RESOURCE, "GET"));
        form.add("permission", String.format("%s#%s", RequestApproval.CLASS_AUTH_RESOURCE, "POST"));
        form.add("permission", String.format("%s#%s", RequestApproval.CLASS_AUTH_RESOURCE, "PATCH"));
        form.add("permission", String.format("%s#%s", RequestApproval.CLASS_AUTH_RESOURCE, "DELETE"));
        form.add("permission", String.format("%s#%s", RequestApproval.CLASS_AUTH_RESOURCE, "Admin"));
        form.add("permission", String.format("%s#%s", RequestApproval.CLASS_AUTH_RESOURCE, "SuperAdmin"));
        if(approvalId != null)
          form.add("permission", String.format("%s-%s#%s", RequestApproval.CLASS_AUTH_RESOURCE, approvalId, "POST"));
        webClient.post(
            config.getInteger(ComputateConfigKeys.AUTH_PORT)
            , config.getString(ComputateConfigKeys.AUTH_HOST_NAME)
            , config.getString(ComputateConfigKeys.AUTH_TOKEN_URI)
            )
            .ssl(config.getBoolean(ComputateConfigKeys.AUTH_SSL))
            .putHeader("Authorization", String.format("Bearer %s", Optional.ofNullable(siteRequest.getUser()).map(u -> u.principal().getString("access_token")).orElse("")))
            .sendForm(form)
            .expecting(HttpResponseExpectation.SC_OK)
        .onComplete(authorizationDecisionResponse -> {
          try {
            HttpResponse<Buffer> authorizationDecision = authorizationDecisionResponse.result();
            JsonArray authorizationDecisionBody = authorizationDecisionResponse.failed() ? new JsonArray() : authorizationDecision.bodyAsJsonArray();
            JsonArray scopes = authorizationDecisionBody.stream().map(o -> (JsonObject)o).filter(o -> "REQUESTAPPROVAL".equals(o.getString("rsname"))).findFirst().map(decision -> ((JsonObject)decision).getJsonArray("scopes")).orElse(new JsonArray());
            if(authorizationDecisionResponse.failed() || !scopes.contains("POST")) {
              String msg = String.format("403 FORBIDDEN user %s to %s %s", siteRequest.getUser().attributes().getJsonObject("accessToken").getString("preferred_username"), serviceRequest.getExtra().getString("method"), serviceRequest.getExtra().getString("uri"));
              eventHandler.handle(Future.succeededFuture(
                new ServiceResponse(403, "FORBIDDEN",
                  Buffer.buffer().appendString(
                    new JsonObject()
                      .put("errorCode", "403")
                      .put("errorMessage", msg)
                      .encodePrettily()
                    ), MultiMap.caseInsensitiveMultiMap()
                )
              ));
            } else {
              siteRequest.setScopes(scopes.stream().map(o -> o.toString()).collect(Collectors.toList()));
              List<String> scopes2 = siteRequest.getScopes();
              ApiRequest apiRequest = new ApiRequest();
              apiRequest.setRows(1L);
              apiRequest.setNumFound(1L);
              apiRequest.setNumPATCH(0L);
              apiRequest.initDeepApiRequest(siteRequest);
              siteRequest.setApiRequest_(apiRequest);
              eventBus.publish("websocketRequestApproval", JsonObject.mapFrom(apiRequest).toString());
              JsonObject params = new JsonObject();
              params.put("body", siteRequest.getJsonObject());
              params.put("path", new JsonObject());
              params.put("scopes", scopes2);
              params.put("cookie", siteRequest.getServiceRequest().getParams().getJsonObject("cookie"));
              params.put("header", siteRequest.getServiceRequest().getParams().getJsonObject("header"));
              params.put("form", new JsonObject());
              JsonObject query = new JsonObject();
              Boolean softCommit = Optional.ofNullable(siteRequest.getServiceRequest().getParams()).map(p -> p.getJsonObject("query")).map( q -> q.getBoolean("softCommit")).orElse(null);
              Integer commitWithin = Optional.ofNullable(siteRequest.getServiceRequest().getParams()).map(p -> p.getJsonObject("query")).map( q -> q.getInteger("commitWithin")).orElse(null);
              if(softCommit == null && commitWithin == null)
                softCommit = true;
              if(softCommit != null)
                query.put("softCommit", softCommit);
              if(commitWithin != null)
                query.put("commitWithin", commitWithin);
              params.put("query", query);
              JsonObject context = new JsonObject().put("params", params).put("user", siteRequest.getUserPrincipal());
              JsonObject json = new JsonObject().put("context", context);
              eventBus.request(RequestApproval.getClassApiAddress(), json, new DeliveryOptions().addHeader("action", "postRequestApprovalFuture")).onSuccess(a -> {
                JsonObject responseMessage = (JsonObject)a.body();
                JsonObject responseBody = new JsonObject(Buffer.buffer(JsonUtil.BASE64_DECODER.decode(responseMessage.getString("payload"))));
                apiRequest.setSolrId(responseBody.getString(RequestApproval.VAR_solrId));
                eventHandler.handle(Future.succeededFuture(ServiceResponse.completedWithJson(Buffer.buffer(responseBody.encodePrettily()))));
                LOG.debug(String.format("postRequestApproval succeeded. "));
              }).onFailure(ex -> {
                LOG.error(String.format("postRequestApproval failed. "), ex);
                error(siteRequest, eventHandler, ex);
              });
            }
          } catch(Exception ex) {
            LOG.error(String.format("postRequestApproval failed. "), ex);
            error(null, eventHandler, ex);
          }
        });
      } catch(Exception ex) {
        LOG.error(String.format("postRequestApproval failed. "), ex);
        error(null, eventHandler, ex);
      }
    }).onFailure(ex -> {
      if("Inactive Token".equals(ex.getMessage()) || StringUtils.startsWith(ex.getMessage(), "invalid_grant:")) {
        try {
          eventHandler.handle(Future.succeededFuture(new ServiceResponse(302, "Found", null, MultiMap.caseInsensitiveMultiMap().add(HttpHeaders.LOCATION, "/logout?redirect_uri=" + URLEncoder.encode(serviceRequest.getExtra().getString("uri"), "UTF-8")))));
        } catch(Exception ex2) {
          LOG.error(String.format("postRequestApproval failed. ", ex2));
          error(null, eventHandler, ex2);
        }
      } else if(StringUtils.startsWith(ex.getMessage(), "401 UNAUTHORIZED ")) {
        eventHandler.handle(Future.succeededFuture(
          new ServiceResponse(401, "UNAUTHORIZED",
            Buffer.buffer().appendString(
              new JsonObject()
                .put("errorCode", "401")
                .put("errorMessage", "SSO Resource Permission check returned DENY")
                .encodePrettily()
              ), MultiMap.caseInsensitiveMultiMap()
              )
          ));
      } else {
        LOG.error(String.format("postRequestApproval failed. "), ex);
        error(null, eventHandler, ex);
      }
    });
  }

  @Override
  public void postRequestApprovalFuture(JsonObject body, ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler) {
    Boolean classPublicRead = false;
    user(serviceRequest, SiteRequest.class, SiteUser.class, SiteUser.getClassApiAddress(), "postSiteUserFuture", "patchSiteUserFuture", classPublicRead).onSuccess(siteRequest -> {
      try {
        siteRequest.setLang("enUS");
        Optional.ofNullable(serviceRequest.getParams().getJsonArray("scopes")).ifPresent(scopes -> {
          scopes.stream().map(v -> v.toString()).forEach(scope -> {
            siteRequest.addScopes(scope);
          });
        });
        ApiRequest apiRequest = new ApiRequest();
        apiRequest.setRows(1L);
        apiRequest.setNumFound(1L);
        apiRequest.setNumPATCH(0L);
        apiRequest.initDeepApiRequest(siteRequest);
        siteRequest.setApiRequest_(apiRequest);
        if(Optional.ofNullable(serviceRequest.getParams()).map(p -> p.getJsonObject("query")).map( q -> q.getJsonArray("var")).orElse(new JsonArray()).stream().filter(s -> "refresh:false".equals(s)).count() > 0L) {
          siteRequest.getRequestVars().put( "refresh", "false" );
        }
        postRequestApprovalFuture(siteRequest, false).onSuccess(o -> {
          eventHandler.handle(Future.succeededFuture(ServiceResponse.completedWithJson(Buffer.buffer(JsonObject.mapFrom(o).encodePrettily()))));
        }).onFailure(ex -> {
          eventHandler.handle(Future.failedFuture(ex));
        });
      } catch(Throwable ex) {
        LOG.error(String.format("postRequestApproval failed. "), ex);
        error(null, eventHandler, ex);
      }
    }).onFailure(ex -> {
      if("Inactive Token".equals(ex.getMessage()) || StringUtils.startsWith(ex.getMessage(), "invalid_grant:")) {
        try {
          eventHandler.handle(Future.succeededFuture(new ServiceResponse(302, "Found", null, MultiMap.caseInsensitiveMultiMap().add(HttpHeaders.LOCATION, "/logout?redirect_uri=" + URLEncoder.encode(serviceRequest.getExtra().getString("uri"), "UTF-8")))));
        } catch(Exception ex2) {
          LOG.error(String.format("postRequestApproval failed. ", ex2));
          error(null, eventHandler, ex2);
        }
      } else if(StringUtils.startsWith(ex.getMessage(), "401 UNAUTHORIZED ")) {
        eventHandler.handle(Future.succeededFuture(
          new ServiceResponse(401, "UNAUTHORIZED",
            Buffer.buffer().appendString(
              new JsonObject()
                .put("errorCode", "401")
                .put("errorMessage", "SSO Resource Permission check returned DENY")
                .encodePrettily()
              ), MultiMap.caseInsensitiveMultiMap()
              )
          ));
      } else {
        LOG.error(String.format("postRequestApproval failed. "), ex);
        error(null, eventHandler, ex);
      }
    });
  }

  public Future<RequestApproval> postRequestApprovalFuture(SiteRequest siteRequest, Boolean approvalId) {
    Promise<RequestApproval> promise = Promise.promise();

    try {
      pgPool.withTransaction(sqlConnection -> {
        Promise<RequestApproval> promise1 = Promise.promise();
        siteRequest.setSqlConnection(sqlConnection);
        varsRequestApproval(siteRequest).onSuccess(a -> {
          createRequestApproval(siteRequest).onSuccess(requestApproval -> {
            sqlPOSTRequestApproval(requestApproval, approvalId).onSuccess(b -> {
              persistRequestApproval(requestApproval, false).onSuccess(c -> {
                relateRequestApproval(requestApproval).onSuccess(d -> {
                  indexRequestApproval(requestApproval).onSuccess(o2 -> {
                    promise1.complete(requestApproval);
                  }).onFailure(ex -> {
                    promise1.tryFail(ex);
                  });
                }).onFailure(ex -> {
                  promise1.tryFail(ex);
                });
              }).onFailure(ex -> {
                promise1.tryFail(ex);
              });
            }).onFailure(ex -> {
              promise1.tryFail(ex);
            });
          }).onFailure(ex -> {
            promise1.tryFail(ex);
          });
        }).onFailure(ex -> {
          promise1.tryFail(ex);
        });
        return promise1.future();
      }).onSuccess(a -> {
        siteRequest.setSqlConnection(null);
      }).onFailure(ex -> {
        siteRequest.setSqlConnection(null);
        promise.tryFail(ex);
      }).compose(requestApproval -> {
        Promise<RequestApproval> promise2 = Promise.promise();
        refreshRequestApproval(requestApproval).onSuccess(a -> {
          try {
            ApiRequest apiRequest = siteRequest.getApiRequest_();
            if(apiRequest != null) {
              apiRequest.setNumPATCH(apiRequest.getNumPATCH() + 1);
              requestApproval.apiRequestRequestApproval();
              eventBus.publish("websocketRequestApproval", JsonObject.mapFrom(apiRequest).toString());
            }
            promise2.complete(requestApproval);
          } catch(Exception ex) {
            LOG.error(String.format("postRequestApprovalFuture failed. "), ex);
            promise2.tryFail(ex);
          }
        }).onFailure(ex -> {
          promise2.tryFail(ex);
        });
        return promise2.future();
      }).onSuccess(requestApproval -> {
        try {
          ApiRequest apiRequest = siteRequest.getApiRequest_();
          if(apiRequest != null) {
            apiRequest.setNumPATCH(apiRequest.getNumPATCH() + 1);
            requestApproval.apiRequestRequestApproval();
            eventBus.publish("websocketRequestApproval", JsonObject.mapFrom(apiRequest).toString());
          }
          promise.complete(requestApproval);
        } catch(Exception ex) {
          LOG.error(String.format("postRequestApprovalFuture failed. "), ex);
          promise.tryFail(ex);
        }
      }).onFailure(ex -> {
        promise.tryFail(ex);
      });
    } catch(Exception ex) {
      LOG.error(String.format("postRequestApprovalFuture failed. "), ex);
      promise.tryFail(ex);
    }
    return promise.future();
  }

  public Future<RequestApproval> sqlPOSTRequestApproval(RequestApproval o, Boolean inheritPrimaryKey) {
    Promise<RequestApproval> promise = Promise.promise();
    try {
      SiteRequest siteRequest = o.getSiteRequest_();
      ApiRequest apiRequest = siteRequest.getApiRequest_();
      List<String> solrIds = Optional.ofNullable(apiRequest).map(r -> r.getSolrIds()).orElse(new ArrayList<>());
      List<String> classes = Optional.ofNullable(apiRequest).map(r -> r.getClasses()).orElse(new ArrayList<>());
      SqlConnection sqlConnection = siteRequest.getSqlConnection();
      Integer num = 1;
      StringBuilder bSql = new StringBuilder("UPDATE RequestApproval SET ");
      List<Object> bParams = new ArrayList<Object>();
      Long pk = o.getPk();
      JsonObject jsonObject = siteRequest.getJsonObject();
      RequestApproval o2 = new RequestApproval();
      o2.setSiteRequest_(siteRequest);
      List<Future> futures1 = new ArrayList<>();
      List<Future> futures2 = new ArrayList<>();

      if(siteRequest.getSessionId() != null) {
        if(bParams.size() > 0) {
          bSql.append(", ");
        }
        bSql.append("sessionId=$" + num);
        num++;
        bParams.add(siteRequest.getSessionId());
      }
      if(siteRequest.getUserKey() != null) {
        if(bParams.size() > 0) {
          bSql.append(", ");
        }
        bSql.append("userKey=$" + num);
        num++;
        bParams.add(siteRequest.getUserKey());
      }

      if(jsonObject != null) {
        Set<String> entityVars = jsonObject.fieldNames();
        for(String entityVar : entityVars) {
          switch(entityVar) {
          case RequestApproval.VAR_approvalName:
            o2.setApprovalName(jsonObject.getString(entityVar));
            if(bParams.size() > 0) {
              bSql.append(", ");
            }
            bSql.append(RequestApproval.VAR_approvalName + "=$" + num);
            num++;
            bParams.add(o2.sqlApprovalName());
            break;
          case RequestApproval.VAR_approvalId:
            o2.setApprovalId(jsonObject.getString(entityVar));
            if(bParams.size() > 0) {
              bSql.append(", ");
            }
            bSql.append(RequestApproval.VAR_approvalId + "=$" + num);
            num++;
            bParams.add(o2.sqlApprovalId());
            break;
          case RequestApproval.VAR_created:
            o2.setCreated(jsonObject.getString(entityVar));
            if(bParams.size() > 0) {
              bSql.append(", ");
            }
            bSql.append(RequestApproval.VAR_created + "=$" + num);
            num++;
            bParams.add(o2.sqlCreated());
            break;
          case RequestApproval.VAR_approvedByEmail:
            o2.setApprovedByEmail(jsonObject.getString(entityVar));
            if(bParams.size() > 0) {
              bSql.append(", ");
            }
            bSql.append(RequestApproval.VAR_approvedByEmail + "=$" + num);
            num++;
            bParams.add(o2.sqlApprovedByEmail());
            break;
          case RequestApproval.VAR_approvedByUserId:
            o2.setApprovedByUserId(jsonObject.getString(entityVar));
            if(bParams.size() > 0) {
              bSql.append(", ");
            }
            bSql.append(RequestApproval.VAR_approvedByUserId + "=$" + num);
            num++;
            bParams.add(o2.sqlApprovedByUserId());
            break;
          case RequestApproval.VAR_archived:
            o2.setArchived(jsonObject.getString(entityVar));
            if(bParams.size() > 0) {
              bSql.append(", ");
            }
            bSql.append(RequestApproval.VAR_archived + "=$" + num);
            num++;
            bParams.add(o2.sqlArchived());
            break;
          case RequestApproval.VAR_approvedByFullName:
            o2.setApprovedByFullName(jsonObject.getString(entityVar));
            if(bParams.size() > 0) {
              bSql.append(", ");
            }
            bSql.append(RequestApproval.VAR_approvedByFullName + "=$" + num);
            num++;
            bParams.add(o2.sqlApprovedByFullName());
            break;
          case RequestApproval.VAR_approvalNote:
            o2.setApprovalNote(jsonObject.getString(entityVar));
            if(bParams.size() > 0) {
              bSql.append(", ");
            }
            bSql.append(RequestApproval.VAR_approvalNote + "=$" + num);
            num++;
            bParams.add(o2.sqlApprovalNote());
            break;
          case RequestApproval.VAR_approved:
            o2.setApproved(jsonObject.getString(entityVar));
            if(bParams.size() > 0) {
              bSql.append(", ");
            }
            bSql.append(RequestApproval.VAR_approved + "=$" + num);
            num++;
            bParams.add(o2.sqlApproved());
            break;
          case RequestApproval.VAR_modelType:
            o2.setModelType(jsonObject.getString(entityVar));
            if(bParams.size() > 0) {
              bSql.append(", ");
            }
            bSql.append(RequestApproval.VAR_modelType + "=$" + num);
            num++;
            bParams.add(o2.sqlModelType());
            break;
          case RequestApproval.VAR_sessionId:
            o2.setSessionId(jsonObject.getString(entityVar));
            if(bParams.size() > 0) {
              bSql.append(", ");
            }
            bSql.append(RequestApproval.VAR_sessionId + "=$" + num);
            num++;
            bParams.add(o2.sqlSessionId());
            break;
          case RequestApproval.VAR_modelResource:
            o2.setModelResource(jsonObject.getString(entityVar));
            if(bParams.size() > 0) {
              bSql.append(", ");
            }
            bSql.append(RequestApproval.VAR_modelResource + "=$" + num);
            num++;
            bParams.add(o2.sqlModelResource());
            break;
          case RequestApproval.VAR_userKey:
            o2.setUserKey(jsonObject.getString(entityVar));
            if(bParams.size() > 0) {
              bSql.append(", ");
            }
            bSql.append(RequestApproval.VAR_userKey + "=$" + num);
            num++;
            bParams.add(o2.sqlUserKey());
            break;
          case RequestApproval.VAR_approvalTitle:
            o2.setApprovalTitle(jsonObject.getString(entityVar));
            if(bParams.size() > 0) {
              bSql.append(", ");
            }
            bSql.append(RequestApproval.VAR_approvalTitle + "=$" + num);
            num++;
            bParams.add(o2.sqlApprovalTitle());
            break;
          case RequestApproval.VAR_objectTitle:
            o2.setObjectTitle(jsonObject.getString(entityVar));
            if(bParams.size() > 0) {
              bSql.append(", ");
            }
            bSql.append(RequestApproval.VAR_objectTitle + "=$" + num);
            num++;
            bParams.add(o2.sqlObjectTitle());
            break;
          case RequestApproval.VAR_displayPage:
            o2.setDisplayPage(jsonObject.getString(entityVar));
            if(bParams.size() > 0) {
              bSql.append(", ");
            }
            bSql.append(RequestApproval.VAR_displayPage + "=$" + num);
            num++;
            bParams.add(o2.sqlDisplayPage());
            break;
          case RequestApproval.VAR_editPage:
            o2.setEditPage(jsonObject.getString(entityVar));
            if(bParams.size() > 0) {
              bSql.append(", ");
            }
            bSql.append(RequestApproval.VAR_editPage + "=$" + num);
            num++;
            bParams.add(o2.sqlEditPage());
            break;
          case RequestApproval.VAR_userPage:
            o2.setUserPage(jsonObject.getString(entityVar));
            if(bParams.size() > 0) {
              bSql.append(", ");
            }
            bSql.append(RequestApproval.VAR_userPage + "=$" + num);
            num++;
            bParams.add(o2.sqlUserPage());
            break;
          case RequestApproval.VAR_download:
            o2.setDownload(jsonObject.getString(entityVar));
            if(bParams.size() > 0) {
              bSql.append(", ");
            }
            bSql.append(RequestApproval.VAR_download + "=$" + num);
            num++;
            bParams.add(o2.sqlDownload());
            break;
          }
        }
      }
      bSql.append(" WHERE pk=$" + num);
      if(bParams.size() > 0) {
      bParams.add(pk);
      num++;
        futures2.add(0, Future.future(a -> {
          sqlConnection.preparedQuery(bSql.toString())
              .execute(Tuple.tuple(bParams)
              ).onSuccess(b -> {
            a.handle(Future.succeededFuture());
          }).onFailure(ex -> {
            RuntimeException ex2 = new RuntimeException("value RequestApproval failed", ex);
            LOG.error(String.format("relateRequestApproval failed. "), ex2);
            a.handle(Future.failedFuture(ex2));
          });
        }));
      }
      CompositeFuture.all(futures1).onSuccess(a -> {
        CompositeFuture.all(futures2).onSuccess(b -> {
          promise.complete(o2);
        }).onFailure(ex -> {
          LOG.error(String.format("sqlPOSTRequestApproval failed. "), ex);
          promise.tryFail(ex);
        });
      }).onFailure(ex -> {
        LOG.error(String.format("sqlPOSTRequestApproval failed. "), ex);
        promise.tryFail(ex);
      });
    } catch(Exception ex) {
      LOG.error(String.format("sqlPOSTRequestApproval failed. "), ex);
      promise.tryFail(ex);
    }
    return promise.future();
  }

  public Future<ServiceResponse> response200POSTRequestApproval(RequestApproval o) {
    Promise<ServiceResponse> promise = Promise.promise();
    try {
      SiteRequest siteRequest = o.getSiteRequest_();
      JsonObject json = JsonObject.mapFrom(o);
      promise.complete(ServiceResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily())));
    } catch(Exception ex) {
      LOG.error(String.format("response200POSTRequestApproval failed. "), ex);
      promise.tryFail(ex);
    }
    return promise.future();
  }

  // DELETE //

  @Override
  public void deleteRequestApproval(JsonObject body, ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler) {
    LOG.debug(String.format("deleteRequestApproval started. "));
    Boolean classPublicRead = false;
    user(serviceRequest, SiteRequest.class, SiteUser.class, SiteUser.getClassApiAddress(), "postSiteUserFuture", "patchSiteUserFuture", classPublicRead).onSuccess(siteRequest -> {
      try {
        siteRequest.setLang("enUS");
        String approvalId = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("approvalId");
        String REQUESTAPPROVAL = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("REQUESTAPPROVAL");
        List<String> groups = Optional.ofNullable(siteRequest.getGroups()).orElse(new ArrayList<>());
        MultiMap form = MultiMap.caseInsensitiveMultiMap();
        form.add("grant_type", "urn:ietf:params:oauth:grant-type:uma-ticket");
        form.add("audience", config.getString(ComputateConfigKeys.AUTH_CLIENT));
        form.add("response_mode", "permissions");
        form.add("permission", String.format("%s#%s", RequestApproval.CLASS_AUTH_RESOURCE, "GET"));
        form.add("permission", String.format("%s#%s", RequestApproval.CLASS_AUTH_RESOURCE, "POST"));
        form.add("permission", String.format("%s#%s", RequestApproval.CLASS_AUTH_RESOURCE, "PATCH"));
        form.add("permission", String.format("%s#%s", RequestApproval.CLASS_AUTH_RESOURCE, "DELETE"));
        form.add("permission", String.format("%s#%s", RequestApproval.CLASS_AUTH_RESOURCE, "Admin"));
        form.add("permission", String.format("%s#%s", RequestApproval.CLASS_AUTH_RESOURCE, "SuperAdmin"));
        if(approvalId != null)
          form.add("permission", String.format("%s-%s#%s", RequestApproval.CLASS_AUTH_RESOURCE, approvalId, "DELETE"));
        webClient.post(
            config.getInteger(ComputateConfigKeys.AUTH_PORT)
            , config.getString(ComputateConfigKeys.AUTH_HOST_NAME)
            , config.getString(ComputateConfigKeys.AUTH_TOKEN_URI)
            )
            .ssl(config.getBoolean(ComputateConfigKeys.AUTH_SSL))
            .putHeader("Authorization", String.format("Bearer %s", Optional.ofNullable(siteRequest.getUser()).map(u -> u.principal().getString("access_token")).orElse("")))
            .sendForm(form)
            .expecting(HttpResponseExpectation.SC_OK)
        .onComplete(authorizationDecisionResponse -> {
          try {
            HttpResponse<Buffer> authorizationDecision = authorizationDecisionResponse.result();
            JsonArray authorizationDecisionBody = authorizationDecisionResponse.failed() ? new JsonArray() : authorizationDecision.bodyAsJsonArray();
            JsonArray scopes = authorizationDecisionBody.stream().map(o -> (JsonObject)o).filter(o -> "REQUESTAPPROVAL".equals(o.getString("rsname"))).findFirst().map(decision -> ((JsonObject)decision).getJsonArray("scopes")).orElse(new JsonArray());
            if(authorizationDecisionResponse.failed() || !scopes.contains("DELETE")) {
              String msg = String.format("403 FORBIDDEN user %s to %s %s", siteRequest.getUser().attributes().getJsonObject("accessToken").getString("preferred_username"), serviceRequest.getExtra().getString("method"), serviceRequest.getExtra().getString("uri"));
              eventHandler.handle(Future.succeededFuture(
                new ServiceResponse(403, "FORBIDDEN",
                  Buffer.buffer().appendString(
                    new JsonObject()
                      .put("errorCode", "403")
                      .put("errorMessage", msg)
                      .encodePrettily()
                    ), MultiMap.caseInsensitiveMultiMap()
                )
              ));
            } else {
              siteRequest.setScopes(scopes.stream().map(o -> o.toString()).collect(Collectors.toList()));
              List<String> scopes2 = siteRequest.getScopes();
              searchRequestApprovalList(siteRequest, false, true, true, "DELETE").onSuccess(listRequestApproval -> {
                try {
                  ApiRequest apiRequest = new ApiRequest();
                  apiRequest.setRows(listRequestApproval.getRequest().getRows());
                  apiRequest.setNumFound(listRequestApproval.getResponse().getResponse().getNumFound());
                  apiRequest.setNumPATCH(0L);
                  apiRequest.initDeepApiRequest(siteRequest);
                  siteRequest.setApiRequest_(apiRequest);
                  if(apiRequest.getNumFound() == 1L)
                    apiRequest.setOriginal(listRequestApproval.first());
                  apiRequest.setSolrId(Optional.ofNullable(listRequestApproval.first()).map(o2 -> o2.getSolrId()).orElse(null));
                  eventBus.publish("websocketRequestApproval", JsonObject.mapFrom(apiRequest).toString());

                  listDELETERequestApproval(apiRequest, listRequestApproval).onSuccess(e -> {
                    response200DELETERequestApproval(siteRequest).onSuccess(response -> {
                      LOG.debug(String.format("deleteRequestApproval succeeded. "));
                      eventHandler.handle(Future.succeededFuture(response));
                    }).onFailure(ex -> {
                      LOG.error(String.format("deleteRequestApproval failed. "), ex);
                      error(siteRequest, eventHandler, ex);
                    });
                  }).onFailure(ex -> {
                    LOG.error(String.format("deleteRequestApproval failed. "), ex);
                    error(siteRequest, eventHandler, ex);
                  });
                } catch(Exception ex) {
                  LOG.error(String.format("deleteRequestApproval failed. "), ex);
                  error(siteRequest, eventHandler, ex);
                }
              }).onFailure(ex -> {
                LOG.error(String.format("deleteRequestApproval failed. "), ex);
                error(siteRequest, eventHandler, ex);
              });
            }
          } catch(Exception ex) {
            LOG.error(String.format("deleteRequestApproval failed. "), ex);
            error(null, eventHandler, ex);
          }
        });
      } catch(Exception ex) {
        LOG.error(String.format("deleteRequestApproval failed. "), ex);
        error(null, eventHandler, ex);
      }
    }).onFailure(ex -> {
      if("Inactive Token".equals(ex.getMessage()) || StringUtils.startsWith(ex.getMessage(), "invalid_grant:")) {
        try {
          eventHandler.handle(Future.succeededFuture(new ServiceResponse(302, "Found", null, MultiMap.caseInsensitiveMultiMap().add(HttpHeaders.LOCATION, "/logout?redirect_uri=" + URLEncoder.encode(serviceRequest.getExtra().getString("uri"), "UTF-8")))));
        } catch(Exception ex2) {
          LOG.error(String.format("deleteRequestApproval failed. ", ex2));
          error(null, eventHandler, ex2);
        }
      } else if(StringUtils.startsWith(ex.getMessage(), "401 UNAUTHORIZED ")) {
        eventHandler.handle(Future.succeededFuture(
          new ServiceResponse(401, "UNAUTHORIZED",
            Buffer.buffer().appendString(
              new JsonObject()
                .put("errorCode", "401")
                .put("errorMessage", "SSO Resource Permission check returned DENY")
                .encodePrettily()
              ), MultiMap.caseInsensitiveMultiMap()
              )
          ));
      } else {
        LOG.error(String.format("deleteRequestApproval failed. "), ex);
        error(null, eventHandler, ex);
      }
    });
  }

  public Future<Void> listDELETERequestApproval(ApiRequest apiRequest, SearchList<RequestApproval> listRequestApproval) {
    Promise<Void> promise = Promise.promise();
    List<Future> futures = new ArrayList<>();
    SiteRequest siteRequest = listRequestApproval.getSiteRequest_(SiteRequest.class);
    listRequestApproval.getList().forEach(o -> {
      SiteRequest siteRequest2 = generateSiteRequest(siteRequest.getUser(), siteRequest.getUserPrincipal(), siteRequest.getServiceRequest(), siteRequest.getJsonObject(), SiteRequest.class);
      siteRequest2.setScopes(siteRequest.getScopes());
      o.setSiteRequest_(siteRequest2);
      siteRequest2.setApiRequest_(siteRequest.getApiRequest_());
      JsonObject jsonObject = JsonObject.mapFrom(o);
      RequestApproval o2 = jsonObject.mapTo(RequestApproval.class);
      o2.setSiteRequest_(siteRequest2);
      futures.add(Future.future(promise1 -> {
        deleteRequestApprovalFuture(o).onSuccess(a -> {
          promise1.complete();
        }).onFailure(ex -> {
          LOG.error(String.format("listDELETERequestApproval failed. "), ex);
          promise1.tryFail(ex);
        });
      }));
    });
    CompositeFuture.all(futures).onSuccess( a -> {
      listRequestApproval.next().onSuccess(next -> {
        if(next) {
          listDELETERequestApproval(apiRequest, listRequestApproval).onSuccess(b -> {
            promise.complete();
          }).onFailure(ex -> {
            LOG.error(String.format("listDELETERequestApproval failed. "), ex);
            promise.tryFail(ex);
          });
        } else {
          promise.complete();
        }
      }).onFailure(ex -> {
        LOG.error(String.format("listDELETERequestApproval failed. "), ex);
        promise.tryFail(ex);
      });
    }).onFailure(ex -> {
      LOG.error(String.format("listDELETERequestApproval failed. "), ex);
      promise.tryFail(ex);
    });
    return promise.future();
  }

  @Override
  public void deleteRequestApprovalFuture(JsonObject body, ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler) {
    Boolean classPublicRead = false;
    user(serviceRequest, SiteRequest.class, SiteUser.class, SiteUser.getClassApiAddress(), "postSiteUserFuture", "patchSiteUserFuture", classPublicRead).onSuccess(siteRequest -> {
      try {
        siteRequest.setLang("enUS");
        siteRequest.setJsonObject(body);
        serviceRequest.getParams().getJsonObject("query").put("rows", 1);
        Optional.ofNullable(serviceRequest.getParams().getJsonArray("scopes")).ifPresent(scopes -> {
          scopes.stream().map(v -> v.toString()).forEach(scope -> {
            siteRequest.addScopes(scope);
          });
        });
        searchRequestApprovalList(siteRequest, false, true, true, "DELETE").onSuccess(listRequestApproval -> {
          try {
            RequestApproval o = listRequestApproval.first();
            if(o != null && listRequestApproval.getResponse().getResponse().getNumFound() == 1) {
              ApiRequest apiRequest = new ApiRequest();
              apiRequest.setRows(1L);
              apiRequest.setNumFound(1L);
              apiRequest.setNumPATCH(0L);
              apiRequest.initDeepApiRequest(siteRequest);
              siteRequest.setApiRequest_(apiRequest);
              if(Optional.ofNullable(serviceRequest.getParams()).map(p -> p.getJsonObject("query")).map( q -> q.getJsonArray("var")).orElse(new JsonArray()).stream().filter(s -> "refresh:false".equals(s)).count() > 0L) {
                siteRequest.getRequestVars().put( "refresh", "false" );
              }
              if(apiRequest.getNumFound() == 1L)
                apiRequest.setOriginal(o);
              apiRequest.setId(Optional.ofNullable(listRequestApproval.first()).map(o2 -> o2.getApprovalId().toString()).orElse(null));
              apiRequest.setSolrId(Optional.ofNullable(listRequestApproval.first()).map(o2 -> o2.getSolrId()).orElse(null));
              deleteRequestApprovalFuture(o).onSuccess(o2 -> {
                eventHandler.handle(Future.succeededFuture(ServiceResponse.completedWithJson(Buffer.buffer(new JsonObject().encodePrettily()))));
              }).onFailure(ex -> {
                eventHandler.handle(Future.failedFuture(ex));
              });
            } else {
              eventHandler.handle(Future.succeededFuture(ServiceResponse.completedWithJson(Buffer.buffer(new JsonObject().encodePrettily()))));
            }
          } catch(Exception ex) {
            LOG.error(String.format("deleteRequestApproval failed. "), ex);
            error(siteRequest, eventHandler, ex);
          }
        }).onFailure(ex -> {
          LOG.error(String.format("deleteRequestApproval failed. "), ex);
          error(siteRequest, eventHandler, ex);
        });
      } catch(Exception ex) {
        LOG.error(String.format("deleteRequestApproval failed. "), ex);
        error(null, eventHandler, ex);
      }
    }).onFailure(ex -> {
      LOG.error(String.format("deleteRequestApproval failed. "), ex);
      error(null, eventHandler, ex);
    });
  }

  public Future<RequestApproval> deleteRequestApprovalFuture(RequestApproval o) {
    SiteRequest siteRequest = o.getSiteRequest_();
    Promise<RequestApproval> promise = Promise.promise();

    try {
      ApiRequest apiRequest = siteRequest.getApiRequest_();
      Promise<RequestApproval> promise1 = Promise.promise();
      pgPool.withTransaction(sqlConnection -> {
        siteRequest.setSqlConnection(sqlConnection);
        varsRequestApproval(siteRequest).onSuccess(a -> {
          sqlDELETERequestApproval(o).onSuccess(requestApproval -> {
            relateRequestApproval(o).onSuccess(d -> {
              unindexRequestApproval(o).onSuccess(o2 -> {
                if(apiRequest != null) {
                  apiRequest.setNumPATCH(apiRequest.getNumPATCH() + 1);
                  if(apiRequest.getNumFound() == 1L && Optional.ofNullable(siteRequest.getJsonObject()).map(json -> json.size() > 0).orElse(false)) {
                    o2.apiRequestRequestApproval();
                    if(apiRequest.getVars().size() > 0 && Optional.ofNullable(siteRequest.getRequestVars().get("refresh")).map(refresh -> !refresh.equals("false")).orElse(true))
                      eventBus.publish("websocketRequestApproval", JsonObject.mapFrom(apiRequest).toString());
                  }
                }
                promise1.complete();
              }).onFailure(ex -> {
                promise1.tryFail(ex);
              });
            }).onFailure(ex -> {
              promise1.tryFail(ex);
            });
          }).onFailure(ex -> {
            promise1.tryFail(ex);
          });
        }).onFailure(ex -> {
          promise1.tryFail(ex);
        });
        return promise1.future();
      }).onSuccess(a -> {
        siteRequest.setSqlConnection(null);
      }).onFailure(ex -> {
        siteRequest.setSqlConnection(null);
        promise.tryFail(ex);
      }).compose(requestApproval -> {
        Promise<RequestApproval> promise2 = Promise.promise();
        refreshRequestApproval(o).onSuccess(a -> {
          promise2.complete(o);
        }).onFailure(ex -> {
          promise2.tryFail(ex);
        });
        return promise2.future();
      }).onSuccess(requestApproval -> {
        promise.complete(requestApproval);
      }).onFailure(ex -> {
        promise.tryFail(ex);
      });
    } catch(Exception ex) {
      LOG.error(String.format("deleteRequestApprovalFuture failed. "), ex);
      promise.tryFail(ex);
    }
    return promise.future();
  }

  public Future<Void> sqlDELETERequestApproval(RequestApproval o) {
    Promise<Void> promise = Promise.promise();
    try {
      SiteRequest siteRequest = o.getSiteRequest_();
      ApiRequest apiRequest = siteRequest.getApiRequest_();
      List<String> solrIds = Optional.ofNullable(apiRequest).map(r -> r.getSolrIds()).orElse(new ArrayList<>());
      List<String> classes = Optional.ofNullable(apiRequest).map(r -> r.getClasses()).orElse(new ArrayList<>());
      SqlConnection sqlConnection = siteRequest.getSqlConnection();
      Integer num = 1;
      StringBuilder bSql = new StringBuilder("DELETE FROM RequestApproval ");
      List<Object> bParams = new ArrayList<Object>();
      Long pk = o.getPk();
      JsonObject jsonObject = siteRequest.getJsonObject();
      RequestApproval o2 = new RequestApproval();
      o2.setSiteRequest_(siteRequest);
      List<Future> futures1 = new ArrayList<>();
      List<Future> futures2 = new ArrayList<>();

      if(jsonObject != null) {
        Set<String> entityVars = jsonObject.fieldNames();
        for(String entityVar : entityVars) {
          switch(entityVar) {
          }
        }
      }
      bSql.append(" WHERE pk=$" + num);
      bParams.add(pk);
      num++;
      futures2.add(0, Future.future(a -> {
        sqlConnection.preparedQuery(bSql.toString())
            .execute(Tuple.tuple(bParams)
            ).onSuccess(b -> {
          a.handle(Future.succeededFuture());
        }).onFailure(ex -> {
          RuntimeException ex2 = new RuntimeException("value RequestApproval failed", ex);
          LOG.error(String.format("unrelateRequestApproval failed. "), ex2);
          a.handle(Future.failedFuture(ex2));
        });
      }));
      CompositeFuture.all(futures1).onSuccess(a -> {
        CompositeFuture.all(futures2).onSuccess(b -> {
          promise.complete();
        }).onFailure(ex -> {
          LOG.error(String.format("sqlDELETERequestApproval failed. "), ex);
          promise.tryFail(ex);
        });
      }).onFailure(ex -> {
        LOG.error(String.format("sqlDELETERequestApproval failed. "), ex);
        promise.tryFail(ex);
      });
    } catch(Exception ex) {
      LOG.error(String.format("sqlDELETERequestApproval failed. "), ex);
      promise.tryFail(ex);
    }
    return promise.future();
  }

  public Future<ServiceResponse> response200DELETERequestApproval(SiteRequest siteRequest) {
    Promise<ServiceResponse> promise = Promise.promise();
    try {
      JsonObject json = new JsonObject();
      promise.complete(ServiceResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily())));
    } catch(Exception ex) {
      LOG.error(String.format("response200DELETERequestApproval failed. "), ex);
      promise.tryFail(ex);
    }
    return promise.future();
  }

  // PUTImport //

  @Override
  public void putimportRequestApproval(JsonObject body, ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler) {
    LOG.debug(String.format("putimportRequestApproval started. "));
    Boolean classPublicRead = false;
    user(serviceRequest, SiteRequest.class, SiteUser.class, SiteUser.getClassApiAddress(), "postSiteUserFuture", "patchSiteUserFuture", classPublicRead).onSuccess(siteRequest -> {
      try {
        siteRequest.setLang("enUS");
        String approvalId = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("approvalId");
        String REQUESTAPPROVAL = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("REQUESTAPPROVAL");
        List<String> groups = Optional.ofNullable(siteRequest.getGroups()).orElse(new ArrayList<>());
        MultiMap form = MultiMap.caseInsensitiveMultiMap();
        form.add("grant_type", "urn:ietf:params:oauth:grant-type:uma-ticket");
        form.add("audience", config.getString(ComputateConfigKeys.AUTH_CLIENT));
        form.add("response_mode", "permissions");
        form.add("permission", String.format("%s#%s", RequestApproval.CLASS_AUTH_RESOURCE, "GET"));
        form.add("permission", String.format("%s#%s", RequestApproval.CLASS_AUTH_RESOURCE, "POST"));
        form.add("permission", String.format("%s#%s", RequestApproval.CLASS_AUTH_RESOURCE, "PATCH"));
        form.add("permission", String.format("%s#%s", RequestApproval.CLASS_AUTH_RESOURCE, "DELETE"));
        form.add("permission", String.format("%s#%s", RequestApproval.CLASS_AUTH_RESOURCE, "Admin"));
        form.add("permission", String.format("%s#%s", RequestApproval.CLASS_AUTH_RESOURCE, "SuperAdmin"));
        if(approvalId != null)
          form.add("permission", String.format("%s-%s#%s", RequestApproval.CLASS_AUTH_RESOURCE, approvalId, "PUT"));
        webClient.post(
            config.getInteger(ComputateConfigKeys.AUTH_PORT)
            , config.getString(ComputateConfigKeys.AUTH_HOST_NAME)
            , config.getString(ComputateConfigKeys.AUTH_TOKEN_URI)
            )
            .ssl(config.getBoolean(ComputateConfigKeys.AUTH_SSL))
            .putHeader("Authorization", String.format("Bearer %s", Optional.ofNullable(siteRequest.getUser()).map(u -> u.principal().getString("access_token")).orElse("")))
            .sendForm(form)
            .expecting(HttpResponseExpectation.SC_OK)
        .onComplete(authorizationDecisionResponse -> {
          try {
            HttpResponse<Buffer> authorizationDecision = authorizationDecisionResponse.result();
            JsonArray authorizationDecisionBody = authorizationDecisionResponse.failed() ? new JsonArray() : authorizationDecision.bodyAsJsonArray();
            JsonArray scopes = authorizationDecisionBody.stream().map(o -> (JsonObject)o).filter(o -> "REQUESTAPPROVAL".equals(o.getString("rsname"))).findFirst().map(decision -> ((JsonObject)decision).getJsonArray("scopes")).orElse(new JsonArray());
            if(authorizationDecisionResponse.failed() || !scopes.contains("PUT")) {
              String msg = String.format("403 FORBIDDEN user %s to %s %s", siteRequest.getUser().attributes().getJsonObject("accessToken").getString("preferred_username"), serviceRequest.getExtra().getString("method"), serviceRequest.getExtra().getString("uri"));
              eventHandler.handle(Future.succeededFuture(
                new ServiceResponse(403, "FORBIDDEN",
                  Buffer.buffer().appendString(
                    new JsonObject()
                      .put("errorCode", "403")
                      .put("errorMessage", msg)
                      .encodePrettily()
                    ), MultiMap.caseInsensitiveMultiMap()
                )
              ));
            } else {
              siteRequest.setScopes(scopes.stream().map(o -> o.toString()).collect(Collectors.toList()));
              List<String> scopes2 = siteRequest.getScopes();
              ApiRequest apiRequest = new ApiRequest();
              JsonArray jsonArray = Optional.ofNullable(siteRequest.getJsonObject()).map(o -> o.getJsonArray("list")).orElse(new JsonArray());
              apiRequest.setRows(Long.valueOf(jsonArray.size()));
              apiRequest.setNumFound(Long.valueOf(jsonArray.size()));
              apiRequest.setNumPATCH(0L);
              apiRequest.initDeepApiRequest(siteRequest);
              siteRequest.setApiRequest_(apiRequest);
              eventBus.publish("websocketRequestApproval", JsonObject.mapFrom(apiRequest).toString());
              varsRequestApproval(siteRequest).onSuccess(d -> {
                listPUTImportRequestApproval(apiRequest, siteRequest).onSuccess(e -> {
                  response200PUTImportRequestApproval(siteRequest).onSuccess(response -> {
                    LOG.debug(String.format("putimportRequestApproval succeeded. "));
                    eventHandler.handle(Future.succeededFuture(response));
                  }).onFailure(ex -> {
                    LOG.error(String.format("putimportRequestApproval failed. "), ex);
                    error(siteRequest, eventHandler, ex);
                  });
                }).onFailure(ex -> {
                  LOG.error(String.format("putimportRequestApproval failed. "), ex);
                  error(siteRequest, eventHandler, ex);
                });
              }).onFailure(ex -> {
                LOG.error(String.format("putimportRequestApproval failed. "), ex);
                error(siteRequest, eventHandler, ex);
              });
            }
          } catch(Exception ex) {
            LOG.error(String.format("putimportRequestApproval failed. "), ex);
            error(null, eventHandler, ex);
          }
        });
      } catch(Exception ex) {
        LOG.error(String.format("putimportRequestApproval failed. "), ex);
        error(null, eventHandler, ex);
      }
    }).onFailure(ex -> {
      if("Inactive Token".equals(ex.getMessage()) || StringUtils.startsWith(ex.getMessage(), "invalid_grant:")) {
        try {
          eventHandler.handle(Future.succeededFuture(new ServiceResponse(302, "Found", null, MultiMap.caseInsensitiveMultiMap().add(HttpHeaders.LOCATION, "/logout?redirect_uri=" + URLEncoder.encode(serviceRequest.getExtra().getString("uri"), "UTF-8")))));
        } catch(Exception ex2) {
          LOG.error(String.format("putimportRequestApproval failed. ", ex2));
          error(null, eventHandler, ex2);
        }
      } else if(StringUtils.startsWith(ex.getMessage(), "401 UNAUTHORIZED ")) {
        eventHandler.handle(Future.succeededFuture(
          new ServiceResponse(401, "UNAUTHORIZED",
            Buffer.buffer().appendString(
              new JsonObject()
                .put("errorCode", "401")
                .put("errorMessage", "SSO Resource Permission check returned DENY")
                .encodePrettily()
              ), MultiMap.caseInsensitiveMultiMap()
              )
          ));
      } else {
        LOG.error(String.format("putimportRequestApproval failed. "), ex);
        error(null, eventHandler, ex);
      }
    });
  }

  public Future<Void> listPUTImportRequestApproval(ApiRequest apiRequest, SiteRequest siteRequest) {
    Promise<Void> promise = Promise.promise();
    List<Future> futures = new ArrayList<>();
    JsonArray jsonArray = Optional.ofNullable(siteRequest.getJsonObject()).map(o -> o.getJsonArray("list")).orElse(new JsonArray());
    try {
      jsonArray.forEach(obj -> {
        futures.add(Future.future(promise1 -> {
          JsonObject params = new JsonObject();
          params.put("body", obj);
          params.put("path", new JsonObject());
          params.put("cookie", siteRequest.getServiceRequest().getParams().getJsonObject("cookie"));
          params.put("header", siteRequest.getServiceRequest().getParams().getJsonObject("header"));
          params.put("form", new JsonObject());
          JsonObject query = new JsonObject();
          Boolean softCommit = Optional.ofNullable(siteRequest.getServiceRequest().getParams()).map(p -> p.getJsonObject("query")).map( q -> q.getBoolean("softCommit")).orElse(null);
          Integer commitWithin = Optional.ofNullable(siteRequest.getServiceRequest().getParams()).map(p -> p.getJsonObject("query")).map( q -> q.getInteger("commitWithin")).orElse(null);
          if(softCommit == null && commitWithin == null)
            softCommit = true;
          if(softCommit != null)
            query.put("softCommit", softCommit);
          if(commitWithin != null)
            query.put("commitWithin", commitWithin);
          params.put("query", query);
          JsonObject context = new JsonObject().put("params", params).put("user", siteRequest.getUserPrincipal());
          JsonObject json = new JsonObject().put("context", context);
          eventBus.request(RequestApproval.getClassApiAddress(), json, new DeliveryOptions().addHeader("action", "putimportRequestApprovalFuture")).onSuccess(a -> {
            promise1.complete();
          }).onFailure(ex -> {
            LOG.error(String.format("listPUTImportRequestApproval failed. "), ex);
            promise1.tryFail(ex);
          });
        }));
      });
      CompositeFuture.all(futures).onSuccess(a -> {
        apiRequest.setNumPATCH(apiRequest.getNumPATCH() + 1);
        promise.complete();
      }).onFailure(ex -> {
        LOG.error(String.format("listPUTImportRequestApproval failed. "), ex);
        promise.tryFail(ex);
      });
    } catch(Exception ex) {
      LOG.error(String.format("listPUTImportRequestApproval failed. "), ex);
      promise.tryFail(ex);
    }
    return promise.future();
  }

  @Override
  public void putimportRequestApprovalFuture(JsonObject body, ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler) {
    Boolean classPublicRead = false;
    user(serviceRequest, SiteRequest.class, SiteUser.class, SiteUser.getClassApiAddress(), "postSiteUserFuture", "patchSiteUserFuture", classPublicRead).onSuccess(siteRequest -> {
      try {
        siteRequest.setLang("enUS");
        Optional.ofNullable(serviceRequest.getParams().getJsonArray("scopes")).ifPresent(scopes -> {
          scopes.stream().map(v -> v.toString()).forEach(scope -> {
            siteRequest.addScopes(scope);
          });
        });
        ApiRequest apiRequest = new ApiRequest();
        apiRequest.setRows(1L);
        apiRequest.setNumFound(1L);
        apiRequest.setNumPATCH(0L);
        apiRequest.initDeepApiRequest(siteRequest);
        siteRequest.setApiRequest_(apiRequest);
        String approvalId = Optional.ofNullable(body.getString(RequestApproval.VAR_approvalId)).orElse(body.getString(RequestApproval.VAR_solrId));
        if(Optional.ofNullable(serviceRequest.getParams()).map(p -> p.getJsonObject("query")).map( q -> q.getJsonArray("var")).orElse(new JsonArray()).stream().filter(s -> "refresh:false".equals(s)).count() > 0L) {
          siteRequest.getRequestVars().put( "refresh", "false" );
        }
        pgPool.getConnection().onSuccess(sqlConnection -> {
          String sqlQuery = String.format("select * from %s WHERE approvalId=$1", RequestApproval.CLASS_SIMPLE_NAME);
          sqlConnection.preparedQuery(sqlQuery)
              .execute(Tuple.tuple(Arrays.asList(approvalId))
              ).onSuccess(result -> {
            sqlConnection.close().onSuccess(a -> {
              try {
                if(result.size() >= 1) {
                  RequestApproval o = new RequestApproval();
                  o.setSiteRequest_(siteRequest);
                  for(Row definition : result.value()) {
                    for(Integer i = 0; i < definition.size(); i++) {
                      try {
                        String columnName = definition.getColumnName(i);
                        Object columnValue = definition.getValue(i);
                        o.persistForClass(columnName, columnValue);
                      } catch(Exception e) {
                        LOG.error(String.format("persistRequestApproval failed. "), e);
                      }
                    }
                  }
                  RequestApproval o2 = new RequestApproval();
                  o2.setSiteRequest_(siteRequest);
                  JsonObject body2 = new JsonObject();
                  for(String f : body.fieldNames()) {
                    Object bodyVal = body.getValue(f);
                    if(bodyVal instanceof JsonArray) {
                      JsonArray bodyVals = (JsonArray)bodyVal;
                      Object valsObj = o.obtainForClass(f);
                      Collection<?> vals = valsObj instanceof JsonArray ? ((JsonArray)valsObj).getList() : (Collection<?>)valsObj;
                      if(vals != null && bodyVals.size() == vals.size()) {
                        Boolean match = true;
                        for(Object val : vals) {
                          if(val != null) {
                            if(!bodyVals.contains(val.toString())) {
                              match = false;
                              break;
                            }
                          } else {
                            match = false;
                            break;
                          }
                        }
                        vals.clear();
                        body2.put("set" + StringUtils.capitalize(f), bodyVal);
                      } else {
                        if(vals != null)
                          vals.clear();
                        body2.put("set" + StringUtils.capitalize(f), bodyVal);
                      }
                    } else {
                      o2.persistForClass(f, bodyVal);
                      o2.relateForClass(f, bodyVal);
                      if(!StringUtils.containsAny(f, "approvalId", "created", "setCreated") && !Objects.equals(o.obtainForClass(f), o2.obtainForClass(f)))
                        body2.put("set" + StringUtils.capitalize(f), bodyVal);
                    }
                  }
                  for(String f : Optional.ofNullable(o.getSaves()).orElse(new ArrayList<>())) {
                    if(!body.fieldNames().contains(f)) {
                      if(!StringUtils.containsAny(f, "approvalId", "created", "setCreated") && !Objects.equals(o.obtainForClass(f), o2.obtainForClass(f)))
                        body2.putNull("set" + StringUtils.capitalize(f));
                    }
                  }
                  if(result.size() >= 1) {
                    apiRequest.setOriginal(o);
                    apiRequest.setId(Optional.ofNullable(o.getApprovalId()).map(v -> v.toString()).orElse(null));
                    apiRequest.setSolrId(o.getSolrId());
                  }
                  siteRequest.setJsonObject(body2);
                  patchRequestApprovalFuture(o, true).onSuccess(b -> {
                    LOG.debug("Import RequestApproval {} succeeded, modified RequestApproval. ", body.getValue(RequestApproval.VAR_approvalId));
                    eventHandler.handle(Future.succeededFuture());
                  }).onFailure(ex -> {
                    LOG.error(String.format("putimportRequestApprovalFuture failed. "), ex);
                    eventHandler.handle(Future.failedFuture(ex));
                  });
                } else {
                  postRequestApprovalFuture(siteRequest, true).onSuccess(b -> {
                    LOG.debug("Import RequestApproval {} succeeded, created new RequestApproval. ", body.getValue(RequestApproval.VAR_approvalId));
                    eventHandler.handle(Future.succeededFuture());
                  }).onFailure(ex -> {
                    LOG.error(String.format("putimportRequestApprovalFuture failed. "), ex);
                    eventHandler.handle(Future.failedFuture(ex));
                  });
                }
              } catch(Exception ex) {
                LOG.error(String.format("putimportRequestApprovalFuture failed. "), ex);
                eventHandler.handle(Future.failedFuture(ex));
              }
            }).onFailure(ex -> {
              LOG.error(String.format("putimportRequestApprovalFuture failed. "), ex);
              eventHandler.handle(Future.failedFuture(ex));
            });
          }).onFailure(ex -> {
            LOG.error(String.format("putimportRequestApprovalFuture failed. "), ex);
            eventHandler.handle(Future.failedFuture(ex));
          });
        }).onFailure(ex -> {
          LOG.error(String.format("putimportRequestApprovalFuture failed. "), ex);
          eventHandler.handle(Future.failedFuture(ex));
        });
      } catch(Exception ex) {
        LOG.error(String.format("putimportRequestApprovalFuture failed. "), ex);
        eventHandler.handle(Future.failedFuture(ex));
      }
    }).onFailure(ex -> {
      if("Inactive Token".equals(ex.getMessage()) || StringUtils.startsWith(ex.getMessage(), "invalid_grant:")) {
        try {
          eventHandler.handle(Future.succeededFuture(new ServiceResponse(302, "Found", null, MultiMap.caseInsensitiveMultiMap().add(HttpHeaders.LOCATION, "/logout?redirect_uri=" + URLEncoder.encode(serviceRequest.getExtra().getString("uri"), "UTF-8")))));
        } catch(Exception ex2) {
          LOG.error(String.format("putimportRequestApproval failed. ", ex2));
          error(null, eventHandler, ex2);
        }
      } else if(StringUtils.startsWith(ex.getMessage(), "401 UNAUTHORIZED ")) {
        eventHandler.handle(Future.succeededFuture(
          new ServiceResponse(401, "UNAUTHORIZED",
            Buffer.buffer().appendString(
              new JsonObject()
                .put("errorCode", "401")
                .put("errorMessage", "SSO Resource Permission check returned DENY")
                .encodePrettily()
              ), MultiMap.caseInsensitiveMultiMap()
              )
          ));
      } else {
        LOG.error(String.format("putimportRequestApproval failed. "), ex);
        error(null, eventHandler, ex);
      }
    });
  }

  public Future<ServiceResponse> response200PUTImportRequestApproval(SiteRequest siteRequest) {
    Promise<ServiceResponse> promise = Promise.promise();
    try {
      JsonObject json = new JsonObject();
      promise.complete(ServiceResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily())));
    } catch(Exception ex) {
      LOG.error(String.format("response200PUTImportRequestApproval failed. "), ex);
      promise.tryFail(ex);
    }
    return promise.future();
  }

  // SearchPage //

  @Override
  public void searchpageRequestApproval(ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler) {
    Boolean classPublicRead = false;
    user(serviceRequest, SiteRequest.class, SiteUser.class, SiteUser.getClassApiAddress(), "postSiteUserFuture", "patchSiteUserFuture", classPublicRead).onSuccess(siteRequest -> {
      try {
        siteRequest.setLang("enUS");
        String approvalId = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("approvalId");
        String REQUESTAPPROVAL = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("REQUESTAPPROVAL");
        List<String> groups = Optional.ofNullable(siteRequest.getGroups()).orElse(new ArrayList<>());
        MultiMap form = MultiMap.caseInsensitiveMultiMap();
        form.add("grant_type", "urn:ietf:params:oauth:grant-type:uma-ticket");
        form.add("audience", config.getString(ComputateConfigKeys.AUTH_CLIENT));
        form.add("response_mode", "permissions");
        form.add("permission", String.format("%s#%s", RequestApproval.CLASS_AUTH_RESOURCE, "GET"));
        form.add("permission", String.format("%s#%s", RequestApproval.CLASS_AUTH_RESOURCE, "POST"));
        form.add("permission", String.format("%s#%s", RequestApproval.CLASS_AUTH_RESOURCE, "PATCH"));
        form.add("permission", String.format("%s#%s", RequestApproval.CLASS_AUTH_RESOURCE, "DELETE"));
        form.add("permission", String.format("%s#%s", RequestApproval.CLASS_AUTH_RESOURCE, "Admin"));
        form.add("permission", String.format("%s#%s", RequestApproval.CLASS_AUTH_RESOURCE, "SuperAdmin"));
        if(approvalId != null)
          form.add("permission", String.format("%s-%s#%s", RequestApproval.CLASS_AUTH_RESOURCE, approvalId, "GET"));
        webClient.post(
            config.getInteger(ComputateConfigKeys.AUTH_PORT)
            , config.getString(ComputateConfigKeys.AUTH_HOST_NAME)
            , config.getString(ComputateConfigKeys.AUTH_TOKEN_URI)
            )
            .ssl(config.getBoolean(ComputateConfigKeys.AUTH_SSL))
            .putHeader("Authorization", String.format("Bearer %s", Optional.ofNullable(siteRequest.getUser()).map(u -> u.principal().getString("access_token")).orElse("")))
            .sendForm(form)
            .expecting(HttpResponseExpectation.SC_OK)
        .onComplete(authorizationDecisionResponse -> {
          try {
            HttpResponse<Buffer> authorizationDecision = authorizationDecisionResponse.result();
            JsonArray authorizationDecisionBody = authorizationDecisionResponse.failed() ? new JsonArray() : authorizationDecision.bodyAsJsonArray();
            JsonArray scopes = authorizationDecisionBody.stream().map(o -> (JsonObject)o).filter(o -> "REQUESTAPPROVAL".equals(o.getString("rsname"))).findFirst().map(decision -> ((JsonObject)decision).getJsonArray("scopes")).orElse(new JsonArray());
            {
              siteRequest.setScopes(scopes.stream().map(o -> o.toString()).collect(Collectors.toList()));
              List<String> scopes2 = siteRequest.getScopes();
              searchRequestApprovalList(siteRequest, false, true, false, "GET").onSuccess(listRequestApproval -> {
                response200SearchPageRequestApproval(listRequestApproval).onSuccess(response -> {
                  eventHandler.handle(Future.succeededFuture(response));
                  LOG.debug(String.format("searchpageRequestApproval succeeded. "));
                }).onFailure(ex -> {
                  LOG.error(String.format("searchpageRequestApproval failed. "), ex);
                  error(siteRequest, eventHandler, ex);
                });
              }).onFailure(ex -> {
                LOG.error(String.format("searchpageRequestApproval failed. "), ex);
                error(siteRequest, eventHandler, ex);
              });
            }
          } catch(Exception ex) {
            LOG.error(String.format("searchpageRequestApproval failed. "), ex);
            error(null, eventHandler, ex);
          }
        });
      } catch(Exception ex) {
        LOG.error(String.format("searchpageRequestApproval failed. "), ex);
        error(null, eventHandler, ex);
      }
    }).onFailure(ex -> {
      if("Inactive Token".equals(ex.getMessage()) || StringUtils.startsWith(ex.getMessage(), "invalid_grant:")) {
        try {
          eventHandler.handle(Future.succeededFuture(new ServiceResponse(302, "Found", null, MultiMap.caseInsensitiveMultiMap().add(HttpHeaders.LOCATION, "/logout?redirect_uri=" + URLEncoder.encode(serviceRequest.getExtra().getString("uri"), "UTF-8")))));
        } catch(Exception ex2) {
          LOG.error(String.format("searchpageRequestApproval failed. ", ex2));
          error(null, eventHandler, ex2);
        }
      } else if(StringUtils.startsWith(ex.getMessage(), "401 UNAUTHORIZED ")) {
        eventHandler.handle(Future.succeededFuture(
          new ServiceResponse(401, "UNAUTHORIZED",
            Buffer.buffer().appendString(
              new JsonObject()
                .put("errorCode", "401")
                .put("errorMessage", "SSO Resource Permission check returned DENY")
                .encodePrettily()
              ), MultiMap.caseInsensitiveMultiMap()
              )
          ));
      } else {
        LOG.error(String.format("searchpageRequestApproval failed. "), ex);
        error(null, eventHandler, ex);
      }
    });
  }

  public void searchpageRequestApprovalPageInit(JsonObject ctx, RequestApprovalPage page, SearchList<RequestApproval> listRequestApproval, Promise<Void> promise) {
    String siteBaseUrl = config.getString(ComputateConfigKeys.SITE_BASE_URL);

    ctx.put("enUSUrlSearchPage", String.format("%s%s", siteBaseUrl, "/en-us/search/approval"));
    ctx.put("enUSUrlPage", String.format("%s%s", siteBaseUrl, "/en-us/search/approval"));
    ctx.put("enUSUrlDisplayPage", Optional.ofNullable(page.getResult()).map(o -> o.getDisplayPage()));
    ctx.put("enUSUrlEditPage", Optional.ofNullable(page.getResult()).map(o -> o.getEditPage()));
    ctx.put("enUSUrlUserPage", Optional.ofNullable(page.getResult()).map(o -> o.getUserPage()));
    ctx.put("enUSUrlDownload", Optional.ofNullable(page.getResult()).map(o -> o.getDownload()));

    promise.complete();
  }

  public String templateUriSearchPageRequestApproval(ServiceRequest serviceRequest, RequestApproval result) {
    return "en-us/search/approval/RequestApprovalSearchPage.htm";
  }
  public void templateSearchPageRequestApproval(JsonObject ctx, RequestApprovalPage page, SearchList<RequestApproval> listRequestApproval, Promise<String> promise) {
    try {
      SiteRequest siteRequest = listRequestApproval.getSiteRequest_(SiteRequest.class);
      ServiceRequest serviceRequest = siteRequest.getServiceRequest();
      RequestApproval result = listRequestApproval.first();
      String pageTemplateUri = templateUriSearchPageRequestApproval(serviceRequest, result);
      String siteTemplatePath = config.getString(ComputateConfigKeys.TEMPLATE_PATH);
      Path resourceTemplatePath = Path.of(siteTemplatePath, pageTemplateUri);
      if(result == null || !Files.exists(resourceTemplatePath)) {
        String template = Files.readString(Path.of(siteTemplatePath, "en-us/search/approval/RequestApprovalSearchPage.htm"), Charset.forName("UTF-8"));
        String renderedTemplate = jinjava.render(template, ctx.getMap());
        promise.complete(renderedTemplate);
      } else if(pageTemplateUri.endsWith(".md")) {
        String template = siteTemplatePath == null ? Resources.toString(Resources.getResource(resourceTemplatePath.toString()), StandardCharsets.UTF_8) : Files.readString(resourceTemplatePath, Charset.forName("UTF-8"));
        String metaPrefixResult = String.format("%s.", i18n.getString(I18n.var_resultat));
        Map<String, Object> data = new HashMap<>();
        String body = "";
        if(template.startsWith("---\n")) {
          Matcher mMeta = Pattern.compile("---\n([\\w\\W]+?)\n---\n([\\w\\W]+)", Pattern.MULTILINE).matcher(template);
          if(mMeta.find()) {
            String meta = mMeta.group(1);
            body = mMeta.group(2);
            Yaml yaml = new Yaml();
            Map<String, Object> map = yaml.load(meta);
            map.forEach((resultKey, value) -> {
              if(resultKey.startsWith(metaPrefixResult)) {
                String key = StringUtils.substringAfter(resultKey, metaPrefixResult);
                String val = Optional.ofNullable(value).map(v -> v.toString()).orElse(null);
                if(val instanceof String) {
                  String rendered = jinjava.render(val, ctx.getMap());
                  data.put(key, rendered);
                } else {
                  data.put(key, val);
                }
              }
            });
            map.forEach((resultKey, value) -> {
              if(resultKey.startsWith(metaPrefixResult)) {
                String key = StringUtils.substringAfter(resultKey, metaPrefixResult);
                String val = Optional.ofNullable(value).map(v -> v.toString()).orElse(null);
                if(val instanceof String) {
                  String rendered = jinjava.render(val, ctx.getMap());
                  data.put(key, rendered);
                } else {
                  data.put(key, val);
                }
              }
            });
          }
        }
        org.commonmark.parser.Parser parser = org.commonmark.parser.Parser.builder().build();
        org.commonmark.node.Node document = parser.parse(body);
        org.commonmark.renderer.html.HtmlRenderer renderer = org.commonmark.renderer.html.HtmlRenderer.builder().build();
        String pageExtends =  Optional.ofNullable((String)data.get("extends")).orElse("en-us/Article.htm");
        String htmTemplate = "{% extends \"" + pageExtends + "\" %}\n{% block htmBodyMiddleArticle %}\n" + renderer.render(document) + "\n{% endblock htmBodyMiddleArticle %}\n";
        String renderedTemplate = jinjava.render(htmTemplate, ctx.getMap());
        promise.complete(renderedTemplate);
      } else {
        String template = siteTemplatePath == null ? Resources.toString(Resources.getResource(resourceTemplatePath.toString()), StandardCharsets.UTF_8) : Files.readString(resourceTemplatePath, Charset.forName("UTF-8"));
        String renderedTemplate = jinjava.render(template, ctx.getMap());
        promise.complete(renderedTemplate);
      }
    } catch(Exception ex) {
      LOG.error(String.format("templateSearchPageRequestApproval failed. "), ex);
      ExceptionUtils.rethrow(ex);
    }
  }
  public Future<ServiceResponse> response200SearchPageRequestApproval(SearchList<RequestApproval> listRequestApproval) {
    Promise<ServiceResponse> promise = Promise.promise();
    try {
      SiteRequest siteRequest = listRequestApproval.getSiteRequest_(SiteRequest.class);
      RequestApprovalPage page = new RequestApprovalPage();
      MultiMap requestHeaders = MultiMap.caseInsensitiveMultiMap();
      siteRequest.setRequestHeaders(requestHeaders);

      if(listRequestApproval.size() >= 1)
        siteRequest.setRequestPk(listRequestApproval.get(0).getPk());
      page.setSearchListRequestApproval_(listRequestApproval);
      page.setSiteRequest_(siteRequest);
      page.setServiceRequest(siteRequest.getServiceRequest());
      page.setWebClient(webClient);
      page.setVertx(vertx);
      page.promiseDeepRequestApprovalPage(siteRequest).onSuccess(a -> {
        try {
          JsonObject ctx = ConfigKeys.getPageContext(config);
          ctx.mergeIn(JsonObject.mapFrom(page));
          Promise<Void> promise1 = Promise.promise();
          searchpageRequestApprovalPageInit(ctx, page, listRequestApproval, promise1);
          promise1.future().onSuccess(b -> {
            try {
              Promise<String> promise2 = Promise.promise();
              templateSearchPageRequestApproval(ctx, page, listRequestApproval, promise2);
              promise2.future().onSuccess(renderedTemplate -> {
                try {
                  Buffer buffer = Buffer.buffer(renderedTemplate);
                  promise.complete(new ServiceResponse(200, "OK", buffer, requestHeaders));
                } catch(Throwable ex) {
                  LOG.error(String.format("response200SearchPageRequestApproval failed. "), ex);
                  promise.fail(ex);
                }
              }).onFailure(ex -> {
                promise.fail(ex);
              });
            } catch(Throwable ex) {
              LOG.error(String.format("response200SearchPageRequestApproval failed. "), ex);
              promise.tryFail(ex);
            }
          }).onFailure(ex -> {
            promise.tryFail(ex);
          });
        } catch(Exception ex) {
          LOG.error(String.format("response200SearchPageRequestApproval failed. "), ex);
          promise.tryFail(ex);
        }
      }).onFailure(ex -> {
        promise.tryFail(ex);
      });
    } catch(Exception ex) {
      LOG.error(String.format("response200SearchPageRequestApproval failed. "), ex);
      promise.tryFail(ex);
    }
    return promise.future();
  }
  public void responsePivotSearchPageRequestApproval(List<SolrResponse.Pivot> pivots, JsonArray pivotArray) {
    if(pivots != null) {
      for(SolrResponse.Pivot pivotField : pivots) {
        String entityIndexed = pivotField.getField();
        String entityVar = StringUtils.substringBefore(entityIndexed, "_docvalues_");
        JsonObject pivotJson = new JsonObject();
        pivotArray.add(pivotJson);
        pivotJson.put("field", entityVar);
        pivotJson.put("value", pivotField.getValue());
        pivotJson.put("count", pivotField.getCount());
        Collection<SolrResponse.PivotRange> pivotRanges = pivotField.getRanges().values();
        List<SolrResponse.Pivot> pivotFields2 = pivotField.getPivotList();
        if(pivotRanges != null) {
          JsonObject rangeJson = new JsonObject();
          pivotJson.put("ranges", rangeJson);
          for(SolrResponse.PivotRange rangeFacet : pivotRanges) {
            JsonObject rangeFacetJson = new JsonObject();
            String rangeFacetVar = StringUtils.substringBefore(rangeFacet.getName(), "_docvalues_");
            rangeJson.put(rangeFacetVar, rangeFacetJson);
            JsonObject rangeFacetCountsObject = new JsonObject();
            rangeFacetJson.put("counts", rangeFacetCountsObject);
            rangeFacet.getCounts().forEach((value, count) -> {
              rangeFacetCountsObject.put(value, count);
            });
          }
        }
        if(pivotFields2 != null) {
          JsonArray pivotArray2 = new JsonArray();
          pivotJson.put("pivot", pivotArray2);
          responsePivotSearchPageRequestApproval(pivotFields2, pivotArray2);
        }
      }
    }
  }

  // EditPage //

  @Override
  public void editpageRequestApproval(ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler) {
    Boolean classPublicRead = false;
    user(serviceRequest, SiteRequest.class, SiteUser.class, SiteUser.getClassApiAddress(), "postSiteUserFuture", "patchSiteUserFuture", classPublicRead).onSuccess(siteRequest -> {
      try {
        siteRequest.setLang("enUS");
        String approvalId = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("approvalId");
        String REQUESTAPPROVAL = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("REQUESTAPPROVAL");
        List<String> groups = Optional.ofNullable(siteRequest.getGroups()).orElse(new ArrayList<>());
        MultiMap form = MultiMap.caseInsensitiveMultiMap();
        form.add("grant_type", "urn:ietf:params:oauth:grant-type:uma-ticket");
        form.add("audience", config.getString(ComputateConfigKeys.AUTH_CLIENT));
        form.add("response_mode", "permissions");
        form.add("permission", String.format("%s#%s", RequestApproval.CLASS_AUTH_RESOURCE, "GET"));
        form.add("permission", String.format("%s#%s", RequestApproval.CLASS_AUTH_RESOURCE, "POST"));
        form.add("permission", String.format("%s#%s", RequestApproval.CLASS_AUTH_RESOURCE, "PATCH"));
        form.add("permission", String.format("%s#%s", RequestApproval.CLASS_AUTH_RESOURCE, "DELETE"));
        form.add("permission", String.format("%s#%s", RequestApproval.CLASS_AUTH_RESOURCE, "Admin"));
        form.add("permission", String.format("%s#%s", RequestApproval.CLASS_AUTH_RESOURCE, "SuperAdmin"));
        if(approvalId != null)
          form.add("permission", String.format("%s-%s#%s", RequestApproval.CLASS_AUTH_RESOURCE, approvalId, "GET"));
        webClient.post(
            config.getInteger(ComputateConfigKeys.AUTH_PORT)
              , config.getString(ComputateConfigKeys.AUTH_HOST_NAME)
              , config.getString(ComputateConfigKeys.AUTH_TOKEN_URI)
              )
              .ssl(config.getBoolean(ComputateConfigKeys.AUTH_SSL))
              .putHeader("Authorization", String.format("Bearer %s", Optional.ofNullable(siteRequest.getUser()).map(u -> u.principal().getString("access_token")).orElse("")))
              .sendForm(form)
              .expecting(HttpResponseExpectation.SC_OK)
        .onComplete(authorizationDecisionResponse -> {
          try {
            HttpResponse<Buffer> authorizationDecision = authorizationDecisionResponse.result();
            JsonArray authorizationDecisionBody = authorizationDecisionResponse.failed() ? new JsonArray() : authorizationDecision.bodyAsJsonArray();
            JsonArray scopes = authorizationDecisionBody.stream().map(o -> (JsonObject)o).filter(o -> "REQUESTAPPROVAL".equals(o.getString("rsname"))).findFirst().map(decision -> ((JsonObject)decision).getJsonArray("scopes")).orElse(new JsonArray());
            {
              siteRequest.setScopes(scopes.stream().map(o -> o.toString()).collect(Collectors.toList()));
              List<String> scopes2 = siteRequest.getScopes();
              searchRequestApprovalList(siteRequest, false, true, false, "GET").onSuccess(listRequestApproval -> {
                response200EditPageRequestApproval(listRequestApproval).onSuccess(response -> {
                  eventHandler.handle(Future.succeededFuture(response));
                  LOG.debug(String.format("editpageRequestApproval succeeded. "));
                }).onFailure(ex -> {
                  LOG.error(String.format("editpageRequestApproval failed. "), ex);
                  error(siteRequest, eventHandler, ex);
                });
              }).onFailure(ex -> {
                LOG.error(String.format("editpageRequestApproval failed. "), ex);
                error(siteRequest, eventHandler, ex);
            });
            }
          } catch(Exception ex) {
            LOG.error(String.format("editpageRequestApproval failed. "), ex);
            error(null, eventHandler, ex);
          }
        });
      } catch(Exception ex) {
        LOG.error(String.format("editpageRequestApproval failed. "), ex);
        error(null, eventHandler, ex);
      }
    }).onFailure(ex -> {
      if("Inactive Token".equals(ex.getMessage()) || StringUtils.startsWith(ex.getMessage(), "invalid_grant:")) {
        try {
          eventHandler.handle(Future.succeededFuture(new ServiceResponse(302, "Found", null, MultiMap.caseInsensitiveMultiMap().add(HttpHeaders.LOCATION, "/logout?redirect_uri=" + URLEncoder.encode(serviceRequest.getExtra().getString("uri"), "UTF-8")))));
        } catch(Exception ex2) {
          LOG.error(String.format("editpageRequestApproval failed. ", ex2));
          error(null, eventHandler, ex2);
        }
      } else if(StringUtils.startsWith(ex.getMessage(), "401 UNAUTHORIZED ")) {
        eventHandler.handle(Future.succeededFuture(
          new ServiceResponse(401, "UNAUTHORIZED",
            Buffer.buffer().appendString(
              new JsonObject()
                .put("errorCode", "401")
                .put("errorMessage", "SSO Resource Permission check returned DENY")
                .encodePrettily()
              ), MultiMap.caseInsensitiveMultiMap()
              )
          ));
      } else {
        LOG.error(String.format("editpageRequestApproval failed. "), ex);
        error(null, eventHandler, ex);
      }
    });
  }

  public void editpageRequestApprovalPageInit(JsonObject ctx, RequestApprovalPage page, SearchList<RequestApproval> listRequestApproval, Promise<Void> promise) {
    String siteBaseUrl = config.getString(ComputateConfigKeys.SITE_BASE_URL);

    ctx.put("enUSUrlSearchPage", String.format("%s%s", siteBaseUrl, "/en-us/search/approval"));
    ctx.put("enUSUrlDisplayPage", Optional.ofNullable(page.getResult()).map(o -> o.getDisplayPage()));
    ctx.put("enUSUrlEditPage", Optional.ofNullable(page.getResult()).map(o -> o.getEditPage()));
    ctx.put("enUSUrlPage", Optional.ofNullable(page.getResult()).map(o -> o.getEditPage()));
    ctx.put("enUSUrlUserPage", Optional.ofNullable(page.getResult()).map(o -> o.getUserPage()));
    ctx.put("enUSUrlDownload", Optional.ofNullable(page.getResult()).map(o -> o.getDownload()));

    promise.complete();
  }

  public String templateUriEditPageRequestApproval(ServiceRequest serviceRequest, RequestApproval result) {
    return "en-us/edit/approval/RequestApprovalEditPage.htm";
  }
  public void templateEditPageRequestApproval(JsonObject ctx, RequestApprovalPage page, SearchList<RequestApproval> listRequestApproval, Promise<String> promise) {
    try {
      SiteRequest siteRequest = listRequestApproval.getSiteRequest_(SiteRequest.class);
      ServiceRequest serviceRequest = siteRequest.getServiceRequest();
      RequestApproval result = listRequestApproval.first();
      String pageTemplateUri = templateUriEditPageRequestApproval(serviceRequest, result);
      String siteTemplatePath = config.getString(ComputateConfigKeys.TEMPLATE_PATH);
      Path resourceTemplatePath = Path.of(siteTemplatePath, pageTemplateUri);
      if(result == null || !Files.exists(resourceTemplatePath)) {
        String template = Files.readString(Path.of(siteTemplatePath, "en-us/edit/approval/RequestApprovalEditPage.htm"), Charset.forName("UTF-8"));
        String renderedTemplate = jinjava.render(template, ctx.getMap());
        promise.complete(renderedTemplate);
      } else if(pageTemplateUri.endsWith(".md")) {
        String template = siteTemplatePath == null ? Resources.toString(Resources.getResource(resourceTemplatePath.toString()), StandardCharsets.UTF_8) : Files.readString(resourceTemplatePath, Charset.forName("UTF-8"));
        String metaPrefixResult = String.format("%s.", i18n.getString(I18n.var_resultat));
        Map<String, Object> data = new HashMap<>();
        String body = "";
        if(template.startsWith("---\n")) {
          Matcher mMeta = Pattern.compile("---\n([\\w\\W]+?)\n---\n([\\w\\W]+)", Pattern.MULTILINE).matcher(template);
          if(mMeta.find()) {
            String meta = mMeta.group(1);
            body = mMeta.group(2);
            Yaml yaml = new Yaml();
            Map<String, Object> map = yaml.load(meta);
            map.forEach((resultKey, value) -> {
              if(resultKey.startsWith(metaPrefixResult)) {
                String key = StringUtils.substringAfter(resultKey, metaPrefixResult);
                String val = Optional.ofNullable(value).map(v -> v.toString()).orElse(null);
                if(val instanceof String) {
                  String rendered = jinjava.render(val, ctx.getMap());
                  data.put(key, rendered);
                } else {
                  data.put(key, val);
                }
              }
            });
            map.forEach((resultKey, value) -> {
              if(resultKey.startsWith(metaPrefixResult)) {
                String key = StringUtils.substringAfter(resultKey, metaPrefixResult);
                String val = Optional.ofNullable(value).map(v -> v.toString()).orElse(null);
                if(val instanceof String) {
                  String rendered = jinjava.render(val, ctx.getMap());
                  data.put(key, rendered);
                } else {
                  data.put(key, val);
                }
              }
            });
          }
        }
        org.commonmark.parser.Parser parser = org.commonmark.parser.Parser.builder().build();
        org.commonmark.node.Node document = parser.parse(body);
        org.commonmark.renderer.html.HtmlRenderer renderer = org.commonmark.renderer.html.HtmlRenderer.builder().build();
        String pageExtends =  Optional.ofNullable((String)data.get("extends")).orElse("en-us/Article.htm");
        String htmTemplate = "{% extends \"" + pageExtends + "\" %}\n{% block htmBodyMiddleArticle %}\n" + renderer.render(document) + "\n{% endblock htmBodyMiddleArticle %}\n";
        String renderedTemplate = jinjava.render(htmTemplate, ctx.getMap());
        promise.complete(renderedTemplate);
      } else {
        String template = siteTemplatePath == null ? Resources.toString(Resources.getResource(resourceTemplatePath.toString()), StandardCharsets.UTF_8) : Files.readString(resourceTemplatePath, Charset.forName("UTF-8"));
        String renderedTemplate = jinjava.render(template, ctx.getMap());
        promise.complete(renderedTemplate);
      }
    } catch(Exception ex) {
      LOG.error(String.format("templateEditPageRequestApproval failed. "), ex);
      ExceptionUtils.rethrow(ex);
    }
  }
  public Future<ServiceResponse> response200EditPageRequestApproval(SearchList<RequestApproval> listRequestApproval) {
    Promise<ServiceResponse> promise = Promise.promise();
    try {
      SiteRequest siteRequest = listRequestApproval.getSiteRequest_(SiteRequest.class);
      RequestApprovalPage page = new RequestApprovalPage();
      MultiMap requestHeaders = MultiMap.caseInsensitiveMultiMap();
      siteRequest.setRequestHeaders(requestHeaders);

      if(listRequestApproval.size() >= 1)
        siteRequest.setRequestPk(listRequestApproval.get(0).getPk());
      page.setSearchListRequestApproval_(listRequestApproval);
      page.setSiteRequest_(siteRequest);
      page.setServiceRequest(siteRequest.getServiceRequest());
      page.setWebClient(webClient);
      page.setVertx(vertx);
      page.promiseDeepRequestApprovalPage(siteRequest).onSuccess(a -> {
        try {
          JsonObject ctx = ConfigKeys.getPageContext(config);
          ctx.mergeIn(JsonObject.mapFrom(page));
          Promise<Void> promise1 = Promise.promise();
          editpageRequestApprovalPageInit(ctx, page, listRequestApproval, promise1);
          promise1.future().onSuccess(b -> {
            try {
              Promise<String> promise2 = Promise.promise();
              templateEditPageRequestApproval(ctx, page, listRequestApproval, promise2);
              promise2.future().onSuccess(renderedTemplate -> {
                try {
                  Buffer buffer = Buffer.buffer(renderedTemplate);
                  promise.complete(new ServiceResponse(200, "OK", buffer, requestHeaders));
                } catch(Throwable ex) {
                  LOG.error(String.format("response200EditPageRequestApproval failed. "), ex);
                  promise.fail(ex);
                }
              }).onFailure(ex -> {
                promise.fail(ex);
              });
            } catch(Throwable ex) {
              LOG.error(String.format("response200EditPageRequestApproval failed. "), ex);
              promise.tryFail(ex);
            }
          }).onFailure(ex -> {
            promise.tryFail(ex);
          });
        } catch(Exception ex) {
          LOG.error(String.format("response200EditPageRequestApproval failed. "), ex);
          promise.tryFail(ex);
        }
      }).onFailure(ex -> {
        promise.tryFail(ex);
      });
    } catch(Exception ex) {
      LOG.error(String.format("response200EditPageRequestApproval failed. "), ex);
      promise.tryFail(ex);
    }
    return promise.future();
  }
  public void responsePivotEditPageRequestApproval(List<SolrResponse.Pivot> pivots, JsonArray pivotArray) {
    if(pivots != null) {
      for(SolrResponse.Pivot pivotField : pivots) {
        String entityIndexed = pivotField.getField();
        String entityVar = StringUtils.substringBefore(entityIndexed, "_docvalues_");
        JsonObject pivotJson = new JsonObject();
        pivotArray.add(pivotJson);
        pivotJson.put("field", entityVar);
        pivotJson.put("value", pivotField.getValue());
        pivotJson.put("count", pivotField.getCount());
        Collection<SolrResponse.PivotRange> pivotRanges = pivotField.getRanges().values();
        List<SolrResponse.Pivot> pivotFields2 = pivotField.getPivotList();
        if(pivotRanges != null) {
          JsonObject rangeJson = new JsonObject();
          pivotJson.put("ranges", rangeJson);
          for(SolrResponse.PivotRange rangeFacet : pivotRanges) {
            JsonObject rangeFacetJson = new JsonObject();
            String rangeFacetVar = StringUtils.substringBefore(rangeFacet.getName(), "_docvalues_");
            rangeJson.put(rangeFacetVar, rangeFacetJson);
            JsonObject rangeFacetCountsObject = new JsonObject();
            rangeFacetJson.put("counts", rangeFacetCountsObject);
            rangeFacet.getCounts().forEach((value, count) -> {
              rangeFacetCountsObject.put(value, count);
            });
          }
        }
        if(pivotFields2 != null) {
          JsonArray pivotArray2 = new JsonArray();
          pivotJson.put("pivot", pivotArray2);
          responsePivotEditPageRequestApproval(pivotFields2, pivotArray2);
        }
      }
    }
  }

  // DELETEFilter //

  @Override
  public void deletefilterRequestApproval(JsonObject body, ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler) {
    LOG.debug(String.format("deletefilterRequestApproval started. "));
    Boolean classPublicRead = false;
    user(serviceRequest, SiteRequest.class, SiteUser.class, SiteUser.getClassApiAddress(), "postSiteUserFuture", "patchSiteUserFuture", classPublicRead).onSuccess(siteRequest -> {
      try {
        siteRequest.setLang("enUS");
        String approvalId = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("approvalId");
        String REQUESTAPPROVAL = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("REQUESTAPPROVAL");
        List<String> groups = Optional.ofNullable(siteRequest.getGroups()).orElse(new ArrayList<>());
        MultiMap form = MultiMap.caseInsensitiveMultiMap();
        form.add("grant_type", "urn:ietf:params:oauth:grant-type:uma-ticket");
        form.add("audience", config.getString(ComputateConfigKeys.AUTH_CLIENT));
        form.add("response_mode", "permissions");
        form.add("permission", String.format("%s#%s", RequestApproval.CLASS_AUTH_RESOURCE, "GET"));
        form.add("permission", String.format("%s#%s", RequestApproval.CLASS_AUTH_RESOURCE, "POST"));
        form.add("permission", String.format("%s#%s", RequestApproval.CLASS_AUTH_RESOURCE, "PATCH"));
        form.add("permission", String.format("%s#%s", RequestApproval.CLASS_AUTH_RESOURCE, "DELETE"));
        form.add("permission", String.format("%s#%s", RequestApproval.CLASS_AUTH_RESOURCE, "Admin"));
        form.add("permission", String.format("%s#%s", RequestApproval.CLASS_AUTH_RESOURCE, "SuperAdmin"));
        if(approvalId != null)
          form.add("permission", String.format("%s-%s#%s", RequestApproval.CLASS_AUTH_RESOURCE, approvalId, "DELETE"));
        webClient.post(
            config.getInteger(ComputateConfigKeys.AUTH_PORT)
            , config.getString(ComputateConfigKeys.AUTH_HOST_NAME)
            , config.getString(ComputateConfigKeys.AUTH_TOKEN_URI)
            )
            .ssl(config.getBoolean(ComputateConfigKeys.AUTH_SSL))
            .putHeader("Authorization", String.format("Bearer %s", Optional.ofNullable(siteRequest.getUser()).map(u -> u.principal().getString("access_token")).orElse("")))
            .sendForm(form)
            .expecting(HttpResponseExpectation.SC_OK)
        .onComplete(authorizationDecisionResponse -> {
          try {
            HttpResponse<Buffer> authorizationDecision = authorizationDecisionResponse.result();
            JsonArray authorizationDecisionBody = authorizationDecisionResponse.failed() ? new JsonArray() : authorizationDecision.bodyAsJsonArray();
            JsonArray scopes = authorizationDecisionBody.stream().map(o -> (JsonObject)o).filter(o -> "REQUESTAPPROVAL".equals(o.getString("rsname"))).findFirst().map(decision -> ((JsonObject)decision).getJsonArray("scopes")).orElse(new JsonArray());
            if(authorizationDecisionResponse.failed() || !scopes.contains("DELETE")) {
              String msg = String.format("403 FORBIDDEN user %s to %s %s", siteRequest.getUser().attributes().getJsonObject("accessToken").getString("preferred_username"), serviceRequest.getExtra().getString("method"), serviceRequest.getExtra().getString("uri"));
              eventHandler.handle(Future.succeededFuture(
                new ServiceResponse(403, "FORBIDDEN",
                  Buffer.buffer().appendString(
                    new JsonObject()
                      .put("errorCode", "403")
                      .put("errorMessage", msg)
                      .encodePrettily()
                    ), MultiMap.caseInsensitiveMultiMap()
                )
              ));
            } else {
              siteRequest.setScopes(scopes.stream().map(o -> o.toString()).collect(Collectors.toList()));
              List<String> scopes2 = siteRequest.getScopes();
              searchRequestApprovalList(siteRequest, false, true, true, "DELETE").onSuccess(listRequestApproval -> {
                try {
                  ApiRequest apiRequest = new ApiRequest();
                  apiRequest.setRows(listRequestApproval.getRequest().getRows());
                  apiRequest.setNumFound(listRequestApproval.getResponse().getResponse().getNumFound());
                  apiRequest.setNumPATCH(0L);
                  apiRequest.initDeepApiRequest(siteRequest);
                  siteRequest.setApiRequest_(apiRequest);
                  if(apiRequest.getNumFound() == 1L)
                    apiRequest.setOriginal(listRequestApproval.first());
                  apiRequest.setSolrId(Optional.ofNullable(listRequestApproval.first()).map(o2 -> o2.getSolrId()).orElse(null));
                  eventBus.publish("websocketRequestApproval", JsonObject.mapFrom(apiRequest).toString());

                  listDELETEFilterRequestApproval(apiRequest, listRequestApproval).onSuccess(e -> {
                    response200DELETEFilterRequestApproval(siteRequest).onSuccess(response -> {
                      LOG.debug(String.format("deletefilterRequestApproval succeeded. "));
                      eventHandler.handle(Future.succeededFuture(response));
                    }).onFailure(ex -> {
                      LOG.error(String.format("deletefilterRequestApproval failed. "), ex);
                      error(siteRequest, eventHandler, ex);
                    });
                  }).onFailure(ex -> {
                    LOG.error(String.format("deletefilterRequestApproval failed. "), ex);
                    error(siteRequest, eventHandler, ex);
                  });
                } catch(Exception ex) {
                  LOG.error(String.format("deletefilterRequestApproval failed. "), ex);
                  error(siteRequest, eventHandler, ex);
                }
              }).onFailure(ex -> {
                LOG.error(String.format("deletefilterRequestApproval failed. "), ex);
                error(siteRequest, eventHandler, ex);
              });
            }
          } catch(Exception ex) {
            LOG.error(String.format("deletefilterRequestApproval failed. "), ex);
            error(null, eventHandler, ex);
          }
        });
      } catch(Exception ex) {
        LOG.error(String.format("deletefilterRequestApproval failed. "), ex);
        error(null, eventHandler, ex);
      }
    }).onFailure(ex -> {
      if("Inactive Token".equals(ex.getMessage()) || StringUtils.startsWith(ex.getMessage(), "invalid_grant:")) {
        try {
          eventHandler.handle(Future.succeededFuture(new ServiceResponse(302, "Found", null, MultiMap.caseInsensitiveMultiMap().add(HttpHeaders.LOCATION, "/logout?redirect_uri=" + URLEncoder.encode(serviceRequest.getExtra().getString("uri"), "UTF-8")))));
        } catch(Exception ex2) {
          LOG.error(String.format("deletefilterRequestApproval failed. ", ex2));
          error(null, eventHandler, ex2);
        }
      } else if(StringUtils.startsWith(ex.getMessage(), "401 UNAUTHORIZED ")) {
        eventHandler.handle(Future.succeededFuture(
          new ServiceResponse(401, "UNAUTHORIZED",
            Buffer.buffer().appendString(
              new JsonObject()
                .put("errorCode", "401")
                .put("errorMessage", "SSO Resource Permission check returned DENY")
                .encodePrettily()
              ), MultiMap.caseInsensitiveMultiMap()
              )
          ));
      } else {
        LOG.error(String.format("deletefilterRequestApproval failed. "), ex);
        error(null, eventHandler, ex);
      }
    });
  }

  public Future<Void> listDELETEFilterRequestApproval(ApiRequest apiRequest, SearchList<RequestApproval> listRequestApproval) {
    Promise<Void> promise = Promise.promise();
    List<Future> futures = new ArrayList<>();
    SiteRequest siteRequest = listRequestApproval.getSiteRequest_(SiteRequest.class);
    listRequestApproval.getList().forEach(o -> {
      SiteRequest siteRequest2 = generateSiteRequest(siteRequest.getUser(), siteRequest.getUserPrincipal(), siteRequest.getServiceRequest(), siteRequest.getJsonObject(), SiteRequest.class);
      siteRequest2.setScopes(siteRequest.getScopes());
      o.setSiteRequest_(siteRequest2);
      siteRequest2.setApiRequest_(siteRequest.getApiRequest_());
      JsonObject jsonObject = JsonObject.mapFrom(o);
      RequestApproval o2 = jsonObject.mapTo(RequestApproval.class);
      o2.setSiteRequest_(siteRequest2);
      futures.add(Future.future(promise1 -> {
        deletefilterRequestApprovalFuture(o).onSuccess(a -> {
          promise1.complete();
        }).onFailure(ex -> {
          LOG.error(String.format("listDELETEFilterRequestApproval failed. "), ex);
          promise1.tryFail(ex);
        });
      }));
    });
    CompositeFuture.all(futures).onSuccess( a -> {
      listRequestApproval.next().onSuccess(next -> {
        if(next) {
          listDELETEFilterRequestApproval(apiRequest, listRequestApproval).onSuccess(b -> {
            promise.complete();
          }).onFailure(ex -> {
            LOG.error(String.format("listDELETEFilterRequestApproval failed. "), ex);
            promise.tryFail(ex);
          });
        } else {
          promise.complete();
        }
      }).onFailure(ex -> {
        LOG.error(String.format("listDELETEFilterRequestApproval failed. "), ex);
        promise.tryFail(ex);
      });
    }).onFailure(ex -> {
      LOG.error(String.format("listDELETEFilterRequestApproval failed. "), ex);
      promise.tryFail(ex);
    });
    return promise.future();
  }

  @Override
  public void deletefilterRequestApprovalFuture(JsonObject body, ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler) {
    Boolean classPublicRead = false;
    user(serviceRequest, SiteRequest.class, SiteUser.class, SiteUser.getClassApiAddress(), "postSiteUserFuture", "patchSiteUserFuture", classPublicRead).onSuccess(siteRequest -> {
      try {
        siteRequest.setLang("enUS");
        siteRequest.setJsonObject(body);
        serviceRequest.getParams().getJsonObject("query").put("rows", 1);
        Optional.ofNullable(serviceRequest.getParams().getJsonArray("scopes")).ifPresent(scopes -> {
          scopes.stream().map(v -> v.toString()).forEach(scope -> {
            siteRequest.addScopes(scope);
          });
        });
        searchRequestApprovalList(siteRequest, false, true, true, "DELETE").onSuccess(listRequestApproval -> {
          try {
            RequestApproval o = listRequestApproval.first();
            if(o != null && listRequestApproval.getResponse().getResponse().getNumFound() == 1) {
              ApiRequest apiRequest = new ApiRequest();
              apiRequest.setRows(1L);
              apiRequest.setNumFound(1L);
              apiRequest.setNumPATCH(0L);
              apiRequest.initDeepApiRequest(siteRequest);
              siteRequest.setApiRequest_(apiRequest);
              if(Optional.ofNullable(serviceRequest.getParams()).map(p -> p.getJsonObject("query")).map( q -> q.getJsonArray("var")).orElse(new JsonArray()).stream().filter(s -> "refresh:false".equals(s)).count() > 0L) {
                siteRequest.getRequestVars().put( "refresh", "false" );
              }
              if(apiRequest.getNumFound() == 1L)
                apiRequest.setOriginal(o);
              apiRequest.setId(Optional.ofNullable(listRequestApproval.first()).map(o2 -> o2.getApprovalId().toString()).orElse(null));
              apiRequest.setSolrId(Optional.ofNullable(listRequestApproval.first()).map(o2 -> o2.getSolrId()).orElse(null));
              deletefilterRequestApprovalFuture(o).onSuccess(o2 -> {
                eventHandler.handle(Future.succeededFuture(ServiceResponse.completedWithJson(Buffer.buffer(new JsonObject().encodePrettily()))));
              }).onFailure(ex -> {
                eventHandler.handle(Future.failedFuture(ex));
              });
            } else {
              eventHandler.handle(Future.succeededFuture(ServiceResponse.completedWithJson(Buffer.buffer(new JsonObject().encodePrettily()))));
            }
          } catch(Exception ex) {
            LOG.error(String.format("deletefilterRequestApproval failed. "), ex);
            error(siteRequest, eventHandler, ex);
          }
        }).onFailure(ex -> {
          LOG.error(String.format("deletefilterRequestApproval failed. "), ex);
          error(siteRequest, eventHandler, ex);
        });
      } catch(Exception ex) {
        LOG.error(String.format("deletefilterRequestApproval failed. "), ex);
        error(null, eventHandler, ex);
      }
    }).onFailure(ex -> {
      LOG.error(String.format("deletefilterRequestApproval failed. "), ex);
      error(null, eventHandler, ex);
    });
  }

  public Future<RequestApproval> deletefilterRequestApprovalFuture(RequestApproval o) {
    SiteRequest siteRequest = o.getSiteRequest_();
    Promise<RequestApproval> promise = Promise.promise();

    try {
      ApiRequest apiRequest = siteRequest.getApiRequest_();
      Promise<RequestApproval> promise1 = Promise.promise();
      pgPool.withTransaction(sqlConnection -> {
        siteRequest.setSqlConnection(sqlConnection);
        varsRequestApproval(siteRequest).onSuccess(a -> {
          sqlDELETEFilterRequestApproval(o).onSuccess(requestApproval -> {
            relateRequestApproval(o).onSuccess(d -> {
              unindexRequestApproval(o).onSuccess(o2 -> {
                if(apiRequest != null) {
                  apiRequest.setNumPATCH(apiRequest.getNumPATCH() + 1);
                  if(apiRequest.getNumFound() == 1L && Optional.ofNullable(siteRequest.getJsonObject()).map(json -> json.size() > 0).orElse(false)) {
                    o2.apiRequestRequestApproval();
                    if(apiRequest.getVars().size() > 0 && Optional.ofNullable(siteRequest.getRequestVars().get("refresh")).map(refresh -> !refresh.equals("false")).orElse(true))
                      eventBus.publish("websocketRequestApproval", JsonObject.mapFrom(apiRequest).toString());
                  }
                }
                promise1.complete();
              }).onFailure(ex -> {
                promise1.tryFail(ex);
              });
            }).onFailure(ex -> {
              promise1.tryFail(ex);
            });
          }).onFailure(ex -> {
            promise1.tryFail(ex);
          });
        }).onFailure(ex -> {
          promise1.tryFail(ex);
        });
        return promise1.future();
      }).onSuccess(a -> {
        siteRequest.setSqlConnection(null);
      }).onFailure(ex -> {
        siteRequest.setSqlConnection(null);
        promise.tryFail(ex);
      }).compose(requestApproval -> {
        Promise<RequestApproval> promise2 = Promise.promise();
        refreshRequestApproval(o).onSuccess(a -> {
          promise2.complete(o);
        }).onFailure(ex -> {
          promise2.tryFail(ex);
        });
        return promise2.future();
      }).onSuccess(requestApproval -> {
        promise.complete(requestApproval);
      }).onFailure(ex -> {
        promise.tryFail(ex);
      });
    } catch(Exception ex) {
      LOG.error(String.format("deletefilterRequestApprovalFuture failed. "), ex);
      promise.tryFail(ex);
    }
    return promise.future();
  }

  public Future<Void> sqlDELETEFilterRequestApproval(RequestApproval o) {
    Promise<Void> promise = Promise.promise();
    try {
      SiteRequest siteRequest = o.getSiteRequest_();
      ApiRequest apiRequest = siteRequest.getApiRequest_();
      List<String> solrIds = Optional.ofNullable(apiRequest).map(r -> r.getSolrIds()).orElse(new ArrayList<>());
      List<String> classes = Optional.ofNullable(apiRequest).map(r -> r.getClasses()).orElse(new ArrayList<>());
      SqlConnection sqlConnection = siteRequest.getSqlConnection();
      Integer num = 1;
      StringBuilder bSql = new StringBuilder("DELETE FROM RequestApproval ");
      List<Object> bParams = new ArrayList<Object>();
      Long pk = o.getPk();
      JsonObject jsonObject = siteRequest.getJsonObject();
      RequestApproval o2 = new RequestApproval();
      o2.setSiteRequest_(siteRequest);
      List<Future> futures1 = new ArrayList<>();
      List<Future> futures2 = new ArrayList<>();

      if(jsonObject != null) {
        Set<String> entityVars = jsonObject.fieldNames();
        for(String entityVar : entityVars) {
          switch(entityVar) {
          }
        }
      }
      bSql.append(" WHERE pk=$" + num);
      bParams.add(pk);
      num++;
      futures2.add(0, Future.future(a -> {
        sqlConnection.preparedQuery(bSql.toString())
            .execute(Tuple.tuple(bParams)
            ).onSuccess(b -> {
          a.handle(Future.succeededFuture());
        }).onFailure(ex -> {
          RuntimeException ex2 = new RuntimeException("value RequestApproval failed", ex);
          LOG.error(String.format("unrelateRequestApproval failed. "), ex2);
          a.handle(Future.failedFuture(ex2));
        });
      }));
      CompositeFuture.all(futures1).onSuccess(a -> {
        CompositeFuture.all(futures2).onSuccess(b -> {
          promise.complete();
        }).onFailure(ex -> {
          LOG.error(String.format("sqlDELETEFilterRequestApproval failed. "), ex);
          promise.tryFail(ex);
        });
      }).onFailure(ex -> {
        LOG.error(String.format("sqlDELETEFilterRequestApproval failed. "), ex);
        promise.tryFail(ex);
      });
    } catch(Exception ex) {
      LOG.error(String.format("sqlDELETEFilterRequestApproval failed. "), ex);
      promise.tryFail(ex);
    }
    return promise.future();
  }

  public Future<ServiceResponse> response200DELETEFilterRequestApproval(SiteRequest siteRequest) {
    Promise<ServiceResponse> promise = Promise.promise();
    try {
      JsonObject json = new JsonObject();
      promise.complete(ServiceResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily())));
    } catch(Exception ex) {
      LOG.error(String.format("response200DELETEFilterRequestApproval failed. "), ex);
      promise.tryFail(ex);
    }
    return promise.future();
  }

  // General //

  public Future<RequestApproval> createRequestApproval(SiteRequest siteRequest) {
    Promise<RequestApproval> promise = Promise.promise();
    try {
      SqlConnection sqlConnection = siteRequest.getSqlConnection();
      String userId = siteRequest.getUserId();
      Long userKey = siteRequest.getUserKey();
      ZonedDateTime created = Optional.ofNullable(siteRequest.getJsonObject()).map(j -> j.getString("created")).map(s -> ZonedDateTime.parse(s, ComputateZonedDateTimeSerializer.ZONED_DATE_TIME_FORMATTER.withZone(ZoneId.of(config.getString(ConfigKeys.SITE_ZONE))))).orElse(ZonedDateTime.now(ZoneId.of(config.getString(ConfigKeys.SITE_ZONE))));

      sqlConnection.preparedQuery("INSERT INTO RequestApproval(created, userKey) VALUES($1, $2) RETURNING pk")
          .collecting(Collectors.toList())
          .execute(Tuple.of(created.toOffsetDateTime(), userKey)).onSuccess(result -> {
        Row createLine = result.value().stream().findFirst().orElseGet(() -> null);
        Long pk = createLine.getLong(0);
        RequestApproval o = new RequestApproval();
        o.setPk(pk);
        o.setSiteRequest_(siteRequest);
        promise.complete(o);
      }).onFailure(ex -> {
        RuntimeException ex2 = new RuntimeException(ex);
        LOG.error("createRequestApproval failed. ", ex2);
        promise.tryFail(ex2);
      });
    } catch(Exception ex) {
      LOG.error(String.format("createRequestApproval failed. "), ex);
      promise.tryFail(ex);
    }
    return promise.future();
  }

  public void searchRequestApprovalQ(SearchList<RequestApproval> searchList, String entityVar, String valueIndexed, String varIndexed) {
    searchList.q(varIndexed + ":" + ("*".equals(valueIndexed) ? valueIndexed : SearchTool.escapeQueryChars(valueIndexed)));
    if(!"*".equals(entityVar)) {
    }
  }

  public String searchRequestApprovalFq(SearchList<RequestApproval> searchList, String entityVar, String valueIndexed, String varIndexed) {
    if(varIndexed == null)
      throw new RuntimeException(String.format("\"%s\" is not an indexed entity. ", entityVar));
    if(StringUtils.startsWith(valueIndexed, "[")) {
      String[] fqs = StringUtils.substringAfter(StringUtils.substringBeforeLast(valueIndexed, "]"), "[").split(" TO ");
      if(fqs.length != 2)
        throw new RuntimeException(String.format("\"%s\" invalid range query. ", valueIndexed));
      String fq1 = fqs[0].equals("*") ? fqs[0] : RequestApproval.staticSearchFqForClass(entityVar, searchList.getSiteRequest_(SiteRequest.class), fqs[0]);
      String fq2 = fqs[1].equals("*") ? fqs[1] : RequestApproval.staticSearchFqForClass(entityVar, searchList.getSiteRequest_(SiteRequest.class), fqs[1]);
       return varIndexed + ":[" + fq1 + " TO " + fq2 + "]";
    } else {
      return varIndexed + ":" + SearchTool.escapeQueryChars(RequestApproval.staticSearchFqForClass(entityVar, searchList.getSiteRequest_(SiteRequest.class), valueIndexed)).replace("\\", "\\\\");
    }
  }

  public void searchRequestApprovalSort(SearchList<RequestApproval> searchList, String entityVar, String valueIndexed, String varIndexed) {
    if(varIndexed == null)
      throw new RuntimeException(String.format("\"%s\" is not an indexed entity. ", entityVar));
    searchList.sort(varIndexed, valueIndexed);
  }

  public void searchRequestApprovalRows(SearchList<RequestApproval> searchList, Long valueRows) {
      searchList.rows(valueRows != null ? valueRows : 10L);
  }

  public void searchRequestApprovalStart(SearchList<RequestApproval> searchList, Long valueStart) {
    searchList.start(valueStart);
  }

  public void searchRequestApprovalVar(SearchList<RequestApproval> searchList, String var, String value) {
    searchList.getSiteRequest_(SiteRequest.class).getRequestVars().put(var, value);
  }

  public void searchRequestApprovalUri(SearchList<RequestApproval> searchList) {
  }

  public Future<ServiceResponse> varsRequestApproval(SiteRequest siteRequest) {
    Promise<ServiceResponse> promise = Promise.promise();
    try {
      ServiceRequest serviceRequest = siteRequest.getServiceRequest();

      serviceRequest.getParams().getJsonObject("query").stream().filter(paramRequest -> "var".equals(paramRequest.getKey()) && paramRequest.getValue() != null).findFirst().ifPresent(paramRequest -> {
        String entityVar = null;
        String valueIndexed = null;
        Object paramValuesObject = paramRequest.getValue();
        JsonArray paramObjects = paramValuesObject instanceof JsonArray ? (JsonArray)paramValuesObject : new JsonArray().add(paramValuesObject);

        try {
          for(Object paramObject : paramObjects) {
            entityVar = StringUtils.trim(StringUtils.substringBefore((String)paramObject, ":"));
            valueIndexed = URLDecoder.decode(StringUtils.trim(StringUtils.substringAfter((String)paramObject, ":")), "UTF-8");
            siteRequest.getRequestVars().put(entityVar, valueIndexed);
          }
        } catch(Exception ex) {
          LOG.error(String.format("searchRequestApproval failed. "), ex);
          promise.tryFail(ex);
        }
      });
      promise.complete();
    } catch(Exception ex) {
      LOG.error(String.format("searchRequestApproval failed. "), ex);
      promise.tryFail(ex);
    }
    return promise.future();
  }

  public Future<SearchList<RequestApproval>> searchRequestApprovalList(SiteRequest siteRequest, Boolean populate, Boolean store, Boolean modify, String scope) {
    Promise<SearchList<RequestApproval>> promise = Promise.promise();
    try {
      ServiceRequest serviceRequest = siteRequest.getServiceRequest();
      String entityListStr = siteRequest.getServiceRequest().getParams().getJsonObject("query").getString("fl");
      String[] entityList = entityListStr == null ? null : entityListStr.split(",\\s*");
      SearchList<RequestApproval> searchList = new SearchList<RequestApproval>();
      searchList.setScope(scope);
      String facetRange = null;
      Date facetRangeStart = null;
      Date facetRangeEnd = null;
      String facetRangeGap = null;
      String statsField = null;
      String statsFieldIndexed = null;
      searchList.setPopulate(populate);
      searchList.setStore(store);
      searchList.q("*:*");
      searchList.setC(RequestApproval.class);
      searchList.setSiteRequest_(siteRequest);
      searchList.facetMinCount(1);
      if(entityList != null) {
        for(String v : entityList) {
          searchList.fl(RequestApproval.varIndexedRequestApproval(v));
        }
      }

      String approvalId = serviceRequest.getParams().getJsonObject("path").getString("approvalId");
      if(approvalId != null) {
        searchList.fq("approvalId_docvalues_string:" + SearchTool.escapeQueryChars(approvalId));
      }

      for(String paramName : serviceRequest.getParams().getJsonObject("query").fieldNames()) {
        Object paramValuesObject = serviceRequest.getParams().getJsonObject("query").getValue(paramName);
        String entityVar = null;
        String valueIndexed = null;
        String varIndexed = null;
        String valueSort = null;
        Long valueStart = null;
        Long valueRows = null;
        String valueCursorMark = null;
        JsonArray paramObjects = paramValuesObject instanceof JsonArray ? (JsonArray)paramValuesObject : new JsonArray().add(paramValuesObject);

        try {
          if(paramValuesObject != null && "facet.pivot".equals(paramName)) {
            Matcher mFacetPivot = Pattern.compile("(?:(\\{![^\\}]+\\}))?(.*)").matcher(StringUtils.join(paramObjects.getList().toArray(), ","));
            if(mFacetPivot.find()) {
              String solrLocalParams = mFacetPivot.group(1);
              String[] entityVars = mFacetPivot.group(2).trim().split(",");
              String[] varsIndexed = new String[entityVars.length];
              for(Integer i = 0; i < entityVars.length; i++) {
                entityVar = entityVars[i];
                varsIndexed[i] = RequestApproval.varIndexedRequestApproval(entityVar);
              }
              searchList.facetPivot((solrLocalParams == null ? "" : solrLocalParams) + StringUtils.join(varsIndexed, ","));
            }
          } else if(paramValuesObject != null) {
            for(Object paramObject : paramObjects) {
              if(paramName.equals("q")) {
                Matcher mQ = Pattern.compile("(\\w+):(.+?(?=(\\)|\\s+OR\\s+|\\s+AND\\s+|\\^|$)))").matcher((String)paramObject);
                StringBuffer sb = new StringBuffer();
                while(mQ.find()) {
                  entityVar = mQ.group(1).trim();
                  valueIndexed = mQ.group(2).trim();
                  varIndexed = RequestApproval.varIndexedRequestApproval(entityVar);
                  String entityQ = searchRequestApprovalFq(searchList, entityVar, valueIndexed, varIndexed);
                  mQ.appendReplacement(sb, entityQ);
                }
                if(!sb.isEmpty()) {
                  mQ.appendTail(sb);
                  searchList.q(sb.toString());
                }
              } else if(paramName.equals("fq")) {
                Matcher mFq = Pattern.compile("(\\w+):(.+?(?=(\\)|\\s+OR\\s+|\\s+AND\\s+|$)))").matcher((String)paramObject);
                  StringBuffer sb = new StringBuffer();
                while(mFq.find()) {
                  entityVar = mFq.group(1).trim();
                  valueIndexed = mFq.group(2).trim();
                  varIndexed = RequestApproval.varIndexedRequestApproval(entityVar);
                  String entityFq = searchRequestApprovalFq(searchList, entityVar, valueIndexed, varIndexed);
                  mFq.appendReplacement(sb, entityFq);
                }
                if(!sb.isEmpty()) {
                  mFq.appendTail(sb);
                  searchList.fq(sb.toString());
                }
              } else if(paramName.equals("sort")) {
                entityVar = StringUtils.trim(StringUtils.substringBefore((String)paramObject, " "));
                valueIndexed = StringUtils.trim(StringUtils.substringAfter((String)paramObject, " "));
                varIndexed = RequestApproval.varIndexedRequestApproval(entityVar);
                searchRequestApprovalSort(searchList, entityVar, valueIndexed, varIndexed);
              } else if(paramName.equals("start")) {
                valueStart = paramObject instanceof Long ? (Long)paramObject : Long.parseLong(paramObject.toString());
                searchRequestApprovalStart(searchList, valueStart);
              } else if(paramName.equals("rows")) {
                valueRows = paramObject instanceof Long ? (Long)paramObject : Long.parseLong(paramObject.toString());
                searchRequestApprovalRows(searchList, valueRows);
              } else if(paramName.equals("stats")) {
                searchList.stats((Boolean)paramObject);
              } else if(paramName.equals("stats.field")) {
                Matcher mStats = Pattern.compile("(?:(\\{![^\\}]+\\}))?(.*)").matcher((String)paramObject);
                if(mStats.find()) {
                  String solrLocalParams = mStats.group(1);
                  entityVar = mStats.group(2).trim();
                  varIndexed = RequestApproval.varIndexedRequestApproval(entityVar);
                  searchList.statsField((solrLocalParams == null ? "" : solrLocalParams) + varIndexed);
                  statsField = entityVar;
                  statsFieldIndexed = varIndexed;
                }
              } else if(paramName.equals("facet")) {
                searchList.facet((Boolean)paramObject);
              } else if(paramName.equals("facet.range.start")) {
                String startMathStr = (String)paramObject;
                Date start = SearchTool.parseMath(startMathStr);
                searchList.facetRangeStart(start.toInstant().toString());
                facetRangeStart = start;
              } else if(paramName.equals("facet.range.end")) {
                String endMathStr = (String)paramObject;
                Date end = SearchTool.parseMath(endMathStr);
                searchList.facetRangeEnd(end.toInstant().toString());
                facetRangeEnd = end;
              } else if(paramName.equals("facet.range.gap")) {
                String gap = (String)paramObject;
                searchList.facetRangeGap(gap);
                facetRangeGap = gap;
              } else if(paramName.equals("facet.range")) {
                Matcher mFacetRange = Pattern.compile("(?:(\\{![^\\}]+\\}))?(.*)").matcher((String)paramObject);
                if(mFacetRange.find()) {
                  String solrLocalParams = mFacetRange.group(1);
                  entityVar = mFacetRange.group(2).trim();
                  varIndexed = RequestApproval.varIndexedRequestApproval(entityVar);
                  searchList.facetRange((solrLocalParams == null ? "" : solrLocalParams) + varIndexed);
                  facetRange = entityVar;
                }
              } else if(paramName.equals("facet.field")) {
                entityVar = (String)paramObject;
                varIndexed = RequestApproval.varIndexedRequestApproval(entityVar);
                if(varIndexed != null)
                  searchList.facetField(varIndexed);
              } else if(paramName.equals("var")) {
                entityVar = StringUtils.trim(StringUtils.substringBefore((String)paramObject, ":"));
                valueIndexed = URLDecoder.decode(StringUtils.trim(StringUtils.substringAfter((String)paramObject, ":")), "UTF-8");
                searchRequestApprovalVar(searchList, entityVar, valueIndexed);
              } else if(paramName.equals("cursorMark")) {
                valueCursorMark = (String)paramObject;
                searchList.cursorMark((String)paramObject);
              }
            }
            searchRequestApprovalUri(searchList);
          }
        } catch(Exception e) {
          ExceptionUtils.rethrow(e);
        }
      }
      if("*:*".equals(searchList.getQuery()) && searchList.getSorts().size() == 0) {
        searchList.sort("created_docvalues_date", "desc");
      }
      String facetRange2 = facetRange;
      Date facetRangeStart2 = facetRangeStart;
      Date facetRangeEnd2 = facetRangeEnd;
      String facetRangeGap2 = facetRangeGap;
      String statsField2 = statsField;
      String statsFieldIndexed2 = statsFieldIndexed;
      searchRequestApproval2(siteRequest, populate, store, modify, searchList);
      searchList.promiseDeepForClass(siteRequest).onSuccess(searchList2 -> {
        if(facetRange2 != null && statsField2 != null && facetRange2.equals(statsField2)) {
          StatsField stats = searchList.getResponse().getStats().getStatsFields().get(statsFieldIndexed2);
          Instant min = Optional.ofNullable(stats.getMin()).map(val -> Instant.parse(val.toString())).orElse(Instant.now());
          Instant max = Optional.ofNullable(stats.getMax()).map(val -> Instant.parse(val.toString())).orElse(Instant.now());
          if(min.equals(max)) {
            min = min.minus(1, ChronoUnit.DAYS);
            max = max.plus(2, ChronoUnit.DAYS);
          }
          Duration duration = Duration.between(min, max);
          String gap = "HOUR";
          if(duration.toDays() >= 365)
            gap = "YEAR";
          else if(duration.toDays() >= 28)
            gap = "MONTH";
          else if(duration.toDays() >= 1)
            gap = "DAY";
          else if(duration.toHours() >= 1)
            gap = "HOUR";
          else if(duration.toMinutes() >= 1)
            gap = "MINUTE";
          else if(duration.toMillis() >= 1000)
            gap = "SECOND";
          else if(duration.toMillis() >= 1)
            gap = "MILLI";

          if(facetRangeStart2 == null)
            searchList.facetRangeStart(min.toString());
          if(facetRangeEnd2 == null)
            searchList.facetRangeEnd(max.toString());
          if(facetRangeGap2 == null)
            searchList.facetRangeGap(String.format("+1%s", gap));
          searchList.query().onSuccess(b -> {
            promise.complete(searchList);
          }).onFailure(ex -> {
            LOG.error(String.format("searchRequestApproval failed. "), ex);
            promise.tryFail(ex);
          });
        } else {
          promise.complete(searchList);
        }
      }).onFailure(ex -> {
        LOG.error(String.format("searchRequestApproval failed. "), ex);
        promise.tryFail(ex);
      });
    } catch(Exception ex) {
      LOG.error(String.format("searchRequestApproval failed. "), ex);
      promise.tryFail(ex);
    }
    return promise.future();
  }
  public void searchRequestApproval2(SiteRequest siteRequest, Boolean populate, Boolean store, Boolean modify, SearchList<RequestApproval> searchList) {
  }

  public Future<JsonObject> upsertRequestApproval(RequestApproval o, Boolean inheritPrimaryKey, Boolean patch) {
    Promise<JsonObject> promise = Promise.promise();
    try {
      SiteRequest siteRequest = o.getSiteRequest_();
      ServiceRequest serviceRequest = siteRequest.getServiceRequest();
      if(Optional.ofNullable(serviceRequest.getParams()).map(p -> p.getJsonObject("query")).map( q -> q.getJsonArray("var")).orElse(new JsonArray()).stream().filter(s -> "refresh:false".equals(s)).count() > 0L) {
        promise.complete();
      } else {
        JsonObject json = o.getSiteRequest_().getJsonObject();

        String old_approvalName = RequestApproval.staticJsonApprovalName(o.getApprovalName());
        String new_approvalName = json.getString(RequestApproval.varJson(RequestApproval.VAR_approvalName, patch));
        String approvalName = Optional.ofNullable(Optional.ofNullable(new_approvalName).orElse(old_approvalName)).orElse(null);
        // json.put(RequestApproval.varJson(RequestApproval.VAR_approvalName, patch), approvalName);

        String old_approvalId = RequestApproval.staticJsonApprovalId(o.getApprovalId());
        String new_approvalId = json.getString(RequestApproval.varJson(RequestApproval.VAR_approvalId, patch));
        String approvalId = Optional.ofNullable(Optional.ofNullable(new_approvalId).orElse(old_approvalId)).orElse(null);
        // json.put(RequestApproval.varJson(RequestApproval.VAR_approvalId, patch), approvalId);

        String old_created = RequestApproval.staticJsonCreated(o.getCreated());
        String new_created = json.getString(RequestApproval.varJson(RequestApproval.VAR_created, patch));
        String created = Optional.ofNullable(Optional.ofNullable(new_created).orElse(old_created)).orElse(null);
        // json.put(RequestApproval.varJson(RequestApproval.VAR_created, patch), created);

        String old_approvedByEmail = RequestApproval.staticJsonApprovedByEmail(o.getApprovedByEmail());
        String new_approvedByEmail = json.getString(RequestApproval.varJson(RequestApproval.VAR_approvedByEmail, patch));
        String approvedByEmail = siteRequest.getUserEmail();
        json.put(RequestApproval.varJson(RequestApproval.VAR_approvedByEmail, patch), approvedByEmail);

        String old_approvedByUserId = RequestApproval.staticJsonApprovedByUserId(o.getApprovedByUserId());
        String new_approvedByUserId = json.getString(RequestApproval.varJson(RequestApproval.VAR_approvedByUserId, patch));
        String approvedByUserId = siteRequest.getUserId();
        json.put(RequestApproval.varJson(RequestApproval.VAR_approvedByUserId, patch), approvedByUserId);

        Boolean old_archived = RequestApproval.staticJsonArchived(o.getArchived());
        Boolean new_archived = json.getBoolean(RequestApproval.varJson(RequestApproval.VAR_archived, patch));
        Boolean archived = Optional.ofNullable(Optional.ofNullable(new_archived).orElse(old_archived)).orElse(null);
        // json.put(RequestApproval.varJson(RequestApproval.VAR_archived, patch), archived);

        String old_approvedByFullName = RequestApproval.staticJsonApprovedByFullName(o.getApprovedByFullName());
        String new_approvedByFullName = json.getString(RequestApproval.varJson(RequestApproval.VAR_approvedByFullName, patch));
        String approvedByFullName = siteRequest.getUserFullName();
        json.put(RequestApproval.varJson(RequestApproval.VAR_approvedByFullName, patch), approvedByFullName);

        String old_approvalNote = RequestApproval.staticJsonApprovalNote(o.getApprovalNote());
        String new_approvalNote = json.getString(RequestApproval.varJson(RequestApproval.VAR_approvalNote, patch));
        String approvalNote = Optional.ofNullable(Optional.ofNullable(new_approvalNote).orElse(old_approvalNote)).orElse(null);
        // json.put(RequestApproval.varJson(RequestApproval.VAR_approvalNote, patch), approvalNote);

        Boolean old_approved = RequestApproval.staticJsonApproved(o.getApproved());
        Boolean new_approved = json.getBoolean(RequestApproval.varJson(RequestApproval.VAR_approved, patch));
        Boolean approved = Optional.ofNullable(Optional.ofNullable(new_approved).orElse(old_approved)).orElse(null);
        // json.put(RequestApproval.varJson(RequestApproval.VAR_approved, patch), approved);

        String old_modelType = RequestApproval.staticJsonModelType(o.getModelType());
        String new_modelType = json.getString(RequestApproval.varJson(RequestApproval.VAR_modelType, patch));
        String modelType = Optional.ofNullable(Optional.ofNullable(new_modelType).orElse(old_modelType)).orElse(null);
        // json.put(RequestApproval.varJson(RequestApproval.VAR_modelType, patch), modelType);

        String old_sessionId = RequestApproval.staticJsonSessionId(o.getSessionId());
        String new_sessionId = json.getString(RequestApproval.varJson(RequestApproval.VAR_sessionId, patch));
        String sessionId = Optional.ofNullable(Optional.ofNullable(new_sessionId).orElse(old_sessionId)).orElse(null);
        // json.put(RequestApproval.varJson(RequestApproval.VAR_sessionId, patch), sessionId);

        String old_modelResource = RequestApproval.staticJsonModelResource(o.getModelResource());
        String new_modelResource = json.getString(RequestApproval.varJson(RequestApproval.VAR_modelResource, patch));
        String modelResource = Optional.ofNullable(Optional.ofNullable(new_modelResource).orElse(old_modelResource)).orElse(null);
        // json.put(RequestApproval.varJson(RequestApproval.VAR_modelResource, patch), modelResource);

        String old_userKey = RequestApproval.staticJsonUserKey(o.getUserKey());
        String new_userKey = json.getString(RequestApproval.varJson(RequestApproval.VAR_userKey, patch));
        String userKey = Optional.ofNullable(Optional.ofNullable(new_userKey).orElse(old_userKey)).orElse(null);
        // json.put(RequestApproval.varJson(RequestApproval.VAR_userKey, patch), userKey);

        String old_approvalTitle = RequestApproval.staticJsonApprovalTitle(o.getApprovalTitle());
        String new_approvalTitle = json.getString(RequestApproval.varJson(RequestApproval.VAR_approvalTitle, patch));
        String approvalTitle = Optional.ofNullable(Optional.ofNullable(new_approvalTitle).orElse(old_approvalTitle)).orElse(null);
        // json.put(RequestApproval.varJson(RequestApproval.VAR_approvalTitle, patch), approvalTitle);

        String old_objectTitle = RequestApproval.staticJsonObjectTitle(o.getObjectTitle());
        String new_objectTitle = json.getString(RequestApproval.varJson(RequestApproval.VAR_objectTitle, patch));
        String objectTitle = Optional.ofNullable(Optional.ofNullable(new_objectTitle).orElse(old_objectTitle)).orElse(null);
        // json.put(RequestApproval.varJson(RequestApproval.VAR_objectTitle, patch), objectTitle);

        String old_displayPage = RequestApproval.staticJsonDisplayPage(o.getDisplayPage());
        String new_displayPage = json.getString(RequestApproval.varJson(RequestApproval.VAR_displayPage, patch));
        String displayPage = Optional.ofNullable(Optional.ofNullable(new_displayPage).orElse(old_displayPage)).orElse(null);
        // json.put(RequestApproval.varJson(RequestApproval.VAR_displayPage, patch), displayPage);

        String old_editPage = RequestApproval.staticJsonEditPage(o.getEditPage());
        String new_editPage = json.getString(RequestApproval.varJson(RequestApproval.VAR_editPage, patch));
        String editPage = Optional.ofNullable(Optional.ofNullable(new_editPage).orElse(old_editPage)).orElse(null);
        // json.put(RequestApproval.varJson(RequestApproval.VAR_editPage, patch), editPage);

        String old_userPage = RequestApproval.staticJsonUserPage(o.getUserPage());
        String new_userPage = json.getString(RequestApproval.varJson(RequestApproval.VAR_userPage, patch));
        String userPage = Optional.ofNullable(Optional.ofNullable(new_userPage).orElse(old_userPage)).orElse(null);
        // json.put(RequestApproval.varJson(RequestApproval.VAR_userPage, patch), userPage);

        String old_download = RequestApproval.staticJsonDownload(o.getDownload());
        String new_download = json.getString(RequestApproval.varJson(RequestApproval.VAR_download, patch));
        String download = Optional.ofNullable(Optional.ofNullable(new_download).orElse(old_download)).orElse(null);
        // json.put(RequestApproval.varJson(RequestApproval.VAR_download, patch), download);

          promise.complete(json);
      }
    } catch(Exception ex) {
      LOG.error(String.format("upsertRequestApproval failed. "), ex);
      promise.tryFail(ex);
    }
    return promise.future();
  }

  public Future<Void> persistRequestApproval(RequestApproval o, Boolean patch) {
    Promise<Void> promise = Promise.promise();
    try {
      SiteRequest siteRequest = o.getSiteRequest_();
      SqlConnection sqlConnection = siteRequest.getSqlConnection();
      Long pk = o.getPk();
      sqlConnection.preparedQuery("SELECT approvalName, approvalId, created, approvedByEmail, approvedByUserId, archived, approvedByFullName, approvalNote, approved, modelType, sessionId, modelResource, userKey, approvalTitle, objectTitle, displayPage, editPage, userPage, download FROM RequestApproval WHERE pk=$1")
          .collecting(Collectors.toList())
          .execute(Tuple.of(pk)
          ).onSuccess(result -> {
        try {
          for(Row definition : result.value()) {
            for(Integer i = 0; i < definition.size(); i++) {
              String columnName = definition.getColumnName(i);
              Object columnValue = definition.getValue(i);
              if(!"pk".equals(columnName)) {
                try {
                  o.persistForClass(columnName, columnValue);
                } catch(Exception e) {
                  LOG.error(String.format("persistRequestApproval failed. "), e);
                }
              }
            }
          }
          o.promiseDeepForClass(siteRequest).onSuccess(a -> {
            promise.complete();
          }).onFailure(ex -> {
            LOG.error(String.format("persistRequestApproval failed. "), ex);
            promise.tryFail(ex);
          });
        } catch(Exception ex) {
          LOG.error(String.format("persistRequestApproval failed. "), ex);
          promise.tryFail(ex);
        }
      }).onFailure(ex -> {
        RuntimeException ex2 = new RuntimeException(ex);
        LOG.error(String.format("persistRequestApproval failed. "), ex2);
        promise.tryFail(ex2);
      });
    } catch(Exception ex) {
      LOG.error(String.format("persistRequestApproval failed. "), ex);
      promise.tryFail(ex);
    }
    return promise.future();
  }

  public Future<Void> relateRequestApproval(RequestApproval o) {
    Promise<Void> promise = Promise.promise();
    promise.complete();
    return promise.future();
  }

  public String searchVar(String varIndexed) {
    return RequestApproval.searchVarRequestApproval(varIndexed);
  }

  @Override
  public String getClassApiAddress() {
    return RequestApproval.CLASS_API_ADDRESS_RequestApproval;
  }

  public Future<RequestApproval> indexRequestApproval(RequestApproval o) {
    Promise<RequestApproval> promise = Promise.promise();
    try {
      SiteRequest siteRequest = o.getSiteRequest_();
      ApiRequest apiRequest = siteRequest.getApiRequest_();
      JsonObject json = new JsonObject();
      JsonObject add = new JsonObject();
      json.put("add", add);
      JsonObject doc = new JsonObject();
      add.put("doc", doc);
      o.indexRequestApproval(doc);
      String solrUsername = siteRequest.getConfig().getString(ConfigKeys.SOLR_USERNAME);
      String solrPassword = siteRequest.getConfig().getString(ConfigKeys.SOLR_PASSWORD);
      String solrHostName = siteRequest.getConfig().getString(ConfigKeys.SOLR_HOST_NAME);
      Integer solrPort = Integer.parseInt(siteRequest.getConfig().getString(ConfigKeys.SOLR_PORT));
      String solrCollection = siteRequest.getConfig().getString(ConfigKeys.SOLR_COLLECTION);
      Boolean solrSsl = Boolean.parseBoolean(siteRequest.getConfig().getString(ConfigKeys.SOLR_SSL));
      Boolean softCommit = Optional.ofNullable(siteRequest.getServiceRequest().getParams()).map(p -> p.getJsonObject("query")).map( q -> q.getBoolean("softCommit")).orElse(null);
      Integer commitWithin = Optional.ofNullable(siteRequest.getServiceRequest().getParams()).map(p -> p.getJsonObject("query")).map( q -> q.getInteger("commitWithin")).orElse(null);
        if(softCommit == null && commitWithin == null)
          softCommit = true;
        else if(softCommit == null)
          softCommit = false;
      String solrRequestUri = String.format("/solr/%s/update%s%s%s", solrCollection, "?overwrite=true&wt=json", softCommit ? "&softCommit=true" : "", commitWithin != null ? ("&commitWithin=" + commitWithin) : "");
      webClient.post(solrPort, solrHostName, solrRequestUri).ssl(solrSsl).authentication(new UsernamePasswordCredentials(solrUsername, solrPassword)).putHeader("Content-Type", "application/json").sendBuffer(json.toBuffer()).expecting(HttpResponseExpectation.SC_OK).onSuccess(b -> {
        promise.complete(o);
      }).onFailure(ex -> {
        LOG.error(String.format("indexRequestApproval failed. "), new RuntimeException(ex));
        promise.tryFail(ex);
      });
    } catch(Exception ex) {
      LOG.error(String.format("indexRequestApproval failed. "), ex);
      promise.tryFail(ex);
    }
    return promise.future();
  }

  public Future<RequestApproval> unindexRequestApproval(RequestApproval o) {
    Promise<RequestApproval> promise = Promise.promise();
    try {
      SiteRequest siteRequest = o.getSiteRequest_();
      ApiRequest apiRequest = siteRequest.getApiRequest_();
      o.promiseDeepForClass(siteRequest).onSuccess(a -> {
        JsonObject json = new JsonObject();
        JsonObject delete = new JsonObject();
        json.put("delete", delete);
        String query = String.format("filter(%s:%s)", RequestApproval.VAR_solrId, o.obtainForClass(RequestApproval.VAR_solrId));
        delete.put("query", query);
        String solrUsername = siteRequest.getConfig().getString(ConfigKeys.SOLR_USERNAME);
        String solrPassword = siteRequest.getConfig().getString(ConfigKeys.SOLR_PASSWORD);
        String solrHostName = siteRequest.getConfig().getString(ConfigKeys.SOLR_HOST_NAME);
        Integer solrPort = Integer.parseInt(siteRequest.getConfig().getString(ConfigKeys.SOLR_PORT));
        String solrCollection = siteRequest.getConfig().getString(ConfigKeys.SOLR_COLLECTION);
        Boolean solrSsl = Boolean.parseBoolean(siteRequest.getConfig().getString(ConfigKeys.SOLR_SSL));
        Boolean softCommit = Optional.ofNullable(siteRequest.getServiceRequest().getParams()).map(p -> p.getJsonObject("query")).map( q -> q.getBoolean("softCommit")).orElse(null);
        Integer commitWithin = Optional.ofNullable(siteRequest.getServiceRequest().getParams()).map(p -> p.getJsonObject("query")).map( q -> q.getInteger("commitWithin")).orElse(null);
          if(softCommit == null && commitWithin == null)
            softCommit = true;
          else if(softCommit == null)
            softCommit = false;
        String solrRequestUri = String.format("/solr/%s/update%s%s%s", solrCollection, "?overwrite=true&wt=json", softCommit ? "&softCommit=true" : "", commitWithin != null ? ("&commitWithin=" + commitWithin) : "");
        webClient.post(solrPort, solrHostName, solrRequestUri).ssl(solrSsl).authentication(new UsernamePasswordCredentials(solrUsername, solrPassword)).putHeader("Content-Type", "application/json").sendBuffer(json.toBuffer()).expecting(HttpResponseExpectation.SC_OK).onSuccess(b -> {
          promise.complete(o);
        }).onFailure(ex -> {
          LOG.error(String.format("unindexRequestApproval failed. "), new RuntimeException(ex));
          promise.tryFail(ex);
        });
      }).onFailure(ex -> {
        LOG.error(String.format("unindexRequestApproval failed. "), ex);
        promise.tryFail(ex);
      });
    } catch(Exception ex) {
      LOG.error(String.format("unindexRequestApproval failed. "), ex);
      promise.tryFail(ex);
    }
    return promise.future();
  }

  public Future<Void> refreshRequestApproval(RequestApproval o) {
    Promise<Void> promise = Promise.promise();
    SiteRequest siteRequest = o.getSiteRequest_();
    try {
      ApiRequest apiRequest = siteRequest.getApiRequest_();
      List<String> solrIds = Optional.ofNullable(apiRequest).map(r -> r.getSolrIds()).orElse(new ArrayList<>());
      List<String> classes = Optional.ofNullable(apiRequest).map(r -> r.getClasses()).orElse(new ArrayList<>());
      Boolean refresh = !"false".equals(siteRequest.getRequestVars().get("refresh"));
      if(refresh && !Optional.ofNullable(siteRequest.getJsonObject()).map(JsonObject::isEmpty).orElse(true)) {
        List<Future> futures = new ArrayList<>();

        for(int i=0; i < solrIds.size(); i++) {
          String solrId2 = solrIds.get(i);
          String classSimpleName2 = classes.get(i);
        }

        CompositeFuture.all(futures).onSuccess(b -> {
          JsonObject params = new JsonObject();
          params.put("body", new JsonObject());
          params.put("cookie", siteRequest.getServiceRequest().getParams().getJsonObject("cookie"));
          params.put("header", siteRequest.getServiceRequest().getParams().getJsonObject("header"));
          params.put("form", new JsonObject());
          params.put("path", new JsonObject());
          params.put("scopes", siteRequest.getScopes());
          JsonObject query = new JsonObject();
          Boolean softCommit = Optional.ofNullable(siteRequest.getServiceRequest().getParams()).map(p -> p.getJsonObject("query")).map( q -> q.getBoolean("softCommit")).orElse(null);
          Integer commitWithin = Optional.ofNullable(siteRequest.getServiceRequest().getParams()).map(p -> p.getJsonObject("query")).map( q -> q.getInteger("commitWithin")).orElse(null);
          if(softCommit == null && commitWithin == null)
            softCommit = true;
          if(softCommit != null)
            query.put("softCommit", softCommit);
          if(commitWithin != null)
            query.put("commitWithin", commitWithin);
          query.put("q", "*:*").put("fq", new JsonArray().add("pk:" + o.getPk())).put("var", new JsonArray().add("refresh:false"));
          params.put("query", query);
          JsonObject context = new JsonObject().put("params", params).put("user", siteRequest.getUserPrincipal());
          JsonObject json = new JsonObject().put("context", context);
          eventBus.request(RequestApproval.getClassApiAddress(), json, new DeliveryOptions().addHeader("action", "patchRequestApprovalFuture")).onSuccess(c -> {
            JsonObject responseMessage = (JsonObject)c.body();
            Integer statusCode = responseMessage.getInteger("statusCode");
            if(statusCode.equals(200))
              promise.complete();
            else
              promise.tryFail(new RuntimeException(responseMessage.getString("statusMessage")));
          }).onFailure(ex -> {
            LOG.error("Refresh relations failed. ", ex);
            promise.tryFail(ex);
          });
        }).onFailure(ex -> {
          LOG.error("Refresh relations failed. ", ex);
          promise.tryFail(ex);
        });
      } else {
        promise.complete();
      }
    } catch(Exception ex) {
      LOG.error(String.format("refreshRequestApproval failed. "), ex);
      promise.tryFail(ex);
    }
    return promise.future();
  }

  @Override
  public Future<JsonObject> generatePageBody(ComputateSiteRequest siteRequest, Map<String, Object> ctx, String templatePath, String classSimpleName, String pageTemplate) {
    Promise<JsonObject> promise = Promise.promise();
    try {
      Map<String, Object> result = (Map<String, Object>)ctx.get("result");
      SiteRequest siteRequest2 = (SiteRequest)siteRequest;
      String siteBaseUrl = config.getString(ComputateConfigKeys.SITE_BASE_URL);
      RequestApproval o = new RequestApproval();
      o.setSiteRequest_((SiteRequest)siteRequest);

      o.persistForClass(RequestApproval.VAR_approvalName, RequestApproval.staticSetApprovalName(siteRequest2, (String)result.get(RequestApproval.VAR_approvalName)));
      o.persistForClass(RequestApproval.VAR_approvalId, RequestApproval.staticSetApprovalId(siteRequest2, (String)result.get(RequestApproval.VAR_approvalId)));
      o.persistForClass(RequestApproval.VAR_created, RequestApproval.staticSetCreated(siteRequest2, (String)result.get(RequestApproval.VAR_created), Optional.ofNullable(siteRequest).map(r -> r.getConfig()).map(config -> config.getString(ConfigKeys.SITE_ZONE)).map(z -> ZoneId.of(z)).orElse(ZoneId.of("UTC"))));
      o.persistForClass(RequestApproval.VAR_approvedByEmail, RequestApproval.staticSetApprovedByEmail(siteRequest2, (String)result.get(RequestApproval.VAR_approvedByEmail)));
      o.persistForClass(RequestApproval.VAR_approvedByUserId, RequestApproval.staticSetApprovedByUserId(siteRequest2, (String)result.get(RequestApproval.VAR_approvedByUserId)));
      o.persistForClass(RequestApproval.VAR_archived, RequestApproval.staticSetArchived(siteRequest2, (String)result.get(RequestApproval.VAR_archived)));
      o.persistForClass(RequestApproval.VAR_approvedByFullName, RequestApproval.staticSetApprovedByFullName(siteRequest2, (String)result.get(RequestApproval.VAR_approvedByFullName)));
      o.persistForClass(RequestApproval.VAR_approvalNote, RequestApproval.staticSetApprovalNote(siteRequest2, (String)result.get(RequestApproval.VAR_approvalNote)));
      o.persistForClass(RequestApproval.VAR_approved, RequestApproval.staticSetApproved(siteRequest2, (String)result.get(RequestApproval.VAR_approved)));
      o.persistForClass(RequestApproval.VAR_modelType, RequestApproval.staticSetModelType(siteRequest2, (String)result.get(RequestApproval.VAR_modelType)));
      o.persistForClass(RequestApproval.VAR_sessionId, RequestApproval.staticSetSessionId(siteRequest2, (String)result.get(RequestApproval.VAR_sessionId)));
      o.persistForClass(RequestApproval.VAR_modelResource, RequestApproval.staticSetModelResource(siteRequest2, (String)result.get(RequestApproval.VAR_modelResource)));
      o.persistForClass(RequestApproval.VAR_userKey, RequestApproval.staticSetUserKey(siteRequest2, (String)result.get(RequestApproval.VAR_userKey)));
      o.persistForClass(RequestApproval.VAR_approvalTitle, RequestApproval.staticSetApprovalTitle(siteRequest2, (String)result.get(RequestApproval.VAR_approvalTitle)));
      o.persistForClass(RequestApproval.VAR_objectTitle, RequestApproval.staticSetObjectTitle(siteRequest2, (String)result.get(RequestApproval.VAR_objectTitle)));
      o.persistForClass(RequestApproval.VAR_displayPage, RequestApproval.staticSetDisplayPage(siteRequest2, (String)result.get(RequestApproval.VAR_displayPage)));
      o.persistForClass(RequestApproval.VAR_editPage, RequestApproval.staticSetEditPage(siteRequest2, (String)result.get(RequestApproval.VAR_editPage)));
      o.persistForClass(RequestApproval.VAR_userPage, RequestApproval.staticSetUserPage(siteRequest2, (String)result.get(RequestApproval.VAR_userPage)));
      o.persistForClass(RequestApproval.VAR_download, RequestApproval.staticSetDownload(siteRequest2, (String)result.get(RequestApproval.VAR_download)));

      o.promiseDeepForClass((SiteRequest)siteRequest).onSuccess(o2 -> {
        try {
          JsonObject data = JsonObject.mapFrom(o2);
          ctx.put("result", data.getMap());
          promise.complete(data);
        } catch(Exception ex) {
          LOG.error(String.format(importModelFail, classSimpleName), ex);
          promise.tryFail(ex);
        }
      }).onFailure(ex -> {
        LOG.error(String.format("generatePageBody failed. "), ex);
        promise.tryFail(ex);
      });
    } catch(Exception ex) {
      LOG.error(String.format("generatePageBody failed. "), ex);
      promise.tryFail(ex);
    }
    return promise.future();
  }
}
