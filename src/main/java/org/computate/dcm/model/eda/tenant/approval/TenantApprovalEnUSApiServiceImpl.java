package org.computate.dcm.model.eda.tenant.approval;

import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.json.JsonObject;

/**
 * Translate: false
 **/
public class TenantApprovalEnUSApiServiceImpl extends TenantApprovalEnUSGenApiServiceImpl {

  @Override
  public Future<JsonObject> upsertTenantApproval(TenantApproval o, Boolean inheritPrimaryKey, Boolean patch) {
    return super.upsertTenantApproval(o, inheritPrimaryKey, patch);
  }

  @Override
  public Future<TenantApproval> sqlPOSTTenantApproval(TenantApproval o, Boolean inheritPrimaryKey) {
    Promise<TenantApproval> promise = Promise.promise();
    upsertTenantApproval(o, inheritPrimaryKey, false).onSuccess(hostCheckJson -> {
      super.sqlPOSTTenantApproval(o, inheritPrimaryKey).onSuccess(o2 -> {
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
  public Future<TenantApproval> sqlPATCHTenantApproval(TenantApproval o, Boolean inheritPrimaryKey) {
    Promise<TenantApproval> promise = Promise.promise();
    upsertTenantApproval(o, inheritPrimaryKey, true).onSuccess(hostCheckJson -> {
      super.sqlPATCHTenantApproval(o, inheritPrimaryKey).onSuccess(o2 -> {
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
