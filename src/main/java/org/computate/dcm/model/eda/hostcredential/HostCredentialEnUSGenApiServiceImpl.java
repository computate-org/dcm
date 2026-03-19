package org.computate.dcm.model.eda.hostcredential;

import org.computate.dcm.model.eda.tenant.TenantEnUSApiServiceImpl;
import org.computate.dcm.model.eda.tenant.Tenant;
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
import org.computate.dcm.model.eda.hostcredential.HostCredentialPage;


/**
 * Translate: false
 * Generated: true
 **/
public class HostCredentialEnUSGenApiServiceImpl extends BaseApiServiceImpl implements HostCredentialEnUSGenApiService {

  protected static final Logger LOG = LoggerFactory.getLogger(HostCredentialEnUSGenApiServiceImpl.class);

  // Search //

  @Override
  public void searchHostCredential(ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler) {
    Boolean classPublicRead = false;
    user(serviceRequest, SiteRequest.class, SiteUser.class, SiteUser.getClassApiAddress(), "postSiteUserFuture", "patchSiteUserFuture", classPublicRead).onSuccess(siteRequest -> {
      try {
        siteRequest.setLang("enUS");
        String credentialResource = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("credentialResource");
        String HOSTCREDENTIAL = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("HOSTCREDENTIAL");
        List<String> groups = Optional.ofNullable(siteRequest.getGroups()).orElse(new ArrayList<>());
        MultiMap form = MultiMap.caseInsensitiveMultiMap();
        form.add("grant_type", "urn:ietf:params:oauth:grant-type:uma-ticket");
        form.add("audience", config.getString(ComputateConfigKeys.AUTH_CLIENT));
        form.add("response_mode", "permissions");
        form.add("permission", String.format("%s#%s", HostCredential.CLASS_AUTH_RESOURCE, "GET"));
        form.add("permission", String.format("%s#%s", HostCredential.CLASS_AUTH_RESOURCE, "POST"));
        form.add("permission", String.format("%s#%s", HostCredential.CLASS_AUTH_RESOURCE, "PATCH"));
        form.add("permission", String.format("%s#%s", HostCredential.CLASS_AUTH_RESOURCE, "PUT"));
        form.add("permission", String.format("%s#%s", HostCredential.CLASS_AUTH_RESOURCE, "DELETE"));
        form.add("permission", String.format("%s#%s", HostCredential.CLASS_AUTH_RESOURCE, "Admin"));
        form.add("permission", String.format("%s#%s", HostCredential.CLASS_AUTH_RESOURCE, "SuperAdmin"));
        if(credentialResource != null)
          form.add("permission", String.format("%s-%s#%s", HostCredential.CLASS_AUTH_RESOURCE, credentialResource, "GET"));
        groups.stream().map(group -> {
              Matcher mPermission = Pattern.compile("^/(.*-?TENANT-([a-z0-9\\-]+))-(\\w+)$").matcher(group);
              return mPermission.find() ? mPermission : null;
            }).filter(v -> v != null).forEach(mPermission -> {
              form.add("permission", String.format("%s#%s", mPermission.group(1), mPermission.group(3)));
            });
        groups.stream().map(group -> {
              Matcher mPermission = Pattern.compile("^/(.*-?HOSTINVENTORY-([a-z0-9\\-]+))-(\\w+)$").matcher(group);
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
            JsonArray scopes = authorizationDecisionBody.stream().map(o -> (JsonObject)o).filter(o -> "HOSTCREDENTIAL".equals(o.getString("rsname"))).findFirst().map(decision -> ((JsonObject)decision).getJsonArray("scopes")).orElse(new JsonArray());
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
              authorizationDecisionBody.stream().map(o -> (JsonObject)o).filter(permission -> {
                    Matcher mPermission = Pattern.compile("^(.*-?HOSTINVENTORY-([a-z0-9\\-]+))$").matcher(permission.getString("rsname"));
                    return permission.getJsonArray("scopes").contains("GET")
                        && mPermission.find();
                  }).forEach(permission -> {
                    fqs.add(String.format("%s:%s", "credentialResource", permission.getString("rsname")));
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
              searchHostCredentialList(siteRequest, false, true, false, "GET").onSuccess(listHostCredential -> {
                response200SearchHostCredential(listHostCredential).onSuccess(response -> {
                  eventHandler.handle(Future.succeededFuture(response));
                  LOG.debug(String.format("searchHostCredential succeeded. "));
                }).onFailure(ex -> {
                  LOG.error(String.format("searchHostCredential failed. "), ex);
                  error(siteRequest, eventHandler, ex);
                });
              }).onFailure(ex -> {
                LOG.error(String.format("searchHostCredential failed. "), ex);
                error(siteRequest, eventHandler, ex);
              });
            }
          } catch(Exception ex) {
            LOG.error(String.format("searchHostCredential failed. "), ex);
            error(null, eventHandler, ex);
          }
        });
      } catch(Exception ex) {
        LOG.error(String.format("searchHostCredential failed. "), ex);
        error(null, eventHandler, ex);
      }
    }).onFailure(ex -> {
      if("Inactive Token".equals(ex.getMessage()) || StringUtils.startsWith(ex.getMessage(), "invalid_grant:")) {
        try {
          eventHandler.handle(Future.succeededFuture(new ServiceResponse(302, "Found", null, MultiMap.caseInsensitiveMultiMap().add(HttpHeaders.LOCATION, "/logout?redirect_uri=" + URLEncoder.encode(serviceRequest.getExtra().getString("uri"), "UTF-8")))));
        } catch(Exception ex2) {
          LOG.error(String.format("searchHostCredential failed. ", ex2));
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
        LOG.error(String.format("searchHostCredential failed. "), ex);
        error(null, eventHandler, ex);
      }
    });
  }

  public Future<ServiceResponse> response200SearchHostCredential(SearchList<HostCredential> listHostCredential) {
    Promise<ServiceResponse> promise = Promise.promise();
    try {
      SiteRequest siteRequest = listHostCredential.getSiteRequest_(SiteRequest.class);
      List<String> fls = listHostCredential.getRequest().getFields();
      JsonObject json = new JsonObject();
      JsonArray l = new JsonArray();
      List<String> scopes = siteRequest.getScopes();
      listHostCredential.getList().stream().forEach(o -> {
        JsonObject json2 = JsonObject.mapFrom(o);
        if(fls.size() > 0) {
          Set<String> fieldNames = new HashSet<String>();
          for(String fieldName : json2.fieldNames()) {
            String v = HostCredential.varIndexedHostCredential(fieldName);
            if(v != null)
              fieldNames.add(HostCredential.varIndexedHostCredential(fieldName));
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
      response200Search(listHostCredential.getRequest(), listHostCredential.getResponse(), json);
      promise.complete(ServiceResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily())));
    } catch(Exception ex) {
      LOG.error(String.format("response200SearchHostCredential failed. "), ex);
      promise.tryFail(ex);
    }
    return promise.future();
  }
  public void responsePivotSearchHostCredential(List<SolrResponse.Pivot> pivots, JsonArray pivotArray) {
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
          responsePivotSearchHostCredential(pivotFields2, pivotArray2);
        }
      }
    }
  }

  // GET //

