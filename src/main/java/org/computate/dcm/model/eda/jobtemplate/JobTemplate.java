package org.computate.dcm.model.eda.jobtemplate;

import java.util.List;
import java.util.Optional;

import org.computate.search.wrap.Wrap;

import io.vertx.core.json.JsonObject;

import org.apache.commons.lang3.StringUtils;
import org.computate.dcm.model.BaseModel;

/**
 * Order: 10
 * Description: A job template to be run on a computer in Ansible Automation Platform. 
 * AName: a job template
 * Icon: <i class="{{ FONTAWESOME_STYLE }} fa-excavator"></i>
 * Rows: 100
 * 
 * SearchPageUri: /en-us/search/job-template
 * EditPageUri: /en-us/edit/job-template/{jobTemplateResource}
 * ApiUri: /en-us/api/job-template
 * ApiMethod:
 *   Search:
 *   GET:
 *   PATCH:
 *   POST:
 *   DELETE:
 *   PUTImport:
 * 
 * AuthGroup:
 *   JobTemplateReader:
 *     GET:
 *   JobTemplateEditor:
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
public class JobTemplate extends JobTemplateGen<BaseModel> {

  /**
   * DocValues: true
   * Persist: true
   * DisplayName: tenant
   * Description: The unique authorization resource for the tenant for multi-tenancy
   * AuthorizationResource: TENANT
   * Relate: Tenant.tenantResource
   * HtmRowTitleOpen: host details
   * HtmColumn: 0
   **/
  protected void _tenantResource(Wrap<String> w) {
  }

  /**
   * DocValues: true
   * Persist: true
   * DisplayName: tenant ID
   * Description: The tenant ID and Sensu namespace for the tenant. 
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
   * DocValues: true
   * Persist: true
   * DisplayName: inventory
   * Description: The unique authorization resource for the inventory for multi-tenancy
   * AuthorizationResource: HOSTINVENTORY
   * Relate: HostInventory.inventoryResource
   * HtmRowTitleOpen: inventory details
   * HtmRow: 3
   * HtmCell: 0
   * Required: true
   **/
  protected void _inventoryResource(Wrap<String> w) {
  }

  /**
   * {@inheritDoc}
   * DocValues: true
   * Persist: true
   * DisplayName: AAP inventory ID
   * Description: The inventory ID in Ansible Automation Platform. 
   */
  protected void _aapInventoryId(Wrap<Long> w) {
  }

  /**
   * {@inheritDoc}
   * DocValues: true
   * Persist: true
   * DisplayName: host credential
   * Description: The Ansible host credential to use when connecting to hosts. 
   * Relate: HostCredential.credentialResource
   * HtmRow: 3
   * HtmCell: 1
   * Required: true
   **/
  protected void _credentialResource(Wrap<String> w) {
  }

  /**
   * {@inheritDoc}
   * DocValues: true
   * Persist: true
   * DisplayName: AAP host credential ID
   * Description: The host credential ID in Ansible Automation Platform. 
   */
  protected void _aapHostCredentialId(Wrap<Long> w) {
  }

  /**
   * {@inheritDoc}
   * DocValues: true
   * Persist: true
   * DisplayName: Ansible project
   * Description: The Ansible project containing the playbook for this Job Template. 
   * Relate: AnsibleProject.ansibleProjectResource
   * HtmRowTitleOpen: Ansible Project details
   * HtmRow: 4
   * HtmCell: 0
   * Required: true
   **/
  protected void _ansibleProjectResource(Wrap<String> w) {
  }

  /**
   * {@inheritDoc}
   * DocValues: true
   * Persist: true
   * DisplayName: AAP project ID
   * Description: The project ID in Ansible Automation Platform. 
   */
  protected void _aapProjectId(Wrap<Long> w) {
  }

  /**
   * {@inheritDoc}
   * Persist: true
   * DocValues: true
   * DisplayName: ansible playbooks
   * Description: The related ansible playbooks for this Ansible project. 
   **/
  protected void _ansiblePlaybooks(List<String> l) {
  }

  /**
   * {@inheritDoc}
   * DocValues: true
   * DisplayName: ansible playbooks
   * Description: The related ansible playbooks for this Ansible project. 
   * Multiline: true
   * HtmRow: 5
   * HtmCell: 0
   **/
  protected void _ansiblePlaybooksList(Wrap<String> w) {
    StringBuilder sb = new StringBuilder();
    ansiblePlaybooks.stream().forEach(s -> {
      sb.append(s).append("\n");
    });
    w.o(sb.toString());
  }

  /**
   * {@inheritDoc}
   * DocValues: true
   * Persist: true
   * DisplayName: Ansible playbook
   * Description: The Ansible playbook for this Job Template. 
   * HtmRowTitleOpen: Ansible Job Template details
   * HtmRow: 5
   * HtmCell: 1
   * Required: true
   **/
  protected void _ansiblePlaybook(Wrap<String> w) {
  }

  /**
   * {@inheritDoc}
   * DocValues: true
   * Persist: true
   * HtmRow: 5
   * HtmCell: 2
   * HtmColumn: 1
   * DisplayName: job template name
   * Description: The name of the job template (may only contain letters, numbers, periods, colons, and dashes). 
   * VarName: true
   */
  protected void _jobTemplateName(Wrap<String> w) {
    w.o(Optional.ofNullable(ansiblePlaybook).map(s -> StringUtils.substringBeforeLast(s, ".")).orElse(null));
  }

  /**
   * {@inheritDoc}
   * DocValues: true
   * Persist: true
   * DisplayName: job template ID
   * Description: The ID of the job template in DCM. 
   */
  protected void _jobTemplateId(Wrap<String> w) {
    w.o(toId(jobTemplateName));
  }

  /**
   * {@inheritDoc}
   * DocValues: true
   * Persist: true
   * Unique: true
   * DisplayName: job template resource
   * Description: The unique authorization resource for the job template for multi-tenancy
   * VarId: true
   * AuthorizationResource: JOBTEMPLATE
   */
  protected void _jobTemplateResource(Wrap<String> w) {
    w.o(String.format("%s-%s-%s", tenantResource, JobTemplate.CLASS_AUTH_RESOURCE, jobTemplateId));
  }

  /**
   * {@inheritDoc}
   * DocValues: true
   * Persist: true
   * HtmRow: 5
   * HtmCell: 3
   * HtmColumn: 2
   * DisplayName: job template description
   * Description: The description of the job template. 
   * VarDescription: true
   */
  protected void _jobTemplateDescription(Wrap<String> w) {
  }

  /**
   * {@inheritDoc}
   * DocValues: true
   * Persist: true
   * HtmRow: 5
   * HtmCell: 4
   * DisplayName: job type
   * Description: The job type of the job template. 
   * Radio:
   *   run: run
   *   check: check
   */
  protected void _jobType(Wrap<String> w) {
    w.o("run");
  }

  /**
   * {@inheritDoc}
   * DocValues: true
   * Persist: true
   * HtmRow: 5
   * HtmCell: 5
   * DisplayName: extra vars
   * Description: The extra vars of the job template. 
   */
  protected void _extraVars(Wrap<JsonObject> w) {
  }

  /**
   * {@inheritDoc}
   * DocValues: true
   * Persist: true
   * DisplayName: AAP template ID
   * Description: The template ID in Ansible Automation Platform. 
   */
  protected void _aapTemplateId(Wrap<Long> w) {
  }
}
