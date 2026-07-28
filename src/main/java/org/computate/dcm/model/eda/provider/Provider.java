package org.computate.dcm.model.eda.provider;

import org.computate.search.wrap.Wrap;
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
   * DisplayName: provider URL
   * Description: The URL to the DCM provider application. 
   **/
  protected void _providerUrl(Wrap<String> w) {
  }
}
