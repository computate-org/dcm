package org.computate.dcm.model.eda.tenant.approval;

import java.time.format.DateTimeFormatter;
import org.computate.search.wrap.Wrap;
import org.computate.dcm.model.BaseModel;
import org.computate.dcm.model.eda.tenant.Tenant;
import org.computate.dcm.request.SiteRequest;
import org.computate.dcm.model.BaseModel;
import io.vertx.core.json.JsonObject;
import java.util.Date;
import java.util.Set;
import org.computate.vertx.api.ApiRequest;
import org.computate.dcm.config.ConfigKeys;
import java.util.Optional;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.computate.search.serialize.ComputateLocalDateSerializer;
import org.computate.search.serialize.ComputateLocalDateDeserializer;
import org.computate.search.serialize.ComputateZonedDateTimeSerializer;
import org.computate.search.serialize.ComputateZonedDateTimeDeserializer;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.computate.search.serialize.ComputateBigDecimalDeserializer;
import java.math.MathContext;
import org.apache.commons.lang3.math.NumberUtils;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.HashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.math.RoundingMode;
import java.util.Map;
import java.lang.String;
import org.computate.dcm.model.eda.tenant.requested.TenantRequested;
import org.computate.dcm.model.eda.tenant.intent.TenantIntent;
import java.lang.Boolean;
import org.computate.search.wrap.Wrap;
import io.vertx.core.Promise;
import io.vertx.core.Future;
import io.vertx.core.json.JsonArray;
import org.computate.vertx.search.list.SearchList;
import org.computate.search.tool.SearchTool;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.computate.search.response.solr.SolrResponse;

/**
 * <ol>
<h3>Suggestions that can generate more code for you: </h3> * </ol>
 * <li>You can add a class comment "{@inheritDoc}" if you wish to inherit the helpful inherited class comments from class TenantApprovalGen into the class TenantApproval. 
 * </li><li><p>
 *   You can add a class comment <kbd><b>Rows: 100</b></kbd> if you wish for the tenant approval API to return more or less than 10 results by default. 
 *   In this case, the API will return 100 results from the API instead of 10 by default. 
 *   Each API has built in pagination of the search results to ensure a user can query all the data a page at a time without running the application out of memory. 
 * </p>
 * </li>
 * <h3>About the TenantApproval class and it's generated class TenantApprovalGen&lt;BaseModel&gt;: </h3>extends TenantApprovalGen
 * <p>
 * This Java class extends a generated Java class TenantApprovalGen built by the <a href="https://github.com/computate-org/computate">https://github.com/computate-org/computate</a> project. 
 * Whenever this Java class is modified or touched, the watch service installed as described in the README, indexes all the information about this Java class in a local Apache Solr Search Engine. 
 * If you are running the service, you can see the indexed data about this Java Class here: 
 * </p>
 * <p><a href="https://solr.apps-crc.testing/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.tenant.approval.TenantApproval">Find the class TenantApproval in Solr. </a></p>
 * <p>
 * The extended class ending with "Gen" did not exist at first, but was automatically created by the same watch service based on the data retrieved from the local Apache Server search engine. 
 * The extended class contains many generated fields, getters, setters, initialization code, and helper methods to help build a website and API fast, reactive, and scalable. 
 * </p>
 * extends TenantApprovalGen<BaseModel>
 * <p>This <code>class TenantApproval extends TenantApprovalGen&lt;BaseModel&gt;</code>, which means it extends a newly generated TenantApprovalGen. 
 * The generated <code>class TenantApprovalGen extends BaseModel</code> which means that TenantApproval extends TenantApprovalGen which extends BaseModel. 
 * This generated inheritance is a powerful feature that allows a lot of boiler plate code to be created for you automatically while still preserving inheritance through the power of Java Generic classes. 
 * </p>
 * <h2>
 *   Api: true
 * </h2>
 * <p>
 *   This class contains a comment <kbd><b>Api: true</b></kbd>, which means this class will have Java Vert.x API backend code generated for these objects. 
 * </p>
 * <h2>ApiTag.enUS: true</h2>
 * <p>This class contains a comment <kbd><b>ApiTag: tenant approvals</b></kbd>, which groups all of the OpenAPIs for TenantApproval objects under the tag "tenant approvals". 
 * </p>
 * <h2>ApiUri.enUS: /en-us/api/approval/tenant</h2>
 * <p>This class contains a comment <kbd><b>ApiUri: /en-us/api/approval/tenant</b></kbd>, which defines the base API URI for TenantApproval objects as "/en-us/api/approval/tenant" in the OpenAPI spec. 
 * </p>
 * <h2>Color: null</h2>
 * <h2>Indexed: true</h2>
 * <p>This class contains a comment <kbd><b>Indexed: true</b></kbd>, which means this class will be indexed in the search engine. 
 * Every protected void method that begins with "_" that is marked to be searched with a comment like "Indexed: true", "Stored: true", or "DocValues: true" will be indexed in the search engine. 
 * </p>
 * <h2>{@inheritDoc}</h2>
 * <p>By adding a class comment "{@inheritDoc}", the TenantApproval class will inherit the helpful inherited class comments from the super class TenantApprovalGen. 
 * </p>
 * <h2>
 *   Rows: 10
 * </h2>
 * <p>This class contains a comment <kbd><b>Rows: 10</b></kbd>, which means the tenant approval API will return a default of 10 results instead of 10 by default. 
 * Each API has built in pagination of the search results to ensure a user can query all the data a page at a time without running the application out of memory. 
 * </p>
 * <p>
 *   You can add a class comment <kbd><b>Rows: 100</b></kbd> if you wish for the tenant approval API to return more or less than 10 results by default. 
 *   In this case, the API will return 100 results from the API instead of 10 by default. 
 *   Each API has built in pagination of the search results to ensure a user can query all the data a page at a time without running the application out of memory. 
 * </p>
 * <h2>
 *   Order: 143
 * </h2>
 * <p>
 *   This class contains a comment <kbd><b>Order: 143</b></kbd>, 
 *   which means this class will be sorted by the given number 143 
 *   ascending when code that relates to multiple classes at the same time is generated. 
 * </p>
 * <h2>SqlOrder: 143</h2>
 * <p>This class contains a comment <kbd><b>SqlOrder: 143</b></kbd>, which means this class will be sorted by the given number 143 ascending when SQL code to create and drop the tables is generated. 
 * </p>
 * <h2>Model: true</h2>
 * <p>This class contains a comment <kbd><b>Model: true</b></kbd>, which means this class will be stored in the database. 
 * Every protected void method that begins with "_" that contains a "Persist: true" comment will be a persisted field in the database table. 
 * </p>
 * <h2>Page: true</h2>
 * <p>This class contains a comment <kbd><b>Page: true</b></kbd>, which means this class will have webpage code generated for these objects. 
 * Java Vert.x backend API code, Handlebars HTML template frontend code, and JavaScript code will all generated and can be extended. 
 * This creates a new Java class org.computate.dcm.model.eda.tenant.approval.TenantApprovalPage. 
 * </p>
 * <h2>SuperPage.enUS: PageLayout</h2>
 * <p>This class contains a comment <kbd><b>SuperPage.enUS: PageLayout</b></kbd>, which identifies the Java super class of the page code by it's class simple name "PageLayout". 
 * This means that the newly created class org.computate.dcm.model.eda.tenant.approval.TenantApprovalPage extends org.computate.dcm.page.PageLayout. 
 * </p>
 * <h2>Promise: true</h2>
 * <p>
 *   This class contains a comment <kbd><b>Promise: true</b></kbd>
 *   Sometimes a Java class must be initialized asynchronously when it involves calling a blocking API. 
 *   This means that the TenantApproval Java class has promiseDeep methods which must be initialized asynchronously as a Vert.x Promise  instead of initDeep methods which are a simple non-asynchronous method. 
 * </p>
 * <p>
 *   Adding protected void methods beginning with an underscore with a Promise as the only parameter will automatically set `Promise: true`. 
 * </p>
 * <p>
 *   <pre>
 *   
 *   	protected void _promiseBefore(Promise&lt;Void&gt; promise) {
 *   		promise.complete();
 *   	}
 *   </pre>
 * </p>
 * <p>
 *   Java classes with the `Model: true` will automatically set `Promise: true`. 
 * </p>
 * <p>
 *   If a super class of this Java class with `Model: true`, then the child class will also inherit `Promise: true`. 
 * </p>
 * <h2>AName.enUS: a tenant approval</h2>
 * <p>This class contains a comment <kbd><b>AName.enUS: a tenant approval</b></kbd>, which identifies the language context to describe a TenantApproval as "a tenant approval". 
 * </p>
 * <p>
 * Delete the class TenantApproval in Solr: 
 * <pre>
 * curl -k 'https://solr.apps-crc.testing/solr/computate/update?commitWithin=1000&amp;overwrite=true&amp;wt=json' -X POST -H 'Content-type: text/xml' -u "admin:$(oc -n solr get secret/solr-solrcloud-security-bootstrap -o jsonpath={.data.admin} | base64 -d)" --data-raw '&lt;delete&gt;&lt;query&gt;classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.tenant.approval.TenantApproval&lt;/query&gt;&lt;/delete&gt;'
 * </pre>
 * </p>
 * <p>
 * Delete  the package org.computate.dcm.model.eda.tenant.approval in Solr: 
 * <pre>
 * curl -k 'https://solr.apps-crc.testing/solr/computate/update?commitWithin=1000&amp;overwrite=true&amp;wt=json' -X POST -H 'Content-type: text/xml' -u "admin:$(oc -n solr get secret/solr-solrcloud-security-bootstrap -o jsonpath={.data.admin} | base64 -d)" --data-raw '&lt;delete&gt;&lt;query&gt;classeNomEnsemble_enUS_indexed_string:org.computate.dcm.model.eda.tenant.approval&lt;/query&gt;&lt;/delete&gt;'
 * </pre>
 * </p>
 * <p>
 * Delete  the project dcm in Solr: 
 * <pre>
 * curl -k 'https://solr.apps-crc.testing/solr/computate/update?commitWithin=1000&amp;overwrite=true&amp;wt=json' -X POST -H 'Content-type: text/xml' -u "admin:$(oc -n solr get secret/solr-solrcloud-security-bootstrap -o jsonpath={.data.admin} | base64 -d)" --data-raw '&lt;delete&gt;&lt;query&gt;siteNom_indexed_string:dcm&lt;/query&gt;&lt;/delete&gt;'
 * </pre>
 * </p>
 * Generated: true
 **/
