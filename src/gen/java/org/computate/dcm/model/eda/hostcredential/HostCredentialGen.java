package org.computate.dcm.model.eda.hostcredential;

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
 * <li>You can add a class comment "{@inheritDoc}" if you wish to inherit the helpful inherited class comments from class HostCredentialGen into the class HostCredential. 
 * </li>
 * <h3>About the HostCredential class and it's generated class HostCredentialGen&lt;BaseModel&gt;: </h3>extends HostCredentialGen
 * <p>
 * This Java class extends a generated Java class HostCredentialGen built by the <a href="https://github.com/computate-org/computate">https://github.com/computate-org/computate</a> project. 
 * Whenever this Java class is modified or touched, the watch service installed as described in the README, indexes all the information about this Java class in a local Apache Solr Search Engine. 
 * If you are running the service, you can see the indexed data about this Java Class here: 
 * </p>
 * <p><a href="https://solr.apps-crc.testing/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.hostcredential.HostCredential">Find the class HostCredential in Solr. </a></p>
 * <p>
 * The extended class ending with "Gen" did not exist at first, but was automatically created by the same watch service based on the data retrieved from the local Apache Server search engine. 
 * The extended class contains many generated fields, getters, setters, initialization code, and helper methods to help build a website and API fast, reactive, and scalable. 
 * </p>
 * extends HostCredentialGen<BaseModel>
 * <p>This <code>class HostCredential extends HostCredentialGen&lt;BaseModel&gt;</code>, which means it extends a newly generated HostCredentialGen. 
 * The generated <code>class HostCredentialGen extends BaseModel</code> which means that HostCredential extends HostCredentialGen which extends BaseModel. 
 * This generated inheritance is a powerful feature that allows a lot of boiler plate code to be created for you automatically while still preserving inheritance through the power of Java Generic classes. 
 * </p>
 * <h2>Api: true</h2>
 * <p>This class contains a comment <b>"Api: true"</b>, which means this class will have Java Vert.x API backend code generated for these objects. 
 * </p>
 * <h2>ApiTag.enUS: true</h2>
 * <p>This class contains a comment <b>"ApiTag: host credentials"</b>, which groups all of the OpenAPIs for HostCredential objects under the tag "host credentials". 
 * </p>
 * <h2>ApiUri.enUS: /en-us/api/host-credential</h2>
 * <p>This class contains a comment <b>"ApiUri: /en-us/api/host-credential"</b>, which defines the base API URI for HostCredential objects as "/en-us/api/host-credential" in the OpenAPI spec. 
 * </p>
 * <h2>Color: null</h2>
 * <h2>Indexed: true</h2>
 * <p>This class contains a comment <b>"Indexed: true"</b>, which means this class will be indexed in the search engine. 
 * Every protected void method that begins with "_" that is marked to be searched with a comment like "Indexed: true", "Stored: true", or "DocValues: true" will be indexed in the search engine. 
 * </p>
 * <h2>{@inheritDoc}</h2>
 * <p>By adding a class comment "{@inheritDoc}", the HostCredential class will inherit the helpful inherited class comments from the super class HostCredentialGen. 
 * </p>
 * <h2>Rows: 100</h2>
 * <p>This class contains a comment <b>"Rows: 100"</b>, which means the HostCredential API will return a default of 100 records instead of 10 by default. 
 * Each API has built in pagination of the search records to ensure a user can query all the data a page at a time without running the application out of memory. 
 * </p>
 * <h2>Order: 5</h2>
 * <p>This class contains a comment <b>"Order: 5"</b>, which means this class will be sorted by the given number 5 ascending when code that relates to multiple classes at the same time is generated. 
 * </p>
 * <h2>SqlOrder: 5</h2>
 * <p>This class contains a comment <b>"SqlOrder: 5"</b>, which means this class will be sorted by the given number 5 ascending when SQL code to create and drop the tables is generated. 
 * </p>
 * <h2>Model: true</h2>
 * <p>This class contains a comment <b>"Model: true"</b>, which means this class will be stored in the database. 
 * Every protected void method that begins with "_" that contains a "Persist: true" comment will be a persisted field in the database table. 
 * </p>
 * <h2>Page: true</h2>
 * <p>This class contains a comment <b>"Page: true"</b>, which means this class will have webpage code generated for these objects. 
 * Java Vert.x backend API code, Handlebars HTML template frontend code, and JavaScript code will all generated and can be extended. 
 * This creates a new Java class org.computate.dcm.model.eda.hostcredential.HostCredentialPage. 
 * </p>
 * <h2>SuperPage.enUS: PageLayout</h2>
 * <p>This class contains a comment <b>"SuperPage.enUS: PageLayout"</b>, which identifies the Java super class of the page code by it's class simple name "PageLayout". 
 * This means that the newly created class org.computate.dcm.model.eda.hostcredential.HostCredentialPage extends org.computate.dcm.page.PageLayout. 
 * </p>
 * <h2>Promise: true</h2>
 * <p>
 *   This class contains a comment <b>"Promise: true"</b>
 *   Sometimes a Java class must be initialized asynchronously when it involves calling a blocking API. 
 *   This means that the HostCredential Java class has promiseDeep methods which must be initialized asynchronously as a Vert.x Promise  instead of initDeep methods which are a simple non-asynchronous method. 
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
 * <h2>AName.enUS: a host credential</h2>
 * <p>This class contains a comment <b>"AName.enUS: a host credential"</b>, which identifies the language context to describe a HostCredential as "a host credential". 
 * </p>
 * <p>
 * Delete the class HostCredential in Solr: 
 * <pre>
 * curl -k 'https://solr.apps-crc.testing/solr/computate/update?commitWithin=1000&amp;overwrite=true&amp;wt=json' -X POST -H 'Content-type: text/xml' -u "admin:$(oc -n solr get secret/solr-solrcloud-security-bootstrap -o jsonpath={.data.admin} | base64 -d)" --data-raw '&lt;delete&gt;&lt;query&gt;classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.hostcredential.HostCredential&lt;/query&gt;&lt;/delete&gt;'
 * </pre>
 * </p>
 * <p>
 * Delete  the package org.computate.dcm.model.eda.hostcredential in Solr: 
 * <pre>
 * curl -k 'https://solr.apps-crc.testing/solr/computate/update?commitWithin=1000&amp;overwrite=true&amp;wt=json' -X POST -H 'Content-type: text/xml' -u "admin:$(oc -n solr get secret/solr-solrcloud-security-bootstrap -o jsonpath={.data.admin} | base64 -d)" --data-raw '&lt;delete&gt;&lt;query&gt;classeNomEnsemble_enUS_indexed_string:org.computate.dcm.model.eda.hostcredential&lt;/query&gt;&lt;/delete&gt;'
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
public abstract class HostCredentialGen<DEV> extends BaseModel {
  protected static final Logger LOG = LoggerFactory.getLogger(HostCredential.class);

