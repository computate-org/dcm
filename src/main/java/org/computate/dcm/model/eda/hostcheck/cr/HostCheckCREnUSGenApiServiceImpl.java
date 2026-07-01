package org.computate.dcm.model.eda.hostcheck.cr;

import org.computate.dcm.model.eda.tenant.TenantEnUSApiServiceImpl;
import org.computate.dcm.model.eda.tenant.Tenant;
import org.computate.dcm.model.eda.jobtemplate.JobTemplateEnUSApiServiceImpl;
import org.computate.dcm.model.eda.jobtemplate.JobTemplate;
import org.computate.dcm.model.eda.hostcheck.HostCheckEnUSApiServiceImpl;
import org.computate.dcm.model.eda.hostcheck.HostCheck;
import org.computate.dcm.model.eda.jobtemplate.JobTemplateEnUSApiServiceImpl;
import org.computate.dcm.model.eda.jobtemplate.JobTemplate;
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
import org.computate.dcm.model.eda.hostcheck.cr.HostCheckCRPage;


/**
 * Translate: false
 * Generated: true
 **/
public class HostCheckCREnUSGenApiServiceImpl extends BaseApiServiceImpl implements HostCheckCREnUSGenApiService {

  protected static final Logger LOG = LoggerFactory.getLogger(HostCheckCREnUSGenApiServiceImpl.class);

  // Search //

