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
 * EditPageUri: /en-us/edit/requested/tenant/{tenantResource}
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
   * DisplayName: tenant intent
   * Description: The unique authorization resource for the tenant intent for multi-tenancy
   * Relate: TenantIntent.tenantResource
   **/
  @Override
  protected void _tenantResource(Wrap<String> w) {
  }

  /**
   * {@inheritDoc}
   * DocValues: true
   * Persist: true
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
   * HtmRowTitleOpen: intent
   * HtmRow: 12
   * HtmCell: 0
   * Multiline: true
   * Required: true
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
   * Required: true
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
   * Required: true
   **/
  protected void _realizedState(Wrap<String> w) {
  }
}
