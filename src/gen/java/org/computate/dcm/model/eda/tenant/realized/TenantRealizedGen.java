package org.computate.dcm.model.eda.tenant.realized;

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
import org.computate.dcm.model.eda.tenant.intent.TenantIntent;
import java.lang.Integer;
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
 * <li>You can add a class comment "{@inheritDoc}" if you wish to inherit the helpful inherited class comments from class TenantRealizedGen into the class TenantRealized. 
 * </li><li><p>
 *   You can add a class comment <kbd><b>Rows: 100</b></kbd> if you wish for the realized tenant API to return more or less than 10 results by default. 
 *   In this case, the API will return 100 results from the API instead of 10 by default. 
 *   Each API has built in pagination of the search results to ensure a user can query all the data a page at a time without running the application out of memory. 
 * </p>
 * </li>
 * <h3>About the TenantRealized class and it's generated class TenantRealizedGen&lt;Tenant&gt;: </h3>extends TenantRealizedGen
 * <p>
 * This Java class extends a generated Java class TenantRealizedGen built by the <a href="https://github.com/computate-org/computate">https://github.com/computate-org/computate</a> project. 
 * Whenever this Java class is modified or touched, the watch service installed as described in the README, indexes all the information about this Java class in a local Apache Solr Search Engine. 
 * If you are running the service, you can see the indexed data about this Java Class here: 
 * </p>
 * <p><a href="https://solr.apps-crc.testing/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.tenant.realized.TenantRealized">Find the class TenantRealized in Solr. </a></p>
 * <p>
 * The extended class ending with "Gen" did not exist at first, but was automatically created by the same watch service based on the data retrieved from the local Apache Server search engine. 
 * The extended class contains many generated fields, getters, setters, initialization code, and helper methods to help build a website and API fast, reactive, and scalable. 
 * </p>
 * extends TenantRealizedGen<Tenant>
 * <p>This <code>class TenantRealized extends TenantRealizedGen&lt;Tenant&gt;</code>, which means it extends a newly generated TenantRealizedGen. 
 * The generated <code>class TenantRealizedGen extends Tenant</code> which means that TenantRealized extends TenantRealizedGen which extends Tenant. 
 * This generated inheritance is a powerful feature that allows a lot of boiler plate code to be created for you automatically while still preserving inheritance through the power of Java Generic classes. 
 * </p>
 * <h2>
 *   Api: true
 * </h2>
 * <p>
 *   This class contains a comment <kbd><b>Api: true</b></kbd>, which means this class will have Java Vert.x API backend code generated for these objects. 
 * </p>
 * <h2>ApiTag.enUS: true</h2>
 * <p>This class contains a comment <kbd><b>ApiTag: realized tenants</b></kbd>, which groups all of the OpenAPIs for TenantRealized objects under the tag "realized tenants". 
 * </p>
 * <h2>ApiUri.enUS: /en-us/api/intent/realized</h2>
 * <p>This class contains a comment <kbd><b>ApiUri: /en-us/api/intent/realized</b></kbd>, which defines the base API URI for TenantRealized objects as "/en-us/api/intent/realized" in the OpenAPI spec. 
 * </p>
 * <h2>Color: null</h2>
 * <h2>Indexed: true</h2>
 * <p>This class contains a comment <kbd><b>Indexed: true</b></kbd>, which means this class will be indexed in the search engine. 
 * Every protected void method that begins with "_" that is marked to be searched with a comment like "Indexed: true", "Stored: true", or "DocValues: true" will be indexed in the search engine. 
 * </p>
 * <h2>{@inheritDoc}</h2>
 * <p>By adding a class comment "{@inheritDoc}", the TenantRealized class will inherit the helpful inherited class comments from the super class TenantRealizedGen. 
 * </p>
 * <h2>
 *   Rows: 10
 * </h2>
 * <p>This class contains a comment <kbd><b>Rows: 10</b></kbd>, which means the realized tenant API will return a default of 10 results instead of 10 by default. 
 * Each API has built in pagination of the search results to ensure a user can query all the data a page at a time without running the application out of memory. 
 * </p>
 * <p>
 *   You can add a class comment <kbd><b>Rows: 100</b></kbd> if you wish for the realized tenant API to return more or less than 10 results by default. 
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
 * This creates a new Java class org.computate.dcm.model.eda.tenant.realized.TenantRealizedPage. 
 * </p>
 * <h2>SuperPage.enUS: PageLayout</h2>
 * <p>This class contains a comment <kbd><b>SuperPage.enUS: PageLayout</b></kbd>, which identifies the Java super class of the page code by it's class simple name "PageLayout". 
 * This means that the newly created class org.computate.dcm.model.eda.tenant.realized.TenantRealizedPage extends org.computate.dcm.page.PageLayout. 
 * </p>
 * <h2>Promise: true</h2>
 * <p>
 *   This class contains a comment <kbd><b>Promise: true</b></kbd>
 *   Sometimes a Java class must be initialized asynchronously when it involves calling a blocking API. 
 *   This means that the TenantRealized Java class has promiseDeep methods which must be initialized asynchronously as a Vert.x Promise  instead of initDeep methods which are a simple non-asynchronous method. 
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
 * <h2>AName.enUS: a realized tenant</h2>
 * <p>This class contains a comment <kbd><b>AName.enUS: a realized tenant</b></kbd>, which identifies the language context to describe a TenantRealized as "a realized tenant". 
 * </p>
 * <p>
 * Delete the class TenantRealized in Solr: 
 * <pre>
 * curl -k 'https://solr.apps-crc.testing/solr/computate/update?commitWithin=1000&amp;overwrite=true&amp;wt=json' -X POST -H 'Content-type: text/xml' -u "admin:$(oc -n solr get secret/solr-solrcloud-security-bootstrap -o jsonpath={.data.admin} | base64 -d)" --data-raw '&lt;delete&gt;&lt;query&gt;classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.tenant.realized.TenantRealized&lt;/query&gt;&lt;/delete&gt;'
 * </pre>
 * </p>
 * <p>
 * Delete  the package org.computate.dcm.model.eda.tenant.realized in Solr: 
 * <pre>
 * curl -k 'https://solr.apps-crc.testing/solr/computate/update?commitWithin=1000&amp;overwrite=true&amp;wt=json' -X POST -H 'Content-type: text/xml' -u "admin:$(oc -n solr get secret/solr-solrcloud-security-bootstrap -o jsonpath={.data.admin} | base64 -d)" --data-raw '&lt;delete&gt;&lt;query&gt;classeNomEnsemble_enUS_indexed_string:org.computate.dcm.model.eda.tenant.realized&lt;/query&gt;&lt;/delete&gt;'
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
public abstract class TenantRealizedGen<DEV> extends Tenant {
  protected static final Logger LOG = LoggerFactory.getLogger(TenantRealized.class);

