package org.computate.dcm.model.eda.host;

import org.computate.dcm.model.eda.tenant.TenantEnUSApiServiceImpl;
import org.computate.dcm.model.eda.tenant.Tenant;
import org.computate.dcm.model.eda.hostinventory.HostInventoryEnUSApiServiceImpl;
import org.computate.dcm.model.eda.hostinventory.HostInventory;
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
import io.vertx.pgclient.PgPool;
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
import io.vertx.ext.web.client.predicate.ResponsePredicate;
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
import org.computate.dcm.model.eda.host.HostPage;


/**
 * Translate: false
 * Generated: true
 **/
public class HostEnUSGenApiServiceImpl extends BaseApiServiceImpl implements HostEnUSGenApiService {

  protected static final Logger LOG = LoggerFactory.getLogger(HostEnUSGenApiServiceImpl.class);

  // Search //

  @Override
  public void searchHost(ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler) {
    Boolean classPublicRead = false;
    user(serviceRequest, SiteRequest.class, SiteUser.class, SiteUser.getClassApiAddress(), "postSiteUserFuture", "patchSiteUserFuture", classPublicRead).onSuccess(siteRequest -> {
      try {
        siteRequest.setLang("enUS");
        String hostResource = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("hostResource");
        String HOST = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("HOST");
        List<String> groups = Optional.ofNullable(siteRequest.getGroups()).orElse(new ArrayList<>());
        MultiMap form = MultiMap.caseInsensitiveMultiMap();
        form.add("grant_type", "urn:ietf:params:oauth:grant-type:uma-ticket");
        form.add("audience", config.getString(ComputateConfigKeys.AUTH_CLIENT));
        form.add("response_mode", "permissions");
        form.add("permission", String.format("%s#%s", Host.CLASS_AUTH_RESOURCE, "GET"));
        form.add("permission", String.format("%s#%s", Host.CLASS_AUTH_RESOURCE, "POST"));
        form.add("permission", String.format("%s#%s", Host.CLASS_AUTH_RESOURCE, "PATCH"));
        form.add("permission", String.format("%s#%s", Host.CLASS_AUTH_RESOURCE, "PUT"));
        form.add("permission", String.format("%s#%s", Host.CLASS_AUTH_RESOURCE, "DELETE"));
        form.add("permission", String.format("%s#%s", Host.CLASS_AUTH_RESOURCE, "Admin"));
        form.add("permission", String.format("%s#%s", Host.CLASS_AUTH_RESOURCE, "SuperAdmin"));
        if(hostResource != null)
          form.add("permission", String.format("%s#%s", hostResource, "GET"));
        groups.stream().map(group -> {
              Matcher mPermission = Pattern.compile("^/(.*-?TENANT-([a-z0-9\\-]+))-(GET)$").matcher(group);
              return mPermission.find() ? mPermission : null;
            }).filter(v -> v != null).forEach(mPermission -> {
              form.add("permission", String.format("%s#%s", mPermission.group(1), mPermission.group(3)));
            });
        groups.stream().map(group -> {
              Matcher mPermission = Pattern.compile("^/(.*-?HOSTINVENTORY-([a-z0-9\\-]+))-(GET)$").matcher(group);
              return mPermission.find() ? mPermission : null;
            }).filter(v -> v != null).forEach(mPermission -> {
              form.add("permission", String.format("%s#%s", mPermission.group(1), mPermission.group(3)));
            });
        groups.stream().map(group -> {
              Matcher mPermission = Pattern.compile("^/(.*-?HOST-([a-z0-9\\-]+))-(GET)$").matcher(group);
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
            JsonArray scopes = authorizationDecisionBody.stream().map(o -> (JsonObject)o).filter(o -> "HOST".equals(o.getString("rsname"))).findFirst().map(decision -> ((JsonObject)decision).getJsonArray("scopes")).orElse(new JsonArray());
            if(!scopes.contains("GET") && !classPublicRead) {
              //
              List<String> fqs = new ArrayList<>();
              authorizationDecisionBody.stream().map(o -> (JsonObject)o).filter(permission -> {
                    Matcher mPermission = Pattern.compile("^(TENANT-([a-z0-9\\-]+))$").matcher(permission.getString("rsname"));
                    return permission.getJsonArray("scopes").contains("GET")
                        && mPermission.find();
                  }).forEach(permission -> {
                    fqs.add(String.format("%s:%s", "tenantResource", permission.getString("rsname")));
                  });
              authorizationDecisionBody.stream().map(o -> (JsonObject)o).filter(permission -> {
                    Matcher mPermission = Pattern.compile("^(HOSTINVENTORY-([a-z0-9\\-]+))$").matcher(permission.getString("rsname"));
                    return permission.getJsonArray("scopes").contains("GET")
                        && mPermission.find();
                  }).forEach(permission -> {
                    fqs.add(String.format("%s:%s", "inventoryResource", permission.getString("rsname")));
                  });
              authorizationDecisionBody.stream().map(o -> (JsonObject)o).filter(permission -> {
                    Matcher mPermission = Pattern.compile("^(HOST-([a-z0-9\\-]+))$").matcher(permission.getString("rsname"));
                    return permission.getJsonArray("scopes").contains("GET")
                        && mPermission.find();
                  }).forEach(permission -> {
                    fqs.add(String.format("%s:%s", "hostResource", permission.getString("rsname")));
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
                scopes.add("GET");
                siteRequest.setFilteredScope(true);
              }
            }
            {
              siteRequest.setScopes(scopes.stream().map(o -> o.toString()).collect(Collectors.toList()));
              List<String> scopes2 = siteRequest.getScopes();
              searchHostList(siteRequest, false, true, false, "GET").onSuccess(listHost -> {
                response200SearchHost(listHost).onSuccess(response -> {
                  eventHandler.handle(Future.succeededFuture(response));
                  LOG.debug(String.format("searchHost succeeded. "));
                }).onFailure(ex -> {
                  LOG.error(String.format("searchHost failed. "), ex);
                  error(siteRequest, eventHandler, ex);
                });
              }).onFailure(ex -> {
                LOG.error(String.format("searchHost failed. "), ex);
                error(siteRequest, eventHandler, ex);
              });
            }
          } catch(Exception ex) {
            LOG.error(String.format("searchHost failed. "), ex);
            error(null, eventHandler, ex);
          }
        });
      } catch(Exception ex) {
        LOG.error(String.format("searchHost failed. "), ex);
        error(null, eventHandler, ex);
      }
    }).onFailure(ex -> {
      if("Inactive Token".equals(ex.getMessage()) || StringUtils.startsWith(ex.getMessage(), "invalid_grant:")) {
        try {
          eventHandler.handle(Future.succeededFuture(new ServiceResponse(302, "Found", null, MultiMap.caseInsensitiveMultiMap().add(HttpHeaders.LOCATION, "/logout?redirect_uri=" + URLEncoder.encode(serviceRequest.getExtra().getString("uri"), "UTF-8")))));
        } catch(Exception ex2) {
          LOG.error(String.format("searchHost failed. ", ex2));
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
        LOG.error(String.format("searchHost failed. "), ex);
        error(null, eventHandler, ex);
      }
    });
  }

  public Future<ServiceResponse> response200SearchHost(SearchList<Host> listHost) {
    Promise<ServiceResponse> promise = Promise.promise();
    try {
      SiteRequest siteRequest = listHost.getSiteRequest_(SiteRequest.class);
      List<String> fls = listHost.getRequest().getFields();
      JsonObject json = new JsonObject();
      JsonArray l = new JsonArray();
      List<String> scopes = siteRequest.getScopes();
      listHost.getList().stream().forEach(o -> {
        JsonObject json2 = JsonObject.mapFrom(o);
        if(fls.size() > 0) {
          Set<String> fieldNames = new HashSet<String>();
          for(String fieldName : json2.fieldNames()) {
            String v = Host.varIndexedHost(fieldName);
            if(v != null)
              fieldNames.add(Host.varIndexedHost(fieldName));
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
      response200Search(listHost.getRequest(), listHost.getResponse(), json);
      promise.complete(ServiceResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily())));
    } catch(Exception ex) {
      LOG.error(String.format("response200SearchHost failed. "), ex);
      promise.tryFail(ex);
    }
    return promise.future();
  }
  public void responsePivotSearchHost(List<SolrResponse.Pivot> pivots, JsonArray pivotArray) {
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
          responsePivotSearchHost(pivotFields2, pivotArray2);
        }
      }
    }
  }

  // GET //

