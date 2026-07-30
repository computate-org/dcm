package org.computate.dcm.model.eda.provider;

import org.computate.search.wrap.Wrap;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Strings;
import org.computate.dcm.model.BaseModel;

/**
 * Description: A provider for requesting other DCM models. 
 * AName: a provider
 * Order: 150
 * Icon: <i class="{{ FONTAWESOME_STYLE }} fa-person-dolly"></i>
 * AuthorizationResource: PROVIDER
 * MenuDetails: DCM providers
 * MenuDetailsOpen: true
 * DCMGenerated: true
 **/
public class Provider extends ProviderGen<BaseModel> {

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
   * DisplayName: requested client ID
   * Description: The client ID you would like to request from the provider Keycloak service. 
   * HtmRow: 21
   * HtmCell: 0
   * HtmRowTitleOpen: remote provider details
   **/
  protected void _requestedClientId(Wrap<String> w) {
  }

  /**
   * {@inheritDoc}
   * DocValues: true
   * Persist: true
   * DisplayName: client secret environment variable
   * Description: The name of the environment variable that should contain the client secret for this provider. 
   * HtmRow: 21
   * HtmCell: 0
   * Multiline: true
   **/
  protected void _requestedEnvironmentVariable(Wrap<String> w) {
    w.o(String.format("PROVIDER_CLIENT_SECRET_%s", StringUtils.upperCase(Strings.CS.replace(toId(providerName), "-", "_"))));
  }

  /**
   * {@inheritDoc}
   * DocValues: true
   * Persist: true
   * DisplayName: provider request instructions
   * Description: Required steps that must be done before requesting this provider. 
   * HtmRow: 21
   * HtmCell: 0
   * Multiline: true
   **/
  protected void _providerRequestInstructions(Wrap<String> w) {
    if(System.getenv(requestedEnvironmentVariable) == null)
      w.o(String.format("You need to contact the provider %s, and ask them to provide you an authentication client secret for the client ID %s, and configure it in your vars.yaml file in development, or %s environment variable in production. The provider, %s, will also provide to you the provider URL you will use to connect to the provider for event-driven automation for realizing your requests in your DCM environment. ", providerName, requestedClientId, requestedEnvironmentVariable, providerName));
  }

  /**
   * {@inheritDoc}
   * DocValues: true
   * Persist: true
   * DisplayName: provider URL
   * Description: The URL to the remote DCM provider application. 
   * HtmRow: 21
   * HtmCell: 0
   **/
  protected void _providerUrl(Wrap<String> w) {
  }
}
