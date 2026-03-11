package org.computate.dcm.model.eda.hostinventory;

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
import org.computate.dcm.model.eda.host.Host;
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
 * <li>You can add a class comment "{@inheritDoc}" if you wish to inherit the helpful inherited class comments from class HostInventoryGen into the class HostInventory. 
 * </li>
 * <h3>About the HostInventory class and it's generated class HostInventoryGen&lt;BaseModel&gt;: </h3>extends HostInventoryGen
 * <p>
 * This Java class extends a generated Java class HostInventoryGen built by the <a href="https://github.com/computate-org/computate">https://github.com/computate-org/computate</a> project. 
 * Whenever this Java class is modified or touched, the watch service installed as described in the README, indexes all the information about this Java class in a local Apache Solr Search Engine. 
 * If you are running the service, you can see the indexed data about this Java Class here: 
 * </p>
 * <p><a href="https://solr.apps-crc.testing/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.hostinventory.HostInventory">Find the class HostInventory in Solr. </a></p>
 * <p>
 * The extended class ending with "Gen" did not exist at first, but was automatically created by the same watch service based on the data retrieved from the local Apache Server search engine. 
 * The extended class contains many generated fields, getters, setters, initialization code, and helper methods to help build a website and API fast, reactive, and scalable. 
 * </p>
 * extends HostInventoryGen<BaseModel>
 * <p>This <code>class HostInventory extends HostInventoryGen&lt;BaseModel&gt;</code>, which means it extends a newly generated HostInventoryGen. 
 * The generated <code>class HostInventoryGen extends BaseModel</code> which means that HostInventory extends HostInventoryGen which extends BaseModel. 
 * This generated inheritance is a powerful feature that allows a lot of boiler plate code to be created for you automatically while still preserving inheritance through the power of Java Generic classes. 
 * </p>
 * <h2>Api: true</h2>
 * <p>This class contains a comment <b>"Api: true"</b>, which means this class will have Java Vert.x API backend code generated for these objects. 
 * </p>
 * <h2>ApiTag.enUS: true</h2>
 * <p>This class contains a comment <b>"ApiTag: host inventories"</b>, which groups all of the OpenAPIs for HostInventory objects under the tag "host inventories". 
 * </p>
 * <h2>ApiUri.enUS: /en-us/api/host-inventory</h2>
 * <p>This class contains a comment <b>"ApiUri: /en-us/api/host-inventory"</b>, which defines the base API URI for HostInventory objects as "/en-us/api/host-inventory" in the OpenAPI spec. 
 * </p>
 * <h2>Color: null</h2>
 * <h2>Indexed: true</h2>
 * <p>This class contains a comment <b>"Indexed: true"</b>, which means this class will be indexed in the search engine. 
 * Every protected void method that begins with "_" that is marked to be searched with a comment like "Indexed: true", "Stored: true", or "DocValues: true" will be indexed in the search engine. 
 * </p>
 * <h2>{@inheritDoc}</h2>
 * <p>By adding a class comment "{@inheritDoc}", the HostInventory class will inherit the helpful inherited class comments from the super class HostInventoryGen. 
 * </p>
 * <h2>Rows: 100</h2>
 * <p>This class contains a comment <b>"Rows: 100"</b>, which means the HostInventory API will return a default of 100 records instead of 10 by default. 
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
 * This creates a new Java class org.computate.dcm.model.eda.hostinventory.HostInventoryPage. 
 * </p>
 * <h2>SuperPage.enUS: PageLayout</h2>
 * <p>This class contains a comment <b>"SuperPage.enUS: PageLayout"</b>, which identifies the Java super class of the page code by it's class simple name "PageLayout". 
 * This means that the newly created class org.computate.dcm.model.eda.hostinventory.HostInventoryPage extends org.computate.dcm.page.PageLayout. 
 * </p>
 * <h2>Promise: true</h2>
 * <p>
 *   This class contains a comment <b>"Promise: true"</b>
 *   Sometimes a Java class must be initialized asynchronously when it involves calling a blocking API. 
 *   This means that the HostInventory Java class has promiseDeep methods which must be initialized asynchronously as a Vert.x Promise  instead of initDeep methods which are a simple non-asynchronous method. 
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
 * <h2>AName.enUS: a host inventory</h2>
 * <p>This class contains a comment <b>"AName.enUS: a host inventory"</b>, which identifies the language context to describe a HostInventory as "a host inventory". 
 * </p>
 * <p>
 * Delete the class HostInventory in Solr: 
 * <pre>
 * curl -k 'https://solr.apps-crc.testing/solr/computate/update?commitWithin=1000&amp;overwrite=true&amp;wt=json' -X POST -H 'Content-type: text/xml' -u "admin:$(oc -n solr get secret/solr-solrcloud-security-bootstrap -o jsonpath={.data.admin} | base64 -d)" --data-raw '&lt;delete&gt;&lt;query&gt;classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.hostinventory.HostInventory&lt;/query&gt;&lt;/delete&gt;'
 * </pre>
 * </p>
 * <p>
 * Delete  the package org.computate.dcm.model.eda.hostinventory in Solr: 
 * <pre>
 * curl -k 'https://solr.apps-crc.testing/solr/computate/update?commitWithin=1000&amp;overwrite=true&amp;wt=json' -X POST -H 'Content-type: text/xml' -u "admin:$(oc -n solr get secret/solr-solrcloud-security-bootstrap -o jsonpath={.data.admin} | base64 -d)" --data-raw '&lt;delete&gt;&lt;query&gt;classeNomEnsemble_enUS_indexed_string:org.computate.dcm.model.eda.hostinventory&lt;/query&gt;&lt;/delete&gt;'
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
public abstract class HostInventoryGen<DEV> extends BaseModel {
  protected static final Logger LOG = LoggerFactory.getLogger(HostInventory.class);

