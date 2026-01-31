package org.computate.dcm.model.eda.computer;

import java.util.List;

import org.computate.search.wrap.Wrap;
import org.computate.dcm.model.BaseModel;

/**
 * Order: 3
 * Description: A computer record for managed computers
 * AName: a computer
 * Icon: <i class="fa-duotone fa-regular fa-server"></i>
 * Rows: 100
 * Page: true
 * SuperPage.enUS: PhenomenalBasePage
 * Indexed: true
 * Map.Integer.sqlSort: 2
 * Map.Integer.classSort: 3
 * 
 * ApiTag.enUS: Computer
 * ApiUri.enUS: /api/computer
 * 
 * ApiMethod.enUS: Search
 * ApiMethod: GET
 * ApiMethod: PATCH
 * ApiInternalPATCH: true
 * ApiMethod: POST
 * ApiMethod: PUTImport
 * 
 * ApiMethod.enUS: SearchPage
 * PageSearchPage.enUS: PhenomenalComputerPage
 * PageSuperSearchPage.enUS: PhenomenalBasePage
 * ApiUriSearchPage.enUS: /computer
 * 
 * IconGroup: duotone
 * IconName: server
 * 
 * RoleAll: true
 */
public class Computer extends ComputerGen<BaseModel> {

	/**
	 * {@inheritDoc}
	 * DocValues: true
	 * Persist: true
	 * HtmlRow: 3
	 * HtmlCell: 1
	 * DisplayName: Fully Qualified Domain Name
	 * VarH2: true
	 * VarOldTitle: true
	 * Description: The computer fully qualified domain name
	 * Facet: true
	 */
	protected void _computerName(Wrap<String> w) {
	}

	/**
	 * DocValues: true
	 * Relate: PhenomenalRule.computerPks
	 * HtmlRow: 3
	 * HtmlCell: 2
	 * DisplayName: Rules
	 * Description: The list of related rule primary keys
	 */
	protected void _rulePks(List<Long> w) {
	}

	/**
	 * Description: Set the object title as the computer name
	 */
	@Override
	protected void _objectTitle(Wrap<String> w) {
		w.o(computerName);
	}
}
