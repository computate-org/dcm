package org.computate.dcm.model.eda.host;

import java.util.List;

import org.computate.search.wrap.Wrap;
import org.computate.dcm.model.BaseModel;
import org.computate.dcm.model.eda.tenant.Tenant;

/**
 * Order: 8
 * Description: A managed host computer. 
 * AName: a host
 * Icon: <i class="fa-duotone fa-regular fa-server"></i>
 * Rows: 100
 * 
 * SearchPageUri: /en-us/search/host
 * EditPageUri: /en-us/edit/host/{hostResource}
 * UserPageUri: /en-us/user/host/{hostResource}
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
   * DocValues: true
   * Persist: true
   * DisplayName: tenant
   * Description: The unique authorization resource for the tenant for multi-tenancy
   * AuthorizationResource: TENANT
   * Relate: Tenant.tenantResource
   **/
  protected void _tenantResource(Wrap<String> w) {
  }

  /**
   * {@inheritDoc}
   * DocValues: true
   * Persist: true
   * DisplayName: AAP ID
   * Description: The Ansible Automation Platform ID of the organization. 
   */
  protected void _aapOrganizationId(Wrap<Long> w) {
  }

  /**
   * DocValues: true
   * Persist: true
   * DisplayName: inventory
   * Description: The unique authorization resource for the inventory for multi-tenancy
   * AuthorizationResource: HOSTINVENTORY
   * Relate: HostInventory.inventoryResource
   * HtmRowTitleOpen: inventory details
   * HtmRow: 3
   * HtmCell: 0
   **/
  protected void _inventoryResource(Wrap<String> w) {
  }

  /**
   * {@inheritDoc}
   * DocValues: true
   * Persist: true
   * DisplayName: AAP ID
   * Description: The Ansible Automation Platform ID of the host. 
   */
  protected void _aapHostId(Wrap<Long> w) {
  }

  /**
   * {@inheritDoc}
   * DocValues: true
   * Persist: true
   * Unique: true
   * HtmRow: 4
   * HtmCell: 0
   * HtmColumn: 1
   * DisplayName: Fully Qualified Domain Name
   * Description: The computer fully qualified domain name
   * Required: true
   * VarName: true
   */
  protected void _hostName(Wrap<String> w) {
  }

  /**
   * {@inheritDoc}
   * DocValues: true
   * Persist: true
   * Unique: true
   * HtmRow: 4
   * HtmCell: 1
   * HtmColumn: 1
   * DisplayName: IP address
   * Description: The IP address of the host
   * Required: true
   */
  protected void _ipAddress(Wrap<String> w) {
  }

  /**
   * {@inheritDoc}
   * DocValues: true
   * Persist: true
   * Unique: true
   * DisplayName: host ID
   * Description: The ID of the host in DCM. 
   */
  protected void _hostId(Wrap<String> w) {
    w.o(toId(hostName));
  }

  /**
   * {@inheritDoc}
   * DocValues: true
   * Persist: true
   * Unique: true
   * DisplayName: host resource
   * Description: The unique authorization resource for the host for multi-tenancy
   * VarId: true
   * AuthorizationResource: HOST
   */
  protected void _hostResource(Wrap<String> w) {
    w.o(String.format("%s-%s-%s", tenantResource, Host.CLASS_AUTH_RESOURCE, hostName));
  }

  /**
   * {@inheritDoc}
   * DocValues: true
   * Persist: true
   * HtmRow: 4
   * HtmCell: 2
   * HtmColumn: 2
   * DisplayName: host description
   * Description: The description of the host in AAP. 
   * VarDescription: true
   */
  protected void _hostDescription(Wrap<String> w) {
  }

  /**
   * {@inheritDoc}
   * DocValues: true
   * Persist: true
   * DisplayName: AAP ID
   * Description: The Ansible Automation Platform ID of the inventory. 
   */
  protected void _aapInventoryId(Wrap<Long> w) {
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
   * HtmRowTitleOpen: relationships
   * HtmRow: 5
   * HtmCell: 1
   * HtmColumn: 2
   * DisplayName: event subscriptions
   * Description: The list of event subscriptions the host subscribes to. 
   */
  protected void _eventSubscriptions(List<String> w) {
  }
}
