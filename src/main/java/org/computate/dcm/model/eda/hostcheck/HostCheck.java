package org.computate.dcm.model.eda.hostcheck;

import java.util.List;

import org.computate.search.wrap.Wrap;
import org.computate.dcm.model.BaseModel;

/**
 * Order: 6
 * Description: A check to be performed on a computer. 
 * AName: a host check
 * Icon: <i class="fa-duotone fa-regular fa-box-check"></i>
 * Rows: 100
 * 
 * SearchPageUri: /en-us/search/host-check
 * EditPageUri: /en-us/edit/host-check/{checkName}
 * UserPageUri: /en-us/user/host-check/{checkName}
 * ApiUri: /en-us/api/host-check
 * ApiMethod:
 *   Search:
 *   GET:
 *   PATCH:
 *   POST:
 *   DELETE:
 *   PUTImport:
 * 
 * AuthGroup:
 *   HostCheckReader:
 *     GET:
 *   HostCheckEditor:
 *     GET:
 *     POST:
 *     PATCH:
 *   Admin:
 *     GET:
 *     PUT:
 *     POST:
 *     PATCH:
 *     DELETE:
 *   SuperAdmin:
 *     GET:
 *     PUT:
 *     POST:
 *     PATCH:
 *     DELETE:
 *     Admin:
 *     SuperAdmin:
 */
public class HostCheck extends HostCheckGen<BaseModel> {

  /**
   * DocValues: true
   * Persist: true
   * DisplayName: tenant
   * Description: The unique authorization resource for the tenant for multi-tenancy
   * AuthorizationResource: TENANT
   * Relate: Tenant.tenantResource
   * HtmRowTitleOpen: host details
   * HtmRow: 3
   * HtmCell: 0
   * HtmColumn: 0
   **/
  protected void _tenantResource(Wrap<String> w) {
  }

  /**
   * {@inheritDoc}
   * DocValues: true
   * Persist: true
   * Unique: true
   * HtmRow: 3
   * HtmCell: 2
   * HtmColumn: 1
   * DisplayName: check name
   * Description: The name of the host check (may only contain letters, numbers, periods, colons, and dashes). 
   * Required: true
   * VarName: true
   * VarId: true
   */
  protected void _checkName(Wrap<String> w) {
  }

  /**
   * {@inheritDoc}
   * DocValues: true
   * Persist: true
   * HtmRow: 3
   * HtmCell: 3
   * DisplayName: check namespace
   * Description: The namespace of the host check. 
   */
  protected void _checkNamespace(Wrap<String> w) {
    w.o("default");
  }

  /**
   * {@inheritDoc}
   * DocValues: true
   * Persist: true
   * HtmRow: 3
   * HtmCell: 4
   * DisplayName: check command
   * Description: The bash command to run during the check. 
   * Multiline: true
   */
  protected void _checkCommand(Wrap<String> w) {
  }

  /**
   * {@inheritDoc}
   * DocValues: true
   * Persist: true
   * HtmRow: 3
   * HtmCell: 5
   * DisplayName: check interval in seconds
   * Description: The check interval in seconds. 
   */
  protected void _checkInterval(Wrap<Integer> w) {
  }

  /**
   * {@inheritDoc}
   * DocValues: true
   * Persist: true
   * HtmRow: 3
   * HtmCell: 6
   * DisplayName: check published
   * Description: When disabled the check will not be executed unless explicitly queued. 
   */
  protected void _checkPublished(Wrap<Boolean> w) {
    w.o(false);
  }

  /**
   * DocValues: true
   * Persist: true
   * HtmRow: 3
   * HtmCell: 7
   * HtmColumn: 2
   * DisplayName: event subscriptions
   * Description: The list of event subscriptions the host subscribes to. 
   */
  protected void _eventSubscriptions(List<String> l) {
  }

  /**
   * DocValues: true
   * Persist: true
   * HtmRow: 3
   * HtmCell: 8
   * HtmColumn: 2
   * DisplayName: event handlers
   * Description: The list of event handlers the host subscribes to. 
   */
  protected void _eventHandlers(List<String> l) {
    l.add("sensu-kafka-handler");
  }
}
