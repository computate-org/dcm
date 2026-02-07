package org.computate.dcm.model.eda.host;

import java.util.List;

import org.computate.search.wrap.Wrap;
import org.computate.dcm.model.BaseModel;
import org.computate.dcm.model.eda.host.HostGen;

/**
 * Order: 3
 * Description: A managed host computer. 
 * AName: a host
 * Icon: <i class="fa-duotone fa-regular fa-server"></i>
 * Rows: 100
 * 
 * SearchPageUri: /en-us/search/host
 * EditPageUri: /en-us/edit/host/{hostName}
 * UserPageUri: /en-us/user/host/{hostName}
 * ApiUri: /en-us/api/host
 * ApiMethod:
 *   Search:
 *   GET:
 *   PATCH:
 *   POST:
 *   DELETE:
 *   PUTImport:
 * 
 * AuthGroup:
 *   HostReader:
 *     GET:
 *   HostEditor:
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
public class Host extends HostGen<BaseModel> {

  /**
   * {@inheritDoc}
   * DocValues: true
   * Persist: true
   * Unique: true
   * HtmRowTitleOpen: host details
   * HtmRow: 3
   * HtmCell: 0
   * HtmColumn: 0
   * DisplayName: Fully Qualified Domain Name
   * Description: The computer fully qualified domain name
   * Required: true
   * VarId: true
   * VarName: true
   */
  protected void _hostName(Wrap<String> w) {
  }

  /**
   * {@inheritDoc}
   * DocValues: true
   * Persist: true
   * DisplayName: host auth resource
   * Description: The unique authorization resource for the host for multi-tenancy
   * Facet: true
   * AuthorizationResource: HOST
   **/
  protected void _hostResource(Wrap<String> w) {
    w.o(String.format("%s-%s", Host.CLASS_AUTH_RESOURCE, hostName));
  }

  /**
   * {@inheritDoc}
   * DocValues: true
   * Persist: true
   * DisplayName: inventory name
   * Description: The unique authorization resource for the host for multi-tenancy
   * Facet: true
   **/
  protected void _inventoryName(Wrap<String> w) {
  }

  /**
   * DocValues: true
   * Persist: true
   * HtmRow: 3
   * HtmCell: 1
   * HtmColumn: 1
   * DisplayName: event subscriptions
   * Description: The list of event subscriptions the host subscribes to. 
   */
  protected void _eventSubscriptions(List<String> w) {
  }
}