  @Override
  public void getHost(ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler) {
    Boolean classPublicRead = false;
    user(serviceRequest, SiteRequest.class, SiteUser.class, SiteUser.getClassApiAddress(), "postSiteUserFuture", "patchSiteUserFuture", classPublicRead).onSuccess(siteRequest -> {
      try {
        siteRequest.setLang("enUS");
        String hostResource = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("hostResource");
        String HOST = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("HOST");
        List<String> groups = Optional.ofNullable(siteRequest.getGroups()).orElse(new ArrayList<>());
        MultiMap form = MultiMap.caseInsensitiveMultiMap();
        form.add("grant_type", "urn:ietf:params:oauth:grant-type:uma-ticket");
        form.add("audience", config.getString(ComputateConfigKeys.AUTH_CLIENT));
        form.add("response_mode", "permissions");
        form.add("permission", String.format("%s#%s", Host.CLASS_AUTH_RESOURCE, "GET"));
        form.add("permission", String.format("%s#%s", Host.CLASS_AUTH_RESOURCE, "POST"));
        form.add("permission", String.format("%s#%s", Host.CLASS_AUTH_RESOURCE, "PATCH"));
        form.add("permission", String.format("%s#%s", Host.CLASS_AUTH_RESOURCE, "PUT"));
        form.add("permission", String.format("%s#%s", Host.CLASS_AUTH_RESOURCE, "DELETE"));
        form.add("permission", String.format("%s#%s", Host.CLASS_AUTH_RESOURCE, "Admin"));
        form.add("permission", String.format("%s#%s", Host.CLASS_AUTH_RESOURCE, "SuperAdmin"));
        if(hostResource != null)
          form.add("permission", String.format("%s#%s", hostResource, "GET"));
        groups.stream().map(group -> {
              Matcher mPermission = Pattern.compile("^/(.*-?TENANT-([a-z0-9\\-]+))-(GET)$").matcher(group);
              return mPermission.find() ? mPermission : null;
            }).filter(v -> v != null).forEach(mPermission -> {
              form.add("permission", String.format("%s#%s", mPermission.group(1), mPermission.group(3)));
            });
        groups.stream().map(group -> {
              Matcher mPermission = Pattern.compile("^/(.*-?HOSTINVENTORY-([a-z0-9\\-]+))-(GET)$").matcher(group);
              return mPermission.find() ? mPermission : null;
            }).filter(v -> v != null).forEach(mPermission -> {
              form.add("permission", String.format("%s#%s", mPermission.group(1), mPermission.group(3)));
            });
        groups.stream().map(group -> {
              Matcher mPermission = Pattern.compile("^/(.*-?HOST-([a-z0-9\\-]+))-(GET)$").matcher(group);
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
            JsonArray scopes = authorizationDecisionBody.stream().map(o -> (JsonObject)o).filter(o -> "HOST".equals(o.getString("rsname"))).findFirst().map(decision -> ((JsonObject)decision).getJsonArray("scopes")).orElse(new JsonArray());
            if(!scopes.contains("GET") && !classPublicRead) {
              //
              List<String> fqs = new ArrayList<>();
              authorizationDecisionBody.stream().map(o -> (JsonObject)o).filter(permission -> {
                    Matcher mPermission = Pattern.compile("^(TENANT-([a-z0-9\\-]+))$").matcher(permission.getString("rsname"));
                    return permission.getJsonArray("scopes").contains("GET")
                        && mPermission.find();
                  }).forEach(permission -> {
                    fqs.add(String.format("%s:%s", "tenantResource", permission.getString("rsname")));
                  });
              authorizationDecisionBody.stream().map(o -> (JsonObject)o).filter(permission -> {
                    Matcher mPermission = Pattern.compile("^(HOSTINVENTORY-([a-z0-9\\-]+))$").matcher(permission.getString("rsname"));
                    return permission.getJsonArray("scopes").contains("GET")
                        && mPermission.find();
                  }).forEach(permission -> {
                    fqs.add(String.format("%s:%s", "inventoryResource", permission.getString("rsname")));
                  });
              authorizationDecisionBody.stream().map(o -> (JsonObject)o).filter(permission -> {
                    Matcher mPermission = Pattern.compile("^(HOST-([a-z0-9\\-]+))$").matcher(permission.getString("rsname"));
                    return permission.getJsonArray("scopes").contains("GET")
                        && mPermission.find();
                  }).forEach(permission -> {
                    fqs.add(String.format("%s:%s", "hostResource", permission.getString("rsname")));
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
                scopes.add("GET");
                siteRequest.setFilteredScope(true);
              }
            }
            {
              siteRequest.setScopes(scopes.stream().map(o -> o.toString()).collect(Collectors.toList()));
              List<String> scopes2 = siteRequest.getScopes();
              searchHostList(siteRequest, false, true, false, "GET").onSuccess(listHost -> {
                response200GETHost(listHost).onSuccess(response -> {
                  eventHandler.handle(Future.succeededFuture(response));
                  LOG.debug(String.format("getHost succeeded. "));
                }).onFailure(ex -> {
                  LOG.error(String.format("getHost failed. "), ex);
                  error(siteRequest, eventHandler, ex);
                });
              }).onFailure(ex -> {
                LOG.error(String.format("getHost failed. "), ex);
                error(siteRequest, eventHandler, ex);
              });
            }
          } catch(Exception ex) {
            LOG.error(String.format("getHost failed. "), ex);
            error(null, eventHandler, ex);
          }
        });
      } catch(Exception ex) {
        LOG.error(String.format("getHost failed. "), ex);
        error(null, eventHandler, ex);
      }
    }).onFailure(ex -> {
      if("Inactive Token".equals(ex.getMessage()) || StringUtils.startsWith(ex.getMessage(), "invalid_grant:")) {
        try {
          eventHandler.handle(Future.succeededFuture(new ServiceResponse(302, "Found", null, MultiMap.caseInsensitiveMultiMap().add(HttpHeaders.LOCATION, "/logout?redirect_uri=" + URLEncoder.encode(serviceRequest.getExtra().getString("uri"), "UTF-8")))));
        } catch(Exception ex2) {
          LOG.error(String.format("getHost failed. ", ex2));
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
        LOG.error(String.format("getHost failed. "), ex);
        error(null, eventHandler, ex);
      }
    });
  }

  public Future<ServiceResponse> response200GETHost(SearchList<Host> listHost) {
    Promise<ServiceResponse> promise = Promise.promise();
    try {
      SiteRequest siteRequest = listHost.getSiteRequest_(SiteRequest.class);
      JsonObject json = JsonObject.mapFrom(listHost.getList().stream().findFirst().orElse(null));
      promise.complete(ServiceResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily())));
    } catch(Exception ex) {
      LOG.error(String.format("response200GETHost failed. "), ex);
      promise.tryFail(ex);
    }
    return promise.future();
  }

  // PATCH //

  @Override
  public void patchHost(JsonObject body, ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler) {
    LOG.debug(String.format("patchHost started. "));
    Boolean classPublicRead = false;
    user(serviceRequest, SiteRequest.class, SiteUser.class, SiteUser.getClassApiAddress(), "postSiteUserFuture", "patchSiteUserFuture", classPublicRead).onSuccess(siteRequest -> {
      try {
        siteRequest.setLang("enUS");
        String hostResource = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("hostResource");
        String HOST = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("HOST");
        List<String> groups = Optional.ofNullable(siteRequest.getGroups()).orElse(new ArrayList<>());
        MultiMap form = MultiMap.caseInsensitiveMultiMap();
        form.add("grant_type", "urn:ietf:params:oauth:grant-type:uma-ticket");
        form.add("audience", config.getString(ComputateConfigKeys.AUTH_CLIENT));
        form.add("response_mode", "permissions");
        form.add("permission", String.format("%s#%s", Host.CLASS_AUTH_RESOURCE, "GET"));
        form.add("permission", String.format("%s#%s", Host.CLASS_AUTH_RESOURCE, "POST"));
        form.add("permission", String.format("%s#%s", Host.CLASS_AUTH_RESOURCE, "PATCH"));
        form.add("permission", String.format("%s#%s", Host.CLASS_AUTH_RESOURCE, "PUT"));
        form.add("permission", String.format("%s#%s", Host.CLASS_AUTH_RESOURCE, "DELETE"));
        form.add("permission", String.format("%s#%s", Host.CLASS_AUTH_RESOURCE, "Admin"));
        form.add("permission", String.format("%s#%s", Host.CLASS_AUTH_RESOURCE, "SuperAdmin"));
        if(hostResource != null)
          form.add("permission", String.format("%s#%s", hostResource, "PATCH"));
        groups.stream().map(group -> {
              Matcher mPermission = Pattern.compile("^/(.*-?TENANT-([a-z0-9\\-]+))-(PATCH)$").matcher(group);
              return mPermission.find() ? mPermission : null;
            }).filter(v -> v != null).forEach(mPermission -> {
              form.add("permission", String.format("%s#%s", mPermission.group(1), mPermission.group(3)));
            });
        groups.stream().map(group -> {
              Matcher mPermission = Pattern.compile("^/(.*-?HOSTINVENTORY-([a-z0-9\\-]+))-(PATCH)$").matcher(group);
              return mPermission.find() ? mPermission : null;
            }).filter(v -> v != null).forEach(mPermission -> {
              form.add("permission", String.format("%s#%s", mPermission.group(1), mPermission.group(3)));
            });
        groups.stream().map(group -> {
              Matcher mPermission = Pattern.compile("^/(.*-?HOST-([a-z0-9\\-]+))-(PATCH)$").matcher(group);
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
            JsonArray scopes = authorizationDecisionBody.stream().map(o -> (JsonObject)o).filter(o -> "HOST".equals(o.getString("rsname"))).findFirst().map(decision -> ((JsonObject)decision).getJsonArray("scopes")).orElse(new JsonArray());
            if(!scopes.contains("PATCH") && !classPublicRead) {
              //
              List<String> fqs = new ArrayList<>();
              authorizationDecisionBody.stream().map(o -> (JsonObject)o).filter(permission -> {
                    Matcher mPermission = Pattern.compile("^(TENANT-([a-z0-9\\-]+))$").matcher(permission.getString("rsname"));
                    return permission.getJsonArray("scopes").contains("PATCH")
                        && mPermission.find();
                  }).forEach(permission -> {
                    fqs.add(String.format("%s:%s", "tenantResource", permission.getString("rsname")));
                  });
              authorizationDecisionBody.stream().map(o -> (JsonObject)o).filter(permission -> {
                    Matcher mPermission = Pattern.compile("^(HOSTINVENTORY-([a-z0-9\\-]+))$").matcher(permission.getString("rsname"));
                    return permission.getJsonArray("scopes").contains("PATCH")
                        && mPermission.find();
                  }).forEach(permission -> {
                    fqs.add(String.format("%s:%s", "inventoryResource", permission.getString("rsname")));
                  });
              authorizationDecisionBody.stream().map(o -> (JsonObject)o).filter(permission -> {
                    Matcher mPermission = Pattern.compile("^(HOST-([a-z0-9\\-]+))$").matcher(permission.getString("rsname"));
                    return permission.getJsonArray("scopes").contains("PATCH")
                        && mPermission.find();
                  }).forEach(permission -> {
                    fqs.add(String.format("%s:%s", "hostResource", permission.getString("rsname")));
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
              searchHostList(siteRequest, false, true, true, "PATCH").onSuccess(listHost -> {
                try {
                  ApiRequest apiRequest = new ApiRequest();
                  apiRequest.setRows(listHost.getRequest().getRows());
                  apiRequest.setNumFound(listHost.getResponse().getResponse().getNumFound());
                  apiRequest.setNumPATCH(0L);
                  apiRequest.initDeepApiRequest(siteRequest);
                  siteRequest.setApiRequest_(apiRequest);
                  if(apiRequest.getNumFound() == 1L)
                    apiRequest.setOriginal(listHost.first());
                  apiRequest.setId(Optional.ofNullable(listHost.first()).map(o2 -> o2.getHostResource().toString()).orElse(null));
                  apiRequest.setSolrId(Optional.ofNullable(listHost.first()).map(o2 -> o2.getSolrId()).orElse(null));
                  eventBus.publish("websocketHost", JsonObject.mapFrom(apiRequest).toString());

                  listPATCHHost(apiRequest, listHost).onSuccess(e -> {
                    response200PATCHHost(siteRequest).onSuccess(response -> {
                      LOG.debug(String.format("patchHost succeeded. "));
                      eventHandler.handle(Future.succeededFuture(response));
                    }).onFailure(ex -> {
                      LOG.error(String.format("patchHost failed. "), ex);
                      error(siteRequest, eventHandler, ex);
                    });
                  }).onFailure(ex -> {
                    LOG.error(String.format("patchHost failed. "), ex);
                    error(siteRequest, eventHandler, ex);
                  });
                } catch(Exception ex) {
                  LOG.error(String.format("patchHost failed. "), ex);
                  error(siteRequest, eventHandler, ex);
                }
              }).onFailure(ex -> {
                LOG.error(String.format("patchHost failed. "), ex);
                error(siteRequest, eventHandler, ex);
              });
            }
          } catch(Exception ex) {
            LOG.error(String.format("patchHost failed. "), ex);
            error(null, eventHandler, ex);
          }
        });
      } catch(Exception ex) {
        LOG.error(String.format("patchHost failed. "), ex);
        error(null, eventHandler, ex);
      }
    }).onFailure(ex -> {
      if("Inactive Token".equals(ex.getMessage()) || StringUtils.startsWith(ex.getMessage(), "invalid_grant:")) {
        try {
          eventHandler.handle(Future.succeededFuture(new ServiceResponse(302, "Found", null, MultiMap.caseInsensitiveMultiMap().add(HttpHeaders.LOCATION, "/logout?redirect_uri=" + URLEncoder.encode(serviceRequest.getExtra().getString("uri"), "UTF-8")))));
        } catch(Exception ex2) {
          LOG.error(String.format("patchHost failed. ", ex2));
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
        LOG.error(String.format("patchHost failed. "), ex);
        error(null, eventHandler, ex);
      }
    });
  }

  public Future<Void> listPATCHHost(ApiRequest apiRequest, SearchList<Host> listHost) {
    Promise<Void> promise = Promise.promise();
    List<Future> futures = new ArrayList<>();
    SiteRequest siteRequest = listHost.getSiteRequest_(SiteRequest.class);
    listHost.getList().forEach(o -> {
      SiteRequest siteRequest2 = generateSiteRequest(siteRequest.getUser(), siteRequest.getUserPrincipal(), siteRequest.getServiceRequest(), siteRequest.getJsonObject(), SiteRequest.class);
      siteRequest2.setScopes(siteRequest.getScopes());
      o.setSiteRequest_(siteRequest2);
      siteRequest2.setApiRequest_(siteRequest.getApiRequest_());
      JsonObject jsonObject = JsonObject.mapFrom(o);
      Host o2 = jsonObject.mapTo(Host.class);
      o2.setSiteRequest_(siteRequest2);
      futures.add(Future.future(promise1 -> {
        patchHostFuture(o2, false).onSuccess(a -> {
          promise1.complete();
        }).onFailure(ex -> {
          LOG.error(String.format("listPATCHHost failed. "), ex);
          promise1.tryFail(ex);
        });
      }));
    });
    CompositeFuture.all(futures).onSuccess( a -> {
      listHost.next().onSuccess(next -> {
        if(next) {
          listPATCHHost(apiRequest, listHost).onSuccess(b -> {
            promise.complete();
          }).onFailure(ex -> {
            LOG.error(String.format("listPATCHHost failed. "), ex);
            promise.tryFail(ex);
          });
        } else {
          promise.complete();
        }
      }).onFailure(ex -> {
        LOG.error(String.format("listPATCHHost failed. "), ex);
        promise.tryFail(ex);
      });
    }).onFailure(ex -> {
      LOG.error(String.format("listPATCHHost failed. "), ex);
      promise.tryFail(ex);
    });
    return promise.future();
  }

  @Override
  public void patchHostFuture(JsonObject body, ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler) {
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
        searchHostList(siteRequest, false, true, true, "PATCH").onSuccess(listHost -> {
          try {
            Host o = listHost.first();
            ApiRequest apiRequest = new ApiRequest();
            apiRequest.setRows(1L);
            apiRequest.setNumFound(1L);
            apiRequest.setNumPATCH(0L);
            apiRequest.initDeepApiRequest(siteRequest);
            siteRequest.setApiRequest_(apiRequest);
            if(Optional.ofNullable(serviceRequest.getParams()).map(p -> p.getJsonObject("query")).map( q -> q.getJsonArray("var")).orElse(new JsonArray()).stream().filter(s -> "refresh:false".equals(s)).count() > 0L) {
              siteRequest.getRequestVars().put( "refresh", "false" );
            }
            Host o2;
            if(o != null) {
              if(apiRequest.getNumFound() == 1L)
                apiRequest.setOriginal(o);
              apiRequest.setId(Optional.ofNullable(listHost.first()).map(o3 -> o3.getHostResource().toString()).orElse(null));
              apiRequest.setSolrId(Optional.ofNullable(listHost.first()).map(o3 -> o3.getSolrId()).orElse(null));
              JsonObject jsonObject = JsonObject.mapFrom(o);
              o2 = jsonObject.mapTo(Host.class);
              o2.setSiteRequest_(siteRequest);
              patchHostFuture(o2, false).onSuccess(o3 -> {
                eventHandler.handle(Future.succeededFuture(ServiceResponse.completedWithJson(Buffer.buffer(new JsonObject().encodePrettily()))));
              }).onFailure(ex -> {
                eventHandler.handle(Future.failedFuture(ex));
              });
            } else {
              String m = String.format("%s %s not found", "host", null);
              eventHandler.handle(Future.failedFuture(m));
            }
          } catch(Exception ex) {
            LOG.error(String.format("patchHost failed. "), ex);
            error(siteRequest, eventHandler, ex);
          }
        }).onFailure(ex -> {
          LOG.error(String.format("patchHost failed. "), ex);
          error(siteRequest, eventHandler, ex);
        });
      } catch(Exception ex) {
        LOG.error(String.format("patchHost failed. "), ex);
        error(null, eventHandler, ex);
      }
    }).onFailure(ex -> {
      LOG.error(String.format("patchHost failed. "), ex);
      error(null, eventHandler, ex);
    });
  }

  public Future<Host> patchHostFuture(Host o, Boolean inheritPrimaryKey) {
    SiteRequest siteRequest = o.getSiteRequest_();
    Promise<Host> promise = Promise.promise();

    try {
      ApiRequest apiRequest = siteRequest.getApiRequest_();
      Promise<Host> promise1 = Promise.promise();
      pgPool.withTransaction(sqlConnection -> {
        siteRequest.setSqlConnection(sqlConnection);
        varsHost(siteRequest).onSuccess(a -> {
          sqlPATCHHost(o, inheritPrimaryKey).onSuccess(host -> {
            persistHost(host, true).onSuccess(c -> {
              relateHost(host).onSuccess(d -> {
                indexHost(host).onSuccess(o2 -> {
                  if(apiRequest != null) {
                    apiRequest.setNumPATCH(apiRequest.getNumPATCH() + 1);
                    if(apiRequest.getNumFound() == 1L && Optional.ofNullable(siteRequest.getJsonObject()).map(json -> json.size() > 0).orElse(false)) {
                      o2.apiRequestHost();
                      if(apiRequest.getVars().size() > 0 && Optional.ofNullable(siteRequest.getRequestVars().get("refresh")).map(refresh -> !refresh.equals("false")).orElse(true))
                        eventBus.publish("websocketHost", JsonObject.mapFrom(apiRequest).toString());
                    }
                  }
                  promise1.complete(host);
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
      }).compose(host -> {
        Promise<Host> promise2 = Promise.promise();
        refreshHost(host).onSuccess(a -> {
          promise2.complete(host);
        }).onFailure(ex -> {
          promise2.tryFail(ex);
        });
        return promise2.future();
      }).onSuccess(host -> {
        promise.complete(host);
      }).onFailure(ex -> {
        promise.tryFail(ex);
      });
    } catch(Exception ex) {
      LOG.error(String.format("patchHostFuture failed. "), ex);
      promise.tryFail(ex);
    }
    return promise.future();
  }

  public Future<Host> sqlPATCHHost(Host o, Boolean inheritPrimaryKey) {
    Promise<Host> promise = Promise.promise();
    try {
      SiteRequest siteRequest = o.getSiteRequest_();
      ApiRequest apiRequest = siteRequest.getApiRequest_();
      List<String> solrIds = Optional.ofNullable(apiRequest).map(r -> r.getSolrIds()).orElse(new ArrayList<>());
      List<String> classes = Optional.ofNullable(apiRequest).map(r -> r.getClasses()).orElse(new ArrayList<>());
      SqlConnection sqlConnection = siteRequest.getSqlConnection();
      Integer num = 1;
      StringBuilder bSql = new StringBuilder("UPDATE Host SET ");
      List<Object> bParams = new ArrayList<Object>();
      Long pk = o.getPk();
      JsonObject jsonObject = siteRequest.getJsonObject();
      Set<String> methodNames = jsonObject.fieldNames();
      Host o2 = new Host();
      o2.setSiteRequest_(siteRequest);
      List<Future> futures1 = new ArrayList<>();
      List<Future> futures2 = new ArrayList<>();

      for(String entityVar : methodNames) {
        switch(entityVar) {
          case "setTenantResource":
            Optional.ofNullable(jsonObject.getString(entityVar)).ifPresent(val -> {
              futures1.add(Future.future(promise2 -> {
                searchModel(siteRequest).query(Tenant.varIndexedTenant(Tenant.VAR_tenantResource), Tenant.class, val).onSuccess(o3 -> {
                  String solrId2 = Optional.ofNullable(o3).map(o4 -> o4.getSolrId()).filter(solrId3 -> !solrIds.contains(solrId3)).orElse(null);
                  if(solrId2 != null) {
                    solrIds.add(solrId2);
                    classes.add("Tenant");
                  }
                  sql(siteRequest).update(Host.class, pk).set(Host.VAR_tenantResource, Tenant.class, solrId2, val).onSuccess(a -> {
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
          case "removeTenantResource":
            Optional.ofNullable(jsonObject.getString(entityVar)).ifPresent(solrId2 -> {
              futures2.add(Future.future(promise2 -> {
                sql(siteRequest).update(Host.class, pk).setToNull(Host.VAR_tenantResource, Tenant.class, null).onSuccess(a -> {
                  promise2.complete();
                }).onFailure(ex -> {
                  promise2.tryFail(ex);
                });
              }));
            });
            break;
          case "setInventoryResource":
            Optional.ofNullable(jsonObject.getString(entityVar)).ifPresent(val -> {
              futures1.add(Future.future(promise2 -> {
                searchModel(siteRequest).query(HostInventory.varIndexedHostInventory(HostInventory.VAR_inventoryResource), HostInventory.class, val).onSuccess(o3 -> {
                  String solrId2 = Optional.ofNullable(o3).map(o4 -> o4.getSolrId()).filter(solrId3 -> !solrIds.contains(solrId3)).orElse(null);
                  if(solrId2 != null) {
                    solrIds.add(solrId2);
                    classes.add("HostInventory");
                  }
                  sql(siteRequest).update(Host.class, pk).set(Host.VAR_inventoryResource, HostInventory.class, solrId2, val).onSuccess(a -> {
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
          case "removeInventoryResource":
            Optional.ofNullable(jsonObject.getString(entityVar)).ifPresent(solrId2 -> {
              futures2.add(Future.future(promise2 -> {
                sql(siteRequest).update(Host.class, pk).setToNull(Host.VAR_inventoryResource, HostInventory.class, null).onSuccess(a -> {
                  promise2.complete();
                }).onFailure(ex -> {
                  promise2.tryFail(ex);
                });
              }));
            });
            break;
          case "setCreated":
              o2.setCreated(jsonObject.getString(entityVar));
              if(bParams.size() > 0)
                bSql.append(", ");
              bSql.append(Host.VAR_created + "=$" + num);
              num++;
              bParams.add(o2.sqlCreated());
            break;
          case "setAapHostId":
              o2.setAapHostId(jsonObject.getString(entityVar));
              if(bParams.size() > 0)
                bSql.append(", ");
              bSql.append(Host.VAR_aapHostId + "=$" + num);
              num++;
              bParams.add(o2.sqlAapHostId());
            break;
          case "setHostName":
              o2.setHostName(jsonObject.getString(entityVar));
              if(bParams.size() > 0)
                bSql.append(", ");
              bSql.append(Host.VAR_hostName + "=$" + num);
              num++;
              bParams.add(o2.sqlHostName());
            break;
          case "setArchived":
              o2.setArchived(jsonObject.getString(entityVar));
              if(bParams.size() > 0)
                bSql.append(", ");
              bSql.append(Host.VAR_archived + "=$" + num);
              num++;
              bParams.add(o2.sqlArchived());
            break;
          case "setIpAddress":
              o2.setIpAddress(jsonObject.getString(entityVar));
              if(bParams.size() > 0)
                bSql.append(", ");
              bSql.append(Host.VAR_ipAddress + "=$" + num);
              num++;
              bParams.add(o2.sqlIpAddress());
            break;
          case "setHostId":
              o2.setHostId(jsonObject.getString(entityVar));
              if(bParams.size() > 0)
                bSql.append(", ");
              bSql.append(Host.VAR_hostId + "=$" + num);
              num++;
              bParams.add(o2.sqlHostId());
            break;
          case "setHostResource":
              o2.setHostResource(jsonObject.getString(entityVar));
              if(bParams.size() > 0)
                bSql.append(", ");
              bSql.append(Host.VAR_hostResource + "=$" + num);
              num++;
              bParams.add(o2.sqlHostResource());
            break;
          case "setHostDescription":
              o2.setHostDescription(jsonObject.getString(entityVar));
              if(bParams.size() > 0)
                bSql.append(", ");
              bSql.append(Host.VAR_hostDescription + "=$" + num);
              num++;
              bParams.add(o2.sqlHostDescription());
            break;
          case "setSessionId":
              o2.setSessionId(jsonObject.getString(entityVar));
              if(bParams.size() > 0)
                bSql.append(", ");
              bSql.append(Host.VAR_sessionId + "=$" + num);
              num++;
              bParams.add(o2.sqlSessionId());
            break;
          case "setAapInventoryId":
              o2.setAapInventoryId(jsonObject.getString(entityVar));
              if(bParams.size() > 0)
                bSql.append(", ");
              bSql.append(Host.VAR_aapInventoryId + "=$" + num);
              num++;
              bParams.add(o2.sqlAapInventoryId());
            break;
          case "setUserKey":
              o2.setUserKey(jsonObject.getString(entityVar));
              if(bParams.size() > 0)
                bSql.append(", ");
              bSql.append(Host.VAR_userKey + "=$" + num);
              num++;
              bParams.add(o2.sqlUserKey());
            break;
          case "setInventoryName":
              o2.setInventoryName(jsonObject.getString(entityVar));
              if(bParams.size() > 0)
                bSql.append(", ");
              bSql.append(Host.VAR_inventoryName + "=$" + num);
              num++;
              bParams.add(o2.sqlInventoryName());
            break;
          case "setEventSubscriptions":
              o2.setEventSubscriptions(jsonObject.getJsonArray(entityVar));
              if(bParams.size() > 0)
                bSql.append(", ");
              bSql.append(Host.VAR_eventSubscriptions + "=$" + num);
              num++;
              bParams.add(o2.sqlEventSubscriptions());
            break;
          case "setObjectTitle":
              o2.setObjectTitle(jsonObject.getString(entityVar));
              if(bParams.size() > 0)
                bSql.append(", ");
              bSql.append(Host.VAR_objectTitle + "=$" + num);
              num++;
              bParams.add(o2.sqlObjectTitle());
            break;
          case "setDisplayPage":
              o2.setDisplayPage(jsonObject.getString(entityVar));
              if(bParams.size() > 0)
                bSql.append(", ");
              bSql.append(Host.VAR_displayPage + "=$" + num);
              num++;
              bParams.add(o2.sqlDisplayPage());
            break;
          case "setEditPage":
              o2.setEditPage(jsonObject.getString(entityVar));
              if(bParams.size() > 0)
                bSql.append(", ");
              bSql.append(Host.VAR_editPage + "=$" + num);
              num++;
              bParams.add(o2.sqlEditPage());
            break;
          case "setUserPage":
              o2.setUserPage(jsonObject.getString(entityVar));
              if(bParams.size() > 0)
                bSql.append(", ");
              bSql.append(Host.VAR_userPage + "=$" + num);
              num++;
              bParams.add(o2.sqlUserPage());
            break;
          case "setDownload":
              o2.setDownload(jsonObject.getString(entityVar));
              if(bParams.size() > 0)
                bSql.append(", ");
              bSql.append(Host.VAR_download + "=$" + num);
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
            RuntimeException ex2 = new RuntimeException("value Host failed", ex);
            LOG.error(String.format("relateHost failed. "), ex2);
            a.handle(Future.failedFuture(ex2));
          });
        }));
      }
      CompositeFuture.all(futures1).onSuccess(a -> {
        CompositeFuture.all(futures2).onSuccess(b -> {
          Host o3 = new Host();
          o3.setSiteRequest_(o.getSiteRequest_());
          o3.setPk(pk);
          promise.complete(o3);
        }).onFailure(ex -> {
          LOG.error(String.format("sqlPATCHHost failed. "), ex);
          promise.tryFail(ex);
        });
      }).onFailure(ex -> {
        LOG.error(String.format("sqlPATCHHost failed. "), ex);
        promise.tryFail(ex);
      });
    } catch(Exception ex) {
      LOG.error(String.format("sqlPATCHHost failed. "), ex);
      promise.tryFail(ex);
    }
    return promise.future();
  }

  public Future<ServiceResponse> response200PATCHHost(SiteRequest siteRequest) {
    Promise<ServiceResponse> promise = Promise.promise();
    try {
      JsonObject json = new JsonObject();
      promise.complete(ServiceResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily())));
    } catch(Exception ex) {
      LOG.error(String.format("response200PATCHHost failed. "), ex);
      promise.tryFail(ex);
    }
    return promise.future();
  }

  // POST //

  @Override
  public void postHost(JsonObject body, ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler) {
    LOG.debug(String.format("postHost started. "));
    Boolean classPublicRead = false;
    user(serviceRequest, SiteRequest.class, SiteUser.class, SiteUser.getClassApiAddress(), "postSiteUserFuture", "patchSiteUserFuture", classPublicRead).onSuccess(siteRequest -> {
      try {
        siteRequest.setLang("enUS");
        String hostResource = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("hostResource");
        String HOST = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("HOST");
        List<String> groups = Optional.ofNullable(siteRequest.getGroups()).orElse(new ArrayList<>());
        MultiMap form = MultiMap.caseInsensitiveMultiMap();
        form.add("grant_type", "urn:ietf:params:oauth:grant-type:uma-ticket");
        form.add("audience", config.getString(ComputateConfigKeys.AUTH_CLIENT));
        form.add("response_mode", "permissions");
        form.add("permission", String.format("%s#%s", Host.CLASS_AUTH_RESOURCE, "GET"));
        form.add("permission", String.format("%s#%s", Host.CLASS_AUTH_RESOURCE, "POST"));
        form.add("permission", String.format("%s#%s", Host.CLASS_AUTH_RESOURCE, "PATCH"));
        form.add("permission", String.format("%s#%s", Host.CLASS_AUTH_RESOURCE, "PUT"));
        form.add("permission", String.format("%s#%s", Host.CLASS_AUTH_RESOURCE, "DELETE"));
        form.add("permission", String.format("%s#%s", Host.CLASS_AUTH_RESOURCE, "Admin"));
        form.add("permission", String.format("%s#%s", Host.CLASS_AUTH_RESOURCE, "SuperAdmin"));
        if(hostResource != null)
          form.add("permission", String.format("%s#%s", hostResource, "POST"));
        groups.stream().map(group -> {
              Matcher mPermission = Pattern.compile("^/(.*-?TENANT-([a-z0-9\\-]+))-(POST)$").matcher(group);
              return mPermission.find() ? mPermission : null;
            }).filter(v -> v != null).forEach(mPermission -> {
              form.add("permission", String.format("%s#%s", mPermission.group(1), mPermission.group(3)));
            });
        groups.stream().map(group -> {
              Matcher mPermission = Pattern.compile("^/(.*-?HOSTINVENTORY-([a-z0-9\\-]+))-(POST)$").matcher(group);
              return mPermission.find() ? mPermission : null;
            }).filter(v -> v != null).forEach(mPermission -> {
              form.add("permission", String.format("%s#%s", mPermission.group(1), mPermission.group(3)));
            });
        groups.stream().map(group -> {
              Matcher mPermission = Pattern.compile("^/(.*-?HOST-([a-z0-9\\-]+))-(POST)$").matcher(group);
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
            JsonArray scopes = authorizationDecisionBody.stream().map(o -> (JsonObject)o).filter(o -> "HOST".equals(o.getString("rsname"))).findFirst().map(decision -> ((JsonObject)decision).getJsonArray("scopes")).orElse(new JsonArray());
            if(!scopes.contains("POST") && !classPublicRead) {
              //
              List<String> fqs = new ArrayList<>();
              authorizationDecisionBody.stream().map(o -> (JsonObject)o).filter(permission -> {
                    Matcher mPermission = Pattern.compile("^(TENANT-([a-z0-9\\-]+))$").matcher(permission.getString("rsname"));
                    return permission.getJsonArray("scopes").contains("POST")
                        && mPermission.find();
                  }).forEach(permission -> {
                    fqs.add(String.format("%s:%s", "tenantResource", permission.getString("rsname")));
                  });
              authorizationDecisionBody.stream().map(o -> (JsonObject)o).filter(permission -> {
                    Matcher mPermission = Pattern.compile("^(HOSTINVENTORY-([a-z0-9\\-]+))$").matcher(permission.getString("rsname"));
                    return permission.getJsonArray("scopes").contains("POST")
                        && mPermission.find();
                  }).forEach(permission -> {
                    fqs.add(String.format("%s:%s", "inventoryResource", permission.getString("rsname")));
                  });
              authorizationDecisionBody.stream().map(o -> (JsonObject)o).filter(permission -> {
                    Matcher mPermission = Pattern.compile("^(HOST-([a-z0-9\\-]+))$").matcher(permission.getString("rsname"));
                    return permission.getJsonArray("scopes").contains("POST")
                        && mPermission.find();
                  }).forEach(permission -> {
                    fqs.add(String.format("%s:%s", "hostResource", permission.getString("rsname")));
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
              eventBus.publish("websocketHost", JsonObject.mapFrom(apiRequest).toString());
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
              eventBus.request(Host.getClassApiAddress(), json, new DeliveryOptions().addHeader("action", "postHostFuture")).onSuccess(a -> {
                JsonObject responseMessage = (JsonObject)a.body();
                JsonObject responseBody = new JsonObject(Buffer.buffer(JsonUtil.BASE64_DECODER.decode(responseMessage.getString("payload"))));
                apiRequest.setSolrId(responseBody.getString(Host.VAR_solrId));
                eventHandler.handle(Future.succeededFuture(ServiceResponse.completedWithJson(Buffer.buffer(responseBody.encodePrettily()))));
                LOG.debug(String.format("postHost succeeded. "));
              }).onFailure(ex -> {
                LOG.error(String.format("postHost failed. "), ex);
                error(siteRequest, eventHandler, ex);
              });
            }
          } catch(Exception ex) {
            LOG.error(String.format("postHost failed. "), ex);
            error(null, eventHandler, ex);
          }
        });
      } catch(Exception ex) {
        LOG.error(String.format("postHost failed. "), ex);
        error(null, eventHandler, ex);
      }
    }).onFailure(ex -> {
      if("Inactive Token".equals(ex.getMessage()) || StringUtils.startsWith(ex.getMessage(), "invalid_grant:")) {
        try {
          eventHandler.handle(Future.succeededFuture(new ServiceResponse(302, "Found", null, MultiMap.caseInsensitiveMultiMap().add(HttpHeaders.LOCATION, "/logout?redirect_uri=" + URLEncoder.encode(serviceRequest.getExtra().getString("uri"), "UTF-8")))));
        } catch(Exception ex2) {
          LOG.error(String.format("postHost failed. ", ex2));
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
        LOG.error(String.format("postHost failed. "), ex);
        error(null, eventHandler, ex);
      }
    });
  }

  @Override
  public void postHostFuture(JsonObject body, ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler) {
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
        postHostFuture(siteRequest, false).onSuccess(o -> {
          eventHandler.handle(Future.succeededFuture(ServiceResponse.completedWithJson(Buffer.buffer(JsonObject.mapFrom(o).encodePrettily()))));
        }).onFailure(ex -> {
          eventHandler.handle(Future.failedFuture(ex));
        });
      } catch(Throwable ex) {
        LOG.error(String.format("postHost failed. "), ex);
        error(null, eventHandler, ex);
      }
    }).onFailure(ex -> {
      if("Inactive Token".equals(ex.getMessage()) || StringUtils.startsWith(ex.getMessage(), "invalid_grant:")) {
        try {
          eventHandler.handle(Future.succeededFuture(new ServiceResponse(302, "Found", null, MultiMap.caseInsensitiveMultiMap().add(HttpHeaders.LOCATION, "/logout?redirect_uri=" + URLEncoder.encode(serviceRequest.getExtra().getString("uri"), "UTF-8")))));
        } catch(Exception ex2) {
          LOG.error(String.format("postHost failed. ", ex2));
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
        LOG.error(String.format("postHost failed. "), ex);
        error(null, eventHandler, ex);
      }
    });
  }

  public Future<Host> postHostFuture(SiteRequest siteRequest, Boolean hostResource) {
    Promise<Host> promise = Promise.promise();

    try {
      pgPool.withTransaction(sqlConnection -> {
        Promise<Host> promise1 = Promise.promise();
        siteRequest.setSqlConnection(sqlConnection);
        varsHost(siteRequest).onSuccess(a -> {
          createHost(siteRequest).onSuccess(host -> {
            sqlPOSTHost(host, hostResource).onSuccess(b -> {
              persistHost(host, false).onSuccess(c -> {
                relateHost(host).onSuccess(d -> {
                  indexHost(host).onSuccess(o2 -> {
                    promise1.complete(host);
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
      }).compose(host -> {
        Promise<Host> promise2 = Promise.promise();
        refreshHost(host).onSuccess(a -> {
          try {
            ApiRequest apiRequest = siteRequest.getApiRequest_();
            if(apiRequest != null) {
              apiRequest.setNumPATCH(apiRequest.getNumPATCH() + 1);
              host.apiRequestHost();
              eventBus.publish("websocketHost", JsonObject.mapFrom(apiRequest).toString());
            }
            promise2.complete(host);
          } catch(Exception ex) {
            LOG.error(String.format("postHostFuture failed. "), ex);
            promise2.tryFail(ex);
          }
        }).onFailure(ex -> {
          promise2.tryFail(ex);
        });
        return promise2.future();
      }).onSuccess(host -> {
        try {
          ApiRequest apiRequest = siteRequest.getApiRequest_();
          if(apiRequest != null) {
            apiRequest.setNumPATCH(apiRequest.getNumPATCH() + 1);
            host.apiRequestHost();
            eventBus.publish("websocketHost", JsonObject.mapFrom(apiRequest).toString());
          }
          promise.complete(host);
        } catch(Exception ex) {
          LOG.error(String.format("postHostFuture failed. "), ex);
          promise.tryFail(ex);
        }
      }).onFailure(ex -> {
        promise.tryFail(ex);
      });
    } catch(Exception ex) {
      LOG.error(String.format("postHostFuture failed. "), ex);
      promise.tryFail(ex);
    }
    return promise.future();
  }

  public Future<Host> sqlPOSTHost(Host o, Boolean inheritPrimaryKey) {
    Promise<Host> promise = Promise.promise();
    try {
      SiteRequest siteRequest = o.getSiteRequest_();
      ApiRequest apiRequest = siteRequest.getApiRequest_();
      List<String> solrIds = Optional.ofNullable(apiRequest).map(r -> r.getSolrIds()).orElse(new ArrayList<>());
      List<String> classes = Optional.ofNullable(apiRequest).map(r -> r.getClasses()).orElse(new ArrayList<>());
      SqlConnection sqlConnection = siteRequest.getSqlConnection();
      Integer num = 1;
      StringBuilder bSql = new StringBuilder("UPDATE Host SET ");
      List<Object> bParams = new ArrayList<Object>();
      Long pk = o.getPk();
      JsonObject jsonObject = siteRequest.getJsonObject();
      Host o2 = new Host();
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
          case Host.VAR_tenantResource:
            Optional.ofNullable(jsonObject.getString(entityVar)).ifPresent(val -> {
              futures1.add(Future.future(promise2 -> {
                searchModel(siteRequest).query(Tenant.varIndexedTenant(Tenant.VAR_tenantResource), Tenant.class, val).onSuccess(o3 -> {
                  String solrId2 = Optional.ofNullable(o3).map(o4 -> o4.getSolrId()).filter(solrId3 -> !solrIds.contains(solrId3)).orElse(null);
                  if(solrId2 != null) {
                    solrIds.add(solrId2);
                    classes.add("Tenant");
                  }
                  sql(siteRequest).update(Host.class, pk).set(Host.VAR_tenantResource, Tenant.class, solrId2, val).onSuccess(a -> {
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
          case Host.VAR_inventoryResource:
            Optional.ofNullable(jsonObject.getString(entityVar)).ifPresent(val -> {
              futures1.add(Future.future(promise2 -> {
                searchModel(siteRequest).query(HostInventory.varIndexedHostInventory(HostInventory.VAR_inventoryResource), HostInventory.class, val).onSuccess(o3 -> {
                  String solrId2 = Optional.ofNullable(o3).map(o4 -> o4.getSolrId()).filter(solrId3 -> !solrIds.contains(solrId3)).orElse(null);
                  if(solrId2 != null) {
                    solrIds.add(solrId2);
                    classes.add("HostInventory");
                  }
                  sql(siteRequest).update(Host.class, pk).set(Host.VAR_inventoryResource, HostInventory.class, solrId2, val).onSuccess(a -> {
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
          case Host.VAR_created:
            o2.setCreated(jsonObject.getString(entityVar));
            if(bParams.size() > 0) {
              bSql.append(", ");
            }
            bSql.append(Host.VAR_created + "=$" + num);
            num++;
            bParams.add(o2.sqlCreated());
            break;
          case Host.VAR_aapHostId:
            o2.setAapHostId(jsonObject.getString(entityVar));
            if(bParams.size() > 0) {
              bSql.append(", ");
            }
            bSql.append(Host.VAR_aapHostId + "=$" + num);
            num++;
            bParams.add(o2.sqlAapHostId());
            break;
          case Host.VAR_hostName:
            o2.setHostName(jsonObject.getString(entityVar));
            if(bParams.size() > 0) {
              bSql.append(", ");
            }
            bSql.append(Host.VAR_hostName + "=$" + num);
            num++;
            bParams.add(o2.sqlHostName());
            break;
          case Host.VAR_archived:
            o2.setArchived(jsonObject.getString(entityVar));
            if(bParams.size() > 0) {
              bSql.append(", ");
            }
            bSql.append(Host.VAR_archived + "=$" + num);
            num++;
            bParams.add(o2.sqlArchived());
            break;
          case Host.VAR_ipAddress:
            o2.setIpAddress(jsonObject.getString(entityVar));
            if(bParams.size() > 0) {
              bSql.append(", ");
            }
            bSql.append(Host.VAR_ipAddress + "=$" + num);
            num++;
            bParams.add(o2.sqlIpAddress());
            break;
          case Host.VAR_hostId:
            o2.setHostId(jsonObject.getString(entityVar));
            if(bParams.size() > 0) {
              bSql.append(", ");
            }
            bSql.append(Host.VAR_hostId + "=$" + num);
            num++;
            bParams.add(o2.sqlHostId());
            break;
          case Host.VAR_hostResource:
            o2.setHostResource(jsonObject.getString(entityVar));
            if(bParams.size() > 0) {
              bSql.append(", ");
            }
            bSql.append(Host.VAR_hostResource + "=$" + num);
            num++;
            bParams.add(o2.sqlHostResource());
            break;
          case Host.VAR_hostDescription:
            o2.setHostDescription(jsonObject.getString(entityVar));
            if(bParams.size() > 0) {
              bSql.append(", ");
            }
            bSql.append(Host.VAR_hostDescription + "=$" + num);
            num++;
            bParams.add(o2.sqlHostDescription());
            break;
          case Host.VAR_sessionId:
            o2.setSessionId(jsonObject.getString(entityVar));
            if(bParams.size() > 0) {
              bSql.append(", ");
            }
            bSql.append(Host.VAR_sessionId + "=$" + num);
            num++;
            bParams.add(o2.sqlSessionId());
            break;
          case Host.VAR_aapInventoryId:
            o2.setAapInventoryId(jsonObject.getString(entityVar));
            if(bParams.size() > 0) {
              bSql.append(", ");
            }
            bSql.append(Host.VAR_aapInventoryId + "=$" + num);
            num++;
            bParams.add(o2.sqlAapInventoryId());
            break;
          case Host.VAR_userKey:
            o2.setUserKey(jsonObject.getString(entityVar));
            if(bParams.size() > 0) {
              bSql.append(", ");
            }
            bSql.append(Host.VAR_userKey + "=$" + num);
            num++;
            bParams.add(o2.sqlUserKey());
            break;
          case Host.VAR_inventoryName:
            o2.setInventoryName(jsonObject.getString(entityVar));
            if(bParams.size() > 0) {
              bSql.append(", ");
            }
            bSql.append(Host.VAR_inventoryName + "=$" + num);
            num++;
            bParams.add(o2.sqlInventoryName());
            break;
          case Host.VAR_eventSubscriptions:
            o2.setEventSubscriptions(jsonObject.getJsonArray(entityVar));
            if(bParams.size() > 0) {
              bSql.append(", ");
            }
            bSql.append(Host.VAR_eventSubscriptions + "=$" + num);
            num++;
            bParams.add(o2.sqlEventSubscriptions());
            break;
          case Host.VAR_objectTitle:
            o2.setObjectTitle(jsonObject.getString(entityVar));
            if(bParams.size() > 0) {
              bSql.append(", ");
            }
            bSql.append(Host.VAR_objectTitle + "=$" + num);
            num++;
            bParams.add(o2.sqlObjectTitle());
            break;
          case Host.VAR_displayPage:
            o2.setDisplayPage(jsonObject.getString(entityVar));
            if(bParams.size() > 0) {
              bSql.append(", ");
            }
            bSql.append(Host.VAR_displayPage + "=$" + num);
            num++;
            bParams.add(o2.sqlDisplayPage());
            break;
          case Host.VAR_editPage:
            o2.setEditPage(jsonObject.getString(entityVar));
            if(bParams.size() > 0) {
              bSql.append(", ");
            }
            bSql.append(Host.VAR_editPage + "=$" + num);
            num++;
            bParams.add(o2.sqlEditPage());
            break;
          case Host.VAR_userPage:
            o2.setUserPage(jsonObject.getString(entityVar));
            if(bParams.size() > 0) {
              bSql.append(", ");
            }
            bSql.append(Host.VAR_userPage + "=$" + num);
            num++;
            bParams.add(o2.sqlUserPage());
            break;
          case Host.VAR_download:
            o2.setDownload(jsonObject.getString(entityVar));
            if(bParams.size() > 0) {
              bSql.append(", ");
            }
            bSql.append(Host.VAR_download + "=$" + num);
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
            RuntimeException ex2 = new RuntimeException("value Host failed", ex);
            LOG.error(String.format("relateHost failed. "), ex2);
            a.handle(Future.failedFuture(ex2));
          });
        }));
      }
      CompositeFuture.all(futures1).onSuccess(a -> {
        CompositeFuture.all(futures2).onSuccess(b -> {
          promise.complete(o2);
        }).onFailure(ex -> {
          LOG.error(String.format("sqlPOSTHost failed. "), ex);
          promise.tryFail(ex);
        });
      }).onFailure(ex -> {
        LOG.error(String.format("sqlPOSTHost failed. "), ex);
        promise.tryFail(ex);
      });
    } catch(Exception ex) {
      LOG.error(String.format("sqlPOSTHost failed. "), ex);
      promise.tryFail(ex);
    }
    return promise.future();
  }

  public Future<ServiceResponse> response200POSTHost(Host o) {
    Promise<ServiceResponse> promise = Promise.promise();
    try {
      SiteRequest siteRequest = o.getSiteRequest_();
      JsonObject json = JsonObject.mapFrom(o);
      promise.complete(ServiceResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily())));
    } catch(Exception ex) {
      LOG.error(String.format("response200POSTHost failed. "), ex);
      promise.tryFail(ex);
    }
    return promise.future();
  }

  // DELETE //

  @Override
  public void deleteHost(JsonObject body, ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler) {
    LOG.debug(String.format("deleteHost started. "));
    Boolean classPublicRead = false;
    user(serviceRequest, SiteRequest.class, SiteUser.class, SiteUser.getClassApiAddress(), "postSiteUserFuture", "patchSiteUserFuture", classPublicRead).onSuccess(siteRequest -> {
      try {
        siteRequest.setLang("enUS");
        String hostResource = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("hostResource");
        String HOST = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("HOST");
        List<String> groups = Optional.ofNullable(siteRequest.getGroups()).orElse(new ArrayList<>());
        MultiMap form = MultiMap.caseInsensitiveMultiMap();
        form.add("grant_type", "urn:ietf:params:oauth:grant-type:uma-ticket");
        form.add("audience", config.getString(ComputateConfigKeys.AUTH_CLIENT));
        form.add("response_mode", "permissions");
        form.add("permission", String.format("%s#%s", Host.CLASS_AUTH_RESOURCE, "GET"));
        form.add("permission", String.format("%s#%s", Host.CLASS_AUTH_RESOURCE, "POST"));
        form.add("permission", String.format("%s#%s", Host.CLASS_AUTH_RESOURCE, "PATCH"));
        form.add("permission", String.format("%s#%s", Host.CLASS_AUTH_RESOURCE, "PUT"));
        form.add("permission", String.format("%s#%s", Host.CLASS_AUTH_RESOURCE, "DELETE"));
        form.add("permission", String.format("%s#%s", Host.CLASS_AUTH_RESOURCE, "Admin"));
        form.add("permission", String.format("%s#%s", Host.CLASS_AUTH_RESOURCE, "SuperAdmin"));
        if(hostResource != null)
          form.add("permission", String.format("%s#%s", hostResource, "DELETE"));
        groups.stream().map(group -> {
              Matcher mPermission = Pattern.compile("^/(.*-?TENANT-([a-z0-9\\-]+))-(DELETE)$").matcher(group);
              return mPermission.find() ? mPermission : null;
            }).filter(v -> v != null).forEach(mPermission -> {
              form.add("permission", String.format("%s#%s", mPermission.group(1), mPermission.group(3)));
            });
        groups.stream().map(group -> {
              Matcher mPermission = Pattern.compile("^/(.*-?HOSTINVENTORY-([a-z0-9\\-]+))-(DELETE)$").matcher(group);
              return mPermission.find() ? mPermission : null;
            }).filter(v -> v != null).forEach(mPermission -> {
              form.add("permission", String.format("%s#%s", mPermission.group(1), mPermission.group(3)));
            });
        groups.stream().map(group -> {
              Matcher mPermission = Pattern.compile("^/(.*-?HOST-([a-z0-9\\-]+))-(DELETE)$").matcher(group);
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
            JsonArray scopes = authorizationDecisionBody.stream().map(o -> (JsonObject)o).filter(o -> "HOST".equals(o.getString("rsname"))).findFirst().map(decision -> ((JsonObject)decision).getJsonArray("scopes")).orElse(new JsonArray());
            if(!scopes.contains("DELETE") && !classPublicRead) {
              //
              List<String> fqs = new ArrayList<>();
              authorizationDecisionBody.stream().map(o -> (JsonObject)o).filter(permission -> {
                    Matcher mPermission = Pattern.compile("^(TENANT-([a-z0-9\\-]+))$").matcher(permission.getString("rsname"));
                    return permission.getJsonArray("scopes").contains("DELETE")
                        && mPermission.find();
                  }).forEach(permission -> {
                    fqs.add(String.format("%s:%s", "tenantResource", permission.getString("rsname")));
                  });
              authorizationDecisionBody.stream().map(o -> (JsonObject)o).filter(permission -> {
                    Matcher mPermission = Pattern.compile("^(HOSTINVENTORY-([a-z0-9\\-]+))$").matcher(permission.getString("rsname"));
                    return permission.getJsonArray("scopes").contains("DELETE")
                        && mPermission.find();
                  }).forEach(permission -> {
                    fqs.add(String.format("%s:%s", "inventoryResource", permission.getString("rsname")));
                  });
              authorizationDecisionBody.stream().map(o -> (JsonObject)o).filter(permission -> {
                    Matcher mPermission = Pattern.compile("^(HOST-([a-z0-9\\-]+))$").matcher(permission.getString("rsname"));
                    return permission.getJsonArray("scopes").contains("DELETE")
                        && mPermission.find();
                  }).forEach(permission -> {
                    fqs.add(String.format("%s:%s", "hostResource", permission.getString("rsname")));
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
              searchHostList(siteRequest, false, true, true, "DELETE").onSuccess(listHost -> {
                try {
                  ApiRequest apiRequest = new ApiRequest();
                  apiRequest.setRows(listHost.getRequest().getRows());
                  apiRequest.setNumFound(listHost.getResponse().getResponse().getNumFound());
                  apiRequest.setNumPATCH(0L);
                  apiRequest.initDeepApiRequest(siteRequest);
                  siteRequest.setApiRequest_(apiRequest);
                  if(apiRequest.getNumFound() == 1L)
                    apiRequest.setOriginal(listHost.first());
                  apiRequest.setSolrId(Optional.ofNullable(listHost.first()).map(o2 -> o2.getSolrId()).orElse(null));
                  eventBus.publish("websocketHost", JsonObject.mapFrom(apiRequest).toString());

                  listDELETEHost(apiRequest, listHost).onSuccess(e -> {
                    response200DELETEHost(siteRequest).onSuccess(response -> {
                      LOG.debug(String.format("deleteHost succeeded. "));
                      eventHandler.handle(Future.succeededFuture(response));
                    }).onFailure(ex -> {
                      LOG.error(String.format("deleteHost failed. "), ex);
                      error(siteRequest, eventHandler, ex);
                    });
                  }).onFailure(ex -> {
                    LOG.error(String.format("deleteHost failed. "), ex);
                    error(siteRequest, eventHandler, ex);
                  });
                } catch(Exception ex) {
                  LOG.error(String.format("deleteHost failed. "), ex);
                  error(siteRequest, eventHandler, ex);
                }
              }).onFailure(ex -> {
                LOG.error(String.format("deleteHost failed. "), ex);
                error(siteRequest, eventHandler, ex);
              });
            }
          } catch(Exception ex) {
            LOG.error(String.format("deleteHost failed. "), ex);
            error(null, eventHandler, ex);
          }
        });
      } catch(Exception ex) {
        LOG.error(String.format("deleteHost failed. "), ex);
        error(null, eventHandler, ex);
      }
    }).onFailure(ex -> {
      if("Inactive Token".equals(ex.getMessage()) || StringUtils.startsWith(ex.getMessage(), "invalid_grant:")) {
        try {
          eventHandler.handle(Future.succeededFuture(new ServiceResponse(302, "Found", null, MultiMap.caseInsensitiveMultiMap().add(HttpHeaders.LOCATION, "/logout?redirect_uri=" + URLEncoder.encode(serviceRequest.getExtra().getString("uri"), "UTF-8")))));
        } catch(Exception ex2) {
          LOG.error(String.format("deleteHost failed. ", ex2));
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
        LOG.error(String.format("deleteHost failed. "), ex);
        error(null, eventHandler, ex);
      }
    });
  }

  public Future<Void> listDELETEHost(ApiRequest apiRequest, SearchList<Host> listHost) {
    Promise<Void> promise = Promise.promise();
    List<Future> futures = new ArrayList<>();
    SiteRequest siteRequest = listHost.getSiteRequest_(SiteRequest.class);
    listHost.getList().forEach(o -> {
      SiteRequest siteRequest2 = generateSiteRequest(siteRequest.getUser(), siteRequest.getUserPrincipal(), siteRequest.getServiceRequest(), siteRequest.getJsonObject(), SiteRequest.class);
      siteRequest2.setScopes(siteRequest.getScopes());
      o.setSiteRequest_(siteRequest2);
      siteRequest2.setApiRequest_(siteRequest.getApiRequest_());
      JsonObject jsonObject = JsonObject.mapFrom(o);
      Host o2 = jsonObject.mapTo(Host.class);
      o2.setSiteRequest_(siteRequest2);
      futures.add(Future.future(promise1 -> {
        deleteHostFuture(o).onSuccess(a -> {
          promise1.complete();
        }).onFailure(ex -> {
          LOG.error(String.format("listDELETEHost failed. "), ex);
          promise1.tryFail(ex);
        });
      }));
    });
    CompositeFuture.all(futures).onSuccess( a -> {
      listHost.next().onSuccess(next -> {
        if(next) {
          listDELETEHost(apiRequest, listHost).onSuccess(b -> {
            promise.complete();
          }).onFailure(ex -> {
            LOG.error(String.format("listDELETEHost failed. "), ex);
            promise.tryFail(ex);
          });
        } else {
          promise.complete();
        }
      }).onFailure(ex -> {
        LOG.error(String.format("listDELETEHost failed. "), ex);
        promise.tryFail(ex);
      });
    }).onFailure(ex -> {
      LOG.error(String.format("listDELETEHost failed. "), ex);
      promise.tryFail(ex);
    });
    return promise.future();
  }

  @Override
  public void deleteHostFuture(JsonObject body, ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler) {
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
        searchHostList(siteRequest, false, true, true, "DELETE").onSuccess(listHost -> {
          try {
            Host o = listHost.first();
            if(o != null && listHost.getResponse().getResponse().getNumFound() == 1) {
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
              apiRequest.setId(Optional.ofNullable(listHost.first()).map(o2 -> o2.getHostResource().toString()).orElse(null));
              apiRequest.setSolrId(Optional.ofNullable(listHost.first()).map(o2 -> o2.getSolrId()).orElse(null));
              deleteHostFuture(o).onSuccess(o2 -> {
                eventHandler.handle(Future.succeededFuture(ServiceResponse.completedWithJson(Buffer.buffer(new JsonObject().encodePrettily()))));
              }).onFailure(ex -> {
                eventHandler.handle(Future.failedFuture(ex));
              });
            } else {
              eventHandler.handle(Future.succeededFuture(ServiceResponse.completedWithJson(Buffer.buffer(new JsonObject().encodePrettily()))));
            }
          } catch(Exception ex) {
            LOG.error(String.format("deleteHost failed. "), ex);
            error(siteRequest, eventHandler, ex);
          }
        }).onFailure(ex -> {
          LOG.error(String.format("deleteHost failed. "), ex);
          error(siteRequest, eventHandler, ex);
        });
      } catch(Exception ex) {
        LOG.error(String.format("deleteHost failed. "), ex);
        error(null, eventHandler, ex);
      }
    }).onFailure(ex -> {
      LOG.error(String.format("deleteHost failed. "), ex);
      error(null, eventHandler, ex);
    });
  }

  public Future<Host> deleteHostFuture(Host o) {
    SiteRequest siteRequest = o.getSiteRequest_();
    Promise<Host> promise = Promise.promise();

    try {
      ApiRequest apiRequest = siteRequest.getApiRequest_();
      Promise<Host> promise1 = Promise.promise();
      pgPool.withTransaction(sqlConnection -> {
        siteRequest.setSqlConnection(sqlConnection);
        varsHost(siteRequest).onSuccess(a -> {
          sqlDELETEHost(o).onSuccess(host -> {
            relateHost(o).onSuccess(d -> {
              unindexHost(o).onSuccess(o2 -> {
                if(apiRequest != null) {
                  apiRequest.setNumPATCH(apiRequest.getNumPATCH() + 1);
                  if(apiRequest.getNumFound() == 1L && Optional.ofNullable(siteRequest.getJsonObject()).map(json -> json.size() > 0).orElse(false)) {
                    o2.apiRequestHost();
                    if(apiRequest.getVars().size() > 0 && Optional.ofNullable(siteRequest.getRequestVars().get("refresh")).map(refresh -> !refresh.equals("false")).orElse(true))
                      eventBus.publish("websocketHost", JsonObject.mapFrom(apiRequest).toString());
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
      }).compose(host -> {
        Promise<Host> promise2 = Promise.promise();
        refreshHost(o).onSuccess(a -> {
          promise2.complete(o);
        }).onFailure(ex -> {
          promise2.tryFail(ex);
        });
        return promise2.future();
      }).onSuccess(host -> {
        promise.complete(host);
      }).onFailure(ex -> {
        promise.tryFail(ex);
      });
    } catch(Exception ex) {
      LOG.error(String.format("deleteHostFuture failed. "), ex);
      promise.tryFail(ex);
    }
    return promise.future();
  }

  public Future<Void> sqlDELETEHost(Host o) {
    Promise<Void> promise = Promise.promise();
    try {
      SiteRequest siteRequest = o.getSiteRequest_();
      ApiRequest apiRequest = siteRequest.getApiRequest_();
      List<String> solrIds = Optional.ofNullable(apiRequest).map(r -> r.getSolrIds()).orElse(new ArrayList<>());
      List<String> classes = Optional.ofNullable(apiRequest).map(r -> r.getClasses()).orElse(new ArrayList<>());
      SqlConnection sqlConnection = siteRequest.getSqlConnection();
      Integer num = 1;
      StringBuilder bSql = new StringBuilder("DELETE FROM Host ");
      List<Object> bParams = new ArrayList<Object>();
      Long pk = o.getPk();
      JsonObject jsonObject = siteRequest.getJsonObject();
      Host o2 = new Host();
      o2.setSiteRequest_(siteRequest);
      List<Future> futures1 = new ArrayList<>();
      List<Future> futures2 = new ArrayList<>();

      if(jsonObject != null) {
        Set<String> entityVars = jsonObject.fieldNames();
        for(String entityVar : entityVars) {
          switch(entityVar) {
          case Host.VAR_tenantResource:
            Optional.ofNullable(jsonObject.getString(entityVar)).ifPresent(val -> {
              futures1.add(Future.future(promise2 -> {
                searchModel(siteRequest).query(Tenant.varIndexedTenant(Tenant.VAR_tenantResource), Tenant.class, val).onSuccess(o3 -> {
                  String solrId2 = Optional.ofNullable(o3).map(o4 -> o4.getSolrId()).filter(solrId3 -> !solrIds.contains(solrId3)).orElse(null);
                  if(solrId2 != null) {
                    solrIds.add(solrId2);
                    classes.add("Tenant");
                  }
                  sql(siteRequest).update(Host.class, pk).set(Host.VAR_tenantResource, Tenant.class, null, null).onSuccess(a -> {
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
          case Host.VAR_inventoryResource:
            Optional.ofNullable(jsonObject.getString(entityVar)).ifPresent(val -> {
              futures1.add(Future.future(promise2 -> {
                searchModel(siteRequest).query(HostInventory.varIndexedHostInventory(HostInventory.VAR_inventoryResource), HostInventory.class, val).onSuccess(o3 -> {
                  String solrId2 = Optional.ofNullable(o3).map(o4 -> o4.getSolrId()).filter(solrId3 -> !solrIds.contains(solrId3)).orElse(null);
                  if(solrId2 != null) {
                    solrIds.add(solrId2);
                    classes.add("HostInventory");
                  }
                  sql(siteRequest).update(Host.class, pk).set(Host.VAR_inventoryResource, HostInventory.class, null, null).onSuccess(a -> {
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
          RuntimeException ex2 = new RuntimeException("value Host failed", ex);
          LOG.error(String.format("unrelateHost failed. "), ex2);
          a.handle(Future.failedFuture(ex2));
        });
      }));
      CompositeFuture.all(futures1).onSuccess(a -> {
        CompositeFuture.all(futures2).onSuccess(b -> {
          promise.complete();
        }).onFailure(ex -> {
          LOG.error(String.format("sqlDELETEHost failed. "), ex);
          promise.tryFail(ex);
        });
      }).onFailure(ex -> {
        LOG.error(String.format("sqlDELETEHost failed. "), ex);
        promise.tryFail(ex);
      });
    } catch(Exception ex) {
      LOG.error(String.format("sqlDELETEHost failed. "), ex);
      promise.tryFail(ex);
    }
    return promise.future();
  }

  public Future<ServiceResponse> response200DELETEHost(SiteRequest siteRequest) {
    Promise<ServiceResponse> promise = Promise.promise();
    try {
      JsonObject json = new JsonObject();
      promise.complete(ServiceResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily())));
    } catch(Exception ex) {
      LOG.error(String.format("response200DELETEHost failed. "), ex);
      promise.tryFail(ex);
    }
    return promise.future();
  }

  // PUTImport //

  @Override
  public void putimportHost(JsonObject body, ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler) {
    LOG.debug(String.format("putimportHost started. "));
    Boolean classPublicRead = false;
    user(serviceRequest, SiteRequest.class, SiteUser.class, SiteUser.getClassApiAddress(), "postSiteUserFuture", "patchSiteUserFuture", classPublicRead).onSuccess(siteRequest -> {
      try {
        siteRequest.setLang("enUS");
        String hostResource = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("hostResource");
        String HOST = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("HOST");
        List<String> groups = Optional.ofNullable(siteRequest.getGroups()).orElse(new ArrayList<>());
        MultiMap form = MultiMap.caseInsensitiveMultiMap();
        form.add("grant_type", "urn:ietf:params:oauth:grant-type:uma-ticket");
        form.add("audience", config.getString(ComputateConfigKeys.AUTH_CLIENT));
        form.add("response_mode", "permissions");
        form.add("permission", String.format("%s#%s", Host.CLASS_AUTH_RESOURCE, "GET"));
        form.add("permission", String.format("%s#%s", Host.CLASS_AUTH_RESOURCE, "POST"));
        form.add("permission", String.format("%s#%s", Host.CLASS_AUTH_RESOURCE, "PATCH"));
        form.add("permission", String.format("%s#%s", Host.CLASS_AUTH_RESOURCE, "PUT"));
        form.add("permission", String.format("%s#%s", Host.CLASS_AUTH_RESOURCE, "DELETE"));
        form.add("permission", String.format("%s#%s", Host.CLASS_AUTH_RESOURCE, "Admin"));
        form.add("permission", String.format("%s#%s", Host.CLASS_AUTH_RESOURCE, "SuperAdmin"));
        if(hostResource != null)
          form.add("permission", String.format("%s#%s", hostResource, "PUT"));
        groups.stream().map(group -> {
              Matcher mPermission = Pattern.compile("^/(.*-?TENANT-([a-z0-9\\-]+))-(PUT)$").matcher(group);
              return mPermission.find() ? mPermission : null;
            }).filter(v -> v != null).forEach(mPermission -> {
              form.add("permission", String.format("%s#%s", mPermission.group(1), mPermission.group(3)));
            });
        groups.stream().map(group -> {
              Matcher mPermission = Pattern.compile("^/(.*-?HOSTINVENTORY-([a-z0-9\\-]+))-(PUT)$").matcher(group);
              return mPermission.find() ? mPermission : null;
            }).filter(v -> v != null).forEach(mPermission -> {
              form.add("permission", String.format("%s#%s", mPermission.group(1), mPermission.group(3)));
            });
        groups.stream().map(group -> {
              Matcher mPermission = Pattern.compile("^/(.*-?HOST-([a-z0-9\\-]+))-(PUT)$").matcher(group);
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
            JsonArray scopes = authorizationDecisionBody.stream().map(o -> (JsonObject)o).filter(o -> "HOST".equals(o.getString("rsname"))).findFirst().map(decision -> ((JsonObject)decision).getJsonArray("scopes")).orElse(new JsonArray());
            if(!scopes.contains("PUT") && !classPublicRead) {
              //
              List<String> fqs = new ArrayList<>();
              authorizationDecisionBody.stream().map(o -> (JsonObject)o).filter(permission -> {
                    Matcher mPermission = Pattern.compile("^(TENANT-([a-z0-9\\-]+))$").matcher(permission.getString("rsname"));
                    return permission.getJsonArray("scopes").contains("PUT")
                        && mPermission.find();
                  }).forEach(permission -> {
                    fqs.add(String.format("%s:%s", "tenantResource", permission.getString("rsname")));
                  });
              authorizationDecisionBody.stream().map(o -> (JsonObject)o).filter(permission -> {
                    Matcher mPermission = Pattern.compile("^(HOSTINVENTORY-([a-z0-9\\-]+))$").matcher(permission.getString("rsname"));
                    return permission.getJsonArray("scopes").contains("PUT")
                        && mPermission.find();
                  }).forEach(permission -> {
                    fqs.add(String.format("%s:%s", "inventoryResource", permission.getString("rsname")));
                  });
              authorizationDecisionBody.stream().map(o -> (JsonObject)o).filter(permission -> {
                    Matcher mPermission = Pattern.compile("^(HOST-([a-z0-9\\-]+))$").matcher(permission.getString("rsname"));
                    return permission.getJsonArray("scopes").contains("PUT")
                        && mPermission.find();
                  }).forEach(permission -> {
                    fqs.add(String.format("%s:%s", "hostResource", permission.getString("rsname")));
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
              eventBus.publish("websocketHost", JsonObject.mapFrom(apiRequest).toString());
              varsHost(siteRequest).onSuccess(d -> {
                listPUTImportHost(apiRequest, siteRequest).onSuccess(e -> {
                  response200PUTImportHost(siteRequest).onSuccess(response -> {
                    LOG.debug(String.format("putimportHost succeeded. "));
                    eventHandler.handle(Future.succeededFuture(response));
                  }).onFailure(ex -> {
                    LOG.error(String.format("putimportHost failed. "), ex);
                    error(siteRequest, eventHandler, ex);
                  });
                }).onFailure(ex -> {
                  LOG.error(String.format("putimportHost failed. "), ex);
                  error(siteRequest, eventHandler, ex);
                });
              }).onFailure(ex -> {
                LOG.error(String.format("putimportHost failed. "), ex);
                error(siteRequest, eventHandler, ex);
              });
            }
          } catch(Exception ex) {
            LOG.error(String.format("putimportHost failed. "), ex);
            error(null, eventHandler, ex);
          }
        });
      } catch(Exception ex) {
        LOG.error(String.format("putimportHost failed. "), ex);
        error(null, eventHandler, ex);
      }
    }).onFailure(ex -> {
      if("Inactive Token".equals(ex.getMessage()) || StringUtils.startsWith(ex.getMessage(), "invalid_grant:")) {
        try {
          eventHandler.handle(Future.succeededFuture(new ServiceResponse(302, "Found", null, MultiMap.caseInsensitiveMultiMap().add(HttpHeaders.LOCATION, "/logout?redirect_uri=" + URLEncoder.encode(serviceRequest.getExtra().getString("uri"), "UTF-8")))));
        } catch(Exception ex2) {
          LOG.error(String.format("putimportHost failed. ", ex2));
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
        LOG.error(String.format("putimportHost failed. "), ex);
        error(null, eventHandler, ex);
      }
    });
  }

  public Future<Void> listPUTImportHost(ApiRequest apiRequest, SiteRequest siteRequest) {
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
          eventBus.request(Host.getClassApiAddress(), json, new DeliveryOptions().addHeader("action", "putimportHostFuture")).onSuccess(a -> {
            promise1.complete();
          }).onFailure(ex -> {
            LOG.error(String.format("listPUTImportHost failed. "), ex);
            promise1.tryFail(ex);
          });
        }));
      });
      CompositeFuture.all(futures).onSuccess(a -> {
        apiRequest.setNumPATCH(apiRequest.getNumPATCH() + 1);
        promise.complete();
      }).onFailure(ex -> {
        LOG.error(String.format("listPUTImportHost failed. "), ex);
        promise.tryFail(ex);
      });
    } catch(Exception ex) {
      LOG.error(String.format("listPUTImportHost failed. "), ex);
      promise.tryFail(ex);
    }
    return promise.future();
  }

  @Override
  public void putimportHostFuture(JsonObject body, ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler) {
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
        String hostResource = Optional.ofNullable(body.getString(Host.VAR_hostResource)).orElse(body.getString(Host.VAR_solrId));
        if(Optional.ofNullable(serviceRequest.getParams()).map(p -> p.getJsonObject("query")).map( q -> q.getJsonArray("var")).orElse(new JsonArray()).stream().filter(s -> "refresh:false".equals(s)).count() > 0L) {
          siteRequest.getRequestVars().put( "refresh", "false" );
        }
        pgPool.getConnection().onSuccess(sqlConnection -> {
          String sqlQuery = String.format("select * from %s WHERE hostResource=$1", Host.CLASS_SIMPLE_NAME);
          sqlConnection.preparedQuery(sqlQuery)
              .execute(Tuple.tuple(Arrays.asList(hostResource))
              ).onSuccess(result -> {
            sqlConnection.close().onSuccess(a -> {
              try {
                if(result.size() >= 1) {
                  Host o = new Host();
                  o.setSiteRequest_(siteRequest);
                  for(Row definition : result.value()) {
                    for(Integer i = 0; i < definition.size(); i++) {
                      try {
                        String columnName = definition.getColumnName(i);
                        Object columnValue = definition.getValue(i);
                        o.persistForClass(columnName, columnValue);
                      } catch(Exception e) {
                        LOG.error(String.format("persistHost failed. "), e);
                      }
                    }
                  }
                  Host o2 = new Host();
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
                      if(!StringUtils.containsAny(f, "hostResource", "created", "setCreated") && !Objects.equals(o.obtainForClass(f), o2.obtainForClass(f)))
                        body2.put("set" + StringUtils.capitalize(f), bodyVal);
                    }
                  }
                  for(String f : Optional.ofNullable(o.getSaves()).orElse(new ArrayList<>())) {
                    if(!body.fieldNames().contains(f)) {
                      if(!StringUtils.containsAny(f, "hostResource", "created", "setCreated") && !Objects.equals(o.obtainForClass(f), o2.obtainForClass(f)))
                        body2.putNull("set" + StringUtils.capitalize(f));
                    }
                  }
                  if(result.size() >= 1) {
                    apiRequest.setOriginal(o);
                    apiRequest.setId(Optional.ofNullable(o.getHostResource()).map(v -> v.toString()).orElse(null));
                    apiRequest.setSolrId(o.getSolrId());
                  }
                  siteRequest.setJsonObject(body2);
                  patchHostFuture(o, true).onSuccess(b -> {
                    LOG.debug("Import Host {} succeeded, modified Host. ", body.getValue(Host.VAR_hostResource));
                    eventHandler.handle(Future.succeededFuture());
                  }).onFailure(ex -> {
                    LOG.error(String.format("putimportHostFuture failed. "), ex);
                    eventHandler.handle(Future.failedFuture(ex));
                  });
                } else {
                  postHostFuture(siteRequest, true).onSuccess(b -> {
                    LOG.debug("Import Host {} succeeded, created new Host. ", body.getValue(Host.VAR_hostResource));
                    eventHandler.handle(Future.succeededFuture());
                  }).onFailure(ex -> {
                    LOG.error(String.format("putimportHostFuture failed. "), ex);
                    eventHandler.handle(Future.failedFuture(ex));
                  });
                }
              } catch(Exception ex) {
                LOG.error(String.format("putimportHostFuture failed. "), ex);
                eventHandler.handle(Future.failedFuture(ex));
              }
            }).onFailure(ex -> {
              LOG.error(String.format("putimportHostFuture failed. "), ex);
              eventHandler.handle(Future.failedFuture(ex));
            });
          }).onFailure(ex -> {
            LOG.error(String.format("putimportHostFuture failed. "), ex);
            eventHandler.handle(Future.failedFuture(ex));
          });
        }).onFailure(ex -> {
          LOG.error(String.format("putimportHostFuture failed. "), ex);
          eventHandler.handle(Future.failedFuture(ex));
        });
      } catch(Exception ex) {
        LOG.error(String.format("putimportHostFuture failed. "), ex);
        eventHandler.handle(Future.failedFuture(ex));
      }
    }).onFailure(ex -> {
      if("Inactive Token".equals(ex.getMessage()) || StringUtils.startsWith(ex.getMessage(), "invalid_grant:")) {
        try {
          eventHandler.handle(Future.succeededFuture(new ServiceResponse(302, "Found", null, MultiMap.caseInsensitiveMultiMap().add(HttpHeaders.LOCATION, "/logout?redirect_uri=" + URLEncoder.encode(serviceRequest.getExtra().getString("uri"), "UTF-8")))));
        } catch(Exception ex2) {
          LOG.error(String.format("putimportHost failed. ", ex2));
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
        LOG.error(String.format("putimportHost failed. "), ex);
        error(null, eventHandler, ex);
      }
    });
  }

  public Future<ServiceResponse> response200PUTImportHost(SiteRequest siteRequest) {
    Promise<ServiceResponse> promise = Promise.promise();
    try {
      JsonObject json = new JsonObject();
      promise.complete(ServiceResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily())));
    } catch(Exception ex) {
      LOG.error(String.format("response200PUTImportHost failed. "), ex);
      promise.tryFail(ex);
    }
    return promise.future();
  }

  // SearchPage //

  @Override
  public void searchpageHost(ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler) {
    oauth2AuthenticationProvider.refresh(User.create(serviceRequest.getUser())).onSuccess(user -> {
      serviceRequest.setUser(user.principal());
    Boolean classPublicRead = false;
    user(serviceRequest, SiteRequest.class, SiteUser.class, SiteUser.getClassApiAddress(), "postSiteUserFuture", "patchSiteUserFuture", classPublicRead).onSuccess(siteRequest -> {
      try {
        siteRequest.setLang("enUS");
        String hostResource = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("hostResource");
        String HOST = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("HOST");
        List<String> groups = Optional.ofNullable(siteRequest.getGroups()).orElse(new ArrayList<>());
        MultiMap form = MultiMap.caseInsensitiveMultiMap();
        form.add("grant_type", "urn:ietf:params:oauth:grant-type:uma-ticket");
        form.add("audience", config.getString(ComputateConfigKeys.AUTH_CLIENT));
        form.add("response_mode", "permissions");
        form.add("permission", String.format("%s#%s", Host.CLASS_AUTH_RESOURCE, "GET"));
        form.add("permission", String.format("%s#%s", Host.CLASS_AUTH_RESOURCE, "POST"));
        form.add("permission", String.format("%s#%s", Host.CLASS_AUTH_RESOURCE, "PATCH"));
        form.add("permission", String.format("%s#%s", Host.CLASS_AUTH_RESOURCE, "PUT"));
        form.add("permission", String.format("%s#%s", Host.CLASS_AUTH_RESOURCE, "DELETE"));
        form.add("permission", String.format("%s#%s", Host.CLASS_AUTH_RESOURCE, "Admin"));
        form.add("permission", String.format("%s#%s", Host.CLASS_AUTH_RESOURCE, "SuperAdmin"));
        if(hostResource != null)
          form.add("permission", String.format("%s#%s", hostResource, "GET"));
        groups.stream().map(group -> {
              Matcher mPermission = Pattern.compile("^/(.*-?TENANT-([a-z0-9\\-]+))-(GET)$").matcher(group);
              return mPermission.find() ? mPermission : null;
            }).filter(v -> v != null).forEach(mPermission -> {
              form.add("permission", String.format("%s#%s", mPermission.group(1), mPermission.group(3)));
            });
        groups.stream().map(group -> {
              Matcher mPermission = Pattern.compile("^/(.*-?HOSTINVENTORY-([a-z0-9\\-]+))-(GET)$").matcher(group);
              return mPermission.find() ? mPermission : null;
            }).filter(v -> v != null).forEach(mPermission -> {
              form.add("permission", String.format("%s#%s", mPermission.group(1), mPermission.group(3)));
            });
        groups.stream().map(group -> {
              Matcher mPermission = Pattern.compile("^/(.*-?HOST-([a-z0-9\\-]+))-(GET)$").matcher(group);
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
            JsonArray scopes = authorizationDecisionBody.stream().map(o -> (JsonObject)o).filter(o -> "HOST".equals(o.getString("rsname"))).findFirst().map(decision -> ((JsonObject)decision).getJsonArray("scopes")).orElse(new JsonArray());
            if(!scopes.contains("GET") && !classPublicRead) {
              //
              List<String> fqs = new ArrayList<>();
              authorizationDecisionBody.stream().map(o -> (JsonObject)o).filter(permission -> {
                    Matcher mPermission = Pattern.compile("^(TENANT-([a-z0-9\\-]+))$").matcher(permission.getString("rsname"));
                    return permission.getJsonArray("scopes").contains("GET")
                        && mPermission.find();
                  }).forEach(permission -> {
                    fqs.add(String.format("%s:%s", "tenantResource", permission.getString("rsname")));
                  });
              authorizationDecisionBody.stream().map(o -> (JsonObject)o).filter(permission -> {
                    Matcher mPermission = Pattern.compile("^(HOSTINVENTORY-([a-z0-9\\-]+))$").matcher(permission.getString("rsname"));
                    return permission.getJsonArray("scopes").contains("GET")
                        && mPermission.find();
                  }).forEach(permission -> {
                    fqs.add(String.format("%s:%s", "inventoryResource", permission.getString("rsname")));
                  });
              authorizationDecisionBody.stream().map(o -> (JsonObject)o).filter(permission -> {
                    Matcher mPermission = Pattern.compile("^(HOST-([a-z0-9\\-]+))$").matcher(permission.getString("rsname"));
                    return permission.getJsonArray("scopes").contains("GET")
                        && mPermission.find();
                  }).forEach(permission -> {
                    fqs.add(String.format("%s:%s", "hostResource", permission.getString("rsname")));
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
                scopes.add("GET");
                siteRequest.setFilteredScope(true);
              }
            }
            {
              siteRequest.setScopes(scopes.stream().map(o -> o.toString()).collect(Collectors.toList()));
              List<String> scopes2 = siteRequest.getScopes();
              searchHostList(siteRequest, false, true, false, "GET").onSuccess(listHost -> {
                response200SearchPageHost(listHost).onSuccess(response -> {
                  eventHandler.handle(Future.succeededFuture(response));
                  LOG.debug(String.format("searchpageHost succeeded. "));
                }).onFailure(ex -> {
                  LOG.error(String.format("searchpageHost failed. "), ex);
                  error(siteRequest, eventHandler, ex);
                });
              }).onFailure(ex -> {
                LOG.error(String.format("searchpageHost failed. "), ex);
                error(siteRequest, eventHandler, ex);
              });
            }
          } catch(Exception ex) {
            LOG.error(String.format("searchpageHost failed. "), ex);
            error(null, eventHandler, ex);
          }
        });
      } catch(Exception ex) {
        LOG.error(String.format("searchpageHost failed. "), ex);
        error(null, eventHandler, ex);
      }
    }).onFailure(ex -> {
      if("Inactive Token".equals(ex.getMessage()) || StringUtils.startsWith(ex.getMessage(), "invalid_grant:")) {
        try {
          eventHandler.handle(Future.succeededFuture(new ServiceResponse(302, "Found", null, MultiMap.caseInsensitiveMultiMap().add(HttpHeaders.LOCATION, "/logout?redirect_uri=" + URLEncoder.encode(serviceRequest.getExtra().getString("uri"), "UTF-8")))));
        } catch(Exception ex2) {
          LOG.error(String.format("searchpageHost failed. ", ex2));
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
        LOG.error(String.format("searchpageHost failed. "), ex);
        error(null, eventHandler, ex);
      }
    });
    }).onFailure(ex -> {
      if("Inactive Token".equals(ex.getMessage()) || StringUtils.startsWith(ex.getMessage(), "invalid_grant:")) {
        try {
          eventHandler.handle(Future.succeededFuture(new ServiceResponse(302, "Found", null, MultiMap.caseInsensitiveMultiMap().add(HttpHeaders.LOCATION, "/logout?redirect_uri=" + URLEncoder.encode(serviceRequest.getExtra().getString("uri"), "UTF-8")))));
        } catch(Exception ex2) {
          LOG.error(String.format("searchpageHost failed. ", ex2));
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
        LOG.error(String.format("searchpageHost failed. "), ex);
        error(null, eventHandler, ex);
      }
    });
  }

  public void searchpageHostPageInit(JsonObject ctx, HostPage page, SearchList<Host> listHost, Promise<Void> promise) {
    String siteBaseUrl = config.getString(ComputateConfigKeys.SITE_BASE_URL);

    ctx.put("enUSUrlSearchPage", String.format("%s%s", siteBaseUrl, "/en-us/search/host"));
    ctx.put("enUSUrlPage", String.format("%s%s", siteBaseUrl, "/en-us/search/host"));
    ctx.put("enUSUrlDisplayPage", Optional.ofNullable(page.getResult()).map(o -> o.getDisplayPage()));
    ctx.put("enUSUrlEditPage", Optional.ofNullable(page.getResult()).map(o -> o.getEditPage()));
    ctx.put("enUSUrlUserPage", Optional.ofNullable(page.getResult()).map(o -> o.getUserPage()));
    ctx.put("enUSUrlDownload", Optional.ofNullable(page.getResult()).map(o -> o.getDownload()));

    promise.complete();
  }

  public String templateUriSearchPageHost(ServiceRequest serviceRequest, Host result) {
    return "en-us/search/host/HostSearchPage.htm";
  }
  public void templateSearchPageHost(JsonObject ctx, HostPage page, SearchList<Host> listHost, Promise<String> promise) {
    try {
      SiteRequest siteRequest = listHost.getSiteRequest_(SiteRequest.class);
      ServiceRequest serviceRequest = siteRequest.getServiceRequest();
      Host result = listHost.first();
      String pageTemplateUri = templateUriSearchPageHost(serviceRequest, result);
      String siteTemplatePath = config.getString(ComputateConfigKeys.TEMPLATE_PATH);
      Path resourceTemplatePath = Path.of(siteTemplatePath, pageTemplateUri);
      if(result == null || !Files.exists(resourceTemplatePath)) {
        String template = Files.readString(Path.of(siteTemplatePath, "en-us/search/host/HostSearchPage.htm"), Charset.forName("UTF-8"));
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
      LOG.error(String.format("templateSearchPageHost failed. "), ex);
      ExceptionUtils.rethrow(ex);
    }
  }
  public Future<ServiceResponse> response200SearchPageHost(SearchList<Host> listHost) {
    Promise<ServiceResponse> promise = Promise.promise();
    try {
      SiteRequest siteRequest = listHost.getSiteRequest_(SiteRequest.class);
      HostPage page = new HostPage();
      MultiMap requestHeaders = MultiMap.caseInsensitiveMultiMap();
      siteRequest.setRequestHeaders(requestHeaders);

      if(listHost.size() >= 1)
        siteRequest.setRequestPk(listHost.get(0).getPk());
      page.setSearchListHost_(listHost);
      page.setSiteRequest_(siteRequest);
      page.setServiceRequest(siteRequest.getServiceRequest());
      page.setWebClient(webClient);
      page.setVertx(vertx);
      page.promiseDeepHostPage(siteRequest).onSuccess(a -> {
        try {
          JsonObject ctx = ConfigKeys.getPageContext(config);
          ctx.mergeIn(JsonObject.mapFrom(page));
          Promise<Void> promise1 = Promise.promise();
          searchpageHostPageInit(ctx, page, listHost, promise1);
          promise1.future().onSuccess(b -> {
            Promise<String> promise2 = Promise.promise();
            templateSearchPageHost(ctx, page, listHost, promise2);
            promise2.future().onSuccess(renderedTemplate -> {
              try {
                Buffer buffer = Buffer.buffer(renderedTemplate);
                promise.complete(new ServiceResponse(200, "OK", buffer, requestHeaders));
              } catch(Throwable ex) {
                LOG.error(String.format("response200SearchPageHost failed. "), ex);
                promise.fail(ex);
              }
            }).onFailure(ex -> {
              promise.fail(ex);
            });
          }).onFailure(ex -> {
            promise.tryFail(ex);
          });
        } catch(Exception ex) {
          LOG.error(String.format("response200SearchPageHost failed. "), ex);
          promise.tryFail(ex);
        }
      }).onFailure(ex -> {
        promise.tryFail(ex);
      });
    } catch(Exception ex) {
      LOG.error(String.format("response200SearchPageHost failed. "), ex);
      promise.tryFail(ex);
    }
    return promise.future();
  }
  public void responsePivotSearchPageHost(List<SolrResponse.Pivot> pivots, JsonArray pivotArray) {
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
          responsePivotSearchPageHost(pivotFields2, pivotArray2);
        }
      }
    }
  }

  // EditPage //

  @Override
  public void editpageHost(ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler) {
    Boolean classPublicRead = false;
    user(serviceRequest, SiteRequest.class, SiteUser.class, SiteUser.getClassApiAddress(), "postSiteUserFuture", "patchSiteUserFuture", classPublicRead).onSuccess(siteRequest -> {
      try {
        siteRequest.setLang("enUS");
        String hostResource = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("hostResource");
        String HOST = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("HOST");
        List<String> groups = Optional.ofNullable(siteRequest.getGroups()).orElse(new ArrayList<>());
        MultiMap form = MultiMap.caseInsensitiveMultiMap();
        form.add("grant_type", "urn:ietf:params:oauth:grant-type:uma-ticket");
        form.add("audience", config.getString(ComputateConfigKeys.AUTH_CLIENT));
        form.add("response_mode", "permissions");
        form.add("permission", String.format("%s#%s", Host.CLASS_AUTH_RESOURCE, "GET"));
        form.add("permission", String.format("%s#%s", Host.CLASS_AUTH_RESOURCE, "POST"));
        form.add("permission", String.format("%s#%s", Host.CLASS_AUTH_RESOURCE, "PATCH"));
        form.add("permission", String.format("%s#%s", Host.CLASS_AUTH_RESOURCE, "PUT"));
        form.add("permission", String.format("%s#%s", Host.CLASS_AUTH_RESOURCE, "DELETE"));
        form.add("permission", String.format("%s#%s", Host.CLASS_AUTH_RESOURCE, "Admin"));
        form.add("permission", String.format("%s#%s", Host.CLASS_AUTH_RESOURCE, "SuperAdmin"));
        if(hostResource != null)
          form.add("permission", String.format("%s#%s", hostResource, "GET"));
        groups.stream().map(group -> {
              Matcher mPermission = Pattern.compile("^/(.*-?TENANT-([a-z0-9\\-]+))-(GET)$").matcher(group);
              return mPermission.find() ? mPermission : null;
            }).filter(v -> v != null).forEach(mPermission -> {
              form.add("permission", String.format("%s#%s", mPermission.group(1), mPermission.group(3)));
            });
        groups.stream().map(group -> {
              Matcher mPermission = Pattern.compile("^/(.*-?HOSTINVENTORY-([a-z0-9\\-]+))-(GET)$").matcher(group);
              return mPermission.find() ? mPermission : null;
            }).filter(v -> v != null).forEach(mPermission -> {
              form.add("permission", String.format("%s#%s", mPermission.group(1), mPermission.group(3)));
            });
        groups.stream().map(group -> {
              Matcher mPermission = Pattern.compile("^/(.*-?HOST-([a-z0-9\\-]+))-(GET)$").matcher(group);
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
            JsonArray scopes = authorizationDecisionBody.stream().map(o -> (JsonObject)o).filter(o -> "HOST".equals(o.getString("rsname"))).findFirst().map(decision -> ((JsonObject)decision).getJsonArray("scopes")).orElse(new JsonArray());
            if(!scopes.contains("GET") && !classPublicRead) {
              List<String> fqs = new ArrayList<>();
              authorizationDecisionBody.stream().map(o -> (JsonObject)o).filter(permission -> {
                    Matcher mPermission = Pattern.compile("^(TENANT-([a-z0-9\\-]+))$").matcher(permission.getString("rsname"));
                    return permission.getJsonArray("scopes").contains("GET")
                        && mPermission.find();
                  }).forEach(permission -> {
                    fqs.add(String.format("%s:%s", "tenantResource", permission.getString("rsname")));
                  });
              authorizationDecisionBody.stream().map(o -> (JsonObject)o).filter(permission -> {
                    Matcher mPermission = Pattern.compile("^(HOSTINVENTORY-([a-z0-9\\-]+))$").matcher(permission.getString("rsname"));
                    return permission.getJsonArray("scopes").contains("GET")
                        && mPermission.find();
                  }).forEach(permission -> {
                    fqs.add(String.format("%s:%s", "inventoryResource", permission.getString("rsname")));
                  });
              authorizationDecisionBody.stream().map(o -> (JsonObject)o).filter(permission -> {
                    Matcher mPermission = Pattern.compile("^(HOST-([a-z0-9\\-]+))$").matcher(permission.getString("rsname"));
                    return permission.getJsonArray("scopes").contains("GET")
                        && mPermission.find();
                  }).forEach(permission -> {
                    fqs.add(String.format("%s:%s", "hostResource", permission.getString("rsname")));
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
                scopes.add("GET");
                siteRequest.setFilteredScope(true);
              }
            }
            {
              siteRequest.setScopes(scopes.stream().map(o -> o.toString()).collect(Collectors.toList()));
              List<String> scopes2 = siteRequest.getScopes();
              searchHostList(siteRequest, false, true, false, "GET").onSuccess(listHost -> {
                response200EditPageHost(listHost).onSuccess(response -> {
                  eventHandler.handle(Future.succeededFuture(response));
                  LOG.debug(String.format("editpageHost succeeded. "));
                }).onFailure(ex -> {
                  LOG.error(String.format("editpageHost failed. "), ex);
                  error(siteRequest, eventHandler, ex);
                });
              }).onFailure(ex -> {
                LOG.error(String.format("editpageHost failed. "), ex);
                error(siteRequest, eventHandler, ex);
            });
            }
          } catch(Exception ex) {
            LOG.error(String.format("editpageHost failed. "), ex);
            error(null, eventHandler, ex);
          }
        });
      } catch(Exception ex) {
        LOG.error(String.format("editpageHost failed. "), ex);
        error(null, eventHandler, ex);
      }
    }).onFailure(ex -> {
      if("Inactive Token".equals(ex.getMessage()) || StringUtils.startsWith(ex.getMessage(), "invalid_grant:")) {
        try {
          eventHandler.handle(Future.succeededFuture(new ServiceResponse(302, "Found", null, MultiMap.caseInsensitiveMultiMap().add(HttpHeaders.LOCATION, "/logout?redirect_uri=" + URLEncoder.encode(serviceRequest.getExtra().getString("uri"), "UTF-8")))));
        } catch(Exception ex2) {
          LOG.error(String.format("editpageHost failed. ", ex2));
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
        LOG.error(String.format("editpageHost failed. "), ex);
        error(null, eventHandler, ex);
      }
    });
  }

  public void editpageHostPageInit(JsonObject ctx, HostPage page, SearchList<Host> listHost, Promise<Void> promise) {
    String siteBaseUrl = config.getString(ComputateConfigKeys.SITE_BASE_URL);

    ctx.put("enUSUrlSearchPage", String.format("%s%s", siteBaseUrl, "/en-us/search/host"));
    ctx.put("enUSUrlDisplayPage", Optional.ofNullable(page.getResult()).map(o -> o.getDisplayPage()));
    ctx.put("enUSUrlEditPage", Optional.ofNullable(page.getResult()).map(o -> o.getEditPage()));
    ctx.put("enUSUrlPage", Optional.ofNullable(page.getResult()).map(o -> o.getEditPage()));
    ctx.put("enUSUrlUserPage", Optional.ofNullable(page.getResult()).map(o -> o.getUserPage()));
    ctx.put("enUSUrlDownload", Optional.ofNullable(page.getResult()).map(o -> o.getDownload()));

    promise.complete();
  }

  public String templateUriEditPageHost(ServiceRequest serviceRequest, Host result) {
    return "en-us/edit/host/HostEditPage.htm";
  }
  public void templateEditPageHost(JsonObject ctx, HostPage page, SearchList<Host> listHost, Promise<String> promise) {
    try {
      SiteRequest siteRequest = listHost.getSiteRequest_(SiteRequest.class);
      ServiceRequest serviceRequest = siteRequest.getServiceRequest();
      Host result = listHost.first();
      String pageTemplateUri = templateUriEditPageHost(serviceRequest, result);
      String siteTemplatePath = config.getString(ComputateConfigKeys.TEMPLATE_PATH);
      Path resourceTemplatePath = Path.of(siteTemplatePath, pageTemplateUri);
      if(result == null || !Files.exists(resourceTemplatePath)) {
        String template = Files.readString(Path.of(siteTemplatePath, "en-us/search/host/HostSearchPage.htm"), Charset.forName("UTF-8"));
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
      LOG.error(String.format("templateEditPageHost failed. "), ex);
      ExceptionUtils.rethrow(ex);
    }
  }
  public Future<ServiceResponse> response200EditPageHost(SearchList<Host> listHost) {
    Promise<ServiceResponse> promise = Promise.promise();
    try {
      SiteRequest siteRequest = listHost.getSiteRequest_(SiteRequest.class);
      HostPage page = new HostPage();
      MultiMap requestHeaders = MultiMap.caseInsensitiveMultiMap();
      siteRequest.setRequestHeaders(requestHeaders);

      if(listHost.size() >= 1)
        siteRequest.setRequestPk(listHost.get(0).getPk());
      page.setSearchListHost_(listHost);
      page.setSiteRequest_(siteRequest);
      page.setServiceRequest(siteRequest.getServiceRequest());
      page.setWebClient(webClient);
      page.setVertx(vertx);
      page.promiseDeepHostPage(siteRequest).onSuccess(a -> {
        try {
          JsonObject ctx = ConfigKeys.getPageContext(config);
          ctx.mergeIn(JsonObject.mapFrom(page));
          Promise<Void> promise1 = Promise.promise();
          editpageHostPageInit(ctx, page, listHost, promise1);
          promise1.future().onSuccess(b -> {
            Promise<String> promise2 = Promise.promise();
            templateEditPageHost(ctx, page, listHost, promise2);
            promise2.future().onSuccess(renderedTemplate -> {
              try {
                Buffer buffer = Buffer.buffer(renderedTemplate);
                promise.complete(new ServiceResponse(200, "OK", buffer, requestHeaders));
              } catch(Throwable ex) {
                LOG.error(String.format("response200EditPageHost failed. "), ex);
                promise.fail(ex);
              }
            }).onFailure(ex -> {
              promise.fail(ex);
            });
          }).onFailure(ex -> {
            promise.tryFail(ex);
          });
        } catch(Exception ex) {
          LOG.error(String.format("response200EditPageHost failed. "), ex);
          promise.tryFail(ex);
        }
      }).onFailure(ex -> {
        promise.tryFail(ex);
      });
    } catch(Exception ex) {
      LOG.error(String.format("response200EditPageHost failed. "), ex);
      promise.tryFail(ex);
    }
    return promise.future();
  }
  public void responsePivotEditPageHost(List<SolrResponse.Pivot> pivots, JsonArray pivotArray) {
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
          responsePivotEditPageHost(pivotFields2, pivotArray2);
        }
      }
    }
  }

  // UserPage //

  @Override
  public void userpageHost(ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler) {
    Boolean classPublicRead = false;
    user(serviceRequest, SiteRequest.class, SiteUser.class, SiteUser.getClassApiAddress(), "postSiteUserFuture", "patchSiteUserFuture", classPublicRead).onSuccess(siteRequest -> {
      try {
        siteRequest.setLang("enUS");
        String hostResource = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("hostResource");
        String HOST = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("HOST");
        List<String> groups = Optional.ofNullable(siteRequest.getGroups()).orElse(new ArrayList<>());
        MultiMap form = MultiMap.caseInsensitiveMultiMap();
        form.add("grant_type", "urn:ietf:params:oauth:grant-type:uma-ticket");
        form.add("audience", config.getString(ComputateConfigKeys.AUTH_CLIENT));
        form.add("response_mode", "permissions");
        form.add("permission", String.format("%s#%s", Host.CLASS_AUTH_RESOURCE, "GET"));
        form.add("permission", String.format("%s#%s", Host.CLASS_AUTH_RESOURCE, "POST"));
        form.add("permission", String.format("%s#%s", Host.CLASS_AUTH_RESOURCE, "PATCH"));
        form.add("permission", String.format("%s#%s", Host.CLASS_AUTH_RESOURCE, "PUT"));
        form.add("permission", String.format("%s#%s", Host.CLASS_AUTH_RESOURCE, "DELETE"));
        form.add("permission", String.format("%s#%s", Host.CLASS_AUTH_RESOURCE, "Admin"));
        form.add("permission", String.format("%s#%s", Host.CLASS_AUTH_RESOURCE, "SuperAdmin"));
        if(hostResource != null)
          form.add("permission", String.format("%s#%s", hostResource, "GET"));
        groups.stream().map(group -> {
              Matcher mPermission = Pattern.compile("^/(.*-?TENANT-([a-z0-9\\-]+))-(GET)$").matcher(group);
              return mPermission.find() ? mPermission : null;
            }).filter(v -> v != null).forEach(mPermission -> {
              form.add("permission", String.format("%s#%s", mPermission.group(1), mPermission.group(3)));
            });
        groups.stream().map(group -> {
              Matcher mPermission = Pattern.compile("^/(.*-?HOSTINVENTORY-([a-z0-9\\-]+))-(GET)$").matcher(group);
              return mPermission.find() ? mPermission : null;
            }).filter(v -> v != null).forEach(mPermission -> {
              form.add("permission", String.format("%s#%s", mPermission.group(1), mPermission.group(3)));
            });
        groups.stream().map(group -> {
              Matcher mPermission = Pattern.compile("^/(.*-?HOST-([a-z0-9\\-]+))-(GET)$").matcher(group);
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
            JsonArray scopes = authorizationDecisionBody.stream().map(o -> (JsonObject)o).filter(o -> "HOST".equals(o.getString("rsname"))).findFirst().map(decision -> ((JsonObject)decision).getJsonArray("scopes")).orElse(new JsonArray());
            if(!scopes.contains("GET") && !classPublicRead) {
              List<String> fqs = new ArrayList<>();
              authorizationDecisionBody.stream().map(o -> (JsonObject)o).filter(permission -> {
                    Matcher mPermission = Pattern.compile("^(TENANT-([a-z0-9\\-]+))$").matcher(permission.getString("rsname"));
                    return permission.getJsonArray("scopes").contains("GET")
                        && mPermission.find();
                  }).forEach(permission -> {
                    fqs.add(String.format("%s:%s", "tenantResource", permission.getString("rsname")));
                  });
              authorizationDecisionBody.stream().map(o -> (JsonObject)o).filter(permission -> {
                    Matcher mPermission = Pattern.compile("^(HOSTINVENTORY-([a-z0-9\\-]+))$").matcher(permission.getString("rsname"));
                    return permission.getJsonArray("scopes").contains("GET")
                        && mPermission.find();
                  }).forEach(permission -> {
                    fqs.add(String.format("%s:%s", "inventoryResource", permission.getString("rsname")));
                  });
              authorizationDecisionBody.stream().map(o -> (JsonObject)o).filter(permission -> {
                    Matcher mPermission = Pattern.compile("^(HOST-([a-z0-9\\-]+))$").matcher(permission.getString("rsname"));
                    return permission.getJsonArray("scopes").contains("GET")
                        && mPermission.find();
                  }).forEach(permission -> {
                    fqs.add(String.format("%s:%s", "hostResource", permission.getString("rsname")));
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
                scopes.add("GET");
                siteRequest.setFilteredScope(true);
              }
            }
            {
              siteRequest.setScopes(scopes.stream().map(o -> o.toString()).collect(Collectors.toList()));
              List<String> scopes2 = siteRequest.getScopes();
              searchHostList(siteRequest, false, true, false, "GET").onSuccess(listHost -> {
                response200UserPageHost(listHost).onSuccess(response -> {
                  eventHandler.handle(Future.succeededFuture(response));
                  LOG.debug(String.format("userpageHost succeeded. "));
                }).onFailure(ex -> {
                  LOG.error(String.format("userpageHost failed. "), ex);
                  error(siteRequest, eventHandler, ex);
                });
              }).onFailure(ex -> {
                LOG.error(String.format("userpageHost failed. "), ex);
                error(siteRequest, eventHandler, ex);
            });
            }
          } catch(Exception ex) {
            LOG.error(String.format("userpageHost failed. "), ex);
            error(null, eventHandler, ex);
          }
        });
      } catch(Exception ex) {
        LOG.error(String.format("userpageHost failed. "), ex);
        error(null, eventHandler, ex);
      }
    }).onFailure(ex -> {
      if("Inactive Token".equals(ex.getMessage()) || StringUtils.startsWith(ex.getMessage(), "invalid_grant:")) {
        try {
          eventHandler.handle(Future.succeededFuture(new ServiceResponse(302, "Found", null, MultiMap.caseInsensitiveMultiMap().add(HttpHeaders.LOCATION, "/logout?redirect_uri=" + URLEncoder.encode(serviceRequest.getExtra().getString("uri"), "UTF-8")))));
        } catch(Exception ex2) {
          LOG.error(String.format("userpageHost failed. ", ex2));
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
        LOG.error(String.format("userpageHost failed. "), ex);
        error(null, eventHandler, ex);
      }
    });
  }

  public void userpageHostPageInit(JsonObject ctx, HostPage page, SearchList<Host> listHost, Promise<Void> promise) {
    String siteBaseUrl = config.getString(ComputateConfigKeys.SITE_BASE_URL);

    ctx.put("enUSUrlSearchPage", String.format("%s%s", siteBaseUrl, "/en-us/search/host"));
    ctx.put("enUSUrlDisplayPage", Optional.ofNullable(page.getResult()).map(o -> o.getDisplayPage()));
    ctx.put("enUSUrlEditPage", Optional.ofNullable(page.getResult()).map(o -> o.getEditPage()));
    ctx.put("enUSUrlUserPage", Optional.ofNullable(page.getResult()).map(o -> o.getUserPage()));
    ctx.put("enUSUrlPage", Optional.ofNullable(page.getResult()).map(o -> o.getUserPage()));
    ctx.put("enUSUrlDownload", Optional.ofNullable(page.getResult()).map(o -> o.getDownload()));

    promise.complete();
  }

  public String templateUriUserPageHost(ServiceRequest serviceRequest, Host result) {
    return String.format("%s.htm", StringUtils.substringBefore(serviceRequest.getExtra().getString("uri").substring(1), "?"));
  }
  public void templateUserPageHost(JsonObject ctx, HostPage page, SearchList<Host> listHost, Promise<String> promise) {
    try {
      SiteRequest siteRequest = listHost.getSiteRequest_(SiteRequest.class);
      ServiceRequest serviceRequest = siteRequest.getServiceRequest();
      Host result = listHost.first();
      String pageTemplateUri = templateUriUserPageHost(serviceRequest, result);
      String siteTemplatePath = config.getString(ComputateConfigKeys.TEMPLATE_PATH);
      Path resourceTemplatePath = Path.of(siteTemplatePath, pageTemplateUri);
      if(result == null || !Files.exists(resourceTemplatePath)) {
        String template = Files.readString(Path.of(siteTemplatePath, "en-us/search/host/HostSearchPage.htm"), Charset.forName("UTF-8"));
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
      LOG.error(String.format("templateUserPageHost failed. "), ex);
      ExceptionUtils.rethrow(ex);
    }
  }
  public Future<ServiceResponse> response200UserPageHost(SearchList<Host> listHost) {
    Promise<ServiceResponse> promise = Promise.promise();
    try {
      SiteRequest siteRequest = listHost.getSiteRequest_(SiteRequest.class);
      HostPage page = new HostPage();
      MultiMap requestHeaders = MultiMap.caseInsensitiveMultiMap();
      siteRequest.setRequestHeaders(requestHeaders);

      if(listHost.size() >= 1)
        siteRequest.setRequestPk(listHost.get(0).getPk());
      page.setSearchListHost_(listHost);
      page.setSiteRequest_(siteRequest);
      page.setServiceRequest(siteRequest.getServiceRequest());
      page.setWebClient(webClient);
      page.setVertx(vertx);
      page.promiseDeepHostPage(siteRequest).onSuccess(a -> {
        try {
          JsonObject ctx = ConfigKeys.getPageContext(config);
          ctx.mergeIn(JsonObject.mapFrom(page));
          Promise<Void> promise1 = Promise.promise();
          userpageHostPageInit(ctx, page, listHost, promise1);
          promise1.future().onSuccess(b -> {
            Promise<String> promise2 = Promise.promise();
            templateUserPageHost(ctx, page, listHost, promise2);
            promise2.future().onSuccess(renderedTemplate -> {
              try {
                Buffer buffer = Buffer.buffer(renderedTemplate);
                promise.complete(new ServiceResponse(200, "OK", buffer, requestHeaders));
              } catch(Throwable ex) {
                LOG.error(String.format("response200UserPageHost failed. "), ex);
                promise.fail(ex);
              }
            }).onFailure(ex -> {
              promise.fail(ex);
            });
          }).onFailure(ex -> {
            promise.tryFail(ex);
          });
        } catch(Exception ex) {
          LOG.error(String.format("response200UserPageHost failed. "), ex);
          promise.tryFail(ex);
        }
      }).onFailure(ex -> {
        promise.tryFail(ex);
      });
    } catch(Exception ex) {
      LOG.error(String.format("response200UserPageHost failed. "), ex);
      promise.tryFail(ex);
    }
    return promise.future();
  }
  public void responsePivotUserPageHost(List<SolrResponse.Pivot> pivots, JsonArray pivotArray) {
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
          responsePivotUserPageHost(pivotFields2, pivotArray2);
        }
      }
    }
  }

  // DELETEFilter //

  @Override
  public void deletefilterHost(JsonObject body, ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler) {
    LOG.debug(String.format("deletefilterHost started. "));
    Boolean classPublicRead = false;
    user(serviceRequest, SiteRequest.class, SiteUser.class, SiteUser.getClassApiAddress(), "postSiteUserFuture", "patchSiteUserFuture", classPublicRead).onSuccess(siteRequest -> {
      try {
        siteRequest.setLang("enUS");
        String hostResource = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("hostResource");
        String HOST = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("HOST");
        List<String> groups = Optional.ofNullable(siteRequest.getGroups()).orElse(new ArrayList<>());
        MultiMap form = MultiMap.caseInsensitiveMultiMap();
        form.add("grant_type", "urn:ietf:params:oauth:grant-type:uma-ticket");
        form.add("audience", config.getString(ComputateConfigKeys.AUTH_CLIENT));
        form.add("response_mode", "permissions");
        form.add("permission", String.format("%s#%s", Host.CLASS_AUTH_RESOURCE, "GET"));
        form.add("permission", String.format("%s#%s", Host.CLASS_AUTH_RESOURCE, "POST"));
        form.add("permission", String.format("%s#%s", Host.CLASS_AUTH_RESOURCE, "PATCH"));
        form.add("permission", String.format("%s#%s", Host.CLASS_AUTH_RESOURCE, "PUT"));
        form.add("permission", String.format("%s#%s", Host.CLASS_AUTH_RESOURCE, "DELETE"));
        form.add("permission", String.format("%s#%s", Host.CLASS_AUTH_RESOURCE, "Admin"));
        form.add("permission", String.format("%s#%s", Host.CLASS_AUTH_RESOURCE, "SuperAdmin"));
        if(hostResource != null)
          form.add("permission", String.format("%s#%s", hostResource, "DELETE"));
        groups.stream().map(group -> {
              Matcher mPermission = Pattern.compile("^/(.*-?TENANT-([a-z0-9\\-]+))-(DELETE)$").matcher(group);
              return mPermission.find() ? mPermission : null;
            }).filter(v -> v != null).forEach(mPermission -> {
              form.add("permission", String.format("%s#%s", mPermission.group(1), mPermission.group(3)));
            });
        groups.stream().map(group -> {
              Matcher mPermission = Pattern.compile("^/(.*-?HOSTINVENTORY-([a-z0-9\\-]+))-(DELETE)$").matcher(group);
              return mPermission.find() ? mPermission : null;
            }).filter(v -> v != null).forEach(mPermission -> {
              form.add("permission", String.format("%s#%s", mPermission.group(1), mPermission.group(3)));
            });
        groups.stream().map(group -> {
              Matcher mPermission = Pattern.compile("^/(.*-?HOST-([a-z0-9\\-]+))-(DELETE)$").matcher(group);
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
            JsonArray scopes = authorizationDecisionBody.stream().map(o -> (JsonObject)o).filter(o -> "HOST".equals(o.getString("rsname"))).findFirst().map(decision -> ((JsonObject)decision).getJsonArray("scopes")).orElse(new JsonArray());
            if(!scopes.contains("DELETE") && !classPublicRead) {
              //
              List<String> fqs = new ArrayList<>();
              authorizationDecisionBody.stream().map(o -> (JsonObject)o).filter(permission -> {
                    Matcher mPermission = Pattern.compile("^(TENANT-([a-z0-9\\-]+))$").matcher(permission.getString("rsname"));
                    return permission.getJsonArray("scopes").contains("DELETE")
                        && mPermission.find();
                  }).forEach(permission -> {
                    fqs.add(String.format("%s:%s", "tenantResource", permission.getString("rsname")));
                  });
              authorizationDecisionBody.stream().map(o -> (JsonObject)o).filter(permission -> {
                    Matcher mPermission = Pattern.compile("^(HOSTINVENTORY-([a-z0-9\\-]+))$").matcher(permission.getString("rsname"));
                    return permission.getJsonArray("scopes").contains("DELETE")
                        && mPermission.find();
                  }).forEach(permission -> {
                    fqs.add(String.format("%s:%s", "inventoryResource", permission.getString("rsname")));
                  });
              authorizationDecisionBody.stream().map(o -> (JsonObject)o).filter(permission -> {
                    Matcher mPermission = Pattern.compile("^(HOST-([a-z0-9\\-]+))$").matcher(permission.getString("rsname"));
                    return permission.getJsonArray("scopes").contains("DELETE")
                        && mPermission.find();
                  }).forEach(permission -> {
                    fqs.add(String.format("%s:%s", "hostResource", permission.getString("rsname")));
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
              searchHostList(siteRequest, false, true, true, "DELETE").onSuccess(listHost -> {
                try {
                  ApiRequest apiRequest = new ApiRequest();
                  apiRequest.setRows(listHost.getRequest().getRows());
                  apiRequest.setNumFound(listHost.getResponse().getResponse().getNumFound());
                  apiRequest.setNumPATCH(0L);
                  apiRequest.initDeepApiRequest(siteRequest);
                  siteRequest.setApiRequest_(apiRequest);
                  if(apiRequest.getNumFound() == 1L)
                    apiRequest.setOriginal(listHost.first());
                  apiRequest.setSolrId(Optional.ofNullable(listHost.first()).map(o2 -> o2.getSolrId()).orElse(null));
                  eventBus.publish("websocketHost", JsonObject.mapFrom(apiRequest).toString());

                  listDELETEFilterHost(apiRequest, listHost).onSuccess(e -> {
                    response200DELETEFilterHost(siteRequest).onSuccess(response -> {
                      LOG.debug(String.format("deletefilterHost succeeded. "));
                      eventHandler.handle(Future.succeededFuture(response));
                    }).onFailure(ex -> {
                      LOG.error(String.format("deletefilterHost failed. "), ex);
                      error(siteRequest, eventHandler, ex);
                    });
                  }).onFailure(ex -> {
                    LOG.error(String.format("deletefilterHost failed. "), ex);
                    error(siteRequest, eventHandler, ex);
                  });
                } catch(Exception ex) {
                  LOG.error(String.format("deletefilterHost failed. "), ex);
                  error(siteRequest, eventHandler, ex);
                }
              }).onFailure(ex -> {
                LOG.error(String.format("deletefilterHost failed. "), ex);
                error(siteRequest, eventHandler, ex);
              });
            }
          } catch(Exception ex) {
            LOG.error(String.format("deletefilterHost failed. "), ex);
            error(null, eventHandler, ex);
          }
        });
      } catch(Exception ex) {
        LOG.error(String.format("deletefilterHost failed. "), ex);
        error(null, eventHandler, ex);
      }
    }).onFailure(ex -> {
      if("Inactive Token".equals(ex.getMessage()) || StringUtils.startsWith(ex.getMessage(), "invalid_grant:")) {
        try {
          eventHandler.handle(Future.succeededFuture(new ServiceResponse(302, "Found", null, MultiMap.caseInsensitiveMultiMap().add(HttpHeaders.LOCATION, "/logout?redirect_uri=" + URLEncoder.encode(serviceRequest.getExtra().getString("uri"), "UTF-8")))));
        } catch(Exception ex2) {
          LOG.error(String.format("deletefilterHost failed. ", ex2));
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
        LOG.error(String.format("deletefilterHost failed. "), ex);
        error(null, eventHandler, ex);
      }
    });
  }

  public Future<Void> listDELETEFilterHost(ApiRequest apiRequest, SearchList<Host> listHost) {
    Promise<Void> promise = Promise.promise();
    List<Future> futures = new ArrayList<>();
    SiteRequest siteRequest = listHost.getSiteRequest_(SiteRequest.class);
    listHost.getList().forEach(o -> {
      SiteRequest siteRequest2 = generateSiteRequest(siteRequest.getUser(), siteRequest.getUserPrincipal(), siteRequest.getServiceRequest(), siteRequest.getJsonObject(), SiteRequest.class);
      siteRequest2.setScopes(siteRequest.getScopes());
      o.setSiteRequest_(siteRequest2);
      siteRequest2.setApiRequest_(siteRequest.getApiRequest_());
      JsonObject jsonObject = JsonObject.mapFrom(o);
      Host o2 = jsonObject.mapTo(Host.class);
      o2.setSiteRequest_(siteRequest2);
      futures.add(Future.future(promise1 -> {
        deletefilterHostFuture(o).onSuccess(a -> {
          promise1.complete();
        }).onFailure(ex -> {
          LOG.error(String.format("listDELETEFilterHost failed. "), ex);
          promise1.tryFail(ex);
        });
      }));
    });
    CompositeFuture.all(futures).onSuccess( a -> {
      listHost.next().onSuccess(next -> {
        if(next) {
          listDELETEFilterHost(apiRequest, listHost).onSuccess(b -> {
            promise.complete();
          }).onFailure(ex -> {
            LOG.error(String.format("listDELETEFilterHost failed. "), ex);
            promise.tryFail(ex);
          });
        } else {
          promise.complete();
        }
      }).onFailure(ex -> {
        LOG.error(String.format("listDELETEFilterHost failed. "), ex);
        promise.tryFail(ex);
      });
    }).onFailure(ex -> {
      LOG.error(String.format("listDELETEFilterHost failed. "), ex);
      promise.tryFail(ex);
    });
    return promise.future();
  }

  @Override
  public void deletefilterHostFuture(JsonObject body, ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler) {
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
        searchHostList(siteRequest, false, true, true, "DELETE").onSuccess(listHost -> {
          try {
            Host o = listHost.first();
            if(o != null && listHost.getResponse().getResponse().getNumFound() == 1) {
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
              apiRequest.setId(Optional.ofNullable(listHost.first()).map(o2 -> o2.getHostResource().toString()).orElse(null));
              apiRequest.setSolrId(Optional.ofNullable(listHost.first()).map(o2 -> o2.getSolrId()).orElse(null));
              deletefilterHostFuture(o).onSuccess(o2 -> {
                eventHandler.handle(Future.succeededFuture(ServiceResponse.completedWithJson(Buffer.buffer(new JsonObject().encodePrettily()))));
              }).onFailure(ex -> {
                eventHandler.handle(Future.failedFuture(ex));
              });
            } else {
              eventHandler.handle(Future.succeededFuture(ServiceResponse.completedWithJson(Buffer.buffer(new JsonObject().encodePrettily()))));
            }
          } catch(Exception ex) {
            LOG.error(String.format("deletefilterHost failed. "), ex);
            error(siteRequest, eventHandler, ex);
          }
        }).onFailure(ex -> {
          LOG.error(String.format("deletefilterHost failed. "), ex);
          error(siteRequest, eventHandler, ex);
        });
      } catch(Exception ex) {
        LOG.error(String.format("deletefilterHost failed. "), ex);
        error(null, eventHandler, ex);
      }
    }).onFailure(ex -> {
      LOG.error(String.format("deletefilterHost failed. "), ex);
      error(null, eventHandler, ex);
    });
  }

  public Future<Host> deletefilterHostFuture(Host o) {
    SiteRequest siteRequest = o.getSiteRequest_();
    Promise<Host> promise = Promise.promise();

    try {
      ApiRequest apiRequest = siteRequest.getApiRequest_();
      Promise<Host> promise1 = Promise.promise();
      pgPool.withTransaction(sqlConnection -> {
        siteRequest.setSqlConnection(sqlConnection);
        varsHost(siteRequest).onSuccess(a -> {
          sqlDELETEFilterHost(o).onSuccess(host -> {
            relateHost(o).onSuccess(d -> {
              unindexHost(o).onSuccess(o2 -> {
                if(apiRequest != null) {
                  apiRequest.setNumPATCH(apiRequest.getNumPATCH() + 1);
                  if(apiRequest.getNumFound() == 1L && Optional.ofNullable(siteRequest.getJsonObject()).map(json -> json.size() > 0).orElse(false)) {
                    o2.apiRequestHost();
                    if(apiRequest.getVars().size() > 0 && Optional.ofNullable(siteRequest.getRequestVars().get("refresh")).map(refresh -> !refresh.equals("false")).orElse(true))
                      eventBus.publish("websocketHost", JsonObject.mapFrom(apiRequest).toString());
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
      }).compose(host -> {
        Promise<Host> promise2 = Promise.promise();
        refreshHost(o).onSuccess(a -> {
          promise2.complete(o);
        }).onFailure(ex -> {
          promise2.tryFail(ex);
        });
        return promise2.future();
      }).onSuccess(host -> {
        promise.complete(host);
      }).onFailure(ex -> {
        promise.tryFail(ex);
      });
    } catch(Exception ex) {
      LOG.error(String.format("deletefilterHostFuture failed. "), ex);
      promise.tryFail(ex);
    }
    return promise.future();
  }

  public Future<Void> sqlDELETEFilterHost(Host o) {
    Promise<Void> promise = Promise.promise();
    try {
      SiteRequest siteRequest = o.getSiteRequest_();
      ApiRequest apiRequest = siteRequest.getApiRequest_();
      List<String> solrIds = Optional.ofNullable(apiRequest).map(r -> r.getSolrIds()).orElse(new ArrayList<>());
      List<String> classes = Optional.ofNullable(apiRequest).map(r -> r.getClasses()).orElse(new ArrayList<>());
      SqlConnection sqlConnection = siteRequest.getSqlConnection();
      Integer num = 1;
      StringBuilder bSql = new StringBuilder("DELETE FROM Host ");
      List<Object> bParams = new ArrayList<Object>();
      Long pk = o.getPk();
      JsonObject jsonObject = siteRequest.getJsonObject();
      Host o2 = new Host();
      o2.setSiteRequest_(siteRequest);
      List<Future> futures1 = new ArrayList<>();
      List<Future> futures2 = new ArrayList<>();

      if(jsonObject != null) {
        Set<String> entityVars = jsonObject.fieldNames();
        for(String entityVar : entityVars) {
          switch(entityVar) {
          case Host.VAR_tenantResource:
            Optional.ofNullable(jsonObject.getString(entityVar)).ifPresent(val -> {
              futures1.add(Future.future(promise2 -> {
                searchModel(siteRequest).query(Tenant.varIndexedTenant(Tenant.VAR_tenantResource), Tenant.class, val).onSuccess(o3 -> {
                  String solrId2 = Optional.ofNullable(o3).map(o4 -> o4.getSolrId()).filter(solrId3 -> !solrIds.contains(solrId3)).orElse(null);
                  if(solrId2 != null) {
                    solrIds.add(solrId2);
                    classes.add("Tenant");
                  }
                  sql(siteRequest).update(Host.class, pk).set(Host.VAR_tenantResource, Tenant.class, null, null).onSuccess(a -> {
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
          case Host.VAR_inventoryResource:
            Optional.ofNullable(jsonObject.getString(entityVar)).ifPresent(val -> {
              futures1.add(Future.future(promise2 -> {
                searchModel(siteRequest).query(HostInventory.varIndexedHostInventory(HostInventory.VAR_inventoryResource), HostInventory.class, val).onSuccess(o3 -> {
                  String solrId2 = Optional.ofNullable(o3).map(o4 -> o4.getSolrId()).filter(solrId3 -> !solrIds.contains(solrId3)).orElse(null);
                  if(solrId2 != null) {
                    solrIds.add(solrId2);
                    classes.add("HostInventory");
                  }
                  sql(siteRequest).update(Host.class, pk).set(Host.VAR_inventoryResource, HostInventory.class, null, null).onSuccess(a -> {
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
          RuntimeException ex2 = new RuntimeException("value Host failed", ex);
          LOG.error(String.format("unrelateHost failed. "), ex2);
          a.handle(Future.failedFuture(ex2));
        });
      }));
      CompositeFuture.all(futures1).onSuccess(a -> {
        CompositeFuture.all(futures2).onSuccess(b -> {
          promise.complete();
        }).onFailure(ex -> {
          LOG.error(String.format("sqlDELETEFilterHost failed. "), ex);
          promise.tryFail(ex);
        });
      }).onFailure(ex -> {
        LOG.error(String.format("sqlDELETEFilterHost failed. "), ex);
        promise.tryFail(ex);
      });
    } catch(Exception ex) {
      LOG.error(String.format("sqlDELETEFilterHost failed. "), ex);
      promise.tryFail(ex);
    }
    return promise.future();
  }

  public Future<ServiceResponse> response200DELETEFilterHost(SiteRequest siteRequest) {
    Promise<ServiceResponse> promise = Promise.promise();
    try {
      JsonObject json = new JsonObject();
      promise.complete(ServiceResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily())));
    } catch(Exception ex) {
      LOG.error(String.format("response200DELETEFilterHost failed. "), ex);
      promise.tryFail(ex);
    }
    return promise.future();
  }

  // General //

  public Future<Host> createHost(SiteRequest siteRequest) {
    Promise<Host> promise = Promise.promise();
    try {
      SqlConnection sqlConnection = siteRequest.getSqlConnection();
      String userId = siteRequest.getUserId();
      Long userKey = siteRequest.getUserKey();
      ZonedDateTime created = Optional.ofNullable(siteRequest.getJsonObject()).map(j -> j.getString("created")).map(s -> ZonedDateTime.parse(s, ComputateZonedDateTimeSerializer.ZONED_DATE_TIME_FORMATTER.withZone(ZoneId.of(config.getString(ConfigKeys.SITE_ZONE))))).orElse(ZonedDateTime.now(ZoneId.of(config.getString(ConfigKeys.SITE_ZONE))));

      sqlConnection.preparedQuery("INSERT INTO Host(created, userKey) VALUES($1, $2) RETURNING pk")
          .collecting(Collectors.toList())
          .execute(Tuple.of(created.toOffsetDateTime(), userKey)).onSuccess(result -> {
        Row createLine = result.value().stream().findFirst().orElseGet(() -> null);
        Long pk = createLine.getLong(0);
        Host o = new Host();
        o.setPk(pk);
        o.setSiteRequest_(siteRequest);
        promise.complete(o);
      }).onFailure(ex -> {
        RuntimeException ex2 = new RuntimeException(ex);
        LOG.error("createHost failed. ", ex2);
        promise.tryFail(ex2);
      });
    } catch(Exception ex) {
      LOG.error(String.format("createHost failed. "), ex);
      promise.tryFail(ex);
    }
    return promise.future();
  }

  public void searchHostQ(SearchList<Host> searchList, String entityVar, String valueIndexed, String varIndexed) {
    searchList.q(varIndexed + ":" + ("*".equals(valueIndexed) ? valueIndexed : SearchTool.escapeQueryChars(valueIndexed)));
    if(!"*".equals(entityVar)) {
    }
  }

  public String searchHostFq(SearchList<Host> searchList, String entityVar, String valueIndexed, String varIndexed) {
    if(varIndexed == null)
      throw new RuntimeException(String.format("\"%s\" is not an indexed entity. ", entityVar));
    if(StringUtils.startsWith(valueIndexed, "[")) {
      String[] fqs = StringUtils.substringAfter(StringUtils.substringBeforeLast(valueIndexed, "]"), "[").split(" TO ");
      if(fqs.length != 2)
        throw new RuntimeException(String.format("\"%s\" invalid range query. ", valueIndexed));
      String fq1 = fqs[0].equals("*") ? fqs[0] : Host.staticSearchFqForClass(entityVar, searchList.getSiteRequest_(SiteRequest.class), fqs[0]);
      String fq2 = fqs[1].equals("*") ? fqs[1] : Host.staticSearchFqForClass(entityVar, searchList.getSiteRequest_(SiteRequest.class), fqs[1]);
       return varIndexed + ":[" + fq1 + " TO " + fq2 + "]";
    } else {
      return varIndexed + ":" + SearchTool.escapeQueryChars(Host.staticSearchFqForClass(entityVar, searchList.getSiteRequest_(SiteRequest.class), valueIndexed)).replace("\\", "\\\\");
    }
  }

  public void searchHostSort(SearchList<Host> searchList, String entityVar, String valueIndexed, String varIndexed) {
    if(varIndexed == null)
      throw new RuntimeException(String.format("\"%s\" is not an indexed entity. ", entityVar));
    searchList.sort(varIndexed, valueIndexed);
  }

  public void searchHostRows(SearchList<Host> searchList, Long valueRows) {
      searchList.rows(valueRows != null ? valueRows : 10L);
  }

  public void searchHostStart(SearchList<Host> searchList, Long valueStart) {
    searchList.start(valueStart);
  }

  public void searchHostVar(SearchList<Host> searchList, String var, String value) {
    searchList.getSiteRequest_(SiteRequest.class).getRequestVars().put(var, value);
  }

  public void searchHostUri(SearchList<Host> searchList) {
  }

  public Future<ServiceResponse> varsHost(SiteRequest siteRequest) {
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
          LOG.error(String.format("searchHost failed. "), ex);
          promise.tryFail(ex);
        }
      });
      promise.complete();
    } catch(Exception ex) {
      LOG.error(String.format("searchHost failed. "), ex);
      promise.tryFail(ex);
    }
    return promise.future();
  }

  public Future<SearchList<Host>> searchHostList(SiteRequest siteRequest, Boolean populate, Boolean store, Boolean modify, String scope) {
    Promise<SearchList<Host>> promise = Promise.promise();
    try {
      ServiceRequest serviceRequest = siteRequest.getServiceRequest();
      String entityListStr = siteRequest.getServiceRequest().getParams().getJsonObject("query").getString("fl");
      String[] entityList = entityListStr == null ? null : entityListStr.split(",\\s*");
      SearchList<Host> searchList = new SearchList<Host>();
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
      searchList.setC(Host.class);
      searchList.setSiteRequest_(siteRequest);
      searchList.facetMinCount(1);
      if(entityList != null) {
        for(String v : entityList) {
          searchList.fl(Host.varIndexedHost(v));
        }
      }

      String hostResource = serviceRequest.getParams().getJsonObject("path").getString("hostResource");
      if(hostResource != null) {
        searchList.fq("hostResource_docvalues_string:" + SearchTool.escapeQueryChars(hostResource));
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
                varsIndexed[i] = Host.varIndexedHost(entityVar);
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
                  varIndexed = Host.varIndexedHost(entityVar);
                  String entityQ = searchHostFq(searchList, entityVar, valueIndexed, varIndexed);
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
                  varIndexed = Host.varIndexedHost(entityVar);
                  String entityFq = searchHostFq(searchList, entityVar, valueIndexed, varIndexed);
                  mFq.appendReplacement(sb, entityFq);
                }
                if(!sb.isEmpty()) {
                  mFq.appendTail(sb);
                  searchList.fq(sb.toString());
                }
              } else if(paramName.equals("sort")) {
                entityVar = StringUtils.trim(StringUtils.substringBefore((String)paramObject, " "));
                valueIndexed = StringUtils.trim(StringUtils.substringAfter((String)paramObject, " "));
                varIndexed = Host.varIndexedHost(entityVar);
                searchHostSort(searchList, entityVar, valueIndexed, varIndexed);
              } else if(paramName.equals("start")) {
                valueStart = paramObject instanceof Long ? (Long)paramObject : Long.parseLong(paramObject.toString());
                searchHostStart(searchList, valueStart);
              } else if(paramName.equals("rows")) {
                valueRows = paramObject instanceof Long ? (Long)paramObject : Long.parseLong(paramObject.toString());
                searchHostRows(searchList, valueRows);
              } else if(paramName.equals("stats")) {
                searchList.stats((Boolean)paramObject);
              } else if(paramName.equals("stats.field")) {
                Matcher mStats = Pattern.compile("(?:(\\{![^\\}]+\\}))?(.*)").matcher((String)paramObject);
                if(mStats.find()) {
                  String solrLocalParams = mStats.group(1);
                  entityVar = mStats.group(2).trim();
                  varIndexed = Host.varIndexedHost(entityVar);
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
                  varIndexed = Host.varIndexedHost(entityVar);
                  searchList.facetRange((solrLocalParams == null ? "" : solrLocalParams) + varIndexed);
                  facetRange = entityVar;
                }
              } else if(paramName.equals("facet.field")) {
                entityVar = (String)paramObject;
                varIndexed = Host.varIndexedHost(entityVar);
                if(varIndexed != null)
                  searchList.facetField(varIndexed);
              } else if(paramName.equals("var")) {
                entityVar = StringUtils.trim(StringUtils.substringBefore((String)paramObject, ":"));
                valueIndexed = URLDecoder.decode(StringUtils.trim(StringUtils.substringAfter((String)paramObject, ":")), "UTF-8");
                searchHostVar(searchList, entityVar, valueIndexed);
              } else if(paramName.equals("cursorMark")) {
                valueCursorMark = (String)paramObject;
                searchList.cursorMark((String)paramObject);
              }
            }
            searchHostUri(searchList);
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
      searchHost2(siteRequest, populate, store, modify, searchList);
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
            LOG.error(String.format("searchHost failed. "), ex);
            promise.tryFail(ex);
          });
        } else {
          promise.complete(searchList);
        }
      }).onFailure(ex -> {
        LOG.error(String.format("searchHost failed. "), ex);
        promise.tryFail(ex);
      });
    } catch(Exception ex) {
      LOG.error(String.format("searchHost failed. "), ex);
      promise.tryFail(ex);
    }
    return promise.future();
  }
  public void searchHost2(SiteRequest siteRequest, Boolean populate, Boolean store, Boolean modify, SearchList<Host> searchList) {
  }

  public Future<Void> persistHost(Host o, Boolean patch) {
    Promise<Void> promise = Promise.promise();
    try {
      SiteRequest siteRequest = o.getSiteRequest_();
      SqlConnection sqlConnection = siteRequest.getSqlConnection();
      Long pk = o.getPk();
      sqlConnection.preparedQuery("SELECT tenantResource, inventoryResource, created, aapHostId, hostName, archived, ipAddress, hostId, hostResource, hostDescription, sessionId, aapInventoryId, userKey, inventoryName, eventSubscriptions, objectTitle, displayPage, editPage, userPage, download FROM Host WHERE pk=$1")
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
                  LOG.error(String.format("persistHost failed. "), e);
                }
              }
            }
          }
          o.promiseDeepForClass(siteRequest).onSuccess(a -> {
            promise.complete();
          }).onFailure(ex -> {
            LOG.error(String.format("persistHost failed. "), ex);
            promise.tryFail(ex);
          });
        } catch(Exception ex) {
          LOG.error(String.format("persistHost failed. "), ex);
          promise.tryFail(ex);
        }
      }).onFailure(ex -> {
        RuntimeException ex2 = new RuntimeException(ex);
        LOG.error(String.format("persistHost failed. "), ex2);
        promise.tryFail(ex2);
      });
    } catch(Exception ex) {
      LOG.error(String.format("persistHost failed. "), ex);
      promise.tryFail(ex);
    }
    return promise.future();
  }

  public Future<Void> relateHost(Host o) {
    Promise<Void> promise = Promise.promise();
    try {
      SiteRequest siteRequest = o.getSiteRequest_();
      SqlConnection sqlConnection = siteRequest.getSqlConnection();
      sqlConnection.preparedQuery("SELECT tenantResource as pk2, 'tenantResource' from Tenant where tenantResource=$1 UNION SELECT inventoryResource as pk2, 'inventoryResource' from HostInventory where inventoryResource=$2")
          .collecting(Collectors.toList())
          .execute(Tuple.of(o.getTenantResource(), o.getInventoryResource())
          ).onSuccess(result -> {
        try {
          if(result != null) {
            for(Row definition : result.value()) {
              o.relateForClass(definition.getString(1), definition.getValue(0));
            }
          }
          promise.complete();
        } catch(Exception ex) {
          LOG.error(String.format("relateHost failed. "), ex);
          promise.tryFail(ex);
        }
      }).onFailure(ex -> {
        RuntimeException ex2 = new RuntimeException(ex);
        LOG.error(String.format("relateHost failed. "), ex2);
        promise.tryFail(ex2);
      });
    } catch(Exception ex) {
      LOG.error(String.format("relateHost failed. "), ex);
      promise.tryFail(ex);
    }
    return promise.future();
  }

  public String searchVar(String varIndexed) {
    return Host.searchVarHost(varIndexed);
  }

  @Override
  public String getClassApiAddress() {
    return Host.CLASS_API_ADDRESS_Host;
  }

  public Future<Host> indexHost(Host o) {
    Promise<Host> promise = Promise.promise();
    try {
      SiteRequest siteRequest = o.getSiteRequest_();
      ApiRequest apiRequest = siteRequest.getApiRequest_();
      JsonObject json = new JsonObject();
      JsonObject add = new JsonObject();
      json.put("add", add);
      JsonObject doc = new JsonObject();
      add.put("doc", doc);
      o.indexHost(doc);
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
        LOG.error(String.format("indexHost failed. "), new RuntimeException(ex));
        promise.tryFail(ex);
      });
    } catch(Exception ex) {
      LOG.error(String.format("indexHost failed. "), ex);
      promise.tryFail(ex);
    }
    return promise.future();
  }

  public Future<Host> unindexHost(Host o) {
    Promise<Host> promise = Promise.promise();
    try {
      SiteRequest siteRequest = o.getSiteRequest_();
      ApiRequest apiRequest = siteRequest.getApiRequest_();
      o.promiseDeepForClass(siteRequest).onSuccess(a -> {
        JsonObject json = new JsonObject();
        JsonObject delete = new JsonObject();
        json.put("delete", delete);
        String query = String.format("filter(%s:%s)", Host.VAR_solrId, o.obtainForClass(Host.VAR_solrId));
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
          LOG.error(String.format("unindexHost failed. "), new RuntimeException(ex));
          promise.tryFail(ex);
        });
      }).onFailure(ex -> {
        LOG.error(String.format("unindexHost failed. "), ex);
        promise.tryFail(ex);
      });
    } catch(Exception ex) {
      LOG.error(String.format("unindexHost failed. "), ex);
      promise.tryFail(ex);
    }
    return promise.future();
  }

  public Future<Void> refreshHost(Host o) {
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

          if("Tenant".equals(classSimpleName2) && solrId2 != null) {
            SearchList<Tenant> searchList2 = new SearchList<Tenant>();
            searchList2.setStore(true);
            searchList2.q("*:*");
            searchList2.setC(Tenant.class);
            searchList2.fq("solrId:" + solrId2);
            searchList2.rows(1L);
            futures.add(Future.future(promise2 -> {
              searchList2.promiseDeepSearchList(siteRequest).onSuccess(b -> {
                Tenant o2 = searchList2.getList().stream().findFirst().orElse(null);
                if(o2 != null) {
                  JsonObject params = new JsonObject();
                  params.put("body", new JsonObject());
                  params.put("scopes", siteRequest.getScopes());
                  params.put("cookie", new JsonObject());
                  params.put("path", new JsonObject());
                  params.put("query", new JsonObject().put("q", "*:*").put("fq", new JsonArray().add("solrId:" + solrId2)).put("var", new JsonArray().add("refresh:false")));
                  JsonObject context = new JsonObject().put("params", params).put("user", siteRequest.getUserPrincipal());
                  JsonObject json = new JsonObject().put("context", context);
                  eventBus.request("dcm-enUS-Tenant", json, new DeliveryOptions().addHeader("action", "patchTenantFuture")).onSuccess(c -> {
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

          if("HostInventory".equals(classSimpleName2) && solrId2 != null) {
            SearchList<HostInventory> searchList2 = new SearchList<HostInventory>();
            searchList2.setStore(true);
            searchList2.q("*:*");
            searchList2.setC(HostInventory.class);
            searchList2.fq("solrId:" + solrId2);
            searchList2.rows(1L);
            futures.add(Future.future(promise2 -> {
              searchList2.promiseDeepSearchList(siteRequest).onSuccess(b -> {
                HostInventory o2 = searchList2.getList().stream().findFirst().orElse(null);
                if(o2 != null) {
                  JsonObject params = new JsonObject();
                  params.put("body", new JsonObject());
                  params.put("scopes", siteRequest.getScopes());
                  params.put("cookie", new JsonObject());
                  params.put("path", new JsonObject());
                  params.put("query", new JsonObject().put("q", "*:*").put("fq", new JsonArray().add("solrId:" + solrId2)).put("var", new JsonArray().add("refresh:false")));
                  JsonObject context = new JsonObject().put("params", params).put("user", siteRequest.getUserPrincipal());
                  JsonObject json = new JsonObject().put("context", context);
                  eventBus.request("dcm-enUS-HostInventory", json, new DeliveryOptions().addHeader("action", "patchHostInventoryFuture")).onSuccess(c -> {
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
          eventBus.request(Host.getClassApiAddress(), json, new DeliveryOptions().addHeader("action", "patchHostFuture")).onSuccess(c -> {
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
      LOG.error(String.format("refreshHost failed. "), ex);
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
      Host o = new Host();
      o.setSiteRequest_((SiteRequest)siteRequest);

      o.persistForClass(Host.VAR_tenantResource, Host.staticSetTenantResource(siteRequest2, (String)result.get(Host.VAR_tenantResource)));
      o.persistForClass(Host.VAR_inventoryResource, Host.staticSetInventoryResource(siteRequest2, (String)result.get(Host.VAR_inventoryResource)));
      o.persistForClass(Host.VAR_created, Host.staticSetCreated(siteRequest2, (String)result.get(Host.VAR_created), Optional.ofNullable(siteRequest).map(r -> r.getConfig()).map(config -> config.getString(ConfigKeys.SITE_ZONE)).map(z -> ZoneId.of(z)).orElse(ZoneId.of("UTC"))));
      o.persistForClass(Host.VAR_aapHostId, Host.staticSetAapHostId(siteRequest2, (String)result.get(Host.VAR_aapHostId)));
      o.persistForClass(Host.VAR_hostName, Host.staticSetHostName(siteRequest2, (String)result.get(Host.VAR_hostName)));
      o.persistForClass(Host.VAR_archived, Host.staticSetArchived(siteRequest2, (String)result.get(Host.VAR_archived)));
      o.persistForClass(Host.VAR_ipAddress, Host.staticSetIpAddress(siteRequest2, (String)result.get(Host.VAR_ipAddress)));
      o.persistForClass(Host.VAR_hostId, Host.staticSetHostId(siteRequest2, (String)result.get(Host.VAR_hostId)));
      o.persistForClass(Host.VAR_hostResource, Host.staticSetHostResource(siteRequest2, (String)result.get(Host.VAR_hostResource)));
      o.persistForClass(Host.VAR_hostDescription, Host.staticSetHostDescription(siteRequest2, (String)result.get(Host.VAR_hostDescription)));
      o.persistForClass(Host.VAR_sessionId, Host.staticSetSessionId(siteRequest2, (String)result.get(Host.VAR_sessionId)));
      o.persistForClass(Host.VAR_aapInventoryId, Host.staticSetAapInventoryId(siteRequest2, (String)result.get(Host.VAR_aapInventoryId)));
      o.persistForClass(Host.VAR_userKey, Host.staticSetUserKey(siteRequest2, (String)result.get(Host.VAR_userKey)));
      o.persistForClass(Host.VAR_inventoryName, Host.staticSetInventoryName(siteRequest2, (String)result.get(Host.VAR_inventoryName)));
      o.persistForClass(Host.VAR_eventSubscriptions, Host.staticSetEventSubscriptions(siteRequest2, (String)result.get(Host.VAR_eventSubscriptions)));
      o.persistForClass(Host.VAR_objectTitle, Host.staticSetObjectTitle(siteRequest2, (String)result.get(Host.VAR_objectTitle)));
      o.persistForClass(Host.VAR_displayPage, Host.staticSetDisplayPage(siteRequest2, (String)result.get(Host.VAR_displayPage)));
      o.persistForClass(Host.VAR_editPage, Host.staticSetEditPage(siteRequest2, (String)result.get(Host.VAR_editPage)));
      o.persistForClass(Host.VAR_userPage, Host.staticSetUserPage(siteRequest2, (String)result.get(Host.VAR_userPage)));
      o.persistForClass(Host.VAR_download, Host.staticSetDownload(siteRequest2, (String)result.get(Host.VAR_download)));

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
