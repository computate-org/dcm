package org.computate.dcm.model.eda.host;

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
import org.computate.dcm.model.eda.tenant.Tenant;
import java.lang.Long;
import org.computate.dcm.model.eda.hostinventory.HostInventory;
import io.vertx.core.json.JsonArray;
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
 * <li>You can add a class comment "{@inheritDoc}" if you wish to inherit the helpful inherited class comments from class HostGen into the class Host. 
 * </li>
 * <h3>About the Host class and it's generated class HostGen&lt;BaseModel&gt;: </h3>extends HostGen
 * <p>
 * This Java class extends a generated Java class HostGen built by the <a href="https://github.com/computate-org/computate">https://github.com/computate-org/computate</a> project. 
 * Whenever this Java class is modified or touched, the watch service installed as described in the README, indexes all the information about this Java class in a local Apache Solr Search Engine. 
 * If you are running the service, you can see the indexed data about this Java Class here: 
 * </p>
 * <p><a href="https://solr.apps-crc.testing/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.host.Host">Find the class Host in Solr. </a></p>
 * <p>
 * The extended class ending with "Gen" did not exist at first, but was automatically created by the same watch service based on the data retrieved from the local Apache Server search engine. 
 * The extended class contains many generated fields, getters, setters, initialization code, and helper methods to help build a website and API fast, reactive, and scalable. 
 * </p>
 * extends HostGen<BaseModel>
 * <p>This <code>class Host extends HostGen&lt;BaseModel&gt;</code>, which means it extends a newly generated HostGen. 
 * The generated <code>class HostGen extends BaseModel</code> which means that Host extends HostGen which extends BaseModel. 
 * This generated inheritance is a powerful feature that allows a lot of boiler plate code to be created for you automatically while still preserving inheritance through the power of Java Generic classes. 
 * </p>
 * <h2>Api: true</h2>
 * <p>This class contains a comment <b>"Api: true"</b>, which means this class will have Java Vert.x API backend code generated for these objects. 
 * </p>
 * <h2>ApiTag.enUS: true</h2>
 * <p>This class contains a comment <b>"ApiTag: hosts"</b>, which groups all of the OpenAPIs for Host objects under the tag "hosts". 
 * </p>
 * <h2>ApiUri.enUS: /en-us/api/host</h2>
 * <p>This class contains a comment <b>"ApiUri: /en-us/api/host"</b>, which defines the base API URI for Host objects as "/en-us/api/host" in the OpenAPI spec. 
 * </p>
 * <h2>Color: null</h2>
 * <h2>Indexed: true</h2>
 * <p>This class contains a comment <b>"Indexed: true"</b>, which means this class will be indexed in the search engine. 
 * Every protected void method that begins with "_" that is marked to be searched with a comment like "Indexed: true", "Stored: true", or "DocValues: true" will be indexed in the search engine. 
 * </p>
 * <h2>{@inheritDoc}</h2>
 * <p>By adding a class comment "{@inheritDoc}", the Host class will inherit the helpful inherited class comments from the super class HostGen. 
 * </p>
 * <h2>Rows: 100</h2>
 * <p>This class contains a comment <b>"Rows: 100"</b>, which means the Host API will return a default of 100 records instead of 10 by default. 
 * Each API has built in pagination of the search records to ensure a user can query all the data a page at a time without running the application out of memory. 
 * </p>
 * <h2>Order: 7</h2>
 * <p>This class contains a comment <b>"Order: 7"</b>, which means this class will be sorted by the given number 7 ascending when code that relates to multiple classes at the same time is generated. 
 * </p>
 * <h2>SqlOrder: 7</h2>
 * <p>This class contains a comment <b>"SqlOrder: 7"</b>, which means this class will be sorted by the given number 7 ascending when SQL code to create and drop the tables is generated. 
 * </p>
 * <h2>Model: true</h2>
 * <p>This class contains a comment <b>"Model: true"</b>, which means this class will be stored in the database. 
 * Every protected void method that begins with "_" that contains a "Persist: true" comment will be a persisted field in the database table. 
 * </p>
 * <h2>Page: true</h2>
 * <p>This class contains a comment <b>"Page: true"</b>, which means this class will have webpage code generated for these objects. 
 * Java Vert.x backend API code, Handlebars HTML template frontend code, and JavaScript code will all generated and can be extended. 
 * This creates a new Java class org.computate.dcm.model.eda.host.HostPage. 
 * </p>
 * <h2>SuperPage.enUS: PageLayout</h2>
 * <p>This class contains a comment <b>"SuperPage.enUS: PageLayout"</b>, which identifies the Java super class of the page code by it's class simple name "PageLayout". 
 * This means that the newly created class org.computate.dcm.model.eda.host.HostPage extends org.computate.dcm.page.PageLayout. 
 * </p>
 * <h2>Promise: true</h2>
 * <p>
 *   This class contains a comment <b>"Promise: true"</b>
 *   Sometimes a Java class must be initialized asynchronously when it involves calling a blocking API. 
 *   This means that the Host Java class has promiseDeep methods which must be initialized asynchronously as a Vert.x Promise  instead of initDeep methods which are a simple non-asynchronous method. 
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
 * <h2>AName.enUS: a host</h2>
 * <p>This class contains a comment <b>"AName.enUS: a host"</b>, which identifies the language context to describe a Host as "a host". 
 * </p>
 * <p>
 * Delete the class Host in Solr: 
 * <pre>
 * curl -k 'https://solr.apps-crc.testing/solr/computate/update?commitWithin=1000&amp;overwrite=true&amp;wt=json' -X POST -H 'Content-type: text/xml' -u "admin:$(oc -n solr get secret/solr-solrcloud-security-bootstrap -o jsonpath={.data.admin} | base64 -d)" --data-raw '&lt;delete&gt;&lt;query&gt;classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.host.Host&lt;/query&gt;&lt;/delete&gt;'
 * </pre>
 * </p>
 * <p>
 * Delete  the package org.computate.dcm.model.eda.host in Solr: 
 * <pre>
 * curl -k 'https://solr.apps-crc.testing/solr/computate/update?commitWithin=1000&amp;overwrite=true&amp;wt=json' -X POST -H 'Content-type: text/xml' -u "admin:$(oc -n solr get secret/solr-solrcloud-security-bootstrap -o jsonpath={.data.admin} | base64 -d)" --data-raw '&lt;delete&gt;&lt;query&gt;classeNomEnsemble_enUS_indexed_string:org.computate.dcm.model.eda.host&lt;/query&gt;&lt;/delete&gt;'
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
public abstract class HostGen<DEV> extends BaseModel {
  protected static final Logger LOG = LoggerFactory.getLogger(Host.class);

  public static final String Description_enUS = "A managed host computer. ";
  public static final String AName_enUS = "a host";
  public static final String This_enUS = "this ";
  public static final String ThisName_enUS = "this host";
  public static final String A_enUS = "a ";
  public static final String TheName_enUS = "thehost";
  public static final String SingularName_enUS = "host";
  public static final String PluralName_enUS = "hosts";
  public static final String NameActual_enUS = "current host";
  public static final String AllName_enUS = "all hosts";
  public static final String SearchAllNameBy_enUS = "search hosts by ";
  public static final String SearchAllName_enUS = "search hosts";
  public static final String Title_enUS = "hosts";
  public static final String ThePluralName_enUS = "the hosts";
  public static final String NoNameFound_enUS = "no host found";
  public static final String ApiUri_enUS = "/en-us/api/host";
  public static final String ApiUriSearchPage_enUS = "/en-us/search/host";
  public static final String ApiUriEditPage_enUS = "/en-us/edit/host/{hostResource}";
  public static final String OfName_enUS = "of host";
  public static final String ANameAdjective_enUS = "an host";
  public static final String NameAdjectiveSingular_enUS = "host";
  public static final String NameAdjectivePlural_enUS = "hosts";
  public static final String Search_enUS_OpenApiUri = "/en-us/api/host";
  public static final String Search_enUS_StringFormatUri = "/en-us/api/host";
  public static final String Search_enUS_StringFormatUrl = "%s/en-us/api/host";
  public static final String GET_enUS_OpenApiUri = "/en-us/api/host/{hostResource}";
  public static final String GET_enUS_StringFormatUri = "/en-us/api/host/%s";
  public static final String GET_enUS_StringFormatUrl = "%s/en-us/api/host/%s";
  public static final String PATCH_enUS_OpenApiUri = "/en-us/api/host";
  public static final String PATCH_enUS_StringFormatUri = "/en-us/api/host";
  public static final String PATCH_enUS_StringFormatUrl = "%s/en-us/api/host";
  public static final String POST_enUS_OpenApiUri = "/en-us/api/host";
  public static final String POST_enUS_StringFormatUri = "/en-us/api/host";
  public static final String POST_enUS_StringFormatUrl = "%s/en-us/api/host";
  public static final String DELETE_enUS_OpenApiUri = "/en-us/api/host/{hostResource}";
  public static final String DELETE_enUS_StringFormatUri = "/en-us/api/host/%s";
  public static final String DELETE_enUS_StringFormatUrl = "%s/en-us/api/host/%s";
  public static final String PUTImport_enUS_OpenApiUri = "/en-us/api/host-import";
  public static final String PUTImport_enUS_StringFormatUri = "/en-us/api/host-import";
  public static final String PUTImport_enUS_StringFormatUrl = "%s/en-us/api/host-import";
  public static final String SearchPage_enUS_OpenApiUri = "/en-us/search/host";
  public static final String SearchPage_enUS_StringFormatUri = "/en-us/search/host";
  public static final String SearchPage_enUS_StringFormatUrl = "%s/en-us/search/host";
  public static final String EditPage_enUS_OpenApiUri = "/en-us/edit/host/{hostResource}";
  public static final String EditPage_enUS_StringFormatUri = "/en-us/edit/host/%s";
  public static final String EditPage_enUS_StringFormatUrl = "%s/en-us/edit/host/%s";
  public static final String UserPage_enUS_OpenApiUri = "/en-us/user/host/{hostResource}";
  public static final String UserPage_enUS_StringFormatUri = "/en-us/user/host/%s";
  public static final String UserPage_enUS_StringFormatUrl = "%s/en-us/user/host/%s";
  public static final String DELETEFilter_enUS_OpenApiUri = "/en-us/api/host";
  public static final String DELETEFilter_enUS_StringFormatUri = "/en-us/api/host";
  public static final String DELETEFilter_enUS_StringFormatUrl = "%s/en-us/api/host";