  @Override
  public void getHostCredential(ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler) {
    Boolean classPublicRead = false;
    user(serviceRequest, SiteRequest.class, SiteUser.class, SiteUser.getClassApiAddress(), "postSiteUserFuture", "patchSiteUserFuture", classPublicRead).onSuccess(siteRequest -> {
      try {
        siteRequest.setLang("enUS");
        String credentialResource = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("credentialResource");
        String HOSTCREDENTIAL = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("HOSTCREDENTIAL");
        List<String> groups = Optional.ofNullable(siteRequest.getGroups()).orElse(new ArrayList<>());
        MultiMap form = MultiMap.caseInsensitiveMultiMap();
        form.add("grant_type", "urn:ietf:params:oauth:grant-type:uma-ticket");
        form.add("audience", config.getString(ComputateConfigKeys.AUTH_CLIENT));
        form.add("response_mode", "permissions");
        form.add("permission", String.format("%s#%s", HostCredential.CLASS_AUTH_RESOURCE, "GET"));
        form.add("permission", String.format("%s#%s", HostCredential.CLASS_AUTH_RESOURCE, "POST"));
        form.add("permission", String.format("%s#%s", HostCredential.CLASS_AUTH_RESOURCE, "PATCH"));
        form.add("permission", String.format("%s#%s", HostCredential.CLASS_AUTH_RESOURCE, "PUT"));
        form.add("permission", String.format("%s#%s", HostCredential.CLASS_AUTH_RESOURCE, "DELETE"));
        form.add("permission", String.format("%s#%s", HostCredential.CLASS_AUTH_RESOURCE, "Admin"));
        form.add("permission", String.format("%s#%s", HostCredential.CLASS_AUTH_RESOURCE, "SuperAdmin"));
        if(credentialResource != null)
          form.add("permission", String.format("%s-%s#%s", HostCredential.CLASS_AUTH_RESOURCE, credentialResource, "GET"));
        groups.stream().map(group -> {
              Matcher mPermission = Pattern.compile("^/(.*-?TENANT-([a-z0-9\\-]+))-(\\w+)$").matcher(group);
              return mPermission.find() ? mPermission : null;
            }).filter(v -> v != null).forEach(mPermission -> {
              form.add("permission", String.format("%s#%s", mPermission.group(1), mPermission.group(3)));
            });
        groups.stream().map(group -> {
              Matcher mPermission = Pattern.compile("^/(.*-?HOSTINVENTORY-([a-z0-9\\-]+))-(\\w+)$").matcher(group);
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
            JsonArray scopes = authorizationDecisionBody.stream().map(o -> (JsonObject)o).filter(o -> "HOSTCREDENTIAL".equals(o.getString("rsname"))).findFirst().map(decision -> ((JsonObject)decision).getJsonArray("scopes")).orElse(new JsonArray());
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
              authorizationDecisionBody.stream().map(o -> (JsonObject)o).filter(permission -> {
                    Matcher mPermission = Pattern.compile("^(.*-?HOSTINVENTORY-([a-z0-9\\-]+))$").matcher(permission.getString("rsname"));
                    return permission.getJsonArray("scopes").contains("GET")
                        && mPermission.find();
                  }).forEach(permission -> {
                    fqs.add(String.format("%s:%s", "credentialResource", permission.getString("rsname")));
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
              searchHostCredentialList(siteRequest, false, true, false, "GET").onSuccess(listHostCredential -> {
                response200GETHostCredential(listHostCredential).onSuccess(response -> {
                  eventHandler.handle(Future.succeededFuture(response));
                  LOG.debug(String.format("getHostCredential succeeded. "));
                }).onFailure(ex -> {
                  LOG.error(String.format("getHostCredential failed. "), ex);
                  error(siteRequest, eventHandler, ex);
                });
              }).onFailure(ex -> {
                LOG.error(String.format("getHostCredential failed. "), ex);
                error(siteRequest, eventHandler, ex);
              });
            }
          } catch(Exception ex) {
            LOG.error(String.format("getHostCredential failed. "), ex);
            error(null, eventHandler, ex);
          }
        });
      } catch(Exception ex) {
        LOG.error(String.format("getHostCredential failed. "), ex);
        error(null, eventHandler, ex);
      }
    }).onFailure(ex -> {
      if("Inactive Token".equals(ex.getMessage()) || StringUtils.startsWith(ex.getMessage(), "invalid_grant:")) {
        try {
          eventHandler.handle(Future.succeededFuture(new ServiceResponse(302, "Found", null, MultiMap.caseInsensitiveMultiMap().add(HttpHeaders.LOCATION, "/logout?redirect_uri=" + URLEncoder.encode(serviceRequest.getExtra().getString("uri"), "UTF-8")))));
        } catch(Exception ex2) {
          LOG.error(String.format("getHostCredential failed. ", ex2));
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
        LOG.error(String.format("getHostCredential failed. "), ex);
        error(null, eventHandler, ex);
      }
    });
  }

  public Future<ServiceResponse> response200GETHostCredential(SearchList<HostCredential> listHostCredential) {
    Promise<ServiceResponse> promise = Promise.promise();
    try {
      SiteRequest siteRequest = listHostCredential.getSiteRequest_(SiteRequest.class);
      JsonObject json = JsonObject.mapFrom(listHostCredential.getList().stream().findFirst().orElse(null));
      promise.complete(ServiceResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily())));
    } catch(Exception ex) {
      LOG.error(String.format("response200GETHostCredential failed. "), ex);
      promise.tryFail(ex);
    }
    return promise.future();
  }

  // PATCH //

  @Override
  public void patchHostCredential(JsonObject body, ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler) {
    LOG.debug(String.format("patchHostCredential started. "));
    Boolean classPublicRead = false;
    user(serviceRequest, SiteRequest.class, SiteUser.class, SiteUser.getClassApiAddress(), "postSiteUserFuture", "patchSiteUserFuture", classPublicRead).onSuccess(siteRequest -> {
      try {
        siteRequest.setLang("enUS");
        String credentialResource = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("credentialResource");
        String HOSTCREDENTIAL = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("HOSTCREDENTIAL");
        List<String> groups = Optional.ofNullable(siteRequest.getGroups()).orElse(new ArrayList<>());
        MultiMap form = MultiMap.caseInsensitiveMultiMap();
        form.add("grant_type", "urn:ietf:params:oauth:grant-type:uma-ticket");
        form.add("audience", config.getString(ComputateConfigKeys.AUTH_CLIENT));
        form.add("response_mode", "permissions");
        form.add("permission", String.format("%s#%s", HostCredential.CLASS_AUTH_RESOURCE, "GET"));
        form.add("permission", String.format("%s#%s", HostCredential.CLASS_AUTH_RESOURCE, "POST"));
        form.add("permission", String.format("%s#%s", HostCredential.CLASS_AUTH_RESOURCE, "PATCH"));
        form.add("permission", String.format("%s#%s", HostCredential.CLASS_AUTH_RESOURCE, "PUT"));
        form.add("permission", String.format("%s#%s", HostCredential.CLASS_AUTH_RESOURCE, "DELETE"));
        form.add("permission", String.format("%s#%s", HostCredential.CLASS_AUTH_RESOURCE, "Admin"));
        form.add("permission", String.format("%s#%s", HostCredential.CLASS_AUTH_RESOURCE, "SuperAdmin"));
        if(credentialResource != null)
          form.add("permission", String.format("%s-%s#%s", HostCredential.CLASS_AUTH_RESOURCE, credentialResource, "PATCH"));
        groups.stream().map(group -> {
              Matcher mPermission = Pattern.compile("^/(.*-?TENANT-([a-z0-9\\-]+))-(\\w+)$").matcher(group);
              return mPermission.find() ? mPermission : null;
            }).filter(v -> v != null).forEach(mPermission -> {
              form.add("permission", String.format("%s#%s", mPermission.group(1), mPermission.group(3)));
            });
        groups.stream().map(group -> {
              Matcher mPermission = Pattern.compile("^/(.*-?HOSTINVENTORY-([a-z0-9\\-]+))-(\\w+)$").matcher(group);
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
            JsonArray scopes = authorizationDecisionBody.stream().map(o -> (JsonObject)o).filter(o -> "HOSTCREDENTIAL".equals(o.getString("rsname"))).findFirst().map(decision -> ((JsonObject)decision).getJsonArray("scopes")).orElse(new JsonArray());
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
            authorizationDecisionBody.stream().map(o -> (JsonObject)o).filter(permission -> {
                  Matcher mPermission = Pattern.compile("^(.*-?HOSTINVENTORY-([a-z0-9\\-]+))$").matcher(permission.getString("rsname"));
                  return permission.getJsonArray("scopes").contains("PATCH")
                      && mPermission.find();
                }).forEach(permission -> {
                  fqs.add(String.format("%s:%s", "credentialResource", permission.getString("rsname")));
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
              searchHostCredentialList(siteRequest, false, true, true, "PATCH").onSuccess(listHostCredential -> {
                try {
                  ApiRequest apiRequest = new ApiRequest();
                  apiRequest.setRows(listHostCredential.getRequest().getRows());
                  apiRequest.setNumFound(listHostCredential.getResponse().getResponse().getNumFound());
                  apiRequest.setNumPATCH(0L);
                  apiRequest.initDeepApiRequest(siteRequest);
                  siteRequest.setApiRequest_(apiRequest);
                  if(apiRequest.getNumFound() == 1L)
                    apiRequest.setOriginal(listHostCredential.first());
                  apiRequest.setId(Optional.ofNullable(listHostCredential.first()).map(o2 -> o2.getCredentialResource().toString()).orElse(null));
                  apiRequest.setSolrId(Optional.ofNullable(listHostCredential.first()).map(o2 -> o2.getSolrId()).orElse(null));
                  eventBus.publish("websocketHostCredential", JsonObject.mapFrom(apiRequest).toString());

                  listPATCHHostCredential(apiRequest, listHostCredential).onSuccess(e -> {
                    response200PATCHHostCredential(siteRequest).onSuccess(response -> {
                      LOG.debug(String.format("patchHostCredential succeeded. "));
                      eventHandler.handle(Future.succeededFuture(response));
                    }).onFailure(ex -> {
                      LOG.error(String.format("patchHostCredential failed. "), ex);
                      error(siteRequest, eventHandler, ex);
                    });
                  }).onFailure(ex -> {
                    LOG.error(String.format("patchHostCredential failed. "), ex);
                    error(siteRequest, eventHandler, ex);
                  });
                } catch(Exception ex) {
                  LOG.error(String.format("patchHostCredential failed. "), ex);
                  error(siteRequest, eventHandler, ex);
                }
              }).onFailure(ex -> {
                LOG.error(String.format("patchHostCredential failed. "), ex);
                error(siteRequest, eventHandler, ex);
              });
            }
          } catch(Exception ex) {
            LOG.error(String.format("patchHostCredential failed. "), ex);
            error(null, eventHandler, ex);
          }
        });
      } catch(Exception ex) {
        LOG.error(String.format("patchHostCredential failed. "), ex);
        error(null, eventHandler, ex);
      }
    }).onFailure(ex -> {
      if("Inactive Token".equals(ex.getMessage()) || StringUtils.startsWith(ex.getMessage(), "invalid_grant:")) {
        try {
          eventHandler.handle(Future.succeededFuture(new ServiceResponse(302, "Found", null, MultiMap.caseInsensitiveMultiMap().add(HttpHeaders.LOCATION, "/logout?redirect_uri=" + URLEncoder.encode(serviceRequest.getExtra().getString("uri"), "UTF-8")))));
        } catch(Exception ex2) {
          LOG.error(String.format("patchHostCredential failed. ", ex2));
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
        LOG.error(String.format("patchHostCredential failed. "), ex);
        error(null, eventHandler, ex);
      }
    });
  }

  public Future<Void> listPATCHHostCredential(ApiRequest apiRequest, SearchList<HostCredential> listHostCredential) {
    Promise<Void> promise = Promise.promise();
    List<Future> futures = new ArrayList<>();
    SiteRequest siteRequest = listHostCredential.getSiteRequest_(SiteRequest.class);
    listHostCredential.getList().forEach(o -> {
      SiteRequest siteRequest2 = generateSiteRequest(siteRequest.getUser(), siteRequest.getUserPrincipal(), siteRequest.getServiceRequest(), siteRequest.getJsonObject(), SiteRequest.class);
      siteRequest2.setScopes(siteRequest.getScopes());
      o.setSiteRequest_(siteRequest2);
      siteRequest2.setApiRequest_(siteRequest.getApiRequest_());
      JsonObject jsonObject = JsonObject.mapFrom(o);
      HostCredential o2 = jsonObject.mapTo(HostCredential.class);
      o2.setSiteRequest_(siteRequest2);
      futures.add(Future.future(promise1 -> {
        patchHostCredentialFuture(o2, false).onSuccess(a -> {
          promise1.complete();
        }).onFailure(ex -> {
          LOG.error(String.format("listPATCHHostCredential failed. "), ex);
          promise1.tryFail(ex);
        });
      }));
    });
    CompositeFuture.all(futures).onSuccess( a -> {
      listHostCredential.next().onSuccess(next -> {
        if(next) {
          listPATCHHostCredential(apiRequest, listHostCredential).onSuccess(b -> {
            promise.complete();
          }).onFailure(ex -> {
            LOG.error(String.format("listPATCHHostCredential failed. "), ex);
            promise.tryFail(ex);
          });
        } else {
          promise.complete();
        }
      }).onFailure(ex -> {
        LOG.error(String.format("listPATCHHostCredential failed. "), ex);
        promise.tryFail(ex);
      });
    }).onFailure(ex -> {
      LOG.error(String.format("listPATCHHostCredential failed. "), ex);
      promise.tryFail(ex);
    });
    return promise.future();
  }

  @Override
  public void patchHostCredentialFuture(JsonObject body, ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler) {
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
        searchHostCredentialList(siteRequest, false, true, true, "PATCH").onSuccess(listHostCredential -> {
          try {
            HostCredential o = listHostCredential.first();
            ApiRequest apiRequest = new ApiRequest();
            apiRequest.setRows(1L);
            apiRequest.setNumFound(1L);
            apiRequest.setNumPATCH(0L);
            apiRequest.initDeepApiRequest(siteRequest);
            siteRequest.setApiRequest_(apiRequest);
            if(Optional.ofNullable(serviceRequest.getParams()).map(p -> p.getJsonObject("query")).map( q -> q.getJsonArray("var")).orElse(new JsonArray()).stream().filter(s -> "refresh:false".equals(s)).count() > 0L) {
              siteRequest.getRequestVars().put( "refresh", "false" );
            }
            HostCredential o2;
            if(o != null) {
              if(apiRequest.getNumFound() == 1L)
                apiRequest.setOriginal(o);
              apiRequest.setId(Optional.ofNullable(listHostCredential.first()).map(o3 -> o3.getCredentialResource().toString()).orElse(null));
              apiRequest.setSolrId(Optional.ofNullable(listHostCredential.first()).map(o3 -> o3.getSolrId()).orElse(null));
              JsonObject jsonObject = JsonObject.mapFrom(o);
              o2 = jsonObject.mapTo(HostCredential.class);
              o2.setSiteRequest_(siteRequest);
              patchHostCredentialFuture(o2, false).onSuccess(o3 -> {
                eventHandler.handle(Future.succeededFuture(ServiceResponse.completedWithJson(Buffer.buffer(new JsonObject().encodePrettily()))));
              }).onFailure(ex -> {
                eventHandler.handle(Future.failedFuture(ex));
              });
            } else {
              String m = String.format("%s %s not found", "host credential", null);
              eventHandler.handle(Future.failedFuture(m));
            }
          } catch(Exception ex) {
            LOG.error(String.format("patchHostCredential failed. "), ex);
            error(siteRequest, eventHandler, ex);
          }
        }).onFailure(ex -> {
          LOG.error(String.format("patchHostCredential failed. "), ex);
          error(siteRequest, eventHandler, ex);
        });
      } catch(Exception ex) {
        LOG.error(String.format("patchHostCredential failed. "), ex);
        error(null, eventHandler, ex);
      }
    }).onFailure(ex -> {
      LOG.error(String.format("patchHostCredential failed. "), ex);
      error(null, eventHandler, ex);
    });
  }

  public Future<HostCredential> patchHostCredentialFuture(HostCredential o, Boolean inheritPrimaryKey) {
    SiteRequest siteRequest = o.getSiteRequest_();
    Promise<HostCredential> promise = Promise.promise();

    try {
      ApiRequest apiRequest = siteRequest.getApiRequest_();
      Promise<HostCredential> promise1 = Promise.promise();
      pgPool.withTransaction(sqlConnection -> {
        siteRequest.setSqlConnection(sqlConnection);
        varsHostCredential(siteRequest).onSuccess(a -> {
          sqlPATCHHostCredential(o, inheritPrimaryKey).onSuccess(hostCredential -> {
            persistHostCredential(hostCredential, true).onSuccess(c -> {
              relateHostCredential(hostCredential).onSuccess(d -> {
                indexHostCredential(hostCredential).onSuccess(o2 -> {
                  if(apiRequest != null) {
                    apiRequest.setNumPATCH(apiRequest.getNumPATCH() + 1);
                    if(apiRequest.getNumFound() == 1L && Optional.ofNullable(siteRequest.getJsonObject()).map(json -> json.size() > 0).orElse(false)) {
                      o2.apiRequestHostCredential();
                      if(apiRequest.getVars().size() > 0 && Optional.ofNullable(siteRequest.getRequestVars().get("refresh")).map(refresh -> !refresh.equals("false")).orElse(true))
                        eventBus.publish("websocketHostCredential", JsonObject.mapFrom(apiRequest).toString());
                    }
                  }
                  promise1.complete(hostCredential);
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
      }).compose(hostCredential -> {
        Promise<HostCredential> promise2 = Promise.promise();
        refreshHostCredential(hostCredential).onSuccess(a -> {
          promise2.complete(hostCredential);
        }).onFailure(ex -> {
          promise2.tryFail(ex);
        });
        return promise2.future();
      }).onSuccess(hostCredential -> {
        promise.complete(hostCredential);
      }).onFailure(ex -> {
        promise.tryFail(ex);
      });
    } catch(Exception ex) {
      LOG.error(String.format("patchHostCredentialFuture failed. "), ex);
      promise.tryFail(ex);
    }
    return promise.future();
  }

  public Future<HostCredential> sqlPATCHHostCredential(HostCredential o, Boolean inheritPrimaryKey) {
    Promise<HostCredential> promise = Promise.promise();
    try {
      SiteRequest siteRequest = o.getSiteRequest_();
      ApiRequest apiRequest = siteRequest.getApiRequest_();
      List<String> solrIds = Optional.ofNullable(apiRequest).map(r -> r.getSolrIds()).orElse(new ArrayList<>());
      List<String> classes = Optional.ofNullable(apiRequest).map(r -> r.getClasses()).orElse(new ArrayList<>());
      SqlConnection sqlConnection = siteRequest.getSqlConnection();
      Integer num = 1;
      StringBuilder bSql = new StringBuilder("UPDATE HostCredential SET ");
      List<Object> bParams = new ArrayList<Object>();
      Long pk = o.getPk();
      JsonObject jsonObject = siteRequest.getJsonObject();
      Set<String> methodNames = jsonObject.fieldNames();
      HostCredential o2 = new HostCredential();
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
                  sql(siteRequest).update(HostCredential.class, pk).set(HostCredential.VAR_tenantResource, Tenant.class, solrId2, val).onSuccess(a -> {
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
                sql(siteRequest).update(HostCredential.class, pk).setToNull(HostCredential.VAR_tenantResource, Tenant.class, null).onSuccess(a -> {
                  promise2.complete();
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
              bSql.append(HostCredential.VAR_tenantId + "=$" + num);
              num++;
              bParams.add(o2.sqlTenantId());
            break;
          case "setCreated":
              o2.setCreated(jsonObject.getString(entityVar));
              if(bParams.size() > 0)
                bSql.append(", ");
              bSql.append(HostCredential.VAR_created + "=$" + num);
              num++;
              bParams.add(o2.sqlCreated());
            break;
          case "setAapOrganizationId":
              o2.setAapOrganizationId(jsonObject.getString(entityVar));
              if(bParams.size() > 0)
                bSql.append(", ");
              bSql.append(HostCredential.VAR_aapOrganizationId + "=$" + num);
              num++;
              bParams.add(o2.sqlAapOrganizationId());
            break;
          case "setCredentialName":
              o2.setCredentialName(jsonObject.getString(entityVar));
              if(bParams.size() > 0)
                bSql.append(", ");
              bSql.append(HostCredential.VAR_credentialName + "=$" + num);
              num++;
              bParams.add(o2.sqlCredentialName());
            break;
          case "setArchived":
              o2.setArchived(jsonObject.getString(entityVar));
              if(bParams.size() > 0)
                bSql.append(", ");
              bSql.append(HostCredential.VAR_archived + "=$" + num);
              num++;
              bParams.add(o2.sqlArchived());
            break;
          case "setCredentialId":
              o2.setCredentialId(jsonObject.getString(entityVar));
              if(bParams.size() > 0)
                bSql.append(", ");
              bSql.append(HostCredential.VAR_credentialId + "=$" + num);
              num++;
              bParams.add(o2.sqlCredentialId());
            break;
          case "setCredentialResource":
              o2.setCredentialResource(jsonObject.getString(entityVar));
              if(bParams.size() > 0)
                bSql.append(", ");
              bSql.append(HostCredential.VAR_credentialResource + "=$" + num);
              num++;
              bParams.add(o2.sqlCredentialResource());
            break;
          case "setCredentialDescription":
              o2.setCredentialDescription(jsonObject.getString(entityVar));
              if(bParams.size() > 0)
                bSql.append(", ");
              bSql.append(HostCredential.VAR_credentialDescription + "=$" + num);
              num++;
              bParams.add(o2.sqlCredentialDescription());
            break;
          case "setAapCredentialId":
              o2.setAapCredentialId(jsonObject.getString(entityVar));
              if(bParams.size() > 0)
                bSql.append(", ");
              bSql.append(HostCredential.VAR_aapCredentialId + "=$" + num);
              num++;
              bParams.add(o2.sqlAapCredentialId());
            break;
          case "setSessionId":
              o2.setSessionId(jsonObject.getString(entityVar));
              if(bParams.size() > 0)
                bSql.append(", ");
              bSql.append(HostCredential.VAR_sessionId + "=$" + num);
              num++;
              bParams.add(o2.sqlSessionId());
            break;
          case "setAapCredentialTypeId":
              o2.setAapCredentialTypeId(jsonObject.getString(entityVar));
              if(bParams.size() > 0)
                bSql.append(", ");
              bSql.append(HostCredential.VAR_aapCredentialTypeId + "=$" + num);
              num++;
              bParams.add(o2.sqlAapCredentialTypeId());
            break;
          case "setUserKey":
              o2.setUserKey(jsonObject.getString(entityVar));
              if(bParams.size() > 0)
                bSql.append(", ");
              bSql.append(HostCredential.VAR_userKey + "=$" + num);
              num++;
              bParams.add(o2.sqlUserKey());
            break;
          case "setUserName":
              o2.setUserName(jsonObject.getString(entityVar));
              if(bParams.size() > 0)
                bSql.append(", ");
              bSql.append(HostCredential.VAR_userName + "=$" + num);
              num++;
              bParams.add(o2.sqlUserName());
            break;
          case "setPassword":
              o2.setPassword(jsonObject.getString(entityVar));
              if(bParams.size() > 0)
                bSql.append(", ");
              bSql.append(HostCredential.VAR_password + "=$" + num);
              num++;
              bParams.add(o2.sqlPassword());
            break;
          case "setBecomeMethod":
              o2.setBecomeMethod(jsonObject.getString(entityVar));
              if(bParams.size() > 0)
                bSql.append(", ");
              bSql.append(HostCredential.VAR_becomeMethod + "=$" + num);
              num++;
              bParams.add(o2.sqlBecomeMethod());
            break;
          case "setObjectTitle":
              o2.setObjectTitle(jsonObject.getString(entityVar));
              if(bParams.size() > 0)
                bSql.append(", ");
              bSql.append(HostCredential.VAR_objectTitle + "=$" + num);
              num++;
              bParams.add(o2.sqlObjectTitle());
            break;
          case "setBecomePassword":
              o2.setBecomePassword(jsonObject.getString(entityVar));
              if(bParams.size() > 0)
                bSql.append(", ");
              bSql.append(HostCredential.VAR_becomePassword + "=$" + num);
              num++;
              bParams.add(o2.sqlBecomePassword());
            break;
          case "setDisplayPage":
              o2.setDisplayPage(jsonObject.getString(entityVar));
              if(bParams.size() > 0)
                bSql.append(", ");
              bSql.append(HostCredential.VAR_displayPage + "=$" + num);
              num++;
              bParams.add(o2.sqlDisplayPage());
            break;
          case "setEditPage":
              o2.setEditPage(jsonObject.getString(entityVar));
              if(bParams.size() > 0)
                bSql.append(", ");
              bSql.append(HostCredential.VAR_editPage + "=$" + num);
              num++;
              bParams.add(o2.sqlEditPage());
            break;
          case "setUserPage":
              o2.setUserPage(jsonObject.getString(entityVar));
              if(bParams.size() > 0)
                bSql.append(", ");
              bSql.append(HostCredential.VAR_userPage + "=$" + num);
              num++;
              bParams.add(o2.sqlUserPage());
            break;
          case "setDownload":
              o2.setDownload(jsonObject.getString(entityVar));
              if(bParams.size() > 0)
                bSql.append(", ");
              bSql.append(HostCredential.VAR_download + "=$" + num);
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
            RuntimeException ex2 = new RuntimeException("value HostCredential failed", ex);
            LOG.error(String.format("relateHostCredential failed. "), ex2);
            a.handle(Future.failedFuture(ex2));
          });
        }));
      }
      CompositeFuture.all(futures1).onSuccess(a -> {
        CompositeFuture.all(futures2).onSuccess(b -> {
          HostCredential o3 = new HostCredential();
          o3.setSiteRequest_(o.getSiteRequest_());
          o3.setPk(pk);
          promise.complete(o3);
        }).onFailure(ex -> {
          LOG.error(String.format("sqlPATCHHostCredential failed. "), ex);
          promise.tryFail(ex);
        });
      }).onFailure(ex -> {
        LOG.error(String.format("sqlPATCHHostCredential failed. "), ex);
        promise.tryFail(ex);
      });
    } catch(Exception ex) {
      LOG.error(String.format("sqlPATCHHostCredential failed. "), ex);
      promise.tryFail(ex);
    }
    return promise.future();
  }

  public Future<ServiceResponse> response200PATCHHostCredential(SiteRequest siteRequest) {
    Promise<ServiceResponse> promise = Promise.promise();
    try {
      JsonObject json = new JsonObject();
      promise.complete(ServiceResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily())));
    } catch(Exception ex) {
      LOG.error(String.format("response200PATCHHostCredential failed. "), ex);
      promise.tryFail(ex);
    }
    return promise.future();
  }

  // POST //

  @Override
  public void postHostCredential(JsonObject body, ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler) {
    LOG.debug(String.format("postHostCredential started. "));
    Boolean classPublicRead = false;
    user(serviceRequest, SiteRequest.class, SiteUser.class, SiteUser.getClassApiAddress(), "postSiteUserFuture", "patchSiteUserFuture", classPublicRead).onSuccess(siteRequest -> {
      try {
        siteRequest.setLang("enUS");
        String credentialResource = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("credentialResource");
        String HOSTCREDENTIAL = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("HOSTCREDENTIAL");
        List<String> groups = Optional.ofNullable(siteRequest.getGroups()).orElse(new ArrayList<>());
        MultiMap form = MultiMap.caseInsensitiveMultiMap();
        form.add("grant_type", "urn:ietf:params:oauth:grant-type:uma-ticket");
        form.add("audience", config.getString(ComputateConfigKeys.AUTH_CLIENT));
        form.add("response_mode", "permissions");
        form.add("permission", String.format("%s#%s", HostCredential.CLASS_AUTH_RESOURCE, "GET"));
        form.add("permission", String.format("%s#%s", HostCredential.CLASS_AUTH_RESOURCE, "POST"));
        form.add("permission", String.format("%s#%s", HostCredential.CLASS_AUTH_RESOURCE, "PATCH"));
        form.add("permission", String.format("%s#%s", HostCredential.CLASS_AUTH_RESOURCE, "PUT"));
        form.add("permission", String.format("%s#%s", HostCredential.CLASS_AUTH_RESOURCE, "DELETE"));
        form.add("permission", String.format("%s#%s", HostCredential.CLASS_AUTH_RESOURCE, "Admin"));
        form.add("permission", String.format("%s#%s", HostCredential.CLASS_AUTH_RESOURCE, "SuperAdmin"));
        if(credentialResource != null)
          form.add("permission", String.format("%s-%s#%s", HostCredential.CLASS_AUTH_RESOURCE, credentialResource, "POST"));
        groups.stream().map(group -> {
              Matcher mPermission = Pattern.compile("^/(.*-?TENANT-([a-z0-9\\-]+))-(\\w+)$").matcher(group);
              return mPermission.find() ? mPermission : null;
            }).filter(v -> v != null).forEach(mPermission -> {
              form.add("permission", String.format("%s#%s", mPermission.group(1), mPermission.group(3)));
            });
        groups.stream().map(group -> {
              Matcher mPermission = Pattern.compile("^/(.*-?HOSTINVENTORY-([a-z0-9\\-]+))-(\\w+)$").matcher(group);
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
            JsonArray scopes = authorizationDecisionBody.stream().map(o -> (JsonObject)o).filter(o -> "HOSTCREDENTIAL".equals(o.getString("rsname"))).findFirst().map(decision -> ((JsonObject)decision).getJsonArray("scopes")).orElse(new JsonArray());
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
            authorizationDecisionBody.stream().map(o -> (JsonObject)o).filter(permission -> {
                  Matcher mPermission = Pattern.compile("^(.*-?HOSTINVENTORY-([a-z0-9\\-]+))$").matcher(permission.getString("rsname"));
                  return permission.getJsonArray("scopes").contains("POST")
                      && mPermission.find();
                }).forEach(permission -> {
                  fqs.add(String.format("%s:%s", "credentialResource", permission.getString("rsname")));
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
              eventBus.publish("websocketHostCredential", JsonObject.mapFrom(apiRequest).toString());
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
              eventBus.request(HostCredential.getClassApiAddress(), json, new DeliveryOptions().addHeader("action", "postHostCredentialFuture")).onSuccess(a -> {
                JsonObject responseMessage = (JsonObject)a.body();
                JsonObject responseBody = new JsonObject(Buffer.buffer(JsonUtil.BASE64_DECODER.decode(responseMessage.getString("payload"))));
                apiRequest.setSolrId(responseBody.getString(HostCredential.VAR_solrId));
                eventHandler.handle(Future.succeededFuture(ServiceResponse.completedWithJson(Buffer.buffer(responseBody.encodePrettily()))));
                LOG.debug(String.format("postHostCredential succeeded. "));
              }).onFailure(ex -> {
                LOG.error(String.format("postHostCredential failed. "), ex);
                error(siteRequest, eventHandler, ex);
              });
            }
          } catch(Exception ex) {
            LOG.error(String.format("postHostCredential failed. "), ex);
            error(null, eventHandler, ex);
          }
        });
      } catch(Exception ex) {
        LOG.error(String.format("postHostCredential failed. "), ex);
        error(null, eventHandler, ex);
      }
    }).onFailure(ex -> {
      if("Inactive Token".equals(ex.getMessage()) || StringUtils.startsWith(ex.getMessage(), "invalid_grant:")) {
        try {
          eventHandler.handle(Future.succeededFuture(new ServiceResponse(302, "Found", null, MultiMap.caseInsensitiveMultiMap().add(HttpHeaders.LOCATION, "/logout?redirect_uri=" + URLEncoder.encode(serviceRequest.getExtra().getString("uri"), "UTF-8")))));
        } catch(Exception ex2) {
          LOG.error(String.format("postHostCredential failed. ", ex2));
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
        LOG.error(String.format("postHostCredential failed. "), ex);
        error(null, eventHandler, ex);
      }
    });
  }

  @Override
  public void postHostCredentialFuture(JsonObject body, ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler) {
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
        postHostCredentialFuture(siteRequest, false).onSuccess(o -> {
          eventHandler.handle(Future.succeededFuture(ServiceResponse.completedWithJson(Buffer.buffer(JsonObject.mapFrom(o).encodePrettily()))));
        }).onFailure(ex -> {
          eventHandler.handle(Future.failedFuture(ex));
        });
      } catch(Throwable ex) {
        LOG.error(String.format("postHostCredential failed. "), ex);
        error(null, eventHandler, ex);
      }
    }).onFailure(ex -> {
      if("Inactive Token".equals(ex.getMessage()) || StringUtils.startsWith(ex.getMessage(), "invalid_grant:")) {
        try {
          eventHandler.handle(Future.succeededFuture(new ServiceResponse(302, "Found", null, MultiMap.caseInsensitiveMultiMap().add(HttpHeaders.LOCATION, "/logout?redirect_uri=" + URLEncoder.encode(serviceRequest.getExtra().getString("uri"), "UTF-8")))));
        } catch(Exception ex2) {
          LOG.error(String.format("postHostCredential failed. ", ex2));
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
        LOG.error(String.format("postHostCredential failed. "), ex);
        error(null, eventHandler, ex);
      }
    });
  }

  public Future<HostCredential> postHostCredentialFuture(SiteRequest siteRequest, Boolean credentialResource) {
    Promise<HostCredential> promise = Promise.promise();

    try {
      pgPool.withTransaction(sqlConnection -> {
        Promise<HostCredential> promise1 = Promise.promise();
        siteRequest.setSqlConnection(sqlConnection);
        varsHostCredential(siteRequest).onSuccess(a -> {
          createHostCredential(siteRequest).onSuccess(hostCredential -> {
            sqlPOSTHostCredential(hostCredential, credentialResource).onSuccess(b -> {
              persistHostCredential(hostCredential, false).onSuccess(c -> {
                relateHostCredential(hostCredential).onSuccess(d -> {
                  indexHostCredential(hostCredential).onSuccess(o2 -> {
                    promise1.complete(hostCredential);
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
      }).compose(hostCredential -> {
        Promise<HostCredential> promise2 = Promise.promise();
        refreshHostCredential(hostCredential).onSuccess(a -> {
          try {
            ApiRequest apiRequest = siteRequest.getApiRequest_();
            if(apiRequest != null) {
              apiRequest.setNumPATCH(apiRequest.getNumPATCH() + 1);
              hostCredential.apiRequestHostCredential();
              eventBus.publish("websocketHostCredential", JsonObject.mapFrom(apiRequest).toString());
            }
            promise2.complete(hostCredential);
          } catch(Exception ex) {
            LOG.error(String.format("postHostCredentialFuture failed. "), ex);
            promise2.tryFail(ex);
          }
        }).onFailure(ex -> {
          promise2.tryFail(ex);
        });
        return promise2.future();
      }).onSuccess(hostCredential -> {
        try {
          ApiRequest apiRequest = siteRequest.getApiRequest_();
          if(apiRequest != null) {
            apiRequest.setNumPATCH(apiRequest.getNumPATCH() + 1);
            hostCredential.apiRequestHostCredential();
            eventBus.publish("websocketHostCredential", JsonObject.mapFrom(apiRequest).toString());
          }
          promise.complete(hostCredential);
        } catch(Exception ex) {
          LOG.error(String.format("postHostCredentialFuture failed. "), ex);
          promise.tryFail(ex);
        }
      }).onFailure(ex -> {
        promise.tryFail(ex);
      });
    } catch(Exception ex) {
      LOG.error(String.format("postHostCredentialFuture failed. "), ex);
      promise.tryFail(ex);
    }
    return promise.future();
  }

  public Future<HostCredential> sqlPOSTHostCredential(HostCredential o, Boolean inheritPrimaryKey) {
    Promise<HostCredential> promise = Promise.promise();
    try {
      SiteRequest siteRequest = o.getSiteRequest_();
      ApiRequest apiRequest = siteRequest.getApiRequest_();
      List<String> solrIds = Optional.ofNullable(apiRequest).map(r -> r.getSolrIds()).orElse(new ArrayList<>());
      List<String> classes = Optional.ofNullable(apiRequest).map(r -> r.getClasses()).orElse(new ArrayList<>());
      SqlConnection sqlConnection = siteRequest.getSqlConnection();
      Integer num = 1;
      StringBuilder bSql = new StringBuilder("UPDATE HostCredential SET ");
      List<Object> bParams = new ArrayList<Object>();
      Long pk = o.getPk();
      JsonObject jsonObject = siteRequest.getJsonObject();
      HostCredential o2 = new HostCredential();
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
          case HostCredential.VAR_tenantResource:
            Optional.ofNullable(jsonObject.getString(entityVar)).ifPresent(val -> {
              futures1.add(Future.future(promise2 -> {
                searchModel(siteRequest).query(Tenant.varIndexedTenant(Tenant.VAR_tenantResource), Tenant.class, val).onSuccess(o3 -> {
                  String solrId2 = Optional.ofNullable(o3).map(o4 -> o4.getSolrId()).filter(solrId3 -> !solrIds.contains(solrId3)).orElse(null);
                  if(solrId2 != null) {
                    solrIds.add(solrId2);
                    classes.add("Tenant");
                  }
                  sql(siteRequest).update(HostCredential.class, pk).set(HostCredential.VAR_tenantResource, Tenant.class, solrId2, val).onSuccess(a -> {
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
          case HostCredential.VAR_tenantId:
            o2.setTenantId(jsonObject.getString(entityVar));
            if(bParams.size() > 0) {
              bSql.append(", ");
            }
            bSql.append(HostCredential.VAR_tenantId + "=$" + num);
            num++;
            bParams.add(o2.sqlTenantId());
            break;
          case HostCredential.VAR_created:
            o2.setCreated(jsonObject.getString(entityVar));
            if(bParams.size() > 0) {
              bSql.append(", ");
            }
            bSql.append(HostCredential.VAR_created + "=$" + num);
            num++;
            bParams.add(o2.sqlCreated());
            break;
          case HostCredential.VAR_aapOrganizationId:
            o2.setAapOrganizationId(jsonObject.getString(entityVar));
            if(bParams.size() > 0) {
              bSql.append(", ");
            }
            bSql.append(HostCredential.VAR_aapOrganizationId + "=$" + num);
            num++;
            bParams.add(o2.sqlAapOrganizationId());
            break;
          case HostCredential.VAR_credentialName:
            o2.setCredentialName(jsonObject.getString(entityVar));
            if(bParams.size() > 0) {
              bSql.append(", ");
            }
            bSql.append(HostCredential.VAR_credentialName + "=$" + num);
            num++;
            bParams.add(o2.sqlCredentialName());
            break;
          case HostCredential.VAR_archived:
            o2.setArchived(jsonObject.getString(entityVar));
            if(bParams.size() > 0) {
              bSql.append(", ");
            }
            bSql.append(HostCredential.VAR_archived + "=$" + num);
            num++;
            bParams.add(o2.sqlArchived());
            break;
          case HostCredential.VAR_credentialId:
            o2.setCredentialId(jsonObject.getString(entityVar));
            if(bParams.size() > 0) {
              bSql.append(", ");
            }
            bSql.append(HostCredential.VAR_credentialId + "=$" + num);
            num++;
            bParams.add(o2.sqlCredentialId());
            break;
          case HostCredential.VAR_credentialResource:
            o2.setCredentialResource(jsonObject.getString(entityVar));
            if(bParams.size() > 0) {
              bSql.append(", ");
            }
            bSql.append(HostCredential.VAR_credentialResource + "=$" + num);
            num++;
            bParams.add(o2.sqlCredentialResource());
            break;
          case HostCredential.VAR_credentialDescription:
            o2.setCredentialDescription(jsonObject.getString(entityVar));
            if(bParams.size() > 0) {
              bSql.append(", ");
            }
            bSql.append(HostCredential.VAR_credentialDescription + "=$" + num);
            num++;
            bParams.add(o2.sqlCredentialDescription());
            break;
          case HostCredential.VAR_aapCredentialId:
            o2.setAapCredentialId(jsonObject.getString(entityVar));
            if(bParams.size() > 0) {
              bSql.append(", ");
            }
            bSql.append(HostCredential.VAR_aapCredentialId + "=$" + num);
            num++;
            bParams.add(o2.sqlAapCredentialId());
            break;
          case HostCredential.VAR_sessionId:
            o2.setSessionId(jsonObject.getString(entityVar));
            if(bParams.size() > 0) {
              bSql.append(", ");
            }
            bSql.append(HostCredential.VAR_sessionId + "=$" + num);
            num++;
            bParams.add(o2.sqlSessionId());
            break;
          case HostCredential.VAR_aapCredentialTypeId:
            o2.setAapCredentialTypeId(jsonObject.getString(entityVar));
            if(bParams.size() > 0) {
              bSql.append(", ");
            }
            bSql.append(HostCredential.VAR_aapCredentialTypeId + "=$" + num);
            num++;
            bParams.add(o2.sqlAapCredentialTypeId());
            break;
          case HostCredential.VAR_userKey:
            o2.setUserKey(jsonObject.getString(entityVar));
            if(bParams.size() > 0) {
              bSql.append(", ");
            }
            bSql.append(HostCredential.VAR_userKey + "=$" + num);
            num++;
            bParams.add(o2.sqlUserKey());
            break;
          case HostCredential.VAR_userName:
            o2.setUserName(jsonObject.getString(entityVar));
            if(bParams.size() > 0) {
              bSql.append(", ");
            }
            bSql.append(HostCredential.VAR_userName + "=$" + num);
            num++;
            bParams.add(o2.sqlUserName());
            break;
          case HostCredential.VAR_password:
            o2.setPassword(jsonObject.getString(entityVar));
            if(bParams.size() > 0) {
              bSql.append(", ");
            }
            bSql.append(HostCredential.VAR_password + "=$" + num);
            num++;
            bParams.add(o2.sqlPassword());
            break;
          case HostCredential.VAR_becomeMethod:
            o2.setBecomeMethod(jsonObject.getString(entityVar));
            if(bParams.size() > 0) {
              bSql.append(", ");
            }
            bSql.append(HostCredential.VAR_becomeMethod + "=$" + num);
            num++;
            bParams.add(o2.sqlBecomeMethod());
            break;
          case HostCredential.VAR_objectTitle:
            o2.setObjectTitle(jsonObject.getString(entityVar));
            if(bParams.size() > 0) {
              bSql.append(", ");
            }
            bSql.append(HostCredential.VAR_objectTitle + "=$" + num);
            num++;
            bParams.add(o2.sqlObjectTitle());
            break;
          case HostCredential.VAR_becomePassword:
            o2.setBecomePassword(jsonObject.getString(entityVar));
            if(bParams.size() > 0) {
              bSql.append(", ");
            }
            bSql.append(HostCredential.VAR_becomePassword + "=$" + num);
            num++;
            bParams.add(o2.sqlBecomePassword());
            break;
          case HostCredential.VAR_displayPage:
            o2.setDisplayPage(jsonObject.getString(entityVar));
            if(bParams.size() > 0) {
              bSql.append(", ");
            }
            bSql.append(HostCredential.VAR_displayPage + "=$" + num);
            num++;
            bParams.add(o2.sqlDisplayPage());
            break;
          case HostCredential.VAR_editPage:
            o2.setEditPage(jsonObject.getString(entityVar));
            if(bParams.size() > 0) {
              bSql.append(", ");
            }
            bSql.append(HostCredential.VAR_editPage + "=$" + num);
            num++;
            bParams.add(o2.sqlEditPage());
            break;
          case HostCredential.VAR_userPage:
            o2.setUserPage(jsonObject.getString(entityVar));
            if(bParams.size() > 0) {
              bSql.append(", ");
            }
            bSql.append(HostCredential.VAR_userPage + "=$" + num);
            num++;
            bParams.add(o2.sqlUserPage());
            break;
          case HostCredential.VAR_download:
            o2.setDownload(jsonObject.getString(entityVar));
            if(bParams.size() > 0) {
              bSql.append(", ");
            }
            bSql.append(HostCredential.VAR_download + "=$" + num);
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
            RuntimeException ex2 = new RuntimeException("value HostCredential failed", ex);
            LOG.error(String.format("relateHostCredential failed. "), ex2);
            a.handle(Future.failedFuture(ex2));
          });
        }));
      }
      CompositeFuture.all(futures1).onSuccess(a -> {
        CompositeFuture.all(futures2).onSuccess(b -> {
          promise.complete(o2);
        }).onFailure(ex -> {
          LOG.error(String.format("sqlPOSTHostCredential failed. "), ex);
          promise.tryFail(ex);
        });
      }).onFailure(ex -> {
        LOG.error(String.format("sqlPOSTHostCredential failed. "), ex);
        promise.tryFail(ex);
      });
    } catch(Exception ex) {
      LOG.error(String.format("sqlPOSTHostCredential failed. "), ex);
      promise.tryFail(ex);
    }
    return promise.future();
  }

  public Future<ServiceResponse> response200POSTHostCredential(HostCredential o) {
    Promise<ServiceResponse> promise = Promise.promise();
    try {
      SiteRequest siteRequest = o.getSiteRequest_();
      JsonObject json = JsonObject.mapFrom(o);
      promise.complete(ServiceResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily())));
    } catch(Exception ex) {
      LOG.error(String.format("response200POSTHostCredential failed. "), ex);
      promise.tryFail(ex);
    }
    return promise.future();
  }

  // DELETE //

  @Override
  public void deleteHostCredential(JsonObject body, ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler) {
    LOG.debug(String.format("deleteHostCredential started. "));
    Boolean classPublicRead = false;
    user(serviceRequest, SiteRequest.class, SiteUser.class, SiteUser.getClassApiAddress(), "postSiteUserFuture", "patchSiteUserFuture", classPublicRead).onSuccess(siteRequest -> {
      try {
        siteRequest.setLang("enUS");
        String credentialResource = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("credentialResource");
        String HOSTCREDENTIAL = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("HOSTCREDENTIAL");
        List<String> groups = Optional.ofNullable(siteRequest.getGroups()).orElse(new ArrayList<>());
        MultiMap form = MultiMap.caseInsensitiveMultiMap();
        form.add("grant_type", "urn:ietf:params:oauth:grant-type:uma-ticket");
        form.add("audience", config.getString(ComputateConfigKeys.AUTH_CLIENT));
        form.add("response_mode", "permissions");
        form.add("permission", String.format("%s#%s", HostCredential.CLASS_AUTH_RESOURCE, "GET"));
        form.add("permission", String.format("%s#%s", HostCredential.CLASS_AUTH_RESOURCE, "POST"));
        form.add("permission", String.format("%s#%s", HostCredential.CLASS_AUTH_RESOURCE, "PATCH"));
        form.add("permission", String.format("%s#%s", HostCredential.CLASS_AUTH_RESOURCE, "PUT"));
        form.add("permission", String.format("%s#%s", HostCredential.CLASS_AUTH_RESOURCE, "DELETE"));
        form.add("permission", String.format("%s#%s", HostCredential.CLASS_AUTH_RESOURCE, "Admin"));
        form.add("permission", String.format("%s#%s", HostCredential.CLASS_AUTH_RESOURCE, "SuperAdmin"));
        if(credentialResource != null)
          form.add("permission", String.format("%s-%s#%s", HostCredential.CLASS_AUTH_RESOURCE, credentialResource, "DELETE"));
        groups.stream().map(group -> {
              Matcher mPermission = Pattern.compile("^/(.*-?TENANT-([a-z0-9\\-]+))-(\\w+)$").matcher(group);
              return mPermission.find() ? mPermission : null;
            }).filter(v -> v != null).forEach(mPermission -> {
              form.add("permission", String.format("%s#%s", mPermission.group(1), mPermission.group(3)));
            });
        groups.stream().map(group -> {
              Matcher mPermission = Pattern.compile("^/(.*-?HOSTINVENTORY-([a-z0-9\\-]+))-(\\w+)$").matcher(group);
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
            JsonArray scopes = authorizationDecisionBody.stream().map(o -> (JsonObject)o).filter(o -> "HOSTCREDENTIAL".equals(o.getString("rsname"))).findFirst().map(decision -> ((JsonObject)decision).getJsonArray("scopes")).orElse(new JsonArray());
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
            authorizationDecisionBody.stream().map(o -> (JsonObject)o).filter(permission -> {
                  Matcher mPermission = Pattern.compile("^(.*-?HOSTINVENTORY-([a-z0-9\\-]+))$").matcher(permission.getString("rsname"));
                  return permission.getJsonArray("scopes").contains("DELETE")
                      && mPermission.find();
                }).forEach(permission -> {
                  fqs.add(String.format("%s:%s", "credentialResource", permission.getString("rsname")));
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
              searchHostCredentialList(siteRequest, false, true, true, "DELETE").onSuccess(listHostCredential -> {
                try {
                  ApiRequest apiRequest = new ApiRequest();
                  apiRequest.setRows(listHostCredential.getRequest().getRows());
                  apiRequest.setNumFound(listHostCredential.getResponse().getResponse().getNumFound());
                  apiRequest.setNumPATCH(0L);
                  apiRequest.initDeepApiRequest(siteRequest);
                  siteRequest.setApiRequest_(apiRequest);
                  if(apiRequest.getNumFound() == 1L)
                    apiRequest.setOriginal(listHostCredential.first());
                  apiRequest.setSolrId(Optional.ofNullable(listHostCredential.first()).map(o2 -> o2.getSolrId()).orElse(null));
                  eventBus.publish("websocketHostCredential", JsonObject.mapFrom(apiRequest).toString());

                  listDELETEHostCredential(apiRequest, listHostCredential).onSuccess(e -> {
                    response200DELETEHostCredential(siteRequest).onSuccess(response -> {
                      LOG.debug(String.format("deleteHostCredential succeeded. "));
                      eventHandler.handle(Future.succeededFuture(response));
                    }).onFailure(ex -> {
                      LOG.error(String.format("deleteHostCredential failed. "), ex);
                      error(siteRequest, eventHandler, ex);
                    });
                  }).onFailure(ex -> {
                    LOG.error(String.format("deleteHostCredential failed. "), ex);
                    error(siteRequest, eventHandler, ex);
                  });
                } catch(Exception ex) {
                  LOG.error(String.format("deleteHostCredential failed. "), ex);
                  error(siteRequest, eventHandler, ex);
                }
              }).onFailure(ex -> {
                LOG.error(String.format("deleteHostCredential failed. "), ex);
                error(siteRequest, eventHandler, ex);
              });
            }
          } catch(Exception ex) {
            LOG.error(String.format("deleteHostCredential failed. "), ex);
            error(null, eventHandler, ex);
          }
        });
      } catch(Exception ex) {
        LOG.error(String.format("deleteHostCredential failed. "), ex);
        error(null, eventHandler, ex);
      }
    }).onFailure(ex -> {
      if("Inactive Token".equals(ex.getMessage()) || StringUtils.startsWith(ex.getMessage(), "invalid_grant:")) {
        try {
          eventHandler.handle(Future.succeededFuture(new ServiceResponse(302, "Found", null, MultiMap.caseInsensitiveMultiMap().add(HttpHeaders.LOCATION, "/logout?redirect_uri=" + URLEncoder.encode(serviceRequest.getExtra().getString("uri"), "UTF-8")))));
        } catch(Exception ex2) {
          LOG.error(String.format("deleteHostCredential failed. ", ex2));
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
        LOG.error(String.format("deleteHostCredential failed. "), ex);
        error(null, eventHandler, ex);
      }
    });
  }

  public Future<Void> listDELETEHostCredential(ApiRequest apiRequest, SearchList<HostCredential> listHostCredential) {
    Promise<Void> promise = Promise.promise();
    List<Future> futures = new ArrayList<>();
    SiteRequest siteRequest = listHostCredential.getSiteRequest_(SiteRequest.class);
    listHostCredential.getList().forEach(o -> {
      SiteRequest siteRequest2 = generateSiteRequest(siteRequest.getUser(), siteRequest.getUserPrincipal(), siteRequest.getServiceRequest(), siteRequest.getJsonObject(), SiteRequest.class);
      siteRequest2.setScopes(siteRequest.getScopes());
      o.setSiteRequest_(siteRequest2);
      siteRequest2.setApiRequest_(siteRequest.getApiRequest_());
      JsonObject jsonObject = JsonObject.mapFrom(o);
      HostCredential o2 = jsonObject.mapTo(HostCredential.class);
      o2.setSiteRequest_(siteRequest2);
      futures.add(Future.future(promise1 -> {
        deleteHostCredentialFuture(o).onSuccess(a -> {
          promise1.complete();
        }).onFailure(ex -> {
          LOG.error(String.format("listDELETEHostCredential failed. "), ex);
          promise1.tryFail(ex);
        });
      }));
    });
    CompositeFuture.all(futures).onSuccess( a -> {
      listHostCredential.next().onSuccess(next -> {
        if(next) {
          listDELETEHostCredential(apiRequest, listHostCredential).onSuccess(b -> {
            promise.complete();
          }).onFailure(ex -> {
            LOG.error(String.format("listDELETEHostCredential failed. "), ex);
            promise.tryFail(ex);
          });
        } else {
          promise.complete();
        }
      }).onFailure(ex -> {
        LOG.error(String.format("listDELETEHostCredential failed. "), ex);
        promise.tryFail(ex);
      });
    }).onFailure(ex -> {
      LOG.error(String.format("listDELETEHostCredential failed. "), ex);
      promise.tryFail(ex);
    });
    return promise.future();
  }

  @Override
  public void deleteHostCredentialFuture(JsonObject body, ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler) {
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
        searchHostCredentialList(siteRequest, false, true, true, "DELETE").onSuccess(listHostCredential -> {
          try {
            HostCredential o = listHostCredential.first();
            if(o != null && listHostCredential.getResponse().getResponse().getNumFound() == 1) {
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
              apiRequest.setId(Optional.ofNullable(listHostCredential.first()).map(o2 -> o2.getCredentialResource().toString()).orElse(null));
              apiRequest.setSolrId(Optional.ofNullable(listHostCredential.first()).map(o2 -> o2.getSolrId()).orElse(null));
              deleteHostCredentialFuture(o).onSuccess(o2 -> {
                eventHandler.handle(Future.succeededFuture(ServiceResponse.completedWithJson(Buffer.buffer(new JsonObject().encodePrettily()))));
              }).onFailure(ex -> {
                eventHandler.handle(Future.failedFuture(ex));
              });
            } else {
              eventHandler.handle(Future.succeededFuture(ServiceResponse.completedWithJson(Buffer.buffer(new JsonObject().encodePrettily()))));
            }
          } catch(Exception ex) {
            LOG.error(String.format("deleteHostCredential failed. "), ex);
            error(siteRequest, eventHandler, ex);
          }
        }).onFailure(ex -> {
          LOG.error(String.format("deleteHostCredential failed. "), ex);
          error(siteRequest, eventHandler, ex);
        });
      } catch(Exception ex) {
        LOG.error(String.format("deleteHostCredential failed. "), ex);
        error(null, eventHandler, ex);
      }
    }).onFailure(ex -> {
      LOG.error(String.format("deleteHostCredential failed. "), ex);
      error(null, eventHandler, ex);
    });
  }

  public Future<HostCredential> deleteHostCredentialFuture(HostCredential o) {
    SiteRequest siteRequest = o.getSiteRequest_();
    Promise<HostCredential> promise = Promise.promise();

    try {
      ApiRequest apiRequest = siteRequest.getApiRequest_();
      Promise<HostCredential> promise1 = Promise.promise();
      pgPool.withTransaction(sqlConnection -> {
        siteRequest.setSqlConnection(sqlConnection);
        varsHostCredential(siteRequest).onSuccess(a -> {
          sqlDELETEHostCredential(o).onSuccess(hostCredential -> {
            relateHostCredential(o).onSuccess(d -> {
              unindexHostCredential(o).onSuccess(o2 -> {
                if(apiRequest != null) {
                  apiRequest.setNumPATCH(apiRequest.getNumPATCH() + 1);
                  if(apiRequest.getNumFound() == 1L && Optional.ofNullable(siteRequest.getJsonObject()).map(json -> json.size() > 0).orElse(false)) {
                    o2.apiRequestHostCredential();
                    if(apiRequest.getVars().size() > 0 && Optional.ofNullable(siteRequest.getRequestVars().get("refresh")).map(refresh -> !refresh.equals("false")).orElse(true))
                      eventBus.publish("websocketHostCredential", JsonObject.mapFrom(apiRequest).toString());
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
      }).compose(hostCredential -> {
        Promise<HostCredential> promise2 = Promise.promise();
        refreshHostCredential(o).onSuccess(a -> {
          promise2.complete(o);
        }).onFailure(ex -> {
          promise2.tryFail(ex);
        });
        return promise2.future();
      }).onSuccess(hostCredential -> {
        promise.complete(hostCredential);
      }).onFailure(ex -> {
        promise.tryFail(ex);
      });
    } catch(Exception ex) {
      LOG.error(String.format("deleteHostCredentialFuture failed. "), ex);
      promise.tryFail(ex);
    }
    return promise.future();
  }

  public Future<Void> sqlDELETEHostCredential(HostCredential o) {
    Promise<Void> promise = Promise.promise();
    try {
      SiteRequest siteRequest = o.getSiteRequest_();
      ApiRequest apiRequest = siteRequest.getApiRequest_();
      List<String> solrIds = Optional.ofNullable(apiRequest).map(r -> r.getSolrIds()).orElse(new ArrayList<>());
      List<String> classes = Optional.ofNullable(apiRequest).map(r -> r.getClasses()).orElse(new ArrayList<>());
      SqlConnection sqlConnection = siteRequest.getSqlConnection();
      Integer num = 1;
      StringBuilder bSql = new StringBuilder("DELETE FROM HostCredential ");
      List<Object> bParams = new ArrayList<Object>();
      Long pk = o.getPk();
      JsonObject jsonObject = siteRequest.getJsonObject();
      HostCredential o2 = new HostCredential();
      o2.setSiteRequest_(siteRequest);
      List<Future> futures1 = new ArrayList<>();
      List<Future> futures2 = new ArrayList<>();

      if(jsonObject != null) {
        Set<String> entityVars = jsonObject.fieldNames();
        for(String entityVar : entityVars) {
          switch(entityVar) {
          case HostCredential.VAR_tenantResource:
            Optional.ofNullable(jsonObject.getString(entityVar)).ifPresent(val -> {
              futures1.add(Future.future(promise2 -> {
                searchModel(siteRequest).query(Tenant.varIndexedTenant(Tenant.VAR_tenantResource), Tenant.class, val).onSuccess(o3 -> {
                  String solrId2 = Optional.ofNullable(o3).map(o4 -> o4.getSolrId()).filter(solrId3 -> !solrIds.contains(solrId3)).orElse(null);
                  if(solrId2 != null) {
                    solrIds.add(solrId2);
                    classes.add("Tenant");
                  }
                  sql(siteRequest).update(HostCredential.class, pk).set(HostCredential.VAR_tenantResource, Tenant.class, null, null).onSuccess(a -> {
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
          RuntimeException ex2 = new RuntimeException("value HostCredential failed", ex);
          LOG.error(String.format("unrelateHostCredential failed. "), ex2);
          a.handle(Future.failedFuture(ex2));
        });
      }));
      CompositeFuture.all(futures1).onSuccess(a -> {
        CompositeFuture.all(futures2).onSuccess(b -> {
          promise.complete();
        }).onFailure(ex -> {
          LOG.error(String.format("sqlDELETEHostCredential failed. "), ex);
          promise.tryFail(ex);
        });
      }).onFailure(ex -> {
        LOG.error(String.format("sqlDELETEHostCredential failed. "), ex);
        promise.tryFail(ex);
      });
    } catch(Exception ex) {
      LOG.error(String.format("sqlDELETEHostCredential failed. "), ex);
      promise.tryFail(ex);
    }
    return promise.future();
  }

  public Future<ServiceResponse> response200DELETEHostCredential(SiteRequest siteRequest) {
    Promise<ServiceResponse> promise = Promise.promise();
    try {
      JsonObject json = new JsonObject();
      promise.complete(ServiceResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily())));
    } catch(Exception ex) {
      LOG.error(String.format("response200DELETEHostCredential failed. "), ex);
      promise.tryFail(ex);
    }
    return promise.future();
  }

  // PUTImport //

  @Override
  public void putimportHostCredential(JsonObject body, ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler) {
    LOG.debug(String.format("putimportHostCredential started. "));
    Boolean classPublicRead = false;
    user(serviceRequest, SiteRequest.class, SiteUser.class, SiteUser.getClassApiAddress(), "postSiteUserFuture", "patchSiteUserFuture", classPublicRead).onSuccess(siteRequest -> {
      try {
        siteRequest.setLang("enUS");
        String credentialResource = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("credentialResource");
        String HOSTCREDENTIAL = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("HOSTCREDENTIAL");
        List<String> groups = Optional.ofNullable(siteRequest.getGroups()).orElse(new ArrayList<>());
        MultiMap form = MultiMap.caseInsensitiveMultiMap();
        form.add("grant_type", "urn:ietf:params:oauth:grant-type:uma-ticket");
        form.add("audience", config.getString(ComputateConfigKeys.AUTH_CLIENT));
        form.add("response_mode", "permissions");
        form.add("permission", String.format("%s#%s", HostCredential.CLASS_AUTH_RESOURCE, "GET"));
        form.add("permission", String.format("%s#%s", HostCredential.CLASS_AUTH_RESOURCE, "POST"));
        form.add("permission", String.format("%s#%s", HostCredential.CLASS_AUTH_RESOURCE, "PATCH"));
        form.add("permission", String.format("%s#%s", HostCredential.CLASS_AUTH_RESOURCE, "PUT"));
        form.add("permission", String.format("%s#%s", HostCredential.CLASS_AUTH_RESOURCE, "DELETE"));
        form.add("permission", String.format("%s#%s", HostCredential.CLASS_AUTH_RESOURCE, "Admin"));
        form.add("permission", String.format("%s#%s", HostCredential.CLASS_AUTH_RESOURCE, "SuperAdmin"));
        if(credentialResource != null)
          form.add("permission", String.format("%s-%s#%s", HostCredential.CLASS_AUTH_RESOURCE, credentialResource, "PUT"));
        groups.stream().map(group -> {
              Matcher mPermission = Pattern.compile("^/(.*-?TENANT-([a-z0-9\\-]+))-(\\w+)$").matcher(group);
              return mPermission.find() ? mPermission : null;
            }).filter(v -> v != null).forEach(mPermission -> {
              form.add("permission", String.format("%s#%s", mPermission.group(1), mPermission.group(3)));
            });
        groups.stream().map(group -> {
              Matcher mPermission = Pattern.compile("^/(.*-?HOSTINVENTORY-([a-z0-9\\-]+))-(\\w+)$").matcher(group);
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
            JsonArray scopes = authorizationDecisionBody.stream().map(o -> (JsonObject)o).filter(o -> "HOSTCREDENTIAL".equals(o.getString("rsname"))).findFirst().map(decision -> ((JsonObject)decision).getJsonArray("scopes")).orElse(new JsonArray());
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
            authorizationDecisionBody.stream().map(o -> (JsonObject)o).filter(permission -> {
                  Matcher mPermission = Pattern.compile("^(.*-?HOSTINVENTORY-([a-z0-9\\-]+))$").matcher(permission.getString("rsname"));
                  return permission.getJsonArray("scopes").contains("PUT")
                      && mPermission.find();
                }).forEach(permission -> {
                  fqs.add(String.format("%s:%s", "credentialResource", permission.getString("rsname")));
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
              eventBus.publish("websocketHostCredential", JsonObject.mapFrom(apiRequest).toString());
              varsHostCredential(siteRequest).onSuccess(d -> {
                listPUTImportHostCredential(apiRequest, siteRequest).onSuccess(e -> {
                  response200PUTImportHostCredential(siteRequest).onSuccess(response -> {
                    LOG.debug(String.format("putimportHostCredential succeeded. "));
                    eventHandler.handle(Future.succeededFuture(response));
                  }).onFailure(ex -> {
                    LOG.error(String.format("putimportHostCredential failed. "), ex);
                    error(siteRequest, eventHandler, ex);
                  });
                }).onFailure(ex -> {
                  LOG.error(String.format("putimportHostCredential failed. "), ex);
                  error(siteRequest, eventHandler, ex);
                });
              }).onFailure(ex -> {
                LOG.error(String.format("putimportHostCredential failed. "), ex);
                error(siteRequest, eventHandler, ex);
              });
            }
          } catch(Exception ex) {
            LOG.error(String.format("putimportHostCredential failed. "), ex);
            error(null, eventHandler, ex);
          }
        });
      } catch(Exception ex) {
        LOG.error(String.format("putimportHostCredential failed. "), ex);
        error(null, eventHandler, ex);
      }
    }).onFailure(ex -> {
      if("Inactive Token".equals(ex.getMessage()) || StringUtils.startsWith(ex.getMessage(), "invalid_grant:")) {
        try {
          eventHandler.handle(Future.succeededFuture(new ServiceResponse(302, "Found", null, MultiMap.caseInsensitiveMultiMap().add(HttpHeaders.LOCATION, "/logout?redirect_uri=" + URLEncoder.encode(serviceRequest.getExtra().getString("uri"), "UTF-8")))));
        } catch(Exception ex2) {
          LOG.error(String.format("putimportHostCredential failed. ", ex2));
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
        LOG.error(String.format("putimportHostCredential failed. "), ex);
        error(null, eventHandler, ex);
      }
    });
  }

  public Future<Void> listPUTImportHostCredential(ApiRequest apiRequest, SiteRequest siteRequest) {
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
          eventBus.request(HostCredential.getClassApiAddress(), json, new DeliveryOptions().addHeader("action", "putimportHostCredentialFuture")).onSuccess(a -> {
            promise1.complete();
          }).onFailure(ex -> {
            LOG.error(String.format("listPUTImportHostCredential failed. "), ex);
            promise1.tryFail(ex);
          });
        }));
      });
      CompositeFuture.all(futures).onSuccess(a -> {
        apiRequest.setNumPATCH(apiRequest.getNumPATCH() + 1);
        promise.complete();
      }).onFailure(ex -> {
        LOG.error(String.format("listPUTImportHostCredential failed. "), ex);
        promise.tryFail(ex);
      });
    } catch(Exception ex) {
      LOG.error(String.format("listPUTImportHostCredential failed. "), ex);
      promise.tryFail(ex);
    }
    return promise.future();
  }

  @Override
  public void putimportHostCredentialFuture(JsonObject body, ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler) {
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
        String credentialResource = Optional.ofNullable(body.getString(HostCredential.VAR_credentialResource)).orElse(body.getString(HostCredential.VAR_solrId));
        if(Optional.ofNullable(serviceRequest.getParams()).map(p -> p.getJsonObject("query")).map( q -> q.getJsonArray("var")).orElse(new JsonArray()).stream().filter(s -> "refresh:false".equals(s)).count() > 0L) {
          siteRequest.getRequestVars().put( "refresh", "false" );
        }
        pgPool.getConnection().onSuccess(sqlConnection -> {
          String sqlQuery = String.format("select * from %s WHERE credentialResource=$1", HostCredential.CLASS_SIMPLE_NAME);
          sqlConnection.preparedQuery(sqlQuery)
              .execute(Tuple.tuple(Arrays.asList(credentialResource))
              ).onSuccess(result -> {
            sqlConnection.close().onSuccess(a -> {
              try {
                if(result.size() >= 1) {
                  HostCredential o = new HostCredential();
                  o.setSiteRequest_(siteRequest);
                  for(Row definition : result.value()) {
                    for(Integer i = 0; i < definition.size(); i++) {
                      try {
                        String columnName = definition.getColumnName(i);
                        Object columnValue = definition.getValue(i);
                        o.persistForClass(columnName, columnValue);
                      } catch(Exception e) {
                        LOG.error(String.format("persistHostCredential failed. "), e);
                      }
                    }
                  }
                  HostCredential o2 = new HostCredential();
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
                      if(!StringUtils.containsAny(f, "credentialResource", "created", "setCreated") && !Objects.equals(o.obtainForClass(f), o2.obtainForClass(f)))
                        body2.put("set" + StringUtils.capitalize(f), bodyVal);
                    }
                  }
                  for(String f : Optional.ofNullable(o.getSaves()).orElse(new ArrayList<>())) {
                    if(!body.fieldNames().contains(f)) {
                      if(!StringUtils.containsAny(f, "credentialResource", "created", "setCreated") && !Objects.equals(o.obtainForClass(f), o2.obtainForClass(f)))
                        body2.putNull("set" + StringUtils.capitalize(f));
                    }
                  }
                  if(result.size() >= 1) {
                    apiRequest.setOriginal(o);
                    apiRequest.setId(Optional.ofNullable(o.getCredentialResource()).map(v -> v.toString()).orElse(null));
                    apiRequest.setSolrId(o.getSolrId());
                  }
                  siteRequest.setJsonObject(body2);
                  patchHostCredentialFuture(o, true).onSuccess(b -> {
                    LOG.debug("Import HostCredential {} succeeded, modified HostCredential. ", body.getValue(HostCredential.VAR_credentialResource));
                    eventHandler.handle(Future.succeededFuture());
                  }).onFailure(ex -> {
                    LOG.error(String.format("putimportHostCredentialFuture failed. "), ex);
                    eventHandler.handle(Future.failedFuture(ex));
                  });
                } else {
                  postHostCredentialFuture(siteRequest, true).onSuccess(b -> {
                    LOG.debug("Import HostCredential {} succeeded, created new HostCredential. ", body.getValue(HostCredential.VAR_credentialResource));
                    eventHandler.handle(Future.succeededFuture());
                  }).onFailure(ex -> {
                    LOG.error(String.format("putimportHostCredentialFuture failed. "), ex);
                    eventHandler.handle(Future.failedFuture(ex));
                  });
                }
              } catch(Exception ex) {
                LOG.error(String.format("putimportHostCredentialFuture failed. "), ex);
                eventHandler.handle(Future.failedFuture(ex));
              }
            }).onFailure(ex -> {
              LOG.error(String.format("putimportHostCredentialFuture failed. "), ex);
              eventHandler.handle(Future.failedFuture(ex));
            });
          }).onFailure(ex -> {
            LOG.error(String.format("putimportHostCredentialFuture failed. "), ex);
            eventHandler.handle(Future.failedFuture(ex));
          });
        }).onFailure(ex -> {
          LOG.error(String.format("putimportHostCredentialFuture failed. "), ex);
          eventHandler.handle(Future.failedFuture(ex));
        });
      } catch(Exception ex) {
        LOG.error(String.format("putimportHostCredentialFuture failed. "), ex);
        eventHandler.handle(Future.failedFuture(ex));
      }
    }).onFailure(ex -> {
      if("Inactive Token".equals(ex.getMessage()) || StringUtils.startsWith(ex.getMessage(), "invalid_grant:")) {
        try {
          eventHandler.handle(Future.succeededFuture(new ServiceResponse(302, "Found", null, MultiMap.caseInsensitiveMultiMap().add(HttpHeaders.LOCATION, "/logout?redirect_uri=" + URLEncoder.encode(serviceRequest.getExtra().getString("uri"), "UTF-8")))));
        } catch(Exception ex2) {
          LOG.error(String.format("putimportHostCredential failed. ", ex2));
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
        LOG.error(String.format("putimportHostCredential failed. "), ex);
        error(null, eventHandler, ex);
      }
    });
  }

  public Future<ServiceResponse> response200PUTImportHostCredential(SiteRequest siteRequest) {
    Promise<ServiceResponse> promise = Promise.promise();
    try {
      JsonObject json = new JsonObject();
      promise.complete(ServiceResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily())));
    } catch(Exception ex) {
      LOG.error(String.format("response200PUTImportHostCredential failed. "), ex);
      promise.tryFail(ex);
    }
    return promise.future();
  }

  // SearchPage //

  @Override
  public void searchpageHostCredential(ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler) {
    oauth2AuthenticationProvider.refresh(User.create(serviceRequest.getUser())).onSuccess(user -> {
      serviceRequest.setUser(user.principal());
    Boolean classPublicRead = false;
    user(serviceRequest, SiteRequest.class, SiteUser.class, SiteUser.getClassApiAddress(), "postSiteUserFuture", "patchSiteUserFuture", classPublicRead).onSuccess(siteRequest -> {
      try {
        siteRequest.setLang("enUS");
        String credentialResource = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("credentialResource");
        String HOSTCREDENTIAL = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("HOSTCREDENTIAL");
        List<String> groups = Optional.ofNullable(siteRequest.getGroups()).orElse(new ArrayList<>());
        MultiMap form = MultiMap.caseInsensitiveMultiMap();
        form.add("grant_type", "urn:ietf:params:oauth:grant-type:uma-ticket");
        form.add("audience", config.getString(ComputateConfigKeys.AUTH_CLIENT));
        form.add("response_mode", "permissions");
        form.add("permission", String.format("%s#%s", HostCredential.CLASS_AUTH_RESOURCE, "GET"));
        form.add("permission", String.format("%s#%s", HostCredential.CLASS_AUTH_RESOURCE, "POST"));
        form.add("permission", String.format("%s#%s", HostCredential.CLASS_AUTH_RESOURCE, "PATCH"));
        form.add("permission", String.format("%s#%s", HostCredential.CLASS_AUTH_RESOURCE, "PUT"));
        form.add("permission", String.format("%s#%s", HostCredential.CLASS_AUTH_RESOURCE, "DELETE"));
        form.add("permission", String.format("%s#%s", HostCredential.CLASS_AUTH_RESOURCE, "Admin"));
        form.add("permission", String.format("%s#%s", HostCredential.CLASS_AUTH_RESOURCE, "SuperAdmin"));
        if(credentialResource != null)
          form.add("permission", String.format("%s-%s#%s", HostCredential.CLASS_AUTH_RESOURCE, credentialResource, "GET"));
        groups.stream().map(group -> {
              Matcher mPermission = Pattern.compile("^/(.*-?TENANT-([a-z0-9\\-]+))-(\\w+)$").matcher(group);
              return mPermission.find() ? mPermission : null;
            }).filter(v -> v != null).forEach(mPermission -> {
              form.add("permission", String.format("%s#%s", mPermission.group(1), mPermission.group(3)));
            });
        groups.stream().map(group -> {
              Matcher mPermission = Pattern.compile("^/(.*-?HOSTINVENTORY-([a-z0-9\\-]+))-(\\w+)$").matcher(group);
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
            JsonArray scopes = authorizationDecisionBody.stream().map(o -> (JsonObject)o).filter(o -> "HOSTCREDENTIAL".equals(o.getString("rsname"))).findFirst().map(decision -> ((JsonObject)decision).getJsonArray("scopes")).orElse(new JsonArray());
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
              authorizationDecisionBody.stream().map(o -> (JsonObject)o).filter(permission -> {
                    Matcher mPermission = Pattern.compile("^(.*-?HOSTINVENTORY-([a-z0-9\\-]+))$").matcher(permission.getString("rsname"));
                    return permission.getJsonArray("scopes").contains("GET")
                        && mPermission.find();
                  }).forEach(permission -> {
                    fqs.add(String.format("%s:%s", "credentialResource", permission.getString("rsname")));
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
              searchHostCredentialList(siteRequest, false, true, false, "GET").onSuccess(listHostCredential -> {
                response200SearchPageHostCredential(listHostCredential).onSuccess(response -> {
                  eventHandler.handle(Future.succeededFuture(response));
                  LOG.debug(String.format("searchpageHostCredential succeeded. "));
                }).onFailure(ex -> {
                  LOG.error(String.format("searchpageHostCredential failed. "), ex);
                  error(siteRequest, eventHandler, ex);
                });
              }).onFailure(ex -> {
                LOG.error(String.format("searchpageHostCredential failed. "), ex);
                error(siteRequest, eventHandler, ex);
              });
            }
          } catch(Exception ex) {
            LOG.error(String.format("searchpageHostCredential failed. "), ex);
            error(null, eventHandler, ex);
          }
        });
      } catch(Exception ex) {
        LOG.error(String.format("searchpageHostCredential failed. "), ex);
        error(null, eventHandler, ex);
      }
    }).onFailure(ex -> {
      if("Inactive Token".equals(ex.getMessage()) || StringUtils.startsWith(ex.getMessage(), "invalid_grant:")) {
        try {
          eventHandler.handle(Future.succeededFuture(new ServiceResponse(302, "Found", null, MultiMap.caseInsensitiveMultiMap().add(HttpHeaders.LOCATION, "/logout?redirect_uri=" + URLEncoder.encode(serviceRequest.getExtra().getString("uri"), "UTF-8")))));
        } catch(Exception ex2) {
          LOG.error(String.format("searchpageHostCredential failed. ", ex2));
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
        LOG.error(String.format("searchpageHostCredential failed. "), ex);
        error(null, eventHandler, ex);
      }
    });
    }).onFailure(ex -> {
      if("Inactive Token".equals(ex.getMessage()) || StringUtils.startsWith(ex.getMessage(), "invalid_grant:")) {
        try {
          eventHandler.handle(Future.succeededFuture(new ServiceResponse(302, "Found", null, MultiMap.caseInsensitiveMultiMap().add(HttpHeaders.LOCATION, "/logout?redirect_uri=" + URLEncoder.encode(serviceRequest.getExtra().getString("uri"), "UTF-8")))));
        } catch(Exception ex2) {
          LOG.error(String.format("searchpageHostCredential failed. ", ex2));
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
        LOG.error(String.format("searchpageHostCredential failed. "), ex);
        error(null, eventHandler, ex);
      }
    });
  }

  public void searchpageHostCredentialPageInit(JsonObject ctx, HostCredentialPage page, SearchList<HostCredential> listHostCredential, Promise<Void> promise) {
    String siteBaseUrl = config.getString(ComputateConfigKeys.SITE_BASE_URL);

    ctx.put("enUSUrlSearchPage", String.format("%s%s", siteBaseUrl, "/en-us/search/host-credential"));
    ctx.put("enUSUrlPage", String.format("%s%s", siteBaseUrl, "/en-us/search/host-credential"));
    ctx.put("enUSUrlDisplayPage", Optional.ofNullable(page.getResult()).map(o -> o.getDisplayPage()));
    ctx.put("enUSUrlEditPage", Optional.ofNullable(page.getResult()).map(o -> o.getEditPage()));
    ctx.put("enUSUrlUserPage", Optional.ofNullable(page.getResult()).map(o -> o.getUserPage()));
    ctx.put("enUSUrlDownload", Optional.ofNullable(page.getResult()).map(o -> o.getDownload()));

    promise.complete();
  }

  public String templateUriSearchPageHostCredential(ServiceRequest serviceRequest, HostCredential result) {
    return "en-us/search/host-credential/HostCredentialSearchPage.htm";
  }
  public void templateSearchPageHostCredential(JsonObject ctx, HostCredentialPage page, SearchList<HostCredential> listHostCredential, Promise<String> promise) {
    try {
      SiteRequest siteRequest = listHostCredential.getSiteRequest_(SiteRequest.class);
      ServiceRequest serviceRequest = siteRequest.getServiceRequest();
      HostCredential result = listHostCredential.first();
      String pageTemplateUri = templateUriSearchPageHostCredential(serviceRequest, result);
      String siteTemplatePath = config.getString(ComputateConfigKeys.TEMPLATE_PATH);
      Path resourceTemplatePath = Path.of(siteTemplatePath, pageTemplateUri);
      if(result == null || !Files.exists(resourceTemplatePath)) {
        String template = Files.readString(Path.of(siteTemplatePath, "en-us/search/host-credential/HostCredentialSearchPage.htm"), Charset.forName("UTF-8"));
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
      LOG.error(String.format("templateSearchPageHostCredential failed. "), ex);
      ExceptionUtils.rethrow(ex);
    }
  }
  public Future<ServiceResponse> response200SearchPageHostCredential(SearchList<HostCredential> listHostCredential) {
    Promise<ServiceResponse> promise = Promise.promise();
    try {
      SiteRequest siteRequest = listHostCredential.getSiteRequest_(SiteRequest.class);
      HostCredentialPage page = new HostCredentialPage();
      MultiMap requestHeaders = MultiMap.caseInsensitiveMultiMap();
      siteRequest.setRequestHeaders(requestHeaders);

      if(listHostCredential.size() >= 1)
        siteRequest.setRequestPk(listHostCredential.get(0).getPk());
      page.setSearchListHostCredential_(listHostCredential);
      page.setSiteRequest_(siteRequest);
      page.setServiceRequest(siteRequest.getServiceRequest());
      page.setWebClient(webClient);
      page.setVertx(vertx);
      page.promiseDeepHostCredentialPage(siteRequest).onSuccess(a -> {
        try {
          JsonObject ctx = ConfigKeys.getPageContext(config);
          ctx.mergeIn(JsonObject.mapFrom(page));
          Promise<Void> promise1 = Promise.promise();
          searchpageHostCredentialPageInit(ctx, page, listHostCredential, promise1);
          promise1.future().onSuccess(b -> {
            Promise<String> promise2 = Promise.promise();
            templateSearchPageHostCredential(ctx, page, listHostCredential, promise2);
            promise2.future().onSuccess(renderedTemplate -> {
              try {
                Buffer buffer = Buffer.buffer(renderedTemplate);
                promise.complete(new ServiceResponse(200, "OK", buffer, requestHeaders));
              } catch(Throwable ex) {
                LOG.error(String.format("response200SearchPageHostCredential failed. "), ex);
                promise.fail(ex);
              }
            }).onFailure(ex -> {
              promise.fail(ex);
            });
          }).onFailure(ex -> {
            promise.tryFail(ex);
          });
        } catch(Exception ex) {
          LOG.error(String.format("response200SearchPageHostCredential failed. "), ex);
          promise.tryFail(ex);
        }
      }).onFailure(ex -> {
        promise.tryFail(ex);
      });
    } catch(Exception ex) {
      LOG.error(String.format("response200SearchPageHostCredential failed. "), ex);
      promise.tryFail(ex);
    }
    return promise.future();
  }
  public void responsePivotSearchPageHostCredential(List<SolrResponse.Pivot> pivots, JsonArray pivotArray) {
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
          responsePivotSearchPageHostCredential(pivotFields2, pivotArray2);
        }
      }
    }
  }

  // EditPage //

  @Override
  public void editpageHostCredential(ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler) {
    Boolean classPublicRead = false;
    user(serviceRequest, SiteRequest.class, SiteUser.class, SiteUser.getClassApiAddress(), "postSiteUserFuture", "patchSiteUserFuture", classPublicRead).onSuccess(siteRequest -> {
      try {
        siteRequest.setLang("enUS");
        String credentialResource = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("credentialResource");
        String HOSTCREDENTIAL = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("HOSTCREDENTIAL");
        List<String> groups = Optional.ofNullable(siteRequest.getGroups()).orElse(new ArrayList<>());
        MultiMap form = MultiMap.caseInsensitiveMultiMap();
        form.add("grant_type", "urn:ietf:params:oauth:grant-type:uma-ticket");
        form.add("audience", config.getString(ComputateConfigKeys.AUTH_CLIENT));
        form.add("response_mode", "permissions");
        form.add("permission", String.format("%s#%s", HostCredential.CLASS_AUTH_RESOURCE, "GET"));
        form.add("permission", String.format("%s#%s", HostCredential.CLASS_AUTH_RESOURCE, "POST"));
        form.add("permission", String.format("%s#%s", HostCredential.CLASS_AUTH_RESOURCE, "PATCH"));
        form.add("permission", String.format("%s#%s", HostCredential.CLASS_AUTH_RESOURCE, "PUT"));
        form.add("permission", String.format("%s#%s", HostCredential.CLASS_AUTH_RESOURCE, "DELETE"));
        form.add("permission", String.format("%s#%s", HostCredential.CLASS_AUTH_RESOURCE, "Admin"));
        form.add("permission", String.format("%s#%s", HostCredential.CLASS_AUTH_RESOURCE, "SuperAdmin"));
        if(credentialResource != null)
          form.add("permission", String.format("%s-%s#%s", HostCredential.CLASS_AUTH_RESOURCE, credentialResource, "GET"));
        groups.stream().map(group -> {
              Matcher mPermission = Pattern.compile("^/(.*-?TENANT-([a-z0-9\\-]+))-(\\w+)$").matcher(group);
              return mPermission.find() ? mPermission : null;
            }).filter(v -> v != null).forEach(mPermission -> {
              form.add("permission", String.format("%s#%s", mPermission.group(1), mPermission.group(3)));
            });
        groups.stream().map(group -> {
              Matcher mPermission = Pattern.compile("^/(.*-?HOSTINVENTORY-([a-z0-9\\-]+))-(\\w+)$").matcher(group);
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
            JsonArray scopes = authorizationDecisionBody.stream().map(o -> (JsonObject)o).filter(o -> "HOSTCREDENTIAL".equals(o.getString("rsname"))).findFirst().map(decision -> ((JsonObject)decision).getJsonArray("scopes")).orElse(new JsonArray());
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
              authorizationDecisionBody.stream().map(o -> (JsonObject)o).filter(permission -> {
                    Matcher mPermission = Pattern.compile("^(.*-?HOSTINVENTORY-([a-z0-9\\-]+))$").matcher(permission.getString("rsname"));
                    return permission.getJsonArray("scopes").contains("GET")
                        && mPermission.find();
                  }).forEach(permission -> {
                    fqs.add(String.format("%s:%s", "credentialResource", permission.getString("rsname")));
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
              searchHostCredentialList(siteRequest, false, true, false, "GET").onSuccess(listHostCredential -> {
                response200EditPageHostCredential(listHostCredential).onSuccess(response -> {
                  eventHandler.handle(Future.succeededFuture(response));
                  LOG.debug(String.format("editpageHostCredential succeeded. "));
                }).onFailure(ex -> {
                  LOG.error(String.format("editpageHostCredential failed. "), ex);
                  error(siteRequest, eventHandler, ex);
                });
              }).onFailure(ex -> {
                LOG.error(String.format("editpageHostCredential failed. "), ex);
                error(siteRequest, eventHandler, ex);
            });
            }
          } catch(Exception ex) {
            LOG.error(String.format("editpageHostCredential failed. "), ex);
            error(null, eventHandler, ex);
          }
        });
      } catch(Exception ex) {
        LOG.error(String.format("editpageHostCredential failed. "), ex);
        error(null, eventHandler, ex);
      }
    }).onFailure(ex -> {
      if("Inactive Token".equals(ex.getMessage()) || StringUtils.startsWith(ex.getMessage(), "invalid_grant:")) {
        try {
          eventHandler.handle(Future.succeededFuture(new ServiceResponse(302, "Found", null, MultiMap.caseInsensitiveMultiMap().add(HttpHeaders.LOCATION, "/logout?redirect_uri=" + URLEncoder.encode(serviceRequest.getExtra().getString("uri"), "UTF-8")))));
        } catch(Exception ex2) {
          LOG.error(String.format("editpageHostCredential failed. ", ex2));
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
        LOG.error(String.format("editpageHostCredential failed. "), ex);
        error(null, eventHandler, ex);
      }
    });
  }

  public void editpageHostCredentialPageInit(JsonObject ctx, HostCredentialPage page, SearchList<HostCredential> listHostCredential, Promise<Void> promise) {
    String siteBaseUrl = config.getString(ComputateConfigKeys.SITE_BASE_URL);

    ctx.put("enUSUrlSearchPage", String.format("%s%s", siteBaseUrl, "/en-us/search/host-credential"));
    ctx.put("enUSUrlDisplayPage", Optional.ofNullable(page.getResult()).map(o -> o.getDisplayPage()));
    ctx.put("enUSUrlEditPage", Optional.ofNullable(page.getResult()).map(o -> o.getEditPage()));
    ctx.put("enUSUrlPage", Optional.ofNullable(page.getResult()).map(o -> o.getEditPage()));
    ctx.put("enUSUrlUserPage", Optional.ofNullable(page.getResult()).map(o -> o.getUserPage()));
    ctx.put("enUSUrlDownload", Optional.ofNullable(page.getResult()).map(o -> o.getDownload()));

    promise.complete();
  }

  public String templateUriEditPageHostCredential(ServiceRequest serviceRequest, HostCredential result) {
    return "en-us/edit/host-credential/HostCredentialEditPage.htm";
  }
  public void templateEditPageHostCredential(JsonObject ctx, HostCredentialPage page, SearchList<HostCredential> listHostCredential, Promise<String> promise) {
    try {
      SiteRequest siteRequest = listHostCredential.getSiteRequest_(SiteRequest.class);
      ServiceRequest serviceRequest = siteRequest.getServiceRequest();
      HostCredential result = listHostCredential.first();
      String pageTemplateUri = templateUriEditPageHostCredential(serviceRequest, result);
      String siteTemplatePath = config.getString(ComputateConfigKeys.TEMPLATE_PATH);
      Path resourceTemplatePath = Path.of(siteTemplatePath, pageTemplateUri);
      if(result == null || !Files.exists(resourceTemplatePath)) {
        String template = Files.readString(Path.of(siteTemplatePath, "en-us/edit/host-credential/HostCredentialEditPage.htm"), Charset.forName("UTF-8"));
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
      LOG.error(String.format("templateEditPageHostCredential failed. "), ex);
      ExceptionUtils.rethrow(ex);
    }
  }
  public Future<ServiceResponse> response200EditPageHostCredential(SearchList<HostCredential> listHostCredential) {
    Promise<ServiceResponse> promise = Promise.promise();
    try {
      SiteRequest siteRequest = listHostCredential.getSiteRequest_(SiteRequest.class);
      HostCredentialPage page = new HostCredentialPage();
      MultiMap requestHeaders = MultiMap.caseInsensitiveMultiMap();
      siteRequest.setRequestHeaders(requestHeaders);

      if(listHostCredential.size() >= 1)
        siteRequest.setRequestPk(listHostCredential.get(0).getPk());
      page.setSearchListHostCredential_(listHostCredential);
      page.setSiteRequest_(siteRequest);
      page.setServiceRequest(siteRequest.getServiceRequest());
      page.setWebClient(webClient);
      page.setVertx(vertx);
      page.promiseDeepHostCredentialPage(siteRequest).onSuccess(a -> {
        try {
          JsonObject ctx = ConfigKeys.getPageContext(config);
          ctx.mergeIn(JsonObject.mapFrom(page));
          Promise<Void> promise1 = Promise.promise();
          editpageHostCredentialPageInit(ctx, page, listHostCredential, promise1);
          promise1.future().onSuccess(b -> {
            Promise<String> promise2 = Promise.promise();
            templateEditPageHostCredential(ctx, page, listHostCredential, promise2);
            promise2.future().onSuccess(renderedTemplate -> {
              try {
                Buffer buffer = Buffer.buffer(renderedTemplate);
                promise.complete(new ServiceResponse(200, "OK", buffer, requestHeaders));
              } catch(Throwable ex) {
                LOG.error(String.format("response200EditPageHostCredential failed. "), ex);
                promise.fail(ex);
              }
            }).onFailure(ex -> {
              promise.fail(ex);
            });
          }).onFailure(ex -> {
            promise.tryFail(ex);
          });
        } catch(Exception ex) {
          LOG.error(String.format("response200EditPageHostCredential failed. "), ex);
          promise.tryFail(ex);
        }
      }).onFailure(ex -> {
        promise.tryFail(ex);
      });
    } catch(Exception ex) {
      LOG.error(String.format("response200EditPageHostCredential failed. "), ex);
      promise.tryFail(ex);
    }
    return promise.future();
  }
  public void responsePivotEditPageHostCredential(List<SolrResponse.Pivot> pivots, JsonArray pivotArray) {
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
          responsePivotEditPageHostCredential(pivotFields2, pivotArray2);
        }
      }
    }
  }

  // UserPage //

  @Override
  public void userpageHostCredential(ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler) {
    Boolean classPublicRead = false;
    user(serviceRequest, SiteRequest.class, SiteUser.class, SiteUser.getClassApiAddress(), "postSiteUserFuture", "patchSiteUserFuture", classPublicRead).onSuccess(siteRequest -> {
      try {
        siteRequest.setLang("enUS");
        String credentialResource = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("credentialResource");
        String HOSTCREDENTIAL = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("HOSTCREDENTIAL");
        List<String> groups = Optional.ofNullable(siteRequest.getGroups()).orElse(new ArrayList<>());
        MultiMap form = MultiMap.caseInsensitiveMultiMap();
        form.add("grant_type", "urn:ietf:params:oauth:grant-type:uma-ticket");
        form.add("audience", config.getString(ComputateConfigKeys.AUTH_CLIENT));
        form.add("response_mode", "permissions");
        form.add("permission", String.format("%s#%s", HostCredential.CLASS_AUTH_RESOURCE, "GET"));
        form.add("permission", String.format("%s#%s", HostCredential.CLASS_AUTH_RESOURCE, "POST"));
        form.add("permission", String.format("%s#%s", HostCredential.CLASS_AUTH_RESOURCE, "PATCH"));
        form.add("permission", String.format("%s#%s", HostCredential.CLASS_AUTH_RESOURCE, "PUT"));
        form.add("permission", String.format("%s#%s", HostCredential.CLASS_AUTH_RESOURCE, "DELETE"));
        form.add("permission", String.format("%s#%s", HostCredential.CLASS_AUTH_RESOURCE, "Admin"));
        form.add("permission", String.format("%s#%s", HostCredential.CLASS_AUTH_RESOURCE, "SuperAdmin"));
        if(credentialResource != null)
          form.add("permission", String.format("%s-%s#%s", HostCredential.CLASS_AUTH_RESOURCE, credentialResource, "GET"));
        groups.stream().map(group -> {
              Matcher mPermission = Pattern.compile("^/(.*-?TENANT-([a-z0-9\\-]+))-(\\w+)$").matcher(group);
              return mPermission.find() ? mPermission : null;
            }).filter(v -> v != null).forEach(mPermission -> {
              form.add("permission", String.format("%s#%s", mPermission.group(1), mPermission.group(3)));
            });
        groups.stream().map(group -> {
              Matcher mPermission = Pattern.compile("^/(.*-?HOSTINVENTORY-([a-z0-9\\-]+))-(\\w+)$").matcher(group);
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
            JsonArray scopes = authorizationDecisionBody.stream().map(o -> (JsonObject)o).filter(o -> "HOSTCREDENTIAL".equals(o.getString("rsname"))).findFirst().map(decision -> ((JsonObject)decision).getJsonArray("scopes")).orElse(new JsonArray());
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
              authorizationDecisionBody.stream().map(o -> (JsonObject)o).filter(permission -> {
                    Matcher mPermission = Pattern.compile("^(.*-?HOSTINVENTORY-([a-z0-9\\-]+))$").matcher(permission.getString("rsname"));
                    return permission.getJsonArray("scopes").contains("GET")
                        && mPermission.find();
                  }).forEach(permission -> {
                    fqs.add(String.format("%s:%s", "credentialResource", permission.getString("rsname")));
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
              searchHostCredentialList(siteRequest, false, true, false, "GET").onSuccess(listHostCredential -> {
                response200UserPageHostCredential(listHostCredential).onSuccess(response -> {
                  eventHandler.handle(Future.succeededFuture(response));
                  LOG.debug(String.format("userpageHostCredential succeeded. "));
                }).onFailure(ex -> {
                  LOG.error(String.format("userpageHostCredential failed. "), ex);
                  error(siteRequest, eventHandler, ex);
                });
              }).onFailure(ex -> {
                LOG.error(String.format("userpageHostCredential failed. "), ex);
                error(siteRequest, eventHandler, ex);
            });
            }
          } catch(Exception ex) {
            LOG.error(String.format("userpageHostCredential failed. "), ex);
            error(null, eventHandler, ex);
          }
        });
      } catch(Exception ex) {
        LOG.error(String.format("userpageHostCredential failed. "), ex);
        error(null, eventHandler, ex);
      }
    }).onFailure(ex -> {
      if("Inactive Token".equals(ex.getMessage()) || StringUtils.startsWith(ex.getMessage(), "invalid_grant:")) {
        try {
          eventHandler.handle(Future.succeededFuture(new ServiceResponse(302, "Found", null, MultiMap.caseInsensitiveMultiMap().add(HttpHeaders.LOCATION, "/logout?redirect_uri=" + URLEncoder.encode(serviceRequest.getExtra().getString("uri"), "UTF-8")))));
        } catch(Exception ex2) {
          LOG.error(String.format("userpageHostCredential failed. ", ex2));
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
        LOG.error(String.format("userpageHostCredential failed. "), ex);
        error(null, eventHandler, ex);
      }
    });
  }

  public void userpageHostCredentialPageInit(JsonObject ctx, HostCredentialPage page, SearchList<HostCredential> listHostCredential, Promise<Void> promise) {
    String siteBaseUrl = config.getString(ComputateConfigKeys.SITE_BASE_URL);

    ctx.put("enUSUrlSearchPage", String.format("%s%s", siteBaseUrl, "/en-us/search/host-credential"));
    ctx.put("enUSUrlDisplayPage", Optional.ofNullable(page.getResult()).map(o -> o.getDisplayPage()));
    ctx.put("enUSUrlEditPage", Optional.ofNullable(page.getResult()).map(o -> o.getEditPage()));
    ctx.put("enUSUrlUserPage", Optional.ofNullable(page.getResult()).map(o -> o.getUserPage()));
    ctx.put("enUSUrlPage", Optional.ofNullable(page.getResult()).map(o -> o.getUserPage()));
    ctx.put("enUSUrlDownload", Optional.ofNullable(page.getResult()).map(o -> o.getDownload()));

    promise.complete();
  }

  public String templateUriUserPageHostCredential(ServiceRequest serviceRequest, HostCredential result) {
    return String.format("%s.htm", StringUtils.substringBefore(serviceRequest.getExtra().getString("uri").substring(1), "?"));
  }
  public void templateUserPageHostCredential(JsonObject ctx, HostCredentialPage page, SearchList<HostCredential> listHostCredential, Promise<String> promise) {
    try {
      SiteRequest siteRequest = listHostCredential.getSiteRequest_(SiteRequest.class);
      ServiceRequest serviceRequest = siteRequest.getServiceRequest();
      HostCredential result = listHostCredential.first();
      String pageTemplateUri = templateUriUserPageHostCredential(serviceRequest, result);
      String siteTemplatePath = config.getString(ComputateConfigKeys.TEMPLATE_PATH);
      Path resourceTemplatePath = Path.of(siteTemplatePath, pageTemplateUri);
      if(result == null || !Files.exists(resourceTemplatePath)) {
        String template = Files.readString(Path.of(siteTemplatePath, "%s.htm"), Charset.forName("UTF-8"));
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
      LOG.error(String.format("templateUserPageHostCredential failed. "), ex);
      ExceptionUtils.rethrow(ex);
    }
  }
  public Future<ServiceResponse> response200UserPageHostCredential(SearchList<HostCredential> listHostCredential) {
    Promise<ServiceResponse> promise = Promise.promise();
    try {
      SiteRequest siteRequest = listHostCredential.getSiteRequest_(SiteRequest.class);
      HostCredentialPage page = new HostCredentialPage();
      MultiMap requestHeaders = MultiMap.caseInsensitiveMultiMap();
      siteRequest.setRequestHeaders(requestHeaders);

      if(listHostCredential.size() >= 1)
        siteRequest.setRequestPk(listHostCredential.get(0).getPk());
      page.setSearchListHostCredential_(listHostCredential);
      page.setSiteRequest_(siteRequest);
      page.setServiceRequest(siteRequest.getServiceRequest());
      page.setWebClient(webClient);
      page.setVertx(vertx);
      page.promiseDeepHostCredentialPage(siteRequest).onSuccess(a -> {
        try {
          JsonObject ctx = ConfigKeys.getPageContext(config);
          ctx.mergeIn(JsonObject.mapFrom(page));
          Promise<Void> promise1 = Promise.promise();
          userpageHostCredentialPageInit(ctx, page, listHostCredential, promise1);
          promise1.future().onSuccess(b -> {
            Promise<String> promise2 = Promise.promise();
            templateUserPageHostCredential(ctx, page, listHostCredential, promise2);
            promise2.future().onSuccess(renderedTemplate -> {
              try {
                Buffer buffer = Buffer.buffer(renderedTemplate);
                promise.complete(new ServiceResponse(200, "OK", buffer, requestHeaders));
              } catch(Throwable ex) {
                LOG.error(String.format("response200UserPageHostCredential failed. "), ex);
                promise.fail(ex);
              }
            }).onFailure(ex -> {
              promise.fail(ex);
            });
          }).onFailure(ex -> {
            promise.tryFail(ex);
          });
        } catch(Exception ex) {
          LOG.error(String.format("response200UserPageHostCredential failed. "), ex);
          promise.tryFail(ex);
        }
      }).onFailure(ex -> {
        promise.tryFail(ex);
      });
    } catch(Exception ex) {
      LOG.error(String.format("response200UserPageHostCredential failed. "), ex);
      promise.tryFail(ex);
    }
    return promise.future();
  }
  public void responsePivotUserPageHostCredential(List<SolrResponse.Pivot> pivots, JsonArray pivotArray) {
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
          responsePivotUserPageHostCredential(pivotFields2, pivotArray2);
        }
      }
    }
  }

  // DELETEFilter //

  @Override
  public void deletefilterHostCredential(JsonObject body, ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler) {
    LOG.debug(String.format("deletefilterHostCredential started. "));
    Boolean classPublicRead = false;
    user(serviceRequest, SiteRequest.class, SiteUser.class, SiteUser.getClassApiAddress(), "postSiteUserFuture", "patchSiteUserFuture", classPublicRead).onSuccess(siteRequest -> {
      try {
        siteRequest.setLang("enUS");
        String credentialResource = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("credentialResource");
        String HOSTCREDENTIAL = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("HOSTCREDENTIAL");
        List<String> groups = Optional.ofNullable(siteRequest.getGroups()).orElse(new ArrayList<>());
        MultiMap form = MultiMap.caseInsensitiveMultiMap();
        form.add("grant_type", "urn:ietf:params:oauth:grant-type:uma-ticket");
        form.add("audience", config.getString(ComputateConfigKeys.AUTH_CLIENT));
        form.add("response_mode", "permissions");
        form.add("permission", String.format("%s#%s", HostCredential.CLASS_AUTH_RESOURCE, "GET"));
        form.add("permission", String.format("%s#%s", HostCredential.CLASS_AUTH_RESOURCE, "POST"));
        form.add("permission", String.format("%s#%s", HostCredential.CLASS_AUTH_RESOURCE, "PATCH"));
        form.add("permission", String.format("%s#%s", HostCredential.CLASS_AUTH_RESOURCE, "PUT"));
        form.add("permission", String.format("%s#%s", HostCredential.CLASS_AUTH_RESOURCE, "DELETE"));
        form.add("permission", String.format("%s#%s", HostCredential.CLASS_AUTH_RESOURCE, "Admin"));
        form.add("permission", String.format("%s#%s", HostCredential.CLASS_AUTH_RESOURCE, "SuperAdmin"));
        if(credentialResource != null)
          form.add("permission", String.format("%s-%s#%s", HostCredential.CLASS_AUTH_RESOURCE, credentialResource, "DELETE"));
        groups.stream().map(group -> {
              Matcher mPermission = Pattern.compile("^/(.*-?TENANT-([a-z0-9\\-]+))-(\\w+)$").matcher(group);
              return mPermission.find() ? mPermission : null;
            }).filter(v -> v != null).forEach(mPermission -> {
              form.add("permission", String.format("%s#%s", mPermission.group(1), mPermission.group(3)));
            });
        groups.stream().map(group -> {
              Matcher mPermission = Pattern.compile("^/(.*-?HOSTINVENTORY-([a-z0-9\\-]+))-(\\w+)$").matcher(group);
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
            JsonArray scopes = authorizationDecisionBody.stream().map(o -> (JsonObject)o).filter(o -> "HOSTCREDENTIAL".equals(o.getString("rsname"))).findFirst().map(decision -> ((JsonObject)decision).getJsonArray("scopes")).orElse(new JsonArray());
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
            authorizationDecisionBody.stream().map(o -> (JsonObject)o).filter(permission -> {
                  Matcher mPermission = Pattern.compile("^(.*-?HOSTINVENTORY-([a-z0-9\\-]+))$").matcher(permission.getString("rsname"));
                  return permission.getJsonArray("scopes").contains("DELETE")
                      && mPermission.find();
                }).forEach(permission -> {
                  fqs.add(String.format("%s:%s", "credentialResource", permission.getString("rsname")));
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
              searchHostCredentialList(siteRequest, false, true, true, "DELETE").onSuccess(listHostCredential -> {
                try {
                  ApiRequest apiRequest = new ApiRequest();
                  apiRequest.setRows(listHostCredential.getRequest().getRows());
                  apiRequest.setNumFound(listHostCredential.getResponse().getResponse().getNumFound());
                  apiRequest.setNumPATCH(0L);
                  apiRequest.initDeepApiRequest(siteRequest);
                  siteRequest.setApiRequest_(apiRequest);
                  if(apiRequest.getNumFound() == 1L)
                    apiRequest.setOriginal(listHostCredential.first());
                  apiRequest.setSolrId(Optional.ofNullable(listHostCredential.first()).map(o2 -> o2.getSolrId()).orElse(null));
                  eventBus.publish("websocketHostCredential", JsonObject.mapFrom(apiRequest).toString());

                  listDELETEFilterHostCredential(apiRequest, listHostCredential).onSuccess(e -> {
                    response200DELETEFilterHostCredential(siteRequest).onSuccess(response -> {
                      LOG.debug(String.format("deletefilterHostCredential succeeded. "));
                      eventHandler.handle(Future.succeededFuture(response));
                    }).onFailure(ex -> {
                      LOG.error(String.format("deletefilterHostCredential failed. "), ex);
                      error(siteRequest, eventHandler, ex);
                    });
                  }).onFailure(ex -> {
                    LOG.error(String.format("deletefilterHostCredential failed. "), ex);
                    error(siteRequest, eventHandler, ex);
                  });
                } catch(Exception ex) {
                  LOG.error(String.format("deletefilterHostCredential failed. "), ex);
                  error(siteRequest, eventHandler, ex);
                }
              }).onFailure(ex -> {
                LOG.error(String.format("deletefilterHostCredential failed. "), ex);
                error(siteRequest, eventHandler, ex);
              });
            }
          } catch(Exception ex) {
            LOG.error(String.format("deletefilterHostCredential failed. "), ex);
            error(null, eventHandler, ex);
          }
        });
      } catch(Exception ex) {
        LOG.error(String.format("deletefilterHostCredential failed. "), ex);
        error(null, eventHandler, ex);
      }
    }).onFailure(ex -> {
      if("Inactive Token".equals(ex.getMessage()) || StringUtils.startsWith(ex.getMessage(), "invalid_grant:")) {
        try {
          eventHandler.handle(Future.succeededFuture(new ServiceResponse(302, "Found", null, MultiMap.caseInsensitiveMultiMap().add(HttpHeaders.LOCATION, "/logout?redirect_uri=" + URLEncoder.encode(serviceRequest.getExtra().getString("uri"), "UTF-8")))));
        } catch(Exception ex2) {
          LOG.error(String.format("deletefilterHostCredential failed. ", ex2));
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
        LOG.error(String.format("deletefilterHostCredential failed. "), ex);
        error(null, eventHandler, ex);
      }
    });
  }

  public Future<Void> listDELETEFilterHostCredential(ApiRequest apiRequest, SearchList<HostCredential> listHostCredential) {
    Promise<Void> promise = Promise.promise();
    List<Future> futures = new ArrayList<>();
    SiteRequest siteRequest = listHostCredential.getSiteRequest_(SiteRequest.class);
    listHostCredential.getList().forEach(o -> {
      SiteRequest siteRequest2 = generateSiteRequest(siteRequest.getUser(), siteRequest.getUserPrincipal(), siteRequest.getServiceRequest(), siteRequest.getJsonObject(), SiteRequest.class);
      siteRequest2.setScopes(siteRequest.getScopes());
      o.setSiteRequest_(siteRequest2);
      siteRequest2.setApiRequest_(siteRequest.getApiRequest_());
      JsonObject jsonObject = JsonObject.mapFrom(o);
      HostCredential o2 = jsonObject.mapTo(HostCredential.class);
      o2.setSiteRequest_(siteRequest2);
      futures.add(Future.future(promise1 -> {
        deletefilterHostCredentialFuture(o).onSuccess(a -> {
          promise1.complete();
        }).onFailure(ex -> {
          LOG.error(String.format("listDELETEFilterHostCredential failed. "), ex);
          promise1.tryFail(ex);
        });
      }));
    });
    CompositeFuture.all(futures).onSuccess( a -> {
      listHostCredential.next().onSuccess(next -> {
        if(next) {
          listDELETEFilterHostCredential(apiRequest, listHostCredential).onSuccess(b -> {
            promise.complete();
          }).onFailure(ex -> {
            LOG.error(String.format("listDELETEFilterHostCredential failed. "), ex);
            promise.tryFail(ex);
          });
        } else {
          promise.complete();
        }
      }).onFailure(ex -> {
        LOG.error(String.format("listDELETEFilterHostCredential failed. "), ex);
        promise.tryFail(ex);
      });
    }).onFailure(ex -> {
      LOG.error(String.format("listDELETEFilterHostCredential failed. "), ex);
      promise.tryFail(ex);
    });
    return promise.future();
  }

  @Override
  public void deletefilterHostCredentialFuture(JsonObject body, ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler) {
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
        searchHostCredentialList(siteRequest, false, true, true, "DELETE").onSuccess(listHostCredential -> {
          try {
            HostCredential o = listHostCredential.first();
            if(o != null && listHostCredential.getResponse().getResponse().getNumFound() == 1) {
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
              apiRequest.setId(Optional.ofNullable(listHostCredential.first()).map(o2 -> o2.getCredentialResource().toString()).orElse(null));
              apiRequest.setSolrId(Optional.ofNullable(listHostCredential.first()).map(o2 -> o2.getSolrId()).orElse(null));
              deletefilterHostCredentialFuture(o).onSuccess(o2 -> {
                eventHandler.handle(Future.succeededFuture(ServiceResponse.completedWithJson(Buffer.buffer(new JsonObject().encodePrettily()))));
              }).onFailure(ex -> {
                eventHandler.handle(Future.failedFuture(ex));
              });
            } else {
              eventHandler.handle(Future.succeededFuture(ServiceResponse.completedWithJson(Buffer.buffer(new JsonObject().encodePrettily()))));
            }
          } catch(Exception ex) {
            LOG.error(String.format("deletefilterHostCredential failed. "), ex);
            error(siteRequest, eventHandler, ex);
          }
        }).onFailure(ex -> {
          LOG.error(String.format("deletefilterHostCredential failed. "), ex);
          error(siteRequest, eventHandler, ex);
        });
      } catch(Exception ex) {
        LOG.error(String.format("deletefilterHostCredential failed. "), ex);
        error(null, eventHandler, ex);
      }
    }).onFailure(ex -> {
      LOG.error(String.format("deletefilterHostCredential failed. "), ex);
      error(null, eventHandler, ex);
    });
  }

  public Future<HostCredential> deletefilterHostCredentialFuture(HostCredential o) {
    SiteRequest siteRequest = o.getSiteRequest_();
    Promise<HostCredential> promise = Promise.promise();

    try {
      ApiRequest apiRequest = siteRequest.getApiRequest_();
      Promise<HostCredential> promise1 = Promise.promise();
      pgPool.withTransaction(sqlConnection -> {
        siteRequest.setSqlConnection(sqlConnection);
        varsHostCredential(siteRequest).onSuccess(a -> {
          sqlDELETEFilterHostCredential(o).onSuccess(hostCredential -> {
            relateHostCredential(o).onSuccess(d -> {
              unindexHostCredential(o).onSuccess(o2 -> {
                if(apiRequest != null) {
                  apiRequest.setNumPATCH(apiRequest.getNumPATCH() + 1);
                  if(apiRequest.getNumFound() == 1L && Optional.ofNullable(siteRequest.getJsonObject()).map(json -> json.size() > 0).orElse(false)) {
                    o2.apiRequestHostCredential();
                    if(apiRequest.getVars().size() > 0 && Optional.ofNullable(siteRequest.getRequestVars().get("refresh")).map(refresh -> !refresh.equals("false")).orElse(true))
                      eventBus.publish("websocketHostCredential", JsonObject.mapFrom(apiRequest).toString());
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
      }).compose(hostCredential -> {
        Promise<HostCredential> promise2 = Promise.promise();
        refreshHostCredential(o).onSuccess(a -> {
          promise2.complete(o);
        }).onFailure(ex -> {
          promise2.tryFail(ex);
        });
        return promise2.future();
      }).onSuccess(hostCredential -> {
        promise.complete(hostCredential);
      }).onFailure(ex -> {
        promise.tryFail(ex);
      });
    } catch(Exception ex) {
      LOG.error(String.format("deletefilterHostCredentialFuture failed. "), ex);
      promise.tryFail(ex);
    }
    return promise.future();
  }

  public Future<Void> sqlDELETEFilterHostCredential(HostCredential o) {
    Promise<Void> promise = Promise.promise();
    try {
      SiteRequest siteRequest = o.getSiteRequest_();
      ApiRequest apiRequest = siteRequest.getApiRequest_();
      List<String> solrIds = Optional.ofNullable(apiRequest).map(r -> r.getSolrIds()).orElse(new ArrayList<>());
      List<String> classes = Optional.ofNullable(apiRequest).map(r -> r.getClasses()).orElse(new ArrayList<>());
      SqlConnection sqlConnection = siteRequest.getSqlConnection();
      Integer num = 1;
      StringBuilder bSql = new StringBuilder("DELETE FROM HostCredential ");
      List<Object> bParams = new ArrayList<Object>();
      Long pk = o.getPk();
      JsonObject jsonObject = siteRequest.getJsonObject();
      HostCredential o2 = new HostCredential();
      o2.setSiteRequest_(siteRequest);
      List<Future> futures1 = new ArrayList<>();
      List<Future> futures2 = new ArrayList<>();

      if(jsonObject != null) {
        Set<String> entityVars = jsonObject.fieldNames();
        for(String entityVar : entityVars) {
          switch(entityVar) {
          case HostCredential.VAR_tenantResource:
            Optional.ofNullable(jsonObject.getString(entityVar)).ifPresent(val -> {
              futures1.add(Future.future(promise2 -> {
                searchModel(siteRequest).query(Tenant.varIndexedTenant(Tenant.VAR_tenantResource), Tenant.class, val).onSuccess(o3 -> {
                  String solrId2 = Optional.ofNullable(o3).map(o4 -> o4.getSolrId()).filter(solrId3 -> !solrIds.contains(solrId3)).orElse(null);
                  if(solrId2 != null) {
                    solrIds.add(solrId2);
                    classes.add("Tenant");
                  }
                  sql(siteRequest).update(HostCredential.class, pk).set(HostCredential.VAR_tenantResource, Tenant.class, null, null).onSuccess(a -> {
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
          RuntimeException ex2 = new RuntimeException("value HostCredential failed", ex);
          LOG.error(String.format("unrelateHostCredential failed. "), ex2);
          a.handle(Future.failedFuture(ex2));
        });
      }));
      CompositeFuture.all(futures1).onSuccess(a -> {
        CompositeFuture.all(futures2).onSuccess(b -> {
          promise.complete();
        }).onFailure(ex -> {
          LOG.error(String.format("sqlDELETEFilterHostCredential failed. "), ex);
          promise.tryFail(ex);
        });
      }).onFailure(ex -> {
        LOG.error(String.format("sqlDELETEFilterHostCredential failed. "), ex);
        promise.tryFail(ex);
      });
    } catch(Exception ex) {
      LOG.error(String.format("sqlDELETEFilterHostCredential failed. "), ex);
      promise.tryFail(ex);
    }
    return promise.future();
  }

  public Future<ServiceResponse> response200DELETEFilterHostCredential(SiteRequest siteRequest) {
    Promise<ServiceResponse> promise = Promise.promise();
    try {
      JsonObject json = new JsonObject();
      promise.complete(ServiceResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily())));
    } catch(Exception ex) {
      LOG.error(String.format("response200DELETEFilterHostCredential failed. "), ex);
      promise.tryFail(ex);
    }
    return promise.future();
  }

  // General //

  public Future<HostCredential> createHostCredential(SiteRequest siteRequest) {
    Promise<HostCredential> promise = Promise.promise();
    try {
      SqlConnection sqlConnection = siteRequest.getSqlConnection();
      String userId = siteRequest.getUserId();
      Long userKey = siteRequest.getUserKey();
      ZonedDateTime created = Optional.ofNullable(siteRequest.getJsonObject()).map(j -> j.getString("created")).map(s -> ZonedDateTime.parse(s, ComputateZonedDateTimeSerializer.ZONED_DATE_TIME_FORMATTER.withZone(ZoneId.of(config.getString(ConfigKeys.SITE_ZONE))))).orElse(ZonedDateTime.now(ZoneId.of(config.getString(ConfigKeys.SITE_ZONE))));

      sqlConnection.preparedQuery("INSERT INTO HostCredential(created, userKey) VALUES($1, $2) RETURNING pk")
          .collecting(Collectors.toList())
          .execute(Tuple.of(created.toOffsetDateTime(), userKey)).onSuccess(result -> {
        Row createLine = result.value().stream().findFirst().orElseGet(() -> null);
        Long pk = createLine.getLong(0);
        HostCredential o = new HostCredential();
        o.setPk(pk);
        o.setSiteRequest_(siteRequest);
        promise.complete(o);
      }).onFailure(ex -> {
        RuntimeException ex2 = new RuntimeException(ex);
        LOG.error("createHostCredential failed. ", ex2);
        promise.tryFail(ex2);
      });
    } catch(Exception ex) {
      LOG.error(String.format("createHostCredential failed. "), ex);
      promise.tryFail(ex);
    }
    return promise.future();
  }

  public void searchHostCredentialQ(SearchList<HostCredential> searchList, String entityVar, String valueIndexed, String varIndexed) {
    searchList.q(varIndexed + ":" + ("*".equals(valueIndexed) ? valueIndexed : SearchTool.escapeQueryChars(valueIndexed)));
    if(!"*".equals(entityVar)) {
    }
  }

  public String searchHostCredentialFq(SearchList<HostCredential> searchList, String entityVar, String valueIndexed, String varIndexed) {
    if(varIndexed == null)
      throw new RuntimeException(String.format("\"%s\" is not an indexed entity. ", entityVar));
    if(StringUtils.startsWith(valueIndexed, "[")) {
      String[] fqs = StringUtils.substringAfter(StringUtils.substringBeforeLast(valueIndexed, "]"), "[").split(" TO ");
      if(fqs.length != 2)
        throw new RuntimeException(String.format("\"%s\" invalid range query. ", valueIndexed));
      String fq1 = fqs[0].equals("*") ? fqs[0] : HostCredential.staticSearchFqForClass(entityVar, searchList.getSiteRequest_(SiteRequest.class), fqs[0]);
      String fq2 = fqs[1].equals("*") ? fqs[1] : HostCredential.staticSearchFqForClass(entityVar, searchList.getSiteRequest_(SiteRequest.class), fqs[1]);
       return varIndexed + ":[" + fq1 + " TO " + fq2 + "]";
    } else {
      return varIndexed + ":" + SearchTool.escapeQueryChars(HostCredential.staticSearchFqForClass(entityVar, searchList.getSiteRequest_(SiteRequest.class), valueIndexed)).replace("\\", "\\\\");
    }
  }

  public void searchHostCredentialSort(SearchList<HostCredential> searchList, String entityVar, String valueIndexed, String varIndexed) {
    if(varIndexed == null)
      throw new RuntimeException(String.format("\"%s\" is not an indexed entity. ", entityVar));
    searchList.sort(varIndexed, valueIndexed);
  }

  public void searchHostCredentialRows(SearchList<HostCredential> searchList, Long valueRows) {
      searchList.rows(valueRows != null ? valueRows : 10L);
  }

  public void searchHostCredentialStart(SearchList<HostCredential> searchList, Long valueStart) {
    searchList.start(valueStart);
  }

  public void searchHostCredentialVar(SearchList<HostCredential> searchList, String var, String value) {
    searchList.getSiteRequest_(SiteRequest.class).getRequestVars().put(var, value);
  }

  public void searchHostCredentialUri(SearchList<HostCredential> searchList) {
  }

  public Future<ServiceResponse> varsHostCredential(SiteRequest siteRequest) {
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
          LOG.error(String.format("searchHostCredential failed. "), ex);
          promise.tryFail(ex);
        }
      });
      promise.complete();
    } catch(Exception ex) {
      LOG.error(String.format("searchHostCredential failed. "), ex);
      promise.tryFail(ex);
    }
    return promise.future();
  }

  public Future<SearchList<HostCredential>> searchHostCredentialList(SiteRequest siteRequest, Boolean populate, Boolean store, Boolean modify, String scope) {
    Promise<SearchList<HostCredential>> promise = Promise.promise();
    try {
      ServiceRequest serviceRequest = siteRequest.getServiceRequest();
      String entityListStr = siteRequest.getServiceRequest().getParams().getJsonObject("query").getString("fl");
      String[] entityList = entityListStr == null ? null : entityListStr.split(",\\s*");
      SearchList<HostCredential> searchList = new SearchList<HostCredential>();
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
      searchList.setC(HostCredential.class);
      searchList.setSiteRequest_(siteRequest);
      searchList.facetMinCount(1);
      if(entityList != null) {
        for(String v : entityList) {
          searchList.fl(HostCredential.varIndexedHostCredential(v));
        }
      }

      String credentialResource = serviceRequest.getParams().getJsonObject("path").getString("credentialResource");
      if(credentialResource != null) {
        searchList.fq("credentialResource_docvalues_string:" + SearchTool.escapeQueryChars(credentialResource));
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
                varsIndexed[i] = HostCredential.varIndexedHostCredential(entityVar);
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
                  varIndexed = HostCredential.varIndexedHostCredential(entityVar);
                  String entityQ = searchHostCredentialFq(searchList, entityVar, valueIndexed, varIndexed);
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
                  varIndexed = HostCredential.varIndexedHostCredential(entityVar);
                  String entityFq = searchHostCredentialFq(searchList, entityVar, valueIndexed, varIndexed);
                  mFq.appendReplacement(sb, entityFq);
                }
                if(!sb.isEmpty()) {
                  mFq.appendTail(sb);
                  searchList.fq(sb.toString());
                }
              } else if(paramName.equals("sort")) {
                entityVar = StringUtils.trim(StringUtils.substringBefore((String)paramObject, " "));
                valueIndexed = StringUtils.trim(StringUtils.substringAfter((String)paramObject, " "));
                varIndexed = HostCredential.varIndexedHostCredential(entityVar);
                searchHostCredentialSort(searchList, entityVar, valueIndexed, varIndexed);
              } else if(paramName.equals("start")) {
                valueStart = paramObject instanceof Long ? (Long)paramObject : Long.parseLong(paramObject.toString());
                searchHostCredentialStart(searchList, valueStart);
              } else if(paramName.equals("rows")) {
                valueRows = paramObject instanceof Long ? (Long)paramObject : Long.parseLong(paramObject.toString());
                searchHostCredentialRows(searchList, valueRows);
              } else if(paramName.equals("stats")) {
                searchList.stats((Boolean)paramObject);
              } else if(paramName.equals("stats.field")) {
                Matcher mStats = Pattern.compile("(?:(\\{![^\\}]+\\}))?(.*)").matcher((String)paramObject);
                if(mStats.find()) {
                  String solrLocalParams = mStats.group(1);
                  entityVar = mStats.group(2).trim();
                  varIndexed = HostCredential.varIndexedHostCredential(entityVar);
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
                  varIndexed = HostCredential.varIndexedHostCredential(entityVar);
                  searchList.facetRange((solrLocalParams == null ? "" : solrLocalParams) + varIndexed);
                  facetRange = entityVar;
                }
              } else if(paramName.equals("facet.field")) {
                entityVar = (String)paramObject;
                varIndexed = HostCredential.varIndexedHostCredential(entityVar);
                if(varIndexed != null)
                  searchList.facetField(varIndexed);
              } else if(paramName.equals("var")) {
                entityVar = StringUtils.trim(StringUtils.substringBefore((String)paramObject, ":"));
                valueIndexed = URLDecoder.decode(StringUtils.trim(StringUtils.substringAfter((String)paramObject, ":")), "UTF-8");
                searchHostCredentialVar(searchList, entityVar, valueIndexed);
              } else if(paramName.equals("cursorMark")) {
                valueCursorMark = (String)paramObject;
                searchList.cursorMark((String)paramObject);
              }
            }
            searchHostCredentialUri(searchList);
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
      searchHostCredential2(siteRequest, populate, store, modify, searchList);
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
            LOG.error(String.format("searchHostCredential failed. "), ex);
            promise.tryFail(ex);
          });
        } else {
          promise.complete(searchList);
        }
      }).onFailure(ex -> {
        LOG.error(String.format("searchHostCredential failed. "), ex);
        promise.tryFail(ex);
      });
    } catch(Exception ex) {
      LOG.error(String.format("searchHostCredential failed. "), ex);
      promise.tryFail(ex);
    }
    return promise.future();
  }
  public void searchHostCredential2(SiteRequest siteRequest, Boolean populate, Boolean store, Boolean modify, SearchList<HostCredential> searchList) {
  }

  public Future<Void> persistHostCredential(HostCredential o, Boolean patch) {
    Promise<Void> promise = Promise.promise();
    try {
      SiteRequest siteRequest = o.getSiteRequest_();
      SqlConnection sqlConnection = siteRequest.getSqlConnection();
      Long pk = o.getPk();
      sqlConnection.preparedQuery("SELECT tenantResource, tenantId, created, aapOrganizationId, credentialName, archived, credentialId, credentialResource, credentialDescription, aapCredentialId, sessionId, aapCredentialTypeId, userKey, userName, password, becomeMethod, objectTitle, becomePassword, displayPage, editPage, userPage, download FROM HostCredential WHERE pk=$1")
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
                  LOG.error(String.format("persistHostCredential failed. "), e);
                }
              }
            }
          }
          o.promiseDeepForClass(siteRequest).onSuccess(a -> {
            promise.complete();
          }).onFailure(ex -> {
            LOG.error(String.format("persistHostCredential failed. "), ex);
            promise.tryFail(ex);
          });
        } catch(Exception ex) {
          LOG.error(String.format("persistHostCredential failed. "), ex);
          promise.tryFail(ex);
        }
      }).onFailure(ex -> {
        RuntimeException ex2 = new RuntimeException(ex);
        LOG.error(String.format("persistHostCredential failed. "), ex2);
        promise.tryFail(ex2);
      });
    } catch(Exception ex) {
      LOG.error(String.format("persistHostCredential failed. "), ex);
      promise.tryFail(ex);
    }
    return promise.future();
  }

  public Future<Void> relateHostCredential(HostCredential o) {
    Promise<Void> promise = Promise.promise();
    try {
      SiteRequest siteRequest = o.getSiteRequest_();
      SqlConnection sqlConnection = siteRequest.getSqlConnection();
      sqlConnection.preparedQuery("SELECT tenantResource as pk2, 'tenantResource' FROM Tenant WHERE tenantResource=$1")
          .collecting(Collectors.toList())
          .execute(Tuple.of(o.getTenantResource())
          ).onSuccess(result -> {
        try {
          if(result != null) {
            for(Row definition : result.value()) {
              o.relateForClass(definition.getString(1), definition.getValue(0));
            }
          }
          promise.complete();
        } catch(Exception ex) {
          LOG.error(String.format("relateHostCredential failed. "), ex);
          promise.tryFail(ex);
        }
      }).onFailure(ex -> {
        RuntimeException ex2 = new RuntimeException(ex);
        LOG.error(String.format("relateHostCredential failed. "), ex2);
        promise.tryFail(ex2);
      });
    } catch(Exception ex) {
      LOG.error(String.format("relateHostCredential failed. "), ex);
      promise.tryFail(ex);
    }
    return promise.future();
  }

  public String searchVar(String varIndexed) {
    return HostCredential.searchVarHostCredential(varIndexed);
  }

  @Override
  public String getClassApiAddress() {
    return HostCredential.CLASS_API_ADDRESS_HostCredential;
  }

  public Future<HostCredential> indexHostCredential(HostCredential o) {
    Promise<HostCredential> promise = Promise.promise();
    try {
      SiteRequest siteRequest = o.getSiteRequest_();
      ApiRequest apiRequest = siteRequest.getApiRequest_();
      JsonObject json = new JsonObject();
      JsonObject add = new JsonObject();
      json.put("add", add);
      JsonObject doc = new JsonObject();
      add.put("doc", doc);
      o.indexHostCredential(doc);
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
        LOG.error(String.format("indexHostCredential failed. "), new RuntimeException(ex));
        promise.tryFail(ex);
      });
    } catch(Exception ex) {
      LOG.error(String.format("indexHostCredential failed. "), ex);
      promise.tryFail(ex);
    }
    return promise.future();
  }

  public Future<HostCredential> unindexHostCredential(HostCredential o) {
    Promise<HostCredential> promise = Promise.promise();
    try {
      SiteRequest siteRequest = o.getSiteRequest_();
      ApiRequest apiRequest = siteRequest.getApiRequest_();
      o.promiseDeepForClass(siteRequest).onSuccess(a -> {
        JsonObject json = new JsonObject();
        JsonObject delete = new JsonObject();
        json.put("delete", delete);
        String query = String.format("filter(%s:%s)", HostCredential.VAR_solrId, o.obtainForClass(HostCredential.VAR_solrId));
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
          LOG.error(String.format("unindexHostCredential failed. "), new RuntimeException(ex));
          promise.tryFail(ex);
        });
      }).onFailure(ex -> {
        LOG.error(String.format("unindexHostCredential failed. "), ex);
        promise.tryFail(ex);
      });
    } catch(Exception ex) {
      LOG.error(String.format("unindexHostCredential failed. "), ex);
      promise.tryFail(ex);
    }
    return promise.future();
  }

  public Future<Void> refreshHostCredential(HostCredential o) {
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
          eventBus.request(HostCredential.getClassApiAddress(), json, new DeliveryOptions().addHeader("action", "patchHostCredentialFuture")).onSuccess(c -> {
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
      LOG.error(String.format("refreshHostCredential failed. "), ex);
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
      HostCredential o = new HostCredential();
      o.setSiteRequest_((SiteRequest)siteRequest);

      o.persistForClass(HostCredential.VAR_tenantResource, HostCredential.staticSetTenantResource(siteRequest2, (String)result.get(HostCredential.VAR_tenantResource)));
      o.persistForClass(HostCredential.VAR_tenantId, HostCredential.staticSetTenantId(siteRequest2, (String)result.get(HostCredential.VAR_tenantId)));
      o.persistForClass(HostCredential.VAR_created, HostCredential.staticSetCreated(siteRequest2, (String)result.get(HostCredential.VAR_created), Optional.ofNullable(siteRequest).map(r -> r.getConfig()).map(config -> config.getString(ConfigKeys.SITE_ZONE)).map(z -> ZoneId.of(z)).orElse(ZoneId.of("UTC"))));
      o.persistForClass(HostCredential.VAR_aapOrganizationId, HostCredential.staticSetAapOrganizationId(siteRequest2, (String)result.get(HostCredential.VAR_aapOrganizationId)));
      o.persistForClass(HostCredential.VAR_credentialName, HostCredential.staticSetCredentialName(siteRequest2, (String)result.get(HostCredential.VAR_credentialName)));
      o.persistForClass(HostCredential.VAR_archived, HostCredential.staticSetArchived(siteRequest2, (String)result.get(HostCredential.VAR_archived)));
      o.persistForClass(HostCredential.VAR_credentialId, HostCredential.staticSetCredentialId(siteRequest2, (String)result.get(HostCredential.VAR_credentialId)));
      o.persistForClass(HostCredential.VAR_credentialResource, HostCredential.staticSetCredentialResource(siteRequest2, (String)result.get(HostCredential.VAR_credentialResource)));
      o.persistForClass(HostCredential.VAR_credentialDescription, HostCredential.staticSetCredentialDescription(siteRequest2, (String)result.get(HostCredential.VAR_credentialDescription)));
      o.persistForClass(HostCredential.VAR_aapCredentialId, HostCredential.staticSetAapCredentialId(siteRequest2, (String)result.get(HostCredential.VAR_aapCredentialId)));
      o.persistForClass(HostCredential.VAR_sessionId, HostCredential.staticSetSessionId(siteRequest2, (String)result.get(HostCredential.VAR_sessionId)));
      o.persistForClass(HostCredential.VAR_aapCredentialTypeId, HostCredential.staticSetAapCredentialTypeId(siteRequest2, (String)result.get(HostCredential.VAR_aapCredentialTypeId)));
      o.persistForClass(HostCredential.VAR_userKey, HostCredential.staticSetUserKey(siteRequest2, (String)result.get(HostCredential.VAR_userKey)));
      o.persistForClass(HostCredential.VAR_userName, HostCredential.staticSetUserName(siteRequest2, (String)result.get(HostCredential.VAR_userName)));
      o.persistForClass(HostCredential.VAR_password, HostCredential.staticSetPassword(siteRequest2, (String)result.get(HostCredential.VAR_password)));
      o.persistForClass(HostCredential.VAR_becomeMethod, HostCredential.staticSetBecomeMethod(siteRequest2, (String)result.get(HostCredential.VAR_becomeMethod)));
      o.persistForClass(HostCredential.VAR_objectTitle, HostCredential.staticSetObjectTitle(siteRequest2, (String)result.get(HostCredential.VAR_objectTitle)));
      o.persistForClass(HostCredential.VAR_becomePassword, HostCredential.staticSetBecomePassword(siteRequest2, (String)result.get(HostCredential.VAR_becomePassword)));
      o.persistForClass(HostCredential.VAR_displayPage, HostCredential.staticSetDisplayPage(siteRequest2, (String)result.get(HostCredential.VAR_displayPage)));
      o.persistForClass(HostCredential.VAR_editPage, HostCredential.staticSetEditPage(siteRequest2, (String)result.get(HostCredential.VAR_editPage)));
      o.persistForClass(HostCredential.VAR_userPage, HostCredential.staticSetUserPage(siteRequest2, (String)result.get(HostCredential.VAR_userPage)));
      o.persistForClass(HostCredential.VAR_download, HostCredential.staticSetDownload(siteRequest2, (String)result.get(HostCredential.VAR_download)));

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
