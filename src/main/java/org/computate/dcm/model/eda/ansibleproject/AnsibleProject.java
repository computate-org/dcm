package org.computate.dcm.model.eda.ansibleproject;

import org.computate.search.wrap.Wrap;

import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.computate.dcm.model.BaseModel;

/**
 * Order: 9
 * Description: A ansible project to be run on a computer in Ansible Automation Platform. 
 * AName: a ansible project
 * Icon: <i class="{{ FONTAWESOME_STYLE }} fa-excavator"></i>
 * Rows: 100
 * MenuDetails: Ansible
 * MenuDetailsOpen: true
 * 
 * SearchPageUri: /en-us/search/ansible-project
 * EditPageUri: /en-us/edit/ansible-project/{ansibleProjectResource}
 * ApiUri: /en-us/api/ansible-project
 * ApiMethod:
 *   Search:
 *   GET:
 *   PATCH:
 *   POST:
 *   DELETE:
 *   PUTImport:
 * 
 * AuthGroup:
 *   AnsibleProjectReader:
 *     GET:
 *   AnsibleProjectEditor:
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
public class AnsibleProject extends AnsibleProjectGen<BaseModel> {

  /**
   * {@inheritDoc}
   * DocValues: true
   * Persist: true
   * DisplayName: tenant
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
   * DisplayName: AAP organization ID
   * Description: The ID of the ansible organization in AAP. 
   */
  protected void _aapOrganizationId(Wrap<Long> w) {
  }

  /**
   * {@inheritDoc}
   * DocValues: true
   * Persist: true
   * HtmRowTitleOpen: source control details
   * HtmRow: 4
   * HtmCell: 0
   * DisplayName: source control type
   * Description: The type of source source control to use. 
   * Radio:
   *   git: Git
   *   svn: Subversion
   *   insights: Red Hat Insights
   *   archive: Remote Archive
   */
  protected void _sourceControlType(Wrap<String> w) {
    w.o("git");
  }

  /**
   * {@inheritDoc}
   * DocValues: true
   * Persist: true
   * HtmRow: 4
   * HtmCell: 1
   * DisplayName: source control URL
   * Description: The URL to the source control repository. 
   * Required: true
   */
  protected void _sourceControlUrl(Wrap<String> w) {
  }

  /**
   * {@inheritDoc}
   * DocValues: true
   * Persist: true
   * HtmRow: 4
   * HtmCell: 2
   * DisplayName: source control branch
   * Description: The URL to the source control branch. 
   */
  protected void _sourceControlBranch(Wrap<String> w) {
  }

  /**
   * {@inheritDoc}
   * DocValues: true
   * Persist: true
   * Unique: true
   * HtmRowTitleOpen: project details
   * HtmRow: 5
   * HtmCell: 0
   * HtmColumn: 1
   * DisplayName: ansible project name
   * Description: The name of the ansible project (may only contain letters, numbers, periods, colons, and dashes). 
   * VarName: true
   */
  protected void _ansibleProjectName(Wrap<String> w) {
    w.o(Optional.ofNullable(sourceControlUrl).map(url -> StringUtils.substringBefore(StringUtils.substringAfterLast(url, "/"), ".")).orElse(null));
  }

  /**
   * {@inheritDoc}
   * DocValues: true
   * Persist: true
   * Unique: true
   * DisplayName: Ansible project ID
   * Description: The ID of the Ansible project in DCM. 
   */
  protected void _ansibleProjectId(Wrap<String> w) {
    w.o(toId(ansibleProjectName));
  }

  /**
   * {@inheritDoc}
   * DocValues: true
   * Persist: true
   * Unique: true
   * DisplayName: project resource
   * Description: The unique authorization resource for the Ansible project for multi-tenancy
   * VarId: true
   * AuthorizationResource: ANSIBLEPROJECT
   */
  protected void _ansibleProjectResource(Wrap<String> w) {
    w.o(String.format("%s-%s-%s", tenantResource, AnsibleProject.CLASS_AUTH_RESOURCE, ansibleProjectId));
  }

  /**
   * {@inheritDoc}
   * DocValues: true
   * Persist: true
   * DisplayName: AAP project ID
   * Description: The Ansible project ID in Ansible Automation Platform. 
   */
  protected void _aapProjectId(Wrap<Long> w) {
  }

  /**
   * {@inheritDoc}
   * DocValues: true
   * Persist: true
   * Unique: true
   * HtmRow: 4
   * HtmCell: 1
   * HtmColumn: 2
   * DisplayName: ansible project description
   * Description: The description of the ansible project. 
   * VarDescription: true
   */
  protected void _ansibleProjectDescription(Wrap<String> w) {
  }

  /**
   * {@inheritDoc}
   * DocValues: true
   * DisplayName: job templates
   * Description: The related job templates for this Ansible project. 
   * Relate: JobTemplate.ansibleProjectResource
   * HtmRow: 4
   * HtmCell: 2
   **/
  protected void _jobTemplateResources(List<String> l) {
  }
}