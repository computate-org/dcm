package org.computate.dcm.model.eda.tenant.discovered;

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
import org.computate.dcm.model.eda.tenant.intent.TenantIntent;
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
 * <li>You can add a class comment "{@inheritDoc}" if you wish to inherit the helpful inherited class comments from class TenantDiscoveredGen into the class TenantDiscovered. 
 * </li><li><p>
 *   You can add a class comment <kbd><b>Rows: 100</b></kbd> if you wish for the discovered tenant API to return more or less than 10 results by default. 
 *   In this case, the API will return 100 results from the API instead of 10 by default. 
 *   Each API has built in pagination of the search results to ensure a user can query all the data a page at a time without running the application out of memory. 
 * </p>
 * </li>
 * <h3>About the TenantDiscovered class and it's generated class TenantDiscoveredGen&lt;Tenant&gt;: </h3>extends TenantDiscoveredGen
 * <p>
 * This Java class extends a generated Java class TenantDiscoveredGen built by the <a href="https://github.com/computate-org/computate">https://github.com/computate-org/computate</a> project. 
 * Whenever this Java class is modified or touched, the watch service installed as described in the README, indexes all the information about this Java class in a local Apache Solr Search Engine. 
 * If you are running the service, you can see the indexed data about this Java Class here: 
 * </p>
 * <p><a href="https://solr.apps-crc.testing/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.tenant.discovered.TenantDiscovered">Find the class TenantDiscovered in Solr. </a></p>
 * <p>
 * The extended class ending with "Gen" did not exist at first, but was automatically created by the same watch service based on the data retrieved from the local Apache Server search engine. 
 * The extended class contains many generated fields, getters, setters, initialization code, and helper methods to help build a website and API fast, reactive, and scalable. 
 * </p>
 * extends TenantDiscoveredGen<Tenant>
 * <p>This <code>class TenantDiscovered extends TenantDiscoveredGen&lt;Tenant&gt;</code>, which means it extends a newly generated TenantDiscoveredGen. 
 * The generated <code>class TenantDiscoveredGen extends Tenant</code> which means that TenantDiscovered extends TenantDiscoveredGen which extends Tenant. 
 * This generated inheritance is a powerful feature that allows a lot of boiler plate code to be created for you automatically while still preserving inheritance through the power of Java Generic classes. 
 * </p>
 * <h2>
 *   Api: true
 * </h2>
 * <p>
 *   This class contains a comment <kbd><b>Api: true</b></kbd>, which means this class will have Java Vert.x API backend code generated for these objects. 
 * </p>
 * <h2>ApiTag.enUS: true</h2>
 * <p>This class contains a comment <kbd><b>ApiTag: discovered tenants</b></kbd>, which groups all of the OpenAPIs for TenantDiscovered objects under the tag "discovered tenants". 
 * </p>
 * <h2>ApiUri.enUS: /en-us/api/intent/discovered</h2>
 * <p>This class contains a comment <kbd><b>ApiUri: /en-us/api/intent/discovered</b></kbd>, which defines the base API URI for TenantDiscovered objects as "/en-us/api/intent/discovered" in the OpenAPI spec. 
 * </p>
 * <h2>Color: null</h2>
 * <h2>Indexed: true</h2>
 * <p>This class contains a comment <kbd><b>Indexed: true</b></kbd>, which means this class will be indexed in the search engine. 
 * Every protected void method that begins with "_" that is marked to be searched with a comment like "Indexed: true", "Stored: true", or "DocValues: true" will be indexed in the search engine. 
 * </p>
 * <h2>{@inheritDoc}</h2>
 * <p>By adding a class comment "{@inheritDoc}", the TenantDiscovered class will inherit the helpful inherited class comments from the super class TenantDiscoveredGen. 
 * </p>
 * <h2>
 *   Rows: 10
 * </h2>
 * <p>This class contains a comment <kbd><b>Rows: 10</b></kbd>, which means the discovered tenant API will return a default of 10 results instead of 10 by default. 
 * Each API has built in pagination of the search results to ensure a user can query all the data a page at a time without running the application out of memory. 
 * </p>
 * <p>
 *   You can add a class comment <kbd><b>Rows: 100</b></kbd> if you wish for the discovered tenant API to return more or less than 10 results by default. 
 *   In this case, the API will return 100 results from the API instead of 10 by default. 
 *   Each API has built in pagination of the search results to ensure a user can query all the data a page at a time without running the application out of memory. 
 * </p>
 * <h2>
 *   Order: 144
 * </h2>
 * <p>
 *   This class contains a comment <kbd><b>Order: 144</b></kbd>, 
 *   which means this class will be sorted by the given number 144 
 *   ascending when code that relates to multiple classes at the same time is generated. 
 * </p>
 * <h2>SqlOrder: 144</h2>
 * <p>This class contains a comment <kbd><b>SqlOrder: 144</b></kbd>, which means this class will be sorted by the given number 144 ascending when SQL code to create and drop the tables is generated. 
 * </p>
 * <h2>Model: true</h2>
 * <p>This class contains a comment <kbd><b>Model: true</b></kbd>, which means this class will be stored in the database. 
 * Every protected void method that begins with "_" that contains a "Persist: true" comment will be a persisted field in the database table. 
 * </p>
 * <h2>Page: true</h2>
 * <p>This class contains a comment <kbd><b>Page: true</b></kbd>, which means this class will have webpage code generated for these objects. 
 * Java Vert.x backend API code, Handlebars HTML template frontend code, and JavaScript code will all generated and can be extended. 
 * This creates a new Java class org.computate.dcm.model.eda.tenant.discovered.TenantDiscoveredPage. 
 * </p>
 * <h2>SuperPage.enUS: PageLayout</h2>
 * <p>This class contains a comment <kbd><b>SuperPage.enUS: PageLayout</b></kbd>, which identifies the Java super class of the page code by it's class simple name "PageLayout". 
 * This means that the newly created class org.computate.dcm.model.eda.tenant.discovered.TenantDiscoveredPage extends org.computate.dcm.page.PageLayout. 
 * </p>
 * <h2>Promise: true</h2>
 * <p>
 *   This class contains a comment <kbd><b>Promise: true</b></kbd>
 *   Sometimes a Java class must be initialized asynchronously when it involves calling a blocking API. 
 *   This means that the TenantDiscovered Java class has promiseDeep methods which must be initialized asynchronously as a Vert.x Promise  instead of initDeep methods which are a simple non-asynchronous method. 
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
 * <h2>AName.enUS: a discovered tenant</h2>
 * <p>This class contains a comment <kbd><b>AName.enUS: a discovered tenant</b></kbd>, which identifies the language context to describe a TenantDiscovered as "a discovered tenant". 
 * </p>
 * <p>
 * Delete the class TenantDiscovered in Solr: 
 * <pre>
 * curl -k 'https://solr.apps-crc.testing/solr/computate/update?commitWithin=1000&amp;overwrite=true&amp;wt=json' -X POST -H 'Content-type: text/xml' -u "admin:$(oc -n solr get secret/solr-solrcloud-security-bootstrap -o jsonpath={.data.admin} | base64 -d)" --data-raw '&lt;delete&gt;&lt;query&gt;classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.tenant.discovered.TenantDiscovered&lt;/query&gt;&lt;/delete&gt;'
 * </pre>
 * </p>
 * <p>
 * Delete  the package org.computate.dcm.model.eda.tenant.discovered in Solr: 
 * <pre>
 * curl -k 'https://solr.apps-crc.testing/solr/computate/update?commitWithin=1000&amp;overwrite=true&amp;wt=json' -X POST -H 'Content-type: text/xml' -u "admin:$(oc -n solr get secret/solr-solrcloud-security-bootstrap -o jsonpath={.data.admin} | base64 -d)" --data-raw '&lt;delete&gt;&lt;query&gt;classeNomEnsemble_enUS_indexed_string:org.computate.dcm.model.eda.tenant.discovered&lt;/query&gt;&lt;/delete&gt;'
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
public abstract class TenantDiscoveredGen<DEV> extends Tenant {
  protected static final Logger LOG = LoggerFactory.getLogger(TenantDiscovered.class);

