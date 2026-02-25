package org.computate.dcm.verticle;

import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.computate.search.response.solr.SolrResponse;
import org.computate.search.response.solr.SolrResponse.FacetField;
import org.computate.search.tool.SearchTool;
import org.computate.vertx.config.ComputateConfigKeys;
import org.computate.vertx.openapi.ComputateOAuth2AuthHandlerImpl;
import org.computate.vertx.search.list.SearchList;
import org.computate.dcm.config.ConfigKeys;
import org.computate.dcm.model.eda.host.Host;
import org.computate.dcm.model.eda.hostcheck.HostCheck;
import org.computate.dcm.request.SiteRequest;
import org.computate.dcm.user.SiteUser;
import org.computate.dcm.user.SiteUserEnUSApiServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.io.Resources;
import com.hubspot.jinjava.Jinjava;

import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.eventbus.DeliveryOptions;
import io.vertx.core.http.HttpResponseExpectation;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.User;
import io.vertx.ext.auth.authentication.UsernamePasswordCredentials;
import io.vertx.ext.auth.oauth2.OAuth2Auth;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.api.service.ServiceRequest;
import io.vertx.ext.web.client.HttpRequest;
import io.vertx.ext.web.client.WebClient;
import io.vertx.kafka.client.consumer.KafkaConsumer;

public class SiteRoutes {
  protected static final Logger LOG = LoggerFactory.getLogger(SiteRoutes.class);
  
