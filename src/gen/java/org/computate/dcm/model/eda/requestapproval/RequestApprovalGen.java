package org.computate.dcm.model.eda.requestapproval;

import java.time.format.DateTimeFormatter;
import java.util.List;
import org.computate.search.wrap.Wrap;
import org.computate.dcm.model.BaseModel;
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
 * <li>You can add a class comment "{@inheritDoc}" if you wish to inherit the helpful inherited class comments from class RequestApprovalGen into the class RequestApproval. 
 * </li><li><p>
 *   You can add a class comment <kbd><b>Rows: 100</b></kbd> if you wish for the request approval API to return more or less than 10 results by default. 
 *   In this case, the API will return 100 results from the API instead of 10 by default. 
 *   Each API has built in pagination of the search results to ensure a user can query all the data a page at a time without running the application out of memory. 
 * </p>
 * </li>
 * <h3>About the RequestApproval class and it's generated class RequestApprovalGen&lt;BaseModel&gt;: </h3>extends RequestApprovalGen
 * <p>
 * This Java class extends a generated Java class RequestApprovalGen built by the <a href="https://github.com/computate-org/computate">https://github.com/computate-org/computate</a> project. 
 * Whenever this Java class is modified or touched, the watch service installed as described in the README, indexes all the information about this Java class in a local Apache Solr Search Engine. 
 * If you are running the service, you can see the indexed data about this Java Class here: 
 * </p>
 * <p><a href="https://solr.apps-crc.testing/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.requestapproval.RequestApproval">Find the class RequestApproval in Solr. </a></p>
 * <p>
 * The extended class ending with "Gen" did not exist at first, but was automatically created by the same watch service based on the data retrieved from the local Apache Server search engine. 
 * The extended class contains many generated fields, getters, setters, initialization code, and helper methods to help build a website and API fast, reactive, and scalable. 
 * </p>
 * extends RequestApprovalGen<BaseModel>
 * <p>This <code>class RequestApproval extends RequestApprovalGen&lt;BaseModel&gt;</code>, which means it extends a newly generated RequestApprovalGen. 
 * The generated <code>class RequestApprovalGen extends BaseModel</code> which means that RequestApproval extends RequestApprovalGen which extends BaseModel. 
 * This generated inheritance is a powerful feature that allows a lot of boiler plate code to be created for you automatically while still preserving inheritance through the power of Java Generic classes. 
 * </p>
 * <h2>
 *   Api: true
 * </h2>
 * <p>
 *   This class contains a comment <kbd><b>Api: true</b></kbd>, which means this class will have Java Vert.x API backend code generated for these objects. 
 * </p>
 * <h2>ApiTag.enUS: true</h2>
 * <p>This class contains a comment <kbd><b>ApiTag: request approvals</b></kbd>, which groups all of the OpenAPIs for RequestApproval objects under the tag "request approvals". 
 * </p>
 * <h2>ApiUri.enUS: /en-us/api/approval</h2>
 * <p>This class contains a comment <kbd><b>ApiUri: /en-us/api/approval</b></kbd>, which defines the base API URI for RequestApproval objects as "/en-us/api/approval" in the OpenAPI spec. 
 * </p>
 * <h2>Color: null</h2>
 * <h2>Indexed: true</h2>
 * <p>This class contains a comment <kbd><b>Indexed: true</b></kbd>, which means this class will be indexed in the search engine. 
 * Every protected void method that begins with "_" that is marked to be searched with a comment like "Indexed: true", "Stored: true", or "DocValues: true" will be indexed in the search engine. 
 * </p>
 * <h2>{@inheritDoc}</h2>
 * <p>By adding a class comment "{@inheritDoc}", the RequestApproval class will inherit the helpful inherited class comments from the super class RequestApprovalGen. 
 * </p>
 * <h2>
 *   Rows: 10
 * </h2>
 * <p>This class contains a comment <kbd><b>Rows: 10</b></kbd>, which means the request approval API will return a default of 10 results instead of 10 by default. 
 * Each API has built in pagination of the search results to ensure a user can query all the data a page at a time without running the application out of memory. 
 * </p>
 * <p>
 *   You can add a class comment <kbd><b>Rows: 100</b></kbd> if you wish for the request approval API to return more or less than 10 results by default. 
 *   In this case, the API will return 100 results from the API instead of 10 by default. 
 *   Each API has built in pagination of the search results to ensure a user can query all the data a page at a time without running the application out of memory. 
 * </p>
 * <h2>
 *   Order: 900
 * </h2>
 * <p>
 *   This class contains a comment <kbd><b>Order: 900</b></kbd>, 
 *   which means this class will be sorted by the given number 900 
 *   ascending when code that relates to multiple classes at the same time is generated. 
 * </p>
 * <h2>SqlOrder: 900</h2>
 * <p>This class contains a comment <kbd><b>SqlOrder: 900</b></kbd>, which means this class will be sorted by the given number 900 ascending when SQL code to create and drop the tables is generated. 
 * </p>
 * <h2>Model: true</h2>
 * <p>This class contains a comment <kbd><b>Model: true</b></kbd>, which means this class will be stored in the database. 
 * Every protected void method that begins with "_" that contains a "Persist: true" comment will be a persisted field in the database table. 
 * </p>
 * <h2>Page: true</h2>
 * <p>This class contains a comment <kbd><b>Page: true</b></kbd>, which means this class will have webpage code generated for these objects. 
 * Java Vert.x backend API code, Handlebars HTML template frontend code, and JavaScript code will all generated and can be extended. 
 * This creates a new Java class org.computate.dcm.model.eda.requestapproval.RequestApprovalPage. 
 * </p>
 * <h2>SuperPage.enUS: PageLayout</h2>
 * <p>This class contains a comment <kbd><b>SuperPage.enUS: PageLayout</b></kbd>, which identifies the Java super class of the page code by it's class simple name "PageLayout". 
 * This means that the newly created class org.computate.dcm.model.eda.requestapproval.RequestApprovalPage extends org.computate.dcm.page.PageLayout. 
 * </p>
 * <h2>Promise: true</h2>
 * <p>
 *   This class contains a comment <kbd><b>Promise: true</b></kbd>
 *   Sometimes a Java class must be initialized asynchronously when it involves calling a blocking API. 
 *   This means that the RequestApproval Java class has promiseDeep methods which must be initialized asynchronously as a Vert.x Promise  instead of initDeep methods which are a simple non-asynchronous method. 
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
 * <h2>AName.enUS: a request approval</h2>
 * <p>This class contains a comment <kbd><b>AName.enUS: a request approval</b></kbd>, which identifies the language context to describe a RequestApproval as "a request approval". 
 * </p>
 * <p>
 * Delete the class RequestApproval in Solr: 
 * <pre>
 * curl -k 'https://solr.apps-crc.testing/solr/computate/update?commitWithin=1000&amp;overwrite=true&amp;wt=json' -X POST -H 'Content-type: text/xml' -u "admin:$(oc -n solr get secret/solr-solrcloud-security-bootstrap -o jsonpath={.data.admin} | base64 -d)" --data-raw '&lt;delete&gt;&lt;query&gt;classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.requestapproval.RequestApproval&lt;/query&gt;&lt;/delete&gt;'
 * </pre>
 * </p>
 * <p>
 * Delete  the package org.computate.dcm.model.eda.requestapproval in Solr: 
 * <pre>
 * curl -k 'https://solr.apps-crc.testing/solr/computate/update?commitWithin=1000&amp;overwrite=true&amp;wt=json' -X POST -H 'Content-type: text/xml' -u "admin:$(oc -n solr get secret/solr-solrcloud-security-bootstrap -o jsonpath={.data.admin} | base64 -d)" --data-raw '&lt;delete&gt;&lt;query&gt;classeNomEnsemble_enUS_indexed_string:org.computate.dcm.model.eda.requestapproval&lt;/query&gt;&lt;/delete&gt;'
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
public abstract class RequestApprovalGen<DEV> extends BaseModel {
  protected static final Logger LOG = LoggerFactory.getLogger(RequestApproval.class);

