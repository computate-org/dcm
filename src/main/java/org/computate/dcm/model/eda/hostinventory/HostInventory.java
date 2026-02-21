package org.computate.dcm.model.eda.hostinventory;

import java.util.List;

import org.computate.search.wrap.Wrap;
import org.computate.dcm.model.BaseModel;

/**
 * Order: 7
 * Description: A managed host inventory. 
 * AName: a host inventory
 * PluralName: host inventories
 * Icon: <i class="fa-duotone fa-regular fa-network-wired"></i>
 * Rows: 100
 * MenuDetails: hosts
 * MenuDetailsOpen: true
 * 
 * SearchPageUri: /en-us/search/host-inventory
 * EditPageUri: /en-us/edit/host-inventory/{inventoryResource}
 * UserPageUri: /en-us/user/host-inventory/{inventoryResource}
 * ApiUri: /en-us/api/host-inventory
 * ApiMethod:
 *   Search:
 *   GET:
 *   PATCH:
 *   POST:
 *   DELETE:
 *   PUTImport:
 * 
 * AuthGroup:
 *   HostInventoryReader:
 *     GET:
 *   HostInventoryEditor:
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
public class HostInventory extends HostInventoryGen<BaseModel> {

  /**
   * DocValues: true
   * Persist: true
   * DisplayName: tenant auth resource
   * Description: The unique authorization resource for the tenant for multi-tenancy
   * AuthorizationResource: TENANT
   * Relate: Tenant.tenantResource
   * HtmRowTitleOpen: tenant details
   * HtmRow: 4
   * HtmCell: 0
   * HtmColumn: 0
   **/
  protected void _tenantResource(Wrap<String> w) {
  }

  /**
   * {@inheritDoc}
   * DocValues: true
   * Persist: true
   * DisplayName: tenant ID
   * Description: The ID of this tenant
   * Facet: true
   * DefaultFacet: true
   **/
  protected void _tenantId(Wrap<String> w) {
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
   * {@inheritDoc}
   * DocValues: true
   * Persist: true
   * Unique: true
   * HtmRow: 4
   * HtmCell: 0
   * HtmColumn: 1
   * HtmRowTitleOpen: inventory details
   * DisplayName: inventory name
   * Description: The name of the inventory in AAP. 
   * VarName: true
   */
  protected void _inventoryName(Wrap<String> w) {
  }

  /**
   * {@inheritDoc}
   * DocValues: true
   * Persist: true
   * Unique: true
   * DisplayName: inventory ID
   * Description: The ID of the inventory in DCM. 
   */
  protected void _inventoryId(Wrap<String> w) {
    w.o(toId(inventoryName));
  }

  /**
   * {@inheritDoc}
   * DocValues: true
   * Persist: true
   * Unique: true
   * DisplayName: inventory resource
   * Description: The unique authorization resource for the inventory for multi-tenancy
   * VarId: true
   * AuthorizationResource: HOSTINVENTORY
   */
  protected void _inventoryResource(Wrap<String> w) {
    w.o(String.format("%s-%s", HostInventory.CLASS_AUTH_RESOURCE, inventoryName));
  }

  /**
   * {@inheritDoc}
   * DocValues: true
   * Persist: true
   * HtmRow: 4
   * HtmCell: 1
   * HtmColumn: 2
   * DisplayName: inventory description
   * Description: The description of the inventory in AAP. 
   * VarDescription: true
   */
  protected void _inventoryDescription(Wrap<String> w) {
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
   * DisplayName: AAP kind
   * Description: The Ansible Automation Platform kind of the inventory ("", "smart", "constructed"). 
   * HtmRow: 4
   * HtmCell: 2
   */
  protected void _inventoryKind(Wrap<String> w) {
    w.o("");
  }

  /**
   * {@inheritDoc}
   * DocValues: true
   * DisplayName: hosts
   * Description: The related hosts for this inventory. 
   * Relate: Host.inventoryResource
   * HtmRowTitleOpen: relationships
   * HtmRow: 5
   * HtmCell: 0
   **/
  protected void _hostInventoryIds(List<String> l) {
  }
}
