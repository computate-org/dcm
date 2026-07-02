package org.computate.dcm.model.eda.tenant.intent;

import java.util.Optional;

import org.computate.dcm.request.SiteRequest;

import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.api.service.ServiceRequest;

/**
 * Translate: false
 **/
public class TenantIntentEnUSApiServiceImpl extends TenantIntentEnUSGenApiServiceImpl {

  @Override
  public Future<JsonObject> upsertTenantIntent(TenantIntent o, Boolean inheritPrimaryKey, Boolean patch) {
    Promise<JsonObject> promise = Promise.promise();
    try {
      SiteRequest siteRequest = o.getSiteRequest_();
      ServiceRequest serviceRequest = siteRequest.getServiceRequest();
      if(Optional.ofNullable(serviceRequest.getParams()).map(p -> p.getJsonObject("query")).map( q -> q.getJsonArray("var")).orElse(new JsonArray()).stream().filter(s -> "refresh:false".equals(s)).count() > 0L) {
        promise.complete();
      } else {
        JsonObject json = o.getSiteRequest_().getJsonObject();

        Boolean old_locked = TenantIntent.staticJsonLocked(o.getLocked());
        Boolean new_locked = json.getBoolean(TenantIntent.varJson(TenantIntent.VAR_locked, patch));
        Boolean locked = Optional.ofNullable(Optional.ofNullable(new_locked).orElse(old_locked)).orElse(false);
        if(locked)
          throw new RuntimeException(String.format("Cannot update this %s %s, because it has already been requested and locked. ", TenantIntent.SingularName_enUS, o.getTenantResource()));

        super.upsertTenantIntent(o, inheritPrimaryKey, patch).onSuccess(json2 -> {
          promise.complete(json2);
        }).onFailure(ex -> promise.fail(ex));
      }
    } catch(Exception ex) {
      LOG.error(String.format("searchTenantIntent failed. "), ex);
      promise.tryFail(ex);
    }
    return promise.future();
  }

  @Override
  public Future<TenantIntent> sqlPOSTTenantIntent(TenantIntent o, Boolean inheritPrimaryKey) {
    Promise<TenantIntent> promise = Promise.promise();
    upsertTenantIntent(o, inheritPrimaryKey, false).onSuccess(hostCheckJson -> {
      super.sqlPOSTTenantIntent(o, inheritPrimaryKey).onSuccess(o2 -> {
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
  public Future<TenantIntent> sqlPATCHTenantIntent(TenantIntent o, Boolean inheritPrimaryKey) {
    Promise<TenantIntent> promise = Promise.promise();
    upsertTenantIntent(o, inheritPrimaryKey, true).onSuccess(hostCheckJson -> {
      super.sqlPATCHTenantIntent(o, inheritPrimaryKey).onSuccess(o2 -> {
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