  public static final String Description_enUS = "Individual approvals per request and per approver. ";
  public static final String AName_enUS = "a request approval";
  public static final String This_enUS = "this ";
  public static final String ThisName_enUS = "this request approval";
  public static final String A_enUS = "a ";
  public static final String TheName_enUS = "the request approval";
  public static final String SingularName_enUS = "request approval";
  public static final String PluralName_enUS = "request approvals";
  public static final String NameActual_enUS = "current request approval";
  public static final String AllName_enUS = "all request approvals";
  public static final String SearchAllNameBy_enUS = "search request approvals by ";
  public static final String SearchAllName_enUS = "search request approvals";
  public static final String Title_enUS = "request approvals";
  public static final String ThePluralName_enUS = "the request approvals";
  public static final String NoNameFound_enUS = "no request approval found";
  public static final String ApiUri_enUS = "/en-us/api/approval";
  public static final String ApiUriSearchPage_enUS = "/en-us/search/approval";
  public static final String ApiUriEditPage_enUS = "/en-us/edit/approval/{approvalResource}";
  public static final String OfName_enUS = "of request approval";
  public static final String ANameAdjective_enUS = "a request approval";
  public static final String NameAdjectiveSingular_enUS = "request approval";
  public static final String NameAdjectivePlural_enUS = "request approvals";
  public static final String Search_enUS_OpenApiUri = "/en-us/api/approval";
  public static final String Search_enUS_StringFormatUri = "/en-us/api/approval";
  public static final String Search_enUS_StringFormatUrl = "%s/en-us/api/approval";
  public static final String GET_enUS_OpenApiUri = "/en-us/api/approval/{approvalId}";
  public static final String GET_enUS_StringFormatUri = "/en-us/api/approval/%s";
  public static final String GET_enUS_StringFormatUrl = "%s/en-us/api/approval/%s";
  public static final String PATCH_enUS_OpenApiUri = "/en-us/api/approval";
  public static final String PATCH_enUS_StringFormatUri = "/en-us/api/approval";
  public static final String PATCH_enUS_StringFormatUrl = "%s/en-us/api/approval";
  public static final String POST_enUS_OpenApiUri = "/en-us/api/approval";
  public static final String POST_enUS_StringFormatUri = "/en-us/api/approval";
  public static final String POST_enUS_StringFormatUrl = "%s/en-us/api/approval";
  public static final String DELETE_enUS_OpenApiUri = "/en-us/api/approval/{approvalId}";
  public static final String DELETE_enUS_StringFormatUri = "/en-us/api/approval/%s";
  public static final String DELETE_enUS_StringFormatUrl = "%s/en-us/api/approval/%s";
  public static final String PUTImport_enUS_OpenApiUri = "/en-us/api/approval-import";
  public static final String PUTImport_enUS_StringFormatUri = "/en-us/api/approval-import";
  public static final String PUTImport_enUS_StringFormatUrl = "%s/en-us/api/approval-import";
  public static final String SearchPage_enUS_OpenApiUri = "/en-us/search/approval";
  public static final String SearchPage_enUS_StringFormatUri = "/en-us/search/approval";
  public static final String SearchPage_enUS_StringFormatUrl = "%s/en-us/search/approval";
  public static final String EditPage_enUS_OpenApiUri = "/en-us/edit/approval/{approvalResource}";
  public static final String EditPage_enUS_StringFormatUri = "/en-us/edit/approval/%s";
  public static final String EditPage_enUS_StringFormatUrl = "%s/en-us/edit/approval/%s";
  public static final String DELETEFilter_enUS_OpenApiUri = "/en-us/api/approval";
  public static final String DELETEFilter_enUS_StringFormatUri = "/en-us/api/approval";
  public static final String DELETEFilter_enUS_StringFormatUrl = "%s/en-us/api/approval";

  public static final String Icon = "<i class=\"{{ FONTAWESOME_STYLE }} fa-thumbs-up\"></i>";

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
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.requestapproval.RequestApproval&fq=entiteVar_enUS_indexed_string:approvalName">Find the entity approvalName in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _approvalName(Wrap<String> w);

