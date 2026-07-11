package org.computate.dcm.model.eda.tenant.realized;

import java.util.List;
import org.computate.search.wrap.Wrap;
import org.computate.dcm.model.BaseModel;
import org.computate.dcm.model.eda.tenant.Tenant;

/**
 * Order: 145
 * Description: An approved and realized Tenant. Tenants are separate organizations sharing the same cloud resources. 
 * AName: a realized tenant
 * Icon: <i class="{{ FONTAWESOME_STYLE }} fa-buildings"></i>
 *
 * SearchPageUri: /en-us/search/realized/tenant
 * EditPageUri: /en-us/edit/realized/tenant/{realizedId}
 * ApiUri: /en-us/api/intent/realized
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
public class TenantRealized extends TenantRealizedGen<Tenant> {

  @Override
  protected void _hostInventoryIds(List<String> l) {
  }

  @Override
  protected void _ansibleProjectIds(List<String> l) {
  }

  /**
   * {@inheritDoc}
   * DocValues: true
   * Persist: true
   * DisplayName: tenant name
   * Description: The name of this tenant
   * HtmRowTitleOpen: tenant details
   * Facet: true
   * StringFormat: oTenantIntent.getTenantName()
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
   * StringFormat: oTenantIntent.getTenantId()
   **/
  protected void _tenantId(Wrap<String> w) {
    w.o(toId(tenantName));
  }

  /**
   * {@inheritDoc}
   * DocValues: true
   * Persist: true
   * DisplayName: tenant requested
   * Description: The tenant requested being approved. 
   * Facet: true
   * HtmRowTitleOpen: tenant details
   * HtmRow: 5
   * HtmCell: 0
   * HtmColumn: 0
   * Required: true
   * AuthorizationResource: TENANT
   * Relate: TenantRequested.requestedId
   **/
  protected void _requestedId(Wrap<String> w) {
  }

  /**
   * {@inheritDoc}
   * DocValues: true
   * Persist: true
   * DisplayName: tenant auth resource
   * Description: The unique authorization resource for the tenant for multi-tenancy
   * Facet: true
   * Required: true
   * AuthorizationResource: TENANT
   * Relate: TenantIntent.tenantResource
   * StringFormat: oTenantRequested.getTenantResource()
   **/
  protected void _tenantResource(Wrap<String> w) {
    w.o(String.format("%s-%s", Tenant.CLASS_AUTH_RESOURCE, tenantId));
  }

  /**
   * {@inheritDoc}
   * DocValues: true
   * Persist: true
   * DisplayName: tenant realized number
   * Description: A unique number for each realized version of this tenant. 
   * Facet: true
   * Required: true
   **/
  protected void _realizedNumber(Wrap<Integer> w) {
  }

  /**
   * {@inheritDoc}
   * DocValues: true
   * Persist: true
   * HtmRowTitle: realized by
   * HtmRow: 10
   * HtmCell: 0
   * DisplayName: realized by user email
   * Description: The email address for the user who realized the change request. 
   * StringFormat: siteRequest.getUserEmail()
   */ 
  protected void _realizedByEmail(Wrap<String> w) {
  }

  /**
   * {@inheritDoc}
   * DocValues: true
   * Persist: true
   * DisplayName: realized by user ID
   * Description: The IdP UUID record for the user who realized the change request. 
   * HtmRow: 10
   * HtmCell: 0
   * StringFormat: siteRequest.getUserId()
   */ 
  protected void _realizedByUserId(Wrap<String> w) {
  }

  /**
   * {@inheritDoc}
   * DocValues: true
   * Persist: true
   * DisplayName: realized by user full name
   * Description: The first and last name for the user who realized the change request. 
   * HtmRow: 10
   * HtmCell: 0
   * StringFormat: siteRequest.getUserFullName()
   */ 
  protected void _realizedByFullName(Wrap<String> w) {
  }

  /**
   * {@inheritDoc}
   * DocValues: true
   * Persist: true
   * DisplayName: tenant realized ID
   * Description: The unique ID for this tenant realized. 
   * Facet: true
   * Required: true
   * Unique: true
   * VarId: true
   * StringFormat: String.format("%s-realized-by-%s-%s", requestedId, TenantRealized.toId(realizedByFullName), TenantRealized.toId(realizedByEmail))
   **/
  protected void _realizedId(Wrap<String> w) {
  }

  /**
   * {@inheritDoc}
   * DocValues: true
   * Persist: true
   * DisplayName: tenant realized name
   * Description: The name of this tenant realized model
   * Facet: true
   * VarName: true
   * StringFormat: String.format("%s %s", oTenantIntent.getTenantName(), realizedNumber)
   * HtmColumn: 1
   **/
  protected void _realizedName(Wrap<String> w) {
  }

  /**
   * {@inheritDoc}
   * DocValues: true
   * Persist: true
   * HtmRowTitle: created by
   * HtmRow: 10
   * HtmCell: 0
   * DisplayName: created by user email
   * Description: The email address for the user who created the change request. 
   * StringFormat: siteRequest.getUserEmail()
   */ 
  protected void _createdByEmail(Wrap<String> w) {
  }

  /**
   * {@inheritDoc}
   * DocValues: true
   * Persist: true
   * DisplayName: created by user ID
   * Description: The IdP UUID record for the user who created the change request. 
   * StringFormat: siteRequest.getUserId()
   */ 
  protected void _createdByUserId(Wrap<String> w) {
  }

  /**
   * {@inheritDoc}
   * DocValues: true
   * Persist: true
   * DisplayName: created by user full name
   * Description: The first and last name for the user who created the change request. 
   * StringFormat: siteRequest.getUserFullName()
   */ 
  protected void _createdByFullName(Wrap<String> w) {
  }

  /**
   * {@inheritDoc}
   * DocValues: true
   * Persist: true
   * HtmRow: 10
   * HtmCell: 0
   * DisplayName: created via
   * Description: Declares the ingestion path that makes audit quality transparent. 
   * Radio:
   *   pr: pull request
   *   api: API
   *   migration: migration
   *   system: system
   */
  protected void _createdVia(Wrap<String> w) {
  }

  /**
   * {@inheritDoc}
   * DocValues: true
   * Persist: true
   * DisplayName: intent state
   * Description: Captures the consumer's raw intent — what they asked for in their own terms. 
   * HtmRowTitle: intent
   * HtmRow: 12
   * HtmCell: 0
   * Multiline: true
   * StringFormat: Optional.ofNullable(new_intentState).orElse(oTenantRequested.getIntentState())
   **/
  protected void _intentState(Wrap<String> w) {
  }

  /**
   * {@inheritDoc}
   * DocValues: true
   * Persist: true
   * DisplayName: requested state
   * Description: Represents a complete, validated, provider-ready declaration of desired state. 
   * HtmRow: 12
   * HtmCell: 0
   * Multiline: true
   * StringFormat: Optional.ofNullable(new_requestedState).orElse(oTenantRequested.getRequestedState())
   **/
  protected void _requestedState(Wrap<String> w) {
  }

  /**
   * {@inheritDoc}
   * DocValues: true
   * Persist: true
   * DisplayName: realized state
   * Description: Must be a complete representation of the provisioned resource in DCM unified format — not a status code, but a full state description. 
   * HtmRow: 12
   * HtmCell: 0
   * Multiline: true
   * StringFormat: Optional.ofNullable(new_realizedState).orElse(oTenantRequested.getRealizedState())
   **/
  protected void _realizedState(Wrap<String> w) {
  }

  /**
   * {@inheritDoc}
   * DocValues: true
   * Persist: true
   * DisplayName: description
   * Description: A description of this tenant
   * HtmRow: 20
   * HtmCell: 4
   * Facet: true
   * HtmColumn: 3
   * VarDescription: true
   * Multiline: true
   * StringFormat: Optional.ofNullable(new_description).orElse(String.format("Intent state: %s\nRequested state: %s\nRealized state: %s", intentState, requestedState, realizedState))
   **/
  protected void _description(Wrap<String> w) {
    w.o(String.format("Intent state: %s\nRequested state: %s\nRealized state: %s", intentState, requestedState, realizedState));
  }

  /**
   * {@inheritDoc}
   * DocValues: true
   * Persist: true
   * DisplayName: locked
   * Description: A tenant intent gets locked after creating the first tenant request. 
   * HtmRow: 21
   * HtmCell: 0
   * Facet: true
   * StringFormat: true
   **/
  protected void _locked(Wrap<Boolean> w) {
  }
}
