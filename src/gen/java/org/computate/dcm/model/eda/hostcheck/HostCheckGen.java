package org.computate.dcm.model.eda.hostcheck;

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
import org.computate.dcm.model.eda.tenant.Tenant;
import java.lang.Integer;
import java.lang.Boolean;
import io.vertx.core.json.JsonArray;
import org.computate.search.wrap.Wrap;
import io.vertx.core.Promise;
import io.vertx.core.Future;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.computate.search.response.solr.SolrResponse;

/**
 * <ol>
<h3>Suggestions that can generate more code for you: </h3> * </ol>
 * <li>You can add a class comment "{@inheritDoc}" if you wish to inherit the helpful inherited class comments from class HostCheckGen into the class HostCheck. 
 * </li>
 * <h3>About the HostCheck class and it's generated class HostCheckGen&lt;BaseModel&gt;: </h3>extends HostCheckGen
 * <p>
 * This Java class extends a generated Java class HostCheckGen built by the <a href="https://github.com/computate-org/computate">https://github.com/computate-org/computate</a> project. 
 * Whenever this Java class is modified or touched, the watch service installed as described in the README, indexes all the information about this Java class in a local Apache Solr Search Engine. 
 * If you are running the service, you can see the indexed data about this Java Class here: 
 * </p>
 * <p><a href="https://solr.apps-crc.testing/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.hostcheck.HostCheck">Find the class HostCheck in Solr. </a></p>
 * <p>
 * The extended class ending with "Gen" did not exist at first, but was automatically created by the same watch service based on the data retrieved from the local Apache Server search engine. 
 * The extended class contains many generated fields, getters, setters, initialization code, and helper methods to help build a website and API fast, reactive, and scalable. 
 * </p>
 * extends HostCheckGen<BaseModel>
 * <p>This <code>class HostCheck extends HostCheckGen&lt;BaseModel&gt;</code>, which means it extends a newly generated HostCheckGen. 
 * The generated <code>class HostCheckGen extends BaseModel</code> which means that HostCheck extends HostCheckGen which extends BaseModel. 
 * This generated inheritance is a powerful feature that allows a lot of boiler plate code to be created for you automatically while still preserving inheritance through the power of Java Generic classes. 
 * </p>
 * <h2>Api: true</h2>
 * <p>This class contains a comment <b>"Api: true"</b>, which means this class will have Java Vert.x API backend code generated for these objects. 
 * </p>
 * <h2>ApiTag.enUS: true</h2>
 * <p>This class contains a comment <b>"ApiTag: host checks"</b>, which groups all of the OpenAPIs for HostCheck objects under the tag "host checks". 
 * </p>
 * <h2>ApiUri.enUS: /en-us/api/host-check</h2>
 * <p>This class contains a comment <b>"ApiUri: /en-us/api/host-check"</b>, which defines the base API URI for HostCheck objects as "/en-us/api/host-check" in the OpenAPI spec. 
 * </p>
 * <h2>Color: null</h2>
 * <h2>Indexed: true</h2>
 * <p>This class contains a comment <b>"Indexed: true"</b>, which means this class will be indexed in the search engine. 
 * Every protected void method that begins with "_" that is marked to be searched with a comment like "Indexed: true", "Stored: true", or "DocValues: true" will be indexed in the search engine. 
 * </p>
 * <h2>{@inheritDoc}</h2>
 * <p>By adding a class comment "{@inheritDoc}", the HostCheck class will inherit the helpful inherited class comments from the super class HostCheckGen. 
 * </p>
 * <h2>Rows: 100</h2>
 * <p>This class contains a comment <b>"Rows: 100"</b>, which means the HostCheck API will return a default of 100 records instead of 10 by default. 
 * Each API has built in pagination of the search records to ensure a user can query all the data a page at a time without running the application out of memory. 
 * </p>
 * <h2>Order: 9</h2>
 * <p>This class contains a comment <b>"Order: 9"</b>, which means this class will be sorted by the given number 9 ascending when code that relates to multiple classes at the same time is generated. 
 * </p>
 * <h2>SqlOrder: 9</h2>
 * <p>This class contains a comment <b>"SqlOrder: 9"</b>, which means this class will be sorted by the given number 9 ascending when SQL code to create and drop the tables is generated. 
 * </p>
 * <h2>Model: true</h2>
 * <p>This class contains a comment <b>"Model: true"</b>, which means this class will be stored in the database. 
 * Every protected void method that begins with "_" that contains a "Persist: true" comment will be a persisted field in the database table. 
 * </p>
 * <h2>Page: true</h2>
 * <p>This class contains a comment <b>"Page: true"</b>, which means this class will have webpage code generated for these objects. 
 * Java Vert.x backend API code, Handlebars HTML template frontend code, and JavaScript code will all generated and can be extended. 
 * This creates a new Java class org.computate.dcm.model.eda.hostcheck.HostCheckPage. 
 * </p>
 * <h2>SuperPage.enUS: PageLayout</h2>
 * <p>This class contains a comment <b>"SuperPage.enUS: PageLayout"</b>, which identifies the Java super class of the page code by it's class simple name "PageLayout". 
 * This means that the newly created class org.computate.dcm.model.eda.hostcheck.HostCheckPage extends org.computate.dcm.page.PageLayout. 
 * </p>
 * <h2>Promise: true</h2>
 * <p>
 *   This class contains a comment <b>"Promise: true"</b>
 *   Sometimes a Java class must be initialized asynchronously when it involves calling a blocking API. 
 *   This means that the HostCheck Java class has promiseDeep methods which must be initialized asynchronously as a Vert.x Promise  instead of initDeep methods which are a simple non-asynchronous method. 
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
 * <h2>AName.enUS: a host check</h2>
 * <p>This class contains a comment <b>"AName.enUS: a host check"</b>, which identifies the language context to describe a HostCheck as "a host check". 
 * </p>
 * <p>
 * Delete the class HostCheck in Solr: 
 * <pre>
 * curl -k 'https://solr.apps-crc.testing/solr/computate/update?commitWithin=1000&amp;overwrite=true&amp;wt=json' -X POST -H 'Content-type: text/xml' -u "admin:$(oc -n solr get secret/solr-solrcloud-security-bootstrap -o jsonpath={.data.admin} | base64 -d)" --data-raw '&lt;delete&gt;&lt;query&gt;classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.hostcheck.HostCheck&lt;/query&gt;&lt;/delete&gt;'
 * </pre>
 * </p>
 * <p>
 * Delete  the package org.computate.dcm.model.eda.hostcheck in Solr: 
 * <pre>
 * curl -k 'https://solr.apps-crc.testing/solr/computate/update?commitWithin=1000&amp;overwrite=true&amp;wt=json' -X POST -H 'Content-type: text/xml' -u "admin:$(oc -n solr get secret/solr-solrcloud-security-bootstrap -o jsonpath={.data.admin} | base64 -d)" --data-raw '&lt;delete&gt;&lt;query&gt;classeNomEnsemble_enUS_indexed_string:org.computate.dcm.model.eda.hostcheck&lt;/query&gt;&lt;/delete&gt;'
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
public abstract class HostCheckGen<DEV> extends BaseModel {
  protected static final Logger LOG = LoggerFactory.getLogger(HostCheck.class);

  public static final String Description_enUS = "A check to be performed on a computer. ";
  public static final String AName_enUS = "a host check";
  public static final String This_enUS = "this ";
  public static final String ThisName_enUS = "this host check";
  public static final String A_enUS = "a ";
  public static final String TheName_enUS = "thehost check";
  public static final String SingularName_enUS = "host check";
  public static final String PluralName_enUS = "host checks";
  public static final String NameActual_enUS = "current host check";
  public static final String AllName_enUS = "all host checks";
  public static final String SearchAllNameBy_enUS = "search host checks by ";
  public static final String SearchAllName_enUS = "search host checks";
  public static final String Title_enUS = "host checks";
  public static final String ThePluralName_enUS = "the host checks";
  public static final String NoNameFound_enUS = "no host check found";
  public static final String ApiUri_enUS = "/en-us/api/host-check";
  public static final String ApiUriSearchPage_enUS = "/en-us/search/host-check";
  public static final String ApiUriEditPage_enUS = "/en-us/edit/host-check/{checkName}";
  public static final String OfName_enUS = "of host check";
  public static final String ANameAdjective_enUS = "an host check";
  public static final String NameAdjectiveSingular_enUS = "host check";
  public static final String NameAdjectivePlural_enUS = "host checks";
  public static final String Search_enUS_OpenApiUri = "/en-us/api/host-check";
  public static final String Search_enUS_StringFormatUri = "/en-us/api/host-check";
  public static final String Search_enUS_StringFormatUrl = "%s/en-us/api/host-check";
  public static final String GET_enUS_OpenApiUri = "/en-us/api/host-check/{checkName}";
  public static final String GET_enUS_StringFormatUri = "/en-us/api/host-check/%s";
  public static final String GET_enUS_StringFormatUrl = "%s/en-us/api/host-check/%s";
  public static final String PATCH_enUS_OpenApiUri = "/en-us/api/host-check";
  public static final String PATCH_enUS_StringFormatUri = "/en-us/api/host-check";
  public static final String PATCH_enUS_StringFormatUrl = "%s/en-us/api/host-check";
  public static final String POST_enUS_OpenApiUri = "/en-us/api/host-check";
  public static final String POST_enUS_StringFormatUri = "/en-us/api/host-check";
  public static final String POST_enUS_StringFormatUrl = "%s/en-us/api/host-check";
  public static final String DELETE_enUS_OpenApiUri = "/en-us/api/host-check/{checkName}";
  public static final String DELETE_enUS_StringFormatUri = "/en-us/api/host-check/%s";
  public static final String DELETE_enUS_StringFormatUrl = "%s/en-us/api/host-check/%s";
  public static final String PUTImport_enUS_OpenApiUri = "/en-us/api/host-check-import";
  public static final String PUTImport_enUS_StringFormatUri = "/en-us/api/host-check-import";
  public static final String PUTImport_enUS_StringFormatUrl = "%s/en-us/api/host-check-import";
  public static final String SearchPage_enUS_OpenApiUri = "/en-us/search/host-check";
  public static final String SearchPage_enUS_StringFormatUri = "/en-us/search/host-check";
  public static final String SearchPage_enUS_StringFormatUrl = "%s/en-us/search/host-check";
  public static final String EditPage_enUS_OpenApiUri = "/en-us/edit/host-check/{checkName}";
  public static final String EditPage_enUS_StringFormatUri = "/en-us/edit/host-check/%s";
  public static final String EditPage_enUS_StringFormatUrl = "%s/en-us/edit/host-check/%s";
  public static final String UserPage_enUS_OpenApiUri = "/en-us/user/host-check/{checkName}";
  public static final String UserPage_enUS_StringFormatUri = "/en-us/user/host-check/%s";
  public static final String UserPage_enUS_StringFormatUrl = "%s/en-us/user/host-check/%s";
  public static final String DELETEFilter_enUS_OpenApiUri = "/en-us/api/host-check";
  public static final String DELETEFilter_enUS_StringFormatUri = "/en-us/api/host-check";
  public static final String DELETEFilter_enUS_StringFormatUrl = "%s/en-us/api/host-check";

  public static final String Icon = "<i class=\"fa-duotone fa-regular fa-box-check\"></i>";
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
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.hostcheck.HostCheck&fq=entiteVar_enUS_indexed_string:tenantResource">Find the entity tenantResource in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _tenantResource(Wrap<String> w);

  public String getTenantResource() {
    return tenantResource;
  }
  public void setTenantResource(String o) {
    this.tenantResource = HostCheck.staticSetTenantResource(siteRequest_, o);
  }
  public static String staticSetTenantResource(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected HostCheck tenantResourceInit() {
    Wrap<String> tenantResourceWrap = new Wrap<String>().var("tenantResource");
    if(tenantResource == null) {
      _tenantResource(tenantResourceWrap);
      Optional.ofNullable(tenantResourceWrap.getO()).ifPresent(o -> {
        setTenantResource(o);
      });
    }
    return (HostCheck)this;
  }

  public static String staticSearchTenantResource(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrTenantResource(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqTenantResource(SiteRequest siteRequest_, String o) {
    return HostCheck.staticSearchTenantResource(siteRequest_, HostCheck.staticSetTenantResource(siteRequest_, o)).toString();
  }

  public String sqlTenantResource() {
    return tenantResource;
  }

  public static String staticJsonTenantResource(String tenantResource) {
    return tenantResource;
  }

	///////////////
  // checkName //
	///////////////


  /**
   *  The entity checkName
   *	 is defined as null before being initialized. 
   */
  @JsonProperty
  @JsonInclude(Include.NON_NULL)
  protected String checkName;

  /**
   * <br> The entity checkName
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.hostcheck.HostCheck&fq=entiteVar_enUS_indexed_string:checkName">Find the entity checkName in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _checkName(Wrap<String> w);

  public String getCheckName() {
    return checkName;
  }
  public void setCheckName(String o) {
    this.checkName = HostCheck.staticSetCheckName(siteRequest_, o);
  }
  public static String staticSetCheckName(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected HostCheck checkNameInit() {
    Wrap<String> checkNameWrap = new Wrap<String>().var("checkName");
    if(checkName == null) {
      _checkName(checkNameWrap);
      Optional.ofNullable(checkNameWrap.getO()).ifPresent(o -> {
        setCheckName(o);
      });
    }
    return (HostCheck)this;
  }

  public static String staticSearchCheckName(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrCheckName(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqCheckName(SiteRequest siteRequest_, String o) {
    return HostCheck.staticSearchCheckName(siteRequest_, HostCheck.staticSetCheckName(siteRequest_, o)).toString();
  }

  public String sqlCheckName() {
    return checkName;
  }

  public static String staticJsonCheckName(String checkName) {
    return checkName;
  }

	////////////////////
  // checkNamespace //
	////////////////////


  /**
   *  The entity checkNamespace
   *	 is defined as null before being initialized. 
   */
  @JsonProperty
  @JsonInclude(Include.NON_NULL)
  protected String checkNamespace;

  /**
   * <br> The entity checkNamespace
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.hostcheck.HostCheck&fq=entiteVar_enUS_indexed_string:checkNamespace">Find the entity checkNamespace in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _checkNamespace(Wrap<String> w);

  public String getCheckNamespace() {
    return checkNamespace;
  }
  public void setCheckNamespace(String o) {
    this.checkNamespace = HostCheck.staticSetCheckNamespace(siteRequest_, o);
  }
  public static String staticSetCheckNamespace(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected HostCheck checkNamespaceInit() {
    Wrap<String> checkNamespaceWrap = new Wrap<String>().var("checkNamespace");
    if(checkNamespace == null) {
      _checkNamespace(checkNamespaceWrap);
      Optional.ofNullable(checkNamespaceWrap.getO()).ifPresent(o -> {
        setCheckNamespace(o);
      });
    }
    return (HostCheck)this;
  }

  public static String staticSearchCheckNamespace(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrCheckNamespace(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqCheckNamespace(SiteRequest siteRequest_, String o) {
    return HostCheck.staticSearchCheckNamespace(siteRequest_, HostCheck.staticSetCheckNamespace(siteRequest_, o)).toString();
  }

  public String sqlCheckNamespace() {
    return checkNamespace;
  }

  public static String staticJsonCheckNamespace(String checkNamespace) {
    return checkNamespace;
  }

	//////////////////
  // checkCommand //
	//////////////////


  /**
   *  The entity checkCommand
   *	 is defined as null before being initialized. 
   */
  @JsonProperty
  @JsonInclude(Include.NON_NULL)
  protected String checkCommand;

  /**
   * <br> The entity checkCommand
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.hostcheck.HostCheck&fq=entiteVar_enUS_indexed_string:checkCommand">Find the entity checkCommand in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _checkCommand(Wrap<String> w);

  public String getCheckCommand() {
    return checkCommand;
  }
  public void setCheckCommand(String o) {
    this.checkCommand = HostCheck.staticSetCheckCommand(siteRequest_, o);
  }
  public static String staticSetCheckCommand(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected HostCheck checkCommandInit() {
    Wrap<String> checkCommandWrap = new Wrap<String>().var("checkCommand");
    if(checkCommand == null) {
      _checkCommand(checkCommandWrap);
      Optional.ofNullable(checkCommandWrap.getO()).ifPresent(o -> {
        setCheckCommand(o);
      });
    }
    return (HostCheck)this;
  }

  public static String staticSearchCheckCommand(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrCheckCommand(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqCheckCommand(SiteRequest siteRequest_, String o) {
    return HostCheck.staticSearchCheckCommand(siteRequest_, HostCheck.staticSetCheckCommand(siteRequest_, o)).toString();
  }

  public String sqlCheckCommand() {
    return checkCommand;
  }

  public static String staticJsonCheckCommand(String checkCommand) {
    return checkCommand;
  }

	///////////////////
  // checkInterval //
	///////////////////


  /**
   *  The entity checkInterval
   *	 is defined as null before being initialized. 
   */
  @JsonProperty
  @JsonSerialize(using = ToStringSerializer.class)
  @JsonInclude(Include.NON_NULL)
  protected Integer checkInterval;

  /**
   * <br> The entity checkInterval
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.hostcheck.HostCheck&fq=entiteVar_enUS_indexed_string:checkInterval">Find the entity checkInterval in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _checkInterval(Wrap<Integer> w);

  public Integer getCheckInterval() {
    return checkInterval;
  }

  public void setCheckInterval(Integer checkInterval) {
    this.checkInterval = checkInterval;
  }
  @JsonIgnore
  public void setCheckInterval(String o) {
    this.checkInterval = HostCheck.staticSetCheckInterval(siteRequest_, o);
  }
  public static Integer staticSetCheckInterval(SiteRequest siteRequest_, String o) {
    if(NumberUtils.isParsable(o))
      return Integer.parseInt(o);
    return null;
  }
  protected HostCheck checkIntervalInit() {
    Wrap<Integer> checkIntervalWrap = new Wrap<Integer>().var("checkInterval");
    if(checkInterval == null) {
      _checkInterval(checkIntervalWrap);
      Optional.ofNullable(checkIntervalWrap.getO()).ifPresent(o -> {
        setCheckInterval(o);
      });
    }
    return (HostCheck)this;
  }

  public static Integer staticSearchCheckInterval(SiteRequest siteRequest_, Integer o) {
    return o;
  }

  public static String staticSearchStrCheckInterval(SiteRequest siteRequest_, Integer o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqCheckInterval(SiteRequest siteRequest_, String o) {
    return HostCheck.staticSearchCheckInterval(siteRequest_, HostCheck.staticSetCheckInterval(siteRequest_, o)).toString();
  }

  public Integer sqlCheckInterval() {
    return checkInterval;
  }

  public static String staticJsonCheckInterval(Integer checkInterval) {
    return Optional.ofNullable(checkInterval).map(v -> v.toString()).orElse(null);
  }

	////////////////////
  // checkPublished //
	////////////////////


  /**
   *  The entity checkPublished
   *	 is defined as null before being initialized. 
   */
  @JsonProperty
  @JsonInclude(Include.NON_NULL)
  protected Boolean checkPublished;

  /**
   * <br> The entity checkPublished
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.hostcheck.HostCheck&fq=entiteVar_enUS_indexed_string:checkPublished">Find the entity checkPublished in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _checkPublished(Wrap<Boolean> w);

  public Boolean getCheckPublished() {
    return checkPublished;
  }

  public void setCheckPublished(Boolean checkPublished) {
    this.checkPublished = checkPublished;
  }
  @JsonIgnore
  public void setCheckPublished(String o) {
    this.checkPublished = HostCheck.staticSetCheckPublished(siteRequest_, o);
  }
  public static Boolean staticSetCheckPublished(SiteRequest siteRequest_, String o) {
    return Boolean.parseBoolean(o);
  }
  protected HostCheck checkPublishedInit() {
    Wrap<Boolean> checkPublishedWrap = new Wrap<Boolean>().var("checkPublished");
    if(checkPublished == null) {
      _checkPublished(checkPublishedWrap);
      Optional.ofNullable(checkPublishedWrap.getO()).ifPresent(o -> {
        setCheckPublished(o);
      });
    }
    return (HostCheck)this;
  }

  public static Boolean staticSearchCheckPublished(SiteRequest siteRequest_, Boolean o) {
    return o;
  }

  public static String staticSearchStrCheckPublished(SiteRequest siteRequest_, Boolean o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqCheckPublished(SiteRequest siteRequest_, String o) {
    return HostCheck.staticSearchCheckPublished(siteRequest_, HostCheck.staticSetCheckPublished(siteRequest_, o)).toString();
  }

  public Boolean sqlCheckPublished() {
    return checkPublished;
  }

  public static Boolean staticJsonCheckPublished(Boolean checkPublished) {
    return checkPublished;
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
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.hostcheck.HostCheck&fq=entiteVar_enUS_indexed_string:eventSubscriptions">Find the entity eventSubscriptions in Solr</a>
   * <br>
   * @param l is the entity already constructed. 
   **/
  protected abstract void _eventSubscriptions(List<String> l);

  public List<String> getEventSubscriptions() {
    return eventSubscriptions;
  }

  public void setEventSubscriptions(List<String> eventSubscriptions) {
    this.eventSubscriptions = eventSubscriptions;
  }
  @JsonIgnore
  public void setEventSubscriptions(String o) {
    String l = HostCheck.staticSetEventSubscriptions(siteRequest_, o);
    if(l != null)
      addEventSubscriptions(l);
  }
  public static String staticSetEventSubscriptions(SiteRequest siteRequest_, String o) {
    return o;
  }
  public HostCheck addEventSubscriptions(String...objects) {
    for(String o : objects) {
      addEventSubscriptions(o);
    }
    return (HostCheck)this;
  }
  public HostCheck addEventSubscriptions(String o) {
    if(o != null)
      this.eventSubscriptions.add(o);
    return (HostCheck)this;
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
  protected HostCheck eventSubscriptionsInit() {
    _eventSubscriptions(eventSubscriptions);
    return (HostCheck)this;
  }

  public static String staticSearchEventSubscriptions(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrEventSubscriptions(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqEventSubscriptions(SiteRequest siteRequest_, String o) {
    return HostCheck.staticSearchEventSubscriptions(siteRequest_, HostCheck.staticSetEventSubscriptions(siteRequest_, o)).toString();
  }

  public String[] sqlEventSubscriptions() {
    return eventSubscriptions.stream().map(v -> (String)v).toArray(String[]::new);
  }

  public static JsonArray staticJsonEventSubscriptions(List<String> eventSubscriptions) {
    JsonArray a = new JsonArray();
    eventSubscriptions.stream().forEach(v -> a.add(v.toString()));
    return a;
  }

	///////////////////
  // eventHandlers //
	///////////////////


  /**
   *  The entity eventHandlers
   *	 It is constructed before being initialized with the constructor by default. 
   */
  @JsonProperty
  @JsonFormat(shape = JsonFormat.Shape.ARRAY)
  @JsonInclude(Include.NON_NULL)
  protected List<String> eventHandlers = new ArrayList<String>();

  /**
   * <br> The entity eventHandlers
   *  It is constructed before being initialized with the constructor by default. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.hostcheck.HostCheck&fq=entiteVar_enUS_indexed_string:eventHandlers">Find the entity eventHandlers in Solr</a>
   * <br>
   * @param l is the entity already constructed. 
   **/
  protected abstract void _eventHandlers(List<String> l);

  public List<String> getEventHandlers() {
    return eventHandlers;
  }

  public void setEventHandlers(List<String> eventHandlers) {
    this.eventHandlers = eventHandlers;
  }
  @JsonIgnore
  public void setEventHandlers(String o) {
    String l = HostCheck.staticSetEventHandlers(siteRequest_, o);
    if(l != null)
      addEventHandlers(l);
  }
  public static String staticSetEventHandlers(SiteRequest siteRequest_, String o) {
    return o;
  }
  public HostCheck addEventHandlers(String...objects) {
    for(String o : objects) {
      addEventHandlers(o);
    }
    return (HostCheck)this;
  }
  public HostCheck addEventHandlers(String o) {
    if(o != null)
      this.eventHandlers.add(o);
    return (HostCheck)this;
  }
  @JsonIgnore
  public void setEventHandlers(JsonArray objects) {
    eventHandlers.clear();
    if(objects == null)
      return;
    for(int i = 0; i < objects.size(); i++) {
      String o = objects.getString(i);
      addEventHandlers(o);
    }
  }
  protected HostCheck eventHandlersInit() {
    _eventHandlers(eventHandlers);
    return (HostCheck)this;
  }

  public static String staticSearchEventHandlers(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrEventHandlers(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqEventHandlers(SiteRequest siteRequest_, String o) {
    return HostCheck.staticSearchEventHandlers(siteRequest_, HostCheck.staticSetEventHandlers(siteRequest_, o)).toString();
  }

  public String[] sqlEventHandlers() {
    return eventHandlers.stream().map(v -> (String)v).toArray(String[]::new);
  }

  public static JsonArray staticJsonEventHandlers(List<String> eventHandlers) {
    JsonArray a = new JsonArray();
    eventHandlers.stream().forEach(v -> a.add(v.toString()));
    return a;
  }

  //////////////
  // initDeep //
  //////////////

  public Future<HostCheckGen<DEV>> promiseDeepHostCheck(SiteRequest siteRequest_) {
    setSiteRequest_(siteRequest_);
    return promiseDeepHostCheck();
  }

  public Future<HostCheckGen<DEV>> promiseDeepHostCheck() {
    Promise<HostCheckGen<DEV>> promise = Promise.promise();
    Promise<Void> promise2 = Promise.promise();
    promiseHostCheck(promise2);
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

  public Future<Void> promiseHostCheck(Promise<Void> promise) {
    Future.future(a -> a.complete()).compose(a -> {
      Promise<Void> promise2 = Promise.promise();
      try {
        tenantResourceInit();
        checkNameInit();
        checkNamespaceInit();
        checkCommandInit();
        checkIntervalInit();
        checkPublishedInit();
        eventSubscriptionsInit();
        eventHandlersInit();
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

  @Override public Future<? extends HostCheckGen<DEV>> promiseDeepForClass(SiteRequest siteRequest_) {
    return promiseDeepHostCheck(siteRequest_);
  }

  /////////////////
  // siteRequest //
  /////////////////

  public void siteRequestHostCheck(SiteRequest siteRequest_) {
      super.siteRequestBaseModel(siteRequest_);
  }

  public void siteRequestForClass(SiteRequest siteRequest_) {
    siteRequestHostCheck(siteRequest_);
  }

  /////////////
  // obtain //
  /////////////

  @Override public Object obtainForClass(String var) {
    String[] vars = StringUtils.split(var, ".");
    Object o = null;
    for(String v : vars) {
      if(o == null)
        o = obtainHostCheck(v);
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
  public Object obtainHostCheck(String var) {
    HostCheck oHostCheck = (HostCheck)this;
    switch(var) {
      case "tenantResource":
        return oHostCheck.tenantResource;
      case "checkName":
        return oHostCheck.checkName;
      case "checkNamespace":
        return oHostCheck.checkNamespace;
      case "checkCommand":
        return oHostCheck.checkCommand;
      case "checkInterval":
        return oHostCheck.checkInterval;
      case "checkPublished":
        return oHostCheck.checkPublished;
      case "eventSubscriptions":
        return oHostCheck.eventSubscriptions;
      case "eventHandlers":
        return oHostCheck.eventHandlers;
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
        o = relateHostCheck(v, val);
      else if(o instanceof BaseModel) {
        BaseModel baseModel = (BaseModel)o;
        o = baseModel.relateForClass(v, val);
      }
    }
    return o != null;
  }
  public Object relateHostCheck(String var, Object val) {
    HostCheck oHostCheck = (HostCheck)this;
    switch(var) {
      case "tenantResource":
        if(oHostCheck.getTenantResource() == null)
          oHostCheck.setTenantResource(Optional.ofNullable(val).map(v -> v.toString()).orElse(null));
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

  public static Object staticSetForClass(String entityVar, SiteRequest siteRequest_, String v, HostCheck o) {
    return staticSetHostCheck(entityVar,  siteRequest_, v, o);
  }
  public static Object staticSetHostCheck(String entityVar, SiteRequest siteRequest_, String v, HostCheck o) {
    switch(entityVar) {
    case "tenantResource":
      return HostCheck.staticSetTenantResource(siteRequest_, v);
    case "checkName":
      return HostCheck.staticSetCheckName(siteRequest_, v);
    case "checkNamespace":
      return HostCheck.staticSetCheckNamespace(siteRequest_, v);
    case "checkCommand":
      return HostCheck.staticSetCheckCommand(siteRequest_, v);
    case "checkInterval":
      return HostCheck.staticSetCheckInterval(siteRequest_, v);
    case "checkPublished":
      return HostCheck.staticSetCheckPublished(siteRequest_, v);
    case "eventSubscriptions":
      return HostCheck.staticSetEventSubscriptions(siteRequest_, v);
    case "eventHandlers":
      return HostCheck.staticSetEventHandlers(siteRequest_, v);
      default:
        return BaseModel.staticSetBaseModel(entityVar,  siteRequest_, v, o);
    }
  }

  ////////////////
  // staticSearch //
  ////////////////

  public static Object staticSearchForClass(String entityVar, SiteRequest siteRequest_, Object o) {
    return staticSearchHostCheck(entityVar,  siteRequest_, o);
  }
  public static Object staticSearchHostCheck(String entityVar, SiteRequest siteRequest_, Object o) {
    switch(entityVar) {
    case "tenantResource":
      return HostCheck.staticSearchTenantResource(siteRequest_, (String)o);
    case "checkName":
      return HostCheck.staticSearchCheckName(siteRequest_, (String)o);
    case "checkNamespace":
      return HostCheck.staticSearchCheckNamespace(siteRequest_, (String)o);
    case "checkCommand":
      return HostCheck.staticSearchCheckCommand(siteRequest_, (String)o);
    case "checkInterval":
      return HostCheck.staticSearchCheckInterval(siteRequest_, (Integer)o);
    case "checkPublished":
      return HostCheck.staticSearchCheckPublished(siteRequest_, (Boolean)o);
    case "eventSubscriptions":
      return HostCheck.staticSearchEventSubscriptions(siteRequest_, (String)o);
    case "eventHandlers":
      return HostCheck.staticSearchEventHandlers(siteRequest_, (String)o);
      default:
        return BaseModel.staticSearchBaseModel(entityVar,  siteRequest_, o);
    }
  }

  ///////////////////
  // staticSearchStr //
  ///////////////////

  public static String staticSearchStrForClass(String entityVar, SiteRequest siteRequest_, Object o) {
    return staticSearchStrHostCheck(entityVar,  siteRequest_, o);
  }
  public static String staticSearchStrHostCheck(String entityVar, SiteRequest siteRequest_, Object o) {
    switch(entityVar) {
    case "tenantResource":
      return HostCheck.staticSearchStrTenantResource(siteRequest_, (String)o);
    case "checkName":
      return HostCheck.staticSearchStrCheckName(siteRequest_, (String)o);
    case "checkNamespace":
      return HostCheck.staticSearchStrCheckNamespace(siteRequest_, (String)o);
    case "checkCommand":
      return HostCheck.staticSearchStrCheckCommand(siteRequest_, (String)o);
    case "checkInterval":
      return HostCheck.staticSearchStrCheckInterval(siteRequest_, (Integer)o);
    case "checkPublished":
      return HostCheck.staticSearchStrCheckPublished(siteRequest_, (Boolean)o);
    case "eventSubscriptions":
      return HostCheck.staticSearchStrEventSubscriptions(siteRequest_, (String)o);
    case "eventHandlers":
      return HostCheck.staticSearchStrEventHandlers(siteRequest_, (String)o);
      default:
        return BaseModel.staticSearchStrBaseModel(entityVar,  siteRequest_, o);
    }
  }

  //////////////////
  // staticSearchFq //
  //////////////////

  public static String staticSearchFqForClass(String entityVar, SiteRequest siteRequest_, String o) {
    return staticSearchFqHostCheck(entityVar,  siteRequest_, o);
  }
  public static String staticSearchFqHostCheck(String entityVar, SiteRequest siteRequest_, String o) {
    switch(entityVar) {
    case "tenantResource":
      return HostCheck.staticSearchFqTenantResource(siteRequest_, o);
    case "checkName":
      return HostCheck.staticSearchFqCheckName(siteRequest_, o);
    case "checkNamespace":
      return HostCheck.staticSearchFqCheckNamespace(siteRequest_, o);
    case "checkCommand":
      return HostCheck.staticSearchFqCheckCommand(siteRequest_, o);
    case "checkInterval":
      return HostCheck.staticSearchFqCheckInterval(siteRequest_, o);
    case "checkPublished":
      return HostCheck.staticSearchFqCheckPublished(siteRequest_, o);
    case "eventSubscriptions":
      return HostCheck.staticSearchFqEventSubscriptions(siteRequest_, o);
    case "eventHandlers":
      return HostCheck.staticSearchFqEventHandlers(siteRequest_, o);
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
          o = persistHostCheck(v, val);
        else if(o instanceof BaseModel) {
          BaseModel oBaseModel = (BaseModel)o;
          o = oBaseModel.persistForClass(v, val);
        }
      }
    }
    return o != null;
  }
  public Object persistHostCheck(String var, Object val) {
    String varLower = var.toLowerCase();
      if("tenantresource".equals(varLower)) {
        if(val instanceof String) {
          setTenantResource((String)val);
        }
        saves.add("tenantResource");
        return val;
      } else if("checkname".equals(varLower)) {
        if(val instanceof String) {
          setCheckName((String)val);
        }
        saves.add("checkName");
        return val;
      } else if("checknamespace".equals(varLower)) {
        if(val instanceof String) {
          setCheckNamespace((String)val);
        }
        saves.add("checkNamespace");
        return val;
      } else if("checkcommand".equals(varLower)) {
        if(val instanceof String) {
          setCheckCommand((String)val);
        }
        saves.add("checkCommand");
        return val;
      } else if("checkinterval".equals(varLower)) {
        if(val instanceof Integer) {
          setCheckInterval((Integer)val);
        } else {
          setCheckInterval(val == null ? null : val.toString());
        }
        saves.add("checkInterval");
        return val;
      } else if("checkpublished".equals(varLower)) {
        if(val instanceof Boolean) {
          setCheckPublished((Boolean)val);
        } else {
          setCheckPublished(val == null ? null : val.toString());
        }
        saves.add("checkPublished");
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
      } else if("eventhandlers".equals(varLower)) {
        if(val instanceof List<?>) {
          ((List<String>)val).stream().forEach(v -> addEventHandlers(v));
        } else if(val instanceof String[]) {
          Arrays.asList((String[])val).stream().forEach(v -> addEventHandlers((String)v));
        } else if(val instanceof JsonArray) {
          ((JsonArray)val).stream().forEach(v -> addEventHandlers(staticSetEventHandlers(siteRequest_, v.toString())));
        }
        if(!saves.contains("eventHandlers")) {
          saves.add("eventHandlers");
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
    populateHostCheck(doc);
  }
  public void populateHostCheck(SolrResponse.Doc doc) {
    HostCheck oHostCheck = (HostCheck)this;
    saves = Optional.ofNullable((ArrayList<String>)doc.get("saves_docvalues_strings")).orElse(new ArrayList<String>());
    if(saves != null) {

      String tenantResource = (String)doc.get("tenantResource_docvalues_string");
      if(tenantResource != null)
        oHostCheck.setTenantResource(tenantResource);

      if(saves.contains("checkName")) {
        String checkName = (String)doc.get("checkName_docvalues_string");
        if(checkName != null)
          oHostCheck.setCheckName(checkName);
      }

      if(saves.contains("checkNamespace")) {
        String checkNamespace = (String)doc.get("checkNamespace_docvalues_string");
        if(checkNamespace != null)
          oHostCheck.setCheckNamespace(checkNamespace);
      }

      if(saves.contains("checkCommand")) {
        String checkCommand = (String)doc.get("checkCommand_docvalues_string");
        if(checkCommand != null)
          oHostCheck.setCheckCommand(checkCommand);
      }

      if(saves.contains("checkInterval")) {
        Integer checkInterval = (Integer)doc.get("checkInterval_docvalues_int");
        if(checkInterval != null)
          oHostCheck.setCheckInterval(checkInterval);
      }

      if(saves.contains("checkPublished")) {
        Boolean checkPublished = (Boolean)doc.get("checkPublished_docvalues_boolean");
        if(checkPublished != null)
          oHostCheck.setCheckPublished(checkPublished);
      }

      if(saves.contains("eventSubscriptions")) {
        List<String> eventSubscriptions = (List<String>)doc.get("eventSubscriptions_docvalues_strings");
        if(eventSubscriptions != null) {
          eventSubscriptions.stream().forEach( v -> {
            oHostCheck.eventSubscriptions.add(HostCheck.staticSetEventSubscriptions(siteRequest_, v));
          });
        }
      }

      if(saves.contains("eventHandlers")) {
        List<String> eventHandlers = (List<String>)doc.get("eventHandlers_docvalues_strings");
        if(eventHandlers != null) {
          eventHandlers.stream().forEach( v -> {
            oHostCheck.eventHandlers.add(HostCheck.staticSetEventHandlers(siteRequest_, v));
          });
        }
      }
    }

    super.populateBaseModel(doc);
  }

  public void indexHostCheck(JsonObject doc) {
    if(tenantResource != null) {
      doc.put("tenantResource_docvalues_string", tenantResource);
    }
    if(checkName != null) {
      doc.put("checkName_docvalues_string", checkName);
    }
    if(checkNamespace != null) {
      doc.put("checkNamespace_docvalues_string", checkNamespace);
    }
    if(checkCommand != null) {
      doc.put("checkCommand_docvalues_string", checkCommand);
    }
    if(checkInterval != null) {
      doc.put("checkInterval_docvalues_int", checkInterval);
    }
    if(checkPublished != null) {
      doc.put("checkPublished_docvalues_boolean", checkPublished);
    }
    if(eventSubscriptions != null) {
      JsonArray l = new JsonArray();
      doc.put("eventSubscriptions_docvalues_strings", l);
      for(String o : eventSubscriptions) {
        l.add(HostCheck.staticSearchEventSubscriptions(siteRequest_, o));
      }
    }
    if(eventHandlers != null) {
      JsonArray l = new JsonArray();
      doc.put("eventHandlers_docvalues_strings", l);
      for(String o : eventHandlers) {
        l.add(HostCheck.staticSearchEventHandlers(siteRequest_, o));
      }
    }
    super.indexBaseModel(doc);

	}

  public static String varStoredHostCheck(String entityVar) {
    switch(entityVar) {
      case "tenantResource":
        return "tenantResource_docvalues_string";
      case "checkName":
        return "checkName_docvalues_string";
      case "checkNamespace":
        return "checkNamespace_docvalues_string";
      case "checkCommand":
        return "checkCommand_docvalues_string";
      case "checkInterval":
        return "checkInterval_docvalues_int";
      case "checkPublished":
        return "checkPublished_docvalues_boolean";
      case "eventSubscriptions":
        return "eventSubscriptions_docvalues_strings";
      case "eventHandlers":
        return "eventHandlers_docvalues_strings";
      default:
        return BaseModel.varStoredBaseModel(entityVar);
    }
  }

  public static String varIndexedHostCheck(String entityVar) {
    switch(entityVar) {
      case "tenantResource":
        return "tenantResource_docvalues_string";
      case "checkName":
        return "checkName_docvalues_string";
      case "checkNamespace":
        return "checkNamespace_docvalues_string";
      case "checkCommand":
        return "checkCommand_docvalues_string";
      case "checkInterval":
        return "checkInterval_docvalues_int";
      case "checkPublished":
        return "checkPublished_docvalues_boolean";
      case "eventSubscriptions":
        return "eventSubscriptions_docvalues_strings";
      case "eventHandlers":
        return "eventHandlers_docvalues_strings";
      default:
        return BaseModel.varIndexedBaseModel(entityVar);
    }
  }

  public static String searchVarHostCheck(String searchVar) {
    switch(searchVar) {
      case "tenantResource_docvalues_string":
        return "tenantResource";
      case "checkName_docvalues_string":
        return "checkName";
      case "checkNamespace_docvalues_string":
        return "checkNamespace";
      case "checkCommand_docvalues_string":
        return "checkCommand";
      case "checkInterval_docvalues_int":
        return "checkInterval";
      case "checkPublished_docvalues_boolean":
        return "checkPublished";
      case "eventSubscriptions_docvalues_strings":
        return "eventSubscriptions";
      case "eventHandlers_docvalues_strings":
        return "eventHandlers";
      default:
        return BaseModel.searchVarBaseModel(searchVar);
    }
  }

  public static String varSearchHostCheck(String entityVar) {
    switch(entityVar) {
      default:
        return BaseModel.varSearchBaseModel(entityVar);
    }
  }

  public static String varSuggestedHostCheck(String entityVar) {
    switch(entityVar) {
      default:
        return BaseModel.varSuggestedBaseModel(entityVar);
    }
  }

  /////////////
  // store //
  /////////////

  @Override public void storeForClass(SolrResponse.Doc doc) {
    storeHostCheck(doc);
  }
  public void storeHostCheck(SolrResponse.Doc doc) {
    HostCheck oHostCheck = (HostCheck)this;
    SiteRequest siteRequest = oHostCheck.getSiteRequest_();

    oHostCheck.setTenantResource(Optional.ofNullable(doc.get("tenantResource_docvalues_string")).map(v -> v.toString()).orElse(null));
    oHostCheck.setCheckName(Optional.ofNullable(doc.get("checkName_docvalues_string")).map(v -> v.toString()).orElse(null));
    oHostCheck.setCheckNamespace(Optional.ofNullable(doc.get("checkNamespace_docvalues_string")).map(v -> v.toString()).orElse(null));
    oHostCheck.setCheckCommand(Optional.ofNullable(doc.get("checkCommand_docvalues_string")).map(v -> v.toString()).orElse(null));
    oHostCheck.setCheckInterval(Optional.ofNullable(doc.get("checkInterval_docvalues_int")).map(v -> v.toString()).orElse(null));
    oHostCheck.setCheckPublished(Optional.ofNullable(doc.get("checkPublished_docvalues_boolean")).map(v -> v.toString()).orElse(null));
    Optional.ofNullable((List<?>)doc.get("eventSubscriptions_docvalues_strings")).orElse(Arrays.asList()).stream().filter(v -> v != null).forEach(v -> {
      oHostCheck.addEventSubscriptions(HostCheck.staticSetEventSubscriptions(siteRequest, v.toString()));
    });
    Optional.ofNullable((List<?>)doc.get("eventHandlers_docvalues_strings")).orElse(Arrays.asList()).stream().filter(v -> v != null).forEach(v -> {
      oHostCheck.addEventHandlers(HostCheck.staticSetEventHandlers(siteRequest, v.toString()));
    });

    super.storeBaseModel(doc);
  }

  //////////////////
  // apiRequest //
  //////////////////

  public void apiRequestHostCheck() {
    ApiRequest apiRequest = Optional.ofNullable(siteRequest_).map(r -> r.getApiRequest_()).orElse(null);
    Object o = Optional.ofNullable(apiRequest).map(ApiRequest::getOriginal).orElse(null);
    if(o != null && o instanceof HostCheck) {
      HostCheck original = (HostCheck)o;
      if(!Objects.equals(tenantResource, original.getTenantResource()))
        apiRequest.addVars("tenantResource");
      if(!Objects.equals(checkName, original.getCheckName()))
        apiRequest.addVars("checkName");
      if(!Objects.equals(checkNamespace, original.getCheckNamespace()))
        apiRequest.addVars("checkNamespace");
      if(!Objects.equals(checkCommand, original.getCheckCommand()))
        apiRequest.addVars("checkCommand");
      if(!Objects.equals(checkInterval, original.getCheckInterval()))
        apiRequest.addVars("checkInterval");
      if(!Objects.equals(checkPublished, original.getCheckPublished()))
        apiRequest.addVars("checkPublished");
      if(!Objects.equals(eventSubscriptions, original.getEventSubscriptions()))
        apiRequest.addVars("eventSubscriptions");
      if(!Objects.equals(eventHandlers, original.getEventHandlers()))
        apiRequest.addVars("eventHandlers");
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
    sb.append(Optional.ofNullable(checkName).map(v -> "checkName: \"" + v + "\"\n" ).orElse(""));
    sb.append(Optional.ofNullable(checkNamespace).map(v -> "checkNamespace: \"" + v + "\"\n" ).orElse(""));
    sb.append(Optional.ofNullable(checkCommand).map(v -> "checkCommand: \"" + v + "\"\n" ).orElse(""));
    sb.append(Optional.ofNullable(checkInterval).map(v -> "checkInterval: " + v + "\n").orElse(""));
    sb.append(Optional.ofNullable(checkPublished).map(v -> "checkPublished: " + v + "\n").orElse(""));
    sb.append(Optional.ofNullable(eventSubscriptions).map(v -> "eventSubscriptions: " + v + "\n").orElse(""));
    sb.append(Optional.ofNullable(eventHandlers).map(v -> "eventHandlers: " + v + "\n").orElse(""));
    return sb.toString();
  }

  public static final String CLASS_SIMPLE_NAME = "HostCheck";
  public static final String CLASS_CANONICAL_NAME = "org.computate.dcm.model.eda.hostcheck.HostCheck";
  public static final String CLASS_AUTH_RESOURCE = "HOSTCHECK";
  public static final String CLASS_API_ADDRESS_HostCheck = "dcm-enUS-HostCheck";
  public static String getClassApiAddress() {
    return CLASS_API_ADDRESS_HostCheck;
  }
  public static final String VAR_tenantResource = "tenantResource";
  public static final String VAR_checkName = "checkName";
  public static final String VAR_checkNamespace = "checkNamespace";
  public static final String VAR_checkCommand = "checkCommand";
  public static final String VAR_checkInterval = "checkInterval";
  public static final String VAR_checkPublished = "checkPublished";
  public static final String VAR_eventSubscriptions = "eventSubscriptions";
  public static final String VAR_eventHandlers = "eventHandlers";

  public static List<String> varsQForClass() {
    return HostCheck.varsQHostCheck(new ArrayList<String>());
  }
  public static List<String> varsQHostCheck(List<String> vars) {
    BaseModel.varsQBaseModel(vars);
    return vars;
  }

  public static List<String> varsFqForClass() {
    return HostCheck.varsFqHostCheck(new ArrayList<String>());
  }
  public static List<String> varsFqHostCheck(List<String> vars) {
    BaseModel.varsFqBaseModel(vars);
    return vars;
  }

  public static List<String> varsRangeForClass() {
    return HostCheck.varsRangeHostCheck(new ArrayList<String>());
  }
  public static List<String> varsRangeHostCheck(List<String> vars) {
    BaseModel.varsRangeBaseModel(vars);
    return vars;
  }

  public static final String DISPLAY_NAME_tenantResource = "tenant";
  public static final String DISPLAY_NAME_checkName = "check name";
  public static final String DISPLAY_NAME_checkNamespace = "check namespace";
  public static final String DISPLAY_NAME_checkCommand = "check command";
  public static final String DISPLAY_NAME_checkInterval = "check interval in seconds";
  public static final String DISPLAY_NAME_checkPublished = "check published";
  public static final String DISPLAY_NAME_eventSubscriptions = "event subscriptions";
  public static final String DISPLAY_NAME_eventHandlers = "event handlers";

  @Override
  public String idForClass() {
    return checkName;
  }

  @Override
  public String titleForClass() {
    return objectTitle;
  }

  @Override
  public String nameForClass() {
    return checkName;
  }

  @Override
  public String classNameAdjectiveSingularForClass() {
    return HostCheck.NameAdjectiveSingular_enUS;
  }

  @Override
  public String descriptionForClass() {
    return null;
  }

  @Override
  public String enUSStringFormatUrlEditPageForClass() {
    return "%s/en-us/edit/host-check/%s";
  }

  @Override
  public String enUSStringFormatUrlDisplayPageForClass() {
    return null;
  }

  @Override
  public String enUSStringFormatUrlUserPageForClass() {
    return "%s/en-us/user/host-check/%s";
  }

  @Override
  public String enUSStringFormatUrlDownloadForClass() {
    return null;
  }

  public static String displayNameForClass(String var) {
    return HostCheck.displayNameHostCheck(var);
  }
  public static String displayNameHostCheck(String var) {
    switch(var) {
    case VAR_tenantResource:
      return DISPLAY_NAME_tenantResource;
    case VAR_checkName:
      return DISPLAY_NAME_checkName;
    case VAR_checkNamespace:
      return DISPLAY_NAME_checkNamespace;
    case VAR_checkCommand:
      return DISPLAY_NAME_checkCommand;
    case VAR_checkInterval:
      return DISPLAY_NAME_checkInterval;
    case VAR_checkPublished:
      return DISPLAY_NAME_checkPublished;
    case VAR_eventSubscriptions:
      return DISPLAY_NAME_eventSubscriptions;
    case VAR_eventHandlers:
      return DISPLAY_NAME_eventHandlers;
    default:
      return BaseModel.displayNameBaseModel(var);
    }
  }

  public static String descriptionHostCheck(String var) {
    if(var == null)
      return null;
    switch(var) {
    case VAR_tenantResource:
      return "The unique authorization resource for the tenant for multi-tenancy";
    case VAR_checkName:
      return "The name of the host check (may only contain letters, numbers, periods, colons, and dashes). ";
    case VAR_checkNamespace:
      return "The namespace of the host check. ";
    case VAR_checkCommand:
      return "The bash command to run during the check. ";
    case VAR_checkInterval:
      return "The check interval in seconds. ";
    case VAR_checkPublished:
      return "When disabled the check will not be executed unless explicitly queued. ";
    case VAR_eventSubscriptions:
      return "The list of event subscriptions the host subscribes to. ";
    case VAR_eventHandlers:
      return "The list of event handlers the host subscribes to. ";
      default:
        return BaseModel.descriptionBaseModel(var);
    }
  }

  public static String classSimpleNameHostCheck(String var) {
    switch(var) {
    case VAR_tenantResource:
      return "String";
    case VAR_checkName:
      return "String";
    case VAR_checkNamespace:
      return "String";
    case VAR_checkCommand:
      return "String";
    case VAR_checkInterval:
      return "Integer";
    case VAR_checkPublished:
      return "Boolean";
    case VAR_eventSubscriptions:
      return "List";
    case VAR_eventHandlers:
      return "List";
      default:
        return BaseModel.classSimpleNameBaseModel(var);
    }
  }

  public static Integer htmColumnHostCheck(String var) {
    switch(var) {
    case VAR_tenantResource:
      return 0;
    case VAR_checkName:
      return 1;
    case VAR_eventSubscriptions:
      return 2;
    case VAR_eventHandlers:
      return 2;
      default:
        return BaseModel.htmColumnBaseModel(var);
    }
  }

  public static Integer htmRowHostCheck(String var) {
    switch(var) {
    case VAR_tenantResource:
      return 3;
    case VAR_checkName:
      return 3;
    case VAR_checkNamespace:
      return 3;
    case VAR_checkCommand:
      return 3;
    case VAR_checkInterval:
      return 3;
    case VAR_checkPublished:
      return 3;
    case VAR_eventSubscriptions:
      return 3;
    case VAR_eventHandlers:
      return 3;
      default:
        return BaseModel.htmRowBaseModel(var);
    }
  }

  public static Integer htmCellHostCheck(String var) {
    switch(var) {
    case VAR_tenantResource:
      return 0;
    case VAR_checkName:
      return 2;
    case VAR_checkNamespace:
      return 3;
    case VAR_checkCommand:
      return 4;
    case VAR_checkInterval:
      return 5;
    case VAR_checkPublished:
      return 6;
    case VAR_eventSubscriptions:
      return 7;
    case VAR_eventHandlers:
      return 8;
      default:
        return BaseModel.htmCellBaseModel(var);
    }
  }

  public static Integer lengthMinHostCheck(String var) {
    switch(var) {
      default:
        return BaseModel.lengthMinBaseModel(var);
    }
  }

  public static Integer lengthMaxHostCheck(String var) {
    switch(var) {
      default:
        return BaseModel.lengthMaxBaseModel(var);
    }
  }

  public static Integer maxHostCheck(String var) {
    switch(var) {
      default:
        return BaseModel.maxBaseModel(var);
    }
  }

  public static Integer minHostCheck(String var) {
    switch(var) {
      default:
        return BaseModel.minBaseModel(var);
    }
  }
}
