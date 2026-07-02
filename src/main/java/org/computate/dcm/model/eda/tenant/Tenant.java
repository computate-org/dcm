package org.computate.dcm.model.eda.tenant;

import java.util.List;
import org.computate.search.wrap.Wrap;
import org.computate.dcm.model.BaseModel;

/**
 * Description: Tenants are separate organizations sharing the same cloud resources. 
 **/
public class Tenant extends TenantGen<BaseModel> {

  /**
   * {@inheritDoc}
   * DocValues: true
   * Persist: true
   * DisplayName: ACM Hub
   * Description: The ID of the ACM Hub for this cluster in Prometheus Keycloak Proxy. 
   * Facet: true
   * DefaultFacet: true
   **/
  protected void _hubId(Wrap<String> w) {}

  /**
   * {@inheritDoc}
   * DocValues: true
   * Persist: true
   * DisplayName: cluster name
   * Description: The name of this cluster
   * Facet: true
   * DefaultFacet: true
   **/
  protected void _clusterName(Wrap<String> w) {}

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
   * DisplayName: host inventories
   * Description: The related host inventories for this tenant. 
   * Relate: HostInventory.tenantResource
   * HtmRowTitle: relationships
   * HtmRow: 24
   * HtmCell: 0
   **/
  protected void _hostInventoryIds(List<String> l) {
  }

  /**
   * {@inheritDoc}
   * DocValues: true
   * DisplayName: Ansible projects
   * Description: The related Ansible projects for this tenant. 
   * Relate: AnsibleProject.tenantResource
   * HtmRow: 24
   * HtmCell: 0
   **/
  protected void _ansibleProjectIds(List<String> l) {
  }
}