  public static void routes(Vertx vertx, Router router, OAuth2Auth oauth2AuthenticationProvider, ComputateOAuth2AuthHandlerImpl oauth2AuthHandler, JsonObject config, WebClient webClient, Jinjava jinjava, SiteUserEnUSApiServiceImpl apiSiteUser) {

    router.get("/refresh").handler(eventHandler -> {
      ServiceRequest serviceRequest = apiSiteUser.generateServiceRequest(eventHandler);
      apiSiteUser.userRefresh(serviceRequest, SiteRequest.class, SiteUser.class, SiteUser.CLASS_API_ADDRESS_ComputateSiteUser, "postSiteUserFuture", "patchSiteUserFuture", false, true).onSuccess(siteRequest -> {
        oauth2AuthenticationProvider.refresh(User.create(serviceRequest.getUser())).onSuccess(refreshedUser -> {
          eventHandler.setUser(refreshedUser);
          eventHandler.response().setStatusCode(200).setStatusMessage("OK").send(new JsonObject().toBuffer());
        }).onFailure(ex -> {
          if("Inactive Token".equals(ex.getMessage()) || StringUtils.startsWith(ex.getMessage(), "invalid_grant:")) {
            try {
              eventHandler.redirect("/logout?redirect_uri=" + URLEncoder.encode("/", "UTF-8"));
            } catch(Exception ex2) {
              LOG.error(String.format("searchSiteUser failed. ", ex2));
              eventHandler.fail(ex2);
            }
          } else if(StringUtils.startsWith(ex.getMessage(), "401 UNAUTHORIZED ")) {
            eventHandler.response().setStatusCode(401).setStatusMessage("UNAUTHORIZED")
                .send(Buffer.buffer().appendString(
                  new JsonObject()
                    .put("errorCode", "401")
                    .put("errorMessage", "SSO Resource Permission check returned DENY")
                    .encodePrettily()
                  )
                );
          } else {
            LOG.error(String.format("searchSiteUser failed. "), ex);
            eventHandler.fail(ex);
          }
        });
      }).onFailure(ex -> {
        if("Inactive Token".equals(ex.getMessage()) || StringUtils.startsWith(ex.getMessage(), "invalid_grant:")) {
          try {
            eventHandler.redirect("/logout?redirect_uri=" + URLEncoder.encode("/", "UTF-8"));
          } catch(Exception ex2) {
            LOG.error(String.format("searchSiteUser failed. ", ex2));
            eventHandler.fail(ex2);
          }
        } else if(StringUtils.startsWith(ex.getMessage(), "401 UNAUTHORIZED ")) {
          eventHandler.response().setStatusCode(401).setStatusMessage("UNAUTHORIZED")
              .send(Buffer.buffer().appendString(
                new JsonObject()
                  .put("errorCode", "401")
                  .put("errorMessage", "SSO Resource Permission check returned DENY")
                  .encodePrettily()
                )
              );
        } else {
          LOG.error(String.format("searchSiteUser failed. "), ex);
          eventHandler.fail(ex);
        }
      });
    });

    router.get("/").handler(eventHandler -> {
      ServiceRequest serviceRequest = apiSiteUser.generateServiceRequest(eventHandler);
      apiSiteUser.user(serviceRequest, SiteRequest.class, SiteUser.class, SiteUser.CLASS_API_ADDRESS_SiteUser, "postSiteUserFuture", "patchSiteUserFuture", false).onSuccess(siteRequest -> {
        try {
          String facetRange = null;

          String solrUsername = config.getString(ComputateConfigKeys.SOLR_USERNAME);
          String solrPassword = config.getString(ComputateConfigKeys.SOLR_PASSWORD);
          String solrHostName = config.getString(ComputateConfigKeys.SOLR_HOST_NAME);
          Integer solrPort = Integer.parseInt(config.getString(ComputateConfigKeys.SOLR_PORT));
          String solrCollection = config.getString(ComputateConfigKeys.SOLR_COLLECTION);
          Boolean solrSsl = Boolean.parseBoolean(config.getString(ComputateConfigKeys.SOLR_SSL));
          String facetUri = String.format(
              "/solr/%s/select%s%s%s"
              , solrCollection
              , "?rows=0&q="
              , URLEncoder.encode("*:*", "UTF-8")
              , "&facet=true&facet.mincount=1&facet.field=classSimpleName_docvalues_string"
              );
          webClient.get(solrPort, solrHostName, facetUri).ssl(solrSsl).authentication(new UsernamePasswordCredentials(solrUsername, solrPassword)).send().onSuccess(a -> {
            try {

              SolrResponse response = a.bodyAsJson(SolrResponse.class);
              FacetField facetClass = response.getFacetField("classSimpleName_docvalues_string");

              String siteTemplatePath = config.getString(ConfigKeys.TEMPLATE_PATH);
              String pageTemplateUri = "/en-us/HomePage.htm";
              Path resourceTemplatePath = Path.of(siteTemplatePath, pageTemplateUri);
              String template = siteTemplatePath == null ? Resources.toString(Resources.getResource(resourceTemplatePath.toString()), StandardCharsets.UTF_8) : Files.readString(resourceTemplatePath, Charset.forName("UTF-8"));
              JsonObject ctx = ConfigKeys.getPageContext(config);

              SiteUser user = siteRequest.getSiteUser_();
              if(user != null) {
                ctx.put("userName", user.getUserName());
                ctx.put("userEmail", user.getUserEmail());
                ctx.put("userFullName", user.getUserFullName());
                ctx.put("userGroups", siteRequest.getGroups().stream().filter(group -> group.startsWith("/")).collect(Collectors.toList()));
                ctx.put("scopes", siteRequest.getScopes());
              }

              ctx.put("facetClass", facetClass);

              String renderedTemplate = jinjava.render(template, ctx.getMap());
              Buffer buffer = Buffer.buffer(renderedTemplate);
              eventHandler.response().putHeader("Content-Type", "text/html");
              eventHandler.end(buffer);
            } catch(Exception ex) {
              LOG.error("Failed to load page. ", ex);
              eventHandler.fail(ex);
            }
          }).onFailure(ex -> {
            LOG.error(String.format("Search failed. "), new RuntimeException(ex));
          });
        } catch(Exception ex) {
          LOG.error("Failed to load page. ", ex);
          eventHandler.fail(ex);
        }
      }).onFailure(ex -> {
        if("Inactive Token".equals(ex.getMessage()) || StringUtils.startsWith(ex.getMessage(), "invalid_grant:")) {
          try {
            eventHandler.redirect("/logout?redirect_uri=" + URLEncoder.encode("/", "UTF-8"));
          } catch(Exception ex2) {
            LOG.error(String.format("searchSiteUser failed. ", ex2));
            eventHandler.fail(ex2);
          }
        } else if(StringUtils.startsWith(ex.getMessage(), "401 UNAUTHORIZED ")) {
          eventHandler.response().setStatusCode(401).setStatusMessage("UNAUTHORIZED")
              .send(Buffer.buffer().appendString(
                new JsonObject()
                  .put("errorCode", "401")
                  .put("errorMessage", "SSO Resource Permission check returned DENY")
                  .encodePrettily()
                )
              );
        } else {
          LOG.error(String.format("searchSiteUser failed. "), ex);
          eventHandler.fail(ex);
        }
      });
    });
  }

