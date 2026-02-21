package org.computate.dcm.model.eda.jobtemplate;

import java.util.List;

import org.computate.search.wrap.Wrap;
import org.computate.dcm.model.BaseModel;

/**
 * Order: 6
 * Description: A job template to be run on a computer in Ansible Automation Platform. 
 * AName: a job template
 * Icon: <i class="fa-duotone fa-regular fa-excavator"></i>
 * Rows: 100
 * 
 * SearchPageUri: /en-us/search/job-template
 * EditPageUri: /en-us/edit/job-template/{jobTemplateId}
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
   * HtmRow: 3
   * HtmCell: 0
   * HtmColumn: 0
   **/
  protected void _tenantResource(Wrap<String> w) {
  }

  /**
   * DocValues: true
   * Persist: true
   * DisplayName: inventory
   * Description: The unique authorization resource for the inventory for multi-tenancy
   * AuthorizationResource: HOSTINVENTORY
   * Relate: HostInventory.inventoryResource
   * HtmRow: 3
   * HtmCell: 1
   * Required: true
   **/
  protected void _inventoryResource(Wrap<String> w) {
  }

  /**
   * {@inheritDoc}
   * DocValues: true
   * Persist: true
   * Unique: true
   * HtmRow: 3
   * HtmCell: 2
   * HtmColumn: 1
   * DisplayName: job template name
   * Description: The name of the job template (may only contain letters, numbers, periods, colons, and dashes). 
   * Required: true
   * VarName: true
   */
  protected void _jobTemplateName(Wrap<String> w) {
  }

  /**
   * {@inheritDoc}
   * DocValues: true
   * Persist: true
   * Unique: true
   * DisplayName: job template ID
   * Description: The ID of the job template in DCM. 
   * VarId: true
   */
  protected void _jobTemplateId(Wrap<String> w) {
    w.o(toId(jobTemplateName));
  }

  /**
   * {@inheritDoc}
   * DocValues: true
   * Persist: true
   * Unique: true
   * HtmRow: 3
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
   * HtmRow: 3
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
   * DisplayName: Ansible project
   * Description: The Ansible project containing the playbook for this Job Template. 
   * Relate: AnsibleProject.ansibleProjectId
   * HtmRowTitleOpen: Ansible details
   * HtmRow: 4
   * HtmCell: 0
   * Required: true
   **/
  protected void _ansibleProjectId(Wrap<String> w) {
  }

  /**
   * {@inheritDoc}
   * DocValues: true
   * Persist: true
   * DisplayName: Ansible playbook
   * Description: The Ansible playbook for this Job Template. 
   * HtmRow: 4
   * HtmCell: 1
   * Required: true
   **/
  protected void _ansiblePlaybook(Wrap<String> w) {
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
   * Unique: true
   * HtmRow: 3
   * HtmCell: 4
   * DisplayName: organization ID
   * Description: The ID of the ansible organization. 
   */
  protected void _organizationId(Wrap<String> w) {
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
   * DisplayName: AAP project ID
   * Description: The project ID in Ansible Automation Platform. 
   */
  protected void _aapProjectId(Wrap<Long> w) {
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