  public static final String Description_enUS = "An approved and discovered Tenant. Tenants are separate organizations sharing the same cloud resources. ";
  public static final String AName_enUS = "a discovered tenant";
  public static final String This_enUS = "this ";
  public static final String ThisName_enUS = "this discovered tenant";
  public static final String A_enUS = "a ";
  public static final String TheName_enUS = "the discovered tenant";
  public static final String SingularName_enUS = "discovered tenant";
  public static final String PluralName_enUS = "discovered tenants";
  public static final String NameActual_enUS = "current discovered tenant";
  public static final String AllName_enUS = "all discovered tenants";
  public static final String SearchAllNameBy_enUS = "search discovered tenants by ";
  public static final String SearchAllName_enUS = "search discovered tenants";
  public static final String Title_enUS = "discovered tenants";
  public static final String ThePluralName_enUS = "the discovered tenants";
  public static final String NoNameFound_enUS = "no discovered tenant found";
  public static final String ApiUri_enUS = "/en-us/api/intent/discovered";
  public static final String ApiUriSearchPage_enUS = "/en-us/search/discovered/tenant";
  public static final String ApiUriEditPage_enUS = "/en-us/edit/discovered/tenant/{tenantResource}";
  public static final String OfName_enUS = "of discovered tenant";
  public static final String ANameAdjective_enUS = "a discovered tenant";
  public static final String NameAdjectiveSingular_enUS = "discovered tenant";
  public static final String NameAdjectivePlural_enUS = "discovered tenants";
  public static final String Search_enUS_OpenApiUri = "/en-us/api/intent/discovered";
  public static final String Search_enUS_StringFormatUri = "/en-us/api/intent/discovered";
  public static final String Search_enUS_StringFormatUrl = "%s/en-us/api/intent/discovered";
  public static final String GET_enUS_OpenApiUri = "/en-us/api/intent/discovered/{tenantResource}";
  public static final String GET_enUS_StringFormatUri = "/en-us/api/intent/discovered/%s";
  public static final String GET_enUS_StringFormatUrl = "%s/en-us/api/intent/discovered/%s";
  public static final String PATCH_enUS_OpenApiUri = "/en-us/api/intent/discovered";
  public static final String PATCH_enUS_StringFormatUri = "/en-us/api/intent/discovered";
  public static final String PATCH_enUS_StringFormatUrl = "%s/en-us/api/intent/discovered";
  public static final String POST_enUS_OpenApiUri = "/en-us/api/intent/discovered";
  public static final String POST_enUS_StringFormatUri = "/en-us/api/intent/discovered";
  public static final String POST_enUS_StringFormatUrl = "%s/en-us/api/intent/discovered";
  public static final String DELETE_enUS_OpenApiUri = "/en-us/api/intent/discovered/{tenantResource}";
  public static final String DELETE_enUS_StringFormatUri = "/en-us/api/intent/discovered/%s";
  public static final String DELETE_enUS_StringFormatUrl = "%s/en-us/api/intent/discovered/%s";
  public static final String PUTImport_enUS_OpenApiUri = "/en-us/api/intent/discovered-import";
  public static final String PUTImport_enUS_StringFormatUri = "/en-us/api/intent/discovered-import";
  public static final String PUTImport_enUS_StringFormatUrl = "%s/en-us/api/intent/discovered-import";
  public static final String SearchPage_enUS_OpenApiUri = "/en-us/search/discovered/tenant";
  public static final String SearchPage_enUS_StringFormatUri = "/en-us/search/discovered/tenant";
  public static final String SearchPage_enUS_StringFormatUrl = "%s/en-us/search/discovered/tenant";
  public static final String EditPage_enUS_OpenApiUri = "/en-us/edit/discovered/tenant/{tenantResource}";
  public static final String EditPage_enUS_StringFormatUri = "/en-us/edit/discovered/tenant/%s";
  public static final String EditPage_enUS_StringFormatUrl = "%s/en-us/edit/discovered/tenant/%s";
  public static final String DELETEFilter_enUS_OpenApiUri = "/en-us/api/intent/discovered";
  public static final String DELETEFilter_enUS_StringFormatUri = "/en-us/api/intent/discovered";
  public static final String DELETEFilter_enUS_StringFormatUrl = "%s/en-us/api/intent/discovered";

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
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.tenant.discovered.TenantDiscovered&fq=entiteVar_enUS_indexed_string:tenantName">Find the entity tenantName in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _tenantName(Wrap<String> w);