  public static Future<Void> kafkaConsumer(Vertx vertx, KafkaConsumer<String, String> consumer, JsonObject config, WebClient webClient) {
    Promise<Void> promise = Promise.promise();
    try {
      String kafkaTopicOrderStatus = config.getString(ConfigKeys.KAFKA_TOPIC_SENSU_EVENT);
      consumer.handler(message -> {
        try {
          String topic = message.topic();
          String bodyStr = message.value();
          LOG.debug(String.format("Kafka message received on topic %s: %s", topic, bodyStr));
          JsonObject messageBody = new JsonObject(bodyStr);
          String checkName = messageBody.getJsonObject("check").getJsonObject("metadata").getString("name");
          String checkState = messageBody.getJsonObject("check").getString("state");
          String hostName = messageBody.getJsonObject("entity").getJsonObject("metadata").getString("name");
          if(!"passing".equals(checkState)) {
            LOG.info(String.format("Check %s in status %s on host %s", checkName, checkState, hostName));
            SiteRequest siteRequest = new SiteRequest();
            siteRequest.setConfig(config);
            siteRequest.setWebClient(webClient);
            siteRequest.initDeepSiteRequest(siteRequest);
            siteRequest.addScopes("GET");
            Host.fqHost(siteRequest, Host.VAR_hostName, hostName).onSuccess(host -> {
              if(host == null) {
                RuntimeException ex = new RuntimeException(String.format("Could not find a matching host %s", hostName));
                LOG.error(ex.getMessage(), ex);
              } else {
                SearchList<HostCheck> searchList = new SearchList<HostCheck>();
                searchList.setStore(true);
                searchList.q("*:*");
                searchList.setC(HostCheck.class);
                searchList.fq(String.format("%s:", HostCheck.varIndexedHostCheck(HostCheck.VAR_checkName)) + SearchTool.escapeQueryChars(checkName));
                searchList.fq(String.format("%s:", HostCheck.varIndexedHostCheck(HostCheck.VAR_tenantResource)) + SearchTool.escapeQueryChars(host.getTenantResource()));
                searchList.promiseDeepForClass(siteRequest).onSuccess(hostCheckSearch -> {
                  try {
                    HostCheck hostCheck = hostCheckSearch.getList().stream().findFirst().orElse(null);
                    if(hostCheck == null) {
                      RuntimeException ex = new RuntimeException(String.format("Could not find a matching host check %s", checkName));
                      LOG.error(ex.getMessage(), ex);
                    } else {
                      String ipAddress = host.getIpAddress();
                      Long aapTemplateId = hostCheck.getAapTemplateId();
                      String jobTemplateId = hostCheck.getJobTemplateId();

                      Integer aapPort = Integer.parseInt(config.getString(ConfigKeys.AAP_PORT));
                      String aapHostName = config.getString(ConfigKeys.AAP_HOST_NAME);
                      Boolean aapSsl = Boolean.parseBoolean(config.getString(ConfigKeys.AAP_SSL));
                      String aapUri = String.format("/api/controller/v2/job_templates/%s/launch/", aapTemplateId);
                      String aapUriRunningJob = String.format("/api/controller/v2/jobs/?status__in=running,pending,waiting&job_template=%s&limit__exact=%s", aapTemplateId, ipAddress);
                      String aapUserName = config.getString(ConfigKeys.AAP_USER_NAME);
                      String aapPassword = config.getString(ConfigKeys.AAP_PASSWORD);

                      webClient.get(aapPort, aapHostName, aapUriRunningJob).ssl(aapSsl)
                          .putHeader("Content-Type", "application/json")
                          .basicAuthentication(aapUserName, aapPassword)
                          .send()
                          .expecting(HttpResponseExpectation.SC_OK)
                          .onSuccess(runningJobResponse -> {
                        JsonObject runningJobResponseBody = runningJobResponse.bodyAsJsonObject();
                        if(runningJobResponseBody.getJsonArray("results").size() > 0) {
                          LOG.info(String.format("AAP job template %s is already running a job on host %s. ", jobTemplateId, hostName));
                        } else {
                          JsonObject body = new JsonObject();
                          body.put("limit", ipAddress);

                          webClient.post(aapPort, aapHostName, aapUri).ssl(aapSsl)
                              .putHeader("Content-Type", "application/json")
                              .basicAuthentication(aapUserName, aapPassword)
                              .sendJsonObject(body)
                              .expecting(HttpResponseExpectation.SC_CREATED)
                              .onSuccess(hostResponse -> {
                            JsonObject responseBody = hostResponse.bodyAsJsonObject();
                            LOG.info(String.format("AAP %s job %s submitted with job template %s on host %s", jobTemplateId, responseBody.getString("job"), responseBody.getString("name"), hostName));
                          }).onFailure(ex -> {
                            LOG.error(String.format("Updating AAP host failed. "), ex);
                          });
                        }
                      }).onFailure(ex -> {
                        LOG.error(String.format("Updating AAP host failed. "), ex);
                        promise.fail(ex);
                      });
                    }
                  } catch(Exception ex) {
                    LOG.error(String.format("Updating Sensu host failed. "), ex);
                  }
                }).onFailure(ex -> {
                  LOG.error(String.format("search HostCheck failed. "), ex);
                });
              }
            }).onFailure(ex -> {
              LOG.error(String.format("search Host failed. "), ex);
            });
          }
        } catch(Exception ex) {
          LOG.error(String.format("Failed to handle message %s", message), ex);
        }
      });
      consumer.subscribe(kafkaTopicOrderStatus).onSuccess(a -> {
        LOG.info(String.format("Successfully subscribed to topic", kafkaTopicOrderStatus));
        promise.complete();
      }).onFailure(ex -> {
        LOG.error(String.format("Failed to subscribe to topic", kafkaTopicOrderStatus), ex);
        promise.fail(ex);
      });
    } catch(Exception ex) {
      LOG.error("Unable to configure Kafka consumers. ", ex);
      promise.fail(ex);
    }

    return promise.future();
  }
}
