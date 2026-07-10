package org.computate.dcm.model.eda.tenant.realized;

import java.util.Optional;

import org.computate.dcm.model.eda.tenant.requested.TenantRequested;
import org.computate.dcm.request.SiteRequest;
import org.computate.search.tool.SearchTool;
import org.computate.vertx.search.list.SearchList;

import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.api.service.ServiceRequest;

/**
 * Translate: false
 **/
public class TenantRealizedEnUSApiServiceImpl extends TenantRealizedEnUSGenApiServiceImpl {

  @Override
  public Future<JsonObject> upsertTenantRealized(TenantRealized o, Boolean inheritPrimaryKey, Boolean patch) {
    Promise<JsonObject> promise = Promise.promise();
    try {
      SiteRequest siteRequest = o.getSiteRequest_();
      ServiceRequest serviceRequest = siteRequest.getServiceRequest();
      if(Optional.ofNullable(serviceRequest.getParams()).map(p -> p.getJsonObject("query")).map( q -> q.getJsonArray("var")).orElse(new JsonArray()).stream().filter(s -> "refresh:false".equals(s)).count() > 0L) {
        promise.complete();
      } else {
        JsonObject json = o.getSiteRequest_().getJsonObject();

        String old_requestedId = TenantRealized.staticJsonRequestedId(o.getRequestedId());
        String new_requestedId = json.getString(TenantRequested.varJson(TenantRequested.VAR_requestedId, patch));
        String requestedId = Optional.ofNullable(Optional.ofNullable(new_requestedId).orElse(old_requestedId)).orElse(null);
        TenantRequested.fqTenantRequested(siteRequest, TenantRequested.VAR_requestedId, requestedId).onSuccess(oTenantRequested -> {
          try {
            if(oTenantRequested == null) {
              RuntimeException ex = new RuntimeException(String.format("Could not find a matching TenantRequested %s", requestedId));
              LOG.error(ex.getMessage(), ex);
              promise.fail(ex);
            } else {
              json.put(TenantRealized.varJson(TenantRealized.VAR_requestedId, patch), requestedId);
              String tenantResource = oTenantRequested.getTenantResource();

              SearchList<TenantRealized> searchList = new SearchList<TenantRealized>();
              searchList.setStore(true);
              searchList.q("*:*");
              searchList.setC(TenantRealized.class);
              searchList.fq(String.format("%s:", TenantRealized.varIndexedTenantRealized(TenantRealized.VAR_tenantResource)) + SearchTool.escapeQueryChars(tenantResource));
              searchList.statsField("realizedNumber_docvalues_int");
              searchList.rows(0);
              searchList.promiseDeepForClass(siteRequest).onSuccess(a -> {
                try {
                  String old_realizedNumber = TenantRealized.staticJsonRealizedNumber(o.getRealizedNumber());
                  String new_realizedNumber = Integer.toString((Optional.ofNullable(searchList.getResponse().getStats().get("realizedNumber_docvalues_int")).map(stats -> (Double)stats.getMax()).map(max -> max.intValue() + 1).orElse(1)));
                  String realizedNumber = Optional.ofNullable(old_realizedNumber).orElse(new_realizedNumber);
                  json.put(TenantRealized.varJson(TenantRealized.VAR_realizedNumber, patch), realizedNumber.toString());

                  Boolean old_locked = TenantRealized.staticJsonLocked(o.getLocked());
                  Boolean new_locked = json.getBoolean(TenantRealized.varJson(TenantRealized.VAR_locked, patch));
                  Boolean locked = Optional.ofNullable(Optional.ofNullable(new_locked).orElse(old_locked)).orElse(false);
                  if(locked)
                    throw new RuntimeException(String.format("Cannot update this %s %s, because it has already been requested and locked. ", TenantRealized.SingularName_enUS, o.getTenantResource()));

                  super.upsertTenantRealized(o, inheritPrimaryKey, patch).onSuccess(json2 -> {
                    promise.complete(json2);
                  }).onFailure(ex -> promise.fail(ex));
                } catch(Exception ex) {
                  LOG.error(String.format("upsertTenantRealized failed. "), ex);
                  promise.tryFail(ex);
                }
              }).onFailure(ex -> {
                promise.fail(ex);
              });
            }
          } catch(Exception ex) {
            LOG.error(String.format("upsertTenantRealized failed. "), ex);
            promise.tryFail(ex);
          }
        }).onFailure(ex -> {
          promise.fail(ex);
        });
      }
    } catch(Exception ex) {
      LOG.error(String.format("searchTenantRealized failed. "), ex);
      promise.tryFail(ex);
    }
    return promise.future();
  }

  @Override
  public Future<TenantRealized> sqlPOSTTenantRealized(TenantRealized o, Boolean inheritPrimaryKey) {
    Promise<TenantRealized> promise = Promise.promise();
    upsertTenantRealized(o, inheritPrimaryKey, false).onSuccess(hostCheckJson -> {
      super.sqlPOSTTenantRealized(o, inheritPrimaryKey).onSuccess(o2 -> {
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
  public Future<TenantRealized> sqlPATCHTenantRealized(TenantRealized o, Boolean inheritPrimaryKey) {
    Promise<TenantRealized> promise = Promise.promise();
    upsertTenantRealized(o, inheritPrimaryKey, true).onSuccess(hostCheckJson -> {
      super.sqlPATCHTenantRealized(o, inheritPrimaryKey).onSuccess(o2 -> {
        promise.complete(o2);
      }).onFailure(ex -> {
        promise.fail(ex);
      });
    }).onFailure(ex -> {
      promise.fail(ex);
    });
    return promise.future();
  }
}
