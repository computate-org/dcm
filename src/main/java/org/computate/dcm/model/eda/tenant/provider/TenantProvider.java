package org.computate.dcm.model.eda.tenant.provider;

import org.computate.search.wrap.Wrap;
import org.computate.dcm.model.BaseModel;

/**
 * Order: 146
 * Description: A provider for requesting a TenantIntent to be realized. 
 * AName: a tenant intent
 * Icon: <i class="{{ FONTAWESOME_STYLE }} fa-buildings"></i>
 *
 * SearchPageUri: /en-us/search/provider/tenant
 * EditPageUri: /en-us/edit/provider/tenant/{tenantResource}
 * ApiUri: /en-us/api/provider/tenant
 * ApiMethod:
 *   Search:
 *   GET:
 *   PATCH:
 *   POST:
 *   DELETE:
 *   PUTImport:
 * 
 * AuthGroup:
 *   TenantProviderAdmin:
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
public class TenantProvider extends TenantProviderGen<BaseModel> {

  /**
   * {@inheritDoc}
   * DocValues: true
   * Persist: true
   * DisplayName: tenant provider name
   * Description: The name of this tenant provider
   * HtmRow: 10
   * HtmCell: 0
   * HtmColumn: 1
   * HtmRowTitleOpen: tenant provider details
   * Facet: true
   * VarName: true
   * Required: true
   **/
  protected void _providerName(Wrap<String> w) {
  }

  /**
   * {@inheritDoc}
   * DocValues: true
   * Persist: true
   * DisplayName: tenant provider ID
   * Description: The ID of this tenant provider. By default, this will be auto-generated based on the tenant provider name, converting non-alphanumeric characters to hyphens, all lowercase. 
   * Facet: true
   * DefaultFacet: true
   * VarId: true
   **/
  protected void _providerId(Wrap<String> w) {
    w.o(toId(providerName));
  }

  /**
   * {@inheritDoc}
   * DocValues: true
   * Persist: true
   * DisplayName: provider URL
   * Description: The URL to the DCM provider application. 
   **/
  protected void _providerUrl(Wrap<String> w) {
  }
}
