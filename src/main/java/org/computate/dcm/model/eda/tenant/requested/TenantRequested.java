package org.computate.dcm.model.eda.tenant.requested;

import java.util.List;
import org.computate.search.wrap.Wrap;
import org.computate.dcm.model.BaseModel;
import org.computate.dcm.model.eda.tenant.Tenant;

/**
 * Order: 142
 * Description: Requesting a change to create a new Tenant, or modify an existing Tenant. Tenants are separate organizations sharing the same cloud resources. 
 * AName: a requested tenant
 * Icon: <i class="{{ FONTAWESOME_STYLE }} fa-buildings"></i>
 *
 * SearchPageUri: /en-us/search/requested/tenant
 * EditPageUri: /en-us/edit/requested/tenant/{tenantRequestedId}
 * ApiUri: /en-us/api/intent/requested
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
public class TenantRequested extends TenantRequestedGen<Tenant> {

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
   * HtmRow: 20
   * HtmCell: 1
   * HtmColumn: 1
   * HtmRowTitleOpen: tenant details
   * Facet: true
   * VarName: true
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
   * DisplayName: tenant auth resource
   * Description: The unique authorization resource for the tenant for multi-tenancy
   * Facet: true
   * AuthorizationResource: TENANT
   * HtmRowTitleOpen: tenant details
   * HtmRow: 5
   * HtmCell: 0
   * HtmColumn: 0
   * Required: true
   * Relate: TenantIntent.tenantResource
   * StringFormat: String.format("%s-%s", TenantIntent.CLASS_AUTH_RESOURCE, tenantId)
   **/
  protected void _tenantResource(Wrap<String> w) {
    w.o(String.format("%s-%s", Tenant.CLASS_AUTH_RESOURCE, tenantId));
  }

  /**
   * {@inheritDoc}
   * DocValues: true
   * Persist: true
   * DisplayName: tenant requested number
   * Description: A unique number for this change request for this tenant. 
   * Facet: true
   * Required: true
   **/
  protected void _tenantRequestedNumber(Wrap<Integer> w) {
    // tomorrow, search for the max requested number by tenantResource, and increment by 1 in upsert. 
  }

  /**
   * {@inheritDoc}
   * DocValues: true
   * Persist: true
   * DisplayName: tenant requested ID
   * Description: The unique ID for this tenant requested. 
   * Facet: true
   * Unique: true
   * Required: true
   * VarId: true
   * StringFormat: String.format("%s-%s", tenantResource, tenantRequestedNumber)
   **/
  protected void _tenantRequestedId(Wrap<String> w) {
  }

  /**
   * {@inheritDoc}
   * DocValues: true
   * DisplayName: Request approvals
   * Description: A list of request approvals for this request. 
   * HtmRow: 8
   * HtmCell: 0
   * Relate: RequestApproval.approvalId
   **/
  protected void _requestApprovals(List<String> w) {
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
   * HtmRow: 10
   * HtmCell: 0
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
   * HtmRow: 10
   * HtmCell: 0
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
   * StringFormat: Optional.ofNullable(new_createdVia).orElse(oTenantIntent.getCreatedVia())
   */
  protected void _createdVia(Wrap<String> w) {
  }

  /**
   * {@inheritDoc}
   * DocValues: true
   * Persist: true
   * DisplayName: intent state
   * Description: Captures the consumer's raw intent — what they asked for in their own terms. 
   * HtmRowTitleOpen: intent
   * HtmRow: 12
   * HtmCell: 0
   * Multiline: true
   * StringFormat: Optional.ofNullable(new_intentState).orElse(oTenantIntent.getIntentState())
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
   * StringFormat: Optional.ofNullable(new_requestedState).orElse(oTenantIntent.getRequestedState())
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
   * StringFormat: Optional.ofNullable(new_realizedState).orElse(oTenantIntent.getRealizedState())
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
   * StringFormat: Optional.ofNullable(new_tenantDescription).orElse(String.format("Intent state: %s\nRequested state: %s\nRealized state: %s", intentState, requestedState, realizedState))
   **/
  protected void _tenantDescription(Wrap<String> w) {
    w.o(String.format("Intent state: %s\nRequested state: %s\nRealized state: %s", intentState, requestedState, realizedState));
  }

  /**
   * {@inheritDoc}
   * DocValues: true
   * DisplayName: tenant realized
   * Description: Each time the tenant was realized for this tenant intent. 
   * Relate: TenantRealized.tenantResource
   * HtmRowTitleOpen: realizations
   * HtmRow: 21
   * HtmCell: 0
   **/
  protected void _tenantRealized(List<String> l) {
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
   * StringFormat: Optional.ofNullable(Optional.ofNullable(new_locked).orElse(old_locked)).orElse(false)
   **/
  protected void _locked(Wrap<Boolean> w) {
  }
}