public abstract class TenantApprovalGen<DEV> extends BaseModel {
  protected static final Logger LOG = LoggerFactory.getLogger(TenantApproval.class);

  public static final String Description_enUS = "Individual tenant approvals per request and per approver. ";
  public static final String AName_enUS = "a tenant approval";
  public static final String This_enUS = "this ";
  public static final String ThisName_enUS = "this tenant approval";
  public static final String A_enUS = "a ";
  public static final String TheName_enUS = "the tenant approval";
  public static final String SingularName_enUS = "tenant approval";
  public static final String PluralName_enUS = "tenant approvals";
  public static final String NameActual_enUS = "current tenant approval";
  public static final String AllName_enUS = "all tenant approvals";
  public static final String SearchAllNameBy_enUS = "search tenant approvals by ";
  public static final String SearchAllName_enUS = "search tenant approvals";
  public static final String Title_enUS = "tenant approvals";
  public static final String ThePluralName_enUS = "the tenant approvals";
  public static final String NoNameFound_enUS = "no tenant approval found";
  public static final String ApiUri_enUS = "/en-us/api/approval/tenant";
  public static final String ApiUriSearchPage_enUS = "/en-us/search/approval/tenant";
  public static final String ApiUriEditPage_enUS = "/en-us/edit/approval/tenant/{approvalId}";
  public static final String OfName_enUS = "of tenant approval";
  public static final String ANameAdjective_enUS = "a tenant approval";
  public static final String NameAdjectiveSingular_enUS = "tenant approval";
  public static final String NameAdjectivePlural_enUS = "tenant approvals";
  public static final String Search_enUS_OpenApiUri = "/en-us/api/approval/tenant";
  public static final String Search_enUS_StringFormatUri = "/en-us/api/approval/tenant";
  public static final String Search_enUS_StringFormatUrl = "%s/en-us/api/approval/tenant";
  public static final String GET_enUS_OpenApiUri = "/en-us/api/approval/tenant/{approvalId}";
  public static final String GET_enUS_StringFormatUri = "/en-us/api/approval/tenant/%s";
  public static final String GET_enUS_StringFormatUrl = "%s/en-us/api/approval/tenant/%s";
  public static final String PATCH_enUS_OpenApiUri = "/en-us/api/approval/tenant";
  public static final String PATCH_enUS_StringFormatUri = "/en-us/api/approval/tenant";
  public static final String PATCH_enUS_StringFormatUrl = "%s/en-us/api/approval/tenant";
  public static final String POST_enUS_OpenApiUri = "/en-us/api/approval/tenant";
  public static final String POST_enUS_StringFormatUri = "/en-us/api/approval/tenant";
  public static final String POST_enUS_StringFormatUrl = "%s/en-us/api/approval/tenant";
  public static final String DELETE_enUS_OpenApiUri = "/en-us/api/approval/tenant/{approvalId}";
  public static final String DELETE_enUS_StringFormatUri = "/en-us/api/approval/tenant/%s";
  public static final String DELETE_enUS_StringFormatUrl = "%s/en-us/api/approval/tenant/%s";
  public static final String PUTImport_enUS_OpenApiUri = "/en-us/api/approval/tenant-import";
  public static final String PUTImport_enUS_StringFormatUri = "/en-us/api/approval/tenant-import";
  public static final String PUTImport_enUS_StringFormatUrl = "%s/en-us/api/approval/tenant-import";
  public static final String SearchPage_enUS_OpenApiUri = "/en-us/search/approval/tenant";
  public static final String SearchPage_enUS_StringFormatUri = "/en-us/search/approval/tenant";
  public static final String SearchPage_enUS_StringFormatUrl = "%s/en-us/search/approval/tenant";
  public static final String EditPage_enUS_OpenApiUri = "/en-us/edit/approval/tenant/{approvalId}";
  public static final String EditPage_enUS_StringFormatUri = "/en-us/edit/approval/tenant/%s";
  public static final String EditPage_enUS_StringFormatUrl = "%s/en-us/edit/approval/tenant/%s";
  public static final String DELETEFilter_enUS_OpenApiUri = "/en-us/api/approval/tenant";
  public static final String DELETEFilter_enUS_StringFormatUri = "/en-us/api/approval/tenant";
  public static final String DELETEFilter_enUS_StringFormatUrl = "%s/en-us/api/approval/tenant";

  public static final String Icon = "<i class=\"{{ FONTAWESOME_STYLE }} fa-thumbs-up\"></i>";

	////////////////
  // tenantName //
	////////////////


  /**
   *  The entity tenantName
   *	 is defined as null before being initialized. 
   */
  @JsonProperty
  @JsonInclude(Include.NON_NULL)
  protected String tenantName;

  /**
   * <br> The entity tenantName
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.tenant.approval.TenantApproval&fq=entiteVar_enUS_indexed_string:tenantName">Find the entity tenantName in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _tenantName(Wrap<String> w);

  public String getTenantName() {
    return tenantName;
  }
  public void setTenantName(String o) {
    this.tenantName = TenantApproval.staticSetTenantName(siteRequest_, o);
  }
  public static String staticSetTenantName(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected TenantApproval tenantNameInit() {
    Wrap<String> tenantNameWrap = new Wrap<String>().var("tenantName");
    if(tenantName == null) {
      _tenantName(tenantNameWrap);
      Optional.ofNullable(tenantNameWrap.getO()).ifPresent(o -> {
        setTenantName(o);
      });
    }
    return (TenantApproval)this;
  }

  public static String staticSearchTenantName(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrTenantName(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqTenantName(SiteRequest siteRequest_, String o) {
    return TenantApproval.staticSearchTenantName(siteRequest_, TenantApproval.staticSetTenantName(siteRequest_, o)).toString();
  }

  public String sqlTenantName() {
    return tenantName;
  }

  public static String staticJsonTenantName(String tenantName) {
    return tenantName;
  }

	//////////////
  // tenantId //
	//////////////


  /**
   *  The entity tenantId
   *	 is defined as null before being initialized. 
   */
  @JsonProperty
  @JsonInclude(Include.NON_NULL)
  protected String tenantId;

  /**
   * <br> The entity tenantId
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.tenant.approval.TenantApproval&fq=entiteVar_enUS_indexed_string:tenantId">Find the entity tenantId in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _tenantId(Wrap<String> w);

  public String getTenantId() {
    return tenantId;
  }
  public void setTenantId(String o) {
    this.tenantId = TenantApproval.staticSetTenantId(siteRequest_, o);
  }
  public static String staticSetTenantId(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected TenantApproval tenantIdInit() {
    Wrap<String> tenantIdWrap = new Wrap<String>().var("tenantId");
    if(tenantId == null) {
      _tenantId(tenantIdWrap);
      Optional.ofNullable(tenantIdWrap.getO()).ifPresent(o -> {
        setTenantId(o);
      });
    }
    return (TenantApproval)this;
  }

  public static String staticSearchTenantId(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrTenantId(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqTenantId(SiteRequest siteRequest_, String o) {
    return TenantApproval.staticSearchTenantId(siteRequest_, TenantApproval.staticSetTenantId(siteRequest_, o)).toString();
  }

  public String sqlTenantId() {
    return tenantId;
  }

  public static String staticJsonTenantId(String tenantId) {
    return tenantId;
  }

	/////////////////
  // requestedId //
	/////////////////


  /**
   *  The entity requestedId
   *	 is defined as null before being initialized. 
   */
  @JsonProperty
  @JsonInclude(Include.NON_NULL)
  protected String requestedId;