  public static final String Description_enUS = "A managed host credential. ";
  public static final String AName_enUS = "a host credential";
  public static final String This_enUS = "this ";
  public static final String ThisName_enUS = "this host credential";
  public static final String A_enUS = "a ";
  public static final String TheName_enUS = "thehost credential";
  public static final String SingularName_enUS = "host credential";
  public static final String PluralName_enUS = "host credentials";
  public static final String NameActual_enUS = "current host credential";
  public static final String AllName_enUS = "all host credentials";
  public static final String SearchAllNameBy_enUS = "search host credentials by ";
  public static final String SearchAllName_enUS = "search host credentials";
  public static final String Title_enUS = "host credentials";
  public static final String ThePluralName_enUS = "the host credentials";
  public static final String NoNameFound_enUS = "no host credential found";
  public static final String ApiUri_enUS = "/en-us/api/host-credential";
  public static final String ApiUriSearchPage_enUS = "/en-us/search/host-credential";
  public static final String ApiUriEditPage_enUS = "/en-us/edit/host-credential/{credentialResource}";
  public static final String OfName_enUS = "of host credential";
  public static final String ANameAdjective_enUS = "an host credential";
  public static final String NameAdjectiveSingular_enUS = "host credential";
  public static final String NameAdjectivePlural_enUS = "host credentials";
  public static final String Search_enUS_OpenApiUri = "/en-us/api/host-credential";
  public static final String Search_enUS_StringFormatUri = "/en-us/api/host-credential";
  public static final String Search_enUS_StringFormatUrl = "%s/en-us/api/host-credential";
  public static final String GET_enUS_OpenApiUri = "/en-us/api/host-credential/{credentialResource}";
  public static final String GET_enUS_StringFormatUri = "/en-us/api/host-credential/%s";
  public static final String GET_enUS_StringFormatUrl = "%s/en-us/api/host-credential/%s";
  public static final String PATCH_enUS_OpenApiUri = "/en-us/api/host-credential";
  public static final String PATCH_enUS_StringFormatUri = "/en-us/api/host-credential";
  public static final String PATCH_enUS_StringFormatUrl = "%s/en-us/api/host-credential";
  public static final String POST_enUS_OpenApiUri = "/en-us/api/host-credential";
  public static final String POST_enUS_StringFormatUri = "/en-us/api/host-credential";
  public static final String POST_enUS_StringFormatUrl = "%s/en-us/api/host-credential";
  public static final String DELETE_enUS_OpenApiUri = "/en-us/api/host-credential/{credentialResource}";
  public static final String DELETE_enUS_StringFormatUri = "/en-us/api/host-credential/%s";
  public static final String DELETE_enUS_StringFormatUrl = "%s/en-us/api/host-credential/%s";
  public static final String PUTImport_enUS_OpenApiUri = "/en-us/api/host-credential-import";
  public static final String PUTImport_enUS_StringFormatUri = "/en-us/api/host-credential-import";
  public static final String PUTImport_enUS_StringFormatUrl = "%s/en-us/api/host-credential-import";
  public static final String SearchPage_enUS_OpenApiUri = "/en-us/search/host-credential";
  public static final String SearchPage_enUS_StringFormatUri = "/en-us/search/host-credential";
  public static final String SearchPage_enUS_StringFormatUrl = "%s/en-us/search/host-credential";
  public static final String EditPage_enUS_OpenApiUri = "/en-us/edit/host-credential/{credentialResource}";
  public static final String EditPage_enUS_StringFormatUri = "/en-us/edit/host-credential/%s";
  public static final String EditPage_enUS_StringFormatUrl = "%s/en-us/edit/host-credential/%s";
  public static final String UserPage_enUS_OpenApiUri = "/en-us/user/host-credential/{credentialResource}";
  public static final String UserPage_enUS_StringFormatUri = "/en-us/user/host-credential/%s";
  public static final String UserPage_enUS_StringFormatUrl = "%s/en-us/user/host-credential/%s";
  public static final String DELETEFilter_enUS_OpenApiUri = "/en-us/api/host-credential";
  public static final String DELETEFilter_enUS_StringFormatUri = "/en-us/api/host-credential";
  public static final String DELETEFilter_enUS_StringFormatUrl = "%s/en-us/api/host-credential";

  public static final String Icon = "<i class=\"{{ FONTAWESOME_STYLE }} fa-input-password\"></i>";
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
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.hostcredential.HostCredential&fq=entiteVar_enUS_indexed_string:tenantResource">Find the entity tenantResource in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _tenantResource(Wrap<String> w);