  public static final String Description_enUS = "A managed host inventory. ";
  public static final String AName_enUS = "a host inventory";
  public static final String This_enUS = "this ";
  public static final String ThisName_enUS = "this host inventory";
  public static final String A_enUS = "a ";
  public static final String TheName_enUS = "thehost inventory";
  public static final String SingularName_enUS = "host inventory";
  public static final String PluralName_enUS = "host inventories";
  public static final String NameActual_enUS = "current host inventory";
  public static final String AllName_enUS = "all host inventories";
  public static final String SearchAllNameBy_enUS = "search host inventories by ";
  public static final String SearchAllName_enUS = "search host inventories";
  public static final String Title_enUS = "host inventories";
  public static final String ThePluralName_enUS = "the host inventories";
  public static final String NoNameFound_enUS = "no host inventory found";
  public static final String ApiUri_enUS = "/en-us/api/host-inventory";
  public static final String ApiUriSearchPage_enUS = "/en-us/search/host-inventory";
  public static final String ApiUriEditPage_enUS = "/en-us/edit/host-inventory/{inventoryResource}";
  public static final String OfName_enUS = "of host inventory";
  public static final String ANameAdjective_enUS = "an host inventory";
  public static final String NameAdjectiveSingular_enUS = "host inventory";
  public static final String NameAdjectivePlural_enUS = "host inventories";
  public static final String Search_enUS_OpenApiUri = "/en-us/api/host-inventory";
  public static final String Search_enUS_StringFormatUri = "/en-us/api/host-inventory";
  public static final String Search_enUS_StringFormatUrl = "%s/en-us/api/host-inventory";
  public static final String GET_enUS_OpenApiUri = "/en-us/api/host-inventory/{inventoryResource}";
  public static final String GET_enUS_StringFormatUri = "/en-us/api/host-inventory/%s";
  public static final String GET_enUS_StringFormatUrl = "%s/en-us/api/host-inventory/%s";
  public static final String PATCH_enUS_OpenApiUri = "/en-us/api/host-inventory";
  public static final String PATCH_enUS_StringFormatUri = "/en-us/api/host-inventory";
  public static final String PATCH_enUS_StringFormatUrl = "%s/en-us/api/host-inventory";
  public static final String POST_enUS_OpenApiUri = "/en-us/api/host-inventory";
  public static final String POST_enUS_StringFormatUri = "/en-us/api/host-inventory";
  public static final String POST_enUS_StringFormatUrl = "%s/en-us/api/host-inventory";
  public static final String DELETE_enUS_OpenApiUri = "/en-us/api/host-inventory/{inventoryResource}";
  public static final String DELETE_enUS_StringFormatUri = "/en-us/api/host-inventory/%s";
  public static final String DELETE_enUS_StringFormatUrl = "%s/en-us/api/host-inventory/%s";
  public static final String PUTImport_enUS_OpenApiUri = "/en-us/api/host-inventory-import";
  public static final String PUTImport_enUS_StringFormatUri = "/en-us/api/host-inventory-import";
  public static final String PUTImport_enUS_StringFormatUrl = "%s/en-us/api/host-inventory-import";
  public static final String SearchPage_enUS_OpenApiUri = "/en-us/search/host-inventory";
  public static final String SearchPage_enUS_StringFormatUri = "/en-us/search/host-inventory";
  public static final String SearchPage_enUS_StringFormatUrl = "%s/en-us/search/host-inventory";
  public static final String EditPage_enUS_OpenApiUri = "/en-us/edit/host-inventory/{inventoryResource}";
  public static final String EditPage_enUS_StringFormatUri = "/en-us/edit/host-inventory/%s";
  public static final String EditPage_enUS_StringFormatUrl = "%s/en-us/edit/host-inventory/%s";
  public static final String UserPage_enUS_OpenApiUri = "/en-us/user/host-inventory/{inventoryResource}";
  public static final String UserPage_enUS_StringFormatUri = "/en-us/user/host-inventory/%s";
  public static final String UserPage_enUS_StringFormatUrl = "%s/en-us/user/host-inventory/%s";
  public static final String DELETEFilter_enUS_OpenApiUri = "/en-us/api/host-inventory";
  public static final String DELETEFilter_enUS_StringFormatUri = "/en-us/api/host-inventory";
  public static final String DELETEFilter_enUS_StringFormatUrl = "%s/en-us/api/host-inventory";

  public static final String Icon = "<i class=\"fa-duotone fa-regular fa-network-wired\"></i>";
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
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.hostinventory.HostInventory&fq=entiteVar_enUS_indexed_string:tenantResource">Find the entity tenantResource in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _tenantResource(Wrap<String> w);

