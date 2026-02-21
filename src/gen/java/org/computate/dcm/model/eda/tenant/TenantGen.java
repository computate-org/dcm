package org.computate.dcm.model.eda.tenant;

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
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.computate.search.serialize.ComputateLocalDateSerializer;
import org.computate.search.serialize.ComputateLocalDateDeserializer;
import org.computate.search.serialize.ComputateZonedDateTimeSerializer;
import org.computate.search.serialize.ComputateZonedDateTimeDeserializer;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
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
import java.lang.Long;
import org.computate.search.wrap.Wrap;
import io.vertx.core.Promise;
import io.vertx.core.Future;
import io.vertx.core.json.JsonArray;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.computate.search.response.solr.SolrResponse;

/**
 * <ol>
<h3>Suggestions that can generate more code for you: </h3> * </ol>
 * <li>You can add a class comment "{@inheritDoc}" if you wish to inherit the helpful inherited class comments from class TenantGen into the class Tenant. 
 * </li><li>You can add a class comment "Rows: 100" if you wish the Tenant API to return more or less than 10 records by default. 
 * In this case, the API will return 100 records from the API instead of 10 by default. 
 * Each API has built in pagination of the search records to ensure a user can query all the data a page at a time without running the application out of memory. 
 * </li>
 * <h3>About the Tenant class and it's generated class TenantGen&lt;BaseModel&gt;: </h3>extends TenantGen
 * <p>
 * This Java class extends a generated Java class TenantGen built by the <a href="https://github.com/computate-org/computate">https://github.com/computate-org/computate</a> project. 
 * Whenever this Java class is modified or touched, the watch service installed as described in the README, indexes all the information about this Java class in a local Apache Solr Search Engine. 
 * If you are running the service, you can see the indexed data about this Java Class here: 
 * </p>
 * <p><a href="https://solr.apps-crc.testing/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.tenant.Tenant">Find the class Tenant in Solr. </a></p>
 * <p>
 * The extended class ending with "Gen" did not exist at first, but was automatically created by the same watch service based on the data retrieved from the local Apache Server search engine. 
 * The extended class contains many generated fields, getters, setters, initialization code, and helper methods to help build a website and API fast, reactive, and scalable. 
 * </p>
 * extends TenantGen<BaseModel>
 * <p>This <code>class Tenant extends TenantGen&lt;BaseModel&gt;</code>, which means it extends a newly generated TenantGen. 
 * The generated <code>class TenantGen extends BaseModel</code> which means that Tenant extends TenantGen which extends BaseModel. 
 * This generated inheritance is a powerful feature that allows a lot of boiler plate code to be created for you automatically while still preserving inheritance through the power of Java Generic classes. 
 * </p>
 * <h2>Api: true</h2>
 * <p>This class contains a comment <b>"Api: true"</b>, which means this class will have Java Vert.x API backend code generated for these objects. 
 * </p>
 * <h2>ApiTag.enUS: true</h2>
 * <p>This class contains a comment <b>"ApiTag: tenants"</b>, which groups all of the OpenAPIs for Tenant objects under the tag "tenants". 
 * </p>
 * <h2>ApiUri.enUS: /en-us/api/tenant</h2>
 * <p>This class contains a comment <b>"ApiUri: /en-us/api/tenant"</b>, which defines the base API URI for Tenant objects as "/en-us/api/tenant" in the OpenAPI spec. 
 * </p>
 * <h2>Color: null</h2>
 * <h2>Indexed: true</h2>
 * <p>This class contains a comment <b>"Indexed: true"</b>, which means this class will be indexed in the search engine. 
 * Every protected void method that begins with "_" that is marked to be searched with a comment like "Indexed: true", "Stored: true", or "DocValues: true" will be indexed in the search engine. 
 * </p>
 * <h2>{@inheritDoc}</h2>
 * <p>By adding a class comment "{@inheritDoc}", the Tenant class will inherit the helpful inherited class comments from the super class TenantGen. 
 * </p>
 * <h2>Rows: null</h2>
 * <h2>Order: 4</h2>
 * <p>This class contains a comment <b>"Order: 4"</b>, which means this class will be sorted by the given number 4 ascending when code that relates to multiple classes at the same time is generated. 
 * </p>
 * <h2>SqlOrder: 4</h2>
 * <p>This class contains a comment <b>"SqlOrder: 4"</b>, which means this class will be sorted by the given number 4 ascending when SQL code to create and drop the tables is generated. 
 * </p>
 * <h2>Model: true</h2>
 * <p>This class contains a comment <b>"Model: true"</b>, which means this class will be stored in the database. 
 * Every protected void method that begins with "_" that contains a "Persist: true" comment will be a persisted field in the database table. 
 * </p>
 * <h2>Page: true</h2>
 * <p>This class contains a comment <b>"Page: true"</b>, which means this class will have webpage code generated for these objects. 
 * Java Vert.x backend API code, Handlebars HTML template frontend code, and JavaScript code will all generated and can be extended. 
 * This creates a new Java class org.computate.dcm.model.eda.tenant.TenantPage. 
 * </p>
 * <h2>SuperPage.enUS: PageLayout</h2>
 * <p>This class contains a comment <b>"SuperPage.enUS: PageLayout"</b>, which identifies the Java super class of the page code by it's class simple name "PageLayout". 
 * This means that the newly created class org.computate.dcm.model.eda.tenant.TenantPage extends org.computate.dcm.page.PageLayout. 
 * </p>
 * <h2>Promise: true</h2>
 * <p>
 *   This class contains a comment <b>"Promise: true"</b>
 *   Sometimes a Java class must be initialized asynchronously when it involves calling a blocking API. 
 *   This means that the Tenant Java class has promiseDeep methods which must be initialized asynchronously as a Vert.x Promise  instead of initDeep methods which are a simple non-asynchronous method. 
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
 * <h2>AName.enUS: a tenant</h2>
 * <p>This class contains a comment <b>"AName.enUS: a tenant"</b>, which identifies the language context to describe a Tenant as "a tenant". 
 * </p>
 * <p>
 * Delete the class Tenant in Solr: 
 * <pre>
 * curl -k 'https://solr.apps-crc.testing/solr/computate/update?commitWithin=1000&amp;overwrite=true&amp;wt=json' -X POST -H 'Content-type: text/xml' -u "admin:$(oc -n solr get secret/solr-solrcloud-security-bootstrap -o jsonpath={.data.admin} | base64 -d)" --data-raw '&lt;delete&gt;&lt;query&gt;classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.tenant.Tenant&lt;/query&gt;&lt;/delete&gt;'
 * </pre>
 * </p>
 * <p>
 * Delete  the package org.computate.dcm.model.eda.tenant in Solr: 
 * <pre>
 * curl -k 'https://solr.apps-crc.testing/solr/computate/update?commitWithin=1000&amp;overwrite=true&amp;wt=json' -X POST -H 'Content-type: text/xml' -u "admin:$(oc -n solr get secret/solr-solrcloud-security-bootstrap -o jsonpath={.data.admin} | base64 -d)" --data-raw '&lt;delete&gt;&lt;query&gt;classeNomEnsemble_enUS_indexed_string:org.computate.dcm.model.eda.tenant&lt;/query&gt;&lt;/delete&gt;'
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
public abstract class TenantGen<DEV> extends BaseModel {
  protected static final Logger LOG = LoggerFactory.getLogger(Tenant.class);

  public static final String Description_enUS = "Tenants are separate organizations sharing the same cloud resources. ";
  public static final String AName_enUS = "a tenant";
  public static final String This_enUS = "this ";
  public static final String ThisName_enUS = "this tenant";
  public static final String A_enUS = "a ";
  public static final String TheName_enUS = "the tenant";
  public static final String SingularName_enUS = "tenant";
  public static final String PluralName_enUS = "tenants";
  public static final String NameActual_enUS = "current tenant";
  public static final String AllName_enUS = "all tenants";
  public static final String SearchAllNameBy_enUS = "search tenants by ";
  public static final String SearchAllName_enUS = "search tenants";
  public static final String Title_enUS = "tenants";
  public static final String ThePluralName_enUS = "the tenants";
  public static final String NoNameFound_enUS = "no tenant found";
  public static final String ApiUri_enUS = "/en-us/api/tenant";
  public static final String ApiUriSearchPage_enUS = "/en-us/search/tenant";
  public static final String ApiUriEditPage_enUS = "/en-us/edit/tenant/{tenantId}";
  public static final String OfName_enUS = "of tenant";
  public static final String ANameAdjective_enUS = "a tenant";
  public static final String NameAdjectiveSingular_enUS = "tenant";
  public static final String NameAdjectivePlural_enUS = "tenants";
  public static final String Search_enUS_OpenApiUri = "/en-us/api/tenant";
  public static final String Search_enUS_StringFormatUri = "/en-us/api/tenant";
  public static final String Search_enUS_StringFormatUrl = "%s/en-us/api/tenant";
  public static final String GET_enUS_OpenApiUri = "/en-us/api/tenant/{tenantId}";
  public static final String GET_enUS_StringFormatUri = "/en-us/api/tenant/%s";
  public static final String GET_enUS_StringFormatUrl = "%s/en-us/api/tenant/%s";
  public static final String PATCH_enUS_OpenApiUri = "/en-us/api/tenant";
  public static final String PATCH_enUS_StringFormatUri = "/en-us/api/tenant";
  public static final String PATCH_enUS_StringFormatUrl = "%s/en-us/api/tenant";
  public static final String POST_enUS_OpenApiUri = "/en-us/api/tenant";
  public static final String POST_enUS_StringFormatUri = "/en-us/api/tenant";
  public static final String POST_enUS_StringFormatUrl = "%s/en-us/api/tenant";
  public static final String DELETE_enUS_OpenApiUri = "/en-us/api/tenant/{tenantId}";
  public static final String DELETE_enUS_StringFormatUri = "/en-us/api/tenant/%s";
  public static final String DELETE_enUS_StringFormatUrl = "%s/en-us/api/tenant/%s";
  public static final String PUTImport_enUS_OpenApiUri = "/en-us/api/tenant-import";
  public static final String PUTImport_enUS_StringFormatUri = "/en-us/api/tenant-import";
  public static final String PUTImport_enUS_StringFormatUrl = "%s/en-us/api/tenant-import";
  public static final String SearchPage_enUS_OpenApiUri = "/en-us/search/tenant";
  public static final String SearchPage_enUS_StringFormatUri = "/en-us/search/tenant";
  public static final String SearchPage_enUS_StringFormatUrl = "%s/en-us/search/tenant";
  public static final String EditPage_enUS_OpenApiUri = "/en-us/edit/tenant/{tenantId}";
  public static final String EditPage_enUS_StringFormatUri = "/en-us/edit/tenant/%s";
  public static final String EditPage_enUS_StringFormatUrl = "%s/en-us/edit/tenant/%s";
  public static final String DELETEFilter_enUS_OpenApiUri = "/en-us/api/tenant";
  public static final String DELETEFilter_enUS_StringFormatUri = "/en-us/api/tenant";
  public static final String DELETEFilter_enUS_StringFormatUrl = "%s/en-us/api/tenant";

  public static final String Icon = "<i class=\"fa-regular fa-buildings\"></i>";

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
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.tenant.Tenant&fq=entiteVar_enUS_indexed_string:tenantName">Find the entity tenantName in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _tenantName(Wrap<String> w);

  public String getTenantName() {
    return tenantName;
  }
  public void setTenantName(String o) {
    this.tenantName = Tenant.staticSetTenantName(siteRequest_, o);
  }
  public static String staticSetTenantName(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected Tenant tenantNameInit() {
    Wrap<String> tenantNameWrap = new Wrap<String>().var("tenantName");
    if(tenantName == null) {
      _tenantName(tenantNameWrap);
      Optional.ofNullable(tenantNameWrap.getO()).ifPresent(o -> {
        setTenantName(o);
      });
    }
    return (Tenant)this;
  }

  public static String staticSearchTenantName(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrTenantName(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqTenantName(SiteRequest siteRequest_, String o) {
    return Tenant.staticSearchTenantName(siteRequest_, Tenant.staticSetTenantName(siteRequest_, o)).toString();
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
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.tenant.Tenant&fq=entiteVar_enUS_indexed_string:tenantId">Find the entity tenantId in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _tenantId(Wrap<String> w);

  public String getTenantId() {
    return tenantId;
  }
  public void setTenantId(String o) {
    this.tenantId = Tenant.staticSetTenantId(siteRequest_, o);
  }
  public static String staticSetTenantId(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected Tenant tenantIdInit() {
    Wrap<String> tenantIdWrap = new Wrap<String>().var("tenantId");
    if(tenantId == null) {
      _tenantId(tenantIdWrap);
      Optional.ofNullable(tenantIdWrap.getO()).ifPresent(o -> {
        setTenantId(o);
      });
    }
    return (Tenant)this;
  }

  public static String staticSearchTenantId(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrTenantId(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqTenantId(SiteRequest siteRequest_, String o) {
    return Tenant.staticSearchTenantId(siteRequest_, Tenant.staticSetTenantId(siteRequest_, o)).toString();
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
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.tenant.Tenant&fq=entiteVar_enUS_indexed_string:tenantResource">Find the entity tenantResource in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _tenantResource(Wrap<String> w);

  public String getTenantResource() {
    return tenantResource;
  }
  public void setTenantResource(String o) {
    this.tenantResource = Tenant.staticSetTenantResource(siteRequest_, o);
  }
  public static String staticSetTenantResource(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected Tenant tenantResourceInit() {
    Wrap<String> tenantResourceWrap = new Wrap<String>().var("tenantResource");
    if(tenantResource == null) {
      _tenantResource(tenantResourceWrap);
      Optional.ofNullable(tenantResourceWrap.getO()).ifPresent(o -> {
        setTenantResource(o);
      });
    }
    return (Tenant)this;
  }

  public static String staticSearchTenantResource(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrTenantResource(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqTenantResource(SiteRequest siteRequest_, String o) {
    return Tenant.staticSearchTenantResource(siteRequest_, Tenant.staticSetTenantResource(siteRequest_, o)).toString();
  }

  public String sqlTenantResource() {
    return tenantResource;
  }

  public static String staticJsonTenantResource(String tenantResource) {
    return tenantResource;
  }

	////////////
  // pageId //
	////////////


  /**
   *  The entity pageId
   *	 is defined as null before being initialized. 
   */
  @JsonProperty
  @JsonInclude(Include.NON_NULL)
  protected String pageId;

  /**
   * <br> The entity pageId
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.tenant.Tenant&fq=entiteVar_enUS_indexed_string:pageId">Find the entity pageId in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _pageId(Wrap<String> w);

  public String getPageId() {
    return pageId;
  }
  public void setPageId(String o) {
    this.pageId = Tenant.staticSetPageId(siteRequest_, o);
  }
  public static String staticSetPageId(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected Tenant pageIdInit() {
    Wrap<String> pageIdWrap = new Wrap<String>().var("pageId");
    if(pageId == null) {
      _pageId(pageIdWrap);
      Optional.ofNullable(pageIdWrap.getO()).ifPresent(o -> {
        setPageId(o);
      });
    }
    return (Tenant)this;
  }

  public static String staticSearchPageId(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrPageId(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqPageId(SiteRequest siteRequest_, String o) {
    return Tenant.staticSearchPageId(siteRequest_, Tenant.staticSetPageId(siteRequest_, o)).toString();
  }

  public String sqlPageId() {
    return pageId;
  }

  public static String staticJsonPageId(String pageId) {
    return pageId;
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
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.tenant.Tenant&fq=entiteVar_enUS_indexed_string:tenantDescription">Find the entity tenantDescription in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _tenantDescription(Wrap<String> w);

  public String getTenantDescription() {
    return tenantDescription;
  }
  public void setTenantDescription(String o) {
    this.tenantDescription = Tenant.staticSetTenantDescription(siteRequest_, o);
  }
  public static String staticSetTenantDescription(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected Tenant tenantDescriptionInit() {
    Wrap<String> tenantDescriptionWrap = new Wrap<String>().var("tenantDescription");
    if(tenantDescription == null) {
      _tenantDescription(tenantDescriptionWrap);
      Optional.ofNullable(tenantDescriptionWrap.getO()).ifPresent(o -> {
        setTenantDescription(o);
      });
    }
    return (Tenant)this;
  }

  public static String staticSearchTenantDescription(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrTenantDescription(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqTenantDescription(SiteRequest siteRequest_, String o) {
    return Tenant.staticSearchTenantDescription(siteRequest_, Tenant.staticSetTenantDescription(siteRequest_, o)).toString();
  }

  public String sqlTenantDescription() {
    return tenantDescription;
  }

  public static String staticJsonTenantDescription(String tenantDescription) {
    return tenantDescription;
  }

	///////////
  // hubId //
	///////////


  /**
   *  The entity hubId
   *	 is defined as null before being initialized. 
   */
  @JsonProperty
  @JsonInclude(Include.NON_NULL)
  protected String hubId;

  /**
   * <br> The entity hubId
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.tenant.Tenant&fq=entiteVar_enUS_indexed_string:hubId">Find the entity hubId in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _hubId(Wrap<String> w);

  public String getHubId() {
    return hubId;
  }
  public void setHubId(String o) {
    this.hubId = Tenant.staticSetHubId(siteRequest_, o);
  }
  public static String staticSetHubId(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected Tenant hubIdInit() {
    Wrap<String> hubIdWrap = new Wrap<String>().var("hubId");
    if(hubId == null) {
      _hubId(hubIdWrap);
      Optional.ofNullable(hubIdWrap.getO()).ifPresent(o -> {
        setHubId(o);
      });
    }
    return (Tenant)this;
  }

  public static String staticSearchHubId(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrHubId(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqHubId(SiteRequest siteRequest_, String o) {
    return Tenant.staticSearchHubId(siteRequest_, Tenant.staticSetHubId(siteRequest_, o)).toString();
  }

  public String sqlHubId() {
    return hubId;
  }

  public static String staticJsonHubId(String hubId) {
    return hubId;
  }

	/////////////////
  // clusterName //
	/////////////////


  /**
   *  The entity clusterName
   *	 is defined as null before being initialized. 
   */
  @JsonProperty
  @JsonInclude(Include.NON_NULL)
  protected String clusterName;

  /**
   * <br> The entity clusterName
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.tenant.Tenant&fq=entiteVar_enUS_indexed_string:clusterName">Find the entity clusterName in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _clusterName(Wrap<String> w);

  public String getClusterName() {
    return clusterName;
  }
  public void setClusterName(String o) {
    this.clusterName = Tenant.staticSetClusterName(siteRequest_, o);
  }
  public static String staticSetClusterName(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected Tenant clusterNameInit() {
    Wrap<String> clusterNameWrap = new Wrap<String>().var("clusterName");
    if(clusterName == null) {
      _clusterName(clusterNameWrap);
      Optional.ofNullable(clusterNameWrap.getO()).ifPresent(o -> {
        setClusterName(o);
      });
    }
    return (Tenant)this;
  }

  public static String staticSearchClusterName(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrClusterName(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqClusterName(SiteRequest siteRequest_, String o) {
    return Tenant.staticSearchClusterName(siteRequest_, Tenant.staticSetClusterName(siteRequest_, o)).toString();
  }

  public String sqlClusterName() {
    return clusterName;
  }

  public static String staticJsonClusterName(String clusterName) {
    return clusterName;
  }

	///////////////////////
  // aapOrganizationId //
	///////////////////////


  /**
   *  The entity aapOrganizationId
   *	 is defined as null before being initialized. 
   */
  @JsonProperty
  @JsonSerialize(using = ToStringSerializer.class)
  @JsonInclude(Include.NON_NULL)
  protected Long aapOrganizationId;

  /**
   * <br> The entity aapOrganizationId
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.tenant.Tenant&fq=entiteVar_enUS_indexed_string:aapOrganizationId">Find the entity aapOrganizationId in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _aapOrganizationId(Wrap<Long> w);

  public Long getAapOrganizationId() {
    return aapOrganizationId;
  }

  public void setAapOrganizationId(Long aapOrganizationId) {
    this.aapOrganizationId = aapOrganizationId;
  }
  @JsonIgnore
  public void setAapOrganizationId(String o) {
    this.aapOrganizationId = Tenant.staticSetAapOrganizationId(siteRequest_, o);
  }
  public static Long staticSetAapOrganizationId(SiteRequest siteRequest_, String o) {
    if(NumberUtils.isParsable(o))
      return Long.parseLong(o);
    return null;
  }
  protected Tenant aapOrganizationIdInit() {
    Wrap<Long> aapOrganizationIdWrap = new Wrap<Long>().var("aapOrganizationId");
    if(aapOrganizationId == null) {
      _aapOrganizationId(aapOrganizationIdWrap);
      Optional.ofNullable(aapOrganizationIdWrap.getO()).ifPresent(o -> {
        setAapOrganizationId(o);
      });
    }
    return (Tenant)this;
  }

  public static Long staticSearchAapOrganizationId(SiteRequest siteRequest_, Long o) {
    return o;
  }

  public static String staticSearchStrAapOrganizationId(SiteRequest siteRequest_, Long o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqAapOrganizationId(SiteRequest siteRequest_, String o) {
    return Tenant.staticSearchAapOrganizationId(siteRequest_, Tenant.staticSetAapOrganizationId(siteRequest_, o)).toString();
  }

  public Long sqlAapOrganizationId() {
    return aapOrganizationId;
  }

  public static String staticJsonAapOrganizationId(Long aapOrganizationId) {
    return Optional.ofNullable(aapOrganizationId).map(v -> v.toString()).orElse(null);
  }

  //////////////
  // initDeep //
  //////////////

  public Future<TenantGen<DEV>> promiseDeepTenant(SiteRequest siteRequest_) {
    setSiteRequest_(siteRequest_);
    return promiseDeepTenant();
  }

  public Future<TenantGen<DEV>> promiseDeepTenant() {
    Promise<TenantGen<DEV>> promise = Promise.promise();
    Promise<Void> promise2 = Promise.promise();
    promiseTenant(promise2);
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

  public Future<Void> promiseTenant(Promise<Void> promise) {
    Future.future(a -> a.complete()).compose(a -> {
      Promise<Void> promise2 = Promise.promise();
      try {
        tenantNameInit();
        tenantIdInit();
        tenantResourceInit();
        pageIdInit();
        tenantDescriptionInit();
        hubIdInit();
        clusterNameInit();
        aapOrganizationIdInit();
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

  @Override public Future<? extends TenantGen<DEV>> promiseDeepForClass(SiteRequest siteRequest_) {
    return promiseDeepTenant(siteRequest_);
  }

  /////////////////
  // siteRequest //
  /////////////////

  public void siteRequestTenant(SiteRequest siteRequest_) {
      super.siteRequestBaseModel(siteRequest_);
  }

  public void siteRequestForClass(SiteRequest siteRequest_) {
    siteRequestTenant(siteRequest_);
  }

  /////////////
  // obtain //
  /////////////

  @Override public Object obtainForClass(String var) {
    String[] vars = StringUtils.split(var, ".");
    Object o = null;
    for(String v : vars) {
      if(o == null)
        o = obtainTenant(v);
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
  public Object obtainTenant(String var) {
    Tenant oTenant = (Tenant)this;
    switch(var) {
      case "tenantName":
        return oTenant.tenantName;
      case "tenantId":
        return oTenant.tenantId;
      case "tenantResource":
        return oTenant.tenantResource;
      case "pageId":
        return oTenant.pageId;
      case "tenantDescription":
        return oTenant.tenantDescription;
      case "hubId":
        return oTenant.hubId;
      case "clusterName":
        return oTenant.clusterName;
      case "aapOrganizationId":
        return oTenant.aapOrganizationId;
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
        o = relateTenant(v, val);
      else if(o instanceof BaseModel) {
        BaseModel baseModel = (BaseModel)o;
        o = baseModel.relateForClass(v, val);
      }
    }
    return o != null;
  }
  public Object relateTenant(String var, Object val) {
    Tenant oTenant = (Tenant)this;
    switch(var) {
      default:
        return super.relateBaseModel(var, val);
    }
  }

  ///////////////
  // staticSet //
  ///////////////

  public static Object staticSetForClass(String entityVar, SiteRequest siteRequest_, String v, Tenant o) {
    return staticSetTenant(entityVar,  siteRequest_, v, o);
  }
  public static Object staticSetTenant(String entityVar, SiteRequest siteRequest_, String v, Tenant o) {
    switch(entityVar) {
    case "tenantName":
      return Tenant.staticSetTenantName(siteRequest_, v);
    case "tenantId":
      return Tenant.staticSetTenantId(siteRequest_, v);
    case "tenantResource":
      return Tenant.staticSetTenantResource(siteRequest_, v);
    case "pageId":
      return Tenant.staticSetPageId(siteRequest_, v);
    case "tenantDescription":
      return Tenant.staticSetTenantDescription(siteRequest_, v);
    case "hubId":
      return Tenant.staticSetHubId(siteRequest_, v);
    case "clusterName":
      return Tenant.staticSetClusterName(siteRequest_, v);
    case "aapOrganizationId":
      return Tenant.staticSetAapOrganizationId(siteRequest_, v);
      default:
        return BaseModel.staticSetBaseModel(entityVar,  siteRequest_, v, o);
    }
  }

  ////////////////
  // staticSearch //
  ////////////////

  public static Object staticSearchForClass(String entityVar, SiteRequest siteRequest_, Object o) {
    return staticSearchTenant(entityVar,  siteRequest_, o);
  }
  public static Object staticSearchTenant(String entityVar, SiteRequest siteRequest_, Object o) {
    switch(entityVar) {
    case "tenantName":
      return Tenant.staticSearchTenantName(siteRequest_, (String)o);
    case "tenantId":
      return Tenant.staticSearchTenantId(siteRequest_, (String)o);
    case "tenantResource":
      return Tenant.staticSearchTenantResource(siteRequest_, (String)o);
    case "pageId":
      return Tenant.staticSearchPageId(siteRequest_, (String)o);
    case "tenantDescription":
      return Tenant.staticSearchTenantDescription(siteRequest_, (String)o);
    case "hubId":
      return Tenant.staticSearchHubId(siteRequest_, (String)o);
    case "clusterName":
      return Tenant.staticSearchClusterName(siteRequest_, (String)o);
    case "aapOrganizationId":
      return Tenant.staticSearchAapOrganizationId(siteRequest_, (Long)o);
      default:
        return BaseModel.staticSearchBaseModel(entityVar,  siteRequest_, o);
    }
  }

  ///////////////////
  // staticSearchStr //
  ///////////////////

  public static String staticSearchStrForClass(String entityVar, SiteRequest siteRequest_, Object o) {
    return staticSearchStrTenant(entityVar,  siteRequest_, o);
  }
  public static String staticSearchStrTenant(String entityVar, SiteRequest siteRequest_, Object o) {
    switch(entityVar) {
    case "tenantName":
      return Tenant.staticSearchStrTenantName(siteRequest_, (String)o);
    case "tenantId":
      return Tenant.staticSearchStrTenantId(siteRequest_, (String)o);
    case "tenantResource":
      return Tenant.staticSearchStrTenantResource(siteRequest_, (String)o);
    case "pageId":
      return Tenant.staticSearchStrPageId(siteRequest_, (String)o);
    case "tenantDescription":
      return Tenant.staticSearchStrTenantDescription(siteRequest_, (String)o);
    case "hubId":
      return Tenant.staticSearchStrHubId(siteRequest_, (String)o);
    case "clusterName":
      return Tenant.staticSearchStrClusterName(siteRequest_, (String)o);
    case "aapOrganizationId":
      return Tenant.staticSearchStrAapOrganizationId(siteRequest_, (Long)o);
      default:
        return BaseModel.staticSearchStrBaseModel(entityVar,  siteRequest_, o);
    }
  }

  //////////////////
  // staticSearchFq //
  //////////////////

  public static String staticSearchFqForClass(String entityVar, SiteRequest siteRequest_, String o) {
    return staticSearchFqTenant(entityVar,  siteRequest_, o);
  }
  public static String staticSearchFqTenant(String entityVar, SiteRequest siteRequest_, String o) {
    switch(entityVar) {
    case "tenantName":
      return Tenant.staticSearchFqTenantName(siteRequest_, o);
    case "tenantId":
      return Tenant.staticSearchFqTenantId(siteRequest_, o);
    case "tenantResource":
      return Tenant.staticSearchFqTenantResource(siteRequest_, o);
    case "pageId":
      return Tenant.staticSearchFqPageId(siteRequest_, o);
    case "tenantDescription":
      return Tenant.staticSearchFqTenantDescription(siteRequest_, o);
    case "hubId":
      return Tenant.staticSearchFqHubId(siteRequest_, o);
    case "clusterName":
      return Tenant.staticSearchFqClusterName(siteRequest_, o);
    case "aapOrganizationId":
      return Tenant.staticSearchFqAapOrganizationId(siteRequest_, o);
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
          o = persistTenant(v, val);
        else if(o instanceof BaseModel) {
          BaseModel oBaseModel = (BaseModel)o;
          o = oBaseModel.persistForClass(v, val);
        }
      }
    }
    return o != null;
  }
  public Object persistTenant(String var, Object val) {
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
      } else if("pageid".equals(varLower)) {
        if(val instanceof String) {
          setPageId((String)val);
        }
        saves.add("pageId");
        return val;
      } else if("tenantdescription".equals(varLower)) {
        if(val instanceof String) {
          setTenantDescription((String)val);
        }
        saves.add("tenantDescription");
        return val;
      } else if("hubid".equals(varLower)) {
        if(val instanceof String) {
          setHubId((String)val);
        }
        saves.add("hubId");
        return val;
      } else if("clustername".equals(varLower)) {
        if(val instanceof String) {
          setClusterName((String)val);
        }
        saves.add("clusterName");
        return val;
      } else if("aaporganizationid".equals(varLower)) {
        if(val instanceof Long) {
          setAapOrganizationId((Long)val);
        } else {
          setAapOrganizationId(val == null ? null : val.toString());
        }
        saves.add("aapOrganizationId");
        return val;
    } else {
      return super.persistBaseModel(var, val);
    }
  }

  /////////////
  // populate //
  /////////////

  @Override public void populateForClass(SolrResponse.Doc doc) {
    populateTenant(doc);
  }
  public void populateTenant(SolrResponse.Doc doc) {
    Tenant oTenant = (Tenant)this;
    saves = Optional.ofNullable((ArrayList<String>)doc.get("saves_docvalues_strings")).orElse(new ArrayList<String>());
    if(saves != null) {

      if(saves.contains("tenantName")) {
        String tenantName = (String)doc.get("tenantName_docvalues_string");
        if(tenantName != null)
          oTenant.setTenantName(tenantName);
      }

      if(saves.contains("tenantId")) {
        String tenantId = (String)doc.get("tenantId_docvalues_string");
        if(tenantId != null)
          oTenant.setTenantId(tenantId);
      }

      if(saves.contains("tenantResource")) {
        String tenantResource = (String)doc.get("tenantResource_docvalues_string");
        if(tenantResource != null)
          oTenant.setTenantResource(tenantResource);
      }

      if(saves.contains("pageId")) {
        String pageId = (String)doc.get("pageId_docvalues_string");
        if(pageId != null)
          oTenant.setPageId(pageId);
      }

      if(saves.contains("tenantDescription")) {
        String tenantDescription = (String)doc.get("tenantDescription_docvalues_string");
        if(tenantDescription != null)
          oTenant.setTenantDescription(tenantDescription);
      }

      if(saves.contains("hubId")) {
        String hubId = (String)doc.get("hubId_docvalues_string");
        if(hubId != null)
          oTenant.setHubId(hubId);
      }

      if(saves.contains("clusterName")) {
        String clusterName = (String)doc.get("clusterName_docvalues_string");
        if(clusterName != null)
          oTenant.setClusterName(clusterName);
      }

      if(saves.contains("aapOrganizationId")) {
        Long aapOrganizationId = (Long)doc.get("aapOrganizationId_docvalues_long");
        if(aapOrganizationId != null)
          oTenant.setAapOrganizationId(aapOrganizationId);
      }
    }

    super.populateBaseModel(doc);
  }

  public void indexTenant(JsonObject doc) {
    if(tenantName != null) {
      doc.put("tenantName_docvalues_string", tenantName);
    }
    if(tenantId != null) {
      doc.put("tenantId_docvalues_string", tenantId);
    }
    if(tenantResource != null) {
      doc.put("tenantResource_docvalues_string", tenantResource);
    }
    if(pageId != null) {
      doc.put("pageId_docvalues_string", pageId);
    }
    if(tenantDescription != null) {
      doc.put("tenantDescription_docvalues_string", tenantDescription);
    }
    if(hubId != null) {
      doc.put("hubId_docvalues_string", hubId);
    }
    if(clusterName != null) {
      doc.put("clusterName_docvalues_string", clusterName);
    }
    if(aapOrganizationId != null) {
      doc.put("aapOrganizationId_docvalues_long", aapOrganizationId);
    }
    super.indexBaseModel(doc);

	}

  public static String varStoredTenant(String entityVar) {
    switch(entityVar) {
      case "tenantName":
        return "tenantName_docvalues_string";
      case "tenantId":
        return "tenantId_docvalues_string";
      case "tenantResource":
        return "tenantResource_docvalues_string";
      case "pageId":
        return "pageId_docvalues_string";
      case "tenantDescription":
        return "tenantDescription_docvalues_string";
      case "hubId":
        return "hubId_docvalues_string";
      case "clusterName":
        return "clusterName_docvalues_string";
      case "aapOrganizationId":
        return "aapOrganizationId_docvalues_long";
      default:
        return BaseModel.varStoredBaseModel(entityVar);
    }
  }

  public static String varIndexedTenant(String entityVar) {
    switch(entityVar) {
      case "tenantName":
        return "tenantName_docvalues_string";
      case "tenantId":
        return "tenantId_docvalues_string";
      case "tenantResource":
        return "tenantResource_docvalues_string";
      case "pageId":
        return "pageId_docvalues_string";
      case "tenantDescription":
        return "tenantDescription_docvalues_string";
      case "hubId":
        return "hubId_docvalues_string";
      case "clusterName":
        return "clusterName_docvalues_string";
      case "aapOrganizationId":
        return "aapOrganizationId_docvalues_long";
      default:
        return BaseModel.varIndexedBaseModel(entityVar);
    }
  }

  public static String searchVarTenant(String searchVar) {
    switch(searchVar) {
      case "tenantName_docvalues_string":
        return "tenantName";
      case "tenantId_docvalues_string":
        return "tenantId";
      case "tenantResource_docvalues_string":
        return "tenantResource";
      case "pageId_docvalues_string":
        return "pageId";
      case "tenantDescription_docvalues_string":
        return "tenantDescription";
      case "hubId_docvalues_string":
        return "hubId";
      case "clusterName_docvalues_string":
        return "clusterName";
      case "aapOrganizationId_docvalues_long":
        return "aapOrganizationId";
      default:
        return BaseModel.searchVarBaseModel(searchVar);
    }
  }

  public static String varSearchTenant(String entityVar) {
    switch(entityVar) {
      default:
        return BaseModel.varSearchBaseModel(entityVar);
    }
  }

  public static String varSuggestedTenant(String entityVar) {
    switch(entityVar) {
      default:
        return BaseModel.varSuggestedBaseModel(entityVar);
    }
  }

  /////////////
  // store //
  /////////////

  @Override public void storeForClass(SolrResponse.Doc doc) {
    storeTenant(doc);
  }
  public void storeTenant(SolrResponse.Doc doc) {
    Tenant oTenant = (Tenant)this;
    SiteRequest siteRequest = oTenant.getSiteRequest_();

    oTenant.setTenantName(Optional.ofNullable(doc.get("tenantName_docvalues_string")).map(v -> v.toString()).orElse(null));
    oTenant.setTenantId(Optional.ofNullable(doc.get("tenantId_docvalues_string")).map(v -> v.toString()).orElse(null));
    oTenant.setTenantResource(Optional.ofNullable(doc.get("tenantResource_docvalues_string")).map(v -> v.toString()).orElse(null));
    oTenant.setPageId(Optional.ofNullable(doc.get("pageId_docvalues_string")).map(v -> v.toString()).orElse(null));
    oTenant.setTenantDescription(Optional.ofNullable(doc.get("tenantDescription_docvalues_string")).map(v -> v.toString()).orElse(null));
    oTenant.setHubId(Optional.ofNullable(doc.get("hubId_docvalues_string")).map(v -> v.toString()).orElse(null));
    oTenant.setClusterName(Optional.ofNullable(doc.get("clusterName_docvalues_string")).map(v -> v.toString()).orElse(null));
    oTenant.setAapOrganizationId(Optional.ofNullable(doc.get("aapOrganizationId_docvalues_long")).map(v -> v.toString()).orElse(null));

    super.storeBaseModel(doc);
  }

  //////////////////
  // apiRequest //
  //////////////////

  public void apiRequestTenant() {
    ApiRequest apiRequest = Optional.ofNullable(siteRequest_).map(r -> r.getApiRequest_()).orElse(null);
    Object o = Optional.ofNullable(apiRequest).map(ApiRequest::getOriginal).orElse(null);
    if(o != null && o instanceof Tenant) {
      Tenant original = (Tenant)o;
      if(!Objects.equals(tenantName, original.getTenantName()))
        apiRequest.addVars("tenantName");
      if(!Objects.equals(tenantId, original.getTenantId()))
        apiRequest.addVars("tenantId");
      if(!Objects.equals(tenantResource, original.getTenantResource()))
        apiRequest.addVars("tenantResource");
      if(!Objects.equals(pageId, original.getPageId()))
        apiRequest.addVars("pageId");
      if(!Objects.equals(tenantDescription, original.getTenantDescription()))
        apiRequest.addVars("tenantDescription");
      if(!Objects.equals(hubId, original.getHubId()))
        apiRequest.addVars("hubId");
      if(!Objects.equals(clusterName, original.getClusterName()))
        apiRequest.addVars("clusterName");
      if(!Objects.equals(aapOrganizationId, original.getAapOrganizationId()))
        apiRequest.addVars("aapOrganizationId");
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
    sb.append(Optional.ofNullable(tenantResource).map(v -> "tenantResource: \"" + v + "\"\n" ).orElse(""));
    sb.append(Optional.ofNullable(pageId).map(v -> "pageId: \"" + v + "\"\n" ).orElse(""));
    sb.append(Optional.ofNullable(tenantDescription).map(v -> "tenantDescription: \"" + v + "\"\n" ).orElse(""));
    sb.append(Optional.ofNullable(hubId).map(v -> "hubId: \"" + v + "\"\n" ).orElse(""));
    sb.append(Optional.ofNullable(clusterName).map(v -> "clusterName: \"" + v + "\"\n" ).orElse(""));
    sb.append(Optional.ofNullable(aapOrganizationId).map(v -> "aapOrganizationId: " + v + "\n").orElse(""));
    return sb.toString();
  }

  public static final String CLASS_SIMPLE_NAME = "Tenant";
  public static final String CLASS_CANONICAL_NAME = "org.computate.dcm.model.eda.tenant.Tenant";
  public static final String CLASS_AUTH_RESOURCE = "TENANT";
  public static final String CLASS_API_ADDRESS_Tenant = "dcm-enUS-Tenant";
  public static String getClassApiAddress() {
    return CLASS_API_ADDRESS_Tenant;
  }
  public static final String VAR_tenantName = "tenantName";
  public static final String VAR_tenantId = "tenantId";
  public static final String VAR_tenantResource = "tenantResource";
  public static final String VAR_pageId = "pageId";
  public static final String VAR_tenantDescription = "tenantDescription";
  public static final String VAR_hubId = "hubId";
  public static final String VAR_clusterName = "clusterName";
  public static final String VAR_aapOrganizationId = "aapOrganizationId";

  public static List<String> varsQForClass() {
    return Tenant.varsQTenant(new ArrayList<String>());
  }
  public static List<String> varsQTenant(List<String> vars) {
    BaseModel.varsQBaseModel(vars);
    return vars;
  }

  public static List<String> varsFqForClass() {
    return Tenant.varsFqTenant(new ArrayList<String>());
  }
  public static List<String> varsFqTenant(List<String> vars) {
    vars.add(VAR_tenantName);
    vars.add(VAR_tenantId);
    vars.add(VAR_tenantResource);
    vars.add(VAR_pageId);
    vars.add(VAR_tenantDescription);
    vars.add(VAR_hubId);
    vars.add(VAR_clusterName);
    BaseModel.varsFqBaseModel(vars);
    return vars;
  }

  public static List<String> varsRangeForClass() {
    return Tenant.varsRangeTenant(new ArrayList<String>());
  }
  public static List<String> varsRangeTenant(List<String> vars) {
    BaseModel.varsRangeBaseModel(vars);
    return vars;
  }

  public static final String DISPLAY_NAME_tenantName = "tenant name";
  public static final String DISPLAY_NAME_tenantId = "tenant ID";
  public static final String DISPLAY_NAME_tenantResource = "tenant auth resource";
  public static final String DISPLAY_NAME_pageId = "Page ID";
  public static final String DISPLAY_NAME_tenantDescription = "description";
  public static final String DISPLAY_NAME_hubId = "ACM Hub";
  public static final String DISPLAY_NAME_clusterName = "cluster name";
  public static final String DISPLAY_NAME_aapOrganizationId = "AAP ID";

  @Override
  public String idForClass() {
    return tenantId;
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
    return Tenant.NameAdjectiveSingular_enUS;
  }

  @Override
  public String descriptionForClass() {
    return tenantDescription;
  }

  @Override
  public String enUSStringFormatUrlEditPageForClass() {
    return "%s/en-us/edit/tenant/%s";
  }

  @Override
  public String enUSStringFormatUrlDisplayPageForClass() {
    return null;
  }

  @Override
  public String enUSStringFormatUrlUserPageForClass() {
    return null;
  }

  @Override
  public String enUSStringFormatUrlDownloadForClass() {
    return null;
  }

  public static String displayNameForClass(String var) {
    return Tenant.displayNameTenant(var);
  }
  public static String displayNameTenant(String var) {
    switch(var) {
    case VAR_tenantName:
      return DISPLAY_NAME_tenantName;
    case VAR_tenantId:
      return DISPLAY_NAME_tenantId;
    case VAR_tenantResource:
      return DISPLAY_NAME_tenantResource;
    case VAR_pageId:
      return DISPLAY_NAME_pageId;
    case VAR_tenantDescription:
      return DISPLAY_NAME_tenantDescription;
    case VAR_hubId:
      return DISPLAY_NAME_hubId;
    case VAR_clusterName:
      return DISPLAY_NAME_clusterName;
    case VAR_aapOrganizationId:
      return DISPLAY_NAME_aapOrganizationId;
    default:
      return BaseModel.displayNameBaseModel(var);
    }
  }

  public static String descriptionTenant(String var) {
    if(var == null)
      return null;
    switch(var) {
    case VAR_tenantName:
      return "The name of this tenant";
    case VAR_tenantId:
      return "The ID of this tenant";
    case VAR_tenantResource:
      return "The unique authorization resource for the tenant for multi-tenancy";
    case VAR_pageId:
      return "The ID for this page. ";
    case VAR_tenantDescription:
      return "A description of this tenant";
    case VAR_hubId:
      return "The ID of the ACM Hub for this cluster in Prometheus Keycloak Proxy. ";
    case VAR_clusterName:
      return "The name of this cluster";
    case VAR_aapOrganizationId:
      return "The Ansible Automation Platform ID of the organization. ";
      default:
        return BaseModel.descriptionBaseModel(var);
    }
  }

  public static String classSimpleNameTenant(String var) {
    switch(var) {
    case VAR_tenantName:
      return "String";
    case VAR_tenantId:
      return "String";
    case VAR_tenantResource:
      return "String";
    case VAR_pageId:
      return "String";
    case VAR_tenantDescription:
      return "String";
    case VAR_hubId:
      return "String";
    case VAR_clusterName:
      return "String";
    case VAR_aapOrganizationId:
      return "Long";
      default:
        return BaseModel.classSimpleNameBaseModel(var);
    }
  }

  public static Integer htmColumnTenant(String var) {
    switch(var) {
    case VAR_tenantName:
      return 1;
    case VAR_tenantDescription:
      return 3;
      default:
        return BaseModel.htmColumnBaseModel(var);
    }
  }

  public static Integer htmRowTenant(String var) {
    switch(var) {
    case VAR_tenantName:
      return 3;
    case VAR_tenantId:
      return 3;
    case VAR_pageId:
      return 99;
    case VAR_tenantDescription:
      return 3;
      default:
        return BaseModel.htmRowBaseModel(var);
    }
  }

  public static Integer htmCellTenant(String var) {
    switch(var) {
    case VAR_tenantName:
      return 1;
    case VAR_tenantId:
      return 2;
    case VAR_pageId:
      return 1;
    case VAR_tenantDescription:
      return 4;
      default:
        return BaseModel.htmCellBaseModel(var);
    }
  }

  public static Integer lengthMinTenant(String var) {
    switch(var) {
      default:
        return BaseModel.lengthMinBaseModel(var);
    }
  }

  public static Integer lengthMaxTenant(String var) {
    switch(var) {
      default:
        return BaseModel.lengthMaxBaseModel(var);
    }
  }

  public static Integer maxTenant(String var) {
    switch(var) {
      default:
        return BaseModel.maxBaseModel(var);
    }
  }

  public static Integer minTenant(String var) {
    switch(var) {
      default:
        return BaseModel.minBaseModel(var);
    }
  }
}