  public String getTenantResource() {
    return tenantResource;
  }
  public void setTenantResource(String o) {
    this.tenantResource = HostCredential.staticSetTenantResource(siteRequest_, o);
  }
  public static String staticSetTenantResource(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected HostCredential tenantResourceInit() {
    Wrap<String> tenantResourceWrap = new Wrap<String>().var("tenantResource");
    if(tenantResource == null) {
      _tenantResource(tenantResourceWrap);
      Optional.ofNullable(tenantResourceWrap.getO()).ifPresent(o -> {
        setTenantResource(o);
      });
    }
    return (HostCredential)this;
  }

  public static String staticSearchTenantResource(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrTenantResource(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqTenantResource(SiteRequest siteRequest_, String o) {
    return HostCredential.staticSearchTenantResource(siteRequest_, HostCredential.staticSetTenantResource(siteRequest_, o)).toString();
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
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.hostcredential.HostCredential&fq=entiteVar_enUS_indexed_string:tenantId">Find the entity tenantId in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _tenantId(Wrap<String> w);

  public String getTenantId() {
    return tenantId;
  }
  public void setTenantId(String o) {
    this.tenantId = HostCredential.staticSetTenantId(siteRequest_, o);
  }
  public static String staticSetTenantId(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected HostCredential tenantIdInit() {
    Wrap<String> tenantIdWrap = new Wrap<String>().var("tenantId");
    if(tenantId == null) {
      _tenantId(tenantIdWrap);
      Optional.ofNullable(tenantIdWrap.getO()).ifPresent(o -> {
        setTenantId(o);
      });
    }
    return (HostCredential)this;
  }

  public static String staticSearchTenantId(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrTenantId(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqTenantId(SiteRequest siteRequest_, String o) {
    return HostCredential.staticSearchTenantId(siteRequest_, HostCredential.staticSetTenantId(siteRequest_, o)).toString();
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
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.hostcredential.HostCredential&fq=entiteVar_enUS_indexed_string:aapOrganizationId">Find the entity aapOrganizationId in Solr</a>
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
    this.aapOrganizationId = HostCredential.staticSetAapOrganizationId(siteRequest_, o);
  }
  public static Long staticSetAapOrganizationId(SiteRequest siteRequest_, String o) {
    if(NumberUtils.isParsable(o))
      return Long.parseLong(o);
    return null;
  }
  protected HostCredential aapOrganizationIdInit() {
    Wrap<Long> aapOrganizationIdWrap = new Wrap<Long>().var("aapOrganizationId");
    if(aapOrganizationId == null) {
      _aapOrganizationId(aapOrganizationIdWrap);
      Optional.ofNullable(aapOrganizationIdWrap.getO()).ifPresent(o -> {
        setAapOrganizationId(o);
      });
    }
    return (HostCredential)this;
  }

  public static Long staticSearchAapOrganizationId(SiteRequest siteRequest_, Long o) {
    return o;
  }

  public static String staticSearchStrAapOrganizationId(SiteRequest siteRequest_, Long o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqAapOrganizationId(SiteRequest siteRequest_, String o) {
    return HostCredential.staticSearchAapOrganizationId(siteRequest_, HostCredential.staticSetAapOrganizationId(siteRequest_, o)).toString();
  }

  public Long sqlAapOrganizationId() {
    return aapOrganizationId;
  }

  public static String staticJsonAapOrganizationId(Long aapOrganizationId) {
    return Optional.ofNullable(aapOrganizationId).map(v -> v.toString()).orElse(null);
  }

	////////////////////
  // credentialName //
	////////////////////


  /**
   *  The entity credentialName
   *	 is defined as null before being initialized. 
   */
  @JsonProperty
  @JsonInclude(Include.NON_NULL)
  protected String credentialName;

  /**
   * <br> The entity credentialName
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.hostcredential.HostCredential&fq=entiteVar_enUS_indexed_string:credentialName">Find the entity credentialName in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _credentialName(Wrap<String> w);

  public String getCredentialName() {
    return credentialName;
  }
  public void setCredentialName(String o) {
    this.credentialName = HostCredential.staticSetCredentialName(siteRequest_, o);
  }
  public static String staticSetCredentialName(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected HostCredential credentialNameInit() {
    Wrap<String> credentialNameWrap = new Wrap<String>().var("credentialName");
    if(credentialName == null) {
      _credentialName(credentialNameWrap);
      Optional.ofNullable(credentialNameWrap.getO()).ifPresent(o -> {
        setCredentialName(o);
      });
    }
    return (HostCredential)this;
  }

  public static String staticSearchCredentialName(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrCredentialName(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqCredentialName(SiteRequest siteRequest_, String o) {
    return HostCredential.staticSearchCredentialName(siteRequest_, HostCredential.staticSetCredentialName(siteRequest_, o)).toString();
  }

  public String sqlCredentialName() {
    return credentialName;
  }

  public static String staticJsonCredentialName(String credentialName) {
    return credentialName;
  }

	//////////////////
  // credentialId //
	//////////////////


  /**
   *  The entity credentialId
   *	 is defined as null before being initialized. 
   */
  @JsonProperty
  @JsonInclude(Include.NON_NULL)
  protected String credentialId;

  /**
   * <br> The entity credentialId
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.hostcredential.HostCredential&fq=entiteVar_enUS_indexed_string:credentialId">Find the entity credentialId in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _credentialId(Wrap<String> w);

  public String getCredentialId() {
    return credentialId;
  }
  public void setCredentialId(String o) {
    this.credentialId = HostCredential.staticSetCredentialId(siteRequest_, o);
  }
  public static String staticSetCredentialId(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected HostCredential credentialIdInit() {
    Wrap<String> credentialIdWrap = new Wrap<String>().var("credentialId");
    if(credentialId == null) {
      _credentialId(credentialIdWrap);
      Optional.ofNullable(credentialIdWrap.getO()).ifPresent(o -> {
        setCredentialId(o);
      });
    }
    return (HostCredential)this;
  }

  public static String staticSearchCredentialId(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrCredentialId(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqCredentialId(SiteRequest siteRequest_, String o) {
    return HostCredential.staticSearchCredentialId(siteRequest_, HostCredential.staticSetCredentialId(siteRequest_, o)).toString();
  }

  public String sqlCredentialId() {
    return credentialId;
  }

  public static String staticJsonCredentialId(String credentialId) {
    return credentialId;
  }

	////////////////////////
  // credentialResource //
	////////////////////////


  /**
   *  The entity credentialResource
   *	 is defined as null before being initialized. 
   */
  @JsonProperty
  @JsonInclude(Include.NON_NULL)
  protected String credentialResource;

  /**
   * <br> The entity credentialResource
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.hostcredential.HostCredential&fq=entiteVar_enUS_indexed_string:credentialResource">Find the entity credentialResource in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _credentialResource(Wrap<String> w);

  public String getCredentialResource() {
    return credentialResource;
  }
  public void setCredentialResource(String o) {
    this.credentialResource = HostCredential.staticSetCredentialResource(siteRequest_, o);
  }
  public static String staticSetCredentialResource(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected HostCredential credentialResourceInit() {
    Wrap<String> credentialResourceWrap = new Wrap<String>().var("credentialResource");
    if(credentialResource == null) {
      _credentialResource(credentialResourceWrap);
      Optional.ofNullable(credentialResourceWrap.getO()).ifPresent(o -> {
        setCredentialResource(o);
      });
    }
    return (HostCredential)this;
  }

  public static String staticSearchCredentialResource(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrCredentialResource(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqCredentialResource(SiteRequest siteRequest_, String o) {
    return HostCredential.staticSearchCredentialResource(siteRequest_, HostCredential.staticSetCredentialResource(siteRequest_, o)).toString();
  }

  public String sqlCredentialResource() {
    return credentialResource;
  }

  public static String staticJsonCredentialResource(String credentialResource) {
    return credentialResource;
  }

	///////////////////////////
  // credentialDescription //
	///////////////////////////


  /**
   *  The entity credentialDescription
   *	 is defined as null before being initialized. 
   */
  @JsonProperty
  @JsonInclude(Include.NON_NULL)
  protected String credentialDescription;

  /**
   * <br> The entity credentialDescription
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.hostcredential.HostCredential&fq=entiteVar_enUS_indexed_string:credentialDescription">Find the entity credentialDescription in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _credentialDescription(Wrap<String> w);

  public String getCredentialDescription() {
    return credentialDescription;
  }
  public void setCredentialDescription(String o) {
    this.credentialDescription = HostCredential.staticSetCredentialDescription(siteRequest_, o);
  }
  public static String staticSetCredentialDescription(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected HostCredential credentialDescriptionInit() {
    Wrap<String> credentialDescriptionWrap = new Wrap<String>().var("credentialDescription");
    if(credentialDescription == null) {
      _credentialDescription(credentialDescriptionWrap);
      Optional.ofNullable(credentialDescriptionWrap.getO()).ifPresent(o -> {
        setCredentialDescription(o);
      });
    }
    return (HostCredential)this;
  }

  public static String staticSearchCredentialDescription(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrCredentialDescription(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqCredentialDescription(SiteRequest siteRequest_, String o) {
    return HostCredential.staticSearchCredentialDescription(siteRequest_, HostCredential.staticSetCredentialDescription(siteRequest_, o)).toString();
  }

  public String sqlCredentialDescription() {
    return credentialDescription;
  }

  public static String staticJsonCredentialDescription(String credentialDescription) {
    return credentialDescription;
  }

	/////////////////////
  // aapCredentialId //
	/////////////////////


  /**
   *  The entity aapCredentialId
   *	 is defined as null before being initialized. 
   */
  @JsonProperty
  @JsonSerialize(using = ToStringSerializer.class)
  @JsonInclude(Include.NON_NULL)
  protected Long aapCredentialId;

  /**
   * <br> The entity aapCredentialId
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.hostcredential.HostCredential&fq=entiteVar_enUS_indexed_string:aapCredentialId">Find the entity aapCredentialId in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _aapCredentialId(Wrap<Long> w);

  public Long getAapCredentialId() {
    return aapCredentialId;
  }

  public void setAapCredentialId(Long aapCredentialId) {
    this.aapCredentialId = aapCredentialId;
  }
  @JsonIgnore
  public void setAapCredentialId(String o) {
    this.aapCredentialId = HostCredential.staticSetAapCredentialId(siteRequest_, o);
  }
  public static Long staticSetAapCredentialId(SiteRequest siteRequest_, String o) {
    if(NumberUtils.isParsable(o))
      return Long.parseLong(o);
    return null;
  }
  protected HostCredential aapCredentialIdInit() {
    Wrap<Long> aapCredentialIdWrap = new Wrap<Long>().var("aapCredentialId");
    if(aapCredentialId == null) {
      _aapCredentialId(aapCredentialIdWrap);
      Optional.ofNullable(aapCredentialIdWrap.getO()).ifPresent(o -> {
        setAapCredentialId(o);
      });
    }
    return (HostCredential)this;
  }

  public static Long staticSearchAapCredentialId(SiteRequest siteRequest_, Long o) {
    return o;
  }

  public static String staticSearchStrAapCredentialId(SiteRequest siteRequest_, Long o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqAapCredentialId(SiteRequest siteRequest_, String o) {
    return HostCredential.staticSearchAapCredentialId(siteRequest_, HostCredential.staticSetAapCredentialId(siteRequest_, o)).toString();
  }

  public Long sqlAapCredentialId() {
    return aapCredentialId;
  }

  public static String staticJsonAapCredentialId(Long aapCredentialId) {
    return Optional.ofNullable(aapCredentialId).map(v -> v.toString()).orElse(null);
  }

	/////////////////////////
  // aapCredentialTypeId //
	/////////////////////////


  /**
   *  The entity aapCredentialTypeId
   *	 is defined as null before being initialized. 
   */
  @JsonProperty
  @JsonSerialize(using = ToStringSerializer.class)
  @JsonInclude(Include.NON_NULL)
  protected Long aapCredentialTypeId;

  /**
   * <br> The entity aapCredentialTypeId
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.hostcredential.HostCredential&fq=entiteVar_enUS_indexed_string:aapCredentialTypeId">Find the entity aapCredentialTypeId in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _aapCredentialTypeId(Wrap<Long> w);

  public Long getAapCredentialTypeId() {
    return aapCredentialTypeId;
  }

  public void setAapCredentialTypeId(Long aapCredentialTypeId) {
    this.aapCredentialTypeId = aapCredentialTypeId;
  }
  @JsonIgnore
  public void setAapCredentialTypeId(String o) {
    this.aapCredentialTypeId = HostCredential.staticSetAapCredentialTypeId(siteRequest_, o);
  }
  public static Long staticSetAapCredentialTypeId(SiteRequest siteRequest_, String o) {
    if(NumberUtils.isParsable(o))
      return Long.parseLong(o);
    return null;
  }
  protected HostCredential aapCredentialTypeIdInit() {
    Wrap<Long> aapCredentialTypeIdWrap = new Wrap<Long>().var("aapCredentialTypeId");
    if(aapCredentialTypeId == null) {
      _aapCredentialTypeId(aapCredentialTypeIdWrap);
      Optional.ofNullable(aapCredentialTypeIdWrap.getO()).ifPresent(o -> {
        setAapCredentialTypeId(o);
      });
    }
    return (HostCredential)this;
  }

  public static Long staticSearchAapCredentialTypeId(SiteRequest siteRequest_, Long o) {
    return o;
  }

  public static String staticSearchStrAapCredentialTypeId(SiteRequest siteRequest_, Long o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqAapCredentialTypeId(SiteRequest siteRequest_, String o) {
    return HostCredential.staticSearchAapCredentialTypeId(siteRequest_, HostCredential.staticSetAapCredentialTypeId(siteRequest_, o)).toString();
  }

  public Long sqlAapCredentialTypeId() {
    return aapCredentialTypeId;
  }

  public static String staticJsonAapCredentialTypeId(Long aapCredentialTypeId) {
    return Optional.ofNullable(aapCredentialTypeId).map(v -> v.toString()).orElse(null);
  }

	//////////////
  // userName //
	//////////////


  /**
   *  The entity userName
   *	 is defined as null before being initialized. 
   */
  @JsonProperty
  @JsonInclude(Include.NON_NULL)
  protected String userName;

  /**
   * <br> The entity userName
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.hostcredential.HostCredential&fq=entiteVar_enUS_indexed_string:userName">Find the entity userName in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _userName(Wrap<String> w);

  public String getUserName() {
    return userName;
  }
  public void setUserName(String o) {
    this.userName = HostCredential.staticSetUserName(siteRequest_, o);
  }
  public static String staticSetUserName(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected HostCredential userNameInit() {
    Wrap<String> userNameWrap = new Wrap<String>().var("userName");
    if(userName == null) {
      _userName(userNameWrap);
      Optional.ofNullable(userNameWrap.getO()).ifPresent(o -> {
        setUserName(o);
      });
    }
    return (HostCredential)this;
  }

  public static String staticSearchUserName(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrUserName(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqUserName(SiteRequest siteRequest_, String o) {
    return HostCredential.staticSearchUserName(siteRequest_, HostCredential.staticSetUserName(siteRequest_, o)).toString();
  }

  public String sqlUserName() {
    return userName;
  }

  public static String staticJsonUserName(String userName) {
    return userName;
  }

	//////////////
  // password //
	//////////////


  /**
   *  The entity password
   *	 is defined as null before being initialized. 
   */
  @JsonProperty
  @JsonInclude(Include.NON_NULL)
  protected String password;

  /**
   * <br> The entity password
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.hostcredential.HostCredential&fq=entiteVar_enUS_indexed_string:password">Find the entity password in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _password(Wrap<String> w);

  public String getPassword() {
    return password;
  }
  public void setPassword(String o) {
    this.password = HostCredential.staticSetPassword(siteRequest_, o);
  }
  public static String staticSetPassword(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected HostCredential passwordInit() {
    Wrap<String> passwordWrap = new Wrap<String>().var("password");
    if(password == null) {
      _password(passwordWrap);
      Optional.ofNullable(passwordWrap.getO()).ifPresent(o -> {
        setPassword(o);
      });
    }
    return (HostCredential)this;
  }

  public static String staticSearchPassword(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrPassword(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqPassword(SiteRequest siteRequest_, String o) {
    return HostCredential.staticSearchPassword(siteRequest_, HostCredential.staticSetPassword(siteRequest_, o)).toString();
  }

  public String sqlPassword() {
    return password;
  }

  public static String staticJsonPassword(String password) {
    return password;
  }

	//////////////////
  // becomeMethod //
	//////////////////


  /**
   *  The entity becomeMethod
   *	 is defined as null before being initialized. 
   */
  @JsonProperty
  @JsonInclude(Include.NON_NULL)
  protected String becomeMethod;

  /**
   * <br> The entity becomeMethod
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.hostcredential.HostCredential&fq=entiteVar_enUS_indexed_string:becomeMethod">Find the entity becomeMethod in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _becomeMethod(Wrap<String> w);

  public String getBecomeMethod() {
    return becomeMethod;
  }
  public void setBecomeMethod(String o) {
    this.becomeMethod = HostCredential.staticSetBecomeMethod(siteRequest_, o);
  }
  public static String staticSetBecomeMethod(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected HostCredential becomeMethodInit() {
    Wrap<String> becomeMethodWrap = new Wrap<String>().var("becomeMethod");
    if(becomeMethod == null) {
      _becomeMethod(becomeMethodWrap);
      Optional.ofNullable(becomeMethodWrap.getO()).ifPresent(o -> {
        setBecomeMethod(o);
      });
    }
    return (HostCredential)this;
  }

  public static String staticSearchBecomeMethod(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrBecomeMethod(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqBecomeMethod(SiteRequest siteRequest_, String o) {
    return HostCredential.staticSearchBecomeMethod(siteRequest_, HostCredential.staticSetBecomeMethod(siteRequest_, o)).toString();
  }

  public String sqlBecomeMethod() {
    return becomeMethod;
  }

  public static String staticJsonBecomeMethod(String becomeMethod) {
    return becomeMethod;
  }

	////////////////////
  // becomePassword //
	////////////////////


  /**
   *  The entity becomePassword
   *	 is defined as null before being initialized. 
   */
  @JsonProperty
  @JsonInclude(Include.NON_NULL)
  protected String becomePassword;

  /**
   * <br> The entity becomePassword
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.hostcredential.HostCredential&fq=entiteVar_enUS_indexed_string:becomePassword">Find the entity becomePassword in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _becomePassword(Wrap<String> w);

  public String getBecomePassword() {
    return becomePassword;
  }
  public void setBecomePassword(String o) {
    this.becomePassword = HostCredential.staticSetBecomePassword(siteRequest_, o);
  }
  public static String staticSetBecomePassword(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected HostCredential becomePasswordInit() {
    Wrap<String> becomePasswordWrap = new Wrap<String>().var("becomePassword");
    if(becomePassword == null) {
      _becomePassword(becomePasswordWrap);
      Optional.ofNullable(becomePasswordWrap.getO()).ifPresent(o -> {
        setBecomePassword(o);
      });
    }
    return (HostCredential)this;
  }

  public static String staticSearchBecomePassword(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrBecomePassword(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqBecomePassword(SiteRequest siteRequest_, String o) {
    return HostCredential.staticSearchBecomePassword(siteRequest_, HostCredential.staticSetBecomePassword(siteRequest_, o)).toString();
  }

  public String sqlBecomePassword() {
    return becomePassword;
  }

  public static String staticJsonBecomePassword(String becomePassword) {
    return becomePassword;
  }

  //////////////
  // initDeep //
  //////////////

  public Future<HostCredentialGen<DEV>> promiseDeepHostCredential(SiteRequest siteRequest_) {
    setSiteRequest_(siteRequest_);
    return promiseDeepHostCredential();
  }

  public Future<HostCredentialGen<DEV>> promiseDeepHostCredential() {
    Promise<HostCredentialGen<DEV>> promise = Promise.promise();
    Promise<Void> promise2 = Promise.promise();
    promiseHostCredential(promise2);
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

  public Future<Void> promiseHostCredential(Promise<Void> promise) {
    Future.future(a -> a.complete()).compose(a -> {
      Promise<Void> promise2 = Promise.promise();
      try {
        tenantResourceInit();
        tenantIdInit();
        aapOrganizationIdInit();
        credentialNameInit();
        credentialIdInit();
        credentialResourceInit();
        credentialDescriptionInit();
        aapCredentialIdInit();
        aapCredentialTypeIdInit();
        userNameInit();
        passwordInit();
        becomeMethodInit();
        becomePasswordInit();
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

  @Override public Future<? extends HostCredentialGen<DEV>> promiseDeepForClass(SiteRequest siteRequest_) {
    return promiseDeepHostCredential(siteRequest_);
  }

  /////////////////
  // siteRequest //
  /////////////////

  public void siteRequestHostCredential(SiteRequest siteRequest_) {
      super.siteRequestBaseModel(siteRequest_);
  }

  public void siteRequestForClass(SiteRequest siteRequest_) {
    siteRequestHostCredential(siteRequest_);
  }

  /////////////
  // obtain //
  /////////////

  @Override public Object obtainForClass(String var) {
    String[] vars = StringUtils.split(var, ".");
    Object o = null;
    for(String v : vars) {
      if(o == null)
        o = obtainHostCredential(v);
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
  public Object obtainHostCredential(String var) {
    HostCredential oHostCredential = (HostCredential)this;
    switch(var) {
      case "tenantResource":
        return oHostCredential.tenantResource;
      case "tenantId":
        return oHostCredential.tenantId;
      case "aapOrganizationId":
        return oHostCredential.aapOrganizationId;
      case "credentialName":
        return oHostCredential.credentialName;
      case "credentialId":
        return oHostCredential.credentialId;
      case "credentialResource":
        return oHostCredential.credentialResource;
      case "credentialDescription":
        return oHostCredential.credentialDescription;
      case "aapCredentialId":
        return oHostCredential.aapCredentialId;
      case "aapCredentialTypeId":
        return oHostCredential.aapCredentialTypeId;
      case "userName":
        return oHostCredential.userName;
      case "password":
        return oHostCredential.password;
      case "becomeMethod":
        return oHostCredential.becomeMethod;
      case "becomePassword":
        return oHostCredential.becomePassword;
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
        o = relateHostCredential(v, val);
      else if(o instanceof BaseModel) {
        BaseModel baseModel = (BaseModel)o;
        o = baseModel.relateForClass(v, val);
      }
    }
    return o != null;
  }
  public Object relateHostCredential(String var, Object val) {
    HostCredential oHostCredential = (HostCredential)this;
    switch(var) {
      case "tenantResource":
        if(oHostCredential.getTenantResource() == null)
          oHostCredential.setTenantResource(Optional.ofNullable(val).map(v -> v.toString()).orElse(null));
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

  public static Object staticSetForClass(String entityVar, SiteRequest siteRequest_, String v, HostCredential o) {
    return staticSetHostCredential(entityVar,  siteRequest_, v, o);
  }
  public static Object staticSetHostCredential(String entityVar, SiteRequest siteRequest_, String v, HostCredential o) {
    switch(entityVar) {
    case "tenantResource":
      return HostCredential.staticSetTenantResource(siteRequest_, v);
    case "tenantId":
      return HostCredential.staticSetTenantId(siteRequest_, v);
    case "aapOrganizationId":
      return HostCredential.staticSetAapOrganizationId(siteRequest_, v);
    case "credentialName":
      return HostCredential.staticSetCredentialName(siteRequest_, v);
    case "credentialId":
      return HostCredential.staticSetCredentialId(siteRequest_, v);
    case "credentialResource":
      return HostCredential.staticSetCredentialResource(siteRequest_, v);
    case "credentialDescription":
      return HostCredential.staticSetCredentialDescription(siteRequest_, v);
    case "aapCredentialId":
      return HostCredential.staticSetAapCredentialId(siteRequest_, v);
    case "aapCredentialTypeId":
      return HostCredential.staticSetAapCredentialTypeId(siteRequest_, v);
    case "userName":
      return HostCredential.staticSetUserName(siteRequest_, v);
    case "password":
      return HostCredential.staticSetPassword(siteRequest_, v);
    case "becomeMethod":
      return HostCredential.staticSetBecomeMethod(siteRequest_, v);
    case "becomePassword":
      return HostCredential.staticSetBecomePassword(siteRequest_, v);
      default:
        return BaseModel.staticSetBaseModel(entityVar,  siteRequest_, v, o);
    }
  }

  //////////////////
  // staticSearch //
  //////////////////

  public static Future<HostCredential> fqHostCredential(SiteRequest siteRequest, String var, Object val) {
    Promise<HostCredential> promise = Promise.promise();
    try {
      if(val == null) {
        promise.complete();
      } else {
        SearchList<HostCredential> searchList = new SearchList<HostCredential>();
        searchList.setStore(true);
        searchList.q("*:*");
        searchList.setC(HostCredential.class);
        searchList.fq(String.format("%s:", HostCredential.varIndexedHostCredential(var)) + SearchTool.escapeQueryChars(val.toString()));
        searchList.promiseDeepForClass(siteRequest).onSuccess(a -> {
          try {
            promise.complete(searchList.getList().stream().findFirst().orElse(null));
          } catch(Throwable ex) {
            LOG.error("Error while querying thehost credential", ex);
            promise.fail(ex);
          }
        }).onFailure(ex -> {
          LOG.error("Error while querying thehost credential", ex);
          promise.fail(ex);
        });
      }
    } catch(Throwable ex) {
      LOG.error("Error while querying thehost credential", ex);
      promise.fail(ex);
    }
    return promise.future();
  }

  public static Object staticSearchForClass(String entityVar, SiteRequest siteRequest_, Object o) {
    return staticSearchHostCredential(entityVar,  siteRequest_, o);
  }
  public static Object staticSearchHostCredential(String entityVar, SiteRequest siteRequest_, Object o) {
    switch(entityVar) {
    case "tenantResource":
      return HostCredential.staticSearchTenantResource(siteRequest_, (String)o);
    case "tenantId":
      return HostCredential.staticSearchTenantId(siteRequest_, (String)o);
    case "aapOrganizationId":
      return HostCredential.staticSearchAapOrganizationId(siteRequest_, (Long)o);
    case "credentialName":
      return HostCredential.staticSearchCredentialName(siteRequest_, (String)o);
    case "credentialId":
      return HostCredential.staticSearchCredentialId(siteRequest_, (String)o);
    case "credentialResource":
      return HostCredential.staticSearchCredentialResource(siteRequest_, (String)o);
    case "credentialDescription":
      return HostCredential.staticSearchCredentialDescription(siteRequest_, (String)o);
    case "aapCredentialId":
      return HostCredential.staticSearchAapCredentialId(siteRequest_, (Long)o);
    case "aapCredentialTypeId":
      return HostCredential.staticSearchAapCredentialTypeId(siteRequest_, (Long)o);
    case "userName":
      return HostCredential.staticSearchUserName(siteRequest_, (String)o);
    case "password":
      return HostCredential.staticSearchPassword(siteRequest_, (String)o);
    case "becomeMethod":
      return HostCredential.staticSearchBecomeMethod(siteRequest_, (String)o);
    case "becomePassword":
      return HostCredential.staticSearchBecomePassword(siteRequest_, (String)o);
      default:
        return BaseModel.staticSearchBaseModel(entityVar,  siteRequest_, o);
    }
  }

  ///////////////////
  // staticSearchStr //
  ///////////////////

  public static String staticSearchStrForClass(String entityVar, SiteRequest siteRequest_, Object o) {
    return staticSearchStrHostCredential(entityVar,  siteRequest_, o);
  }
  public static String staticSearchStrHostCredential(String entityVar, SiteRequest siteRequest_, Object o) {
    switch(entityVar) {
    case "tenantResource":
      return HostCredential.staticSearchStrTenantResource(siteRequest_, (String)o);
    case "tenantId":
      return HostCredential.staticSearchStrTenantId(siteRequest_, (String)o);
    case "aapOrganizationId":
      return HostCredential.staticSearchStrAapOrganizationId(siteRequest_, (Long)o);
    case "credentialName":
      return HostCredential.staticSearchStrCredentialName(siteRequest_, (String)o);
    case "credentialId":
      return HostCredential.staticSearchStrCredentialId(siteRequest_, (String)o);
    case "credentialResource":
      return HostCredential.staticSearchStrCredentialResource(siteRequest_, (String)o);
    case "credentialDescription":
      return HostCredential.staticSearchStrCredentialDescription(siteRequest_, (String)o);
    case "aapCredentialId":
      return HostCredential.staticSearchStrAapCredentialId(siteRequest_, (Long)o);
    case "aapCredentialTypeId":
      return HostCredential.staticSearchStrAapCredentialTypeId(siteRequest_, (Long)o);
    case "userName":
      return HostCredential.staticSearchStrUserName(siteRequest_, (String)o);
    case "password":
      return HostCredential.staticSearchStrPassword(siteRequest_, (String)o);
    case "becomeMethod":
      return HostCredential.staticSearchStrBecomeMethod(siteRequest_, (String)o);
    case "becomePassword":
      return HostCredential.staticSearchStrBecomePassword(siteRequest_, (String)o);
      default:
        return BaseModel.staticSearchStrBaseModel(entityVar,  siteRequest_, o);
    }
  }

  //////////////////
  // staticSearchFq //
  //////////////////

  public static String staticSearchFqForClass(String entityVar, SiteRequest siteRequest_, String o) {
    return staticSearchFqHostCredential(entityVar,  siteRequest_, o);
  }
  public static String staticSearchFqHostCredential(String entityVar, SiteRequest siteRequest_, String o) {
    switch(entityVar) {
    case "tenantResource":
      return HostCredential.staticSearchFqTenantResource(siteRequest_, o);
    case "tenantId":
      return HostCredential.staticSearchFqTenantId(siteRequest_, o);
    case "aapOrganizationId":
      return HostCredential.staticSearchFqAapOrganizationId(siteRequest_, o);
    case "credentialName":
      return HostCredential.staticSearchFqCredentialName(siteRequest_, o);
    case "credentialId":
      return HostCredential.staticSearchFqCredentialId(siteRequest_, o);
    case "credentialResource":
      return HostCredential.staticSearchFqCredentialResource(siteRequest_, o);
    case "credentialDescription":
      return HostCredential.staticSearchFqCredentialDescription(siteRequest_, o);
    case "aapCredentialId":
      return HostCredential.staticSearchFqAapCredentialId(siteRequest_, o);
    case "aapCredentialTypeId":
      return HostCredential.staticSearchFqAapCredentialTypeId(siteRequest_, o);
    case "userName":
      return HostCredential.staticSearchFqUserName(siteRequest_, o);
    case "password":
      return HostCredential.staticSearchFqPassword(siteRequest_, o);
    case "becomeMethod":
      return HostCredential.staticSearchFqBecomeMethod(siteRequest_, o);
    case "becomePassword":
      return HostCredential.staticSearchFqBecomePassword(siteRequest_, o);
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
          o = persistHostCredential(v, val);
        else if(o instanceof BaseModel) {
          BaseModel oBaseModel = (BaseModel)o;
          o = oBaseModel.persistForClass(v, val);
        }
      }
    }
    return o != null;
  }
  public Object persistHostCredential(String var, Object val) {
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
      } else if("credentialname".equals(varLower)) {
        if(val instanceof String) {
          setCredentialName((String)val);
        }
        saves.add("credentialName");
        return val;
      } else if("credentialid".equals(varLower)) {
        if(val instanceof String) {
          setCredentialId((String)val);
        }
        saves.add("credentialId");
        return val;
      } else if("credentialresource".equals(varLower)) {
        if(val instanceof String) {
          setCredentialResource((String)val);
        }
        saves.add("credentialResource");
        return val;
      } else if("credentialdescription".equals(varLower)) {
        if(val instanceof String) {
          setCredentialDescription((String)val);
        }
        saves.add("credentialDescription");
        return val;
      } else if("aapcredentialid".equals(varLower)) {
        if(val instanceof Long) {
          setAapCredentialId((Long)val);
        } else {
          setAapCredentialId(val == null ? null : val.toString());
        }
        saves.add("aapCredentialId");
        return val;
      } else if("aapcredentialtypeid".equals(varLower)) {
        if(val instanceof Long) {
          setAapCredentialTypeId((Long)val);
        } else {
          setAapCredentialTypeId(val == null ? null : val.toString());
        }
        saves.add("aapCredentialTypeId");
        return val;
      } else if("username".equals(varLower)) {
        if(val instanceof String) {
          setUserName((String)val);
        }
        saves.add("userName");
        return val;
      } else if("password".equals(varLower)) {
        if(val instanceof String) {
          setPassword((String)val);
        }
        saves.add("password");
        return val;
      } else if("becomemethod".equals(varLower)) {
        if(val instanceof String) {
          setBecomeMethod((String)val);
        }
        saves.add("becomeMethod");
        return val;
      } else if("becomepassword".equals(varLower)) {
        if(val instanceof String) {
          setBecomePassword((String)val);
        }
        saves.add("becomePassword");
        return val;
    } else {
      return super.persistBaseModel(var, val);
    }
  }

  /////////////
  // populate //
  /////////////

  @Override public void populateForClass(SolrResponse.Doc doc) {
    populateHostCredential(doc);
  }
  public void populateHostCredential(SolrResponse.Doc doc) {
    HostCredential oHostCredential = (HostCredential)this;
    saves = Optional.ofNullable((ArrayList<String>)doc.get("saves_docvalues_strings")).orElse(new ArrayList<String>());
    if(saves != null) {

      String tenantResource = (String)doc.get("tenantResource_docvalues_string");
      if(tenantResource != null)
        oHostCredential.setTenantResource(tenantResource);

      if(saves.contains("tenantId")) {
        String tenantId = (String)doc.get("tenantId_docvalues_string");
        if(tenantId != null)
          oHostCredential.setTenantId(tenantId);
      }

      if(saves.contains("aapOrganizationId")) {
        Long aapOrganizationId = (Long)doc.get("aapOrganizationId_docvalues_long");
        if(aapOrganizationId != null)
          oHostCredential.setAapOrganizationId(aapOrganizationId);
      }

      if(saves.contains("credentialName")) {
        String credentialName = (String)doc.get("credentialName_docvalues_string");
        if(credentialName != null)
          oHostCredential.setCredentialName(credentialName);
      }

      if(saves.contains("credentialId")) {
        String credentialId = (String)doc.get("credentialId_docvalues_string");
        if(credentialId != null)
          oHostCredential.setCredentialId(credentialId);
      }

      if(saves.contains("credentialResource")) {
        String credentialResource = (String)doc.get("credentialResource_docvalues_string");
        if(credentialResource != null)
          oHostCredential.setCredentialResource(credentialResource);
      }

      if(saves.contains("credentialDescription")) {
        String credentialDescription = (String)doc.get("credentialDescription_docvalues_string");
        if(credentialDescription != null)
          oHostCredential.setCredentialDescription(credentialDescription);
      }

      if(saves.contains("aapCredentialId")) {
        Long aapCredentialId = (Long)doc.get("aapCredentialId_docvalues_long");
        if(aapCredentialId != null)
          oHostCredential.setAapCredentialId(aapCredentialId);
      }

      if(saves.contains("aapCredentialTypeId")) {
        Long aapCredentialTypeId = (Long)doc.get("aapCredentialTypeId_docvalues_long");
        if(aapCredentialTypeId != null)
          oHostCredential.setAapCredentialTypeId(aapCredentialTypeId);
      }

      if(saves.contains("userName")) {
        String userName = (String)doc.get("userName_docvalues_string");
        if(userName != null)
          oHostCredential.setUserName(userName);
      }

      if(saves.contains("password")) {
        String password = (String)doc.get("password_docvalues_string");
        if(password != null)
          oHostCredential.setPassword(password);
      }

      if(saves.contains("becomeMethod")) {
        String becomeMethod = (String)doc.get("becomeMethod_docvalues_string");
        if(becomeMethod != null)
          oHostCredential.setBecomeMethod(becomeMethod);
      }

      if(saves.contains("becomePassword")) {
        String becomePassword = (String)doc.get("becomePassword_docvalues_string");
        if(becomePassword != null)
          oHostCredential.setBecomePassword(becomePassword);
      }
    }

    super.populateBaseModel(doc);
  }

  public void indexHostCredential(JsonObject doc) {
    if(tenantResource != null) {
      doc.put("tenantResource_docvalues_string", tenantResource);
    }
    if(tenantId != null) {
      doc.put("tenantId_docvalues_string", tenantId);
    }
    if(aapOrganizationId != null) {
      doc.put("aapOrganizationId_docvalues_long", aapOrganizationId);
    }
    if(credentialName != null) {
      doc.put("credentialName_docvalues_string", credentialName);
    }
    if(credentialId != null) {
      doc.put("credentialId_docvalues_string", credentialId);
    }
    if(credentialResource != null) {
      doc.put("credentialResource_docvalues_string", credentialResource);
    }
    if(credentialDescription != null) {
      doc.put("credentialDescription_docvalues_string", credentialDescription);
    }
    if(aapCredentialId != null) {
      doc.put("aapCredentialId_docvalues_long", aapCredentialId);
    }
    if(aapCredentialTypeId != null) {
      doc.put("aapCredentialTypeId_docvalues_long", aapCredentialTypeId);
    }
    if(userName != null) {
      doc.put("userName_docvalues_string", userName);
    }
    if(password != null) {
      doc.put("password_docvalues_string", password);
    }
    if(becomeMethod != null) {
      doc.put("becomeMethod_docvalues_string", becomeMethod);
    }
    if(becomePassword != null) {
      doc.put("becomePassword_docvalues_string", becomePassword);
    }
    super.indexBaseModel(doc);

	}

  public static String varStoredHostCredential(String entityVar) {
    switch(entityVar) {
      case "tenantResource":
        return "tenantResource_docvalues_string";
      case "tenantId":
        return "tenantId_docvalues_string";
      case "aapOrganizationId":
        return "aapOrganizationId_docvalues_long";
      case "credentialName":
        return "credentialName_docvalues_string";
      case "credentialId":
        return "credentialId_docvalues_string";
      case "credentialResource":
        return "credentialResource_docvalues_string";
      case "credentialDescription":
        return "credentialDescription_docvalues_string";
      case "aapCredentialId":
        return "aapCredentialId_docvalues_long";
      case "aapCredentialTypeId":
        return "aapCredentialTypeId_docvalues_long";
      case "userName":
        return "userName_docvalues_string";
      case "password":
        return "password_docvalues_string";
      case "becomeMethod":
        return "becomeMethod_docvalues_string";
      case "becomePassword":
        return "becomePassword_docvalues_string";
      default:
        return BaseModel.varStoredBaseModel(entityVar);
    }
  }

  public static String varIndexedHostCredential(String entityVar) {
    switch(entityVar) {
      case "tenantResource":
        return "tenantResource_docvalues_string";
      case "tenantId":
        return "tenantId_docvalues_string";
      case "aapOrganizationId":
        return "aapOrganizationId_docvalues_long";
      case "credentialName":
        return "credentialName_docvalues_string";
      case "credentialId":
        return "credentialId_docvalues_string";
      case "credentialResource":
        return "credentialResource_docvalues_string";
      case "credentialDescription":
        return "credentialDescription_docvalues_string";
      case "aapCredentialId":
        return "aapCredentialId_docvalues_long";
      case "aapCredentialTypeId":
        return "aapCredentialTypeId_docvalues_long";
      case "userName":
        return "userName_docvalues_string";
      case "password":
        return "password_docvalues_string";
      case "becomeMethod":
        return "becomeMethod_docvalues_string";
      case "becomePassword":
        return "becomePassword_docvalues_string";
      default:
        return BaseModel.varIndexedBaseModel(entityVar);
    }
  }

  public static String searchVarHostCredential(String searchVar) {
    switch(searchVar) {
      case "tenantResource_docvalues_string":
        return "tenantResource";
      case "tenantId_docvalues_string":
        return "tenantId";
      case "aapOrganizationId_docvalues_long":
        return "aapOrganizationId";
      case "credentialName_docvalues_string":
        return "credentialName";
      case "credentialId_docvalues_string":
        return "credentialId";
      case "credentialResource_docvalues_string":
        return "credentialResource";
      case "credentialDescription_docvalues_string":
        return "credentialDescription";
      case "aapCredentialId_docvalues_long":
        return "aapCredentialId";
      case "aapCredentialTypeId_docvalues_long":
        return "aapCredentialTypeId";
      case "userName_docvalues_string":
        return "userName";
      case "password_docvalues_string":
        return "password";
      case "becomeMethod_docvalues_string":
        return "becomeMethod";
      case "becomePassword_docvalues_string":
        return "becomePassword";
      default:
        return BaseModel.searchVarBaseModel(searchVar);
    }
  }

  public static String varSearchHostCredential(String entityVar) {
    switch(entityVar) {
      default:
        return BaseModel.varSearchBaseModel(entityVar);
    }
  }

  public static String varSuggestedHostCredential(String entityVar) {
    switch(entityVar) {
      default:
        return BaseModel.varSuggestedBaseModel(entityVar);
    }
  }

  /////////////
  // store //
  /////////////

  @Override public void storeForClass(SolrResponse.Doc doc) {
    storeHostCredential(doc);
  }
  public void storeHostCredential(SolrResponse.Doc doc) {
    HostCredential oHostCredential = (HostCredential)this;
    SiteRequest siteRequest = oHostCredential.getSiteRequest_();

    oHostCredential.setTenantResource(Optional.ofNullable(doc.get("tenantResource_docvalues_string")).map(v -> v.toString()).orElse(null));
    oHostCredential.setTenantId(Optional.ofNullable(doc.get("tenantId_docvalues_string")).map(v -> v.toString()).orElse(null));
    oHostCredential.setAapOrganizationId(Optional.ofNullable(doc.get("aapOrganizationId_docvalues_long")).map(v -> v.toString()).orElse(null));
    oHostCredential.setCredentialName(Optional.ofNullable(doc.get("credentialName_docvalues_string")).map(v -> v.toString()).orElse(null));
    oHostCredential.setCredentialId(Optional.ofNullable(doc.get("credentialId_docvalues_string")).map(v -> v.toString()).orElse(null));
    oHostCredential.setCredentialResource(Optional.ofNullable(doc.get("credentialResource_docvalues_string")).map(v -> v.toString()).orElse(null));
    oHostCredential.setCredentialDescription(Optional.ofNullable(doc.get("credentialDescription_docvalues_string")).map(v -> v.toString()).orElse(null));
    oHostCredential.setAapCredentialId(Optional.ofNullable(doc.get("aapCredentialId_docvalues_long")).map(v -> v.toString()).orElse(null));
    oHostCredential.setAapCredentialTypeId(Optional.ofNullable(doc.get("aapCredentialTypeId_docvalues_long")).map(v -> v.toString()).orElse(null));
    oHostCredential.setUserName(Optional.ofNullable(doc.get("userName_docvalues_string")).map(v -> v.toString()).orElse(null));
    oHostCredential.setPassword(Optional.ofNullable(doc.get("password_docvalues_string")).map(v -> v.toString()).orElse(null));
    oHostCredential.setBecomeMethod(Optional.ofNullable(doc.get("becomeMethod_docvalues_string")).map(v -> v.toString()).orElse(null));
    oHostCredential.setBecomePassword(Optional.ofNullable(doc.get("becomePassword_docvalues_string")).map(v -> v.toString()).orElse(null));

    super.storeBaseModel(doc);
  }

  //////////////////
  // apiRequest //
  //////////////////

  public void apiRequestHostCredential() {
    ApiRequest apiRequest = Optional.ofNullable(siteRequest_).map(r -> r.getApiRequest_()).orElse(null);
    Object o = Optional.ofNullable(apiRequest).map(ApiRequest::getOriginal).orElse(null);
    if(o != null && o instanceof HostCredential) {
      HostCredential original = (HostCredential)o;
      if(!Objects.equals(tenantResource, original.getTenantResource()))
        apiRequest.addVars("tenantResource");
      if(!Objects.equals(tenantId, original.getTenantId()))
        apiRequest.addVars("tenantId");
      if(!Objects.equals(aapOrganizationId, original.getAapOrganizationId()))
        apiRequest.addVars("aapOrganizationId");
      if(!Objects.equals(credentialName, original.getCredentialName()))
        apiRequest.addVars("credentialName");
      if(!Objects.equals(credentialId, original.getCredentialId()))
        apiRequest.addVars("credentialId");
      if(!Objects.equals(credentialResource, original.getCredentialResource()))
        apiRequest.addVars("credentialResource");
      if(!Objects.equals(credentialDescription, original.getCredentialDescription()))
        apiRequest.addVars("credentialDescription");
      if(!Objects.equals(aapCredentialId, original.getAapCredentialId()))
        apiRequest.addVars("aapCredentialId");
      if(!Objects.equals(aapCredentialTypeId, original.getAapCredentialTypeId()))
        apiRequest.addVars("aapCredentialTypeId");
      if(!Objects.equals(userName, original.getUserName()))
        apiRequest.addVars("userName");
      if(!Objects.equals(password, original.getPassword()))
        apiRequest.addVars("password");
      if(!Objects.equals(becomeMethod, original.getBecomeMethod()))
        apiRequest.addVars("becomeMethod");
      if(!Objects.equals(becomePassword, original.getBecomePassword()))
        apiRequest.addVars("becomePassword");
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
    sb.append(Optional.ofNullable(credentialName).map(v -> "credentialName: \"" + v + "\"\n" ).orElse(""));
    sb.append(Optional.ofNullable(credentialId).map(v -> "credentialId: \"" + v + "\"\n" ).orElse(""));
    sb.append(Optional.ofNullable(credentialResource).map(v -> "credentialResource: \"" + v + "\"\n" ).orElse(""));
    sb.append(Optional.ofNullable(credentialDescription).map(v -> "credentialDescription: \"" + v + "\"\n" ).orElse(""));
    sb.append(Optional.ofNullable(aapCredentialId).map(v -> "aapCredentialId: " + v + "\n").orElse(""));
    sb.append(Optional.ofNullable(aapCredentialTypeId).map(v -> "aapCredentialTypeId: " + v + "\n").orElse(""));
    sb.append(Optional.ofNullable(userName).map(v -> "userName: \"" + v + "\"\n" ).orElse(""));
    sb.append(Optional.ofNullable(password).map(v -> "password: \"" + v + "\"\n" ).orElse(""));
    sb.append(Optional.ofNullable(becomeMethod).map(v -> "becomeMethod: \"" + v + "\"\n" ).orElse(""));
    sb.append(Optional.ofNullable(becomePassword).map(v -> "becomePassword: \"" + v + "\"\n" ).orElse(""));
    return sb.toString();
  }

  public static final String CLASS_SIMPLE_NAME = "HostCredential";
  public static final String CLASS_CANONICAL_NAME = "org.computate.dcm.model.eda.hostcredential.HostCredential";
  public static final String CLASS_AUTH_RESOURCE = "HOSTCREDENTIAL";
  public static final String CLASS_API_ADDRESS_HostCredential = "dcm-enUS-HostCredential";
  public static String getClassApiAddress() {
    return CLASS_API_ADDRESS_HostCredential;
  }
  public static final String VAR_tenantResource = "tenantResource";
  public static final String SET_tenantResource = "setTenantResource";
  public static final String VAR_tenantId = "tenantId";
  public static final String SET_tenantId = "setTenantId";
  public static final String VAR_aapOrganizationId = "aapOrganizationId";
  public static final String SET_aapOrganizationId = "setAapOrganizationId";
  public static final String VAR_credentialName = "credentialName";
  public static final String SET_credentialName = "setCredentialName";
  public static final String VAR_credentialId = "credentialId";
  public static final String SET_credentialId = "setCredentialId";
  public static final String VAR_credentialResource = "credentialResource";
  public static final String SET_credentialResource = "setCredentialResource";
  public static final String VAR_credentialDescription = "credentialDescription";
  public static final String SET_credentialDescription = "setCredentialDescription";
  public static final String VAR_aapCredentialId = "aapCredentialId";
  public static final String SET_aapCredentialId = "setAapCredentialId";
  public static final String VAR_aapCredentialTypeId = "aapCredentialTypeId";
  public static final String SET_aapCredentialTypeId = "setAapCredentialTypeId";
  public static final String VAR_userName = "userName";
  public static final String SET_userName = "setUserName";
  public static final String VAR_password = "password";
  public static final String SET_password = "setPassword";
  public static final String VAR_becomeMethod = "becomeMethod";
  public static final String SET_becomeMethod = "setBecomeMethod";
  public static final String VAR_becomePassword = "becomePassword";
  public static final String SET_becomePassword = "setBecomePassword";

  public static List<String> varsQForClass() {
    return HostCredential.varsQHostCredential(new ArrayList<String>());
  }
  public static List<String> varsQHostCredential(List<String> vars) {
    BaseModel.varsQBaseModel(vars);
    return vars;
  }

  public static List<String> varsFqForClass() {
    return HostCredential.varsFqHostCredential(new ArrayList<String>());
  }
  public static List<String> varsFqHostCredential(List<String> vars) {
    vars.add(VAR_tenantId);
    BaseModel.varsFqBaseModel(vars);
    return vars;
  }

  public static List<String> varsRangeForClass() {
    return HostCredential.varsRangeHostCredential(new ArrayList<String>());
  }
  public static List<String> varsRangeHostCredential(List<String> vars) {
    BaseModel.varsRangeBaseModel(vars);
    return vars;
  }

  public static final String DISPLAY_NAME_tenantResource = "tenant auth resource";
  public static final String DISPLAY_NAME_tenantId = "tenant ID";
  public static final String DISPLAY_NAME_aapOrganizationId = "AAP ID";
  public static final String DISPLAY_NAME_credentialName = "credential name";
  public static final String DISPLAY_NAME_credentialId = "credential ID";
  public static final String DISPLAY_NAME_credentialResource = "credential resource";
  public static final String DISPLAY_NAME_credentialDescription = "credential description";
  public static final String DISPLAY_NAME_aapCredentialId = "AAP ID";
  public static final String DISPLAY_NAME_aapCredentialTypeId = "AAP credential type";
  public static final String DISPLAY_NAME_userName = "user name";
  public static final String DISPLAY_NAME_password = "password";
  public static final String DISPLAY_NAME_becomeMethod = "become method";
  public static final String DISPLAY_NAME_becomePassword = "become password";

  @Override
  public String idForClass() {
    return credentialResource;
  }

  @Override
  public String titleForClass() {
    return objectTitle;
  }

  @Override
  public String nameForClass() {
    return credentialName;
  }

  @Override
  public String classNameAdjectiveSingularForClass() {
    return HostCredential.NameAdjectiveSingular_enUS;
  }

  @Override
  public String descriptionForClass() {
    return credentialDescription;
  }

  @Override
  public String enUSStringFormatUrlEditPageForClass() {
    return "%s/en-us/edit/host-credential/%s";
  }

  @Override
  public String enUSStringFormatUrlUserPageForClass() {
    return "%s/en-us/user/host-credential/%s";
  }

  public static String varJsonForClass(String var, Boolean patch) {
    return HostCredential.varJsonHostCredential(var, patch);
  }
  public static String varJsonHostCredential(String var, Boolean patch) {
    switch(var) {
    case VAR_tenantResource:
      return patch ? SET_tenantResource : VAR_tenantResource;
    case VAR_tenantId:
      return patch ? SET_tenantId : VAR_tenantId;
    case VAR_aapOrganizationId:
      return patch ? SET_aapOrganizationId : VAR_aapOrganizationId;
    case VAR_credentialName:
      return patch ? SET_credentialName : VAR_credentialName;
    case VAR_credentialId:
      return patch ? SET_credentialId : VAR_credentialId;
    case VAR_credentialResource:
      return patch ? SET_credentialResource : VAR_credentialResource;
    case VAR_credentialDescription:
      return patch ? SET_credentialDescription : VAR_credentialDescription;
    case VAR_aapCredentialId:
      return patch ? SET_aapCredentialId : VAR_aapCredentialId;
    case VAR_aapCredentialTypeId:
      return patch ? SET_aapCredentialTypeId : VAR_aapCredentialTypeId;
    case VAR_userName:
      return patch ? SET_userName : VAR_userName;
    case VAR_password:
      return patch ? SET_password : VAR_password;
    case VAR_becomeMethod:
      return patch ? SET_becomeMethod : VAR_becomeMethod;
    case VAR_becomePassword:
      return patch ? SET_becomePassword : VAR_becomePassword;
    default:
      return BaseModel.varJsonBaseModel(var, patch);
    }
  }

  public static String displayNameForClass(String var) {
    return HostCredential.displayNameHostCredential(var);
  }
  public static String displayNameHostCredential(String var) {
    switch(var) {
    case VAR_tenantResource:
      return DISPLAY_NAME_tenantResource;
    case VAR_tenantId:
      return DISPLAY_NAME_tenantId;
    case VAR_aapOrganizationId:
      return DISPLAY_NAME_aapOrganizationId;
    case VAR_credentialName:
      return DISPLAY_NAME_credentialName;
    case VAR_credentialId:
      return DISPLAY_NAME_credentialId;
    case VAR_credentialResource:
      return DISPLAY_NAME_credentialResource;
    case VAR_credentialDescription:
      return DISPLAY_NAME_credentialDescription;
    case VAR_aapCredentialId:
      return DISPLAY_NAME_aapCredentialId;
    case VAR_aapCredentialTypeId:
      return DISPLAY_NAME_aapCredentialTypeId;
    case VAR_userName:
      return DISPLAY_NAME_userName;
    case VAR_password:
      return DISPLAY_NAME_password;
    case VAR_becomeMethod:
      return DISPLAY_NAME_becomeMethod;
    case VAR_becomePassword:
      return DISPLAY_NAME_becomePassword;
    default:
      return BaseModel.displayNameBaseModel(var);
    }
  }

  public static String descriptionHostCredential(String var) {
    if(var == null)
      return null;
    switch(var) {
    case VAR_tenantResource:
      return "The unique authorization resource for the tenant for multi-tenancy";
    case VAR_tenantId:
      return "The ID of this tenant";
    case VAR_aapOrganizationId:
      return "The Ansible Automation Platform ID of the organization. ";
    case VAR_credentialName:
      return "The name of the credential in AAP. ";
    case VAR_credentialId:
      return "The ID of the credential in DCM. ";
    case VAR_credentialResource:
      return "The unique authorization resource for the credential for multi-tenancy";
    case VAR_credentialDescription:
      return "The description of the credential in AAP. ";
    case VAR_aapCredentialId:
      return "The Ansible Automation Platform ID of the credential. ";
    case VAR_aapCredentialTypeId:
      return "The Ansible Automation Platform credential type ID. ";
    case VAR_userName:
      return "The Ansible Automation Platform user name to connect to hosts. ";
    case VAR_password:
      return "The Ansible Automation Platform password to connect to hosts. ";
    case VAR_becomeMethod:
      return "The Ansible Automation Platform become method to run privileged commands on hosts. ";
    case VAR_becomePassword:
      return "The Ansible Automation become password to run privileged commands on hosts. ";
      default:
        return BaseModel.descriptionBaseModel(var);
    }
  }

  public static String classSimpleNameHostCredential(String var) {
    switch(var) {
    case VAR_tenantResource:
      return "String";
    case VAR_tenantId:
      return "String";
    case VAR_aapOrganizationId:
      return "Long";
    case VAR_credentialName:
      return "String";
    case VAR_credentialId:
      return "String";
    case VAR_credentialResource:
      return "String";
    case VAR_credentialDescription:
      return "String";
    case VAR_aapCredentialId:
      return "Long";
    case VAR_aapCredentialTypeId:
      return "Long";
    case VAR_userName:
      return "String";
    case VAR_password:
      return "String";
    case VAR_becomeMethod:
      return "String";
    case VAR_becomePassword:
      return "String";
      default:
        return BaseModel.classSimpleNameBaseModel(var);
    }
  }

  public static Integer htmColumnHostCredential(String var) {
    switch(var) {
    case VAR_tenantResource:
      return 0;
    case VAR_credentialName:
      return 1;
    case VAR_credentialDescription:
      return 2;
      default:
        return BaseModel.htmColumnBaseModel(var);
    }
  }

  public static Integer htmRowHostCredential(String var) {
    switch(var) {
    case VAR_tenantResource:
      return 3;
    case VAR_credentialName:
      return 4;
    case VAR_credentialDescription:
      return 4;
    case VAR_userName:
      return 4;
    case VAR_password:
      return 4;
    case VAR_becomeMethod:
      return 4;
    case VAR_becomePassword:
      return 4;
      default:
        return BaseModel.htmRowBaseModel(var);
    }
  }

  public static Integer htmCellHostCredential(String var) {
    switch(var) {
    case VAR_tenantResource:
      return 0;
    case VAR_credentialName:
      return 0;
    case VAR_credentialDescription:
      return 1;
    case VAR_userName:
      return 2;
    case VAR_password:
      return 3;
    case VAR_becomeMethod:
      return 4;
    case VAR_becomePassword:
      return 2;
      default:
        return BaseModel.htmCellBaseModel(var);
    }
  }

  public static Integer lengthMinHostCredential(String var) {
    switch(var) {
      default:
        return BaseModel.lengthMinBaseModel(var);
    }
  }

  public static Integer lengthMaxHostCredential(String var) {
    switch(var) {
      default:
        return BaseModel.lengthMaxBaseModel(var);
    }
  }

  public static Integer maxHostCredential(String var) {
    switch(var) {
      default:
        return BaseModel.maxBaseModel(var);
    }
  }

  public static Integer minHostCredential(String var) {
    switch(var) {
      default:
        return BaseModel.minBaseModel(var);
    }
  }
}