  public String getTenantResource() {
    return tenantResource;
  }
  public void setTenantResource(String o) {
    this.tenantResource = HostInventory.staticSetTenantResource(siteRequest_, o);
  }
  public static String staticSetTenantResource(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected HostInventory tenantResourceInit() {
    Wrap<String> tenantResourceWrap = new Wrap<String>().var("tenantResource");
    if(tenantResource == null) {
      _tenantResource(tenantResourceWrap);
      Optional.ofNullable(tenantResourceWrap.getO()).ifPresent(o -> {
        setTenantResource(o);
      });
    }
    return (HostInventory)this;
  }

  public static String staticSearchTenantResource(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrTenantResource(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqTenantResource(SiteRequest siteRequest_, String o) {
    return HostInventory.staticSearchTenantResource(siteRequest_, HostInventory.staticSetTenantResource(siteRequest_, o)).toString();
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
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.hostinventory.HostInventory&fq=entiteVar_enUS_indexed_string:tenantId">Find the entity tenantId in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _tenantId(Wrap<String> w);

  public String getTenantId() {
    return tenantId;
  }
  public void setTenantId(String o) {
    this.tenantId = HostInventory.staticSetTenantId(siteRequest_, o);
  }
  public static String staticSetTenantId(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected HostInventory tenantIdInit() {
    Wrap<String> tenantIdWrap = new Wrap<String>().var("tenantId");
    if(tenantId == null) {
      _tenantId(tenantIdWrap);
      Optional.ofNullable(tenantIdWrap.getO()).ifPresent(o -> {
        setTenantId(o);
      });
    }
    return (HostInventory)this;
  }

  public static String staticSearchTenantId(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrTenantId(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqTenantId(SiteRequest siteRequest_, String o) {
    return HostInventory.staticSearchTenantId(siteRequest_, HostInventory.staticSetTenantId(siteRequest_, o)).toString();
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
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.hostinventory.HostInventory&fq=entiteVar_enUS_indexed_string:aapOrganizationId">Find the entity aapOrganizationId in Solr</a>
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
    this.aapOrganizationId = HostInventory.staticSetAapOrganizationId(siteRequest_, o);
  }
  public static Long staticSetAapOrganizationId(SiteRequest siteRequest_, String o) {
    if(NumberUtils.isParsable(o))
      return Long.parseLong(o);
    return null;
  }
  protected HostInventory aapOrganizationIdInit() {
    Wrap<Long> aapOrganizationIdWrap = new Wrap<Long>().var("aapOrganizationId");
    if(aapOrganizationId == null) {
      _aapOrganizationId(aapOrganizationIdWrap);
      Optional.ofNullable(aapOrganizationIdWrap.getO()).ifPresent(o -> {
        setAapOrganizationId(o);
      });
    }
    return (HostInventory)this;
  }

  public static Long staticSearchAapOrganizationId(SiteRequest siteRequest_, Long o) {
    return o;
  }

  public static String staticSearchStrAapOrganizationId(SiteRequest siteRequest_, Long o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqAapOrganizationId(SiteRequest siteRequest_, String o) {
    return HostInventory.staticSearchAapOrganizationId(siteRequest_, HostInventory.staticSetAapOrganizationId(siteRequest_, o)).toString();
  }

  public Long sqlAapOrganizationId() {
    return aapOrganizationId;
  }

  public static String staticJsonAapOrganizationId(Long aapOrganizationId) {
    return Optional.ofNullable(aapOrganizationId).map(v -> v.toString()).orElse(null);
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
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.hostinventory.HostInventory&fq=entiteVar_enUS_indexed_string:inventoryName">Find the entity inventoryName in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _inventoryName(Wrap<String> w);

  public String getInventoryName() {
    return inventoryName;
  }
  public void setInventoryName(String o) {
    this.inventoryName = HostInventory.staticSetInventoryName(siteRequest_, o);
  }
  public static String staticSetInventoryName(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected HostInventory inventoryNameInit() {
    Wrap<String> inventoryNameWrap = new Wrap<String>().var("inventoryName");
    if(inventoryName == null) {
      _inventoryName(inventoryNameWrap);
      Optional.ofNullable(inventoryNameWrap.getO()).ifPresent(o -> {
        setInventoryName(o);
      });
    }
    return (HostInventory)this;
  }

  public static String staticSearchInventoryName(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrInventoryName(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqInventoryName(SiteRequest siteRequest_, String o) {
    return HostInventory.staticSearchInventoryName(siteRequest_, HostInventory.staticSetInventoryName(siteRequest_, o)).toString();
  }

  public String sqlInventoryName() {
    return inventoryName;
  }

  public static String staticJsonInventoryName(String inventoryName) {
    return inventoryName;
  }

	/////////////////
  // inventoryId //
	/////////////////


  /**
   *  The entity inventoryId
   *	 is defined as null before being initialized. 
   */
  @JsonProperty
  @JsonInclude(Include.NON_NULL)
  protected String inventoryId;

  /**
   * <br> The entity inventoryId
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.hostinventory.HostInventory&fq=entiteVar_enUS_indexed_string:inventoryId">Find the entity inventoryId in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _inventoryId(Wrap<String> w);

  public String getInventoryId() {
    return inventoryId;
  }
  public void setInventoryId(String o) {
    this.inventoryId = HostInventory.staticSetInventoryId(siteRequest_, o);
  }
  public static String staticSetInventoryId(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected HostInventory inventoryIdInit() {
    Wrap<String> inventoryIdWrap = new Wrap<String>().var("inventoryId");
    if(inventoryId == null) {
      _inventoryId(inventoryIdWrap);
      Optional.ofNullable(inventoryIdWrap.getO()).ifPresent(o -> {
        setInventoryId(o);
      });
    }
    return (HostInventory)this;
  }

  public static String staticSearchInventoryId(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrInventoryId(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqInventoryId(SiteRequest siteRequest_, String o) {
    return HostInventory.staticSearchInventoryId(siteRequest_, HostInventory.staticSetInventoryId(siteRequest_, o)).toString();
  }

  public String sqlInventoryId() {
    return inventoryId;
  }

  public static String staticJsonInventoryId(String inventoryId) {
    return inventoryId;
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
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.hostinventory.HostInventory&fq=entiteVar_enUS_indexed_string:inventoryResource">Find the entity inventoryResource in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _inventoryResource(Wrap<String> w);

  public String getInventoryResource() {
    return inventoryResource;
  }
  public void setInventoryResource(String o) {
    this.inventoryResource = HostInventory.staticSetInventoryResource(siteRequest_, o);
  }
  public static String staticSetInventoryResource(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected HostInventory inventoryResourceInit() {
    Wrap<String> inventoryResourceWrap = new Wrap<String>().var("inventoryResource");
    if(inventoryResource == null) {
      _inventoryResource(inventoryResourceWrap);
      Optional.ofNullable(inventoryResourceWrap.getO()).ifPresent(o -> {
        setInventoryResource(o);
      });
    }
    return (HostInventory)this;
  }

  public static String staticSearchInventoryResource(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrInventoryResource(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqInventoryResource(SiteRequest siteRequest_, String o) {
    return HostInventory.staticSearchInventoryResource(siteRequest_, HostInventory.staticSetInventoryResource(siteRequest_, o)).toString();
  }

  public String sqlInventoryResource() {
    return inventoryResource;
  }

  public static String staticJsonInventoryResource(String inventoryResource) {
    return inventoryResource;
  }

	//////////////////////////
  // inventoryDescription //
	//////////////////////////


  /**
   *  The entity inventoryDescription
   *	 is defined as null before being initialized. 
   */
  @JsonProperty
  @JsonInclude(Include.NON_NULL)
  protected String inventoryDescription;

  /**
   * <br> The entity inventoryDescription
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.hostinventory.HostInventory&fq=entiteVar_enUS_indexed_string:inventoryDescription">Find the entity inventoryDescription in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _inventoryDescription(Wrap<String> w);

  public String getInventoryDescription() {
    return inventoryDescription;
  }
  public void setInventoryDescription(String o) {
    this.inventoryDescription = HostInventory.staticSetInventoryDescription(siteRequest_, o);
  }
  public static String staticSetInventoryDescription(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected HostInventory inventoryDescriptionInit() {
    Wrap<String> inventoryDescriptionWrap = new Wrap<String>().var("inventoryDescription");
    if(inventoryDescription == null) {
      _inventoryDescription(inventoryDescriptionWrap);
      Optional.ofNullable(inventoryDescriptionWrap.getO()).ifPresent(o -> {
        setInventoryDescription(o);
      });
    }
    return (HostInventory)this;
  }

  public static String staticSearchInventoryDescription(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrInventoryDescription(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqInventoryDescription(SiteRequest siteRequest_, String o) {
    return HostInventory.staticSearchInventoryDescription(siteRequest_, HostInventory.staticSetInventoryDescription(siteRequest_, o)).toString();
  }

  public String sqlInventoryDescription() {
    return inventoryDescription;
  }

  public static String staticJsonInventoryDescription(String inventoryDescription) {
    return inventoryDescription;
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
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.hostinventory.HostInventory&fq=entiteVar_enUS_indexed_string:aapInventoryId">Find the entity aapInventoryId in Solr</a>
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
    this.aapInventoryId = HostInventory.staticSetAapInventoryId(siteRequest_, o);
  }
  public static Long staticSetAapInventoryId(SiteRequest siteRequest_, String o) {
    if(NumberUtils.isParsable(o))
      return Long.parseLong(o);
    return null;
  }
  protected HostInventory aapInventoryIdInit() {
    Wrap<Long> aapInventoryIdWrap = new Wrap<Long>().var("aapInventoryId");
    if(aapInventoryId == null) {
      _aapInventoryId(aapInventoryIdWrap);
      Optional.ofNullable(aapInventoryIdWrap.getO()).ifPresent(o -> {
        setAapInventoryId(o);
      });
    }
    return (HostInventory)this;
  }

  public static Long staticSearchAapInventoryId(SiteRequest siteRequest_, Long o) {
    return o;
  }

  public static String staticSearchStrAapInventoryId(SiteRequest siteRequest_, Long o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqAapInventoryId(SiteRequest siteRequest_, String o) {
    return HostInventory.staticSearchAapInventoryId(siteRequest_, HostInventory.staticSetAapInventoryId(siteRequest_, o)).toString();
  }

  public Long sqlAapInventoryId() {
    return aapInventoryId;
  }

  public static String staticJsonAapInventoryId(Long aapInventoryId) {
    return Optional.ofNullable(aapInventoryId).map(v -> v.toString()).orElse(null);
  }

	///////////////////
  // inventoryKind //
	///////////////////


  /**
   *  The entity inventoryKind
   *	 is defined as null before being initialized. 
   */
  @JsonProperty
  @JsonInclude(Include.NON_NULL)
  protected String inventoryKind;

  /**
   * <br> The entity inventoryKind
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.hostinventory.HostInventory&fq=entiteVar_enUS_indexed_string:inventoryKind">Find the entity inventoryKind in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _inventoryKind(Wrap<String> w);

  public String getInventoryKind() {
    return inventoryKind;
  }
  public void setInventoryKind(String o) {
    this.inventoryKind = HostInventory.staticSetInventoryKind(siteRequest_, o);
  }
  public static String staticSetInventoryKind(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected HostInventory inventoryKindInit() {
    Wrap<String> inventoryKindWrap = new Wrap<String>().var("inventoryKind");
    if(inventoryKind == null) {
      _inventoryKind(inventoryKindWrap);
      Optional.ofNullable(inventoryKindWrap.getO()).ifPresent(o -> {
        setInventoryKind(o);
      });
    }
    return (HostInventory)this;
  }

  public static String staticSearchInventoryKind(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrInventoryKind(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqInventoryKind(SiteRequest siteRequest_, String o) {
    return HostInventory.staticSearchInventoryKind(siteRequest_, HostInventory.staticSetInventoryKind(siteRequest_, o)).toString();
  }

  public String sqlInventoryKind() {
    return inventoryKind;
  }

  public static String staticJsonInventoryKind(String inventoryKind) {
    return inventoryKind;
  }

	//////////////////////
  // hostInventoryIds //
	//////////////////////


  /**
   *  The entity hostInventoryIds
   *	 It is constructed before being initialized with the constructor by default. 
   */
  @JsonProperty
  @JsonFormat(shape = JsonFormat.Shape.ARRAY)
  @JsonInclude(Include.NON_NULL)
  protected List<String> hostInventoryIds = new ArrayList<String>();

  /**
   * <br> The entity hostInventoryIds
   *  It is constructed before being initialized with the constructor by default. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.hostinventory.HostInventory&fq=entiteVar_enUS_indexed_string:hostInventoryIds">Find the entity hostInventoryIds in Solr</a>
   * <br>
   * @param l is the entity already constructed. 
   **/
  protected abstract void _hostInventoryIds(List<String> l);

  public List<String> getHostInventoryIds() {
    return hostInventoryIds;
  }

  public void setHostInventoryIds(List<String> hostInventoryIds) {
    this.hostInventoryIds = hostInventoryIds;
  }
  @JsonIgnore
  public void setHostInventoryIds(String o) {
    String l = HostInventory.staticSetHostInventoryIds(siteRequest_, o);
    if(l != null)
      addHostInventoryIds(l);
  }
  public static String staticSetHostInventoryIds(SiteRequest siteRequest_, String o) {
    return o;
  }
  public HostInventory addHostInventoryIds(String...objects) {
    for(String o : objects) {
      addHostInventoryIds(o);
    }
    return (HostInventory)this;
  }
  public HostInventory addHostInventoryIds(String o) {
    if(o != null)
      this.hostInventoryIds.add(o);
    return (HostInventory)this;
  }
  @JsonIgnore
  public void setHostInventoryIds(JsonArray objects) {
    hostInventoryIds.clear();
    if(objects == null)
      return;
    for(int i = 0; i < objects.size(); i++) {
      String o = objects.getString(i);
      addHostInventoryIds(o);
    }
  }
  protected HostInventory hostInventoryIdsInit() {
    _hostInventoryIds(hostInventoryIds);
    return (HostInventory)this;
  }

  public static String staticSearchHostInventoryIds(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrHostInventoryIds(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqHostInventoryIds(SiteRequest siteRequest_, String o) {
    return HostInventory.staticSearchHostInventoryIds(siteRequest_, HostInventory.staticSetHostInventoryIds(siteRequest_, o)).toString();
  }

  public String[] sqlHostInventoryIds() {
    return hostInventoryIds.stream().map(v -> (String)v).toArray(String[]::new);
  }

  public static JsonArray staticJsonHostInventoryIds(List<String> hostInventoryIds) {
    JsonArray a = new JsonArray();
    hostInventoryIds.stream().forEach(v -> a.add(v.toString()));
    return a;
  }

  //////////////
  // initDeep //
  //////////////

  public Future<HostInventoryGen<DEV>> promiseDeepHostInventory(SiteRequest siteRequest_) {
    setSiteRequest_(siteRequest_);
    return promiseDeepHostInventory();
  }

  public Future<HostInventoryGen<DEV>> promiseDeepHostInventory() {
    Promise<HostInventoryGen<DEV>> promise = Promise.promise();
    Promise<Void> promise2 = Promise.promise();
    promiseHostInventory(promise2);
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

  public Future<Void> promiseHostInventory(Promise<Void> promise) {
    Future.future(a -> a.complete()).compose(a -> {
      Promise<Void> promise2 = Promise.promise();
      try {
        tenantResourceInit();
        tenantIdInit();
        aapOrganizationIdInit();
        inventoryNameInit();
        inventoryIdInit();
        inventoryResourceInit();
        inventoryDescriptionInit();
        aapInventoryIdInit();
        inventoryKindInit();
        hostInventoryIdsInit();
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

  @Override public Future<? extends HostInventoryGen<DEV>> promiseDeepForClass(SiteRequest siteRequest_) {
    return promiseDeepHostInventory(siteRequest_);
  }

  /////////////////
  // siteRequest //
  /////////////////

  public void siteRequestHostInventory(SiteRequest siteRequest_) {
      super.siteRequestBaseModel(siteRequest_);
  }

  public void siteRequestForClass(SiteRequest siteRequest_) {
    siteRequestHostInventory(siteRequest_);
  }

  /////////////
  // obtain //
  /////////////

  @Override public Object obtainForClass(String var) {
    String[] vars = StringUtils.split(var, ".");
    Object o = null;
    for(String v : vars) {
      if(o == null)
        o = obtainHostInventory(v);
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
  public Object obtainHostInventory(String var) {
    HostInventory oHostInventory = (HostInventory)this;
    switch(var) {
      case "tenantResource":
        return oHostInventory.tenantResource;
      case "tenantId":
        return oHostInventory.tenantId;
      case "aapOrganizationId":
        return oHostInventory.aapOrganizationId;
      case "inventoryName":
        return oHostInventory.inventoryName;
      case "inventoryId":
        return oHostInventory.inventoryId;
      case "inventoryResource":
        return oHostInventory.inventoryResource;
      case "inventoryDescription":
        return oHostInventory.inventoryDescription;
      case "aapInventoryId":
        return oHostInventory.aapInventoryId;
      case "inventoryKind":
        return oHostInventory.inventoryKind;
      case "hostInventoryIds":
        return oHostInventory.hostInventoryIds;
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
        o = relateHostInventory(v, val);
      else if(o instanceof BaseModel) {
        BaseModel baseModel = (BaseModel)o;
        o = baseModel.relateForClass(v, val);
      }
    }
    return o != null;
  }
  public Object relateHostInventory(String var, Object val) {
    HostInventory oHostInventory = (HostInventory)this;
    switch(var) {
      case "tenantResource":
        if(oHostInventory.getTenantResource() == null)
          oHostInventory.setTenantResource(Optional.ofNullable(val).map(v -> v.toString()).orElse(null));
        if(!saves.contains("tenantResource"))
          saves.add("tenantResource");
        return val;
      case "hostInventoryIds":
        oHostInventory.addHostInventoryIds((String)val);
        if(!saves.contains("hostInventoryIds"))
          saves.add("hostInventoryIds");
        return val;
      default:
        return super.relateBaseModel(var, val);
    }
  }

  ///////////////
  // staticSet //
  ///////////////

  public static Object staticSetForClass(String entityVar, SiteRequest siteRequest_, String v, HostInventory o) {
    return staticSetHostInventory(entityVar,  siteRequest_, v, o);
  }
  public static Object staticSetHostInventory(String entityVar, SiteRequest siteRequest_, String v, HostInventory o) {
    switch(entityVar) {
    case "tenantResource":
      return HostInventory.staticSetTenantResource(siteRequest_, v);
    case "tenantId":
      return HostInventory.staticSetTenantId(siteRequest_, v);
    case "aapOrganizationId":
      return HostInventory.staticSetAapOrganizationId(siteRequest_, v);
    case "inventoryName":
      return HostInventory.staticSetInventoryName(siteRequest_, v);
    case "inventoryId":
      return HostInventory.staticSetInventoryId(siteRequest_, v);
    case "inventoryResource":
      return HostInventory.staticSetInventoryResource(siteRequest_, v);
    case "inventoryDescription":
      return HostInventory.staticSetInventoryDescription(siteRequest_, v);
    case "aapInventoryId":
      return HostInventory.staticSetAapInventoryId(siteRequest_, v);
    case "inventoryKind":
      return HostInventory.staticSetInventoryKind(siteRequest_, v);
    case "hostInventoryIds":
      return HostInventory.staticSetHostInventoryIds(siteRequest_, v);
      default:
        return BaseModel.staticSetBaseModel(entityVar,  siteRequest_, v, o);
    }
  }

  //////////////////
  // staticSearch //
  //////////////////

  public static Future<HostInventory> fqHostInventory(SiteRequest siteRequest, String var, Object val) {
    Promise<HostInventory> promise = Promise.promise();
    try {
      if(val == null) {
        promise.complete();
      } else {
        SearchList<HostInventory> searchList = new SearchList<HostInventory>();
        searchList.setStore(true);
        searchList.q("*:*");
        searchList.setC(HostInventory.class);
        searchList.fq(String.format("%s:", HostInventory.varIndexedHostInventory(var)) + SearchTool.escapeQueryChars(val.toString()));
        searchList.promiseDeepForClass(siteRequest).onSuccess(a -> {
          try {
            promise.complete(searchList.getList().stream().findFirst().orElse(null));
          } catch(Throwable ex) {
            LOG.error("Error while querying thehost inventory", ex);
            promise.fail(ex);
          }
        }).onFailure(ex -> {
          LOG.error("Error while querying thehost inventory", ex);
          promise.fail(ex);
        });
      }
    } catch(Throwable ex) {
      LOG.error("Error while querying thehost inventory", ex);
      promise.fail(ex);
    }
    return promise.future();
  }

  public static Object staticSearchForClass(String entityVar, SiteRequest siteRequest_, Object o) {
    return staticSearchHostInventory(entityVar,  siteRequest_, o);
  }
  public static Object staticSearchHostInventory(String entityVar, SiteRequest siteRequest_, Object o) {
    switch(entityVar) {
    case "tenantResource":
      return HostInventory.staticSearchTenantResource(siteRequest_, (String)o);
    case "tenantId":
      return HostInventory.staticSearchTenantId(siteRequest_, (String)o);
    case "aapOrganizationId":
      return HostInventory.staticSearchAapOrganizationId(siteRequest_, (Long)o);
    case "inventoryName":
      return HostInventory.staticSearchInventoryName(siteRequest_, (String)o);
    case "inventoryId":
      return HostInventory.staticSearchInventoryId(siteRequest_, (String)o);
    case "inventoryResource":
      return HostInventory.staticSearchInventoryResource(siteRequest_, (String)o);
    case "inventoryDescription":
      return HostInventory.staticSearchInventoryDescription(siteRequest_, (String)o);
    case "aapInventoryId":
      return HostInventory.staticSearchAapInventoryId(siteRequest_, (Long)o);
    case "inventoryKind":
      return HostInventory.staticSearchInventoryKind(siteRequest_, (String)o);
    case "hostInventoryIds":
      return HostInventory.staticSearchHostInventoryIds(siteRequest_, (String)o);
      default:
        return BaseModel.staticSearchBaseModel(entityVar,  siteRequest_, o);
    }
  }

  ///////////////////
  // staticSearchStr //
  ///////////////////

  public static String staticSearchStrForClass(String entityVar, SiteRequest siteRequest_, Object o) {
    return staticSearchStrHostInventory(entityVar,  siteRequest_, o);
  }
  public static String staticSearchStrHostInventory(String entityVar, SiteRequest siteRequest_, Object o) {
    switch(entityVar) {
    case "tenantResource":
      return HostInventory.staticSearchStrTenantResource(siteRequest_, (String)o);
    case "tenantId":
      return HostInventory.staticSearchStrTenantId(siteRequest_, (String)o);
    case "aapOrganizationId":
      return HostInventory.staticSearchStrAapOrganizationId(siteRequest_, (Long)o);
    case "inventoryName":
      return HostInventory.staticSearchStrInventoryName(siteRequest_, (String)o);
    case "inventoryId":
      return HostInventory.staticSearchStrInventoryId(siteRequest_, (String)o);
    case "inventoryResource":
      return HostInventory.staticSearchStrInventoryResource(siteRequest_, (String)o);
    case "inventoryDescription":
      return HostInventory.staticSearchStrInventoryDescription(siteRequest_, (String)o);
    case "aapInventoryId":
      return HostInventory.staticSearchStrAapInventoryId(siteRequest_, (Long)o);
    case "inventoryKind":
      return HostInventory.staticSearchStrInventoryKind(siteRequest_, (String)o);
    case "hostInventoryIds":
      return HostInventory.staticSearchStrHostInventoryIds(siteRequest_, (String)o);
      default:
        return BaseModel.staticSearchStrBaseModel(entityVar,  siteRequest_, o);
    }
  }

  //////////////////
  // staticSearchFq //
  //////////////////

  public static String staticSearchFqForClass(String entityVar, SiteRequest siteRequest_, String o) {
    return staticSearchFqHostInventory(entityVar,  siteRequest_, o);
  }
  public static String staticSearchFqHostInventory(String entityVar, SiteRequest siteRequest_, String o) {
    switch(entityVar) {
    case "tenantResource":
      return HostInventory.staticSearchFqTenantResource(siteRequest_, o);
    case "tenantId":
      return HostInventory.staticSearchFqTenantId(siteRequest_, o);
    case "aapOrganizationId":
      return HostInventory.staticSearchFqAapOrganizationId(siteRequest_, o);
    case "inventoryName":
      return HostInventory.staticSearchFqInventoryName(siteRequest_, o);
    case "inventoryId":
      return HostInventory.staticSearchFqInventoryId(siteRequest_, o);
    case "inventoryResource":
      return HostInventory.staticSearchFqInventoryResource(siteRequest_, o);
    case "inventoryDescription":
      return HostInventory.staticSearchFqInventoryDescription(siteRequest_, o);
    case "aapInventoryId":
      return HostInventory.staticSearchFqAapInventoryId(siteRequest_, o);
    case "inventoryKind":
      return HostInventory.staticSearchFqInventoryKind(siteRequest_, o);
    case "hostInventoryIds":
      return HostInventory.staticSearchFqHostInventoryIds(siteRequest_, o);
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
          o = persistHostInventory(v, val);
        else if(o instanceof BaseModel) {
          BaseModel oBaseModel = (BaseModel)o;
          o = oBaseModel.persistForClass(v, val);
        }
      }
    }
    return o != null;
  }
  public Object persistHostInventory(String var, Object val) {
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
      } else if("inventoryname".equals(varLower)) {
        if(val instanceof String) {
          setInventoryName((String)val);
        }
        saves.add("inventoryName");
        return val;
      } else if("inventoryid".equals(varLower)) {
        if(val instanceof String) {
          setInventoryId((String)val);
        }
        saves.add("inventoryId");
        return val;
      } else if("inventoryresource".equals(varLower)) {
        if(val instanceof String) {
          setInventoryResource((String)val);
        }
        saves.add("inventoryResource");
        return val;
      } else if("inventorydescription".equals(varLower)) {
        if(val instanceof String) {
          setInventoryDescription((String)val);
        }
        saves.add("inventoryDescription");
        return val;
      } else if("aapinventoryid".equals(varLower)) {
        if(val instanceof Long) {
          setAapInventoryId((Long)val);
        } else {
          setAapInventoryId(val == null ? null : val.toString());
        }
        saves.add("aapInventoryId");
        return val;
      } else if("inventorykind".equals(varLower)) {
        if(val instanceof String) {
          setInventoryKind((String)val);
        }
        saves.add("inventoryKind");
        return val;
    } else {
      return super.persistBaseModel(var, val);
    }
  }

  /////////////
  // populate //
  /////////////

  @Override public void populateForClass(SolrResponse.Doc doc) {
    populateHostInventory(doc);
  }
  public void populateHostInventory(SolrResponse.Doc doc) {
    HostInventory oHostInventory = (HostInventory)this;
    saves = Optional.ofNullable((ArrayList<String>)doc.get("saves_docvalues_strings")).orElse(new ArrayList<String>());
    if(saves != null) {

      String tenantResource = (String)doc.get("tenantResource_docvalues_string");
      if(tenantResource != null)
        oHostInventory.setTenantResource(tenantResource);

      if(saves.contains("tenantId")) {
        String tenantId = (String)doc.get("tenantId_docvalues_string");
        if(tenantId != null)
          oHostInventory.setTenantId(tenantId);
      }

      if(saves.contains("aapOrganizationId")) {
        Long aapOrganizationId = (Long)doc.get("aapOrganizationId_docvalues_long");
        if(aapOrganizationId != null)
          oHostInventory.setAapOrganizationId(aapOrganizationId);
      }

      if(saves.contains("inventoryName")) {
        String inventoryName = (String)doc.get("inventoryName_docvalues_string");
        if(inventoryName != null)
          oHostInventory.setInventoryName(inventoryName);
      }

      if(saves.contains("inventoryId")) {
        String inventoryId = (String)doc.get("inventoryId_docvalues_string");
        if(inventoryId != null)
          oHostInventory.setInventoryId(inventoryId);
      }

      if(saves.contains("inventoryResource")) {
        String inventoryResource = (String)doc.get("inventoryResource_docvalues_string");
        if(inventoryResource != null)
          oHostInventory.setInventoryResource(inventoryResource);
      }

      if(saves.contains("inventoryDescription")) {
        String inventoryDescription = (String)doc.get("inventoryDescription_docvalues_string");
        if(inventoryDescription != null)
          oHostInventory.setInventoryDescription(inventoryDescription);
      }

      if(saves.contains("aapInventoryId")) {
        Long aapInventoryId = (Long)doc.get("aapInventoryId_docvalues_long");
        if(aapInventoryId != null)
          oHostInventory.setAapInventoryId(aapInventoryId);
      }

      if(saves.contains("inventoryKind")) {
        String inventoryKind = (String)doc.get("inventoryKind_docvalues_string");
        if(inventoryKind != null)
          oHostInventory.setInventoryKind(inventoryKind);
      }

      List<String> hostInventoryIds = (List<String>)doc.get("hostInventoryIds_docvalues_strings");
      if(hostInventoryIds != null)
        oHostInventory.hostInventoryIds.addAll(hostInventoryIds);
    }

    super.populateBaseModel(doc);
  }

  public void indexHostInventory(JsonObject doc) {
    if(tenantResource != null) {
      doc.put("tenantResource_docvalues_string", tenantResource);
    }
    if(tenantId != null) {
      doc.put("tenantId_docvalues_string", tenantId);
    }
    if(aapOrganizationId != null) {
      doc.put("aapOrganizationId_docvalues_long", aapOrganizationId);
    }
    if(inventoryName != null) {
      doc.put("inventoryName_docvalues_string", inventoryName);
    }
    if(inventoryId != null) {
      doc.put("inventoryId_docvalues_string", inventoryId);
    }
    if(inventoryResource != null) {
      doc.put("inventoryResource_docvalues_string", inventoryResource);
    }
    if(inventoryDescription != null) {
      doc.put("inventoryDescription_docvalues_string", inventoryDescription);
    }
    if(aapInventoryId != null) {
      doc.put("aapInventoryId_docvalues_long", aapInventoryId);
    }
    if(inventoryKind != null) {
      doc.put("inventoryKind_docvalues_string", inventoryKind);
    }
    if(hostInventoryIds != null) {
      JsonArray l = new JsonArray();
      doc.put("hostInventoryIds_docvalues_strings", l);
      for(String o : hostInventoryIds) {
        l.add(HostInventory.staticSearchHostInventoryIds(siteRequest_, o));
      }
    }
    super.indexBaseModel(doc);

	}

  public static String varStoredHostInventory(String entityVar) {
    switch(entityVar) {
      case "tenantResource":
        return "tenantResource_docvalues_string";
      case "tenantId":
        return "tenantId_docvalues_string";
      case "aapOrganizationId":
        return "aapOrganizationId_docvalues_long";
      case "inventoryName":
        return "inventoryName_docvalues_string";
      case "inventoryId":
        return "inventoryId_docvalues_string";
      case "inventoryResource":
        return "inventoryResource_docvalues_string";
      case "inventoryDescription":
        return "inventoryDescription_docvalues_string";
      case "aapInventoryId":
        return "aapInventoryId_docvalues_long";
      case "inventoryKind":
        return "inventoryKind_docvalues_string";
      case "hostInventoryIds":
        return "hostInventoryIds_docvalues_strings";
      default:
        return BaseModel.varStoredBaseModel(entityVar);
    }
  }

  public static String varIndexedHostInventory(String entityVar) {
    switch(entityVar) {
      case "tenantResource":
        return "tenantResource_docvalues_string";
      case "tenantId":
        return "tenantId_docvalues_string";
      case "aapOrganizationId":
        return "aapOrganizationId_docvalues_long";
      case "inventoryName":
        return "inventoryName_docvalues_string";
      case "inventoryId":
        return "inventoryId_docvalues_string";
      case "inventoryResource":
        return "inventoryResource_docvalues_string";
      case "inventoryDescription":
        return "inventoryDescription_docvalues_string";
      case "aapInventoryId":
        return "aapInventoryId_docvalues_long";
      case "inventoryKind":
        return "inventoryKind_docvalues_string";
      case "hostInventoryIds":
        return "hostInventoryIds_docvalues_strings";
      default:
        return BaseModel.varIndexedBaseModel(entityVar);
    }
  }

  public static String searchVarHostInventory(String searchVar) {
    switch(searchVar) {
      case "tenantResource_docvalues_string":
        return "tenantResource";
      case "tenantId_docvalues_string":
        return "tenantId";
      case "aapOrganizationId_docvalues_long":
        return "aapOrganizationId";
      case "inventoryName_docvalues_string":
        return "inventoryName";
      case "inventoryId_docvalues_string":
        return "inventoryId";
      case "inventoryResource_docvalues_string":
        return "inventoryResource";
      case "inventoryDescription_docvalues_string":
        return "inventoryDescription";
      case "aapInventoryId_docvalues_long":
        return "aapInventoryId";
      case "inventoryKind_docvalues_string":
        return "inventoryKind";
      case "hostInventoryIds_docvalues_strings":
        return "hostInventoryIds";
      default:
        return BaseModel.searchVarBaseModel(searchVar);
    }
  }

  public static String varSearchHostInventory(String entityVar) {
    switch(entityVar) {
      default:
        return BaseModel.varSearchBaseModel(entityVar);
    }
  }

  public static String varSuggestedHostInventory(String entityVar) {
    switch(entityVar) {
      default:
        return BaseModel.varSuggestedBaseModel(entityVar);
    }
  }

  /////////////
  // store //
  /////////////

  @Override public void storeForClass(SolrResponse.Doc doc) {
    storeHostInventory(doc);
  }
  public void storeHostInventory(SolrResponse.Doc doc) {
    HostInventory oHostInventory = (HostInventory)this;
    SiteRequest siteRequest = oHostInventory.getSiteRequest_();

    oHostInventory.setTenantResource(Optional.ofNullable(doc.get("tenantResource_docvalues_string")).map(v -> v.toString()).orElse(null));
    oHostInventory.setTenantId(Optional.ofNullable(doc.get("tenantId_docvalues_string")).map(v -> v.toString()).orElse(null));
    oHostInventory.setAapOrganizationId(Optional.ofNullable(doc.get("aapOrganizationId_docvalues_long")).map(v -> v.toString()).orElse(null));
    oHostInventory.setInventoryName(Optional.ofNullable(doc.get("inventoryName_docvalues_string")).map(v -> v.toString()).orElse(null));
    oHostInventory.setInventoryId(Optional.ofNullable(doc.get("inventoryId_docvalues_string")).map(v -> v.toString()).orElse(null));
    oHostInventory.setInventoryResource(Optional.ofNullable(doc.get("inventoryResource_docvalues_string")).map(v -> v.toString()).orElse(null));
    oHostInventory.setInventoryDescription(Optional.ofNullable(doc.get("inventoryDescription_docvalues_string")).map(v -> v.toString()).orElse(null));
    oHostInventory.setAapInventoryId(Optional.ofNullable(doc.get("aapInventoryId_docvalues_long")).map(v -> v.toString()).orElse(null));
    oHostInventory.setInventoryKind(Optional.ofNullable(doc.get("inventoryKind_docvalues_string")).map(v -> v.toString()).orElse(null));
    Optional.ofNullable((List<?>)doc.get("hostInventoryIds_docvalues_strings")).orElse(Arrays.asList()).stream().filter(v -> v != null).forEach(v -> {
      oHostInventory.addHostInventoryIds(HostInventory.staticSetHostInventoryIds(siteRequest, v.toString()));
    });

    super.storeBaseModel(doc);
  }

  //////////////////
  // apiRequest //
  //////////////////

  public void apiRequestHostInventory() {
    ApiRequest apiRequest = Optional.ofNullable(siteRequest_).map(r -> r.getApiRequest_()).orElse(null);
    Object o = Optional.ofNullable(apiRequest).map(ApiRequest::getOriginal).orElse(null);
    if(o != null && o instanceof HostInventory) {
      HostInventory original = (HostInventory)o;
      if(!Objects.equals(tenantResource, original.getTenantResource()))
        apiRequest.addVars("tenantResource");
      if(!Objects.equals(tenantId, original.getTenantId()))
        apiRequest.addVars("tenantId");
      if(!Objects.equals(aapOrganizationId, original.getAapOrganizationId()))
        apiRequest.addVars("aapOrganizationId");
      if(!Objects.equals(inventoryName, original.getInventoryName()))
        apiRequest.addVars("inventoryName");
      if(!Objects.equals(inventoryId, original.getInventoryId()))
        apiRequest.addVars("inventoryId");
      if(!Objects.equals(inventoryResource, original.getInventoryResource()))
        apiRequest.addVars("inventoryResource");
      if(!Objects.equals(inventoryDescription, original.getInventoryDescription()))
        apiRequest.addVars("inventoryDescription");
      if(!Objects.equals(aapInventoryId, original.getAapInventoryId()))
        apiRequest.addVars("aapInventoryId");
      if(!Objects.equals(inventoryKind, original.getInventoryKind()))
        apiRequest.addVars("inventoryKind");
      if(!Objects.equals(hostInventoryIds, original.getHostInventoryIds()))
        apiRequest.addVars("hostInventoryIds");
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
    sb.append(Optional.ofNullable(inventoryName).map(v -> "inventoryName: \"" + v + "\"\n" ).orElse(""));
    sb.append(Optional.ofNullable(inventoryId).map(v -> "inventoryId: \"" + v + "\"\n" ).orElse(""));
    sb.append(Optional.ofNullable(inventoryResource).map(v -> "inventoryResource: \"" + v + "\"\n" ).orElse(""));
    sb.append(Optional.ofNullable(inventoryDescription).map(v -> "inventoryDescription: \"" + v + "\"\n" ).orElse(""));
    sb.append(Optional.ofNullable(aapInventoryId).map(v -> "aapInventoryId: " + v + "\n").orElse(""));
    sb.append(Optional.ofNullable(inventoryKind).map(v -> "inventoryKind: \"" + v + "\"\n" ).orElse(""));
    sb.append(Optional.ofNullable(hostInventoryIds).map(v -> "hostInventoryIds: " + v + "\n").orElse(""));
    return sb.toString();
  }

  public static final String CLASS_SIMPLE_NAME = "HostInventory";
  public static final String CLASS_CANONICAL_NAME = "org.computate.dcm.model.eda.hostinventory.HostInventory";
  public static final String CLASS_AUTH_RESOURCE = "HOSTINVENTORY";
  public static final String CLASS_API_ADDRESS_HostInventory = "dcm-enUS-HostInventory";
  public static String getClassApiAddress() {
    return CLASS_API_ADDRESS_HostInventory;
  }
  public static final String VAR_tenantResource = "tenantResource";
  public static final String SET_tenantResource = "setTenantResource";
  public static final String VAR_tenantId = "tenantId";
  public static final String SET_tenantId = "setTenantId";
  public static final String VAR_aapOrganizationId = "aapOrganizationId";
  public static final String SET_aapOrganizationId = "setAapOrganizationId";
  public static final String VAR_inventoryName = "inventoryName";
  public static final String SET_inventoryName = "setInventoryName";
  public static final String VAR_inventoryId = "inventoryId";
  public static final String SET_inventoryId = "setInventoryId";
  public static final String VAR_inventoryResource = "inventoryResource";
  public static final String SET_inventoryResource = "setInventoryResource";
  public static final String VAR_inventoryDescription = "inventoryDescription";
  public static final String SET_inventoryDescription = "setInventoryDescription";
  public static final String VAR_aapInventoryId = "aapInventoryId";
  public static final String SET_aapInventoryId = "setAapInventoryId";
  public static final String VAR_inventoryKind = "inventoryKind";
  public static final String SET_inventoryKind = "setInventoryKind";
  public static final String VAR_hostInventoryIds = "hostInventoryIds";
  public static final String SET_hostInventoryIds = "setHostInventoryIds";

  public static List<String> varsQForClass() {
    return HostInventory.varsQHostInventory(new ArrayList<String>());
  }
  public static List<String> varsQHostInventory(List<String> vars) {
    BaseModel.varsQBaseModel(vars);
    return vars;
  }

  public static List<String> varsFqForClass() {
    return HostInventory.varsFqHostInventory(new ArrayList<String>());
  }
  public static List<String> varsFqHostInventory(List<String> vars) {
    vars.add(VAR_tenantId);
    BaseModel.varsFqBaseModel(vars);
    return vars;
  }

  public static List<String> varsRangeForClass() {
    return HostInventory.varsRangeHostInventory(new ArrayList<String>());
  }
  public static List<String> varsRangeHostInventory(List<String> vars) {
    BaseModel.varsRangeBaseModel(vars);
    return vars;
  }

  public static final String DISPLAY_NAME_tenantResource = "tenant auth resource";
  public static final String DISPLAY_NAME_tenantId = "tenant ID";
  public static final String DISPLAY_NAME_aapOrganizationId = "AAP ID";
  public static final String DISPLAY_NAME_inventoryName = "inventory name";
  public static final String DISPLAY_NAME_inventoryId = "inventory ID";
  public static final String DISPLAY_NAME_inventoryResource = "inventory resource";
  public static final String DISPLAY_NAME_inventoryDescription = "inventory description";
  public static final String DISPLAY_NAME_aapInventoryId = "AAP ID";
  public static final String DISPLAY_NAME_inventoryKind = "AAP kind";
  public static final String DISPLAY_NAME_hostInventoryIds = "hosts";

  @Override
  public String idForClass() {
    return inventoryResource;
  }

  @Override
  public String titleForClass() {
    return objectTitle;
  }

  @Override
  public String nameForClass() {
    return inventoryName;
  }

  @Override
  public String classNameAdjectiveSingularForClass() {
    return HostInventory.NameAdjectiveSingular_enUS;
  }

  @Override
  public String descriptionForClass() {
    return inventoryDescription;
  }

  @Override
  public String enUSStringFormatUrlEditPageForClass() {
    return "%s/en-us/edit/host-inventory/%s";
  }

  @Override
  public String enUSStringFormatUrlUserPageForClass() {
    return "%s/en-us/user/host-inventory/%s";
  }

  public static String varJsonForClass(String var, Boolean patch) {
    return HostInventory.varJsonHostInventory(var, patch);
  }
  public static String varJsonHostInventory(String var, Boolean patch) {
    switch(var) {
    case VAR_tenantResource:
      return patch ? SET_tenantResource : VAR_tenantResource;
    case VAR_tenantId:
      return patch ? SET_tenantId : VAR_tenantId;
    case VAR_aapOrganizationId:
      return patch ? SET_aapOrganizationId : VAR_aapOrganizationId;
    case VAR_inventoryName:
      return patch ? SET_inventoryName : VAR_inventoryName;
    case VAR_inventoryId:
      return patch ? SET_inventoryId : VAR_inventoryId;
    case VAR_inventoryResource:
      return patch ? SET_inventoryResource : VAR_inventoryResource;
    case VAR_inventoryDescription:
      return patch ? SET_inventoryDescription : VAR_inventoryDescription;
    case VAR_aapInventoryId:
      return patch ? SET_aapInventoryId : VAR_aapInventoryId;
    case VAR_inventoryKind:
      return patch ? SET_inventoryKind : VAR_inventoryKind;
    case VAR_hostInventoryIds:
      return patch ? SET_hostInventoryIds : VAR_hostInventoryIds;
    default:
      return BaseModel.varJsonBaseModel(var, patch);
    }
  }

  public static String displayNameForClass(String var) {
    return HostInventory.displayNameHostInventory(var);
  }
  public static String displayNameHostInventory(String var) {
    switch(var) {
    case VAR_tenantResource:
      return DISPLAY_NAME_tenantResource;
    case VAR_tenantId:
      return DISPLAY_NAME_tenantId;
    case VAR_aapOrganizationId:
      return DISPLAY_NAME_aapOrganizationId;
    case VAR_inventoryName:
      return DISPLAY_NAME_inventoryName;
    case VAR_inventoryId:
      return DISPLAY_NAME_inventoryId;
    case VAR_inventoryResource:
      return DISPLAY_NAME_inventoryResource;
    case VAR_inventoryDescription:
      return DISPLAY_NAME_inventoryDescription;
    case VAR_aapInventoryId:
      return DISPLAY_NAME_aapInventoryId;
    case VAR_inventoryKind:
      return DISPLAY_NAME_inventoryKind;
    case VAR_hostInventoryIds:
      return DISPLAY_NAME_hostInventoryIds;
    default:
      return BaseModel.displayNameBaseModel(var);
    }
  }

  public static String descriptionHostInventory(String var) {
    if(var == null)
      return null;
    switch(var) {
    case VAR_tenantResource:
      return "The unique authorization resource for the tenant for multi-tenancy";
    case VAR_tenantId:
      return "The ID of this tenant";
    case VAR_aapOrganizationId:
      return "The Ansible Automation Platform ID of the organization. ";
    case VAR_inventoryName:
      return "The name of the inventory in AAP. ";
    case VAR_inventoryId:
      return "The ID of the inventory in DCM. ";
    case VAR_inventoryResource:
      return "The unique authorization resource for the inventory for multi-tenancy";
    case VAR_inventoryDescription:
      return "The description of the inventory in AAP. ";
    case VAR_aapInventoryId:
      return "The Ansible Automation Platform ID of the inventory. ";
    case VAR_inventoryKind:
      return "The Ansible Automation Platform kind of the inventory (\"\", \"smart\", \"constructed\"). ";
    case VAR_hostInventoryIds:
      return "The related hosts for this inventory. ";
      default:
        return BaseModel.descriptionBaseModel(var);
    }
  }

  public static String classSimpleNameHostInventory(String var) {
    switch(var) {
    case VAR_tenantResource:
      return "String";
    case VAR_tenantId:
      return "String";
    case VAR_aapOrganizationId:
      return "Long";
    case VAR_inventoryName:
      return "String";
    case VAR_inventoryId:
      return "String";
    case VAR_inventoryResource:
      return "String";
    case VAR_inventoryDescription:
      return "String";
    case VAR_aapInventoryId:
      return "Long";
    case VAR_inventoryKind:
      return "String";
    case VAR_hostInventoryIds:
      return "List";
      default:
        return BaseModel.classSimpleNameBaseModel(var);
    }
  }

  public static Integer htmColumnHostInventory(String var) {
    switch(var) {
    case VAR_tenantResource:
      return 0;
    case VAR_inventoryName:
      return 1;
    case VAR_inventoryDescription:
      return 2;
      default:
        return BaseModel.htmColumnBaseModel(var);
    }
  }

  public static Integer htmRowHostInventory(String var) {
    switch(var) {
    case VAR_tenantResource:
      return 3;
    case VAR_inventoryName:
      return 4;
    case VAR_inventoryDescription:
      return 4;
    case VAR_inventoryKind:
      return 4;
    case VAR_hostInventoryIds:
      return 5;
      default:
        return BaseModel.htmRowBaseModel(var);
    }
  }

  public static Integer htmCellHostInventory(String var) {
    switch(var) {
    case VAR_tenantResource:
      return 0;
    case VAR_inventoryName:
      return 0;
    case VAR_inventoryDescription:
      return 1;
    case VAR_inventoryKind:
      return 2;
    case VAR_hostInventoryIds:
      return 0;
      default:
        return BaseModel.htmCellBaseModel(var);
    }
  }

  public static Integer lengthMinHostInventory(String var) {
    switch(var) {
      default:
        return BaseModel.lengthMinBaseModel(var);
    }
  }

  public static Integer lengthMaxHostInventory(String var) {
    switch(var) {
      default:
        return BaseModel.lengthMaxBaseModel(var);
    }
  }

  public static Integer maxHostInventory(String var) {
    switch(var) {
      default:
        return BaseModel.maxBaseModel(var);
    }
  }

  public static Integer minHostInventory(String var) {
    switch(var) {
      default:
        return BaseModel.minBaseModel(var);
    }
  }
}