  /**
   * <br> The entity requestedId
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.tenant.approval.TenantApproval&fq=entiteVar_enUS_indexed_string:requestedId">Find the entity requestedId in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _requestedId(Wrap<String> w);

  public String getRequestedId() {
    return requestedId;
  }
  public void setRequestedId(String o) {
    this.requestedId = TenantApproval.staticSetRequestedId(siteRequest_, o);
  }
  public static String staticSetRequestedId(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected TenantApproval requestedIdInit() {
    Wrap<String> requestedIdWrap = new Wrap<String>().var("requestedId");
    if(requestedId == null) {
      _requestedId(requestedIdWrap);
      Optional.ofNullable(requestedIdWrap.getO()).ifPresent(o -> {
        setRequestedId(o);
      });
    }
    return (TenantApproval)this;
  }

  public static String staticSearchRequestedId(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrRequestedId(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqRequestedId(SiteRequest siteRequest_, String o) {
    return TenantApproval.staticSearchRequestedId(siteRequest_, TenantApproval.staticSetRequestedId(siteRequest_, o)).toString();
  }

  public String sqlRequestedId() {
    return requestedId;
  }

  public static String staticJsonRequestedId(String requestedId) {
    return requestedId;
  }

	////////////////////
  // tenantResource //
	////////////////////


  /**
   *  The entity tenantResource
   *	 is defined as null before being initialized. 
   */
  @JsonProperty
  @JsonInclude(Include.NON_NULL)
  protected String tenantResource;

  /**
   * <br> The entity tenantResource
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.tenant.approval.TenantApproval&fq=entiteVar_enUS_indexed_string:tenantResource">Find the entity tenantResource in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _tenantResource(Wrap<String> w);

  public String getTenantResource() {
    return tenantResource;
  }
  public void setTenantResource(String o) {
    this.tenantResource = TenantApproval.staticSetTenantResource(siteRequest_, o);
  }
  public static String staticSetTenantResource(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected TenantApproval tenantResourceInit() {
    Wrap<String> tenantResourceWrap = new Wrap<String>().var("tenantResource");
    if(tenantResource == null) {
      _tenantResource(tenantResourceWrap);
      Optional.ofNullable(tenantResourceWrap.getO()).ifPresent(o -> {
        setTenantResource(o);
      });
    }
    return (TenantApproval)this;
  }

  public static String staticSearchTenantResource(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrTenantResource(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqTenantResource(SiteRequest siteRequest_, String o) {
    return TenantApproval.staticSearchTenantResource(siteRequest_, TenantApproval.staticSetTenantResource(siteRequest_, o)).toString();
  }

  public String sqlTenantResource() {
    return tenantResource;
  }

  public static String staticJsonTenantResource(String tenantResource) {
    return tenantResource;
  }

	/////////////////////
  // approvedByEmail //
	/////////////////////


  /**
   *  The entity approvedByEmail
   *	 is defined as null before being initialized. 
   */
  @JsonProperty
  @JsonInclude(Include.NON_NULL)
  protected String approvedByEmail;

  /**
   * <br> The entity approvedByEmail
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.tenant.approval.TenantApproval&fq=entiteVar_enUS_indexed_string:approvedByEmail">Find the entity approvedByEmail in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _approvedByEmail(Wrap<String> w);

  public String getApprovedByEmail() {
    return approvedByEmail;
  }
  public void setApprovedByEmail(String o) {
    this.approvedByEmail = TenantApproval.staticSetApprovedByEmail(siteRequest_, o);
  }
  public static String staticSetApprovedByEmail(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected TenantApproval approvedByEmailInit() {
    Wrap<String> approvedByEmailWrap = new Wrap<String>().var("approvedByEmail");
    if(approvedByEmail == null) {
      _approvedByEmail(approvedByEmailWrap);
      Optional.ofNullable(approvedByEmailWrap.getO()).ifPresent(o -> {
        setApprovedByEmail(o);
      });
    }
    return (TenantApproval)this;
  }

  public static String staticSearchApprovedByEmail(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrApprovedByEmail(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqApprovedByEmail(SiteRequest siteRequest_, String o) {
    return TenantApproval.staticSearchApprovedByEmail(siteRequest_, TenantApproval.staticSetApprovedByEmail(siteRequest_, o)).toString();
  }

  public String sqlApprovedByEmail() {
    return approvedByEmail;
  }

  public static String staticJsonApprovedByEmail(String approvedByEmail) {
    return approvedByEmail;
  }

	//////////////////////
  // approvedByUserId //
	//////////////////////


  /**
   *  The entity approvedByUserId
   *	 is defined as null before being initialized. 
   */
  @JsonProperty
  @JsonInclude(Include.NON_NULL)
  protected String approvedByUserId;

  /**
   * <br> The entity approvedByUserId
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.tenant.approval.TenantApproval&fq=entiteVar_enUS_indexed_string:approvedByUserId">Find the entity approvedByUserId in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _approvedByUserId(Wrap<String> w);

  public String getApprovedByUserId() {
    return approvedByUserId;
  }
  public void setApprovedByUserId(String o) {
    this.approvedByUserId = TenantApproval.staticSetApprovedByUserId(siteRequest_, o);
  }
  public static String staticSetApprovedByUserId(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected TenantApproval approvedByUserIdInit() {
    Wrap<String> approvedByUserIdWrap = new Wrap<String>().var("approvedByUserId");
    if(approvedByUserId == null) {
      _approvedByUserId(approvedByUserIdWrap);
      Optional.ofNullable(approvedByUserIdWrap.getO()).ifPresent(o -> {
        setApprovedByUserId(o);
      });
    }
    return (TenantApproval)this;
  }

  public static String staticSearchApprovedByUserId(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrApprovedByUserId(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqApprovedByUserId(SiteRequest siteRequest_, String o) {
    return TenantApproval.staticSearchApprovedByUserId(siteRequest_, TenantApproval.staticSetApprovedByUserId(siteRequest_, o)).toString();
  }

  public String sqlApprovedByUserId() {
    return approvedByUserId;
  }

  public static String staticJsonApprovedByUserId(String approvedByUserId) {
    return approvedByUserId;
  }

	////////////////////////
  // approvedByFullName //
	////////////////////////


  /**
   *  The entity approvedByFullName
   *	 is defined as null before being initialized. 
   */
  @JsonProperty
  @JsonInclude(Include.NON_NULL)
  protected String approvedByFullName;

  /**
   * <br> The entity approvedByFullName
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.tenant.approval.TenantApproval&fq=entiteVar_enUS_indexed_string:approvedByFullName">Find the entity approvedByFullName in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _approvedByFullName(Wrap<String> w);

  public String getApprovedByFullName() {
    return approvedByFullName;
  }
  public void setApprovedByFullName(String o) {
    this.approvedByFullName = TenantApproval.staticSetApprovedByFullName(siteRequest_, o);
  }
  public static String staticSetApprovedByFullName(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected TenantApproval approvedByFullNameInit() {
    Wrap<String> approvedByFullNameWrap = new Wrap<String>().var("approvedByFullName");
    if(approvedByFullName == null) {
      _approvedByFullName(approvedByFullNameWrap);
      Optional.ofNullable(approvedByFullNameWrap.getO()).ifPresent(o -> {
        setApprovedByFullName(o);
      });
    }
    return (TenantApproval)this;
  }

  public static String staticSearchApprovedByFullName(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrApprovedByFullName(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqApprovedByFullName(SiteRequest siteRequest_, String o) {
    return TenantApproval.staticSearchApprovedByFullName(siteRequest_, TenantApproval.staticSetApprovedByFullName(siteRequest_, o)).toString();
  }

  public String sqlApprovedByFullName() {
    return approvedByFullName;
  }

  public static String staticJsonApprovedByFullName(String approvedByFullName) {
    return approvedByFullName;
  }

	//////////////
  // approved //
	//////////////


  /**
   *  The entity approved
   *	 is defined as null before being initialized. 
   */
  @JsonProperty
  @JsonInclude(Include.NON_NULL)
  protected Boolean approved;

