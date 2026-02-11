package org.computate.dcm.model.eda.hostinventory;

import java.util.List;

import org.computate.search.wrap.Wrap;
import org.computate.dcm.model.BaseModel;

/**
 * Order: 4
 * Description: A managed host inventory. 
 * AName: a host inventory
 * PluralName: host inventories
 * Icon: <i class="fa-duotone fa-regular fa-network-wired"></i>
 * Rows: 100
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
   * HtmRowTitleOpen: relations
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
   * Unique: true
   * HtmRow: 3
   * HtmCell: 1
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
   * HtmRow: 3
   * HtmCell: 3
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
   * DisplayName: AAP organization ID
   * Description: The Ansible Automation Platform organization ID of the inventory. 
   */
  protected void _inventoryOrganizationId(Wrap<Long> w) {
  }

  /**
   * {@inheritDoc}
   * DocValues: true
   * Persist: true
   * DisplayName: AAP kind
   * Description: The Ansible Automation Platform kind of the inventory ("", "smart", "constructed"). 
   * HtmRow: 3
   * HtmCell: 6
   */
  protected void _inventoryKind(Wrap<String> w) {
    w.o("");
  }
}
