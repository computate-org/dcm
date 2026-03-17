package org.computate.dcm.model.eda.tenant;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import org.apache.commons.lang3.StringUtils;
import org.computate.search.tool.SearchTool;
import org.computate.search.wrap.Wrap;
import org.computate.vertx.api.BaseApiServiceImpl;
import org.computate.vertx.config.ComputateConfigKeys;
import org.computate.vertx.search.list.SearchList;
import org.computate.dcm.model.BaseModel;

import io.vertx.core.Promise;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.pgclient.data.Path;
import io.vertx.pgclient.data.Point;
import io.vertx.pgclient.data.Polygon;

/**
 * Order: 4
 * Description: Tenants are separate organizations sharing the same cloud resources. 
 * AName: a tenant
 * Icon: <i class="{{ FONTAWESOME_STYLE }} fa-buildings"></i>
 * MenuDetails: multi-tenancy
 * MenuDetailsOpen: true
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
   * HtmRow: 3
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
   * Description: The ID of this tenant
   * HtmRow: 3
   * HtmCell: 2
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
   * HtmRowTitleOpen: Useful URLs
   * HtmRow: 99
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
   * HtmRow: 3
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
   * HtmRowTitleOpen: relationships
   * HtmRow: 4
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
   * HtmRow: 4
   * HtmCell: 0
   **/
  protected void _ansibleProjectIds(List<String> l) {
  }
}