  /**
   * <br> The entity approved
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.tenant.approval.TenantApproval&fq=entiteVar_enUS_indexed_string:approved">Find the entity approved in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _approved(Wrap<Boolean> w);

  public Boolean getApproved() {
    return approved;
  }

  public void setApproved(Boolean approved) {
    this.approved = approved;
  }
  @JsonIgnore
  public void setApproved(String o) {
    this.approved = TenantApproval.staticSetApproved(siteRequest_, o);
  }
  public static Boolean staticSetApproved(SiteRequest siteRequest_, String o) {
    return Boolean.parseBoolean(o);
  }
  protected TenantApproval approvedInit() {
    Wrap<Boolean> approvedWrap = new Wrap<Boolean>().var("approved");
    if(approved == null) {
      _approved(approvedWrap);
      Optional.ofNullable(approvedWrap.getO()).ifPresent(o -> {
        setApproved(o);
      });
    }
    return (TenantApproval)this;
  }

  public static Boolean staticSearchApproved(SiteRequest siteRequest_, Boolean o) {
    return o;
  }

  public static String staticSearchStrApproved(SiteRequest siteRequest_, Boolean o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqApproved(SiteRequest siteRequest_, String o) {
    return TenantApproval.staticSearchApproved(siteRequest_, TenantApproval.staticSetApproved(siteRequest_, o)).toString();
  }

  public Boolean sqlApproved() {
    return approved;
  }

  public static Boolean staticJsonApproved(Boolean approved) {
    return approved;
  }

	//////////////////
  // approvalNote //
	//////////////////


  /**
   *  The entity approvalNote
   *	 is defined as null before being initialized. 
   */
  @JsonProperty
  @JsonInclude(Include.NON_NULL)
  protected String approvalNote;

  /**
   * <br> The entity approvalNote
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.tenant.approval.TenantApproval&fq=entiteVar_enUS_indexed_string:approvalNote">Find the entity approvalNote in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _approvalNote(Wrap<String> w);

  public String getApprovalNote() {
    return approvalNote;
  }
  public void setApprovalNote(String o) {
    this.approvalNote = TenantApproval.staticSetApprovalNote(siteRequest_, o);
  }
  public static String staticSetApprovalNote(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected TenantApproval approvalNoteInit() {
    Wrap<String> approvalNoteWrap = new Wrap<String>().var("approvalNote");
    if(approvalNote == null) {
      _approvalNote(approvalNoteWrap);
      Optional.ofNullable(approvalNoteWrap.getO()).ifPresent(o -> {
        setApprovalNote(o);
      });
    }
    return (TenantApproval)this;
  }

  public static String staticSearchApprovalNote(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrApprovalNote(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqApprovalNote(SiteRequest siteRequest_, String o) {
    return TenantApproval.staticSearchApprovalNote(siteRequest_, TenantApproval.staticSetApprovalNote(siteRequest_, o)).toString();
  }

  public String sqlApprovalNote() {
    return approvalNote;
  }

  public static String staticJsonApprovalNote(String approvalNote) {
    return approvalNote;
  }

	//////////////////
  // approvalName //
	//////////////////


  /**
   *  The entity approvalName
   *	 is defined as null before being initialized. 
   */
  @JsonProperty
  @JsonInclude(Include.NON_NULL)
  protected String approvalName;

  /**
   * <br> The entity approvalName
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.tenant.approval.TenantApproval&fq=entiteVar_enUS_indexed_string:approvalName">Find the entity approvalName in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _approvalName(Wrap<String> w);

  public String getApprovalName() {
    return approvalName;
  }
  public void setApprovalName(String o) {
    this.approvalName = TenantApproval.staticSetApprovalName(siteRequest_, o);
  }
  public static String staticSetApprovalName(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected TenantApproval approvalNameInit() {
    Wrap<String> approvalNameWrap = new Wrap<String>().var("approvalName");
    if(approvalName == null) {
      _approvalName(approvalNameWrap);
      Optional.ofNullable(approvalNameWrap.getO()).ifPresent(o -> {
        setApprovalName(o);
      });
    }
    return (TenantApproval)this;
  }

  public static String staticSearchApprovalName(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrApprovalName(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqApprovalName(SiteRequest siteRequest_, String o) {
    return TenantApproval.staticSearchApprovalName(siteRequest_, TenantApproval.staticSetApprovalName(siteRequest_, o)).toString();
  }

  public String sqlApprovalName() {
    return approvalName;
  }

  public static String staticJsonApprovalName(String approvalName) {
    return approvalName;
  }

	////////////////
  // approvalId //
	////////////////


  /**
   *  The entity approvalId
   *	 is defined as null before being initialized. 
   */
  @JsonProperty
  @JsonInclude(Include.NON_NULL)
  protected String approvalId;

