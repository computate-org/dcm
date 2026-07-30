package org.computate.dcm.model.eda.provider.requested;

import java.util.List;
import org.computate.search.wrap.Wrap;
import org.computate.dcm.model.eda.provider.Provider;

/**
 * Order: 151
 * Description: A provider for requesting other DCM models. 
 * AName: a provider requested
 * Icon: <i class=" fa-person-dolly"></i>
 *
 * SearchPageUri: /en-us/search/requested/provider
 * EditPageUri: /en-us/edit/requested/provider/{providerResource}
 * ApiUri: /en-us/api/requested/provider
 * ApiMethod:
 *   Search:
 *   GET:
 *   PATCH:
 *   POST:
 *   DELETE:
 *   PUTImport:
 * 
 * AuthGroup:
 *   ProviderEditor:
 *     POST:
 *     PATCH:
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
public class ProviderRequested extends ProviderRequestedGen<Provider> {
/**
   * {@inheritDoc}
   * DocValues: true
   * Persist: true
   * DisplayName: provider ID
   * Description: The ID of this provider. By default, this will be auto-generated based on the provider name, converting non-alphanumeric characters to hyphens, all lowercase. 
   * Facet: true
   * DefaultFacet: true
   * StringFormat: oProviderIntent.getProviderId()
   **/
  protected void _providerId(Wrap<String> w) {
    w.o(toId(providerName));
  }

  /**
   * {@inheritDoc}
   * DocValues: true
   * Persist: true
   * DisplayName: provider auth resource
   * Description: The unique authorization resource for the provider for multi-tenancy
   * Facet: true
   * AuthorizationResource: PROVIDER
   * HtmRowTitleOpen: provider details
   * HtmRow: 5
   * HtmCell: 0
   * Required: true
   * Relate: ProviderIntent.providerResource
   **/
  protected void _providerResource(Wrap<String> w) {
  }

  /**
   * {@inheritDoc}
   * DocValues: true
   * Persist: true
   * DisplayName: provider requested number
   * Description: A unique number for this change to this provider. 
   * Facet: true
   **/
  protected void _requestedNumber(Wrap<Integer> w) {
  }

  /**
   * {@inheritDoc}
   * DocValues: true
   * Persist: true
   * DisplayName: provider requested ID
   * Description: The unique ID for this provider requested. 
   * Facet: true
   * VarId: true
   * StringFormat: String.format("%s-%s", providerResource, requestedNumber)
   * HtmColumn: 0
   * Unique: true
   **/
  protected void _requestedId(Wrap<String> w) {
  }

  /**
   * {@inheritDoc}
   * DocValues: true
   * Persist: true
   * DisplayName: provider requested name
   * Description: The name of this provider requested model
   * Facet: true
   * VarName: true
   * StringFormat: String.format("%s %s", oProviderIntent.getProviderName(), requestedNumber)
   * HtmColumn: 1
   **/
  protected void _requestedName(Wrap<String> w) {
  }

  /**
   * {@inheritDoc}
   * DocValues: true
   * DisplayName: provider approvals
   * Description: A list of provider approvals for this request. 
   * HtmRow: 8
   * HtmCell: 0
   * Relate: ProviderApproval.approvalId
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
   * StringFormat: Optional.ofNullable(new_createdVia).orElse(oProviderIntent.getCreatedVia())
   */
  protected void _createdVia(Wrap<String> w) {
  }

  /**
   * {@inheritDoc}
   * DocValues: true
   * Persist: true
   * DisplayName: requested state
   * Description: Captures the consumer's raw intent — what they asked for in their own terms. 
   * HtmRowTitleOpen: intent
   * HtmRow: 12
   * HtmCell: 0
   * Multiline: true
   * StringFormat: Optional.ofNullable(new_intentState).orElse(oProviderIntent.getIntentState())
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
   * StringFormat: Optional.ofNullable(new_requestedState).orElse(oProviderIntent.getRequestedState())
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
   * StringFormat: Optional.ofNullable(new_realizedState).orElse(oProviderIntent.getRealizedState())
   **/
  protected void _realizedState(Wrap<String> w) {
  }

  /**
   * {@inheritDoc}
   * DocValues: true
   * Persist: true
   * DisplayName: description
   * Description: A description of this provider
   * HtmRow: 20
   * HtmCell: 4
   * Facet: true
   * HtmColumn: 3
   * VarDescription: true
   * Multiline: true
   * StringFormat: Optional.ofNullable(new_description).orElse(String.format("Requested state: %s\nRequested state: %s\nRealized state: %s", requestedState, requestedState, realizedState))
   **/
  protected void _description(Wrap<String> w) {
  }

  /**
   * {@inheritDoc}
   * DocValues: true
   * Persist: true
   * DisplayName: locked
   * Description: A provider requested gets locked after creating the first provider request. 
   * HtmRow: 21
   * HtmCell: 0
   * Facet: true
   * StringFormat: Optional.ofNullable(Optional.ofNullable(new_locked).orElse(old_locked)).orElse(false)
   **/
  protected void _locked(Wrap<Boolean> w) {
  }

  /**
   * {@inheritDoc}
   * DocValues: true
   * DisplayName: provider discovered
   * Description: Each time the provider was discovered for this provider intent. 
   * Relate: Discovered.providerResource
   * HtmRowTitleOpen: discovered
   * HtmRow: 22
   * HtmCell: 0
   **/
  protected void _providerDiscovered(List<String> l) {
  }

  /**
   * {@inheritDoc}
   * DocValues: true
   * DisplayName: provider realized
   * Description: Each time the provider was realized for this provider requested. 
   * Relate: ProviderRealized.providerResource
   * HtmRowTitleOpen: realizations
   * HtmRow: 23
   * HtmCell: 0
   **/
  protected void _providerRealized(List<String> l) {
  }
}
