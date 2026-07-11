package org.computate.dcm.model.eda.tenant.intent;

import org.computate.dcm.model.eda.hostinventory.HostInventoryEnUSApiServiceImpl;
import org.computate.dcm.model.eda.hostinventory.HostInventory;
import org.computate.dcm.model.eda.ansibleproject.AnsibleProjectEnUSApiServiceImpl;
import org.computate.dcm.model.eda.ansibleproject.AnsibleProject;
import org.computate.dcm.model.eda.tenant.requested.TenantRequestedEnUSApiServiceImpl;
import org.computate.dcm.model.eda.tenant.requested.TenantRequested;
import org.computate.dcm.model.eda.tenant.discovered.TenantDiscoveredEnUSApiServiceImpl;
import org.computate.dcm.model.eda.tenant.discovered.TenantDiscovered;
import org.computate.dcm.model.eda.tenant.realized.TenantRealizedEnUSApiServiceImpl;
import org.computate.dcm.model.eda.tenant.realized.TenantRealized;
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
import org.computate.dcm.model.eda.tenant.intent.TenantIntentPage;


/**
 * Translate: false
 * Generated: true
 **/
public class TenantIntentEnUSGenApiServiceImpl extends BaseApiServiceImpl implements TenantIntentEnUSGenApiService {

  protected static final Logger LOG = LoggerFactory.getLogger(TenantIntentEnUSGenApiServiceImpl.class);

  // Search //