  @Override
  public void searchHostCheckCR(ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler) {
    Boolean classPublicRead = false;
    user(serviceRequest, SiteRequest.class, SiteUser.class, SiteUser.getClassApiAddress(), "postSiteUserFuture", "patchSiteUserFuture", classPublicRead).onSuccess(siteRequest -> {
      try {
        siteRequest.setLang("enUS");
        String changeRequestId = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("changeRequestId");
        String HOSTCHECKCR = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("HOSTCHECKCR");
        List<String> groups = Optional.ofNullable(siteRequest.getGroups()).orElse(new ArrayList<>());
        MultiMap form = MultiMap.caseInsensitiveMultiMap();
        form.add("grant_type", "urn:ietf:params:oauth:grant-type:uma-ticket");
        form.add("audience", config.getString(ComputateConfigKeys.AUTH_CLIENT));
        form.add("response_mode", "permissions");
        form.add("permission", String.format("%s#%s", HostCheckCR.CLASS_AUTH_RESOURCE, "GET"));
        form.add("permission", String.format("%s#%s", HostCheckCR.CLASS_AUTH_RESOURCE, "POST"));
        form.add("permission", String.format("%s#%s", HostCheckCR.CLASS_AUTH_RESOURCE, "PATCH"));
        form.add("permission", String.format("%s#%s", HostCheckCR.CLASS_AUTH_RESOURCE, "PUT"));
        form.add("permission", String.format("%s#%s", HostCheckCR.CLASS_AUTH_RESOURCE, "DELETE"));
        form.add("permission", String.format("%s#%s", HostCheckCR.CLASS_AUTH_RESOURCE, "Admin"));
        form.add("permission", String.format("%s#%s", HostCheckCR.CLASS_AUTH_RESOURCE, "SuperAdmin"));
        if(changeRequestId != null)
          form.add("permission", String.format("%s-%s#%s", HostCheckCR.CLASS_AUTH_RESOURCE, changeRequestId, "GET"));
        groups.stream().map(group -> {
              Matcher mPermission = Pattern.compile("^/(.*-?TENANT-([a-z0-9\\-]+))-(\\w+)$").matcher(group);
              return mPermission.find() ? mPermission : null;
            }).filter(v -> v != null).forEach(mPermission -> {
              form.add("permission", String.format("%s#%s", mPermission.group(1), mPermission.group(3)));
            });
        groups.stream().map(group -> {
              Matcher mPermission = Pattern.compile("^/(.*-?JOBTEMPLATE-([a-z0-9\\-]+))-(\\w+)$").matcher(group);
              return mPermission.find() ? mPermission : null;
            }).filter(v -> v != null).forEach(mPermission -> {
              form.add("permission", String.format("%s#%s", mPermission.group(1), mPermission.group(3)));
            });
        groups.stream().map(group -> {
              Matcher mPermission = Pattern.compile("^/(.*-?HOSTCHECK-([a-z0-9\\-]+))-(\\w+)$").matcher(group);
              return mPermission.find() ? mPermission : null;
            }).filter(v -> v != null).forEach(mPermission -> {
              form.add("permission", String.format("%s#%s", mPermission.group(1), mPermission.group(3)));
            });
        groups.stream().map(group -> {
              Matcher mPermission = Pattern.compile("^/(.*-?HOSTCHECK-([a-z0-9\\-]+))-(\\w+)$").matcher(group);
              return mPermission.find() ? mPermission : null;
            }).filter(v -> v != null).forEach(mPermission -> {
              form.add("permission", String.format("%s#%s", mPermission.group(1), mPermission.group(3)));
            });
        groups.stream().map(group -> {
              Matcher mPermission = Pattern.compile("^/(.*-?HOSTCHECK-([a-z0-9\\-]+))-(\\w+)$").matcher(group);
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
            JsonArray scopes = authorizationDecisionBody.stream().map(o -> (JsonObject)o).filter(o -> "HOSTCHECKCR".equals(o.getString("rsname"))).findFirst().map(decision -> ((JsonObject)decision).getJsonArray("scopes")).orElse(new JsonArray());
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
                    Matcher mPermission = Pattern.compile("^(.*-?JOBTEMPLATE-([a-z0-9\\-]+))$").matcher(permission.getString("rsname"));
                    return permission.getJsonArray("scopes").contains("GET")
                        && mPermission.find();
                  }).forEach(permission -> {
                    fqs.add(String.format("%s:%s", "jobTemplateResource", permission.getString("rsname")));
                    permission.getJsonArray("scopes").stream().map(s -> (String)s).forEach(scope -> {
                      if(!scopes.contains(scope))
                        scopes.add(scope);
                    });
                  });
              authorizationDecisionBody.stream().map(o -> (JsonObject)o).filter(permission -> {
                    Matcher mPermission = Pattern.compile("^(.*-?HOSTCHECK-([a-z0-9\\-]+))$").matcher(permission.getString("rsname"));
                    return permission.getJsonArray("scopes").contains("GET")
                        && mPermission.find();
                  }).forEach(permission -> {
                    fqs.add(String.format("%s:%s", "checkResource", permission.getString("rsname")));
                    permission.getJsonArray("scopes").stream().map(s -> (String)s).forEach(scope -> {
                      if(!scopes.contains(scope))
                        scopes.add(scope);
                    });
                  });
              authorizationDecisionBody.stream().map(o -> (JsonObject)o).filter(permission -> {
                    Matcher mPermission = Pattern.compile("^(.*-?HOSTCHECK-([a-z0-9\\-]+))$").matcher(permission.getString("rsname"));
                    return permission.getJsonArray("scopes").contains("GET")
                        && mPermission.find();
                  }).forEach(permission -> {
                    fqs.add(String.format("%s:%s", "changeRequestId", permission.getString("rsname")));
                    permission.getJsonArray("scopes").stream().map(s -> (String)s).forEach(scope -> {
                      if(!scopes.contains(scope))
                        scopes.add(scope);
                    });
                  });
              authorizationDecisionBody.stream().map(o -> (JsonObject)o).filter(permission -> {
                    Matcher mPermission = Pattern.compile("^(.*-?HOSTCHECK-([a-z0-9\\-]+))$").matcher(permission.getString("rsname"));
                    return permission.getJsonArray("scopes").contains("GET")
                        && mPermission.find();
                  }).forEach(permission -> {
                    fqs.add(String.format("%s:%s", "checkResourceChange", permission.getString("rsname")));
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
              searchHostCheckCRList(siteRequest, false, true, false, "GET").onSuccess(listHostCheckCR -> {
                response200SearchHostCheckCR(listHostCheckCR).onSuccess(response -> {
                  eventHandler.handle(Future.succeededFuture(response));
                  LOG.debug(String.format("searchHostCheckCR succeeded. "));
                }).onFailure(ex -> {
                  LOG.error(String.format("searchHostCheckCR failed. "), ex);
                  error(siteRequest, eventHandler, ex);
                });
              }).onFailure(ex -> {
                LOG.error(String.format("searchHostCheckCR failed. "), ex);
                error(siteRequest, eventHandler, ex);
              });
            }
          } catch(Exception ex) {
            LOG.error(String.format("searchHostCheckCR failed. "), ex);
            error(null, eventHandler, ex);
          }
        });
      } catch(Exception ex) {
        LOG.error(String.format("searchHostCheckCR failed. "), ex);
        error(null, eventHandler, ex);
      }
    }).onFailure(ex -> {
      if("Inactive Token".equals(ex.getMessage()) || StringUtils.startsWith(ex.getMessage(), "invalid_grant:")) {
        try {
          eventHandler.handle(Future.succeededFuture(new ServiceResponse(302, "Found", null, MultiMap.caseInsensitiveMultiMap().add(HttpHeaders.LOCATION, "/logout?redirect_uri=" + URLEncoder.encode(serviceRequest.getExtra().getString("uri"), "UTF-8")))));
        } catch(Exception ex2) {
          LOG.error(String.format("searchHostCheckCR failed. ", ex2));
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
        LOG.error(String.format("searchHostCheckCR failed. "), ex);
        error(null, eventHandler, ex);
      }
    });
  }

  public Future<ServiceResponse> response200SearchHostCheckCR(SearchList<HostCheckCR> listHostCheckCR) {
    Promise<ServiceResponse> promise = Promise.promise();
    try {
      SiteRequest siteRequest = listHostCheckCR.getSiteRequest_(SiteRequest.class);
      List<String> fls = listHostCheckCR.getRequest().getFields();
      JsonObject json = new JsonObject();
      JsonArray l = new JsonArray();
      List<String> scopes = siteRequest.getScopes();
      listHostCheckCR.getList().stream().forEach(o -> {
        JsonObject json2 = JsonObject.mapFrom(o);
        if(fls.size() > 0) {
          Set<String> fieldNames = new HashSet<String>();
          for(String fieldName : json2.fieldNames()) {
            String v = HostCheckCR.varIndexedHostCheckCR(fieldName);
            if(v != null)
              fieldNames.add(HostCheckCR.varIndexedHostCheckCR(fieldName));
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
      response200Search(listHostCheckCR.getRequest(), listHostCheckCR.getResponse(), json);
      promise.complete(ServiceResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily())));
    } catch(Exception ex) {
      LOG.error(String.format("response200SearchHostCheckCR failed. "), ex);
      promise.tryFail(ex);
    }
    return promise.future();
  }
  public void responsePivotSearchHostCheckCR(List<SolrResponse.Pivot> pivots, JsonArray pivotArray) {
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
          responsePivotSearchHostCheckCR(pivotFields2, pivotArray2);
        }
      }
    }
  }

  // GET //

  @Override
  public void getHostCheckCR(ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler) {
    Boolean classPublicRead = false;
    user(serviceRequest, SiteRequest.class, SiteUser.class, SiteUser.getClassApiAddress(), "postSiteUserFuture", "patchSiteUserFuture", classPublicRead).onSuccess(siteRequest -> {
      try {
        siteRequest.setLang("enUS");
        String changeRequestId = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("changeRequestId");
        String HOSTCHECKCR = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("HOSTCHECKCR");
        List<String> groups = Optional.ofNullable(siteRequest.getGroups()).orElse(new ArrayList<>());
        MultiMap form = MultiMap.caseInsensitiveMultiMap();
        form.add("grant_type", "urn:ietf:params:oauth:grant-type:uma-ticket");
        form.add("audience", config.getString(ComputateConfigKeys.AUTH_CLIENT));
        form.add("response_mode", "permissions");
        form.add("permission", String.format("%s#%s", HostCheckCR.CLASS_AUTH_RESOURCE, "GET"));
        form.add("permission", String.format("%s#%s", HostCheckCR.CLASS_AUTH_RESOURCE, "POST"));
        form.add("permission", String.format("%s#%s", HostCheckCR.CLASS_AUTH_RESOURCE, "PATCH"));
        form.add("permission", String.format("%s#%s", HostCheckCR.CLASS_AUTH_RESOURCE, "PUT"));
        form.add("permission", String.format("%s#%s", HostCheckCR.CLASS_AUTH_RESOURCE, "DELETE"));
        form.add("permission", String.format("%s#%s", HostCheckCR.CLASS_AUTH_RESOURCE, "Admin"));
        form.add("permission", String.format("%s#%s", HostCheckCR.CLASS_AUTH_RESOURCE, "SuperAdmin"));
        if(changeRequestId != null)
          form.add("permission", String.format("%s-%s#%s", HostCheckCR.CLASS_AUTH_RESOURCE, changeRequestId, "GET"));
        groups.stream().map(group -> {
              Matcher mPermission = Pattern.compile("^/(.*-?TENANT-([a-z0-9\\-]+))-(\\w+)$").matcher(group);
              return mPermission.find() ? mPermission : null;
            }).filter(v -> v != null).forEach(mPermission -> {
              form.add("permission", String.format("%s#%s", mPermission.group(1), mPermission.group(3)));
            });
        groups.stream().map(group -> {
              Matcher mPermission = Pattern.compile("^/(.*-?JOBTEMPLATE-([a-z0-9\\-]+))-(\\w+)$").matcher(group);
              return mPermission.find() ? mPermission : null;
            }).filter(v -> v != null).forEach(mPermission -> {
              form.add("permission", String.format("%s#%s", mPermission.group(1), mPermission.group(3)));
            });
        groups.stream().map(group -> {
              Matcher mPermission = Pattern.compile("^/(.*-?HOSTCHECK-([a-z0-9\\-]+))-(\\w+)$").matcher(group);
              return mPermission.find() ? mPermission : null;
            }).filter(v -> v != null).forEach(mPermission -> {
              form.add("permission", String.format("%s#%s", mPermission.group(1), mPermission.group(3)));
            });
        groups.stream().map(group -> {
              Matcher mPermission = Pattern.compile("^/(.*-?HOSTCHECK-([a-z0-9\\-]+))-(\\w+)$").matcher(group);
              return mPermission.find() ? mPermission : null;
            }).filter(v -> v != null).forEach(mPermission -> {
              form.add("permission", String.format("%s#%s", mPermission.group(1), mPermission.group(3)));
            });
        groups.stream().map(group -> {
              Matcher mPermission = Pattern.compile("^/(.*-?HOSTCHECK-([a-z0-9\\-]+))-(\\w+)$").matcher(group);
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
            JsonArray scopes = authorizationDecisionBody.stream().map(o -> (JsonObject)o).filter(o -> "HOSTCHECKCR".equals(o.getString("rsname"))).findFirst().map(decision -> ((JsonObject)decision).getJsonArray("scopes")).orElse(new JsonArray());
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
                    Matcher mPermission = Pattern.compile("^(.*-?JOBTEMPLATE-([a-z0-9\\-]+))$").matcher(permission.getString("rsname"));
                    return permission.getJsonArray("scopes").contains("GET")
                        && mPermission.find();
                  }).forEach(permission -> {
                    fqs.add(String.format("%s:%s", "jobTemplateResource", permission.getString("rsname")));
                    permission.getJsonArray("scopes").stream().map(s -> (String)s).forEach(scope -> {
                      if(!scopes.contains(scope))
                        scopes.add(scope);
                    });
                  });
              authorizationDecisionBody.stream().map(o -> (JsonObject)o).filter(permission -> {
                    Matcher mPermission = Pattern.compile("^(.*-?HOSTCHECK-([a-z0-9\\-]+))$").matcher(permission.getString("rsname"));
                    return permission.getJsonArray("scopes").contains("GET")
                        && mPermission.find();
                  }).forEach(permission -> {
                    fqs.add(String.format("%s:%s", "checkResource", permission.getString("rsname")));
                    permission.getJsonArray("scopes").stream().map(s -> (String)s).forEach(scope -> {
                      if(!scopes.contains(scope))
                        scopes.add(scope);
                    });
                  });
              authorizationDecisionBody.stream().map(o -> (JsonObject)o).filter(permission -> {
                    Matcher mPermission = Pattern.compile("^(.*-?HOSTCHECK-([a-z0-9\\-]+))$").matcher(permission.getString("rsname"));
                    return permission.getJsonArray("scopes").contains("GET")
                        && mPermission.find();
                  }).forEach(permission -> {
                    fqs.add(String.format("%s:%s", "changeRequestId", permission.getString("rsname")));
                    permission.getJsonArray("scopes").stream().map(s -> (String)s).forEach(scope -> {
                      if(!scopes.contains(scope))
                        scopes.add(scope);
                    });
                  });
              authorizationDecisionBody.stream().map(o -> (JsonObject)o).filter(permission -> {
                    Matcher mPermission = Pattern.compile("^(.*-?HOSTCHECK-([a-z0-9\\-]+))$").matcher(permission.getString("rsname"));
                    return permission.getJsonArray("scopes").contains("GET")
                        && mPermission.find();
                  }).forEach(permission -> {
                    fqs.add(String.format("%s:%s", "checkResourceChange", permission.getString("rsname")));
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
              searchHostCheckCRList(siteRequest, false, true, false, "GET").onSuccess(listHostCheckCR -> {
                response200GETHostCheckCR(listHostCheckCR).onSuccess(response -> {
                  eventHandler.handle(Future.succeededFuture(response));
                  LOG.debug(String.format("getHostCheckCR succeeded. "));
                }).onFailure(ex -> {
                  LOG.error(String.format("getHostCheckCR failed. "), ex);
                  error(siteRequest, eventHandler, ex);
                });
              }).onFailure(ex -> {
                LOG.error(String.format("getHostCheckCR failed. "), ex);
                error(siteRequest, eventHandler, ex);
              });
            }
          } catch(Exception ex) {
            LOG.error(String.format("getHostCheckCR failed. "), ex);
            error(null, eventHandler, ex);
          }
        });
      } catch(Exception ex) {
        LOG.error(String.format("getHostCheckCR failed. "), ex);
        error(null, eventHandler, ex);
      }
    }).onFailure(ex -> {
      if("Inactive Token".equals(ex.getMessage()) || StringUtils.startsWith(ex.getMessage(), "invalid_grant:")) {
        try {
          eventHandler.handle(Future.succeededFuture(new ServiceResponse(302, "Found", null, MultiMap.caseInsensitiveMultiMap().add(HttpHeaders.LOCATION, "/logout?redirect_uri=" + URLEncoder.encode(serviceRequest.getExtra().getString("uri"), "UTF-8")))));
        } catch(Exception ex2) {
          LOG.error(String.format("getHostCheckCR failed. ", ex2));
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
        LOG.error(String.format("getHostCheckCR failed. "), ex);
        error(null, eventHandler, ex);
      }
    });
  }

  public Future<ServiceResponse> response200GETHostCheckCR(SearchList<HostCheckCR> listHostCheckCR) {
    Promise<ServiceResponse> promise = Promise.promise();
    try {
      SiteRequest siteRequest = listHostCheckCR.getSiteRequest_(SiteRequest.class);
      JsonObject json = JsonObject.mapFrom(listHostCheckCR.getList().stream().findFirst().orElse(null));
      promise.complete(ServiceResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily())));
    } catch(Exception ex) {
      LOG.error(String.format("response200GETHostCheckCR failed. "), ex);
      promise.tryFail(ex);
    }
    return promise.future();
  }

  // PATCH //

  @Override
  public void patchHostCheckCR(JsonObject body, ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler) {
    LOG.debug(String.format("patchHostCheckCR started. "));
    Boolean classPublicRead = false;
    user(serviceRequest, SiteRequest.class, SiteUser.class, SiteUser.getClassApiAddress(), "postSiteUserFuture", "patchSiteUserFuture", classPublicRead).onSuccess(siteRequest -> {
      try {
        siteRequest.setLang("enUS");
        String changeRequestId = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("changeRequestId");
        String HOSTCHECKCR = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("HOSTCHECKCR");
        List<String> groups = Optional.ofNullable(siteRequest.getGroups()).orElse(new ArrayList<>());
        MultiMap form = MultiMap.caseInsensitiveMultiMap();
        form.add("grant_type", "urn:ietf:params:oauth:grant-type:uma-ticket");
        form.add("audience", config.getString(ComputateConfigKeys.AUTH_CLIENT));
        form.add("response_mode", "permissions");
        form.add("permission", String.format("%s#%s", HostCheckCR.CLASS_AUTH_RESOURCE, "GET"));
        form.add("permission", String.format("%s#%s", HostCheckCR.CLASS_AUTH_RESOURCE, "POST"));
        form.add("permission", String.format("%s#%s", HostCheckCR.CLASS_AUTH_RESOURCE, "PATCH"));
        form.add("permission", String.format("%s#%s", HostCheckCR.CLASS_AUTH_RESOURCE, "PUT"));
        form.add("permission", String.format("%s#%s", HostCheckCR.CLASS_AUTH_RESOURCE, "DELETE"));
        form.add("permission", String.format("%s#%s", HostCheckCR.CLASS_AUTH_RESOURCE, "Admin"));
        form.add("permission", String.format("%s#%s", HostCheckCR.CLASS_AUTH_RESOURCE, "SuperAdmin"));
        if(changeRequestId != null)
          form.add("permission", String.format("%s-%s#%s", HostCheckCR.CLASS_AUTH_RESOURCE, changeRequestId, "PATCH"));
        groups.stream().map(group -> {
              Matcher mPermission = Pattern.compile("^/(.*-?TENANT-([a-z0-9\\-]+))-(\\w+)$").matcher(group);
              return mPermission.find() ? mPermission : null;
            }).filter(v -> v != null).forEach(mPermission -> {
              form.add("permission", String.format("%s#%s", mPermission.group(1), mPermission.group(3)));
            });
        groups.stream().map(group -> {
              Matcher mPermission = Pattern.compile("^/(.*-?JOBTEMPLATE-([a-z0-9\\-]+))-(\\w+)$").matcher(group);
              return mPermission.find() ? mPermission : null;
            }).filter(v -> v != null).forEach(mPermission -> {
              form.add("permission", String.format("%s#%s", mPermission.group(1), mPermission.group(3)));
            });
        groups.stream().map(group -> {
              Matcher mPermission = Pattern.compile("^/(.*-?HOSTCHECK-([a-z0-9\\-]+))-(\\w+)$").matcher(group);
              return mPermission.find() ? mPermission : null;
            }).filter(v -> v != null).forEach(mPermission -> {
              form.add("permission", String.format("%s#%s", mPermission.group(1), mPermission.group(3)));
            });
        groups.stream().map(group -> {
              Matcher mPermission = Pattern.compile("^/(.*-?HOSTCHECK-([a-z0-9\\-]+))-(\\w+)$").matcher(group);
              return mPermission.find() ? mPermission : null;
            }).filter(v -> v != null).forEach(mPermission -> {
              form.add("permission", String.format("%s#%s", mPermission.group(1), mPermission.group(3)));
            });
        groups.stream().map(group -> {
              Matcher mPermission = Pattern.compile("^/(.*-?HOSTCHECK-([a-z0-9\\-]+))-(\\w+)$").matcher(group);
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
            JsonArray scopes = authorizationDecisionBody.stream().map(o -> (JsonObject)o).filter(o -> "HOSTCHECKCR".equals(o.getString("rsname"))).findFirst().map(decision -> ((JsonObject)decision).getJsonArray("scopes")).orElse(new JsonArray());
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
                  Matcher mPermission = Pattern.compile("^(.*-?JOBTEMPLATE-([a-z0-9\\-]+))$").matcher(permission.getString("rsname"));
                  return permission.getJsonArray("scopes").contains("PATCH")
                      && mPermission.find();
                }).forEach(permission -> {
                  fqs.add(String.format("%s:%s", "jobTemplateResource", permission.getString("rsname")));
                  permission.getJsonArray("scopes").stream().map(s -> (String)s).forEach(scope -> {
                    if(!scopes.contains(scope))
                      scopes.add(scope);
                  });
                });
            authorizationDecisionBody.stream().map(o -> (JsonObject)o).filter(permission -> {
                  Matcher mPermission = Pattern.compile("^(.*-?HOSTCHECK-([a-z0-9\\-]+))$").matcher(permission.getString("rsname"));
                  return permission.getJsonArray("scopes").contains("PATCH")
                      && mPermission.find();
                }).forEach(permission -> {
                  fqs.add(String.format("%s:%s", "checkResource", permission.getString("rsname")));
                  permission.getJsonArray("scopes").stream().map(s -> (String)s).forEach(scope -> {
                    if(!scopes.contains(scope))
                      scopes.add(scope);
                  });
                });
            authorizationDecisionBody.stream().map(o -> (JsonObject)o).filter(permission -> {
                  Matcher mPermission = Pattern.compile("^(.*-?HOSTCHECK-([a-z0-9\\-]+))$").matcher(permission.getString("rsname"));
                  return permission.getJsonArray("scopes").contains("PATCH")
                      && mPermission.find();
                }).forEach(permission -> {
                  fqs.add(String.format("%s:%s", "changeRequestId", permission.getString("rsname")));
                  permission.getJsonArray("scopes").stream().map(s -> (String)s).forEach(scope -> {
                    if(!scopes.contains(scope))
                      scopes.add(scope);
                  });
                });
            authorizationDecisionBody.stream().map(o -> (JsonObject)o).filter(permission -> {
                  Matcher mPermission = Pattern.compile("^(.*-?HOSTCHECK-([a-z0-9\\-]+))$").matcher(permission.getString("rsname"));
                  return permission.getJsonArray("scopes").contains("PATCH")
                      && mPermission.find();
                }).forEach(permission -> {
                  fqs.add(String.format("%s:%s", "checkResourceChange", permission.getString("rsname")));
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
              searchHostCheckCRList(siteRequest, false, true, true, "PATCH").onSuccess(listHostCheckCR -> {
                try {
                  ApiRequest apiRequest = new ApiRequest();
                  apiRequest.setRows(listHostCheckCR.getRequest().getRows());
                  apiRequest.setNumFound(listHostCheckCR.getResponse().getResponse().getNumFound());
                  apiRequest.setNumPATCH(0L);
                  apiRequest.initDeepApiRequest(siteRequest);
                  siteRequest.setApiRequest_(apiRequest);
                  if(apiRequest.getNumFound() == 1L)
                    apiRequest.setOriginal(listHostCheckCR.first());
                  apiRequest.setId(Optional.ofNullable(listHostCheckCR.first()).map(o2 -> o2.getChangeRequestId().toString()).orElse(null));
                  apiRequest.setSolrId(Optional.ofNullable(listHostCheckCR.first()).map(o2 -> o2.getSolrId()).orElse(null));
                  eventBus.publish("websocketHostCheckCR", JsonObject.mapFrom(apiRequest).toString());

                  listPATCHHostCheckCR(apiRequest, listHostCheckCR).onSuccess(e -> {
                    response200PATCHHostCheckCR(siteRequest).onSuccess(response -> {
                      LOG.debug(String.format("patchHostCheckCR succeeded. "));
                      eventHandler.handle(Future.succeededFuture(response));
                    }).onFailure(ex -> {
                      LOG.error(String.format("patchHostCheckCR failed. "), ex);
                      error(siteRequest, eventHandler, ex);
                    });
                  }).onFailure(ex -> {
                    LOG.error(String.format("patchHostCheckCR failed. "), ex);
                    error(siteRequest, eventHandler, ex);
                  });
                } catch(Exception ex) {
                  LOG.error(String.format("patchHostCheckCR failed. "), ex);
                  error(siteRequest, eventHandler, ex);
                }
              }).onFailure(ex -> {
                LOG.error(String.format("patchHostCheckCR failed. "), ex);
                error(siteRequest, eventHandler, ex);
              });
            }
          } catch(Exception ex) {
            LOG.error(String.format("patchHostCheckCR failed. "), ex);
            error(null, eventHandler, ex);
          }
        });
      } catch(Exception ex) {
        LOG.error(String.format("patchHostCheckCR failed. "), ex);
        error(null, eventHandler, ex);
      }
    }).onFailure(ex -> {
      if("Inactive Token".equals(ex.getMessage()) || StringUtils.startsWith(ex.getMessage(), "invalid_grant:")) {
        try {
          eventHandler.handle(Future.succeededFuture(new ServiceResponse(302, "Found", null, MultiMap.caseInsensitiveMultiMap().add(HttpHeaders.LOCATION, "/logout?redirect_uri=" + URLEncoder.encode(serviceRequest.getExtra().getString("uri"), "UTF-8")))));
        } catch(Exception ex2) {
          LOG.error(String.format("patchHostCheckCR failed. ", ex2));
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
        LOG.error(String.format("patchHostCheckCR failed. "), ex);
        error(null, eventHandler, ex);
      }
    });
  }

  public Future<Void> listPATCHHostCheckCR(ApiRequest apiRequest, SearchList<HostCheckCR> listHostCheckCR) {
    Promise<Void> promise = Promise.promise();
    List<Future> futures = new ArrayList<>();
    SiteRequest siteRequest = listHostCheckCR.getSiteRequest_(SiteRequest.class);
    listHostCheckCR.getList().forEach(o -> {
      SiteRequest siteRequest2 = generateSiteRequest(siteRequest.getUser(), siteRequest.getUserPrincipal(), siteRequest.getServiceRequest(), siteRequest.getJsonObject(), SiteRequest.class);
      siteRequest2.setScopes(siteRequest.getScopes());
      o.setSiteRequest_(siteRequest2);
      siteRequest2.setApiRequest_(siteRequest.getApiRequest_());
      JsonObject jsonObject = JsonObject.mapFrom(o);
      HostCheckCR o2 = jsonObject.mapTo(HostCheckCR.class);
      o2.setSiteRequest_(siteRequest2);
      futures.add(Future.future(promise1 -> {
        patchHostCheckCRFuture(o2, false).onSuccess(a -> {
          promise1.complete();
        }).onFailure(ex -> {
          LOG.error(String.format("listPATCHHostCheckCR failed. "), ex);
          promise1.tryFail(ex);
        });
      }));
    });
    CompositeFuture.all(futures).onSuccess( a -> {
      listHostCheckCR.next().onSuccess(next -> {
        if(next) {
          listPATCHHostCheckCR(apiRequest, listHostCheckCR).onSuccess(b -> {
            promise.complete();
          }).onFailure(ex -> {
            LOG.error(String.format("listPATCHHostCheckCR failed. "), ex);
            promise.tryFail(ex);
          });
        } else {
          promise.complete();
        }
      }).onFailure(ex -> {
        LOG.error(String.format("listPATCHHostCheckCR failed. "), ex);
        promise.tryFail(ex);
      });
    }).onFailure(ex -> {
      LOG.error(String.format("listPATCHHostCheckCR failed. "), ex);
      promise.tryFail(ex);
    });
    return promise.future();
  }

  @Override
  public void patchHostCheckCRFuture(JsonObject body, ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler) {
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
        searchHostCheckCRList(siteRequest, false, true, true, "PATCH").onSuccess(listHostCheckCR -> {
          try {
            HostCheckCR o = listHostCheckCR.first();
            ApiRequest apiRequest = new ApiRequest();
            apiRequest.setRows(1L);
            apiRequest.setNumFound(1L);
            apiRequest.setNumPATCH(0L);
            apiRequest.initDeepApiRequest(siteRequest);
            siteRequest.setApiRequest_(apiRequest);
            if(Optional.ofNullable(serviceRequest.getParams()).map(p -> p.getJsonObject("query")).map( q -> q.getJsonArray("var")).orElse(new JsonArray()).stream().filter(s -> "refresh:false".equals(s)).count() > 0L) {
              siteRequest.getRequestVars().put( "refresh", "false" );
            }
            HostCheckCR o2;
            if(o != null) {
              if(apiRequest.getNumFound() == 1L)
                apiRequest.setOriginal(o);
              apiRequest.setId(Optional.ofNullable(listHostCheckCR.first()).map(o3 -> o3.getChangeRequestId().toString()).orElse(null));
              apiRequest.setSolrId(Optional.ofNullable(listHostCheckCR.first()).map(o3 -> o3.getSolrId()).orElse(null));
              JsonObject jsonObject = JsonObject.mapFrom(o);
              o2 = jsonObject.mapTo(HostCheckCR.class);
              o2.setSiteRequest_(siteRequest);
              patchHostCheckCRFuture(o2, false).onSuccess(o3 -> {
                eventHandler.handle(Future.succeededFuture(ServiceResponse.completedWithJson(Buffer.buffer(new JsonObject().encodePrettily()))));
              }).onFailure(ex -> {
                eventHandler.handle(Future.failedFuture(ex));
              });
            } else {
              String m = String.format("%s %s not found", "host check change request", null);
              eventHandler.handle(Future.failedFuture(m));
            }
          } catch(Exception ex) {
            LOG.error(String.format("patchHostCheckCR failed. "), ex);
            error(siteRequest, eventHandler, ex);
          }
        }).onFailure(ex -> {
          LOG.error(String.format("patchHostCheckCR failed. "), ex);
          error(siteRequest, eventHandler, ex);
        });
      } catch(Exception ex) {
        LOG.error(String.format("patchHostCheckCR failed. "), ex);
        error(null, eventHandler, ex);
      }
    }).onFailure(ex -> {
      LOG.error(String.format("patchHostCheckCR failed. "), ex);
      error(null, eventHandler, ex);
    });
  }

  public Future<HostCheckCR> patchHostCheckCRFuture(HostCheckCR o, Boolean inheritPrimaryKey) {
    SiteRequest siteRequest = o.getSiteRequest_();
    Promise<HostCheckCR> promise = Promise.promise();

    try {
      ApiRequest apiRequest = siteRequest.getApiRequest_();
      Promise<HostCheckCR> promise1 = Promise.promise();
      pgPool.withTransaction(sqlConnection -> {
        siteRequest.setSqlConnection(sqlConnection);
        varsHostCheckCR(siteRequest).onSuccess(a -> {
          sqlPATCHHostCheckCR(o, inheritPrimaryKey).onSuccess(hostCheckCR -> {
            persistHostCheckCR(hostCheckCR, true).onSuccess(c -> {
              relateHostCheckCR(hostCheckCR).onSuccess(d -> {
                indexHostCheckCR(hostCheckCR).onSuccess(o2 -> {
                  if(apiRequest != null) {
                    apiRequest.setNumPATCH(apiRequest.getNumPATCH() + 1);
                    if(apiRequest.getNumFound() == 1L && Optional.ofNullable(siteRequest.getJsonObject()).map(json -> json.size() > 0).orElse(false)) {
                      o2.apiRequestHostCheckCR();
                      if(apiRequest.getVars().size() > 0 && Optional.ofNullable(siteRequest.getRequestVars().get("refresh")).map(refresh -> !refresh.equals("false")).orElse(true))
                        eventBus.publish("websocketHostCheckCR", JsonObject.mapFrom(apiRequest).toString());
                    }
                  }
                  promise1.complete(hostCheckCR);
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
      }).compose(hostCheckCR -> {
        Promise<HostCheckCR> promise2 = Promise.promise();
        refreshHostCheckCR(hostCheckCR).onSuccess(a -> {
          promise2.complete(hostCheckCR);
        }).onFailure(ex -> {
          promise2.tryFail(ex);
        });
        return promise2.future();
      }).onSuccess(hostCheckCR -> {
        promise.complete(hostCheckCR);
      }).onFailure(ex -> {
        promise.tryFail(ex);
      });
    } catch(Exception ex) {
      LOG.error(String.format("patchHostCheckCRFuture failed. "), ex);
      promise.tryFail(ex);
    }
    return promise.future();
  }

  public Future<HostCheckCR> sqlPATCHHostCheckCR(HostCheckCR o, Boolean inheritPrimaryKey) {
    Promise<HostCheckCR> promise = Promise.promise();
    try {
      SiteRequest siteRequest = o.getSiteRequest_();
      ApiRequest apiRequest = siteRequest.getApiRequest_();
      List<String> solrIds = Optional.ofNullable(apiRequest).map(r -> r.getSolrIds()).orElse(new ArrayList<>());
      List<String> classes = Optional.ofNullable(apiRequest).map(r -> r.getClasses()).orElse(new ArrayList<>());
      SqlConnection sqlConnection = siteRequest.getSqlConnection();
      Integer num = 1;
      StringBuilder bSql = new StringBuilder("UPDATE HostCheckCR SET ");
      List<Object> bParams = new ArrayList<Object>();
      Long pk = o.getPk();
      JsonObject jsonObject = siteRequest.getJsonObject();
      Set<String> methodNames = jsonObject.fieldNames();
      HostCheckCR o2 = new HostCheckCR();
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
                  sql(siteRequest).update(HostCheckCR.class, pk).set(HostCheckCR.VAR_tenantResource, Tenant.class, solrId2, val).onSuccess(a -> {
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
                sql(siteRequest).update(HostCheckCR.class, pk).setToNull(HostCheckCR.VAR_tenantResource, Tenant.class, null).onSuccess(a -> {
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
              bSql.append(HostCheckCR.VAR_tenantId + "=$" + num);
              num++;
              bParams.add(o2.sqlTenantId());
            break;
          case "setCreated":
              o2.setCreated(jsonObject.getString(entityVar));
              if(bParams.size() > 0)
                bSql.append(", ");
              bSql.append(HostCheckCR.VAR_created + "=$" + num);
              num++;
              bParams.add(o2.sqlCreated());
            break;
          case "setAapOrganizationId":
              o2.setAapOrganizationId(jsonObject.getString(entityVar));
              if(bParams.size() > 0)
                bSql.append(", ");
              bSql.append(HostCheckCR.VAR_aapOrganizationId + "=$" + num);
              num++;
              bParams.add(o2.sqlAapOrganizationId());
            break;
          case "setJobTemplateResource":
            Optional.ofNullable(jsonObject.getString(entityVar)).ifPresent(val -> {
              futures1.add(Future.future(promise2 -> {
                searchModel(siteRequest).query(JobTemplate.varIndexedJobTemplate(JobTemplate.VAR_jobTemplateResource), JobTemplate.class, val).onSuccess(o3 -> {
                  String solrId2 = Optional.ofNullable(o3).map(o4 -> o4.getSolrId()).filter(solrId3 -> !solrIds.contains(solrId3)).orElse(null);
                  if(solrId2 != null) {
                    solrIds.add(solrId2);
                    classes.add("JobTemplate");
                  }
                  sql(siteRequest).update(HostCheckCR.class, pk).set(HostCheckCR.VAR_jobTemplateResource, JobTemplate.class, solrId2, val).onSuccess(a -> {
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
          case "removeJobTemplateResource":
            Optional.ofNullable(jsonObject.getString(entityVar)).ifPresent(solrId2 -> {
              futures2.add(Future.future(promise2 -> {
                sql(siteRequest).update(HostCheckCR.class, pk).setToNull(HostCheckCR.VAR_jobTemplateResource, JobTemplate.class, null).onSuccess(a -> {
                  promise2.complete();
                }).onFailure(ex -> {
                  promise2.tryFail(ex);
                });
              }));
            });
            break;
          case "setChangeRequestId":
              o2.setChangeRequestId(jsonObject.getString(entityVar));
              if(bParams.size() > 0)
                bSql.append(", ");
              bSql.append(HostCheckCR.VAR_changeRequestId + "=$" + num);
              num++;
              bParams.add(o2.sqlChangeRequestId());
            break;
          case "setArchived":
              o2.setArchived(jsonObject.getString(entityVar));
              if(bParams.size() > 0)
                bSql.append(", ");
              bSql.append(HostCheckCR.VAR_archived + "=$" + num);
              num++;
              bParams.add(o2.sqlArchived());
            break;
          case "setJobTemplateId":
              o2.setJobTemplateId(jsonObject.getString(entityVar));
              if(bParams.size() > 0)
                bSql.append(", ");
              bSql.append(HostCheckCR.VAR_jobTemplateId + "=$" + num);
              num++;
              bParams.add(o2.sqlJobTemplateId());
            break;
          case "setCreatedByEmail":
              o2.setCreatedByEmail(jsonObject.getString(entityVar));
              if(bParams.size() > 0)
                bSql.append(", ");
              bSql.append(HostCheckCR.VAR_createdByEmail + "=$" + num);
              num++;
              bParams.add(o2.sqlCreatedByEmail());
            break;
          case "setAapTemplateId":
              o2.setAapTemplateId(jsonObject.getString(entityVar));
              if(bParams.size() > 0)
                bSql.append(", ");
              bSql.append(HostCheckCR.VAR_aapTemplateId + "=$" + num);
              num++;
              bParams.add(o2.sqlAapTemplateId());
            break;
          case "setCreatedByUserId":
              o2.setCreatedByUserId(jsonObject.getString(entityVar));
              if(bParams.size() > 0)
                bSql.append(", ");
              bSql.append(HostCheckCR.VAR_createdByUserId + "=$" + num);
              num++;
              bParams.add(o2.sqlCreatedByUserId());
            break;
          case "setCheckName":
              o2.setCheckName(jsonObject.getString(entityVar));
              if(bParams.size() > 0)
                bSql.append(", ");
              bSql.append(HostCheckCR.VAR_checkName + "=$" + num);
              num++;
              bParams.add(o2.sqlCheckName());
            break;
          case "setCreatedByFullName":
              o2.setCreatedByFullName(jsonObject.getString(entityVar));
              if(bParams.size() > 0)
                bSql.append(", ");
              bSql.append(HostCheckCR.VAR_createdByFullName + "=$" + num);
              num++;
              bParams.add(o2.sqlCreatedByFullName());
            break;
          case "setCheckId":
              o2.setCheckId(jsonObject.getString(entityVar));
              if(bParams.size() > 0)
                bSql.append(", ");
              bSql.append(HostCheckCR.VAR_checkId + "=$" + num);
              num++;
              bParams.add(o2.sqlCheckId());
            break;
          case "setCreatedVia":
              o2.setCreatedVia(jsonObject.getString(entityVar));
              if(bParams.size() > 0)
                bSql.append(", ");
              bSql.append(HostCheckCR.VAR_createdVia + "=$" + num);
              num++;
              bParams.add(o2.sqlCreatedVia());
            break;
          case "setSessionId":
              o2.setSessionId(jsonObject.getString(entityVar));
              if(bParams.size() > 0)
                bSql.append(", ");
              bSql.append(HostCheckCR.VAR_sessionId + "=$" + num);
              num++;
              bParams.add(o2.sqlSessionId());
            break;
          case "setCheckResource":
            Optional.ofNullable(jsonObject.getString(entityVar)).ifPresent(val -> {
              futures1.add(Future.future(promise2 -> {
                searchModel(siteRequest).query(HostCheck.varIndexedHostCheck(HostCheck.VAR_checkResource), HostCheck.class, val).onSuccess(o3 -> {
                  String solrId2 = Optional.ofNullable(o3).map(o4 -> o4.getSolrId()).filter(solrId3 -> !solrIds.contains(solrId3)).orElse(null);
                  if(solrId2 != null) {
                    solrIds.add(solrId2);
                    classes.add("HostCheck");
                  }
                  sql(siteRequest).update(HostCheckCR.class, pk).set(HostCheckCR.VAR_checkResource, HostCheck.class, solrId2, val).onSuccess(a -> {
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
          case "removeCheckResource":
            Optional.ofNullable(jsonObject.getString(entityVar)).ifPresent(solrId2 -> {
              futures2.add(Future.future(promise2 -> {
                sql(siteRequest).update(HostCheckCR.class, pk).setToNull(HostCheckCR.VAR_checkResource, HostCheck.class, null).onSuccess(a -> {
                  promise2.complete();
                }).onFailure(ex -> {
                  promise2.tryFail(ex);
                });
              }));
            });
            break;
          case "setOwnedByEmail":
              o2.setOwnedByEmail(jsonObject.getString(entityVar));
              if(bParams.size() > 0)
                bSql.append(", ");
              bSql.append(HostCheckCR.VAR_ownedByEmail + "=$" + num);
              num++;
              bParams.add(o2.sqlOwnedByEmail());
            break;
          case "setUserKey":
              o2.setUserKey(jsonObject.getString(entityVar));
              if(bParams.size() > 0)
                bSql.append(", ");
              bSql.append(HostCheckCR.VAR_userKey + "=$" + num);
              num++;
              bParams.add(o2.sqlUserKey());
            break;
          case "setCheckDescription":
              o2.setCheckDescription(jsonObject.getString(entityVar));
              if(bParams.size() > 0)
                bSql.append(", ");
              bSql.append(HostCheckCR.VAR_checkDescription + "=$" + num);
              num++;
              bParams.add(o2.sqlCheckDescription());
            break;
          case "setOwnedByUserId":
              o2.setOwnedByUserId(jsonObject.getString(entityVar));
              if(bParams.size() > 0)
                bSql.append(", ");
              bSql.append(HostCheckCR.VAR_ownedByUserId + "=$" + num);
              num++;
              bParams.add(o2.sqlOwnedByUserId());
            break;
          case "setCheckNamespace":
              o2.setCheckNamespace(jsonObject.getString(entityVar));
              if(bParams.size() > 0)
                bSql.append(", ");
              bSql.append(HostCheckCR.VAR_checkNamespace + "=$" + num);
              num++;
              bParams.add(o2.sqlCheckNamespace());
            break;
          case "setOwnedByDisplayName":
              o2.setOwnedByDisplayName(jsonObject.getString(entityVar));
              if(bParams.size() > 0)
                bSql.append(", ");
              bSql.append(HostCheckCR.VAR_ownedByDisplayName + "=$" + num);
              num++;
              bParams.add(o2.sqlOwnedByDisplayName());
            break;
          case "setCheckCommand":
              o2.setCheckCommand(jsonObject.getString(entityVar));
              if(bParams.size() > 0)
                bSql.append(", ");
              bSql.append(HostCheckCR.VAR_checkCommand + "=$" + num);
              num++;
              bParams.add(o2.sqlCheckCommand());
            break;
          case "setLifecycleState":
              o2.setLifecycleState(jsonObject.getString(entityVar));
              if(bParams.size() > 0)
                bSql.append(", ");
              bSql.append(HostCheckCR.VAR_lifecycleState + "=$" + num);
              num++;
              bParams.add(o2.sqlLifecycleState());
            break;
          case "setObjectTitle":
              o2.setObjectTitle(jsonObject.getString(entityVar));
              if(bParams.size() > 0)
                bSql.append(", ");
              bSql.append(HostCheckCR.VAR_objectTitle + "=$" + num);
              num++;
              bParams.add(o2.sqlObjectTitle());
            break;
          case "setCheckInterval":
              o2.setCheckInterval(jsonObject.getString(entityVar));
              if(bParams.size() > 0)
                bSql.append(", ");
              bSql.append(HostCheckCR.VAR_checkInterval + "=$" + num);
              num++;
              bParams.add(o2.sqlCheckInterval());
            break;
          case "setIntentState":
              o2.setIntentState(jsonObject.getString(entityVar));
              if(bParams.size() > 0)
                bSql.append(", ");
              bSql.append(HostCheckCR.VAR_intentState + "=$" + num);
              num++;
              bParams.add(o2.sqlIntentState());
            break;
          case "setDisplayPage":
              o2.setDisplayPage(jsonObject.getString(entityVar));
              if(bParams.size() > 0)
                bSql.append(", ");
              bSql.append(HostCheckCR.VAR_displayPage + "=$" + num);
              num++;
              bParams.add(o2.sqlDisplayPage());
            break;
          case "setCheckPublished":
              o2.setCheckPublished(jsonObject.getString(entityVar));
              if(bParams.size() > 0)
                bSql.append(", ");
              bSql.append(HostCheckCR.VAR_checkPublished + "=$" + num);
              num++;
              bParams.add(o2.sqlCheckPublished());
            break;
          case "setRequestedState":
              o2.setRequestedState(jsonObject.getString(entityVar));
              if(bParams.size() > 0)
                bSql.append(", ");
              bSql.append(HostCheckCR.VAR_requestedState + "=$" + num);
              num++;
              bParams.add(o2.sqlRequestedState());
            break;
          case "setEditPage":
              o2.setEditPage(jsonObject.getString(entityVar));
              if(bParams.size() > 0)
                bSql.append(", ");
              bSql.append(HostCheckCR.VAR_editPage + "=$" + num);
              num++;
              bParams.add(o2.sqlEditPage());
            break;
          case "setEventSubscriptions":
              o2.setEventSubscriptions(jsonObject.getJsonArray(entityVar));
              if(bParams.size() > 0)
                bSql.append(", ");
              bSql.append(HostCheckCR.VAR_eventSubscriptions + "=$" + num);
              num++;
              bParams.add(o2.sqlEventSubscriptions());
            break;
          case "setRealizedState":
              o2.setRealizedState(jsonObject.getString(entityVar));
              if(bParams.size() > 0)
                bSql.append(", ");
              bSql.append(HostCheckCR.VAR_realizedState + "=$" + num);
              num++;
              bParams.add(o2.sqlRealizedState());
            break;
          case "setUserPage":
              o2.setUserPage(jsonObject.getString(entityVar));
              if(bParams.size() > 0)
                bSql.append(", ");
              bSql.append(HostCheckCR.VAR_userPage + "=$" + num);
              num++;
              bParams.add(o2.sqlUserPage());
            break;
          case "setEventHandlers":
              o2.setEventHandlers(jsonObject.getJsonArray(entityVar));
              if(bParams.size() > 0)
                bSql.append(", ");
              bSql.append(HostCheckCR.VAR_eventHandlers + "=$" + num);
              num++;
              bParams.add(o2.sqlEventHandlers());
            break;
          case "setAapOrganizationIdChange":
              o2.setAapOrganizationIdChange(jsonObject.getString(entityVar));
              if(bParams.size() > 0)
                bSql.append(", ");
              bSql.append(HostCheckCR.VAR_aapOrganizationIdChange + "=$" + num);
              num++;
              bParams.add(o2.sqlAapOrganizationIdChange());
            break;
          case "setDownload":
              o2.setDownload(jsonObject.getString(entityVar));
              if(bParams.size() > 0)
                bSql.append(", ");
              bSql.append(HostCheckCR.VAR_download + "=$" + num);
              num++;
              bParams.add(o2.sqlDownload());
            break;
          case "setJobTemplateResourceChange":
            Optional.ofNullable(jsonObject.getString(entityVar)).ifPresent(val -> {
              futures1.add(Future.future(promise2 -> {
                searchModel(siteRequest).query(JobTemplate.varIndexedJobTemplate(JobTemplate.VAR_jobTemplateResource), JobTemplate.class, val).onSuccess(o3 -> {
                  String solrId2 = Optional.ofNullable(o3).map(o4 -> o4.getSolrId()).filter(solrId3 -> !solrIds.contains(solrId3)).orElse(null);
                  if(solrId2 != null) {
                    solrIds.add(solrId2);
                    classes.add("JobTemplate");
                  }
                  sql(siteRequest).update(HostCheckCR.class, pk).set(HostCheckCR.VAR_jobTemplateResourceChange, JobTemplate.class, solrId2, val).onSuccess(a -> {
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
          case "removeJobTemplateResourceChange":
            Optional.ofNullable(jsonObject.getString(entityVar)).ifPresent(solrId2 -> {
              futures2.add(Future.future(promise2 -> {
                sql(siteRequest).update(HostCheckCR.class, pk).setToNull(HostCheckCR.VAR_jobTemplateResourceChange, JobTemplate.class, null).onSuccess(a -> {
                  promise2.complete();
                }).onFailure(ex -> {
                  promise2.tryFail(ex);
                });
              }));
            });
            break;
          case "setJobTemplateIdChange":
              o2.setJobTemplateIdChange(jsonObject.getString(entityVar));
              if(bParams.size() > 0)
                bSql.append(", ");
              bSql.append(HostCheckCR.VAR_jobTemplateIdChange + "=$" + num);
              num++;
              bParams.add(o2.sqlJobTemplateIdChange());
            break;
          case "setAapTemplateIdChange":
              o2.setAapTemplateIdChange(jsonObject.getString(entityVar));
              if(bParams.size() > 0)
                bSql.append(", ");
              bSql.append(HostCheckCR.VAR_aapTemplateIdChange + "=$" + num);
              num++;
              bParams.add(o2.sqlAapTemplateIdChange());
            break;
          case "setCheckNameChange":
              o2.setCheckNameChange(jsonObject.getString(entityVar));
              if(bParams.size() > 0)
                bSql.append(", ");
              bSql.append(HostCheckCR.VAR_checkNameChange + "=$" + num);
              num++;
              bParams.add(o2.sqlCheckNameChange());
            break;
          case "setCheckIdChange":
              o2.setCheckIdChange(jsonObject.getString(entityVar));
              if(bParams.size() > 0)
                bSql.append(", ");
              bSql.append(HostCheckCR.VAR_checkIdChange + "=$" + num);
              num++;
              bParams.add(o2.sqlCheckIdChange());
            break;
          case "setCheckResourceChange":
              o2.setCheckResourceChange(jsonObject.getString(entityVar));
              if(bParams.size() > 0)
                bSql.append(", ");
              bSql.append(HostCheckCR.VAR_checkResourceChange + "=$" + num);
              num++;
              bParams.add(o2.sqlCheckResourceChange());
            break;
          case "setCheckDescriptionChange":
              o2.setCheckDescriptionChange(jsonObject.getString(entityVar));
              if(bParams.size() > 0)
                bSql.append(", ");
              bSql.append(HostCheckCR.VAR_checkDescriptionChange + "=$" + num);
              num++;
              bParams.add(o2.sqlCheckDescriptionChange());
            break;
          case "setCheckNamespaceChange":
              o2.setCheckNamespaceChange(jsonObject.getString(entityVar));
              if(bParams.size() > 0)
                bSql.append(", ");
              bSql.append(HostCheckCR.VAR_checkNamespaceChange + "=$" + num);
              num++;
              bParams.add(o2.sqlCheckNamespaceChange());
            break;
          case "setCheckCommandChange":
              o2.setCheckCommandChange(jsonObject.getString(entityVar));
              if(bParams.size() > 0)
                bSql.append(", ");
              bSql.append(HostCheckCR.VAR_checkCommandChange + "=$" + num);
              num++;
              bParams.add(o2.sqlCheckCommandChange());
            break;
          case "setCheckIntervalChange":
              o2.setCheckIntervalChange(jsonObject.getString(entityVar));
              if(bParams.size() > 0)
                bSql.append(", ");
              bSql.append(HostCheckCR.VAR_checkIntervalChange + "=$" + num);
              num++;
              bParams.add(o2.sqlCheckIntervalChange());
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
            RuntimeException ex2 = new RuntimeException("value HostCheckCR failed", ex);
            LOG.error(String.format("relateHostCheckCR failed. "), ex2);
            a.handle(Future.failedFuture(ex2));
          });
        }));
      }
      CompositeFuture.all(futures1).onSuccess(a -> {
        CompositeFuture.all(futures2).onSuccess(b -> {
          HostCheckCR o3 = new HostCheckCR();
          o3.setSiteRequest_(o.getSiteRequest_());
          o3.setPk(pk);
          promise.complete(o3);
        }).onFailure(ex -> {
          LOG.error(String.format("sqlPATCHHostCheckCR failed. "), ex);
          promise.tryFail(ex);
        });
      }).onFailure(ex -> {
        LOG.error(String.format("sqlPATCHHostCheckCR failed. "), ex);
        promise.tryFail(ex);
      });
    } catch(Exception ex) {
      LOG.error(String.format("sqlPATCHHostCheckCR failed. "), ex);
      promise.tryFail(ex);
    }
    return promise.future();
  }

  public Future<ServiceResponse> response200PATCHHostCheckCR(SiteRequest siteRequest) {
    Promise<ServiceResponse> promise = Promise.promise();
    try {
      JsonObject json = new JsonObject();
      promise.complete(ServiceResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily())));
    } catch(Exception ex) {
      LOG.error(String.format("response200PATCHHostCheckCR failed. "), ex);
      promise.tryFail(ex);
    }
    return promise.future();
  }

  // POST //

  @Override
  public void postHostCheckCR(JsonObject body, ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler) {
    LOG.debug(String.format("postHostCheckCR started. "));
    Boolean classPublicRead = false;
    user(serviceRequest, SiteRequest.class, SiteUser.class, SiteUser.getClassApiAddress(), "postSiteUserFuture", "patchSiteUserFuture", classPublicRead).onSuccess(siteRequest -> {
      try {
        siteRequest.setLang("enUS");
        String changeRequestId = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("changeRequestId");
        String HOSTCHECKCR = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("HOSTCHECKCR");
        List<String> groups = Optional.ofNullable(siteRequest.getGroups()).orElse(new ArrayList<>());
        MultiMap form = MultiMap.caseInsensitiveMultiMap();
        form.add("grant_type", "urn:ietf:params:oauth:grant-type:uma-ticket");
        form.add("audience", config.getString(ComputateConfigKeys.AUTH_CLIENT));
        form.add("response_mode", "permissions");
        form.add("permission", String.format("%s#%s", HostCheckCR.CLASS_AUTH_RESOURCE, "GET"));
        form.add("permission", String.format("%s#%s", HostCheckCR.CLASS_AUTH_RESOURCE, "POST"));
        form.add("permission", String.format("%s#%s", HostCheckCR.CLASS_AUTH_RESOURCE, "PATCH"));
        form.add("permission", String.format("%s#%s", HostCheckCR.CLASS_AUTH_RESOURCE, "PUT"));
        form.add("permission", String.format("%s#%s", HostCheckCR.CLASS_AUTH_RESOURCE, "DELETE"));
        form.add("permission", String.format("%s#%s", HostCheckCR.CLASS_AUTH_RESOURCE, "Admin"));
        form.add("permission", String.format("%s#%s", HostCheckCR.CLASS_AUTH_RESOURCE, "SuperAdmin"));
        if(changeRequestId != null)
          form.add("permission", String.format("%s-%s#%s", HostCheckCR.CLASS_AUTH_RESOURCE, changeRequestId, "POST"));
        groups.stream().map(group -> {
              Matcher mPermission = Pattern.compile("^/(.*-?TENANT-([a-z0-9\\-]+))-(\\w+)$").matcher(group);
              return mPermission.find() ? mPermission : null;
            }).filter(v -> v != null).forEach(mPermission -> {
              form.add("permission", String.format("%s#%s", mPermission.group(1), mPermission.group(3)));
            });
        groups.stream().map(group -> {
              Matcher mPermission = Pattern.compile("^/(.*-?JOBTEMPLATE-([a-z0-9\\-]+))-(\\w+)$").matcher(group);
              return mPermission.find() ? mPermission : null;
            }).filter(v -> v != null).forEach(mPermission -> {
              form.add("permission", String.format("%s#%s", mPermission.group(1), mPermission.group(3)));
            });
        groups.stream().map(group -> {
              Matcher mPermission = Pattern.compile("^/(.*-?HOSTCHECK-([a-z0-9\\-]+))-(\\w+)$").matcher(group);
              return mPermission.find() ? mPermission : null;
            }).filter(v -> v != null).forEach(mPermission -> {
              form.add("permission", String.format("%s#%s", mPermission.group(1), mPermission.group(3)));
            });
        groups.stream().map(group -> {
              Matcher mPermission = Pattern.compile("^/(.*-?HOSTCHECK-([a-z0-9\\-]+))-(\\w+)$").matcher(group);
              return mPermission.find() ? mPermission : null;
            }).filter(v -> v != null).forEach(mPermission -> {
              form.add("permission", String.format("%s#%s", mPermission.group(1), mPermission.group(3)));
            });
        groups.stream().map(group -> {
              Matcher mPermission = Pattern.compile("^/(.*-?HOSTCHECK-([a-z0-9\\-]+))-(\\w+)$").matcher(group);
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
            JsonArray scopes = authorizationDecisionBody.stream().map(o -> (JsonObject)o).filter(o -> "HOSTCHECKCR".equals(o.getString("rsname"))).findFirst().map(decision -> ((JsonObject)decision).getJsonArray("scopes")).orElse(new JsonArray());
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
                  Matcher mPermission = Pattern.compile("^(.*-?JOBTEMPLATE-([a-z0-9\\-]+))$").matcher(permission.getString("rsname"));
                  return permission.getJsonArray("scopes").contains("POST")
                      && mPermission.find();
                }).forEach(permission -> {
                  fqs.add(String.format("%s:%s", "jobTemplateResource", permission.getString("rsname")));
                  permission.getJsonArray("scopes").stream().map(s -> (String)s).forEach(scope -> {
                    if(!scopes.contains(scope))
                      scopes.add(scope);
                  });
                });
            authorizationDecisionBody.stream().map(o -> (JsonObject)o).filter(permission -> {
                  Matcher mPermission = Pattern.compile("^(.*-?HOSTCHECK-([a-z0-9\\-]+))$").matcher(permission.getString("rsname"));
                  return permission.getJsonArray("scopes").contains("POST")
                      && mPermission.find();
                }).forEach(permission -> {
                  fqs.add(String.format("%s:%s", "checkResource", permission.getString("rsname")));
                  permission.getJsonArray("scopes").stream().map(s -> (String)s).forEach(scope -> {
                    if(!scopes.contains(scope))
                      scopes.add(scope);
                  });
                });
            authorizationDecisionBody.stream().map(o -> (JsonObject)o).filter(permission -> {
                  Matcher mPermission = Pattern.compile("^(.*-?HOSTCHECK-([a-z0-9\\-]+))$").matcher(permission.getString("rsname"));
                  return permission.getJsonArray("scopes").contains("POST")
                      && mPermission.find();
                }).forEach(permission -> {
                  fqs.add(String.format("%s:%s", "changeRequestId", permission.getString("rsname")));
                  permission.getJsonArray("scopes").stream().map(s -> (String)s).forEach(scope -> {
                    if(!scopes.contains(scope))
                      scopes.add(scope);
                  });
                });
            authorizationDecisionBody.stream().map(o -> (JsonObject)o).filter(permission -> {
                  Matcher mPermission = Pattern.compile("^(.*-?HOSTCHECK-([a-z0-9\\-]+))$").matcher(permission.getString("rsname"));
                  return permission.getJsonArray("scopes").contains("POST")
                      && mPermission.find();
                }).forEach(permission -> {
                  fqs.add(String.format("%s:%s", "checkResourceChange", permission.getString("rsname")));
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
              eventBus.publish("websocketHostCheckCR", JsonObject.mapFrom(apiRequest).toString());
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
              eventBus.request(HostCheckCR.getClassApiAddress(), json, new DeliveryOptions().addHeader("action", "postHostCheckCRFuture")).onSuccess(a -> {
                JsonObject responseMessage = (JsonObject)a.body();
                JsonObject responseBody = new JsonObject(Buffer.buffer(JsonUtil.BASE64_DECODER.decode(responseMessage.getString("payload"))));
                apiRequest.setSolrId(responseBody.getString(HostCheckCR.VAR_solrId));
                eventHandler.handle(Future.succeededFuture(ServiceResponse.completedWithJson(Buffer.buffer(responseBody.encodePrettily()))));
                LOG.debug(String.format("postHostCheckCR succeeded. "));
              }).onFailure(ex -> {
                LOG.error(String.format("postHostCheckCR failed. "), ex);
                error(siteRequest, eventHandler, ex);
              });
            }
          } catch(Exception ex) {
            LOG.error(String.format("postHostCheckCR failed. "), ex);
            error(null, eventHandler, ex);
          }
        });
      } catch(Exception ex) {
        LOG.error(String.format("postHostCheckCR failed. "), ex);
        error(null, eventHandler, ex);
      }
    }).onFailure(ex -> {
      if("Inactive Token".equals(ex.getMessage()) || StringUtils.startsWith(ex.getMessage(), "invalid_grant:")) {
        try {
          eventHandler.handle(Future.succeededFuture(new ServiceResponse(302, "Found", null, MultiMap.caseInsensitiveMultiMap().add(HttpHeaders.LOCATION, "/logout?redirect_uri=" + URLEncoder.encode(serviceRequest.getExtra().getString("uri"), "UTF-8")))));
        } catch(Exception ex2) {
          LOG.error(String.format("postHostCheckCR failed. ", ex2));
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
        LOG.error(String.format("postHostCheckCR failed. "), ex);
        error(null, eventHandler, ex);
      }
    });
  }

  @Override
  public void postHostCheckCRFuture(JsonObject body, ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler) {
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
        postHostCheckCRFuture(siteRequest, false).onSuccess(o -> {
          eventHandler.handle(Future.succeededFuture(ServiceResponse.completedWithJson(Buffer.buffer(JsonObject.mapFrom(o).encodePrettily()))));
        }).onFailure(ex -> {
          eventHandler.handle(Future.failedFuture(ex));
        });
      } catch(Throwable ex) {
        LOG.error(String.format("postHostCheckCR failed. "), ex);
        error(null, eventHandler, ex);
      }
    }).onFailure(ex -> {
      if("Inactive Token".equals(ex.getMessage()) || StringUtils.startsWith(ex.getMessage(), "invalid_grant:")) {
        try {
          eventHandler.handle(Future.succeededFuture(new ServiceResponse(302, "Found", null, MultiMap.caseInsensitiveMultiMap().add(HttpHeaders.LOCATION, "/logout?redirect_uri=" + URLEncoder.encode(serviceRequest.getExtra().getString("uri"), "UTF-8")))));
        } catch(Exception ex2) {
          LOG.error(String.format("postHostCheckCR failed. ", ex2));
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
        LOG.error(String.format("postHostCheckCR failed. "), ex);
        error(null, eventHandler, ex);
      }
    });
  }

  public Future<HostCheckCR> postHostCheckCRFuture(SiteRequest siteRequest, Boolean changeRequestId) {
    Promise<HostCheckCR> promise = Promise.promise();

    try {
      pgPool.withTransaction(sqlConnection -> {
        Promise<HostCheckCR> promise1 = Promise.promise();
        siteRequest.setSqlConnection(sqlConnection);
        varsHostCheckCR(siteRequest).onSuccess(a -> {
          createHostCheckCR(siteRequest).onSuccess(hostCheckCR -> {
            sqlPOSTHostCheckCR(hostCheckCR, changeRequestId).onSuccess(b -> {
              persistHostCheckCR(hostCheckCR, false).onSuccess(c -> {
                relateHostCheckCR(hostCheckCR).onSuccess(d -> {
                  indexHostCheckCR(hostCheckCR).onSuccess(o2 -> {
                    promise1.complete(hostCheckCR);
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
      }).compose(hostCheckCR -> {
        Promise<HostCheckCR> promise2 = Promise.promise();
        refreshHostCheckCR(hostCheckCR).onSuccess(a -> {
          try {
            ApiRequest apiRequest = siteRequest.getApiRequest_();
            if(apiRequest != null) {
              apiRequest.setNumPATCH(apiRequest.getNumPATCH() + 1);
              hostCheckCR.apiRequestHostCheckCR();
              eventBus.publish("websocketHostCheckCR", JsonObject.mapFrom(apiRequest).toString());
            }
            promise2.complete(hostCheckCR);
          } catch(Exception ex) {
            LOG.error(String.format("postHostCheckCRFuture failed. "), ex);
            promise2.tryFail(ex);
          }
        }).onFailure(ex -> {
          promise2.tryFail(ex);
        });
        return promise2.future();
      }).onSuccess(hostCheckCR -> {
        try {
          ApiRequest apiRequest = siteRequest.getApiRequest_();
          if(apiRequest != null) {
            apiRequest.setNumPATCH(apiRequest.getNumPATCH() + 1);
            hostCheckCR.apiRequestHostCheckCR();
            eventBus.publish("websocketHostCheckCR", JsonObject.mapFrom(apiRequest).toString());
          }
          promise.complete(hostCheckCR);
        } catch(Exception ex) {
          LOG.error(String.format("postHostCheckCRFuture failed. "), ex);
          promise.tryFail(ex);
        }
      }).onFailure(ex -> {
        promise.tryFail(ex);
      });
    } catch(Exception ex) {
      LOG.error(String.format("postHostCheckCRFuture failed. "), ex);
      promise.tryFail(ex);
    }
    return promise.future();
  }

  public Future<HostCheckCR> sqlPOSTHostCheckCR(HostCheckCR o, Boolean inheritPrimaryKey) {
    Promise<HostCheckCR> promise = Promise.promise();
    try {
      SiteRequest siteRequest = o.getSiteRequest_();
      ApiRequest apiRequest = siteRequest.getApiRequest_();
      List<String> solrIds = Optional.ofNullable(apiRequest).map(r -> r.getSolrIds()).orElse(new ArrayList<>());
      List<String> classes = Optional.ofNullable(apiRequest).map(r -> r.getClasses()).orElse(new ArrayList<>());
      SqlConnection sqlConnection = siteRequest.getSqlConnection();
      Integer num = 1;
      StringBuilder bSql = new StringBuilder("UPDATE HostCheckCR SET ");
      List<Object> bParams = new ArrayList<Object>();
      Long pk = o.getPk();
      JsonObject jsonObject = siteRequest.getJsonObject();
      HostCheckCR o2 = new HostCheckCR();
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
          case HostCheckCR.VAR_tenantResource:
            Optional.ofNullable(jsonObject.getString(entityVar)).ifPresent(val -> {
              futures1.add(Future.future(promise2 -> {
                searchModel(siteRequest).query(Tenant.varIndexedTenant(Tenant.VAR_tenantResource), Tenant.class, val).onSuccess(o3 -> {
                  String solrId2 = Optional.ofNullable(o3).map(o4 -> o4.getSolrId()).filter(solrId3 -> !solrIds.contains(solrId3)).orElse(null);
                  if(solrId2 != null) {
                    solrIds.add(solrId2);
                    classes.add("Tenant");
                  }
                  sql(siteRequest).update(HostCheckCR.class, pk).set(HostCheckCR.VAR_tenantResource, Tenant.class, solrId2, val).onSuccess(a -> {
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
          case HostCheckCR.VAR_tenantId:
            o2.setTenantId(jsonObject.getString(entityVar));
            if(bParams.size() > 0) {
              bSql.append(", ");
            }
            bSql.append(HostCheckCR.VAR_tenantId + "=$" + num);
            num++;
            bParams.add(o2.sqlTenantId());
            break;
          case HostCheckCR.VAR_created:
            o2.setCreated(jsonObject.getString(entityVar));
            if(bParams.size() > 0) {
              bSql.append(", ");
            }
            bSql.append(HostCheckCR.VAR_created + "=$" + num);
            num++;
            bParams.add(o2.sqlCreated());
            break;
          case HostCheckCR.VAR_aapOrganizationId:
            o2.setAapOrganizationId(jsonObject.getString(entityVar));
            if(bParams.size() > 0) {
              bSql.append(", ");
            }
            bSql.append(HostCheckCR.VAR_aapOrganizationId + "=$" + num);
            num++;
            bParams.add(o2.sqlAapOrganizationId());
            break;
          case HostCheckCR.VAR_jobTemplateResource:
            Optional.ofNullable(jsonObject.getString(entityVar)).ifPresent(val -> {
              futures1.add(Future.future(promise2 -> {
                searchModel(siteRequest).query(JobTemplate.varIndexedJobTemplate(JobTemplate.VAR_jobTemplateResource), JobTemplate.class, val).onSuccess(o3 -> {
                  String solrId2 = Optional.ofNullable(o3).map(o4 -> o4.getSolrId()).filter(solrId3 -> !solrIds.contains(solrId3)).orElse(null);
                  if(solrId2 != null) {
                    solrIds.add(solrId2);
                    classes.add("JobTemplate");
                  }
                  sql(siteRequest).update(HostCheckCR.class, pk).set(HostCheckCR.VAR_jobTemplateResource, JobTemplate.class, solrId2, val).onSuccess(a -> {
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
          case HostCheckCR.VAR_changeRequestId:
            o2.setChangeRequestId(jsonObject.getString(entityVar));
            if(bParams.size() > 0) {
              bSql.append(", ");
            }
            bSql.append(HostCheckCR.VAR_changeRequestId + "=$" + num);
            num++;
            bParams.add(o2.sqlChangeRequestId());
            break;
          case HostCheckCR.VAR_archived:
            o2.setArchived(jsonObject.getString(entityVar));
            if(bParams.size() > 0) {
              bSql.append(", ");
            }
            bSql.append(HostCheckCR.VAR_archived + "=$" + num);
            num++;
            bParams.add(o2.sqlArchived());
            break;
          case HostCheckCR.VAR_jobTemplateId:
            o2.setJobTemplateId(jsonObject.getString(entityVar));
            if(bParams.size() > 0) {
              bSql.append(", ");
            }
            bSql.append(HostCheckCR.VAR_jobTemplateId + "=$" + num);
            num++;
            bParams.add(o2.sqlJobTemplateId());
            break;
          case HostCheckCR.VAR_createdByEmail:
            o2.setCreatedByEmail(jsonObject.getString(entityVar));
            if(bParams.size() > 0) {
              bSql.append(", ");
            }
            bSql.append(HostCheckCR.VAR_createdByEmail + "=$" + num);
            num++;
            bParams.add(o2.sqlCreatedByEmail());
            break;
          case HostCheckCR.VAR_aapTemplateId:
            o2.setAapTemplateId(jsonObject.getString(entityVar));
            if(bParams.size() > 0) {
              bSql.append(", ");
            }
            bSql.append(HostCheckCR.VAR_aapTemplateId + "=$" + num);
            num++;
            bParams.add(o2.sqlAapTemplateId());
            break;
          case HostCheckCR.VAR_createdByUserId:
            o2.setCreatedByUserId(jsonObject.getString(entityVar));
            if(bParams.size() > 0) {
              bSql.append(", ");
            }
            bSql.append(HostCheckCR.VAR_createdByUserId + "=$" + num);
            num++;
            bParams.add(o2.sqlCreatedByUserId());
            break;
          case HostCheckCR.VAR_checkName:
            o2.setCheckName(jsonObject.getString(entityVar));
            if(bParams.size() > 0) {
              bSql.append(", ");
            }
            bSql.append(HostCheckCR.VAR_checkName + "=$" + num);
            num++;
            bParams.add(o2.sqlCheckName());
            break;
          case HostCheckCR.VAR_createdByFullName:
            o2.setCreatedByFullName(jsonObject.getString(entityVar));
            if(bParams.size() > 0) {
              bSql.append(", ");
            }
            bSql.append(HostCheckCR.VAR_createdByFullName + "=$" + num);
            num++;
            bParams.add(o2.sqlCreatedByFullName());
            break;
          case HostCheckCR.VAR_checkId:
            o2.setCheckId(jsonObject.getString(entityVar));
            if(bParams.size() > 0) {
              bSql.append(", ");
            }
            bSql.append(HostCheckCR.VAR_checkId + "=$" + num);
            num++;
            bParams.add(o2.sqlCheckId());
            break;
          case HostCheckCR.VAR_createdVia:
            o2.setCreatedVia(jsonObject.getString(entityVar));
            if(bParams.size() > 0) {
              bSql.append(", ");
            }
            bSql.append(HostCheckCR.VAR_createdVia + "=$" + num);
            num++;
            bParams.add(o2.sqlCreatedVia());
            break;
          case HostCheckCR.VAR_sessionId:
            o2.setSessionId(jsonObject.getString(entityVar));
            if(bParams.size() > 0) {
              bSql.append(", ");
            }
            bSql.append(HostCheckCR.VAR_sessionId + "=$" + num);
            num++;
            bParams.add(o2.sqlSessionId());
            break;
          case HostCheckCR.VAR_checkResource:
            Optional.ofNullable(jsonObject.getString(entityVar)).ifPresent(val -> {
              futures1.add(Future.future(promise2 -> {
                searchModel(siteRequest).query(HostCheck.varIndexedHostCheck(HostCheck.VAR_checkResource), HostCheck.class, val).onSuccess(o3 -> {
                  String solrId2 = Optional.ofNullable(o3).map(o4 -> o4.getSolrId()).filter(solrId3 -> !solrIds.contains(solrId3)).orElse(null);
                  if(solrId2 != null) {
                    solrIds.add(solrId2);
                    classes.add("HostCheck");
                  }
                  sql(siteRequest).update(HostCheckCR.class, pk).set(HostCheckCR.VAR_checkResource, HostCheck.class, solrId2, val).onSuccess(a -> {
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
          case HostCheckCR.VAR_ownedByEmail:
            o2.setOwnedByEmail(jsonObject.getString(entityVar));
            if(bParams.size() > 0) {
              bSql.append(", ");
            }
            bSql.append(HostCheckCR.VAR_ownedByEmail + "=$" + num);
            num++;
            bParams.add(o2.sqlOwnedByEmail());
            break;
          case HostCheckCR.VAR_userKey:
            o2.setUserKey(jsonObject.getString(entityVar));
            if(bParams.size() > 0) {
              bSql.append(", ");
            }
            bSql.append(HostCheckCR.VAR_userKey + "=$" + num);
            num++;
            bParams.add(o2.sqlUserKey());
            break;
          case HostCheckCR.VAR_checkDescription:
            o2.setCheckDescription(jsonObject.getString(entityVar));
            if(bParams.size() > 0) {
              bSql.append(", ");
            }
            bSql.append(HostCheckCR.VAR_checkDescription + "=$" + num);
            num++;
            bParams.add(o2.sqlCheckDescription());
            break;
          case HostCheckCR.VAR_ownedByUserId:
            o2.setOwnedByUserId(jsonObject.getString(entityVar));
            if(bParams.size() > 0) {
              bSql.append(", ");
            }
            bSql.append(HostCheckCR.VAR_ownedByUserId + "=$" + num);
            num++;
            bParams.add(o2.sqlOwnedByUserId());
            break;
          case HostCheckCR.VAR_checkNamespace:
            o2.setCheckNamespace(jsonObject.getString(entityVar));
            if(bParams.size() > 0) {
              bSql.append(", ");
            }
            bSql.append(HostCheckCR.VAR_checkNamespace + "=$" + num);
            num++;
            bParams.add(o2.sqlCheckNamespace());
            break;
          case HostCheckCR.VAR_ownedByDisplayName:
            o2.setOwnedByDisplayName(jsonObject.getString(entityVar));
            if(bParams.size() > 0) {
              bSql.append(", ");
            }
            bSql.append(HostCheckCR.VAR_ownedByDisplayName + "=$" + num);
            num++;
            bParams.add(o2.sqlOwnedByDisplayName());
            break;
          case HostCheckCR.VAR_checkCommand:
            o2.setCheckCommand(jsonObject.getString(entityVar));
            if(bParams.size() > 0) {
              bSql.append(", ");
            }
            bSql.append(HostCheckCR.VAR_checkCommand + "=$" + num);
            num++;
            bParams.add(o2.sqlCheckCommand());
            break;
          case HostCheckCR.VAR_lifecycleState:
            o2.setLifecycleState(jsonObject.getString(entityVar));
            if(bParams.size() > 0) {
              bSql.append(", ");
            }
            bSql.append(HostCheckCR.VAR_lifecycleState + "=$" + num);
            num++;
            bParams.add(o2.sqlLifecycleState());
            break;
          case HostCheckCR.VAR_objectTitle:
            o2.setObjectTitle(jsonObject.getString(entityVar));
            if(bParams.size() > 0) {
              bSql.append(", ");
            }
            bSql.append(HostCheckCR.VAR_objectTitle + "=$" + num);
            num++;
            bParams.add(o2.sqlObjectTitle());
            break;
          case HostCheckCR.VAR_checkInterval:
            o2.setCheckInterval(jsonObject.getString(entityVar));
            if(bParams.size() > 0) {
              bSql.append(", ");
            }
            bSql.append(HostCheckCR.VAR_checkInterval + "=$" + num);
            num++;
            bParams.add(o2.sqlCheckInterval());
            break;
          case HostCheckCR.VAR_intentState:
            o2.setIntentState(jsonObject.getString(entityVar));
            if(bParams.size() > 0) {
              bSql.append(", ");
            }
            bSql.append(HostCheckCR.VAR_intentState + "=$" + num);
            num++;
            bParams.add(o2.sqlIntentState());
            break;
          case HostCheckCR.VAR_displayPage:
            o2.setDisplayPage(jsonObject.getString(entityVar));
            if(bParams.size() > 0) {
              bSql.append(", ");
            }
            bSql.append(HostCheckCR.VAR_displayPage + "=$" + num);
            num++;
            bParams.add(o2.sqlDisplayPage());
            break;
          case HostCheckCR.VAR_checkPublished:
            o2.setCheckPublished(jsonObject.getString(entityVar));
            if(bParams.size() > 0) {
              bSql.append(", ");
            }
            bSql.append(HostCheckCR.VAR_checkPublished + "=$" + num);
            num++;
            bParams.add(o2.sqlCheckPublished());
            break;
          case HostCheckCR.VAR_requestedState:
            o2.setRequestedState(jsonObject.getString(entityVar));
            if(bParams.size() > 0) {
              bSql.append(", ");
            }
            bSql.append(HostCheckCR.VAR_requestedState + "=$" + num);
            num++;
            bParams.add(o2.sqlRequestedState());
            break;
          case HostCheckCR.VAR_editPage:
            o2.setEditPage(jsonObject.getString(entityVar));
            if(bParams.size() > 0) {
              bSql.append(", ");
            }
            bSql.append(HostCheckCR.VAR_editPage + "=$" + num);
            num++;
            bParams.add(o2.sqlEditPage());
            break;
          case HostCheckCR.VAR_eventSubscriptions:
            o2.setEventSubscriptions(jsonObject.getJsonArray(entityVar));
            if(bParams.size() > 0) {
              bSql.append(", ");
            }
            bSql.append(HostCheckCR.VAR_eventSubscriptions + "=$" + num);
            num++;
            bParams.add(o2.sqlEventSubscriptions());
            break;
          case HostCheckCR.VAR_realizedState:
            o2.setRealizedState(jsonObject.getString(entityVar));
            if(bParams.size() > 0) {
              bSql.append(", ");
            }
            bSql.append(HostCheckCR.VAR_realizedState + "=$" + num);
            num++;
            bParams.add(o2.sqlRealizedState());
            break;
          case HostCheckCR.VAR_userPage:
            o2.setUserPage(jsonObject.getString(entityVar));
            if(bParams.size() > 0) {
              bSql.append(", ");
            }
            bSql.append(HostCheckCR.VAR_userPage + "=$" + num);
            num++;
            bParams.add(o2.sqlUserPage());
            break;
          case HostCheckCR.VAR_eventHandlers:
            o2.setEventHandlers(jsonObject.getJsonArray(entityVar));
            if(bParams.size() > 0) {
              bSql.append(", ");
            }
            bSql.append(HostCheckCR.VAR_eventHandlers + "=$" + num);
            num++;
            bParams.add(o2.sqlEventHandlers());
            break;
          case HostCheckCR.VAR_aapOrganizationIdChange:
            o2.setAapOrganizationIdChange(jsonObject.getString(entityVar));
            if(bParams.size() > 0) {
              bSql.append(", ");
            }
            bSql.append(HostCheckCR.VAR_aapOrganizationIdChange + "=$" + num);
            num++;
            bParams.add(o2.sqlAapOrganizationIdChange());
            break;
          case HostCheckCR.VAR_download:
            o2.setDownload(jsonObject.getString(entityVar));
            if(bParams.size() > 0) {
              bSql.append(", ");
            }
            bSql.append(HostCheckCR.VAR_download + "=$" + num);
            num++;
            bParams.add(o2.sqlDownload());
            break;
          case HostCheckCR.VAR_jobTemplateResourceChange:
            Optional.ofNullable(jsonObject.getString(entityVar)).ifPresent(val -> {
              futures1.add(Future.future(promise2 -> {
                searchModel(siteRequest).query(JobTemplate.varIndexedJobTemplate(JobTemplate.VAR_jobTemplateResource), JobTemplate.class, val).onSuccess(o3 -> {
                  String solrId2 = Optional.ofNullable(o3).map(o4 -> o4.getSolrId()).filter(solrId3 -> !solrIds.contains(solrId3)).orElse(null);
                  if(solrId2 != null) {
                    solrIds.add(solrId2);
                    classes.add("JobTemplate");
                  }
                  sql(siteRequest).update(HostCheckCR.class, pk).set(HostCheckCR.VAR_jobTemplateResourceChange, JobTemplate.class, solrId2, val).onSuccess(a -> {
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
          case HostCheckCR.VAR_jobTemplateIdChange:
            o2.setJobTemplateIdChange(jsonObject.getString(entityVar));
            if(bParams.size() > 0) {
              bSql.append(", ");
            }
            bSql.append(HostCheckCR.VAR_jobTemplateIdChange + "=$" + num);
            num++;
            bParams.add(o2.sqlJobTemplateIdChange());
            break;
          case HostCheckCR.VAR_aapTemplateIdChange:
            o2.setAapTemplateIdChange(jsonObject.getString(entityVar));
            if(bParams.size() > 0) {
              bSql.append(", ");
            }
            bSql.append(HostCheckCR.VAR_aapTemplateIdChange + "=$" + num);
            num++;
            bParams.add(o2.sqlAapTemplateIdChange());
            break;
          case HostCheckCR.VAR_checkNameChange:
            o2.setCheckNameChange(jsonObject.getString(entityVar));
            if(bParams.size() > 0) {
              bSql.append(", ");
            }
            bSql.append(HostCheckCR.VAR_checkNameChange + "=$" + num);
            num++;
            bParams.add(o2.sqlCheckNameChange());
            break;
          case HostCheckCR.VAR_checkIdChange:
            o2.setCheckIdChange(jsonObject.getString(entityVar));
            if(bParams.size() > 0) {
              bSql.append(", ");
            }
            bSql.append(HostCheckCR.VAR_checkIdChange + "=$" + num);
            num++;
            bParams.add(o2.sqlCheckIdChange());
            break;
          case HostCheckCR.VAR_checkResourceChange:
            o2.setCheckResourceChange(jsonObject.getString(entityVar));
            if(bParams.size() > 0) {
              bSql.append(", ");
            }
            bSql.append(HostCheckCR.VAR_checkResourceChange + "=$" + num);
            num++;
            bParams.add(o2.sqlCheckResourceChange());
            break;
          case HostCheckCR.VAR_checkDescriptionChange:
            o2.setCheckDescriptionChange(jsonObject.getString(entityVar));
            if(bParams.size() > 0) {
              bSql.append(", ");
            }
            bSql.append(HostCheckCR.VAR_checkDescriptionChange + "=$" + num);
            num++;
            bParams.add(o2.sqlCheckDescriptionChange());
            break;
          case HostCheckCR.VAR_checkNamespaceChange:
            o2.setCheckNamespaceChange(jsonObject.getString(entityVar));
            if(bParams.size() > 0) {
              bSql.append(", ");
            }
            bSql.append(HostCheckCR.VAR_checkNamespaceChange + "=$" + num);
            num++;
            bParams.add(o2.sqlCheckNamespaceChange());
            break;
          case HostCheckCR.VAR_checkCommandChange:
            o2.setCheckCommandChange(jsonObject.getString(entityVar));
            if(bParams.size() > 0) {
              bSql.append(", ");
            }
            bSql.append(HostCheckCR.VAR_checkCommandChange + "=$" + num);
            num++;
            bParams.add(o2.sqlCheckCommandChange());
            break;
          case HostCheckCR.VAR_checkIntervalChange:
            o2.setCheckIntervalChange(jsonObject.getString(entityVar));
            if(bParams.size() > 0) {
              bSql.append(", ");
            }
            bSql.append(HostCheckCR.VAR_checkIntervalChange + "=$" + num);
            num++;
            bParams.add(o2.sqlCheckIntervalChange());
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
            RuntimeException ex2 = new RuntimeException("value HostCheckCR failed", ex);
            LOG.error(String.format("relateHostCheckCR failed. "), ex2);
            a.handle(Future.failedFuture(ex2));
          });
        }));
      }
      CompositeFuture.all(futures1).onSuccess(a -> {
        CompositeFuture.all(futures2).onSuccess(b -> {
          promise.complete(o2);
        }).onFailure(ex -> {
          LOG.error(String.format("sqlPOSTHostCheckCR failed. "), ex);
          promise.tryFail(ex);
        });
      }).onFailure(ex -> {
        LOG.error(String.format("sqlPOSTHostCheckCR failed. "), ex);
        promise.tryFail(ex);
      });
    } catch(Exception ex) {
      LOG.error(String.format("sqlPOSTHostCheckCR failed. "), ex);
      promise.tryFail(ex);
    }
    return promise.future();
  }

  public Future<ServiceResponse> response200POSTHostCheckCR(HostCheckCR o) {
    Promise<ServiceResponse> promise = Promise.promise();
    try {
      SiteRequest siteRequest = o.getSiteRequest_();
      JsonObject json = JsonObject.mapFrom(o);
      promise.complete(ServiceResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily())));
    } catch(Exception ex) {
      LOG.error(String.format("response200POSTHostCheckCR failed. "), ex);
      promise.tryFail(ex);
    }
    return promise.future();
  }

  // DELETE //

  @Override
  public void deleteHostCheckCR(JsonObject body, ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler) {
    LOG.debug(String.format("deleteHostCheckCR started. "));
    Boolean classPublicRead = false;
    user(serviceRequest, SiteRequest.class, SiteUser.class, SiteUser.getClassApiAddress(), "postSiteUserFuture", "patchSiteUserFuture", classPublicRead).onSuccess(siteRequest -> {
      try {
        siteRequest.setLang("enUS");
        String changeRequestId = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("changeRequestId");
        String HOSTCHECKCR = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("HOSTCHECKCR");
        List<String> groups = Optional.ofNullable(siteRequest.getGroups()).orElse(new ArrayList<>());
        MultiMap form = MultiMap.caseInsensitiveMultiMap();
        form.add("grant_type", "urn:ietf:params:oauth:grant-type:uma-ticket");
        form.add("audience", config.getString(ComputateConfigKeys.AUTH_CLIENT));
        form.add("response_mode", "permissions");
        form.add("permission", String.format("%s#%s", HostCheckCR.CLASS_AUTH_RESOURCE, "GET"));
        form.add("permission", String.format("%s#%s", HostCheckCR.CLASS_AUTH_RESOURCE, "POST"));
        form.add("permission", String.format("%s#%s", HostCheckCR.CLASS_AUTH_RESOURCE, "PATCH"));
        form.add("permission", String.format("%s#%s", HostCheckCR.CLASS_AUTH_RESOURCE, "PUT"));
        form.add("permission", String.format("%s#%s", HostCheckCR.CLASS_AUTH_RESOURCE, "DELETE"));
        form.add("permission", String.format("%s#%s", HostCheckCR.CLASS_AUTH_RESOURCE, "Admin"));
        form.add("permission", String.format("%s#%s", HostCheckCR.CLASS_AUTH_RESOURCE, "SuperAdmin"));
        if(changeRequestId != null)
          form.add("permission", String.format("%s-%s#%s", HostCheckCR.CLASS_AUTH_RESOURCE, changeRequestId, "DELETE"));
        groups.stream().map(group -> {
              Matcher mPermission = Pattern.compile("^/(.*-?TENANT-([a-z0-9\\-]+))-(\\w+)$").matcher(group);
              return mPermission.find() ? mPermission : null;
            }).filter(v -> v != null).forEach(mPermission -> {
              form.add("permission", String.format("%s#%s", mPermission.group(1), mPermission.group(3)));
            });
        groups.stream().map(group -> {
              Matcher mPermission = Pattern.compile("^/(.*-?JOBTEMPLATE-([a-z0-9\\-]+))-(\\w+)$").matcher(group);
              return mPermission.find() ? mPermission : null;
            }).filter(v -> v != null).forEach(mPermission -> {
              form.add("permission", String.format("%s#%s", mPermission.group(1), mPermission.group(3)));
            });
        groups.stream().map(group -> {
              Matcher mPermission = Pattern.compile("^/(.*-?HOSTCHECK-([a-z0-9\\-]+))-(\\w+)$").matcher(group);
              return mPermission.find() ? mPermission : null;
            }).filter(v -> v != null).forEach(mPermission -> {
              form.add("permission", String.format("%s#%s", mPermission.group(1), mPermission.group(3)));
            });
        groups.stream().map(group -> {
              Matcher mPermission = Pattern.compile("^/(.*-?HOSTCHECK-([a-z0-9\\-]+))-(\\w+)$").matcher(group);
              return mPermission.find() ? mPermission : null;
            }).filter(v -> v != null).forEach(mPermission -> {
              form.add("permission", String.format("%s#%s", mPermission.group(1), mPermission.group(3)));
            });
        groups.stream().map(group -> {
              Matcher mPermission = Pattern.compile("^/(.*-?HOSTCHECK-([a-z0-9\\-]+))-(\\w+)$").matcher(group);
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
            JsonArray scopes = authorizationDecisionBody.stream().map(o -> (JsonObject)o).filter(o -> "HOSTCHECKCR".equals(o.getString("rsname"))).findFirst().map(decision -> ((JsonObject)decision).getJsonArray("scopes")).orElse(new JsonArray());
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
                  Matcher mPermission = Pattern.compile("^(.*-?JOBTEMPLATE-([a-z0-9\\-]+))$").matcher(permission.getString("rsname"));
                  return permission.getJsonArray("scopes").contains("DELETE")
                      && mPermission.find();
                }).forEach(permission -> {
                  fqs.add(String.format("%s:%s", "jobTemplateResource", permission.getString("rsname")));
                  permission.getJsonArray("scopes").stream().map(s -> (String)s).forEach(scope -> {
                    if(!scopes.contains(scope))
                      scopes.add(scope);
                  });
                });
            authorizationDecisionBody.stream().map(o -> (JsonObject)o).filter(permission -> {
                  Matcher mPermission = Pattern.compile("^(.*-?HOSTCHECK-([a-z0-9\\-]+))$").matcher(permission.getString("rsname"));
                  return permission.getJsonArray("scopes").contains("DELETE")
                      && mPermission.find();
                }).forEach(permission -> {
                  fqs.add(String.format("%s:%s", "checkResource", permission.getString("rsname")));
                  permission.getJsonArray("scopes").stream().map(s -> (String)s).forEach(scope -> {
                    if(!scopes.contains(scope))
                      scopes.add(scope);
                  });
                });
            authorizationDecisionBody.stream().map(o -> (JsonObject)o).filter(permission -> {
                  Matcher mPermission = Pattern.compile("^(.*-?HOSTCHECK-([a-z0-9\\-]+))$").matcher(permission.getString("rsname"));
                  return permission.getJsonArray("scopes").contains("DELETE")
                      && mPermission.find();
                }).forEach(permission -> {
                  fqs.add(String.format("%s:%s", "changeRequestId", permission.getString("rsname")));
                  permission.getJsonArray("scopes").stream().map(s -> (String)s).forEach(scope -> {
                    if(!scopes.contains(scope))
                      scopes.add(scope);
                  });
                });
            authorizationDecisionBody.stream().map(o -> (JsonObject)o).filter(permission -> {
                  Matcher mPermission = Pattern.compile("^(.*-?HOSTCHECK-([a-z0-9\\-]+))$").matcher(permission.getString("rsname"));
                  return permission.getJsonArray("scopes").contains("DELETE")
                      && mPermission.find();
                }).forEach(permission -> {
                  fqs.add(String.format("%s:%s", "checkResourceChange", permission.getString("rsname")));
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
              searchHostCheckCRList(siteRequest, false, true, true, "DELETE").onSuccess(listHostCheckCR -> {
                try {
                  ApiRequest apiRequest = new ApiRequest();
                  apiRequest.setRows(listHostCheckCR.getRequest().getRows());
                  apiRequest.setNumFound(listHostCheckCR.getResponse().getResponse().getNumFound());
                  apiRequest.setNumPATCH(0L);
                  apiRequest.initDeepApiRequest(siteRequest);
                  siteRequest.setApiRequest_(apiRequest);
                  if(apiRequest.getNumFound() == 1L)
                    apiRequest.setOriginal(listHostCheckCR.first());
                  apiRequest.setSolrId(Optional.ofNullable(listHostCheckCR.first()).map(o2 -> o2.getSolrId()).orElse(null));
                  eventBus.publish("websocketHostCheckCR", JsonObject.mapFrom(apiRequest).toString());

                  listDELETEHostCheckCR(apiRequest, listHostCheckCR).onSuccess(e -> {
                    response200DELETEHostCheckCR(siteRequest).onSuccess(response -> {
                      LOG.debug(String.format("deleteHostCheckCR succeeded. "));
                      eventHandler.handle(Future.succeededFuture(response));
                    }).onFailure(ex -> {
                      LOG.error(String.format("deleteHostCheckCR failed. "), ex);
                      error(siteRequest, eventHandler, ex);
                    });
                  }).onFailure(ex -> {
                    LOG.error(String.format("deleteHostCheckCR failed. "), ex);
                    error(siteRequest, eventHandler, ex);
                  });
                } catch(Exception ex) {
                  LOG.error(String.format("deleteHostCheckCR failed. "), ex);
                  error(siteRequest, eventHandler, ex);
                }
              }).onFailure(ex -> {
                LOG.error(String.format("deleteHostCheckCR failed. "), ex);
                error(siteRequest, eventHandler, ex);
              });
            }
          } catch(Exception ex) {
            LOG.error(String.format("deleteHostCheckCR failed. "), ex);
            error(null, eventHandler, ex);
          }
        });
      } catch(Exception ex) {
        LOG.error(String.format("deleteHostCheckCR failed. "), ex);
        error(null, eventHandler, ex);
      }
    }).onFailure(ex -> {
      if("Inactive Token".equals(ex.getMessage()) || StringUtils.startsWith(ex.getMessage(), "invalid_grant:")) {
        try {
          eventHandler.handle(Future.succeededFuture(new ServiceResponse(302, "Found", null, MultiMap.caseInsensitiveMultiMap().add(HttpHeaders.LOCATION, "/logout?redirect_uri=" + URLEncoder.encode(serviceRequest.getExtra().getString("uri"), "UTF-8")))));
        } catch(Exception ex2) {
          LOG.error(String.format("deleteHostCheckCR failed. ", ex2));
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
        LOG.error(String.format("deleteHostCheckCR failed. "), ex);
        error(null, eventHandler, ex);
      }
    });
  }

  public Future<Void> listDELETEHostCheckCR(ApiRequest apiRequest, SearchList<HostCheckCR> listHostCheckCR) {
    Promise<Void> promise = Promise.promise();
    List<Future> futures = new ArrayList<>();
    SiteRequest siteRequest = listHostCheckCR.getSiteRequest_(SiteRequest.class);
    listHostCheckCR.getList().forEach(o -> {
      SiteRequest siteRequest2 = generateSiteRequest(siteRequest.getUser(), siteRequest.getUserPrincipal(), siteRequest.getServiceRequest(), siteRequest.getJsonObject(), SiteRequest.class);
      siteRequest2.setScopes(siteRequest.getScopes());
      o.setSiteRequest_(siteRequest2);
      siteRequest2.setApiRequest_(siteRequest.getApiRequest_());
      JsonObject jsonObject = JsonObject.mapFrom(o);
      HostCheckCR o2 = jsonObject.mapTo(HostCheckCR.class);
      o2.setSiteRequest_(siteRequest2);
      futures.add(Future.future(promise1 -> {
        deleteHostCheckCRFuture(o).onSuccess(a -> {
          promise1.complete();
        }).onFailure(ex -> {
          LOG.error(String.format("listDELETEHostCheckCR failed. "), ex);
          promise1.tryFail(ex);
        });
      }));
    });
    CompositeFuture.all(futures).onSuccess( a -> {
      listHostCheckCR.next().onSuccess(next -> {
        if(next) {
          listDELETEHostCheckCR(apiRequest, listHostCheckCR).onSuccess(b -> {
            promise.complete();
          }).onFailure(ex -> {
            LOG.error(String.format("listDELETEHostCheckCR failed. "), ex);
            promise.tryFail(ex);
          });
        } else {
          promise.complete();
        }
      }).onFailure(ex -> {
        LOG.error(String.format("listDELETEHostCheckCR failed. "), ex);
        promise.tryFail(ex);
      });
    }).onFailure(ex -> {
      LOG.error(String.format("listDELETEHostCheckCR failed. "), ex);
      promise.tryFail(ex);
    });
    return promise.future();
  }

  @Override
  public void deleteHostCheckCRFuture(JsonObject body, ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler) {
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
        searchHostCheckCRList(siteRequest, false, true, true, "DELETE").onSuccess(listHostCheckCR -> {
          try {
            HostCheckCR o = listHostCheckCR.first();
            if(o != null && listHostCheckCR.getResponse().getResponse().getNumFound() == 1) {
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
              apiRequest.setId(Optional.ofNullable(listHostCheckCR.first()).map(o2 -> o2.getChangeRequestId().toString()).orElse(null));
              apiRequest.setSolrId(Optional.ofNullable(listHostCheckCR.first()).map(o2 -> o2.getSolrId()).orElse(null));
              deleteHostCheckCRFuture(o).onSuccess(o2 -> {
                eventHandler.handle(Future.succeededFuture(ServiceResponse.completedWithJson(Buffer.buffer(new JsonObject().encodePrettily()))));
              }).onFailure(ex -> {
                eventHandler.handle(Future.failedFuture(ex));
              });
            } else {
              eventHandler.handle(Future.succeededFuture(ServiceResponse.completedWithJson(Buffer.buffer(new JsonObject().encodePrettily()))));
            }
          } catch(Exception ex) {
            LOG.error(String.format("deleteHostCheckCR failed. "), ex);
            error(siteRequest, eventHandler, ex);
          }
        }).onFailure(ex -> {
          LOG.error(String.format("deleteHostCheckCR failed. "), ex);
          error(siteRequest, eventHandler, ex);
        });
      } catch(Exception ex) {
        LOG.error(String.format("deleteHostCheckCR failed. "), ex);
        error(null, eventHandler, ex);
      }
    }).onFailure(ex -> {
      LOG.error(String.format("deleteHostCheckCR failed. "), ex);
      error(null, eventHandler, ex);
    });
  }

  public Future<HostCheckCR> deleteHostCheckCRFuture(HostCheckCR o) {
    SiteRequest siteRequest = o.getSiteRequest_();
    Promise<HostCheckCR> promise = Promise.promise();

    try {
      ApiRequest apiRequest = siteRequest.getApiRequest_();
      Promise<HostCheckCR> promise1 = Promise.promise();
      pgPool.withTransaction(sqlConnection -> {
        siteRequest.setSqlConnection(sqlConnection);
        varsHostCheckCR(siteRequest).onSuccess(a -> {
          sqlDELETEHostCheckCR(o).onSuccess(hostCheckCR -> {
            relateHostCheckCR(o).onSuccess(d -> {
              unindexHostCheckCR(o).onSuccess(o2 -> {
                if(apiRequest != null) {
                  apiRequest.setNumPATCH(apiRequest.getNumPATCH() + 1);
                  if(apiRequest.getNumFound() == 1L && Optional.ofNullable(siteRequest.getJsonObject()).map(json -> json.size() > 0).orElse(false)) {
                    o2.apiRequestHostCheckCR();
                    if(apiRequest.getVars().size() > 0 && Optional.ofNullable(siteRequest.getRequestVars().get("refresh")).map(refresh -> !refresh.equals("false")).orElse(true))
                      eventBus.publish("websocketHostCheckCR", JsonObject.mapFrom(apiRequest).toString());
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
      }).compose(hostCheckCR -> {
        Promise<HostCheckCR> promise2 = Promise.promise();
        refreshHostCheckCR(o).onSuccess(a -> {
          promise2.complete(o);
        }).onFailure(ex -> {
          promise2.tryFail(ex);
        });
        return promise2.future();
      }).onSuccess(hostCheckCR -> {
        promise.complete(hostCheckCR);
      }).onFailure(ex -> {
        promise.tryFail(ex);
      });
    } catch(Exception ex) {
      LOG.error(String.format("deleteHostCheckCRFuture failed. "), ex);
      promise.tryFail(ex);
    }
    return promise.future();
  }

  public Future<Void> sqlDELETEHostCheckCR(HostCheckCR o) {
    Promise<Void> promise = Promise.promise();
    try {
      SiteRequest siteRequest = o.getSiteRequest_();
      ApiRequest apiRequest = siteRequest.getApiRequest_();
      List<String> solrIds = Optional.ofNullable(apiRequest).map(r -> r.getSolrIds()).orElse(new ArrayList<>());
      List<String> classes = Optional.ofNullable(apiRequest).map(r -> r.getClasses()).orElse(new ArrayList<>());
      SqlConnection sqlConnection = siteRequest.getSqlConnection();
      Integer num = 1;
      StringBuilder bSql = new StringBuilder("DELETE FROM HostCheckCR ");
      List<Object> bParams = new ArrayList<Object>();
      Long pk = o.getPk();
      JsonObject jsonObject = siteRequest.getJsonObject();
      HostCheckCR o2 = new HostCheckCR();
      o2.setSiteRequest_(siteRequest);
      List<Future> futures1 = new ArrayList<>();
      List<Future> futures2 = new ArrayList<>();

      if(jsonObject != null) {
        Set<String> entityVars = jsonObject.fieldNames();
        for(String entityVar : entityVars) {
          switch(entityVar) {
          case HostCheckCR.VAR_tenantResource:
            Optional.ofNullable(jsonObject.getString(entityVar)).ifPresent(val -> {
              futures1.add(Future.future(promise2 -> {
                searchModel(siteRequest).query(Tenant.varIndexedTenant(Tenant.VAR_tenantResource), Tenant.class, val).onSuccess(o3 -> {
                  String solrId2 = Optional.ofNullable(o3).map(o4 -> o4.getSolrId()).filter(solrId3 -> !solrIds.contains(solrId3)).orElse(null);
                  if(solrId2 != null) {
                    solrIds.add(solrId2);
                    classes.add("Tenant");
                  }
                  sql(siteRequest).update(HostCheckCR.class, pk).set(HostCheckCR.VAR_tenantResource, Tenant.class, null, null).onSuccess(a -> {
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
          case HostCheckCR.VAR_jobTemplateResource:
            Optional.ofNullable(jsonObject.getString(entityVar)).ifPresent(val -> {
              futures1.add(Future.future(promise2 -> {
                searchModel(siteRequest).query(JobTemplate.varIndexedJobTemplate(JobTemplate.VAR_jobTemplateResource), JobTemplate.class, val).onSuccess(o3 -> {
                  String solrId2 = Optional.ofNullable(o3).map(o4 -> o4.getSolrId()).filter(solrId3 -> !solrIds.contains(solrId3)).orElse(null);
                  if(solrId2 != null) {
                    solrIds.add(solrId2);
                    classes.add("JobTemplate");
                  }
                  sql(siteRequest).update(HostCheckCR.class, pk).set(HostCheckCR.VAR_jobTemplateResource, JobTemplate.class, null, null).onSuccess(a -> {
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
          case HostCheckCR.VAR_checkResource:
            Optional.ofNullable(jsonObject.getString(entityVar)).ifPresent(val -> {
              futures1.add(Future.future(promise2 -> {
                searchModel(siteRequest).query(HostCheck.varIndexedHostCheck(HostCheck.VAR_checkResource), HostCheck.class, val).onSuccess(o3 -> {
                  String solrId2 = Optional.ofNullable(o3).map(o4 -> o4.getSolrId()).filter(solrId3 -> !solrIds.contains(solrId3)).orElse(null);
                  if(solrId2 != null) {
                    solrIds.add(solrId2);
                    classes.add("HostCheck");
                  }
                  sql(siteRequest).update(HostCheckCR.class, pk).set(HostCheckCR.VAR_checkResource, HostCheck.class, null, null).onSuccess(a -> {
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
          case HostCheckCR.VAR_jobTemplateResourceChange:
            Optional.ofNullable(jsonObject.getString(entityVar)).ifPresent(val -> {
              futures1.add(Future.future(promise2 -> {
                searchModel(siteRequest).query(JobTemplate.varIndexedJobTemplate(JobTemplate.VAR_jobTemplateResource), JobTemplate.class, val).onSuccess(o3 -> {
                  String solrId2 = Optional.ofNullable(o3).map(o4 -> o4.getSolrId()).filter(solrId3 -> !solrIds.contains(solrId3)).orElse(null);
                  if(solrId2 != null) {
                    solrIds.add(solrId2);
                    classes.add("JobTemplate");
                  }
                  sql(siteRequest).update(HostCheckCR.class, pk).set(HostCheckCR.VAR_jobTemplateResourceChange, JobTemplate.class, null, null).onSuccess(a -> {
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
          RuntimeException ex2 = new RuntimeException("value HostCheckCR failed", ex);
          LOG.error(String.format("unrelateHostCheckCR failed. "), ex2);
          a.handle(Future.failedFuture(ex2));
        });
      }));
      CompositeFuture.all(futures1).onSuccess(a -> {
        CompositeFuture.all(futures2).onSuccess(b -> {
          promise.complete();
        }).onFailure(ex -> {
          LOG.error(String.format("sqlDELETEHostCheckCR failed. "), ex);
          promise.tryFail(ex);
        });
      }).onFailure(ex -> {
        LOG.error(String.format("sqlDELETEHostCheckCR failed. "), ex);
        promise.tryFail(ex);
      });
    } catch(Exception ex) {
      LOG.error(String.format("sqlDELETEHostCheckCR failed. "), ex);
      promise.tryFail(ex);
    }
    return promise.future();
  }

  public Future<ServiceResponse> response200DELETEHostCheckCR(SiteRequest siteRequest) {
    Promise<ServiceResponse> promise = Promise.promise();
    try {
      JsonObject json = new JsonObject();
      promise.complete(ServiceResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily())));
    } catch(Exception ex) {
      LOG.error(String.format("response200DELETEHostCheckCR failed. "), ex);
      promise.tryFail(ex);
    }
    return promise.future();
  }

  // PUTImport //

  @Override
  public void putimportHostCheckCR(JsonObject body, ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler) {
    LOG.debug(String.format("putimportHostCheckCR started. "));
    Boolean classPublicRead = false;
    user(serviceRequest, SiteRequest.class, SiteUser.class, SiteUser.getClassApiAddress(), "postSiteUserFuture", "patchSiteUserFuture", classPublicRead).onSuccess(siteRequest -> {
      try {
        siteRequest.setLang("enUS");
        String changeRequestId = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("changeRequestId");
        String HOSTCHECKCR = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("HOSTCHECKCR");
        List<String> groups = Optional.ofNullable(siteRequest.getGroups()).orElse(new ArrayList<>());
        MultiMap form = MultiMap.caseInsensitiveMultiMap();
        form.add("grant_type", "urn:ietf:params:oauth:grant-type:uma-ticket");
        form.add("audience", config.getString(ComputateConfigKeys.AUTH_CLIENT));
        form.add("response_mode", "permissions");
        form.add("permission", String.format("%s#%s", HostCheckCR.CLASS_AUTH_RESOURCE, "GET"));
        form.add("permission", String.format("%s#%s", HostCheckCR.CLASS_AUTH_RESOURCE, "POST"));
        form.add("permission", String.format("%s#%s", HostCheckCR.CLASS_AUTH_RESOURCE, "PATCH"));
        form.add("permission", String.format("%s#%s", HostCheckCR.CLASS_AUTH_RESOURCE, "PUT"));
        form.add("permission", String.format("%s#%s", HostCheckCR.CLASS_AUTH_RESOURCE, "DELETE"));
        form.add("permission", String.format("%s#%s", HostCheckCR.CLASS_AUTH_RESOURCE, "Admin"));
        form.add("permission", String.format("%s#%s", HostCheckCR.CLASS_AUTH_RESOURCE, "SuperAdmin"));
        if(changeRequestId != null)
          form.add("permission", String.format("%s-%s#%s", HostCheckCR.CLASS_AUTH_RESOURCE, changeRequestId, "PUT"));
        groups.stream().map(group -> {
              Matcher mPermission = Pattern.compile("^/(.*-?TENANT-([a-z0-9\\-]+))-(\\w+)$").matcher(group);
              return mPermission.find() ? mPermission : null;
            }).filter(v -> v != null).forEach(mPermission -> {
              form.add("permission", String.format("%s#%s", mPermission.group(1), mPermission.group(3)));
            });
        groups.stream().map(group -> {
              Matcher mPermission = Pattern.compile("^/(.*-?JOBTEMPLATE-([a-z0-9\\-]+))-(\\w+)$").matcher(group);
              return mPermission.find() ? mPermission : null;
            }).filter(v -> v != null).forEach(mPermission -> {
              form.add("permission", String.format("%s#%s", mPermission.group(1), mPermission.group(3)));
            });
        groups.stream().map(group -> {
              Matcher mPermission = Pattern.compile("^/(.*-?HOSTCHECK-([a-z0-9\\-]+))-(\\w+)$").matcher(group);
              return mPermission.find() ? mPermission : null;
            }).filter(v -> v != null).forEach(mPermission -> {
              form.add("permission", String.format("%s#%s", mPermission.group(1), mPermission.group(3)));
            });
        groups.stream().map(group -> {
              Matcher mPermission = Pattern.compile("^/(.*-?HOSTCHECK-([a-z0-9\\-]+))-(\\w+)$").matcher(group);
              return mPermission.find() ? mPermission : null;
            }).filter(v -> v != null).forEach(mPermission -> {
              form.add("permission", String.format("%s#%s", mPermission.group(1), mPermission.group(3)));
            });
        groups.stream().map(group -> {
              Matcher mPermission = Pattern.compile("^/(.*-?HOSTCHECK-([a-z0-9\\-]+))-(\\w+)$").matcher(group);
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
            JsonArray scopes = authorizationDecisionBody.stream().map(o -> (JsonObject)o).filter(o -> "HOSTCHECKCR".equals(o.getString("rsname"))).findFirst().map(decision -> ((JsonObject)decision).getJsonArray("scopes")).orElse(new JsonArray());
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
                  Matcher mPermission = Pattern.compile("^(.*-?JOBTEMPLATE-([a-z0-9\\-]+))$").matcher(permission.getString("rsname"));
                  return permission.getJsonArray("scopes").contains("PUT")
                      && mPermission.find();
                }).forEach(permission -> {
                  fqs.add(String.format("%s:%s", "jobTemplateResource", permission.getString("rsname")));
                  permission.getJsonArray("scopes").stream().map(s -> (String)s).forEach(scope -> {
                    if(!scopes.contains(scope))
                      scopes.add(scope);
                  });
                });
            authorizationDecisionBody.stream().map(o -> (JsonObject)o).filter(permission -> {
                  Matcher mPermission = Pattern.compile("^(.*-?HOSTCHECK-([a-z0-9\\-]+))$").matcher(permission.getString("rsname"));
                  return permission.getJsonArray("scopes").contains("PUT")
                      && mPermission.find();
                }).forEach(permission -> {
                  fqs.add(String.format("%s:%s", "checkResource", permission.getString("rsname")));
                  permission.getJsonArray("scopes").stream().map(s -> (String)s).forEach(scope -> {
                    if(!scopes.contains(scope))
                      scopes.add(scope);
                  });
                });
            authorizationDecisionBody.stream().map(o -> (JsonObject)o).filter(permission -> {
                  Matcher mPermission = Pattern.compile("^(.*-?HOSTCHECK-([a-z0-9\\-]+))$").matcher(permission.getString("rsname"));
                  return permission.getJsonArray("scopes").contains("PUT")
                      && mPermission.find();
                }).forEach(permission -> {
                  fqs.add(String.format("%s:%s", "changeRequestId", permission.getString("rsname")));
                  permission.getJsonArray("scopes").stream().map(s -> (String)s).forEach(scope -> {
                    if(!scopes.contains(scope))
                      scopes.add(scope);
                  });
                });
            authorizationDecisionBody.stream().map(o -> (JsonObject)o).filter(permission -> {
                  Matcher mPermission = Pattern.compile("^(.*-?HOSTCHECK-([a-z0-9\\-]+))$").matcher(permission.getString("rsname"));
                  return permission.getJsonArray("scopes").contains("PUT")
                      && mPermission.find();
                }).forEach(permission -> {
                  fqs.add(String.format("%s:%s", "checkResourceChange", permission.getString("rsname")));
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
              eventBus.publish("websocketHostCheckCR", JsonObject.mapFrom(apiRequest).toString());
              varsHostCheckCR(siteRequest).onSuccess(d -> {
                listPUTImportHostCheckCR(apiRequest, siteRequest).onSuccess(e -> {
                  response200PUTImportHostCheckCR(siteRequest).onSuccess(response -> {
                    LOG.debug(String.format("putimportHostCheckCR succeeded. "));
                    eventHandler.handle(Future.succeededFuture(response));
                  }).onFailure(ex -> {
                    LOG.error(String.format("putimportHostCheckCR failed. "), ex);
                    error(siteRequest, eventHandler, ex);
                  });
                }).onFailure(ex -> {
                  LOG.error(String.format("putimportHostCheckCR failed. "), ex);
                  error(siteRequest, eventHandler, ex);
                });
              }).onFailure(ex -> {
                LOG.error(String.format("putimportHostCheckCR failed. "), ex);
                error(siteRequest, eventHandler, ex);
              });
            }
          } catch(Exception ex) {
            LOG.error(String.format("putimportHostCheckCR failed. "), ex);
            error(null, eventHandler, ex);
          }
        });
      } catch(Exception ex) {
        LOG.error(String.format("putimportHostCheckCR failed. "), ex);
        error(null, eventHandler, ex);
      }
    }).onFailure(ex -> {
      if("Inactive Token".equals(ex.getMessage()) || StringUtils.startsWith(ex.getMessage(), "invalid_grant:")) {
        try {
          eventHandler.handle(Future.succeededFuture(new ServiceResponse(302, "Found", null, MultiMap.caseInsensitiveMultiMap().add(HttpHeaders.LOCATION, "/logout?redirect_uri=" + URLEncoder.encode(serviceRequest.getExtra().getString("uri"), "UTF-8")))));
        } catch(Exception ex2) {
          LOG.error(String.format("putimportHostCheckCR failed. ", ex2));
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
        LOG.error(String.format("putimportHostCheckCR failed. "), ex);
        error(null, eventHandler, ex);
      }
    });
  }

  public Future<Void> listPUTImportHostCheckCR(ApiRequest apiRequest, SiteRequest siteRequest) {
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
          eventBus.request(HostCheckCR.getClassApiAddress(), json, new DeliveryOptions().addHeader("action", "putimportHostCheckCRFuture")).onSuccess(a -> {
            promise1.complete();
          }).onFailure(ex -> {
            LOG.error(String.format("listPUTImportHostCheckCR failed. "), ex);
            promise1.tryFail(ex);
          });
        }));
      });
      CompositeFuture.all(futures).onSuccess(a -> {
        apiRequest.setNumPATCH(apiRequest.getNumPATCH() + 1);
        promise.complete();
      }).onFailure(ex -> {
        LOG.error(String.format("listPUTImportHostCheckCR failed. "), ex);
        promise.tryFail(ex);
      });
    } catch(Exception ex) {
      LOG.error(String.format("listPUTImportHostCheckCR failed. "), ex);
      promise.tryFail(ex);
    }
    return promise.future();
  }

  @Override
  public void putimportHostCheckCRFuture(JsonObject body, ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler) {
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
        String changeRequestId = Optional.ofNullable(body.getString(HostCheckCR.VAR_changeRequestId)).orElse(body.getString(HostCheckCR.VAR_solrId));
        if(Optional.ofNullable(serviceRequest.getParams()).map(p -> p.getJsonObject("query")).map( q -> q.getJsonArray("var")).orElse(new JsonArray()).stream().filter(s -> "refresh:false".equals(s)).count() > 0L) {
          siteRequest.getRequestVars().put( "refresh", "false" );
        }
        pgPool.getConnection().onSuccess(sqlConnection -> {
          String sqlQuery = String.format("select * from %s WHERE changeRequestId=$1", HostCheckCR.CLASS_SIMPLE_NAME);
          sqlConnection.preparedQuery(sqlQuery)
              .execute(Tuple.tuple(Arrays.asList(changeRequestId))
              ).onSuccess(result -> {
            sqlConnection.close().onSuccess(a -> {
              try {
                if(result.size() >= 1) {
                  HostCheckCR o = new HostCheckCR();
                  o.setSiteRequest_(siteRequest);
                  for(Row definition : result.value()) {
                    for(Integer i = 0; i < definition.size(); i++) {
                      try {
                        String columnName = definition.getColumnName(i);
                        Object columnValue = definition.getValue(i);
                        o.persistForClass(columnName, columnValue);
                      } catch(Exception e) {
                        LOG.error(String.format("persistHostCheckCR failed. "), e);
                      }
                    }
                  }
                  HostCheckCR o2 = new HostCheckCR();
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
                      if(!StringUtils.containsAny(f, "changeRequestId", "created", "setCreated") && !Objects.equals(o.obtainForClass(f), o2.obtainForClass(f)))
                        body2.put("set" + StringUtils.capitalize(f), bodyVal);
                    }
                  }
                  for(String f : Optional.ofNullable(o.getSaves()).orElse(new ArrayList<>())) {
                    if(!body.fieldNames().contains(f)) {
                      if(!StringUtils.containsAny(f, "changeRequestId", "created", "setCreated") && !Objects.equals(o.obtainForClass(f), o2.obtainForClass(f)))
                        body2.putNull("set" + StringUtils.capitalize(f));
                    }
                  }
                  if(result.size() >= 1) {
                    apiRequest.setOriginal(o);
                    apiRequest.setId(Optional.ofNullable(o.getChangeRequestId()).map(v -> v.toString()).orElse(null));
                    apiRequest.setSolrId(o.getSolrId());
                  }
                  siteRequest.setJsonObject(body2);
                  patchHostCheckCRFuture(o, true).onSuccess(b -> {
                    LOG.debug("Import HostCheckCR {} succeeded, modified HostCheckCR. ", body.getValue(HostCheckCR.VAR_changeRequestId));
                    eventHandler.handle(Future.succeededFuture());
                  }).onFailure(ex -> {
                    LOG.error(String.format("putimportHostCheckCRFuture failed. "), ex);
                    eventHandler.handle(Future.failedFuture(ex));
                  });
                } else {
                  postHostCheckCRFuture(siteRequest, true).onSuccess(b -> {
                    LOG.debug("Import HostCheckCR {} succeeded, created new HostCheckCR. ", body.getValue(HostCheckCR.VAR_changeRequestId));
                    eventHandler.handle(Future.succeededFuture());
                  }).onFailure(ex -> {
                    LOG.error(String.format("putimportHostCheckCRFuture failed. "), ex);
                    eventHandler.handle(Future.failedFuture(ex));
                  });
                }
              } catch(Exception ex) {
                LOG.error(String.format("putimportHostCheckCRFuture failed. "), ex);
                eventHandler.handle(Future.failedFuture(ex));
              }
            }).onFailure(ex -> {
              LOG.error(String.format("putimportHostCheckCRFuture failed. "), ex);
              eventHandler.handle(Future.failedFuture(ex));
            });
          }).onFailure(ex -> {
            LOG.error(String.format("putimportHostCheckCRFuture failed. "), ex);
            eventHandler.handle(Future.failedFuture(ex));
          });
        }).onFailure(ex -> {
          LOG.error(String.format("putimportHostCheckCRFuture failed. "), ex);
          eventHandler.handle(Future.failedFuture(ex));
        });
      } catch(Exception ex) {
        LOG.error(String.format("putimportHostCheckCRFuture failed. "), ex);
        eventHandler.handle(Future.failedFuture(ex));
      }
    }).onFailure(ex -> {
      if("Inactive Token".equals(ex.getMessage()) || StringUtils.startsWith(ex.getMessage(), "invalid_grant:")) {
        try {
          eventHandler.handle(Future.succeededFuture(new ServiceResponse(302, "Found", null, MultiMap.caseInsensitiveMultiMap().add(HttpHeaders.LOCATION, "/logout?redirect_uri=" + URLEncoder.encode(serviceRequest.getExtra().getString("uri"), "UTF-8")))));
        } catch(Exception ex2) {
          LOG.error(String.format("putimportHostCheckCR failed. ", ex2));
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
        LOG.error(String.format("putimportHostCheckCR failed. "), ex);
        error(null, eventHandler, ex);
      }
    });
  }

  public Future<ServiceResponse> response200PUTImportHostCheckCR(SiteRequest siteRequest) {
    Promise<ServiceResponse> promise = Promise.promise();
    try {
      JsonObject json = new JsonObject();
      promise.complete(ServiceResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily())));
    } catch(Exception ex) {
      LOG.error(String.format("response200PUTImportHostCheckCR failed. "), ex);
      promise.tryFail(ex);
    }
    return promise.future();
  }

  // SearchPage //

  @Override
  public void searchpageHostCheckCR(ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler) {
    oauth2AuthenticationProvider.refresh(User.create(serviceRequest.getUser())).onSuccess(user -> {
      serviceRequest.setUser(user.principal());
    Boolean classPublicRead = false;
    user(serviceRequest, SiteRequest.class, SiteUser.class, SiteUser.getClassApiAddress(), "postSiteUserFuture", "patchSiteUserFuture", classPublicRead).onSuccess(siteRequest -> {
      try {
        siteRequest.setLang("enUS");
        String changeRequestId = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("changeRequestId");
        String HOSTCHECKCR = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("HOSTCHECKCR");
        List<String> groups = Optional.ofNullable(siteRequest.getGroups()).orElse(new ArrayList<>());
        MultiMap form = MultiMap.caseInsensitiveMultiMap();
        form.add("grant_type", "urn:ietf:params:oauth:grant-type:uma-ticket");
        form.add("audience", config.getString(ComputateConfigKeys.AUTH_CLIENT));
        form.add("response_mode", "permissions");
        form.add("permission", String.format("%s#%s", HostCheckCR.CLASS_AUTH_RESOURCE, "GET"));
        form.add("permission", String.format("%s#%s", HostCheckCR.CLASS_AUTH_RESOURCE, "POST"));
        form.add("permission", String.format("%s#%s", HostCheckCR.CLASS_AUTH_RESOURCE, "PATCH"));
        form.add("permission", String.format("%s#%s", HostCheckCR.CLASS_AUTH_RESOURCE, "PUT"));
        form.add("permission", String.format("%s#%s", HostCheckCR.CLASS_AUTH_RESOURCE, "DELETE"));
        form.add("permission", String.format("%s#%s", HostCheckCR.CLASS_AUTH_RESOURCE, "Admin"));
        form.add("permission", String.format("%s#%s", HostCheckCR.CLASS_AUTH_RESOURCE, "SuperAdmin"));
        if(changeRequestId != null)
          form.add("permission", String.format("%s-%s#%s", HostCheckCR.CLASS_AUTH_RESOURCE, changeRequestId, "GET"));
        groups.stream().map(group -> {
              Matcher mPermission = Pattern.compile("^/(.*-?TENANT-([a-z0-9\\-]+))-(\\w+)$").matcher(group);
              return mPermission.find() ? mPermission : null;
            }).filter(v -> v != null).forEach(mPermission -> {
              form.add("permission", String.format("%s#%s", mPermission.group(1), mPermission.group(3)));
            });
        groups.stream().map(group -> {
              Matcher mPermission = Pattern.compile("^/(.*-?JOBTEMPLATE-([a-z0-9\\-]+))-(\\w+)$").matcher(group);
              return mPermission.find() ? mPermission : null;
            }).filter(v -> v != null).forEach(mPermission -> {
              form.add("permission", String.format("%s#%s", mPermission.group(1), mPermission.group(3)));
            });
        groups.stream().map(group -> {
              Matcher mPermission = Pattern.compile("^/(.*-?HOSTCHECK-([a-z0-9\\-]+))-(\\w+)$").matcher(group);
              return mPermission.find() ? mPermission : null;
            }).filter(v -> v != null).forEach(mPermission -> {
              form.add("permission", String.format("%s#%s", mPermission.group(1), mPermission.group(3)));
            });
        groups.stream().map(group -> {
              Matcher mPermission = Pattern.compile("^/(.*-?HOSTCHECK-([a-z0-9\\-]+))-(\\w+)$").matcher(group);
              return mPermission.find() ? mPermission : null;
            }).filter(v -> v != null).forEach(mPermission -> {
              form.add("permission", String.format("%s#%s", mPermission.group(1), mPermission.group(3)));
            });
        groups.stream().map(group -> {
              Matcher mPermission = Pattern.compile("^/(.*-?HOSTCHECK-([a-z0-9\\-]+))-(\\w+)$").matcher(group);
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
            JsonArray scopes = authorizationDecisionBody.stream().map(o -> (JsonObject)o).filter(o -> "HOSTCHECKCR".equals(o.getString("rsname"))).findFirst().map(decision -> ((JsonObject)decision).getJsonArray("scopes")).orElse(new JsonArray());
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
                    Matcher mPermission = Pattern.compile("^(.*-?JOBTEMPLATE-([a-z0-9\\-]+))$").matcher(permission.getString("rsname"));
                    return permission.getJsonArray("scopes").contains("GET")
                        && mPermission.find();
                  }).forEach(permission -> {
                    fqs.add(String.format("%s:%s", "jobTemplateResource", permission.getString("rsname")));
                    permission.getJsonArray("scopes").stream().map(s -> (String)s).forEach(scope -> {
                      if(!scopes.contains(scope))
                        scopes.add(scope);
                    });
                  });
              authorizationDecisionBody.stream().map(o -> (JsonObject)o).filter(permission -> {
                    Matcher mPermission = Pattern.compile("^(.*-?HOSTCHECK-([a-z0-9\\-]+))$").matcher(permission.getString("rsname"));
                    return permission.getJsonArray("scopes").contains("GET")
                        && mPermission.find();
                  }).forEach(permission -> {
                    fqs.add(String.format("%s:%s", "checkResource", permission.getString("rsname")));
                    permission.getJsonArray("scopes").stream().map(s -> (String)s).forEach(scope -> {
                      if(!scopes.contains(scope))
                        scopes.add(scope);
                    });
                  });
              authorizationDecisionBody.stream().map(o -> (JsonObject)o).filter(permission -> {
                    Matcher mPermission = Pattern.compile("^(.*-?HOSTCHECK-([a-z0-9\\-]+))$").matcher(permission.getString("rsname"));
                    return permission.getJsonArray("scopes").contains("GET")
                        && mPermission.find();
                  }).forEach(permission -> {
                    fqs.add(String.format("%s:%s", "changeRequestId", permission.getString("rsname")));
                    permission.getJsonArray("scopes").stream().map(s -> (String)s).forEach(scope -> {
                      if(!scopes.contains(scope))
                        scopes.add(scope);
                    });
                  });
              authorizationDecisionBody.stream().map(o -> (JsonObject)o).filter(permission -> {
                    Matcher mPermission = Pattern.compile("^(.*-?HOSTCHECK-([a-z0-9\\-]+))$").matcher(permission.getString("rsname"));
                    return permission.getJsonArray("scopes").contains("GET")
                        && mPermission.find();
                  }).forEach(permission -> {
                    fqs.add(String.format("%s:%s", "checkResourceChange", permission.getString("rsname")));
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
              searchHostCheckCRList(siteRequest, false, true, false, "GET").onSuccess(listHostCheckCR -> {
                response200SearchPageHostCheckCR(listHostCheckCR).onSuccess(response -> {
                  eventHandler.handle(Future.succeededFuture(response));
                  LOG.debug(String.format("searchpageHostCheckCR succeeded. "));
                }).onFailure(ex -> {
                  LOG.error(String.format("searchpageHostCheckCR failed. "), ex);
                  error(siteRequest, eventHandler, ex);
                });
              }).onFailure(ex -> {
                LOG.error(String.format("searchpageHostCheckCR failed. "), ex);
                error(siteRequest, eventHandler, ex);
              });
            }
          } catch(Exception ex) {
            LOG.error(String.format("searchpageHostCheckCR failed. "), ex);
            error(null, eventHandler, ex);
          }
        });
      } catch(Exception ex) {
        LOG.error(String.format("searchpageHostCheckCR failed. "), ex);
        error(null, eventHandler, ex);
      }
    }).onFailure(ex -> {
      if("Inactive Token".equals(ex.getMessage()) || StringUtils.startsWith(ex.getMessage(), "invalid_grant:")) {
        try {
          eventHandler.handle(Future.succeededFuture(new ServiceResponse(302, "Found", null, MultiMap.caseInsensitiveMultiMap().add(HttpHeaders.LOCATION, "/logout?redirect_uri=" + URLEncoder.encode(serviceRequest.getExtra().getString("uri"), "UTF-8")))));
        } catch(Exception ex2) {
          LOG.error(String.format("searchpageHostCheckCR failed. ", ex2));
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
        LOG.error(String.format("searchpageHostCheckCR failed. "), ex);
        error(null, eventHandler, ex);
      }
    });
    }).onFailure(ex -> {
      if("Inactive Token".equals(ex.getMessage()) || StringUtils.startsWith(ex.getMessage(), "invalid_grant:")) {
        try {
          eventHandler.handle(Future.succeededFuture(new ServiceResponse(302, "Found", null, MultiMap.caseInsensitiveMultiMap().add(HttpHeaders.LOCATION, "/logout?redirect_uri=" + URLEncoder.encode(serviceRequest.getExtra().getString("uri"), "UTF-8")))));
        } catch(Exception ex2) {
          LOG.error(String.format("searchpageHostCheckCR failed. ", ex2));
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
        LOG.error(String.format("searchpageHostCheckCR failed. "), ex);
        error(null, eventHandler, ex);
      }
    });
  }

  public void searchpageHostCheckCRPageInit(JsonObject ctx, HostCheckCRPage page, SearchList<HostCheckCR> listHostCheckCR, Promise<Void> promise) {
    String siteBaseUrl = config.getString(ComputateConfigKeys.SITE_BASE_URL);

    ctx.put("enUSUrlSearchPage", String.format("%s%s", siteBaseUrl, "/en-us/search/host-check-cr"));
    ctx.put("enUSUrlPage", String.format("%s%s", siteBaseUrl, "/en-us/search/host-check-cr"));
    ctx.put("enUSUrlDisplayPage", Optional.ofNullable(page.getResult()).map(o -> o.getDisplayPage()));
    ctx.put("enUSUrlEditPage", Optional.ofNullable(page.getResult()).map(o -> o.getEditPage()));
    ctx.put("enUSUrlUserPage", Optional.ofNullable(page.getResult()).map(o -> o.getUserPage()));
    ctx.put("enUSUrlDownload", Optional.ofNullable(page.getResult()).map(o -> o.getDownload()));

    promise.complete();
  }

  public String templateUriSearchPageHostCheckCR(ServiceRequest serviceRequest, HostCheckCR result) {
    return "en-us/search/host-check-cr/HostCheckCRSearchPage.htm";
  }
  public void templateSearchPageHostCheckCR(JsonObject ctx, HostCheckCRPage page, SearchList<HostCheckCR> listHostCheckCR, Promise<String> promise) {
    try {
      SiteRequest siteRequest = listHostCheckCR.getSiteRequest_(SiteRequest.class);
      ServiceRequest serviceRequest = siteRequest.getServiceRequest();
      HostCheckCR result = listHostCheckCR.first();
      String pageTemplateUri = templateUriSearchPageHostCheckCR(serviceRequest, result);
      String siteTemplatePath = config.getString(ComputateConfigKeys.TEMPLATE_PATH);
      Path resourceTemplatePath = Path.of(siteTemplatePath, pageTemplateUri);
      if(result == null || !Files.exists(resourceTemplatePath)) {
        String template = Files.readString(Path.of(siteTemplatePath, "en-us/search/host-check-cr/HostCheckCRSearchPage.htm"), Charset.forName("UTF-8"));
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
      LOG.error(String.format("templateSearchPageHostCheckCR failed. "), ex);
      ExceptionUtils.rethrow(ex);
    }
  }
  public Future<ServiceResponse> response200SearchPageHostCheckCR(SearchList<HostCheckCR> listHostCheckCR) {
    Promise<ServiceResponse> promise = Promise.promise();
    try {
      SiteRequest siteRequest = listHostCheckCR.getSiteRequest_(SiteRequest.class);
      HostCheckCRPage page = new HostCheckCRPage();
      MultiMap requestHeaders = MultiMap.caseInsensitiveMultiMap();
      siteRequest.setRequestHeaders(requestHeaders);

      if(listHostCheckCR.size() >= 1)
        siteRequest.setRequestPk(listHostCheckCR.get(0).getPk());
      page.setSearchListHostCheckCR_(listHostCheckCR);
      page.setSiteRequest_(siteRequest);
      page.setServiceRequest(siteRequest.getServiceRequest());
      page.setWebClient(webClient);
      page.setVertx(vertx);
      page.promiseDeepHostCheckCRPage(siteRequest).onSuccess(a -> {
        try {
          JsonObject ctx = ConfigKeys.getPageContext(config);
          ctx.mergeIn(JsonObject.mapFrom(page));
          Promise<Void> promise1 = Promise.promise();
          searchpageHostCheckCRPageInit(ctx, page, listHostCheckCR, promise1);
          promise1.future().onSuccess(b -> {
            try {
              Promise<String> promise2 = Promise.promise();
              templateSearchPageHostCheckCR(ctx, page, listHostCheckCR, promise2);
              promise2.future().onSuccess(renderedTemplate -> {
                try {
                  Buffer buffer = Buffer.buffer(renderedTemplate);
                  promise.complete(new ServiceResponse(200, "OK", buffer, requestHeaders));
                } catch(Throwable ex) {
                  LOG.error(String.format("response200SearchPageHostCheckCR failed. "), ex);
                  promise.fail(ex);
                }
              }).onFailure(ex -> {
                promise.fail(ex);
              });
            } catch(Throwable ex) {
              LOG.error(String.format("response200SearchPageHostCheckCR failed. "), ex);
              promise.tryFail(ex);
            }
          }).onFailure(ex -> {
            promise.tryFail(ex);
          });
        } catch(Exception ex) {
          LOG.error(String.format("response200SearchPageHostCheckCR failed. "), ex);
          promise.tryFail(ex);
        }
      }).onFailure(ex -> {
        promise.tryFail(ex);
      });
    } catch(Exception ex) {
      LOG.error(String.format("response200SearchPageHostCheckCR failed. "), ex);
      promise.tryFail(ex);
    }
    return promise.future();
  }
  public void responsePivotSearchPageHostCheckCR(List<SolrResponse.Pivot> pivots, JsonArray pivotArray) {
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
          responsePivotSearchPageHostCheckCR(pivotFields2, pivotArray2);
        }
      }
    }
  }

  // EditPage //

  @Override
  public void editpageHostCheckCR(ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler) {
    Boolean classPublicRead = false;
    user(serviceRequest, SiteRequest.class, SiteUser.class, SiteUser.getClassApiAddress(), "postSiteUserFuture", "patchSiteUserFuture", classPublicRead).onSuccess(siteRequest -> {
      try {
        siteRequest.setLang("enUS");
        String changeRequestId = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("changeRequestId");
        String HOSTCHECKCR = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("HOSTCHECKCR");
        List<String> groups = Optional.ofNullable(siteRequest.getGroups()).orElse(new ArrayList<>());
        MultiMap form = MultiMap.caseInsensitiveMultiMap();
        form.add("grant_type", "urn:ietf:params:oauth:grant-type:uma-ticket");
        form.add("audience", config.getString(ComputateConfigKeys.AUTH_CLIENT));
        form.add("response_mode", "permissions");
        form.add("permission", String.format("%s#%s", HostCheckCR.CLASS_AUTH_RESOURCE, "GET"));
        form.add("permission", String.format("%s#%s", HostCheckCR.CLASS_AUTH_RESOURCE, "POST"));
        form.add("permission", String.format("%s#%s", HostCheckCR.CLASS_AUTH_RESOURCE, "PATCH"));
        form.add("permission", String.format("%s#%s", HostCheckCR.CLASS_AUTH_RESOURCE, "PUT"));
        form.add("permission", String.format("%s#%s", HostCheckCR.CLASS_AUTH_RESOURCE, "DELETE"));
        form.add("permission", String.format("%s#%s", HostCheckCR.CLASS_AUTH_RESOURCE, "Admin"));
        form.add("permission", String.format("%s#%s", HostCheckCR.CLASS_AUTH_RESOURCE, "SuperAdmin"));
        if(changeRequestId != null)
          form.add("permission", String.format("%s-%s#%s", HostCheckCR.CLASS_AUTH_RESOURCE, changeRequestId, "GET"));
        groups.stream().map(group -> {
              Matcher mPermission = Pattern.compile("^/(.*-?TENANT-([a-z0-9\\-]+))-(\\w+)$").matcher(group);
              return mPermission.find() ? mPermission : null;
            }).filter(v -> v != null).forEach(mPermission -> {
              form.add("permission", String.format("%s#%s", mPermission.group(1), mPermission.group(3)));
            });
        groups.stream().map(group -> {
              Matcher mPermission = Pattern.compile("^/(.*-?JOBTEMPLATE-([a-z0-9\\-]+))-(\\w+)$").matcher(group);
              return mPermission.find() ? mPermission : null;
            }).filter(v -> v != null).forEach(mPermission -> {
              form.add("permission", String.format("%s#%s", mPermission.group(1), mPermission.group(3)));
            });
        groups.stream().map(group -> {
              Matcher mPermission = Pattern.compile("^/(.*-?HOSTCHECK-([a-z0-9\\-]+))-(\\w+)$").matcher(group);
              return mPermission.find() ? mPermission : null;
            }).filter(v -> v != null).forEach(mPermission -> {
              form.add("permission", String.format("%s#%s", mPermission.group(1), mPermission.group(3)));
            });
        groups.stream().map(group -> {
              Matcher mPermission = Pattern.compile("^/(.*-?HOSTCHECK-([a-z0-9\\-]+))-(\\w+)$").matcher(group);
              return mPermission.find() ? mPermission : null;
            }).filter(v -> v != null).forEach(mPermission -> {
              form.add("permission", String.format("%s#%s", mPermission.group(1), mPermission.group(3)));
            });
        groups.stream().map(group -> {
              Matcher mPermission = Pattern.compile("^/(.*-?HOSTCHECK-([a-z0-9\\-]+))-(\\w+)$").matcher(group);
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
            JsonArray scopes = authorizationDecisionBody.stream().map(o -> (JsonObject)o).filter(o -> "HOSTCHECKCR".equals(o.getString("rsname"))).findFirst().map(decision -> ((JsonObject)decision).getJsonArray("scopes")).orElse(new JsonArray());
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
                    Matcher mPermission = Pattern.compile("^(.*-?JOBTEMPLATE-([a-z0-9\\-]+))$").matcher(permission.getString("rsname"));
                    return permission.getJsonArray("scopes").contains("GET")
                        && mPermission.find();
                  }).forEach(permission -> {
                    fqs.add(String.format("%s:%s", "jobTemplateResource", permission.getString("rsname")));
                    permission.getJsonArray("scopes").stream().map(s -> (String)s).forEach(scope -> {
                      if(!scopes.contains(scope))
                        scopes.add(scope);
                    });
                  });
              authorizationDecisionBody.stream().map(o -> (JsonObject)o).filter(permission -> {
                    Matcher mPermission = Pattern.compile("^(.*-?HOSTCHECK-([a-z0-9\\-]+))$").matcher(permission.getString("rsname"));
                    return permission.getJsonArray("scopes").contains("GET")
                        && mPermission.find();
                  }).forEach(permission -> {
                    fqs.add(String.format("%s:%s", "checkResource", permission.getString("rsname")));
                    permission.getJsonArray("scopes").stream().map(s -> (String)s).forEach(scope -> {
                      if(!scopes.contains(scope))
                        scopes.add(scope);
                    });
                  });
              authorizationDecisionBody.stream().map(o -> (JsonObject)o).filter(permission -> {
                    Matcher mPermission = Pattern.compile("^(.*-?HOSTCHECK-([a-z0-9\\-]+))$").matcher(permission.getString("rsname"));
                    return permission.getJsonArray("scopes").contains("GET")
                        && mPermission.find();
                  }).forEach(permission -> {
                    fqs.add(String.format("%s:%s", "changeRequestId", permission.getString("rsname")));
                    permission.getJsonArray("scopes").stream().map(s -> (String)s).forEach(scope -> {
                      if(!scopes.contains(scope))
                        scopes.add(scope);
                    });
                  });
              authorizationDecisionBody.stream().map(o -> (JsonObject)o).filter(permission -> {
                    Matcher mPermission = Pattern.compile("^(.*-?HOSTCHECK-([a-z0-9\\-]+))$").matcher(permission.getString("rsname"));
                    return permission.getJsonArray("scopes").contains("GET")
                        && mPermission.find();
                  }).forEach(permission -> {
                    fqs.add(String.format("%s:%s", "checkResourceChange", permission.getString("rsname")));
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
              searchHostCheckCRList(siteRequest, false, true, false, "GET").onSuccess(listHostCheckCR -> {
                response200EditPageHostCheckCR(listHostCheckCR).onSuccess(response -> {
                  eventHandler.handle(Future.succeededFuture(response));
                  LOG.debug(String.format("editpageHostCheckCR succeeded. "));
                }).onFailure(ex -> {
                  LOG.error(String.format("editpageHostCheckCR failed. "), ex);
                  error(siteRequest, eventHandler, ex);
                });
              }).onFailure(ex -> {
                LOG.error(String.format("editpageHostCheckCR failed. "), ex);
                error(siteRequest, eventHandler, ex);
            });
            }
          } catch(Exception ex) {
            LOG.error(String.format("editpageHostCheckCR failed. "), ex);
            error(null, eventHandler, ex);
          }
        });
      } catch(Exception ex) {
        LOG.error(String.format("editpageHostCheckCR failed. "), ex);
        error(null, eventHandler, ex);
      }
    }).onFailure(ex -> {
      if("Inactive Token".equals(ex.getMessage()) || StringUtils.startsWith(ex.getMessage(), "invalid_grant:")) {
        try {
          eventHandler.handle(Future.succeededFuture(new ServiceResponse(302, "Found", null, MultiMap.caseInsensitiveMultiMap().add(HttpHeaders.LOCATION, "/logout?redirect_uri=" + URLEncoder.encode(serviceRequest.getExtra().getString("uri"), "UTF-8")))));
        } catch(Exception ex2) {
          LOG.error(String.format("editpageHostCheckCR failed. ", ex2));
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
        LOG.error(String.format("editpageHostCheckCR failed. "), ex);
        error(null, eventHandler, ex);
      }
    });
  }

  public void editpageHostCheckCRPageInit(JsonObject ctx, HostCheckCRPage page, SearchList<HostCheckCR> listHostCheckCR, Promise<Void> promise) {
    String siteBaseUrl = config.getString(ComputateConfigKeys.SITE_BASE_URL);

    ctx.put("enUSUrlSearchPage", String.format("%s%s", siteBaseUrl, "/en-us/search/host-check-cr"));
    ctx.put("enUSUrlDisplayPage", Optional.ofNullable(page.getResult()).map(o -> o.getDisplayPage()));
    ctx.put("enUSUrlEditPage", Optional.ofNullable(page.getResult()).map(o -> o.getEditPage()));
    ctx.put("enUSUrlPage", Optional.ofNullable(page.getResult()).map(o -> o.getEditPage()));
    ctx.put("enUSUrlUserPage", Optional.ofNullable(page.getResult()).map(o -> o.getUserPage()));
    ctx.put("enUSUrlDownload", Optional.ofNullable(page.getResult()).map(o -> o.getDownload()));

    promise.complete();
  }

  public String templateUriEditPageHostCheckCR(ServiceRequest serviceRequest, HostCheckCR result) {
    return "en-us/edit/host-check-cr/HostCheckCREditPage.htm";
  }
  public void templateEditPageHostCheckCR(JsonObject ctx, HostCheckCRPage page, SearchList<HostCheckCR> listHostCheckCR, Promise<String> promise) {
    try {
      SiteRequest siteRequest = listHostCheckCR.getSiteRequest_(SiteRequest.class);
      ServiceRequest serviceRequest = siteRequest.getServiceRequest();
      HostCheckCR result = listHostCheckCR.first();
      String pageTemplateUri = templateUriEditPageHostCheckCR(serviceRequest, result);
      String siteTemplatePath = config.getString(ComputateConfigKeys.TEMPLATE_PATH);
      Path resourceTemplatePath = Path.of(siteTemplatePath, pageTemplateUri);
      if(result == null || !Files.exists(resourceTemplatePath)) {
        String template = Files.readString(Path.of(siteTemplatePath, "en-us/edit/host-check-cr/HostCheckCREditPage.htm"), Charset.forName("UTF-8"));
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
      LOG.error(String.format("templateEditPageHostCheckCR failed. "), ex);
      ExceptionUtils.rethrow(ex);
    }
  }
  public Future<ServiceResponse> response200EditPageHostCheckCR(SearchList<HostCheckCR> listHostCheckCR) {
    Promise<ServiceResponse> promise = Promise.promise();
    try {
      SiteRequest siteRequest = listHostCheckCR.getSiteRequest_(SiteRequest.class);
      HostCheckCRPage page = new HostCheckCRPage();
      MultiMap requestHeaders = MultiMap.caseInsensitiveMultiMap();
      siteRequest.setRequestHeaders(requestHeaders);

      if(listHostCheckCR.size() >= 1)
        siteRequest.setRequestPk(listHostCheckCR.get(0).getPk());
      page.setSearchListHostCheckCR_(listHostCheckCR);
      page.setSiteRequest_(siteRequest);
      page.setServiceRequest(siteRequest.getServiceRequest());
      page.setWebClient(webClient);
      page.setVertx(vertx);
      page.promiseDeepHostCheckCRPage(siteRequest).onSuccess(a -> {
        try {
          JsonObject ctx = ConfigKeys.getPageContext(config);
          ctx.mergeIn(JsonObject.mapFrom(page));
          Promise<Void> promise1 = Promise.promise();
          editpageHostCheckCRPageInit(ctx, page, listHostCheckCR, promise1);
          promise1.future().onSuccess(b -> {
            try {
              Promise<String> promise2 = Promise.promise();
              templateEditPageHostCheckCR(ctx, page, listHostCheckCR, promise2);
              promise2.future().onSuccess(renderedTemplate -> {
                try {
                  Buffer buffer = Buffer.buffer(renderedTemplate);
                  promise.complete(new ServiceResponse(200, "OK", buffer, requestHeaders));
                } catch(Throwable ex) {
                  LOG.error(String.format("response200EditPageHostCheckCR failed. "), ex);
                  promise.fail(ex);
                }
              }).onFailure(ex -> {
                promise.fail(ex);
              });
            } catch(Throwable ex) {
              LOG.error(String.format("response200EditPageHostCheckCR failed. "), ex);
              promise.tryFail(ex);
            }
          }).onFailure(ex -> {
            promise.tryFail(ex);
          });
        } catch(Exception ex) {
          LOG.error(String.format("response200EditPageHostCheckCR failed. "), ex);
          promise.tryFail(ex);
        }
      }).onFailure(ex -> {
        promise.tryFail(ex);
      });
    } catch(Exception ex) {
      LOG.error(String.format("response200EditPageHostCheckCR failed. "), ex);
      promise.tryFail(ex);
    }
    return promise.future();
  }
  public void responsePivotEditPageHostCheckCR(List<SolrResponse.Pivot> pivots, JsonArray pivotArray) {
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
          responsePivotEditPageHostCheckCR(pivotFields2, pivotArray2);
        }
      }
    }
  }

  // DELETEFilter //

  @Override
  public void deletefilterHostCheckCR(JsonObject body, ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler) {
    LOG.debug(String.format("deletefilterHostCheckCR started. "));
    Boolean classPublicRead = false;
    user(serviceRequest, SiteRequest.class, SiteUser.class, SiteUser.getClassApiAddress(), "postSiteUserFuture", "patchSiteUserFuture", classPublicRead).onSuccess(siteRequest -> {
      try {
        siteRequest.setLang("enUS");
        String changeRequestId = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("changeRequestId");
        String HOSTCHECKCR = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("HOSTCHECKCR");
        List<String> groups = Optional.ofNullable(siteRequest.getGroups()).orElse(new ArrayList<>());
        MultiMap form = MultiMap.caseInsensitiveMultiMap();
        form.add("grant_type", "urn:ietf:params:oauth:grant-type:uma-ticket");
        form.add("audience", config.getString(ComputateConfigKeys.AUTH_CLIENT));
        form.add("response_mode", "permissions");
        form.add("permission", String.format("%s#%s", HostCheckCR.CLASS_AUTH_RESOURCE, "GET"));
        form.add("permission", String.format("%s#%s", HostCheckCR.CLASS_AUTH_RESOURCE, "POST"));
        form.add("permission", String.format("%s#%s", HostCheckCR.CLASS_AUTH_RESOURCE, "PATCH"));
        form.add("permission", String.format("%s#%s", HostCheckCR.CLASS_AUTH_RESOURCE, "PUT"));
        form.add("permission", String.format("%s#%s", HostCheckCR.CLASS_AUTH_RESOURCE, "DELETE"));
        form.add("permission", String.format("%s#%s", HostCheckCR.CLASS_AUTH_RESOURCE, "Admin"));
        form.add("permission", String.format("%s#%s", HostCheckCR.CLASS_AUTH_RESOURCE, "SuperAdmin"));
        if(changeRequestId != null)
          form.add("permission", String.format("%s-%s#%s", HostCheckCR.CLASS_AUTH_RESOURCE, changeRequestId, "DELETE"));
        groups.stream().map(group -> {
              Matcher mPermission = Pattern.compile("^/(.*-?TENANT-([a-z0-9\\-]+))-(\\w+)$").matcher(group);
              return mPermission.find() ? mPermission : null;
            }).filter(v -> v != null).forEach(mPermission -> {
              form.add("permission", String.format("%s#%s", mPermission.group(1), mPermission.group(3)));
            });
        groups.stream().map(group -> {
              Matcher mPermission = Pattern.compile("^/(.*-?JOBTEMPLATE-([a-z0-9\\-]+))-(\\w+)$").matcher(group);
              return mPermission.find() ? mPermission : null;
            }).filter(v -> v != null).forEach(mPermission -> {
              form.add("permission", String.format("%s#%s", mPermission.group(1), mPermission.group(3)));
            });
        groups.stream().map(group -> {
              Matcher mPermission = Pattern.compile("^/(.*-?HOSTCHECK-([a-z0-9\\-]+))-(\\w+)$").matcher(group);
              return mPermission.find() ? mPermission : null;
            }).filter(v -> v != null).forEach(mPermission -> {
              form.add("permission", String.format("%s#%s", mPermission.group(1), mPermission.group(3)));
            });
        groups.stream().map(group -> {
              Matcher mPermission = Pattern.compile("^/(.*-?HOSTCHECK-([a-z0-9\\-]+))-(\\w+)$").matcher(group);
              return mPermission.find() ? mPermission : null;
            }).filter(v -> v != null).forEach(mPermission -> {
              form.add("permission", String.format("%s#%s", mPermission.group(1), mPermission.group(3)));
            });
        groups.stream().map(group -> {
              Matcher mPermission = Pattern.compile("^/(.*-?HOSTCHECK-([a-z0-9\\-]+))-(\\w+)$").matcher(group);
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
            JsonArray scopes = authorizationDecisionBody.stream().map(o -> (JsonObject)o).filter(o -> "HOSTCHECKCR".equals(o.getString("rsname"))).findFirst().map(decision -> ((JsonObject)decision).getJsonArray("scopes")).orElse(new JsonArray());
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
                  Matcher mPermission = Pattern.compile("^(.*-?JOBTEMPLATE-([a-z0-9\\-]+))$").matcher(permission.getString("rsname"));
                  return permission.getJsonArray("scopes").contains("DELETE")
                      && mPermission.find();
                }).forEach(permission -> {
                  fqs.add(String.format("%s:%s", "jobTemplateResource", permission.getString("rsname")));
                  permission.getJsonArray("scopes").stream().map(s -> (String)s).forEach(scope -> {
                    if(!scopes.contains(scope))
                      scopes.add(scope);
                  });
                });
            authorizationDecisionBody.stream().map(o -> (JsonObject)o).filter(permission -> {
                  Matcher mPermission = Pattern.compile("^(.*-?HOSTCHECK-([a-z0-9\\-]+))$").matcher(permission.getString("rsname"));
                  return permission.getJsonArray("scopes").contains("DELETE")
                      && mPermission.find();
                }).forEach(permission -> {
                  fqs.add(String.format("%s:%s", "checkResource", permission.getString("rsname")));
                  permission.getJsonArray("scopes").stream().map(s -> (String)s).forEach(scope -> {
                    if(!scopes.contains(scope))
                      scopes.add(scope);
                  });
                });
            authorizationDecisionBody.stream().map(o -> (JsonObject)o).filter(permission -> {
                  Matcher mPermission = Pattern.compile("^(.*-?HOSTCHECK-([a-z0-9\\-]+))$").matcher(permission.getString("rsname"));
                  return permission.getJsonArray("scopes").contains("DELETE")
                      && mPermission.find();
                }).forEach(permission -> {
                  fqs.add(String.format("%s:%s", "changeRequestId", permission.getString("rsname")));
                  permission.getJsonArray("scopes").stream().map(s -> (String)s).forEach(scope -> {
                    if(!scopes.contains(scope))
                      scopes.add(scope);
                  });
                });
            authorizationDecisionBody.stream().map(o -> (JsonObject)o).filter(permission -> {
                  Matcher mPermission = Pattern.compile("^(.*-?HOSTCHECK-([a-z0-9\\-]+))$").matcher(permission.getString("rsname"));
                  return permission.getJsonArray("scopes").contains("DELETE")
                      && mPermission.find();
                }).forEach(permission -> {
                  fqs.add(String.format("%s:%s", "checkResourceChange", permission.getString("rsname")));
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
              searchHostCheckCRList(siteRequest, false, true, true, "DELETE").onSuccess(listHostCheckCR -> {
                try {
                  ApiRequest apiRequest = new ApiRequest();
                  apiRequest.setRows(listHostCheckCR.getRequest().getRows());
                  apiRequest.setNumFound(listHostCheckCR.getResponse().getResponse().getNumFound());
                  apiRequest.setNumPATCH(0L);
                  apiRequest.initDeepApiRequest(siteRequest);
                  siteRequest.setApiRequest_(apiRequest);
                  if(apiRequest.getNumFound() == 1L)
                    apiRequest.setOriginal(listHostCheckCR.first());
                  apiRequest.setSolrId(Optional.ofNullable(listHostCheckCR.first()).map(o2 -> o2.getSolrId()).orElse(null));
                  eventBus.publish("websocketHostCheckCR", JsonObject.mapFrom(apiRequest).toString());

                  listDELETEFilterHostCheckCR(apiRequest, listHostCheckCR).onSuccess(e -> {
                    response200DELETEFilterHostCheckCR(siteRequest).onSuccess(response -> {
                      LOG.debug(String.format("deletefilterHostCheckCR succeeded. "));
                      eventHandler.handle(Future.succeededFuture(response));
                    }).onFailure(ex -> {
                      LOG.error(String.format("deletefilterHostCheckCR failed. "), ex);
                      error(siteRequest, eventHandler, ex);
                    });
                  }).onFailure(ex -> {
                    LOG.error(String.format("deletefilterHostCheckCR failed. "), ex);
                    error(siteRequest, eventHandler, ex);
                  });
                } catch(Exception ex) {
                  LOG.error(String.format("deletefilterHostCheckCR failed. "), ex);
                  error(siteRequest, eventHandler, ex);
                }
              }).onFailure(ex -> {
                LOG.error(String.format("deletefilterHostCheckCR failed. "), ex);
                error(siteRequest, eventHandler, ex);
              });
            }
          } catch(Exception ex) {
            LOG.error(String.format("deletefilterHostCheckCR failed. "), ex);
            error(null, eventHandler, ex);
          }
        });
      } catch(Exception ex) {
        LOG.error(String.format("deletefilterHostCheckCR failed. "), ex);
        error(null, eventHandler, ex);
      }
    }).onFailure(ex -> {
      if("Inactive Token".equals(ex.getMessage()) || StringUtils.startsWith(ex.getMessage(), "invalid_grant:")) {
        try {
          eventHandler.handle(Future.succeededFuture(new ServiceResponse(302, "Found", null, MultiMap.caseInsensitiveMultiMap().add(HttpHeaders.LOCATION, "/logout?redirect_uri=" + URLEncoder.encode(serviceRequest.getExtra().getString("uri"), "UTF-8")))));
        } catch(Exception ex2) {
          LOG.error(String.format("deletefilterHostCheckCR failed. ", ex2));
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
        LOG.error(String.format("deletefilterHostCheckCR failed. "), ex);
        error(null, eventHandler, ex);
      }
    });
  }

  public Future<Void> listDELETEFilterHostCheckCR(ApiRequest apiRequest, SearchList<HostCheckCR> listHostCheckCR) {
    Promise<Void> promise = Promise.promise();
    List<Future> futures = new ArrayList<>();
    SiteRequest siteRequest = listHostCheckCR.getSiteRequest_(SiteRequest.class);
    listHostCheckCR.getList().forEach(o -> {
      SiteRequest siteRequest2 = generateSiteRequest(siteRequest.getUser(), siteRequest.getUserPrincipal(), siteRequest.getServiceRequest(), siteRequest.getJsonObject(), SiteRequest.class);
      siteRequest2.setScopes(siteRequest.getScopes());
      o.setSiteRequest_(siteRequest2);
      siteRequest2.setApiRequest_(siteRequest.getApiRequest_());
      JsonObject jsonObject = JsonObject.mapFrom(o);
      HostCheckCR o2 = jsonObject.mapTo(HostCheckCR.class);
      o2.setSiteRequest_(siteRequest2);
      futures.add(Future.future(promise1 -> {
        deletefilterHostCheckCRFuture(o).onSuccess(a -> {
          promise1.complete();
        }).onFailure(ex -> {
          LOG.error(String.format("listDELETEFilterHostCheckCR failed. "), ex);
          promise1.tryFail(ex);
        });
      }));
    });
    CompositeFuture.all(futures).onSuccess( a -> {
      listHostCheckCR.next().onSuccess(next -> {
        if(next) {
          listDELETEFilterHostCheckCR(apiRequest, listHostCheckCR).onSuccess(b -> {
            promise.complete();
          }).onFailure(ex -> {
            LOG.error(String.format("listDELETEFilterHostCheckCR failed. "), ex);
            promise.tryFail(ex);
          });
        } else {
          promise.complete();
        }
      }).onFailure(ex -> {
        LOG.error(String.format("listDELETEFilterHostCheckCR failed. "), ex);
        promise.tryFail(ex);
      });
    }).onFailure(ex -> {
      LOG.error(String.format("listDELETEFilterHostCheckCR failed. "), ex);
      promise.tryFail(ex);
    });
    return promise.future();
  }

  @Override
  public void deletefilterHostCheckCRFuture(JsonObject body, ServiceRequest serviceRequest, Handler<AsyncResult<ServiceResponse>> eventHandler) {
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
        searchHostCheckCRList(siteRequest, false, true, true, "DELETE").onSuccess(listHostCheckCR -> {
          try {
            HostCheckCR o = listHostCheckCR.first();
            if(o != null && listHostCheckCR.getResponse().getResponse().getNumFound() == 1) {
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
              apiRequest.setId(Optional.ofNullable(listHostCheckCR.first()).map(o2 -> o2.getChangeRequestId().toString()).orElse(null));
              apiRequest.setSolrId(Optional.ofNullable(listHostCheckCR.first()).map(o2 -> o2.getSolrId()).orElse(null));
              deletefilterHostCheckCRFuture(o).onSuccess(o2 -> {
                eventHandler.handle(Future.succeededFuture(ServiceResponse.completedWithJson(Buffer.buffer(new JsonObject().encodePrettily()))));
              }).onFailure(ex -> {
                eventHandler.handle(Future.failedFuture(ex));
              });
            } else {
              eventHandler.handle(Future.succeededFuture(ServiceResponse.completedWithJson(Buffer.buffer(new JsonObject().encodePrettily()))));
            }
          } catch(Exception ex) {
            LOG.error(String.format("deletefilterHostCheckCR failed. "), ex);
            error(siteRequest, eventHandler, ex);
          }
        }).onFailure(ex -> {
          LOG.error(String.format("deletefilterHostCheckCR failed. "), ex);
          error(siteRequest, eventHandler, ex);
        });
      } catch(Exception ex) {
        LOG.error(String.format("deletefilterHostCheckCR failed. "), ex);
        error(null, eventHandler, ex);
      }
    }).onFailure(ex -> {
      LOG.error(String.format("deletefilterHostCheckCR failed. "), ex);
      error(null, eventHandler, ex);
    });
  }

  public Future<HostCheckCR> deletefilterHostCheckCRFuture(HostCheckCR o) {
    SiteRequest siteRequest = o.getSiteRequest_();
    Promise<HostCheckCR> promise = Promise.promise();

    try {
      ApiRequest apiRequest = siteRequest.getApiRequest_();
      Promise<HostCheckCR> promise1 = Promise.promise();
      pgPool.withTransaction(sqlConnection -> {
        siteRequest.setSqlConnection(sqlConnection);
        varsHostCheckCR(siteRequest).onSuccess(a -> {
          sqlDELETEFilterHostCheckCR(o).onSuccess(hostCheckCR -> {
            relateHostCheckCR(o).onSuccess(d -> {
              unindexHostCheckCR(o).onSuccess(o2 -> {
                if(apiRequest != null) {
                  apiRequest.setNumPATCH(apiRequest.getNumPATCH() + 1);
                  if(apiRequest.getNumFound() == 1L && Optional.ofNullable(siteRequest.getJsonObject()).map(json -> json.size() > 0).orElse(false)) {
                    o2.apiRequestHostCheckCR();
                    if(apiRequest.getVars().size() > 0 && Optional.ofNullable(siteRequest.getRequestVars().get("refresh")).map(refresh -> !refresh.equals("false")).orElse(true))
                      eventBus.publish("websocketHostCheckCR", JsonObject.mapFrom(apiRequest).toString());
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
      }).compose(hostCheckCR -> {
        Promise<HostCheckCR> promise2 = Promise.promise();
        refreshHostCheckCR(o).onSuccess(a -> {
          promise2.complete(o);
        }).onFailure(ex -> {
          promise2.tryFail(ex);
        });
        return promise2.future();
      }).onSuccess(hostCheckCR -> {
        promise.complete(hostCheckCR);
      }).onFailure(ex -> {
        promise.tryFail(ex);
      });
    } catch(Exception ex) {
      LOG.error(String.format("deletefilterHostCheckCRFuture failed. "), ex);
      promise.tryFail(ex);
    }
    return promise.future();
  }

  public Future<Void> sqlDELETEFilterHostCheckCR(HostCheckCR o) {
    Promise<Void> promise = Promise.promise();
    try {
      SiteRequest siteRequest = o.getSiteRequest_();
      ApiRequest apiRequest = siteRequest.getApiRequest_();
      List<String> solrIds = Optional.ofNullable(apiRequest).map(r -> r.getSolrIds()).orElse(new ArrayList<>());
      List<String> classes = Optional.ofNullable(apiRequest).map(r -> r.getClasses()).orElse(new ArrayList<>());
      SqlConnection sqlConnection = siteRequest.getSqlConnection();
      Integer num = 1;
      StringBuilder bSql = new StringBuilder("DELETE FROM HostCheckCR ");
      List<Object> bParams = new ArrayList<Object>();
      Long pk = o.getPk();
      JsonObject jsonObject = siteRequest.getJsonObject();
      HostCheckCR o2 = new HostCheckCR();
      o2.setSiteRequest_(siteRequest);
      List<Future> futures1 = new ArrayList<>();
      List<Future> futures2 = new ArrayList<>();

      if(jsonObject != null) {
        Set<String> entityVars = jsonObject.fieldNames();
        for(String entityVar : entityVars) {
          switch(entityVar) {
          case HostCheckCR.VAR_tenantResource:
            Optional.ofNullable(jsonObject.getString(entityVar)).ifPresent(val -> {
              futures1.add(Future.future(promise2 -> {
                searchModel(siteRequest).query(Tenant.varIndexedTenant(Tenant.VAR_tenantResource), Tenant.class, val).onSuccess(o3 -> {
                  String solrId2 = Optional.ofNullable(o3).map(o4 -> o4.getSolrId()).filter(solrId3 -> !solrIds.contains(solrId3)).orElse(null);
                  if(solrId2 != null) {
                    solrIds.add(solrId2);
                    classes.add("Tenant");
                  }
                  sql(siteRequest).update(HostCheckCR.class, pk).set(HostCheckCR.VAR_tenantResource, Tenant.class, null, null).onSuccess(a -> {
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
          case HostCheckCR.VAR_jobTemplateResource:
            Optional.ofNullable(jsonObject.getString(entityVar)).ifPresent(val -> {
              futures1.add(Future.future(promise2 -> {
                searchModel(siteRequest).query(JobTemplate.varIndexedJobTemplate(JobTemplate.VAR_jobTemplateResource), JobTemplate.class, val).onSuccess(o3 -> {
                  String solrId2 = Optional.ofNullable(o3).map(o4 -> o4.getSolrId()).filter(solrId3 -> !solrIds.contains(solrId3)).orElse(null);
                  if(solrId2 != null) {
                    solrIds.add(solrId2);
                    classes.add("JobTemplate");
                  }
                  sql(siteRequest).update(HostCheckCR.class, pk).set(HostCheckCR.VAR_jobTemplateResource, JobTemplate.class, null, null).onSuccess(a -> {
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
          case HostCheckCR.VAR_checkResource:
            Optional.ofNullable(jsonObject.getString(entityVar)).ifPresent(val -> {
              futures1.add(Future.future(promise2 -> {
                searchModel(siteRequest).query(HostCheck.varIndexedHostCheck(HostCheck.VAR_checkResource), HostCheck.class, val).onSuccess(o3 -> {
                  String solrId2 = Optional.ofNullable(o3).map(o4 -> o4.getSolrId()).filter(solrId3 -> !solrIds.contains(solrId3)).orElse(null);
                  if(solrId2 != null) {
                    solrIds.add(solrId2);
                    classes.add("HostCheck");
                  }
                  sql(siteRequest).update(HostCheckCR.class, pk).set(HostCheckCR.VAR_checkResource, HostCheck.class, null, null).onSuccess(a -> {
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
          case HostCheckCR.VAR_jobTemplateResourceChange:
            Optional.ofNullable(jsonObject.getString(entityVar)).ifPresent(val -> {
              futures1.add(Future.future(promise2 -> {
                searchModel(siteRequest).query(JobTemplate.varIndexedJobTemplate(JobTemplate.VAR_jobTemplateResource), JobTemplate.class, val).onSuccess(o3 -> {
                  String solrId2 = Optional.ofNullable(o3).map(o4 -> o4.getSolrId()).filter(solrId3 -> !solrIds.contains(solrId3)).orElse(null);
                  if(solrId2 != null) {
                    solrIds.add(solrId2);
                    classes.add("JobTemplate");
                  }
                  sql(siteRequest).update(HostCheckCR.class, pk).set(HostCheckCR.VAR_jobTemplateResourceChange, JobTemplate.class, null, null).onSuccess(a -> {
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
          RuntimeException ex2 = new RuntimeException("value HostCheckCR failed", ex);
          LOG.error(String.format("unrelateHostCheckCR failed. "), ex2);
          a.handle(Future.failedFuture(ex2));
        });
      }));
      CompositeFuture.all(futures1).onSuccess(a -> {
        CompositeFuture.all(futures2).onSuccess(b -> {
          promise.complete();
        }).onFailure(ex -> {
          LOG.error(String.format("sqlDELETEFilterHostCheckCR failed. "), ex);
          promise.tryFail(ex);
        });
      }).onFailure(ex -> {
        LOG.error(String.format("sqlDELETEFilterHostCheckCR failed. "), ex);
        promise.tryFail(ex);
      });
    } catch(Exception ex) {
      LOG.error(String.format("sqlDELETEFilterHostCheckCR failed. "), ex);
      promise.tryFail(ex);
    }
    return promise.future();
  }

  public Future<ServiceResponse> response200DELETEFilterHostCheckCR(SiteRequest siteRequest) {
    Promise<ServiceResponse> promise = Promise.promise();
    try {
      JsonObject json = new JsonObject();
      promise.complete(ServiceResponse.completedWithJson(Buffer.buffer(Optional.ofNullable(json).orElse(new JsonObject()).encodePrettily())));
    } catch(Exception ex) {
      LOG.error(String.format("response200DELETEFilterHostCheckCR failed. "), ex);
      promise.tryFail(ex);
    }
    return promise.future();
  }

  // General //

  public Future<HostCheckCR> createHostCheckCR(SiteRequest siteRequest) {
    Promise<HostCheckCR> promise = Promise.promise();
    try {
      SqlConnection sqlConnection = siteRequest.getSqlConnection();
      String userId = siteRequest.getUserId();
      Long userKey = siteRequest.getUserKey();
      ZonedDateTime created = Optional.ofNullable(siteRequest.getJsonObject()).map(j -> j.getString("created")).map(s -> ZonedDateTime.parse(s, ComputateZonedDateTimeSerializer.ZONED_DATE_TIME_FORMATTER.withZone(ZoneId.of(config.getString(ConfigKeys.SITE_ZONE))))).orElse(ZonedDateTime.now(ZoneId.of(config.getString(ConfigKeys.SITE_ZONE))));

      sqlConnection.preparedQuery("INSERT INTO HostCheckCR(created, userKey) VALUES($1, $2) RETURNING pk")
          .collecting(Collectors.toList())
          .execute(Tuple.of(created.toOffsetDateTime(), userKey)).onSuccess(result -> {
        Row createLine = result.value().stream().findFirst().orElseGet(() -> null);
        Long pk = createLine.getLong(0);
        HostCheckCR o = new HostCheckCR();
        o.setPk(pk);
        o.setSiteRequest_(siteRequest);
        promise.complete(o);
      }).onFailure(ex -> {
        RuntimeException ex2 = new RuntimeException(ex);
        LOG.error("createHostCheckCR failed. ", ex2);
        promise.tryFail(ex2);
      });
    } catch(Exception ex) {
      LOG.error(String.format("createHostCheckCR failed. "), ex);
      promise.tryFail(ex);
    }
    return promise.future();
  }

  public void searchHostCheckCRQ(SearchList<HostCheckCR> searchList, String entityVar, String valueIndexed, String varIndexed) {
    searchList.q(varIndexed + ":" + ("*".equals(valueIndexed) ? valueIndexed : SearchTool.escapeQueryChars(valueIndexed)));
    if(!"*".equals(entityVar)) {
    }
  }

  public String searchHostCheckCRFq(SearchList<HostCheckCR> searchList, String entityVar, String valueIndexed, String varIndexed) {
    if(varIndexed == null)
      throw new RuntimeException(String.format("\"%s\" is not an indexed entity. ", entityVar));
    if(StringUtils.startsWith(valueIndexed, "[")) {
      String[] fqs = StringUtils.substringAfter(StringUtils.substringBeforeLast(valueIndexed, "]"), "[").split(" TO ");
      if(fqs.length != 2)
        throw new RuntimeException(String.format("\"%s\" invalid range query. ", valueIndexed));
      String fq1 = fqs[0].equals("*") ? fqs[0] : HostCheckCR.staticSearchFqForClass(entityVar, searchList.getSiteRequest_(SiteRequest.class), fqs[0]);
      String fq2 = fqs[1].equals("*") ? fqs[1] : HostCheckCR.staticSearchFqForClass(entityVar, searchList.getSiteRequest_(SiteRequest.class), fqs[1]);
       return varIndexed + ":[" + fq1 + " TO " + fq2 + "]";
    } else {
      return varIndexed + ":" + SearchTool.escapeQueryChars(HostCheckCR.staticSearchFqForClass(entityVar, searchList.getSiteRequest_(SiteRequest.class), valueIndexed)).replace("\\", "\\\\");
    }
  }

  public void searchHostCheckCRSort(SearchList<HostCheckCR> searchList, String entityVar, String valueIndexed, String varIndexed) {
    if(varIndexed == null)
      throw new RuntimeException(String.format("\"%s\" is not an indexed entity. ", entityVar));
    searchList.sort(varIndexed, valueIndexed);
  }

  public void searchHostCheckCRRows(SearchList<HostCheckCR> searchList, Long valueRows) {
      searchList.rows(valueRows != null ? valueRows : 10L);
  }

  public void searchHostCheckCRStart(SearchList<HostCheckCR> searchList, Long valueStart) {
    searchList.start(valueStart);
  }

  public void searchHostCheckCRVar(SearchList<HostCheckCR> searchList, String var, String value) {
    searchList.getSiteRequest_(SiteRequest.class).getRequestVars().put(var, value);
  }

  public void searchHostCheckCRUri(SearchList<HostCheckCR> searchList) {
  }

  public Future<ServiceResponse> varsHostCheckCR(SiteRequest siteRequest) {
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
          LOG.error(String.format("searchHostCheckCR failed. "), ex);
          promise.tryFail(ex);
        }
      });
      promise.complete();
    } catch(Exception ex) {
      LOG.error(String.format("searchHostCheckCR failed. "), ex);
      promise.tryFail(ex);
    }
    return promise.future();
  }

  public Future<SearchList<HostCheckCR>> searchHostCheckCRList(SiteRequest siteRequest, Boolean populate, Boolean store, Boolean modify, String scope) {
    Promise<SearchList<HostCheckCR>> promise = Promise.promise();
    try {
      ServiceRequest serviceRequest = siteRequest.getServiceRequest();
      String entityListStr = siteRequest.getServiceRequest().getParams().getJsonObject("query").getString("fl");
      String[] entityList = entityListStr == null ? null : entityListStr.split(",\\s*");
      SearchList<HostCheckCR> searchList = new SearchList<HostCheckCR>();
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
      searchList.setC(HostCheckCR.class);
      searchList.setSiteRequest_(siteRequest);
      searchList.facetMinCount(1);
      if(entityList != null) {
        for(String v : entityList) {
          searchList.fl(HostCheckCR.varIndexedHostCheckCR(v));
        }
      }

      String changeRequestId = serviceRequest.getParams().getJsonObject("path").getString("changeRequestId");
      if(changeRequestId != null) {
        searchList.fq("changeRequestId_docvalues_string:" + SearchTool.escapeQueryChars(changeRequestId));
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
                varsIndexed[i] = HostCheckCR.varIndexedHostCheckCR(entityVar);
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
                  varIndexed = HostCheckCR.varIndexedHostCheckCR(entityVar);
                  String entityQ = searchHostCheckCRFq(searchList, entityVar, valueIndexed, varIndexed);
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
                  varIndexed = HostCheckCR.varIndexedHostCheckCR(entityVar);
                  String entityFq = searchHostCheckCRFq(searchList, entityVar, valueIndexed, varIndexed);
                  mFq.appendReplacement(sb, entityFq);
                }
                if(!sb.isEmpty()) {
                  mFq.appendTail(sb);
                  searchList.fq(sb.toString());
                }
              } else if(paramName.equals("sort")) {
                entityVar = StringUtils.trim(StringUtils.substringBefore((String)paramObject, " "));
                valueIndexed = StringUtils.trim(StringUtils.substringAfter((String)paramObject, " "));
                varIndexed = HostCheckCR.varIndexedHostCheckCR(entityVar);
                searchHostCheckCRSort(searchList, entityVar, valueIndexed, varIndexed);
              } else if(paramName.equals("start")) {
                valueStart = paramObject instanceof Long ? (Long)paramObject : Long.parseLong(paramObject.toString());
                searchHostCheckCRStart(searchList, valueStart);
              } else if(paramName.equals("rows")) {
                valueRows = paramObject instanceof Long ? (Long)paramObject : Long.parseLong(paramObject.toString());
                searchHostCheckCRRows(searchList, valueRows);
              } else if(paramName.equals("stats")) {
                searchList.stats((Boolean)paramObject);
              } else if(paramName.equals("stats.field")) {
                Matcher mStats = Pattern.compile("(?:(\\{![^\\}]+\\}))?(.*)").matcher((String)paramObject);
                if(mStats.find()) {
                  String solrLocalParams = mStats.group(1);
                  entityVar = mStats.group(2).trim();
                  varIndexed = HostCheckCR.varIndexedHostCheckCR(entityVar);
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
                  varIndexed = HostCheckCR.varIndexedHostCheckCR(entityVar);
                  searchList.facetRange((solrLocalParams == null ? "" : solrLocalParams) + varIndexed);
                  facetRange = entityVar;
                }
              } else if(paramName.equals("facet.field")) {
                entityVar = (String)paramObject;
                varIndexed = HostCheckCR.varIndexedHostCheckCR(entityVar);
                if(varIndexed != null)
                  searchList.facetField(varIndexed);
              } else if(paramName.equals("var")) {
                entityVar = StringUtils.trim(StringUtils.substringBefore((String)paramObject, ":"));
                valueIndexed = URLDecoder.decode(StringUtils.trim(StringUtils.substringAfter((String)paramObject, ":")), "UTF-8");
                searchHostCheckCRVar(searchList, entityVar, valueIndexed);
              } else if(paramName.equals("cursorMark")) {
                valueCursorMark = (String)paramObject;
                searchList.cursorMark((String)paramObject);
              }
            }
            searchHostCheckCRUri(searchList);
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
      searchHostCheckCR2(siteRequest, populate, store, modify, searchList);
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
            LOG.error(String.format("searchHostCheckCR failed. "), ex);
            promise.tryFail(ex);
          });
        } else {
          promise.complete(searchList);
        }
      }).onFailure(ex -> {
        LOG.error(String.format("searchHostCheckCR failed. "), ex);
        promise.tryFail(ex);
      });
    } catch(Exception ex) {
      LOG.error(String.format("searchHostCheckCR failed. "), ex);
      promise.tryFail(ex);
    }
    return promise.future();
  }
  public void searchHostCheckCR2(SiteRequest siteRequest, Boolean populate, Boolean store, Boolean modify, SearchList<HostCheckCR> searchList) {
  }

  public Future<JsonObject> upsertHostCheckCR(HostCheckCR o, Boolean inheritPrimaryKey, Boolean patch) {
    Promise<JsonObject> promise = Promise.promise();
    try {
      SiteRequest siteRequest = o.getSiteRequest_();
      ServiceRequest serviceRequest = siteRequest.getServiceRequest();
      if(Optional.ofNullable(serviceRequest.getParams()).map(p -> p.getJsonObject("query")).map( q -> q.getJsonArray("var")).orElse(new JsonArray()).stream().filter(s -> "refresh:false".equals(s)).count() > 0L) {
        promise.complete();
      } else {
        JsonObject json = o.getSiteRequest_().getJsonObject();
        String old_tenantResource = HostCheckCR.staticJsonTenantResource(o.getTenantResource());
        String new_tenantResource = json.getString(Tenant.varJson(Tenant.VAR_tenantResource, patch));
        String tenantResource = Optional.ofNullable(new_tenantResource).orElse(old_tenantResource);
        Tenant.fqTenant(siteRequest, Tenant.VAR_tenantResource, tenantResource).onSuccess(oTenant -> {
          try {
            if(oTenant == null) {
              RuntimeException ex = new RuntimeException(String.format("Could not find a matching Tenant %s", tenantResource));
              LOG.error(ex.getMessage(), ex);
              promise.fail(ex);
            } else {
              json.put(Tenant.varJson(Tenant.VAR_tenantResource, patch), tenantResource);
              String old_jobTemplateResource = HostCheckCR.staticJsonJobTemplateResource(o.getJobTemplateResource());
              String new_jobTemplateResource = json.getString(JobTemplate.varJson(JobTemplate.VAR_jobTemplateResource, patch));
              String jobTemplateResource = Optional.ofNullable(new_jobTemplateResource).orElse(old_jobTemplateResource);
              JobTemplate.fqJobTemplate(siteRequest, JobTemplate.VAR_jobTemplateResource, jobTemplateResource).onSuccess(oJobTemplate -> {
                try {
                  if(oJobTemplate == null) {
                    RuntimeException ex = new RuntimeException(String.format("Could not find a matching JobTemplate %s", jobTemplateResource));
                    LOG.error(ex.getMessage(), ex);
                    promise.fail(ex);
                  } else {
                    json.put(JobTemplate.varJson(JobTemplate.VAR_jobTemplateResource, patch), jobTemplateResource);
                    String old_checkResource = HostCheckCR.staticJsonCheckResource(o.getCheckResource());
                    String new_checkResource = json.getString(HostCheck.varJson(HostCheck.VAR_checkResource, patch));
                    String checkResource = Optional.ofNullable(new_checkResource).orElse(old_checkResource);
                    HostCheck.fqHostCheck(siteRequest, HostCheck.VAR_checkResource, checkResource).onSuccess(oHostCheck -> {
                      try {
                        if(oHostCheck == null) {
                          RuntimeException ex = new RuntimeException(String.format("Could not find a matching HostCheck %s", checkResource));
                          LOG.error(ex.getMessage(), ex);
                          promise.fail(ex);
                        } else {
                          json.put(HostCheck.varJson(HostCheck.VAR_checkResource, patch), checkResource);

                          String old_tenantId = HostCheckCR.staticJsonTenantId(o.getTenantId());
                          String new_tenantId = json.getString(HostCheckCR.varJson(HostCheckCR.VAR_tenantId, patch));
                          String tenantId = Optional.ofNullable(Optional.ofNullable(new_tenantId).orElse(old_tenantId)).orElse(null);
                          // json.put(HostCheckCR.varJson(HostCheckCR.VAR_tenantId, patch), tenantId);

                          String old_created = HostCheckCR.staticJsonCreated(o.getCreated());
                          String new_created = json.getString(HostCheckCR.varJson(HostCheckCR.VAR_created, patch));
                          String created = Optional.ofNullable(Optional.ofNullable(new_created).orElse(old_created)).orElse(null);
                          // json.put(HostCheckCR.varJson(HostCheckCR.VAR_created, patch), created);

                          String old_aapOrganizationId = HostCheckCR.staticJsonAapOrganizationId(o.getAapOrganizationId());
                          String new_aapOrganizationId = json.getString(HostCheckCR.varJson(HostCheckCR.VAR_aapOrganizationId, patch));
                          String aapOrganizationId = Optional.ofNullable(Optional.ofNullable(new_aapOrganizationId).orElse(old_aapOrganizationId)).orElse(null);
                          // json.put(HostCheckCR.varJson(HostCheckCR.VAR_aapOrganizationId, patch), aapOrganizationId);

                          String old_changeRequestId = HostCheckCR.staticJsonChangeRequestId(o.getChangeRequestId());
                          String new_changeRequestId = json.getString(HostCheckCR.varJson(HostCheckCR.VAR_changeRequestId, patch));
                          String changeRequestId = Optional.ofNullable(Optional.ofNullable(new_changeRequestId).orElse(old_changeRequestId)).orElse(null);
                          // json.put(HostCheckCR.varJson(HostCheckCR.VAR_changeRequestId, patch), changeRequestId);

                          Boolean old_archived = HostCheckCR.staticJsonArchived(o.getArchived());
                          Boolean new_archived = json.getBoolean(HostCheckCR.varJson(HostCheckCR.VAR_archived, patch));
                          Boolean archived = Optional.ofNullable(Optional.ofNullable(new_archived).orElse(old_archived)).orElse(null);
                          // json.put(HostCheckCR.varJson(HostCheckCR.VAR_archived, patch), archived);

                          String old_jobTemplateId = HostCheckCR.staticJsonJobTemplateId(o.getJobTemplateId());
                          String new_jobTemplateId = json.getString(HostCheckCR.varJson(HostCheckCR.VAR_jobTemplateId, patch));
                          String jobTemplateId = Optional.ofNullable(Optional.ofNullable(new_jobTemplateId).orElse(old_jobTemplateId)).orElse(null);
                          // json.put(HostCheckCR.varJson(HostCheckCR.VAR_jobTemplateId, patch), jobTemplateId);

                          String old_createdByEmail = HostCheckCR.staticJsonCreatedByEmail(o.getCreatedByEmail());
                          String new_createdByEmail = json.getString(HostCheckCR.varJson(HostCheckCR.VAR_createdByEmail, patch));
                          String createdByEmail = siteRequest.getUserEmail();
                          json.put(HostCheckCR.varJson(HostCheckCR.VAR_createdByEmail, patch), createdByEmail);

                          String old_aapTemplateId = HostCheckCR.staticJsonAapTemplateId(o.getAapTemplateId());
                          String new_aapTemplateId = json.getString(HostCheckCR.varJson(HostCheckCR.VAR_aapTemplateId, patch));
                          String aapTemplateId = Optional.ofNullable(Optional.ofNullable(new_aapTemplateId).orElse(old_aapTemplateId)).orElse(null);
                          // json.put(HostCheckCR.varJson(HostCheckCR.VAR_aapTemplateId, patch), aapTemplateId);

                          String old_createdByUserId = HostCheckCR.staticJsonCreatedByUserId(o.getCreatedByUserId());
                          String new_createdByUserId = json.getString(HostCheckCR.varJson(HostCheckCR.VAR_createdByUserId, patch));
                          String createdByUserId = siteRequest.getUserId();
                          json.put(HostCheckCR.varJson(HostCheckCR.VAR_createdByUserId, patch), createdByUserId);

                          String old_checkName = HostCheckCR.staticJsonCheckName(o.getCheckName());
                          String new_checkName = json.getString(HostCheckCR.varJson(HostCheckCR.VAR_checkName, patch));
                          String checkName = Optional.ofNullable(Optional.ofNullable(new_checkName).orElse(old_checkName)).orElse(null);
                          // json.put(HostCheckCR.varJson(HostCheckCR.VAR_checkName, patch), checkName);

                          String old_createdByFullName = HostCheckCR.staticJsonCreatedByFullName(o.getCreatedByFullName());
                          String new_createdByFullName = json.getString(HostCheckCR.varJson(HostCheckCR.VAR_createdByFullName, patch));
                          String createdByFullName = siteRequest.getUserFullName();
                          json.put(HostCheckCR.varJson(HostCheckCR.VAR_createdByFullName, patch), createdByFullName);

                          String old_checkId = HostCheckCR.staticJsonCheckId(o.getCheckId());
                          String new_checkId = json.getString(HostCheckCR.varJson(HostCheckCR.VAR_checkId, patch));
                          String checkId = Optional.ofNullable(Optional.ofNullable(new_checkId).orElse(old_checkId)).orElse(null);
                          // json.put(HostCheckCR.varJson(HostCheckCR.VAR_checkId, patch), checkId);

                          String old_createdVia = HostCheckCR.staticJsonCreatedVia(o.getCreatedVia());
                          String new_createdVia = json.getString(HostCheckCR.varJson(HostCheckCR.VAR_createdVia, patch));
                          String createdVia = Optional.ofNullable(Optional.ofNullable(new_createdVia).orElse(old_createdVia)).orElse(null);
                          // json.put(HostCheckCR.varJson(HostCheckCR.VAR_createdVia, patch), createdVia);

                          String old_sessionId = HostCheckCR.staticJsonSessionId(o.getSessionId());
                          String new_sessionId = json.getString(HostCheckCR.varJson(HostCheckCR.VAR_sessionId, patch));
                          String sessionId = Optional.ofNullable(Optional.ofNullable(new_sessionId).orElse(old_sessionId)).orElse(null);
                          // json.put(HostCheckCR.varJson(HostCheckCR.VAR_sessionId, patch), sessionId);

                          String old_ownedByEmail = HostCheckCR.staticJsonOwnedByEmail(o.getOwnedByEmail());
                          String new_ownedByEmail = json.getString(HostCheckCR.varJson(HostCheckCR.VAR_ownedByEmail, patch));
                          String ownedByEmail = Optional.ofNullable(Optional.ofNullable(new_ownedByEmail).orElse(old_ownedByEmail)).orElse(null);
                          // json.put(HostCheckCR.varJson(HostCheckCR.VAR_ownedByEmail, patch), ownedByEmail);

                          String old_userKey = HostCheckCR.staticJsonUserKey(o.getUserKey());
                          String new_userKey = json.getString(HostCheckCR.varJson(HostCheckCR.VAR_userKey, patch));
                          String userKey = Optional.ofNullable(Optional.ofNullable(new_userKey).orElse(old_userKey)).orElse(null);
                          // json.put(HostCheckCR.varJson(HostCheckCR.VAR_userKey, patch), userKey);

                          String old_checkDescription = HostCheckCR.staticJsonCheckDescription(o.getCheckDescription());
                          String new_checkDescription = json.getString(HostCheckCR.varJson(HostCheckCR.VAR_checkDescription, patch));
                          String checkDescription = Optional.ofNullable(Optional.ofNullable(new_checkDescription).orElse(old_checkDescription)).orElse(null);
                          // json.put(HostCheckCR.varJson(HostCheckCR.VAR_checkDescription, patch), checkDescription);

                          String old_ownedByUserId = HostCheckCR.staticJsonOwnedByUserId(o.getOwnedByUserId());
                          String new_ownedByUserId = json.getString(HostCheckCR.varJson(HostCheckCR.VAR_ownedByUserId, patch));
                          String ownedByUserId = Optional.ofNullable(Optional.ofNullable(new_ownedByUserId).orElse(old_ownedByUserId)).orElse(null);
                          // json.put(HostCheckCR.varJson(HostCheckCR.VAR_ownedByUserId, patch), ownedByUserId);

                          String old_checkNamespace = HostCheckCR.staticJsonCheckNamespace(o.getCheckNamespace());
                          String new_checkNamespace = json.getString(HostCheckCR.varJson(HostCheckCR.VAR_checkNamespace, patch));
                          String checkNamespace = Optional.ofNullable(Optional.ofNullable(new_checkNamespace).orElse(old_checkNamespace)).orElse(null);
                          // json.put(HostCheckCR.varJson(HostCheckCR.VAR_checkNamespace, patch), checkNamespace);

                          String old_ownedByDisplayName = HostCheckCR.staticJsonOwnedByDisplayName(o.getOwnedByDisplayName());
                          String new_ownedByDisplayName = json.getString(HostCheckCR.varJson(HostCheckCR.VAR_ownedByDisplayName, patch));
                          String ownedByDisplayName = Optional.ofNullable(Optional.ofNullable(new_ownedByDisplayName).orElse(old_ownedByDisplayName)).orElse(null);
                          // json.put(HostCheckCR.varJson(HostCheckCR.VAR_ownedByDisplayName, patch), ownedByDisplayName);

                          String old_checkCommand = HostCheckCR.staticJsonCheckCommand(o.getCheckCommand());
                          String new_checkCommand = json.getString(HostCheckCR.varJson(HostCheckCR.VAR_checkCommand, patch));
                          String checkCommand = Optional.ofNullable(Optional.ofNullable(new_checkCommand).orElse(old_checkCommand)).orElse(null);
                          // json.put(HostCheckCR.varJson(HostCheckCR.VAR_checkCommand, patch), checkCommand);

                          String old_lifecycleState = HostCheckCR.staticJsonLifecycleState(o.getLifecycleState());
                          String new_lifecycleState = json.getString(HostCheckCR.varJson(HostCheckCR.VAR_lifecycleState, patch));
                          String lifecycleState = Optional.ofNullable(Optional.ofNullable(new_lifecycleState).orElse(old_lifecycleState)).orElse(null);
                          // json.put(HostCheckCR.varJson(HostCheckCR.VAR_lifecycleState, patch), lifecycleState);

                          String old_objectTitle = HostCheckCR.staticJsonObjectTitle(o.getObjectTitle());
                          String new_objectTitle = json.getString(HostCheckCR.varJson(HostCheckCR.VAR_objectTitle, patch));
                          String objectTitle = Optional.ofNullable(Optional.ofNullable(new_objectTitle).orElse(old_objectTitle)).orElse(null);
                          // json.put(HostCheckCR.varJson(HostCheckCR.VAR_objectTitle, patch), objectTitle);

                          String old_checkInterval = HostCheckCR.staticJsonCheckInterval(o.getCheckInterval());
                          String new_checkInterval = json.getString(HostCheckCR.varJson(HostCheckCR.VAR_checkInterval, patch));
                          String checkInterval = Optional.ofNullable(Optional.ofNullable(new_checkInterval).orElse(old_checkInterval)).orElse(null);
                          // json.put(HostCheckCR.varJson(HostCheckCR.VAR_checkInterval, patch), checkInterval);

                          String old_intentState = HostCheckCR.staticJsonIntentState(o.getIntentState());
                          String new_intentState = json.getString(HostCheckCR.varJson(HostCheckCR.VAR_intentState, patch));
                          String intentState = Optional.ofNullable(Optional.ofNullable(new_intentState).orElse(old_intentState)).orElse(null);
                          // json.put(HostCheckCR.varJson(HostCheckCR.VAR_intentState, patch), intentState);

                          String old_displayPage = HostCheckCR.staticJsonDisplayPage(o.getDisplayPage());
                          String new_displayPage = json.getString(HostCheckCR.varJson(HostCheckCR.VAR_displayPage, patch));
                          String displayPage = Optional.ofNullable(Optional.ofNullable(new_displayPage).orElse(old_displayPage)).orElse(null);
                          // json.put(HostCheckCR.varJson(HostCheckCR.VAR_displayPage, patch), displayPage);

                          Boolean old_checkPublished = HostCheckCR.staticJsonCheckPublished(o.getCheckPublished());
                          Boolean new_checkPublished = json.getBoolean(HostCheckCR.varJson(HostCheckCR.VAR_checkPublished, patch));
                          Boolean checkPublished = Optional.ofNullable(Optional.ofNullable(new_checkPublished).orElse(old_checkPublished)).orElse(null);
                          // json.put(HostCheckCR.varJson(HostCheckCR.VAR_checkPublished, patch), checkPublished);

                          String old_requestedState = HostCheckCR.staticJsonRequestedState(o.getRequestedState());
                          String new_requestedState = json.getString(HostCheckCR.varJson(HostCheckCR.VAR_requestedState, patch));
                          String requestedState = Optional.ofNullable(Optional.ofNullable(new_requestedState).orElse(old_requestedState)).orElse(null);
                          // json.put(HostCheckCR.varJson(HostCheckCR.VAR_requestedState, patch), requestedState);

                          String old_editPage = HostCheckCR.staticJsonEditPage(o.getEditPage());
                          String new_editPage = json.getString(HostCheckCR.varJson(HostCheckCR.VAR_editPage, patch));
                          String editPage = Optional.ofNullable(Optional.ofNullable(new_editPage).orElse(old_editPage)).orElse(null);
                          // json.put(HostCheckCR.varJson(HostCheckCR.VAR_editPage, patch), editPage);

                          JsonArray old_eventSubscriptions = HostCheckCR.staticJsonEventSubscriptions(o.getEventSubscriptions());
                          JsonArray new_eventSubscriptions = json.getJsonArray(HostCheckCR.varJson(HostCheckCR.VAR_eventSubscriptions, patch));
                          JsonArray eventSubscriptions = Optional.ofNullable(Optional.ofNullable(new_eventSubscriptions).orElse(old_eventSubscriptions)).orElse(null);
                          // json.put(HostCheckCR.varJson(HostCheckCR.VAR_eventSubscriptions, patch), eventSubscriptions);

                          String old_realizedState = HostCheckCR.staticJsonRealizedState(o.getRealizedState());
                          String new_realizedState = json.getString(HostCheckCR.varJson(HostCheckCR.VAR_realizedState, patch));
                          String realizedState = Optional.ofNullable(Optional.ofNullable(new_realizedState).orElse(old_realizedState)).orElse(null);
                          // json.put(HostCheckCR.varJson(HostCheckCR.VAR_realizedState, patch), realizedState);

                          String old_userPage = HostCheckCR.staticJsonUserPage(o.getUserPage());
                          String new_userPage = json.getString(HostCheckCR.varJson(HostCheckCR.VAR_userPage, patch));
                          String userPage = Optional.ofNullable(Optional.ofNullable(new_userPage).orElse(old_userPage)).orElse(null);
                          // json.put(HostCheckCR.varJson(HostCheckCR.VAR_userPage, patch), userPage);

                          JsonArray old_eventHandlers = HostCheckCR.staticJsonEventHandlers(o.getEventHandlers());
                          JsonArray new_eventHandlers = json.getJsonArray(HostCheckCR.varJson(HostCheckCR.VAR_eventHandlers, patch));
                          JsonArray eventHandlers = Optional.ofNullable(Optional.ofNullable(new_eventHandlers).orElse(old_eventHandlers)).orElse(null);
                          // json.put(HostCheckCR.varJson(HostCheckCR.VAR_eventHandlers, patch), eventHandlers);

                          String old_aapOrganizationIdChange = HostCheckCR.staticJsonAapOrganizationIdChange(o.getAapOrganizationIdChange());
                          String new_aapOrganizationIdChange = json.getString(HostCheckCR.varJson(HostCheckCR.VAR_aapOrganizationIdChange, patch));
                          String aapOrganizationIdChange = Optional.ofNullable(Optional.ofNullable(new_aapOrganizationIdChange).orElse(old_aapOrganizationIdChange)).orElse(null);
                          // json.put(HostCheckCR.varJson(HostCheckCR.VAR_aapOrganizationIdChange, patch), aapOrganizationIdChange);

                          String old_download = HostCheckCR.staticJsonDownload(o.getDownload());
                          String new_download = json.getString(HostCheckCR.varJson(HostCheckCR.VAR_download, patch));
                          String download = Optional.ofNullable(Optional.ofNullable(new_download).orElse(old_download)).orElse(null);
                          // json.put(HostCheckCR.varJson(HostCheckCR.VAR_download, patch), download);

                          String old_jobTemplateIdChange = HostCheckCR.staticJsonJobTemplateIdChange(o.getJobTemplateIdChange());
                          String new_jobTemplateIdChange = json.getString(HostCheckCR.varJson(HostCheckCR.VAR_jobTemplateIdChange, patch));
                          String jobTemplateIdChange = Optional.ofNullable(Optional.ofNullable(new_jobTemplateIdChange).orElse(old_jobTemplateIdChange)).orElse(null);
                          // json.put(HostCheckCR.varJson(HostCheckCR.VAR_jobTemplateIdChange, patch), jobTemplateIdChange);

                          String old_aapTemplateIdChange = HostCheckCR.staticJsonAapTemplateIdChange(o.getAapTemplateIdChange());
                          String new_aapTemplateIdChange = json.getString(HostCheckCR.varJson(HostCheckCR.VAR_aapTemplateIdChange, patch));
                          String aapTemplateIdChange = Optional.ofNullable(Optional.ofNullable(new_aapTemplateIdChange).orElse(old_aapTemplateIdChange)).orElse(null);
                          // json.put(HostCheckCR.varJson(HostCheckCR.VAR_aapTemplateIdChange, patch), aapTemplateIdChange);

                          String old_checkNameChange = HostCheckCR.staticJsonCheckNameChange(o.getCheckNameChange());
                          String new_checkNameChange = json.getString(HostCheckCR.varJson(HostCheckCR.VAR_checkNameChange, patch));
                          String checkNameChange = Optional.ofNullable(Optional.ofNullable(new_checkNameChange).orElse(old_checkNameChange)).orElse(null);
                          // json.put(HostCheckCR.varJson(HostCheckCR.VAR_checkNameChange, patch), checkNameChange);

                          String old_checkIdChange = HostCheckCR.staticJsonCheckIdChange(o.getCheckIdChange());
                          String new_checkIdChange = json.getString(HostCheckCR.varJson(HostCheckCR.VAR_checkIdChange, patch));
                          String checkIdChange = Optional.ofNullable(Optional.ofNullable(new_checkIdChange).orElse(old_checkIdChange)).orElse(null);
                          // json.put(HostCheckCR.varJson(HostCheckCR.VAR_checkIdChange, patch), checkIdChange);

                          String old_checkResourceChange = HostCheckCR.staticJsonCheckResourceChange(o.getCheckResourceChange());
                          String new_checkResourceChange = json.getString(HostCheckCR.varJson(HostCheckCR.VAR_checkResourceChange, patch));
                          String checkResourceChange = Optional.ofNullable(Optional.ofNullable(new_checkResourceChange).orElse(old_checkResourceChange)).orElse(null);
                          // json.put(HostCheckCR.varJson(HostCheckCR.VAR_checkResourceChange, patch), checkResourceChange);

                          String old_checkDescriptionChange = HostCheckCR.staticJsonCheckDescriptionChange(o.getCheckDescriptionChange());
                          String new_checkDescriptionChange = json.getString(HostCheckCR.varJson(HostCheckCR.VAR_checkDescriptionChange, patch));
                          String checkDescriptionChange = Optional.ofNullable(Optional.ofNullable(new_checkDescriptionChange).orElse(old_checkDescriptionChange)).orElse(null);
                          // json.put(HostCheckCR.varJson(HostCheckCR.VAR_checkDescriptionChange, patch), checkDescriptionChange);

                          String old_checkNamespaceChange = HostCheckCR.staticJsonCheckNamespaceChange(o.getCheckNamespaceChange());
                          String new_checkNamespaceChange = json.getString(HostCheckCR.varJson(HostCheckCR.VAR_checkNamespaceChange, patch));
                          String checkNamespaceChange = Optional.ofNullable(Optional.ofNullable(new_checkNamespaceChange).orElse(old_checkNamespaceChange)).orElse(null);
                          // json.put(HostCheckCR.varJson(HostCheckCR.VAR_checkNamespaceChange, patch), checkNamespaceChange);

                          String old_checkCommandChange = HostCheckCR.staticJsonCheckCommandChange(o.getCheckCommandChange());
                          String new_checkCommandChange = json.getString(HostCheckCR.varJson(HostCheckCR.VAR_checkCommandChange, patch));
                          String checkCommandChange = Optional.ofNullable(Optional.ofNullable(new_checkCommandChange).orElse(old_checkCommandChange)).orElse(null);
                          // json.put(HostCheckCR.varJson(HostCheckCR.VAR_checkCommandChange, patch), checkCommandChange);

                          String old_checkIntervalChange = HostCheckCR.staticJsonCheckIntervalChange(o.getCheckIntervalChange());
                          String new_checkIntervalChange = json.getString(HostCheckCR.varJson(HostCheckCR.VAR_checkIntervalChange, patch));
                          String checkIntervalChange = Optional.ofNullable(Optional.ofNullable(new_checkIntervalChange).orElse(old_checkIntervalChange)).orElse(null);
                          // json.put(HostCheckCR.varJson(HostCheckCR.VAR_checkIntervalChange, patch), checkIntervalChange);

                      promise.complete(json);
                        }
                      } catch(Exception ex) {
                        LOG.error(String.format("upsertHostCheckCR failed. "), ex);
                        promise.tryFail(ex);
                      }
                    }).onFailure(ex -> {
                      promise.fail(ex);
                    });
                  }
                } catch(Exception ex) {
                  LOG.error(String.format("upsertHostCheckCR failed. "), ex);
                  promise.tryFail(ex);
                }
              }).onFailure(ex -> {
                promise.fail(ex);
              });
            }
          } catch(Exception ex) {
            LOG.error(String.format("upsertHostCheckCR failed. "), ex);
            promise.tryFail(ex);
          }
        }).onFailure(ex -> {
          promise.fail(ex);
        });
      }
    } catch(Exception ex) {
      LOG.error(String.format("upsertHostCheckCR failed. "), ex);
      promise.tryFail(ex);
    }
    return promise.future();
  }

  public Future<Void> persistHostCheckCR(HostCheckCR o, Boolean patch) {
    Promise<Void> promise = Promise.promise();
    try {
      SiteRequest siteRequest = o.getSiteRequest_();
      SqlConnection sqlConnection = siteRequest.getSqlConnection();
      Long pk = o.getPk();
      sqlConnection.preparedQuery("SELECT tenantResource, tenantId, created, aapOrganizationId, jobTemplateResource, changeRequestId, archived, jobTemplateId, createdByEmail, aapTemplateId, createdByUserId, checkName, createdByFullName, checkId, createdVia, sessionId, checkResource, ownedByEmail, userKey, checkDescription, ownedByUserId, checkNamespace, ownedByDisplayName, checkCommand, lifecycleState, objectTitle, checkInterval, intentState, displayPage, checkPublished, requestedState, editPage, eventSubscriptions, realizedState, userPage, eventHandlers, aapOrganizationIdChange, download, jobTemplateResourceChange, jobTemplateIdChange, aapTemplateIdChange, checkNameChange, checkIdChange, checkResourceChange, checkDescriptionChange, checkNamespaceChange, checkCommandChange, checkIntervalChange FROM HostCheckCR WHERE pk=$1")
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
                  LOG.error(String.format("persistHostCheckCR failed. "), e);
                }
              }
            }
          }
          o.promiseDeepForClass(siteRequest).onSuccess(a -> {
            promise.complete();
          }).onFailure(ex -> {
            LOG.error(String.format("persistHostCheckCR failed. "), ex);
            promise.tryFail(ex);
          });
        } catch(Exception ex) {
          LOG.error(String.format("persistHostCheckCR failed. "), ex);
          promise.tryFail(ex);
        }
      }).onFailure(ex -> {
        RuntimeException ex2 = new RuntimeException(ex);
        LOG.error(String.format("persistHostCheckCR failed. "), ex2);
        promise.tryFail(ex2);
      });
    } catch(Exception ex) {
      LOG.error(String.format("persistHostCheckCR failed. "), ex);
      promise.tryFail(ex);
    }
    return promise.future();
  }

  public Future<Void> relateHostCheckCR(HostCheckCR o) {
    Promise<Void> promise = Promise.promise();
    try {
      SiteRequest siteRequest = o.getSiteRequest_();
      SqlConnection sqlConnection = siteRequest.getSqlConnection();
      sqlConnection.preparedQuery("SELECT jobTemplateResource as pk1, 'jobTemplateResourceChange' FROM JobTemplate WHERE jobTemplateResource=$1")
          .collecting(Collectors.toList())
          .execute(Tuple.of(o.getJobTemplateResourceChange())
          ).onSuccess(result -> {
        try {
          if(result != null) {
            for(Row definition : result.value()) {
              o.relateForClass(definition.getString(1), definition.getValue(0));
            }
          }
          promise.complete();
        } catch(Exception ex) {
          LOG.error(String.format("relateHostCheckCR failed. "), ex);
          promise.tryFail(ex);
        }
      }).onFailure(ex -> {
        RuntimeException ex2 = new RuntimeException(ex);
        LOG.error(String.format("relateHostCheckCR failed. "), ex2);
        promise.tryFail(ex2);
      });
    } catch(Exception ex) {
      LOG.error(String.format("relateHostCheckCR failed. "), ex);
      promise.tryFail(ex);
    }
    return promise.future();
  }

  public String searchVar(String varIndexed) {
    return HostCheckCR.searchVarHostCheckCR(varIndexed);
  }

  @Override
  public String getClassApiAddress() {
    return HostCheckCR.CLASS_API_ADDRESS_HostCheckCR;
  }

  public Future<HostCheckCR> indexHostCheckCR(HostCheckCR o) {
    Promise<HostCheckCR> promise = Promise.promise();
    try {
      SiteRequest siteRequest = o.getSiteRequest_();
      ApiRequest apiRequest = siteRequest.getApiRequest_();
      JsonObject json = new JsonObject();
      JsonObject add = new JsonObject();
      json.put("add", add);
      JsonObject doc = new JsonObject();
      add.put("doc", doc);
      o.indexHostCheckCR(doc);
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
        LOG.error(String.format("indexHostCheckCR failed. "), new RuntimeException(ex));
        promise.tryFail(ex);
      });
    } catch(Exception ex) {
      LOG.error(String.format("indexHostCheckCR failed. "), ex);
      promise.tryFail(ex);
    }
    return promise.future();
  }

  public Future<HostCheckCR> unindexHostCheckCR(HostCheckCR o) {
    Promise<HostCheckCR> promise = Promise.promise();
    try {
      SiteRequest siteRequest = o.getSiteRequest_();
      ApiRequest apiRequest = siteRequest.getApiRequest_();
      o.promiseDeepForClass(siteRequest).onSuccess(a -> {
        JsonObject json = new JsonObject();
        JsonObject delete = new JsonObject();
        json.put("delete", delete);
        String query = String.format("filter(%s:%s)", HostCheckCR.VAR_solrId, o.obtainForClass(HostCheckCR.VAR_solrId));
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
          LOG.error(String.format("unindexHostCheckCR failed. "), new RuntimeException(ex));
          promise.tryFail(ex);
        });
      }).onFailure(ex -> {
        LOG.error(String.format("unindexHostCheckCR failed. "), ex);
        promise.tryFail(ex);
      });
    } catch(Exception ex) {
      LOG.error(String.format("unindexHostCheckCR failed. "), ex);
      promise.tryFail(ex);
    }
    return promise.future();
  }

  public Future<Void> refreshHostCheckCR(HostCheckCR o) {
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

          if("JobTemplate".equals(classSimpleName2) && solrId2 != null) {
            SearchList<JobTemplate> searchList2 = new SearchList<JobTemplate>();
            searchList2.setStore(true);
            searchList2.q("*:*");
            searchList2.setC(JobTemplate.class);
            searchList2.fq("solrId:" + solrId2);
            searchList2.rows(1L);
            futures.add(Future.future(promise2 -> {
              searchList2.promiseDeepSearchList(siteRequest).onSuccess(b -> {
                JobTemplate o2 = searchList2.getList().stream().findFirst().orElse(null);
                if(o2 != null) {
                  JsonObject params = new JsonObject();
                  params.put("body", new JsonObject());
                  params.put("scopes", siteRequest.getScopes());
                  params.put("cookie", new JsonObject());
                  params.put("path", new JsonObject());
                  params.put("query", new JsonObject().put("q", "*:*").put("fq", new JsonArray().add("solrId:" + solrId2)).put("var", new JsonArray().add("refresh:false")));
                  JsonObject context = new JsonObject().put("params", params).put("user", siteRequest.getUserPrincipal());
                  JsonObject json = new JsonObject().put("context", context);
                  eventBus.request("dcm-enUS-JobTemplate", json, new DeliveryOptions().addHeader("action", "patchJobTemplateFuture")).onSuccess(c -> {
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
          eventBus.request(HostCheckCR.getClassApiAddress(), json, new DeliveryOptions().addHeader("action", "patchHostCheckCRFuture")).onSuccess(c -> {
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
      LOG.error(String.format("refreshHostCheckCR failed. "), ex);
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
      HostCheckCR o = new HostCheckCR();
      o.setSiteRequest_((SiteRequest)siteRequest);

      o.persistForClass(HostCheckCR.VAR_tenantResource, HostCheckCR.staticSetTenantResource(siteRequest2, (String)result.get(HostCheckCR.VAR_tenantResource)));
      o.persistForClass(HostCheckCR.VAR_tenantId, HostCheckCR.staticSetTenantId(siteRequest2, (String)result.get(HostCheckCR.VAR_tenantId)));
      o.persistForClass(HostCheckCR.VAR_created, HostCheckCR.staticSetCreated(siteRequest2, (String)result.get(HostCheckCR.VAR_created), Optional.ofNullable(siteRequest).map(r -> r.getConfig()).map(config -> config.getString(ConfigKeys.SITE_ZONE)).map(z -> ZoneId.of(z)).orElse(ZoneId.of("UTC"))));
      o.persistForClass(HostCheckCR.VAR_aapOrganizationId, HostCheckCR.staticSetAapOrganizationId(siteRequest2, (String)result.get(HostCheckCR.VAR_aapOrganizationId)));
      o.persistForClass(HostCheckCR.VAR_jobTemplateResource, HostCheckCR.staticSetJobTemplateResource(siteRequest2, (String)result.get(HostCheckCR.VAR_jobTemplateResource)));
      o.persistForClass(HostCheckCR.VAR_changeRequestId, HostCheckCR.staticSetChangeRequestId(siteRequest2, (String)result.get(HostCheckCR.VAR_changeRequestId)));
      o.persistForClass(HostCheckCR.VAR_archived, HostCheckCR.staticSetArchived(siteRequest2, (String)result.get(HostCheckCR.VAR_archived)));
      o.persistForClass(HostCheckCR.VAR_jobTemplateId, HostCheckCR.staticSetJobTemplateId(siteRequest2, (String)result.get(HostCheckCR.VAR_jobTemplateId)));
      o.persistForClass(HostCheckCR.VAR_createdByEmail, HostCheckCR.staticSetCreatedByEmail(siteRequest2, (String)result.get(HostCheckCR.VAR_createdByEmail)));
      o.persistForClass(HostCheckCR.VAR_aapTemplateId, HostCheckCR.staticSetAapTemplateId(siteRequest2, (String)result.get(HostCheckCR.VAR_aapTemplateId)));
      o.persistForClass(HostCheckCR.VAR_createdByUserId, HostCheckCR.staticSetCreatedByUserId(siteRequest2, (String)result.get(HostCheckCR.VAR_createdByUserId)));
      o.persistForClass(HostCheckCR.VAR_checkName, HostCheckCR.staticSetCheckName(siteRequest2, (String)result.get(HostCheckCR.VAR_checkName)));
      o.persistForClass(HostCheckCR.VAR_createdByFullName, HostCheckCR.staticSetCreatedByFullName(siteRequest2, (String)result.get(HostCheckCR.VAR_createdByFullName)));
      o.persistForClass(HostCheckCR.VAR_checkId, HostCheckCR.staticSetCheckId(siteRequest2, (String)result.get(HostCheckCR.VAR_checkId)));
      o.persistForClass(HostCheckCR.VAR_createdVia, HostCheckCR.staticSetCreatedVia(siteRequest2, (String)result.get(HostCheckCR.VAR_createdVia)));
      o.persistForClass(HostCheckCR.VAR_sessionId, HostCheckCR.staticSetSessionId(siteRequest2, (String)result.get(HostCheckCR.VAR_sessionId)));
      o.persistForClass(HostCheckCR.VAR_checkResource, HostCheckCR.staticSetCheckResource(siteRequest2, (String)result.get(HostCheckCR.VAR_checkResource)));
      o.persistForClass(HostCheckCR.VAR_ownedByEmail, HostCheckCR.staticSetOwnedByEmail(siteRequest2, (String)result.get(HostCheckCR.VAR_ownedByEmail)));
      o.persistForClass(HostCheckCR.VAR_userKey, HostCheckCR.staticSetUserKey(siteRequest2, (String)result.get(HostCheckCR.VAR_userKey)));
      o.persistForClass(HostCheckCR.VAR_checkDescription, HostCheckCR.staticSetCheckDescription(siteRequest2, (String)result.get(HostCheckCR.VAR_checkDescription)));
      o.persistForClass(HostCheckCR.VAR_ownedByUserId, HostCheckCR.staticSetOwnedByUserId(siteRequest2, (String)result.get(HostCheckCR.VAR_ownedByUserId)));
      o.persistForClass(HostCheckCR.VAR_checkNamespace, HostCheckCR.staticSetCheckNamespace(siteRequest2, (String)result.get(HostCheckCR.VAR_checkNamespace)));
      o.persistForClass(HostCheckCR.VAR_ownedByDisplayName, HostCheckCR.staticSetOwnedByDisplayName(siteRequest2, (String)result.get(HostCheckCR.VAR_ownedByDisplayName)));
      o.persistForClass(HostCheckCR.VAR_checkCommand, HostCheckCR.staticSetCheckCommand(siteRequest2, (String)result.get(HostCheckCR.VAR_checkCommand)));
      o.persistForClass(HostCheckCR.VAR_lifecycleState, HostCheckCR.staticSetLifecycleState(siteRequest2, (String)result.get(HostCheckCR.VAR_lifecycleState)));
      o.persistForClass(HostCheckCR.VAR_objectTitle, HostCheckCR.staticSetObjectTitle(siteRequest2, (String)result.get(HostCheckCR.VAR_objectTitle)));
      o.persistForClass(HostCheckCR.VAR_checkInterval, HostCheckCR.staticSetCheckInterval(siteRequest2, (String)result.get(HostCheckCR.VAR_checkInterval)));
      o.persistForClass(HostCheckCR.VAR_intentState, HostCheckCR.staticSetIntentState(siteRequest2, (String)result.get(HostCheckCR.VAR_intentState)));
      o.persistForClass(HostCheckCR.VAR_displayPage, HostCheckCR.staticSetDisplayPage(siteRequest2, (String)result.get(HostCheckCR.VAR_displayPage)));
      o.persistForClass(HostCheckCR.VAR_checkPublished, HostCheckCR.staticSetCheckPublished(siteRequest2, (String)result.get(HostCheckCR.VAR_checkPublished)));
      o.persistForClass(HostCheckCR.VAR_requestedState, HostCheckCR.staticSetRequestedState(siteRequest2, (String)result.get(HostCheckCR.VAR_requestedState)));
      o.persistForClass(HostCheckCR.VAR_editPage, HostCheckCR.staticSetEditPage(siteRequest2, (String)result.get(HostCheckCR.VAR_editPage)));
      o.persistForClass(HostCheckCR.VAR_eventSubscriptions, HostCheckCR.staticSetEventSubscriptions(siteRequest2, (String)result.get(HostCheckCR.VAR_eventSubscriptions)));
      o.persistForClass(HostCheckCR.VAR_realizedState, HostCheckCR.staticSetRealizedState(siteRequest2, (String)result.get(HostCheckCR.VAR_realizedState)));
      o.persistForClass(HostCheckCR.VAR_userPage, HostCheckCR.staticSetUserPage(siteRequest2, (String)result.get(HostCheckCR.VAR_userPage)));
      o.persistForClass(HostCheckCR.VAR_eventHandlers, HostCheckCR.staticSetEventHandlers(siteRequest2, (String)result.get(HostCheckCR.VAR_eventHandlers)));
      o.persistForClass(HostCheckCR.VAR_aapOrganizationIdChange, HostCheckCR.staticSetAapOrganizationIdChange(siteRequest2, (String)result.get(HostCheckCR.VAR_aapOrganizationIdChange)));
      o.persistForClass(HostCheckCR.VAR_download, HostCheckCR.staticSetDownload(siteRequest2, (String)result.get(HostCheckCR.VAR_download)));
      o.persistForClass(HostCheckCR.VAR_jobTemplateResourceChange, HostCheckCR.staticSetJobTemplateResourceChange(siteRequest2, (String)result.get(HostCheckCR.VAR_jobTemplateResourceChange)));
      o.persistForClass(HostCheckCR.VAR_jobTemplateIdChange, HostCheckCR.staticSetJobTemplateIdChange(siteRequest2, (String)result.get(HostCheckCR.VAR_jobTemplateIdChange)));
      o.persistForClass(HostCheckCR.VAR_aapTemplateIdChange, HostCheckCR.staticSetAapTemplateIdChange(siteRequest2, (String)result.get(HostCheckCR.VAR_aapTemplateIdChange)));
      o.persistForClass(HostCheckCR.VAR_checkNameChange, HostCheckCR.staticSetCheckNameChange(siteRequest2, (String)result.get(HostCheckCR.VAR_checkNameChange)));
      o.persistForClass(HostCheckCR.VAR_checkIdChange, HostCheckCR.staticSetCheckIdChange(siteRequest2, (String)result.get(HostCheckCR.VAR_checkIdChange)));
      o.persistForClass(HostCheckCR.VAR_checkResourceChange, HostCheckCR.staticSetCheckResourceChange(siteRequest2, (String)result.get(HostCheckCR.VAR_checkResourceChange)));
      o.persistForClass(HostCheckCR.VAR_checkDescriptionChange, HostCheckCR.staticSetCheckDescriptionChange(siteRequest2, (String)result.get(HostCheckCR.VAR_checkDescriptionChange)));
      o.persistForClass(HostCheckCR.VAR_checkNamespaceChange, HostCheckCR.staticSetCheckNamespaceChange(siteRequest2, (String)result.get(HostCheckCR.VAR_checkNamespaceChange)));
      o.persistForClass(HostCheckCR.VAR_checkCommandChange, HostCheckCR.staticSetCheckCommandChange(siteRequest2, (String)result.get(HostCheckCR.VAR_checkCommandChange)));
      o.persistForClass(HostCheckCR.VAR_checkIntervalChange, HostCheckCR.staticSetCheckIntervalChange(siteRequest2, (String)result.get(HostCheckCR.VAR_checkIntervalChange)));

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