  public String getApprovalName() {
    return approvalName;
  }
  public void setApprovalName(String o) {
    this.approvalName = RequestApproval.staticSetApprovalName(siteRequest_, o);
  }
  public static String staticSetApprovalName(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected RequestApproval approvalNameInit() {
    Wrap<String> approvalNameWrap = new Wrap<String>().var("approvalName");
    if(approvalName == null) {
      _approvalName(approvalNameWrap);
      Optional.ofNullable(approvalNameWrap.getO()).ifPresent(o -> {
        setApprovalName(o);
      });
    }
    return (RequestApproval)this;
  }

  public static String staticSearchApprovalName(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrApprovalName(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqApprovalName(SiteRequest siteRequest_, String o) {
    return RequestApproval.staticSearchApprovalName(siteRequest_, RequestApproval.staticSetApprovalName(siteRequest_, o)).toString();
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
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.requestapproval.RequestApproval&fq=entiteVar_enUS_indexed_string:approvalId">Find the entity approvalId in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _approvalId(Wrap<String> w);

  public String getApprovalId() {
    return approvalId;
  }
  public void setApprovalId(String o) {
    this.approvalId = RequestApproval.staticSetApprovalId(siteRequest_, o);
  }
  public static String staticSetApprovalId(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected RequestApproval approvalIdInit() {
    Wrap<String> approvalIdWrap = new Wrap<String>().var("approvalId");
    if(approvalId == null) {
      _approvalId(approvalIdWrap);
      Optional.ofNullable(approvalIdWrap.getO()).ifPresent(o -> {
        setApprovalId(o);
      });
    }
    return (RequestApproval)this;
  }

  public static String staticSearchApprovalId(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrApprovalId(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqApprovalId(SiteRequest siteRequest_, String o) {
    return RequestApproval.staticSearchApprovalId(siteRequest_, RequestApproval.staticSetApprovalId(siteRequest_, o)).toString();
  }

  public String sqlApprovalId() {
    return approvalId;
  }

  public static String staticJsonApprovalId(String approvalId) {
    return approvalId;
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
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.requestapproval.RequestApproval&fq=entiteVar_enUS_indexed_string:approvedByEmail">Find the entity approvedByEmail in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _approvedByEmail(Wrap<String> w);

  public String getApprovedByEmail() {
    return approvedByEmail;
  }
  public void setApprovedByEmail(String o) {
    this.approvedByEmail = RequestApproval.staticSetApprovedByEmail(siteRequest_, o);
  }
  public static String staticSetApprovedByEmail(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected RequestApproval approvedByEmailInit() {
    Wrap<String> approvedByEmailWrap = new Wrap<String>().var("approvedByEmail");
    if(approvedByEmail == null) {
      _approvedByEmail(approvedByEmailWrap);
      Optional.ofNullable(approvedByEmailWrap.getO()).ifPresent(o -> {
        setApprovedByEmail(o);
      });
    }
    return (RequestApproval)this;
  }

  public static String staticSearchApprovedByEmail(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrApprovedByEmail(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqApprovedByEmail(SiteRequest siteRequest_, String o) {
    return RequestApproval.staticSearchApprovedByEmail(siteRequest_, RequestApproval.staticSetApprovedByEmail(siteRequest_, o)).toString();
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
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.requestapproval.RequestApproval&fq=entiteVar_enUS_indexed_string:approvedByUserId">Find the entity approvedByUserId in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _approvedByUserId(Wrap<String> w);

  public String getApprovedByUserId() {
    return approvedByUserId;
  }
  public void setApprovedByUserId(String o) {
    this.approvedByUserId = RequestApproval.staticSetApprovedByUserId(siteRequest_, o);
  }
  public static String staticSetApprovedByUserId(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected RequestApproval approvedByUserIdInit() {
    Wrap<String> approvedByUserIdWrap = new Wrap<String>().var("approvedByUserId");
    if(approvedByUserId == null) {
      _approvedByUserId(approvedByUserIdWrap);
      Optional.ofNullable(approvedByUserIdWrap.getO()).ifPresent(o -> {
        setApprovedByUserId(o);
      });
    }
    return (RequestApproval)this;
  }

  public static String staticSearchApprovedByUserId(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrApprovedByUserId(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqApprovedByUserId(SiteRequest siteRequest_, String o) {
    return RequestApproval.staticSearchApprovedByUserId(siteRequest_, RequestApproval.staticSetApprovedByUserId(siteRequest_, o)).toString();
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
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.requestapproval.RequestApproval&fq=entiteVar_enUS_indexed_string:approvedByFullName">Find the entity approvedByFullName in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _approvedByFullName(Wrap<String> w);

  public String getApprovedByFullName() {
    return approvedByFullName;
  }
  public void setApprovedByFullName(String o) {
    this.approvedByFullName = RequestApproval.staticSetApprovedByFullName(siteRequest_, o);
  }
  public static String staticSetApprovedByFullName(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected RequestApproval approvedByFullNameInit() {
    Wrap<String> approvedByFullNameWrap = new Wrap<String>().var("approvedByFullName");
    if(approvedByFullName == null) {
      _approvedByFullName(approvedByFullNameWrap);
      Optional.ofNullable(approvedByFullNameWrap.getO()).ifPresent(o -> {
        setApprovedByFullName(o);
      });
    }
    return (RequestApproval)this;
  }

  public static String staticSearchApprovedByFullName(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrApprovedByFullName(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqApprovedByFullName(SiteRequest siteRequest_, String o) {
    return RequestApproval.staticSearchApprovedByFullName(siteRequest_, RequestApproval.staticSetApprovedByFullName(siteRequest_, o)).toString();
  }

  public String sqlApprovedByFullName() {
    return approvedByFullName;
  }

  public static String staticJsonApprovedByFullName(String approvedByFullName) {
    return approvedByFullName;
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
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.requestapproval.RequestApproval&fq=entiteVar_enUS_indexed_string:approvalNote">Find the entity approvalNote in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _approvalNote(Wrap<String> w);

  public String getApprovalNote() {
    return approvalNote;
  }
  public void setApprovalNote(String o) {
    this.approvalNote = RequestApproval.staticSetApprovalNote(siteRequest_, o);
  }
  public static String staticSetApprovalNote(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected RequestApproval approvalNoteInit() {
    Wrap<String> approvalNoteWrap = new Wrap<String>().var("approvalNote");
    if(approvalNote == null) {
      _approvalNote(approvalNoteWrap);
      Optional.ofNullable(approvalNoteWrap.getO()).ifPresent(o -> {
        setApprovalNote(o);
      });
    }
    return (RequestApproval)this;
  }

  public static String staticSearchApprovalNote(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrApprovalNote(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqApprovalNote(SiteRequest siteRequest_, String o) {
    return RequestApproval.staticSearchApprovalNote(siteRequest_, RequestApproval.staticSetApprovalNote(siteRequest_, o)).toString();
  }

  public String sqlApprovalNote() {
    return approvalNote;
  }

  public static String staticJsonApprovalNote(String approvalNote) {
    return approvalNote;
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
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.requestapproval.RequestApproval&fq=entiteVar_enUS_indexed_string:approved">Find the entity approved in Solr</a>
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
    this.approved = RequestApproval.staticSetApproved(siteRequest_, o);
  }
  public static Boolean staticSetApproved(SiteRequest siteRequest_, String o) {
    return Boolean.parseBoolean(o);
  }
  protected RequestApproval approvedInit() {
    Wrap<Boolean> approvedWrap = new Wrap<Boolean>().var("approved");
    if(approved == null) {
      _approved(approvedWrap);
      Optional.ofNullable(approvedWrap.getO()).ifPresent(o -> {
        setApproved(o);
      });
    }
    return (RequestApproval)this;
  }

  public static Boolean staticSearchApproved(SiteRequest siteRequest_, Boolean o) {
    return o;
  }

  public static String staticSearchStrApproved(SiteRequest siteRequest_, Boolean o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqApproved(SiteRequest siteRequest_, String o) {
    return RequestApproval.staticSearchApproved(siteRequest_, RequestApproval.staticSetApproved(siteRequest_, o)).toString();
  }

  public Boolean sqlApproved() {
    return approved;
  }

  public static Boolean staticJsonApproved(Boolean approved) {
    return approved;
  }

	///////////////
  // modelType //
	///////////////


  /**
   *  The entity modelType
   *	 is defined as null before being initialized. 
   */
  @JsonProperty
  @JsonInclude(Include.NON_NULL)
  protected String modelType;

  /**
   * <br> The entity modelType
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.requestapproval.RequestApproval&fq=entiteVar_enUS_indexed_string:modelType">Find the entity modelType in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _modelType(Wrap<String> w);

  public String getModelType() {
    return modelType;
  }
  public void setModelType(String o) {
    this.modelType = RequestApproval.staticSetModelType(siteRequest_, o);
  }
  public static String staticSetModelType(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected RequestApproval modelTypeInit() {
    Wrap<String> modelTypeWrap = new Wrap<String>().var("modelType");
    if(modelType == null) {
      _modelType(modelTypeWrap);
      Optional.ofNullable(modelTypeWrap.getO()).ifPresent(o -> {
        setModelType(o);
      });
    }
    return (RequestApproval)this;
  }

  public static String staticSearchModelType(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrModelType(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqModelType(SiteRequest siteRequest_, String o) {
    return RequestApproval.staticSearchModelType(siteRequest_, RequestApproval.staticSetModelType(siteRequest_, o)).toString();
  }

  public String sqlModelType() {
    return modelType;
  }

  public static String staticJsonModelType(String modelType) {
    return modelType;
  }

	///////////////////
  // modelResource //
	///////////////////


  /**
   *  The entity modelResource
   *	 is defined as null before being initialized. 
   */
  @JsonProperty
  @JsonInclude(Include.NON_NULL)
  protected String modelResource;

  /**
   * <br> The entity modelResource
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.requestapproval.RequestApproval&fq=entiteVar_enUS_indexed_string:modelResource">Find the entity modelResource in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _modelResource(Wrap<String> w);

  public String getModelResource() {
    return modelResource;
  }
  public void setModelResource(String o) {
    this.modelResource = RequestApproval.staticSetModelResource(siteRequest_, o);
  }
  public static String staticSetModelResource(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected RequestApproval modelResourceInit() {
    Wrap<String> modelResourceWrap = new Wrap<String>().var("modelResource");
    if(modelResource == null) {
      _modelResource(modelResourceWrap);
      Optional.ofNullable(modelResourceWrap.getO()).ifPresent(o -> {
        setModelResource(o);
      });
    }
    return (RequestApproval)this;
  }

  public static String staticSearchModelResource(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrModelResource(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqModelResource(SiteRequest siteRequest_, String o) {
    return RequestApproval.staticSearchModelResource(siteRequest_, RequestApproval.staticSetModelResource(siteRequest_, o)).toString();
  }

  public String sqlModelResource() {
    return modelResource;
  }

  public static String staticJsonModelResource(String modelResource) {
    return modelResource;
  }

	///////////////////
  // approvalTitle //
	///////////////////


  /**
   *  The entity approvalTitle
   *	 is defined as null before being initialized. 
   */
  @JsonProperty
  @JsonInclude(Include.NON_NULL)
  protected String approvalTitle;

  /**
   * <br> The entity approvalTitle
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.requestapproval.RequestApproval&fq=entiteVar_enUS_indexed_string:approvalTitle">Find the entity approvalTitle in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _approvalTitle(Wrap<String> w);

  public String getApprovalTitle() {
    return approvalTitle;
  }
  public void setApprovalTitle(String o) {
    this.approvalTitle = RequestApproval.staticSetApprovalTitle(siteRequest_, o);
  }
  public static String staticSetApprovalTitle(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected RequestApproval approvalTitleInit() {
    Wrap<String> approvalTitleWrap = new Wrap<String>().var("approvalTitle");
    if(approvalTitle == null) {
      _approvalTitle(approvalTitleWrap);
      Optional.ofNullable(approvalTitleWrap.getO()).ifPresent(o -> {
        setApprovalTitle(o);
      });
    }
    return (RequestApproval)this;
  }

  public static String staticSearchApprovalTitle(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrApprovalTitle(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqApprovalTitle(SiteRequest siteRequest_, String o) {
    return RequestApproval.staticSearchApprovalTitle(siteRequest_, RequestApproval.staticSetApprovalTitle(siteRequest_, o)).toString();
  }

  public String sqlApprovalTitle() {
    return approvalTitle;
  }

  public static String staticJsonApprovalTitle(String approvalTitle) {
    return approvalTitle;
  }

  //////////////
  // initDeep //
  //////////////

  public Future<RequestApprovalGen<DEV>> promiseDeepRequestApproval(SiteRequest siteRequest_) {
    if(this.siteRequest_ == null)
      setSiteRequest_(siteRequest_);
    return promiseDeepRequestApproval();
  }

  public Future<RequestApprovalGen<DEV>> promiseDeepRequestApproval() {
    Promise<RequestApprovalGen<DEV>> promise = Promise.promise();
    Promise<Void> promise2 = Promise.promise();
    promiseRequestApproval(promise2);
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

  public Future<Void> promiseRequestApproval(Promise<Void> promise) {
    Future.future(a -> a.complete()).compose(a -> {
      Promise<Void> promise2 = Promise.promise();
      try {
        approvalNameInit();
        approvalIdInit();
        approvedByEmailInit();
        approvedByUserIdInit();
        approvedByFullNameInit();
        approvalNoteInit();
        approvedInit();
        modelTypeInit();
        modelResourceInit();
        approvalTitleInit();
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

  @Override public Future<? extends RequestApprovalGen<DEV>> promiseDeepForClass(SiteRequest siteRequest_) {
    return promiseDeepRequestApproval(siteRequest_);
  }

  /////////////////
  // siteRequest //
  /////////////////

  public void siteRequestRequestApproval(SiteRequest siteRequest_) {
      super.siteRequestBaseModel(siteRequest_);
  }

  public void siteRequestForClass(SiteRequest siteRequest_) {
    siteRequestRequestApproval(siteRequest_);
  }

  /////////////
  // obtain //
  /////////////

  @Override public Object obtainForClass(String var) {
    String[] vars = StringUtils.split(var, ".");
    Object o = null;
    for(String v : vars) {
      if(o == null)
        o = obtainRequestApproval(v);
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
  public Object obtainRequestApproval(String var) {
    RequestApproval oRequestApproval = (RequestApproval)this;
    switch(var) {
      case "approvalName":
        return oRequestApproval.approvalName;
      case "approvalId":
        return oRequestApproval.approvalId;
      case "approvedByEmail":
        return oRequestApproval.approvedByEmail;
      case "approvedByUserId":
        return oRequestApproval.approvedByUserId;
      case "approvedByFullName":
        return oRequestApproval.approvedByFullName;
      case "approvalNote":
        return oRequestApproval.approvalNote;
      case "approved":
        return oRequestApproval.approved;
      case "modelType":
        return oRequestApproval.modelType;
      case "modelResource":
        return oRequestApproval.modelResource;
      case "approvalTitle":
        return oRequestApproval.approvalTitle;
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
        o = relateRequestApproval(v, val);
      else if(o instanceof BaseModel) {
        BaseModel baseModel = (BaseModel)o;
        o = baseModel.relateForClass(v, val);
      }
    }
    return o != null;
  }
  public Object relateRequestApproval(String var, Object val) {
    RequestApproval oRequestApproval = (RequestApproval)this;
    switch(var) {
      default:
        return super.relateBaseModel(var, val);
    }
  }

  ///////////////
  // staticSet //
  ///////////////

  public static Object staticSetForClass(String entityVar, SiteRequest siteRequest_, String v, RequestApproval o) {
    return staticSetRequestApproval(entityVar,  siteRequest_, v, o);
  }
  public static Object staticSetRequestApproval(String entityVar, SiteRequest siteRequest_, String v, RequestApproval o) {
    switch(entityVar) {
    case "approvalName":
      return RequestApproval.staticSetApprovalName(siteRequest_, v);
    case "approvalId":
      return RequestApproval.staticSetApprovalId(siteRequest_, v);
    case "approvedByEmail":
      return RequestApproval.staticSetApprovedByEmail(siteRequest_, v);
    case "approvedByUserId":
      return RequestApproval.staticSetApprovedByUserId(siteRequest_, v);
    case "approvedByFullName":
      return RequestApproval.staticSetApprovedByFullName(siteRequest_, v);
    case "approvalNote":
      return RequestApproval.staticSetApprovalNote(siteRequest_, v);
    case "approved":
      return RequestApproval.staticSetApproved(siteRequest_, v);
    case "modelType":
      return RequestApproval.staticSetModelType(siteRequest_, v);
    case "modelResource":
      return RequestApproval.staticSetModelResource(siteRequest_, v);
    case "approvalTitle":
      return RequestApproval.staticSetApprovalTitle(siteRequest_, v);
      default:
        return BaseModel.staticSetBaseModel(entityVar,  siteRequest_, v, o);
    }
  }

  //////////////////
  // staticSearch //
  //////////////////

  public static Future<RequestApproval> fqRequestApproval(SiteRequest siteRequest, String var, Object val) {
    Promise<RequestApproval> promise = Promise.promise();
    try {
      if(val == null) {
        promise.complete();
      } else {
        SearchList<RequestApproval> searchList = new SearchList<RequestApproval>();
        searchList.setStore(true);
        searchList.q("*:*");
        searchList.setC(RequestApproval.class);
        searchList.fq(String.format("%s:", RequestApproval.varIndexedRequestApproval(var)) + SearchTool.escapeQueryChars(val.toString()));
        searchList.promiseDeepForClass(siteRequest).onSuccess(a -> {
          try {
            promise.complete(searchList.getList().stream().findFirst().orElse(null));
          } catch(Throwable ex) {
            LOG.error("Error while querying the request approval", ex);
            promise.fail(ex);
          }
        }).onFailure(ex -> {
          LOG.error("Error while querying the request approval", ex);
          promise.fail(ex);
        });
      }
    } catch(Throwable ex) {
      LOG.error("Error while querying the request approval", ex);
      promise.fail(ex);
    }
    return promise.future();
  }

  public static Object staticSearchForClass(String entityVar, SiteRequest siteRequest_, Object o) {
    return staticSearchRequestApproval(entityVar,  siteRequest_, o);
  }
  public static Object staticSearchRequestApproval(String entityVar, SiteRequest siteRequest_, Object o) {
    switch(entityVar) {
    case "approvalName":
      return RequestApproval.staticSearchApprovalName(siteRequest_, (String)o);
    case "approvalId":
      return RequestApproval.staticSearchApprovalId(siteRequest_, (String)o);
    case "approvedByEmail":
      return RequestApproval.staticSearchApprovedByEmail(siteRequest_, (String)o);
    case "approvedByUserId":
      return RequestApproval.staticSearchApprovedByUserId(siteRequest_, (String)o);
    case "approvedByFullName":
      return RequestApproval.staticSearchApprovedByFullName(siteRequest_, (String)o);
    case "approvalNote":
      return RequestApproval.staticSearchApprovalNote(siteRequest_, (String)o);
    case "approved":
      return RequestApproval.staticSearchApproved(siteRequest_, (Boolean)o);
    case "modelType":
      return RequestApproval.staticSearchModelType(siteRequest_, (String)o);
    case "modelResource":
      return RequestApproval.staticSearchModelResource(siteRequest_, (String)o);
    case "approvalTitle":
      return RequestApproval.staticSearchApprovalTitle(siteRequest_, (String)o);
      default:
        return BaseModel.staticSearchBaseModel(entityVar,  siteRequest_, o);
    }
  }

  ///////////////////
  // staticSearchStr //
  ///////////////////

  public static String staticSearchStrForClass(String entityVar, SiteRequest siteRequest_, Object o) {
    return staticSearchStrRequestApproval(entityVar,  siteRequest_, o);
  }
  public static String staticSearchStrRequestApproval(String entityVar, SiteRequest siteRequest_, Object o) {
    switch(entityVar) {
    case "approvalName":
      return RequestApproval.staticSearchStrApprovalName(siteRequest_, (String)o);
    case "approvalId":
      return RequestApproval.staticSearchStrApprovalId(siteRequest_, (String)o);
    case "approvedByEmail":
      return RequestApproval.staticSearchStrApprovedByEmail(siteRequest_, (String)o);
    case "approvedByUserId":
      return RequestApproval.staticSearchStrApprovedByUserId(siteRequest_, (String)o);
    case "approvedByFullName":
      return RequestApproval.staticSearchStrApprovedByFullName(siteRequest_, (String)o);
    case "approvalNote":
      return RequestApproval.staticSearchStrApprovalNote(siteRequest_, (String)o);
    case "approved":
      return RequestApproval.staticSearchStrApproved(siteRequest_, (Boolean)o);
    case "modelType":
      return RequestApproval.staticSearchStrModelType(siteRequest_, (String)o);
    case "modelResource":
      return RequestApproval.staticSearchStrModelResource(siteRequest_, (String)o);
    case "approvalTitle":
      return RequestApproval.staticSearchStrApprovalTitle(siteRequest_, (String)o);
      default:
        return BaseModel.staticSearchStrBaseModel(entityVar,  siteRequest_, o);
    }
  }

  //////////////////
  // staticSearchFq //
  //////////////////

  public static String staticSearchFqForClass(String entityVar, SiteRequest siteRequest_, String o) {
    return staticSearchFqRequestApproval(entityVar,  siteRequest_, o);
  }
  public static String staticSearchFqRequestApproval(String entityVar, SiteRequest siteRequest_, String o) {
    switch(entityVar) {
    case "approvalName":
      return RequestApproval.staticSearchFqApprovalName(siteRequest_, o);
    case "approvalId":
      return RequestApproval.staticSearchFqApprovalId(siteRequest_, o);
    case "approvedByEmail":
      return RequestApproval.staticSearchFqApprovedByEmail(siteRequest_, o);
    case "approvedByUserId":
      return RequestApproval.staticSearchFqApprovedByUserId(siteRequest_, o);
    case "approvedByFullName":
      return RequestApproval.staticSearchFqApprovedByFullName(siteRequest_, o);
    case "approvalNote":
      return RequestApproval.staticSearchFqApprovalNote(siteRequest_, o);
    case "approved":
      return RequestApproval.staticSearchFqApproved(siteRequest_, o);
    case "modelType":
      return RequestApproval.staticSearchFqModelType(siteRequest_, o);
    case "modelResource":
      return RequestApproval.staticSearchFqModelResource(siteRequest_, o);
    case "approvalTitle":
      return RequestApproval.staticSearchFqApprovalTitle(siteRequest_, o);
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
          o = persistRequestApproval(v, val);
        else if(o instanceof BaseModel) {
          BaseModel oBaseModel = (BaseModel)o;
          o = oBaseModel.persistForClass(v, val);
        }
      }
    }
    return o != null;
  }
  public Object persistRequestApproval(String var, Object val) {
    String varLower = var.toLowerCase();
      if("approvalname".equals(varLower)) {
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
      } else if("approvalnote".equals(varLower)) {
        if(val instanceof String) {
          setApprovalNote((String)val);
        }
        saves.add("approvalNote");
        return val;
      } else if("approved".equals(varLower)) {
        if(val instanceof Boolean) {
          setApproved((Boolean)val);
        } else {
          setApproved(val == null ? null : val.toString());
        }
        saves.add("approved");
        return val;
      } else if("modeltype".equals(varLower)) {
        if(val instanceof String) {
          setModelType((String)val);
        }
        saves.add("modelType");
        return val;
      } else if("modelresource".equals(varLower)) {
        if(val instanceof String) {
          setModelResource((String)val);
        }
        saves.add("modelResource");
        return val;
      } else if("approvaltitle".equals(varLower)) {
        if(val instanceof String) {
          setApprovalTitle((String)val);
        }
        saves.add("approvalTitle");
        return val;
    } else {
      return super.persistBaseModel(var, val);
    }
  }

  /////////////
  // populate //
  /////////////

  @Override public void populateForClass(SolrResponse.Doc doc) {
    populateRequestApproval(doc);
  }
  public void populateRequestApproval(SolrResponse.Doc doc) {
    RequestApproval oRequestApproval = (RequestApproval)this;
    saves = Optional.ofNullable((ArrayList<String>)doc.get("saves_docvalues_strings")).orElse(new ArrayList<String>());
    if(saves != null) {

      if(saves.contains("approvalName")) {
        String approvalName = (String)doc.get("approvalName_docvalues_string");
        if(approvalName != null)
          oRequestApproval.setApprovalName(approvalName);
      }

      if(saves.contains("approvalId")) {
        String approvalId = (String)doc.get("approvalId_docvalues_string");
        if(approvalId != null)
          oRequestApproval.setApprovalId(approvalId);
      }

      if(saves.contains("approvedByEmail")) {
        String approvedByEmail = (String)doc.get("approvedByEmail_docvalues_string");
        if(approvedByEmail != null)
          oRequestApproval.setApprovedByEmail(approvedByEmail);
      }

      if(saves.contains("approvedByUserId")) {
        String approvedByUserId = (String)doc.get("approvedByUserId_docvalues_string");
        if(approvedByUserId != null)
          oRequestApproval.setApprovedByUserId(approvedByUserId);
      }

      if(saves.contains("approvedByFullName")) {
        String approvedByFullName = (String)doc.get("approvedByFullName_docvalues_string");
        if(approvedByFullName != null)
          oRequestApproval.setApprovedByFullName(approvedByFullName);
      }

      if(saves.contains("approvalNote")) {
        String approvalNote = (String)doc.get("approvalNote_docvalues_string");
        if(approvalNote != null)
          oRequestApproval.setApprovalNote(approvalNote);
      }

      if(saves.contains("approved")) {
        Boolean approved = (Boolean)doc.get("approved_docvalues_boolean");
        if(approved != null)
          oRequestApproval.setApproved(approved);
      }

      if(saves.contains("modelType")) {
        String modelType = (String)doc.get("modelType_docvalues_string");
        if(modelType != null)
          oRequestApproval.setModelType(modelType);
      }

      if(saves.contains("modelResource")) {
        String modelResource = (String)doc.get("modelResource_docvalues_string");
        if(modelResource != null)
          oRequestApproval.setModelResource(modelResource);
      }

      if(saves.contains("approvalTitle")) {
        String approvalTitle = (String)doc.get("approvalTitle_docvalues_string");
        if(approvalTitle != null)
          oRequestApproval.setApprovalTitle(approvalTitle);
      }
    }

    super.populateBaseModel(doc);
  }

  public void indexRequestApproval(JsonObject doc) {
    if(approvalName != null) {
      doc.put("approvalName_docvalues_string", approvalName);
    }
    if(approvalId != null) {
      doc.put("approvalId_docvalues_string", approvalId);
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
    if(approvalNote != null) {
      doc.put("approvalNote_docvalues_string", approvalNote);
    }
    if(approved != null) {
      doc.put("approved_docvalues_boolean", approved);
    }
    if(modelType != null) {
      doc.put("modelType_docvalues_string", modelType);
    }
    if(modelResource != null) {
      doc.put("modelResource_docvalues_string", modelResource);
    }
    if(approvalTitle != null) {
      doc.put("approvalTitle_docvalues_string", approvalTitle);
    }
    super.indexBaseModel(doc);

	}

  public static String varStoredRequestApproval(String entityVar) {
    switch(entityVar) {
      case "approvalName":
        return "approvalName_docvalues_string";
      case "approvalId":
        return "approvalId_docvalues_string";
      case "approvedByEmail":
        return "approvedByEmail_docvalues_string";
      case "approvedByUserId":
        return "approvedByUserId_docvalues_string";
      case "approvedByFullName":
        return "approvedByFullName_docvalues_string";
      case "approvalNote":
        return "approvalNote_docvalues_string";
      case "approved":
        return "approved_docvalues_boolean";
      case "modelType":
        return "modelType_docvalues_string";
      case "modelResource":
        return "modelResource_docvalues_string";
      case "approvalTitle":
        return "approvalTitle_docvalues_string";
      default:
        return BaseModel.varStoredBaseModel(entityVar);
    }
  }

  public static String varIndexedRequestApproval(String entityVar) {
    switch(entityVar) {
      case "approvalName":
        return "approvalName_docvalues_string";
      case "approvalId":
        return "approvalId_docvalues_string";
      case "approvedByEmail":
        return "approvedByEmail_docvalues_string";
      case "approvedByUserId":
        return "approvedByUserId_docvalues_string";
      case "approvedByFullName":
        return "approvedByFullName_docvalues_string";
      case "approvalNote":
        return "approvalNote_docvalues_string";
      case "approved":
        return "approved_docvalues_boolean";
      case "modelType":
        return "modelType_docvalues_string";
      case "modelResource":
        return "modelResource_docvalues_string";
      case "approvalTitle":
        return "approvalTitle_docvalues_string";
      default:
        return BaseModel.varIndexedBaseModel(entityVar);
    }
  }

  public static String searchVarRequestApproval(String searchVar) {
    switch(searchVar) {
      case "approvalName_docvalues_string":
        return "approvalName";
      case "approvalId_docvalues_string":
        return "approvalId";
      case "approvedByEmail_docvalues_string":
        return "approvedByEmail";
      case "approvedByUserId_docvalues_string":
        return "approvedByUserId";
      case "approvedByFullName_docvalues_string":
        return "approvedByFullName";
      case "approvalNote_docvalues_string":
        return "approvalNote";
      case "approved_docvalues_boolean":
        return "approved";
      case "modelType_docvalues_string":
        return "modelType";
      case "modelResource_docvalues_string":
        return "modelResource";
      case "approvalTitle_docvalues_string":
        return "approvalTitle";
      default:
        return BaseModel.searchVarBaseModel(searchVar);
    }
  }

  public static String varSearchRequestApproval(String entityVar) {
    switch(entityVar) {
      default:
        return BaseModel.varSearchBaseModel(entityVar);
    }
  }

  public static String varSuggestedRequestApproval(String entityVar) {
    switch(entityVar) {
      default:
        return BaseModel.varSuggestedBaseModel(entityVar);
    }
  }

  /////////////
  // store //
  /////////////

  @Override public void storeForClass(SolrResponse.Doc doc) {
    storeRequestApproval(doc);
  }
  public void storeRequestApproval(SolrResponse.Doc doc) {
    RequestApproval oRequestApproval = (RequestApproval)this;
    SiteRequest siteRequest = oRequestApproval.getSiteRequest_();

    oRequestApproval.setApprovalName(Optional.ofNullable(doc.get("approvalName_docvalues_string")).map(v -> v.toString()).orElse(null));
    oRequestApproval.setApprovalId(Optional.ofNullable(doc.get("approvalId_docvalues_string")).map(v -> v.toString()).orElse(null));
    oRequestApproval.setApprovedByEmail(Optional.ofNullable(doc.get("approvedByEmail_docvalues_string")).map(v -> v.toString()).orElse(null));
    oRequestApproval.setApprovedByUserId(Optional.ofNullable(doc.get("approvedByUserId_docvalues_string")).map(v -> v.toString()).orElse(null));
    oRequestApproval.setApprovedByFullName(Optional.ofNullable(doc.get("approvedByFullName_docvalues_string")).map(v -> v.toString()).orElse(null));
    oRequestApproval.setApprovalNote(Optional.ofNullable(doc.get("approvalNote_docvalues_string")).map(v -> v.toString()).orElse(null));
    oRequestApproval.setApproved(Optional.ofNullable(doc.get("approved_docvalues_boolean")).map(v -> v.toString()).orElse(null));
    oRequestApproval.setModelType(Optional.ofNullable(doc.get("modelType_docvalues_string")).map(v -> v.toString()).orElse(null));
    oRequestApproval.setModelResource(Optional.ofNullable(doc.get("modelResource_docvalues_string")).map(v -> v.toString()).orElse(null));
    oRequestApproval.setApprovalTitle(Optional.ofNullable(doc.get("approvalTitle_docvalues_string")).map(v -> v.toString()).orElse(null));

    super.storeBaseModel(doc);
  }

  //////////////////
  // apiRequest //
  //////////////////

  public void apiRequestRequestApproval() {
    ApiRequest apiRequest = Optional.ofNullable(siteRequest_).map(r -> r.getApiRequest_()).orElse(null);
    Object o = Optional.ofNullable(apiRequest).map(ApiRequest::getOriginal).orElse(null);
    if(o != null && o instanceof RequestApproval) {
      RequestApproval original = (RequestApproval)o;
      if(!Objects.equals(approvalName, original.getApprovalName()))
        apiRequest.addVars("approvalName");
      if(!Objects.equals(approvalId, original.getApprovalId()))
        apiRequest.addVars("approvalId");
      if(!Objects.equals(approvedByEmail, original.getApprovedByEmail()))
        apiRequest.addVars("approvedByEmail");
      if(!Objects.equals(approvedByUserId, original.getApprovedByUserId()))
        apiRequest.addVars("approvedByUserId");
      if(!Objects.equals(approvedByFullName, original.getApprovedByFullName()))
        apiRequest.addVars("approvedByFullName");
      if(!Objects.equals(approvalNote, original.getApprovalNote()))
        apiRequest.addVars("approvalNote");
      if(!Objects.equals(approved, original.getApproved()))
        apiRequest.addVars("approved");
      if(!Objects.equals(modelType, original.getModelType()))
        apiRequest.addVars("modelType");
      if(!Objects.equals(modelResource, original.getModelResource()))
        apiRequest.addVars("modelResource");
      if(!Objects.equals(approvalTitle, original.getApprovalTitle()))
        apiRequest.addVars("approvalTitle");
      super.apiRequestBaseModel();
    }
  }

  //////////////
  // toString //
  //////////////

  @Override public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(super.toString());
    sb.append(Optional.ofNullable(approvalName).map(v -> "approvalName: \"" + v + "\"\n" ).orElse(""));
    sb.append(Optional.ofNullable(approvalId).map(v -> "approvalId: \"" + v + "\"\n" ).orElse(""));
    sb.append(Optional.ofNullable(approvedByEmail).map(v -> "approvedByEmail: \"" + v + "\"\n" ).orElse(""));
    sb.append(Optional.ofNullable(approvedByUserId).map(v -> "approvedByUserId: \"" + v + "\"\n" ).orElse(""));
    sb.append(Optional.ofNullable(approvedByFullName).map(v -> "approvedByFullName: \"" + v + "\"\n" ).orElse(""));
    sb.append(Optional.ofNullable(approvalNote).map(v -> "approvalNote: \"" + v + "\"\n" ).orElse(""));
    sb.append(Optional.ofNullable(approved).map(v -> "approved: " + v + "\n").orElse(""));
    sb.append(Optional.ofNullable(modelType).map(v -> "modelType: \"" + v + "\"\n" ).orElse(""));
    sb.append(Optional.ofNullable(modelResource).map(v -> "modelResource: \"" + v + "\"\n" ).orElse(""));
    sb.append(Optional.ofNullable(approvalTitle).map(v -> "approvalTitle: \"" + v + "\"\n" ).orElse(""));
    return sb.toString();
  }

  public static final String CLASS_SIMPLE_NAME = "RequestApproval";
  public static final String CLASS_CANONICAL_NAME = "org.computate.dcm.model.eda.requestapproval.RequestApproval";
  public static final String CLASS_AUTH_RESOURCE = "TENANT";
  public static final String CLASS_API_ADDRESS_RequestApproval = "dcm-enUS-RequestApproval";
  public static String getClassApiAddress() {
    return CLASS_API_ADDRESS_RequestApproval;
  }
  public static final String VAR_approvalName = "approvalName";
  public static final String SET_approvalName = "setApprovalName";
  public static final String VAR_approvalId = "approvalId";
  public static final String SET_approvalId = "setApprovalId";
  public static final String VAR_approvedByEmail = "approvedByEmail";
  public static final String SET_approvedByEmail = "setApprovedByEmail";
  public static final String VAR_approvedByUserId = "approvedByUserId";
  public static final String SET_approvedByUserId = "setApprovedByUserId";
  public static final String VAR_approvedByFullName = "approvedByFullName";
  public static final String SET_approvedByFullName = "setApprovedByFullName";
  public static final String VAR_approvalNote = "approvalNote";
  public static final String SET_approvalNote = "setApprovalNote";
  public static final String VAR_approved = "approved";
  public static final String SET_approved = "setApproved";
  public static final String VAR_modelType = "modelType";
  public static final String SET_modelType = "setModelType";
  public static final String VAR_modelResource = "modelResource";
  public static final String SET_modelResource = "setModelResource";
  public static final String VAR_approvalTitle = "approvalTitle";
  public static final String SET_approvalTitle = "setApprovalTitle";

  public static List<String> varsQForClass() {
    return RequestApproval.varsQRequestApproval(new ArrayList<String>());
  }
  public static List<String> varsQRequestApproval(List<String> vars) {
    BaseModel.varsQBaseModel(vars);
    return vars;
  }

  public static List<String> varsFqForClass() {
    return RequestApproval.varsFqRequestApproval(new ArrayList<String>());
  }
  public static List<String> varsFqRequestApproval(List<String> vars) {
    vars.add(VAR_approvalName);
    vars.add(VAR_approvalId);
    vars.add(VAR_approvalNote);
    vars.add(VAR_approved);
    vars.add(VAR_modelType);
    vars.add(VAR_modelResource);
    vars.add(VAR_approvalTitle);
    BaseModel.varsFqBaseModel(vars);
    return vars;
  }

  public static List<String> varsRangeForClass() {
    return RequestApproval.varsRangeRequestApproval(new ArrayList<String>());
  }
  public static List<String> varsRangeRequestApproval(List<String> vars) {
    BaseModel.varsRangeBaseModel(vars);
    return vars;
  }

  public static final String DISPLAY_NAME_approvalName = "approval name";
  public static final String DISPLAY_NAME_approvalId = "approval ID";
  public static final String DISPLAY_NAME_approvedByEmail = "approved by user email";
  public static final String DISPLAY_NAME_approvedByUserId = "approved by user ID";
  public static final String DISPLAY_NAME_approvedByFullName = "approved by user full name";
  public static final String DISPLAY_NAME_approvalNote = "approval note";
  public static final String DISPLAY_NAME_approved = "approved";
  public static final String DISPLAY_NAME_modelType = "model type";
  public static final String DISPLAY_NAME_modelResource = "model resource";
  public static final String DISPLAY_NAME_approvalTitle = "approval title";

  @Override
  public String idForClass() {
    return approvalId;
  }

  @Override
  public String titleForClass() {
    return approvalTitle;
  }

  @Override
  public String nameForClass() {
    return approvalName;
  }

  @Override
  public String classNameAdjectiveSingularForClass() {
    return RequestApproval.NameAdjectiveSingular_enUS;
  }

  @Override
  public String descriptionForClass() {
    return approvalNote;
  }

  @Override
  public String enUSStringFormatUrlEditPageForClass() {
    return "%s/en-us/edit/approval/%s";
  }

  public static String varJson(String var, Boolean patch) {
    return RequestApproval.varJsonRequestApproval(var, patch);
  }
  public static String varJsonRequestApproval(String var, Boolean patch) {
    switch(var) {
    case VAR_approvalName:
      return patch ? SET_approvalName : VAR_approvalName;
    case VAR_approvalId:
      return patch ? SET_approvalId : VAR_approvalId;
    case VAR_approvedByEmail:
      return patch ? SET_approvedByEmail : VAR_approvedByEmail;
    case VAR_approvedByUserId:
      return patch ? SET_approvedByUserId : VAR_approvedByUserId;
    case VAR_approvedByFullName:
      return patch ? SET_approvedByFullName : VAR_approvedByFullName;
    case VAR_approvalNote:
      return patch ? SET_approvalNote : VAR_approvalNote;
    case VAR_approved:
      return patch ? SET_approved : VAR_approved;
    case VAR_modelType:
      return patch ? SET_modelType : VAR_modelType;
    case VAR_modelResource:
      return patch ? SET_modelResource : VAR_modelResource;
    case VAR_approvalTitle:
      return patch ? SET_approvalTitle : VAR_approvalTitle;
    default:
      return BaseModel.varJsonBaseModel(var, patch);
    }
  }

  public static String displayNameForClass(String var) {
    return RequestApproval.displayNameRequestApproval(var);
  }
  public static String displayNameRequestApproval(String var) {
    switch(var) {
    case VAR_approvalName:
      return DISPLAY_NAME_approvalName;
    case VAR_approvalId:
      return DISPLAY_NAME_approvalId;
    case VAR_approvedByEmail:
      return DISPLAY_NAME_approvedByEmail;
    case VAR_approvedByUserId:
      return DISPLAY_NAME_approvedByUserId;
    case VAR_approvedByFullName:
      return DISPLAY_NAME_approvedByFullName;
    case VAR_approvalNote:
      return DISPLAY_NAME_approvalNote;
    case VAR_approved:
      return DISPLAY_NAME_approved;
    case VAR_modelType:
      return DISPLAY_NAME_modelType;
    case VAR_modelResource:
      return DISPLAY_NAME_modelResource;
    case VAR_approvalTitle:
      return DISPLAY_NAME_approvalTitle;
    default:
      return BaseModel.displayNameBaseModel(var);
    }
  }

  public static String descriptionRequestApproval(String var) {
    if(var == null)
      return null;
    switch(var) {
    case VAR_approvalName:
      return "The name of this approval";
    case VAR_approvalId:
      return "The ID of this approval. By default, this will be auto-generated based on the approval name, converting non-alphanumeric characters to hyphens, all lowercase. ";
    case VAR_approvedByEmail:
      return "The email address for the user who approved the change request. ";
    case VAR_approvedByUserId:
      return "The IdP UUID record for the user who approved the change request. ";
    case VAR_approvedByFullName:
      return "The first and last name for the user who approved the change request. ";
    case VAR_approvalNote:
      return "A note from the approver about their decision about the requested change. ";
    case VAR_approved:
      return "Whether the requested change was approved by the approver. ";
    case VAR_modelType:
      return "The Java Class simple name of this approval. ";
    case VAR_modelResource:
      return "The unique model resource of this approval. ";
    case VAR_approvalTitle:
      return "A brief title from the approver about their decision about the requested change. ";
      default:
        return BaseModel.descriptionBaseModel(var);
    }
  }

  public static String classSimpleNameRequestApproval(String var) {
    switch(var) {
    case VAR_approvalName:
      return "String";
    case VAR_approvalId:
      return "String";
    case VAR_approvedByEmail:
      return "String";
    case VAR_approvedByUserId:
      return "String";
    case VAR_approvedByFullName:
      return "String";
    case VAR_approvalNote:
      return "String";
    case VAR_approved:
      return "Boolean";
    case VAR_modelType:
      return "String";
    case VAR_modelResource:
      return "String";
    case VAR_approvalTitle:
      return "String";
      default:
        return BaseModel.classSimpleNameBaseModel(var);
    }
  }

  public static Integer htmColumnRequestApproval(String var) {
    switch(var) {
    case VAR_approvalName:
      return 1;
    case VAR_approvalNote:
      return 3;
      default:
        return BaseModel.htmColumnBaseModel(var);
    }
  }

  public static Integer htmRowRequestApproval(String var) {
    switch(var) {
    case VAR_approvalName:
      return 23;
    case VAR_approvedByEmail:
      return 10;
    case VAR_approvalNote:
      return 23;
      default:
        return BaseModel.htmRowBaseModel(var);
    }
  }

  public static Integer htmCellRequestApproval(String var) {
    switch(var) {
    case VAR_approvalName:
      return 1;
    case VAR_approvedByEmail:
      return 0;
    case VAR_approvalNote:
      return 4;
      default:
        return BaseModel.htmCellBaseModel(var);
    }
  }

  public static Integer lengthMinRequestApproval(String var) {
    switch(var) {
      default:
        return BaseModel.lengthMinBaseModel(var);
    }
  }

  public static Integer lengthMaxRequestApproval(String var) {
    switch(var) {
      default:
        return BaseModel.lengthMaxBaseModel(var);
    }
  }

  public static Integer maxRequestApproval(String var) {
    switch(var) {
      default:
        return BaseModel.maxBaseModel(var);
    }
  }

  public static Integer minRequestApproval(String var) {
    switch(var) {
      default:
        return BaseModel.minBaseModel(var);
    }
  }
}