  /**
   * <br> The entity approvalId
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.tenant.approval.TenantApproval&fq=entiteVar_enUS_indexed_string:approvalId">Find the entity approvalId in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _approvalId(Wrap<String> w);

  public String getApprovalId() {
    return approvalId;
  }
  public void setApprovalId(String o) {
    this.approvalId = TenantApproval.staticSetApprovalId(siteRequest_, o);
  }
  public static String staticSetApprovalId(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected TenantApproval approvalIdInit() {
    Wrap<String> approvalIdWrap = new Wrap<String>().var("approvalId");
    if(approvalId == null) {
      _approvalId(approvalIdWrap);
      Optional.ofNullable(approvalIdWrap.getO()).ifPresent(o -> {
        setApprovalId(o);
      });
    }
    return (TenantApproval)this;
  }

  public static String staticSearchApprovalId(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrApprovalId(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqApprovalId(SiteRequest siteRequest_, String o) {
    return TenantApproval.staticSearchApprovalId(siteRequest_, TenantApproval.staticSetApprovalId(siteRequest_, o)).toString();
  }

  public String sqlApprovalId() {
    return approvalId;
  }

  public static String staticJsonApprovalId(String approvalId) {
    return approvalId;
  }

  //////////////
  // initDeep //
  //////////////

  public Future<TenantApprovalGen<DEV>> promiseDeepTenantApproval(SiteRequest siteRequest_) {
    if(this.siteRequest_ == null)
      setSiteRequest_(siteRequest_);
    return promiseDeepTenantApproval();
  }

  public Future<TenantApprovalGen<DEV>> promiseDeepTenantApproval() {
    Promise<TenantApprovalGen<DEV>> promise = Promise.promise();
    Promise<Void> promise2 = Promise.promise();
    promiseTenantApproval(promise2);
    promise2.future().onSuccess(a -> {
      super.promiseDeepBaseModel(siteRequest_).onSuccess(b -> {
        promise.complete(this);
      }).onFailure(ex -> {
        promise.fail(ex);
      });
    }).onFailure(ex -> {
      promise.fail(ex);
    });
    return promise.future();
  }

  public Future<Void> promiseTenantApproval(Promise<Void> promise) {
    Future.future(a -> a.complete()).compose(a -> {
      Promise<Void> promise2 = Promise.promise();
      try {
        tenantNameInit();
        tenantIdInit();
        requestedIdInit();
        tenantResourceInit();
        approvedByEmailInit();
        approvedByUserIdInit();
        approvedByFullNameInit();
        approvedInit();
        approvalNoteInit();
        approvalNameInit();
        approvalIdInit();
        promise2.complete();
      } catch(Exception ex) {
        promise2.fail(ex);
      }
      return promise2.future();
    }).onSuccess(a -> {
      promise.complete();
    }).onFailure(ex -> {
      promise.fail(ex);
    });
    return promise.future();
  }

  @Override public Future<? extends TenantApprovalGen<DEV>> promiseDeepForClass(SiteRequest siteRequest_) {
    return promiseDeepTenantApproval(siteRequest_);
  }

  /////////////////
  // siteRequest //
  /////////////////

  public void siteRequestTenantApproval(SiteRequest siteRequest_) {
      super.siteRequestBaseModel(siteRequest_);
  }

  public void siteRequestForClass(SiteRequest siteRequest_) {
    siteRequestTenantApproval(siteRequest_);
  }

  /////////////
  // obtain //
  /////////////

  @Override public Object obtainForClass(String var) {
    String[] vars = StringUtils.split(var, ".");
    Object o = null;
    for(String v : vars) {
      if(o == null)
        o = obtainTenantApproval(v);
      else if(o instanceof BaseModel) {
        BaseModel baseModel = (BaseModel)o;
        o = baseModel.obtainForClass(v);
      }
      else if(o instanceof Map) {
        Map<?, ?> map = (Map<?, ?>)o;
        o = map.get(v);
      }
    }
    return o;
  }
  public Object obtainTenantApproval(String var) {
    TenantApproval oTenantApproval = (TenantApproval)this;
    switch(var) {
      case "tenantName":
        return oTenantApproval.tenantName;
      case "tenantId":
        return oTenantApproval.tenantId;
      case "requestedId":
        return oTenantApproval.requestedId;
      case "tenantResource":
        return oTenantApproval.tenantResource;
      case "approvedByEmail":
        return oTenantApproval.approvedByEmail;
      case "approvedByUserId":
        return oTenantApproval.approvedByUserId;
      case "approvedByFullName":
        return oTenantApproval.approvedByFullName;
      case "approved":
        return oTenantApproval.approved;
      case "approvalNote":
        return oTenantApproval.approvalNote;
      case "approvalName":
        return oTenantApproval.approvalName;
      case "approvalId":
        return oTenantApproval.approvalId;
      default:
        return super.obtainBaseModel(var);
    }
  }

  ///////////////
  // relate //
  ///////////////

  @Override public boolean relateForClass(String var, Object val) {
    String[] vars = StringUtils.split(var, ".");
    Object o = null;
    for(String v : vars) {
      if(o == null)
        o = relateTenantApproval(v, val);
      else if(o instanceof BaseModel) {
        BaseModel baseModel = (BaseModel)o;
        o = baseModel.relateForClass(v, val);
      }
    }
    return o != null;
  }
  public Object relateTenantApproval(String var, Object val) {
    TenantApproval oTenantApproval = (TenantApproval)this;
    switch(var) {
      case "requestedId":
        if(oTenantApproval.getRequestedId() == null)
          oTenantApproval.setRequestedId(Optional.ofNullable(val).map(v -> v.toString()).orElse(null));
        if(!saves.contains("requestedId"))
          saves.add("requestedId");
        return val;
      case "tenantResource":
        if(oTenantApproval.getTenantResource() == null)
          oTenantApproval.setTenantResource(Optional.ofNullable(val).map(v -> v.toString()).orElse(null));
        if(!saves.contains("tenantResource"))
          saves.add("tenantResource");
        return val;
      default:
        return super.relateBaseModel(var, val);
    }
  }

  ///////////////
  // staticSet //
  ///////////////

  public static Object staticSetForClass(String entityVar, SiteRequest siteRequest_, String v, TenantApproval o) {
    return staticSetTenantApproval(entityVar,  siteRequest_, v, o);
  }
  public static Object staticSetTenantApproval(String entityVar, SiteRequest siteRequest_, String v, TenantApproval o) {
    switch(entityVar) {
    case "tenantName":
      return TenantApproval.staticSetTenantName(siteRequest_, v);
    case "tenantId":
      return TenantApproval.staticSetTenantId(siteRequest_, v);
    case "requestedId":
      return TenantApproval.staticSetRequestedId(siteRequest_, v);
    case "tenantResource":
      return TenantApproval.staticSetTenantResource(siteRequest_, v);
    case "approvedByEmail":
      return TenantApproval.staticSetApprovedByEmail(siteRequest_, v);
    case "approvedByUserId":
      return TenantApproval.staticSetApprovedByUserId(siteRequest_, v);
    case "approvedByFullName":
      return TenantApproval.staticSetApprovedByFullName(siteRequest_, v);
    case "approved":
      return TenantApproval.staticSetApproved(siteRequest_, v);
    case "approvalNote":
      return TenantApproval.staticSetApprovalNote(siteRequest_, v);
    case "approvalName":
      return TenantApproval.staticSetApprovalName(siteRequest_, v);
    case "approvalId":
      return TenantApproval.staticSetApprovalId(siteRequest_, v);
      default:
        return BaseModel.staticSetBaseModel(entityVar,  siteRequest_, v, o);
    }
  }

  //////////////////
  // staticSearch //
  //////////////////

  public static Future<TenantApproval> fqTenantApproval(SiteRequest siteRequest, String var, Object val) {
    Promise<TenantApproval> promise = Promise.promise();
    try {
      if(val == null) {
        promise.complete();
      } else {
        SearchList<TenantApproval> searchList = new SearchList<TenantApproval>();
        searchList.setStore(true);
        searchList.q("*:*");
        searchList.setC(TenantApproval.class);
        searchList.fq(String.format("%s:", TenantApproval.varIndexedTenantApproval(var)) + SearchTool.escapeQueryChars(val.toString()));
        searchList.promiseDeepForClass(siteRequest).onSuccess(a -> {
          try {
            promise.complete(searchList.getList().stream().findFirst().orElse(null));
          } catch(Throwable ex) {
            LOG.error("Error while querying the tenant approval", ex);
            promise.fail(ex);
          }
        }).onFailure(ex -> {
          LOG.error("Error while querying the tenant approval", ex);
          promise.fail(ex);
        });
      }
    } catch(Throwable ex) {
      LOG.error("Error while querying the tenant approval", ex);
      promise.fail(ex);
    }
    return promise.future();
  }

  public static Object staticSearchForClass(String entityVar, SiteRequest siteRequest_, Object o) {
    return staticSearchTenantApproval(entityVar,  siteRequest_, o);
  }
  public static Object staticSearchTenantApproval(String entityVar, SiteRequest siteRequest_, Object o) {
    switch(entityVar) {
    case "tenantName":
      return TenantApproval.staticSearchTenantName(siteRequest_, (String)o);
    case "tenantId":
      return TenantApproval.staticSearchTenantId(siteRequest_, (String)o);
    case "requestedId":
      return TenantApproval.staticSearchRequestedId(siteRequest_, (String)o);
    case "tenantResource":
      return TenantApproval.staticSearchTenantResource(siteRequest_, (String)o);
    case "approvedByEmail":
      return TenantApproval.staticSearchApprovedByEmail(siteRequest_, (String)o);
    case "approvedByUserId":
      return TenantApproval.staticSearchApprovedByUserId(siteRequest_, (String)o);
    case "approvedByFullName":
      return TenantApproval.staticSearchApprovedByFullName(siteRequest_, (String)o);
    case "approved":
      return TenantApproval.staticSearchApproved(siteRequest_, (Boolean)o);
    case "approvalNote":
      return TenantApproval.staticSearchApprovalNote(siteRequest_, (String)o);
    case "approvalName":
      return TenantApproval.staticSearchApprovalName(siteRequest_, (String)o);
    case "approvalId":
      return TenantApproval.staticSearchApprovalId(siteRequest_, (String)o);
      default:
        return BaseModel.staticSearchBaseModel(entityVar,  siteRequest_, o);
    }
  }

  ///////////////////
  // staticSearchStr //
  ///////////////////

  public static String staticSearchStrForClass(String entityVar, SiteRequest siteRequest_, Object o) {
    return staticSearchStrTenantApproval(entityVar,  siteRequest_, o);
  }
  public static String staticSearchStrTenantApproval(String entityVar, SiteRequest siteRequest_, Object o) {
    switch(entityVar) {
    case "tenantName":
      return TenantApproval.staticSearchStrTenantName(siteRequest_, (String)o);
    case "tenantId":
      return TenantApproval.staticSearchStrTenantId(siteRequest_, (String)o);
    case "requestedId":
      return TenantApproval.staticSearchStrRequestedId(siteRequest_, (String)o);
    case "tenantResource":
      return TenantApproval.staticSearchStrTenantResource(siteRequest_, (String)o);
    case "approvedByEmail":
      return TenantApproval.staticSearchStrApprovedByEmail(siteRequest_, (String)o);
    case "approvedByUserId":
      return TenantApproval.staticSearchStrApprovedByUserId(siteRequest_, (String)o);
    case "approvedByFullName":
      return TenantApproval.staticSearchStrApprovedByFullName(siteRequest_, (String)o);
    case "approved":
      return TenantApproval.staticSearchStrApproved(siteRequest_, (Boolean)o);
    case "approvalNote":
      return TenantApproval.staticSearchStrApprovalNote(siteRequest_, (String)o);
    case "approvalName":
      return TenantApproval.staticSearchStrApprovalName(siteRequest_, (String)o);
    case "approvalId":
      return TenantApproval.staticSearchStrApprovalId(siteRequest_, (String)o);
      default:
        return BaseModel.staticSearchStrBaseModel(entityVar,  siteRequest_, o);
    }
  }

  //////////////////
  // staticSearchFq //
  //////////////////

  public static String staticSearchFqForClass(String entityVar, SiteRequest siteRequest_, String o) {
    return staticSearchFqTenantApproval(entityVar,  siteRequest_, o);
  }
  public static String staticSearchFqTenantApproval(String entityVar, SiteRequest siteRequest_, String o) {
    switch(entityVar) {
    case "tenantName":
      return TenantApproval.staticSearchFqTenantName(siteRequest_, o);
    case "tenantId":
      return TenantApproval.staticSearchFqTenantId(siteRequest_, o);
    case "requestedId":
      return TenantApproval.staticSearchFqRequestedId(siteRequest_, o);
    case "tenantResource":
      return TenantApproval.staticSearchFqTenantResource(siteRequest_, o);
    case "approvedByEmail":
      return TenantApproval.staticSearchFqApprovedByEmail(siteRequest_, o);
    case "approvedByUserId":
      return TenantApproval.staticSearchFqApprovedByUserId(siteRequest_, o);
    case "approvedByFullName":
      return TenantApproval.staticSearchFqApprovedByFullName(siteRequest_, o);
    case "approved":
      return TenantApproval.staticSearchFqApproved(siteRequest_, o);
    case "approvalNote":
      return TenantApproval.staticSearchFqApprovalNote(siteRequest_, o);
    case "approvalName":
      return TenantApproval.staticSearchFqApprovalName(siteRequest_, o);
    case "approvalId":
      return TenantApproval.staticSearchFqApprovalId(siteRequest_, o);
      default:
        return BaseModel.staticSearchFqBaseModel(entityVar,  siteRequest_, o);
    }
  }

  /////////////
  // persist //
  /////////////

  @Override public boolean persistForClass(String var, Object val) {
    String[] vars = StringUtils.split(var, ".");
    Object o = null;
    if(val != null) {
      for(String v : vars) {
        if(o == null)
          o = persistTenantApproval(v, val);
        else if(o instanceof BaseModel) {
          BaseModel oBaseModel = (BaseModel)o;
          o = oBaseModel.persistForClass(v, val);
        }
      }
    }
    return o != null;
  }
  public Object persistTenantApproval(String var, Object val) {
    String varLower = var.toLowerCase();
      if("tenantname".equals(varLower)) {
        if(val instanceof String) {
          setTenantName((String)val);
        }
        saves.add("tenantName");
        return val;
      } else if("tenantid".equals(varLower)) {
        if(val instanceof String) {
          setTenantId((String)val);
        }
        saves.add("tenantId");
        return val;
      } else if("requestedid".equals(varLower)) {
        if(val instanceof String) {
          setRequestedId((String)val);
        }
        saves.add("requestedId");
        return val;
      } else if("tenantresource".equals(varLower)) {
        if(val instanceof String) {
          setTenantResource((String)val);
        }
        saves.add("tenantResource");
        return val;
      } else if("approvedbyemail".equals(varLower)) {
        if(val instanceof String) {
          setApprovedByEmail((String)val);
        }
        saves.add("approvedByEmail");
        return val;
      } else if("approvedbyuserid".equals(varLower)) {
        if(val instanceof String) {
          setApprovedByUserId((String)val);
        }
        saves.add("approvedByUserId");
        return val;
      } else if("approvedbyfullname".equals(varLower)) {
        if(val instanceof String) {
          setApprovedByFullName((String)val);
        }
        saves.add("approvedByFullName");
        return val;
      } else if("approved".equals(varLower)) {
        if(val instanceof Boolean) {
          setApproved((Boolean)val);
        } else {
          setApproved(val == null ? null : val.toString());
        }
        saves.add("approved");
        return val;
      } else if("approvalnote".equals(varLower)) {
        if(val instanceof String) {
          setApprovalNote((String)val);
        }
        saves.add("approvalNote");
        return val;
      } else if("approvalname".equals(varLower)) {
        if(val instanceof String) {
          setApprovalName((String)val);
        }
        saves.add("approvalName");
        return val;
      } else if("approvalid".equals(varLower)) {
        if(val instanceof String) {
          setApprovalId((String)val);
        }
        saves.add("approvalId");
        return val;
    } else {
      return super.persistBaseModel(var, val);
    }
  }

  /////////////
  // populate //
  /////////////

  @Override public void populateForClass(SolrResponse.Doc doc) {
    populateTenantApproval(doc);
  }
  public void populateTenantApproval(SolrResponse.Doc doc) {
    TenantApproval oTenantApproval = (TenantApproval)this;
    saves = Optional.ofNullable((ArrayList<String>)doc.get("saves_docvalues_strings")).orElse(new ArrayList<String>());
    if(saves != null) {

      if(saves.contains("tenantName")) {
        String tenantName = (String)doc.get("tenantName_docvalues_string");
        if(tenantName != null)
          oTenantApproval.setTenantName(tenantName);
      }

      if(saves.contains("tenantId")) {
        String tenantId = (String)doc.get("tenantId_docvalues_string");
        if(tenantId != null)
          oTenantApproval.setTenantId(tenantId);
      }

      String requestedId = (String)doc.get("requestedId_docvalues_string");
      if(requestedId != null)
        oTenantApproval.setRequestedId(requestedId);

      String tenantResource = (String)doc.get("tenantResource_docvalues_string");
      if(tenantResource != null)
        oTenantApproval.setTenantResource(tenantResource);

      if(saves.contains("approvedByEmail")) {
        String approvedByEmail = (String)doc.get("approvedByEmail_docvalues_string");
        if(approvedByEmail != null)
          oTenantApproval.setApprovedByEmail(approvedByEmail);
      }

      if(saves.contains("approvedByUserId")) {
        String approvedByUserId = (String)doc.get("approvedByUserId_docvalues_string");
        if(approvedByUserId != null)
          oTenantApproval.setApprovedByUserId(approvedByUserId);
      }

      if(saves.contains("approvedByFullName")) {
        String approvedByFullName = (String)doc.get("approvedByFullName_docvalues_string");
        if(approvedByFullName != null)
          oTenantApproval.setApprovedByFullName(approvedByFullName);
      }

      if(saves.contains("approved")) {
        Boolean approved = (Boolean)doc.get("approved_docvalues_boolean");
        if(approved != null)
          oTenantApproval.setApproved(approved);
      }

      if(saves.contains("approvalNote")) {
        String approvalNote = (String)doc.get("approvalNote_docvalues_string");
        if(approvalNote != null)
          oTenantApproval.setApprovalNote(approvalNote);
      }

      if(saves.contains("approvalName")) {
        String approvalName = (String)doc.get("approvalName_docvalues_string");
        if(approvalName != null)
          oTenantApproval.setApprovalName(approvalName);
      }

      if(saves.contains("approvalId")) {
        String approvalId = (String)doc.get("approvalId_docvalues_string");
        if(approvalId != null)
          oTenantApproval.setApprovalId(approvalId);
      }
    }

    super.populateBaseModel(doc);
  }

  public void indexTenantApproval(JsonObject doc) {
    if(tenantName != null) {
      doc.put("tenantName_docvalues_string", tenantName);
    }
    if(tenantId != null) {
      doc.put("tenantId_docvalues_string", tenantId);
    }
    if(requestedId != null) {
      doc.put("requestedId_docvalues_string", requestedId);
    }
    if(tenantResource != null) {
      doc.put("tenantResource_docvalues_string", tenantResource);
    }
    if(approvedByEmail != null) {
      doc.put("approvedByEmail_docvalues_string", approvedByEmail);
    }
    if(approvedByUserId != null) {
      doc.put("approvedByUserId_docvalues_string", approvedByUserId);
    }
    if(approvedByFullName != null) {
      doc.put("approvedByFullName_docvalues_string", approvedByFullName);
    }
    if(approved != null) {
      doc.put("approved_docvalues_boolean", approved);
    }
    if(approvalNote != null) {
      doc.put("approvalNote_docvalues_string", approvalNote);
    }
    if(approvalName != null) {
      doc.put("approvalName_docvalues_string", approvalName);
    }
    if(approvalId != null) {
      doc.put("approvalId_docvalues_string", approvalId);
    }
    super.indexBaseModel(doc);

	}

  public static String varStoredTenantApproval(String entityVar) {
    switch(entityVar) {
      case "tenantName":
        return "tenantName_docvalues_string";
      case "tenantId":
        return "tenantId_docvalues_string";
      case "requestedId":
        return "requestedId_docvalues_string";
      case "tenantResource":
        return "tenantResource_docvalues_string";
      case "approvedByEmail":
        return "approvedByEmail_docvalues_string";
      case "approvedByUserId":
        return "approvedByUserId_docvalues_string";
      case "approvedByFullName":
        return "approvedByFullName_docvalues_string";
      case "approved":
        return "approved_docvalues_boolean";
      case "approvalNote":
        return "approvalNote_docvalues_string";
      case "approvalName":
        return "approvalName_docvalues_string";
      case "approvalId":
        return "approvalId_docvalues_string";
      default:
        return BaseModel.varStoredBaseModel(entityVar);
    }
  }

  public static String varIndexedTenantApproval(String entityVar) {
    switch(entityVar) {
      case "tenantName":
        return "tenantName_docvalues_string";
      case "tenantId":
        return "tenantId_docvalues_string";
      case "requestedId":
        return "requestedId_docvalues_string";
      case "tenantResource":
        return "tenantResource_docvalues_string";
      case "approvedByEmail":
        return "approvedByEmail_docvalues_string";
      case "approvedByUserId":
        return "approvedByUserId_docvalues_string";
      case "approvedByFullName":
        return "approvedByFullName_docvalues_string";
      case "approved":
        return "approved_docvalues_boolean";
      case "approvalNote":
        return "approvalNote_docvalues_string";
      case "approvalName":
        return "approvalName_docvalues_string";
      case "approvalId":
        return "approvalId_docvalues_string";
      default:
        return BaseModel.varIndexedBaseModel(entityVar);
    }
  }

  public static String searchVarTenantApproval(String searchVar) {
    switch(searchVar) {
      case "tenantName_docvalues_string":
        return "tenantName";
      case "tenantId_docvalues_string":
        return "tenantId";
      case "requestedId_docvalues_string":
        return "requestedId";
      case "tenantResource_docvalues_string":
        return "tenantResource";
      case "approvedByEmail_docvalues_string":
        return "approvedByEmail";
      case "approvedByUserId_docvalues_string":
        return "approvedByUserId";
      case "approvedByFullName_docvalues_string":
        return "approvedByFullName";
      case "approved_docvalues_boolean":
        return "approved";
      case "approvalNote_docvalues_string":
        return "approvalNote";
      case "approvalName_docvalues_string":
        return "approvalName";
      case "approvalId_docvalues_string":
        return "approvalId";
      default:
        return BaseModel.searchVarBaseModel(searchVar);
    }
  }

  public static String varSearchTenantApproval(String entityVar) {
    switch(entityVar) {
      default:
        return BaseModel.varSearchBaseModel(entityVar);
    }
  }

  public static String varSuggestedTenantApproval(String entityVar) {
    switch(entityVar) {
      default:
        return BaseModel.varSuggestedBaseModel(entityVar);
    }
  }

  /////////////
  // store //
  /////////////

  @Override public void storeForClass(SolrResponse.Doc doc) {
    storeTenantApproval(doc);
  }
  public void storeTenantApproval(SolrResponse.Doc doc) {
    TenantApproval oTenantApproval = (TenantApproval)this;
    SiteRequest siteRequest = oTenantApproval.getSiteRequest_();

    oTenantApproval.setTenantName(Optional.ofNullable(doc.get("tenantName_docvalues_string")).map(v -> v.toString()).orElse(null));
    oTenantApproval.setTenantId(Optional.ofNullable(doc.get("tenantId_docvalues_string")).map(v -> v.toString()).orElse(null));
    oTenantApproval.setRequestedId(Optional.ofNullable(doc.get("requestedId_docvalues_string")).map(v -> v.toString()).orElse(null));
    oTenantApproval.setTenantResource(Optional.ofNullable(doc.get("tenantResource_docvalues_string")).map(v -> v.toString()).orElse(null));
    oTenantApproval.setApprovedByEmail(Optional.ofNullable(doc.get("approvedByEmail_docvalues_string")).map(v -> v.toString()).orElse(null));
    oTenantApproval.setApprovedByUserId(Optional.ofNullable(doc.get("approvedByUserId_docvalues_string")).map(v -> v.toString()).orElse(null));
    oTenantApproval.setApprovedByFullName(Optional.ofNullable(doc.get("approvedByFullName_docvalues_string")).map(v -> v.toString()).orElse(null));
    oTenantApproval.setApproved(Optional.ofNullable(doc.get("approved_docvalues_boolean")).map(v -> v.toString()).orElse(null));
    oTenantApproval.setApprovalNote(Optional.ofNullable(doc.get("approvalNote_docvalues_string")).map(v -> v.toString()).orElse(null));
    oTenantApproval.setApprovalName(Optional.ofNullable(doc.get("approvalName_docvalues_string")).map(v -> v.toString()).orElse(null));
    oTenantApproval.setApprovalId(Optional.ofNullable(doc.get("approvalId_docvalues_string")).map(v -> v.toString()).orElse(null));

    super.storeBaseModel(doc);
  }

  //////////////////
  // apiRequest //
  //////////////////

  public void apiRequestTenantApproval() {
    ApiRequest apiRequest = Optional.ofNullable(siteRequest_).map(r -> r.getApiRequest_()).orElse(null);
    Object o = Optional.ofNullable(apiRequest).map(ApiRequest::getOriginal).orElse(null);
    if(o != null && o instanceof TenantApproval) {
      TenantApproval original = (TenantApproval)o;
      if(!Objects.equals(tenantName, original.getTenantName()))
        apiRequest.addVars("tenantName");
      if(!Objects.equals(tenantId, original.getTenantId()))
        apiRequest.addVars("tenantId");
      if(!Objects.equals(requestedId, original.getRequestedId()))
        apiRequest.addVars("requestedId");
      if(!Objects.equals(tenantResource, original.getTenantResource()))
        apiRequest.addVars("tenantResource");
      if(!Objects.equals(approvedByEmail, original.getApprovedByEmail()))
        apiRequest.addVars("approvedByEmail");
      if(!Objects.equals(approvedByUserId, original.getApprovedByUserId()))
        apiRequest.addVars("approvedByUserId");
      if(!Objects.equals(approvedByFullName, original.getApprovedByFullName()))
        apiRequest.addVars("approvedByFullName");
      if(!Objects.equals(approved, original.getApproved()))
        apiRequest.addVars("approved");
      if(!Objects.equals(approvalNote, original.getApprovalNote()))
        apiRequest.addVars("approvalNote");
      if(!Objects.equals(approvalName, original.getApprovalName()))
        apiRequest.addVars("approvalName");
      if(!Objects.equals(approvalId, original.getApprovalId()))
        apiRequest.addVars("approvalId");
      super.apiRequestBaseModel();
    }
  }

  //////////////
  // toString //
  //////////////

  @Override public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(super.toString());
    sb.append(Optional.ofNullable(tenantName).map(v -> "tenantName: \"" + v + "\"\n" ).orElse(""));
    sb.append(Optional.ofNullable(tenantId).map(v -> "tenantId: \"" + v + "\"\n" ).orElse(""));
    sb.append(Optional.ofNullable(requestedId).map(v -> "requestedId: \"" + v + "\"\n" ).orElse(""));
    sb.append(Optional.ofNullable(tenantResource).map(v -> "tenantResource: \"" + v + "\"\n" ).orElse(""));
    sb.append(Optional.ofNullable(approvedByEmail).map(v -> "approvedByEmail: \"" + v + "\"\n" ).orElse(""));
    sb.append(Optional.ofNullable(approvedByUserId).map(v -> "approvedByUserId: \"" + v + "\"\n" ).orElse(""));
    sb.append(Optional.ofNullable(approvedByFullName).map(v -> "approvedByFullName: \"" + v + "\"\n" ).orElse(""));
    sb.append(Optional.ofNullable(approved).map(v -> "approved: " + v + "\n").orElse(""));
    sb.append(Optional.ofNullable(approvalNote).map(v -> "approvalNote: \"" + v + "\"\n" ).orElse(""));
    sb.append(Optional.ofNullable(approvalName).map(v -> "approvalName: \"" + v + "\"\n" ).orElse(""));
    sb.append(Optional.ofNullable(approvalId).map(v -> "approvalId: \"" + v + "\"\n" ).orElse(""));
    return sb.toString();
  }

  public static final String CLASS_SIMPLE_NAME = "TenantApproval";
  public static final String CLASS_CANONICAL_NAME = "org.computate.dcm.model.eda.tenant.approval.TenantApproval";
  public static final String CLASS_AUTH_RESOURCE = "TENANT";
  public static final String CLASS_API_ADDRESS_TenantApproval = "dcm-enUS-TenantApproval";
  public static String getClassApiAddress() {
    return CLASS_API_ADDRESS_TenantApproval;
  }
  public static final String VAR_tenantName = "tenantName";
  public static final String SET_tenantName = "setTenantName";
  public static final String VAR_tenantId = "tenantId";
  public static final String SET_tenantId = "setTenantId";
  public static final String VAR_requestedId = "requestedId";
  public static final String SET_requestedId = "setRequestedId";
  public static final String VAR_tenantResource = "tenantResource";
  public static final String SET_tenantResource = "setTenantResource";
  public static final String VAR_approvedByEmail = "approvedByEmail";
  public static final String SET_approvedByEmail = "setApprovedByEmail";
  public static final String VAR_approvedByUserId = "approvedByUserId";
  public static final String SET_approvedByUserId = "setApprovedByUserId";
  public static final String VAR_approvedByFullName = "approvedByFullName";
  public static final String SET_approvedByFullName = "setApprovedByFullName";
  public static final String VAR_approved = "approved";
  public static final String SET_approved = "setApproved";
  public static final String VAR_approvalNote = "approvalNote";
  public static final String SET_approvalNote = "setApprovalNote";
  public static final String VAR_approvalName = "approvalName";
  public static final String SET_approvalName = "setApprovalName";
  public static final String VAR_approvalId = "approvalId";
  public static final String SET_approvalId = "setApprovalId";

  public static List<String> varsQForClass() {
    return TenantApproval.varsQTenantApproval(new ArrayList<String>());
  }
  public static List<String> varsQTenantApproval(List<String> vars) {
    BaseModel.varsQBaseModel(vars);
    return vars;
  }

  public static List<String> varsFqForClass() {
    return TenantApproval.varsFqTenantApproval(new ArrayList<String>());
  }
  public static List<String> varsFqTenantApproval(List<String> vars) {
    vars.add(VAR_tenantName);
    vars.add(VAR_tenantId);
    vars.add(VAR_requestedId);
    vars.add(VAR_tenantResource);
    vars.add(VAR_approved);
    vars.add(VAR_approvalNote);
    vars.add(VAR_approvalName);
    vars.add(VAR_approvalId);
    BaseModel.varsFqBaseModel(vars);
    return vars;
  }

  public static List<String> varsRangeForClass() {
    return TenantApproval.varsRangeTenantApproval(new ArrayList<String>());
  }
  public static List<String> varsRangeTenantApproval(List<String> vars) {
    BaseModel.varsRangeBaseModel(vars);
    return vars;
  }

  public static final String DISPLAY_NAME_tenantName = "tenant name";
  public static final String DISPLAY_NAME_tenantId = "tenant ID";
  public static final String DISPLAY_NAME_requestedId = "tenant requested";
  public static final String DISPLAY_NAME_tenantResource = "tenant auth resource";
  public static final String DISPLAY_NAME_approvedByEmail = "approved by user email";
  public static final String DISPLAY_NAME_approvedByUserId = "approved by user ID";
  public static final String DISPLAY_NAME_approvedByFullName = "approved by user full name";
  public static final String DISPLAY_NAME_approved = "approved";
  public static final String DISPLAY_NAME_approvalNote = "approval note";
  public static final String DISPLAY_NAME_approvalName = "approval name";
  public static final String DISPLAY_NAME_approvalId = "approval ID";

  @Override
  public String idForClass() {
    return approvalId;
  }

  @Override
  public String titleForClass() {
    return objectTitle;
  }

  @Override
  public String nameForClass() {
    return approvalName;
  }

  @Override
  public String classNameAdjectiveSingularForClass() {
    return TenantApproval.NameAdjectiveSingular_enUS;
  }

  @Override
  public String descriptionForClass() {
    return approvalNote;
  }

  @Override
  public String enUSStringFormatUrlEditPageForClass() {
    return "%s/en-us/edit/approval/tenant/%s";
  }

  public static String varJson(String var, Boolean patch) {
    return TenantApproval.varJsonTenantApproval(var, patch);
  }
  public static String varJsonTenantApproval(String var, Boolean patch) {
    switch(var) {
    case VAR_tenantName:
      return patch ? SET_tenantName : VAR_tenantName;
    case VAR_tenantId:
      return patch ? SET_tenantId : VAR_tenantId;
    case VAR_requestedId:
      return patch ? SET_requestedId : VAR_requestedId;
    case VAR_tenantResource:
      return patch ? SET_tenantResource : VAR_tenantResource;
    case VAR_approvedByEmail:
      return patch ? SET_approvedByEmail : VAR_approvedByEmail;
    case VAR_approvedByUserId:
      return patch ? SET_approvedByUserId : VAR_approvedByUserId;
    case VAR_approvedByFullName:
      return patch ? SET_approvedByFullName : VAR_approvedByFullName;
    case VAR_approved:
      return patch ? SET_approved : VAR_approved;
    case VAR_approvalNote:
      return patch ? SET_approvalNote : VAR_approvalNote;
    case VAR_approvalName:
      return patch ? SET_approvalName : VAR_approvalName;
    case VAR_approvalId:
      return patch ? SET_approvalId : VAR_approvalId;
    default:
      return BaseModel.varJsonBaseModel(var, patch);
    }
  }

  public static String displayNameForClass(String var) {
    return TenantApproval.displayNameTenantApproval(var);
  }
  public static String displayNameTenantApproval(String var) {
    switch(var) {
    case VAR_tenantName:
      return DISPLAY_NAME_tenantName;
    case VAR_tenantId:
      return DISPLAY_NAME_tenantId;
    case VAR_requestedId:
      return DISPLAY_NAME_requestedId;
    case VAR_tenantResource:
      return DISPLAY_NAME_tenantResource;
    case VAR_approvedByEmail:
      return DISPLAY_NAME_approvedByEmail;
    case VAR_approvedByUserId:
      return DISPLAY_NAME_approvedByUserId;
    case VAR_approvedByFullName:
      return DISPLAY_NAME_approvedByFullName;
    case VAR_approved:
      return DISPLAY_NAME_approved;
    case VAR_approvalNote:
      return DISPLAY_NAME_approvalNote;
    case VAR_approvalName:
      return DISPLAY_NAME_approvalName;
    case VAR_approvalId:
      return DISPLAY_NAME_approvalId;
    default:
      return BaseModel.displayNameBaseModel(var);
    }
  }

  public static String descriptionTenantApproval(String var) {
    if(var == null)
      return null;
    switch(var) {
    case VAR_tenantName:
      return "The name of this tenant";
    case VAR_tenantId:
      return "The ID of this tenant. By default, this will be auto-generated based on the tenant name, converting non-alphanumeric characters to hyphens, all lowercase. ";
    case VAR_requestedId:
      return "The tenant requested being approved. ";
    case VAR_tenantResource:
      return "The unique authorization resource for the tenant for multi-tenancy";
    case VAR_approvedByEmail:
      return "The email address for the user who approved the change request. ";
    case VAR_approvedByUserId:
      return "The IdP UUID record for the user who approved the change request. ";
    case VAR_approvedByFullName:
      return "The first and last name for the user who approved the change request. ";
    case VAR_approved:
      return "Whether the requested change was approved by the approver. ";
    case VAR_approvalNote:
      return "A note from the approver about their decision about the requested change. ";
    case VAR_approvalName:
      return "The name of this approval";
    case VAR_approvalId:
      return "The ID of this approval. By default, this will be auto-generated based on the approval name, converting non-alphanumeric characters to hyphens, all lowercase. ";
      default:
        return BaseModel.descriptionBaseModel(var);
    }
  }

  public static String classSimpleNameTenantApproval(String var) {
    switch(var) {
    case VAR_tenantName:
      return "String";
    case VAR_tenantId:
      return "String";
    case VAR_requestedId:
      return "String";
    case VAR_tenantResource:
      return "String";
    case VAR_approvedByEmail:
      return "String";
    case VAR_approvedByUserId:
      return "String";
    case VAR_approvedByFullName:
      return "String";
    case VAR_approved:
      return "Boolean";
    case VAR_approvalNote:
      return "String";
    case VAR_approvalName:
      return "String";
    case VAR_approvalId:
      return "String";
      default:
        return BaseModel.classSimpleNameBaseModel(var);
    }
  }

  public static Integer htmColumnTenantApproval(String var) {
    switch(var) {
    case VAR_requestedId:
      return 0;
    case VAR_approvalNote:
      return 3;
    case VAR_approvalName:
      return 1;
      default:
        return BaseModel.htmColumnBaseModel(var);
    }
  }

  public static Integer htmRowTenantApproval(String var) {
    switch(var) {
    case VAR_requestedId:
      return 5;
    case VAR_approvedByEmail:
      return 10;
    case VAR_approvedByUserId:
      return 10;
    case VAR_approvedByFullName:
      return 10;
    case VAR_approved:
      return 11;
    case VAR_approvalNote:
      return 11;
      default:
        return BaseModel.htmRowBaseModel(var);
    }
  }

  public static Integer htmCellTenantApproval(String var) {
    switch(var) {
    case VAR_requestedId:
      return 0;
    case VAR_approvedByEmail:
      return 0;
    case VAR_approvedByUserId:
      return 0;
    case VAR_approvedByFullName:
      return 0;
    case VAR_approved:
      return 0;
    case VAR_approvalNote:
      return 0;
      default:
        return BaseModel.htmCellBaseModel(var);
    }
  }

  public static Integer lengthMinTenantApproval(String var) {
    switch(var) {
      default:
        return BaseModel.lengthMinBaseModel(var);
    }
  }

  public static Integer lengthMaxTenantApproval(String var) {
    switch(var) {
      default:
        return BaseModel.lengthMaxBaseModel(var);
    }
  }

  public static Integer maxTenantApproval(String var) {
    switch(var) {
      default:
        return BaseModel.maxBaseModel(var);
    }
  }

  public static Integer minTenantApproval(String var) {
    switch(var) {
      default:
        return BaseModel.minBaseModel(var);
    }
  }
}