  public static final String Icon = "<i class=\"{{ FONTAWESOME_STYLE }} fa-server\"></i>";
  public static final Integer Rows = 100;

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
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.host.Host&fq=entiteVar_enUS_indexed_string:tenantResource">Find the entity tenantResource in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _tenantResource(Wrap<String> w);

  public String getTenantResource() {
    return tenantResource;
  }
  public void setTenantResource(String o) {
    this.tenantResource = Host.staticSetTenantResource(siteRequest_, o);
  }
  public static String staticSetTenantResource(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected Host tenantResourceInit() {
    Wrap<String> tenantResourceWrap = new Wrap<String>().var("tenantResource");
    if(tenantResource == null) {
      _tenantResource(tenantResourceWrap);
      Optional.ofNullable(tenantResourceWrap.getO()).ifPresent(o -> {
        setTenantResource(o);
      });
    }
    return (Host)this;
  }

  public static String staticSearchTenantResource(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrTenantResource(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqTenantResource(SiteRequest siteRequest_, String o) {
    return Host.staticSearchTenantResource(siteRequest_, Host.staticSetTenantResource(siteRequest_, o)).toString();
  }

  public String sqlTenantResource() {
    return tenantResource;
  }

  public static String staticJsonTenantResource(String tenantResource) {
    return tenantResource;
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
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.host.Host&fq=entiteVar_enUS_indexed_string:tenantId">Find the entity tenantId in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _tenantId(Wrap<String> w);

  public String getTenantId() {
    return tenantId;
  }
  public void setTenantId(String o) {
    this.tenantId = Host.staticSetTenantId(siteRequest_, o);
  }
  public static String staticSetTenantId(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected Host tenantIdInit() {
    Wrap<String> tenantIdWrap = new Wrap<String>().var("tenantId");
    if(tenantId == null) {
      _tenantId(tenantIdWrap);
      Optional.ofNullable(tenantIdWrap.getO()).ifPresent(o -> {
        setTenantId(o);
      });
    }
    return (Host)this;
  }

  public static String staticSearchTenantId(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrTenantId(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqTenantId(SiteRequest siteRequest_, String o) {
    return Host.staticSearchTenantId(siteRequest_, Host.staticSetTenantId(siteRequest_, o)).toString();
  }

  public String sqlTenantId() {
    return tenantId;
  }

  public static String staticJsonTenantId(String tenantId) {
    return tenantId;
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
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.host.Host&fq=entiteVar_enUS_indexed_string:aapOrganizationId">Find the entity aapOrganizationId in Solr</a>
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
    this.aapOrganizationId = Host.staticSetAapOrganizationId(siteRequest_, o);
  }
  public static Long staticSetAapOrganizationId(SiteRequest siteRequest_, String o) {
    if(NumberUtils.isParsable(o))
      return Long.parseLong(o);
    return null;
  }
  protected Host aapOrganizationIdInit() {
    Wrap<Long> aapOrganizationIdWrap = new Wrap<Long>().var("aapOrganizationId");
    if(aapOrganizationId == null) {
      _aapOrganizationId(aapOrganizationIdWrap);
      Optional.ofNullable(aapOrganizationIdWrap.getO()).ifPresent(o -> {
        setAapOrganizationId(o);
      });
    }
    return (Host)this;
  }

  public static Long staticSearchAapOrganizationId(SiteRequest siteRequest_, Long o) {
    return o;
  }

  public static String staticSearchStrAapOrganizationId(SiteRequest siteRequest_, Long o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqAapOrganizationId(SiteRequest siteRequest_, String o) {
    return Host.staticSearchAapOrganizationId(siteRequest_, Host.staticSetAapOrganizationId(siteRequest_, o)).toString();
  }

  public Long sqlAapOrganizationId() {
    return aapOrganizationId;
  }

  public static String staticJsonAapOrganizationId(Long aapOrganizationId) {
    return Optional.ofNullable(aapOrganizationId).map(v -> v.toString()).orElse(null);
  }

	///////////////////////
  // inventoryResource //
	///////////////////////


  /**
   *  The entity inventoryResource
   *	 is defined as null before being initialized. 
   */
  @JsonProperty
  @JsonInclude(Include.NON_NULL)
  protected String inventoryResource;

  /**
   * <br> The entity inventoryResource
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.host.Host&fq=entiteVar_enUS_indexed_string:inventoryResource">Find the entity inventoryResource in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _inventoryResource(Wrap<String> w);

  public String getInventoryResource() {
    return inventoryResource;
  }
  public void setInventoryResource(String o) {
    this.inventoryResource = Host.staticSetInventoryResource(siteRequest_, o);
  }
  public static String staticSetInventoryResource(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected Host inventoryResourceInit() {
    Wrap<String> inventoryResourceWrap = new Wrap<String>().var("inventoryResource");
    if(inventoryResource == null) {
      _inventoryResource(inventoryResourceWrap);
      Optional.ofNullable(inventoryResourceWrap.getO()).ifPresent(o -> {
        setInventoryResource(o);
      });
    }
    return (Host)this;
  }

  public static String staticSearchInventoryResource(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrInventoryResource(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqInventoryResource(SiteRequest siteRequest_, String o) {
    return Host.staticSearchInventoryResource(siteRequest_, Host.staticSetInventoryResource(siteRequest_, o)).toString();
  }

  public String sqlInventoryResource() {
    return inventoryResource;
  }

  public static String staticJsonInventoryResource(String inventoryResource) {
    return inventoryResource;
  }

	///////////////
  // aapHostId //
	///////////////


  /**
   *  The entity aapHostId
   *	 is defined as null before being initialized. 
   */
  @JsonProperty
  @JsonSerialize(using = ToStringSerializer.class)
  @JsonInclude(Include.NON_NULL)
  protected Long aapHostId;

  /**
   * <br> The entity aapHostId
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.host.Host&fq=entiteVar_enUS_indexed_string:aapHostId">Find the entity aapHostId in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _aapHostId(Wrap<Long> w);

  public Long getAapHostId() {
    return aapHostId;
  }

  public void setAapHostId(Long aapHostId) {
    this.aapHostId = aapHostId;
  }
  @JsonIgnore
  public void setAapHostId(String o) {
    this.aapHostId = Host.staticSetAapHostId(siteRequest_, o);
  }
  public static Long staticSetAapHostId(SiteRequest siteRequest_, String o) {
    if(NumberUtils.isParsable(o))
      return Long.parseLong(o);
    return null;
  }
  protected Host aapHostIdInit() {
    Wrap<Long> aapHostIdWrap = new Wrap<Long>().var("aapHostId");
    if(aapHostId == null) {
      _aapHostId(aapHostIdWrap);
      Optional.ofNullable(aapHostIdWrap.getO()).ifPresent(o -> {
        setAapHostId(o);
      });
    }
    return (Host)this;
  }

  public static Long staticSearchAapHostId(SiteRequest siteRequest_, Long o) {
    return o;
  }

  public static String staticSearchStrAapHostId(SiteRequest siteRequest_, Long o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqAapHostId(SiteRequest siteRequest_, String o) {
    return Host.staticSearchAapHostId(siteRequest_, Host.staticSetAapHostId(siteRequest_, o)).toString();
  }

  public Long sqlAapHostId() {
    return aapHostId;
  }

  public static String staticJsonAapHostId(Long aapHostId) {
    return Optional.ofNullable(aapHostId).map(v -> v.toString()).orElse(null);
  }

	//////////////
  // hostName //
	//////////////


  /**
   *  The entity hostName
   *	 is defined as null before being initialized. 
   */
  @JsonProperty
  @JsonInclude(Include.NON_NULL)
  protected String hostName;

  /**
   * <br> The entity hostName
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.host.Host&fq=entiteVar_enUS_indexed_string:hostName">Find the entity hostName in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _hostName(Wrap<String> w);

  public String getHostName() {
    return hostName;
  }
  public void setHostName(String o) {
    this.hostName = Host.staticSetHostName(siteRequest_, o);
  }
  public static String staticSetHostName(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected Host hostNameInit() {
    Wrap<String> hostNameWrap = new Wrap<String>().var("hostName");
    if(hostName == null) {
      _hostName(hostNameWrap);
      Optional.ofNullable(hostNameWrap.getO()).ifPresent(o -> {
        setHostName(o);
      });
    }
    return (Host)this;
  }

  public static String staticSearchHostName(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrHostName(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqHostName(SiteRequest siteRequest_, String o) {
    return Host.staticSearchHostName(siteRequest_, Host.staticSetHostName(siteRequest_, o)).toString();
  }

  public String sqlHostName() {
    return hostName;
  }

  public static String staticJsonHostName(String hostName) {
    return hostName;
  }

	///////////////
  // ipAddress //
	///////////////


  /**
   *  The entity ipAddress
   *	 is defined as null before being initialized. 
   */
  @JsonProperty
  @JsonInclude(Include.NON_NULL)
  protected String ipAddress;

  /**
   * <br> The entity ipAddress
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.host.Host&fq=entiteVar_enUS_indexed_string:ipAddress">Find the entity ipAddress in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _ipAddress(Wrap<String> w);

  public String getIpAddress() {
    return ipAddress;
  }
  public void setIpAddress(String o) {
    this.ipAddress = Host.staticSetIpAddress(siteRequest_, o);
  }
  public static String staticSetIpAddress(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected Host ipAddressInit() {
    Wrap<String> ipAddressWrap = new Wrap<String>().var("ipAddress");
    if(ipAddress == null) {
      _ipAddress(ipAddressWrap);
      Optional.ofNullable(ipAddressWrap.getO()).ifPresent(o -> {
        setIpAddress(o);
      });
    }
    return (Host)this;
  }

  public static String staticSearchIpAddress(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrIpAddress(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqIpAddress(SiteRequest siteRequest_, String o) {
    return Host.staticSearchIpAddress(siteRequest_, Host.staticSetIpAddress(siteRequest_, o)).toString();
  }

  public String sqlIpAddress() {
    return ipAddress;
  }

  public static String staticJsonIpAddress(String ipAddress) {
    return ipAddress;
  }

	////////////
  // hostId //
	////////////


  /**
   *  The entity hostId
   *	 is defined as null before being initialized. 
   */
  @JsonProperty
  @JsonInclude(Include.NON_NULL)
  protected String hostId;

  /**
   * <br> The entity hostId
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.host.Host&fq=entiteVar_enUS_indexed_string:hostId">Find the entity hostId in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _hostId(Wrap<String> w);

  public String getHostId() {
    return hostId;
  }
  public void setHostId(String o) {
    this.hostId = Host.staticSetHostId(siteRequest_, o);
  }
  public static String staticSetHostId(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected Host hostIdInit() {
    Wrap<String> hostIdWrap = new Wrap<String>().var("hostId");
    if(hostId == null) {
      _hostId(hostIdWrap);
      Optional.ofNullable(hostIdWrap.getO()).ifPresent(o -> {
        setHostId(o);
      });
    }
    return (Host)this;
  }

  public static String staticSearchHostId(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrHostId(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqHostId(SiteRequest siteRequest_, String o) {
    return Host.staticSearchHostId(siteRequest_, Host.staticSetHostId(siteRequest_, o)).toString();
  }

  public String sqlHostId() {
    return hostId;
  }

  public static String staticJsonHostId(String hostId) {
    return hostId;
  }

	//////////////////
  // hostResource //
	//////////////////


  /**
   *  The entity hostResource
   *	 is defined as null before being initialized. 
   */
  @JsonProperty
  @JsonInclude(Include.NON_NULL)
  protected String hostResource;

  /**
   * <br> The entity hostResource
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.host.Host&fq=entiteVar_enUS_indexed_string:hostResource">Find the entity hostResource in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _hostResource(Wrap<String> w);

  public String getHostResource() {
    return hostResource;
  }
  public void setHostResource(String o) {
    this.hostResource = Host.staticSetHostResource(siteRequest_, o);
  }
  public static String staticSetHostResource(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected Host hostResourceInit() {
    Wrap<String> hostResourceWrap = new Wrap<String>().var("hostResource");
    if(hostResource == null) {
      _hostResource(hostResourceWrap);
      Optional.ofNullable(hostResourceWrap.getO()).ifPresent(o -> {
        setHostResource(o);
      });
    }
    return (Host)this;
  }

  public static String staticSearchHostResource(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrHostResource(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqHostResource(SiteRequest siteRequest_, String o) {
    return Host.staticSearchHostResource(siteRequest_, Host.staticSetHostResource(siteRequest_, o)).toString();
  }

  public String sqlHostResource() {
    return hostResource;
  }

  public static String staticJsonHostResource(String hostResource) {
    return hostResource;
  }

	/////////////////////
  // hostDescription //
	/////////////////////


  /**
   *  The entity hostDescription
   *	 is defined as null before being initialized. 
   */
  @JsonProperty
  @JsonInclude(Include.NON_NULL)
  protected String hostDescription;

  /**
   * <br> The entity hostDescription
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.host.Host&fq=entiteVar_enUS_indexed_string:hostDescription">Find the entity hostDescription in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _hostDescription(Wrap<String> w);

  public String getHostDescription() {
    return hostDescription;
  }
  public void setHostDescription(String o) {
    this.hostDescription = Host.staticSetHostDescription(siteRequest_, o);
  }
  public static String staticSetHostDescription(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected Host hostDescriptionInit() {
    Wrap<String> hostDescriptionWrap = new Wrap<String>().var("hostDescription");
    if(hostDescription == null) {
      _hostDescription(hostDescriptionWrap);
      Optional.ofNullable(hostDescriptionWrap.getO()).ifPresent(o -> {
        setHostDescription(o);
      });
    }
    return (Host)this;
  }

  public static String staticSearchHostDescription(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrHostDescription(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqHostDescription(SiteRequest siteRequest_, String o) {
    return Host.staticSearchHostDescription(siteRequest_, Host.staticSetHostDescription(siteRequest_, o)).toString();
  }

  public String sqlHostDescription() {
    return hostDescription;
  }

  public static String staticJsonHostDescription(String hostDescription) {
    return hostDescription;
  }

	////////////////////
  // aapInventoryId //
	////////////////////


  /**
   *  The entity aapInventoryId
   *	 is defined as null before being initialized. 
   */
  @JsonProperty
  @JsonSerialize(using = ToStringSerializer.class)
  @JsonInclude(Include.NON_NULL)
  protected Long aapInventoryId;

  /**
   * <br> The entity aapInventoryId
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.host.Host&fq=entiteVar_enUS_indexed_string:aapInventoryId">Find the entity aapInventoryId in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _aapInventoryId(Wrap<Long> w);

  public Long getAapInventoryId() {
    return aapInventoryId;
  }

  public void setAapInventoryId(Long aapInventoryId) {
    this.aapInventoryId = aapInventoryId;
  }
  @JsonIgnore
  public void setAapInventoryId(String o) {
    this.aapInventoryId = Host.staticSetAapInventoryId(siteRequest_, o);
  }
  public static Long staticSetAapInventoryId(SiteRequest siteRequest_, String o) {
    if(NumberUtils.isParsable(o))
      return Long.parseLong(o);
    return null;
  }
  protected Host aapInventoryIdInit() {
    Wrap<Long> aapInventoryIdWrap = new Wrap<Long>().var("aapInventoryId");
    if(aapInventoryId == null) {
      _aapInventoryId(aapInventoryIdWrap);
      Optional.ofNullable(aapInventoryIdWrap.getO()).ifPresent(o -> {
        setAapInventoryId(o);
      });
    }
    return (Host)this;
  }

  public static Long staticSearchAapInventoryId(SiteRequest siteRequest_, Long o) {
    return o;
  }

  public static String staticSearchStrAapInventoryId(SiteRequest siteRequest_, Long o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqAapInventoryId(SiteRequest siteRequest_, String o) {
    return Host.staticSearchAapInventoryId(siteRequest_, Host.staticSetAapInventoryId(siteRequest_, o)).toString();
  }

  public Long sqlAapInventoryId() {
    return aapInventoryId;
  }

  public static String staticJsonAapInventoryId(Long aapInventoryId) {
    return Optional.ofNullable(aapInventoryId).map(v -> v.toString()).orElse(null);
  }

	///////////////////
  // inventoryName //
	///////////////////


  /**
   *  The entity inventoryName
   *	 is defined as null before being initialized. 
   */
  @JsonProperty
  @JsonInclude(Include.NON_NULL)
  protected String inventoryName;

  /**
   * <br> The entity inventoryName
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.host.Host&fq=entiteVar_enUS_indexed_string:inventoryName">Find the entity inventoryName in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _inventoryName(Wrap<String> w);

  public String getInventoryName() {
    return inventoryName;
  }
  public void setInventoryName(String o) {
    this.inventoryName = Host.staticSetInventoryName(siteRequest_, o);
  }
  public static String staticSetInventoryName(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected Host inventoryNameInit() {
    Wrap<String> inventoryNameWrap = new Wrap<String>().var("inventoryName");
    if(inventoryName == null) {
      _inventoryName(inventoryNameWrap);
      Optional.ofNullable(inventoryNameWrap.getO()).ifPresent(o -> {
        setInventoryName(o);
      });
    }
    return (Host)this;
  }

  public static String staticSearchInventoryName(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrInventoryName(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqInventoryName(SiteRequest siteRequest_, String o) {
    return Host.staticSearchInventoryName(siteRequest_, Host.staticSetInventoryName(siteRequest_, o)).toString();
  }

  public String sqlInventoryName() {
    return inventoryName;
  }

  public static String staticJsonInventoryName(String inventoryName) {
    return inventoryName;
  }

	////////////////////////
  // eventSubscriptions //
	////////////////////////


  /**
   *  The entity eventSubscriptions
   *	 It is constructed before being initialized with the constructor by default. 
   */
  @JsonProperty
  @JsonFormat(shape = JsonFormat.Shape.ARRAY)
  @JsonInclude(Include.NON_NULL)
  protected List<String> eventSubscriptions = new ArrayList<String>();

  /**
   * <br> The entity eventSubscriptions
   *  It is constructed before being initialized with the constructor by default. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.host.Host&fq=entiteVar_enUS_indexed_string:eventSubscriptions">Find the entity eventSubscriptions in Solr</a>
   * <br>
   * @param w is the entity already constructed. 
   **/
  protected abstract void _eventSubscriptions(List<String> w);

  public List<String> getEventSubscriptions() {
    return eventSubscriptions;
  }

  public void setEventSubscriptions(List<String> eventSubscriptions) {
    this.eventSubscriptions = eventSubscriptions;
  }
  @JsonIgnore
  public void setEventSubscriptions(String o) {
    String l = Host.staticSetEventSubscriptions(siteRequest_, o);
    if(l != null)
      addEventSubscriptions(l);
  }
  public static String staticSetEventSubscriptions(SiteRequest siteRequest_, String o) {
    return o;
  }
  public Host addEventSubscriptions(String...objects) {
    for(String o : objects) {
      addEventSubscriptions(o);
    }
    return (Host)this;
  }
  public Host addEventSubscriptions(String o) {
    if(o != null)
      this.eventSubscriptions.add(o);
    return (Host)this;
  }
  @JsonIgnore
  public void setEventSubscriptions(JsonArray objects) {
    eventSubscriptions.clear();
    if(objects == null)
      return;
    for(int i = 0; i < objects.size(); i++) {
      String o = objects.getString(i);
      addEventSubscriptions(o);
    }
  }
  protected Host eventSubscriptionsInit() {
    _eventSubscriptions(eventSubscriptions);
    return (Host)this;
  }

  public static String staticSearchEventSubscriptions(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrEventSubscriptions(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqEventSubscriptions(SiteRequest siteRequest_, String o) {
    return Host.staticSearchEventSubscriptions(siteRequest_, Host.staticSetEventSubscriptions(siteRequest_, o)).toString();
  }

  public String[] sqlEventSubscriptions() {
    return eventSubscriptions.stream().map(v -> (String)v).toArray(String[]::new);
  }

  public static JsonArray staticJsonEventSubscriptions(List<String> eventSubscriptions) {
    JsonArray a = new JsonArray();
    eventSubscriptions.stream().forEach(v -> a.add(v.toString()));
    return a;
  }

	//////////
  // tags //
	//////////


  /**
   *  The entity tags
   *	 It is constructed before being initialized with the constructor by default. 
   */
  @JsonProperty
  @JsonFormat(shape = JsonFormat.Shape.ARRAY)
  @JsonInclude(Include.NON_NULL)
  protected List<String> tags = new ArrayList<String>();

  /**
   * <br> The entity tags
   *  It is constructed before being initialized with the constructor by default. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.host.Host&fq=entiteVar_enUS_indexed_string:tags">Find the entity tags in Solr</a>
   * <br>
   * @param w is the entity already constructed. 
   **/
  protected abstract void _tags(List<String> w);

  public List<String> getTags() {
    return tags;
  }

  public void setTags(List<String> tags) {
    this.tags = tags;
  }
  @JsonIgnore
  public void setTags(String o) {
    String l = Host.staticSetTags(siteRequest_, o);
    if(l != null)
      addTags(l);
  }
  public static String staticSetTags(SiteRequest siteRequest_, String o) {
    return o;
  }
  public Host addTags(String...objects) {
    for(String o : objects) {
      addTags(o);
    }
    return (Host)this;
  }
  public Host addTags(String o) {
    if(o != null)
      this.tags.add(o);
    return (Host)this;
  }
  @JsonIgnore
  public void setTags(JsonArray objects) {
    tags.clear();
    if(objects == null)
      return;
    for(int i = 0; i < objects.size(); i++) {
      String o = objects.getString(i);
      addTags(o);
    }
  }
  protected Host tagsInit() {
    _tags(tags);
    return (Host)this;
  }

  public static String staticSearchTags(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrTags(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqTags(SiteRequest siteRequest_, String o) {
    return Host.staticSearchTags(siteRequest_, Host.staticSetTags(siteRequest_, o)).toString();
  }

  public String[] sqlTags() {
    return tags.stream().map(v -> (String)v).toArray(String[]::new);
  }

  public static JsonArray staticJsonTags(List<String> tags) {
    JsonArray a = new JsonArray();
    tags.stream().forEach(v -> a.add(v.toString()));
    return a;
  }

  //////////////
  // initDeep //
  //////////////

  public Future<HostGen<DEV>> promiseDeepHost(SiteRequest siteRequest_) {
    setSiteRequest_(siteRequest_);
    return promiseDeepHost();
  }

  public Future<HostGen<DEV>> promiseDeepHost() {
    Promise<HostGen<DEV>> promise = Promise.promise();
    Promise<Void> promise2 = Promise.promise();
    promiseHost(promise2);
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

  public Future<Void> promiseHost(Promise<Void> promise) {
    Future.future(a -> a.complete()).compose(a -> {
      Promise<Void> promise2 = Promise.promise();
      try {
        tenantResourceInit();
        tenantIdInit();
        aapOrganizationIdInit();
        inventoryResourceInit();
        aapHostIdInit();
        hostNameInit();
        ipAddressInit();
        hostIdInit();
        hostResourceInit();
        hostDescriptionInit();
        aapInventoryIdInit();
        inventoryNameInit();
        eventSubscriptionsInit();
        tagsInit();
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

  @Override public Future<? extends HostGen<DEV>> promiseDeepForClass(SiteRequest siteRequest_) {
    return promiseDeepHost(siteRequest_);
  }

  /////////////////
  // siteRequest //
  /////////////////

  public void siteRequestHost(SiteRequest siteRequest_) {
      super.siteRequestBaseModel(siteRequest_);
  }

  public void siteRequestForClass(SiteRequest siteRequest_) {
    siteRequestHost(siteRequest_);
  }

  /////////////
  // obtain //
  /////////////

  @Override public Object obtainForClass(String var) {
    String[] vars = StringUtils.split(var, ".");
    Object o = null;
    for(String v : vars) {
      if(o == null)
        o = obtainHost(v);
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
  public Object obtainHost(String var) {
    Host oHost = (Host)this;
    switch(var) {
      case "tenantResource":
        return oHost.tenantResource;
      case "tenantId":
        return oHost.tenantId;
      case "aapOrganizationId":
        return oHost.aapOrganizationId;
      case "inventoryResource":
        return oHost.inventoryResource;
      case "aapHostId":
        return oHost.aapHostId;
      case "hostName":
        return oHost.hostName;
      case "ipAddress":
        return oHost.ipAddress;
      case "hostId":
        return oHost.hostId;
      case "hostResource":
        return oHost.hostResource;
      case "hostDescription":
        return oHost.hostDescription;
      case "aapInventoryId":
        return oHost.aapInventoryId;
      case "inventoryName":
        return oHost.inventoryName;
      case "eventSubscriptions":
        return oHost.eventSubscriptions;
      case "tags":
        return oHost.tags;
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
        o = relateHost(v, val);
      else if(o instanceof BaseModel) {
        BaseModel baseModel = (BaseModel)o;
        o = baseModel.relateForClass(v, val);
      }
    }
    return o != null;
  }
  public Object relateHost(String var, Object val) {
    Host oHost = (Host)this;
    switch(var) {
      case "tenantResource":
        if(oHost.getTenantResource() == null)
          oHost.setTenantResource(Optional.ofNullable(val).map(v -> v.toString()).orElse(null));
        if(!saves.contains("tenantResource"))
          saves.add("tenantResource");
        return val;
      case "inventoryResource":
        if(oHost.getInventoryResource() == null)
          oHost.setInventoryResource(Optional.ofNullable(val).map(v -> v.toString()).orElse(null));
        if(!saves.contains("inventoryResource"))
          saves.add("inventoryResource");
        return val;
      default:
        return super.relateBaseModel(var, val);
    }
  }

  ///////////////
  // staticSet //
  ///////////////

  public static Object staticSetForClass(String entityVar, SiteRequest siteRequest_, String v, Host o) {
    return staticSetHost(entityVar,  siteRequest_, v, o);
  }
  public static Object staticSetHost(String entityVar, SiteRequest siteRequest_, String v, Host o) {
    switch(entityVar) {
    case "tenantResource":
      return Host.staticSetTenantResource(siteRequest_, v);
    case "tenantId":
      return Host.staticSetTenantId(siteRequest_, v);
    case "aapOrganizationId":
      return Host.staticSetAapOrganizationId(siteRequest_, v);
    case "inventoryResource":
      return Host.staticSetInventoryResource(siteRequest_, v);
    case "aapHostId":
      return Host.staticSetAapHostId(siteRequest_, v);
    case "hostName":
      return Host.staticSetHostName(siteRequest_, v);
    case "ipAddress":
      return Host.staticSetIpAddress(siteRequest_, v);
    case "hostId":
      return Host.staticSetHostId(siteRequest_, v);
    case "hostResource":
      return Host.staticSetHostResource(siteRequest_, v);
    case "hostDescription":
      return Host.staticSetHostDescription(siteRequest_, v);
    case "aapInventoryId":
      return Host.staticSetAapInventoryId(siteRequest_, v);
    case "inventoryName":
      return Host.staticSetInventoryName(siteRequest_, v);
    case "eventSubscriptions":
      return Host.staticSetEventSubscriptions(siteRequest_, v);
    case "tags":
      return Host.staticSetTags(siteRequest_, v);
      default:
        return BaseModel.staticSetBaseModel(entityVar,  siteRequest_, v, o);
    }
  }

  //////////////////
  // staticSearch //
  //////////////////

  public static Future<Host> fqHost(SiteRequest siteRequest, String var, Object val) {
    Promise<Host> promise = Promise.promise();
    try {
      if(val == null) {
        promise.complete();
      } else {
        SearchList<Host> searchList = new SearchList<Host>();
        searchList.setStore(true);
        searchList.q("*:*");
        searchList.setC(Host.class);
        searchList.fq(String.format("%s:", Host.varIndexedHost(var)) + SearchTool.escapeQueryChars(val.toString()));
        searchList.promiseDeepForClass(siteRequest).onSuccess(a -> {
          try {
            promise.complete(searchList.getList().stream().findFirst().orElse(null));
          } catch(Throwable ex) {
            LOG.error("Error while querying thehost", ex);
            promise.fail(ex);
          }
        }).onFailure(ex -> {
          LOG.error("Error while querying thehost", ex);
          promise.fail(ex);
        });
      }
    } catch(Throwable ex) {
      LOG.error("Error while querying thehost", ex);
      promise.fail(ex);
    }
    return promise.future();
  }

  public static Object staticSearchForClass(String entityVar, SiteRequest siteRequest_, Object o) {
    return staticSearchHost(entityVar,  siteRequest_, o);
  }
  public static Object staticSearchHost(String entityVar, SiteRequest siteRequest_, Object o) {
    switch(entityVar) {
    case "tenantResource":
      return Host.staticSearchTenantResource(siteRequest_, (String)o);
    case "tenantId":
      return Host.staticSearchTenantId(siteRequest_, (String)o);
    case "aapOrganizationId":
      return Host.staticSearchAapOrganizationId(siteRequest_, (Long)o);
    case "inventoryResource":
      return Host.staticSearchInventoryResource(siteRequest_, (String)o);
    case "aapHostId":
      return Host.staticSearchAapHostId(siteRequest_, (Long)o);
    case "hostName":
      return Host.staticSearchHostName(siteRequest_, (String)o);
    case "ipAddress":
      return Host.staticSearchIpAddress(siteRequest_, (String)o);
    case "hostId":
      return Host.staticSearchHostId(siteRequest_, (String)o);
    case "hostResource":
      return Host.staticSearchHostResource(siteRequest_, (String)o);
    case "hostDescription":
      return Host.staticSearchHostDescription(siteRequest_, (String)o);
    case "aapInventoryId":
      return Host.staticSearchAapInventoryId(siteRequest_, (Long)o);
    case "inventoryName":
      return Host.staticSearchInventoryName(siteRequest_, (String)o);
    case "eventSubscriptions":
      return Host.staticSearchEventSubscriptions(siteRequest_, (String)o);
    case "tags":
      return Host.staticSearchTags(siteRequest_, (String)o);
      default:
        return BaseModel.staticSearchBaseModel(entityVar,  siteRequest_, o);
    }
  }

  ///////////////////
  // staticSearchStr //
  ///////////////////

  public static String staticSearchStrForClass(String entityVar, SiteRequest siteRequest_, Object o) {
    return staticSearchStrHost(entityVar,  siteRequest_, o);
  }
  public static String staticSearchStrHost(String entityVar, SiteRequest siteRequest_, Object o) {
    switch(entityVar) {
    case "tenantResource":
      return Host.staticSearchStrTenantResource(siteRequest_, (String)o);
    case "tenantId":
      return Host.staticSearchStrTenantId(siteRequest_, (String)o);
    case "aapOrganizationId":
      return Host.staticSearchStrAapOrganizationId(siteRequest_, (Long)o);
    case "inventoryResource":
      return Host.staticSearchStrInventoryResource(siteRequest_, (String)o);
    case "aapHostId":
      return Host.staticSearchStrAapHostId(siteRequest_, (Long)o);
    case "hostName":
      return Host.staticSearchStrHostName(siteRequest_, (String)o);
    case "ipAddress":
      return Host.staticSearchStrIpAddress(siteRequest_, (String)o);
    case "hostId":
      return Host.staticSearchStrHostId(siteRequest_, (String)o);
    case "hostResource":
      return Host.staticSearchStrHostResource(siteRequest_, (String)o);
    case "hostDescription":
      return Host.staticSearchStrHostDescription(siteRequest_, (String)o);
    case "aapInventoryId":
      return Host.staticSearchStrAapInventoryId(siteRequest_, (Long)o);
    case "inventoryName":
      return Host.staticSearchStrInventoryName(siteRequest_, (String)o);
    case "eventSubscriptions":
      return Host.staticSearchStrEventSubscriptions(siteRequest_, (String)o);
    case "tags":
      return Host.staticSearchStrTags(siteRequest_, (String)o);
      default:
        return BaseModel.staticSearchStrBaseModel(entityVar,  siteRequest_, o);
    }
  }

  //////////////////
  // staticSearchFq //
  //////////////////

  public static String staticSearchFqForClass(String entityVar, SiteRequest siteRequest_, String o) {
    return staticSearchFqHost(entityVar,  siteRequest_, o);
  }
  public static String staticSearchFqHost(String entityVar, SiteRequest siteRequest_, String o) {
    switch(entityVar) {
    case "tenantResource":
      return Host.staticSearchFqTenantResource(siteRequest_, o);
    case "tenantId":
      return Host.staticSearchFqTenantId(siteRequest_, o);
    case "aapOrganizationId":
      return Host.staticSearchFqAapOrganizationId(siteRequest_, o);
    case "inventoryResource":
      return Host.staticSearchFqInventoryResource(siteRequest_, o);
    case "aapHostId":
      return Host.staticSearchFqAapHostId(siteRequest_, o);
    case "hostName":
      return Host.staticSearchFqHostName(siteRequest_, o);
    case "ipAddress":
      return Host.staticSearchFqIpAddress(siteRequest_, o);
    case "hostId":
      return Host.staticSearchFqHostId(siteRequest_, o);
    case "hostResource":
      return Host.staticSearchFqHostResource(siteRequest_, o);
    case "hostDescription":
      return Host.staticSearchFqHostDescription(siteRequest_, o);
    case "aapInventoryId":
      return Host.staticSearchFqAapInventoryId(siteRequest_, o);
    case "inventoryName":
      return Host.staticSearchFqInventoryName(siteRequest_, o);
    case "eventSubscriptions":
      return Host.staticSearchFqEventSubscriptions(siteRequest_, o);
    case "tags":
      return Host.staticSearchFqTags(siteRequest_, o);
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
          o = persistHost(v, val);
        else if(o instanceof BaseModel) {
          BaseModel oBaseModel = (BaseModel)o;
          o = oBaseModel.persistForClass(v, val);
        }
      }
    }
    return o != null;
  }
  public Object persistHost(String var, Object val) {
    String varLower = var.toLowerCase();
      if("tenantresource".equals(varLower)) {
        if(val instanceof String) {
          setTenantResource((String)val);
        }
        saves.add("tenantResource");
        return val;
      } else if("tenantid".equals(varLower)) {
        if(val instanceof String) {
          setTenantId((String)val);
        }
        saves.add("tenantId");
        return val;
      } else if("aaporganizationid".equals(varLower)) {
        if(val instanceof Long) {
          setAapOrganizationId((Long)val);
        } else {
          setAapOrganizationId(val == null ? null : val.toString());
        }
        saves.add("aapOrganizationId");
        return val;
      } else if("inventoryresource".equals(varLower)) {
        if(val instanceof String) {
          setInventoryResource((String)val);
        }
        saves.add("inventoryResource");
        return val;
      } else if("aaphostid".equals(varLower)) {
        if(val instanceof Long) {
          setAapHostId((Long)val);
        } else {
          setAapHostId(val == null ? null : val.toString());
        }
        saves.add("aapHostId");
        return val;
      } else if("hostname".equals(varLower)) {
        if(val instanceof String) {
          setHostName((String)val);
        }
        saves.add("hostName");
        return val;
      } else if("ipaddress".equals(varLower)) {
        if(val instanceof String) {
          setIpAddress((String)val);
        }
        saves.add("ipAddress");
        return val;
      } else if("hostid".equals(varLower)) {
        if(val instanceof String) {
          setHostId((String)val);
        }
        saves.add("hostId");
        return val;
      } else if("hostresource".equals(varLower)) {
        if(val instanceof String) {
          setHostResource((String)val);
        }
        saves.add("hostResource");
        return val;
      } else if("hostdescription".equals(varLower)) {
        if(val instanceof String) {
          setHostDescription((String)val);
        }
        saves.add("hostDescription");
        return val;
      } else if("aapinventoryid".equals(varLower)) {
        if(val instanceof Long) {
          setAapInventoryId((Long)val);
        } else {
          setAapInventoryId(val == null ? null : val.toString());
        }
        saves.add("aapInventoryId");
        return val;
      } else if("inventoryname".equals(varLower)) {
        if(val instanceof String) {
          setInventoryName((String)val);
        }
        saves.add("inventoryName");
        return val;
      } else if("eventsubscriptions".equals(varLower)) {
        if(val instanceof List<?>) {
          ((List<String>)val).stream().forEach(v -> addEventSubscriptions(v));
        } else if(val instanceof String[]) {
          Arrays.asList((String[])val).stream().forEach(v -> addEventSubscriptions((String)v));
        } else if(val instanceof JsonArray) {
          ((JsonArray)val).stream().forEach(v -> addEventSubscriptions(staticSetEventSubscriptions(siteRequest_, v.toString())));
        }
        if(!saves.contains("eventSubscriptions")) {
          saves.add("eventSubscriptions");
        }
        return val;
      } else if("tags".equals(varLower)) {
        if(val instanceof List<?>) {
          ((List<String>)val).stream().forEach(v -> addTags(v));
        } else if(val instanceof String[]) {
          Arrays.asList((String[])val).stream().forEach(v -> addTags((String)v));
        } else if(val instanceof JsonArray) {
          ((JsonArray)val).stream().forEach(v -> addTags(staticSetTags(siteRequest_, v.toString())));
        }
        if(!saves.contains("tags")) {
          saves.add("tags");
        }
        return val;
    } else {
      return super.persistBaseModel(var, val);
    }
  }

  /////////////
  // populate //
  /////////////

  @Override public void populateForClass(SolrResponse.Doc doc) {
    populateHost(doc);
  }
  public void populateHost(SolrResponse.Doc doc) {
    Host oHost = (Host)this;
    saves = Optional.ofNullable((ArrayList<String>)doc.get("saves_docvalues_strings")).orElse(new ArrayList<String>());
    if(saves != null) {

      String tenantResource = (String)doc.get("tenantResource_docvalues_string");
      if(tenantResource != null)
        oHost.setTenantResource(tenantResource);

      if(saves.contains("tenantId")) {
        String tenantId = (String)doc.get("tenantId_docvalues_string");
        if(tenantId != null)
          oHost.setTenantId(tenantId);
      }

      if(saves.contains("aapOrganizationId")) {
        Long aapOrganizationId = (Long)doc.get("aapOrganizationId_docvalues_long");
        if(aapOrganizationId != null)
          oHost.setAapOrganizationId(aapOrganizationId);
      }

      String inventoryResource = (String)doc.get("inventoryResource_docvalues_string");
      if(inventoryResource != null)
        oHost.setInventoryResource(inventoryResource);

      if(saves.contains("aapHostId")) {
        Long aapHostId = (Long)doc.get("aapHostId_docvalues_long");
        if(aapHostId != null)
          oHost.setAapHostId(aapHostId);
      }

      if(saves.contains("hostName")) {
        String hostName = (String)doc.get("hostName_docvalues_string");
        if(hostName != null)
          oHost.setHostName(hostName);
      }

      if(saves.contains("ipAddress")) {
        String ipAddress = (String)doc.get("ipAddress_docvalues_string");
        if(ipAddress != null)
          oHost.setIpAddress(ipAddress);
      }

      if(saves.contains("hostId")) {
        String hostId = (String)doc.get("hostId_docvalues_string");
        if(hostId != null)
          oHost.setHostId(hostId);
      }

      if(saves.contains("hostResource")) {
        String hostResource = (String)doc.get("hostResource_docvalues_string");
        if(hostResource != null)
          oHost.setHostResource(hostResource);
      }

      if(saves.contains("hostDescription")) {
        String hostDescription = (String)doc.get("hostDescription_docvalues_string");
        if(hostDescription != null)
          oHost.setHostDescription(hostDescription);
      }

      if(saves.contains("aapInventoryId")) {
        Long aapInventoryId = (Long)doc.get("aapInventoryId_docvalues_long");
        if(aapInventoryId != null)
          oHost.setAapInventoryId(aapInventoryId);
      }

      if(saves.contains("inventoryName")) {
        String inventoryName = (String)doc.get("inventoryName_docvalues_string");
        if(inventoryName != null)
          oHost.setInventoryName(inventoryName);
      }

      if(saves.contains("eventSubscriptions")) {
        List<String> eventSubscriptions = (List<String>)doc.get("eventSubscriptions_docvalues_strings");
        if(eventSubscriptions != null) {
          eventSubscriptions.stream().forEach( v -> {
            oHost.eventSubscriptions.add(Host.staticSetEventSubscriptions(siteRequest_, v));
          });
        }
      }

      if(saves.contains("tags")) {
        List<String> tags = (List<String>)doc.get("tags_docvalues_strings");
        if(tags != null) {
          tags.stream().forEach( v -> {
            oHost.tags.add(Host.staticSetTags(siteRequest_, v));
          });
        }
      }
    }

    super.populateBaseModel(doc);
  }

  public void indexHost(JsonObject doc) {
    if(tenantResource != null) {
      doc.put("tenantResource_docvalues_string", tenantResource);
    }
    if(tenantId != null) {
      doc.put("tenantId_docvalues_string", tenantId);
    }
    if(aapOrganizationId != null) {
      doc.put("aapOrganizationId_docvalues_long", aapOrganizationId);
    }
    if(inventoryResource != null) {
      doc.put("inventoryResource_docvalues_string", inventoryResource);
    }
    if(aapHostId != null) {
      doc.put("aapHostId_docvalues_long", aapHostId);
    }
    if(hostName != null) {
      doc.put("hostName_docvalues_string", hostName);
    }
    if(ipAddress != null) {
      doc.put("ipAddress_docvalues_string", ipAddress);
    }
    if(hostId != null) {
      doc.put("hostId_docvalues_string", hostId);
    }
    if(hostResource != null) {
      doc.put("hostResource_docvalues_string", hostResource);
    }
    if(hostDescription != null) {
      doc.put("hostDescription_docvalues_string", hostDescription);
    }
    if(aapInventoryId != null) {
      doc.put("aapInventoryId_docvalues_long", aapInventoryId);
    }
    if(inventoryName != null) {
      doc.put("inventoryName_docvalues_string", inventoryName);
    }
    if(eventSubscriptions != null) {
      JsonArray l = new JsonArray();
      doc.put("eventSubscriptions_docvalues_strings", l);
      for(String o : eventSubscriptions) {
        l.add(Host.staticSearchEventSubscriptions(siteRequest_, o));
      }
    }
    if(tags != null) {
      JsonArray l = new JsonArray();
      doc.put("tags_docvalues_strings", l);
      for(String o : tags) {
        l.add(Host.staticSearchTags(siteRequest_, o));
      }
    }
    super.indexBaseModel(doc);

	}

  public static String varStoredHost(String entityVar) {
    switch(entityVar) {
      case "tenantResource":
        return "tenantResource_docvalues_string";
      case "tenantId":
        return "tenantId_docvalues_string";
      case "aapOrganizationId":
        return "aapOrganizationId_docvalues_long";
      case "inventoryResource":
        return "inventoryResource_docvalues_string";
      case "aapHostId":
        return "aapHostId_docvalues_long";
      case "hostName":
        return "hostName_docvalues_string";
      case "ipAddress":
        return "ipAddress_docvalues_string";
      case "hostId":
        return "hostId_docvalues_string";
      case "hostResource":
        return "hostResource_docvalues_string";
      case "hostDescription":
        return "hostDescription_docvalues_string";
      case "aapInventoryId":
        return "aapInventoryId_docvalues_long";
      case "inventoryName":
        return "inventoryName_docvalues_string";
      case "eventSubscriptions":
        return "eventSubscriptions_docvalues_strings";
      case "tags":
        return "tags_docvalues_strings";
      default:
        return BaseModel.varStoredBaseModel(entityVar);
    }
  }

  public static String varIndexedHost(String entityVar) {
    switch(entityVar) {
      case "tenantResource":
        return "tenantResource_docvalues_string";
      case "tenantId":
        return "tenantId_docvalues_string";
      case "aapOrganizationId":
        return "aapOrganizationId_docvalues_long";
      case "inventoryResource":
        return "inventoryResource_docvalues_string";
      case "aapHostId":
        return "aapHostId_docvalues_long";
      case "hostName":
        return "hostName_docvalues_string";
      case "ipAddress":
        return "ipAddress_docvalues_string";
      case "hostId":
        return "hostId_docvalues_string";
      case "hostResource":
        return "hostResource_docvalues_string";
      case "hostDescription":
        return "hostDescription_docvalues_string";
      case "aapInventoryId":
        return "aapInventoryId_docvalues_long";
      case "inventoryName":
        return "inventoryName_docvalues_string";
      case "eventSubscriptions":
        return "eventSubscriptions_docvalues_strings";
      case "tags":
        return "tags_docvalues_strings";
      default:
        return BaseModel.varIndexedBaseModel(entityVar);
    }
  }

  public static String searchVarHost(String searchVar) {
    switch(searchVar) {
      case "tenantResource_docvalues_string":
        return "tenantResource";
      case "tenantId_docvalues_string":
        return "tenantId";
      case "aapOrganizationId_docvalues_long":
        return "aapOrganizationId";
      case "inventoryResource_docvalues_string":
        return "inventoryResource";
      case "aapHostId_docvalues_long":
        return "aapHostId";
      case "hostName_docvalues_string":
        return "hostName";
      case "ipAddress_docvalues_string":
        return "ipAddress";
      case "hostId_docvalues_string":
        return "hostId";
      case "hostResource_docvalues_string":
        return "hostResource";
      case "hostDescription_docvalues_string":
        return "hostDescription";
      case "aapInventoryId_docvalues_long":
        return "aapInventoryId";
      case "inventoryName_docvalues_string":
        return "inventoryName";
      case "eventSubscriptions_docvalues_strings":
        return "eventSubscriptions";
      case "tags_docvalues_strings":
        return "tags";
      default:
        return BaseModel.searchVarBaseModel(searchVar);
    }
  }

  public static String varSearchHost(String entityVar) {
    switch(entityVar) {
      default:
        return BaseModel.varSearchBaseModel(entityVar);
    }
  }

  public static String varSuggestedHost(String entityVar) {
    switch(entityVar) {
      default:
        return BaseModel.varSuggestedBaseModel(entityVar);
    }
  }

  /////////////
  // store //
  /////////////

  @Override public void storeForClass(SolrResponse.Doc doc) {
    storeHost(doc);
  }
  public void storeHost(SolrResponse.Doc doc) {
    Host oHost = (Host)this;
    SiteRequest siteRequest = oHost.getSiteRequest_();

    oHost.setTenantResource(Optional.ofNullable(doc.get("tenantResource_docvalues_string")).map(v -> v.toString()).orElse(null));
    oHost.setTenantId(Optional.ofNullable(doc.get("tenantId_docvalues_string")).map(v -> v.toString()).orElse(null));
    oHost.setAapOrganizationId(Optional.ofNullable(doc.get("aapOrganizationId_docvalues_long")).map(v -> v.toString()).orElse(null));
    oHost.setInventoryResource(Optional.ofNullable(doc.get("inventoryResource_docvalues_string")).map(v -> v.toString()).orElse(null));
    oHost.setAapHostId(Optional.ofNullable(doc.get("aapHostId_docvalues_long")).map(v -> v.toString()).orElse(null));
    oHost.setHostName(Optional.ofNullable(doc.get("hostName_docvalues_string")).map(v -> v.toString()).orElse(null));
    oHost.setIpAddress(Optional.ofNullable(doc.get("ipAddress_docvalues_string")).map(v -> v.toString()).orElse(null));
    oHost.setHostId(Optional.ofNullable(doc.get("hostId_docvalues_string")).map(v -> v.toString()).orElse(null));
    oHost.setHostResource(Optional.ofNullable(doc.get("hostResource_docvalues_string")).map(v -> v.toString()).orElse(null));
    oHost.setHostDescription(Optional.ofNullable(doc.get("hostDescription_docvalues_string")).map(v -> v.toString()).orElse(null));
    oHost.setAapInventoryId(Optional.ofNullable(doc.get("aapInventoryId_docvalues_long")).map(v -> v.toString()).orElse(null));
    oHost.setInventoryName(Optional.ofNullable(doc.get("inventoryName_docvalues_string")).map(v -> v.toString()).orElse(null));
    Optional.ofNullable((List<?>)doc.get("eventSubscriptions_docvalues_strings")).orElse(Arrays.asList()).stream().filter(v -> v != null).forEach(v -> {
      oHost.addEventSubscriptions(Host.staticSetEventSubscriptions(siteRequest, v.toString()));
    });
    Optional.ofNullable((List<?>)doc.get("tags_docvalues_strings")).orElse(Arrays.asList()).stream().filter(v -> v != null).forEach(v -> {
      oHost.addTags(Host.staticSetTags(siteRequest, v.toString()));
    });

    super.storeBaseModel(doc);
  }

  //////////////////
  // apiRequest //
  //////////////////

  public void apiRequestHost() {
    ApiRequest apiRequest = Optional.ofNullable(siteRequest_).map(r -> r.getApiRequest_()).orElse(null);
    Object o = Optional.ofNullable(apiRequest).map(ApiRequest::getOriginal).orElse(null);
    if(o != null && o instanceof Host) {
      Host original = (Host)o;
      if(!Objects.equals(tenantResource, original.getTenantResource()))
        apiRequest.addVars("tenantResource");
      if(!Objects.equals(tenantId, original.getTenantId()))
        apiRequest.addVars("tenantId");
      if(!Objects.equals(aapOrganizationId, original.getAapOrganizationId()))
        apiRequest.addVars("aapOrganizationId");
      if(!Objects.equals(inventoryResource, original.getInventoryResource()))
        apiRequest.addVars("inventoryResource");
      if(!Objects.equals(aapHostId, original.getAapHostId()))
        apiRequest.addVars("aapHostId");
      if(!Objects.equals(hostName, original.getHostName()))
        apiRequest.addVars("hostName");
      if(!Objects.equals(ipAddress, original.getIpAddress()))
        apiRequest.addVars("ipAddress");
      if(!Objects.equals(hostId, original.getHostId()))
        apiRequest.addVars("hostId");
      if(!Objects.equals(hostResource, original.getHostResource()))
        apiRequest.addVars("hostResource");
      if(!Objects.equals(hostDescription, original.getHostDescription()))
        apiRequest.addVars("hostDescription");
      if(!Objects.equals(aapInventoryId, original.getAapInventoryId()))
        apiRequest.addVars("aapInventoryId");
      if(!Objects.equals(inventoryName, original.getInventoryName()))
        apiRequest.addVars("inventoryName");
      if(!Objects.equals(eventSubscriptions, original.getEventSubscriptions()))
        apiRequest.addVars("eventSubscriptions");
      if(!Objects.equals(tags, original.getTags()))
        apiRequest.addVars("tags");
      super.apiRequestBaseModel();
    }
  }

  //////////////
  // toString //
  //////////////

  @Override public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(super.toString());
    sb.append(Optional.ofNullable(tenantResource).map(v -> "tenantResource: \"" + v + "\"\n" ).orElse(""));
    sb.append(Optional.ofNullable(tenantId).map(v -> "tenantId: \"" + v + "\"\n" ).orElse(""));
    sb.append(Optional.ofNullable(aapOrganizationId).map(v -> "aapOrganizationId: " + v + "\n").orElse(""));
    sb.append(Optional.ofNullable(inventoryResource).map(v -> "inventoryResource: \"" + v + "\"\n" ).orElse(""));
    sb.append(Optional.ofNullable(aapHostId).map(v -> "aapHostId: " + v + "\n").orElse(""));
    sb.append(Optional.ofNullable(hostName).map(v -> "hostName: \"" + v + "\"\n" ).orElse(""));
    sb.append(Optional.ofNullable(ipAddress).map(v -> "ipAddress: \"" + v + "\"\n" ).orElse(""));
    sb.append(Optional.ofNullable(hostId).map(v -> "hostId: \"" + v + "\"\n" ).orElse(""));
    sb.append(Optional.ofNullable(hostResource).map(v -> "hostResource: \"" + v + "\"\n" ).orElse(""));
    sb.append(Optional.ofNullable(hostDescription).map(v -> "hostDescription: \"" + v + "\"\n" ).orElse(""));
    sb.append(Optional.ofNullable(aapInventoryId).map(v -> "aapInventoryId: " + v + "\n").orElse(""));
    sb.append(Optional.ofNullable(inventoryName).map(v -> "inventoryName: \"" + v + "\"\n" ).orElse(""));
    sb.append(Optional.ofNullable(eventSubscriptions).map(v -> "eventSubscriptions: " + v + "\n").orElse(""));
    sb.append(Optional.ofNullable(tags).map(v -> "tags: " + v + "\n").orElse(""));
    return sb.toString();
  }

  public static final String CLASS_SIMPLE_NAME = "Host";
  public static final String CLASS_CANONICAL_NAME = "org.computate.dcm.model.eda.host.Host";
  public static final String CLASS_AUTH_RESOURCE = "HOST";
  public static final String CLASS_API_ADDRESS_Host = "dcm-enUS-Host";
  public static String getClassApiAddress() {
    return CLASS_API_ADDRESS_Host;
  }
  public static final String VAR_tenantResource = "tenantResource";
  public static final String SET_tenantResource = "setTenantResource";
  public static final String VAR_tenantId = "tenantId";
  public static final String SET_tenantId = "setTenantId";
  public static final String VAR_aapOrganizationId = "aapOrganizationId";
  public static final String SET_aapOrganizationId = "setAapOrganizationId";
  public static final String VAR_inventoryResource = "inventoryResource";
  public static final String SET_inventoryResource = "setInventoryResource";
  public static final String VAR_aapHostId = "aapHostId";
  public static final String SET_aapHostId = "setAapHostId";
  public static final String VAR_hostName = "hostName";
  public static final String SET_hostName = "setHostName";
  public static final String VAR_ipAddress = "ipAddress";
  public static final String SET_ipAddress = "setIpAddress";
  public static final String VAR_hostId = "hostId";
  public static final String SET_hostId = "setHostId";
  public static final String VAR_hostResource = "hostResource";
  public static final String SET_hostResource = "setHostResource";
  public static final String VAR_hostDescription = "hostDescription";
  public static final String SET_hostDescription = "setHostDescription";
  public static final String VAR_aapInventoryId = "aapInventoryId";
  public static final String SET_aapInventoryId = "setAapInventoryId";
  public static final String VAR_inventoryName = "inventoryName";
  public static final String SET_inventoryName = "setInventoryName";
  public static final String VAR_eventSubscriptions = "eventSubscriptions";
  public static final String SET_eventSubscriptions = "setEventSubscriptions";
  public static final String VAR_tags = "tags";
  public static final String SET_tags = "setTags";

  public static List<String> varsQForClass() {
    return Host.varsQHost(new ArrayList<String>());
  }
  public static List<String> varsQHost(List<String> vars) {
    BaseModel.varsQBaseModel(vars);
    return vars;
  }

  public static List<String> varsFqForClass() {
    return Host.varsFqHost(new ArrayList<String>());
  }
  public static List<String> varsFqHost(List<String> vars) {
    vars.add(VAR_inventoryName);
    BaseModel.varsFqBaseModel(vars);
    return vars;
  }

  public static List<String> varsRangeForClass() {
    return Host.varsRangeHost(new ArrayList<String>());
  }
  public static List<String> varsRangeHost(List<String> vars) {
    BaseModel.varsRangeBaseModel(vars);
    return vars;
  }

  public static final String DISPLAY_NAME_tenantResource = "tenant";
  public static final String DISPLAY_NAME_tenantId = "tenant ID";
  public static final String DISPLAY_NAME_aapOrganizationId = "AAP ID";
  public static final String DISPLAY_NAME_inventoryResource = "inventory";
  public static final String DISPLAY_NAME_aapHostId = "AAP ID";
  public static final String DISPLAY_NAME_hostName = "Fully Qualified Domain Name";
  public static final String DISPLAY_NAME_ipAddress = "IP address";
  public static final String DISPLAY_NAME_hostId = "host ID";
  public static final String DISPLAY_NAME_hostResource = "host resource";
  public static final String DISPLAY_NAME_hostDescription = "host description";
  public static final String DISPLAY_NAME_aapInventoryId = "AAP ID";
  public static final String DISPLAY_NAME_inventoryName = "inventory name";
  public static final String DISPLAY_NAME_eventSubscriptions = "event subscriptions";
  public static final String DISPLAY_NAME_tags = "tags";

  @Override
  public String idForClass() {
    return hostResource;
  }

  @Override
  public String titleForClass() {
    return objectTitle;
  }

  @Override
  public String nameForClass() {
    return hostName;
  }

  @Override
  public String classNameAdjectiveSingularForClass() {
    return Host.NameAdjectiveSingular_enUS;
  }

  @Override
  public String descriptionForClass() {
    return hostDescription;
  }

  @Override
  public String enUSStringFormatUrlEditPageForClass() {
    return "%s/en-us/edit/host/%s";
  }

  @Override
  public String enUSStringFormatUrlUserPageForClass() {
    return "%s/en-us/user/host/%s";
  }

  public static String varJsonForClass(String var, Boolean patch) {
    return Host.varJsonHost(var, patch);
  }
  public static String varJsonHost(String var, Boolean patch) {
    switch(var) {
    case VAR_tenantResource:
      return patch ? SET_tenantResource : VAR_tenantResource;
    case VAR_tenantId:
      return patch ? SET_tenantId : VAR_tenantId;
    case VAR_aapOrganizationId:
      return patch ? SET_aapOrganizationId : VAR_aapOrganizationId;
    case VAR_inventoryResource:
      return patch ? SET_inventoryResource : VAR_inventoryResource;
    case VAR_aapHostId:
      return patch ? SET_aapHostId : VAR_aapHostId;
    case VAR_hostName:
      return patch ? SET_hostName : VAR_hostName;
    case VAR_ipAddress:
      return patch ? SET_ipAddress : VAR_ipAddress;
    case VAR_hostId:
      return patch ? SET_hostId : VAR_hostId;
    case VAR_hostResource:
      return patch ? SET_hostResource : VAR_hostResource;
    case VAR_hostDescription:
      return patch ? SET_hostDescription : VAR_hostDescription;
    case VAR_aapInventoryId:
      return patch ? SET_aapInventoryId : VAR_aapInventoryId;
    case VAR_inventoryName:
      return patch ? SET_inventoryName : VAR_inventoryName;
    case VAR_eventSubscriptions:
      return patch ? SET_eventSubscriptions : VAR_eventSubscriptions;
    case VAR_tags:
      return patch ? SET_tags : VAR_tags;
    default:
      return BaseModel.varJsonBaseModel(var, patch);
    }
  }

  public static String displayNameForClass(String var) {
    return Host.displayNameHost(var);
  }
  public static String displayNameHost(String var) {
    switch(var) {
    case VAR_tenantResource:
      return DISPLAY_NAME_tenantResource;
    case VAR_tenantId:
      return DISPLAY_NAME_tenantId;
    case VAR_aapOrganizationId:
      return DISPLAY_NAME_aapOrganizationId;
    case VAR_inventoryResource:
      return DISPLAY_NAME_inventoryResource;
    case VAR_aapHostId:
      return DISPLAY_NAME_aapHostId;
    case VAR_hostName:
      return DISPLAY_NAME_hostName;
    case VAR_ipAddress:
      return DISPLAY_NAME_ipAddress;
    case VAR_hostId:
      return DISPLAY_NAME_hostId;
    case VAR_hostResource:
      return DISPLAY_NAME_hostResource;
    case VAR_hostDescription:
      return DISPLAY_NAME_hostDescription;
    case VAR_aapInventoryId:
      return DISPLAY_NAME_aapInventoryId;
    case VAR_inventoryName:
      return DISPLAY_NAME_inventoryName;
    case VAR_eventSubscriptions:
      return DISPLAY_NAME_eventSubscriptions;
    case VAR_tags:
      return DISPLAY_NAME_tags;
    default:
      return BaseModel.displayNameBaseModel(var);
    }
  }

  public static String descriptionHost(String var) {
    if(var == null)
      return null;
    switch(var) {
    case VAR_tenantResource:
      return "The unique authorization resource for the tenant for multi-tenancy";
    case VAR_tenantId:
      return "The tenant ID and Sensu namespace for the tenant. ";
    case VAR_aapOrganizationId:
      return "The Ansible Automation Platform ID of the organization. ";
    case VAR_inventoryResource:
      return "The unique authorization resource for the inventory for multi-tenancy";
    case VAR_aapHostId:
      return "The Ansible Automation Platform ID of the host. ";
    case VAR_hostName:
      return "The computer fully qualified domain name";
    case VAR_ipAddress:
      return "The IP address of the host";
    case VAR_hostId:
      return "The ID of the host in DCM. ";
    case VAR_hostResource:
      return "The unique authorization resource for the host for multi-tenancy";
    case VAR_hostDescription:
      return "The description of the host in AAP. ";
    case VAR_aapInventoryId:
      return "The Ansible Automation Platform ID of the inventory. ";
    case VAR_inventoryName:
      return "The unique authorization resource for the host for multi-tenancy";
    case VAR_eventSubscriptions:
      return "The list of event subscriptions the host subscribes to. ";
    case VAR_tags:
      return "The list of tags the host has. ";
      default:
        return BaseModel.descriptionBaseModel(var);
    }
  }

  public static String classSimpleNameHost(String var) {
    switch(var) {
    case VAR_tenantResource:
      return "String";
    case VAR_tenantId:
      return "String";
    case VAR_aapOrganizationId:
      return "Long";
    case VAR_inventoryResource:
      return "String";
    case VAR_aapHostId:
      return "Long";
    case VAR_hostName:
      return "String";
    case VAR_ipAddress:
      return "String";
    case VAR_hostId:
      return "String";
    case VAR_hostResource:
      return "String";
    case VAR_hostDescription:
      return "String";
    case VAR_aapInventoryId:
      return "Long";
    case VAR_inventoryName:
      return "String";
    case VAR_eventSubscriptions:
      return "List";
    case VAR_tags:
      return "List";
      default:
        return BaseModel.classSimpleNameBaseModel(var);
    }
  }

  public static Integer htmColumnHost(String var) {
    switch(var) {
    case VAR_hostName:
      return 1;
    case VAR_ipAddress:
      return 1;
    case VAR_hostDescription:
      return 2;
    case VAR_eventSubscriptions:
      return 2;
    case VAR_tags:
      return 2;
      default:
        return BaseModel.htmColumnBaseModel(var);
    }
  }

  public static Integer htmRowHost(String var) {
    switch(var) {
    case VAR_inventoryResource:
      return 3;
    case VAR_hostName:
      return 4;
    case VAR_ipAddress:
      return 4;
    case VAR_hostDescription:
      return 4;
    case VAR_eventSubscriptions:
      return 5;
    case VAR_tags:
      return 5;
      default:
        return BaseModel.htmRowBaseModel(var);
    }
  }

  public static Integer htmCellHost(String var) {
    switch(var) {
    case VAR_inventoryResource:
      return 0;
    case VAR_hostName:
      return 0;
    case VAR_ipAddress:
      return 1;
    case VAR_hostDescription:
      return 2;
    case VAR_eventSubscriptions:
      return 1;
    case VAR_tags:
      return 2;
      default:
        return BaseModel.htmCellBaseModel(var);
    }
  }

  public static Integer lengthMinHost(String var) {
    switch(var) {
      default:
        return BaseModel.lengthMinBaseModel(var);
    }
  }

  public static Integer lengthMaxHost(String var) {
    switch(var) {
      default:
        return BaseModel.lengthMaxBaseModel(var);
    }
  }

  public static Integer maxHost(String var) {
    switch(var) {
      default:
        return BaseModel.maxBaseModel(var);
    }
  }

  public static Integer minHost(String var) {
    switch(var) {
      default:
        return BaseModel.minBaseModel(var);
    }
  }
}
