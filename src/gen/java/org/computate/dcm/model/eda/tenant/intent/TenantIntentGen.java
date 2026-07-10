package org.computate.dcm.model.eda.tenant.intent;

import java.util.List;
import org.computate.search.wrap.Wrap;
import org.computate.dcm.model.BaseModel;
import org.computate.dcm.model.eda.tenant.Tenant;
import org.computate.dcm.request.SiteRequest;
import org.computate.dcm.model.eda.tenant.Tenant;
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
import org.computate.dcm.model.eda.hostinventory.HostInventory;
import io.vertx.core.json.JsonArray;
import org.computate.dcm.model.eda.ansibleproject.AnsibleProject;
import org.computate.dcm.model.eda.tenant.requested.TenantRequested;
import java.lang.Boolean;
import org.computate.dcm.model.eda.tenant.discovered.TenantDiscovered;
import org.computate.dcm.model.eda.tenant.realized.TenantRealized;
import org.computate.search.wrap.Wrap;
import io.vertx.core.Promise;
import io.vertx.core.Future;
import org.computate.vertx.search.list.SearchList;
import org.computate.search.tool.SearchTool;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.computate.search.response.solr.SolrResponse;

/**
 * <ol>
<h3>Suggestions that can generate more code for you: </h3> * </ol>
 * <li>You can add a class comment "{@inheritDoc}" if you wish to inherit the helpful inherited class comments from class TenantIntentGen into the class TenantIntent. 
 * </li><li><p>
 *   You can add a class comment <kbd><b>Rows: 100</b></kbd> if you wish for the tenant intent API to return more or less than 10 results by default. 
 *   In this case, the API will return 100 results from the API instead of 10 by default. 
 *   Each API has built in pagination of the search results to ensure a user can query all the data a page at a time without running the application out of memory. 
 * </p>
 * </li>
 * <h3>About the TenantIntent class and it's generated class TenantIntentGen&lt;Tenant&gt;: </h3>extends TenantIntentGen
 * <p>
 * This Java class extends a generated Java class TenantIntentGen built by the <a href="https://github.com/computate-org/computate">https://github.com/computate-org/computate</a> project. 
 * Whenever this Java class is modified or touched, the watch service installed as described in the README, indexes all the information about this Java class in a local Apache Solr Search Engine. 
 * If you are running the service, you can see the indexed data about this Java Class here: 
 * </p>
 * <p><a href="https://solr.apps-crc.testing/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.tenant.intent.TenantIntent">Find the class TenantIntent in Solr. </a></p>
 * <p>
 * The extended class ending with "Gen" did not exist at first, but was automatically created by the same watch service based on the data retrieved from the local Apache Server search engine. 
 * The extended class contains many generated fields, getters, setters, initialization code, and helper methods to help build a website and API fast, reactive, and scalable. 
 * </p>
 * extends TenantIntentGen<Tenant>
 * <p>This <code>class TenantIntent extends TenantIntentGen&lt;Tenant&gt;</code>, which means it extends a newly generated TenantIntentGen. 
 * The generated <code>class TenantIntentGen extends Tenant</code> which means that TenantIntent extends TenantIntentGen which extends Tenant. 
 * This generated inheritance is a powerful feature that allows a lot of boiler plate code to be created for you automatically while still preserving inheritance through the power of Java Generic classes. 
 * </p>
 * <h2>
 *   Api: true
 * </h2>
 * <p>
 *   This class contains a comment <kbd><b>Api: true</b></kbd>, which means this class will have Java Vert.x API backend code generated for these objects. 
 * </p>
 * <h2>ApiTag.enUS: true</h2>
 * <p>This class contains a comment <kbd><b>ApiTag: tenant intents</b></kbd>, which groups all of the OpenAPIs for TenantIntent objects under the tag "tenant intents". 
 * </p>
 * <h2>ApiUri.enUS: /en-us/api/intent/tenant</h2>
 * <p>This class contains a comment <kbd><b>ApiUri: /en-us/api/intent/tenant</b></kbd>, which defines the base API URI for TenantIntent objects as "/en-us/api/intent/tenant" in the OpenAPI spec. 
 * </p>
 * <h2>Color: null</h2>
 * <h2>Indexed: true</h2>
 * <p>This class contains a comment <kbd><b>Indexed: true</b></kbd>, which means this class will be indexed in the search engine. 
 * Every protected void method that begins with "_" that is marked to be searched with a comment like "Indexed: true", "Stored: true", or "DocValues: true" will be indexed in the search engine. 
 * </p>
 * <h2>{@inheritDoc}</h2>
 * <p>By adding a class comment "{@inheritDoc}", the TenantIntent class will inherit the helpful inherited class comments from the super class TenantIntentGen. 
 * </p>
 * <h2>
 *   Rows: 10
 * </h2>
 * <p>This class contains a comment <kbd><b>Rows: 10</b></kbd>, which means the tenant intent API will return a default of 10 results instead of 10 by default. 
 * Each API has built in pagination of the search results to ensure a user can query all the data a page at a time without running the application out of memory. 
 * </p>
 * <p>
 *   You can add a class comment <kbd><b>Rows: 100</b></kbd> if you wish for the tenant intent API to return more or less than 10 results by default. 
 *   In this case, the API will return 100 results from the API instead of 10 by default. 
 *   Each API has built in pagination of the search results to ensure a user can query all the data a page at a time without running the application out of memory. 
 * </p>
 * <h2>
 *   Order: 140
 * </h2>
 * <p>
 *   This class contains a comment <kbd><b>Order: 140</b></kbd>, 
 *   which means this class will be sorted by the given number 140 
 *   ascending when code that relates to multiple classes at the same time is generated. 
 * </p>
 * <h2>SqlOrder: 140</h2>
 * <p>This class contains a comment <kbd><b>SqlOrder: 140</b></kbd>, which means this class will be sorted by the given number 140 ascending when SQL code to create and drop the tables is generated. 
 * </p>
 * <h2>Model: true</h2>
 * <p>This class contains a comment <kbd><b>Model: true</b></kbd>, which means this class will be stored in the database. 
 * Every protected void method that begins with "_" that contains a "Persist: true" comment will be a persisted field in the database table. 
 * </p>
 * <h2>Page: true</h2>
 * <p>This class contains a comment <kbd><b>Page: true</b></kbd>, which means this class will have webpage code generated for these objects. 
 * Java Vert.x backend API code, Handlebars HTML template frontend code, and JavaScript code will all generated and can be extended. 
 * This creates a new Java class org.computate.dcm.model.eda.tenant.intent.TenantIntentPage. 
 * </p>
 * <h2>SuperPage.enUS: PageLayout</h2>
 * <p>This class contains a comment <kbd><b>SuperPage.enUS: PageLayout</b></kbd>, which identifies the Java super class of the page code by it's class simple name "PageLayout". 
 * This means that the newly created class org.computate.dcm.model.eda.tenant.intent.TenantIntentPage extends org.computate.dcm.page.PageLayout. 
 * </p>
 * <h2>Promise: true</h2>
 * <p>
 *   This class contains a comment <kbd><b>Promise: true</b></kbd>
 *   Sometimes a Java class must be initialized asynchronously when it involves calling a blocking API. 
 *   This means that the TenantIntent Java class has promiseDeep methods which must be initialized asynchronously as a Vert.x Promise  instead of initDeep methods which are a simple non-asynchronous method. 
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
 * <h2>AName.enUS: a tenant intent</h2>
 * <p>This class contains a comment <kbd><b>AName.enUS: a tenant intent</b></kbd>, which identifies the language context to describe a TenantIntent as "a tenant intent". 
 * </p>
 * <p>
 * Delete the class TenantIntent in Solr: 
 * <pre>
 * curl -k 'https://solr.apps-crc.testing/solr/computate/update?commitWithin=1000&amp;overwrite=true&amp;wt=json' -X POST -H 'Content-type: text/xml' -u "admin:$(oc -n solr get secret/solr-solrcloud-security-bootstrap -o jsonpath={.data.admin} | base64 -d)" --data-raw '&lt;delete&gt;&lt;query&gt;classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.tenant.intent.TenantIntent&lt;/query&gt;&lt;/delete&gt;'
 * </pre>
 * </p>
 * <p>
 * Delete  the package org.computate.dcm.model.eda.tenant.intent in Solr: 
 * <pre>
 * curl -k 'https://solr.apps-crc.testing/solr/computate/update?commitWithin=1000&amp;overwrite=true&amp;wt=json' -X POST -H 'Content-type: text/xml' -u "admin:$(oc -n solr get secret/solr-solrcloud-security-bootstrap -o jsonpath={.data.admin} | base64 -d)" --data-raw '&lt;delete&gt;&lt;query&gt;classeNomEnsemble_enUS_indexed_string:org.computate.dcm.model.eda.tenant.intent&lt;/query&gt;&lt;/delete&gt;'
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
public abstract class TenantIntentGen<DEV> extends Tenant {
  protected static final Logger LOG = LoggerFactory.getLogger(TenantIntent.class);

  public static final String Description_enUS = "Describing the intent for a new Tenant. Tenants are separate organizations sharing the same cloud resources. ";
  public static final String AName_enUS = "a tenant intent";
  public static final String This_enUS = "this ";
  public static final String ThisName_enUS = "this tenant intent";
  public static final String A_enUS = "a ";
  public static final String TheName_enUS = "the tenant intent";
  public static final String SingularName_enUS = "tenant intent";
  public static final String PluralName_enUS = "tenant intents";
  public static final String NameActual_enUS = "current tenant intent";
  public static final String AllName_enUS = "all tenant intents";
  public static final String SearchAllNameBy_enUS = "search tenant intents by ";
  public static final String SearchAllName_enUS = "search tenant intents";
  public static final String Title_enUS = "tenant intents";
  public static final String ThePluralName_enUS = "the tenant intents";
  public static final String NoNameFound_enUS = "no tenant intent found";
  public static final String ApiUri_enUS = "/en-us/api/intent/tenant";
  public static final String ApiUriSearchPage_enUS = "/en-us/search/intent/tenant";
  public static final String ApiUriEditPage_enUS = "/en-us/edit/intent/tenant/{tenantResource}";
  public static final String OfName_enUS = "of tenant intent";
  public static final String ANameAdjective_enUS = "a tenant intent";
  public static final String NameAdjectiveSingular_enUS = "tenant intent";
  public static final String NameAdjectivePlural_enUS = "tenant intents";
  public static final String Search_enUS_OpenApiUri = "/en-us/api/intent/tenant";
  public static final String Search_enUS_StringFormatUri = "/en-us/api/intent/tenant";
  public static final String Search_enUS_StringFormatUrl = "%s/en-us/api/intent/tenant";
  public static final String GET_enUS_OpenApiUri = "/en-us/api/intent/tenant/{tenantResource}";
  public static final String GET_enUS_StringFormatUri = "/en-us/api/intent/tenant/%s";
  public static final String GET_enUS_StringFormatUrl = "%s/en-us/api/intent/tenant/%s";
  public static final String PATCH_enUS_OpenApiUri = "/en-us/api/intent/tenant";
  public static final String PATCH_enUS_StringFormatUri = "/en-us/api/intent/tenant";
  public static final String PATCH_enUS_StringFormatUrl = "%s/en-us/api/intent/tenant";
  public static final String POST_enUS_OpenApiUri = "/en-us/api/intent/tenant";
  public static final String POST_enUS_StringFormatUri = "/en-us/api/intent/tenant";
  public static final String POST_enUS_StringFormatUrl = "%s/en-us/api/intent/tenant";
  public static final String DELETE_enUS_OpenApiUri = "/en-us/api/intent/tenant/{tenantResource}";
  public static final String DELETE_enUS_StringFormatUri = "/en-us/api/intent/tenant/%s";
  public static final String DELETE_enUS_StringFormatUrl = "%s/en-us/api/intent/tenant/%s";
  public static final String PUTImport_enUS_OpenApiUri = "/en-us/api/intent/tenant-import";
  public static final String PUTImport_enUS_StringFormatUri = "/en-us/api/intent/tenant-import";
  public static final String PUTImport_enUS_StringFormatUrl = "%s/en-us/api/intent/tenant-import";
  public static final String SearchPage_enUS_OpenApiUri = "/en-us/search/intent/tenant";
  public static final String SearchPage_enUS_StringFormatUri = "/en-us/search/intent/tenant";
  public static final String SearchPage_enUS_StringFormatUrl = "%s/en-us/search/intent/tenant";
  public static final String EditPage_enUS_OpenApiUri = "/en-us/edit/intent/tenant/{tenantResource}";
  public static final String EditPage_enUS_StringFormatUri = "/en-us/edit/intent/tenant/%s";
  public static final String EditPage_enUS_StringFormatUrl = "%s/en-us/edit/intent/tenant/%s";
  public static final String DELETEFilter_enUS_OpenApiUri = "/en-us/api/intent/tenant";
  public static final String DELETEFilter_enUS_StringFormatUri = "/en-us/api/intent/tenant";
  public static final String DELETEFilter_enUS_StringFormatUrl = "%s/en-us/api/intent/tenant";

  public static final String Icon = "<i class=\"{{ FONTAWESOME_STYLE }} fa-buildings\"></i>";

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
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.tenant.intent.TenantIntent&fq=entiteVar_enUS_indexed_string:tenantName">Find the entity tenantName in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _tenantName(Wrap<String> w);

  public String getTenantName() {
    return tenantName;
  }
  public void setTenantName(String o) {
    this.tenantName = TenantIntent.staticSetTenantName(siteRequest_, o);
  }
  public static String staticSetTenantName(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected TenantIntent tenantNameInit() {
    Wrap<String> tenantNameWrap = new Wrap<String>().var("tenantName");
    if(tenantName == null) {
      _tenantName(tenantNameWrap);
      Optional.ofNullable(tenantNameWrap.getO()).ifPresent(o -> {
        setTenantName(o);
      });
    }
    return (TenantIntent)this;
  }

  public static String staticSearchTenantName(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrTenantName(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqTenantName(SiteRequest siteRequest_, String o) {
    return TenantIntent.staticSearchTenantName(siteRequest_, TenantIntent.staticSetTenantName(siteRequest_, o)).toString();
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
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.tenant.intent.TenantIntent&fq=entiteVar_enUS_indexed_string:tenantId">Find the entity tenantId in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _tenantId(Wrap<String> w);

  public String getTenantId() {
    return tenantId;
  }
  public void setTenantId(String o) {
    this.tenantId = TenantIntent.staticSetTenantId(siteRequest_, o);
  }
  public static String staticSetTenantId(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected TenantIntent tenantIdInit() {
    Wrap<String> tenantIdWrap = new Wrap<String>().var("tenantId");
    if(tenantId == null) {
      _tenantId(tenantIdWrap);
      Optional.ofNullable(tenantIdWrap.getO()).ifPresent(o -> {
        setTenantId(o);
      });
    }
    return (TenantIntent)this;
  }

  public static String staticSearchTenantId(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrTenantId(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqTenantId(SiteRequest siteRequest_, String o) {
    return TenantIntent.staticSearchTenantId(siteRequest_, TenantIntent.staticSetTenantId(siteRequest_, o)).toString();
  }

  public String sqlTenantId() {
    return tenantId;
  }

  public static String staticJsonTenantId(String tenantId) {
    return tenantId;
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
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.tenant.intent.TenantIntent&fq=entiteVar_enUS_indexed_string:tenantResource">Find the entity tenantResource in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _tenantResource(Wrap<String> w);

  public String getTenantResource() {
    return tenantResource;
  }
  public void setTenantResource(String o) {
    this.tenantResource = TenantIntent.staticSetTenantResource(siteRequest_, o);
  }
  public static String staticSetTenantResource(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected TenantIntent tenantResourceInit() {
    Wrap<String> tenantResourceWrap = new Wrap<String>().var("tenantResource");
    if(tenantResource == null) {
      _tenantResource(tenantResourceWrap);
      Optional.ofNullable(tenantResourceWrap.getO()).ifPresent(o -> {
        setTenantResource(o);
      });
    }
    return (TenantIntent)this;
  }

  public static String staticSearchTenantResource(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrTenantResource(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqTenantResource(SiteRequest siteRequest_, String o) {
    return TenantIntent.staticSearchTenantResource(siteRequest_, TenantIntent.staticSetTenantResource(siteRequest_, o)).toString();
  }

  public String sqlTenantResource() {
    return tenantResource;
  }

  public static String staticJsonTenantResource(String tenantResource) {
    return tenantResource;
  }

	////////////////////
  // createdByEmail //
	////////////////////


  /**
   *  The entity createdByEmail
   *	 is defined as null before being initialized. 
   */
  @JsonProperty
  @JsonInclude(Include.NON_NULL)
  protected String createdByEmail;

  /**
   * <br> The entity createdByEmail
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.tenant.intent.TenantIntent&fq=entiteVar_enUS_indexed_string:createdByEmail">Find the entity createdByEmail in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _createdByEmail(Wrap<String> w);

  public String getCreatedByEmail() {
    return createdByEmail;
  }
  public void setCreatedByEmail(String o) {
    this.createdByEmail = TenantIntent.staticSetCreatedByEmail(siteRequest_, o);
  }
  public static String staticSetCreatedByEmail(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected TenantIntent createdByEmailInit() {
    Wrap<String> createdByEmailWrap = new Wrap<String>().var("createdByEmail");
    if(createdByEmail == null) {
      _createdByEmail(createdByEmailWrap);
      Optional.ofNullable(createdByEmailWrap.getO()).ifPresent(o -> {
        setCreatedByEmail(o);
      });
    }
    return (TenantIntent)this;
  }

  public static String staticSearchCreatedByEmail(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrCreatedByEmail(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqCreatedByEmail(SiteRequest siteRequest_, String o) {
    return TenantIntent.staticSearchCreatedByEmail(siteRequest_, TenantIntent.staticSetCreatedByEmail(siteRequest_, o)).toString();
  }

  public String sqlCreatedByEmail() {
    return createdByEmail;
  }

  public static String staticJsonCreatedByEmail(String createdByEmail) {
    return createdByEmail;
  }

	/////////////////////
  // createdByUserId //
	/////////////////////


  /**
   *  The entity createdByUserId
   *	 is defined as null before being initialized. 
   */
  @JsonProperty
  @JsonInclude(Include.NON_NULL)
  protected String createdByUserId;

  /**
   * <br> The entity createdByUserId
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.tenant.intent.TenantIntent&fq=entiteVar_enUS_indexed_string:createdByUserId">Find the entity createdByUserId in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _createdByUserId(Wrap<String> w);

  public String getCreatedByUserId() {
    return createdByUserId;
  }
  public void setCreatedByUserId(String o) {
    this.createdByUserId = TenantIntent.staticSetCreatedByUserId(siteRequest_, o);
  }
  public static String staticSetCreatedByUserId(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected TenantIntent createdByUserIdInit() {
    Wrap<String> createdByUserIdWrap = new Wrap<String>().var("createdByUserId");
    if(createdByUserId == null) {
      _createdByUserId(createdByUserIdWrap);
      Optional.ofNullable(createdByUserIdWrap.getO()).ifPresent(o -> {
        setCreatedByUserId(o);
      });
    }
    return (TenantIntent)this;
  }

  public static String staticSearchCreatedByUserId(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrCreatedByUserId(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqCreatedByUserId(SiteRequest siteRequest_, String o) {
    return TenantIntent.staticSearchCreatedByUserId(siteRequest_, TenantIntent.staticSetCreatedByUserId(siteRequest_, o)).toString();
  }

  public String sqlCreatedByUserId() {
    return createdByUserId;
  }

  public static String staticJsonCreatedByUserId(String createdByUserId) {
    return createdByUserId;
  }

	///////////////////////
  // createdByFullName //
	///////////////////////


  /**
   *  The entity createdByFullName
   *	 is defined as null before being initialized. 
   */
  @JsonProperty
  @JsonInclude(Include.NON_NULL)
  protected String createdByFullName;

  /**
   * <br> The entity createdByFullName
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.tenant.intent.TenantIntent&fq=entiteVar_enUS_indexed_string:createdByFullName">Find the entity createdByFullName in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _createdByFullName(Wrap<String> w);

  public String getCreatedByFullName() {
    return createdByFullName;
  }
  public void setCreatedByFullName(String o) {
    this.createdByFullName = TenantIntent.staticSetCreatedByFullName(siteRequest_, o);
  }
  public static String staticSetCreatedByFullName(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected TenantIntent createdByFullNameInit() {
    Wrap<String> createdByFullNameWrap = new Wrap<String>().var("createdByFullName");
    if(createdByFullName == null) {
      _createdByFullName(createdByFullNameWrap);
      Optional.ofNullable(createdByFullNameWrap.getO()).ifPresent(o -> {
        setCreatedByFullName(o);
      });
    }
    return (TenantIntent)this;
  }

  public static String staticSearchCreatedByFullName(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrCreatedByFullName(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqCreatedByFullName(SiteRequest siteRequest_, String o) {
    return TenantIntent.staticSearchCreatedByFullName(siteRequest_, TenantIntent.staticSetCreatedByFullName(siteRequest_, o)).toString();
  }

  public String sqlCreatedByFullName() {
    return createdByFullName;
  }

  public static String staticJsonCreatedByFullName(String createdByFullName) {
    return createdByFullName;
  }

	////////////////
  // createdVia //
	////////////////


  /**
   *  The entity createdVia
   *	 is defined as null before being initialized. 
   */
  @JsonProperty
  @JsonInclude(Include.NON_NULL)
  protected String createdVia;

  /**
   * <br> The entity createdVia
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.tenant.intent.TenantIntent&fq=entiteVar_enUS_indexed_string:createdVia">Find the entity createdVia in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _createdVia(Wrap<String> w);

  public String getCreatedVia() {
    return createdVia;
  }
  public void setCreatedVia(String o) {
    this.createdVia = TenantIntent.staticSetCreatedVia(siteRequest_, o);
  }
  public static String staticSetCreatedVia(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected TenantIntent createdViaInit() {
    Wrap<String> createdViaWrap = new Wrap<String>().var("createdVia");
    if(createdVia == null) {
      _createdVia(createdViaWrap);
      Optional.ofNullable(createdViaWrap.getO()).ifPresent(o -> {
        setCreatedVia(o);
      });
    }
    return (TenantIntent)this;
  }

  public static String staticSearchCreatedVia(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrCreatedVia(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqCreatedVia(SiteRequest siteRequest_, String o) {
    return TenantIntent.staticSearchCreatedVia(siteRequest_, TenantIntent.staticSetCreatedVia(siteRequest_, o)).toString();
  }

  public String sqlCreatedVia() {
    return createdVia;
  }

  public static String staticJsonCreatedVia(String createdVia) {
    return createdVia;
  }

	/////////////////
  // intentState //
	/////////////////


  /**
   *  The entity intentState
   *	 is defined as null before being initialized. 
   */
  @JsonProperty
  @JsonInclude(Include.NON_NULL)
  protected String intentState;

  /**
   * <br> The entity intentState
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.tenant.intent.TenantIntent&fq=entiteVar_enUS_indexed_string:intentState">Find the entity intentState in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _intentState(Wrap<String> w);

  public String getIntentState() {
    return intentState;
  }
  public void setIntentState(String o) {
    this.intentState = TenantIntent.staticSetIntentState(siteRequest_, o);
  }
  public static String staticSetIntentState(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected TenantIntent intentStateInit() {
    Wrap<String> intentStateWrap = new Wrap<String>().var("intentState");
    if(intentState == null) {
      _intentState(intentStateWrap);
      Optional.ofNullable(intentStateWrap.getO()).ifPresent(o -> {
        setIntentState(o);
      });
    }
    return (TenantIntent)this;
  }

  public static String staticSearchIntentState(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrIntentState(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqIntentState(SiteRequest siteRequest_, String o) {
    return TenantIntent.staticSearchIntentState(siteRequest_, TenantIntent.staticSetIntentState(siteRequest_, o)).toString();
  }

  public String sqlIntentState() {
    return intentState;
  }

  public static String staticJsonIntentState(String intentState) {
    return intentState;
  }

	////////////////////
  // requestedState //
	////////////////////


  /**
   *  The entity requestedState
   *	 is defined as null before being initialized. 
   */
  @JsonProperty
  @JsonInclude(Include.NON_NULL)
  protected String requestedState;

  /**
   * <br> The entity requestedState
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.tenant.intent.TenantIntent&fq=entiteVar_enUS_indexed_string:requestedState">Find the entity requestedState in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _requestedState(Wrap<String> w);

  public String getRequestedState() {
    return requestedState;
  }
  public void setRequestedState(String o) {
    this.requestedState = TenantIntent.staticSetRequestedState(siteRequest_, o);
  }
  public static String staticSetRequestedState(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected TenantIntent requestedStateInit() {
    Wrap<String> requestedStateWrap = new Wrap<String>().var("requestedState");
    if(requestedState == null) {
      _requestedState(requestedStateWrap);
      Optional.ofNullable(requestedStateWrap.getO()).ifPresent(o -> {
        setRequestedState(o);
      });
    }
    return (TenantIntent)this;
  }

  public static String staticSearchRequestedState(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrRequestedState(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqRequestedState(SiteRequest siteRequest_, String o) {
    return TenantIntent.staticSearchRequestedState(siteRequest_, TenantIntent.staticSetRequestedState(siteRequest_, o)).toString();
  }

  public String sqlRequestedState() {
    return requestedState;
  }

  public static String staticJsonRequestedState(String requestedState) {
    return requestedState;
  }

	///////////////////
  // realizedState //
	///////////////////


  /**
   *  The entity realizedState
   *	 is defined as null before being initialized. 
   */
  @JsonProperty
  @JsonInclude(Include.NON_NULL)
  protected String realizedState;

  /**
   * <br> The entity realizedState
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.tenant.intent.TenantIntent&fq=entiteVar_enUS_indexed_string:realizedState">Find the entity realizedState in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _realizedState(Wrap<String> w);

  public String getRealizedState() {
    return realizedState;
  }
  public void setRealizedState(String o) {
    this.realizedState = TenantIntent.staticSetRealizedState(siteRequest_, o);
  }
  public static String staticSetRealizedState(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected TenantIntent realizedStateInit() {
    Wrap<String> realizedStateWrap = new Wrap<String>().var("realizedState");
    if(realizedState == null) {
      _realizedState(realizedStateWrap);
      Optional.ofNullable(realizedStateWrap.getO()).ifPresent(o -> {
        setRealizedState(o);
      });
    }
    return (TenantIntent)this;
  }

  public static String staticSearchRealizedState(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrRealizedState(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqRealizedState(SiteRequest siteRequest_, String o) {
    return TenantIntent.staticSearchRealizedState(siteRequest_, TenantIntent.staticSetRealizedState(siteRequest_, o)).toString();
  }

  public String sqlRealizedState() {
    return realizedState;
  }

  public static String staticJsonRealizedState(String realizedState) {
    return realizedState;
  }

	///////////////////////
  // tenantDescription //
	///////////////////////


  /**
   *  The entity tenantDescription
   *	 is defined as null before being initialized. 
   */
  @JsonProperty
  @JsonInclude(Include.NON_NULL)
  protected String tenantDescription;

  /**
   * <br> The entity tenantDescription
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.tenant.intent.TenantIntent&fq=entiteVar_enUS_indexed_string:tenantDescription">Find the entity tenantDescription in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _tenantDescription(Wrap<String> w);

  public String getTenantDescription() {
    return tenantDescription;
  }
  public void setTenantDescription(String o) {
    this.tenantDescription = TenantIntent.staticSetTenantDescription(siteRequest_, o);
  }
  public static String staticSetTenantDescription(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected TenantIntent tenantDescriptionInit() {
    Wrap<String> tenantDescriptionWrap = new Wrap<String>().var("tenantDescription");
    if(tenantDescription == null) {
      _tenantDescription(tenantDescriptionWrap);
      Optional.ofNullable(tenantDescriptionWrap.getO()).ifPresent(o -> {
        setTenantDescription(o);
      });
    }
    return (TenantIntent)this;
  }

  public static String staticSearchTenantDescription(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrTenantDescription(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqTenantDescription(SiteRequest siteRequest_, String o) {
    return TenantIntent.staticSearchTenantDescription(siteRequest_, TenantIntent.staticSetTenantDescription(siteRequest_, o)).toString();
  }

  public String sqlTenantDescription() {
    return tenantDescription;
  }

  public static String staticJsonTenantDescription(String tenantDescription) {
    return tenantDescription;
  }

	///////////////
  // requested //
	///////////////


  /**
   *  The entity requested
   *	 It is constructed before being initialized with the constructor by default. 
   */
  @JsonProperty
  @JsonFormat(shape = JsonFormat.Shape.ARRAY)
  @JsonInclude(Include.NON_NULL)
  protected List<String> requested = new ArrayList<String>();

  /**
   * <br> The entity requested
   *  It is constructed before being initialized with the constructor by default. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.tenant.intent.TenantIntent&fq=entiteVar_enUS_indexed_string:requested">Find the entity requested in Solr</a>
   * <br>
   * @param l is the entity already constructed. 
   **/
  protected abstract void _requested(List<String> l);

  public List<String> getRequested() {
    return requested;
  }

  public void setRequested(List<String> requested) {
    this.requested = requested;
  }
  @JsonIgnore
  public void setRequested(String o) {
    String l = TenantIntent.staticSetRequested(siteRequest_, o);
    if(l != null)
      addRequested(l);
  }
  public static String staticSetRequested(SiteRequest siteRequest_, String o) {
    return o;
  }
  public TenantIntent addRequested(String...objects) {
    for(String o : objects) {
      addRequested(o);
    }
    return (TenantIntent)this;
  }
  public TenantIntent addRequested(String o) {
    if(o != null)
      this.requested.add(o);
    return (TenantIntent)this;
  }
  @JsonIgnore
  public void setRequested(JsonArray objects) {
    requested.clear();
    if(objects == null)
      return;
    for(int i = 0; i < objects.size(); i++) {
      String o = objects.getString(i);
      addRequested(o);
    }
  }
  protected TenantIntent requestedInit() {
    _requested(requested);
    return (TenantIntent)this;
  }

  public static String staticSearchRequested(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrRequested(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqRequested(SiteRequest siteRequest_, String o) {
    return TenantIntent.staticSearchRequested(siteRequest_, TenantIntent.staticSetRequested(siteRequest_, o)).toString();
  }

  public String[] sqlRequested() {
    return requested.stream().map(v -> (String)v).toArray(String[]::new);
  }

  public static JsonArray staticJsonRequested(List<String> requested) {
    JsonArray a = new JsonArray();
    requested.stream().forEach(v -> a.add(v.toString()));
    return a;
  }

	////////////
  // locked //
	////////////


  /**
   *  The entity locked
   *	 is defined as null before being initialized. 
   */
  @JsonProperty
  @JsonInclude(Include.NON_NULL)
  protected Boolean locked;

  /**
   * <br> The entity locked
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.tenant.intent.TenantIntent&fq=entiteVar_enUS_indexed_string:locked">Find the entity locked in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _locked(Wrap<Boolean> w);

  public Boolean getLocked() {
    return locked;
  }

  public void setLocked(Boolean locked) {
    this.locked = locked;
  }
  @JsonIgnore
  public void setLocked(String o) {
    this.locked = TenantIntent.staticSetLocked(siteRequest_, o);
  }
  public static Boolean staticSetLocked(SiteRequest siteRequest_, String o) {
    return Boolean.parseBoolean(o);
  }
  protected TenantIntent lockedInit() {
    Wrap<Boolean> lockedWrap = new Wrap<Boolean>().var("locked");
    if(locked == null) {
      _locked(lockedWrap);
      Optional.ofNullable(lockedWrap.getO()).ifPresent(o -> {
        setLocked(o);
      });
    }
    return (TenantIntent)this;
  }

  public static Boolean staticSearchLocked(SiteRequest siteRequest_, Boolean o) {
    return o;
  }

  public static String staticSearchStrLocked(SiteRequest siteRequest_, Boolean o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqLocked(SiteRequest siteRequest_, String o) {
    return TenantIntent.staticSearchLocked(siteRequest_, TenantIntent.staticSetLocked(siteRequest_, o)).toString();
  }

  public Boolean sqlLocked() {
    return locked;
  }

  public static Boolean staticJsonLocked(Boolean locked) {
    return locked;
  }

	//////////////////////
  // tenantDiscovered //
	//////////////////////


  /**
   *  The entity tenantDiscovered
   *	 It is constructed before being initialized with the constructor by default. 
   */
  @JsonProperty
  @JsonFormat(shape = JsonFormat.Shape.ARRAY)
  @JsonInclude(Include.NON_NULL)
  protected List<String> tenantDiscovered = new ArrayList<String>();

  /**
   * <br> The entity tenantDiscovered
   *  It is constructed before being initialized with the constructor by default. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.tenant.intent.TenantIntent&fq=entiteVar_enUS_indexed_string:tenantDiscovered">Find the entity tenantDiscovered in Solr</a>
   * <br>
   * @param l is the entity already constructed. 
   **/
  protected abstract void _tenantDiscovered(List<String> l);

  public List<String> getTenantDiscovered() {
    return tenantDiscovered;
  }

  public void setTenantDiscovered(List<String> tenantDiscovered) {
    this.tenantDiscovered = tenantDiscovered;
  }
  @JsonIgnore
  public void setTenantDiscovered(String o) {
    String l = TenantIntent.staticSetTenantDiscovered(siteRequest_, o);
    if(l != null)
      addTenantDiscovered(l);
  }
  public static String staticSetTenantDiscovered(SiteRequest siteRequest_, String o) {
    return o;
  }
  public TenantIntent addTenantDiscovered(String...objects) {
    for(String o : objects) {
      addTenantDiscovered(o);
    }
    return (TenantIntent)this;
  }
  public TenantIntent addTenantDiscovered(String o) {
    if(o != null)
      this.tenantDiscovered.add(o);
    return (TenantIntent)this;
  }
  @JsonIgnore
  public void setTenantDiscovered(JsonArray objects) {
    tenantDiscovered.clear();
    if(objects == null)
      return;
    for(int i = 0; i < objects.size(); i++) {
      String o = objects.getString(i);
      addTenantDiscovered(o);
    }
  }
  protected TenantIntent tenantDiscoveredInit() {
    _tenantDiscovered(tenantDiscovered);
    return (TenantIntent)this;
  }

  public static String staticSearchTenantDiscovered(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrTenantDiscovered(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqTenantDiscovered(SiteRequest siteRequest_, String o) {
    return TenantIntent.staticSearchTenantDiscovered(siteRequest_, TenantIntent.staticSetTenantDiscovered(siteRequest_, o)).toString();
  }

  public String[] sqlTenantDiscovered() {
    return tenantDiscovered.stream().map(v -> (String)v).toArray(String[]::new);
  }

  public static JsonArray staticJsonTenantDiscovered(List<String> tenantDiscovered) {
    JsonArray a = new JsonArray();
    tenantDiscovered.stream().forEach(v -> a.add(v.toString()));
    return a;
  }

	////////////////////
  // tenantRealized //
	////////////////////


  /**
   *  The entity tenantRealized
   *	 It is constructed before being initialized with the constructor by default. 
   */
  @JsonProperty
  @JsonFormat(shape = JsonFormat.Shape.ARRAY)
  @JsonInclude(Include.NON_NULL)
  protected List<String> tenantRealized = new ArrayList<String>();

  /**
   * <br> The entity tenantRealized
   *  It is constructed before being initialized with the constructor by default. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.tenant.intent.TenantIntent&fq=entiteVar_enUS_indexed_string:tenantRealized">Find the entity tenantRealized in Solr</a>
   * <br>
   * @param l is the entity already constructed. 
   **/
  protected abstract void _tenantRealized(List<String> l);

  public List<String> getTenantRealized() {
    return tenantRealized;
  }

  public void setTenantRealized(List<String> tenantRealized) {
    this.tenantRealized = tenantRealized;
  }
  @JsonIgnore
  public void setTenantRealized(String o) {
    String l = TenantIntent.staticSetTenantRealized(siteRequest_, o);
    if(l != null)
      addTenantRealized(l);
  }
  public static String staticSetTenantRealized(SiteRequest siteRequest_, String o) {
    return o;
  }
  public TenantIntent addTenantRealized(String...objects) {
    for(String o : objects) {
      addTenantRealized(o);
    }
    return (TenantIntent)this;
  }
  public TenantIntent addTenantRealized(String o) {
    if(o != null)
      this.tenantRealized.add(o);
    return (TenantIntent)this;
  }
  @JsonIgnore
  public void setTenantRealized(JsonArray objects) {
    tenantRealized.clear();
    if(objects == null)
      return;
    for(int i = 0; i < objects.size(); i++) {
      String o = objects.getString(i);
      addTenantRealized(o);
    }
  }
  protected TenantIntent tenantRealizedInit() {
    _tenantRealized(tenantRealized);
    return (TenantIntent)this;
  }

  public static String staticSearchTenantRealized(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrTenantRealized(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqTenantRealized(SiteRequest siteRequest_, String o) {
    return TenantIntent.staticSearchTenantRealized(siteRequest_, TenantIntent.staticSetTenantRealized(siteRequest_, o)).toString();
  }

  public String[] sqlTenantRealized() {
    return tenantRealized.stream().map(v -> (String)v).toArray(String[]::new);
  }

  public static JsonArray staticJsonTenantRealized(List<String> tenantRealized) {
    JsonArray a = new JsonArray();
    tenantRealized.stream().forEach(v -> a.add(v.toString()));
    return a;
  }

  //////////////
  // initDeep //
  //////////////

  public Future<TenantIntentGen<DEV>> promiseDeepTenantIntent(SiteRequest siteRequest_) {
    if(this.siteRequest_ == null)
      setSiteRequest_(siteRequest_);
    return promiseDeepTenantIntent();
  }

  public Future<TenantIntentGen<DEV>> promiseDeepTenantIntent() {
    Promise<TenantIntentGen<DEV>> promise = Promise.promise();
    Promise<Void> promise2 = Promise.promise();
    promiseTenantIntent(promise2);
    promise2.future().onSuccess(a -> {
      super.promiseDeepTenant(siteRequest_).onSuccess(b -> {
        promise.complete(this);
      }).onFailure(ex -> {
        promise.fail(ex);
      });
    }).onFailure(ex -> {
      promise.fail(ex);
    });
    return promise.future();
  }

  public Future<Void> promiseTenantIntent(Promise<Void> promise) {
    Future.future(a -> a.complete()).compose(a -> {
      Promise<Void> promise2 = Promise.promise();
      try {
        tenantNameInit();
        tenantIdInit();
        tenantResourceInit();
        createdByEmailInit();
        createdByUserIdInit();
        createdByFullNameInit();
        createdViaInit();
        intentStateInit();
        requestedStateInit();
        realizedStateInit();
        tenantDescriptionInit();
        requestedInit();
        lockedInit();
        tenantDiscoveredInit();
        tenantRealizedInit();
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

  @Override public Future<? extends TenantIntentGen<DEV>> promiseDeepForClass(SiteRequest siteRequest_) {
    return promiseDeepTenantIntent(siteRequest_);
  }

  /////////////////
  // siteRequest //
  /////////////////

  public void siteRequestTenantIntent(SiteRequest siteRequest_) {
      super.siteRequestTenant(siteRequest_);
  }

  public void siteRequestForClass(SiteRequest siteRequest_) {
    siteRequestTenantIntent(siteRequest_);
  }

  /////////////
  // obtain //
  /////////////

  @Override public Object obtainForClass(String var) {
    String[] vars = StringUtils.split(var, ".");
    Object o = null;
    for(String v : vars) {
      if(o == null)
        o = obtainTenantIntent(v);
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
  public Object obtainTenantIntent(String var) {
    TenantIntent oTenantIntent = (TenantIntent)this;
    switch(var) {
      case "tenantName":
        return oTenantIntent.tenantName;
      case "tenantId":
        return oTenantIntent.tenantId;
      case "tenantResource":
        return oTenantIntent.tenantResource;
      case "createdByEmail":
        return oTenantIntent.createdByEmail;
      case "createdByUserId":
        return oTenantIntent.createdByUserId;
      case "createdByFullName":
        return oTenantIntent.createdByFullName;
      case "createdVia":
        return oTenantIntent.createdVia;
      case "intentState":
        return oTenantIntent.intentState;
      case "requestedState":
        return oTenantIntent.requestedState;
      case "realizedState":
        return oTenantIntent.realizedState;
      case "tenantDescription":
        return oTenantIntent.tenantDescription;
      case "requested":
        return oTenantIntent.requested;
      case "locked":
        return oTenantIntent.locked;
      case "tenantDiscovered":
        return oTenantIntent.tenantDiscovered;
      case "tenantRealized":
        return oTenantIntent.tenantRealized;
      default:
        return super.obtainTenant(var);
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
        o = relateTenantIntent(v, val);
      else if(o instanceof BaseModel) {
        BaseModel baseModel = (BaseModel)o;
        o = baseModel.relateForClass(v, val);
      }
    }
    return o != null;
  }
  public Object relateTenantIntent(String var, Object val) {
    TenantIntent oTenantIntent = (TenantIntent)this;
    switch(var) {
      case "requested":
        oTenantIntent.addRequested((String)val);
        if(!saves.contains("requested"))
          saves.add("requested");
        return val;
      case "tenantDiscovered":
        oTenantIntent.addTenantDiscovered((String)val);
        if(!saves.contains("tenantDiscovered"))
          saves.add("tenantDiscovered");
        return val;
      case "tenantRealized":
        oTenantIntent.addTenantRealized((String)val);
        if(!saves.contains("tenantRealized"))
          saves.add("tenantRealized");
        return val;
      default:
        return super.relateTenant(var, val);
    }
  }

  ///////////////
  // staticSet //
  ///////////////

  public static Object staticSetForClass(String entityVar, SiteRequest siteRequest_, String v, TenantIntent o) {
    return staticSetTenantIntent(entityVar,  siteRequest_, v, o);
  }
  public static Object staticSetTenantIntent(String entityVar, SiteRequest siteRequest_, String v, TenantIntent o) {
    switch(entityVar) {
    case "tenantName":
      return TenantIntent.staticSetTenantName(siteRequest_, v);
    case "tenantId":
      return TenantIntent.staticSetTenantId(siteRequest_, v);
    case "tenantResource":
      return TenantIntent.staticSetTenantResource(siteRequest_, v);
    case "createdByEmail":
      return TenantIntent.staticSetCreatedByEmail(siteRequest_, v);
    case "createdByUserId":
      return TenantIntent.staticSetCreatedByUserId(siteRequest_, v);
    case "createdByFullName":
      return TenantIntent.staticSetCreatedByFullName(siteRequest_, v);
    case "createdVia":
      return TenantIntent.staticSetCreatedVia(siteRequest_, v);
    case "intentState":
      return TenantIntent.staticSetIntentState(siteRequest_, v);
    case "requestedState":
      return TenantIntent.staticSetRequestedState(siteRequest_, v);
    case "realizedState":
      return TenantIntent.staticSetRealizedState(siteRequest_, v);
    case "tenantDescription":
      return TenantIntent.staticSetTenantDescription(siteRequest_, v);
    case "requested":
      return TenantIntent.staticSetRequested(siteRequest_, v);
    case "locked":
      return TenantIntent.staticSetLocked(siteRequest_, v);
    case "tenantDiscovered":
      return TenantIntent.staticSetTenantDiscovered(siteRequest_, v);
    case "tenantRealized":
      return TenantIntent.staticSetTenantRealized(siteRequest_, v);
      default:
        return Tenant.staticSetTenant(entityVar,  siteRequest_, v, o);
    }
  }

  //////////////////
  // staticSearch //
  //////////////////

  public static Future<TenantIntent> fqTenantIntent(SiteRequest siteRequest, String var, Object val) {
    Promise<TenantIntent> promise = Promise.promise();
    try {
      if(val == null) {
        promise.complete();
      } else {
        SearchList<TenantIntent> searchList = new SearchList<TenantIntent>();
        searchList.setStore(true);
        searchList.q("*:*");
        searchList.setC(TenantIntent.class);
        searchList.fq(String.format("%s:", TenantIntent.varIndexedTenantIntent(var)) + SearchTool.escapeQueryChars(val.toString()));
        searchList.promiseDeepForClass(siteRequest).onSuccess(a -> {
          try {
            promise.complete(searchList.getList().stream().findFirst().orElse(null));
          } catch(Throwable ex) {
            LOG.error("Error while querying the tenant intent", ex);
            promise.fail(ex);
          }
        }).onFailure(ex -> {
          LOG.error("Error while querying the tenant intent", ex);
          promise.fail(ex);
        });
      }
    } catch(Throwable ex) {
      LOG.error("Error while querying the tenant intent", ex);
      promise.fail(ex);
    }
    return promise.future();
  }

  public static Object staticSearchForClass(String entityVar, SiteRequest siteRequest_, Object o) {
    return staticSearchTenantIntent(entityVar,  siteRequest_, o);
  }
  public static Object staticSearchTenantIntent(String entityVar, SiteRequest siteRequest_, Object o) {
    switch(entityVar) {
    case "tenantName":
      return TenantIntent.staticSearchTenantName(siteRequest_, (String)o);
    case "tenantId":
      return TenantIntent.staticSearchTenantId(siteRequest_, (String)o);
    case "tenantResource":
      return TenantIntent.staticSearchTenantResource(siteRequest_, (String)o);
    case "createdByEmail":
      return TenantIntent.staticSearchCreatedByEmail(siteRequest_, (String)o);
    case "createdByUserId":
      return TenantIntent.staticSearchCreatedByUserId(siteRequest_, (String)o);
    case "createdByFullName":
      return TenantIntent.staticSearchCreatedByFullName(siteRequest_, (String)o);
    case "createdVia":
      return TenantIntent.staticSearchCreatedVia(siteRequest_, (String)o);
    case "intentState":
      return TenantIntent.staticSearchIntentState(siteRequest_, (String)o);
    case "requestedState":
      return TenantIntent.staticSearchRequestedState(siteRequest_, (String)o);
    case "realizedState":
      return TenantIntent.staticSearchRealizedState(siteRequest_, (String)o);
    case "tenantDescription":
      return TenantIntent.staticSearchTenantDescription(siteRequest_, (String)o);
    case "requested":
      return TenantIntent.staticSearchRequested(siteRequest_, (String)o);
    case "locked":
      return TenantIntent.staticSearchLocked(siteRequest_, (Boolean)o);
    case "tenantDiscovered":
      return TenantIntent.staticSearchTenantDiscovered(siteRequest_, (String)o);
    case "tenantRealized":
      return TenantIntent.staticSearchTenantRealized(siteRequest_, (String)o);
      default:
        return Tenant.staticSearchTenant(entityVar,  siteRequest_, o);
    }
  }

  ///////////////////
  // staticSearchStr //
  ///////////////////

  public static String staticSearchStrForClass(String entityVar, SiteRequest siteRequest_, Object o) {
    return staticSearchStrTenantIntent(entityVar,  siteRequest_, o);
  }
  public static String staticSearchStrTenantIntent(String entityVar, SiteRequest siteRequest_, Object o) {
    switch(entityVar) {
    case "tenantName":
      return TenantIntent.staticSearchStrTenantName(siteRequest_, (String)o);
    case "tenantId":
      return TenantIntent.staticSearchStrTenantId(siteRequest_, (String)o);
    case "tenantResource":
      return TenantIntent.staticSearchStrTenantResource(siteRequest_, (String)o);
    case "createdByEmail":
      return TenantIntent.staticSearchStrCreatedByEmail(siteRequest_, (String)o);
    case "createdByUserId":
      return TenantIntent.staticSearchStrCreatedByUserId(siteRequest_, (String)o);
    case "createdByFullName":
      return TenantIntent.staticSearchStrCreatedByFullName(siteRequest_, (String)o);
    case "createdVia":
      return TenantIntent.staticSearchStrCreatedVia(siteRequest_, (String)o);
    case "intentState":
      return TenantIntent.staticSearchStrIntentState(siteRequest_, (String)o);
    case "requestedState":
      return TenantIntent.staticSearchStrRequestedState(siteRequest_, (String)o);
    case "realizedState":
      return TenantIntent.staticSearchStrRealizedState(siteRequest_, (String)o);
    case "tenantDescription":
      return TenantIntent.staticSearchStrTenantDescription(siteRequest_, (String)o);
    case "requested":
      return TenantIntent.staticSearchStrRequested(siteRequest_, (String)o);
    case "locked":
      return TenantIntent.staticSearchStrLocked(siteRequest_, (Boolean)o);
    case "tenantDiscovered":
      return TenantIntent.staticSearchStrTenantDiscovered(siteRequest_, (String)o);
    case "tenantRealized":
      return TenantIntent.staticSearchStrTenantRealized(siteRequest_, (String)o);
      default:
        return Tenant.staticSearchStrTenant(entityVar,  siteRequest_, o);
    }
  }

  //////////////////
  // staticSearchFq //
  //////////////////

  public static String staticSearchFqForClass(String entityVar, SiteRequest siteRequest_, String o) {
    return staticSearchFqTenantIntent(entityVar,  siteRequest_, o);
  }
  public static String staticSearchFqTenantIntent(String entityVar, SiteRequest siteRequest_, String o) {
    switch(entityVar) {
    case "tenantName":
      return TenantIntent.staticSearchFqTenantName(siteRequest_, o);
    case "tenantId":
      return TenantIntent.staticSearchFqTenantId(siteRequest_, o);
    case "tenantResource":
      return TenantIntent.staticSearchFqTenantResource(siteRequest_, o);
    case "createdByEmail":
      return TenantIntent.staticSearchFqCreatedByEmail(siteRequest_, o);
    case "createdByUserId":
      return TenantIntent.staticSearchFqCreatedByUserId(siteRequest_, o);
    case "createdByFullName":
      return TenantIntent.staticSearchFqCreatedByFullName(siteRequest_, o);
    case "createdVia":
      return TenantIntent.staticSearchFqCreatedVia(siteRequest_, o);
    case "intentState":
      return TenantIntent.staticSearchFqIntentState(siteRequest_, o);
    case "requestedState":
      return TenantIntent.staticSearchFqRequestedState(siteRequest_, o);
    case "realizedState":
      return TenantIntent.staticSearchFqRealizedState(siteRequest_, o);
    case "tenantDescription":
      return TenantIntent.staticSearchFqTenantDescription(siteRequest_, o);
    case "requested":
      return TenantIntent.staticSearchFqRequested(siteRequest_, o);
    case "locked":
      return TenantIntent.staticSearchFqLocked(siteRequest_, o);
    case "tenantDiscovered":
      return TenantIntent.staticSearchFqTenantDiscovered(siteRequest_, o);
    case "tenantRealized":
      return TenantIntent.staticSearchFqTenantRealized(siteRequest_, o);
      default:
        return Tenant.staticSearchFqTenant(entityVar,  siteRequest_, o);
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
          o = persistTenantIntent(v, val);
        else if(o instanceof BaseModel) {
          BaseModel oBaseModel = (BaseModel)o;
          o = oBaseModel.persistForClass(v, val);
        }
      }
    }
    return o != null;
  }
  public Object persistTenantIntent(String var, Object val) {
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
      } else if("tenantresource".equals(varLower)) {
        if(val instanceof String) {
          setTenantResource((String)val);
        }
        saves.add("tenantResource");
        return val;
      } else if("createdbyemail".equals(varLower)) {
        if(val instanceof String) {
          setCreatedByEmail((String)val);
        }
        saves.add("createdByEmail");
        return val;
      } else if("createdbyuserid".equals(varLower)) {
        if(val instanceof String) {
          setCreatedByUserId((String)val);
        }
        saves.add("createdByUserId");
        return val;
      } else if("createdbyfullname".equals(varLower)) {
        if(val instanceof String) {
          setCreatedByFullName((String)val);
        }
        saves.add("createdByFullName");
        return val;
      } else if("createdvia".equals(varLower)) {
        if(val instanceof String) {
          setCreatedVia((String)val);
        }
        saves.add("createdVia");
        return val;
      } else if("intentstate".equals(varLower)) {
        if(val instanceof String) {
          setIntentState((String)val);
        }
        saves.add("intentState");
        return val;
      } else if("requestedstate".equals(varLower)) {
        if(val instanceof String) {
          setRequestedState((String)val);
        }
        saves.add("requestedState");
        return val;
      } else if("realizedstate".equals(varLower)) {
        if(val instanceof String) {
          setRealizedState((String)val);
        }
        saves.add("realizedState");
        return val;
      } else if("tenantdescription".equals(varLower)) {
        if(val instanceof String) {
          setTenantDescription((String)val);
        }
        saves.add("tenantDescription");
        return val;
      } else if("locked".equals(varLower)) {
        if(val instanceof Boolean) {
          setLocked((Boolean)val);
        } else {
          setLocked(val == null ? null : val.toString());
        }
        saves.add("locked");
        return val;
    } else {
      return super.persistTenant(var, val);
    }
  }

  /////////////
  // populate //
  /////////////

  @Override public void populateForClass(SolrResponse.Doc doc) {
    populateTenantIntent(doc);
  }
  public void populateTenantIntent(SolrResponse.Doc doc) {
    TenantIntent oTenantIntent = (TenantIntent)this;
    saves = Optional.ofNullable((ArrayList<String>)doc.get("saves_docvalues_strings")).orElse(new ArrayList<String>());
    if(saves != null) {

      if(saves.contains("tenantName")) {
        String tenantName = (String)doc.get("tenantName_docvalues_string");
        if(tenantName != null)
          oTenantIntent.setTenantName(tenantName);
      }

      if(saves.contains("tenantId")) {
        String tenantId = (String)doc.get("tenantId_docvalues_string");
        if(tenantId != null)
          oTenantIntent.setTenantId(tenantId);
      }

      if(saves.contains("tenantResource")) {
        String tenantResource = (String)doc.get("tenantResource_docvalues_string");
        if(tenantResource != null)
          oTenantIntent.setTenantResource(tenantResource);
      }

      if(saves.contains("createdByEmail")) {
        String createdByEmail = (String)doc.get("createdByEmail_docvalues_string");
        if(createdByEmail != null)
          oTenantIntent.setCreatedByEmail(createdByEmail);
      }

      if(saves.contains("createdByUserId")) {
        String createdByUserId = (String)doc.get("createdByUserId_docvalues_string");
        if(createdByUserId != null)
          oTenantIntent.setCreatedByUserId(createdByUserId);
      }

      if(saves.contains("createdByFullName")) {
        String createdByFullName = (String)doc.get("createdByFullName_docvalues_string");
        if(createdByFullName != null)
          oTenantIntent.setCreatedByFullName(createdByFullName);
      }

      if(saves.contains("createdVia")) {
        String createdVia = (String)doc.get("createdVia_docvalues_string");
        if(createdVia != null)
          oTenantIntent.setCreatedVia(createdVia);
      }

      if(saves.contains("intentState")) {
        String intentState = (String)doc.get("intentState_docvalues_string");
        if(intentState != null)
          oTenantIntent.setIntentState(intentState);
      }

      if(saves.contains("requestedState")) {
        String requestedState = (String)doc.get("requestedState_docvalues_string");
        if(requestedState != null)
          oTenantIntent.setRequestedState(requestedState);
      }

      if(saves.contains("realizedState")) {
        String realizedState = (String)doc.get("realizedState_docvalues_string");
        if(realizedState != null)
          oTenantIntent.setRealizedState(realizedState);
      }

      if(saves.contains("tenantDescription")) {
        String tenantDescription = (String)doc.get("tenantDescription_docvalues_string");
        if(tenantDescription != null)
          oTenantIntent.setTenantDescription(tenantDescription);
      }

      List<String> requested = (List<String>)doc.get("requested_docvalues_strings");
      if(requested != null)
        oTenantIntent.requested.addAll(requested);

      if(saves.contains("locked")) {
        Boolean locked = (Boolean)doc.get("locked_docvalues_boolean");
        if(locked != null)
          oTenantIntent.setLocked(locked);
      }

      List<String> tenantDiscovered = (List<String>)doc.get("tenantDiscovered_docvalues_strings");
      if(tenantDiscovered != null)
        oTenantIntent.tenantDiscovered.addAll(tenantDiscovered);

      List<String> tenantRealized = (List<String>)doc.get("tenantRealized_docvalues_strings");
      if(tenantRealized != null)
        oTenantIntent.tenantRealized.addAll(tenantRealized);
    }

    super.populateTenant(doc);
  }

  public void indexTenantIntent(JsonObject doc) {
    if(tenantName != null) {
      doc.put("tenantName_docvalues_string", tenantName);
    }
    if(tenantId != null) {
      doc.put("tenantId_docvalues_string", tenantId);
    }
    if(tenantResource != null) {
      doc.put("tenantResource_docvalues_string", tenantResource);
    }
    if(createdByEmail != null) {
      doc.put("createdByEmail_docvalues_string", createdByEmail);
    }
    if(createdByUserId != null) {
      doc.put("createdByUserId_docvalues_string", createdByUserId);
    }
    if(createdByFullName != null) {
      doc.put("createdByFullName_docvalues_string", createdByFullName);
    }
    if(createdVia != null) {
      doc.put("createdVia_docvalues_string", createdVia);
    }
    if(intentState != null) {
      doc.put("intentState_docvalues_string", intentState);
    }
    if(requestedState != null) {
      doc.put("requestedState_docvalues_string", requestedState);
    }
    if(realizedState != null) {
      doc.put("realizedState_docvalues_string", realizedState);
    }
    if(tenantDescription != null) {
      doc.put("tenantDescription_docvalues_string", tenantDescription);
    }
    if(requested != null) {
      JsonArray l = new JsonArray();
      doc.put("requested_docvalues_strings", l);
      for(String o : requested) {
        l.add(TenantIntent.staticSearchRequested(siteRequest_, o));
      }
    }
    if(locked != null) {
      doc.put("locked_docvalues_boolean", locked);
    }
    if(tenantDiscovered != null) {
      JsonArray l = new JsonArray();
      doc.put("tenantDiscovered_docvalues_strings", l);
      for(String o : tenantDiscovered) {
        l.add(TenantIntent.staticSearchTenantDiscovered(siteRequest_, o));
      }
    }
    if(tenantRealized != null) {
      JsonArray l = new JsonArray();
      doc.put("tenantRealized_docvalues_strings", l);
      for(String o : tenantRealized) {
        l.add(TenantIntent.staticSearchTenantRealized(siteRequest_, o));
      }
    }
    super.indexTenant(doc);

	}

  public static String varStoredTenantIntent(String entityVar) {
    switch(entityVar) {
      case "tenantName":
        return "tenantName_docvalues_string";
      case "tenantId":
        return "tenantId_docvalues_string";
      case "tenantResource":
        return "tenantResource_docvalues_string";
      case "createdByEmail":
        return "createdByEmail_docvalues_string";
      case "createdByUserId":
        return "createdByUserId_docvalues_string";
      case "createdByFullName":
        return "createdByFullName_docvalues_string";
      case "createdVia":
        return "createdVia_docvalues_string";
      case "intentState":
        return "intentState_docvalues_string";
      case "requestedState":
        return "requestedState_docvalues_string";
      case "realizedState":
        return "realizedState_docvalues_string";
      case "tenantDescription":
        return "tenantDescription_docvalues_string";
      case "requested":
        return "requested_docvalues_strings";
      case "locked":
        return "locked_docvalues_boolean";
      case "tenantDiscovered":
        return "tenantDiscovered_docvalues_strings";
      case "tenantRealized":
        return "tenantRealized_docvalues_strings";
      default:
        return Tenant.varStoredTenant(entityVar);
    }
  }

  public static String varIndexedTenantIntent(String entityVar) {
    switch(entityVar) {
      case "tenantName":
        return "tenantName_docvalues_string";
      case "tenantId":
        return "tenantId_docvalues_string";
      case "tenantResource":
        return "tenantResource_docvalues_string";
      case "createdByEmail":
        return "createdByEmail_docvalues_string";
      case "createdByUserId":
        return "createdByUserId_docvalues_string";
      case "createdByFullName":
        return "createdByFullName_docvalues_string";
      case "createdVia":
        return "createdVia_docvalues_string";
      case "intentState":
        return "intentState_docvalues_string";
      case "requestedState":
        return "requestedState_docvalues_string";
      case "realizedState":
        return "realizedState_docvalues_string";
      case "tenantDescription":
        return "tenantDescription_docvalues_string";
      case "requested":
        return "requested_docvalues_strings";
      case "locked":
        return "locked_docvalues_boolean";
      case "tenantDiscovered":
        return "tenantDiscovered_docvalues_strings";
      case "tenantRealized":
        return "tenantRealized_docvalues_strings";
      default:
        return Tenant.varIndexedTenant(entityVar);
    }
  }

  public static String searchVarTenantIntent(String searchVar) {
    switch(searchVar) {
      case "tenantName_docvalues_string":
        return "tenantName";
      case "tenantId_docvalues_string":
        return "tenantId";
      case "tenantResource_docvalues_string":
        return "tenantResource";
      case "createdByEmail_docvalues_string":
        return "createdByEmail";
      case "createdByUserId_docvalues_string":
        return "createdByUserId";
      case "createdByFullName_docvalues_string":
        return "createdByFullName";
      case "createdVia_docvalues_string":
        return "createdVia";
      case "intentState_docvalues_string":
        return "intentState";
      case "requestedState_docvalues_string":
        return "requestedState";
      case "realizedState_docvalues_string":
        return "realizedState";
      case "tenantDescription_docvalues_string":
        return "tenantDescription";
      case "requested_docvalues_strings":
        return "requested";
      case "locked_docvalues_boolean":
        return "locked";
      case "tenantDiscovered_docvalues_strings":
        return "tenantDiscovered";
      case "tenantRealized_docvalues_strings":
        return "tenantRealized";
      default:
        return Tenant.searchVarTenant(searchVar);
    }
  }

  public static String varSearchTenantIntent(String entityVar) {
    switch(entityVar) {
      default:
        return Tenant.varSearchTenant(entityVar);
    }
  }

  public static String varSuggestedTenantIntent(String entityVar) {
    switch(entityVar) {
      default:
        return Tenant.varSuggestedTenant(entityVar);
    }
  }

  /////////////
  // store //
  /////////////

  @Override public void storeForClass(SolrResponse.Doc doc) {
    storeTenantIntent(doc);
  }
  public void storeTenantIntent(SolrResponse.Doc doc) {
    TenantIntent oTenantIntent = (TenantIntent)this;
    SiteRequest siteRequest = oTenantIntent.getSiteRequest_();

    oTenantIntent.setTenantName(Optional.ofNullable(doc.get("tenantName_docvalues_string")).map(v -> v.toString()).orElse(null));
    oTenantIntent.setTenantId(Optional.ofNullable(doc.get("tenantId_docvalues_string")).map(v -> v.toString()).orElse(null));
    oTenantIntent.setTenantResource(Optional.ofNullable(doc.get("tenantResource_docvalues_string")).map(v -> v.toString()).orElse(null));
    oTenantIntent.setCreatedByEmail(Optional.ofNullable(doc.get("createdByEmail_docvalues_string")).map(v -> v.toString()).orElse(null));
    oTenantIntent.setCreatedByUserId(Optional.ofNullable(doc.get("createdByUserId_docvalues_string")).map(v -> v.toString()).orElse(null));
    oTenantIntent.setCreatedByFullName(Optional.ofNullable(doc.get("createdByFullName_docvalues_string")).map(v -> v.toString()).orElse(null));
    oTenantIntent.setCreatedVia(Optional.ofNullable(doc.get("createdVia_docvalues_string")).map(v -> v.toString()).orElse(null));
    oTenantIntent.setIntentState(Optional.ofNullable(doc.get("intentState_docvalues_string")).map(v -> v.toString()).orElse(null));
    oTenantIntent.setRequestedState(Optional.ofNullable(doc.get("requestedState_docvalues_string")).map(v -> v.toString()).orElse(null));
    oTenantIntent.setRealizedState(Optional.ofNullable(doc.get("realizedState_docvalues_string")).map(v -> v.toString()).orElse(null));
    oTenantIntent.setTenantDescription(Optional.ofNullable(doc.get("tenantDescription_docvalues_string")).map(v -> v.toString()).orElse(null));
    Optional.ofNullable((List<?>)doc.get("requested_docvalues_strings")).orElse(Arrays.asList()).stream().filter(v -> v != null).forEach(v -> {
      oTenantIntent.addRequested(TenantIntent.staticSetRequested(siteRequest, v.toString()));
    });
    oTenantIntent.setLocked(Optional.ofNullable(doc.get("locked_docvalues_boolean")).map(v -> v.toString()).orElse(null));
    Optional.ofNullable((List<?>)doc.get("tenantDiscovered_docvalues_strings")).orElse(Arrays.asList()).stream().filter(v -> v != null).forEach(v -> {
      oTenantIntent.addTenantDiscovered(TenantIntent.staticSetTenantDiscovered(siteRequest, v.toString()));
    });
    Optional.ofNullable((List<?>)doc.get("tenantRealized_docvalues_strings")).orElse(Arrays.asList()).stream().filter(v -> v != null).forEach(v -> {
      oTenantIntent.addTenantRealized(TenantIntent.staticSetTenantRealized(siteRequest, v.toString()));
    });

    super.storeTenant(doc);
  }

  //////////////////
  // apiRequest //
  //////////////////

  public void apiRequestTenantIntent() {
    ApiRequest apiRequest = Optional.ofNullable(siteRequest_).map(r -> r.getApiRequest_()).orElse(null);
    Object o = Optional.ofNullable(apiRequest).map(ApiRequest::getOriginal).orElse(null);
    if(o != null && o instanceof TenantIntent) {
      TenantIntent original = (TenantIntent)o;
      if(!Objects.equals(tenantName, original.getTenantName()))
        apiRequest.addVars("tenantName");
      if(!Objects.equals(tenantId, original.getTenantId()))
        apiRequest.addVars("tenantId");
      if(!Objects.equals(tenantResource, original.getTenantResource()))
        apiRequest.addVars("tenantResource");
      if(!Objects.equals(createdByEmail, original.getCreatedByEmail()))
        apiRequest.addVars("createdByEmail");
      if(!Objects.equals(createdByUserId, original.getCreatedByUserId()))
        apiRequest.addVars("createdByUserId");
      if(!Objects.equals(createdByFullName, original.getCreatedByFullName()))
        apiRequest.addVars("createdByFullName");
      if(!Objects.equals(createdVia, original.getCreatedVia()))
        apiRequest.addVars("createdVia");
      if(!Objects.equals(intentState, original.getIntentState()))
        apiRequest.addVars("intentState");
      if(!Objects.equals(requestedState, original.getRequestedState()))
        apiRequest.addVars("requestedState");
      if(!Objects.equals(realizedState, original.getRealizedState()))
        apiRequest.addVars("realizedState");
      if(!Objects.equals(tenantDescription, original.getTenantDescription()))
        apiRequest.addVars("tenantDescription");
      if(!Objects.equals(requested, original.getRequested()))
        apiRequest.addVars("requested");
      if(!Objects.equals(locked, original.getLocked()))
        apiRequest.addVars("locked");
      if(!Objects.equals(tenantDiscovered, original.getTenantDiscovered()))
        apiRequest.addVars("tenantDiscovered");
      if(!Objects.equals(tenantRealized, original.getTenantRealized()))
        apiRequest.addVars("tenantRealized");
      super.apiRequestTenant();
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
    sb.append(Optional.ofNullable(tenantResource).map(v -> "tenantResource: \"" + v + "\"\n" ).orElse(""));
    sb.append(Optional.ofNullable(createdByEmail).map(v -> "createdByEmail: \"" + v + "\"\n" ).orElse(""));
    sb.append(Optional.ofNullable(createdByUserId).map(v -> "createdByUserId: \"" + v + "\"\n" ).orElse(""));
    sb.append(Optional.ofNullable(createdByFullName).map(v -> "createdByFullName: \"" + v + "\"\n" ).orElse(""));
    sb.append(Optional.ofNullable(createdVia).map(v -> "createdVia: \"" + v + "\"\n" ).orElse(""));
    sb.append(Optional.ofNullable(intentState).map(v -> "intentState: \"" + v + "\"\n" ).orElse(""));
    sb.append(Optional.ofNullable(requestedState).map(v -> "requestedState: \"" + v + "\"\n" ).orElse(""));
    sb.append(Optional.ofNullable(realizedState).map(v -> "realizedState: \"" + v + "\"\n" ).orElse(""));
    sb.append(Optional.ofNullable(tenantDescription).map(v -> "tenantDescription: \"" + v + "\"\n" ).orElse(""));
    sb.append(Optional.ofNullable(requested).map(v -> "requested: " + v + "\n").orElse(""));
    sb.append(Optional.ofNullable(locked).map(v -> "locked: " + v + "\n").orElse(""));
    sb.append(Optional.ofNullable(tenantDiscovered).map(v -> "tenantDiscovered: " + v + "\n").orElse(""));
    sb.append(Optional.ofNullable(tenantRealized).map(v -> "tenantRealized: " + v + "\n").orElse(""));
    return sb.toString();
  }

  public static final String CLASS_SIMPLE_NAME = "TenantIntent";
  public static final String CLASS_CANONICAL_NAME = "org.computate.dcm.model.eda.tenant.intent.TenantIntent";
  public static final String CLASS_AUTH_RESOURCE = "TENANT";
  public static final String CLASS_API_ADDRESS_TenantIntent = "dcm-enUS-TenantIntent";
  public static String getClassApiAddress() {
    return CLASS_API_ADDRESS_TenantIntent;
  }
  public static final String VAR_tenantName = "tenantName";
  public static final String SET_tenantName = "setTenantName";
  public static final String VAR_tenantId = "tenantId";
  public static final String SET_tenantId = "setTenantId";
  public static final String VAR_tenantResource = "tenantResource";
  public static final String SET_tenantResource = "setTenantResource";
  public static final String VAR_createdByEmail = "createdByEmail";
  public static final String SET_createdByEmail = "setCreatedByEmail";
  public static final String VAR_createdByUserId = "createdByUserId";
  public static final String SET_createdByUserId = "setCreatedByUserId";
  public static final String VAR_createdByFullName = "createdByFullName";
  public static final String SET_createdByFullName = "setCreatedByFullName";
  public static final String VAR_createdVia = "createdVia";
  public static final String SET_createdVia = "setCreatedVia";
  public static final String VAR_intentState = "intentState";
  public static final String SET_intentState = "setIntentState";
  public static final String VAR_requestedState = "requestedState";
  public static final String SET_requestedState = "setRequestedState";
  public static final String VAR_realizedState = "realizedState";
  public static final String SET_realizedState = "setRealizedState";
  public static final String VAR_tenantDescription = "tenantDescription";
  public static final String SET_tenantDescription = "setTenantDescription";
  public static final String VAR_requested = "requested";
  public static final String SET_requested = "setRequested";
  public static final String VAR_locked = "locked";
  public static final String SET_locked = "setLocked";
  public static final String VAR_tenantDiscovered = "tenantDiscovered";
  public static final String SET_tenantDiscovered = "setTenantDiscovered";
  public static final String VAR_tenantRealized = "tenantRealized";
  public static final String SET_tenantRealized = "setTenantRealized";

  public static List<String> varsQForClass() {
    return TenantIntent.varsQTenantIntent(new ArrayList<String>());
  }
  public static List<String> varsQTenantIntent(List<String> vars) {
    Tenant.varsQTenant(vars);
    return vars;
  }

  public static List<String> varsFqForClass() {
    return TenantIntent.varsFqTenantIntent(new ArrayList<String>());
  }
  public static List<String> varsFqTenantIntent(List<String> vars) {
    vars.add(VAR_tenantName);
    vars.add(VAR_tenantId);
    vars.add(VAR_tenantResource);
    vars.add(VAR_tenantDescription);
    vars.add(VAR_locked);
    Tenant.varsFqTenant(vars);
    return vars;
  }

  public static List<String> varsRangeForClass() {
    return TenantIntent.varsRangeTenantIntent(new ArrayList<String>());
  }
  public static List<String> varsRangeTenantIntent(List<String> vars) {
    Tenant.varsRangeTenant(vars);
    return vars;
  }

  public static final String DISPLAY_NAME_tenantName = "tenant name";
  public static final String DISPLAY_NAME_tenantId = "tenant ID";
  public static final String DISPLAY_NAME_tenantResource = "tenant auth resource";
  public static final String DISPLAY_NAME_createdByEmail = "created by user email";
  public static final String DISPLAY_NAME_createdByUserId = "created by user ID";
  public static final String DISPLAY_NAME_createdByFullName = "created by user full name";
  public static final String DISPLAY_NAME_createdVia = "created via";
  public static final String DISPLAY_NAME_intentState = "intent state";
  public static final String DISPLAY_NAME_requestedState = "requested state";
  public static final String DISPLAY_NAME_realizedState = "realized state";
  public static final String DISPLAY_NAME_tenantDescription = "description";
  public static final String DISPLAY_NAME_requested = "tenant requested";
  public static final String DISPLAY_NAME_locked = "locked";
  public static final String DISPLAY_NAME_tenantDiscovered = "tenant discovered";
  public static final String DISPLAY_NAME_tenantRealized = "tenant realized";

  @Override
  public String idForClass() {
    return tenantResource;
  }

  @Override
  public String titleForClass() {
    return objectTitle;
  }

  @Override
  public String nameForClass() {
    return tenantName;
  }

  @Override
  public String classNameAdjectiveSingularForClass() {
    return TenantIntent.NameAdjectiveSingular_enUS;
  }

  @Override
  public String descriptionForClass() {
    return tenantDescription;
  }

  @Override
  public String enUSStringFormatUrlEditPageForClass() {
    return "%s/en-us/edit/intent/tenant/%s";
  }

  public static String varJson(String var, Boolean patch) {
    return TenantIntent.varJsonTenantIntent(var, patch);
  }
  public static String varJsonTenantIntent(String var, Boolean patch) {
    switch(var) {
    case VAR_tenantName:
      return patch ? SET_tenantName : VAR_tenantName;
    case VAR_tenantId:
      return patch ? SET_tenantId : VAR_tenantId;
    case VAR_tenantResource:
      return patch ? SET_tenantResource : VAR_tenantResource;
    case VAR_createdByEmail:
      return patch ? SET_createdByEmail : VAR_createdByEmail;
    case VAR_createdByUserId:
      return patch ? SET_createdByUserId : VAR_createdByUserId;
    case VAR_createdByFullName:
      return patch ? SET_createdByFullName : VAR_createdByFullName;
    case VAR_createdVia:
      return patch ? SET_createdVia : VAR_createdVia;
    case VAR_intentState:
      return patch ? SET_intentState : VAR_intentState;
    case VAR_requestedState:
      return patch ? SET_requestedState : VAR_requestedState;
    case VAR_realizedState:
      return patch ? SET_realizedState : VAR_realizedState;
    case VAR_tenantDescription:
      return patch ? SET_tenantDescription : VAR_tenantDescription;
    case VAR_requested:
      return patch ? SET_requested : VAR_requested;
    case VAR_locked:
      return patch ? SET_locked : VAR_locked;
    case VAR_tenantDiscovered:
      return patch ? SET_tenantDiscovered : VAR_tenantDiscovered;
    case VAR_tenantRealized:
      return patch ? SET_tenantRealized : VAR_tenantRealized;
    default:
      return Tenant.varJsonTenant(var, patch);
    }
  }

  public static String displayNameForClass(String var) {
    return TenantIntent.displayNameTenantIntent(var);
  }
  public static String displayNameTenantIntent(String var) {
    switch(var) {
    case VAR_tenantName:
      return DISPLAY_NAME_tenantName;
    case VAR_tenantId:
      return DISPLAY_NAME_tenantId;
    case VAR_tenantResource:
      return DISPLAY_NAME_tenantResource;
    case VAR_createdByEmail:
      return DISPLAY_NAME_createdByEmail;
    case VAR_createdByUserId:
      return DISPLAY_NAME_createdByUserId;
    case VAR_createdByFullName:
      return DISPLAY_NAME_createdByFullName;
    case VAR_createdVia:
      return DISPLAY_NAME_createdVia;
    case VAR_intentState:
      return DISPLAY_NAME_intentState;
    case VAR_requestedState:
      return DISPLAY_NAME_requestedState;
    case VAR_realizedState:
      return DISPLAY_NAME_realizedState;
    case VAR_tenantDescription:
      return DISPLAY_NAME_tenantDescription;
    case VAR_requested:
      return DISPLAY_NAME_requested;
    case VAR_locked:
      return DISPLAY_NAME_locked;
    case VAR_tenantDiscovered:
      return DISPLAY_NAME_tenantDiscovered;
    case VAR_tenantRealized:
      return DISPLAY_NAME_tenantRealized;
    default:
      return Tenant.displayNameTenant(var);
    }
  }

  public static String descriptionTenantIntent(String var) {
    if(var == null)
      return null;
    switch(var) {
    case VAR_tenantName:
      return "The name of this tenant";
    case VAR_tenantId:
      return "The ID of this tenant. By default, this will be auto-generated based on the tenant name, converting non-alphanumeric characters to hyphens, all lowercase. ";
    case VAR_tenantResource:
      return "The unique authorization resource for the tenant for multi-tenancy";
    case VAR_createdByEmail:
      return "The email address for the user who created the change request. ";
    case VAR_createdByUserId:
      return "The IdP UUID record for the user who created the change request. ";
    case VAR_createdByFullName:
      return "The first and last name for the user who created the change request. ";
    case VAR_createdVia:
      return "Declares the ingestion path that makes audit quality transparent. ";
    case VAR_intentState:
      return "Captures the consumer's raw intent \u2014 what they asked for in their own terms. ";
    case VAR_requestedState:
      return "Represents a complete, validated, provider-ready declaration of desired state. ";
    case VAR_realizedState:
      return "Must be a complete representation of the provisioned resource in DCM unified format \u2014 not a status code, but a full state description. ";
    case VAR_tenantDescription:
      return "A description of this tenant";
    case VAR_requested:
      return "The related tenant requests for this tenant intent. ";
    case VAR_locked:
      return "A tenant intent gets locked after creating the first tenant request. ";
    case VAR_tenantDiscovered:
      return "Each time the tenant was discovered for this tenant intent. ";
    case VAR_tenantRealized:
      return "Each time the tenant was realized for this tenant intent. ";
      default:
        return Tenant.descriptionTenant(var);
    }
  }

  public static String classSimpleNameTenantIntent(String var) {
    switch(var) {
    case VAR_tenantName:
      return "String";
    case VAR_tenantId:
      return "String";
    case VAR_tenantResource:
      return "String";
    case VAR_createdByEmail:
      return "String";
    case VAR_createdByUserId:
      return "String";
    case VAR_createdByFullName:
      return "String";
    case VAR_createdVia:
      return "String";
    case VAR_intentState:
      return "String";
    case VAR_requestedState:
      return "String";
    case VAR_realizedState:
      return "String";
    case VAR_tenantDescription:
      return "String";
    case VAR_requested:
      return "List";
    case VAR_locked:
      return "Boolean";
    case VAR_tenantDiscovered:
      return "List";
    case VAR_tenantRealized:
      return "List";
      default:
        return Tenant.classSimpleNameTenant(var);
    }
  }

  public static Integer htmColumnTenantIntent(String var) {
    switch(var) {
    case VAR_tenantName:
      return 1;
    case VAR_tenantDescription:
      return 3;
      default:
        return Tenant.htmColumnTenant(var);
    }
  }

  public static Integer htmRowTenantIntent(String var) {
    switch(var) {
    case VAR_tenantName:
      return 20;
    case VAR_createdByEmail:
      return 10;
    case VAR_createdByUserId:
      return 10;
    case VAR_createdByFullName:
      return 10;
    case VAR_createdVia:
      return 10;
    case VAR_intentState:
      return 12;
    case VAR_requestedState:
      return 12;
    case VAR_realizedState:
      return 12;
    case VAR_tenantDescription:
      return 20;
    case VAR_requested:
      return 21;
    case VAR_locked:
      return 21;
    case VAR_tenantDiscovered:
      return 22;
    case VAR_tenantRealized:
      return 23;
      default:
        return Tenant.htmRowTenant(var);
    }
  }

  public static Integer htmCellTenantIntent(String var) {
    switch(var) {
    case VAR_tenantName:
      return 1;
    case VAR_createdByEmail:
      return 0;
    case VAR_createdByUserId:
      return 0;
    case VAR_createdByFullName:
      return 0;
    case VAR_createdVia:
      return 0;
    case VAR_intentState:
      return 0;
    case VAR_requestedState:
      return 0;
    case VAR_realizedState:
      return 0;
    case VAR_tenantDescription:
      return 4;
    case VAR_requested:
      return 0;
    case VAR_locked:
      return 0;
    case VAR_tenantDiscovered:
      return 0;
    case VAR_tenantRealized:
      return 0;
      default:
        return Tenant.htmCellTenant(var);
    }
  }

  public static Integer lengthMinTenantIntent(String var) {
    switch(var) {
      default:
        return Tenant.lengthMinTenant(var);
    }
  }

  public static Integer lengthMaxTenantIntent(String var) {
    switch(var) {
      default:
        return Tenant.lengthMaxTenant(var);
    }
  }

  public static Integer maxTenantIntent(String var) {
    switch(var) {
      default:
        return Tenant.maxTenant(var);
    }
  }

  public static Integer minTenantIntent(String var) {
    switch(var) {
      default:
        return Tenant.minTenant(var);
    }
  }
}