  public static final String Description_enUS = "An approved and realized Tenant. Tenants are separate organizations sharing the same cloud resources. ";
  public static final String AName_enUS = "a realized tenant";
  public static final String This_enUS = "this ";
  public static final String ThisName_enUS = "this realized tenant";
  public static final String A_enUS = "a ";
  public static final String TheName_enUS = "the realized tenant";
  public static final String SingularName_enUS = "realized tenant";
  public static final String PluralName_enUS = "realized tenants";
  public static final String NameActual_enUS = "current realized tenant";
  public static final String AllName_enUS = "all realized tenants";
  public static final String SearchAllNameBy_enUS = "search realized tenants by ";
  public static final String SearchAllName_enUS = "search realized tenants";
  public static final String Title_enUS = "realized tenants";
  public static final String ThePluralName_enUS = "the realized tenants";
  public static final String NoNameFound_enUS = "no realized tenant found";
  public static final String ApiUri_enUS = "/en-us/api/intent/realized";
  public static final String ApiUriSearchPage_enUS = "/en-us/search/realized/tenant";
  public static final String ApiUriEditPage_enUS = "/en-us/edit/realized/tenant/{tenantResource}";
  public static final String OfName_enUS = "of realized tenant";
  public static final String ANameAdjective_enUS = "a realized tenant";
  public static final String NameAdjectiveSingular_enUS = "realized tenant";
  public static final String NameAdjectivePlural_enUS = "realized tenants";
  public static final String Search_enUS_OpenApiUri = "/en-us/api/intent/realized";
  public static final String Search_enUS_StringFormatUri = "/en-us/api/intent/realized";
  public static final String Search_enUS_StringFormatUrl = "%s/en-us/api/intent/realized";
  public static final String GET_enUS_OpenApiUri = "/en-us/api/intent/realized/{tenantRealizedId}";
  public static final String GET_enUS_StringFormatUri = "/en-us/api/intent/realized/%s";
  public static final String GET_enUS_StringFormatUrl = "%s/en-us/api/intent/realized/%s";
  public static final String PATCH_enUS_OpenApiUri = "/en-us/api/intent/realized";
  public static final String PATCH_enUS_StringFormatUri = "/en-us/api/intent/realized";
  public static final String PATCH_enUS_StringFormatUrl = "%s/en-us/api/intent/realized";
  public static final String POST_enUS_OpenApiUri = "/en-us/api/intent/realized";
  public static final String POST_enUS_StringFormatUri = "/en-us/api/intent/realized";
  public static final String POST_enUS_StringFormatUrl = "%s/en-us/api/intent/realized";
  public static final String DELETE_enUS_OpenApiUri = "/en-us/api/intent/realized/{tenantRealizedId}";
  public static final String DELETE_enUS_StringFormatUri = "/en-us/api/intent/realized/%s";
  public static final String DELETE_enUS_StringFormatUrl = "%s/en-us/api/intent/realized/%s";
  public static final String PUTImport_enUS_OpenApiUri = "/en-us/api/intent/realized-import";
  public static final String PUTImport_enUS_StringFormatUri = "/en-us/api/intent/realized-import";
  public static final String PUTImport_enUS_StringFormatUrl = "%s/en-us/api/intent/realized-import";
  public static final String SearchPage_enUS_OpenApiUri = "/en-us/search/realized/tenant";
  public static final String SearchPage_enUS_StringFormatUri = "/en-us/search/realized/tenant";
  public static final String SearchPage_enUS_StringFormatUrl = "%s/en-us/search/realized/tenant";
  public static final String EditPage_enUS_OpenApiUri = "/en-us/edit/realized/tenant/{tenantResource}";
  public static final String EditPage_enUS_StringFormatUri = "/en-us/edit/realized/tenant/%s";
  public static final String EditPage_enUS_StringFormatUrl = "%s/en-us/edit/realized/tenant/%s";
  public static final String DELETEFilter_enUS_OpenApiUri = "/en-us/api/intent/realized";
  public static final String DELETEFilter_enUS_StringFormatUri = "/en-us/api/intent/realized";
  public static final String DELETEFilter_enUS_StringFormatUrl = "%s/en-us/api/intent/realized";

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
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.tenant.realized.TenantRealized&fq=entiteVar_enUS_indexed_string:tenantName">Find the entity tenantName in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _tenantName(Wrap<String> w);

