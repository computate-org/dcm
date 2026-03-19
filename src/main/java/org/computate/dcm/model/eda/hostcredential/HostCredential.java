package org.computate.dcm.model.eda.hostcredential;

import java.util.List;

import org.computate.search.wrap.Wrap;
import org.computate.dcm.model.BaseModel;

/**
 * Order: 5
 * Description: A managed host credential. 
 * AName: a host credential
 * PluralName: host credentials
 * Icon: <i class="{{ FONTAWESOME_STYLE }} fa-input-password"></i>
 * Rows: 100
 * 
 * SearchPageUri: /en-us/search/host-credential
 * EditPageUri: /en-us/edit/host-credential/{credentialResource}
 * UserPageUri: /en-us/user/host-credential/{credentialResource}
 * ApiUri: /en-us/api/host-credential
 * ApiMethod:
 *   Search:
 *   GET:
 *   PATCH:
 *   POST:
 *   DELETE:
 *   PUTImport:
 * 
 * AuthGroup:
 *   HostCredentialReader:
 *     GET:
 *   HostCredentialEditor:
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
public class HostCredential extends HostCredentialGen<BaseModel> {

  /**
   * DocValues: true
   * Persist: true
   * DisplayName: tenant auth resource
   * Description: The unique authorization resource for the tenant for multi-tenancy
   * AuthorizationResource: TENANT
   * Relate: Tenant.tenantResource
   * HtmRowTitleOpen: tenant details
   * HtmRow: 3
   * HtmCell: 0
   * HtmColumn: 0
   **/
  protected void _tenantResource(Wrap<String> w) {
  }

  /**
   * {@inheritDoc}
   * DocValues: true
   * Persist: true
   * DisplayName: tenant ID
   * Description: The ID of this tenant
   * Facet: true
   * DefaultFacet: true
   **/
  protected void _tenantId(Wrap<String> w) {
  }

  /**
   * {@inheritDoc}
   * DocValues: true
   * Persist: true
   * DisplayName: AAP ID
   * Description: The Ansible Automation Platform ID of the organization. 
   */
  protected void _aapOrganizationId(Wrap<Long> w) {
  }

  /**
   * {@inheritDoc}
   * DocValues: true
   * Persist: true
   * Unique: true
   * HtmRow: 4
   * HtmCell: 0
   * HtmColumn: 1
   * HtmRowTitleOpen: credential details
   * DisplayName: credential name
   * Description: The name of the credential in AAP. 
   * VarName: true
   */
  protected void _credentialName(Wrap<String> w) {
  }

  /**
   * {@inheritDoc}
   * DocValues: true
   * Persist: true
   * Unique: true
   * DisplayName: credential ID
   * Description: The ID of the credential in DCM. 
   */
  protected void _credentialId(Wrap<String> w) {
    w.o(toId(credentialName));
  }

  /**
   * {@inheritDoc}
   * DocValues: true
   * Persist: true
   * Unique: true
   * DisplayName: credential resource
   * Description: The unique authorization resource for the credential for multi-tenancy
   * VarId: true
   * AuthorizationResource: HOSTINVENTORY
   */
  protected void _credentialResource(Wrap<String> w) {
    w.o(String.format("%s-%s-%s", tenantResource, HostCredential.CLASS_AUTH_RESOURCE, credentialId));
  }

  /**
   * {@inheritDoc}
   * DocValues: true
   * Persist: true
   * HtmRow: 4
   * HtmCell: 1
   * HtmColumn: 2
   * DisplayName: credential description
   * Description: The description of the credential in AAP. 
   * VarDescription: true
   */
  protected void _credentialDescription(Wrap<String> w) {
  }

  /**
   * {@inheritDoc}
   * DocValues: true
   * Persist: true
   * DisplayName: AAP ID
   * Description: The Ansible Automation Platform ID of the credential. 
   */
  protected void _aapCredentialId(Wrap<Long> w) {
  }

  /**
   * {@inheritDoc}
   * DocValues: true
   * Persist: true
   * DisplayName: AAP credential type
   * Description: The Ansible Automation Platform credential type ID. 
   */
  protected void _aapCredentialTypeId(Wrap<Long> w) {
    w.o(1L);
  }

  /**
   * {@inheritDoc}
   * DocValues: true
   * Persist: true
   * DisplayName: user name
   * Description: The Ansible Automation Platform user name to connect to hosts. 
   * HtmRow: 4
   * HtmCell: 2
   */
  protected void _userName(Wrap<String> w) {
  }

  /**
   * {@inheritDoc}
   * DocValues: true
   * Persist: true
   * DisplayName: password
   * Description: The Ansible Automation Platform password to connect to hosts. 
   * HtmRow: 4
   * HtmCell: 3
   * FiwareType: password
   */
  protected void _password(Wrap<String> w) {
  }

  /**
   * {@inheritDoc}
   * DocValues: true
   * Persist: true
   * DisplayName: become method
   * Description: The Ansible Automation Platform become method to run privileged commands on hosts. 
   * HtmRow: 4
   * HtmCell: 4
   */
  protected void _becomeMethod(Wrap<String> w) {
    w.o("sudo");
  }

  /**
   * {@inheritDoc}
   * DocValues: true
   * Persist: true
   * DisplayName: become password
   * Description: The Ansible Automation become password to run privileged commands on hosts. 
   * HtmRow: 4
   * HtmCell: 2
   * FiwareType: password
   */
  protected void _becomePassword(Wrap<String> w) {
  }
}