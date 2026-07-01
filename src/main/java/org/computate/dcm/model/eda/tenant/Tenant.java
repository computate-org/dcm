package org.computate.dcm.model.eda.tenant;

import java.util.List;
import org.computate.search.wrap.Wrap;
import org.computate.dcm.model.BaseModel;

/**
 * Order: 144
 * Description: Tenants are separate organizations sharing the same cloud resources. 
 * AName: a discovered tenant
 * Icon: <i class="{{ FONTAWESOME_STYLE }} fa-buildings"></i>
 *
 * SearchPageUri: /en-us/search/tenant
 * EditPageUri: /en-us/edit/tenant/{tenantResource}
 * ApiUri: /en-us/api/tenant
 * ApiMethod:
 *   Search:
 *   GET:
 *   PATCH:
 *   POST:
 *   DELETE:
 *   PUTImport:
 * 
 * AuthGroup:
 *   TenantAdmin:
 *     GET:
 *   Admin:
 *     POST:
 *     PATCH:
 *     GET:
 *     DELETE:
 *   SuperAdmin:
 *     POST:
 *     PATCH:
 *     GET:
 *     DELETE:
 *     Admin:
 *     SuperAdmin:
 **/
public class Tenant extends TenantGen<BaseModel> {

  /**
   * {@inheritDoc}
   * DocValues: true
   * Persist: true
   * DisplayName: tenant name
   * Description: The name of this tenant
   * HtmRow: 23
   * HtmCell: 1
   * HtmColumn: 1
   * HtmRowTitleOpen: tenant details
   * Facet: true
   * VarName: true
   * Required: true
   **/
  protected void _tenantName(Wrap<String> w) {
  }

  /**
   * {@inheritDoc}
   * DocValues: true
   * Persist: true
   * DisplayName: tenant ID
   * Description: The ID of this tenant. By default, this will be auto-generated based on the tenant name, converting non-alphanumeric characters to hyphens, all lowercase. 
   * Facet: true
   * DefaultFacet: true
   **/
  protected void _tenantId(Wrap<String> w) {
    w.o(toId(tenantName));
  }

  /**
   * {@inheritDoc}
   * DocValues: true
   * Persist: true
   * DisplayName: tenant auth resource
   * Description: The unique authorization resource for the tenant for multi-tenancy
   * Facet: true
   * AuthorizationResource: TENANT
   * Unique: true
   * VarId: true
   **/
  protected void _tenantResource(Wrap<String> w) {
    w.o(String.format("%s-%s", Tenant.CLASS_AUTH_RESOURCE, tenantId));
  }

  /**
   * {@inheritDoc}
   * DocValues: true
   * Persist: true
   * HtmRowTitle: Useful URLs
   * HtmRow: 299
   * HtmCell: 1
   * Facet: true
   * DisplayName: Page ID
   * Description: The ID for this page. 
   */
  protected void _pageId(Wrap<String> w) {
    w.o(tenantId);
  }

  /**
   * {@inheritDoc}
   * DocValues: true
   * Persist: true
   * DisplayName: description
   * Description: A description of this tenant
   * HtmRow: 23
   * HtmCell: 4
   * Facet: true
   * HtmColumn: 3
   * VarDescription: true
   **/
  protected void _tenantDescription(Wrap<String> w) {
  }

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