  public String getTenantName() {
    return tenantName;
  }
  public void setTenantName(String o) {
    this.tenantName = TenantDiscovered.staticSetTenantName(siteRequest_, o);
  }
  public static String staticSetTenantName(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected TenantDiscovered tenantNameInit() {
    Wrap<String> tenantNameWrap = new Wrap<String>().var("tenantName");
    if(tenantName == null) {
      _tenantName(tenantNameWrap);
      Optional.ofNullable(tenantNameWrap.getO()).ifPresent(o -> {
        setTenantName(o);
      });
    }
    return (TenantDiscovered)this;
  }

  public static String staticSearchTenantName(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrTenantName(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqTenantName(SiteRequest siteRequest_, String o) {
    return TenantDiscovered.staticSearchTenantName(siteRequest_, TenantDiscovered.staticSetTenantName(siteRequest_, o)).toString();
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
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.tenant.discovered.TenantDiscovered&fq=entiteVar_enUS_indexed_string:tenantId">Find the entity tenantId in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _tenantId(Wrap<String> w);

  public String getTenantId() {
    return tenantId;
  }
  public void setTenantId(String o) {
    this.tenantId = TenantDiscovered.staticSetTenantId(siteRequest_, o);
  }
  public static String staticSetTenantId(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected TenantDiscovered tenantIdInit() {
    Wrap<String> tenantIdWrap = new Wrap<String>().var("tenantId");
    if(tenantId == null) {
      _tenantId(tenantIdWrap);
      Optional.ofNullable(tenantIdWrap.getO()).ifPresent(o -> {
        setTenantId(o);
      });
    }
    return (TenantDiscovered)this;
  }

  public static String staticSearchTenantId(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrTenantId(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqTenantId(SiteRequest siteRequest_, String o) {
    return TenantDiscovered.staticSearchTenantId(siteRequest_, TenantDiscovered.staticSetTenantId(siteRequest_, o)).toString();
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
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.tenant.discovered.TenantDiscovered&fq=entiteVar_enUS_indexed_string:requestedId">Find the entity requestedId in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _requestedId(Wrap<String> w);

  public String getRequestedId() {
    return requestedId;
  }
  public void setRequestedId(String o) {
    this.requestedId = TenantDiscovered.staticSetRequestedId(siteRequest_, o);
  }
  public static String staticSetRequestedId(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected TenantDiscovered requestedIdInit() {
    Wrap<String> requestedIdWrap = new Wrap<String>().var("requestedId");
    if(requestedId == null) {
      _requestedId(requestedIdWrap);
      Optional.ofNullable(requestedIdWrap.getO()).ifPresent(o -> {
        setRequestedId(o);
      });
    }
    return (TenantDiscovered)this;
  }

  public static String staticSearchRequestedId(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrRequestedId(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqRequestedId(SiteRequest siteRequest_, String o) {
    return TenantDiscovered.staticSearchRequestedId(siteRequest_, TenantDiscovered.staticSetRequestedId(siteRequest_, o)).toString();
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
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.tenant.discovered.TenantDiscovered&fq=entiteVar_enUS_indexed_string:tenantResource">Find the entity tenantResource in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _tenantResource(Wrap<String> w);

  public String getTenantResource() {
    return tenantResource;
  }
  public void setTenantResource(String o) {
    this.tenantResource = TenantDiscovered.staticSetTenantResource(siteRequest_, o);
  }
  public static String staticSetTenantResource(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected TenantDiscovered tenantResourceInit() {
    Wrap<String> tenantResourceWrap = new Wrap<String>().var("tenantResource");
    if(tenantResource == null) {
      _tenantResource(tenantResourceWrap);
      Optional.ofNullable(tenantResourceWrap.getO()).ifPresent(o -> {
        setTenantResource(o);
      });
    }
    return (TenantDiscovered)this;
  }

  public static String staticSearchTenantResource(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrTenantResource(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqTenantResource(SiteRequest siteRequest_, String o) {
    return TenantDiscovered.staticSearchTenantResource(siteRequest_, TenantDiscovered.staticSetTenantResource(siteRequest_, o)).toString();
  }

  public String sqlTenantResource() {
    return tenantResource;
  }

  public static String staticJsonTenantResource(String tenantResource) {
    return tenantResource;
  }

	///////////////////////
  // discoveredByEmail //
	///////////////////////


  /**
   *  The entity discoveredByEmail
   *	 is defined as null before being initialized. 
   */
  @JsonProperty
  @JsonInclude(Include.NON_NULL)
  protected String discoveredByEmail;

  /**
   * <br> The entity discoveredByEmail
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.tenant.discovered.TenantDiscovered&fq=entiteVar_enUS_indexed_string:discoveredByEmail">Find the entity discoveredByEmail in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _discoveredByEmail(Wrap<String> w);

  public String getDiscoveredByEmail() {
    return discoveredByEmail;
  }
  public void setDiscoveredByEmail(String o) {
    this.discoveredByEmail = TenantDiscovered.staticSetDiscoveredByEmail(siteRequest_, o);
  }
  public static String staticSetDiscoveredByEmail(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected TenantDiscovered discoveredByEmailInit() {
    Wrap<String> discoveredByEmailWrap = new Wrap<String>().var("discoveredByEmail");
    if(discoveredByEmail == null) {
      _discoveredByEmail(discoveredByEmailWrap);
      Optional.ofNullable(discoveredByEmailWrap.getO()).ifPresent(o -> {
        setDiscoveredByEmail(o);
      });
    }
    return (TenantDiscovered)this;
  }

  public static String staticSearchDiscoveredByEmail(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrDiscoveredByEmail(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqDiscoveredByEmail(SiteRequest siteRequest_, String o) {
    return TenantDiscovered.staticSearchDiscoveredByEmail(siteRequest_, TenantDiscovered.staticSetDiscoveredByEmail(siteRequest_, o)).toString();
  }

  public String sqlDiscoveredByEmail() {
    return discoveredByEmail;
  }

  public static String staticJsonDiscoveredByEmail(String discoveredByEmail) {
    return discoveredByEmail;
  }

	////////////////////////
  // discoveredByUserId //
	////////////////////////


  /**
   *  The entity discoveredByUserId
   *	 is defined as null before being initialized. 
   */
  @JsonProperty
  @JsonInclude(Include.NON_NULL)
  protected String discoveredByUserId;

  /**
   * <br> The entity discoveredByUserId
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.tenant.discovered.TenantDiscovered&fq=entiteVar_enUS_indexed_string:discoveredByUserId">Find the entity discoveredByUserId in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _discoveredByUserId(Wrap<String> w);

  public String getDiscoveredByUserId() {
    return discoveredByUserId;
  }
  public void setDiscoveredByUserId(String o) {
    this.discoveredByUserId = TenantDiscovered.staticSetDiscoveredByUserId(siteRequest_, o);
  }
  public static String staticSetDiscoveredByUserId(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected TenantDiscovered discoveredByUserIdInit() {
    Wrap<String> discoveredByUserIdWrap = new Wrap<String>().var("discoveredByUserId");
    if(discoveredByUserId == null) {
      _discoveredByUserId(discoveredByUserIdWrap);
      Optional.ofNullable(discoveredByUserIdWrap.getO()).ifPresent(o -> {
        setDiscoveredByUserId(o);
      });
    }
    return (TenantDiscovered)this;
  }

  public static String staticSearchDiscoveredByUserId(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrDiscoveredByUserId(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqDiscoveredByUserId(SiteRequest siteRequest_, String o) {
    return TenantDiscovered.staticSearchDiscoveredByUserId(siteRequest_, TenantDiscovered.staticSetDiscoveredByUserId(siteRequest_, o)).toString();
  }

  public String sqlDiscoveredByUserId() {
    return discoveredByUserId;
  }

  public static String staticJsonDiscoveredByUserId(String discoveredByUserId) {
    return discoveredByUserId;
  }

	//////////////////////////
  // discoveredByFullName //
	//////////////////////////


  /**
   *  The entity discoveredByFullName
   *	 is defined as null before being initialized. 
   */
  @JsonProperty
  @JsonInclude(Include.NON_NULL)
  protected String discoveredByFullName;

  /**
   * <br> The entity discoveredByFullName
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.tenant.discovered.TenantDiscovered&fq=entiteVar_enUS_indexed_string:discoveredByFullName">Find the entity discoveredByFullName in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _discoveredByFullName(Wrap<String> w);

  public String getDiscoveredByFullName() {
    return discoveredByFullName;
  }
  public void setDiscoveredByFullName(String o) {
    this.discoveredByFullName = TenantDiscovered.staticSetDiscoveredByFullName(siteRequest_, o);
  }
  public static String staticSetDiscoveredByFullName(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected TenantDiscovered discoveredByFullNameInit() {
    Wrap<String> discoveredByFullNameWrap = new Wrap<String>().var("discoveredByFullName");
    if(discoveredByFullName == null) {
      _discoveredByFullName(discoveredByFullNameWrap);
      Optional.ofNullable(discoveredByFullNameWrap.getO()).ifPresent(o -> {
        setDiscoveredByFullName(o);
      });
    }
    return (TenantDiscovered)this;
  }

  public static String staticSearchDiscoveredByFullName(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrDiscoveredByFullName(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqDiscoveredByFullName(SiteRequest siteRequest_, String o) {
    return TenantDiscovered.staticSearchDiscoveredByFullName(siteRequest_, TenantDiscovered.staticSetDiscoveredByFullName(siteRequest_, o)).toString();
  }

  public String sqlDiscoveredByFullName() {
    return discoveredByFullName;
  }

  public static String staticJsonDiscoveredByFullName(String discoveredByFullName) {
    return discoveredByFullName;
  }

	////////////////////
  // discoveredName //
	////////////////////


  /**
   *  The entity discoveredName
   *	 is defined as null before being initialized. 
   */
  @JsonProperty
  @JsonInclude(Include.NON_NULL)
  protected String discoveredName;

  /**
   * <br> The entity discoveredName
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.tenant.discovered.TenantDiscovered&fq=entiteVar_enUS_indexed_string:discoveredName">Find the entity discoveredName in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _discoveredName(Wrap<String> w);

  public String getDiscoveredName() {
    return discoveredName;
  }
  public void setDiscoveredName(String o) {
    this.discoveredName = TenantDiscovered.staticSetDiscoveredName(siteRequest_, o);
  }
  public static String staticSetDiscoveredName(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected TenantDiscovered discoveredNameInit() {
    Wrap<String> discoveredNameWrap = new Wrap<String>().var("discoveredName");
    if(discoveredName == null) {
      _discoveredName(discoveredNameWrap);
      Optional.ofNullable(discoveredNameWrap.getO()).ifPresent(o -> {
        setDiscoveredName(o);
      });
    }
    return (TenantDiscovered)this;
  }

  public static String staticSearchDiscoveredName(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrDiscoveredName(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqDiscoveredName(SiteRequest siteRequest_, String o) {
    return TenantDiscovered.staticSearchDiscoveredName(siteRequest_, TenantDiscovered.staticSetDiscoveredName(siteRequest_, o)).toString();
  }

  public String sqlDiscoveredName() {
    return discoveredName;
  }

  public static String staticJsonDiscoveredName(String discoveredName) {
    return discoveredName;
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
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.tenant.discovered.TenantDiscovered&fq=entiteVar_enUS_indexed_string:createdByEmail">Find the entity createdByEmail in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _createdByEmail(Wrap<String> w);

  public String getCreatedByEmail() {
    return createdByEmail;
  }
  public void setCreatedByEmail(String o) {
    this.createdByEmail = TenantDiscovered.staticSetCreatedByEmail(siteRequest_, o);
  }
  public static String staticSetCreatedByEmail(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected TenantDiscovered createdByEmailInit() {
    Wrap<String> createdByEmailWrap = new Wrap<String>().var("createdByEmail");
    if(createdByEmail == null) {
      _createdByEmail(createdByEmailWrap);
      Optional.ofNullable(createdByEmailWrap.getO()).ifPresent(o -> {
        setCreatedByEmail(o);
      });
    }
    return (TenantDiscovered)this;
  }

  public static String staticSearchCreatedByEmail(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrCreatedByEmail(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqCreatedByEmail(SiteRequest siteRequest_, String o) {
    return TenantDiscovered.staticSearchCreatedByEmail(siteRequest_, TenantDiscovered.staticSetCreatedByEmail(siteRequest_, o)).toString();
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
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.tenant.discovered.TenantDiscovered&fq=entiteVar_enUS_indexed_string:createdByUserId">Find the entity createdByUserId in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _createdByUserId(Wrap<String> w);

  public String getCreatedByUserId() {
    return createdByUserId;
  }
  public void setCreatedByUserId(String o) {
    this.createdByUserId = TenantDiscovered.staticSetCreatedByUserId(siteRequest_, o);
  }
  public static String staticSetCreatedByUserId(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected TenantDiscovered createdByUserIdInit() {
    Wrap<String> createdByUserIdWrap = new Wrap<String>().var("createdByUserId");
    if(createdByUserId == null) {
      _createdByUserId(createdByUserIdWrap);
      Optional.ofNullable(createdByUserIdWrap.getO()).ifPresent(o -> {
        setCreatedByUserId(o);
      });
    }
    return (TenantDiscovered)this;
  }

  public static String staticSearchCreatedByUserId(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrCreatedByUserId(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqCreatedByUserId(SiteRequest siteRequest_, String o) {
    return TenantDiscovered.staticSearchCreatedByUserId(siteRequest_, TenantDiscovered.staticSetCreatedByUserId(siteRequest_, o)).toString();
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
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.tenant.discovered.TenantDiscovered&fq=entiteVar_enUS_indexed_string:createdByFullName">Find the entity createdByFullName in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _createdByFullName(Wrap<String> w);

  public String getCreatedByFullName() {
    return createdByFullName;
  }
  public void setCreatedByFullName(String o) {
    this.createdByFullName = TenantDiscovered.staticSetCreatedByFullName(siteRequest_, o);
  }
  public static String staticSetCreatedByFullName(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected TenantDiscovered createdByFullNameInit() {
    Wrap<String> createdByFullNameWrap = new Wrap<String>().var("createdByFullName");
    if(createdByFullName == null) {
      _createdByFullName(createdByFullNameWrap);
      Optional.ofNullable(createdByFullNameWrap.getO()).ifPresent(o -> {
        setCreatedByFullName(o);
      });
    }
    return (TenantDiscovered)this;
  }

  public static String staticSearchCreatedByFullName(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrCreatedByFullName(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqCreatedByFullName(SiteRequest siteRequest_, String o) {
    return TenantDiscovered.staticSearchCreatedByFullName(siteRequest_, TenantDiscovered.staticSetCreatedByFullName(siteRequest_, o)).toString();
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
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.tenant.discovered.TenantDiscovered&fq=entiteVar_enUS_indexed_string:createdVia">Find the entity createdVia in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _createdVia(Wrap<String> w);

  public String getCreatedVia() {
    return createdVia;
  }
  public void setCreatedVia(String o) {
    this.createdVia = TenantDiscovered.staticSetCreatedVia(siteRequest_, o);
  }
  public static String staticSetCreatedVia(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected TenantDiscovered createdViaInit() {
    Wrap<String> createdViaWrap = new Wrap<String>().var("createdVia");
    if(createdVia == null) {
      _createdVia(createdViaWrap);
      Optional.ofNullable(createdViaWrap.getO()).ifPresent(o -> {
        setCreatedVia(o);
      });
    }
    return (TenantDiscovered)this;
  }

  public static String staticSearchCreatedVia(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrCreatedVia(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqCreatedVia(SiteRequest siteRequest_, String o) {
    return TenantDiscovered.staticSearchCreatedVia(siteRequest_, TenantDiscovered.staticSetCreatedVia(siteRequest_, o)).toString();
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
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.tenant.discovered.TenantDiscovered&fq=entiteVar_enUS_indexed_string:intentState">Find the entity intentState in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _intentState(Wrap<String> w);

  public String getIntentState() {
    return intentState;
  }
  public void setIntentState(String o) {
    this.intentState = TenantDiscovered.staticSetIntentState(siteRequest_, o);
  }
  public static String staticSetIntentState(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected TenantDiscovered intentStateInit() {
    Wrap<String> intentStateWrap = new Wrap<String>().var("intentState");
    if(intentState == null) {
      _intentState(intentStateWrap);
      Optional.ofNullable(intentStateWrap.getO()).ifPresent(o -> {
        setIntentState(o);
      });
    }
    return (TenantDiscovered)this;
  }

  public static String staticSearchIntentState(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrIntentState(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqIntentState(SiteRequest siteRequest_, String o) {
    return TenantDiscovered.staticSearchIntentState(siteRequest_, TenantDiscovered.staticSetIntentState(siteRequest_, o)).toString();
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
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.tenant.discovered.TenantDiscovered&fq=entiteVar_enUS_indexed_string:requestedState">Find the entity requestedState in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _requestedState(Wrap<String> w);

  public String getRequestedState() {
    return requestedState;
  }
  public void setRequestedState(String o) {
    this.requestedState = TenantDiscovered.staticSetRequestedState(siteRequest_, o);
  }
  public static String staticSetRequestedState(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected TenantDiscovered requestedStateInit() {
    Wrap<String> requestedStateWrap = new Wrap<String>().var("requestedState");
    if(requestedState == null) {
      _requestedState(requestedStateWrap);
      Optional.ofNullable(requestedStateWrap.getO()).ifPresent(o -> {
        setRequestedState(o);
      });
    }
    return (TenantDiscovered)this;
  }

  public static String staticSearchRequestedState(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrRequestedState(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqRequestedState(SiteRequest siteRequest_, String o) {
    return TenantDiscovered.staticSearchRequestedState(siteRequest_, TenantDiscovered.staticSetRequestedState(siteRequest_, o)).toString();
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
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.tenant.discovered.TenantDiscovered&fq=entiteVar_enUS_indexed_string:realizedState">Find the entity realizedState in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _realizedState(Wrap<String> w);

  public String getRealizedState() {
    return realizedState;
  }
  public void setRealizedState(String o) {
    this.realizedState = TenantDiscovered.staticSetRealizedState(siteRequest_, o);
  }
  public static String staticSetRealizedState(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected TenantDiscovered realizedStateInit() {
    Wrap<String> realizedStateWrap = new Wrap<String>().var("realizedState");
    if(realizedState == null) {
      _realizedState(realizedStateWrap);
      Optional.ofNullable(realizedStateWrap.getO()).ifPresent(o -> {
        setRealizedState(o);
      });
    }
    return (TenantDiscovered)this;
  }

  public static String staticSearchRealizedState(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrRealizedState(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqRealizedState(SiteRequest siteRequest_, String o) {
    return TenantDiscovered.staticSearchRealizedState(siteRequest_, TenantDiscovered.staticSetRealizedState(siteRequest_, o)).toString();
  }

  public String sqlRealizedState() {
    return realizedState;
  }

  public static String staticJsonRealizedState(String realizedState) {
    return realizedState;
  }

	/////////////////////
  // discoveredState //
	/////////////////////


  /**
   *  The entity discoveredState
   *	 is defined as null before being initialized. 
   */
  @JsonProperty
  @JsonInclude(Include.NON_NULL)
  protected String discoveredState;

  /**
   * <br> The entity discoveredState
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.tenant.discovered.TenantDiscovered&fq=entiteVar_enUS_indexed_string:discoveredState">Find the entity discoveredState in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _discoveredState(Wrap<String> w);

  public String getDiscoveredState() {
    return discoveredState;
  }
  public void setDiscoveredState(String o) {
    this.discoveredState = TenantDiscovered.staticSetDiscoveredState(siteRequest_, o);
  }
  public static String staticSetDiscoveredState(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected TenantDiscovered discoveredStateInit() {
    Wrap<String> discoveredStateWrap = new Wrap<String>().var("discoveredState");
    if(discoveredState == null) {
      _discoveredState(discoveredStateWrap);
      Optional.ofNullable(discoveredStateWrap.getO()).ifPresent(o -> {
        setDiscoveredState(o);
      });
    }
    return (TenantDiscovered)this;
  }

  public static String staticSearchDiscoveredState(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrDiscoveredState(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqDiscoveredState(SiteRequest siteRequest_, String o) {
    return TenantDiscovered.staticSearchDiscoveredState(siteRequest_, TenantDiscovered.staticSetDiscoveredState(siteRequest_, o)).toString();
  }

  public String sqlDiscoveredState() {
    return discoveredState;
  }

  public static String staticJsonDiscoveredState(String discoveredState) {
    return discoveredState;
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
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.tenant.discovered.TenantDiscovered&fq=entiteVar_enUS_indexed_string:tenantDescription">Find the entity tenantDescription in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _tenantDescription(Wrap<String> w);

  public String getTenantDescription() {
    return tenantDescription;
  }
  public void setTenantDescription(String o) {
    this.tenantDescription = TenantDiscovered.staticSetTenantDescription(siteRequest_, o);
  }
  public static String staticSetTenantDescription(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected TenantDiscovered tenantDescriptionInit() {
    Wrap<String> tenantDescriptionWrap = new Wrap<String>().var("tenantDescription");
    if(tenantDescription == null) {
      _tenantDescription(tenantDescriptionWrap);
      Optional.ofNullable(tenantDescriptionWrap.getO()).ifPresent(o -> {
        setTenantDescription(o);
      });
    }
    return (TenantDiscovered)this;
  }

  public static String staticSearchTenantDescription(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrTenantDescription(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqTenantDescription(SiteRequest siteRequest_, String o) {
    return TenantDiscovered.staticSearchTenantDescription(siteRequest_, TenantDiscovered.staticSetTenantDescription(siteRequest_, o)).toString();
  }

  public String sqlTenantDescription() {
    return tenantDescription;
  }

  public static String staticJsonTenantDescription(String tenantDescription) {
    return tenantDescription;
  }

  //////////////
  // initDeep //
  //////////////

  public Future<TenantDiscoveredGen<DEV>> promiseDeepTenantDiscovered(SiteRequest siteRequest_) {
    if(this.siteRequest_ == null)
      setSiteRequest_(siteRequest_);
    return promiseDeepTenantDiscovered();
  }

  public Future<TenantDiscoveredGen<DEV>> promiseDeepTenantDiscovered() {
    Promise<TenantDiscoveredGen<DEV>> promise = Promise.promise();
    Promise<Void> promise2 = Promise.promise();
    promiseTenantDiscovered(promise2);
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

  public Future<Void> promiseTenantDiscovered(Promise<Void> promise) {
    Future.future(a -> a.complete()).compose(a -> {
      Promise<Void> promise2 = Promise.promise();
      try {
        tenantNameInit();
        tenantIdInit();
        requestedIdInit();
        tenantResourceInit();
        discoveredByEmailInit();
        discoveredByUserIdInit();
        discoveredByFullNameInit();
        discoveredNameInit();
        createdByEmailInit();
        createdByUserIdInit();
        createdByFullNameInit();
        createdViaInit();
        intentStateInit();
        requestedStateInit();
        realizedStateInit();
        discoveredStateInit();
        tenantDescriptionInit();
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

  @Override public Future<? extends TenantDiscoveredGen<DEV>> promiseDeepForClass(SiteRequest siteRequest_) {
    return promiseDeepTenantDiscovered(siteRequest_);
  }

  /////////////////
  // siteRequest //
  /////////////////

  public void siteRequestTenantDiscovered(SiteRequest siteRequest_) {
      super.siteRequestTenant(siteRequest_);
  }

  public void siteRequestForClass(SiteRequest siteRequest_) {
    siteRequestTenantDiscovered(siteRequest_);
  }

  /////////////
  // obtain //
  /////////////

  @Override public Object obtainForClass(String var) {
    String[] vars = StringUtils.split(var, ".");
    Object o = null;
    for(String v : vars) {
      if(o == null)
        o = obtainTenantDiscovered(v);
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
  public Object obtainTenantDiscovered(String var) {
    TenantDiscovered oTenantDiscovered = (TenantDiscovered)this;
    switch(var) {
      case "tenantName":
        return oTenantDiscovered.tenantName;
      case "tenantId":
        return oTenantDiscovered.tenantId;
      case "requestedId":
        return oTenantDiscovered.requestedId;
      case "tenantResource":
        return oTenantDiscovered.tenantResource;
      case "discoveredByEmail":
        return oTenantDiscovered.discoveredByEmail;
      case "discoveredByUserId":
        return oTenantDiscovered.discoveredByUserId;
      case "discoveredByFullName":
        return oTenantDiscovered.discoveredByFullName;
      case "discoveredName":
        return oTenantDiscovered.discoveredName;
      case "createdByEmail":
        return oTenantDiscovered.createdByEmail;
      case "createdByUserId":
        return oTenantDiscovered.createdByUserId;
      case "createdByFullName":
        return oTenantDiscovered.createdByFullName;
      case "createdVia":
        return oTenantDiscovered.createdVia;
      case "intentState":
        return oTenantDiscovered.intentState;
      case "requestedState":
        return oTenantDiscovered.requestedState;
      case "realizedState":
        return oTenantDiscovered.realizedState;
      case "discoveredState":
        return oTenantDiscovered.discoveredState;
      case "tenantDescription":
        return oTenantDiscovered.tenantDescription;
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
        o = relateTenantDiscovered(v, val);
      else if(o instanceof BaseModel) {
        BaseModel baseModel = (BaseModel)o;
        o = baseModel.relateForClass(v, val);
      }
    }
    return o != null;
  }
  public Object relateTenantDiscovered(String var, Object val) {
    TenantDiscovered oTenantDiscovered = (TenantDiscovered)this;
    switch(var) {
      case "requestedId":
        if(oTenantDiscovered.getRequestedId() == null)
          oTenantDiscovered.setRequestedId(Optional.ofNullable(val).map(v -> v.toString()).orElse(null));
        if(!saves.contains("requestedId"))
          saves.add("requestedId");
        return val;
      case "tenantResource":
        if(oTenantDiscovered.getTenantResource() == null)
          oTenantDiscovered.setTenantResource(Optional.ofNullable(val).map(v -> v.toString()).orElse(null));
        if(!saves.contains("tenantResource"))
          saves.add("tenantResource");
        return val;
      default:
        return super.relateTenant(var, val);
    }
  }

  ///////////////
  // staticSet //
  ///////////////

  public static Object staticSetForClass(String entityVar, SiteRequest siteRequest_, String v, TenantDiscovered o) {
    return staticSetTenantDiscovered(entityVar,  siteRequest_, v, o);
  }
  public static Object staticSetTenantDiscovered(String entityVar, SiteRequest siteRequest_, String v, TenantDiscovered o) {
    switch(entityVar) {
    case "tenantName":
      return TenantDiscovered.staticSetTenantName(siteRequest_, v);
    case "tenantId":
      return TenantDiscovered.staticSetTenantId(siteRequest_, v);
    case "requestedId":
      return TenantDiscovered.staticSetRequestedId(siteRequest_, v);
    case "tenantResource":
      return TenantDiscovered.staticSetTenantResource(siteRequest_, v);
    case "discoveredByEmail":
      return TenantDiscovered.staticSetDiscoveredByEmail(siteRequest_, v);
    case "discoveredByUserId":
      return TenantDiscovered.staticSetDiscoveredByUserId(siteRequest_, v);
    case "discoveredByFullName":
      return TenantDiscovered.staticSetDiscoveredByFullName(siteRequest_, v);
    case "discoveredName":
      return TenantDiscovered.staticSetDiscoveredName(siteRequest_, v);
    case "createdByEmail":
      return TenantDiscovered.staticSetCreatedByEmail(siteRequest_, v);
    case "createdByUserId":
      return TenantDiscovered.staticSetCreatedByUserId(siteRequest_, v);
    case "createdByFullName":
      return TenantDiscovered.staticSetCreatedByFullName(siteRequest_, v);
    case "createdVia":
      return TenantDiscovered.staticSetCreatedVia(siteRequest_, v);
    case "intentState":
      return TenantDiscovered.staticSetIntentState(siteRequest_, v);
    case "requestedState":
      return TenantDiscovered.staticSetRequestedState(siteRequest_, v);
    case "realizedState":
      return TenantDiscovered.staticSetRealizedState(siteRequest_, v);
    case "discoveredState":
      return TenantDiscovered.staticSetDiscoveredState(siteRequest_, v);
    case "tenantDescription":
      return TenantDiscovered.staticSetTenantDescription(siteRequest_, v);
      default:
        return Tenant.staticSetTenant(entityVar,  siteRequest_, v, o);
    }
  }

  //////////////////
  // staticSearch //
  //////////////////

  public static Future<TenantDiscovered> fqTenantDiscovered(SiteRequest siteRequest, String var, Object val) {
    Promise<TenantDiscovered> promise = Promise.promise();
    try {
      if(val == null) {
        promise.complete();
      } else {
        SearchList<TenantDiscovered> searchList = new SearchList<TenantDiscovered>();
        searchList.setStore(true);
        searchList.q("*:*");
        searchList.setC(TenantDiscovered.class);
        searchList.fq(String.format("%s:", TenantDiscovered.varIndexedTenantDiscovered(var)) + SearchTool.escapeQueryChars(val.toString()));
        searchList.promiseDeepForClass(siteRequest).onSuccess(a -> {
          try {
            promise.complete(searchList.getList().stream().findFirst().orElse(null));
          } catch(Throwable ex) {
            LOG.error("Error while querying the discovered tenant", ex);
            promise.fail(ex);
          }
        }).onFailure(ex -> {
          LOG.error("Error while querying the discovered tenant", ex);
          promise.fail(ex);
        });
      }
    } catch(Throwable ex) {
      LOG.error("Error while querying the discovered tenant", ex);
      promise.fail(ex);
    }
    return promise.future();
  }

  public static Object staticSearchForClass(String entityVar, SiteRequest siteRequest_, Object o) {
    return staticSearchTenantDiscovered(entityVar,  siteRequest_, o);
  }
  public static Object staticSearchTenantDiscovered(String entityVar, SiteRequest siteRequest_, Object o) {
    switch(entityVar) {
    case "tenantName":
      return TenantDiscovered.staticSearchTenantName(siteRequest_, (String)o);
    case "tenantId":
      return TenantDiscovered.staticSearchTenantId(siteRequest_, (String)o);
    case "requestedId":
      return TenantDiscovered.staticSearchRequestedId(siteRequest_, (String)o);
    case "tenantResource":
      return TenantDiscovered.staticSearchTenantResource(siteRequest_, (String)o);
    case "discoveredByEmail":
      return TenantDiscovered.staticSearchDiscoveredByEmail(siteRequest_, (String)o);
    case "discoveredByUserId":
      return TenantDiscovered.staticSearchDiscoveredByUserId(siteRequest_, (String)o);
    case "discoveredByFullName":
      return TenantDiscovered.staticSearchDiscoveredByFullName(siteRequest_, (String)o);
    case "discoveredName":
      return TenantDiscovered.staticSearchDiscoveredName(siteRequest_, (String)o);
    case "createdByEmail":
      return TenantDiscovered.staticSearchCreatedByEmail(siteRequest_, (String)o);
    case "createdByUserId":
      return TenantDiscovered.staticSearchCreatedByUserId(siteRequest_, (String)o);
    case "createdByFullName":
      return TenantDiscovered.staticSearchCreatedByFullName(siteRequest_, (String)o);
    case "createdVia":
      return TenantDiscovered.staticSearchCreatedVia(siteRequest_, (String)o);
    case "intentState":
      return TenantDiscovered.staticSearchIntentState(siteRequest_, (String)o);
    case "requestedState":
      return TenantDiscovered.staticSearchRequestedState(siteRequest_, (String)o);
    case "realizedState":
      return TenantDiscovered.staticSearchRealizedState(siteRequest_, (String)o);
    case "discoveredState":
      return TenantDiscovered.staticSearchDiscoveredState(siteRequest_, (String)o);
    case "tenantDescription":
      return TenantDiscovered.staticSearchTenantDescription(siteRequest_, (String)o);
      default:
        return Tenant.staticSearchTenant(entityVar,  siteRequest_, o);
    }
  }

  ///////////////////
  // staticSearchStr //
  ///////////////////

  public static String staticSearchStrForClass(String entityVar, SiteRequest siteRequest_, Object o) {
    return staticSearchStrTenantDiscovered(entityVar,  siteRequest_, o);
  }
  public static String staticSearchStrTenantDiscovered(String entityVar, SiteRequest siteRequest_, Object o) {
    switch(entityVar) {
    case "tenantName":
      return TenantDiscovered.staticSearchStrTenantName(siteRequest_, (String)o);
    case "tenantId":
      return TenantDiscovered.staticSearchStrTenantId(siteRequest_, (String)o);
    case "requestedId":
      return TenantDiscovered.staticSearchStrRequestedId(siteRequest_, (String)o);
    case "tenantResource":
      return TenantDiscovered.staticSearchStrTenantResource(siteRequest_, (String)o);
    case "discoveredByEmail":
      return TenantDiscovered.staticSearchStrDiscoveredByEmail(siteRequest_, (String)o);
    case "discoveredByUserId":
      return TenantDiscovered.staticSearchStrDiscoveredByUserId(siteRequest_, (String)o);
    case "discoveredByFullName":
      return TenantDiscovered.staticSearchStrDiscoveredByFullName(siteRequest_, (String)o);
    case "discoveredName":
      return TenantDiscovered.staticSearchStrDiscoveredName(siteRequest_, (String)o);
    case "createdByEmail":
      return TenantDiscovered.staticSearchStrCreatedByEmail(siteRequest_, (String)o);
    case "createdByUserId":
      return TenantDiscovered.staticSearchStrCreatedByUserId(siteRequest_, (String)o);
    case "createdByFullName":
      return TenantDiscovered.staticSearchStrCreatedByFullName(siteRequest_, (String)o);
    case "createdVia":
      return TenantDiscovered.staticSearchStrCreatedVia(siteRequest_, (String)o);
    case "intentState":
      return TenantDiscovered.staticSearchStrIntentState(siteRequest_, (String)o);
    case "requestedState":
      return TenantDiscovered.staticSearchStrRequestedState(siteRequest_, (String)o);
    case "realizedState":
      return TenantDiscovered.staticSearchStrRealizedState(siteRequest_, (String)o);
    case "discoveredState":
      return TenantDiscovered.staticSearchStrDiscoveredState(siteRequest_, (String)o);
    case "tenantDescription":
      return TenantDiscovered.staticSearchStrTenantDescription(siteRequest_, (String)o);
      default:
        return Tenant.staticSearchStrTenant(entityVar,  siteRequest_, o);
    }
  }

  //////////////////
  // staticSearchFq //
  //////////////////

  public static String staticSearchFqForClass(String entityVar, SiteRequest siteRequest_, String o) {
    return staticSearchFqTenantDiscovered(entityVar,  siteRequest_, o);
  }
  public static String staticSearchFqTenantDiscovered(String entityVar, SiteRequest siteRequest_, String o) {
    switch(entityVar) {
    case "tenantName":
      return TenantDiscovered.staticSearchFqTenantName(siteRequest_, o);
    case "tenantId":
      return TenantDiscovered.staticSearchFqTenantId(siteRequest_, o);
    case "requestedId":
      return TenantDiscovered.staticSearchFqRequestedId(siteRequest_, o);
    case "tenantResource":
      return TenantDiscovered.staticSearchFqTenantResource(siteRequest_, o);
    case "discoveredByEmail":
      return TenantDiscovered.staticSearchFqDiscoveredByEmail(siteRequest_, o);
    case "discoveredByUserId":
      return TenantDiscovered.staticSearchFqDiscoveredByUserId(siteRequest_, o);
    case "discoveredByFullName":
      return TenantDiscovered.staticSearchFqDiscoveredByFullName(siteRequest_, o);
    case "discoveredName":
      return TenantDiscovered.staticSearchFqDiscoveredName(siteRequest_, o);
    case "createdByEmail":
      return TenantDiscovered.staticSearchFqCreatedByEmail(siteRequest_, o);
    case "createdByUserId":
      return TenantDiscovered.staticSearchFqCreatedByUserId(siteRequest_, o);
    case "createdByFullName":
      return TenantDiscovered.staticSearchFqCreatedByFullName(siteRequest_, o);
    case "createdVia":
      return TenantDiscovered.staticSearchFqCreatedVia(siteRequest_, o);
    case "intentState":
      return TenantDiscovered.staticSearchFqIntentState(siteRequest_, o);
    case "requestedState":
      return TenantDiscovered.staticSearchFqRequestedState(siteRequest_, o);
    case "realizedState":
      return TenantDiscovered.staticSearchFqRealizedState(siteRequest_, o);
    case "discoveredState":
      return TenantDiscovered.staticSearchFqDiscoveredState(siteRequest_, o);
    case "tenantDescription":
      return TenantDiscovered.staticSearchFqTenantDescription(siteRequest_, o);
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
          o = persistTenantDiscovered(v, val);
        else if(o instanceof BaseModel) {
          BaseModel oBaseModel = (BaseModel)o;
          o = oBaseModel.persistForClass(v, val);
        }
      }
    }
    return o != null;
  }
  public Object persistTenantDiscovered(String var, Object val) {
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
      } else if("discoveredbyemail".equals(varLower)) {
        if(val instanceof String) {
          setDiscoveredByEmail((String)val);
        }
        saves.add("discoveredByEmail");
        return val;
      } else if("discoveredbyuserid".equals(varLower)) {
        if(val instanceof String) {
          setDiscoveredByUserId((String)val);
        }
        saves.add("discoveredByUserId");
        return val;
      } else if("discoveredbyfullname".equals(varLower)) {
        if(val instanceof String) {
          setDiscoveredByFullName((String)val);
        }
        saves.add("discoveredByFullName");
        return val;
      } else if("discoveredname".equals(varLower)) {
        if(val instanceof String) {
          setDiscoveredName((String)val);
        }
        saves.add("discoveredName");
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
      } else if("discoveredstate".equals(varLower)) {
        if(val instanceof String) {
          setDiscoveredState((String)val);
        }
        saves.add("discoveredState");
        return val;
      } else if("tenantdescription".equals(varLower)) {
        if(val instanceof String) {
          setTenantDescription((String)val);
        }
        saves.add("tenantDescription");
        return val;
    } else {
      return super.persistTenant(var, val);
    }
  }

  /////////////
  // populate //
  /////////////

  @Override public void populateForClass(SolrResponse.Doc doc) {
    populateTenantDiscovered(doc);
  }
  public void populateTenantDiscovered(SolrResponse.Doc doc) {
    TenantDiscovered oTenantDiscovered = (TenantDiscovered)this;
    saves = Optional.ofNullable((ArrayList<String>)doc.get("saves_docvalues_strings")).orElse(new ArrayList<String>());
    if(saves != null) {

      if(saves.contains("tenantName")) {
        String tenantName = (String)doc.get("tenantName_docvalues_string");
        if(tenantName != null)
          oTenantDiscovered.setTenantName(tenantName);
      }

      if(saves.contains("tenantId")) {
        String tenantId = (String)doc.get("tenantId_docvalues_string");
        if(tenantId != null)
          oTenantDiscovered.setTenantId(tenantId);
      }

      String requestedId = (String)doc.get("requestedId_docvalues_string");
      if(requestedId != null)
        oTenantDiscovered.setRequestedId(requestedId);

      String tenantResource = (String)doc.get("tenantResource_docvalues_string");
      if(tenantResource != null)
        oTenantDiscovered.setTenantResource(tenantResource);

      if(saves.contains("discoveredByEmail")) {
        String discoveredByEmail = (String)doc.get("discoveredByEmail_docvalues_string");
        if(discoveredByEmail != null)
          oTenantDiscovered.setDiscoveredByEmail(discoveredByEmail);
      }

      if(saves.contains("discoveredByUserId")) {
        String discoveredByUserId = (String)doc.get("discoveredByUserId_docvalues_string");
        if(discoveredByUserId != null)
          oTenantDiscovered.setDiscoveredByUserId(discoveredByUserId);
      }

      if(saves.contains("discoveredByFullName")) {
        String discoveredByFullName = (String)doc.get("discoveredByFullName_docvalues_string");
        if(discoveredByFullName != null)
          oTenantDiscovered.setDiscoveredByFullName(discoveredByFullName);
      }

      if(saves.contains("discoveredName")) {
        String discoveredName = (String)doc.get("discoveredName_docvalues_string");
        if(discoveredName != null)
          oTenantDiscovered.setDiscoveredName(discoveredName);
      }

      if(saves.contains("createdByEmail")) {
        String createdByEmail = (String)doc.get("createdByEmail_docvalues_string");
        if(createdByEmail != null)
          oTenantDiscovered.setCreatedByEmail(createdByEmail);
      }

      if(saves.contains("createdByUserId")) {
        String createdByUserId = (String)doc.get("createdByUserId_docvalues_string");
        if(createdByUserId != null)
          oTenantDiscovered.setCreatedByUserId(createdByUserId);
      }

      if(saves.contains("createdByFullName")) {
        String createdByFullName = (String)doc.get("createdByFullName_docvalues_string");
        if(createdByFullName != null)
          oTenantDiscovered.setCreatedByFullName(createdByFullName);
      }

      if(saves.contains("createdVia")) {
        String createdVia = (String)doc.get("createdVia_docvalues_string");
        if(createdVia != null)
          oTenantDiscovered.setCreatedVia(createdVia);
      }

      if(saves.contains("intentState")) {
        String intentState = (String)doc.get("intentState_docvalues_string");
        if(intentState != null)
          oTenantDiscovered.setIntentState(intentState);
      }

      if(saves.contains("requestedState")) {
        String requestedState = (String)doc.get("requestedState_docvalues_string");
        if(requestedState != null)
          oTenantDiscovered.setRequestedState(requestedState);
      }

      if(saves.contains("realizedState")) {
        String realizedState = (String)doc.get("realizedState_docvalues_string");
        if(realizedState != null)
          oTenantDiscovered.setRealizedState(realizedState);
      }

      if(saves.contains("discoveredState")) {
        String discoveredState = (String)doc.get("discoveredState_docvalues_string");
        if(discoveredState != null)
          oTenantDiscovered.setDiscoveredState(discoveredState);
      }

      if(saves.contains("tenantDescription")) {
        String tenantDescription = (String)doc.get("tenantDescription_docvalues_string");
        if(tenantDescription != null)
          oTenantDiscovered.setTenantDescription(tenantDescription);
      }
    }

    super.populateTenant(doc);
  }

  public void indexTenantDiscovered(JsonObject doc) {
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
    if(discoveredByEmail != null) {
      doc.put("discoveredByEmail_docvalues_string", discoveredByEmail);
    }
    if(discoveredByUserId != null) {
      doc.put("discoveredByUserId_docvalues_string", discoveredByUserId);
    }
    if(discoveredByFullName != null) {
      doc.put("discoveredByFullName_docvalues_string", discoveredByFullName);
    }
    if(discoveredName != null) {
      doc.put("discoveredName_docvalues_string", discoveredName);
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
    if(discoveredState != null) {
      doc.put("discoveredState_docvalues_string", discoveredState);
    }
    if(tenantDescription != null) {
      doc.put("tenantDescription_docvalues_string", tenantDescription);
    }
    super.indexTenant(doc);

	}

  public static String varStoredTenantDiscovered(String entityVar) {
    switch(entityVar) {
      case "tenantName":
        return "tenantName_docvalues_string";
      case "tenantId":
        return "tenantId_docvalues_string";
      case "requestedId":
        return "requestedId_docvalues_string";
      case "tenantResource":
        return "tenantResource_docvalues_string";
      case "discoveredByEmail":
        return "discoveredByEmail_docvalues_string";
      case "discoveredByUserId":
        return "discoveredByUserId_docvalues_string";
      case "discoveredByFullName":
        return "discoveredByFullName_docvalues_string";
      case "discoveredName":
        return "discoveredName_docvalues_string";
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
      case "discoveredState":
        return "discoveredState_docvalues_string";
      case "tenantDescription":
        return "tenantDescription_docvalues_string";
      default:
        return Tenant.varStoredTenant(entityVar);
    }
  }

  public static String varIndexedTenantDiscovered(String entityVar) {
    switch(entityVar) {
      case "tenantName":
        return "tenantName_docvalues_string";
      case "tenantId":
        return "tenantId_docvalues_string";
      case "requestedId":
        return "requestedId_docvalues_string";
      case "tenantResource":
        return "tenantResource_docvalues_string";
      case "discoveredByEmail":
        return "discoveredByEmail_docvalues_string";
      case "discoveredByUserId":
        return "discoveredByUserId_docvalues_string";
      case "discoveredByFullName":
        return "discoveredByFullName_docvalues_string";
      case "discoveredName":
        return "discoveredName_docvalues_string";
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
      case "discoveredState":
        return "discoveredState_docvalues_string";
      case "tenantDescription":
        return "tenantDescription_docvalues_string";
      default:
        return Tenant.varIndexedTenant(entityVar);
    }
  }

  public static String searchVarTenantDiscovered(String searchVar) {
    switch(searchVar) {
      case "tenantName_docvalues_string":
        return "tenantName";
      case "tenantId_docvalues_string":
        return "tenantId";
      case "requestedId_docvalues_string":
        return "requestedId";
      case "tenantResource_docvalues_string":
        return "tenantResource";
      case "discoveredByEmail_docvalues_string":
        return "discoveredByEmail";
      case "discoveredByUserId_docvalues_string":
        return "discoveredByUserId";
      case "discoveredByFullName_docvalues_string":
        return "discoveredByFullName";
      case "discoveredName_docvalues_string":
        return "discoveredName";
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
      case "discoveredState_docvalues_string":
        return "discoveredState";
      case "tenantDescription_docvalues_string":
        return "tenantDescription";
      default:
        return Tenant.searchVarTenant(searchVar);
    }
  }

  public static String varSearchTenantDiscovered(String entityVar) {
    switch(entityVar) {
      default:
        return Tenant.varSearchTenant(entityVar);
    }
  }

  public static String varSuggestedTenantDiscovered(String entityVar) {
    switch(entityVar) {
      default:
        return Tenant.varSuggestedTenant(entityVar);
    }
  }

  /////////////
  // store //
  /////////////

  @Override public void storeForClass(SolrResponse.Doc doc) {
    storeTenantDiscovered(doc);
  }
  public void storeTenantDiscovered(SolrResponse.Doc doc) {
    TenantDiscovered oTenantDiscovered = (TenantDiscovered)this;
    SiteRequest siteRequest = oTenantDiscovered.getSiteRequest_();

    oTenantDiscovered.setTenantName(Optional.ofNullable(doc.get("tenantName_docvalues_string")).map(v -> v.toString()).orElse(null));
    oTenantDiscovered.setTenantId(Optional.ofNullable(doc.get("tenantId_docvalues_string")).map(v -> v.toString()).orElse(null));
    oTenantDiscovered.setRequestedId(Optional.ofNullable(doc.get("requestedId_docvalues_string")).map(v -> v.toString()).orElse(null));
    oTenantDiscovered.setTenantResource(Optional.ofNullable(doc.get("tenantResource_docvalues_string")).map(v -> v.toString()).orElse(null));
    oTenantDiscovered.setDiscoveredByEmail(Optional.ofNullable(doc.get("discoveredByEmail_docvalues_string")).map(v -> v.toString()).orElse(null));
    oTenantDiscovered.setDiscoveredByUserId(Optional.ofNullable(doc.get("discoveredByUserId_docvalues_string")).map(v -> v.toString()).orElse(null));
    oTenantDiscovered.setDiscoveredByFullName(Optional.ofNullable(doc.get("discoveredByFullName_docvalues_string")).map(v -> v.toString()).orElse(null));
    oTenantDiscovered.setDiscoveredName(Optional.ofNullable(doc.get("discoveredName_docvalues_string")).map(v -> v.toString()).orElse(null));
    oTenantDiscovered.setCreatedByEmail(Optional.ofNullable(doc.get("createdByEmail_docvalues_string")).map(v -> v.toString()).orElse(null));
    oTenantDiscovered.setCreatedByUserId(Optional.ofNullable(doc.get("createdByUserId_docvalues_string")).map(v -> v.toString()).orElse(null));
    oTenantDiscovered.setCreatedByFullName(Optional.ofNullable(doc.get("createdByFullName_docvalues_string")).map(v -> v.toString()).orElse(null));
    oTenantDiscovered.setCreatedVia(Optional.ofNullable(doc.get("createdVia_docvalues_string")).map(v -> v.toString()).orElse(null));
    oTenantDiscovered.setIntentState(Optional.ofNullable(doc.get("intentState_docvalues_string")).map(v -> v.toString()).orElse(null));
    oTenantDiscovered.setRequestedState(Optional.ofNullable(doc.get("requestedState_docvalues_string")).map(v -> v.toString()).orElse(null));
    oTenantDiscovered.setRealizedState(Optional.ofNullable(doc.get("realizedState_docvalues_string")).map(v -> v.toString()).orElse(null));
    oTenantDiscovered.setDiscoveredState(Optional.ofNullable(doc.get("discoveredState_docvalues_string")).map(v -> v.toString()).orElse(null));
    oTenantDiscovered.setTenantDescription(Optional.ofNullable(doc.get("tenantDescription_docvalues_string")).map(v -> v.toString()).orElse(null));

    super.storeTenant(doc);
  }

  //////////////////
  // apiRequest //
  //////////////////

  public void apiRequestTenantDiscovered() {
    ApiRequest apiRequest = Optional.ofNullable(siteRequest_).map(r -> r.getApiRequest_()).orElse(null);
    Object o = Optional.ofNullable(apiRequest).map(ApiRequest::getOriginal).orElse(null);
    if(o != null && o instanceof TenantDiscovered) {
      TenantDiscovered original = (TenantDiscovered)o;
      if(!Objects.equals(tenantName, original.getTenantName()))
        apiRequest.addVars("tenantName");
      if(!Objects.equals(tenantId, original.getTenantId()))
        apiRequest.addVars("tenantId");
      if(!Objects.equals(requestedId, original.getRequestedId()))
        apiRequest.addVars("requestedId");
      if(!Objects.equals(tenantResource, original.getTenantResource()))
        apiRequest.addVars("tenantResource");
      if(!Objects.equals(discoveredByEmail, original.getDiscoveredByEmail()))
        apiRequest.addVars("discoveredByEmail");
      if(!Objects.equals(discoveredByUserId, original.getDiscoveredByUserId()))
        apiRequest.addVars("discoveredByUserId");
      if(!Objects.equals(discoveredByFullName, original.getDiscoveredByFullName()))
        apiRequest.addVars("discoveredByFullName");
      if(!Objects.equals(discoveredName, original.getDiscoveredName()))
        apiRequest.addVars("discoveredName");
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
      if(!Objects.equals(discoveredState, original.getDiscoveredState()))
        apiRequest.addVars("discoveredState");
      if(!Objects.equals(tenantDescription, original.getTenantDescription()))
        apiRequest.addVars("tenantDescription");
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
    sb.append(Optional.ofNullable(requestedId).map(v -> "requestedId: \"" + v + "\"\n" ).orElse(""));
    sb.append(Optional.ofNullable(tenantResource).map(v -> "tenantResource: \"" + v + "\"\n" ).orElse(""));
    sb.append(Optional.ofNullable(discoveredByEmail).map(v -> "discoveredByEmail: \"" + v + "\"\n" ).orElse(""));
    sb.append(Optional.ofNullable(discoveredByUserId).map(v -> "discoveredByUserId: \"" + v + "\"\n" ).orElse(""));
    sb.append(Optional.ofNullable(discoveredByFullName).map(v -> "discoveredByFullName: \"" + v + "\"\n" ).orElse(""));
    sb.append(Optional.ofNullable(discoveredName).map(v -> "discoveredName: \"" + v + "\"\n" ).orElse(""));
    sb.append(Optional.ofNullable(createdByEmail).map(v -> "createdByEmail: \"" + v + "\"\n" ).orElse(""));
    sb.append(Optional.ofNullable(createdByUserId).map(v -> "createdByUserId: \"" + v + "\"\n" ).orElse(""));
    sb.append(Optional.ofNullable(createdByFullName).map(v -> "createdByFullName: \"" + v + "\"\n" ).orElse(""));
    sb.append(Optional.ofNullable(createdVia).map(v -> "createdVia: \"" + v + "\"\n" ).orElse(""));
    sb.append(Optional.ofNullable(intentState).map(v -> "intentState: \"" + v + "\"\n" ).orElse(""));
    sb.append(Optional.ofNullable(requestedState).map(v -> "requestedState: \"" + v + "\"\n" ).orElse(""));
    sb.append(Optional.ofNullable(realizedState).map(v -> "realizedState: \"" + v + "\"\n" ).orElse(""));
    sb.append(Optional.ofNullable(discoveredState).map(v -> "discoveredState: \"" + v + "\"\n" ).orElse(""));
    sb.append(Optional.ofNullable(tenantDescription).map(v -> "tenantDescription: \"" + v + "\"\n" ).orElse(""));
    return sb.toString();
  }

  public static final String CLASS_SIMPLE_NAME = "TenantDiscovered";
  public static final String CLASS_CANONICAL_NAME = "org.computate.dcm.model.eda.tenant.discovered.TenantDiscovered";
  public static final String CLASS_AUTH_RESOURCE = "TENANTDISCOVERED";
  public static final String CLASS_API_ADDRESS_TenantDiscovered = "dcm-enUS-TenantDiscovered";
  public static String getClassApiAddress() {
    return CLASS_API_ADDRESS_TenantDiscovered;
  }
  public static final String VAR_tenantName = "tenantName";
  public static final String SET_tenantName = "setTenantName";
  public static final String VAR_tenantId = "tenantId";
  public static final String SET_tenantId = "setTenantId";
  public static final String VAR_requestedId = "requestedId";
  public static final String SET_requestedId = "setRequestedId";
  public static final String VAR_tenantResource = "tenantResource";
  public static final String SET_tenantResource = "setTenantResource";
  public static final String VAR_discoveredByEmail = "discoveredByEmail";
  public static final String SET_discoveredByEmail = "setDiscoveredByEmail";
  public static final String VAR_discoveredByUserId = "discoveredByUserId";
  public static final String SET_discoveredByUserId = "setDiscoveredByUserId";
  public static final String VAR_discoveredByFullName = "discoveredByFullName";
  public static final String SET_discoveredByFullName = "setDiscoveredByFullName";
  public static final String VAR_discoveredName = "discoveredName";
  public static final String SET_discoveredName = "setDiscoveredName";
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
  public static final String VAR_discoveredState = "discoveredState";
  public static final String SET_discoveredState = "setDiscoveredState";
  public static final String VAR_tenantDescription = "tenantDescription";
  public static final String SET_tenantDescription = "setTenantDescription";

  public static List<String> varsQForClass() {
    return TenantDiscovered.varsQTenantDiscovered(new ArrayList<String>());
  }
  public static List<String> varsQTenantDiscovered(List<String> vars) {
    Tenant.varsQTenant(vars);
    return vars;
  }

  public static List<String> varsFqForClass() {
    return TenantDiscovered.varsFqTenantDiscovered(new ArrayList<String>());
  }
  public static List<String> varsFqTenantDiscovered(List<String> vars) {
    vars.add(VAR_tenantName);
    vars.add(VAR_tenantId);
    vars.add(VAR_requestedId);
    vars.add(VAR_tenantResource);
    vars.add(VAR_discoveredName);
    vars.add(VAR_tenantDescription);
    Tenant.varsFqTenant(vars);
    return vars;
  }

  public static List<String> varsRangeForClass() {
    return TenantDiscovered.varsRangeTenantDiscovered(new ArrayList<String>());
  }
  public static List<String> varsRangeTenantDiscovered(List<String> vars) {
    Tenant.varsRangeTenant(vars);
    return vars;
  }

  public static final String DISPLAY_NAME_tenantName = "tenant name";
  public static final String DISPLAY_NAME_tenantId = "tenant ID";
  public static final String DISPLAY_NAME_requestedId = "tenant requested";
  public static final String DISPLAY_NAME_tenantResource = "tenant auth resource";
  public static final String DISPLAY_NAME_discoveredByEmail = "discovered by user email";
  public static final String DISPLAY_NAME_discoveredByUserId = "discovered by user ID";
  public static final String DISPLAY_NAME_discoveredByFullName = "discovered by user full name";
  public static final String DISPLAY_NAME_discoveredName = "tenant discovered name";
  public static final String DISPLAY_NAME_createdByEmail = "created by user email";
  public static final String DISPLAY_NAME_createdByUserId = "created by user ID";
  public static final String DISPLAY_NAME_createdByFullName = "created by user full name";
  public static final String DISPLAY_NAME_createdVia = "created via";
  public static final String DISPLAY_NAME_intentState = "intent state";
  public static final String DISPLAY_NAME_requestedState = "requested state";
  public static final String DISPLAY_NAME_realizedState = "realized state";
  public static final String DISPLAY_NAME_discoveredState = "discovered state";
  public static final String DISPLAY_NAME_tenantDescription = "description";

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
    return discoveredName;
  }

  @Override
  public String classNameAdjectiveSingularForClass() {
    return TenantDiscovered.NameAdjectiveSingular_enUS;
  }

  @Override
  public String descriptionForClass() {
    return tenantDescription;
  }

  @Override
  public String enUSStringFormatUrlEditPageForClass() {
    return "%s/en-us/edit/discovered/tenant/%s";
  }

  public static String varJson(String var, Boolean patch) {
    return TenantDiscovered.varJsonTenantDiscovered(var, patch);
  }
  public static String varJsonTenantDiscovered(String var, Boolean patch) {
    switch(var) {
    case VAR_tenantName:
      return patch ? SET_tenantName : VAR_tenantName;
    case VAR_tenantId:
      return patch ? SET_tenantId : VAR_tenantId;
    case VAR_requestedId:
      return patch ? SET_requestedId : VAR_requestedId;
    case VAR_tenantResource:
      return patch ? SET_tenantResource : VAR_tenantResource;
    case VAR_discoveredByEmail:
      return patch ? SET_discoveredByEmail : VAR_discoveredByEmail;
    case VAR_discoveredByUserId:
      return patch ? SET_discoveredByUserId : VAR_discoveredByUserId;
    case VAR_discoveredByFullName:
      return patch ? SET_discoveredByFullName : VAR_discoveredByFullName;
    case VAR_discoveredName:
      return patch ? SET_discoveredName : VAR_discoveredName;
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
    case VAR_discoveredState:
      return patch ? SET_discoveredState : VAR_discoveredState;
    case VAR_tenantDescription:
      return patch ? SET_tenantDescription : VAR_tenantDescription;
    default:
      return Tenant.varJsonTenant(var, patch);
    }
  }

  public static String displayNameForClass(String var) {
    return TenantDiscovered.displayNameTenantDiscovered(var);
  }
  public static String displayNameTenantDiscovered(String var) {
    switch(var) {
    case VAR_tenantName:
      return DISPLAY_NAME_tenantName;
    case VAR_tenantId:
      return DISPLAY_NAME_tenantId;
    case VAR_requestedId:
      return DISPLAY_NAME_requestedId;
    case VAR_tenantResource:
      return DISPLAY_NAME_tenantResource;
    case VAR_discoveredByEmail:
      return DISPLAY_NAME_discoveredByEmail;
    case VAR_discoveredByUserId:
      return DISPLAY_NAME_discoveredByUserId;
    case VAR_discoveredByFullName:
      return DISPLAY_NAME_discoveredByFullName;
    case VAR_discoveredName:
      return DISPLAY_NAME_discoveredName;
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
    case VAR_discoveredState:
      return DISPLAY_NAME_discoveredState;
    case VAR_tenantDescription:
      return DISPLAY_NAME_tenantDescription;
    default:
      return Tenant.displayNameTenant(var);
    }
  }

  public static String descriptionTenantDiscovered(String var) {
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
    case VAR_discoveredByEmail:
      return "The email address for the user who discovered the change request. ";
    case VAR_discoveredByUserId:
      return "The IdP UUID record for the user who discovered the change request. ";
    case VAR_discoveredByFullName:
      return "The first and last name for the user who discovered the change request. ";
    case VAR_discoveredName:
      return "The name of this tenant discovered model";
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
    case VAR_discoveredState:
      return "Must be a complete representation of the provisioned resource in DCM unified format \u2014 not a status code, but a full state description. ";
    case VAR_tenantDescription:
      return "A description of this tenant";
      default:
        return Tenant.descriptionTenant(var);
    }
  }

  public static String classSimpleNameTenantDiscovered(String var) {
    switch(var) {
    case VAR_tenantName:
      return "String";
    case VAR_tenantId:
      return "String";
    case VAR_requestedId:
      return "String";
    case VAR_tenantResource:
      return "String";
    case VAR_discoveredByEmail:
      return "String";
    case VAR_discoveredByUserId:
      return "String";
    case VAR_discoveredByFullName:
      return "String";
    case VAR_discoveredName:
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
    case VAR_discoveredState:
      return "String";
    case VAR_tenantDescription:
      return "String";
      default:
        return Tenant.classSimpleNameTenant(var);
    }
  }

  public static Integer htmColumnTenantDiscovered(String var) {
    switch(var) {
    case VAR_requestedId:
      return 0;
    case VAR_discoveredName:
      return 1;
    case VAR_tenantDescription:
      return 3;
      default:
        return Tenant.htmColumnTenant(var);
    }
  }

  public static Integer htmRowTenantDiscovered(String var) {
    switch(var) {
    case VAR_tenantName:
      return 20;
    case VAR_requestedId:
      return 5;
    case VAR_discoveredByEmail:
      return 10;
    case VAR_discoveredByUserId:
      return 10;
    case VAR_discoveredByFullName:
      return 10;
    case VAR_createdByEmail:
      return 10;
    case VAR_createdVia:
      return 10;
    case VAR_intentState:
      return 12;
    case VAR_requestedState:
      return 12;
    case VAR_realizedState:
      return 12;
    case VAR_discoveredState:
      return 12;
    case VAR_tenantDescription:
      return 20;
      default:
        return Tenant.htmRowTenant(var);
    }
  }

  public static Integer htmCellTenantDiscovered(String var) {
    switch(var) {
    case VAR_tenantName:
      return 1;
    case VAR_requestedId:
      return 0;
    case VAR_discoveredByEmail:
      return 0;
    case VAR_discoveredByUserId:
      return 0;
    case VAR_discoveredByFullName:
      return 0;
    case VAR_createdByEmail:
      return 0;
    case VAR_createdVia:
      return 0;
    case VAR_intentState:
      return 0;
    case VAR_requestedState:
      return 0;
    case VAR_realizedState:
      return 0;
    case VAR_discoveredState:
      return 0;
    case VAR_tenantDescription:
      return 4;
      default:
        return Tenant.htmCellTenant(var);
    }
  }

  public static Integer lengthMinTenantDiscovered(String var) {
    switch(var) {
      default:
        return Tenant.lengthMinTenant(var);
    }
  }

  public static Integer lengthMaxTenantDiscovered(String var) {
    switch(var) {
      default:
        return Tenant.lengthMaxTenant(var);
    }
  }

  public static Integer maxTenantDiscovered(String var) {
    switch(var) {
      default:
        return Tenant.maxTenant(var);
    }
  }

  public static Integer minTenantDiscovered(String var) {
    switch(var) {
      default:
        return Tenant.minTenant(var);
    }
  }
}
