package org.computate.dcm.model.eda.tenant.approval;

import java.time.format.DateTimeFormatter;
import org.computate.search.wrap.Wrap;
import org.computate.dcm.model.BaseModel;
import org.computate.dcm.model.eda.tenant.Tenant;

/**
 * Order: 143
 * Description: Individual tenant approvals per request and per approver. 
 * AName: a tenant approval
 * Icon: <i class="{{ FONTAWESOME_STYLE }} fa-thumbs-up"></i>
 *
 * AuthorizationResource: TENANT
 * SearchPageUri: /en-us/search/approval/tenant
 * EditPageUri: /en-us/edit/approval/tenant/{approvalId}
 * ApiUri: /en-us/api/approval/tenant
 * ApiMethod:
 *   Search:
 *   GET:
 *   PATCH:
 *   POST:
 *   DELETE:
 *   PUTImport:
 * 
 * AuthGroup:
 *   ApprovalAdmin:
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
public class TenantApproval extends TenantApprovalGen<BaseModel> {

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
   * HtmRowTitle: approved by
   * HtmRow: 10
   * HtmCell: 0
   * DisplayName: approved by user email
   * Description: The email address for the user who approved the change request. 
   * StringFormat: siteRequest.getUserEmail()
   */ 
  protected void _approvedByEmail(Wrap<String> w) {
  }

  /**
   * {@inheritDoc}
   * DocValues: true
   * Persist: true
   * DisplayName: approved by user ID
   * Description: The IdP UUID record for the user who approved the change request. 
   * HtmRow: 10
   * HtmCell: 0
   * StringFormat: siteRequest.getUserId()
   */ 
  protected void _approvedByUserId(Wrap<String> w) {
  }

  /**
   * {@inheritDoc}
   * DocValues: true
   * Persist: true
   * DisplayName: approved by user full name
   * Description: The first and last name for the user who approved the change request. 
   * HtmRow: 10
   * HtmCell: 0
   * StringFormat: siteRequest.getUserFullName()
   */ 
  protected void _approvedByFullName(Wrap<String> w) {
  }

  /**
   * {@inheritDoc}
   * DocValues: true
   * Persist: true
   * DisplayName: approved
   * Description: Whether the requested change was approved by the approver. 
   * Facet: true
   * DefaultFacet: true
   * HtmRowTitleOpen: approval
   * HtmRow: 11
   * HtmCell: 0
   * Required: true
   **/
  protected void _approved(Wrap<Boolean> w) {}

  /**
   * {@inheritDoc}
   * DocValues: true
   * Persist: true
   * DisplayName: approval note
   * Description: A note from the approver about their decision about the requested change. 
   * HtmRow: 11
   * HtmCell: 0
   * Facet: true
   * HtmColumn: 3
   * VarDescription: true
   * Required: true
   **/
  protected void _approvalNote(Wrap<String> w) {
  }

  /**
   * {@inheritDoc}
   * DocValues: true
   * Persist: true
   * DisplayName: approval name
   * Description: The name of this approval
   * HtmColumn: 1
   * Facet: true
   * VarName: true
   * Required: true
   * StringFormat: String.format("%s <%s> %s the %s", approvedByFullName, approvedByEmail, approved ? "approved" : "rejected", requestedId)
   **/
  protected void _approvalName(Wrap<String> w) {
  }

  /**
   * {@inheritDoc}
   * DocValues: true
   * Persist: true
   * DisplayName: approval ID
   * Description: The ID of this approval. By default, this will be auto-generated based on the approval name, converting non-alphanumeric characters to hyphens, all lowercase. 
   * Facet: true
   * DefaultFacet: true
   * Unique: true
   * VarId: true
   * StringFormat: String.format("%s-%s-by-%s-%s", requestedId, approved ? "approved" : "rejected", TenantApproval.toId(approvedByFullName), TenantApproval.toId(approvedByEmail))
   **/
  protected void _approvalId(Wrap<String> w) {
    w.o(toId(approvalName));
  }

  @Override
  protected void _objectTitle(Wrap<String> w) {
    String approvedStr;
    if(approved == null)
      approvedStr = "has not been reviewed";
    else if(approved)
      approvedStr = "was approved";
    else
      approvedStr = "was rejected";

    String noteStr;
    if(approvalNote == null)
      noteStr = "";
    else
      noteStr = ": " + approvalNote;

    String dateStr;
    if(created == null)
      dateStr = "";
    else
      dateStr = " " + created.format(DateTimeFormatter.ISO_DATE_TIME);

    w.o(String.format("%s %s by %s <%s>%s%s", requestedId, approvedStr, approvedByFullName, approvedByEmail, dateStr, noteStr));
  }
}
