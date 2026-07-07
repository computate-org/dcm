package org.computate.dcm.model.eda.requestapproval;

import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.json.JsonObject;

/**
 * Translate: false
 **/
public class RequestApprovalEnUSApiServiceImpl extends RequestApprovalEnUSGenApiServiceImpl {

  @Override
  public Future<JsonObject> upsertRequestApproval(RequestApproval o, Boolean inheritPrimaryKey, Boolean patch) {
    return super.upsertRequestApproval(o, inheritPrimaryKey, patch);
  }

  @Override
  public Future<RequestApproval> sqlPOSTRequestApproval(RequestApproval o, Boolean inheritPrimaryKey) {
    Promise<RequestApproval> promise = Promise.promise();
    upsertRequestApproval(o, inheritPrimaryKey, false).onSuccess(hostCheckJson -> {
      super.sqlPOSTRequestApproval(o, inheritPrimaryKey).onSuccess(o2 -> {
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
  public Future<RequestApproval> sqlPATCHRequestApproval(RequestApproval o, Boolean inheritPrimaryKey) {
    Promise<RequestApproval> promise = Promise.promise();
    upsertRequestApproval(o, inheritPrimaryKey, true).onSuccess(hostCheckJson -> {
      super.sqlPATCHRequestApproval(o, inheritPrimaryKey).onSuccess(o2 -> {
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
