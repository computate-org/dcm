package org.computate.dcm.model.eda.provider.intent;

import java.util.List;
import org.computate.search.wrap.Wrap;
import org.computate.dcm.model.eda.provider.Provider;

/**
 * Order: 151
 * Description: A provider for requesting other DCM models. 
 * AName: a provider intent
 * Icon: <i class=" fa-person-dolly"></i>
 * MenuDetails: DCM providers
 * MenuDetailsOpen: true
 *
 * AuthorizationResource: PROVIDER
 * SearchPageUri: /en-us/search/intent/provider
 * EditPageUri: /en-us/edit/intent/provider/{providerResource}
 * ApiUri: /en-us/api/intent/provider
 * ApiMethod:
 *   Search:
 *   GET:
 *   PATCH:
 *   POST:
 *   DELETE:
 *   PUTImport:
 * 
 * AuthGroup:
 *   ProviderAdmin:
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
public class ProviderIntent extends ProviderIntentGen<Provider> {

  /**
   * {@inheritDoc}
   * DocValues: true
   * Persist: true
   * DisplayName: provider name
   * Description: The name of this provider
   * HtmRow: 20
   * HtmCell: 1
   * HtmColumn: 1
   * HtmRowTitleOpen: provider details
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
   * DisplayName: provider ID
   * Description: The ID of this provider. By default, this will be auto-generated based on the provider name, converting non-alphanumeric characters to hyphens, all lowercase. 
   * Facet: true
   * DefaultFacet: true
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
   * Unique: true
   * VarId: true
   * StringFormat: String.format("%s-%s", ProviderIntent.CLASS_AUTH_RESOURCE, providerId)
   **/
  protected void _providerResource(Wrap<String> w) {
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
   * StringFormat: Optional.ofNullable(new_description).orElse(String.format("Intent state: %s\nRequested state: %s\nRealized state: %s", intentState, requestedState, realizedState))
   **/
  protected void _description(Wrap<String> w) {
  }

  /**
   * {@inheritDoc}
   * DocValues: true
   * DisplayName: provider requested
   * Description: The related provider requests for this provider intent. 
   * Relate: ProviderRequested.providerResource
   * HtmRowTitleOpen: requests
   * HtmRow: 21
   * HtmCell: 0
   **/
  protected void _requested(List<String> l) {
  }

  /**
   * {@inheritDoc}
   * DocValues: true
   * Persist: true
   * DisplayName: locked
   * Description: A provider intent gets locked after creating the first provider request. 
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
   * Relate: ProviderDiscovered.providerResource
   * HtmRowTitleOpen: discovered
   * HtmRow: 22
   * HtmCell: 0
   **/
  protected void _dcmDiscovered(List<String> l) {
  }

  /**
   * {@inheritDoc}
   * DocValues: true
   * DisplayName: provider realized
   * Description: Each time the provider was realized for this provider intent. 
   * Relate: ProviderRealized.providerResource
   * HtmRowTitleOpen: realizations
   * HtmRow: 23
   * HtmCell: 0
   **/
  protected void _dcmRealized(List<String> l) {
  }
}