  @Override
  public void searchTenantIntent(ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler) {
    Boolean classPublicRead = false;
    user(serviceRequest, SiteRequest.class, SiteUser.class, SiteUser.getClassApiAddress(), "postSiteUserFuture", "patchSiteUserFuture", classPublicRead).onSuccess(siteRequest -> {
      try {
        siteRequest.setLang("enUS");
        String tenantResource = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("tenantResource");
        String TENANT = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("TENANT");
        List<String> groups = Optional.ofNullable(siteRequest.getGroups()).orElse(new ArrayList<>());
        MultiMap form = MultiMap.caseInsensitiveMultiMap();
        form.add("grant_type", "urn:ietf:params:oauth:grant-type:uma-ticket");
        form.add("audience", config.getString(ComputateConfigKeys.AUTH_CLIENT));
        form.add("response_mode", "permissions");
        form.add("permission", String.format("%s#%s", TenantIntent.CLASS_AUTH_RESOURCE, "GET"));
        form.add("permission", String.format("%s#%s", TenantIntent.CLASS_AUTH_RESOURCE, "POST"));
        form.add("permission", String.format("%s#%s", TenantIntent.CLASS_AUTH_RESOURCE, "PATCH"));
        form.add("permission", String.format("%s#%s", TenantIntent.CLASS_AUTH_RESOURCE, "DELETE"));
        form.add("permission", String.format("%s#%s", TenantIntent.CLASS_AUTH_RESOURCE, "Admin"));
        form.add("permission", String.format("%s#%s", TenantIntent.CLASS_AUTH_RESOURCE, "SuperAdmin"));
        if(tenantResource != null)
          form.add("permission", String.format("%s#%s", tenantResource, "GET"));
        groups.stream().map(group -> {
              Matcher mPermission = Pattern.compile("^/(.*-?TENANT-([a-z0-9\\-]+))-(\\w+)$").matcher(group);
              return mPermission.find() ? mPermission : null;
            }).filter(v -> v != null).forEach(mPermission -> {
              form.add("permission", String.format("%s#%s", mPermission.group(1), mPermission.group(3)));
            });
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
            JsonArray scopes = authorizationDecisionBody.stream().map(o -> (JsonObject)o).filter(o -> "TENANT".equals(o.getString("rsname"))).findFirst().map(decision -> ((JsonObject)decision).getJsonArray("scopes")).orElse(new JsonArray());
            if(!scopes.contains("GET")) {
              List<String> fqs = new ArrayList<>();
              authorizationDecisionBody.stream().map(o -> (JsonObject)o).filter(permission -> {
                    Matcher mPermission = Pattern.compile("^(.*-?TENANT-([a-z0-9\\-]+))$").matcher(permission.getString("rsname"));
                    return permission.getJsonArray("scopes").contains("GET")
                        && mPermission.find();
                  }).forEach(permission -> {
                    fqs.add(String.format("%s:%s", "tenantResource", permission.getString("rsname")));
                    permission.getJsonArray("scopes").stream().map(s -> (String)s).forEach(scope -> {
                      if(!scopes.contains(scope))
                        scopes.add(scope);
                    });
                  });
              if(!classPublicRead) {
                JsonObject authParams = siteRequest.getServiceRequest().getParams();
                JsonObject authQuery = authParams.getJsonObject("query");
                if(authQuery == null) {
                  authQuery = new JsonObject();
                  authParams.put("query", authQuery);
                }
                JsonArray fq = authQuery.getJsonArray("fq");
                if(fq == null) {
                  fq = new JsonArray();
                  authQuery.put("fq", fq);
                }
                if(fqs.size() > 0) {
                  fq.add(fqs.stream().collect(Collectors.joining(" OR ")));
                  if(!scopes.contains("GET"))
                    scopes.add("GET");
                  siteRequest.setFilteredScope(true);
                }
              }
            }
            {
              siteRequest.setScopes(scopes.stream().map(o -> o.toString()).collect(Collectors.toList()));
              List<String> scopes2 = siteRequest.getScopes();
              searchTenantIntentList(siteRequest, false, true, false, "GET").onSuccess(listTenantIntent -> {
                response200SearchTenantIntent(listTenantIntent).onSuccess(response -> {
                  eventHandler.handle(Future.succeededFuture(response));
                  LOG.debug(String.format("searchTenantIntent succeeded. "));
                }).onFailure(ex -> {
                  LOG.error(String.format("searchTenantIntent failed. "), ex);
                  error(siteRequest, eventHandler, ex);
                });
              }).onFailure(ex -> {
                LOG.error(String.format("searchTenantIntent failed. "), ex);
                error(siteRequest, eventHandler, ex);
              });
            }
          } catch(Exception ex) {
            LOG.error(String.format("searchTenantIntent failed. "), ex);
            error(null, eventHandler, ex);
          }
        });
      } catch(Exception ex) {
        LOG.error(String.format("searchTenantIntent failed. "), ex);
        error(null, eventHandler, ex);
      }
    }).onFailure(ex -> {
      if("Inactive Token".equals(ex.getMessage()) || StringUtils.startsWith(ex.getMessage(), "invalid_grant:")) {
        try {
          eventHandler.handle(Future.succeededFuture(new ServiceResponse(302, "Found", null, MultiMap.caseInsensitiveMultiMap().add(HttpHeaders.LOCATION, "/logout?redirect_uri=" + URLEncoder.encode(serviceRequest.getExtra().getString("uri"), "UTF-8")))));
        } catch(Exception ex2) {
          LOG.error(String.format("searchTenantIntent failed. ", ex2));
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
        LOG.error(String.format("searchTenantIntent failed. "), ex);
        error(null, eventHandler, ex);
      }
    });
  }

  public Future<ServiceResponse> response200SearchTenantIntent(SearchList<TenantIntent> listTenantIntent) {
    Promise<ServiceResponse> promise = Promise.promise();
    try {
      SiteRequest siteRequest = listTenantIntent.getSiteRequest_(SiteRequest.class);
      List<String> fls = listTenantIntent.getRequest().getFields();
      JsonObject json = new JsonObject();
      JsonArray l = new JsonArray();
      List<String> scopes = siteRequest.getScopes();
      listTenantIntent.getList().stream().forEach(o -> {
        JsonObject json2 = JsonObject.mapFrom(o);
        if(fls.size() > 0) {
          Set<String> fieldNames = new HashSet<String>();
          for(String fieldName : json2.fieldNames()) {
            String v = TenantIntent.varIndexedTenantIntent(fieldName);
            if(v != null)
              fieldNames.add(TenantIntent.varIndexedTenantIntent(fieldName));
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
      response200Search(listTenantIntent.getRequest(), listTenantIntent.getResponse(), json);
      promise.complete(ServiceResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily())));
    } catch(Exception ex) {
      LOG.error(String.format("response200SearchTenantIntent failed. "), ex);
      promise.tryFail(ex);
    }
    return promise.future();
  }
  public void responsePivotSearchTenantIntent(List<SolrResponse.Pivot> pivots, JsonArray pivotArray) {
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
          responsePivotSearchTenantIntent(pivotFields2, pivotArray2);
        }
      }
    }
  }

  // GET //

  @Override
  public void getTenantIntent(ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler) {
    Boolean classPublicRead = false;
    user(serviceRequest, SiteRequest.class, SiteUser.class, SiteUser.getClassApiAddress(), "postSiteUserFuture", "patchSiteUserFuture", classPublicRead).onSuccess(siteRequest -> {
      try {
        siteRequest.setLang("enUS");
        String tenantResource = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("tenantResource");
        String TENANT = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("TENANT");
        List<String> groups = Optional.ofNullable(siteRequest.getGroups()).orElse(new ArrayList<>());
        MultiMap form = MultiMap.caseInsensitiveMultiMap();
        form.add("grant_type", "urn:ietf:params:oauth:grant-type:uma-ticket");
        form.add("audience", config.getString(ComputateConfigKeys.AUTH_CLIENT));
        form.add("response_mode", "permissions");
        form.add("permission", String.format("%s#%s", TenantIntent.CLASS_AUTH_RESOURCE, "GET"));
        form.add("permission", String.format("%s#%s", TenantIntent.CLASS_AUTH_RESOURCE, "POST"));
        form.add("permission", String.format("%s#%s", TenantIntent.CLASS_AUTH_RESOURCE, "PATCH"));
        form.add("permission", String.format("%s#%s", TenantIntent.CLASS_AUTH_RESOURCE, "DELETE"));
        form.add("permission", String.format("%s#%s", TenantIntent.CLASS_AUTH_RESOURCE, "Admin"));
        form.add("permission", String.format("%s#%s", TenantIntent.CLASS_AUTH_RESOURCE, "SuperAdmin"));
        if(tenantResource != null)
          form.add("permission", String.format("%s#%s", tenantResource, "GET"));
        groups.stream().map(group -> {
              Matcher mPermission = Pattern.compile("^/(.*-?TENANT-([a-z0-9\\-]+))-(\\w+)$").matcher(group);
              return mPermission.find() ? mPermission : null;
            }).filter(v -> v != null).forEach(mPermission -> {
              form.add("permission", String.format("%s#%s", mPermission.group(1), mPermission.group(3)));
            });
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
            JsonArray scopes = authorizationDecisionBody.stream().map(o -> (JsonObject)o).filter(o -> "TENANT".equals(o.getString("rsname"))).findFirst().map(decision -> ((JsonObject)decision).getJsonArray("scopes")).orElse(new JsonArray());
            if(!scopes.contains("GET")) {
              List<String> fqs = new ArrayList<>();
              authorizationDecisionBody.stream().map(o -> (JsonObject)o).filter(permission -> {
                    Matcher mPermission = Pattern.compile("^(.*-?TENANT-([a-z0-9\\-]+))$").matcher(permission.getString("rsname"));
                    return permission.getJsonArray("scopes").contains("GET")
                        && mPermission.find();
                  }).forEach(permission -> {
                    fqs.add(String.format("%s:%s", "tenantResource", permission.getString("rsname")));
                    permission.getJsonArray("scopes").stream().map(s -> (String)s).forEach(scope -> {
                      if(!scopes.contains(scope))
                        scopes.add(scope);
                    });
                  });
              if(!classPublicRead) {
                JsonObject authParams = siteRequest.getServiceRequest().getParams();
                JsonObject authQuery = authParams.getJsonObject("query");
                if(authQuery == null) {
                  authQuery = new JsonObject();
                  authParams.put("query", authQuery);
                }
                JsonArray fq = authQuery.getJsonArray("fq");
                if(fq == null) {
                  fq = new JsonArray();
                  authQuery.put("fq", fq);
                }
                if(fqs.size() > 0) {
                  fq.add(fqs.stream().collect(Collectors.joining(" OR ")));
                  if(!scopes.contains("GET"))
                    scopes.add("GET");
                  siteRequest.setFilteredScope(true);
                }
              }
            }
            {
              siteRequest.setScopes(scopes.stream().map(o -> o.toString()).collect(Collectors.toList()));
              List<String> scopes2 = siteRequest.getScopes();
              searchTenantIntentList(siteRequest, false, true, false, "GET").onSuccess(listTenantIntent -> {
                response200GETTenantIntent(listTenantIntent).onSuccess(response -> {
                  eventHandler.handle(Future.succeededFuture(response));
                  LOG.debug(String.format("getTenantIntent succeeded. "));
                }).onFailure(ex -> {
                  LOG.error(String.format("getTenantIntent failed. "), ex);
                  error(siteRequest, eventHandler, ex);
                });
              }).onFailure(ex -> {
                LOG.error(String.format("getTenantIntent failed. "), ex);
                error(siteRequest, eventHandler, ex);
              });
            }
          } catch(Exception ex) {
            LOG.error(String.format("getTenantIntent failed. "), ex);
            error(null, eventHandler, ex);
          }
        });
      } catch(Exception ex) {
        LOG.error(String.format("getTenantIntent failed. "), ex);
        error(null, eventHandler, ex);
      }
    }).onFailure(ex -> {
      if("Inactive Token".equals(ex.getMessage()) || StringUtils.startsWith(ex.getMessage(), "invalid_grant:")) {
        try {
          eventHandler.handle(Future.succeededFuture(new ServiceResponse(302, "Found", null, MultiMap.caseInsensitiveMultiMap().add(HttpHeaders.LOCATION, "/logout?redirect_uri=" + URLEncoder.encode(serviceRequest.getExtra().getString("uri"), "UTF-8")))));
        } catch(Exception ex2) {
          LOG.error(String.format("getTenantIntent failed. ", ex2));
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
        LOG.error(String.format("getTenantIntent failed. "), ex);
        error(null, eventHandler, ex);
      }
    });
  }

  public Future<ServiceResponse> response200GETTenantIntent(SearchList<TenantIntent> listTenantIntent) {
    Promise<ServiceResponse> promise = Promise.promise();
    try {
      SiteRequest siteRequest = listTenantIntent.getSiteRequest_(SiteRequest.class);
      JsonObject json = JsonObject.mapFrom(listTenantIntent.getList().stream().findFirst().orElse(null));
      promise.complete(ServiceResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily())));
    } catch(Exception ex) {
      LOG.error(String.format("response200GETTenantIntent failed. "), ex);
      promise.tryFail(ex);
    }
    return promise.future();
  }

  // PATCH //

  @Override
  public void patchTenantIntent(JsonObject body, ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler) {
    LOG.debug(String.format("patchTenantIntent started. "));
    Boolean classPublicRead = false;
    user(serviceRequest, SiteRequest.class, SiteUser.class, SiteUser.getClassApiAddress(), "postSiteUserFuture", "patchSiteUserFuture", classPublicRead).onSuccess(siteRequest -> {
      try {
        siteRequest.setLang("enUS");
        String tenantResource = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("tenantResource");
        String TENANT = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("TENANT");
        List<String> groups = Optional.ofNullable(siteRequest.getGroups()).orElse(new ArrayList<>());
        MultiMap form = MultiMap.caseInsensitiveMultiMap();
        form.add("grant_type", "urn:ietf:params:oauth:grant-type:uma-ticket");
        form.add("audience", config.getString(ComputateConfigKeys.AUTH_CLIENT));
        form.add("response_mode", "permissions");
        form.add("permission", String.format("%s#%s", TenantIntent.CLASS_AUTH_RESOURCE, "GET"));
        form.add("permission", String.format("%s#%s", TenantIntent.CLASS_AUTH_RESOURCE, "POST"));
        form.add("permission", String.format("%s#%s", TenantIntent.CLASS_AUTH_RESOURCE, "PATCH"));
        form.add("permission", String.format("%s#%s", TenantIntent.CLASS_AUTH_RESOURCE, "DELETE"));
        form.add("permission", String.format("%s#%s", TenantIntent.CLASS_AUTH_RESOURCE, "Admin"));
        form.add("permission", String.format("%s#%s", TenantIntent.CLASS_AUTH_RESOURCE, "SuperAdmin"));
        if(tenantResource != null)
          form.add("permission", String.format("%s#%s", tenantResource, "PATCH"));
        groups.stream().map(group -> {
              Matcher mPermission = Pattern.compile("^/(.*-?TENANT-([a-z0-9\\-]+))-(\\w+)$").matcher(group);
              return mPermission.find() ? mPermission : null;
            }).filter(v -> v != null).forEach(mPermission -> {
              form.add("permission", String.format("%s#%s", mPermission.group(1), mPermission.group(3)));
            });
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
            JsonArray scopes = authorizationDecisionBody.stream().map(o -> (JsonObject)o).filter(o -> "TENANT".equals(o.getString("rsname"))).findFirst().map(decision -> ((JsonObject)decision).getJsonArray("scopes")).orElse(new JsonArray());
            if(!scopes.contains("PATCH")) {
            List<String> fqs = new ArrayList<>();
            authorizationDecisionBody.stream().map(o -> (JsonObject)o).filter(permission -> {
                  Matcher mPermission = Pattern.compile("^(.*-?TENANT-([a-z0-9\\-]+))$").matcher(permission.getString("rsname"));
                  return permission.getJsonArray("scopes").contains("PATCH")
                      && mPermission.find();
                }).forEach(permission -> {
                  fqs.add(String.format("%s:%s", "tenantResource", permission.getString("rsname")));
                  permission.getJsonArray("scopes").stream().map(s -> (String)s).forEach(scope -> {
                    if(!scopes.contains(scope))
                      scopes.add(scope);
                  });
                });
              JsonObject authParams = siteRequest.getServiceRequest().getParams();
              JsonObject authQuery = authParams.getJsonObject("query");
              if(authQuery == null) {
                authQuery = new JsonObject();
                authParams.put("query", authQuery);
              }
              JsonArray fq = authQuery.getJsonArray("fq");
              if(fq == null) {
                fq = new JsonArray();
                authQuery.put("fq", fq);
              }
              if(fqs.size() > 0) {
                fq.add(fqs.stream().collect(Collectors.joining(" OR ")));
                if(!scopes.contains("PATCH"))
                  scopes.add("PATCH");
                siteRequest.setFilteredScope(true);
              }
            }
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
              searchTenantIntentList(siteRequest, false, true, true, "PATCH").onSuccess(listTenantIntent -> {
                try {
                  ApiRequest apiRequest = new ApiRequest();
                  apiRequest.setRows(listTenantIntent.getRequest().getRows());
                  apiRequest.setNumFound(listTenantIntent.getResponse().getResponse().getNumFound());
                  apiRequest.setNumPATCH(0L);
                  apiRequest.initDeepApiRequest(siteRequest);
                  siteRequest.setApiRequest_(apiRequest);
                  if(apiRequest.getNumFound() == 1L)
                    apiRequest.setOriginal(listTenantIntent.first());
                  apiRequest.setId(Optional.ofNullable(listTenantIntent.first()).map(o2 -> o2.getTenantResource().toString()).orElse(null));
                  apiRequest.setSolrId(Optional.ofNullable(listTenantIntent.first()).map(o2 -> o2.getSolrId()).orElse(null));
                  eventBus.publish("websocketTenantIntent", JsonObject.mapFrom(apiRequest).toString());

                  listPATCHTenantIntent(apiRequest, listTenantIntent).onSuccess(e -> {
                    response200PATCHTenantIntent(siteRequest).onSuccess(response -> {
                      LOG.debug(String.format("patchTenantIntent succeeded. "));
                      eventHandler.handle(Future.succeededFuture(response));
                    }).onFailure(ex -> {
                      LOG.error(String.format("patchTenantIntent failed. "), ex);
                      error(siteRequest, eventHandler, ex);
                    });
                  }).onFailure(ex -> {
                    LOG.error(String.format("patchTenantIntent failed. "), ex);
                    error(siteRequest, eventHandler, ex);
                  });
                } catch(Exception ex) {
                  LOG.error(String.format("patchTenantIntent failed. "), ex);
                  error(siteRequest, eventHandler, ex);
                }
              }).onFailure(ex -> {
                LOG.error(String.format("patchTenantIntent failed. "), ex);
                error(siteRequest, eventHandler, ex);
              });
            }
          } catch(Exception ex) {
            LOG.error(String.format("patchTenantIntent failed. "), ex);
            error(null, eventHandler, ex);
          }
        });
      } catch(Exception ex) {
        LOG.error(String.format("patchTenantIntent failed. "), ex);
        error(null, eventHandler, ex);
      }
    }).onFailure(ex -> {
      if("Inactive Token".equals(ex.getMessage()) || StringUtils.startsWith(ex.getMessage(), "invalid_grant:")) {
        try {
          eventHandler.handle(Future.succeededFuture(new ServiceResponse(302, "Found", null, MultiMap.caseInsensitiveMultiMap().add(HttpHeaders.LOCATION, "/logout?redirect_uri=" + URLEncoder.encode(serviceRequest.getExtra().getString("uri"), "UTF-8")))));
        } catch(Exception ex2) {
          LOG.error(String.format("patchTenantIntent failed. ", ex2));
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
        LOG.error(String.format("patchTenantIntent failed. "), ex);
        error(null, eventHandler, ex);
      }
    });
  }

  public Future<Void> listPATCHTenantIntent(ApiRequest apiRequest, SearchList<TenantIntent> listTenantIntent) {
    Promise<Void> promise = Promise.promise();
    List<Future> futures = new ArrayList<>();
    SiteRequest siteRequest = listTenantIntent.getSiteRequest_(SiteRequest.class);
    listTenantIntent.getList().forEach(o -> {
      SiteRequest siteRequest2 = generateSiteRequest(siteRequest.getUser(), siteRequest.getUserPrincipal(), siteRequest.getServiceRequest(), siteRequest.getJsonObject(), SiteRequest.class);
      siteRequest2.setScopes(siteRequest.getScopes());
      o.setSiteRequest_(siteRequest2);
      siteRequest2.setApiRequest_(siteRequest.getApiRequest_());
      JsonObject jsonObject = JsonObject.mapFrom(o);
      TenantIntent o2 = jsonObject.mapTo(TenantIntent.class);
      o2.setSiteRequest_(siteRequest2);
      futures.add(Future.future(promise1 -> {
        patchTenantIntentFuture(o2, false).onSuccess(a -> {
          promise1.complete();
        }).onFailure(ex -> {
          LOG.error(String.format("listPATCHTenantIntent failed. "), ex);
          promise1.tryFail(ex);
        });
      }));
    });
    CompositeFuture.all(futures).onSuccess( a -> {
      listTenantIntent.next().onSuccess(next -> {
        if(next) {
          listPATCHTenantIntent(apiRequest, listTenantIntent).onSuccess(b -> {
            promise.complete();
          }).onFailure(ex -> {
            LOG.error(String.format("listPATCHTenantIntent failed. "), ex);
            promise.tryFail(ex);
          });
        } else {
          promise.complete();
        }
      }).onFailure(ex -> {
        LOG.error(String.format("listPATCHTenantIntent failed. "), ex);
        promise.tryFail(ex);
      });
    }).onFailure(ex -> {
      LOG.error(String.format("listPATCHTenantIntent failed. "), ex);
      promise.tryFail(ex);
    });
    return promise.future();
  }

  @Override
  public void patchTenantIntentFuture(JsonObject body, ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler) {
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
        searchTenantIntentList(siteRequest, false, true, true, "PATCH").onSuccess(listTenantIntent -> {
          try {
            TenantIntent o = listTenantIntent.first();
            ApiRequest apiRequest = new ApiRequest();
            apiRequest.setRows(1L);
            apiRequest.setNumFound(1L);
            apiRequest.setNumPATCH(0L);
            apiRequest.initDeepApiRequest(siteRequest);
            siteRequest.setApiRequest_(apiRequest);
            if(Optional.ofNullable(serviceRequest.getParams()).map(p -> p.getJsonObject("query")).map( q -> q.getJsonArray("var")).orElse(new JsonArray()).stream().filter(s -> "refresh:false".equals(s)).count() > 0L) {
              siteRequest.getRequestVars().put( "refresh", "false" );
            }
            TenantIntent o2;
            if(o != null) {
              if(apiRequest.getNumFound() == 1L)
                apiRequest.setOriginal(o);
              apiRequest.setId(Optional.ofNullable(listTenantIntent.first()).map(o3 -> o3.getTenantResource().toString()).orElse(null));
              apiRequest.setSolrId(Optional.ofNullable(listTenantIntent.first()).map(o3 -> o3.getSolrId()).orElse(null));
              JsonObject jsonObject = JsonObject.mapFrom(o);
              o2 = jsonObject.mapTo(TenantIntent.class);
              o2.setSiteRequest_(siteRequest);
              patchTenantIntentFuture(o2, false).onSuccess(o3 -> {
                eventHandler.handle(Future.succeededFuture(ServiceResponse.completedWithJson(Buffer.buffer(new JsonObject().encodePrettily()))));
              }).onFailure(ex -> {
                eventHandler.handle(Future.failedFuture(ex));
              });
            } else {
              String m = String.format("%s %s not found", "tenant intent", null);
              eventHandler.handle(Future.failedFuture(m));
            }
          } catch(Exception ex) {
            LOG.error(String.format("patchTenantIntent failed. "), ex);
            error(siteRequest, eventHandler, ex);
          }
        }).onFailure(ex -> {
          LOG.error(String.format("patchTenantIntent failed. "), ex);
          error(siteRequest, eventHandler, ex);
        });
      } catch(Exception ex) {
        LOG.error(String.format("patchTenantIntent failed. "), ex);
        error(null, eventHandler, ex);
      }
    }).onFailure(ex -> {
      LOG.error(String.format("patchTenantIntent failed. "), ex);
      error(null, eventHandler, ex);
    });
  }

  public Future<TenantIntent> patchTenantIntentFuture(TenantIntent o, Boolean inheritPrimaryKey) {
    SiteRequest siteRequest = o.getSiteRequest_();
    Promise<TenantIntent> promise = Promise.promise();

    try {
      ApiRequest apiRequest = siteRequest.getApiRequest_();
      Promise<TenantIntent> promise1 = Promise.promise();
      pgPool.withTransaction(sqlConnection -> {
        siteRequest.setSqlConnection(sqlConnection);
        varsTenantIntent(siteRequest).onSuccess(a -> {
          sqlPATCHTenantIntent(o, inheritPrimaryKey).onSuccess(tenantIntent -> {
            persistTenantIntent(tenantIntent, true).onSuccess(c -> {
              relateTenantIntent(tenantIntent).onSuccess(d -> {
                indexTenantIntent(tenantIntent).onSuccess(o2 -> {
                  if(apiRequest != null) {
                    apiRequest.setNumPATCH(apiRequest.getNumPATCH() + 1);
                    if(apiRequest.getNumFound() == 1L && Optional.ofNullable(siteRequest.getJsonObject()).map(json -> json.size() > 0).orElse(false)) {
                      o2.apiRequestTenantIntent();
                      if(apiRequest.getVars().size() > 0 && Optional.ofNullable(siteRequest.getRequestVars().get("refresh")).map(refresh -> !refresh.equals("false")).orElse(true))
                        eventBus.publish("websocketTenantIntent", JsonObject.mapFrom(apiRequest).toString());
                    }
                  }
                  promise1.complete(tenantIntent);
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
      }).compose(tenantIntent -> {
        Promise<TenantIntent> promise2 = Promise.promise();
        refreshTenantIntent(tenantIntent).onSuccess(a -> {
          promise2.complete(tenantIntent);
        }).onFailure(ex -> {
          promise2.tryFail(ex);
        });
        return promise2.future();
      }).onSuccess(tenantIntent -> {
        promise.complete(tenantIntent);
      }).onFailure(ex -> {
        promise.tryFail(ex);
      });
    } catch(Exception ex) {
      LOG.error(String.format("patchTenantIntentFuture failed. "), ex);
      promise.tryFail(ex);
    }
    return promise.future();
  }

  public Future<TenantIntent> sqlPATCHTenantIntent(TenantIntent o, Boolean inheritPrimaryKey) {
    Promise<TenantIntent> promise = Promise.promise();
    try {
      SiteRequest siteRequest = o.getSiteRequest_();
      ApiRequest apiRequest = siteRequest.getApiRequest_();
      List<String> solrIds = Optional.ofNullable(apiRequest).map(r -> r.getSolrIds()).orElse(new ArrayList<>());
      List<String> classes = Optional.ofNullable(apiRequest).map(r -> r.getClasses()).orElse(new ArrayList<>());
      SqlConnection sqlConnection = siteRequest.getSqlConnection();
      Integer num = 1;
      StringBuilder bSql = new StringBuilder("UPDATE TenantIntent SET ");
      List<Object> bParams = new ArrayList<Object>();
      Long pk = o.getPk();
      JsonObject jsonObject = siteRequest.getJsonObject();
      Set<String> methodNames = jsonObject.fieldNames();
      TenantIntent o2 = new TenantIntent();
      o2.setSiteRequest_(siteRequest);
      List<Future> futures1 = new ArrayList<>();
      List<Future> futures2 = new ArrayList<>();

      for(String entityVar : methodNames) {
        switch(entityVar) {
          case "setHubId":
              o2.setHubId(jsonObject.getString(entityVar));
              if(bParams.size() > 0)
                bSql.append(", ");
              bSql.append(TenantIntent.VAR_hubId + "=$" + num);
              num++;
              bParams.add(o2.sqlHubId());
            break;
          case "setClusterName":
              o2.setClusterName(jsonObject.getString(entityVar));
              if(bParams.size() > 0)
                bSql.append(", ");
              bSql.append(TenantIntent.VAR_clusterName + "=$" + num);
              num++;
              bParams.add(o2.sqlClusterName());
            break;
          case "setCreated":
              o2.setCreated(jsonObject.getString(entityVar));
              if(bParams.size() > 0)
                bSql.append(", ");
              bSql.append(TenantIntent.VAR_created + "=$" + num);
              num++;
              bParams.add(o2.sqlCreated());
            break;
          case "setAapOrganizationId":
              o2.setAapOrganizationId(jsonObject.getString(entityVar));
              if(bParams.size() > 0)
                bSql.append(", ");
              bSql.append(TenantIntent.VAR_aapOrganizationId + "=$" + num);
              num++;
              bParams.add(o2.sqlAapOrganizationId());
            break;
          case "setTenantName":
              o2.setTenantName(jsonObject.getString(entityVar));
              if(bParams.size() > 0)
                bSql.append(", ");
              bSql.append(TenantIntent.VAR_tenantName + "=$" + num);
              num++;
              bParams.add(o2.sqlTenantName());
            break;
          case "setHostInventoryIds":
            JsonArray setHostInventoryIdsValues = Optional.ofNullable(jsonObject.getJsonArray(entityVar)).orElse(new JsonArray());
            setHostInventoryIdsValues.stream().map(oVal -> oVal.toString()).forEach(val -> {
              futures2.add(Future.future(promise2 -> {
                searchModel(siteRequest).query(HostInventory.varIndexedHostInventory(HostInventory.VAR_tenantResource), HostInventory.class, val).onSuccess(o3 -> {
                  String solrId2 = Optional.ofNullable(o3).map(o4 -> o4.getSolrId()).filter(solrId3 -> !solrIds.contains(solrId3)).orElse(null);
                  Long pk2 = Optional.ofNullable(o3).map(o4 -> o4.getPk()).orElse(null);
                  if(solrId2 != null) {
                    solrIds.add(solrId2);
                    classes.add("HostInventory");
                  }
                  sql(siteRequest).update(HostInventory.class, pk2).set(HostInventory.VAR_tenantResource, TenantIntent.class, o.getSolrId(), val).onSuccess(a -> {
                    promise2.complete();
                  }).onFailure(ex -> {
                    promise2.tryFail(ex);
                  });
                }).onFailure(ex -> {
                  promise2.tryFail(ex);
                });
              }));
            });
            Optional.ofNullable(o.getHostInventoryIds()).orElse(Arrays.asList()).stream().filter(oVal -> oVal != null && !setHostInventoryIdsValues.contains(oVal.toString())).forEach(val -> {
              futures2.add(Future.future(promise2 -> {
                searchModel(siteRequest).query(HostInventory.varIndexedHostInventory(HostInventory.VAR_tenantResource), HostInventory.class, val).onSuccess(o3 -> {
                  String solrId2 = Optional.ofNullable(o3).map(o4 -> o4.getSolrId()).filter(solrId3 -> !solrIds.contains(solrId3)).orElse(null);
                  Long pk2 = Optional.ofNullable(o3).map(o4 -> o4.getPk()).orElse(null);
                  if(solrId2 != null) {
                    solrIds.add(solrId2);
                    classes.add("HostInventory");
                  }
                  sql(siteRequest).update(HostInventory.class, pk2).setToNull(HostInventory.VAR_tenantResource, TenantIntent.class, solrId2).onSuccess(a -> {
                    promise2.complete();
                  }).onFailure(ex -> {
                    promise2.tryFail(ex);
                  });
                }).onFailure(ex -> {
                  promise2.tryFail(ex);
                });
              }));
            });
            break;
          case "addAllHostInventoryIds":
            JsonArray addAllHostInventoryIdsValues = Optional.ofNullable(jsonObject.getJsonArray(entityVar)).orElse(new JsonArray());
            addAllHostInventoryIdsValues.stream().map(oVal -> oVal.toString()).forEach(val -> {
              futures2.add(Future.future(promise2 -> {
                searchModel(siteRequest).query(HostInventory.varIndexedHostInventory(HostInventory.VAR_tenantResource), HostInventory.class, val).onSuccess(o3 -> {
                  String solrId2 = Optional.ofNullable(o3).map(o4 -> o4.getSolrId()).filter(solrId3 -> !solrIds.contains(solrId3)).orElse(null);
                  Long pk2 = Optional.ofNullable(o3).map(o4 -> o4.getPk()).orElse(null);
                  if(solrId2 != null) {
                    solrIds.add(solrId2);
                    classes.add("HostInventory");
                  }
                  sql(siteRequest).update(HostInventory.class, pk2).set(HostInventory.VAR_tenantResource, TenantIntent.class, o.getSolrId(), val).onSuccess(a -> {
                    promise2.complete();
                  }).onFailure(ex -> {
                    promise2.tryFail(ex);
                  });
                }).onFailure(ex -> {
                  promise2.tryFail(ex);
                });
              }));
            });
            break;
          case "addHostInventoryIds":
            Optional.ofNullable(jsonObject.getString(entityVar)).ifPresent(val -> {
              futures2.add(Future.future(promise2 -> {
                searchModel(siteRequest).query(HostInventory.varIndexedHostInventory(HostInventory.VAR_tenantResource), HostInventory.class, val).onSuccess(o3 -> {
                  String solrId2 = Optional.ofNullable(o3).map(o4 -> o4.getSolrId()).filter(solrId3 -> !solrIds.contains(solrId3)).orElse(null);
                  Long pk2 = Optional.ofNullable(o3).map(o4 -> o4.getPk()).orElse(null);
                  if(solrId2 != null) {
                    solrIds.add(solrId2);
                    classes.add("HostInventory");
                  }
                  sql(siteRequest).update(HostInventory.class, pk2).set(HostInventory.VAR_tenantResource, TenantIntent.class, o.getSolrId(), val).onSuccess(a -> {
                    promise2.complete();
                  }).onFailure(ex -> {
                    promise2.tryFail(ex);
                  });
                }).onFailure(ex -> {
                  promise2.tryFail(ex);
                });
              }));
            });
            break;
          case "removeHostInventoryIds":
            Optional.ofNullable(jsonObject.getString(entityVar)).ifPresent(val -> {
              futures2.add(Future.future(promise2 -> {
                searchModel(siteRequest).query(HostInventory.varIndexedHostInventory(HostInventory.VAR_tenantResource), HostInventory.class, val).onSuccess(o3 -> {
                  Long pk2 = Optional.ofNullable(o3).map(o4 -> o4.getPk()).orElse(null);
                  sql(siteRequest).update(HostInventory.class, pk2).setToNull(HostInventory.VAR_tenantResource, TenantIntent.class, null).onSuccess(a -> {
                    promise2.complete();
                  }).onFailure(ex -> {
                    promise2.tryFail(ex);
                  });
                }).onFailure(ex -> {
                  promise2.tryFail(ex);
                });
              }));
            });
            break;
          case "setTenantId":
              o2.setTenantId(jsonObject.getString(entityVar));
              if(bParams.size() > 0)
                bSql.append(", ");
              bSql.append(TenantIntent.VAR_tenantId + "=$" + num);
              num++;
              bParams.add(o2.sqlTenantId());
            break;
          case "setArchived":
              o2.setArchived(jsonObject.getString(entityVar));
              if(bParams.size() > 0)
                bSql.append(", ");
              bSql.append(TenantIntent.VAR_archived + "=$" + num);
              num++;
              bParams.add(o2.sqlArchived());
            break;
          case "setAnsibleProjectIds":
            JsonArray setAnsibleProjectIdsValues = Optional.ofNullable(jsonObject.getJsonArray(entityVar)).orElse(new JsonArray());
            setAnsibleProjectIdsValues.stream().map(oVal -> oVal.toString()).forEach(val -> {
              futures2.add(Future.future(promise2 -> {
                searchModel(siteRequest).query(AnsibleProject.varIndexedAnsibleProject(AnsibleProject.VAR_tenantResource), AnsibleProject.class, val).onSuccess(o3 -> {
                  String solrId2 = Optional.ofNullable(o3).map(o4 -> o4.getSolrId()).filter(solrId3 -> !solrIds.contains(solrId3)).orElse(null);
                  Long pk2 = Optional.ofNullable(o3).map(o4 -> o4.getPk()).orElse(null);
                  if(solrId2 != null) {
                    solrIds.add(solrId2);
                    classes.add("AnsibleProject");
                  }
                  sql(siteRequest).update(AnsibleProject.class, pk2).set(AnsibleProject.VAR_tenantResource, TenantIntent.class, o.getSolrId(), val).onSuccess(a -> {
                    promise2.complete();
                  }).onFailure(ex -> {
                    promise2.tryFail(ex);
                  });
                }).onFailure(ex -> {
                  promise2.tryFail(ex);
                });
              }));
            });
            Optional.ofNullable(o.getAnsibleProjectIds()).orElse(Arrays.asList()).stream().filter(oVal -> oVal != null && !setAnsibleProjectIdsValues.contains(oVal.toString())).forEach(val -> {
              futures2.add(Future.future(promise2 -> {
                searchModel(siteRequest).query(AnsibleProject.varIndexedAnsibleProject(AnsibleProject.VAR_tenantResource), AnsibleProject.class, val).onSuccess(o3 -> {
                  String solrId2 = Optional.ofNullable(o3).map(o4 -> o4.getSolrId()).filter(solrId3 -> !solrIds.contains(solrId3)).orElse(null);
                  Long pk2 = Optional.ofNullable(o3).map(o4 -> o4.getPk()).orElse(null);
                  if(solrId2 != null) {
                    solrIds.add(solrId2);
                    classes.add("AnsibleProject");
                  }
                  sql(siteRequest).update(AnsibleProject.class, pk2).setToNull(AnsibleProject.VAR_tenantResource, TenantIntent.class, solrId2).onSuccess(a -> {
                    promise2.complete();
                  }).onFailure(ex -> {
                    promise2.tryFail(ex);
                  });
                }).onFailure(ex -> {
                  promise2.tryFail(ex);
                });
              }));
            });
            break;
          case "addAllAnsibleProjectIds":
            JsonArray addAllAnsibleProjectIdsValues = Optional.ofNullable(jsonObject.getJsonArray(entityVar)).orElse(new JsonArray());
            addAllAnsibleProjectIdsValues.stream().map(oVal -> oVal.toString()).forEach(val -> {
              futures2.add(Future.future(promise2 -> {
                searchModel(siteRequest).query(AnsibleProject.varIndexedAnsibleProject(AnsibleProject.VAR_tenantResource), AnsibleProject.class, val).onSuccess(o3 -> {
                  String solrId2 = Optional.ofNullable(o3).map(o4 -> o4.getSolrId()).filter(solrId3 -> !solrIds.contains(solrId3)).orElse(null);
                  Long pk2 = Optional.ofNullable(o3).map(o4 -> o4.getPk()).orElse(null);
                  if(solrId2 != null) {
                    solrIds.add(solrId2);
                    classes.add("AnsibleProject");
                  }
                  sql(siteRequest).update(AnsibleProject.class, pk2).set(AnsibleProject.VAR_tenantResource, TenantIntent.class, o.getSolrId(), val).onSuccess(a -> {
                    promise2.complete();
                  }).onFailure(ex -> {
                    promise2.tryFail(ex);
                  });
                }).onFailure(ex -> {
                  promise2.tryFail(ex);
                });
              }));
            });
            break;
          case "addAnsibleProjectIds":
            Optional.ofNullable(jsonObject.getString(entityVar)).ifPresent(val -> {
              futures2.add(Future.future(promise2 -> {
                searchModel(siteRequest).query(AnsibleProject.varIndexedAnsibleProject(AnsibleProject.VAR_tenantResource), AnsibleProject.class, val).onSuccess(o3 -> {
                  String solrId2 = Optional.ofNullable(o3).map(o4 -> o4.getSolrId()).filter(solrId3 -> !solrIds.contains(solrId3)).orElse(null);
                  Long pk2 = Optional.ofNullable(o3).map(o4 -> o4.getPk()).orElse(null);
                  if(solrId2 != null) {
                    solrIds.add(solrId2);
                    classes.add("AnsibleProject");
                  }
                  sql(siteRequest).update(AnsibleProject.class, pk2).set(AnsibleProject.VAR_tenantResource, TenantIntent.class, o.getSolrId(), val).onSuccess(a -> {
                    promise2.complete();
                  }).onFailure(ex -> {
                    promise2.tryFail(ex);
                  });
                }).onFailure(ex -> {
                  promise2.tryFail(ex);
                });
              }));
            });
            break;
          case "removeAnsibleProjectIds":
            Optional.ofNullable(jsonObject.getString(entityVar)).ifPresent(val -> {
              futures2.add(Future.future(promise2 -> {
                searchModel(siteRequest).query(AnsibleProject.varIndexedAnsibleProject(AnsibleProject.VAR_tenantResource), AnsibleProject.class, val).onSuccess(o3 -> {
                  Long pk2 = Optional.ofNullable(o3).map(o4 -> o4.getPk()).orElse(null);
                  sql(siteRequest).update(AnsibleProject.class, pk2).setToNull(AnsibleProject.VAR_tenantResource, TenantIntent.class, null).onSuccess(a -> {
                    promise2.complete();
                  }).onFailure(ex -> {
                    promise2.tryFail(ex);
                  });
                }).onFailure(ex -> {
                  promise2.tryFail(ex);
                });
              }));
            });
            break;
          case "setTenantResource":
              o2.setTenantResource(jsonObject.getString(entityVar));
              if(bParams.size() > 0)
                bSql.append(", ");
              bSql.append(TenantIntent.VAR_tenantResource + "=$" + num);
              num++;
              bParams.add(o2.sqlTenantResource());
            break;
          case "setCreatedByEmail":
              o2.setCreatedByEmail(jsonObject.getString(entityVar));
              if(bParams.size() > 0)
                bSql.append(", ");
              bSql.append(TenantIntent.VAR_createdByEmail + "=$" + num);
              num++;
              bParams.add(o2.sqlCreatedByEmail());
            break;
          case "setCreatedByUserId":
              o2.setCreatedByUserId(jsonObject.getString(entityVar));
              if(bParams.size() > 0)
                bSql.append(", ");
              bSql.append(TenantIntent.VAR_createdByUserId + "=$" + num);
              num++;
              bParams.add(o2.sqlCreatedByUserId());
            break;
          case "setCreatedByFullName":
              o2.setCreatedByFullName(jsonObject.getString(entityVar));
              if(bParams.size() > 0)
                bSql.append(", ");
              bSql.append(TenantIntent.VAR_createdByFullName + "=$" + num);
              num++;
              bParams.add(o2.sqlCreatedByFullName());
            break;
          case "setSessionId":
              o2.setSessionId(jsonObject.getString(entityVar));
              if(bParams.size() > 0)
                bSql.append(", ");
              bSql.append(TenantIntent.VAR_sessionId + "=$" + num);
              num++;
              bParams.add(o2.sqlSessionId());
            break;
          case "setCreatedVia":
              o2.setCreatedVia(jsonObject.getString(entityVar));
              if(bParams.size() > 0)
                bSql.append(", ");
              bSql.append(TenantIntent.VAR_createdVia + "=$" + num);
              num++;
              bParams.add(o2.sqlCreatedVia());
            break;
          case "setUserKey":
              o2.setUserKey(jsonObject.getString(entityVar));
              if(bParams.size() > 0)
                bSql.append(", ");
              bSql.append(TenantIntent.VAR_userKey + "=$" + num);
              num++;
              bParams.add(o2.sqlUserKey());
            break;
          case "setIntentState":
              o2.setIntentState(jsonObject.getString(entityVar));
              if(bParams.size() > 0)
                bSql.append(", ");
              bSql.append(TenantIntent.VAR_intentState + "=$" + num);
              num++;
              bParams.add(o2.sqlIntentState());
            break;
          case "setRequestedState":
              o2.setRequestedState(jsonObject.getString(entityVar));
              if(bParams.size() > 0)
                bSql.append(", ");
              bSql.append(TenantIntent.VAR_requestedState + "=$" + num);
              num++;
              bParams.add(o2.sqlRequestedState());
            break;
          case "setRealizedState":
              o2.setRealizedState(jsonObject.getString(entityVar));
              if(bParams.size() > 0)
                bSql.append(", ");
              bSql.append(TenantIntent.VAR_realizedState + "=$" + num);
              num++;
              bParams.add(o2.sqlRealizedState());
            break;
          case "setObjectTitle":
              o2.setObjectTitle(jsonObject.getString(entityVar));
              if(bParams.size() > 0)
                bSql.append(", ");
              bSql.append(TenantIntent.VAR_objectTitle + "=$" + num);
              num++;
              bParams.add(o2.sqlObjectTitle());
            break;
          case "setDescription":
              o2.setDescription(jsonObject.getString(entityVar));
              if(bParams.size() > 0)
                bSql.append(", ");
              bSql.append(TenantIntent.VAR_description + "=$" + num);
              num++;
              bParams.add(o2.sqlDescription());
            break;
          case "setDisplayPage":
              o2.setDisplayPage(jsonObject.getString(entityVar));
              if(bParams.size() > 0)
                bSql.append(", ");
              bSql.append(TenantIntent.VAR_displayPage + "=$" + num);
              num++;
              bParams.add(o2.sqlDisplayPage());
            break;
          case "setRequested":
            JsonArray setRequestedValues = Optional.ofNullable(jsonObject.getJsonArray(entityVar)).orElse(new JsonArray());
            setRequestedValues.stream().map(oVal -> oVal.toString()).forEach(val -> {
              futures2.add(Future.future(promise2 -> {
                searchModel(siteRequest).query(TenantRequested.varIndexedTenantRequested(TenantRequested.VAR_tenantResource), TenantRequested.class, val).onSuccess(o3 -> {
                  String solrId2 = Optional.ofNullable(o3).map(o4 -> o4.getSolrId()).filter(solrId3 -> !solrIds.contains(solrId3)).orElse(null);
                  Long pk2 = Optional.ofNullable(o3).map(o4 -> o4.getPk()).orElse(null);
                  if(solrId2 != null) {
                    solrIds.add(solrId2);
                    classes.add("TenantRequested");
                  }
                  sql(siteRequest).update(TenantRequested.class, pk2).set(TenantRequested.VAR_tenantResource, TenantIntent.class, o.getSolrId(), val).onSuccess(a -> {
                    promise2.complete();
                  }).onFailure(ex -> {
                    promise2.tryFail(ex);
                  });
                }).onFailure(ex -> {
                  promise2.tryFail(ex);
                });
              }));
            });
            Optional.ofNullable(o.getRequested()).orElse(Arrays.asList()).stream().filter(oVal -> oVal != null && !setRequestedValues.contains(oVal.toString())).forEach(val -> {
              futures2.add(Future.future(promise2 -> {
                searchModel(siteRequest).query(TenantRequested.varIndexedTenantRequested(TenantRequested.VAR_tenantResource), TenantRequested.class, val).onSuccess(o3 -> {
                  String solrId2 = Optional.ofNullable(o3).map(o4 -> o4.getSolrId()).filter(solrId3 -> !solrIds.contains(solrId3)).orElse(null);
                  Long pk2 = Optional.ofNullable(o3).map(o4 -> o4.getPk()).orElse(null);
                  if(solrId2 != null) {
                    solrIds.add(solrId2);
                    classes.add("TenantRequested");
                  }
                  sql(siteRequest).update(TenantRequested.class, pk2).setToNull(TenantRequested.VAR_tenantResource, TenantIntent.class, solrId2).onSuccess(a -> {
                    promise2.complete();
                  }).onFailure(ex -> {
                    promise2.tryFail(ex);
                  });
                }).onFailure(ex -> {
                  promise2.tryFail(ex);
                });
              }));
            });
            break;
          case "addAllRequested":
            JsonArray addAllRequestedValues = Optional.ofNullable(jsonObject.getJsonArray(entityVar)).orElse(new JsonArray());
            addAllRequestedValues.stream().map(oVal -> oVal.toString()).forEach(val -> {
              futures2.add(Future.future(promise2 -> {
                searchModel(siteRequest).query(TenantRequested.varIndexedTenantRequested(TenantRequested.VAR_tenantResource), TenantRequested.class, val).onSuccess(o3 -> {
                  String solrId2 = Optional.ofNullable(o3).map(o4 -> o4.getSolrId()).filter(solrId3 -> !solrIds.contains(solrId3)).orElse(null);
                  Long pk2 = Optional.ofNullable(o3).map(o4 -> o4.getPk()).orElse(null);
                  if(solrId2 != null) {
                    solrIds.add(solrId2);
                    classes.add("TenantRequested");
                  }
                  sql(siteRequest).update(TenantRequested.class, pk2).set(TenantRequested.VAR_tenantResource, TenantIntent.class, o.getSolrId(), val).onSuccess(a -> {
                    promise2.complete();
                  }).onFailure(ex -> {
                    promise2.tryFail(ex);
                  });
                }).onFailure(ex -> {
                  promise2.tryFail(ex);
                });
              }));
            });
            break;
          case "addRequested":
            Optional.ofNullable(jsonObject.getString(entityVar)).ifPresent(val -> {
              futures2.add(Future.future(promise2 -> {
                searchModel(siteRequest).query(TenantRequested.varIndexedTenantRequested(TenantRequested.VAR_tenantResource), TenantRequested.class, val).onSuccess(o3 -> {
                  String solrId2 = Optional.ofNullable(o3).map(o4 -> o4.getSolrId()).filter(solrId3 -> !solrIds.contains(solrId3)).orElse(null);
                  Long pk2 = Optional.ofNullable(o3).map(o4 -> o4.getPk()).orElse(null);
                  if(solrId2 != null) {
                    solrIds.add(solrId2);
                    classes.add("TenantRequested");
                  }
                  sql(siteRequest).update(TenantRequested.class, pk2).set(TenantRequested.VAR_tenantResource, TenantIntent.class, o.getSolrId(), val).onSuccess(a -> {
                    promise2.complete();
                  }).onFailure(ex -> {
                    promise2.tryFail(ex);
                  });
                }).onFailure(ex -> {
                  promise2.tryFail(ex);
                });
              }));
            });
            break;
          case "removeRequested":
            Optional.ofNullable(jsonObject.getString(entityVar)).ifPresent(val -> {
              futures2.add(Future.future(promise2 -> {
                searchModel(siteRequest).query(TenantRequested.varIndexedTenantRequested(TenantRequested.VAR_tenantResource), TenantRequested.class, val).onSuccess(o3 -> {
                  Long pk2 = Optional.ofNullable(o3).map(o4 -> o4.getPk()).orElse(null);
                  sql(siteRequest).update(TenantRequested.class, pk2).setToNull(TenantRequested.VAR_tenantResource, TenantIntent.class, null).onSuccess(a -> {
                    promise2.complete();
                  }).onFailure(ex -> {
                    promise2.tryFail(ex);
                  });
                }).onFailure(ex -> {
                  promise2.tryFail(ex);
                });
              }));
            });
            break;
          case "setEditPage":
              o2.setEditPage(jsonObject.getString(entityVar));
              if(bParams.size() > 0)
                bSql.append(", ");
              bSql.append(TenantIntent.VAR_editPage + "=$" + num);
              num++;
              bParams.add(o2.sqlEditPage());
            break;
          case "setLocked":
              o2.setLocked(jsonObject.getString(entityVar));
              if(bParams.size() > 0)
                bSql.append(", ");
              bSql.append(TenantIntent.VAR_locked + "=$" + num);
              num++;
              bParams.add(o2.sqlLocked());
            break;
          case "setUserPage":
              o2.setUserPage(jsonObject.getString(entityVar));
              if(bParams.size() > 0)
                bSql.append(", ");
              bSql.append(TenantIntent.VAR_userPage + "=$" + num);
              num++;
              bParams.add(o2.sqlUserPage());
            break;
          case "setDcmDiscovered":
            JsonArray setDcmDiscoveredValues = Optional.ofNullable(jsonObject.getJsonArray(entityVar)).orElse(new JsonArray());
            setDcmDiscoveredValues.stream().map(oVal -> oVal.toString()).forEach(val -> {
              futures2.add(Future.future(promise2 -> {
                searchModel(siteRequest).query(TenantDiscovered.varIndexedTenantDiscovered(TenantDiscovered.VAR_tenantResource), TenantDiscovered.class, val).onSuccess(o3 -> {
                  String solrId2 = Optional.ofNullable(o3).map(o4 -> o4.getSolrId()).filter(solrId3 -> !solrIds.contains(solrId3)).orElse(null);
                  Long pk2 = Optional.ofNullable(o3).map(o4 -> o4.getPk()).orElse(null);
                  if(solrId2 != null) {
                    solrIds.add(solrId2);
                    classes.add("TenantDiscovered");
                  }
                  sql(siteRequest).update(TenantDiscovered.class, pk2).set(TenantDiscovered.VAR_tenantResource, TenantIntent.class, o.getSolrId(), val).onSuccess(a -> {
                    promise2.complete();
                  }).onFailure(ex -> {
                    promise2.tryFail(ex);
                  });
                }).onFailure(ex -> {
                  promise2.tryFail(ex);
                });
              }));
            });
            Optional.ofNullable(o.getDcmDiscovered()).orElse(Arrays.asList()).stream().filter(oVal -> oVal != null && !setDcmDiscoveredValues.contains(oVal.toString())).forEach(val -> {
              futures2.add(Future.future(promise2 -> {
                searchModel(siteRequest).query(TenantDiscovered.varIndexedTenantDiscovered(TenantDiscovered.VAR_tenantResource), TenantDiscovered.class, val).onSuccess(o3 -> {
                  String solrId2 = Optional.ofNullable(o3).map(o4 -> o4.getSolrId()).filter(solrId3 -> !solrIds.contains(solrId3)).orElse(null);
                  Long pk2 = Optional.ofNullable(o3).map(o4 -> o4.getPk()).orElse(null);
                  if(solrId2 != null) {
                    solrIds.add(solrId2);
                    classes.add("TenantDiscovered");
                  }
                  sql(siteRequest).update(TenantDiscovered.class, pk2).setToNull(TenantDiscovered.VAR_tenantResource, TenantIntent.class, solrId2).onSuccess(a -> {
                    promise2.complete();
                  }).onFailure(ex -> {
                    promise2.tryFail(ex);
                  });
                }).onFailure(ex -> {
                  promise2.tryFail(ex);
                });
              }));
            });
            break;
          case "addAllDcmDiscovered":
            JsonArray addAllDcmDiscoveredValues = Optional.ofNullable(jsonObject.getJsonArray(entityVar)).orElse(new JsonArray());
            addAllDcmDiscoveredValues.stream().map(oVal -> oVal.toString()).forEach(val -> {
              futures2.add(Future.future(promise2 -> {
                searchModel(siteRequest).query(TenantDiscovered.varIndexedTenantDiscovered(TenantDiscovered.VAR_tenantResource), TenantDiscovered.class, val).onSuccess(o3 -> {
                  String solrId2 = Optional.ofNullable(o3).map(o4 -> o4.getSolrId()).filter(solrId3 -> !solrIds.contains(solrId3)).orElse(null);
                  Long pk2 = Optional.ofNullable(o3).map(o4 -> o4.getPk()).orElse(null);
                  if(solrId2 != null) {
                    solrIds.add(solrId2);
                    classes.add("TenantDiscovered");
                  }
                  sql(siteRequest).update(TenantDiscovered.class, pk2).set(TenantDiscovered.VAR_tenantResource, TenantIntent.class, o.getSolrId(), val).onSuccess(a -> {
                    promise2.complete();
                  }).onFailure(ex -> {
                    promise2.tryFail(ex);
                  });
                }).onFailure(ex -> {
                  promise2.tryFail(ex);
                });
              }));
            });
            break;
          case "addDcmDiscovered":
            Optional.ofNullable(jsonObject.getString(entityVar)).ifPresent(val -> {
              futures2.add(Future.future(promise2 -> {
                searchModel(siteRequest).query(TenantDiscovered.varIndexedTenantDiscovered(TenantDiscovered.VAR_tenantResource), TenantDiscovered.class, val).onSuccess(o3 -> {
                  String solrId2 = Optional.ofNullable(o3).map(o4 -> o4.getSolrId()).filter(solrId3 -> !solrIds.contains(solrId3)).orElse(null);
                  Long pk2 = Optional.ofNullable(o3).map(o4 -> o4.getPk()).orElse(null);
                  if(solrId2 != null) {
                    solrIds.add(solrId2);
                    classes.add("TenantDiscovered");
                  }
                  sql(siteRequest).update(TenantDiscovered.class, pk2).set(TenantDiscovered.VAR_tenantResource, TenantIntent.class, o.getSolrId(), val).onSuccess(a -> {
                    promise2.complete();
                  }).onFailure(ex -> {
                    promise2.tryFail(ex);
                  });
                }).onFailure(ex -> {
                  promise2.tryFail(ex);
                });
              }));
            });
            break;
          case "removeDcmDiscovered":
            Optional.ofNullable(jsonObject.getString(entityVar)).ifPresent(val -> {
              futures2.add(Future.future(promise2 -> {
                searchModel(siteRequest).query(TenantDiscovered.varIndexedTenantDiscovered(TenantDiscovered.VAR_tenantResource), TenantDiscovered.class, val).onSuccess(o3 -> {
                  Long pk2 = Optional.ofNullable(o3).map(o4 -> o4.getPk()).orElse(null);
                  sql(siteRequest).update(TenantDiscovered.class, pk2).setToNull(TenantDiscovered.VAR_tenantResource, TenantIntent.class, null).onSuccess(a -> {
                    promise2.complete();
                  }).onFailure(ex -> {
                    promise2.tryFail(ex);
                  });
                }).onFailure(ex -> {
                  promise2.tryFail(ex);
                });
              }));
            });
            break;
          case "setDownload":
              o2.setDownload(jsonObject.getString(entityVar));
              if(bParams.size() > 0)
                bSql.append(", ");
              bSql.append(TenantIntent.VAR_download + "=$" + num);
              num++;
              bParams.add(o2.sqlDownload());
            break;
          case "setDcmRealized":
            JsonArray setDcmRealizedValues = Optional.ofNullable(jsonObject.getJsonArray(entityVar)).orElse(new JsonArray());
            setDcmRealizedValues.stream().map(oVal -> oVal.toString()).forEach(val -> {
              futures2.add(Future.future(promise2 -> {
                searchModel(siteRequest).query(TenantRealized.varIndexedTenantRealized(TenantRealized.VAR_tenantResource), TenantRealized.class, val).onSuccess(o3 -> {
                  String solrId2 = Optional.ofNullable(o3).map(o4 -> o4.getSolrId()).filter(solrId3 -> !solrIds.contains(solrId3)).orElse(null);
                  Long pk2 = Optional.ofNullable(o3).map(o4 -> o4.getPk()).orElse(null);
                  if(solrId2 != null) {
                    solrIds.add(solrId2);
                    classes.add("TenantRealized");
                  }
                  sql(siteRequest).update(TenantRealized.class, pk2).set(TenantRealized.VAR_tenantResource, TenantIntent.class, o.getSolrId(), val).onSuccess(a -> {
                    promise2.complete();
                  }).onFailure(ex -> {
                    promise2.tryFail(ex);
                  });
                }).onFailure(ex -> {
                  promise2.tryFail(ex);
                });
              }));
            });
            Optional.ofNullable(o.getDcmRealized()).orElse(Arrays.asList()).stream().filter(oVal -> oVal != null && !setDcmRealizedValues.contains(oVal.toString())).forEach(val -> {
              futures2.add(Future.future(promise2 -> {
                searchModel(siteRequest).query(TenantRealized.varIndexedTenantRealized(TenantRealized.VAR_tenantResource), TenantRealized.class, val).onSuccess(o3 -> {
                  String solrId2 = Optional.ofNullable(o3).map(o4 -> o4.getSolrId()).filter(solrId3 -> !solrIds.contains(solrId3)).orElse(null);
                  Long pk2 = Optional.ofNullable(o3).map(o4 -> o4.getPk()).orElse(null);
                  if(solrId2 != null) {
                    solrIds.add(solrId2);
                    classes.add("TenantRealized");
                  }
                  sql(siteRequest).update(TenantRealized.class, pk2).setToNull(TenantRealized.VAR_tenantResource, TenantIntent.class, solrId2).onSuccess(a -> {
                    promise2.complete();
                  }).onFailure(ex -> {
                    promise2.tryFail(ex);
                  });
                }).onFailure(ex -> {
                  promise2.tryFail(ex);
                });
              }));
            });
            break;
          case "addAllDcmRealized":
            JsonArray addAllDcmRealizedValues = Optional.ofNullable(jsonObject.getJsonArray(entityVar)).orElse(new JsonArray());
            addAllDcmRealizedValues.stream().map(oVal -> oVal.toString()).forEach(val -> {
              futures2.add(Future.future(promise2 -> {
                searchModel(siteRequest).query(TenantRealized.varIndexedTenantRealized(TenantRealized.VAR_tenantResource), TenantRealized.class, val).onSuccess(o3 -> {
                  String solrId2 = Optional.ofNullable(o3).map(o4 -> o4.getSolrId()).filter(solrId3 -> !solrIds.contains(solrId3)).orElse(null);
                  Long pk2 = Optional.ofNullable(o3).map(o4 -> o4.getPk()).orElse(null);
                  if(solrId2 != null) {
                    solrIds.add(solrId2);
                    classes.add("TenantRealized");
                  }
                  sql(siteRequest).update(TenantRealized.class, pk2).set(TenantRealized.VAR_tenantResource, TenantIntent.class, o.getSolrId(), val).onSuccess(a -> {
                    promise2.complete();
                  }).onFailure(ex -> {
                    promise2.tryFail(ex);
                  });
                }).onFailure(ex -> {
                  promise2.tryFail(ex);
                });
              }));
            });
            break;
          case "addDcmRealized":
            Optional.ofNullable(jsonObject.getString(entityVar)).ifPresent(val -> {
              futures2.add(Future.future(promise2 -> {
                searchModel(siteRequest).query(TenantRealized.varIndexedTenantRealized(TenantRealized.VAR_tenantResource), TenantRealized.class, val).onSuccess(o3 -> {
                  String solrId2 = Optional.ofNullable(o3).map(o4 -> o4.getSolrId()).filter(solrId3 -> !solrIds.contains(solrId3)).orElse(null);
                  Long pk2 = Optional.ofNullable(o3).map(o4 -> o4.getPk()).orElse(null);
                  if(solrId2 != null) {
                    solrIds.add(solrId2);
                    classes.add("TenantRealized");
                  }
                  sql(siteRequest).update(TenantRealized.class, pk2).set(TenantRealized.VAR_tenantResource, TenantIntent.class, o.getSolrId(), val).onSuccess(a -> {
                    promise2.complete();
                  }).onFailure(ex -> {
                    promise2.tryFail(ex);
                  });
                }).onFailure(ex -> {
                  promise2.tryFail(ex);
                });
              }));
            });
            break;
          case "removeDcmRealized":
            Optional.ofNullable(jsonObject.getString(entityVar)).ifPresent(val -> {
              futures2.add(Future.future(promise2 -> {
                searchModel(siteRequest).query(TenantRealized.varIndexedTenantRealized(TenantRealized.VAR_tenantResource), TenantRealized.class, val).onSuccess(o3 -> {
                  Long pk2 = Optional.ofNullable(o3).map(o4 -> o4.getPk()).orElse(null);
                  sql(siteRequest).update(TenantRealized.class, pk2).setToNull(TenantRealized.VAR_tenantResource, TenantIntent.class, null).onSuccess(a -> {
                    promise2.complete();
                  }).onFailure(ex -> {
                    promise2.tryFail(ex);
                  });
                }).onFailure(ex -> {
                  promise2.tryFail(ex);
                });
              }));
            });
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
            RuntimeException ex2 = new RuntimeException("value TenantIntent failed", ex);
            LOG.error(String.format("relateTenantIntent failed. "), ex2);
            a.handle(Future.failedFuture(ex2));
          });
        }));
      }
      CompositeFuture.all(futures1).onSuccess(a -> {
        CompositeFuture.all(futures2).onSuccess(b -> {
          TenantIntent o3 = new TenantIntent();
          o3.setSiteRequest_(o.getSiteRequest_());
          o3.setPk(pk);
          promise.complete(o3);
        }).onFailure(ex -> {
          LOG.error(String.format("sqlPATCHTenantIntent failed. "), ex);
          promise.tryFail(ex);
        });
      }).onFailure(ex -> {
        LOG.error(String.format("sqlPATCHTenantIntent failed. "), ex);
        promise.tryFail(ex);
      });
    } catch(Exception ex) {
      LOG.error(String.format("sqlPATCHTenantIntent failed. "), ex);
      promise.tryFail(ex);
    }
    return promise.future();
  }

  public Future<ServiceResponse> response200PATCHTenantIntent(SiteRequest siteRequest) {
    Promise<ServiceResponse> promise = Promise.promise();
    try {
      JsonObject json = new JsonObject();
      promise.complete(ServiceResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily())));
    } catch(Exception ex) {
      LOG.error(String.format("response200PATCHTenantIntent failed. "), ex);
      promise.tryFail(ex);
    }
    return promise.future();
  }

  // POST //

  @Override
  public void postTenantIntent(JsonObject body, ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler) {
    LOG.debug(String.format("postTenantIntent started. "));
    Boolean classPublicRead = false;
    user(serviceRequest, SiteRequest.class, SiteUser.class, SiteUser.getClassApiAddress(), "postSiteUserFuture", "patchSiteUserFuture", classPublicRead).onSuccess(siteRequest -> {
      try {
        siteRequest.setLang("enUS");
        String tenantResource = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("tenantResource");
        String TENANT = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("TENANT");
        List<String> groups = Optional.ofNullable(siteRequest.getGroups()).orElse(new ArrayList<>());
        MultiMap form = MultiMap.caseInsensitiveMultiMap();
        form.add("grant_type", "urn:ietf:params:oauth:grant-type:uma-ticket");
        form.add("audience", config.getString(ComputateConfigKeys.AUTH_CLIENT));
        form.add("response_mode", "permissions");
        form.add("permission", String.format("%s#%s", TenantIntent.CLASS_AUTH_RESOURCE, "GET"));
        form.add("permission", String.format("%s#%s", TenantIntent.CLASS_AUTH_RESOURCE, "POST"));
        form.add("permission", String.format("%s#%s", TenantIntent.CLASS_AUTH_RESOURCE, "PATCH"));
        form.add("permission", String.format("%s#%s", TenantIntent.CLASS_AUTH_RESOURCE, "DELETE"));
        form.add("permission", String.format("%s#%s", TenantIntent.CLASS_AUTH_RESOURCE, "Admin"));
        form.add("permission", String.format("%s#%s", TenantIntent.CLASS_AUTH_RESOURCE, "SuperAdmin"));
        if(tenantResource != null)
          form.add("permission", String.format("%s#%s", tenantResource, "POST"));
        groups.stream().map(group -> {
              Matcher mPermission = Pattern.compile("^/(.*-?TENANT-([a-z0-9\\-]+))-(\\w+)$").matcher(group);
              return mPermission.find() ? mPermission : null;
            }).filter(v -> v != null).forEach(mPermission -> {
              form.add("permission", String.format("%s#%s", mPermission.group(1), mPermission.group(3)));
            });
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
            JsonArray scopes = authorizationDecisionBody.stream().map(o -> (JsonObject)o).filter(o -> "TENANT".equals(o.getString("rsname"))).findFirst().map(decision -> ((JsonObject)decision).getJsonArray("scopes")).orElse(new JsonArray());
            if(!scopes.contains("POST")) {
            List<String> fqs = new ArrayList<>();
            authorizationDecisionBody.stream().map(o -> (JsonObject)o).filter(permission -> {
                  Matcher mPermission = Pattern.compile("^(.*-?TENANT-([a-z0-9\\-]+))$").matcher(permission.getString("rsname"));
                  return permission.getJsonArray("scopes").contains("POST")
                      && mPermission.find();
                }).forEach(permission -> {
                  fqs.add(String.format("%s:%s", "tenantResource", permission.getString("rsname")));
                  permission.getJsonArray("scopes").stream().map(s -> (String)s).forEach(scope -> {
                    if(!scopes.contains(scope))
                      scopes.add(scope);
                  });
                });
              JsonObject authParams = siteRequest.getServiceRequest().getParams();
              JsonObject authQuery = authParams.getJsonObject("query");
              if(authQuery == null) {
                authQuery = new JsonObject();
                authParams.put("query", authQuery);
              }
              JsonArray fq = authQuery.getJsonArray("fq");
              if(fq == null) {
                fq = new JsonArray();
                authQuery.put("fq", fq);
              }
              if(fqs.size() > 0) {
                fq.add(fqs.stream().collect(Collectors.joining(" OR ")));
                if(!scopes.contains("POST"))
                  scopes.add("POST");
                siteRequest.setFilteredScope(true);
              }
            }
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
              eventBus.publish("websocketTenantIntent", JsonObject.mapFrom(apiRequest).toString());
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
              eventBus.request(TenantIntent.getClassApiAddress(), json, new DeliveryOptions().addHeader("action", "postTenantIntentFuture")).onSuccess(a -> {
                JsonObject responseMessage = (JsonObject)a.body();
                JsonObject responseBody = new JsonObject(Buffer.buffer(JsonUtil.BASE64_DECODER.decode(responseMessage.getString("payload"))));
                apiRequest.setSolrId(responseBody.getString(TenantIntent.VAR_solrId));
                eventHandler.handle(Future.succeededFuture(ServiceResponse.completedWithJson(Buffer.buffer(responseBody.encodePrettily()))));
                LOG.debug(String.format("postTenantIntent succeeded. "));
              }).onFailure(ex -> {
                LOG.error(String.format("postTenantIntent failed. "), ex);
                error(siteRequest, eventHandler, ex);
              });
            }
          } catch(Exception ex) {
            LOG.error(String.format("postTenantIntent failed. "), ex);
            error(null, eventHandler, ex);
          }
        });
      } catch(Exception ex) {
        LOG.error(String.format("postTenantIntent failed. "), ex);
        error(null, eventHandler, ex);
      }
    }).onFailure(ex -> {
      if("Inactive Token".equals(ex.getMessage()) || StringUtils.startsWith(ex.getMessage(), "invalid_grant:")) {
        try {
          eventHandler.handle(Future.succeededFuture(new ServiceResponse(302, "Found", null, MultiMap.caseInsensitiveMultiMap().add(HttpHeaders.LOCATION, "/logout?redirect_uri=" + URLEncoder.encode(serviceRequest.getExtra().getString("uri"), "UTF-8")))));
        } catch(Exception ex2) {
          LOG.error(String.format("postTenantIntent failed. ", ex2));
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
        LOG.error(String.format("postTenantIntent failed. "), ex);
        error(null, eventHandler, ex);
      }
    });
  }

  @Override
  public void postTenantIntentFuture(JsonObject body, ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler) {
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
        postTenantIntentFuture(siteRequest, false).onSuccess(o -> {
          eventHandler.handle(Future.succeededFuture(ServiceResponse.completedWithJson(Buffer.buffer(JsonObject.mapFrom(o).encodePrettily()))));
        }).onFailure(ex -> {
          eventHandler.handle(Future.failedFuture(ex));
        });
      } catch(Throwable ex) {
        LOG.error(String.format("postTenantIntent failed. "), ex);
        error(null, eventHandler, ex);
      }
    }).onFailure(ex -> {
      if("Inactive Token".equals(ex.getMessage()) || StringUtils.startsWith(ex.getMessage(), "invalid_grant:")) {
        try {
          eventHandler.handle(Future.succeededFuture(new ServiceResponse(302, "Found", null, MultiMap.caseInsensitiveMultiMap().add(HttpHeaders.LOCATION, "/logout?redirect_uri=" + URLEncoder.encode(serviceRequest.getExtra().getString("uri"), "UTF-8")))));
        } catch(Exception ex2) {
          LOG.error(String.format("postTenantIntent failed. ", ex2));
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
        LOG.error(String.format("postTenantIntent failed. "), ex);
        error(null, eventHandler, ex);
      }
    });
  }

  public Future<TenantIntent> postTenantIntentFuture(SiteRequest siteRequest, Boolean tenantResource) {
    Promise<TenantIntent> promise = Promise.promise();

    try {
      pgPool.withTransaction(sqlConnection -> {
        Promise<TenantIntent> promise1 = Promise.promise();
        siteRequest.setSqlConnection(sqlConnection);
        varsTenantIntent(siteRequest).onSuccess(a -> {
          createTenantIntent(siteRequest).onSuccess(tenantIntent -> {
            sqlPOSTTenantIntent(tenantIntent, tenantResource).onSuccess(b -> {
              persistTenantIntent(tenantIntent, false).onSuccess(c -> {
                relateTenantIntent(tenantIntent).onSuccess(d -> {
                  indexTenantIntent(tenantIntent).onSuccess(o2 -> {
                    promise1.complete(tenantIntent);
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
      }).compose(tenantIntent -> {
        Promise<TenantIntent> promise2 = Promise.promise();
        refreshTenantIntent(tenantIntent).onSuccess(a -> {
          try {
            ApiRequest apiRequest = siteRequest.getApiRequest_();
            if(apiRequest != null) {
              apiRequest.setNumPATCH(apiRequest.getNumPATCH() + 1);
              tenantIntent.apiRequestTenantIntent();
              eventBus.publish("websocketTenantIntent", JsonObject.mapFrom(apiRequest).toString());
            }
            promise2.complete(tenantIntent);
          } catch(Exception ex) {
            LOG.error(String.format("postTenantIntentFuture failed. "), ex);
            promise2.tryFail(ex);
          }
        }).onFailure(ex -> {
          promise2.tryFail(ex);
        });
        return promise2.future();
      }).onSuccess(tenantIntent -> {
        try {
          ApiRequest apiRequest = siteRequest.getApiRequest_();
          if(apiRequest != null) {
            apiRequest.setNumPATCH(apiRequest.getNumPATCH() + 1);
            tenantIntent.apiRequestTenantIntent();
            eventBus.publish("websocketTenantIntent", JsonObject.mapFrom(apiRequest).toString());
          }
          promise.complete(tenantIntent);
        } catch(Exception ex) {
          LOG.error(String.format("postTenantIntentFuture failed. "), ex);
          promise.tryFail(ex);
        }
      }).onFailure(ex -> {
        promise.tryFail(ex);
      });
    } catch(Exception ex) {
      LOG.error(String.format("postTenantIntentFuture failed. "), ex);
      promise.tryFail(ex);
    }
    return promise.future();
  }

  public Future<TenantIntent> sqlPOSTTenantIntent(TenantIntent o, Boolean inheritPrimaryKey) {
    Promise<TenantIntent> promise = Promise.promise();
    try {
      SiteRequest siteRequest = o.getSiteRequest_();
      ApiRequest apiRequest = siteRequest.getApiRequest_();
      List<String> solrIds = Optional.ofNullable(apiRequest).map(r -> r.getSolrIds()).orElse(new ArrayList<>());
      List<String> classes = Optional.ofNullable(apiRequest).map(r -> r.getClasses()).orElse(new ArrayList<>());
      SqlConnection sqlConnection = siteRequest.getSqlConnection();
      Integer num = 1;
      StringBuilder bSql = new StringBuilder("UPDATE TenantIntent SET ");
      List<Object> bParams = new ArrayList<Object>();
      Long pk = o.getPk();
      JsonObject jsonObject = siteRequest.getJsonObject();
      TenantIntent o2 = new TenantIntent();
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
          case TenantIntent.VAR_hubId:
            o2.setHubId(jsonObject.getString(entityVar));
            if(bParams.size() > 0) {
              bSql.append(", ");
            }
            bSql.append(TenantIntent.VAR_hubId + "=$" + num);
            num++;
            bParams.add(o2.sqlHubId());
            break;
          case TenantIntent.VAR_clusterName:
            o2.setClusterName(jsonObject.getString(entityVar));
            if(bParams.size() > 0) {
              bSql.append(", ");
            }
            bSql.append(TenantIntent.VAR_clusterName + "=$" + num);
            num++;
            bParams.add(o2.sqlClusterName());
            break;
          case TenantIntent.VAR_created:
            o2.setCreated(jsonObject.getString(entityVar));
            if(bParams.size() > 0) {
              bSql.append(", ");
            }
            bSql.append(TenantIntent.VAR_created + "=$" + num);
            num++;
            bParams.add(o2.sqlCreated());
            break;
          case TenantIntent.VAR_aapOrganizationId:
            o2.setAapOrganizationId(jsonObject.getString(entityVar));
            if(bParams.size() > 0) {
              bSql.append(", ");
            }
            bSql.append(TenantIntent.VAR_aapOrganizationId + "=$" + num);
            num++;
            bParams.add(o2.sqlAapOrganizationId());
            break;
          case TenantIntent.VAR_tenantName:
            o2.setTenantName(jsonObject.getString(entityVar));
            if(bParams.size() > 0) {
              bSql.append(", ");
            }
            bSql.append(TenantIntent.VAR_tenantName + "=$" + num);
            num++;
            bParams.add(o2.sqlTenantName());
            break;
          case TenantIntent.VAR_hostInventoryIds:
            Optional.ofNullable(jsonObject.getJsonArray(entityVar)).orElse(new JsonArray()).stream().map(oVal -> oVal.toString()).forEach(val -> {
              futures2.add(Future.future(promise2 -> {
                searchModel(siteRequest).query(HostInventory.varIndexedHostInventory(HostInventory.VAR_tenantResource), HostInventory.class, val).onSuccess(o3 -> {
                  String solrId2 = Optional.ofNullable(o3).map(o4 -> o4.getSolrId()).filter(solrId3 -> !solrIds.contains(solrId3)).orElse(null);
                  Long pk2 = Optional.ofNullable(o3).map(o4 -> o4.getPk()).orElse(null);
                  if(solrId2 != null) {
                    solrIds.add(solrId2);
                    classes.add("HostInventory");
                  }
                  sql(siteRequest).update(HostInventory.class, pk2).set(HostInventory.VAR_tenantResource, TenantIntent.class, o.getSolrId(), val).onSuccess(a -> {
                    promise2.complete();
                  }).onFailure(ex -> {
                    promise2.tryFail(ex);
                  });
                }).onFailure(ex -> {
                  promise2.tryFail(ex);
                });
              }));
            });
            break;
          case TenantIntent.VAR_tenantId:
            o2.setTenantId(jsonObject.getString(entityVar));
            if(bParams.size() > 0) {
              bSql.append(", ");
            }
            bSql.append(TenantIntent.VAR_tenantId + "=$" + num);
            num++;
            bParams.add(o2.sqlTenantId());
            break;
          case TenantIntent.VAR_archived:
            o2.setArchived(jsonObject.getString(entityVar));
            if(bParams.size() > 0) {
              bSql.append(", ");
            }
            bSql.append(TenantIntent.VAR_archived + "=$" + num);
            num++;
            bParams.add(o2.sqlArchived());
            break;
          case TenantIntent.VAR_ansibleProjectIds:
            Optional.ofNullable(jsonObject.getJsonArray(entityVar)).orElse(new JsonArray()).stream().map(oVal -> oVal.toString()).forEach(val -> {
              futures2.add(Future.future(promise2 -> {
                searchModel(siteRequest).query(AnsibleProject.varIndexedAnsibleProject(AnsibleProject.VAR_tenantResource), AnsibleProject.class, val).onSuccess(o3 -> {
                  String solrId2 = Optional.ofNullable(o3).map(o4 -> o4.getSolrId()).filter(solrId3 -> !solrIds.contains(solrId3)).orElse(null);
                  Long pk2 = Optional.ofNullable(o3).map(o4 -> o4.getPk()).orElse(null);
                  if(solrId2 != null) {
                    solrIds.add(solrId2);
                    classes.add("AnsibleProject");
                  }
                  sql(siteRequest).update(AnsibleProject.class, pk2).set(AnsibleProject.VAR_tenantResource, TenantIntent.class, o.getSolrId(), val).onSuccess(a -> {
                    promise2.complete();
                  }).onFailure(ex -> {
                    promise2.tryFail(ex);
                  });
                }).onFailure(ex -> {
                  promise2.tryFail(ex);
                });
              }));
            });
            break;
          case TenantIntent.VAR_tenantResource:
            o2.setTenantResource(jsonObject.getString(entityVar));
            if(bParams.size() > 0) {
              bSql.append(", ");
            }
            bSql.append(TenantIntent.VAR_tenantResource + "=$" + num);
            num++;
            bParams.add(o2.sqlTenantResource());
            break;
          case TenantIntent.VAR_createdByEmail:
            o2.setCreatedByEmail(jsonObject.getString(entityVar));
            if(bParams.size() > 0) {
              bSql.append(", ");
            }
            bSql.append(TenantIntent.VAR_createdByEmail + "=$" + num);
            num++;
            bParams.add(o2.sqlCreatedByEmail());
            break;
          case TenantIntent.VAR_createdByUserId:
            o2.setCreatedByUserId(jsonObject.getString(entityVar));
            if(bParams.size() > 0) {
              bSql.append(", ");
            }
            bSql.append(TenantIntent.VAR_createdByUserId + "=$" + num);
            num++;
            bParams.add(o2.sqlCreatedByUserId());
            break;
          case TenantIntent.VAR_createdByFullName:
            o2.setCreatedByFullName(jsonObject.getString(entityVar));
            if(bParams.size() > 0) {
              bSql.append(", ");
            }
            bSql.append(TenantIntent.VAR_createdByFullName + "=$" + num);
            num++;
            bParams.add(o2.sqlCreatedByFullName());
            break;
          case TenantIntent.VAR_sessionId:
            o2.setSessionId(jsonObject.getString(entityVar));
            if(bParams.size() > 0) {
              bSql.append(", ");
            }
            bSql.append(TenantIntent.VAR_sessionId + "=$" + num);
            num++;
            bParams.add(o2.sqlSessionId());
            break;
          case TenantIntent.VAR_createdVia:
            o2.setCreatedVia(jsonObject.getString(entityVar));
            if(bParams.size() > 0) {
              bSql.append(", ");
            }
            bSql.append(TenantIntent.VAR_createdVia + "=$" + num);
            num++;
            bParams.add(o2.sqlCreatedVia());
            break;
          case TenantIntent.VAR_userKey:
            o2.setUserKey(jsonObject.getString(entityVar));
            if(bParams.size() > 0) {
              bSql.append(", ");
            }
            bSql.append(TenantIntent.VAR_userKey + "=$" + num);
            num++;
            bParams.add(o2.sqlUserKey());
            break;
          case TenantIntent.VAR_intentState:
            o2.setIntentState(jsonObject.getString(entityVar));
            if(bParams.size() > 0) {
              bSql.append(", ");
            }
            bSql.append(TenantIntent.VAR_intentState + "=$" + num);
            num++;
            bParams.add(o2.sqlIntentState());
            break;
          case TenantIntent.VAR_requestedState:
            o2.setRequestedState(jsonObject.getString(entityVar));
            if(bParams.size() > 0) {
              bSql.append(", ");
            }
            bSql.append(TenantIntent.VAR_requestedState + "=$" + num);
            num++;
            bParams.add(o2.sqlRequestedState());
            break;
          case TenantIntent.VAR_realizedState:
            o2.setRealizedState(jsonObject.getString(entityVar));
            if(bParams.size() > 0) {
              bSql.append(", ");
            }
            bSql.append(TenantIntent.VAR_realizedState + "=$" + num);
            num++;
            bParams.add(o2.sqlRealizedState());
            break;
          case TenantIntent.VAR_objectTitle:
            o2.setObjectTitle(jsonObject.getString(entityVar));
            if(bParams.size() > 0) {
              bSql.append(", ");
            }
            bSql.append(TenantIntent.VAR_objectTitle + "=$" + num);
            num++;
            bParams.add(o2.sqlObjectTitle());
            break;
          case TenantIntent.VAR_description:
            o2.setDescription(jsonObject.getString(entityVar));
            if(bParams.size() > 0) {
              bSql.append(", ");
            }
            bSql.append(TenantIntent.VAR_description + "=$" + num);
            num++;
            bParams.add(o2.sqlDescription());
            break;
          case TenantIntent.VAR_displayPage:
            o2.setDisplayPage(jsonObject.getString(entityVar));
            if(bParams.size() > 0) {
              bSql.append(", ");
            }
            bSql.append(TenantIntent.VAR_displayPage + "=$" + num);
            num++;
            bParams.add(o2.sqlDisplayPage());
            break;
          case TenantIntent.VAR_requested:
            Optional.ofNullable(jsonObject.getJsonArray(entityVar)).orElse(new JsonArray()).stream().map(oVal -> oVal.toString()).forEach(val -> {
              futures2.add(Future.future(promise2 -> {
                searchModel(siteRequest).query(TenantRequested.varIndexedTenantRequested(TenantRequested.VAR_tenantResource), TenantRequested.class, val).onSuccess(o3 -> {
                  String solrId2 = Optional.ofNullable(o3).map(o4 -> o4.getSolrId()).filter(solrId3 -> !solrIds.contains(solrId3)).orElse(null);
                  Long pk2 = Optional.ofNullable(o3).map(o4 -> o4.getPk()).orElse(null);
                  if(solrId2 != null) {
                    solrIds.add(solrId2);
                    classes.add("TenantRequested");
                  }
                  sql(siteRequest).update(TenantRequested.class, pk2).set(TenantRequested.VAR_tenantResource, TenantIntent.class, o.getSolrId(), val).onSuccess(a -> {
                    promise2.complete();
                  }).onFailure(ex -> {
                    promise2.tryFail(ex);
                  });
                }).onFailure(ex -> {
                  promise2.tryFail(ex);
                });
              }));
            });
            break;
          case TenantIntent.VAR_editPage:
            o2.setEditPage(jsonObject.getString(entityVar));
            if(bParams.size() > 0) {
              bSql.append(", ");
            }
            bSql.append(TenantIntent.VAR_editPage + "=$" + num);
            num++;
            bParams.add(o2.sqlEditPage());
            break;
          case TenantIntent.VAR_locked:
            o2.setLocked(jsonObject.getString(entityVar));
            if(bParams.size() > 0) {
              bSql.append(", ");
            }
            bSql.append(TenantIntent.VAR_locked + "=$" + num);
            num++;
            bParams.add(o2.sqlLocked());
            break;
          case TenantIntent.VAR_userPage:
            o2.setUserPage(jsonObject.getString(entityVar));
            if(bParams.size() > 0) {
              bSql.append(", ");
            }
            bSql.append(TenantIntent.VAR_userPage + "=$" + num);
            num++;
            bParams.add(o2.sqlUserPage());
            break;
          case TenantIntent.VAR_dcmDiscovered:
            Optional.ofNullable(jsonObject.getJsonArray(entityVar)).orElse(new JsonArray()).stream().map(oVal -> oVal.toString()).forEach(val -> {
              futures2.add(Future.future(promise2 -> {
                searchModel(siteRequest).query(TenantDiscovered.varIndexedTenantDiscovered(TenantDiscovered.VAR_tenantResource), TenantDiscovered.class, val).onSuccess(o3 -> {
                  String solrId2 = Optional.ofNullable(o3).map(o4 -> o4.getSolrId()).filter(solrId3 -> !solrIds.contains(solrId3)).orElse(null);
                  Long pk2 = Optional.ofNullable(o3).map(o4 -> o4.getPk()).orElse(null);
                  if(solrId2 != null) {
                    solrIds.add(solrId2);
                    classes.add("TenantDiscovered");
                  }
                  sql(siteRequest).update(TenantDiscovered.class, pk2).set(TenantDiscovered.VAR_tenantResource, TenantIntent.class, o.getSolrId(), val).onSuccess(a -> {
                    promise2.complete();
                  }).onFailure(ex -> {
                    promise2.tryFail(ex);
                  });
                }).onFailure(ex -> {
                  promise2.tryFail(ex);
                });
              }));
            });
            break;
          case TenantIntent.VAR_download:
            o2.setDownload(jsonObject.getString(entityVar));
            if(bParams.size() > 0) {
              bSql.append(", ");
            }
            bSql.append(TenantIntent.VAR_download + "=$" + num);
            num++;
            bParams.add(o2.sqlDownload());
            break;
          case TenantIntent.VAR_dcmRealized:
            Optional.ofNullable(jsonObject.getJsonArray(entityVar)).orElse(new JsonArray()).stream().map(oVal -> oVal.toString()).forEach(val -> {
              futures2.add(Future.future(promise2 -> {
                searchModel(siteRequest).query(TenantRealized.varIndexedTenantRealized(TenantRealized.VAR_tenantResource), TenantRealized.class, val).onSuccess(o3 -> {
                  String solrId2 = Optional.ofNullable(o3).map(o4 -> o4.getSolrId()).filter(solrId3 -> !solrIds.contains(solrId3)).orElse(null);
                  Long pk2 = Optional.ofNullable(o3).map(o4 -> o4.getPk()).orElse(null);
                  if(solrId2 != null) {
                    solrIds.add(solrId2);
                    classes.add("TenantRealized");
                  }
                  sql(siteRequest).update(TenantRealized.class, pk2).set(TenantRealized.VAR_tenantResource, TenantIntent.class, o.getSolrId(), val).onSuccess(a -> {
                    promise2.complete();
                  }).onFailure(ex -> {
                    promise2.tryFail(ex);
                  });
                }).onFailure(ex -> {
                  promise2.tryFail(ex);
                });
              }));
            });
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
            RuntimeException ex2 = new RuntimeException("value TenantIntent failed", ex);
            LOG.error(String.format("relateTenantIntent failed. "), ex2);
            a.handle(Future.failedFuture(ex2));
          });
        }));
      }
      CompositeFuture.all(futures1).onSuccess(a -> {
        CompositeFuture.all(futures2).onSuccess(b -> {
          promise.complete(o2);
        }).onFailure(ex -> {
          LOG.error(String.format("sqlPOSTTenantIntent failed. "), ex);
          promise.tryFail(ex);
        });
      }).onFailure(ex -> {
        LOG.error(String.format("sqlPOSTTenantIntent failed. "), ex);
        promise.tryFail(ex);
      });
    } catch(Exception ex) {
      LOG.error(String.format("sqlPOSTTenantIntent failed. "), ex);
      promise.tryFail(ex);
    }
    return promise.future();
  }

  public Future<ServiceResponse> response200POSTTenantIntent(TenantIntent o) {
    Promise<ServiceResponse> promise = Promise.promise();
    try {
      SiteRequest siteRequest = o.getSiteRequest_();
      JsonObject json = JsonObject.mapFrom(o);
      promise.complete(ServiceResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily())));
    } catch(Exception ex) {
      LOG.error(String.format("response200POSTTenantIntent failed. "), ex);
      promise.tryFail(ex);
    }
    return promise.future();
  }

  // DELETE //

  @Override
  public void deleteTenantIntent(JsonObject body, ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler) {
    LOG.debug(String.format("deleteTenantIntent started. "));
    Boolean classPublicRead = false;
    user(serviceRequest, SiteRequest.class, SiteUser.class, SiteUser.getClassApiAddress(), "postSiteUserFuture", "patchSiteUserFuture", classPublicRead).onSuccess(siteRequest -> {
      try {
        siteRequest.setLang("enUS");
        String tenantResource = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("tenantResource");
        String TENANT = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("TENANT");
        List<String> groups = Optional.ofNullable(siteRequest.getGroups()).orElse(new ArrayList<>());
        MultiMap form = MultiMap.caseInsensitiveMultiMap();
        form.add("grant_type", "urn:ietf:params:oauth:grant-type:uma-ticket");
        form.add("audience", config.getString(ComputateConfigKeys.AUTH_CLIENT));
        form.add("response_mode", "permissions");
        form.add("permission", String.format("%s#%s", TenantIntent.CLASS_AUTH_RESOURCE, "GET"));
        form.add("permission", String.format("%s#%s", TenantIntent.CLASS_AUTH_RESOURCE, "POST"));
        form.add("permission", String.format("%s#%s", TenantIntent.CLASS_AUTH_RESOURCE, "PATCH"));
        form.add("permission", String.format("%s#%s", TenantIntent.CLASS_AUTH_RESOURCE, "DELETE"));
        form.add("permission", String.format("%s#%s", TenantIntent.CLASS_AUTH_RESOURCE, "Admin"));
        form.add("permission", String.format("%s#%s", TenantIntent.CLASS_AUTH_RESOURCE, "SuperAdmin"));
        if(tenantResource != null)
          form.add("permission", String.format("%s#%s", tenantResource, "DELETE"));
        groups.stream().map(group -> {
              Matcher mPermission = Pattern.compile("^/(.*-?TENANT-([a-z0-9\\-]+))-(\\w+)$").matcher(group);
              return mPermission.find() ? mPermission : null;
            }).filter(v -> v != null).forEach(mPermission -> {
              form.add("permission", String.format("%s#%s", mPermission.group(1), mPermission.group(3)));
            });
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
            JsonArray scopes = authorizationDecisionBody.stream().map(o -> (JsonObject)o).filter(o -> "TENANT".equals(o.getString("rsname"))).findFirst().map(decision -> ((JsonObject)decision).getJsonArray("scopes")).orElse(new JsonArray());
            if(!scopes.contains("DELETE")) {
            List<String> fqs = new ArrayList<>();
            authorizationDecisionBody.stream().map(o -> (JsonObject)o).filter(permission -> {
                  Matcher mPermission = Pattern.compile("^(.*-?TENANT-([a-z0-9\\-]+))$").matcher(permission.getString("rsname"));
                  return permission.getJsonArray("scopes").contains("DELETE")
                      && mPermission.find();
                }).forEach(permission -> {
                  fqs.add(String.format("%s:%s", "tenantResource", permission.getString("rsname")));
                  permission.getJsonArray("scopes").stream().map(s -> (String)s).forEach(scope -> {
                    if(!scopes.contains(scope))
                      scopes.add(scope);
                  });
                });
              JsonObject authParams = siteRequest.getServiceRequest().getParams();
              JsonObject authQuery = authParams.getJsonObject("query");
              if(authQuery == null) {
                authQuery = new JsonObject();
                authParams.put("query", authQuery);
              }
              JsonArray fq = authQuery.getJsonArray("fq");
              if(fq == null) {
                fq = new JsonArray();
                authQuery.put("fq", fq);
              }
              if(fqs.size() > 0) {
                fq.add(fqs.stream().collect(Collectors.joining(" OR ")));
                if(!scopes.contains("DELETE"))
                  scopes.add("DELETE");
                siteRequest.setFilteredScope(true);
              }
            }
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
              searchTenantIntentList(siteRequest, false, true, true, "DELETE").onSuccess(listTenantIntent -> {
                try {
                  ApiRequest apiRequest = new ApiRequest();
                  apiRequest.setRows(listTenantIntent.getRequest().getRows());
                  apiRequest.setNumFound(listTenantIntent.getResponse().getResponse().getNumFound());
                  apiRequest.setNumPATCH(0L);
                  apiRequest.initDeepApiRequest(siteRequest);
                  siteRequest.setApiRequest_(apiRequest);
                  if(apiRequest.getNumFound() == 1L)
                    apiRequest.setOriginal(listTenantIntent.first());
                  apiRequest.setSolrId(Optional.ofNullable(listTenantIntent.first()).map(o2 -> o2.getSolrId()).orElse(null));
                  eventBus.publish("websocketTenantIntent", JsonObject.mapFrom(apiRequest).toString());

                  listDELETETenantIntent(apiRequest, listTenantIntent).onSuccess(e -> {
                    response200DELETETenantIntent(siteRequest).onSuccess(response -> {
                      LOG.debug(String.format("deleteTenantIntent succeeded. "));
                      eventHandler.handle(Future.succeededFuture(response));
                    }).onFailure(ex -> {
                      LOG.error(String.format("deleteTenantIntent failed. "), ex);
                      error(siteRequest, eventHandler, ex);
                    });
                  }).onFailure(ex -> {
                    LOG.error(String.format("deleteTenantIntent failed. "), ex);
                    error(siteRequest, eventHandler, ex);
                  });
                } catch(Exception ex) {
                  LOG.error(String.format("deleteTenantIntent failed. "), ex);
                  error(siteRequest, eventHandler, ex);
                }
              }).onFailure(ex -> {
                LOG.error(String.format("deleteTenantIntent failed. "), ex);
                error(siteRequest, eventHandler, ex);
              });
            }
          } catch(Exception ex) {
            LOG.error(String.format("deleteTenantIntent failed. "), ex);
            error(null, eventHandler, ex);
          }
        });
      } catch(Exception ex) {
        LOG.error(String.format("deleteTenantIntent failed. "), ex);
        error(null, eventHandler, ex);
      }
    }).onFailure(ex -> {
      if("Inactive Token".equals(ex.getMessage()) || StringUtils.startsWith(ex.getMessage(), "invalid_grant:")) {
        try {
          eventHandler.handle(Future.succeededFuture(new ServiceResponse(302, "Found", null, MultiMap.caseInsensitiveMultiMap().add(HttpHeaders.LOCATION, "/logout?redirect_uri=" + URLEncoder.encode(serviceRequest.getExtra().getString("uri"), "UTF-8")))));
        } catch(Exception ex2) {
          LOG.error(String.format("deleteTenantIntent failed. ", ex2));
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
        LOG.error(String.format("deleteTenantIntent failed. "), ex);
        error(null, eventHandler, ex);
      }
    });
  }

  public Future<Void> listDELETETenantIntent(ApiRequest apiRequest, SearchList<TenantIntent> listTenantIntent) {
    Promise<Void> promise = Promise.promise();
    List<Future> futures = new ArrayList<>();
    SiteRequest siteRequest = listTenantIntent.getSiteRequest_(SiteRequest.class);
    listTenantIntent.getList().forEach(o -> {
      SiteRequest siteRequest2 = generateSiteRequest(siteRequest.getUser(), siteRequest.getUserPrincipal(), siteRequest.getServiceRequest(), siteRequest.getJsonObject(), SiteRequest.class);
      siteRequest2.setScopes(siteRequest.getScopes());
      o.setSiteRequest_(siteRequest2);
      siteRequest2.setApiRequest_(siteRequest.getApiRequest_());
      JsonObject jsonObject = JsonObject.mapFrom(o);
      TenantIntent o2 = jsonObject.mapTo(TenantIntent.class);
      o2.setSiteRequest_(siteRequest2);
      futures.add(Future.future(promise1 -> {
        deleteTenantIntentFuture(o).onSuccess(a -> {
          promise1.complete();
        }).onFailure(ex -> {
          LOG.error(String.format("listDELETETenantIntent failed. "), ex);
          promise1.tryFail(ex);
        });
      }));
    });
    CompositeFuture.all(futures).onSuccess( a -> {
      listTenantIntent.next().onSuccess(next -> {
        if(next) {
          listDELETETenantIntent(apiRequest, listTenantIntent).onSuccess(b -> {
            promise.complete();
          }).onFailure(ex -> {
            LOG.error(String.format("listDELETETenantIntent failed. "), ex);
            promise.tryFail(ex);
          });
        } else {
          promise.complete();
        }
      }).onFailure(ex -> {
        LOG.error(String.format("listDELETETenantIntent failed. "), ex);
        promise.tryFail(ex);
      });
    }).onFailure(ex -> {
      LOG.error(String.format("listDELETETenantIntent failed. "), ex);
      promise.tryFail(ex);
    });
    return promise.future();
  }

  @Override
  public void deleteTenantIntentFuture(JsonObject body, ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler) {
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
        searchTenantIntentList(siteRequest, false, true, true, "DELETE").onSuccess(listTenantIntent -> {
          try {
            TenantIntent o = listTenantIntent.first();
            if(o != null && listTenantIntent.getResponse().getResponse().getNumFound() == 1) {
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
              apiRequest.setId(Optional.ofNullable(listTenantIntent.first()).map(o2 -> o2.getTenantResource().toString()).orElse(null));
              apiRequest.setSolrId(Optional.ofNullable(listTenantIntent.first()).map(o2 -> o2.getSolrId()).orElse(null));
              deleteTenantIntentFuture(o).onSuccess(o2 -> {
                eventHandler.handle(Future.succeededFuture(ServiceResponse.completedWithJson(Buffer.buffer(new JsonObject().encodePrettily()))));
              }).onFailure(ex -> {
                eventHandler.handle(Future.failedFuture(ex));
              });
            } else {
              eventHandler.handle(Future.succeededFuture(ServiceResponse.completedWithJson(Buffer.buffer(new JsonObject().encodePrettily()))));
            }
          } catch(Exception ex) {
            LOG.error(String.format("deleteTenantIntent failed. "), ex);
            error(siteRequest, eventHandler, ex);
          }
        }).onFailure(ex -> {
          LOG.error(String.format("deleteTenantIntent failed. "), ex);
          error(siteRequest, eventHandler, ex);
        });
      } catch(Exception ex) {
        LOG.error(String.format("deleteTenantIntent failed. "), ex);
        error(null, eventHandler, ex);
      }
    }).onFailure(ex -> {
      LOG.error(String.format("deleteTenantIntent failed. "), ex);
      error(null, eventHandler, ex);
    });
  }

  public Future<TenantIntent> deleteTenantIntentFuture(TenantIntent o) {
    SiteRequest siteRequest = o.getSiteRequest_();
    Promise<TenantIntent> promise = Promise.promise();

    try {
      ApiRequest apiRequest = siteRequest.getApiRequest_();
      Promise<TenantIntent> promise1 = Promise.promise();
      pgPool.withTransaction(sqlConnection -> {
        siteRequest.setSqlConnection(sqlConnection);
        varsTenantIntent(siteRequest).onSuccess(a -> {
          sqlDELETETenantIntent(o).onSuccess(tenantIntent -> {
            relateTenantIntent(o).onSuccess(d -> {
              unindexTenantIntent(o).onSuccess(o2 -> {
                if(apiRequest != null) {
                  apiRequest.setNumPATCH(apiRequest.getNumPATCH() + 1);
                  if(apiRequest.getNumFound() == 1L && Optional.ofNullable(siteRequest.getJsonObject()).map(json -> json.size() > 0).orElse(false)) {
                    o2.apiRequestTenantIntent();
                    if(apiRequest.getVars().size() > 0 && Optional.ofNullable(siteRequest.getRequestVars().get("refresh")).map(refresh -> !refresh.equals("false")).orElse(true))
                      eventBus.publish("websocketTenantIntent", JsonObject.mapFrom(apiRequest).toString());
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
      }).compose(tenantIntent -> {
        Promise<TenantIntent> promise2 = Promise.promise();
        refreshTenantIntent(o).onSuccess(a -> {
          promise2.complete(o);
        }).onFailure(ex -> {
          promise2.tryFail(ex);
        });
        return promise2.future();
      }).onSuccess(tenantIntent -> {
        promise.complete(tenantIntent);
      }).onFailure(ex -> {
        promise.tryFail(ex);
      });
    } catch(Exception ex) {
      LOG.error(String.format("deleteTenantIntentFuture failed. "), ex);
      promise.tryFail(ex);
    }
    return promise.future();
  }

  public Future<Void> sqlDELETETenantIntent(TenantIntent o) {
    Promise<Void> promise = Promise.promise();
    try {
      SiteRequest siteRequest = o.getSiteRequest_();
      ApiRequest apiRequest = siteRequest.getApiRequest_();
      List<String> solrIds = Optional.ofNullable(apiRequest).map(r -> r.getSolrIds()).orElse(new ArrayList<>());
      List<String> classes = Optional.ofNullable(apiRequest).map(r -> r.getClasses()).orElse(new ArrayList<>());
      SqlConnection sqlConnection = siteRequest.getSqlConnection();
      Integer num = 1;
      StringBuilder bSql = new StringBuilder("DELETE FROM TenantIntent ");
      List<Object> bParams = new ArrayList<Object>();
      Long pk = o.getPk();
      JsonObject jsonObject = siteRequest.getJsonObject();
      TenantIntent o2 = new TenantIntent();
      o2.setSiteRequest_(siteRequest);
      List<Future> futures1 = new ArrayList<>();
      List<Future> futures2 = new ArrayList<>();

      if(jsonObject != null) {
        Set<String> entityVars = jsonObject.fieldNames();
        for(String entityVar : entityVars) {
          switch(entityVar) {
          case TenantIntent.VAR_hostInventoryIds:
            Optional.ofNullable(jsonObject.getJsonArray(entityVar)).orElse(new JsonArray()).stream().map(oVal -> oVal.toString()).forEach(val -> {
              futures2.add(Future.future(promise2 -> {
                searchModel(siteRequest).query(HostInventory.varIndexedHostInventory(HostInventory.VAR_tenantResource), HostInventory.class, val).onSuccess(o3 -> {
                  String solrId2 = Optional.ofNullable(o3).map(o4 -> o4.getSolrId()).filter(solrId3 -> !solrIds.contains(solrId3)).orElse(null);
                  Long pk2 = Optional.ofNullable(o3).map(o4 -> o4.getPk()).orElse(null);
                  if(solrId2 != null) {
                    solrIds.add(solrId2);
                    classes.add("HostInventory");
                  }
                  sql(siteRequest).update(HostInventory.class, pk2).set(HostInventory.VAR_tenantResource, TenantIntent.class, null, null).onSuccess(a -> {
                    promise2.complete();
                  }).onFailure(ex -> {
                    promise2.tryFail(ex);
                  });
                }).onFailure(ex -> {
                  promise2.tryFail(ex);
                });
              }));
            });
            break;
          case TenantIntent.VAR_ansibleProjectIds:
            Optional.ofNullable(jsonObject.getJsonArray(entityVar)).orElse(new JsonArray()).stream().map(oVal -> oVal.toString()).forEach(val -> {
              futures2.add(Future.future(promise2 -> {
                searchModel(siteRequest).query(AnsibleProject.varIndexedAnsibleProject(AnsibleProject.VAR_tenantResource), AnsibleProject.class, val).onSuccess(o3 -> {
                  String solrId2 = Optional.ofNullable(o3).map(o4 -> o4.getSolrId()).filter(solrId3 -> !solrIds.contains(solrId3)).orElse(null);
                  Long pk2 = Optional.ofNullable(o3).map(o4 -> o4.getPk()).orElse(null);
                  if(solrId2 != null) {
                    solrIds.add(solrId2);
                    classes.add("AnsibleProject");
                  }
                  sql(siteRequest).update(AnsibleProject.class, pk2).set(AnsibleProject.VAR_tenantResource, TenantIntent.class, null, null).onSuccess(a -> {
                    promise2.complete();
                  }).onFailure(ex -> {
                    promise2.tryFail(ex);
                  });
                }).onFailure(ex -> {
                  promise2.tryFail(ex);
                });
              }));
            });
            break;
          case TenantIntent.VAR_requested:
            Optional.ofNullable(jsonObject.getJsonArray(entityVar)).orElse(new JsonArray()).stream().map(oVal -> oVal.toString()).forEach(val -> {
              futures2.add(Future.future(promise2 -> {
                searchModel(siteRequest).query(TenantRequested.varIndexedTenantRequested(TenantRequested.VAR_tenantResource), TenantRequested.class, val).onSuccess(o3 -> {
                  String solrId2 = Optional.ofNullable(o3).map(o4 -> o4.getSolrId()).filter(solrId3 -> !solrIds.contains(solrId3)).orElse(null);
                  Long pk2 = Optional.ofNullable(o3).map(o4 -> o4.getPk()).orElse(null);
                  if(solrId2 != null) {
                    solrIds.add(solrId2);
                    classes.add("TenantRequested");
                  }
                  sql(siteRequest).update(TenantRequested.class, pk2).set(TenantRequested.VAR_tenantResource, TenantIntent.class, null, null).onSuccess(a -> {
                    promise2.complete();
                  }).onFailure(ex -> {
                    promise2.tryFail(ex);
                  });
                }).onFailure(ex -> {
                  promise2.tryFail(ex);
                });
              }));
            });
            break;
          case TenantIntent.VAR_dcmDiscovered:
            Optional.ofNullable(jsonObject.getJsonArray(entityVar)).orElse(new JsonArray()).stream().map(oVal -> oVal.toString()).forEach(val -> {
              futures2.add(Future.future(promise2 -> {
                searchModel(siteRequest).query(TenantDiscovered.varIndexedTenantDiscovered(TenantDiscovered.VAR_tenantResource), TenantDiscovered.class, val).onSuccess(o3 -> {
                  String solrId2 = Optional.ofNullable(o3).map(o4 -> o4.getSolrId()).filter(solrId3 -> !solrIds.contains(solrId3)).orElse(null);
                  Long pk2 = Optional.ofNullable(o3).map(o4 -> o4.getPk()).orElse(null);
                  if(solrId2 != null) {
                    solrIds.add(solrId2);
                    classes.add("TenantDiscovered");
                  }
                  sql(siteRequest).update(TenantDiscovered.class, pk2).set(TenantDiscovered.VAR_tenantResource, TenantIntent.class, null, null).onSuccess(a -> {
                    promise2.complete();
                  }).onFailure(ex -> {
                    promise2.tryFail(ex);
                  });
                }).onFailure(ex -> {
                  promise2.tryFail(ex);
                });
              }));
            });
            break;
          case TenantIntent.VAR_dcmRealized:
            Optional.ofNullable(jsonObject.getJsonArray(entityVar)).orElse(new JsonArray()).stream().map(oVal -> oVal.toString()).forEach(val -> {
              futures2.add(Future.future(promise2 -> {
                searchModel(siteRequest).query(TenantRealized.varIndexedTenantRealized(TenantRealized.VAR_tenantResource), TenantRealized.class, val).onSuccess(o3 -> {
                  String solrId2 = Optional.ofNullable(o3).map(o4 -> o4.getSolrId()).filter(solrId3 -> !solrIds.contains(solrId3)).orElse(null);
                  Long pk2 = Optional.ofNullable(o3).map(o4 -> o4.getPk()).orElse(null);
                  if(solrId2 != null) {
                    solrIds.add(solrId2);
                    classes.add("TenantRealized");
                  }
                  sql(siteRequest).update(TenantRealized.class, pk2).set(TenantRealized.VAR_tenantResource, TenantIntent.class, null, null).onSuccess(a -> {
                    promise2.complete();
                  }).onFailure(ex -> {
                    promise2.tryFail(ex);
                  });
                }).onFailure(ex -> {
                  promise2.tryFail(ex);
                });
              }));
            });
            break;
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
          RuntimeException ex2 = new RuntimeException("value TenantIntent failed", ex);
          LOG.error(String.format("unrelateTenantIntent failed. "), ex2);
          a.handle(Future.failedFuture(ex2));
        });
      }));
      CompositeFuture.all(futures1).onSuccess(a -> {
        CompositeFuture.all(futures2).onSuccess(b -> {
          promise.complete();
        }).onFailure(ex -> {
          LOG.error(String.format("sqlDELETETenantIntent failed. "), ex);
          promise.tryFail(ex);
        });
      }).onFailure(ex -> {
        LOG.error(String.format("sqlDELETETenantIntent failed. "), ex);
        promise.tryFail(ex);
      });
    } catch(Exception ex) {
      LOG.error(String.format("sqlDELETETenantIntent failed. "), ex);
      promise.tryFail(ex);
    }
    return promise.future();
  }

  public Future<ServiceResponse> response200DELETETenantIntent(SiteRequest siteRequest) {
    Promise<ServiceResponse> promise = Promise.promise();
    try {
      JsonObject json = new JsonObject();
      promise.complete(ServiceResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily())));
    } catch(Exception ex) {
      LOG.error(String.format("response200DELETETenantIntent failed. "), ex);
      promise.tryFail(ex);
    }
    return promise.future();
  }

  // PUTImport //

  @Override
  public void putimportTenantIntent(JsonObject body, ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler) {
    LOG.debug(String.format("putimportTenantIntent started. "));
    Boolean classPublicRead = false;
    user(serviceRequest, SiteRequest.class, SiteUser.class, SiteUser.getClassApiAddress(), "postSiteUserFuture", "patchSiteUserFuture", classPublicRead).onSuccess(siteRequest -> {
      try {
        siteRequest.setLang("enUS");
        String tenantResource = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("tenantResource");
        String TENANT = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("TENANT");
        List<String> groups = Optional.ofNullable(siteRequest.getGroups()).orElse(new ArrayList<>());
        MultiMap form = MultiMap.caseInsensitiveMultiMap();
        form.add("grant_type", "urn:ietf:params:oauth:grant-type:uma-ticket");
        form.add("audience", config.getString(ComputateConfigKeys.AUTH_CLIENT));
        form.add("response_mode", "permissions");
        form.add("permission", String.format("%s#%s", TenantIntent.CLASS_AUTH_RESOURCE, "GET"));
        form.add("permission", String.format("%s#%s", TenantIntent.CLASS_AUTH_RESOURCE, "POST"));
        form.add("permission", String.format("%s#%s", TenantIntent.CLASS_AUTH_RESOURCE, "PATCH"));
        form.add("permission", String.format("%s#%s", TenantIntent.CLASS_AUTH_RESOURCE, "DELETE"));
        form.add("permission", String.format("%s#%s", TenantIntent.CLASS_AUTH_RESOURCE, "Admin"));
        form.add("permission", String.format("%s#%s", TenantIntent.CLASS_AUTH_RESOURCE, "SuperAdmin"));
        if(tenantResource != null)
          form.add("permission", String.format("%s#%s", tenantResource, "PUT"));
        groups.stream().map(group -> {
              Matcher mPermission = Pattern.compile("^/(.*-?TENANT-([a-z0-9\\-]+))-(\\w+)$").matcher(group);
              return mPermission.find() ? mPermission : null;
            }).filter(v -> v != null).forEach(mPermission -> {
              form.add("permission", String.format("%s#%s", mPermission.group(1), mPermission.group(3)));
            });
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
            JsonArray scopes = authorizationDecisionBody.stream().map(o -> (JsonObject)o).filter(o -> "TENANT".equals(o.getString("rsname"))).findFirst().map(decision -> ((JsonObject)decision).getJsonArray("scopes")).orElse(new JsonArray());
            if(!scopes.contains("PUT")) {
            List<String> fqs = new ArrayList<>();
            authorizationDecisionBody.stream().map(o -> (JsonObject)o).filter(permission -> {
                  Matcher mPermission = Pattern.compile("^(.*-?TENANT-([a-z0-9\\-]+))$").matcher(permission.getString("rsname"));
                  return permission.getJsonArray("scopes").contains("PUT")
                      && mPermission.find();
                }).forEach(permission -> {
                  fqs.add(String.format("%s:%s", "tenantResource", permission.getString("rsname")));
                  permission.getJsonArray("scopes").stream().map(s -> (String)s).forEach(scope -> {
                    if(!scopes.contains(scope))
                      scopes.add(scope);
                  });
                });
              JsonObject authParams = siteRequest.getServiceRequest().getParams();
              JsonObject authQuery = authParams.getJsonObject("query");
              if(authQuery == null) {
                authQuery = new JsonObject();
                authParams.put("query", authQuery);
              }
              JsonArray fq = authQuery.getJsonArray("fq");
              if(fq == null) {
                fq = new JsonArray();
                authQuery.put("fq", fq);
              }
              if(fqs.size() > 0) {
                fq.add(fqs.stream().collect(Collectors.joining(" OR ")));
                if(!scopes.contains("PUT"))
                  scopes.add("PUT");
                siteRequest.setFilteredScope(true);
              }
            }
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
              eventBus.publish("websocketTenantIntent", JsonObject.mapFrom(apiRequest).toString());
              varsTenantIntent(siteRequest).onSuccess(d -> {
                listPUTImportTenantIntent(apiRequest, siteRequest).onSuccess(e -> {
                  response200PUTImportTenantIntent(siteRequest).onSuccess(response -> {
                    LOG.debug(String.format("putimportTenantIntent succeeded. "));
                    eventHandler.handle(Future.succeededFuture(response));
                  }).onFailure(ex -> {
                    LOG.error(String.format("putimportTenantIntent failed. "), ex);
                    error(siteRequest, eventHandler, ex);
                  });
                }).onFailure(ex -> {
                  LOG.error(String.format("putimportTenantIntent failed. "), ex);
                  error(siteRequest, eventHandler, ex);
                });
              }).onFailure(ex -> {
                LOG.error(String.format("putimportTenantIntent failed. "), ex);
                error(siteRequest, eventHandler, ex);
              });
            }
          } catch(Exception ex) {
            LOG.error(String.format("putimportTenantIntent failed. "), ex);
            error(null, eventHandler, ex);
          }
        });
      } catch(Exception ex) {
        LOG.error(String.format("putimportTenantIntent failed. "), ex);
        error(null, eventHandler, ex);
      }
    }).onFailure(ex -> {
      if("Inactive Token".equals(ex.getMessage()) || StringUtils.startsWith(ex.getMessage(), "invalid_grant:")) {
        try {
          eventHandler.handle(Future.succeededFuture(new ServiceResponse(302, "Found", null, MultiMap.caseInsensitiveMultiMap().add(HttpHeaders.LOCATION, "/logout?redirect_uri=" + URLEncoder.encode(serviceRequest.getExtra().getString("uri"), "UTF-8")))));
        } catch(Exception ex2) {
          LOG.error(String.format("putimportTenantIntent failed. ", ex2));
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
        LOG.error(String.format("putimportTenantIntent failed. "), ex);
        error(null, eventHandler, ex);
      }
    });
  }

  public Future<Void> listPUTImportTenantIntent(ApiRequest apiRequest, SiteRequest siteRequest) {
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
          eventBus.request(TenantIntent.getClassApiAddress(), json, new DeliveryOptions().addHeader("action", "putimportTenantIntentFuture")).onSuccess(a -> {
            promise1.complete();
          }).onFailure(ex -> {
            LOG.error(String.format("listPUTImportTenantIntent failed. "), ex);
            promise1.tryFail(ex);
          });
        }));
      });
      CompositeFuture.all(futures).onSuccess(a -> {
        apiRequest.setNumPATCH(apiRequest.getNumPATCH() + 1);
        promise.complete();
      }).onFailure(ex -> {
        LOG.error(String.format("listPUTImportTenantIntent failed. "), ex);
        promise.tryFail(ex);
      });
    } catch(Exception ex) {
      LOG.error(String.format("listPUTImportTenantIntent failed. "), ex);
      promise.tryFail(ex);
    }
    return promise.future();
  }

  @Override
  public void putimportTenantIntentFuture(JsonObject body, ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler) {
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
        String tenantResource = Optional.ofNullable(body.getString(TenantIntent.VAR_tenantResource)).orElse(body.getString(TenantIntent.VAR_solrId));
        if(Optional.ofNullable(serviceRequest.getParams()).map(p -> p.getJsonObject("query")).map( q -> q.getJsonArray("var")).orElse(new JsonArray()).stream().filter(s -> "refresh:false".equals(s)).count() > 0L) {
          siteRequest.getRequestVars().put( "refresh", "false" );
        }
        pgPool.getConnection().onSuccess(sqlConnection -> {
          String sqlQuery = String.format("select * from %s WHERE tenantResource=$1", TenantIntent.CLASS_SIMPLE_NAME);
          sqlConnection.preparedQuery(sqlQuery)
              .execute(Tuple.tuple(Arrays.asList(tenantResource))
              ).onSuccess(result -> {
            sqlConnection.close().onSuccess(a -> {
              try {
                if(result.size() >= 1) {
                  TenantIntent o = new TenantIntent();
                  o.setSiteRequest_(siteRequest);
                  for(Row definition : result.value()) {
                    for(Integer i = 0; i < definition.size(); i++) {
                      try {
                        String columnName = definition.getColumnName(i);
                        Object columnValue = definition.getValue(i);
                        o.persistForClass(columnName, columnValue);
                      } catch(Exception e) {
                        LOG.error(String.format("persistTenantIntent failed. "), e);
                      }
                    }
                  }
                  TenantIntent o2 = new TenantIntent();
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
                      if(!StringUtils.containsAny(f, "tenantResource", "created", "setCreated") && !Objects.equals(o.obtainForClass(f), o2.obtainForClass(f)))
                        body2.put("set" + StringUtils.capitalize(f), bodyVal);
                    }
                  }
                  for(String f : Optional.ofNullable(o.getSaves()).orElse(new ArrayList<>())) {
                    if(!body.fieldNames().contains(f)) {
                      if(!StringUtils.containsAny(f, "tenantResource", "created", "setCreated") && !Objects.equals(o.obtainForClass(f), o2.obtainForClass(f)))
                        body2.putNull("set" + StringUtils.capitalize(f));
                    }
                  }
                  if(result.size() >= 1) {
                    apiRequest.setOriginal(o);
                    apiRequest.setId(Optional.ofNullable(o.getTenantResource()).map(v -> v.toString()).orElse(null));
                    apiRequest.setSolrId(o.getSolrId());
                  }
                  siteRequest.setJsonObject(body2);
                  patchTenantIntentFuture(o, true).onSuccess(b -> {
                    LOG.debug("Import TenantIntent {} succeeded, modified TenantIntent. ", body.getValue(TenantIntent.VAR_tenantResource));
                    eventHandler.handle(Future.succeededFuture());
                  }).onFailure(ex -> {
                    LOG.error(String.format("putimportTenantIntentFuture failed. "), ex);
                    eventHandler.handle(Future.failedFuture(ex));
                  });
                } else {
                  postTenantIntentFuture(siteRequest, true).onSuccess(b -> {
                    LOG.debug("Import TenantIntent {} succeeded, created new TenantIntent. ", body.getValue(TenantIntent.VAR_tenantResource));
                    eventHandler.handle(Future.succeededFuture());
                  }).onFailure(ex -> {
                    LOG.error(String.format("putimportTenantIntentFuture failed. "), ex);
                    eventHandler.handle(Future.failedFuture(ex));
                  });
                }
              } catch(Exception ex) {
                LOG.error(String.format("putimportTenantIntentFuture failed. "), ex);
                eventHandler.handle(Future.failedFuture(ex));
              }
            }).onFailure(ex -> {
              LOG.error(String.format("putimportTenantIntentFuture failed. "), ex);
              eventHandler.handle(Future.failedFuture(ex));
            });
          }).onFailure(ex -> {
            LOG.error(String.format("putimportTenantIntentFuture failed. "), ex);
            eventHandler.handle(Future.failedFuture(ex));
          });
        }).onFailure(ex -> {
          LOG.error(String.format("putimportTenantIntentFuture failed. "), ex);
          eventHandler.handle(Future.failedFuture(ex));
        });
      } catch(Exception ex) {
        LOG.error(String.format("putimportTenantIntentFuture failed. "), ex);
        eventHandler.handle(Future.failedFuture(ex));
      }
    }).onFailure(ex -> {
      if("Inactive Token".equals(ex.getMessage()) || StringUtils.startsWith(ex.getMessage(), "invalid_grant:")) {
        try {
          eventHandler.handle(Future.succeededFuture(new ServiceResponse(302, "Found", null, MultiMap.caseInsensitiveMultiMap().add(HttpHeaders.LOCATION, "/logout?redirect_uri=" + URLEncoder.encode(serviceRequest.getExtra().getString("uri"), "UTF-8")))));
        } catch(Exception ex2) {
          LOG.error(String.format("putimportTenantIntent failed. ", ex2));
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
        LOG.error(String.format("putimportTenantIntent failed. "), ex);
        error(null, eventHandler, ex);
      }
    });
  }

  public Future<ServiceResponse> response200PUTImportTenantIntent(SiteRequest siteRequest) {
    Promise<ServiceResponse> promise = Promise.promise();
    try {
      JsonObject json = new JsonObject();
      promise.complete(ServiceResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily())));
    } catch(Exception ex) {
      LOG.error(String.format("response200PUTImportTenantIntent failed. "), ex);
      promise.tryFail(ex);
    }
    return promise.future();
  }

  // SearchPage //

  @Override
  public void searchpageTenantIntent(ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler) {
    oauth2AuthenticationProvider.refresh(User.create(serviceRequest.getUser())).onSuccess(user -> {
      serviceRequest.setUser(user.principal());
    Boolean classPublicRead = false;
    user(serviceRequest, SiteRequest.class, SiteUser.class, SiteUser.getClassApiAddress(), "postSiteUserFuture", "patchSiteUserFuture", classPublicRead).onSuccess(siteRequest -> {
      try {
        siteRequest.setLang("enUS");
        String tenantResource = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("tenantResource");
        String TENANT = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("TENANT");
        List<String> groups = Optional.ofNullable(siteRequest.getGroups()).orElse(new ArrayList<>());
        MultiMap form = MultiMap.caseInsensitiveMultiMap();
        form.add("grant_type", "urn:ietf:params:oauth:grant-type:uma-ticket");
        form.add("audience", config.getString(ComputateConfigKeys.AUTH_CLIENT));
        form.add("response_mode", "permissions");
        form.add("permission", String.format("%s#%s", TenantIntent.CLASS_AUTH_RESOURCE, "GET"));
        form.add("permission", String.format("%s#%s", TenantIntent.CLASS_AUTH_RESOURCE, "POST"));
        form.add("permission", String.format("%s#%s", TenantIntent.CLASS_AUTH_RESOURCE, "PATCH"));
        form.add("permission", String.format("%s#%s", TenantIntent.CLASS_AUTH_RESOURCE, "DELETE"));
        form.add("permission", String.format("%s#%s", TenantIntent.CLASS_AUTH_RESOURCE, "Admin"));
        form.add("permission", String.format("%s#%s", TenantIntent.CLASS_AUTH_RESOURCE, "SuperAdmin"));
        if(tenantResource != null)
          form.add("permission", String.format("%s#%s", tenantResource, "GET"));
        groups.stream().map(group -> {
              Matcher mPermission = Pattern.compile("^/(.*-?TENANT-([a-z0-9\\-]+))-(\\w+)$").matcher(group);
              return mPermission.find() ? mPermission : null;
            }).filter(v -> v != null).forEach(mPermission -> {
              form.add("permission", String.format("%s#%s", mPermission.group(1), mPermission.group(3)));
            });
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
            JsonArray scopes = authorizationDecisionBody.stream().map(o -> (JsonObject)o).filter(o -> "TENANT".equals(o.getString("rsname"))).findFirst().map(decision -> ((JsonObject)decision).getJsonArray("scopes")).orElse(new JsonArray());
            if(!scopes.contains("GET")) {
              List<String> fqs = new ArrayList<>();
              authorizationDecisionBody.stream().map(o -> (JsonObject)o).filter(permission -> {
                    Matcher mPermission = Pattern.compile("^(.*-?TENANT-([a-z0-9\\-]+))$").matcher(permission.getString("rsname"));
                    return permission.getJsonArray("scopes").contains("GET")
                        && mPermission.find();
                  }).forEach(permission -> {
                    fqs.add(String.format("%s:%s", "tenantResource", permission.getString("rsname")));
                    permission.getJsonArray("scopes").stream().map(s -> (String)s).forEach(scope -> {
                      if(!scopes.contains(scope))
                        scopes.add(scope);
                    });
                  });
              if(!classPublicRead) {
                JsonObject authParams = siteRequest.getServiceRequest().getParams();
                JsonObject authQuery = authParams.getJsonObject("query");
                if(authQuery == null) {
                  authQuery = new JsonObject();
                  authParams.put("query", authQuery);
                }
                JsonArray fq = authQuery.getJsonArray("fq");
                if(fq == null) {
                  fq = new JsonArray();
                  authQuery.put("fq", fq);
                }
                if(fqs.size() > 0) {
                  fq.add(fqs.stream().collect(Collectors.joining(" OR ")));
                  if(!scopes.contains("GET"))
                    scopes.add("GET");
                  siteRequest.setFilteredScope(true);
                }
              }
            }
            {
              siteRequest.setScopes(scopes.stream().map(o -> o.toString()).collect(Collectors.toList()));
              List<String> scopes2 = siteRequest.getScopes();
              searchTenantIntentList(siteRequest, false, true, false, "GET").onSuccess(listTenantIntent -> {
                response200SearchPageTenantIntent(listTenantIntent).onSuccess(response -> {
                  eventHandler.handle(Future.succeededFuture(response));
                  LOG.debug(String.format("searchpageTenantIntent succeeded. "));
                }).onFailure(ex -> {
                  LOG.error(String.format("searchpageTenantIntent failed. "), ex);
                  error(siteRequest, eventHandler, ex);
                });
              }).onFailure(ex -> {
                LOG.error(String.format("searchpageTenantIntent failed. "), ex);
                error(siteRequest, eventHandler, ex);
              });
            }
          } catch(Exception ex) {
            LOG.error(String.format("searchpageTenantIntent failed. "), ex);
            error(null, eventHandler, ex);
          }
        });
      } catch(Exception ex) {
        LOG.error(String.format("searchpageTenantIntent failed. "), ex);
        error(null, eventHandler, ex);
      }
    }).onFailure(ex -> {
      if("Inactive Token".equals(ex.getMessage()) || StringUtils.startsWith(ex.getMessage(), "invalid_grant:")) {
        try {
          eventHandler.handle(Future.succeededFuture(new ServiceResponse(302, "Found", null, MultiMap.caseInsensitiveMultiMap().add(HttpHeaders.LOCATION, "/logout?redirect_uri=" + URLEncoder.encode(serviceRequest.getExtra().getString("uri"), "UTF-8")))));
        } catch(Exception ex2) {
          LOG.error(String.format("searchpageTenantIntent failed. ", ex2));
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
        LOG.error(String.format("searchpageTenantIntent failed. "), ex);
        error(null, eventHandler, ex);
      }
    });
    }).onFailure(ex -> {
      if("Inactive Token".equals(ex.getMessage()) || StringUtils.startsWith(ex.getMessage(), "invalid_grant:")) {
        try {
          eventHandler.handle(Future.succeededFuture(new ServiceResponse(302, "Found", null, MultiMap.caseInsensitiveMultiMap().add(HttpHeaders.LOCATION, "/logout?redirect_uri=" + URLEncoder.encode(serviceRequest.getExtra().getString("uri"), "UTF-8")))));
        } catch(Exception ex2) {
          LOG.error(String.format("searchpageTenantIntent failed. ", ex2));
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
        LOG.error(String.format("searchpageTenantIntent failed. "), ex);
        error(null, eventHandler, ex);
      }
    });
  }

  public void searchpageTenantIntentPageInit(JsonObject ctx, TenantIntentPage page, SearchList<TenantIntent> listTenantIntent, Promise<Void> promise) {
    String siteBaseUrl = config.getString(ComputateConfigKeys.SITE_BASE_URL);

    ctx.put("enUSUrlSearchPage", String.format("%s%s", siteBaseUrl, "/en-us/search/intent/tenant"));
    ctx.put("enUSUrlPage", String.format("%s%s", siteBaseUrl, "/en-us/search/intent/tenant"));
    ctx.put("enUSUrlDisplayPage", Optional.ofNullable(page.getResult()).map(o -> o.getDisplayPage()));
    ctx.put("enUSUrlEditPage", Optional.ofNullable(page.getResult()).map(o -> o.getEditPage()));
    ctx.put("enUSUrlUserPage", Optional.ofNullable(page.getResult()).map(o -> o.getUserPage()));
    ctx.put("enUSUrlDownload", Optional.ofNullable(page.getResult()).map(o -> o.getDownload()));

    promise.complete();
  }

  public String templateUriSearchPageTenantIntent(ServiceRequest serviceRequest, TenantIntent result) {
    return "en-us/search/intent/tenant/TenantIntentSearchPage.htm";
  }
  public void templateSearchPageTenantIntent(JsonObject ctx, TenantIntentPage page, SearchList<TenantIntent> listTenantIntent, Promise<String> promise) {
    try {
      SiteRequest siteRequest = listTenantIntent.getSiteRequest_(SiteRequest.class);
      ServiceRequest serviceRequest = siteRequest.getServiceRequest();
      TenantIntent result = listTenantIntent.first();
      String pageTemplateUri = templateUriSearchPageTenantIntent(serviceRequest, result);
      String siteTemplatePath = config.getString(ComputateConfigKeys.TEMPLATE_PATH);
      Path resourceTemplatePath = Path.of(siteTemplatePath, pageTemplateUri);
      if(result == null || !Files.exists(resourceTemplatePath)) {
        String template = Files.readString(Path.of(siteTemplatePath, "en-us/search/intent/tenant/TenantIntentSearchPage.htm"), Charset.forName("UTF-8"));
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
      LOG.error(String.format("templateSearchPageTenantIntent failed. "), ex);
      ExceptionUtils.rethrow(ex);
    }
  }
  public Future<ServiceResponse> response200SearchPageTenantIntent(SearchList<TenantIntent> listTenantIntent) {
    Promise<ServiceResponse> promise = Promise.promise();
    try {
      SiteRequest siteRequest = listTenantIntent.getSiteRequest_(SiteRequest.class);
      TenantIntentPage page = new TenantIntentPage();
      MultiMap requestHeaders = MultiMap.caseInsensitiveMultiMap();
      siteRequest.setRequestHeaders(requestHeaders);

      if(listTenantIntent.size() >= 1)
        siteRequest.setRequestPk(listTenantIntent.get(0).getPk());
      page.setSearchListTenantIntent_(listTenantIntent);
      page.setSiteRequest_(siteRequest);
      page.setServiceRequest(siteRequest.getServiceRequest());
      page.setWebClient(webClient);
      page.setVertx(vertx);
      page.promiseDeepTenantIntentPage(siteRequest).onSuccess(a -> {
        try {
          JsonObject ctx = ConfigKeys.getPageContext(config);
          ctx.mergeIn(JsonObject.mapFrom(page));
          Promise<Void> promise1 = Promise.promise();
          searchpageTenantIntentPageInit(ctx, page, listTenantIntent, promise1);
          promise1.future().onSuccess(b -> {
            try {
              Promise<String> promise2 = Promise.promise();
              templateSearchPageTenantIntent(ctx, page, listTenantIntent, promise2);
              promise2.future().onSuccess(renderedTemplate -> {
                try {
                  Buffer buffer = Buffer.buffer(renderedTemplate);
                  promise.complete(new ServiceResponse(200, "OK", buffer, requestHeaders));
                } catch(Throwable ex) {
                  LOG.error(String.format("response200SearchPageTenantIntent failed. "), ex);
                  promise.fail(ex);
                }
              }).onFailure(ex -> {
                promise.fail(ex);
              });
            } catch(Throwable ex) {
              LOG.error(String.format("response200SearchPageTenantIntent failed. "), ex);
              promise.tryFail(ex);
            }
          }).onFailure(ex -> {
            promise.tryFail(ex);
          });
        } catch(Exception ex) {
          LOG.error(String.format("response200SearchPageTenantIntent failed. "), ex);
          promise.tryFail(ex);
        }
      }).onFailure(ex -> {
        promise.tryFail(ex);
      });
    } catch(Exception ex) {
      LOG.error(String.format("response200SearchPageTenantIntent failed. "), ex);
      promise.tryFail(ex);
    }
    return promise.future();
  }
  public void responsePivotSearchPageTenantIntent(List<SolrResponse.Pivot> pivots, JsonArray pivotArray) {
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
          responsePivotSearchPageTenantIntent(pivotFields2, pivotArray2);
        }
      }
    }
  }

  // EditPage //

  @Override
  public void editpageTenantIntent(ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler) {
    Boolean classPublicRead = false;
    user(serviceRequest, SiteRequest.class, SiteUser.class, SiteUser.getClassApiAddress(), "postSiteUserFuture", "patchSiteUserFuture", classPublicRead).onSuccess(siteRequest -> {
      try {
        siteRequest.setLang("enUS");
        String tenantResource = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("tenantResource");
        String TENANT = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("TENANT");
        List<String> groups = Optional.ofNullable(siteRequest.getGroups()).orElse(new ArrayList<>());
        MultiMap form = MultiMap.caseInsensitiveMultiMap();
        form.add("grant_type", "urn:ietf:params:oauth:grant-type:uma-ticket");
        form.add("audience", config.getString(ComputateConfigKeys.AUTH_CLIENT));
        form.add("response_mode", "permissions");
        form.add("permission", String.format("%s#%s", TenantIntent.CLASS_AUTH_RESOURCE, "GET"));
        form.add("permission", String.format("%s#%s", TenantIntent.CLASS_AUTH_RESOURCE, "POST"));
        form.add("permission", String.format("%s#%s", TenantIntent.CLASS_AUTH_RESOURCE, "PATCH"));
        form.add("permission", String.format("%s#%s", TenantIntent.CLASS_AUTH_RESOURCE, "DELETE"));
        form.add("permission", String.format("%s#%s", TenantIntent.CLASS_AUTH_RESOURCE, "Admin"));
        form.add("permission", String.format("%s#%s", TenantIntent.CLASS_AUTH_RESOURCE, "SuperAdmin"));
        if(tenantResource != null)
          form.add("permission", String.format("%s#%s", tenantResource, "GET"));
        groups.stream().map(group -> {
              Matcher mPermission = Pattern.compile("^/(.*-?TENANT-([a-z0-9\\-]+))-(\\w+)$").matcher(group);
              return mPermission.find() ? mPermission : null;
            }).filter(v -> v != null).forEach(mPermission -> {
              form.add("permission", String.format("%s#%s", mPermission.group(1), mPermission.group(3)));
            });
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
            JsonArray scopes = authorizationDecisionBody.stream().map(o -> (JsonObject)o).filter(o -> "TENANT".equals(o.getString("rsname"))).findFirst().map(decision -> ((JsonObject)decision).getJsonArray("scopes")).orElse(new JsonArray());
            if(!scopes.contains("GET")) {
              List<String> fqs = new ArrayList<>();
              authorizationDecisionBody.stream().map(o -> (JsonObject)o).filter(permission -> {
                    Matcher mPermission = Pattern.compile("^(.*-?TENANT-([a-z0-9\\-]+))$").matcher(permission.getString("rsname"));
                    return permission.getJsonArray("scopes").contains("GET")
                        && mPermission.find();
                  }).forEach(permission -> {
                    fqs.add(String.format("%s:%s", "tenantResource", permission.getString("rsname")));
                    permission.getJsonArray("scopes").stream().map(s -> (String)s).forEach(scope -> {
                      if(!scopes.contains(scope))
                        scopes.add(scope);
                    });
                  });
              if(!classPublicRead) {
                JsonObject authParams = siteRequest.getServiceRequest().getParams();
                JsonObject authQuery = authParams.getJsonObject("query");
                if(authQuery == null) {
                  authQuery = new JsonObject();
                  authParams.put("query", authQuery);
                }
                JsonArray fq = authQuery.getJsonArray("fq");
                if(fq == null) {
                  fq = new JsonArray();
                  authQuery.put("fq", fq);
                }
                if(fqs.size() > 0) {
                  fq.add(fqs.stream().collect(Collectors.joining(" OR ")));
                  if(!scopes.contains("GET"))
                    scopes.add("GET");
                  siteRequest.setFilteredScope(true);
                }
              }
            }
            {
              siteRequest.setScopes(scopes.stream().map(o -> o.toString()).collect(Collectors.toList()));
              List<String> scopes2 = siteRequest.getScopes();
              searchTenantIntentList(siteRequest, false, true, false, "GET").onSuccess(listTenantIntent -> {
                response200EditPageTenantIntent(listTenantIntent).onSuccess(response -> {
                  eventHandler.handle(Future.succeededFuture(response));
                  LOG.debug(String.format("editpageTenantIntent succeeded. "));
                }).onFailure(ex -> {
                  LOG.error(String.format("editpageTenantIntent failed. "), ex);
                  error(siteRequest, eventHandler, ex);
                });
              }).onFailure(ex -> {
                LOG.error(String.format("editpageTenantIntent failed. "), ex);
                error(siteRequest, eventHandler, ex);
            });
            }
          } catch(Exception ex) {
            LOG.error(String.format("editpageTenantIntent failed. "), ex);
            error(null, eventHandler, ex);
          }
        });
      } catch(Exception ex) {
        LOG.error(String.format("editpageTenantIntent failed. "), ex);
        error(null, eventHandler, ex);
      }
    }).onFailure(ex -> {
      if("Inactive Token".equals(ex.getMessage()) || StringUtils.startsWith(ex.getMessage(), "invalid_grant:")) {
        try {
          eventHandler.handle(Future.succeededFuture(new ServiceResponse(302, "Found", null, MultiMap.caseInsensitiveMultiMap().add(HttpHeaders.LOCATION, "/logout?redirect_uri=" + URLEncoder.encode(serviceRequest.getExtra().getString("uri"), "UTF-8")))));
        } catch(Exception ex2) {
          LOG.error(String.format("editpageTenantIntent failed. ", ex2));
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
        LOG.error(String.format("editpageTenantIntent failed. "), ex);
        error(null, eventHandler, ex);
      }
    });
  }

  public void editpageTenantIntentPageInit(JsonObject ctx, TenantIntentPage page, SearchList<TenantIntent> listTenantIntent, Promise<Void> promise) {
    String siteBaseUrl = config.getString(ComputateConfigKeys.SITE_BASE_URL);

    ctx.put("enUSUrlSearchPage", String.format("%s%s", siteBaseUrl, "/en-us/search/intent/tenant"));
    ctx.put("enUSUrlDisplayPage", Optional.ofNullable(page.getResult()).map(o -> o.getDisplayPage()));
    ctx.put("enUSUrlEditPage", Optional.ofNullable(page.getResult()).map(o -> o.getEditPage()));
    ctx.put("enUSUrlPage", Optional.ofNullable(page.getResult()).map(o -> o.getEditPage()));
    ctx.put("enUSUrlUserPage", Optional.ofNullable(page.getResult()).map(o -> o.getUserPage()));
    ctx.put("enUSUrlDownload", Optional.ofNullable(page.getResult()).map(o -> o.getDownload()));

    promise.complete();
  }

  public String templateUriEditPageTenantIntent(ServiceRequest serviceRequest, TenantIntent result) {
    return "en-us/edit/intent/tenant/TenantIntentEditPage.htm";
  }
  public void templateEditPageTenantIntent(JsonObject ctx, TenantIntentPage page, SearchList<TenantIntent> listTenantIntent, Promise<String> promise) {
    try {
      SiteRequest siteRequest = listTenantIntent.getSiteRequest_(SiteRequest.class);
      ServiceRequest serviceRequest = siteRequest.getServiceRequest();
      TenantIntent result = listTenantIntent.first();
      String pageTemplateUri = templateUriEditPageTenantIntent(serviceRequest, result);
      String siteTemplatePath = config.getString(ComputateConfigKeys.TEMPLATE_PATH);
      Path resourceTemplatePath = Path.of(siteTemplatePath, pageTemplateUri);
      if(result == null || !Files.exists(resourceTemplatePath)) {
        String template = Files.readString(Path.of(siteTemplatePath, "en-us/edit/intent/tenant/TenantIntentEditPage.htm"), Charset.forName("UTF-8"));
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
      LOG.error(String.format("templateEditPageTenantIntent failed. "), ex);
      ExceptionUtils.rethrow(ex);
    }
  }
  public Future<ServiceResponse> response200EditPageTenantIntent(SearchList<TenantIntent> listTenantIntent) {
    Promise<ServiceResponse> promise = Promise.promise();
    try {
      SiteRequest siteRequest = listTenantIntent.getSiteRequest_(SiteRequest.class);
      TenantIntentPage page = new TenantIntentPage();
      MultiMap requestHeaders = MultiMap.caseInsensitiveMultiMap();
      siteRequest.setRequestHeaders(requestHeaders);

      if(listTenantIntent.size() >= 1)
        siteRequest.setRequestPk(listTenantIntent.get(0).getPk());
      page.setSearchListTenantIntent_(listTenantIntent);
      page.setSiteRequest_(siteRequest);
      page.setServiceRequest(siteRequest.getServiceRequest());
      page.setWebClient(webClient);
      page.setVertx(vertx);
      page.promiseDeepTenantIntentPage(siteRequest).onSuccess(a -> {
        try {
          JsonObject ctx = ConfigKeys.getPageContext(config);
          ctx.mergeIn(JsonObject.mapFrom(page));
          Promise<Void> promise1 = Promise.promise();
          editpageTenantIntentPageInit(ctx, page, listTenantIntent, promise1);
          promise1.future().onSuccess(b -> {
            try {
              Promise<String> promise2 = Promise.promise();
              templateEditPageTenantIntent(ctx, page, listTenantIntent, promise2);
              promise2.future().onSuccess(renderedTemplate -> {
                try {
                  Buffer buffer = Buffer.buffer(renderedTemplate);
                  promise.complete(new ServiceResponse(200, "OK", buffer, requestHeaders));
                } catch(Throwable ex) {
                  LOG.error(String.format("response200EditPageTenantIntent failed. "), ex);
                  promise.fail(ex);
                }
              }).onFailure(ex -> {
                promise.fail(ex);
              });
            } catch(Throwable ex) {
              LOG.error(String.format("response200EditPageTenantIntent failed. "), ex);
              promise.tryFail(ex);
            }
          }).onFailure(ex -> {
            promise.tryFail(ex);
          });
        } catch(Exception ex) {
          LOG.error(String.format("response200EditPageTenantIntent failed. "), ex);
          promise.tryFail(ex);
        }
      }).onFailure(ex -> {
        promise.tryFail(ex);
      });
    } catch(Exception ex) {
      LOG.error(String.format("response200EditPageTenantIntent failed. "), ex);
      promise.tryFail(ex);
    }
    return promise.future();
  }
  public void responsePivotEditPageTenantIntent(List<SolrResponse.Pivot> pivots, JsonArray pivotArray) {
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
          responsePivotEditPageTenantIntent(pivotFields2, pivotArray2);
        }
      }
    }
  }

  // DELETEFilter //

  @Override
  public void deletefilterTenantIntent(JsonObject body, ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler) {
    LOG.debug(String.format("deletefilterTenantIntent started. "));
    Boolean classPublicRead = false;
    user(serviceRequest, SiteRequest.class, SiteUser.class, SiteUser.getClassApiAddress(), "postSiteUserFuture", "patchSiteUserFuture", classPublicRead).onSuccess(siteRequest -> {
      try {
        siteRequest.setLang("enUS");
        String tenantResource = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("tenantResource");
        String TENANT = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("TENANT");
        List<String> groups = Optional.ofNullable(siteRequest.getGroups()).orElse(new ArrayList<>());
        MultiMap form = MultiMap.caseInsensitiveMultiMap();
        form.add("grant_type", "urn:ietf:params:oauth:grant-type:uma-ticket");
        form.add("audience", config.getString(ComputateConfigKeys.AUTH_CLIENT));
        form.add("response_mode", "permissions");
        form.add("permission", String.format("%s#%s", TenantIntent.CLASS_AUTH_RESOURCE, "GET"));
        form.add("permission", String.format("%s#%s", TenantIntent.CLASS_AUTH_RESOURCE, "POST"));
        form.add("permission", String.format("%s#%s", TenantIntent.CLASS_AUTH_RESOURCE, "PATCH"));
        form.add("permission", String.format("%s#%s", TenantIntent.CLASS_AUTH_RESOURCE, "DELETE"));
        form.add("permission", String.format("%s#%s", TenantIntent.CLASS_AUTH_RESOURCE, "Admin"));
        form.add("permission", String.format("%s#%s", TenantIntent.CLASS_AUTH_RESOURCE, "SuperAdmin"));
        if(tenantResource != null)
          form.add("permission", String.format("%s#%s", tenantResource, "DELETE"));
        groups.stream().map(group -> {
              Matcher mPermission = Pattern.compile("^/(.*-?TENANT-([a-z0-9\\-]+))-(\\w+)$").matcher(group);
              return mPermission.find() ? mPermission : null;
            }).filter(v -> v != null).forEach(mPermission -> {
              form.add("permission", String.format("%s#%s", mPermission.group(1), mPermission.group(3)));
            });
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
            JsonArray scopes = authorizationDecisionBody.stream().map(o -> (JsonObject)o).filter(o -> "TENANT".equals(o.getString("rsname"))).findFirst().map(decision -> ((JsonObject)decision).getJsonArray("scopes")).orElse(new JsonArray());
            if(!scopes.contains("DELETE")) {
            List<String> fqs = new ArrayList<>();
            authorizationDecisionBody.stream().map(o -> (JsonObject)o).filter(permission -> {
                  Matcher mPermission = Pattern.compile("^(.*-?TENANT-([a-z0-9\\-]+))$").matcher(permission.getString("rsname"));
                  return permission.getJsonArray("scopes").contains("DELETE")
                      && mPermission.find();
                }).forEach(permission -> {
                  fqs.add(String.format("%s:%s", "tenantResource", permission.getString("rsname")));
                  permission.getJsonArray("scopes").stream().map(s -> (String)s).forEach(scope -> {
                    if(!scopes.contains(scope))
                      scopes.add(scope);
                  });
                });
              JsonObject authParams = siteRequest.getServiceRequest().getParams();
              JsonObject authQuery = authParams.getJsonObject("query");
              if(authQuery == null) {
                authQuery = new JsonObject();
                authParams.put("query", authQuery);
              }
              JsonArray fq = authQuery.getJsonArray("fq");
              if(fq == null) {
                fq = new JsonArray();
                authQuery.put("fq", fq);
              }
              if(fqs.size() > 0) {
                fq.add(fqs.stream().collect(Collectors.joining(" OR ")));
                if(!scopes.contains("DELETE"))
                  scopes.add("DELETE");
                siteRequest.setFilteredScope(true);
              }
            }
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
              searchTenantIntentList(siteRequest, false, true, true, "DELETE").onSuccess(listTenantIntent -> {
                try {
                  ApiRequest apiRequest = new ApiRequest();
                  apiRequest.setRows(listTenantIntent.getRequest().getRows());
                  apiRequest.setNumFound(listTenantIntent.getResponse().getResponse().getNumFound());
                  apiRequest.setNumPATCH(0L);
                  apiRequest.initDeepApiRequest(siteRequest);
                  siteRequest.setApiRequest_(apiRequest);
                  if(apiRequest.getNumFound() == 1L)
                    apiRequest.setOriginal(listTenantIntent.first());
                  apiRequest.setSolrId(Optional.ofNullable(listTenantIntent.first()).map(o2 -> o2.getSolrId()).orElse(null));
                  eventBus.publish("websocketTenantIntent", JsonObject.mapFrom(apiRequest).toString());

                  listDELETEFilterTenantIntent(apiRequest, listTenantIntent).onSuccess(e -> {
                    response200DELETEFilterTenantIntent(siteRequest).onSuccess(response -> {
                      LOG.debug(String.format("deletefilterTenantIntent succeeded. "));
                      eventHandler.handle(Future.succeededFuture(response));
                    }).onFailure(ex -> {
                      LOG.error(String.format("deletefilterTenantIntent failed. "), ex);
                      error(siteRequest, eventHandler, ex);
                    });
                  }).onFailure(ex -> {
                    LOG.error(String.format("deletefilterTenantIntent failed. "), ex);
                    error(siteRequest, eventHandler, ex);
                  });
                } catch(Exception ex) {
                  LOG.error(String.format("deletefilterTenantIntent failed. "), ex);
                  error(siteRequest, eventHandler, ex);
                }
              }).onFailure(ex -> {
                LOG.error(String.format("deletefilterTenantIntent failed. "), ex);
                error(siteRequest, eventHandler, ex);
              });
            }
          } catch(Exception ex) {
            LOG.error(String.format("deletefilterTenantIntent failed. "), ex);
            error(null, eventHandler, ex);
          }
        });
      } catch(Exception ex) {
        LOG.error(String.format("deletefilterTenantIntent failed. "), ex);
        error(null, eventHandler, ex);
      }
    }).onFailure(ex -> {
      if("Inactive Token".equals(ex.getMessage()) || StringUtils.startsWith(ex.getMessage(), "invalid_grant:")) {
        try {
          eventHandler.handle(Future.succeededFuture(new ServiceResponse(302, "Found", null, MultiMap.caseInsensitiveMultiMap().add(HttpHeaders.LOCATION, "/logout?redirect_uri=" + URLEncoder.encode(serviceRequest.getExtra().getString("uri"), "UTF-8")))));
        } catch(Exception ex2) {
          LOG.error(String.format("deletefilterTenantIntent failed. ", ex2));
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
        LOG.error(String.format("deletefilterTenantIntent failed. "), ex);
        error(null, eventHandler, ex);
      }
    });
  }

  public Future<Void> listDELETEFilterTenantIntent(ApiRequest apiRequest, SearchList<TenantIntent> listTenantIntent) {
    Promise<Void> promise = Promise.promise();
    List<Future> futures = new ArrayList<>();
    SiteRequest siteRequest = listTenantIntent.getSiteRequest_(SiteRequest.class);
    listTenantIntent.getList().forEach(o -> {
      SiteRequest siteRequest2 = generateSiteRequest(siteRequest.getUser(), siteRequest.getUserPrincipal(), siteRequest.getServiceRequest(), siteRequest.getJsonObject(), SiteRequest.class);
      siteRequest2.setScopes(siteRequest.getScopes());
      o.setSiteRequest_(siteRequest2);
      siteRequest2.setApiRequest_(siteRequest.getApiRequest_());
      JsonObject jsonObject = JsonObject.mapFrom(o);
      TenantIntent o2 = jsonObject.mapTo(TenantIntent.class);
      o2.setSiteRequest_(siteRequest2);
      futures.add(Future.future(promise1 -> {
        deletefilterTenantIntentFuture(o).onSuccess(a -> {
          promise1.complete();
        }).onFailure(ex -> {
          LOG.error(String.format("listDELETEFilterTenantIntent failed. "), ex);
          promise1.tryFail(ex);
        });
      }));
    });
    CompositeFuture.all(futures).onSuccess( a -> {
      listTenantIntent.next().onSuccess(next -> {
        if(next) {
          listDELETEFilterTenantIntent(apiRequest, listTenantIntent).onSuccess(b -> {
            promise.complete();
          }).onFailure(ex -> {
            LOG.error(String.format("listDELETEFilterTenantIntent failed. "), ex);
            promise.tryFail(ex);
          });
        } else {
          promise.complete();
        }
      }).onFailure(ex -> {
        LOG.error(String.format("listDELETEFilterTenantIntent failed. "), ex);
        promise.tryFail(ex);
      });
    }).onFailure(ex -> {
      LOG.error(String.format("listDELETEFilterTenantIntent failed. "), ex);
      promise.tryFail(ex);
    });
    return promise.future();
  }

  @Override
  public void deletefilterTenantIntentFuture(JsonObject body, ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler) {
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
        searchTenantIntentList(siteRequest, false, true, true, "DELETE").onSuccess(listTenantIntent -> {
          try {
            TenantIntent o = listTenantIntent.first();
            if(o != null && listTenantIntent.getResponse().getResponse().getNumFound() == 1) {
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
              apiRequest.setId(Optional.ofNullable(listTenantIntent.first()).map(o2 -> o2.getTenantResource().toString()).orElse(null));
              apiRequest.setSolrId(Optional.ofNullable(listTenantIntent.first()).map(o2 -> o2.getSolrId()).orElse(null));
              deletefilterTenantIntentFuture(o).onSuccess(o2 -> {
                eventHandler.handle(Future.succeededFuture(ServiceResponse.completedWithJson(Buffer.buffer(new JsonObject().encodePrettily()))));
              }).onFailure(ex -> {
                eventHandler.handle(Future.failedFuture(ex));
              });
            } else {
              eventHandler.handle(Future.succeededFuture(ServiceResponse.completedWithJson(Buffer.buffer(new JsonObject().encodePrettily()))));
            }
          } catch(Exception ex) {
            LOG.error(String.format("deletefilterTenantIntent failed. "), ex);
            error(siteRequest, eventHandler, ex);
          }
        }).onFailure(ex -> {
          LOG.error(String.format("deletefilterTenantIntent failed. "), ex);
          error(siteRequest, eventHandler, ex);
        });
      } catch(Exception ex) {
        LOG.error(String.format("deletefilterTenantIntent failed. "), ex);
        error(null, eventHandler, ex);
      }
    }).onFailure(ex -> {
      LOG.error(String.format("deletefilterTenantIntent failed. "), ex);
      error(null, eventHandler, ex);
    });
  }

  public Future<TenantIntent> deletefilterTenantIntentFuture(TenantIntent o) {
    SiteRequest siteRequest = o.getSiteRequest_();
    Promise<TenantIntent> promise = Promise.promise();

    try {
      ApiRequest apiRequest = siteRequest.getApiRequest_();
      Promise<TenantIntent> promise1 = Promise.promise();
      pgPool.withTransaction(sqlConnection -> {
        siteRequest.setSqlConnection(sqlConnection);
        varsTenantIntent(siteRequest).onSuccess(a -> {
          sqlDELETEFilterTenantIntent(o).onSuccess(tenantIntent -> {
            relateTenantIntent(o).onSuccess(d -> {
              unindexTenantIntent(o).onSuccess(o2 -> {
                if(apiRequest != null) {
                  apiRequest.setNumPATCH(apiRequest.getNumPATCH() + 1);
                  if(apiRequest.getNumFound() == 1L && Optional.ofNullable(siteRequest.getJsonObject()).map(json -> json.size() > 0).orElse(false)) {
                    o2.apiRequestTenantIntent();
                    if(apiRequest.getVars().size() > 0 && Optional.ofNullable(siteRequest.getRequestVars().get("refresh")).map(refresh -> !refresh.equals("false")).orElse(true))
                      eventBus.publish("websocketTenantIntent", JsonObject.mapFrom(apiRequest).toString());
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
      }).compose(tenantIntent -> {
        Promise<TenantIntent> promise2 = Promise.promise();
        refreshTenantIntent(o).onSuccess(a -> {
          promise2.complete(o);
        }).onFailure(ex -> {
          promise2.tryFail(ex);
        });
        return promise2.future();
      }).onSuccess(tenantIntent -> {
        promise.complete(tenantIntent);
      }).onFailure(ex -> {
        promise.tryFail(ex);
      });
    } catch(Exception ex) {
      LOG.error(String.format("deletefilterTenantIntentFuture failed. "), ex);
      promise.tryFail(ex);
    }
    return promise.future();
  }

  public Future<Void> sqlDELETEFilterTenantIntent(TenantIntent o) {
    Promise<Void> promise = Promise.promise();
    try {
      SiteRequest siteRequest = o.getSiteRequest_();
      ApiRequest apiRequest = siteRequest.getApiRequest_();
      List<String> solrIds = Optional.ofNullable(apiRequest).map(r -> r.getSolrIds()).orElse(new ArrayList<>());
      List<String> classes = Optional.ofNullable(apiRequest).map(r -> r.getClasses()).orElse(new ArrayList<>());
      SqlConnection sqlConnection = siteRequest.getSqlConnection();
      Integer num = 1;
      StringBuilder bSql = new StringBuilder("DELETE FROM TenantIntent ");
      List<Object> bParams = new ArrayList<Object>();
      Long pk = o.getPk();
      JsonObject jsonObject = siteRequest.getJsonObject();
      TenantIntent o2 = new TenantIntent();
      o2.setSiteRequest_(siteRequest);
      List<Future> futures1 = new ArrayList<>();
      List<Future> futures2 = new ArrayList<>();

      if(jsonObject != null) {
        Set<String> entityVars = jsonObject.fieldNames();
        for(String entityVar : entityVars) {
          switch(entityVar) {
          case TenantIntent.VAR_hostInventoryIds:
            Optional.ofNullable(jsonObject.getJsonArray(entityVar)).orElse(new JsonArray()).stream().map(oVal -> oVal.toString()).forEach(val -> {
              futures2.add(Future.future(promise2 -> {
                searchModel(siteRequest).query(HostInventory.varIndexedHostInventory(HostInventory.VAR_tenantResource), HostInventory.class, val).onSuccess(o3 -> {
                  String solrId2 = Optional.ofNullable(o3).map(o4 -> o4.getSolrId()).filter(solrId3 -> !solrIds.contains(solrId3)).orElse(null);
                  Long pk2 = Optional.ofNullable(o3).map(o4 -> o4.getPk()).orElse(null);
                  if(solrId2 != null) {
                    solrIds.add(solrId2);
                    classes.add("HostInventory");
                  }
                  sql(siteRequest).update(HostInventory.class, pk2).set(HostInventory.VAR_tenantResource, TenantIntent.class, null, null).onSuccess(a -> {
                    promise2.complete();
                  }).onFailure(ex -> {
                    promise2.tryFail(ex);
                  });
                }).onFailure(ex -> {
                  promise2.tryFail(ex);
                });
              }));
            });
            break;
          case TenantIntent.VAR_ansibleProjectIds:
            Optional.ofNullable(jsonObject.getJsonArray(entityVar)).orElse(new JsonArray()).stream().map(oVal -> oVal.toString()).forEach(val -> {
              futures2.add(Future.future(promise2 -> {
                searchModel(siteRequest).query(AnsibleProject.varIndexedAnsibleProject(AnsibleProject.VAR_tenantResource), AnsibleProject.class, val).onSuccess(o3 -> {
                  String solrId2 = Optional.ofNullable(o3).map(o4 -> o4.getSolrId()).filter(solrId3 -> !solrIds.contains(solrId3)).orElse(null);
                  Long pk2 = Optional.ofNullable(o3).map(o4 -> o4.getPk()).orElse(null);
                  if(solrId2 != null) {
                    solrIds.add(solrId2);
                    classes.add("AnsibleProject");
                  }
                  sql(siteRequest).update(AnsibleProject.class, pk2).set(AnsibleProject.VAR_tenantResource, TenantIntent.class, null, null).onSuccess(a -> {
                    promise2.complete();
                  }).onFailure(ex -> {
                    promise2.tryFail(ex);
                  });
                }).onFailure(ex -> {
                  promise2.tryFail(ex);
                });
              }));
            });
            break;
          case TenantIntent.VAR_requested:
            Optional.ofNullable(jsonObject.getJsonArray(entityVar)).orElse(new JsonArray()).stream().map(oVal -> oVal.toString()).forEach(val -> {
              futures2.add(Future.future(promise2 -> {
                searchModel(siteRequest).query(TenantRequested.varIndexedTenantRequested(TenantRequested.VAR_tenantResource), TenantRequested.class, val).onSuccess(o3 -> {
                  String solrId2 = Optional.ofNullable(o3).map(o4 -> o4.getSolrId()).filter(solrId3 -> !solrIds.contains(solrId3)).orElse(null);
                  Long pk2 = Optional.ofNullable(o3).map(o4 -> o4.getPk()).orElse(null);
                  if(solrId2 != null) {
                    solrIds.add(solrId2);
                    classes.add("TenantRequested");
                  }
                  sql(siteRequest).update(TenantRequested.class, pk2).set(TenantRequested.VAR_tenantResource, TenantIntent.class, null, null).onSuccess(a -> {
                    promise2.complete();
                  }).onFailure(ex -> {
                    promise2.tryFail(ex);
                  });
                }).onFailure(ex -> {
                  promise2.tryFail(ex);
                });
              }));
            });
            break;
          case TenantIntent.VAR_dcmDiscovered:
            Optional.ofNullable(jsonObject.getJsonArray(entityVar)).orElse(new JsonArray()).stream().map(oVal -> oVal.toString()).forEach(val -> {
              futures2.add(Future.future(promise2 -> {
                searchModel(siteRequest).query(TenantDiscovered.varIndexedTenantDiscovered(TenantDiscovered.VAR_tenantResource), TenantDiscovered.class, val).onSuccess(o3 -> {
                  String solrId2 = Optional.ofNullable(o3).map(o4 -> o4.getSolrId()).filter(solrId3 -> !solrIds.contains(solrId3)).orElse(null);
                  Long pk2 = Optional.ofNullable(o3).map(o4 -> o4.getPk()).orElse(null);
                  if(solrId2 != null) {
                    solrIds.add(solrId2);
                    classes.add("TenantDiscovered");
                  }
                  sql(siteRequest).update(TenantDiscovered.class, pk2).set(TenantDiscovered.VAR_tenantResource, TenantIntent.class, null, null).onSuccess(a -> {
                    promise2.complete();
                  }).onFailure(ex -> {
                    promise2.tryFail(ex);
                  });
                }).onFailure(ex -> {
                  promise2.tryFail(ex);
                });
              }));
            });
            break;
          case TenantIntent.VAR_dcmRealized:
            Optional.ofNullable(jsonObject.getJsonArray(entityVar)).orElse(new JsonArray()).stream().map(oVal -> oVal.toString()).forEach(val -> {
              futures2.add(Future.future(promise2 -> {
                searchModel(siteRequest).query(TenantRealized.varIndexedTenantRealized(TenantRealized.VAR_tenantResource), TenantRealized.class, val).onSuccess(o3 -> {
                  String solrId2 = Optional.ofNullable(o3).map(o4 -> o4.getSolrId()).filter(solrId3 -> !solrIds.contains(solrId3)).orElse(null);
                  Long pk2 = Optional.ofNullable(o3).map(o4 -> o4.getPk()).orElse(null);
                  if(solrId2 != null) {
                    solrIds.add(solrId2);
                    classes.add("TenantRealized");
                  }
                  sql(siteRequest).update(TenantRealized.class, pk2).set(TenantRealized.VAR_tenantResource, TenantIntent.class, null, null).onSuccess(a -> {
                    promise2.complete();
                  }).onFailure(ex -> {
                    promise2.tryFail(ex);
                  });
                }).onFailure(ex -> {
                  promise2.tryFail(ex);
                });
              }));
            });
            break;
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
          RuntimeException ex2 = new RuntimeException("value TenantIntent failed", ex);
          LOG.error(String.format("unrelateTenantIntent failed. "), ex2);
          a.handle(Future.failedFuture(ex2));
        });
      }));
      CompositeFuture.all(futures1).onSuccess(a -> {
        CompositeFuture.all(futures2).onSuccess(b -> {
          promise.complete();
        }).onFailure(ex -> {
          LOG.error(String.format("sqlDELETEFilterTenantIntent failed. "), ex);
          promise.tryFail(ex);
        });
      }).onFailure(ex -> {
        LOG.error(String.format("sqlDELETEFilterTenantIntent failed. "), ex);
        promise.tryFail(ex);
      });
    } catch(Exception ex) {
      LOG.error(String.format("sqlDELETEFilterTenantIntent failed. "), ex);
      promise.tryFail(ex);
    }
    return promise.future();
  }

  public Future<ServiceResponse> response200DELETEFilterTenantIntent(SiteRequest siteRequest) {
    Promise<ServiceResponse> promise = Promise.promise();
    try {
      JsonObject json = new JsonObject();
      promise.complete(ServiceResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily())));
    } catch(Exception ex) {
      LOG.error(String.format("response200DELETEFilterTenantIntent failed. "), ex);
      promise.tryFail(ex);
    }
    return promise.future();
  }

  // General //

  public Future<TenantIntent> createTenantIntent(SiteRequest siteRequest) {
    Promise<TenantIntent> promise = Promise.promise();
    try {
      SqlConnection sqlConnection = siteRequest.getSqlConnection();
      String userId = siteRequest.getUserId();
      Long userKey = siteRequest.getUserKey();
      ZonedDateTime created = Optional.ofNullable(siteRequest.getJsonObject()).map(j -> j.getString("created")).map(s -> ZonedDateTime.parse(s, ComputateZonedDateTimeSerializer.ZONED_DATE_TIME_FORMATTER.withZone(ZoneId.of(config.getString(ConfigKeys.SITE_ZONE))))).orElse(ZonedDateTime.now(ZoneId.of(config.getString(ConfigKeys.SITE_ZONE))));

      sqlConnection.preparedQuery("INSERT INTO TenantIntent(created, userKey) VALUES($1, $2) RETURNING pk")
          .collecting(Collectors.toList())
          .execute(Tuple.of(created.toOffsetDateTime(), userKey)).onSuccess(result -> {
        Row createLine = result.value().stream().findFirst().orElseGet(() -> null);
        Long pk = createLine.getLong(0);
        TenantIntent o = new TenantIntent();
        o.setPk(pk);
        o.setSiteRequest_(siteRequest);
        promise.complete(o);
      }).onFailure(ex -> {
        RuntimeException ex2 = new RuntimeException(ex);
        LOG.error("createTenantIntent failed. ", ex2);
        promise.tryFail(ex2);
      });
    } catch(Exception ex) {
      LOG.error(String.format("createTenantIntent failed. "), ex);
      promise.tryFail(ex);
    }
    return promise.future();
  }

  public void searchTenantIntentQ(SearchList<TenantIntent> searchList, String entityVar, String valueIndexed, String varIndexed) {
    searchList.q(varIndexed + ":" + ("*".equals(valueIndexed) ? valueIndexed : SearchTool.escapeQueryChars(valueIndexed)));
    if(!"*".equals(entityVar)) {
    }
  }

  public String searchTenantIntentFq(SearchList<TenantIntent> searchList, String entityVar, String valueIndexed, String varIndexed) {
    if(varIndexed == null)
      throw new RuntimeException(String.format("\"%s\" is not an indexed entity. ", entityVar));
    if(StringUtils.startsWith(valueIndexed, "[")) {
      String[] fqs = StringUtils.substringAfter(StringUtils.substringBeforeLast(valueIndexed, "]"), "[").split(" TO ");
      if(fqs.length != 2)
        throw new RuntimeException(String.format("\"%s\" invalid range query. ", valueIndexed));
      String fq1 = fqs[0].equals("*") ? fqs[0] : TenantIntent.staticSearchFqForClass(entityVar, searchList.getSiteRequest_(SiteRequest.class), fqs[0]);
      String fq2 = fqs[1].equals("*") ? fqs[1] : TenantIntent.staticSearchFqForClass(entityVar, searchList.getSiteRequest_(SiteRequest.class), fqs[1]);
       return varIndexed + ":[" + fq1 + " TO " + fq2 + "]";
    } else {
      return varIndexed + ":" + SearchTool.escapeQueryChars(TenantIntent.staticSearchFqForClass(entityVar, searchList.getSiteRequest_(SiteRequest.class), valueIndexed)).replace("\\", "\\\\");
    }
  }

  public void searchTenantIntentSort(SearchList<TenantIntent> searchList, String entityVar, String valueIndexed, String varIndexed) {
    if(varIndexed == null)
      throw new RuntimeException(String.format("\"%s\" is not an indexed entity. ", entityVar));
    searchList.sort(varIndexed, valueIndexed);
  }

  public void searchTenantIntentRows(SearchList<TenantIntent> searchList, Long valueRows) {
      searchList.rows(valueRows != null ? valueRows : 10L);
  }

  public void searchTenantIntentStart(SearchList<TenantIntent> searchList, Long valueStart) {
    searchList.start(valueStart);
  }

  public void searchTenantIntentVar(SearchList<TenantIntent> searchList, String var, String value) {
    searchList.getSiteRequest_(SiteRequest.class).getRequestVars().put(var, value);
  }

  public void searchTenantIntentUri(SearchList<TenantIntent> searchList) {
  }

  public Future<ServiceResponse> varsTenantIntent(SiteRequest siteRequest) {
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
          LOG.error(String.format("searchTenantIntent failed. "), ex);
          promise.tryFail(ex);
        }
      });
      promise.complete();
    } catch(Exception ex) {
      LOG.error(String.format("searchTenantIntent failed. "), ex);
      promise.tryFail(ex);
    }
    return promise.future();
  }

  public Future<SearchList<TenantIntent>> searchTenantIntentList(SiteRequest siteRequest, Boolean populate, Boolean store, Boolean modify, String scope) {
    Promise<SearchList<TenantIntent>> promise = Promise.promise();
    try {
      ServiceRequest serviceRequest = siteRequest.getServiceRequest();
      String entityListStr = siteRequest.getServiceRequest().getParams().getJsonObject("query").getString("fl");
      String[] entityList = entityListStr == null ? null : entityListStr.split(",\\s*");
      SearchList<TenantIntent> searchList = new SearchList<TenantIntent>();
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
      searchList.setC(TenantIntent.class);
      searchList.setSiteRequest_(siteRequest);
      searchList.facetMinCount(1);
      if(entityList != null) {
        for(String v : entityList) {
          searchList.fl(TenantIntent.varIndexedTenantIntent(v));
        }
      }

      String tenantResource = serviceRequest.getParams().getJsonObject("path").getString("tenantResource");
      if(tenantResource != null) {
        searchList.fq("tenantResource_docvalues_string:" + SearchTool.escapeQueryChars(tenantResource));
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
                varsIndexed[i] = TenantIntent.varIndexedTenantIntent(entityVar);
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
                  varIndexed = TenantIntent.varIndexedTenantIntent(entityVar);
                  String entityQ = searchTenantIntentFq(searchList, entityVar, valueIndexed, varIndexed);
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
                  varIndexed = TenantIntent.varIndexedTenantIntent(entityVar);
                  String entityFq = searchTenantIntentFq(searchList, entityVar, valueIndexed, varIndexed);
                  mFq.appendReplacement(sb, entityFq);
                }
                if(!sb.isEmpty()) {
                  mFq.appendTail(sb);
                  searchList.fq(sb.toString());
                }
              } else if(paramName.equals("sort")) {
                entityVar = StringUtils.trim(StringUtils.substringBefore((String)paramObject, " "));
                valueIndexed = StringUtils.trim(StringUtils.substringAfter((String)paramObject, " "));
                varIndexed = TenantIntent.varIndexedTenantIntent(entityVar);
                searchTenantIntentSort(searchList, entityVar, valueIndexed, varIndexed);
              } else if(paramName.equals("start")) {
                valueStart = paramObject instanceof Long ? (Long)paramObject : Long.parseLong(paramObject.toString());
                searchTenantIntentStart(searchList, valueStart);
              } else if(paramName.equals("rows")) {
                valueRows = paramObject instanceof Long ? (Long)paramObject : Long.parseLong(paramObject.toString());
                searchTenantIntentRows(searchList, valueRows);
              } else if(paramName.equals("stats")) {
                searchList.stats((Boolean)paramObject);
              } else if(paramName.equals("stats.field")) {
                Matcher mStats = Pattern.compile("(?:(\\{![^\\}]+\\}))?(.*)").matcher((String)paramObject);
                if(mStats.find()) {
                  String solrLocalParams = mStats.group(1);
                  entityVar = mStats.group(2).trim();
                  varIndexed = TenantIntent.varIndexedTenantIntent(entityVar);
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
                  varIndexed = TenantIntent.varIndexedTenantIntent(entityVar);
                  searchList.facetRange((solrLocalParams == null ? "" : solrLocalParams) + varIndexed);
                  facetRange = entityVar;
                }
              } else if(paramName.equals("facet.field")) {
                entityVar = (String)paramObject;
                varIndexed = TenantIntent.varIndexedTenantIntent(entityVar);
                if(varIndexed != null)
                  searchList.facetField(varIndexed);
              } else if(paramName.equals("var")) {
                entityVar = StringUtils.trim(StringUtils.substringBefore((String)paramObject, ":"));
                valueIndexed = URLDecoder.decode(StringUtils.trim(StringUtils.substringAfter((String)paramObject, ":")), "UTF-8");
                searchTenantIntentVar(searchList, entityVar, valueIndexed);
              } else if(paramName.equals("cursorMark")) {
                valueCursorMark = (String)paramObject;
                searchList.cursorMark((String)paramObject);
              }
            }
            searchTenantIntentUri(searchList);
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
      searchTenantIntent2(siteRequest, populate, store, modify, searchList);
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
            LOG.error(String.format("searchTenantIntent failed. "), ex);
            promise.tryFail(ex);
          });
        } else {
          promise.complete(searchList);
        }
      }).onFailure(ex -> {
        LOG.error(String.format("searchTenantIntent failed. "), ex);
        promise.tryFail(ex);
      });
    } catch(Exception ex) {
      LOG.error(String.format("searchTenantIntent failed. "), ex);
      promise.tryFail(ex);
    }
    return promise.future();
  }
  public void searchTenantIntent2(SiteRequest siteRequest, Boolean populate, Boolean store, Boolean modify, SearchList<TenantIntent> searchList) {
  }

  public Future<JsonObject> upsertTenantIntent(TenantIntent o, Boolean inheritPrimaryKey, Boolean patch) {
    Promise<JsonObject> promise = Promise.promise();
    try {
      SiteRequest siteRequest = o.getSiteRequest_();
      ServiceRequest serviceRequest = siteRequest.getServiceRequest();
      if(Optional.ofNullable(serviceRequest.getParams()).map(p -> p.getJsonObject("query")).map( q -> q.getJsonArray("var")).orElse(new JsonArray()).stream().filter(s -> "refresh:false".equals(s)).count() > 0L) {
        promise.complete();
      } else {
        JsonObject json = o.getSiteRequest_().getJsonObject();

        String old_hubId = TenantIntent.staticJsonHubId(o.getHubId());
        String new_hubId = json.getString(TenantIntent.varJson(TenantIntent.VAR_hubId, patch));
        String hubId = Optional.ofNullable(Optional.ofNullable(new_hubId).orElse(old_hubId)).orElse(null);
        // json.put(TenantIntent.varJson(TenantIntent.VAR_hubId, patch), hubId);

        String old_clusterName = TenantIntent.staticJsonClusterName(o.getClusterName());
        String new_clusterName = json.getString(TenantIntent.varJson(TenantIntent.VAR_clusterName, patch));
        String clusterName = Optional.ofNullable(Optional.ofNullable(new_clusterName).orElse(old_clusterName)).orElse(null);
        // json.put(TenantIntent.varJson(TenantIntent.VAR_clusterName, patch), clusterName);

        String old_created = TenantIntent.staticJsonCreated(o.getCreated());
        String new_created = json.getString(TenantIntent.varJson(TenantIntent.VAR_created, patch));
        String created = Optional.ofNullable(Optional.ofNullable(new_created).orElse(old_created)).orElse(null);
        // json.put(TenantIntent.varJson(TenantIntent.VAR_created, patch), created);

        String old_aapOrganizationId = TenantIntent.staticJsonAapOrganizationId(o.getAapOrganizationId());
        String new_aapOrganizationId = json.getString(TenantIntent.varJson(TenantIntent.VAR_aapOrganizationId, patch));
        String aapOrganizationId = Optional.ofNullable(Optional.ofNullable(new_aapOrganizationId).orElse(old_aapOrganizationId)).orElse(null);
        // json.put(TenantIntent.varJson(TenantIntent.VAR_aapOrganizationId, patch), aapOrganizationId);

        String old_tenantName = TenantIntent.staticJsonTenantName(o.getTenantName());
        String new_tenantName = json.getString(TenantIntent.varJson(TenantIntent.VAR_tenantName, patch));
        String tenantName = Optional.ofNullable(Optional.ofNullable(new_tenantName).orElse(old_tenantName)).orElse(null);
        // json.put(TenantIntent.varJson(TenantIntent.VAR_tenantName, patch), tenantName);

        JsonArray old_hostInventoryIds = TenantIntent.staticJsonHostInventoryIds(o.getHostInventoryIds());
        JsonArray new_hostInventoryIds = json.getJsonArray(TenantIntent.varJson(TenantIntent.VAR_hostInventoryIds, patch));
        JsonArray hostInventoryIds = Optional.ofNullable(Optional.ofNullable(new_hostInventoryIds).orElse(old_hostInventoryIds)).orElse(null);
        // json.put(TenantIntent.varJson(TenantIntent.VAR_hostInventoryIds, patch), hostInventoryIds);

        String old_tenantId = TenantIntent.staticJsonTenantId(o.getTenantId());
        String new_tenantId = json.getString(TenantIntent.varJson(TenantIntent.VAR_tenantId, patch));
        String tenantId = Optional.ofNullable(Optional.ofNullable(new_tenantId).orElse(old_tenantId)).orElse(null);
        // json.put(TenantIntent.varJson(TenantIntent.VAR_tenantId, patch), tenantId);

        Boolean old_archived = TenantIntent.staticJsonArchived(o.getArchived());
        Boolean new_archived = json.getBoolean(TenantIntent.varJson(TenantIntent.VAR_archived, patch));
        Boolean archived = Optional.ofNullable(Optional.ofNullable(new_archived).orElse(old_archived)).orElse(null);
        // json.put(TenantIntent.varJson(TenantIntent.VAR_archived, patch), archived);

        JsonArray old_ansibleProjectIds = TenantIntent.staticJsonAnsibleProjectIds(o.getAnsibleProjectIds());
        JsonArray new_ansibleProjectIds = json.getJsonArray(TenantIntent.varJson(TenantIntent.VAR_ansibleProjectIds, patch));
        JsonArray ansibleProjectIds = Optional.ofNullable(Optional.ofNullable(new_ansibleProjectIds).orElse(old_ansibleProjectIds)).orElse(null);
        // json.put(TenantIntent.varJson(TenantIntent.VAR_ansibleProjectIds, patch), ansibleProjectIds);

        String old_tenantResource = TenantIntent.staticJsonTenantResource(o.getTenantResource());
        String new_tenantResource = json.getString(TenantIntent.varJson(TenantIntent.VAR_tenantResource, patch));
        String tenantResource = String.format("%s-%s", TenantIntent.CLASS_AUTH_RESOURCE, tenantId);
        json.put(TenantIntent.varJson(TenantIntent.VAR_tenantResource, patch), tenantResource);

        String old_createdByEmail = TenantIntent.staticJsonCreatedByEmail(o.getCreatedByEmail());
        String new_createdByEmail = json.getString(TenantIntent.varJson(TenantIntent.VAR_createdByEmail, patch));
        String createdByEmail = siteRequest.getUserEmail();
        json.put(TenantIntent.varJson(TenantIntent.VAR_createdByEmail, patch), createdByEmail);

        String old_createdByUserId = TenantIntent.staticJsonCreatedByUserId(o.getCreatedByUserId());
        String new_createdByUserId = json.getString(TenantIntent.varJson(TenantIntent.VAR_createdByUserId, patch));
        String createdByUserId = siteRequest.getUserId();
        json.put(TenantIntent.varJson(TenantIntent.VAR_createdByUserId, patch), createdByUserId);

        String old_createdByFullName = TenantIntent.staticJsonCreatedByFullName(o.getCreatedByFullName());
        String new_createdByFullName = json.getString(TenantIntent.varJson(TenantIntent.VAR_createdByFullName, patch));
        String createdByFullName = siteRequest.getUserFullName();
        json.put(TenantIntent.varJson(TenantIntent.VAR_createdByFullName, patch), createdByFullName);

        String old_sessionId = TenantIntent.staticJsonSessionId(o.getSessionId());
        String new_sessionId = json.getString(TenantIntent.varJson(TenantIntent.VAR_sessionId, patch));
        String sessionId = Optional.ofNullable(Optional.ofNullable(new_sessionId).orElse(old_sessionId)).orElse(null);
        // json.put(TenantIntent.varJson(TenantIntent.VAR_sessionId, patch), sessionId);

        String old_createdVia = TenantIntent.staticJsonCreatedVia(o.getCreatedVia());
        String new_createdVia = json.getString(TenantIntent.varJson(TenantIntent.VAR_createdVia, patch));
        String createdVia = Optional.ofNullable(Optional.ofNullable(new_createdVia).orElse(old_createdVia)).orElse(null);
        // json.put(TenantIntent.varJson(TenantIntent.VAR_createdVia, patch), createdVia);

        String old_userKey = TenantIntent.staticJsonUserKey(o.getUserKey());
        String new_userKey = json.getString(TenantIntent.varJson(TenantIntent.VAR_userKey, patch));
        String userKey = Optional.ofNullable(Optional.ofNullable(new_userKey).orElse(old_userKey)).orElse(null);
        // json.put(TenantIntent.varJson(TenantIntent.VAR_userKey, patch), userKey);

        String old_intentState = TenantIntent.staticJsonIntentState(o.getIntentState());
        String new_intentState = json.getString(TenantIntent.varJson(TenantIntent.VAR_intentState, patch));
        String intentState = Optional.ofNullable(Optional.ofNullable(new_intentState).orElse(old_intentState)).orElse(null);
        // json.put(TenantIntent.varJson(TenantIntent.VAR_intentState, patch), intentState);

        String old_requestedState = TenantIntent.staticJsonRequestedState(o.getRequestedState());
        String new_requestedState = json.getString(TenantIntent.varJson(TenantIntent.VAR_requestedState, patch));
        String requestedState = Optional.ofNullable(Optional.ofNullable(new_requestedState).orElse(old_requestedState)).orElse(null);
        // json.put(TenantIntent.varJson(TenantIntent.VAR_requestedState, patch), requestedState);

        String old_realizedState = TenantIntent.staticJsonRealizedState(o.getRealizedState());
        String new_realizedState = json.getString(TenantIntent.varJson(TenantIntent.VAR_realizedState, patch));
        String realizedState = Optional.ofNullable(Optional.ofNullable(new_realizedState).orElse(old_realizedState)).orElse(null);
        // json.put(TenantIntent.varJson(TenantIntent.VAR_realizedState, patch), realizedState);

        String old_objectTitle = TenantIntent.staticJsonObjectTitle(o.getObjectTitle());
        String new_objectTitle = json.getString(TenantIntent.varJson(TenantIntent.VAR_objectTitle, patch));
        String objectTitle = Optional.ofNullable(Optional.ofNullable(new_objectTitle).orElse(old_objectTitle)).orElse(null);
        // json.put(TenantIntent.varJson(TenantIntent.VAR_objectTitle, patch), objectTitle);

        String old_description = TenantIntent.staticJsonDescription(o.getDescription());
        String new_description = json.getString(TenantIntent.varJson(TenantIntent.VAR_description, patch));
        String description = Optional.ofNullable(new_description).orElse(String.format("Intent state: %s\nRequested state: %s\nRealized state: %s", intentState, requestedState, realizedState));
        json.put(TenantIntent.varJson(TenantIntent.VAR_description, patch), description);

        String old_displayPage = TenantIntent.staticJsonDisplayPage(o.getDisplayPage());
        String new_displayPage = json.getString(TenantIntent.varJson(TenantIntent.VAR_displayPage, patch));
        String displayPage = Optional.ofNullable(Optional.ofNullable(new_displayPage).orElse(old_displayPage)).orElse(null);
        // json.put(TenantIntent.varJson(TenantIntent.VAR_displayPage, patch), displayPage);

        JsonArray old_requested = TenantIntent.staticJsonRequested(o.getRequested());
        JsonArray new_requested = json.getJsonArray(TenantIntent.varJson(TenantIntent.VAR_requested, patch));
        JsonArray requested = Optional.ofNullable(Optional.ofNullable(new_requested).orElse(old_requested)).orElse(null);
        // json.put(TenantIntent.varJson(TenantIntent.VAR_requested, patch), requested);

        String old_editPage = TenantIntent.staticJsonEditPage(o.getEditPage());
        String new_editPage = json.getString(TenantIntent.varJson(TenantIntent.VAR_editPage, patch));
        String editPage = Optional.ofNullable(Optional.ofNullable(new_editPage).orElse(old_editPage)).orElse(null);
        // json.put(TenantIntent.varJson(TenantIntent.VAR_editPage, patch), editPage);

        Boolean old_locked = TenantIntent.staticJsonLocked(o.getLocked());
        Boolean new_locked = json.getBoolean(TenantIntent.varJson(TenantIntent.VAR_locked, patch));
        Boolean locked = Optional.ofNullable(Optional.ofNullable(new_locked).orElse(old_locked)).orElse(false);
        json.put(TenantIntent.varJson(TenantIntent.VAR_locked, patch), locked);

        String old_userPage = TenantIntent.staticJsonUserPage(o.getUserPage());
        String new_userPage = json.getString(TenantIntent.varJson(TenantIntent.VAR_userPage, patch));
        String userPage = Optional.ofNullable(Optional.ofNullable(new_userPage).orElse(old_userPage)).orElse(null);
        // json.put(TenantIntent.varJson(TenantIntent.VAR_userPage, patch), userPage);

        JsonArray old_dcmDiscovered = TenantIntent.staticJsonDcmDiscovered(o.getDcmDiscovered());
        JsonArray new_dcmDiscovered = json.getJsonArray(TenantIntent.varJson(TenantIntent.VAR_dcmDiscovered, patch));
        JsonArray dcmDiscovered = Optional.ofNullable(Optional.ofNullable(new_dcmDiscovered).orElse(old_dcmDiscovered)).orElse(null);
        // json.put(TenantIntent.varJson(TenantIntent.VAR_dcmDiscovered, patch), dcmDiscovered);

        String old_download = TenantIntent.staticJsonDownload(o.getDownload());
        String new_download = json.getString(TenantIntent.varJson(TenantIntent.VAR_download, patch));
        String download = Optional.ofNullable(Optional.ofNullable(new_download).orElse(old_download)).orElse(null);
        // json.put(TenantIntent.varJson(TenantIntent.VAR_download, patch), download);

        JsonArray old_dcmRealized = TenantIntent.staticJsonDcmRealized(o.getDcmRealized());
        JsonArray new_dcmRealized = json.getJsonArray(TenantIntent.varJson(TenantIntent.VAR_dcmRealized, patch));
        JsonArray dcmRealized = Optional.ofNullable(Optional.ofNullable(new_dcmRealized).orElse(old_dcmRealized)).orElse(null);
        // json.put(TenantIntent.varJson(TenantIntent.VAR_dcmRealized, patch), dcmRealized);

        promise.complete(json);
      }
    } catch(Exception ex) {
      LOG.error(String.format("upsertTenantIntent failed. "), ex);
      promise.tryFail(ex);
    }
    return promise.future();
  }

  public Future<Void> persistTenantIntent(TenantIntent o, Boolean patch) {
    Promise<Void> promise = Promise.promise();
    try {
      SiteRequest siteRequest = o.getSiteRequest_();
      SqlConnection sqlConnection = siteRequest.getSqlConnection();
      Long pk = o.getPk();
      sqlConnection.preparedQuery("SELECT hubId, clusterName, created, aapOrganizationId, tenantName, tenantId, archived, tenantResource, createdByEmail, createdByUserId, createdByFullName, sessionId, createdVia, userKey, intentState, requestedState, realizedState, objectTitle, description, displayPage, editPage, locked, userPage, download FROM TenantIntent WHERE pk=$1")
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
                  LOG.error(String.format("persistTenantIntent failed. "), e);
                }
              }
            }
          }
          o.promiseDeepForClass(siteRequest).onSuccess(a -> {
            promise.complete();
          }).onFailure(ex -> {
            LOG.error(String.format("persistTenantIntent failed. "), ex);
            promise.tryFail(ex);
          });
        } catch(Exception ex) {
          LOG.error(String.format("persistTenantIntent failed. "), ex);
          promise.tryFail(ex);
        }
      }).onFailure(ex -> {
        RuntimeException ex2 = new RuntimeException(ex);
        LOG.error(String.format("persistTenantIntent failed. "), ex2);
        promise.tryFail(ex2);
      });
    } catch(Exception ex) {
      LOG.error(String.format("persistTenantIntent failed. "), ex);
      promise.tryFail(ex);
    }
    return promise.future();
  }

  public Future<Void> relateTenantIntent(TenantIntent o) {
    Promise<Void> promise = Promise.promise();
    promise.complete();
    return promise.future();
  }

  public String searchVar(String varIndexed) {
    return TenantIntent.searchVarTenantIntent(varIndexed);
  }

  @Override
  public String getClassApiAddress() {
    return TenantIntent.CLASS_API_ADDRESS_TenantIntent;
  }

  public Future<TenantIntent> indexTenantIntent(TenantIntent o) {
    Promise<TenantIntent> promise = Promise.promise();
    try {
      SiteRequest siteRequest = o.getSiteRequest_();
      ApiRequest apiRequest = siteRequest.getApiRequest_();
      JsonObject json = new JsonObject();
      JsonObject add = new JsonObject();
      json.put("add", add);
      JsonObject doc = new JsonObject();
      add.put("doc", doc);
      o.indexTenantIntent(doc);
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
        LOG.error(String.format("indexTenantIntent failed. "), new RuntimeException(ex));
        promise.tryFail(ex);
      });
    } catch(Exception ex) {
      LOG.error(String.format("indexTenantIntent failed. "), ex);
      promise.tryFail(ex);
    }
    return promise.future();
  }

  public Future<TenantIntent> unindexTenantIntent(TenantIntent o) {
    Promise<TenantIntent> promise = Promise.promise();
    try {
      SiteRequest siteRequest = o.getSiteRequest_();
      ApiRequest apiRequest = siteRequest.getApiRequest_();
      o.promiseDeepForClass(siteRequest).onSuccess(a -> {
        JsonObject json = new JsonObject();
        JsonObject delete = new JsonObject();
        json.put("delete", delete);
        String query = String.format("filter(%s:%s)", TenantIntent.VAR_solrId, o.obtainForClass(TenantIntent.VAR_solrId));
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
          LOG.error(String.format("unindexTenantIntent failed. "), new RuntimeException(ex));
          promise.tryFail(ex);
        });
      }).onFailure(ex -> {
        LOG.error(String.format("unindexTenantIntent failed. "), ex);
        promise.tryFail(ex);
      });
    } catch(Exception ex) {
      LOG.error(String.format("unindexTenantIntent failed. "), ex);
      promise.tryFail(ex);
    }
    return promise.future();
  }

  public Future<Void> refreshTenantIntent(TenantIntent o) {
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

          if("TenantRequested".equals(classSimpleName2) && solrId2 != null) {
            SearchList<TenantRequested> searchList2 = new SearchList<TenantRequested>();
            searchList2.setStore(true);
            searchList2.q("*:*");
            searchList2.setC(TenantRequested.class);
            searchList2.fq("solrId:" + solrId2);
            searchList2.rows(1L);
            futures.add(Future.future(promise2 -> {
              searchList2.promiseDeepSearchList(siteRequest).onSuccess(b -> {
                TenantRequested o2 = searchList2.getList().stream().findFirst().orElse(null);
                if(o2 != null) {
                  JsonObject params = new JsonObject();
                  params.put("body", new JsonObject());
                  params.put("scopes", siteRequest.getScopes());
                  params.put("cookie", new JsonObject());
                  params.put("path", new JsonObject());
                  params.put("query", new JsonObject().put("q", "*:*").put("fq", new JsonArray().add("solrId:" + solrId2)).put("var", new JsonArray().add("refresh:false")));
                  JsonObject context = new JsonObject().put("params", params).put("user", siteRequest.getUserPrincipal());
                  JsonObject json = new JsonObject().put("context", context);
                  eventBus.request("dcm-enUS-TenantRequested", json, new DeliveryOptions().addHeader("action", "patchTenantRequestedFuture")).onSuccess(c -> {
                    JsonObject responseMessage = (JsonObject)c.body();
                    Integer statusCode = responseMessage.getInteger("statusCode");
                    if(statusCode.equals(200))
                      promise2.complete();
                    else
                      promise2.fail(new RuntimeException(responseMessage.getString("statusMessage")));
                  }).onFailure(ex -> {
                    promise2.fail(ex);
                  });
                }
              }).onFailure(ex -> {
                promise2.fail(ex);
              });
            }));
          }

          if("TenantDiscovered".equals(classSimpleName2) && solrId2 != null) {
            SearchList<TenantDiscovered> searchList2 = new SearchList<TenantDiscovered>();
            searchList2.setStore(true);
            searchList2.q("*:*");
            searchList2.setC(TenantDiscovered.class);
            searchList2.fq("solrId:" + solrId2);
            searchList2.rows(1L);
            futures.add(Future.future(promise2 -> {
              searchList2.promiseDeepSearchList(siteRequest).onSuccess(b -> {
                TenantDiscovered o2 = searchList2.getList().stream().findFirst().orElse(null);
                if(o2 != null) {
                  JsonObject params = new JsonObject();
                  params.put("body", new JsonObject());
                  params.put("scopes", siteRequest.getScopes());
                  params.put("cookie", new JsonObject());
                  params.put("path", new JsonObject());
                  params.put("query", new JsonObject().put("q", "*:*").put("fq", new JsonArray().add("solrId:" + solrId2)).put("var", new JsonArray().add("refresh:false")));
                  JsonObject context = new JsonObject().put("params", params).put("user", siteRequest.getUserPrincipal());
                  JsonObject json = new JsonObject().put("context", context);
                  eventBus.request("dcm-enUS-TenantDiscovered", json, new DeliveryOptions().addHeader("action", "patchTenantDiscoveredFuture")).onSuccess(c -> {
                    JsonObject responseMessage = (JsonObject)c.body();
                    Integer statusCode = responseMessage.getInteger("statusCode");
                    if(statusCode.equals(200))
                      promise2.complete();
                    else
                      promise2.fail(new RuntimeException(responseMessage.getString("statusMessage")));
                  }).onFailure(ex -> {
                    promise2.fail(ex);
                  });
                }
              }).onFailure(ex -> {
                promise2.fail(ex);
              });
            }));
          }

          if("TenantRealized".equals(classSimpleName2) && solrId2 != null) {
            SearchList<TenantRealized> searchList2 = new SearchList<TenantRealized>();
            searchList2.setStore(true);
            searchList2.q("*:*");
            searchList2.setC(TenantRealized.class);
            searchList2.fq("solrId:" + solrId2);
            searchList2.rows(1L);
            futures.add(Future.future(promise2 -> {
              searchList2.promiseDeepSearchList(siteRequest).onSuccess(b -> {
                TenantRealized o2 = searchList2.getList().stream().findFirst().orElse(null);
                if(o2 != null) {
                  JsonObject params = new JsonObject();
                  params.put("body", new JsonObject());
                  params.put("scopes", siteRequest.getScopes());
                  params.put("cookie", new JsonObject());
                  params.put("path", new JsonObject());
                  params.put("query", new JsonObject().put("q", "*:*").put("fq", new JsonArray().add("solrId:" + solrId2)).put("var", new JsonArray().add("refresh:false")));
                  JsonObject context = new JsonObject().put("params", params).put("user", siteRequest.getUserPrincipal());
                  JsonObject json = new JsonObject().put("context", context);
                  eventBus.request("dcm-enUS-TenantRealized", json, new DeliveryOptions().addHeader("action", "patchTenantRealizedFuture")).onSuccess(c -> {
                    JsonObject responseMessage = (JsonObject)c.body();
                    Integer statusCode = responseMessage.getInteger("statusCode");
                    if(statusCode.equals(200))
                      promise2.complete();
                    else
                      promise2.fail(new RuntimeException(responseMessage.getString("statusMessage")));
                  }).onFailure(ex -> {
                    promise2.fail(ex);
                  });
                }
              }).onFailure(ex -> {
                promise2.fail(ex);
              });
            }));
          }
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
          eventBus.request(TenantIntent.getClassApiAddress(), json, new DeliveryOptions().addHeader("action", "patchTenantIntentFuture")).onSuccess(c -> {
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
      LOG.error(String.format("refreshTenantIntent failed. "), ex);
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
      TenantIntent o = new TenantIntent();
      o.setSiteRequest_((SiteRequest)siteRequest);

      o.persistForClass(TenantIntent.VAR_hubId, TenantIntent.staticSetHubId(siteRequest2, (String)result.get(TenantIntent.VAR_hubId)));
      o.persistForClass(TenantIntent.VAR_clusterName, TenantIntent.staticSetClusterName(siteRequest2, (String)result.get(TenantIntent.VAR_clusterName)));
      o.persistForClass(TenantIntent.VAR_created, TenantIntent.staticSetCreated(siteRequest2, (String)result.get(TenantIntent.VAR_created), Optional.ofNullable(siteRequest).map(r -> r.getConfig()).map(config -> config.getString(ConfigKeys.SITE_ZONE)).map(z -> ZoneId.of(z)).orElse(ZoneId.of("UTC"))));
      o.persistForClass(TenantIntent.VAR_aapOrganizationId, TenantIntent.staticSetAapOrganizationId(siteRequest2, (String)result.get(TenantIntent.VAR_aapOrganizationId)));
      o.persistForClass(TenantIntent.VAR_tenantName, TenantIntent.staticSetTenantName(siteRequest2, (String)result.get(TenantIntent.VAR_tenantName)));
      o.persistForClass(TenantIntent.VAR_tenantId, TenantIntent.staticSetTenantId(siteRequest2, (String)result.get(TenantIntent.VAR_tenantId)));
      o.persistForClass(TenantIntent.VAR_archived, TenantIntent.staticSetArchived(siteRequest2, (String)result.get(TenantIntent.VAR_archived)));
      o.persistForClass(TenantIntent.VAR_tenantResource, TenantIntent.staticSetTenantResource(siteRequest2, (String)result.get(TenantIntent.VAR_tenantResource)));
      o.persistForClass(TenantIntent.VAR_createdByEmail, TenantIntent.staticSetCreatedByEmail(siteRequest2, (String)result.get(TenantIntent.VAR_createdByEmail)));
      o.persistForClass(TenantIntent.VAR_createdByUserId, TenantIntent.staticSetCreatedByUserId(siteRequest2, (String)result.get(TenantIntent.VAR_createdByUserId)));
      o.persistForClass(TenantIntent.VAR_createdByFullName, TenantIntent.staticSetCreatedByFullName(siteRequest2, (String)result.get(TenantIntent.VAR_createdByFullName)));
      o.persistForClass(TenantIntent.VAR_sessionId, TenantIntent.staticSetSessionId(siteRequest2, (String)result.get(TenantIntent.VAR_sessionId)));
      o.persistForClass(TenantIntent.VAR_createdVia, TenantIntent.staticSetCreatedVia(siteRequest2, (String)result.get(TenantIntent.VAR_createdVia)));
      o.persistForClass(TenantIntent.VAR_userKey, TenantIntent.staticSetUserKey(siteRequest2, (String)result.get(TenantIntent.VAR_userKey)));
      o.persistForClass(TenantIntent.VAR_intentState, TenantIntent.staticSetIntentState(siteRequest2, (String)result.get(TenantIntent.VAR_intentState)));
      o.persistForClass(TenantIntent.VAR_requestedState, TenantIntent.staticSetRequestedState(siteRequest2, (String)result.get(TenantIntent.VAR_requestedState)));
      o.persistForClass(TenantIntent.VAR_realizedState, TenantIntent.staticSetRealizedState(siteRequest2, (String)result.get(TenantIntent.VAR_realizedState)));
      o.persistForClass(TenantIntent.VAR_objectTitle, TenantIntent.staticSetObjectTitle(siteRequest2, (String)result.get(TenantIntent.VAR_objectTitle)));
      o.persistForClass(TenantIntent.VAR_description, TenantIntent.staticSetDescription(siteRequest2, (String)result.get(TenantIntent.VAR_description)));
      o.persistForClass(TenantIntent.VAR_displayPage, TenantIntent.staticSetDisplayPage(siteRequest2, (String)result.get(TenantIntent.VAR_displayPage)));
      o.persistForClass(TenantIntent.VAR_editPage, TenantIntent.staticSetEditPage(siteRequest2, (String)result.get(TenantIntent.VAR_editPage)));
      o.persistForClass(TenantIntent.VAR_locked, TenantIntent.staticSetLocked(siteRequest2, (String)result.get(TenantIntent.VAR_locked)));
      o.persistForClass(TenantIntent.VAR_userPage, TenantIntent.staticSetUserPage(siteRequest2, (String)result.get(TenantIntent.VAR_userPage)));
      o.persistForClass(TenantIntent.VAR_download, TenantIntent.staticSetDownload(siteRequest2, (String)result.get(TenantIntent.VAR_download)));

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