  public String getTenantName() {
    return tenantName;
  }
  public void setTenantName(String o) {
    this.tenantName = TenantRealized.staticSetTenantName(siteRequest_, o);
  }
  public static String staticSetTenantName(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected TenantRealized tenantNameInit() {
    Wrap<String> tenantNameWrap = new Wrap<String>().var("tenantName");
    if(tenantName == null) {
      _tenantName(tenantNameWrap);
      Optional.ofNullable(tenantNameWrap.getO()).ifPresent(o -> {
        setTenantName(o);
      });
    }
    return (TenantRealized)this;
  }

  public static String staticSearchTenantName(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrTenantName(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqTenantName(SiteRequest siteRequest_, String o) {
    return TenantRealized.staticSearchTenantName(siteRequest_, TenantRealized.staticSetTenantName(siteRequest_, o)).toString();
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
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.tenant.realized.TenantRealized&fq=entiteVar_enUS_indexed_string:tenantId">Find the entity tenantId in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _tenantId(Wrap<String> w);

  public String getTenantId() {
    return tenantId;
  }
  public void setTenantId(String o) {
    this.tenantId = TenantRealized.staticSetTenantId(siteRequest_, o);
  }
  public static String staticSetTenantId(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected TenantRealized tenantIdInit() {
    Wrap<String> tenantIdWrap = new Wrap<String>().var("tenantId");
    if(tenantId == null) {
      _tenantId(tenantIdWrap);
      Optional.ofNullable(tenantIdWrap.getO()).ifPresent(o -> {
        setTenantId(o);
      });
    }
    return (TenantRealized)this;
  }

  public static String staticSearchTenantId(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrTenantId(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqTenantId(SiteRequest siteRequest_, String o) {
    return TenantRealized.staticSearchTenantId(siteRequest_, TenantRealized.staticSetTenantId(siteRequest_, o)).toString();
  }

  public String sqlTenantId() {
    return tenantId;
  }

  public static String staticJsonTenantId(String tenantId) {
    return tenantId;
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
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.tenant.realized.TenantRealized&fq=entiteVar_enUS_indexed_string:tenantDescription">Find the entity tenantDescription in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _tenantDescription(Wrap<String> w);

  public String getTenantDescription() {
    return tenantDescription;
  }
  public void setTenantDescription(String o) {
    this.tenantDescription = TenantRealized.staticSetTenantDescription(siteRequest_, o);
  }
  public static String staticSetTenantDescription(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected TenantRealized tenantDescriptionInit() {
    Wrap<String> tenantDescriptionWrap = new Wrap<String>().var("tenantDescription");
    if(tenantDescription == null) {
      _tenantDescription(tenantDescriptionWrap);
      Optional.ofNullable(tenantDescriptionWrap.getO()).ifPresent(o -> {
        setTenantDescription(o);
      });
    }
    return (TenantRealized)this;
  }

  public static String staticSearchTenantDescription(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrTenantDescription(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqTenantDescription(SiteRequest siteRequest_, String o) {
    return TenantRealized.staticSearchTenantDescription(siteRequest_, TenantRealized.staticSetTenantDescription(siteRequest_, o)).toString();
  }

  public String sqlTenantDescription() {
    return tenantDescription;
  }

  public static String staticJsonTenantDescription(String tenantDescription) {
    return tenantDescription;
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
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.tenant.realized.TenantRealized&fq=entiteVar_enUS_indexed_string:tenantResource">Find the entity tenantResource in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _tenantResource(Wrap<String> w);

  public String getTenantResource() {
    return tenantResource;
  }
  public void setTenantResource(String o) {
    this.tenantResource = TenantRealized.staticSetTenantResource(siteRequest_, o);
  }
  public static String staticSetTenantResource(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected TenantRealized tenantResourceInit() {
    Wrap<String> tenantResourceWrap = new Wrap<String>().var("tenantResource");
    if(tenantResource == null) {
      _tenantResource(tenantResourceWrap);
      Optional.ofNullable(tenantResourceWrap.getO()).ifPresent(o -> {
        setTenantResource(o);
      });
    }
    return (TenantRealized)this;
  }

  public static String staticSearchTenantResource(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrTenantResource(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqTenantResource(SiteRequest siteRequest_, String o) {
    return TenantRealized.staticSearchTenantResource(siteRequest_, TenantRealized.staticSetTenantResource(siteRequest_, o)).toString();
  }

  public String sqlTenantResource() {
    return tenantResource;
  }

  public static String staticJsonTenantResource(String tenantResource) {
    return tenantResource;
  }

	//////////////////////////
  // tenantRealizedNumber //
	//////////////////////////


  /**
   *  The entity tenantRealizedNumber
   *	 is defined as null before being initialized. 
   */
  @JsonProperty
  @JsonSerialize(using = ToStringSerializer.class)
  @JsonInclude(Include.NON_NULL)
  protected Integer tenantRealizedNumber;

  /**
   * <br> The entity tenantRealizedNumber
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.tenant.realized.TenantRealized&fq=entiteVar_enUS_indexed_string:tenantRealizedNumber">Find the entity tenantRealizedNumber in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _tenantRealizedNumber(Wrap<Integer> w);

  public Integer getTenantRealizedNumber() {
    return tenantRealizedNumber;
  }

  public void setTenantRealizedNumber(Integer tenantRealizedNumber) {
    this.tenantRealizedNumber = tenantRealizedNumber;
  }
  @JsonIgnore
  public void setTenantRealizedNumber(String o) {
    this.tenantRealizedNumber = TenantRealized.staticSetTenantRealizedNumber(siteRequest_, o);
  }
  public static Integer staticSetTenantRealizedNumber(SiteRequest siteRequest_, String o) {
    if(NumberUtils.isParsable(o))
      return Integer.parseInt(o);
    return null;
  }
  protected TenantRealized tenantRealizedNumberInit() {
    Wrap<Integer> tenantRealizedNumberWrap = new Wrap<Integer>().var("tenantRealizedNumber");
    if(tenantRealizedNumber == null) {
      _tenantRealizedNumber(tenantRealizedNumberWrap);
      Optional.ofNullable(tenantRealizedNumberWrap.getO()).ifPresent(o -> {
        setTenantRealizedNumber(o);
      });
    }
    return (TenantRealized)this;
  }

  public static Integer staticSearchTenantRealizedNumber(SiteRequest siteRequest_, Integer o) {
    return o;
  }

  public static String staticSearchStrTenantRealizedNumber(SiteRequest siteRequest_, Integer o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqTenantRealizedNumber(SiteRequest siteRequest_, String o) {
    return TenantRealized.staticSearchTenantRealizedNumber(siteRequest_, TenantRealized.staticSetTenantRealizedNumber(siteRequest_, o)).toString();
  }

  public Integer sqlTenantRealizedNumber() {
    return tenantRealizedNumber;
  }

  public static String staticJsonTenantRealizedNumber(Integer tenantRealizedNumber) {
    return Optional.ofNullable(tenantRealizedNumber).map(v -> v.toString()).orElse(null);
  }

	//////////////////////
  // tenantRealizedId //
	//////////////////////


  /**
   *  The entity tenantRealizedId
   *	 is defined as null before being initialized. 
   */
  @JsonProperty
  @JsonSerialize(using = ToStringSerializer.class)
  @JsonInclude(Include.NON_NULL)
  protected Integer tenantRealizedId;

  /**
   * <br> The entity tenantRealizedId
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.tenant.realized.TenantRealized&fq=entiteVar_enUS_indexed_string:tenantRealizedId">Find the entity tenantRealizedId in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _tenantRealizedId(Wrap<Integer> w);

  public Integer getTenantRealizedId() {
    return tenantRealizedId;
  }

  public void setTenantRealizedId(Integer tenantRealizedId) {
    this.tenantRealizedId = tenantRealizedId;
  }
  @JsonIgnore
  public void setTenantRealizedId(String o) {
    this.tenantRealizedId = TenantRealized.staticSetTenantRealizedId(siteRequest_, o);
  }
  public static Integer staticSetTenantRealizedId(SiteRequest siteRequest_, String o) {
    if(NumberUtils.isParsable(o))
      return Integer.parseInt(o);
    return null;
  }
  protected TenantRealized tenantRealizedIdInit() {
    Wrap<Integer> tenantRealizedIdWrap = new Wrap<Integer>().var("tenantRealizedId");
    if(tenantRealizedId == null) {
      _tenantRealizedId(tenantRealizedIdWrap);
      Optional.ofNullable(tenantRealizedIdWrap.getO()).ifPresent(o -> {
        setTenantRealizedId(o);
      });
    }
    return (TenantRealized)this;
  }

  public static Integer staticSearchTenantRealizedId(SiteRequest siteRequest_, Integer o) {
    return o;
  }

  public static String staticSearchStrTenantRealizedId(SiteRequest siteRequest_, Integer o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqTenantRealizedId(SiteRequest siteRequest_, String o) {
    return TenantRealized.staticSearchTenantRealizedId(siteRequest_, TenantRealized.staticSetTenantRealizedId(siteRequest_, o)).toString();
  }

  public Integer sqlTenantRealizedId() {
    return tenantRealizedId;
  }

  public static String staticJsonTenantRealizedId(Integer tenantRealizedId) {
    return Optional.ofNullable(tenantRealizedId).map(v -> v.toString()).orElse(null);
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
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.tenant.realized.TenantRealized&fq=entiteVar_enUS_indexed_string:createdByEmail">Find the entity createdByEmail in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _createdByEmail(Wrap<String> w);

  public String getCreatedByEmail() {
    return createdByEmail;
  }
  public void setCreatedByEmail(String o) {
    this.createdByEmail = TenantRealized.staticSetCreatedByEmail(siteRequest_, o);
  }
  public static String staticSetCreatedByEmail(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected TenantRealized createdByEmailInit() {
    Wrap<String> createdByEmailWrap = new Wrap<String>().var("createdByEmail");
    if(createdByEmail == null) {
      _createdByEmail(createdByEmailWrap);
      Optional.ofNullable(createdByEmailWrap.getO()).ifPresent(o -> {
        setCreatedByEmail(o);
      });
    }
    return (TenantRealized)this;
  }

  public static String staticSearchCreatedByEmail(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrCreatedByEmail(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqCreatedByEmail(SiteRequest siteRequest_, String o) {
    return TenantRealized.staticSearchCreatedByEmail(siteRequest_, TenantRealized.staticSetCreatedByEmail(siteRequest_, o)).toString();
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
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.tenant.realized.TenantRealized&fq=entiteVar_enUS_indexed_string:createdByUserId">Find the entity createdByUserId in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _createdByUserId(Wrap<String> w);

  public String getCreatedByUserId() {
    return createdByUserId;
  }
  public void setCreatedByUserId(String o) {
    this.createdByUserId = TenantRealized.staticSetCreatedByUserId(siteRequest_, o);
  }
  public static String staticSetCreatedByUserId(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected TenantRealized createdByUserIdInit() {
    Wrap<String> createdByUserIdWrap = new Wrap<String>().var("createdByUserId");
    if(createdByUserId == null) {
      _createdByUserId(createdByUserIdWrap);
      Optional.ofNullable(createdByUserIdWrap.getO()).ifPresent(o -> {
        setCreatedByUserId(o);
      });
    }
    return (TenantRealized)this;
  }

  public static String staticSearchCreatedByUserId(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrCreatedByUserId(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqCreatedByUserId(SiteRequest siteRequest_, String o) {
    return TenantRealized.staticSearchCreatedByUserId(siteRequest_, TenantRealized.staticSetCreatedByUserId(siteRequest_, o)).toString();
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
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.tenant.realized.TenantRealized&fq=entiteVar_enUS_indexed_string:createdByFullName">Find the entity createdByFullName in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _createdByFullName(Wrap<String> w);

  public String getCreatedByFullName() {
    return createdByFullName;
  }
  public void setCreatedByFullName(String o) {
    this.createdByFullName = TenantRealized.staticSetCreatedByFullName(siteRequest_, o);
  }
  public static String staticSetCreatedByFullName(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected TenantRealized createdByFullNameInit() {
    Wrap<String> createdByFullNameWrap = new Wrap<String>().var("createdByFullName");
    if(createdByFullName == null) {
      _createdByFullName(createdByFullNameWrap);
      Optional.ofNullable(createdByFullNameWrap.getO()).ifPresent(o -> {
        setCreatedByFullName(o);
      });
    }
    return (TenantRealized)this;
  }

  public static String staticSearchCreatedByFullName(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrCreatedByFullName(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqCreatedByFullName(SiteRequest siteRequest_, String o) {
    return TenantRealized.staticSearchCreatedByFullName(siteRequest_, TenantRealized.staticSetCreatedByFullName(siteRequest_, o)).toString();
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
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.tenant.realized.TenantRealized&fq=entiteVar_enUS_indexed_string:createdVia">Find the entity createdVia in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _createdVia(Wrap<String> w);

  public String getCreatedVia() {
    return createdVia;
  }
  public void setCreatedVia(String o) {
    this.createdVia = TenantRealized.staticSetCreatedVia(siteRequest_, o);
  }
  public static String staticSetCreatedVia(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected TenantRealized createdViaInit() {
    Wrap<String> createdViaWrap = new Wrap<String>().var("createdVia");
    if(createdVia == null) {
      _createdVia(createdViaWrap);
      Optional.ofNullable(createdViaWrap.getO()).ifPresent(o -> {
        setCreatedVia(o);
      });
    }
    return (TenantRealized)this;
  }

  public static String staticSearchCreatedVia(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrCreatedVia(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqCreatedVia(SiteRequest siteRequest_, String o) {
    return TenantRealized.staticSearchCreatedVia(siteRequest_, TenantRealized.staticSetCreatedVia(siteRequest_, o)).toString();
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
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.tenant.realized.TenantRealized&fq=entiteVar_enUS_indexed_string:intentState">Find the entity intentState in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _intentState(Wrap<String> w);

  public String getIntentState() {
    return intentState;
  }
  public void setIntentState(String o) {
    this.intentState = TenantRealized.staticSetIntentState(siteRequest_, o);
  }
  public static String staticSetIntentState(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected TenantRealized intentStateInit() {
    Wrap<String> intentStateWrap = new Wrap<String>().var("intentState");
    if(intentState == null) {
      _intentState(intentStateWrap);
      Optional.ofNullable(intentStateWrap.getO()).ifPresent(o -> {
        setIntentState(o);
      });
    }
    return (TenantRealized)this;
  }

  public static String staticSearchIntentState(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrIntentState(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqIntentState(SiteRequest siteRequest_, String o) {
    return TenantRealized.staticSearchIntentState(siteRequest_, TenantRealized.staticSetIntentState(siteRequest_, o)).toString();
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
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.tenant.realized.TenantRealized&fq=entiteVar_enUS_indexed_string:requestedState">Find the entity requestedState in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _requestedState(Wrap<String> w);

  public String getRequestedState() {
    return requestedState;
  }
  public void setRequestedState(String o) {
    this.requestedState = TenantRealized.staticSetRequestedState(siteRequest_, o);
  }
  public static String staticSetRequestedState(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected TenantRealized requestedStateInit() {
    Wrap<String> requestedStateWrap = new Wrap<String>().var("requestedState");
    if(requestedState == null) {
      _requestedState(requestedStateWrap);
      Optional.ofNullable(requestedStateWrap.getO()).ifPresent(o -> {
        setRequestedState(o);
      });
    }
    return (TenantRealized)this;
  }

  public static String staticSearchRequestedState(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrRequestedState(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqRequestedState(SiteRequest siteRequest_, String o) {
    return TenantRealized.staticSearchRequestedState(siteRequest_, TenantRealized.staticSetRequestedState(siteRequest_, o)).toString();
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
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.tenant.realized.TenantRealized&fq=entiteVar_enUS_indexed_string:realizedState">Find the entity realizedState in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _realizedState(Wrap<String> w);

  public String getRealizedState() {
    return realizedState;
  }
  public void setRealizedState(String o) {
    this.realizedState = TenantRealized.staticSetRealizedState(siteRequest_, o);
  }
  public static String staticSetRealizedState(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected TenantRealized realizedStateInit() {
    Wrap<String> realizedStateWrap = new Wrap<String>().var("realizedState");
    if(realizedState == null) {
      _realizedState(realizedStateWrap);
      Optional.ofNullable(realizedStateWrap.getO()).ifPresent(o -> {
        setRealizedState(o);
      });
    }
    return (TenantRealized)this;
  }

  public static String staticSearchRealizedState(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrRealizedState(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqRealizedState(SiteRequest siteRequest_, String o) {
    return TenantRealized.staticSearchRealizedState(siteRequest_, TenantRealized.staticSetRealizedState(siteRequest_, o)).toString();
  }

  public String sqlRealizedState() {
    return realizedState;
  }

  public static String staticJsonRealizedState(String realizedState) {
    return realizedState;
  }

  //////////////
  // initDeep //
  //////////////

  public Future<TenantRealizedGen<DEV>> promiseDeepTenantRealized(SiteRequest siteRequest_) {
    if(this.siteRequest_ == null)
      setSiteRequest_(siteRequest_);
    return promiseDeepTenantRealized();
  }

  public Future<TenantRealizedGen<DEV>> promiseDeepTenantRealized() {
    Promise<TenantRealizedGen<DEV>> promise = Promise.promise();
    Promise<Void> promise2 = Promise.promise();
    promiseTenantRealized(promise2);
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

  public Future<Void> promiseTenantRealized(Promise<Void> promise) {
    Future.future(a -> a.complete()).compose(a -> {
      Promise<Void> promise2 = Promise.promise();
      try {
        tenantNameInit();
        tenantIdInit();
        tenantDescriptionInit();
        tenantResourceInit();
        tenantRealizedNumberInit();
        tenantRealizedIdInit();
        createdByEmailInit();
        createdByUserIdInit();
        createdByFullNameInit();
        createdViaInit();
        intentStateInit();
        requestedStateInit();
        realizedStateInit();
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

  @Override public Future<? extends TenantRealizedGen<DEV>> promiseDeepForClass(SiteRequest siteRequest_) {
    return promiseDeepTenantRealized(siteRequest_);
  }

  /////////////////
  // siteRequest //
  /////////////////

  public void siteRequestTenantRealized(SiteRequest siteRequest_) {
      super.siteRequestTenant(siteRequest_);
  }

  public void siteRequestForClass(SiteRequest siteRequest_) {
    siteRequestTenantRealized(siteRequest_);
  }

  /////////////
  // obtain //
  /////////////

  @Override public Object obtainForClass(String var) {
    String[] vars = StringUtils.split(var, ".");
    Object o = null;
    for(String v : vars) {
      if(o == null)
        o = obtainTenantRealized(v);
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
  public Object obtainTenantRealized(String var) {
    TenantRealized oTenantRealized = (TenantRealized)this;
    switch(var) {
      case "tenantName":
        return oTenantRealized.tenantName;
      case "tenantId":
        return oTenantRealized.tenantId;
      case "tenantDescription":
        return oTenantRealized.tenantDescription;
      case "tenantResource":
        return oTenantRealized.tenantResource;
      case "tenantRealizedNumber":
        return oTenantRealized.tenantRealizedNumber;
      case "tenantRealizedId":
        return oTenantRealized.tenantRealizedId;
      case "createdByEmail":
        return oTenantRealized.createdByEmail;
      case "createdByUserId":
        return oTenantRealized.createdByUserId;
      case "createdByFullName":
        return oTenantRealized.createdByFullName;
      case "createdVia":
        return oTenantRealized.createdVia;
      case "intentState":
        return oTenantRealized.intentState;
      case "requestedState":
        return oTenantRealized.requestedState;
      case "realizedState":
        return oTenantRealized.realizedState;
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
        o = relateTenantRealized(v, val);
      else if(o instanceof BaseModel) {
        BaseModel baseModel = (BaseModel)o;
        o = baseModel.relateForClass(v, val);
      }
    }
    return o != null;
  }
  public Object relateTenantRealized(String var, Object val) {
    TenantRealized oTenantRealized = (TenantRealized)this;
    switch(var) {
      case "tenantResource":
        if(oTenantRealized.getTenantResource() == null)
          oTenantRealized.setTenantResource(Optional.ofNullable(val).map(v -> v.toString()).orElse(null));
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

  public static Object staticSetForClass(String entityVar, SiteRequest siteRequest_, String v, TenantRealized o) {
    return staticSetTenantRealized(entityVar,  siteRequest_, v, o);
  }
  public static Object staticSetTenantRealized(String entityVar, SiteRequest siteRequest_, String v, TenantRealized o) {
    switch(entityVar) {
    case "tenantName":
      return TenantRealized.staticSetTenantName(siteRequest_, v);
    case "tenantId":
      return TenantRealized.staticSetTenantId(siteRequest_, v);
    case "tenantDescription":
      return TenantRealized.staticSetTenantDescription(siteRequest_, v);
    case "tenantResource":
      return TenantRealized.staticSetTenantResource(siteRequest_, v);
    case "tenantRealizedNumber":
      return TenantRealized.staticSetTenantRealizedNumber(siteRequest_, v);
    case "tenantRealizedId":
      return TenantRealized.staticSetTenantRealizedId(siteRequest_, v);
    case "createdByEmail":
      return TenantRealized.staticSetCreatedByEmail(siteRequest_, v);
    case "createdByUserId":
      return TenantRealized.staticSetCreatedByUserId(siteRequest_, v);
    case "createdByFullName":
      return TenantRealized.staticSetCreatedByFullName(siteRequest_, v);
    case "createdVia":
      return TenantRealized.staticSetCreatedVia(siteRequest_, v);
    case "intentState":
      return TenantRealized.staticSetIntentState(siteRequest_, v);
    case "requestedState":
      return TenantRealized.staticSetRequestedState(siteRequest_, v);
    case "realizedState":
      return TenantRealized.staticSetRealizedState(siteRequest_, v);
      default:
        return Tenant.staticSetTenant(entityVar,  siteRequest_, v, o);
    }
  }

  //////////////////
  // staticSearch //
  //////////////////

  public static Future<TenantRealized> fqTenantRealized(SiteRequest siteRequest, String var, Object val) {
    Promise<TenantRealized> promise = Promise.promise();
    try {
      if(val == null) {
        promise.complete();
      } else {
        SearchList<TenantRealized> searchList = new SearchList<TenantRealized>();
        searchList.setStore(true);
        searchList.q("*:*");
        searchList.setC(TenantRealized.class);
        searchList.fq(String.format("%s:", TenantRealized.varIndexedTenantRealized(var)) + SearchTool.escapeQueryChars(val.toString()));
        searchList.promiseDeepForClass(siteRequest).onSuccess(a -> {
          try {
            promise.complete(searchList.getList().stream().findFirst().orElse(null));
          } catch(Throwable ex) {
            LOG.error("Error while querying the realized tenant", ex);
            promise.fail(ex);
          }
        }).onFailure(ex -> {
          LOG.error("Error while querying the realized tenant", ex);
          promise.fail(ex);
        });
      }
    } catch(Throwable ex) {
      LOG.error("Error while querying the realized tenant", ex);
      promise.fail(ex);
    }
    return promise.future();
  }

  public static Object staticSearchForClass(String entityVar, SiteRequest siteRequest_, Object o) {
    return staticSearchTenantRealized(entityVar,  siteRequest_, o);
  }
  public static Object staticSearchTenantRealized(String entityVar, SiteRequest siteRequest_, Object o) {
    switch(entityVar) {
    case "tenantName":
      return TenantRealized.staticSearchTenantName(siteRequest_, (String)o);
    case "tenantId":
      return TenantRealized.staticSearchTenantId(siteRequest_, (String)o);
    case "tenantDescription":
      return TenantRealized.staticSearchTenantDescription(siteRequest_, (String)o);
    case "tenantResource":
      return TenantRealized.staticSearchTenantResource(siteRequest_, (String)o);
    case "tenantRealizedNumber":
      return TenantRealized.staticSearchTenantRealizedNumber(siteRequest_, (Integer)o);
    case "tenantRealizedId":
      return TenantRealized.staticSearchTenantRealizedId(siteRequest_, (Integer)o);
    case "createdByEmail":
      return TenantRealized.staticSearchCreatedByEmail(siteRequest_, (String)o);
    case "createdByUserId":
      return TenantRealized.staticSearchCreatedByUserId(siteRequest_, (String)o);
    case "createdByFullName":
      return TenantRealized.staticSearchCreatedByFullName(siteRequest_, (String)o);
    case "createdVia":
      return TenantRealized.staticSearchCreatedVia(siteRequest_, (String)o);
    case "intentState":
      return TenantRealized.staticSearchIntentState(siteRequest_, (String)o);
    case "requestedState":
      return TenantRealized.staticSearchRequestedState(siteRequest_, (String)o);
    case "realizedState":
      return TenantRealized.staticSearchRealizedState(siteRequest_, (String)o);
      default:
        return Tenant.staticSearchTenant(entityVar,  siteRequest_, o);
    }
  }

  ///////////////////
  // staticSearchStr //
  ///////////////////

  public static String staticSearchStrForClass(String entityVar, SiteRequest siteRequest_, Object o) {
    return staticSearchStrTenantRealized(entityVar,  siteRequest_, o);
  }
  public static String staticSearchStrTenantRealized(String entityVar, SiteRequest siteRequest_, Object o) {
    switch(entityVar) {
    case "tenantName":
      return TenantRealized.staticSearchStrTenantName(siteRequest_, (String)o);
    case "tenantId":
      return TenantRealized.staticSearchStrTenantId(siteRequest_, (String)o);
    case "tenantDescription":
      return TenantRealized.staticSearchStrTenantDescription(siteRequest_, (String)o);
    case "tenantResource":
      return TenantRealized.staticSearchStrTenantResource(siteRequest_, (String)o);
    case "tenantRealizedNumber":
      return TenantRealized.staticSearchStrTenantRealizedNumber(siteRequest_, (Integer)o);
    case "tenantRealizedId":
      return TenantRealized.staticSearchStrTenantRealizedId(siteRequest_, (Integer)o);
    case "createdByEmail":
      return TenantRealized.staticSearchStrCreatedByEmail(siteRequest_, (String)o);
    case "createdByUserId":
      return TenantRealized.staticSearchStrCreatedByUserId(siteRequest_, (String)o);
    case "createdByFullName":
      return TenantRealized.staticSearchStrCreatedByFullName(siteRequest_, (String)o);
    case "createdVia":
      return TenantRealized.staticSearchStrCreatedVia(siteRequest_, (String)o);
    case "intentState":
      return TenantRealized.staticSearchStrIntentState(siteRequest_, (String)o);
    case "requestedState":
      return TenantRealized.staticSearchStrRequestedState(siteRequest_, (String)o);
    case "realizedState":
      return TenantRealized.staticSearchStrRealizedState(siteRequest_, (String)o);
      default:
        return Tenant.staticSearchStrTenant(entityVar,  siteRequest_, o);
    }
  }

  //////////////////
  // staticSearchFq //
  //////////////////

  public static String staticSearchFqForClass(String entityVar, SiteRequest siteRequest_, String o) {
    return staticSearchFqTenantRealized(entityVar,  siteRequest_, o);
  }
  public static String staticSearchFqTenantRealized(String entityVar, SiteRequest siteRequest_, String o) {
    switch(entityVar) {
    case "tenantName":
      return TenantRealized.staticSearchFqTenantName(siteRequest_, o);
    case "tenantId":
      return TenantRealized.staticSearchFqTenantId(siteRequest_, o);
    case "tenantDescription":
      return TenantRealized.staticSearchFqTenantDescription(siteRequest_, o);
    case "tenantResource":
      return TenantRealized.staticSearchFqTenantResource(siteRequest_, o);
    case "tenantRealizedNumber":
      return TenantRealized.staticSearchFqTenantRealizedNumber(siteRequest_, o);
    case "tenantRealizedId":
      return TenantRealized.staticSearchFqTenantRealizedId(siteRequest_, o);
    case "createdByEmail":
      return TenantRealized.staticSearchFqCreatedByEmail(siteRequest_, o);
    case "createdByUserId":
      return TenantRealized.staticSearchFqCreatedByUserId(siteRequest_, o);
    case "createdByFullName":
      return TenantRealized.staticSearchFqCreatedByFullName(siteRequest_, o);
    case "createdVia":
      return TenantRealized.staticSearchFqCreatedVia(siteRequest_, o);
    case "intentState":
      return TenantRealized.staticSearchFqIntentState(siteRequest_, o);
    case "requestedState":
      return TenantRealized.staticSearchFqRequestedState(siteRequest_, o);
    case "realizedState":
      return TenantRealized.staticSearchFqRealizedState(siteRequest_, o);
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
          o = persistTenantRealized(v, val);
        else if(o instanceof BaseModel) {
          BaseModel oBaseModel = (BaseModel)o;
          o = oBaseModel.persistForClass(v, val);
        }
      }
    }
    return o != null;
  }
  public Object persistTenantRealized(String var, Object val) {
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
      } else if("tenantdescription".equals(varLower)) {
        if(val instanceof String) {
          setTenantDescription((String)val);
        }
        saves.add("tenantDescription");
        return val;
      } else if("tenantresource".equals(varLower)) {
        if(val instanceof String) {
          setTenantResource((String)val);
        }
        saves.add("tenantResource");
        return val;
      } else if("tenantrealizednumber".equals(varLower)) {
        if(val instanceof Integer) {
          setTenantRealizedNumber((Integer)val);
        } else {
          setTenantRealizedNumber(val == null ? null : val.toString());
        }
        saves.add("tenantRealizedNumber");
        return val;
      } else if("tenantrealizedid".equals(varLower)) {
        if(val instanceof Integer) {
          setTenantRealizedId((Integer)val);
        } else {
          setTenantRealizedId(val == null ? null : val.toString());
        }
        saves.add("tenantRealizedId");
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
    } else {
      return super.persistTenant(var, val);
    }
  }

  /////////////
  // populate //
  /////////////

  @Override public void populateForClass(SolrResponse.Doc doc) {
    populateTenantRealized(doc);
  }
  public void populateTenantRealized(SolrResponse.Doc doc) {
    TenantRealized oTenantRealized = (TenantRealized)this;
    saves = Optional.ofNullable((ArrayList<String>)doc.get("saves_docvalues_strings")).orElse(new ArrayList<String>());
    if(saves != null) {

      if(saves.contains("tenantName")) {
        String tenantName = (String)doc.get("tenantName_docvalues_string");
        if(tenantName != null)
          oTenantRealized.setTenantName(tenantName);
      }

      if(saves.contains("tenantId")) {
        String tenantId = (String)doc.get("tenantId_docvalues_string");
        if(tenantId != null)
          oTenantRealized.setTenantId(tenantId);
      }

      if(saves.contains("tenantDescription")) {
        String tenantDescription = (String)doc.get("tenantDescription_docvalues_string");
        if(tenantDescription != null)
          oTenantRealized.setTenantDescription(tenantDescription);
      }

      String tenantResource = (String)doc.get("tenantResource_docvalues_string");
      if(tenantResource != null)
        oTenantRealized.setTenantResource(tenantResource);

      if(saves.contains("tenantRealizedNumber")) {
        Integer tenantRealizedNumber = (Integer)doc.get("tenantRealizedNumber_docvalues_int");
        if(tenantRealizedNumber != null)
          oTenantRealized.setTenantRealizedNumber(tenantRealizedNumber);
      }

      if(saves.contains("tenantRealizedId")) {
        Integer tenantRealizedId = (Integer)doc.get("tenantRealizedId_docvalues_int");
        if(tenantRealizedId != null)
          oTenantRealized.setTenantRealizedId(tenantRealizedId);
      }

      if(saves.contains("createdByEmail")) {
        String createdByEmail = (String)doc.get("createdByEmail_docvalues_string");
        if(createdByEmail != null)
          oTenantRealized.setCreatedByEmail(createdByEmail);
      }

      if(saves.contains("createdByUserId")) {
        String createdByUserId = (String)doc.get("createdByUserId_docvalues_string");
        if(createdByUserId != null)
          oTenantRealized.setCreatedByUserId(createdByUserId);
      }

      if(saves.contains("createdByFullName")) {
        String createdByFullName = (String)doc.get("createdByFullName_docvalues_string");
        if(createdByFullName != null)
          oTenantRealized.setCreatedByFullName(createdByFullName);
      }

      if(saves.contains("createdVia")) {
        String createdVia = (String)doc.get("createdVia_docvalues_string");
        if(createdVia != null)
          oTenantRealized.setCreatedVia(createdVia);
      }

      if(saves.contains("intentState")) {
        String intentState = (String)doc.get("intentState_docvalues_string");
        if(intentState != null)
          oTenantRealized.setIntentState(intentState);
      }

      if(saves.contains("requestedState")) {
        String requestedState = (String)doc.get("requestedState_docvalues_string");
        if(requestedState != null)
          oTenantRealized.setRequestedState(requestedState);
      }

      if(saves.contains("realizedState")) {
        String realizedState = (String)doc.get("realizedState_docvalues_string");
        if(realizedState != null)
          oTenantRealized.setRealizedState(realizedState);
      }
    }

    super.populateTenant(doc);
  }

  public void indexTenantRealized(JsonObject doc) {
    if(tenantName != null) {
      doc.put("tenantName_docvalues_string", tenantName);
    }
    if(tenantId != null) {
      doc.put("tenantId_docvalues_string", tenantId);
    }
    if(tenantDescription != null) {
      doc.put("tenantDescription_docvalues_string", tenantDescription);
    }
    if(tenantResource != null) {
      doc.put("tenantResource_docvalues_string", tenantResource);
    }
    if(tenantRealizedNumber != null) {
      doc.put("tenantRealizedNumber_docvalues_int", tenantRealizedNumber);
    }
    if(tenantRealizedId != null) {
      doc.put("tenantRealizedId_docvalues_int", tenantRealizedId);
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
    super.indexTenant(doc);

	}

  public static String varStoredTenantRealized(String entityVar) {
    switch(entityVar) {
      case "tenantName":
        return "tenantName_docvalues_string";
      case "tenantId":
        return "tenantId_docvalues_string";
      case "tenantDescription":
        return "tenantDescription_docvalues_string";
      case "tenantResource":
        return "tenantResource_docvalues_string";
      case "tenantRealizedNumber":
        return "tenantRealizedNumber_docvalues_int";
      case "tenantRealizedId":
        return "tenantRealizedId_docvalues_int";
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
      default:
        return Tenant.varStoredTenant(entityVar);
    }
  }

  public static String varIndexedTenantRealized(String entityVar) {
    switch(entityVar) {
      case "tenantName":
        return "tenantName_docvalues_string";
      case "tenantId":
        return "tenantId_docvalues_string";
      case "tenantDescription":
        return "tenantDescription_docvalues_string";
      case "tenantResource":
        return "tenantResource_docvalues_string";
      case "tenantRealizedNumber":
        return "tenantRealizedNumber_docvalues_int";
      case "tenantRealizedId":
        return "tenantRealizedId_docvalues_int";
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
      default:
        return Tenant.varIndexedTenant(entityVar);
    }
  }

  public static String searchVarTenantRealized(String searchVar) {
    switch(searchVar) {
      case "tenantName_docvalues_string":
        return "tenantName";
      case "tenantId_docvalues_string":
        return "tenantId";
      case "tenantDescription_docvalues_string":
        return "tenantDescription";
      case "tenantResource_docvalues_string":
        return "tenantResource";
      case "tenantRealizedNumber_docvalues_int":
        return "tenantRealizedNumber";
      case "tenantRealizedId_docvalues_int":
        return "tenantRealizedId";
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
      default:
        return Tenant.searchVarTenant(searchVar);
    }
  }

  public static String varSearchTenantRealized(String entityVar) {
    switch(entityVar) {
      default:
        return Tenant.varSearchTenant(entityVar);
    }
  }

  public static String varSuggestedTenantRealized(String entityVar) {
    switch(entityVar) {
      default:
        return Tenant.varSuggestedTenant(entityVar);
    }
  }

  /////////////
  // store //
  /////////////

  @Override public void storeForClass(SolrResponse.Doc doc) {
    storeTenantRealized(doc);
  }
  public void storeTenantRealized(SolrResponse.Doc doc) {
    TenantRealized oTenantRealized = (TenantRealized)this;
    SiteRequest siteRequest = oTenantRealized.getSiteRequest_();

    oTenantRealized.setTenantName(Optional.ofNullable(doc.get("tenantName_docvalues_string")).map(v -> v.toString()).orElse(null));
    oTenantRealized.setTenantId(Optional.ofNullable(doc.get("tenantId_docvalues_string")).map(v -> v.toString()).orElse(null));
    oTenantRealized.setTenantDescription(Optional.ofNullable(doc.get("tenantDescription_docvalues_string")).map(v -> v.toString()).orElse(null));
    oTenantRealized.setTenantResource(Optional.ofNullable(doc.get("tenantResource_docvalues_string")).map(v -> v.toString()).orElse(null));
    oTenantRealized.setTenantRealizedNumber(Optional.ofNullable(doc.get("tenantRealizedNumber_docvalues_int")).map(v -> v.toString()).orElse(null));
    oTenantRealized.setTenantRealizedId(Optional.ofNullable(doc.get("tenantRealizedId_docvalues_int")).map(v -> v.toString()).orElse(null));
    oTenantRealized.setCreatedByEmail(Optional.ofNullable(doc.get("createdByEmail_docvalues_string")).map(v -> v.toString()).orElse(null));
    oTenantRealized.setCreatedByUserId(Optional.ofNullable(doc.get("createdByUserId_docvalues_string")).map(v -> v.toString()).orElse(null));
    oTenantRealized.setCreatedByFullName(Optional.ofNullable(doc.get("createdByFullName_docvalues_string")).map(v -> v.toString()).orElse(null));
    oTenantRealized.setCreatedVia(Optional.ofNullable(doc.get("createdVia_docvalues_string")).map(v -> v.toString()).orElse(null));
    oTenantRealized.setIntentState(Optional.ofNullable(doc.get("intentState_docvalues_string")).map(v -> v.toString()).orElse(null));
    oTenantRealized.setRequestedState(Optional.ofNullable(doc.get("requestedState_docvalues_string")).map(v -> v.toString()).orElse(null));
    oTenantRealized.setRealizedState(Optional.ofNullable(doc.get("realizedState_docvalues_string")).map(v -> v.toString()).orElse(null));

    super.storeTenant(doc);
  }

  //////////////////
  // apiRequest //
  //////////////////

  public void apiRequestTenantRealized() {
    ApiRequest apiRequest = Optional.ofNullable(siteRequest_).map(r -> r.getApiRequest_()).orElse(null);
    Object o = Optional.ofNullable(apiRequest).map(ApiRequest::getOriginal).orElse(null);
    if(o != null && o instanceof TenantRealized) {
      TenantRealized original = (TenantRealized)o;
      if(!Objects.equals(tenantName, original.getTenantName()))
        apiRequest.addVars("tenantName");
      if(!Objects.equals(tenantId, original.getTenantId()))
        apiRequest.addVars("tenantId");
      if(!Objects.equals(tenantDescription, original.getTenantDescription()))
        apiRequest.addVars("tenantDescription");
      if(!Objects.equals(tenantResource, original.getTenantResource()))
        apiRequest.addVars("tenantResource");
      if(!Objects.equals(tenantRealizedNumber, original.getTenantRealizedNumber()))
        apiRequest.addVars("tenantRealizedNumber");
      if(!Objects.equals(tenantRealizedId, original.getTenantRealizedId()))
        apiRequest.addVars("tenantRealizedId");
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
    sb.append(Optional.ofNullable(tenantDescription).map(v -> "tenantDescription: \"" + v + "\"\n" ).orElse(""));
    sb.append(Optional.ofNullable(tenantResource).map(v -> "tenantResource: \"" + v + "\"\n" ).orElse(""));
    sb.append(Optional.ofNullable(tenantRealizedNumber).map(v -> "tenantRealizedNumber: " + v + "\n").orElse(""));
    sb.append(Optional.ofNullable(tenantRealizedId).map(v -> "tenantRealizedId: " + v + "\n").orElse(""));
    sb.append(Optional.ofNullable(createdByEmail).map(v -> "createdByEmail: \"" + v + "\"\n" ).orElse(""));
    sb.append(Optional.ofNullable(createdByUserId).map(v -> "createdByUserId: \"" + v + "\"\n" ).orElse(""));
    sb.append(Optional.ofNullable(createdByFullName).map(v -> "createdByFullName: \"" + v + "\"\n" ).orElse(""));
    sb.append(Optional.ofNullable(createdVia).map(v -> "createdVia: \"" + v + "\"\n" ).orElse(""));
    sb.append(Optional.ofNullable(intentState).map(v -> "intentState: \"" + v + "\"\n" ).orElse(""));
    sb.append(Optional.ofNullable(requestedState).map(v -> "requestedState: \"" + v + "\"\n" ).orElse(""));
    sb.append(Optional.ofNullable(realizedState).map(v -> "realizedState: \"" + v + "\"\n" ).orElse(""));
    return sb.toString();
  }

  public static final String CLASS_SIMPLE_NAME = "TenantRealized";
  public static final String CLASS_CANONICAL_NAME = "org.computate.dcm.model.eda.tenant.realized.TenantRealized";
  public static final String CLASS_AUTH_RESOURCE = "TENANTREALIZED";
  public static final String CLASS_API_ADDRESS_TenantRealized = "dcm-enUS-TenantRealized";
  public static String getClassApiAddress() {
    return CLASS_API_ADDRESS_TenantRealized;
  }
  public static final String VAR_tenantName = "tenantName";
  public static final String SET_tenantName = "setTenantName";
  public static final String VAR_tenantId = "tenantId";
  public static final String SET_tenantId = "setTenantId";
  public static final String VAR_tenantDescription = "tenantDescription";
  public static final String SET_tenantDescription = "setTenantDescription";
  public static final String VAR_tenantResource = "tenantResource";
  public static final String SET_tenantResource = "setTenantResource";
  public static final String VAR_tenantRealizedNumber = "tenantRealizedNumber";
  public static final String SET_tenantRealizedNumber = "setTenantRealizedNumber";
  public static final String VAR_tenantRealizedId = "tenantRealizedId";
  public static final String SET_tenantRealizedId = "setTenantRealizedId";
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

  public static List<String> varsQForClass() {
    return TenantRealized.varsQTenantRealized(new ArrayList<String>());
  }
  public static List<String> varsQTenantRealized(List<String> vars) {
    Tenant.varsQTenant(vars);
    return vars;
  }

  public static List<String> varsFqForClass() {
    return TenantRealized.varsFqTenantRealized(new ArrayList<String>());
  }
  public static List<String> varsFqTenantRealized(List<String> vars) {
    vars.add(VAR_tenantName);
    vars.add(VAR_tenantId);
    vars.add(VAR_tenantDescription);
    vars.add(VAR_tenantResource);
    vars.add(VAR_tenantRealizedNumber);
    vars.add(VAR_tenantRealizedId);
    Tenant.varsFqTenant(vars);
    return vars;
  }

  public static List<String> varsRangeForClass() {
    return TenantRealized.varsRangeTenantRealized(new ArrayList<String>());
  }
  public static List<String> varsRangeTenantRealized(List<String> vars) {
    vars.add(VAR_tenantRealizedNumber);
    vars.add(VAR_tenantRealizedId);
    Tenant.varsRangeTenant(vars);
    return vars;
  }

  public static final String DISPLAY_NAME_tenantName = "tenant name";
  public static final String DISPLAY_NAME_tenantId = "tenant ID";
  public static final String DISPLAY_NAME_tenantDescription = "description";
  public static final String DISPLAY_NAME_tenantResource = "tenant auth resource";
  public static final String DISPLAY_NAME_tenantRealizedNumber = "tenant realized number";
  public static final String DISPLAY_NAME_tenantRealizedId = "tenant realized ID";
  public static final String DISPLAY_NAME_createdByEmail = "created by user email";
  public static final String DISPLAY_NAME_createdByUserId = "created by user ID";
  public static final String DISPLAY_NAME_createdByFullName = "created by user full name";
  public static final String DISPLAY_NAME_createdVia = "created via";
  public static final String DISPLAY_NAME_intentState = "intent state";
  public static final String DISPLAY_NAME_requestedState = "requested state";
  public static final String DISPLAY_NAME_realizedState = "realized state";

  @Override
  public String idForClass() {
    return Optional.ofNullable(tenantRealizedId).map(o -> o.toString()).orElse(null);
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
    return TenantRealized.NameAdjectiveSingular_enUS;
  }

  @Override
  public String descriptionForClass() {
    return tenantDescription;
  }

  @Override
  public String enUSStringFormatUrlEditPageForClass() {
    return "%s/en-us/edit/realized/tenant/%s";
  }

  public static String varJson(String var, Boolean patch) {
    return TenantRealized.varJsonTenantRealized(var, patch);
  }
  public static String varJsonTenantRealized(String var, Boolean patch) {
    switch(var) {
    case VAR_tenantName:
      return patch ? SET_tenantName : VAR_tenantName;
    case VAR_tenantId:
      return patch ? SET_tenantId : VAR_tenantId;
    case VAR_tenantDescription:
      return patch ? SET_tenantDescription : VAR_tenantDescription;
    case VAR_tenantResource:
      return patch ? SET_tenantResource : VAR_tenantResource;
    case VAR_tenantRealizedNumber:
      return patch ? SET_tenantRealizedNumber : VAR_tenantRealizedNumber;
    case VAR_tenantRealizedId:
      return patch ? SET_tenantRealizedId : VAR_tenantRealizedId;
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
    default:
      return Tenant.varJsonTenant(var, patch);
    }
  }

  public static String displayNameForClass(String var) {
    return TenantRealized.displayNameTenantRealized(var);
  }
  public static String displayNameTenantRealized(String var) {
    switch(var) {
    case VAR_tenantName:
      return DISPLAY_NAME_tenantName;
    case VAR_tenantId:
      return DISPLAY_NAME_tenantId;
    case VAR_tenantDescription:
      return DISPLAY_NAME_tenantDescription;
    case VAR_tenantResource:
      return DISPLAY_NAME_tenantResource;
    case VAR_tenantRealizedNumber:
      return DISPLAY_NAME_tenantRealizedNumber;
    case VAR_tenantRealizedId:
      return DISPLAY_NAME_tenantRealizedId;
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
    default:
      return Tenant.displayNameTenant(var);
    }
  }

  public static String descriptionTenantRealized(String var) {
    if(var == null)
      return null;
    switch(var) {
    case VAR_tenantName:
      return "The name of this tenant";
    case VAR_tenantId:
      return "The ID of this tenant. By default, this will be auto-generated based on the tenant name, converting non-alphanumeric characters to hyphens, all lowercase. ";
    case VAR_tenantDescription:
      return "A description of this tenant";
    case VAR_tenantResource:
      return "The unique authorization resource for the tenant for multi-tenancy";
    case VAR_tenantRealizedNumber:
      return "A unique number for each realized version of this tenant. ";
    case VAR_tenantRealizedId:
      return "The unique ID for this tenant realized. ";
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
      default:
        return Tenant.descriptionTenant(var);
    }
  }

  public static String classSimpleNameTenantRealized(String var) {
    switch(var) {
    case VAR_tenantName:
      return "String";
    case VAR_tenantId:
      return "String";
    case VAR_tenantDescription:
      return "String";
    case VAR_tenantResource:
      return "String";
    case VAR_tenantRealizedNumber:
      return "Integer";
    case VAR_tenantRealizedId:
      return "Integer";
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
      default:
        return Tenant.classSimpleNameTenant(var);
    }
  }

  public static Integer htmColumnTenantRealized(String var) {
    switch(var) {
    case VAR_tenantName:
      return 1;
    case VAR_tenantDescription:
      return 3;
    case VAR_tenantResource:
      return 0;
      default:
        return Tenant.htmColumnTenant(var);
    }
  }

  public static Integer htmRowTenantRealized(String var) {
    switch(var) {
    case VAR_tenantName:
      return 23;
    case VAR_tenantDescription:
      return 23;
    case VAR_tenantResource:
      return 5;
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
      default:
        return Tenant.htmRowTenant(var);
    }
  }

  public static Integer htmCellTenantRealized(String var) {
    switch(var) {
    case VAR_tenantName:
      return 1;
    case VAR_tenantDescription:
      return 4;
    case VAR_tenantResource:
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
      default:
        return Tenant.htmCellTenant(var);
    }
  }

  public static Integer lengthMinTenantRealized(String var) {
    switch(var) {
      default:
        return Tenant.lengthMinTenant(var);
    }
  }

  public static Integer lengthMaxTenantRealized(String var) {
    switch(var) {
      default:
        return Tenant.lengthMaxTenant(var);
    }
  }

  public static Integer maxTenantRealized(String var) {
    switch(var) {
      default:
        return Tenant.maxTenant(var);
    }
  }

  public static Integer minTenantRealized(String var) {
    switch(var) {
      default:
        return Tenant.minTenant(var);
    }
  }
}
