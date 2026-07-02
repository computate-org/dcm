package org.computate.dcm.model.eda.requestapproval;

import java.time.format.DateTimeFormatter;
import java.util.List;
import org.computate.search.wrap.Wrap;
import org.computate.dcm.model.BaseModel;

/**
 * Order: 900
 * Description: Individual approvals per request and per approver. 
 * AName: a request approval
 * Icon: <i class="{{ FONTAWESOME_STYLE }} fa-thumbs-up"></i>
 *
 * SearchPageUri: /en-us/search/approval
 * EditPageUri: /en-us/edit/approval/{approvalResource}
 * ApiUri: /en-us/api/approval
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
public class RequestApproval extends RequestApprovalGen<BaseModel> {

  /**
   * {@inheritDoc}
   * DocValues: true
   * Persist: true
   * DisplayName: approval name
   * Description: The name of this approval
   * HtmRow: 23
   * HtmCell: 1
   * HtmColumn: 1
   * HtmRowTitleOpen: approval details
   * Facet: true
   * VarName: true
   * Required: true
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
   **/
  protected void _approvalId(Wrap<String> w) {
    w.o(toId(approvalName));
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
   * StringFormat: siteRequest.getUserFullName()
   */ 
  protected void _approvedByFullName(Wrap<String> w) {
  }

  /**
   * {@inheritDoc}
   * DocValues: true
   * Persist: true
   * DisplayName: approval note
   * Description: A note from the approver about their decision about the requested change. 
   * HtmRow: 23
   * HtmCell: 4
   * Facet: true
   * HtmColumn: 3
   * VarDescription: true
   **/
  protected void _approvalNote(Wrap<String> w) {
  }

  /**
   * {@inheritDoc}
   * DocValues: true
   * Persist: true
   * DisplayName: approved
   * Description: Whether the requested change was approved by the approver. 
   * Facet: true
   * DefaultFacet: true
   **/
  protected void _approved(Wrap<Boolean> w) {}

  /**
   * {@inheritDoc}
   * DocValues: true
   * Persist: true
   * DisplayName: model type
   * Description: The Java Class simple name of this approval. 
   * Facet: true
   * DefaultFacet: true
   **/
  protected void _modelType(Wrap<String> w) {}

  /**
   * {@inheritDoc}
   * DocValues: true
   * Persist: true
   * DisplayName: model resource
   * Description: The unique model resource of this approval. 
   * Facet: true
   * DefaultFacet: true
   **/
  protected void _modelResource(Wrap<String> w) {}

  /**
   * {@inheritDoc}
   * DocValues: true
   * Persist: true
   * DisplayName: approval title
   * Description: A brief title from the approver about their decision about the requested change. 
   * Facet: true
   * VarTitle: true
   **/
  protected void _approvalTitle(Wrap<String> w) {

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

    w.o(String.format("%s %s by %s <%s>%s%s", modelResource, approvedStr, approvedByFullName, approvedByEmail, dateStr, noteStr));
  }
}
