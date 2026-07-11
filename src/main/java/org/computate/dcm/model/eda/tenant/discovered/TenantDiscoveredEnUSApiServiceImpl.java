package org.computate.dcm.model.eda.tenant.discovered;

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
public class TenantDiscoveredEnUSApiServiceImpl extends TenantDiscoveredEnUSGenApiServiceImpl {

  @Override
  public Future<JsonObject> upsertTenantDiscovered(TenantDiscovered o, Boolean inheritPrimaryKey, Boolean patch) {
    Promise<JsonObject> promise = Promise.promise();
    try {
      SiteRequest siteRequest = o.getSiteRequest_();
      ServiceRequest serviceRequest = siteRequest.getServiceRequest();
      if(Optional.ofNullable(serviceRequest.getParams()).map(p -> p.getJsonObject("query")).map( q -> q.getJsonArray("var")).orElse(new JsonArray()).stream().filter(s -> "refresh:false".equals(s)).count() > 0L) {
        promise.complete();
      } else {
        JsonObject json = o.getSiteRequest_().getJsonObject();

        String old_requestedId = TenantDiscovered.staticJsonRequestedId(o.getRequestedId());
        String new_requestedId = json.getString(TenantRequested.varJson(TenantRequested.VAR_requestedId, patch));
        String requestedId = Optional.ofNullable(Optional.ofNullable(new_requestedId).orElse(old_requestedId)).orElse(null);
        SearchList<TenantRequested> searchList = new SearchList<TenantRequested>();
        searchList.setStore(true);
        searchList.q("*:*");
        searchList.setC(TenantRequested.class);
        searchList.fq(String.format("%s:", TenantRequested.varIndexedTenantRequested(TenantRequested.VAR_requestedId)) + SearchTool.escapeQueryChars(requestedId));
        searchList.sortDesc("requestedNumber_docvalues_int");
        searchList.rows(1);
        searchList.promiseDeepForClass(siteRequest).onSuccess(requestedSearch -> {
          try {
            TenantRequested oTenantRequested = requestedSearch.getList().stream().findFirst().orElse(null);
            if(oTenantRequested == null) {
              RuntimeException ex = new RuntimeException(String.format("Could not find a matching TenantRequested by requestedId %s", requestedId));
              LOG.error(ex.getMessage(), ex);
              promise.fail(ex);
            } else {
              super.upsertTenantDiscovered(o, inheritPrimaryKey, patch).onSuccess(json2 -> {
                promise.complete(json2);
              }).onFailure(ex -> promise.fail(ex));
            }
          } catch(Exception ex) {
            LOG.error(String.format("upsertTenantDiscovered failed. "), ex);
            promise.tryFail(ex);
          }
        }).onFailure(ex -> {
          promise.fail(ex);
        });
      }
    } catch(Exception ex) {
      LOG.error(String.format("searchTenantDiscovered failed. "), ex);
      promise.tryFail(ex);
    }
    return promise.future();
  }

  @Override
  public Future<TenantDiscovered> sqlPOSTTenantDiscovered(TenantDiscovered o, Boolean inheritPrimaryKey) {
    Promise<TenantDiscovered> promise = Promise.promise();
    upsertTenantDiscovered(o, inheritPrimaryKey, false).onSuccess(hostCheckJson -> {
      super.sqlPOSTTenantDiscovered(o, inheritPrimaryKey).onSuccess(o2 -> {
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
  public Future<TenantDiscovered> sqlPATCHTenantDiscovered(TenantDiscovered o, Boolean inheritPrimaryKey) {
    Promise<TenantDiscovered> promise = Promise.promise();
    upsertTenantDiscovered(o, inheritPrimaryKey, true).onSuccess(hostCheckJson -> {
      super.sqlPATCHTenantDiscovered(o, inheritPrimaryKey).onSuccess(o2 -> {
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
