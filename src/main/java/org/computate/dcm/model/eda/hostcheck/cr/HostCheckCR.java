package org.computate.dcm.model.eda.hostcheck.cr;

import java.util.List;

import org.computate.dcm.model.eda.hostcheck.HostCheck;
import org.computate.search.wrap.Wrap;

/**
 * MenuDetails: host checks
 * MenuDetailsOpen: true
 * Order: 120
 * Description: A new or updated check to be performed on a computer. 
 * AName: a host check change request
 * Icon: <i class="{{ FONTAWESOME_STYLE }} fa-box-check"></i>
 * Rows: 100
 * 
 * SearchPageUri: /en-us/search/host-check-cr
 * EditPageUri: /en-us/edit/host-check-cr/{changeRequestId}
 * ApiUri: /en-us/api/host-check-cr
 * ApiMethod:
 *   Search:
 *   GET:
 *   PATCH:
 *   POST:
 *   DELETE:
 *   PUTImport:
 * 
 * AuthGroup:
 *   HostCheckReader:
 *     GET:
 *   HostCheckEditor:
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
public class HostCheckCR extends HostCheckCRGen<HostCheck> {

  @Override
  protected void _tenantResource(Wrap<String> w) {
    super._tenantResource(w);
  }

  @Override
  protected void _jobTemplateResource(Wrap<String> w) {
    super._jobTemplateResource(w);
  }

  /**
   * {@inheritDoc}
   * Relate: HostCheck.checkResource
   * HtmRowTitleOpen: host check details
   * HtmRow: 9
   * HtmCell: 0
   **/
  @Override
  protected void _checkResource(Wrap<String> w) {
    super._checkResource(w);
  }

  /**
   * {@inheritDoc}
   * DocValues: true
   * Persist: true
   * Unique: true
   * DisplayName: host check resource
   * Description: The unique authorization resource for the host check for multi-tenancy
   * VarId: true
   * AuthorizationResource: HOSTCHECK
   * HtmRow: 9
   * HtmCell: 0
   */
  protected void _changeRequestId(Wrap<String> w) {
    w.o(String.format("%s-%s-%s", tenantResource, HostCheck.CLASS_AUTH_RESOURCE, checkId));
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
   * HtmRowTitleOpen: owned by
   * HtmRow: 11
   * HtmCell: 0
   * DisplayName: owned by user email
   * Description: The email address for the user who owns the change request. 
   */ 
  protected void _ownedByEmail(Wrap<String> w) {
  }

  /**
   * {@inheritDoc}
   * DocValues: true
   * Persist: true
   * DisplayName: owned by user ID
   * Description: The IdP UUID record for the user who owns the change request. 
   */ 
  protected void _ownedByUserId(Wrap<String> w) {
  }

  /**
   * {@inheritDoc}
   * DocValues: true
   * Persist: true
   * DisplayName: created by user name
   * Description: The first and last name for the user who owns the change request. 
   */ 
  protected void _ownedByDisplayName(Wrap<String> w) {
  }

  /**
   * {@inheritDoc}
   * DocValues: true
   * Persist: true
   * HtmRowTitleOpen: lifecycle state
   * HtmRow: 12
   * HtmCell: 0
   * DisplayName: lifecycle state
   * Description: Every Data artifact is in exactly one lifecycle state at any moment. 
   * Radio:
   *   REQUESTED: Requested
   *   INITIATED: Initiated
   *   EXECUTING: Executing
   *   COMPLETED: Completed
   *   FAILED: Failed
   *   CANCELLED: Cancelled
   */
  protected void _lifecycleState(Wrap<String> w) {
  }

  /**
   * {@inheritDoc}
   * DocValues: true
   * Persist: true
   * DisplayName: intent state
   * Description: Captures the consumer's raw intent — what they asked for in their own terms. 
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

  /**
   * {@inheritDoc}
   * DocValues: true
   * Persist: true
   * DisplayName: AAP ID
   * Description: The Ansible Automation Platform ID of the organization. 
   */
  protected void _aapOrganizationIdChange(Wrap<Long> w) {
  }

  /**
   * DocValues: true
   * Persist: true
   * DisplayName: job template
   * Description: The unique authorization resource for the job template for multi-tenancy
   * Relate: JobTemplate.jobTemplateResource
   * HtmRowTitleOpen: job template details
   * HtmRow: 14
   * HtmCell: 0
   * HtmColumn: 0
   **/
  protected void _jobTemplateResourceChange(Wrap<String> w) {
  }

  /**
   * {@inheritDoc}
   * DocValues: true
   * Persist: true
   * DisplayName: job template ID
   * Description: The ID of the job template in DCM. 
   */
  protected void _jobTemplateIdChange(Wrap<String> w) {
  }

  /**
   * {@inheritDoc}
   * DocValues: true
   * Persist: true
   * DisplayName: AAP template ID
   * Description: The template ID in Ansible Automation Platform. 
   */
  protected void _aapTemplateIdChange(Wrap<Long> w) {
  }

  /**
   * {@inheritDoc}
   * DocValues: true
   * Persist: true
   * Unique: true
   * HtmRowTitleOpen: host check details
   * HtmRow: 15
   * HtmCell: 0
   * HtmColumn: 1
   * DisplayName: check name
   * Description: The name of the host check (may only contain letters, numbers, periods, colons, and dashes). 
   * Required: true
   * VarName: true
   */
  protected void _checkNameChange(Wrap<String> w) {
  }

  /**
   * {@inheritDoc}
   * DocValues: true
   * Persist: true
   * Unique: true
   * DisplayName: check ID
   * Description: The ID of the host check in DCM. 
   */
  protected void _checkIdChange(Wrap<String> w) {
    w.o(toId(checkName));
  }

  /**
   * {@inheritDoc}
   * DocValues: true
   * Persist: true
   * Unique: true
   * DisplayName: host check resource
   * Description: The unique authorization resource for the host check for multi-tenancy
   * AuthorizationResource: HOSTCHECK
   */
  protected void _checkResourceChange(Wrap<String> w) {
    w.o(String.format("%s-%s-%s", tenantResource, HostCheck.CLASS_AUTH_RESOURCE, checkId));
  }

  /**
   * {@inheritDoc}
   * DocValues: true
   * Persist: true
   * Unique: true
   * HtmRow: 15
   * HtmCell: 1
   * HtmColumn: 2
   * DisplayName: check description
   * Description: The descrition of the host check. 
   * VarDescription: true
   */
  protected void _checkDescriptionChange(Wrap<String> w) {
  }

  /**
   * {@inheritDoc}
   * DocValues: true
   * Persist: true
   * HtmRow: 15
   * HtmCell: 2
   * DisplayName: check namespace
   * Description: The namespace of the host check. 
   */
  protected void _checkNamespaceChange(Wrap<String> w) {
    w.o(tenantId);
  }

  /**
   * {@inheritDoc}
   * DocValues: true
   * Persist: true
   * HtmRow: 15
   * HtmCell: 3
   * DisplayName: check command
   * Description: The bash command to run during the check. 
   * Multiline: true
   */
  protected void _checkCommandChange(Wrap<String> w) {
  }

  /**
   * {@inheritDoc}
   * DocValues: true
   * Persist: true
   * HtmRow: 15
   * HtmCell: 4
   * DisplayName: check interval in seconds
   * Description: The check interval in seconds. 
   */
  protected void _checkIntervalChange(Wrap<Integer> w) {
  }

  /**
   * {@inheritDoc}
   * DocValues: true
   * Persist: true
   * HtmRow: 15
   * HtmCell: 6
   * DisplayName: check published
   * Description: When disabled the check will not be executed unless explicitly queued. 
   */
  protected void _checkPublishedChange(Wrap<Boolean> w) {
    w.o(false);
  }

  /**
   * DocValues: true
   * Persist: true
   * HtmRow: 15
   * HtmCell: 7
   * HtmColumn: 3
   * DisplayName: event subscriptions
   * Description: The list of event subscriptions the host check subscribes to. 
   */
  protected void _eventSubscriptionsChange(List<String> l) {
  }

  /**
   * DocValues: true
   * Persist: true
   * HtmRow: 15
   * HtmCell: 8
   * HtmColumn: 4
   * DisplayName: event handlers
   * Description: The list of event handlers the host subscribes to. 
   */
  protected void _eventHandlersChange(List<String> l) {
  }
}
