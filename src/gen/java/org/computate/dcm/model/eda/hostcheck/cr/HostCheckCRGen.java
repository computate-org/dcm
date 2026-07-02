package org.computate.dcm.model.eda.hostcheck.cr;

import java.util.List;
import org.computate.dcm.model.eda.hostcheck.HostCheck;
import org.computate.search.wrap.Wrap;
import org.computate.dcm.request.SiteRequest;
import org.computate.dcm.model.eda.hostcheck.HostCheck;
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
import org.computate.dcm.model.eda.tenant.intent.TenantIntent;
import org.computate.dcm.model.eda.jobtemplate.JobTemplate;
import java.lang.Long;
import java.lang.Integer;
import java.lang.Boolean;
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
 * <li>You can add a class comment "{@inheritDoc}" if you wish to inherit the helpful inherited class comments from class HostCheckCRGen into the class HostCheckCR. 
 * </li>
 * <h3>About the HostCheckCR class and it's generated class HostCheckCRGen&lt;HostCheck&gt;: </h3>extends HostCheckCRGen
 * <p>
 * This Java class extends a generated Java class HostCheckCRGen built by the <a href="https://github.com/computate-org/computate">https://github.com/computate-org/computate</a> project. 
 * Whenever this Java class is modified or touched, the watch service installed as described in the README, indexes all the information about this Java class in a local Apache Solr Search Engine. 
 * If you are running the service, you can see the indexed data about this Java Class here: 
 * </p>
 * <p><a href="https://solr.apps-crc.testing/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.hostcheck.cr.HostCheckCR">Find the class HostCheckCR in Solr. </a></p>
 * <p>
 * The extended class ending with "Gen" did not exist at first, but was automatically created by the same watch service based on the data retrieved from the local Apache Server search engine. 
 * The extended class contains many generated fields, getters, setters, initialization code, and helper methods to help build a website and API fast, reactive, and scalable. 
 * </p>
 * extends HostCheckCRGen<HostCheck>
 * <p>This <code>class HostCheckCR extends HostCheckCRGen&lt;HostCheck&gt;</code>, which means it extends a newly generated HostCheckCRGen. 
 * The generated <code>class HostCheckCRGen extends HostCheck</code> which means that HostCheckCR extends HostCheckCRGen which extends HostCheck. 
 * This generated inheritance is a powerful feature that allows a lot of boiler plate code to be created for you automatically while still preserving inheritance through the power of Java Generic classes. 
 * </p>
 * <h2>
 *   Api: true
 * </h2>
 * <p>
 *   This class contains a comment <kbd><b>Api: true</b></kbd>, which means this class will have Java Vert.x API backend code generated for these objects. 
 * </p>
 * <h2>ApiTag.enUS: true</h2>
 * <p>This class contains a comment <kbd><b>ApiTag: host check change requests</b></kbd>, which groups all of the OpenAPIs for HostCheckCR objects under the tag "host check change requests". 
 * </p>
 * <h2>ApiUri.enUS: /en-us/api/host-check-cr</h2>
 * <p>This class contains a comment <kbd><b>ApiUri: /en-us/api/host-check-cr</b></kbd>, which defines the base API URI for HostCheckCR objects as "/en-us/api/host-check-cr" in the OpenAPI spec. 
 * </p>
 * <h2>Color: null</h2>
 * <h2>Indexed: true</h2>
 * <p>This class contains a comment <kbd><b>Indexed: true</b></kbd>, which means this class will be indexed in the search engine. 
 * Every protected void method that begins with "_" that is marked to be searched with a comment like "Indexed: true", "Stored: true", or "DocValues: true" will be indexed in the search engine. 
 * </p>
 * <h2>{@inheritDoc}</h2>
 * <p>By adding a class comment "{@inheritDoc}", the HostCheckCR class will inherit the helpful inherited class comments from the super class HostCheckCRGen. 
 * </p>
 * <h2>
 *   Rows: 100
 * </h2>
 * <p>This class contains a comment <kbd><b>Rows: 100</b></kbd>, which means the host check change request API will return a default of 100 results instead of 10 by default. 
 * Each API has built in pagination of the search results to ensure a user can query all the data a page at a time without running the application out of memory. 
 * </p>
 * <p>This class contains a comment <kbd><b>Rows: 100</b></kbd>, which means the host check change request API will return a default of 100 results instead of 10 by default. 
 * Each API has built in pagination of the search results to ensure a user can query all the data a page at a time without running the application out of memory. 
 * </p>
 * <h2>
 *   Order: 120
 * </h2>
 * <p>
 *   This class contains a comment <kbd><b>Order: 120</b></kbd>, 
 *   which means this class will be sorted by the given number 120 
 *   ascending when code that relates to multiple classes at the same time is generated. 
 * </p>
 * <h2>SqlOrder: 120</h2>
 * <p>This class contains a comment <kbd><b>SqlOrder: 120</b></kbd>, which means this class will be sorted by the given number 120 ascending when SQL code to create and drop the tables is generated. 
 * </p>
 * <h2>Model: true</h2>
 * <p>This class contains a comment <kbd><b>Model: true</b></kbd>, which means this class will be stored in the database. 
 * Every protected void method that begins with "_" that contains a "Persist: true" comment will be a persisted field in the database table. 
 * </p>
 * <h2>Page: true</h2>
 * <p>This class contains a comment <kbd><b>Page: true</b></kbd>, which means this class will have webpage code generated for these objects. 
 * Java Vert.x backend API code, Handlebars HTML template frontend code, and JavaScript code will all generated and can be extended. 
 * This creates a new Java class org.computate.dcm.model.eda.hostcheck.cr.HostCheckCRPage. 
 * </p>
 * <h2>SuperPage.enUS: PageLayout</h2>
 * <p>This class contains a comment <kbd><b>SuperPage.enUS: PageLayout</b></kbd>, which identifies the Java super class of the page code by it's class simple name "PageLayout". 
 * This means that the newly created class org.computate.dcm.model.eda.hostcheck.cr.HostCheckCRPage extends org.computate.dcm.page.PageLayout. 
 * </p>
 * <h2>Promise: true</h2>
 * <p>
 *   This class contains a comment <kbd><b>Promise: true</b></kbd>
 *   Sometimes a Java class must be initialized asynchronously when it involves calling a blocking API. 
 *   This means that the HostCheckCR Java class has promiseDeep methods which must be initialized asynchronously as a Vert.x Promise  instead of initDeep methods which are a simple non-asynchronous method. 
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
 * <h2>AName.enUS: a host check change request</h2>
 * <p>This class contains a comment <kbd><b>AName.enUS: a host check change request</b></kbd>, which identifies the language context to describe a HostCheckCR as "a host check change request". 
 * </p>
 * <p>
 * Delete the class HostCheckCR in Solr: 
 * <pre>
 * curl -k 'https://solr.apps-crc.testing/solr/computate/update?commitWithin=1000&amp;overwrite=true&amp;wt=json' -X POST -H 'Content-type: text/xml' -u "admin:$(oc -n solr get secret/solr-solrcloud-security-bootstrap -o jsonpath={.data.admin} | base64 -d)" --data-raw '&lt;delete&gt;&lt;query&gt;classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.hostcheck.cr.HostCheckCR&lt;/query&gt;&lt;/delete&gt;'
 * </pre>
 * </p>
 * <p>
 * Delete  the package org.computate.dcm.model.eda.hostcheck.cr in Solr: 
 * <pre>
 * curl -k 'https://solr.apps-crc.testing/solr/computate/update?commitWithin=1000&amp;overwrite=true&amp;wt=json' -X POST -H 'Content-type: text/xml' -u "admin:$(oc -n solr get secret/solr-solrcloud-security-bootstrap -o jsonpath={.data.admin} | base64 -d)" --data-raw '&lt;delete&gt;&lt;query&gt;classeNomEnsemble_enUS_indexed_string:org.computate.dcm.model.eda.hostcheck.cr&lt;/query&gt;&lt;/delete&gt;'
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
public abstract class HostCheckCRGen<DEV> extends HostCheck {
  protected static final Logger LOG = LoggerFactory.getLogger(HostCheckCR.class);

  public static final String Description_enUS = "A new or updated check to be performed on a computer. ";
  public static final String AName_enUS = "a host check change request";
  public static final String This_enUS = "this ";
  public static final String ThisName_enUS = "this host check change request";
  public static final String A_enUS = "a ";
  public static final String TheName_enUS = "thehost check change request";
  public static final String SingularName_enUS = "host check change request";
  public static final String PluralName_enUS = "host check change requests";
  public static final String NameActual_enUS = "current host check change request";
  public static final String AllName_enUS = "all host check change requests";
  public static final String SearchAllNameBy_enUS = "search host check change requests by ";
  public static final String SearchAllName_enUS = "search host check change requests";
  public static final String Title_enUS = "host check change requests";
  public static final String ThePluralName_enUS = "the host check change requests";
  public static final String NoNameFound_enUS = "no host check change request found";
  public static final String ApiUri_enUS = "/en-us/api/host-check-cr";
  public static final String ApiUriSearchPage_enUS = "/en-us/search/host-check-cr";
  public static final String ApiUriEditPage_enUS = "/en-us/edit/host-check-cr/{changeRequestId}";
  public static final String OfName_enUS = "of host check change request";
  public static final String ANameAdjective_enUS = "an host check change request";
  public static final String NameAdjectiveSingular_enUS = "host check change request";
  public static final String NameAdjectivePlural_enUS = "host check change requests";
  public static final String Search_enUS_OpenApiUri = "/en-us/api/host-check-cr";
  public static final String Search_enUS_StringFormatUri = "/en-us/api/host-check-cr";
  public static final String Search_enUS_StringFormatUrl = "%s/en-us/api/host-check-cr";
  public static final String GET_enUS_OpenApiUri = "/en-us/api/host-check-cr/{changeRequestId}";
  public static final String GET_enUS_StringFormatUri = "/en-us/api/host-check-cr/%s";
  public static final String GET_enUS_StringFormatUrl = "%s/en-us/api/host-check-cr/%s";
  public static final String PATCH_enUS_OpenApiUri = "/en-us/api/host-check-cr";
  public static final String PATCH_enUS_StringFormatUri = "/en-us/api/host-check-cr";
  public static final String PATCH_enUS_StringFormatUrl = "%s/en-us/api/host-check-cr";
  public static final String POST_enUS_OpenApiUri = "/en-us/api/host-check-cr";
  public static final String POST_enUS_StringFormatUri = "/en-us/api/host-check-cr";
  public static final String POST_enUS_StringFormatUrl = "%s/en-us/api/host-check-cr";
  public static final String DELETE_enUS_OpenApiUri = "/en-us/api/host-check-cr/{changeRequestId}";
  public static final String DELETE_enUS_StringFormatUri = "/en-us/api/host-check-cr/%s";
  public static final String DELETE_enUS_StringFormatUrl = "%s/en-us/api/host-check-cr/%s";
  public static final String PUTImport_enUS_OpenApiUri = "/en-us/api/host-check-cr-import";
  public static final String PUTImport_enUS_StringFormatUri = "/en-us/api/host-check-cr-import";
  public static final String PUTImport_enUS_StringFormatUrl = "%s/en-us/api/host-check-cr-import";
  public static final String SearchPage_enUS_OpenApiUri = "/en-us/search/host-check-cr";
  public static final String SearchPage_enUS_StringFormatUri = "/en-us/search/host-check-cr";
  public static final String SearchPage_enUS_StringFormatUrl = "%s/en-us/search/host-check-cr";
  public static final String EditPage_enUS_OpenApiUri = "/en-us/edit/host-check-cr/{changeRequestId}";
  public static final String EditPage_enUS_StringFormatUri = "/en-us/edit/host-check-cr/%s";
  public static final String EditPage_enUS_StringFormatUrl = "%s/en-us/edit/host-check-cr/%s";
  public static final String DELETEFilter_enUS_OpenApiUri = "/en-us/api/host-check-cr";
  public static final String DELETEFilter_enUS_StringFormatUri = "/en-us/api/host-check-cr";
  public static final String DELETEFilter_enUS_StringFormatUrl = "%s/en-us/api/host-check-cr";

  public static final String Icon = "<i class=\"{{ FONTAWESOME_STYLE }} fa-box-check\"></i>";
  public static final Integer Rows = 100;

	/////////////////////
  // changeRequestId //
	/////////////////////


  /**
   *  The entity changeRequestId
   *	 is defined as null before being initialized. 
   */
  @JsonProperty
  @JsonInclude(Include.NON_NULL)
  protected String changeRequestId;

  /**
   * <br> The entity changeRequestId
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.hostcheck.cr.HostCheckCR&fq=entiteVar_enUS_indexed_string:changeRequestId">Find the entity changeRequestId in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _changeRequestId(Wrap<String> w);

  public String getChangeRequestId() {
    return changeRequestId;
  }
  public void setChangeRequestId(String o) {
    this.changeRequestId = HostCheckCR.staticSetChangeRequestId(siteRequest_, o);
  }
  public static String staticSetChangeRequestId(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected HostCheckCR changeRequestIdInit() {
    Wrap<String> changeRequestIdWrap = new Wrap<String>().var("changeRequestId");
    if(changeRequestId == null) {
      _changeRequestId(changeRequestIdWrap);
      Optional.ofNullable(changeRequestIdWrap.getO()).ifPresent(o -> {
        setChangeRequestId(o);
      });
    }
    return (HostCheckCR)this;
  }

  public static String staticSearchChangeRequestId(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrChangeRequestId(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqChangeRequestId(SiteRequest siteRequest_, String o) {
    return HostCheckCR.staticSearchChangeRequestId(siteRequest_, HostCheckCR.staticSetChangeRequestId(siteRequest_, o)).toString();
  }

  public String sqlChangeRequestId() {
    return changeRequestId;
  }

  public static String staticJsonChangeRequestId(String changeRequestId) {
    return changeRequestId;
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
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.hostcheck.cr.HostCheckCR&fq=entiteVar_enUS_indexed_string:createdByEmail">Find the entity createdByEmail in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _createdByEmail(Wrap<String> w);

  public String getCreatedByEmail() {
    return createdByEmail;
  }
  public void setCreatedByEmail(String o) {
    this.createdByEmail = HostCheckCR.staticSetCreatedByEmail(siteRequest_, o);
  }
  public static String staticSetCreatedByEmail(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected HostCheckCR createdByEmailInit() {
    Wrap<String> createdByEmailWrap = new Wrap<String>().var("createdByEmail");
    if(createdByEmail == null) {
      _createdByEmail(createdByEmailWrap);
      Optional.ofNullable(createdByEmailWrap.getO()).ifPresent(o -> {
        setCreatedByEmail(o);
      });
    }
    return (HostCheckCR)this;
  }

  public static String staticSearchCreatedByEmail(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrCreatedByEmail(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqCreatedByEmail(SiteRequest siteRequest_, String o) {
    return HostCheckCR.staticSearchCreatedByEmail(siteRequest_, HostCheckCR.staticSetCreatedByEmail(siteRequest_, o)).toString();
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
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.hostcheck.cr.HostCheckCR&fq=entiteVar_enUS_indexed_string:createdByUserId">Find the entity createdByUserId in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _createdByUserId(Wrap<String> w);

  public String getCreatedByUserId() {
    return createdByUserId;
  }
  public void setCreatedByUserId(String o) {
    this.createdByUserId = HostCheckCR.staticSetCreatedByUserId(siteRequest_, o);
  }
  public static String staticSetCreatedByUserId(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected HostCheckCR createdByUserIdInit() {
    Wrap<String> createdByUserIdWrap = new Wrap<String>().var("createdByUserId");
    if(createdByUserId == null) {
      _createdByUserId(createdByUserIdWrap);
      Optional.ofNullable(createdByUserIdWrap.getO()).ifPresent(o -> {
        setCreatedByUserId(o);
      });
    }
    return (HostCheckCR)this;
  }

  public static String staticSearchCreatedByUserId(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrCreatedByUserId(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqCreatedByUserId(SiteRequest siteRequest_, String o) {
    return HostCheckCR.staticSearchCreatedByUserId(siteRequest_, HostCheckCR.staticSetCreatedByUserId(siteRequest_, o)).toString();
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
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.hostcheck.cr.HostCheckCR&fq=entiteVar_enUS_indexed_string:createdByFullName">Find the entity createdByFullName in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _createdByFullName(Wrap<String> w);

  public String getCreatedByFullName() {
    return createdByFullName;
  }
  public void setCreatedByFullName(String o) {
    this.createdByFullName = HostCheckCR.staticSetCreatedByFullName(siteRequest_, o);
  }
  public static String staticSetCreatedByFullName(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected HostCheckCR createdByFullNameInit() {
    Wrap<String> createdByFullNameWrap = new Wrap<String>().var("createdByFullName");
    if(createdByFullName == null) {
      _createdByFullName(createdByFullNameWrap);
      Optional.ofNullable(createdByFullNameWrap.getO()).ifPresent(o -> {
        setCreatedByFullName(o);
      });
    }
    return (HostCheckCR)this;
  }

  public static String staticSearchCreatedByFullName(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrCreatedByFullName(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqCreatedByFullName(SiteRequest siteRequest_, String o) {
    return HostCheckCR.staticSearchCreatedByFullName(siteRequest_, HostCheckCR.staticSetCreatedByFullName(siteRequest_, o)).toString();
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
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.hostcheck.cr.HostCheckCR&fq=entiteVar_enUS_indexed_string:createdVia">Find the entity createdVia in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _createdVia(Wrap<String> w);

  public String getCreatedVia() {
    return createdVia;
  }
  public void setCreatedVia(String o) {
    this.createdVia = HostCheckCR.staticSetCreatedVia(siteRequest_, o);
  }
  public static String staticSetCreatedVia(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected HostCheckCR createdViaInit() {
    Wrap<String> createdViaWrap = new Wrap<String>().var("createdVia");
    if(createdVia == null) {
      _createdVia(createdViaWrap);
      Optional.ofNullable(createdViaWrap.getO()).ifPresent(o -> {
        setCreatedVia(o);
      });
    }
    return (HostCheckCR)this;
  }

  public static String staticSearchCreatedVia(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrCreatedVia(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqCreatedVia(SiteRequest siteRequest_, String o) {
    return HostCheckCR.staticSearchCreatedVia(siteRequest_, HostCheckCR.staticSetCreatedVia(siteRequest_, o)).toString();
  }

  public String sqlCreatedVia() {
    return createdVia;
  }

  public static String staticJsonCreatedVia(String createdVia) {
    return createdVia;
  }

	//////////////////
  // ownedByEmail //
	//////////////////


  /**
   *  The entity ownedByEmail
   *	 is defined as null before being initialized. 
   */
  @JsonProperty
  @JsonInclude(Include.NON_NULL)
  protected String ownedByEmail;

  /**
   * <br> The entity ownedByEmail
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.hostcheck.cr.HostCheckCR&fq=entiteVar_enUS_indexed_string:ownedByEmail">Find the entity ownedByEmail in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _ownedByEmail(Wrap<String> w);

  public String getOwnedByEmail() {
    return ownedByEmail;
  }
  public void setOwnedByEmail(String o) {
    this.ownedByEmail = HostCheckCR.staticSetOwnedByEmail(siteRequest_, o);
  }
  public static String staticSetOwnedByEmail(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected HostCheckCR ownedByEmailInit() {
    Wrap<String> ownedByEmailWrap = new Wrap<String>().var("ownedByEmail");
    if(ownedByEmail == null) {
      _ownedByEmail(ownedByEmailWrap);
      Optional.ofNullable(ownedByEmailWrap.getO()).ifPresent(o -> {
        setOwnedByEmail(o);
      });
    }
    return (HostCheckCR)this;
  }

  public static String staticSearchOwnedByEmail(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrOwnedByEmail(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqOwnedByEmail(SiteRequest siteRequest_, String o) {
    return HostCheckCR.staticSearchOwnedByEmail(siteRequest_, HostCheckCR.staticSetOwnedByEmail(siteRequest_, o)).toString();
  }

  public String sqlOwnedByEmail() {
    return ownedByEmail;
  }

  public static String staticJsonOwnedByEmail(String ownedByEmail) {
    return ownedByEmail;
  }

	///////////////////
  // ownedByUserId //
	///////////////////


  /**
   *  The entity ownedByUserId
   *	 is defined as null before being initialized. 
   */
  @JsonProperty
  @JsonInclude(Include.NON_NULL)
  protected String ownedByUserId;

  /**
   * <br> The entity ownedByUserId
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.hostcheck.cr.HostCheckCR&fq=entiteVar_enUS_indexed_string:ownedByUserId">Find the entity ownedByUserId in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _ownedByUserId(Wrap<String> w);

  public String getOwnedByUserId() {
    return ownedByUserId;
  }
  public void setOwnedByUserId(String o) {
    this.ownedByUserId = HostCheckCR.staticSetOwnedByUserId(siteRequest_, o);
  }
  public static String staticSetOwnedByUserId(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected HostCheckCR ownedByUserIdInit() {
    Wrap<String> ownedByUserIdWrap = new Wrap<String>().var("ownedByUserId");
    if(ownedByUserId == null) {
      _ownedByUserId(ownedByUserIdWrap);
      Optional.ofNullable(ownedByUserIdWrap.getO()).ifPresent(o -> {
        setOwnedByUserId(o);
      });
    }
    return (HostCheckCR)this;
  }

  public static String staticSearchOwnedByUserId(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrOwnedByUserId(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqOwnedByUserId(SiteRequest siteRequest_, String o) {
    return HostCheckCR.staticSearchOwnedByUserId(siteRequest_, HostCheckCR.staticSetOwnedByUserId(siteRequest_, o)).toString();
  }

  public String sqlOwnedByUserId() {
    return ownedByUserId;
  }

  public static String staticJsonOwnedByUserId(String ownedByUserId) {
    return ownedByUserId;
  }

	////////////////////////
  // ownedByDisplayName //
	////////////////////////


  /**
   *  The entity ownedByDisplayName
   *	 is defined as null before being initialized. 
   */
  @JsonProperty
  @JsonInclude(Include.NON_NULL)
  protected String ownedByDisplayName;

  /**
   * <br> The entity ownedByDisplayName
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.hostcheck.cr.HostCheckCR&fq=entiteVar_enUS_indexed_string:ownedByDisplayName">Find the entity ownedByDisplayName in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _ownedByDisplayName(Wrap<String> w);

  public String getOwnedByDisplayName() {
    return ownedByDisplayName;
  }
  public void setOwnedByDisplayName(String o) {
    this.ownedByDisplayName = HostCheckCR.staticSetOwnedByDisplayName(siteRequest_, o);
  }
  public static String staticSetOwnedByDisplayName(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected HostCheckCR ownedByDisplayNameInit() {
    Wrap<String> ownedByDisplayNameWrap = new Wrap<String>().var("ownedByDisplayName");
    if(ownedByDisplayName == null) {
      _ownedByDisplayName(ownedByDisplayNameWrap);
      Optional.ofNullable(ownedByDisplayNameWrap.getO()).ifPresent(o -> {
        setOwnedByDisplayName(o);
      });
    }
    return (HostCheckCR)this;
  }

  public static String staticSearchOwnedByDisplayName(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrOwnedByDisplayName(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqOwnedByDisplayName(SiteRequest siteRequest_, String o) {
    return HostCheckCR.staticSearchOwnedByDisplayName(siteRequest_, HostCheckCR.staticSetOwnedByDisplayName(siteRequest_, o)).toString();
  }

  public String sqlOwnedByDisplayName() {
    return ownedByDisplayName;
  }

  public static String staticJsonOwnedByDisplayName(String ownedByDisplayName) {
    return ownedByDisplayName;
  }

	////////////////////
  // lifecycleState //
	////////////////////


  /**
   *  The entity lifecycleState
   *	 is defined as null before being initialized. 
   */
  @JsonProperty
  @JsonInclude(Include.NON_NULL)
  protected String lifecycleState;

  /**
   * <br> The entity lifecycleState
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.hostcheck.cr.HostCheckCR&fq=entiteVar_enUS_indexed_string:lifecycleState">Find the entity lifecycleState in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _lifecycleState(Wrap<String> w);

  public String getLifecycleState() {
    return lifecycleState;
  }
  public void setLifecycleState(String o) {
    this.lifecycleState = HostCheckCR.staticSetLifecycleState(siteRequest_, o);
  }
  public static String staticSetLifecycleState(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected HostCheckCR lifecycleStateInit() {
    Wrap<String> lifecycleStateWrap = new Wrap<String>().var("lifecycleState");
    if(lifecycleState == null) {
      _lifecycleState(lifecycleStateWrap);
      Optional.ofNullable(lifecycleStateWrap.getO()).ifPresent(o -> {
        setLifecycleState(o);
      });
    }
    return (HostCheckCR)this;
  }

  public static String staticSearchLifecycleState(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrLifecycleState(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqLifecycleState(SiteRequest siteRequest_, String o) {
    return HostCheckCR.staticSearchLifecycleState(siteRequest_, HostCheckCR.staticSetLifecycleState(siteRequest_, o)).toString();
  }

  public String sqlLifecycleState() {
    return lifecycleState;
  }

  public static String staticJsonLifecycleState(String lifecycleState) {
    return lifecycleState;
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
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.hostcheck.cr.HostCheckCR&fq=entiteVar_enUS_indexed_string:intentState">Find the entity intentState in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _intentState(Wrap<String> w);

  public String getIntentState() {
    return intentState;
  }
  public void setIntentState(String o) {
    this.intentState = HostCheckCR.staticSetIntentState(siteRequest_, o);
  }
  public static String staticSetIntentState(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected HostCheckCR intentStateInit() {
    Wrap<String> intentStateWrap = new Wrap<String>().var("intentState");
    if(intentState == null) {
      _intentState(intentStateWrap);
      Optional.ofNullable(intentStateWrap.getO()).ifPresent(o -> {
        setIntentState(o);
      });
    }
    return (HostCheckCR)this;
  }

  public static String staticSearchIntentState(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrIntentState(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqIntentState(SiteRequest siteRequest_, String o) {
    return HostCheckCR.staticSearchIntentState(siteRequest_, HostCheckCR.staticSetIntentState(siteRequest_, o)).toString();
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
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.hostcheck.cr.HostCheckCR&fq=entiteVar_enUS_indexed_string:requestedState">Find the entity requestedState in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _requestedState(Wrap<String> w);

  public String getRequestedState() {
    return requestedState;
  }
  public void setRequestedState(String o) {
    this.requestedState = HostCheckCR.staticSetRequestedState(siteRequest_, o);
  }
  public static String staticSetRequestedState(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected HostCheckCR requestedStateInit() {
    Wrap<String> requestedStateWrap = new Wrap<String>().var("requestedState");
    if(requestedState == null) {
      _requestedState(requestedStateWrap);
      Optional.ofNullable(requestedStateWrap.getO()).ifPresent(o -> {
        setRequestedState(o);
      });
    }
    return (HostCheckCR)this;
  }

  public static String staticSearchRequestedState(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrRequestedState(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqRequestedState(SiteRequest siteRequest_, String o) {
    return HostCheckCR.staticSearchRequestedState(siteRequest_, HostCheckCR.staticSetRequestedState(siteRequest_, o)).toString();
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
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.hostcheck.cr.HostCheckCR&fq=entiteVar_enUS_indexed_string:realizedState">Find the entity realizedState in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _realizedState(Wrap<String> w);

  public String getRealizedState() {
    return realizedState;
  }
  public void setRealizedState(String o) {
    this.realizedState = HostCheckCR.staticSetRealizedState(siteRequest_, o);
  }
  public static String staticSetRealizedState(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected HostCheckCR realizedStateInit() {
    Wrap<String> realizedStateWrap = new Wrap<String>().var("realizedState");
    if(realizedState == null) {
      _realizedState(realizedStateWrap);
      Optional.ofNullable(realizedStateWrap.getO()).ifPresent(o -> {
        setRealizedState(o);
      });
    }
    return (HostCheckCR)this;
  }

  public static String staticSearchRealizedState(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrRealizedState(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqRealizedState(SiteRequest siteRequest_, String o) {
    return HostCheckCR.staticSearchRealizedState(siteRequest_, HostCheckCR.staticSetRealizedState(siteRequest_, o)).toString();
  }

  public String sqlRealizedState() {
    return realizedState;
  }

  public static String staticJsonRealizedState(String realizedState) {
    return realizedState;
  }

	/////////////////////////////
  // aapOrganizationIdChange //
	/////////////////////////////


  /**
   *  The entity aapOrganizationIdChange
   *	 is defined as null before being initialized. 
   */
  @JsonProperty
  @JsonSerialize(using = ToStringSerializer.class)
  @JsonInclude(Include.NON_NULL)
  protected Long aapOrganizationIdChange;

  /**
   * <br> The entity aapOrganizationIdChange
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.hostcheck.cr.HostCheckCR&fq=entiteVar_enUS_indexed_string:aapOrganizationIdChange">Find the entity aapOrganizationIdChange in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _aapOrganizationIdChange(Wrap<Long> w);

  public Long getAapOrganizationIdChange() {
    return aapOrganizationIdChange;
  }

  public void setAapOrganizationIdChange(Long aapOrganizationIdChange) {
    this.aapOrganizationIdChange = aapOrganizationIdChange;
  }
  @JsonIgnore
  public void setAapOrganizationIdChange(String o) {
    this.aapOrganizationIdChange = HostCheckCR.staticSetAapOrganizationIdChange(siteRequest_, o);
  }
  public static Long staticSetAapOrganizationIdChange(SiteRequest siteRequest_, String o) {
    if(NumberUtils.isParsable(o))
      return Long.parseLong(o);
    return null;
  }
  protected HostCheckCR aapOrganizationIdChangeInit() {
    Wrap<Long> aapOrganizationIdChangeWrap = new Wrap<Long>().var("aapOrganizationIdChange");
    if(aapOrganizationIdChange == null) {
      _aapOrganizationIdChange(aapOrganizationIdChangeWrap);
      Optional.ofNullable(aapOrganizationIdChangeWrap.getO()).ifPresent(o -> {
        setAapOrganizationIdChange(o);
      });
    }
    return (HostCheckCR)this;
  }

  public static Long staticSearchAapOrganizationIdChange(SiteRequest siteRequest_, Long o) {
    return o;
  }

  public static String staticSearchStrAapOrganizationIdChange(SiteRequest siteRequest_, Long o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqAapOrganizationIdChange(SiteRequest siteRequest_, String o) {
    return HostCheckCR.staticSearchAapOrganizationIdChange(siteRequest_, HostCheckCR.staticSetAapOrganizationIdChange(siteRequest_, o)).toString();
  }

  public Long sqlAapOrganizationIdChange() {
    return aapOrganizationIdChange;
  }

  public static String staticJsonAapOrganizationIdChange(Long aapOrganizationIdChange) {
    return Optional.ofNullable(aapOrganizationIdChange).map(v -> v.toString()).orElse(null);
  }

	///////////////////////////////
  // jobTemplateResourceChange //
	///////////////////////////////


  /**
   *  The entity jobTemplateResourceChange
   *	 is defined as null before being initialized. 
   */
  @JsonProperty
  @JsonInclude(Include.NON_NULL)
  protected String jobTemplateResourceChange;

  /**
   * <br> The entity jobTemplateResourceChange
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.hostcheck.cr.HostCheckCR&fq=entiteVar_enUS_indexed_string:jobTemplateResourceChange">Find the entity jobTemplateResourceChange in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _jobTemplateResourceChange(Wrap<String> w);

  public String getJobTemplateResourceChange() {
    return jobTemplateResourceChange;
  }
  public void setJobTemplateResourceChange(String o) {
    this.jobTemplateResourceChange = HostCheckCR.staticSetJobTemplateResourceChange(siteRequest_, o);
  }
  public static String staticSetJobTemplateResourceChange(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected HostCheckCR jobTemplateResourceChangeInit() {
    Wrap<String> jobTemplateResourceChangeWrap = new Wrap<String>().var("jobTemplateResourceChange");
    if(jobTemplateResourceChange == null) {
      _jobTemplateResourceChange(jobTemplateResourceChangeWrap);
      Optional.ofNullable(jobTemplateResourceChangeWrap.getO()).ifPresent(o -> {
        setJobTemplateResourceChange(o);
      });
    }
    return (HostCheckCR)this;
  }

  public static String staticSearchJobTemplateResourceChange(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrJobTemplateResourceChange(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqJobTemplateResourceChange(SiteRequest siteRequest_, String o) {
    return HostCheckCR.staticSearchJobTemplateResourceChange(siteRequest_, HostCheckCR.staticSetJobTemplateResourceChange(siteRequest_, o)).toString();
  }

  public String sqlJobTemplateResourceChange() {
    return jobTemplateResourceChange;
  }

  public static String staticJsonJobTemplateResourceChange(String jobTemplateResourceChange) {
    return jobTemplateResourceChange;
  }

	/////////////////////////
  // jobTemplateIdChange //
	/////////////////////////


  /**
   *  The entity jobTemplateIdChange
   *	 is defined as null before being initialized. 
   */
  @JsonProperty
  @JsonInclude(Include.NON_NULL)
  protected String jobTemplateIdChange;

  /**
   * <br> The entity jobTemplateIdChange
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.hostcheck.cr.HostCheckCR&fq=entiteVar_enUS_indexed_string:jobTemplateIdChange">Find the entity jobTemplateIdChange in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _jobTemplateIdChange(Wrap<String> w);

  public String getJobTemplateIdChange() {
    return jobTemplateIdChange;
  }
  public void setJobTemplateIdChange(String o) {
    this.jobTemplateIdChange = HostCheckCR.staticSetJobTemplateIdChange(siteRequest_, o);
  }
  public static String staticSetJobTemplateIdChange(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected HostCheckCR jobTemplateIdChangeInit() {
    Wrap<String> jobTemplateIdChangeWrap = new Wrap<String>().var("jobTemplateIdChange");
    if(jobTemplateIdChange == null) {
      _jobTemplateIdChange(jobTemplateIdChangeWrap);
      Optional.ofNullable(jobTemplateIdChangeWrap.getO()).ifPresent(o -> {
        setJobTemplateIdChange(o);
      });
    }
    return (HostCheckCR)this;
  }

  public static String staticSearchJobTemplateIdChange(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrJobTemplateIdChange(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqJobTemplateIdChange(SiteRequest siteRequest_, String o) {
    return HostCheckCR.staticSearchJobTemplateIdChange(siteRequest_, HostCheckCR.staticSetJobTemplateIdChange(siteRequest_, o)).toString();
  }

  public String sqlJobTemplateIdChange() {
    return jobTemplateIdChange;
  }

  public static String staticJsonJobTemplateIdChange(String jobTemplateIdChange) {
    return jobTemplateIdChange;
  }

	/////////////////////////
  // aapTemplateIdChange //
	/////////////////////////


  /**
   *  The entity aapTemplateIdChange
   *	 is defined as null before being initialized. 
   */
  @JsonProperty
  @JsonSerialize(using = ToStringSerializer.class)
  @JsonInclude(Include.NON_NULL)
  protected Long aapTemplateIdChange;

  /**
   * <br> The entity aapTemplateIdChange
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.hostcheck.cr.HostCheckCR&fq=entiteVar_enUS_indexed_string:aapTemplateIdChange">Find the entity aapTemplateIdChange in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _aapTemplateIdChange(Wrap<Long> w);

  public Long getAapTemplateIdChange() {
    return aapTemplateIdChange;
  }

  public void setAapTemplateIdChange(Long aapTemplateIdChange) {
    this.aapTemplateIdChange = aapTemplateIdChange;
  }
  @JsonIgnore
  public void setAapTemplateIdChange(String o) {
    this.aapTemplateIdChange = HostCheckCR.staticSetAapTemplateIdChange(siteRequest_, o);
  }
  public static Long staticSetAapTemplateIdChange(SiteRequest siteRequest_, String o) {
    if(NumberUtils.isParsable(o))
      return Long.parseLong(o);
    return null;
  }
  protected HostCheckCR aapTemplateIdChangeInit() {
    Wrap<Long> aapTemplateIdChangeWrap = new Wrap<Long>().var("aapTemplateIdChange");
    if(aapTemplateIdChange == null) {
      _aapTemplateIdChange(aapTemplateIdChangeWrap);
      Optional.ofNullable(aapTemplateIdChangeWrap.getO()).ifPresent(o -> {
        setAapTemplateIdChange(o);
      });
    }
    return (HostCheckCR)this;
  }

  public static Long staticSearchAapTemplateIdChange(SiteRequest siteRequest_, Long o) {
    return o;
  }

  public static String staticSearchStrAapTemplateIdChange(SiteRequest siteRequest_, Long o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqAapTemplateIdChange(SiteRequest siteRequest_, String o) {
    return HostCheckCR.staticSearchAapTemplateIdChange(siteRequest_, HostCheckCR.staticSetAapTemplateIdChange(siteRequest_, o)).toString();
  }

  public Long sqlAapTemplateIdChange() {
    return aapTemplateIdChange;
  }

  public static String staticJsonAapTemplateIdChange(Long aapTemplateIdChange) {
    return Optional.ofNullable(aapTemplateIdChange).map(v -> v.toString()).orElse(null);
  }

	/////////////////////
  // checkNameChange //
	/////////////////////


  /**
   *  The entity checkNameChange
   *	 is defined as null before being initialized. 
   */
  @JsonProperty
  @JsonInclude(Include.NON_NULL)
  protected String checkNameChange;

  /**
   * <br> The entity checkNameChange
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.hostcheck.cr.HostCheckCR&fq=entiteVar_enUS_indexed_string:checkNameChange">Find the entity checkNameChange in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _checkNameChange(Wrap<String> w);

  public String getCheckNameChange() {
    return checkNameChange;
  }
  public void setCheckNameChange(String o) {
    this.checkNameChange = HostCheckCR.staticSetCheckNameChange(siteRequest_, o);
  }
  public static String staticSetCheckNameChange(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected HostCheckCR checkNameChangeInit() {
    Wrap<String> checkNameChangeWrap = new Wrap<String>().var("checkNameChange");
    if(checkNameChange == null) {
      _checkNameChange(checkNameChangeWrap);
      Optional.ofNullable(checkNameChangeWrap.getO()).ifPresent(o -> {
        setCheckNameChange(o);
      });
    }
    return (HostCheckCR)this;
  }

  public static String staticSearchCheckNameChange(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrCheckNameChange(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqCheckNameChange(SiteRequest siteRequest_, String o) {
    return HostCheckCR.staticSearchCheckNameChange(siteRequest_, HostCheckCR.staticSetCheckNameChange(siteRequest_, o)).toString();
  }

  public String sqlCheckNameChange() {
    return checkNameChange;
  }

  public static String staticJsonCheckNameChange(String checkNameChange) {
    return checkNameChange;
  }

	///////////////////
  // checkIdChange //
	///////////////////


  /**
   *  The entity checkIdChange
   *	 is defined as null before being initialized. 
   */
  @JsonProperty
  @JsonInclude(Include.NON_NULL)
  protected String checkIdChange;

  /**
   * <br> The entity checkIdChange
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.hostcheck.cr.HostCheckCR&fq=entiteVar_enUS_indexed_string:checkIdChange">Find the entity checkIdChange in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _checkIdChange(Wrap<String> w);

  public String getCheckIdChange() {
    return checkIdChange;
  }
  public void setCheckIdChange(String o) {
    this.checkIdChange = HostCheckCR.staticSetCheckIdChange(siteRequest_, o);
  }
  public static String staticSetCheckIdChange(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected HostCheckCR checkIdChangeInit() {
    Wrap<String> checkIdChangeWrap = new Wrap<String>().var("checkIdChange");
    if(checkIdChange == null) {
      _checkIdChange(checkIdChangeWrap);
      Optional.ofNullable(checkIdChangeWrap.getO()).ifPresent(o -> {
        setCheckIdChange(o);
      });
    }
    return (HostCheckCR)this;
  }

  public static String staticSearchCheckIdChange(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrCheckIdChange(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqCheckIdChange(SiteRequest siteRequest_, String o) {
    return HostCheckCR.staticSearchCheckIdChange(siteRequest_, HostCheckCR.staticSetCheckIdChange(siteRequest_, o)).toString();
  }

  public String sqlCheckIdChange() {
    return checkIdChange;
  }

  public static String staticJsonCheckIdChange(String checkIdChange) {
    return checkIdChange;
  }

	/////////////////////////
  // checkResourceChange //
	/////////////////////////


  /**
   *  The entity checkResourceChange
   *	 is defined as null before being initialized. 
   */
  @JsonProperty
  @JsonInclude(Include.NON_NULL)
  protected String checkResourceChange;

  /**
   * <br> The entity checkResourceChange
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.hostcheck.cr.HostCheckCR&fq=entiteVar_enUS_indexed_string:checkResourceChange">Find the entity checkResourceChange in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _checkResourceChange(Wrap<String> w);

  public String getCheckResourceChange() {
    return checkResourceChange;
  }
  public void setCheckResourceChange(String o) {
    this.checkResourceChange = HostCheckCR.staticSetCheckResourceChange(siteRequest_, o);
  }
  public static String staticSetCheckResourceChange(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected HostCheckCR checkResourceChangeInit() {
    Wrap<String> checkResourceChangeWrap = new Wrap<String>().var("checkResourceChange");
    if(checkResourceChange == null) {
      _checkResourceChange(checkResourceChangeWrap);
      Optional.ofNullable(checkResourceChangeWrap.getO()).ifPresent(o -> {
        setCheckResourceChange(o);
      });
    }
    return (HostCheckCR)this;
  }

  public static String staticSearchCheckResourceChange(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrCheckResourceChange(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqCheckResourceChange(SiteRequest siteRequest_, String o) {
    return HostCheckCR.staticSearchCheckResourceChange(siteRequest_, HostCheckCR.staticSetCheckResourceChange(siteRequest_, o)).toString();
  }

  public String sqlCheckResourceChange() {
    return checkResourceChange;
  }

  public static String staticJsonCheckResourceChange(String checkResourceChange) {
    return checkResourceChange;
  }

	////////////////////////////
  // checkDescriptionChange //
	////////////////////////////


  /**
   *  The entity checkDescriptionChange
   *	 is defined as null before being initialized. 
   */
  @JsonProperty
  @JsonInclude(Include.NON_NULL)
  protected String checkDescriptionChange;

  /**
   * <br> The entity checkDescriptionChange
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.hostcheck.cr.HostCheckCR&fq=entiteVar_enUS_indexed_string:checkDescriptionChange">Find the entity checkDescriptionChange in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _checkDescriptionChange(Wrap<String> w);

  public String getCheckDescriptionChange() {
    return checkDescriptionChange;
  }
  public void setCheckDescriptionChange(String o) {
    this.checkDescriptionChange = HostCheckCR.staticSetCheckDescriptionChange(siteRequest_, o);
  }
  public static String staticSetCheckDescriptionChange(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected HostCheckCR checkDescriptionChangeInit() {
    Wrap<String> checkDescriptionChangeWrap = new Wrap<String>().var("checkDescriptionChange");
    if(checkDescriptionChange == null) {
      _checkDescriptionChange(checkDescriptionChangeWrap);
      Optional.ofNullable(checkDescriptionChangeWrap.getO()).ifPresent(o -> {
        setCheckDescriptionChange(o);
      });
    }
    return (HostCheckCR)this;
  }

  public static String staticSearchCheckDescriptionChange(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrCheckDescriptionChange(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqCheckDescriptionChange(SiteRequest siteRequest_, String o) {
    return HostCheckCR.staticSearchCheckDescriptionChange(siteRequest_, HostCheckCR.staticSetCheckDescriptionChange(siteRequest_, o)).toString();
  }

  public String sqlCheckDescriptionChange() {
    return checkDescriptionChange;
  }

  public static String staticJsonCheckDescriptionChange(String checkDescriptionChange) {
    return checkDescriptionChange;
  }

	//////////////////////////
  // checkNamespaceChange //
	//////////////////////////


  /**
   *  The entity checkNamespaceChange
   *	 is defined as null before being initialized. 
   */
  @JsonProperty
  @JsonInclude(Include.NON_NULL)
  protected String checkNamespaceChange;

  /**
   * <br> The entity checkNamespaceChange
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.hostcheck.cr.HostCheckCR&fq=entiteVar_enUS_indexed_string:checkNamespaceChange">Find the entity checkNamespaceChange in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _checkNamespaceChange(Wrap<String> w);

  public String getCheckNamespaceChange() {
    return checkNamespaceChange;
  }
  public void setCheckNamespaceChange(String o) {
    this.checkNamespaceChange = HostCheckCR.staticSetCheckNamespaceChange(siteRequest_, o);
  }
  public static String staticSetCheckNamespaceChange(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected HostCheckCR checkNamespaceChangeInit() {
    Wrap<String> checkNamespaceChangeWrap = new Wrap<String>().var("checkNamespaceChange");
    if(checkNamespaceChange == null) {
      _checkNamespaceChange(checkNamespaceChangeWrap);
      Optional.ofNullable(checkNamespaceChangeWrap.getO()).ifPresent(o -> {
        setCheckNamespaceChange(o);
      });
    }
    return (HostCheckCR)this;
  }

  public static String staticSearchCheckNamespaceChange(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrCheckNamespaceChange(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqCheckNamespaceChange(SiteRequest siteRequest_, String o) {
    return HostCheckCR.staticSearchCheckNamespaceChange(siteRequest_, HostCheckCR.staticSetCheckNamespaceChange(siteRequest_, o)).toString();
  }

  public String sqlCheckNamespaceChange() {
    return checkNamespaceChange;
  }

  public static String staticJsonCheckNamespaceChange(String checkNamespaceChange) {
    return checkNamespaceChange;
  }

	////////////////////////
  // checkCommandChange //
	////////////////////////


  /**
   *  The entity checkCommandChange
   *	 is defined as null before being initialized. 
   */
  @JsonProperty
  @JsonInclude(Include.NON_NULL)
  protected String checkCommandChange;

  /**
   * <br> The entity checkCommandChange
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.hostcheck.cr.HostCheckCR&fq=entiteVar_enUS_indexed_string:checkCommandChange">Find the entity checkCommandChange in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _checkCommandChange(Wrap<String> w);

  public String getCheckCommandChange() {
    return checkCommandChange;
  }
  public void setCheckCommandChange(String o) {
    this.checkCommandChange = HostCheckCR.staticSetCheckCommandChange(siteRequest_, o);
  }
  public static String staticSetCheckCommandChange(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected HostCheckCR checkCommandChangeInit() {
    Wrap<String> checkCommandChangeWrap = new Wrap<String>().var("checkCommandChange");
    if(checkCommandChange == null) {
      _checkCommandChange(checkCommandChangeWrap);
      Optional.ofNullable(checkCommandChangeWrap.getO()).ifPresent(o -> {
        setCheckCommandChange(o);
      });
    }
    return (HostCheckCR)this;
  }

  public static String staticSearchCheckCommandChange(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrCheckCommandChange(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqCheckCommandChange(SiteRequest siteRequest_, String o) {
    return HostCheckCR.staticSearchCheckCommandChange(siteRequest_, HostCheckCR.staticSetCheckCommandChange(siteRequest_, o)).toString();
  }

  public String sqlCheckCommandChange() {
    return checkCommandChange;
  }

  public static String staticJsonCheckCommandChange(String checkCommandChange) {
    return checkCommandChange;
  }

	/////////////////////////
  // checkIntervalChange //
	/////////////////////////


  /**
   *  The entity checkIntervalChange
   *	 is defined as null before being initialized. 
   */
  @JsonProperty
  @JsonSerialize(using = ToStringSerializer.class)
  @JsonInclude(Include.NON_NULL)
  protected Integer checkIntervalChange;

  /**
   * <br> The entity checkIntervalChange
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.hostcheck.cr.HostCheckCR&fq=entiteVar_enUS_indexed_string:checkIntervalChange">Find the entity checkIntervalChange in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _checkIntervalChange(Wrap<Integer> w);

  public Integer getCheckIntervalChange() {
    return checkIntervalChange;
  }

  public void setCheckIntervalChange(Integer checkIntervalChange) {
    this.checkIntervalChange = checkIntervalChange;
  }
  @JsonIgnore
  public void setCheckIntervalChange(String o) {
    this.checkIntervalChange = HostCheckCR.staticSetCheckIntervalChange(siteRequest_, o);
  }
  public static Integer staticSetCheckIntervalChange(SiteRequest siteRequest_, String o) {
    if(NumberUtils.isParsable(o))
      return Integer.parseInt(o);
    return null;
  }
  protected HostCheckCR checkIntervalChangeInit() {
    Wrap<Integer> checkIntervalChangeWrap = new Wrap<Integer>().var("checkIntervalChange");
    if(checkIntervalChange == null) {
      _checkIntervalChange(checkIntervalChangeWrap);
      Optional.ofNullable(checkIntervalChangeWrap.getO()).ifPresent(o -> {
        setCheckIntervalChange(o);
      });
    }
    return (HostCheckCR)this;
  }

  public static Integer staticSearchCheckIntervalChange(SiteRequest siteRequest_, Integer o) {
    return o;
  }

  public static String staticSearchStrCheckIntervalChange(SiteRequest siteRequest_, Integer o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqCheckIntervalChange(SiteRequest siteRequest_, String o) {
    return HostCheckCR.staticSearchCheckIntervalChange(siteRequest_, HostCheckCR.staticSetCheckIntervalChange(siteRequest_, o)).toString();
  }

  public Integer sqlCheckIntervalChange() {
    return checkIntervalChange;
  }

  public static String staticJsonCheckIntervalChange(Integer checkIntervalChange) {
    return Optional.ofNullable(checkIntervalChange).map(v -> v.toString()).orElse(null);
  }

	//////////////////////////
  // checkPublishedChange //
	//////////////////////////


  /**
   *  The entity checkPublishedChange
   *	 is defined as null before being initialized. 
   */
  @JsonProperty
  @JsonInclude(Include.NON_NULL)
  protected Boolean checkPublishedChange;

  /**
   * <br> The entity checkPublishedChange
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.hostcheck.cr.HostCheckCR&fq=entiteVar_enUS_indexed_string:checkPublishedChange">Find the entity checkPublishedChange in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _checkPublishedChange(Wrap<Boolean> w);

  public Boolean getCheckPublishedChange() {
    return checkPublishedChange;
  }

  public void setCheckPublishedChange(Boolean checkPublishedChange) {
    this.checkPublishedChange = checkPublishedChange;
  }
  @JsonIgnore
  public void setCheckPublishedChange(String o) {
    this.checkPublishedChange = HostCheckCR.staticSetCheckPublishedChange(siteRequest_, o);
  }
  public static Boolean staticSetCheckPublishedChange(SiteRequest siteRequest_, String o) {
    return Boolean.parseBoolean(o);
  }
  protected HostCheckCR checkPublishedChangeInit() {
    Wrap<Boolean> checkPublishedChangeWrap = new Wrap<Boolean>().var("checkPublishedChange");
    if(checkPublishedChange == null) {
      _checkPublishedChange(checkPublishedChangeWrap);
      Optional.ofNullable(checkPublishedChangeWrap.getO()).ifPresent(o -> {
        setCheckPublishedChange(o);
      });
    }
    return (HostCheckCR)this;
  }

  public static Boolean staticSearchCheckPublishedChange(SiteRequest siteRequest_, Boolean o) {
    return o;
  }

  public static String staticSearchStrCheckPublishedChange(SiteRequest siteRequest_, Boolean o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqCheckPublishedChange(SiteRequest siteRequest_, String o) {
    return HostCheckCR.staticSearchCheckPublishedChange(siteRequest_, HostCheckCR.staticSetCheckPublishedChange(siteRequest_, o)).toString();
  }

  public Boolean sqlCheckPublishedChange() {
    return checkPublishedChange;
  }

  public static Boolean staticJsonCheckPublishedChange(Boolean checkPublishedChange) {
    return checkPublishedChange;
  }

	//////////////////////////////
  // eventSubscriptionsChange //
	//////////////////////////////


  /**
   *  The entity eventSubscriptionsChange
   *	 It is constructed before being initialized with the constructor by default. 
   */
  @JsonProperty
  @JsonFormat(shape = JsonFormat.Shape.ARRAY)
  @JsonInclude(Include.NON_NULL)
  protected List<String> eventSubscriptionsChange = new ArrayList<String>();

  /**
   * <br> The entity eventSubscriptionsChange
   *  It is constructed before being initialized with the constructor by default. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.hostcheck.cr.HostCheckCR&fq=entiteVar_enUS_indexed_string:eventSubscriptionsChange">Find the entity eventSubscriptionsChange in Solr</a>
   * <br>
   * @param l is the entity already constructed. 
   **/
  protected abstract void _eventSubscriptionsChange(List<String> l);

  public List<String> getEventSubscriptionsChange() {
    return eventSubscriptionsChange;
  }

  public void setEventSubscriptionsChange(List<String> eventSubscriptionsChange) {
    this.eventSubscriptionsChange = eventSubscriptionsChange;
  }
  @JsonIgnore
  public void setEventSubscriptionsChange(String o) {
    String l = HostCheckCR.staticSetEventSubscriptionsChange(siteRequest_, o);
    if(l != null)
      addEventSubscriptionsChange(l);
  }
  public static String staticSetEventSubscriptionsChange(SiteRequest siteRequest_, String o) {
    return o;
  }
  public HostCheckCR addEventSubscriptionsChange(String...objects) {
    for(String o : objects) {
      addEventSubscriptionsChange(o);
    }
    return (HostCheckCR)this;
  }
  public HostCheckCR addEventSubscriptionsChange(String o) {
    if(o != null)
      this.eventSubscriptionsChange.add(o);
    return (HostCheckCR)this;
  }
  @JsonIgnore
  public void setEventSubscriptionsChange(JsonArray objects) {
    eventSubscriptionsChange.clear();
    if(objects == null)
      return;
    for(int i = 0; i < objects.size(); i++) {
      String o = objects.getString(i);
      addEventSubscriptionsChange(o);
    }
  }
  protected HostCheckCR eventSubscriptionsChangeInit() {
    _eventSubscriptionsChange(eventSubscriptionsChange);
    return (HostCheckCR)this;
  }

  public static String staticSearchEventSubscriptionsChange(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrEventSubscriptionsChange(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqEventSubscriptionsChange(SiteRequest siteRequest_, String o) {
    return HostCheckCR.staticSearchEventSubscriptionsChange(siteRequest_, HostCheckCR.staticSetEventSubscriptionsChange(siteRequest_, o)).toString();
  }

  public String[] sqlEventSubscriptionsChange() {
    return eventSubscriptionsChange.stream().map(v -> (String)v).toArray(String[]::new);
  }

  public static JsonArray staticJsonEventSubscriptionsChange(List<String> eventSubscriptionsChange) {
    JsonArray a = new JsonArray();
    eventSubscriptionsChange.stream().forEach(v -> a.add(v.toString()));
    return a;
  }

	/////////////////////////
  // eventHandlersChange //
	/////////////////////////


  /**
   *  The entity eventHandlersChange
   *	 It is constructed before being initialized with the constructor by default. 
   */
  @JsonProperty
  @JsonFormat(shape = JsonFormat.Shape.ARRAY)
  @JsonInclude(Include.NON_NULL)
  protected List<String> eventHandlersChange = new ArrayList<String>();

  /**
   * <br> The entity eventHandlersChange
   *  It is constructed before being initialized with the constructor by default. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.hostcheck.cr.HostCheckCR&fq=entiteVar_enUS_indexed_string:eventHandlersChange">Find the entity eventHandlersChange in Solr</a>
   * <br>
   * @param l is the entity already constructed. 
   **/
  protected abstract void _eventHandlersChange(List<String> l);

  public List<String> getEventHandlersChange() {
    return eventHandlersChange;
  }

  public void setEventHandlersChange(List<String> eventHandlersChange) {
    this.eventHandlersChange = eventHandlersChange;
  }
  @JsonIgnore
  public void setEventHandlersChange(String o) {
    String l = HostCheckCR.staticSetEventHandlersChange(siteRequest_, o);
    if(l != null)
      addEventHandlersChange(l);
  }
  public static String staticSetEventHandlersChange(SiteRequest siteRequest_, String o) {
    return o;
  }
  public HostCheckCR addEventHandlersChange(String...objects) {
    for(String o : objects) {
      addEventHandlersChange(o);
    }
    return (HostCheckCR)this;
  }
  public HostCheckCR addEventHandlersChange(String o) {
    if(o != null)
      this.eventHandlersChange.add(o);
    return (HostCheckCR)this;
  }
  @JsonIgnore
  public void setEventHandlersChange(JsonArray objects) {
    eventHandlersChange.clear();
    if(objects == null)
      return;
    for(int i = 0; i < objects.size(); i++) {
      String o = objects.getString(i);
      addEventHandlersChange(o);
    }
  }
  protected HostCheckCR eventHandlersChangeInit() {
    _eventHandlersChange(eventHandlersChange);
    return (HostCheckCR)this;
  }

  public static String staticSearchEventHandlersChange(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrEventHandlersChange(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqEventHandlersChange(SiteRequest siteRequest_, String o) {
    return HostCheckCR.staticSearchEventHandlersChange(siteRequest_, HostCheckCR.staticSetEventHandlersChange(siteRequest_, o)).toString();
  }

  public String[] sqlEventHandlersChange() {
    return eventHandlersChange.stream().map(v -> (String)v).toArray(String[]::new);
  }

  public static JsonArray staticJsonEventHandlersChange(List<String> eventHandlersChange) {
    JsonArray a = new JsonArray();
    eventHandlersChange.stream().forEach(v -> a.add(v.toString()));
    return a;
  }

  //////////////
  // initDeep //
  //////////////

  public Future<HostCheckCRGen<DEV>> promiseDeepHostCheckCR(SiteRequest siteRequest_) {
    if(this.siteRequest_ == null)
      setSiteRequest_(siteRequest_);
    return promiseDeepHostCheckCR();
  }

  public Future<HostCheckCRGen<DEV>> promiseDeepHostCheckCR() {
    Promise<HostCheckCRGen<DEV>> promise = Promise.promise();
    Promise<Void> promise2 = Promise.promise();
    promiseHostCheckCR(promise2);
    promise2.future().onSuccess(a -> {
      super.promiseDeepHostCheck(siteRequest_).onSuccess(b -> {
        promise.complete(this);
      }).onFailure(ex -> {
        promise.fail(ex);
      });
    }).onFailure(ex -> {
      promise.fail(ex);
    });
    return promise.future();
  }

  public Future<Void> promiseHostCheckCR(Promise<Void> promise) {
    Future.future(a -> a.complete()).compose(a -> {
      Promise<Void> promise2 = Promise.promise();
      try {
        changeRequestIdInit();
        createdByEmailInit();
        createdByUserIdInit();
        createdByFullNameInit();
        createdViaInit();
        ownedByEmailInit();
        ownedByUserIdInit();
        ownedByDisplayNameInit();
        lifecycleStateInit();
        intentStateInit();
        requestedStateInit();
        realizedStateInit();
        aapOrganizationIdChangeInit();
        jobTemplateResourceChangeInit();
        jobTemplateIdChangeInit();
        aapTemplateIdChangeInit();
        checkNameChangeInit();
        checkIdChangeInit();
        checkResourceChangeInit();
        checkDescriptionChangeInit();
        checkNamespaceChangeInit();
        checkCommandChangeInit();
        checkIntervalChangeInit();
        checkPublishedChangeInit();
        eventSubscriptionsChangeInit();
        eventHandlersChangeInit();
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

  @Override public Future<? extends HostCheckCRGen<DEV>> promiseDeepForClass(SiteRequest siteRequest_) {
    return promiseDeepHostCheckCR(siteRequest_);
  }

  /////////////////
  // siteRequest //
  /////////////////

  public void siteRequestHostCheckCR(SiteRequest siteRequest_) {
      super.siteRequestHostCheck(siteRequest_);
  }

  public void siteRequestForClass(SiteRequest siteRequest_) {
    siteRequestHostCheckCR(siteRequest_);
  }

  /////////////
  // obtain //
  /////////////

  @Override public Object obtainForClass(String var) {
    String[] vars = StringUtils.split(var, ".");
    Object o = null;
    for(String v : vars) {
      if(o == null)
        o = obtainHostCheckCR(v);
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
  public Object obtainHostCheckCR(String var) {
    HostCheckCR oHostCheckCR = (HostCheckCR)this;
    switch(var) {
      case "changeRequestId":
        return oHostCheckCR.changeRequestId;
      case "createdByEmail":
        return oHostCheckCR.createdByEmail;
      case "createdByUserId":
        return oHostCheckCR.createdByUserId;
      case "createdByFullName":
        return oHostCheckCR.createdByFullName;
      case "createdVia":
        return oHostCheckCR.createdVia;
      case "ownedByEmail":
        return oHostCheckCR.ownedByEmail;
      case "ownedByUserId":
        return oHostCheckCR.ownedByUserId;
      case "ownedByDisplayName":
        return oHostCheckCR.ownedByDisplayName;
      case "lifecycleState":
        return oHostCheckCR.lifecycleState;
      case "intentState":
        return oHostCheckCR.intentState;
      case "requestedState":
        return oHostCheckCR.requestedState;
      case "realizedState":
        return oHostCheckCR.realizedState;
      case "aapOrganizationIdChange":
        return oHostCheckCR.aapOrganizationIdChange;
      case "jobTemplateResourceChange":
        return oHostCheckCR.jobTemplateResourceChange;
      case "jobTemplateIdChange":
        return oHostCheckCR.jobTemplateIdChange;
      case "aapTemplateIdChange":
        return oHostCheckCR.aapTemplateIdChange;
      case "checkNameChange":
        return oHostCheckCR.checkNameChange;
      case "checkIdChange":
        return oHostCheckCR.checkIdChange;
      case "checkResourceChange":
        return oHostCheckCR.checkResourceChange;
      case "checkDescriptionChange":
        return oHostCheckCR.checkDescriptionChange;
      case "checkNamespaceChange":
        return oHostCheckCR.checkNamespaceChange;
      case "checkCommandChange":
        return oHostCheckCR.checkCommandChange;
      case "checkIntervalChange":
        return oHostCheckCR.checkIntervalChange;
      case "checkPublishedChange":
        return oHostCheckCR.checkPublishedChange;
      case "eventSubscriptionsChange":
        return oHostCheckCR.eventSubscriptionsChange;
      case "eventHandlersChange":
        return oHostCheckCR.eventHandlersChange;
      default:
        return super.obtainHostCheck(var);
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
        o = relateHostCheckCR(v, val);
      else if(o instanceof BaseModel) {
        BaseModel baseModel = (BaseModel)o;
        o = baseModel.relateForClass(v, val);
      }
    }
    return o != null;
  }
  public Object relateHostCheckCR(String var, Object val) {
    HostCheckCR oHostCheckCR = (HostCheckCR)this;
    switch(var) {
      case "jobTemplateResourceChange":
        if(oHostCheckCR.getJobTemplateResourceChange() == null)
          oHostCheckCR.setJobTemplateResourceChange(Optional.ofNullable(val).map(v -> v.toString()).orElse(null));
        if(!saves.contains("jobTemplateResourceChange"))
          saves.add("jobTemplateResourceChange");
        return val;
      default:
        return super.relateHostCheck(var, val);
    }
  }

  ///////////////
  // staticSet //
  ///////////////

  public static Object staticSetForClass(String entityVar, SiteRequest siteRequest_, String v, HostCheckCR o) {
    return staticSetHostCheckCR(entityVar,  siteRequest_, v, o);
  }
  public static Object staticSetHostCheckCR(String entityVar, SiteRequest siteRequest_, String v, HostCheckCR o) {
    switch(entityVar) {
    case "changeRequestId":
      return HostCheckCR.staticSetChangeRequestId(siteRequest_, v);
    case "createdByEmail":
      return HostCheckCR.staticSetCreatedByEmail(siteRequest_, v);
    case "createdByUserId":
      return HostCheckCR.staticSetCreatedByUserId(siteRequest_, v);
    case "createdByFullName":
      return HostCheckCR.staticSetCreatedByFullName(siteRequest_, v);
    case "createdVia":
      return HostCheckCR.staticSetCreatedVia(siteRequest_, v);
    case "ownedByEmail":
      return HostCheckCR.staticSetOwnedByEmail(siteRequest_, v);
    case "ownedByUserId":
      return HostCheckCR.staticSetOwnedByUserId(siteRequest_, v);
    case "ownedByDisplayName":
      return HostCheckCR.staticSetOwnedByDisplayName(siteRequest_, v);
    case "lifecycleState":
      return HostCheckCR.staticSetLifecycleState(siteRequest_, v);
    case "intentState":
      return HostCheckCR.staticSetIntentState(siteRequest_, v);
    case "requestedState":
      return HostCheckCR.staticSetRequestedState(siteRequest_, v);
    case "realizedState":
      return HostCheckCR.staticSetRealizedState(siteRequest_, v);
    case "aapOrganizationIdChange":
      return HostCheckCR.staticSetAapOrganizationIdChange(siteRequest_, v);
    case "jobTemplateResourceChange":
      return HostCheckCR.staticSetJobTemplateResourceChange(siteRequest_, v);
    case "jobTemplateIdChange":
      return HostCheckCR.staticSetJobTemplateIdChange(siteRequest_, v);
    case "aapTemplateIdChange":
      return HostCheckCR.staticSetAapTemplateIdChange(siteRequest_, v);
    case "checkNameChange":
      return HostCheckCR.staticSetCheckNameChange(siteRequest_, v);
    case "checkIdChange":
      return HostCheckCR.staticSetCheckIdChange(siteRequest_, v);
    case "checkResourceChange":
      return HostCheckCR.staticSetCheckResourceChange(siteRequest_, v);
    case "checkDescriptionChange":
      return HostCheckCR.staticSetCheckDescriptionChange(siteRequest_, v);
    case "checkNamespaceChange":
      return HostCheckCR.staticSetCheckNamespaceChange(siteRequest_, v);
    case "checkCommandChange":
      return HostCheckCR.staticSetCheckCommandChange(siteRequest_, v);
    case "checkIntervalChange":
      return HostCheckCR.staticSetCheckIntervalChange(siteRequest_, v);
    case "checkPublishedChange":
      return HostCheckCR.staticSetCheckPublishedChange(siteRequest_, v);
    case "eventSubscriptionsChange":
      return HostCheckCR.staticSetEventSubscriptionsChange(siteRequest_, v);
    case "eventHandlersChange":
      return HostCheckCR.staticSetEventHandlersChange(siteRequest_, v);
      default:
        return HostCheck.staticSetHostCheck(entityVar,  siteRequest_, v, o);
    }
  }

  //////////////////
  // staticSearch //
  //////////////////

  public static Future<HostCheckCR> fqHostCheckCR(SiteRequest siteRequest, String var, Object val) {
    Promise<HostCheckCR> promise = Promise.promise();
    try {
      if(val == null) {
        promise.complete();
      } else {
        SearchList<HostCheckCR> searchList = new SearchList<HostCheckCR>();
        searchList.setStore(true);
        searchList.q("*:*");
        searchList.setC(HostCheckCR.class);
        searchList.fq(String.format("%s:", HostCheckCR.varIndexedHostCheckCR(var)) + SearchTool.escapeQueryChars(val.toString()));
        searchList.promiseDeepForClass(siteRequest).onSuccess(a -> {
          try {
            promise.complete(searchList.getList().stream().findFirst().orElse(null));
          } catch(Throwable ex) {
            LOG.error("Error while querying thehost check change request", ex);
            promise.fail(ex);
          }
        }).onFailure(ex -> {
          LOG.error("Error while querying thehost check change request", ex);
          promise.fail(ex);
        });
      }
    } catch(Throwable ex) {
      LOG.error("Error while querying thehost check change request", ex);
      promise.fail(ex);
    }
    return promise.future();
  }

  public static Object staticSearchForClass(String entityVar, SiteRequest siteRequest_, Object o) {
    return staticSearchHostCheckCR(entityVar,  siteRequest_, o);
  }
  public static Object staticSearchHostCheckCR(String entityVar, SiteRequest siteRequest_, Object o) {
    switch(entityVar) {
    case "changeRequestId":
      return HostCheckCR.staticSearchChangeRequestId(siteRequest_, (String)o);
    case "createdByEmail":
      return HostCheckCR.staticSearchCreatedByEmail(siteRequest_, (String)o);
    case "createdByUserId":
      return HostCheckCR.staticSearchCreatedByUserId(siteRequest_, (String)o);
    case "createdByFullName":
      return HostCheckCR.staticSearchCreatedByFullName(siteRequest_, (String)o);
    case "createdVia":
      return HostCheckCR.staticSearchCreatedVia(siteRequest_, (String)o);
    case "ownedByEmail":
      return HostCheckCR.staticSearchOwnedByEmail(siteRequest_, (String)o);
    case "ownedByUserId":
      return HostCheckCR.staticSearchOwnedByUserId(siteRequest_, (String)o);
    case "ownedByDisplayName":
      return HostCheckCR.staticSearchOwnedByDisplayName(siteRequest_, (String)o);
    case "lifecycleState":
      return HostCheckCR.staticSearchLifecycleState(siteRequest_, (String)o);
    case "intentState":
      return HostCheckCR.staticSearchIntentState(siteRequest_, (String)o);
    case "requestedState":
      return HostCheckCR.staticSearchRequestedState(siteRequest_, (String)o);
    case "realizedState":
      return HostCheckCR.staticSearchRealizedState(siteRequest_, (String)o);
    case "aapOrganizationIdChange":
      return HostCheckCR.staticSearchAapOrganizationIdChange(siteRequest_, (Long)o);
    case "jobTemplateResourceChange":
      return HostCheckCR.staticSearchJobTemplateResourceChange(siteRequest_, (String)o);
    case "jobTemplateIdChange":
      return HostCheckCR.staticSearchJobTemplateIdChange(siteRequest_, (String)o);
    case "aapTemplateIdChange":
      return HostCheckCR.staticSearchAapTemplateIdChange(siteRequest_, (Long)o);
    case "checkNameChange":
      return HostCheckCR.staticSearchCheckNameChange(siteRequest_, (String)o);
    case "checkIdChange":
      return HostCheckCR.staticSearchCheckIdChange(siteRequest_, (String)o);
    case "checkResourceChange":
      return HostCheckCR.staticSearchCheckResourceChange(siteRequest_, (String)o);
    case "checkDescriptionChange":
      return HostCheckCR.staticSearchCheckDescriptionChange(siteRequest_, (String)o);
    case "checkNamespaceChange":
      return HostCheckCR.staticSearchCheckNamespaceChange(siteRequest_, (String)o);
    case "checkCommandChange":
      return HostCheckCR.staticSearchCheckCommandChange(siteRequest_, (String)o);
    case "checkIntervalChange":
      return HostCheckCR.staticSearchCheckIntervalChange(siteRequest_, (Integer)o);
    case "checkPublishedChange":
      return HostCheckCR.staticSearchCheckPublishedChange(siteRequest_, (Boolean)o);
    case "eventSubscriptionsChange":
      return HostCheckCR.staticSearchEventSubscriptionsChange(siteRequest_, (String)o);
    case "eventHandlersChange":
      return HostCheckCR.staticSearchEventHandlersChange(siteRequest_, (String)o);
      default:
        return HostCheck.staticSearchHostCheck(entityVar,  siteRequest_, o);
    }
  }

  ///////////////////
  // staticSearchStr //
  ///////////////////

  public static String staticSearchStrForClass(String entityVar, SiteRequest siteRequest_, Object o) {
    return staticSearchStrHostCheckCR(entityVar,  siteRequest_, o);
  }
  public static String staticSearchStrHostCheckCR(String entityVar, SiteRequest siteRequest_, Object o) {
    switch(entityVar) {
    case "changeRequestId":
      return HostCheckCR.staticSearchStrChangeRequestId(siteRequest_, (String)o);
    case "createdByEmail":
      return HostCheckCR.staticSearchStrCreatedByEmail(siteRequest_, (String)o);
    case "createdByUserId":
      return HostCheckCR.staticSearchStrCreatedByUserId(siteRequest_, (String)o);
    case "createdByFullName":
      return HostCheckCR.staticSearchStrCreatedByFullName(siteRequest_, (String)o);
    case "createdVia":
      return HostCheckCR.staticSearchStrCreatedVia(siteRequest_, (String)o);
    case "ownedByEmail":
      return HostCheckCR.staticSearchStrOwnedByEmail(siteRequest_, (String)o);
    case "ownedByUserId":
      return HostCheckCR.staticSearchStrOwnedByUserId(siteRequest_, (String)o);
    case "ownedByDisplayName":
      return HostCheckCR.staticSearchStrOwnedByDisplayName(siteRequest_, (String)o);
    case "lifecycleState":
      return HostCheckCR.staticSearchStrLifecycleState(siteRequest_, (String)o);
    case "intentState":
      return HostCheckCR.staticSearchStrIntentState(siteRequest_, (String)o);
    case "requestedState":
      return HostCheckCR.staticSearchStrRequestedState(siteRequest_, (String)o);
    case "realizedState":
      return HostCheckCR.staticSearchStrRealizedState(siteRequest_, (String)o);
    case "aapOrganizationIdChange":
      return HostCheckCR.staticSearchStrAapOrganizationIdChange(siteRequest_, (Long)o);
    case "jobTemplateResourceChange":
      return HostCheckCR.staticSearchStrJobTemplateResourceChange(siteRequest_, (String)o);
    case "jobTemplateIdChange":
      return HostCheckCR.staticSearchStrJobTemplateIdChange(siteRequest_, (String)o);
    case "aapTemplateIdChange":
      return HostCheckCR.staticSearchStrAapTemplateIdChange(siteRequest_, (Long)o);
    case "checkNameChange":
      return HostCheckCR.staticSearchStrCheckNameChange(siteRequest_, (String)o);
    case "checkIdChange":
      return HostCheckCR.staticSearchStrCheckIdChange(siteRequest_, (String)o);
    case "checkResourceChange":
      return HostCheckCR.staticSearchStrCheckResourceChange(siteRequest_, (String)o);
    case "checkDescriptionChange":
      return HostCheckCR.staticSearchStrCheckDescriptionChange(siteRequest_, (String)o);
    case "checkNamespaceChange":
      return HostCheckCR.staticSearchStrCheckNamespaceChange(siteRequest_, (String)o);
    case "checkCommandChange":
      return HostCheckCR.staticSearchStrCheckCommandChange(siteRequest_, (String)o);
    case "checkIntervalChange":
      return HostCheckCR.staticSearchStrCheckIntervalChange(siteRequest_, (Integer)o);
    case "checkPublishedChange":
      return HostCheckCR.staticSearchStrCheckPublishedChange(siteRequest_, (Boolean)o);
    case "eventSubscriptionsChange":
      return HostCheckCR.staticSearchStrEventSubscriptionsChange(siteRequest_, (String)o);
    case "eventHandlersChange":
      return HostCheckCR.staticSearchStrEventHandlersChange(siteRequest_, (String)o);
      default:
        return HostCheck.staticSearchStrHostCheck(entityVar,  siteRequest_, o);
    }
  }

  //////////////////
  // staticSearchFq //
  //////////////////

  public static String staticSearchFqForClass(String entityVar, SiteRequest siteRequest_, String o) {
    return staticSearchFqHostCheckCR(entityVar,  siteRequest_, o);
  }
  public static String staticSearchFqHostCheckCR(String entityVar, SiteRequest siteRequest_, String o) {
    switch(entityVar) {
    case "changeRequestId":
      return HostCheckCR.staticSearchFqChangeRequestId(siteRequest_, o);
    case "createdByEmail":
      return HostCheckCR.staticSearchFqCreatedByEmail(siteRequest_, o);
    case "createdByUserId":
      return HostCheckCR.staticSearchFqCreatedByUserId(siteRequest_, o);
    case "createdByFullName":
      return HostCheckCR.staticSearchFqCreatedByFullName(siteRequest_, o);
    case "createdVia":
      return HostCheckCR.staticSearchFqCreatedVia(siteRequest_, o);
    case "ownedByEmail":
      return HostCheckCR.staticSearchFqOwnedByEmail(siteRequest_, o);
    case "ownedByUserId":
      return HostCheckCR.staticSearchFqOwnedByUserId(siteRequest_, o);
    case "ownedByDisplayName":
      return HostCheckCR.staticSearchFqOwnedByDisplayName(siteRequest_, o);
    case "lifecycleState":
      return HostCheckCR.staticSearchFqLifecycleState(siteRequest_, o);
    case "intentState":
      return HostCheckCR.staticSearchFqIntentState(siteRequest_, o);
    case "requestedState":
      return HostCheckCR.staticSearchFqRequestedState(siteRequest_, o);
    case "realizedState":
      return HostCheckCR.staticSearchFqRealizedState(siteRequest_, o);
    case "aapOrganizationIdChange":
      return HostCheckCR.staticSearchFqAapOrganizationIdChange(siteRequest_, o);
    case "jobTemplateResourceChange":
      return HostCheckCR.staticSearchFqJobTemplateResourceChange(siteRequest_, o);
    case "jobTemplateIdChange":
      return HostCheckCR.staticSearchFqJobTemplateIdChange(siteRequest_, o);
    case "aapTemplateIdChange":
      return HostCheckCR.staticSearchFqAapTemplateIdChange(siteRequest_, o);
    case "checkNameChange":
      return HostCheckCR.staticSearchFqCheckNameChange(siteRequest_, o);
    case "checkIdChange":
      return HostCheckCR.staticSearchFqCheckIdChange(siteRequest_, o);
    case "checkResourceChange":
      return HostCheckCR.staticSearchFqCheckResourceChange(siteRequest_, o);
    case "checkDescriptionChange":
      return HostCheckCR.staticSearchFqCheckDescriptionChange(siteRequest_, o);
    case "checkNamespaceChange":
      return HostCheckCR.staticSearchFqCheckNamespaceChange(siteRequest_, o);
    case "checkCommandChange":
      return HostCheckCR.staticSearchFqCheckCommandChange(siteRequest_, o);
    case "checkIntervalChange":
      return HostCheckCR.staticSearchFqCheckIntervalChange(siteRequest_, o);
    case "checkPublishedChange":
      return HostCheckCR.staticSearchFqCheckPublishedChange(siteRequest_, o);
    case "eventSubscriptionsChange":
      return HostCheckCR.staticSearchFqEventSubscriptionsChange(siteRequest_, o);
    case "eventHandlersChange":
      return HostCheckCR.staticSearchFqEventHandlersChange(siteRequest_, o);
      default:
        return HostCheck.staticSearchFqHostCheck(entityVar,  siteRequest_, o);
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
          o = persistHostCheckCR(v, val);
        else if(o instanceof BaseModel) {
          BaseModel oBaseModel = (BaseModel)o;
          o = oBaseModel.persistForClass(v, val);
        }
      }
    }
    return o != null;
  }
  public Object persistHostCheckCR(String var, Object val) {
    String varLower = var.toLowerCase();
      if("changerequestid".equals(varLower)) {
        if(val instanceof String) {
          setChangeRequestId((String)val);
        }
        saves.add("changeRequestId");
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
      } else if("ownedbyemail".equals(varLower)) {
        if(val instanceof String) {
          setOwnedByEmail((String)val);
        }
        saves.add("ownedByEmail");
        return val;
      } else if("ownedbyuserid".equals(varLower)) {
        if(val instanceof String) {
          setOwnedByUserId((String)val);
        }
        saves.add("ownedByUserId");
        return val;
      } else if("ownedbydisplayname".equals(varLower)) {
        if(val instanceof String) {
          setOwnedByDisplayName((String)val);
        }
        saves.add("ownedByDisplayName");
        return val;
      } else if("lifecyclestate".equals(varLower)) {
        if(val instanceof String) {
          setLifecycleState((String)val);
        }
        saves.add("lifecycleState");
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
      } else if("aaporganizationidchange".equals(varLower)) {
        if(val instanceof Long) {
          setAapOrganizationIdChange((Long)val);
        } else {
          setAapOrganizationIdChange(val == null ? null : val.toString());
        }
        saves.add("aapOrganizationIdChange");
        return val;
      } else if("jobtemplateresourcechange".equals(varLower)) {
        if(val instanceof String) {
          setJobTemplateResourceChange((String)val);
        }
        saves.add("jobTemplateResourceChange");
        return val;
      } else if("jobtemplateidchange".equals(varLower)) {
        if(val instanceof String) {
          setJobTemplateIdChange((String)val);
        }
        saves.add("jobTemplateIdChange");
        return val;
      } else if("aaptemplateidchange".equals(varLower)) {
        if(val instanceof Long) {
          setAapTemplateIdChange((Long)val);
        } else {
          setAapTemplateIdChange(val == null ? null : val.toString());
        }
        saves.add("aapTemplateIdChange");
        return val;
      } else if("checknamechange".equals(varLower)) {
        if(val instanceof String) {
          setCheckNameChange((String)val);
        }
        saves.add("checkNameChange");
        return val;
      } else if("checkidchange".equals(varLower)) {
        if(val instanceof String) {
          setCheckIdChange((String)val);
        }
        saves.add("checkIdChange");
        return val;
      } else if("checkresourcechange".equals(varLower)) {
        if(val instanceof String) {
          setCheckResourceChange((String)val);
        }
        saves.add("checkResourceChange");
        return val;
      } else if("checkdescriptionchange".equals(varLower)) {
        if(val instanceof String) {
          setCheckDescriptionChange((String)val);
        }
        saves.add("checkDescriptionChange");
        return val;
      } else if("checknamespacechange".equals(varLower)) {
        if(val instanceof String) {
          setCheckNamespaceChange((String)val);
        }
        saves.add("checkNamespaceChange");
        return val;
      } else if("checkcommandchange".equals(varLower)) {
        if(val instanceof String) {
          setCheckCommandChange((String)val);
        }
        saves.add("checkCommandChange");
        return val;
      } else if("checkintervalchange".equals(varLower)) {
        if(val instanceof Integer) {
          setCheckIntervalChange((Integer)val);
        } else {
          setCheckIntervalChange(val == null ? null : val.toString());
        }
        saves.add("checkIntervalChange");
        return val;
      } else if("checkpublishedchange".equals(varLower)) {
        if(val instanceof Boolean) {
          setCheckPublishedChange((Boolean)val);
        } else {
          setCheckPublishedChange(val == null ? null : val.toString());
        }
        saves.add("checkPublishedChange");
        return val;
      } else if("eventsubscriptionschange".equals(varLower)) {
        if(val instanceof List<?>) {
          ((List<String>)val).stream().forEach(v -> addEventSubscriptionsChange(v));
        } else if(val instanceof String[]) {
          Arrays.asList((String[])val).stream().forEach(v -> addEventSubscriptionsChange((String)v));
        } else if(val instanceof JsonArray) {
          ((JsonArray)val).stream().forEach(v -> addEventSubscriptionsChange(staticSetEventSubscriptionsChange(siteRequest_, v.toString())));
        }
        if(!saves.contains("eventSubscriptionsChange")) {
          saves.add("eventSubscriptionsChange");
        }
        return val;
      } else if("eventhandlerschange".equals(varLower)) {
        if(val instanceof List<?>) {
          ((List<String>)val).stream().forEach(v -> addEventHandlersChange(v));
        } else if(val instanceof String[]) {
          Arrays.asList((String[])val).stream().forEach(v -> addEventHandlersChange((String)v));
        } else if(val instanceof JsonArray) {
          ((JsonArray)val).stream().forEach(v -> addEventHandlersChange(staticSetEventHandlersChange(siteRequest_, v.toString())));
        }
        if(!saves.contains("eventHandlersChange")) {
          saves.add("eventHandlersChange");
        }
        return val;
    } else {
      return super.persistHostCheck(var, val);
    }
  }

  /////////////
  // populate //
  /////////////

  @Override public void populateForClass(SolrResponse.Doc doc) {
    populateHostCheckCR(doc);
  }
  public void populateHostCheckCR(SolrResponse.Doc doc) {
    HostCheckCR oHostCheckCR = (HostCheckCR)this;
    saves = Optional.ofNullable((ArrayList<String>)doc.get("saves_docvalues_strings")).orElse(new ArrayList<String>());
    if(saves != null) {

      if(saves.contains("changeRequestId")) {
        String changeRequestId = (String)doc.get("changeRequestId_docvalues_string");
        if(changeRequestId != null)
          oHostCheckCR.setChangeRequestId(changeRequestId);
      }

      if(saves.contains("createdByEmail")) {
        String createdByEmail = (String)doc.get("createdByEmail_docvalues_string");
        if(createdByEmail != null)
          oHostCheckCR.setCreatedByEmail(createdByEmail);
      }

      if(saves.contains("createdByUserId")) {
        String createdByUserId = (String)doc.get("createdByUserId_docvalues_string");
        if(createdByUserId != null)
          oHostCheckCR.setCreatedByUserId(createdByUserId);
      }

      if(saves.contains("createdByFullName")) {
        String createdByFullName = (String)doc.get("createdByFullName_docvalues_string");
        if(createdByFullName != null)
          oHostCheckCR.setCreatedByFullName(createdByFullName);
      }

      if(saves.contains("createdVia")) {
        String createdVia = (String)doc.get("createdVia_docvalues_string");
        if(createdVia != null)
          oHostCheckCR.setCreatedVia(createdVia);
      }

      if(saves.contains("ownedByEmail")) {
        String ownedByEmail = (String)doc.get("ownedByEmail_docvalues_string");
        if(ownedByEmail != null)
          oHostCheckCR.setOwnedByEmail(ownedByEmail);
      }

      if(saves.contains("ownedByUserId")) {
        String ownedByUserId = (String)doc.get("ownedByUserId_docvalues_string");
        if(ownedByUserId != null)
          oHostCheckCR.setOwnedByUserId(ownedByUserId);
      }

      if(saves.contains("ownedByDisplayName")) {
        String ownedByDisplayName = (String)doc.get("ownedByDisplayName_docvalues_string");
        if(ownedByDisplayName != null)
          oHostCheckCR.setOwnedByDisplayName(ownedByDisplayName);
      }

      if(saves.contains("lifecycleState")) {
        String lifecycleState = (String)doc.get("lifecycleState_docvalues_string");
        if(lifecycleState != null)
          oHostCheckCR.setLifecycleState(lifecycleState);
      }

      if(saves.contains("intentState")) {
        String intentState = (String)doc.get("intentState_docvalues_string");
        if(intentState != null)
          oHostCheckCR.setIntentState(intentState);
      }

      if(saves.contains("requestedState")) {
        String requestedState = (String)doc.get("requestedState_docvalues_string");
        if(requestedState != null)
          oHostCheckCR.setRequestedState(requestedState);
      }

      if(saves.contains("realizedState")) {
        String realizedState = (String)doc.get("realizedState_docvalues_string");
        if(realizedState != null)
          oHostCheckCR.setRealizedState(realizedState);
      }

      if(saves.contains("aapOrganizationIdChange")) {
        Long aapOrganizationIdChange = (Long)doc.get("aapOrganizationIdChange_docvalues_long");
        if(aapOrganizationIdChange != null)
          oHostCheckCR.setAapOrganizationIdChange(aapOrganizationIdChange);
      }

      String jobTemplateResourceChange = (String)doc.get("jobTemplateResourceChange_docvalues_string");
      if(jobTemplateResourceChange != null)
        oHostCheckCR.setJobTemplateResourceChange(jobTemplateResourceChange);

      if(saves.contains("jobTemplateIdChange")) {
        String jobTemplateIdChange = (String)doc.get("jobTemplateIdChange_docvalues_string");
        if(jobTemplateIdChange != null)
          oHostCheckCR.setJobTemplateIdChange(jobTemplateIdChange);
      }

      if(saves.contains("aapTemplateIdChange")) {
        Long aapTemplateIdChange = (Long)doc.get("aapTemplateIdChange_docvalues_long");
        if(aapTemplateIdChange != null)
          oHostCheckCR.setAapTemplateIdChange(aapTemplateIdChange);
      }

      if(saves.contains("checkNameChange")) {
        String checkNameChange = (String)doc.get("checkNameChange_docvalues_string");
        if(checkNameChange != null)
          oHostCheckCR.setCheckNameChange(checkNameChange);
      }

      if(saves.contains("checkIdChange")) {
        String checkIdChange = (String)doc.get("checkIdChange_docvalues_string");
        if(checkIdChange != null)
          oHostCheckCR.setCheckIdChange(checkIdChange);
      }

      if(saves.contains("checkResourceChange")) {
        String checkResourceChange = (String)doc.get("checkResourceChange_docvalues_string");
        if(checkResourceChange != null)
          oHostCheckCR.setCheckResourceChange(checkResourceChange);
      }

      if(saves.contains("checkDescriptionChange")) {
        String checkDescriptionChange = (String)doc.get("checkDescriptionChange_docvalues_string");
        if(checkDescriptionChange != null)
          oHostCheckCR.setCheckDescriptionChange(checkDescriptionChange);
      }

      if(saves.contains("checkNamespaceChange")) {
        String checkNamespaceChange = (String)doc.get("checkNamespaceChange_docvalues_string");
        if(checkNamespaceChange != null)
          oHostCheckCR.setCheckNamespaceChange(checkNamespaceChange);
      }

      if(saves.contains("checkCommandChange")) {
        String checkCommandChange = (String)doc.get("checkCommandChange_docvalues_string");
        if(checkCommandChange != null)
          oHostCheckCR.setCheckCommandChange(checkCommandChange);
      }

      if(saves.contains("checkIntervalChange")) {
        Integer checkIntervalChange = (Integer)doc.get("checkIntervalChange_docvalues_int");
        if(checkIntervalChange != null)
          oHostCheckCR.setCheckIntervalChange(checkIntervalChange);
      }

      if(saves.contains("checkPublishedChange")) {
        Boolean checkPublishedChange = (Boolean)doc.get("checkPublishedChange_docvalues_boolean");
        if(checkPublishedChange != null)
          oHostCheckCR.setCheckPublishedChange(checkPublishedChange);
      }

      if(saves.contains("eventSubscriptionsChange")) {
        List<String> eventSubscriptionsChange = (List<String>)doc.get("eventSubscriptionsChange_docvalues_strings");
        if(eventSubscriptionsChange != null) {
          eventSubscriptionsChange.stream().forEach( v -> {
            oHostCheckCR.eventSubscriptionsChange.add(HostCheckCR.staticSetEventSubscriptionsChange(siteRequest_, v));
          });
        }
      }

      if(saves.contains("eventHandlersChange")) {
        List<String> eventHandlersChange = (List<String>)doc.get("eventHandlersChange_docvalues_strings");
        if(eventHandlersChange != null) {
          eventHandlersChange.stream().forEach( v -> {
            oHostCheckCR.eventHandlersChange.add(HostCheckCR.staticSetEventHandlersChange(siteRequest_, v));
          });
        }
      }
    }

    super.populateHostCheck(doc);
  }

  public void indexHostCheckCR(JsonObject doc) {
    if(changeRequestId != null) {
      doc.put("changeRequestId_docvalues_string", changeRequestId);
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
    if(ownedByEmail != null) {
      doc.put("ownedByEmail_docvalues_string", ownedByEmail);
    }
    if(ownedByUserId != null) {
      doc.put("ownedByUserId_docvalues_string", ownedByUserId);
    }
    if(ownedByDisplayName != null) {
      doc.put("ownedByDisplayName_docvalues_string", ownedByDisplayName);
    }
    if(lifecycleState != null) {
      doc.put("lifecycleState_docvalues_string", lifecycleState);
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
    if(aapOrganizationIdChange != null) {
      doc.put("aapOrganizationIdChange_docvalues_long", aapOrganizationIdChange);
    }
    if(jobTemplateResourceChange != null) {
      doc.put("jobTemplateResourceChange_docvalues_string", jobTemplateResourceChange);
    }
    if(jobTemplateIdChange != null) {
      doc.put("jobTemplateIdChange_docvalues_string", jobTemplateIdChange);
    }
    if(aapTemplateIdChange != null) {
      doc.put("aapTemplateIdChange_docvalues_long", aapTemplateIdChange);
    }
    if(checkNameChange != null) {
      doc.put("checkNameChange_docvalues_string", checkNameChange);
    }
    if(checkIdChange != null) {
      doc.put("checkIdChange_docvalues_string", checkIdChange);
    }
    if(checkResourceChange != null) {
      doc.put("checkResourceChange_docvalues_string", checkResourceChange);
    }
    if(checkDescriptionChange != null) {
      doc.put("checkDescriptionChange_docvalues_string", checkDescriptionChange);
    }
    if(checkNamespaceChange != null) {
      doc.put("checkNamespaceChange_docvalues_string", checkNamespaceChange);
    }
    if(checkCommandChange != null) {
      doc.put("checkCommandChange_docvalues_string", checkCommandChange);
    }
    if(checkIntervalChange != null) {
      doc.put("checkIntervalChange_docvalues_int", checkIntervalChange);
    }
    if(checkPublishedChange != null) {
      doc.put("checkPublishedChange_docvalues_boolean", checkPublishedChange);
    }
    if(eventSubscriptionsChange != null) {
      JsonArray l = new JsonArray();
      doc.put("eventSubscriptionsChange_docvalues_strings", l);
      for(String o : eventSubscriptionsChange) {
        l.add(HostCheckCR.staticSearchEventSubscriptionsChange(siteRequest_, o));
      }
    }
    if(eventHandlersChange != null) {
      JsonArray l = new JsonArray();
      doc.put("eventHandlersChange_docvalues_strings", l);
      for(String o : eventHandlersChange) {
        l.add(HostCheckCR.staticSearchEventHandlersChange(siteRequest_, o));
      }
    }
    super.indexHostCheck(doc);

	}

  public static String varStoredHostCheckCR(String entityVar) {
    switch(entityVar) {
      case "changeRequestId":
        return "changeRequestId_docvalues_string";
      case "createdByEmail":
        return "createdByEmail_docvalues_string";
      case "createdByUserId":
        return "createdByUserId_docvalues_string";
      case "createdByFullName":
        return "createdByFullName_docvalues_string";
      case "createdVia":
        return "createdVia_docvalues_string";
      case "ownedByEmail":
        return "ownedByEmail_docvalues_string";
      case "ownedByUserId":
        return "ownedByUserId_docvalues_string";
      case "ownedByDisplayName":
        return "ownedByDisplayName_docvalues_string";
      case "lifecycleState":
        return "lifecycleState_docvalues_string";
      case "intentState":
        return "intentState_docvalues_string";
      case "requestedState":
        return "requestedState_docvalues_string";
      case "realizedState":
        return "realizedState_docvalues_string";
      case "aapOrganizationIdChange":
        return "aapOrganizationIdChange_docvalues_long";
      case "jobTemplateResourceChange":
        return "jobTemplateResourceChange_docvalues_string";
      case "jobTemplateIdChange":
        return "jobTemplateIdChange_docvalues_string";
      case "aapTemplateIdChange":
        return "aapTemplateIdChange_docvalues_long";
      case "checkNameChange":
        return "checkNameChange_docvalues_string";
      case "checkIdChange":
        return "checkIdChange_docvalues_string";
      case "checkResourceChange":
        return "checkResourceChange_docvalues_string";
      case "checkDescriptionChange":
        return "checkDescriptionChange_docvalues_string";
      case "checkNamespaceChange":
        return "checkNamespaceChange_docvalues_string";
      case "checkCommandChange":
        return "checkCommandChange_docvalues_string";
      case "checkIntervalChange":
        return "checkIntervalChange_docvalues_int";
      case "checkPublishedChange":
        return "checkPublishedChange_docvalues_boolean";
      case "eventSubscriptionsChange":
        return "eventSubscriptionsChange_docvalues_strings";
      case "eventHandlersChange":
        return "eventHandlersChange_docvalues_strings";
      default:
        return HostCheck.varStoredHostCheck(entityVar);
    }
  }

  public static String varIndexedHostCheckCR(String entityVar) {
    switch(entityVar) {
      case "changeRequestId":
        return "changeRequestId_docvalues_string";
      case "createdByEmail":
        return "createdByEmail_docvalues_string";
      case "createdByUserId":
        return "createdByUserId_docvalues_string";
      case "createdByFullName":
        return "createdByFullName_docvalues_string";
      case "createdVia":
        return "createdVia_docvalues_string";
      case "ownedByEmail":
        return "ownedByEmail_docvalues_string";
      case "ownedByUserId":
        return "ownedByUserId_docvalues_string";
      case "ownedByDisplayName":
        return "ownedByDisplayName_docvalues_string";
      case "lifecycleState":
        return "lifecycleState_docvalues_string";
      case "intentState":
        return "intentState_docvalues_string";
      case "requestedState":
        return "requestedState_docvalues_string";
      case "realizedState":
        return "realizedState_docvalues_string";
      case "aapOrganizationIdChange":
        return "aapOrganizationIdChange_docvalues_long";
      case "jobTemplateResourceChange":
        return "jobTemplateResourceChange_docvalues_string";
      case "jobTemplateIdChange":
        return "jobTemplateIdChange_docvalues_string";
      case "aapTemplateIdChange":
        return "aapTemplateIdChange_docvalues_long";
      case "checkNameChange":
        return "checkNameChange_docvalues_string";
      case "checkIdChange":
        return "checkIdChange_docvalues_string";
      case "checkResourceChange":
        return "checkResourceChange_docvalues_string";
      case "checkDescriptionChange":
        return "checkDescriptionChange_docvalues_string";
      case "checkNamespaceChange":
        return "checkNamespaceChange_docvalues_string";
      case "checkCommandChange":
        return "checkCommandChange_docvalues_string";
      case "checkIntervalChange":
        return "checkIntervalChange_docvalues_int";
      case "checkPublishedChange":
        return "checkPublishedChange_docvalues_boolean";
      case "eventSubscriptionsChange":
        return "eventSubscriptionsChange_docvalues_strings";
      case "eventHandlersChange":
        return "eventHandlersChange_docvalues_strings";
      default:
        return HostCheck.varIndexedHostCheck(entityVar);
    }
  }

  public static String searchVarHostCheckCR(String searchVar) {
    switch(searchVar) {
      case "changeRequestId_docvalues_string":
        return "changeRequestId";
      case "createdByEmail_docvalues_string":
        return "createdByEmail";
      case "createdByUserId_docvalues_string":
        return "createdByUserId";
      case "createdByFullName_docvalues_string":
        return "createdByFullName";
      case "createdVia_docvalues_string":
        return "createdVia";
      case "ownedByEmail_docvalues_string":
        return "ownedByEmail";
      case "ownedByUserId_docvalues_string":
        return "ownedByUserId";
      case "ownedByDisplayName_docvalues_string":
        return "ownedByDisplayName";
      case "lifecycleState_docvalues_string":
        return "lifecycleState";
      case "intentState_docvalues_string":
        return "intentState";
      case "requestedState_docvalues_string":
        return "requestedState";
      case "realizedState_docvalues_string":
        return "realizedState";
      case "aapOrganizationIdChange_docvalues_long":
        return "aapOrganizationIdChange";
      case "jobTemplateResourceChange_docvalues_string":
        return "jobTemplateResourceChange";
      case "jobTemplateIdChange_docvalues_string":
        return "jobTemplateIdChange";
      case "aapTemplateIdChange_docvalues_long":
        return "aapTemplateIdChange";
      case "checkNameChange_docvalues_string":
        return "checkNameChange";
      case "checkIdChange_docvalues_string":
        return "checkIdChange";
      case "checkResourceChange_docvalues_string":
        return "checkResourceChange";
      case "checkDescriptionChange_docvalues_string":
        return "checkDescriptionChange";
      case "checkNamespaceChange_docvalues_string":
        return "checkNamespaceChange";
      case "checkCommandChange_docvalues_string":
        return "checkCommandChange";
      case "checkIntervalChange_docvalues_int":
        return "checkIntervalChange";
      case "checkPublishedChange_docvalues_boolean":
        return "checkPublishedChange";
      case "eventSubscriptionsChange_docvalues_strings":
        return "eventSubscriptionsChange";
      case "eventHandlersChange_docvalues_strings":
        return "eventHandlersChange";
      default:
        return HostCheck.searchVarHostCheck(searchVar);
    }
  }

  public static String varSearchHostCheckCR(String entityVar) {
    switch(entityVar) {
      default:
        return HostCheck.varSearchHostCheck(entityVar);
    }
  }

  public static String varSuggestedHostCheckCR(String entityVar) {
    switch(entityVar) {
      default:
        return HostCheck.varSuggestedHostCheck(entityVar);
    }
  }

  /////////////
  // store //
  /////////////

  @Override public void storeForClass(SolrResponse.Doc doc) {
    storeHostCheckCR(doc);
  }
  public void storeHostCheckCR(SolrResponse.Doc doc) {
    HostCheckCR oHostCheckCR = (HostCheckCR)this;
    SiteRequest siteRequest = oHostCheckCR.getSiteRequest_();

    oHostCheckCR.setChangeRequestId(Optional.ofNullable(doc.get("changeRequestId_docvalues_string")).map(v -> v.toString()).orElse(null));
    oHostCheckCR.setCreatedByEmail(Optional.ofNullable(doc.get("createdByEmail_docvalues_string")).map(v -> v.toString()).orElse(null));
    oHostCheckCR.setCreatedByUserId(Optional.ofNullable(doc.get("createdByUserId_docvalues_string")).map(v -> v.toString()).orElse(null));
    oHostCheckCR.setCreatedByFullName(Optional.ofNullable(doc.get("createdByFullName_docvalues_string")).map(v -> v.toString()).orElse(null));
    oHostCheckCR.setCreatedVia(Optional.ofNullable(doc.get("createdVia_docvalues_string")).map(v -> v.toString()).orElse(null));
    oHostCheckCR.setOwnedByEmail(Optional.ofNullable(doc.get("ownedByEmail_docvalues_string")).map(v -> v.toString()).orElse(null));
    oHostCheckCR.setOwnedByUserId(Optional.ofNullable(doc.get("ownedByUserId_docvalues_string")).map(v -> v.toString()).orElse(null));
    oHostCheckCR.setOwnedByDisplayName(Optional.ofNullable(doc.get("ownedByDisplayName_docvalues_string")).map(v -> v.toString()).orElse(null));
    oHostCheckCR.setLifecycleState(Optional.ofNullable(doc.get("lifecycleState_docvalues_string")).map(v -> v.toString()).orElse(null));
    oHostCheckCR.setIntentState(Optional.ofNullable(doc.get("intentState_docvalues_string")).map(v -> v.toString()).orElse(null));
    oHostCheckCR.setRequestedState(Optional.ofNullable(doc.get("requestedState_docvalues_string")).map(v -> v.toString()).orElse(null));
    oHostCheckCR.setRealizedState(Optional.ofNullable(doc.get("realizedState_docvalues_string")).map(v -> v.toString()).orElse(null));
    oHostCheckCR.setAapOrganizationIdChange(Optional.ofNullable(doc.get("aapOrganizationIdChange_docvalues_long")).map(v -> v.toString()).orElse(null));
    oHostCheckCR.setJobTemplateResourceChange(Optional.ofNullable(doc.get("jobTemplateResourceChange_docvalues_string")).map(v -> v.toString()).orElse(null));
    oHostCheckCR.setJobTemplateIdChange(Optional.ofNullable(doc.get("jobTemplateIdChange_docvalues_string")).map(v -> v.toString()).orElse(null));
    oHostCheckCR.setAapTemplateIdChange(Optional.ofNullable(doc.get("aapTemplateIdChange_docvalues_long")).map(v -> v.toString()).orElse(null));
    oHostCheckCR.setCheckNameChange(Optional.ofNullable(doc.get("checkNameChange_docvalues_string")).map(v -> v.toString()).orElse(null));
    oHostCheckCR.setCheckIdChange(Optional.ofNullable(doc.get("checkIdChange_docvalues_string")).map(v -> v.toString()).orElse(null));
    oHostCheckCR.setCheckResourceChange(Optional.ofNullable(doc.get("checkResourceChange_docvalues_string")).map(v -> v.toString()).orElse(null));
    oHostCheckCR.setCheckDescriptionChange(Optional.ofNullable(doc.get("checkDescriptionChange_docvalues_string")).map(v -> v.toString()).orElse(null));
    oHostCheckCR.setCheckNamespaceChange(Optional.ofNullable(doc.get("checkNamespaceChange_docvalues_string")).map(v -> v.toString()).orElse(null));
    oHostCheckCR.setCheckCommandChange(Optional.ofNullable(doc.get("checkCommandChange_docvalues_string")).map(v -> v.toString()).orElse(null));
    oHostCheckCR.setCheckIntervalChange(Optional.ofNullable(doc.get("checkIntervalChange_docvalues_int")).map(v -> v.toString()).orElse(null));
    oHostCheckCR.setCheckPublishedChange(Optional.ofNullable(doc.get("checkPublishedChange_docvalues_boolean")).map(v -> v.toString()).orElse(null));
    Optional.ofNullable((List<?>)doc.get("eventSubscriptionsChange_docvalues_strings")).orElse(Arrays.asList()).stream().filter(v -> v != null).forEach(v -> {
      oHostCheckCR.addEventSubscriptionsChange(HostCheckCR.staticSetEventSubscriptionsChange(siteRequest, v.toString()));
    });
    Optional.ofNullable((List<?>)doc.get("eventHandlersChange_docvalues_strings")).orElse(Arrays.asList()).stream().filter(v -> v != null).forEach(v -> {
      oHostCheckCR.addEventHandlersChange(HostCheckCR.staticSetEventHandlersChange(siteRequest, v.toString()));
    });

    super.storeHostCheck(doc);
  }

  //////////////////
  // apiRequest //
  //////////////////

  public void apiRequestHostCheckCR() {
    ApiRequest apiRequest = Optional.ofNullable(siteRequest_).map(r -> r.getApiRequest_()).orElse(null);
    Object o = Optional.ofNullable(apiRequest).map(ApiRequest::getOriginal).orElse(null);
    if(o != null && o instanceof HostCheckCR) {
      HostCheckCR original = (HostCheckCR)o;
      if(!Objects.equals(changeRequestId, original.getChangeRequestId()))
        apiRequest.addVars("changeRequestId");
      if(!Objects.equals(createdByEmail, original.getCreatedByEmail()))
        apiRequest.addVars("createdByEmail");
      if(!Objects.equals(createdByUserId, original.getCreatedByUserId()))
        apiRequest.addVars("createdByUserId");
      if(!Objects.equals(createdByFullName, original.getCreatedByFullName()))
        apiRequest.addVars("createdByFullName");
      if(!Objects.equals(createdVia, original.getCreatedVia()))
        apiRequest.addVars("createdVia");
      if(!Objects.equals(ownedByEmail, original.getOwnedByEmail()))
        apiRequest.addVars("ownedByEmail");
      if(!Objects.equals(ownedByUserId, original.getOwnedByUserId()))
        apiRequest.addVars("ownedByUserId");
      if(!Objects.equals(ownedByDisplayName, original.getOwnedByDisplayName()))
        apiRequest.addVars("ownedByDisplayName");
      if(!Objects.equals(lifecycleState, original.getLifecycleState()))
        apiRequest.addVars("lifecycleState");
      if(!Objects.equals(intentState, original.getIntentState()))
        apiRequest.addVars("intentState");
      if(!Objects.equals(requestedState, original.getRequestedState()))
        apiRequest.addVars("requestedState");
      if(!Objects.equals(realizedState, original.getRealizedState()))
        apiRequest.addVars("realizedState");
      if(!Objects.equals(aapOrganizationIdChange, original.getAapOrganizationIdChange()))
        apiRequest.addVars("aapOrganizationIdChange");
      if(!Objects.equals(jobTemplateResourceChange, original.getJobTemplateResourceChange()))
        apiRequest.addVars("jobTemplateResourceChange");
      if(!Objects.equals(jobTemplateIdChange, original.getJobTemplateIdChange()))
        apiRequest.addVars("jobTemplateIdChange");
      if(!Objects.equals(aapTemplateIdChange, original.getAapTemplateIdChange()))
        apiRequest.addVars("aapTemplateIdChange");
      if(!Objects.equals(checkNameChange, original.getCheckNameChange()))
        apiRequest.addVars("checkNameChange");
      if(!Objects.equals(checkIdChange, original.getCheckIdChange()))
        apiRequest.addVars("checkIdChange");
      if(!Objects.equals(checkResourceChange, original.getCheckResourceChange()))
        apiRequest.addVars("checkResourceChange");
      if(!Objects.equals(checkDescriptionChange, original.getCheckDescriptionChange()))
        apiRequest.addVars("checkDescriptionChange");
      if(!Objects.equals(checkNamespaceChange, original.getCheckNamespaceChange()))
        apiRequest.addVars("checkNamespaceChange");
      if(!Objects.equals(checkCommandChange, original.getCheckCommandChange()))
        apiRequest.addVars("checkCommandChange");
      if(!Objects.equals(checkIntervalChange, original.getCheckIntervalChange()))
        apiRequest.addVars("checkIntervalChange");
      if(!Objects.equals(checkPublishedChange, original.getCheckPublishedChange()))
        apiRequest.addVars("checkPublishedChange");
      if(!Objects.equals(eventSubscriptionsChange, original.getEventSubscriptionsChange()))
        apiRequest.addVars("eventSubscriptionsChange");
      if(!Objects.equals(eventHandlersChange, original.getEventHandlersChange()))
        apiRequest.addVars("eventHandlersChange");
      super.apiRequestHostCheck();
    }
  }

  //////////////
  // toString //
  //////////////

  @Override public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(super.toString());
    sb.append(Optional.ofNullable(changeRequestId).map(v -> "changeRequestId: \"" + v + "\"\n" ).orElse(""));
    sb.append(Optional.ofNullable(createdByEmail).map(v -> "createdByEmail: \"" + v + "\"\n" ).orElse(""));
    sb.append(Optional.ofNullable(createdByUserId).map(v -> "createdByUserId: \"" + v + "\"\n" ).orElse(""));
    sb.append(Optional.ofNullable(createdByFullName).map(v -> "createdByFullName: \"" + v + "\"\n" ).orElse(""));
    sb.append(Optional.ofNullable(createdVia).map(v -> "createdVia: \"" + v + "\"\n" ).orElse(""));
    sb.append(Optional.ofNullable(ownedByEmail).map(v -> "ownedByEmail: \"" + v + "\"\n" ).orElse(""));
    sb.append(Optional.ofNullable(ownedByUserId).map(v -> "ownedByUserId: \"" + v + "\"\n" ).orElse(""));
    sb.append(Optional.ofNullable(ownedByDisplayName).map(v -> "ownedByDisplayName: \"" + v + "\"\n" ).orElse(""));
    sb.append(Optional.ofNullable(lifecycleState).map(v -> "lifecycleState: \"" + v + "\"\n" ).orElse(""));
    sb.append(Optional.ofNullable(intentState).map(v -> "intentState: \"" + v + "\"\n" ).orElse(""));
    sb.append(Optional.ofNullable(requestedState).map(v -> "requestedState: \"" + v + "\"\n" ).orElse(""));
    sb.append(Optional.ofNullable(realizedState).map(v -> "realizedState: \"" + v + "\"\n" ).orElse(""));
    sb.append(Optional.ofNullable(aapOrganizationIdChange).map(v -> "aapOrganizationIdChange: " + v + "\n").orElse(""));
    sb.append(Optional.ofNullable(jobTemplateResourceChange).map(v -> "jobTemplateResourceChange: \"" + v + "\"\n" ).orElse(""));
    sb.append(Optional.ofNullable(jobTemplateIdChange).map(v -> "jobTemplateIdChange: \"" + v + "\"\n" ).orElse(""));
    sb.append(Optional.ofNullable(aapTemplateIdChange).map(v -> "aapTemplateIdChange: " + v + "\n").orElse(""));
    sb.append(Optional.ofNullable(checkNameChange).map(v -> "checkNameChange: \"" + v + "\"\n" ).orElse(""));
    sb.append(Optional.ofNullable(checkIdChange).map(v -> "checkIdChange: \"" + v + "\"\n" ).orElse(""));
    sb.append(Optional.ofNullable(checkResourceChange).map(v -> "checkResourceChange: \"" + v + "\"\n" ).orElse(""));
    sb.append(Optional.ofNullable(checkDescriptionChange).map(v -> "checkDescriptionChange: \"" + v + "\"\n" ).orElse(""));
    sb.append(Optional.ofNullable(checkNamespaceChange).map(v -> "checkNamespaceChange: \"" + v + "\"\n" ).orElse(""));
    sb.append(Optional.ofNullable(checkCommandChange).map(v -> "checkCommandChange: \"" + v + "\"\n" ).orElse(""));
    sb.append(Optional.ofNullable(checkIntervalChange).map(v -> "checkIntervalChange: " + v + "\n").orElse(""));
    sb.append(Optional.ofNullable(checkPublishedChange).map(v -> "checkPublishedChange: " + v + "\n").orElse(""));
    sb.append(Optional.ofNullable(eventSubscriptionsChange).map(v -> "eventSubscriptionsChange: " + v + "\n").orElse(""));
    sb.append(Optional.ofNullable(eventHandlersChange).map(v -> "eventHandlersChange: " + v + "\n").orElse(""));
    return sb.toString();
  }

  public static final String CLASS_SIMPLE_NAME = "HostCheckCR";
  public static final String CLASS_CANONICAL_NAME = "org.computate.dcm.model.eda.hostcheck.cr.HostCheckCR";
  public static final String CLASS_AUTH_RESOURCE = "HOSTCHECKCR";
  public static final String CLASS_API_ADDRESS_HostCheckCR = "dcm-enUS-HostCheckCR";
  public static String getClassApiAddress() {
    return CLASS_API_ADDRESS_HostCheckCR;
  }
  public static final String VAR_changeRequestId = "changeRequestId";
  public static final String SET_changeRequestId = "setChangeRequestId";
  public static final String VAR_createdByEmail = "createdByEmail";
  public static final String SET_createdByEmail = "setCreatedByEmail";
  public static final String VAR_createdByUserId = "createdByUserId";
  public static final String SET_createdByUserId = "setCreatedByUserId";
  public static final String VAR_createdByFullName = "createdByFullName";
  public static final String SET_createdByFullName = "setCreatedByFullName";
  public static final String VAR_createdVia = "createdVia";
  public static final String SET_createdVia = "setCreatedVia";
  public static final String VAR_ownedByEmail = "ownedByEmail";
  public static final String SET_ownedByEmail = "setOwnedByEmail";
  public static final String VAR_ownedByUserId = "ownedByUserId";
  public static final String SET_ownedByUserId = "setOwnedByUserId";
  public static final String VAR_ownedByDisplayName = "ownedByDisplayName";
  public static final String SET_ownedByDisplayName = "setOwnedByDisplayName";
  public static final String VAR_lifecycleState = "lifecycleState";
  public static final String SET_lifecycleState = "setLifecycleState";
  public static final String VAR_intentState = "intentState";
  public static final String SET_intentState = "setIntentState";
  public static final String VAR_requestedState = "requestedState";
  public static final String SET_requestedState = "setRequestedState";
  public static final String VAR_realizedState = "realizedState";
  public static final String SET_realizedState = "setRealizedState";
  public static final String VAR_aapOrganizationIdChange = "aapOrganizationIdChange";
  public static final String SET_aapOrganizationIdChange = "setAapOrganizationIdChange";
  public static final String VAR_jobTemplateResourceChange = "jobTemplateResourceChange";
  public static final String SET_jobTemplateResourceChange = "setJobTemplateResourceChange";
  public static final String VAR_jobTemplateIdChange = "jobTemplateIdChange";
  public static final String SET_jobTemplateIdChange = "setJobTemplateIdChange";
  public static final String VAR_aapTemplateIdChange = "aapTemplateIdChange";
  public static final String SET_aapTemplateIdChange = "setAapTemplateIdChange";
  public static final String VAR_checkNameChange = "checkNameChange";
  public static final String SET_checkNameChange = "setCheckNameChange";
  public static final String VAR_checkIdChange = "checkIdChange";
  public static final String SET_checkIdChange = "setCheckIdChange";
  public static final String VAR_checkResourceChange = "checkResourceChange";
  public static final String SET_checkResourceChange = "setCheckResourceChange";
  public static final String VAR_checkDescriptionChange = "checkDescriptionChange";
  public static final String SET_checkDescriptionChange = "setCheckDescriptionChange";
  public static final String VAR_checkNamespaceChange = "checkNamespaceChange";
  public static final String SET_checkNamespaceChange = "setCheckNamespaceChange";
  public static final String VAR_checkCommandChange = "checkCommandChange";
  public static final String SET_checkCommandChange = "setCheckCommandChange";
  public static final String VAR_checkIntervalChange = "checkIntervalChange";
  public static final String SET_checkIntervalChange = "setCheckIntervalChange";
  public static final String VAR_checkPublishedChange = "checkPublishedChange";
  public static final String SET_checkPublishedChange = "setCheckPublishedChange";
  public static final String VAR_eventSubscriptionsChange = "eventSubscriptionsChange";
  public static final String SET_eventSubscriptionsChange = "setEventSubscriptionsChange";
  public static final String VAR_eventHandlersChange = "eventHandlersChange";
  public static final String SET_eventHandlersChange = "setEventHandlersChange";

  public static List<String> varsQForClass() {
    return HostCheckCR.varsQHostCheckCR(new ArrayList<String>());
  }
  public static List<String> varsQHostCheckCR(List<String> vars) {
    HostCheck.varsQHostCheck(vars);
    return vars;
  }

  public static List<String> varsFqForClass() {
    return HostCheckCR.varsFqHostCheckCR(new ArrayList<String>());
  }
  public static List<String> varsFqHostCheckCR(List<String> vars) {
    HostCheck.varsFqHostCheck(vars);
    return vars;
  }

  public static List<String> varsRangeForClass() {
    return HostCheckCR.varsRangeHostCheckCR(new ArrayList<String>());
  }
  public static List<String> varsRangeHostCheckCR(List<String> vars) {
    HostCheck.varsRangeHostCheck(vars);
    return vars;
  }

  public static final String DISPLAY_NAME_changeRequestId = "host check resource";
  public static final String DISPLAY_NAME_createdByEmail = "created by user email";
  public static final String DISPLAY_NAME_createdByUserId = "created by user ID";
  public static final String DISPLAY_NAME_createdByFullName = "created by user full name";
  public static final String DISPLAY_NAME_createdVia = "created via";
  public static final String DISPLAY_NAME_ownedByEmail = "owned by user email";
  public static final String DISPLAY_NAME_ownedByUserId = "owned by user ID";
  public static final String DISPLAY_NAME_ownedByDisplayName = "created by user name";
  public static final String DISPLAY_NAME_lifecycleState = "lifecycle state";
  public static final String DISPLAY_NAME_intentState = "intent state";
  public static final String DISPLAY_NAME_requestedState = "requested state";
  public static final String DISPLAY_NAME_realizedState = "realized state";
  public static final String DISPLAY_NAME_aapOrganizationIdChange = "AAP ID";
  public static final String DISPLAY_NAME_jobTemplateResourceChange = "job template";
  public static final String DISPLAY_NAME_jobTemplateIdChange = "job template ID";
  public static final String DISPLAY_NAME_aapTemplateIdChange = "AAP template ID";
  public static final String DISPLAY_NAME_checkNameChange = "check name";
  public static final String DISPLAY_NAME_checkIdChange = "check ID";
  public static final String DISPLAY_NAME_checkResourceChange = "host check resource";
  public static final String DISPLAY_NAME_checkDescriptionChange = "check description";
  public static final String DISPLAY_NAME_checkNamespaceChange = "check namespace";
  public static final String DISPLAY_NAME_checkCommandChange = "check command";
  public static final String DISPLAY_NAME_checkIntervalChange = "check interval in seconds";
  public static final String DISPLAY_NAME_checkPublishedChange = "check published";
  public static final String DISPLAY_NAME_eventSubscriptionsChange = "event subscriptions";
  public static final String DISPLAY_NAME_eventHandlersChange = "event handlers";

  @Override
  public String idForClass() {
    return changeRequestId;
  }

  @Override
  public String titleForClass() {
    return objectTitle;
  }

  @Override
  public String nameForClass() {
    return checkNameChange;
  }

  @Override
  public String classNameAdjectiveSingularForClass() {
    return HostCheckCR.NameAdjectiveSingular_enUS;
  }

  @Override
  public String descriptionForClass() {
    return checkDescriptionChange;
  }

  @Override
  public String enUSStringFormatUrlEditPageForClass() {
    return "%s/en-us/edit/host-check-cr/%s";
  }

  public static String varJson(String var, Boolean patch) {
    return HostCheckCR.varJsonHostCheckCR(var, patch);
  }
  public static String varJsonHostCheckCR(String var, Boolean patch) {
    switch(var) {
    case VAR_changeRequestId:
      return patch ? SET_changeRequestId : VAR_changeRequestId;
    case VAR_createdByEmail:
      return patch ? SET_createdByEmail : VAR_createdByEmail;
    case VAR_createdByUserId:
      return patch ? SET_createdByUserId : VAR_createdByUserId;
    case VAR_createdByFullName:
      return patch ? SET_createdByFullName : VAR_createdByFullName;
    case VAR_createdVia:
      return patch ? SET_createdVia : VAR_createdVia;
    case VAR_ownedByEmail:
      return patch ? SET_ownedByEmail : VAR_ownedByEmail;
    case VAR_ownedByUserId:
      return patch ? SET_ownedByUserId : VAR_ownedByUserId;
    case VAR_ownedByDisplayName:
      return patch ? SET_ownedByDisplayName : VAR_ownedByDisplayName;
    case VAR_lifecycleState:
      return patch ? SET_lifecycleState : VAR_lifecycleState;
    case VAR_intentState:
      return patch ? SET_intentState : VAR_intentState;
    case VAR_requestedState:
      return patch ? SET_requestedState : VAR_requestedState;
    case VAR_realizedState:
      return patch ? SET_realizedState : VAR_realizedState;
    case VAR_aapOrganizationIdChange:
      return patch ? SET_aapOrganizationIdChange : VAR_aapOrganizationIdChange;
    case VAR_jobTemplateResourceChange:
      return patch ? SET_jobTemplateResourceChange : VAR_jobTemplateResourceChange;
    case VAR_jobTemplateIdChange:
      return patch ? SET_jobTemplateIdChange : VAR_jobTemplateIdChange;
    case VAR_aapTemplateIdChange:
      return patch ? SET_aapTemplateIdChange : VAR_aapTemplateIdChange;
    case VAR_checkNameChange:
      return patch ? SET_checkNameChange : VAR_checkNameChange;
    case VAR_checkIdChange:
      return patch ? SET_checkIdChange : VAR_checkIdChange;
    case VAR_checkResourceChange:
      return patch ? SET_checkResourceChange : VAR_checkResourceChange;
    case VAR_checkDescriptionChange:
      return patch ? SET_checkDescriptionChange : VAR_checkDescriptionChange;
    case VAR_checkNamespaceChange:
      return patch ? SET_checkNamespaceChange : VAR_checkNamespaceChange;
    case VAR_checkCommandChange:
      return patch ? SET_checkCommandChange : VAR_checkCommandChange;
    case VAR_checkIntervalChange:
      return patch ? SET_checkIntervalChange : VAR_checkIntervalChange;
    case VAR_checkPublishedChange:
      return patch ? SET_checkPublishedChange : VAR_checkPublishedChange;
    case VAR_eventSubscriptionsChange:
      return patch ? SET_eventSubscriptionsChange : VAR_eventSubscriptionsChange;
    case VAR_eventHandlersChange:
      return patch ? SET_eventHandlersChange : VAR_eventHandlersChange;
    default:
      return HostCheck.varJsonHostCheck(var, patch);
    }
  }

  public static String displayNameForClass(String var) {
    return HostCheckCR.displayNameHostCheckCR(var);
  }
  public static String displayNameHostCheckCR(String var) {
    switch(var) {
    case VAR_changeRequestId:
      return DISPLAY_NAME_changeRequestId;
    case VAR_createdByEmail:
      return DISPLAY_NAME_createdByEmail;
    case VAR_createdByUserId:
      return DISPLAY_NAME_createdByUserId;
    case VAR_createdByFullName:
      return DISPLAY_NAME_createdByFullName;
    case VAR_createdVia:
      return DISPLAY_NAME_createdVia;
    case VAR_ownedByEmail:
      return DISPLAY_NAME_ownedByEmail;
    case VAR_ownedByUserId:
      return DISPLAY_NAME_ownedByUserId;
    case VAR_ownedByDisplayName:
      return DISPLAY_NAME_ownedByDisplayName;
    case VAR_lifecycleState:
      return DISPLAY_NAME_lifecycleState;
    case VAR_intentState:
      return DISPLAY_NAME_intentState;
    case VAR_requestedState:
      return DISPLAY_NAME_requestedState;
    case VAR_realizedState:
      return DISPLAY_NAME_realizedState;
    case VAR_aapOrganizationIdChange:
      return DISPLAY_NAME_aapOrganizationIdChange;
    case VAR_jobTemplateResourceChange:
      return DISPLAY_NAME_jobTemplateResourceChange;
    case VAR_jobTemplateIdChange:
      return DISPLAY_NAME_jobTemplateIdChange;
    case VAR_aapTemplateIdChange:
      return DISPLAY_NAME_aapTemplateIdChange;
    case VAR_checkNameChange:
      return DISPLAY_NAME_checkNameChange;
    case VAR_checkIdChange:
      return DISPLAY_NAME_checkIdChange;
    case VAR_checkResourceChange:
      return DISPLAY_NAME_checkResourceChange;
    case VAR_checkDescriptionChange:
      return DISPLAY_NAME_checkDescriptionChange;
    case VAR_checkNamespaceChange:
      return DISPLAY_NAME_checkNamespaceChange;
    case VAR_checkCommandChange:
      return DISPLAY_NAME_checkCommandChange;
    case VAR_checkIntervalChange:
      return DISPLAY_NAME_checkIntervalChange;
    case VAR_checkPublishedChange:
      return DISPLAY_NAME_checkPublishedChange;
    case VAR_eventSubscriptionsChange:
      return DISPLAY_NAME_eventSubscriptionsChange;
    case VAR_eventHandlersChange:
      return DISPLAY_NAME_eventHandlersChange;
    default:
      return HostCheck.displayNameHostCheck(var);
    }
  }

  public static String descriptionHostCheckCR(String var) {
    if(var == null)
      return null;
    switch(var) {
    case VAR_changeRequestId:
      return "The unique authorization resource for the host check for multi-tenancy";
    case VAR_createdByEmail:
      return "The email address for the user who created the change request. ";
    case VAR_createdByUserId:
      return "The IdP UUID record for the user who created the change request. ";
    case VAR_createdByFullName:
      return "The first and last name for the user who created the change request. ";
    case VAR_createdVia:
      return "Declares the ingestion path that makes audit quality transparent. ";
    case VAR_ownedByEmail:
      return "The email address for the user who owns the change request. ";
    case VAR_ownedByUserId:
      return "The IdP UUID record for the user who owns the change request. ";
    case VAR_ownedByDisplayName:
      return "The first and last name for the user who owns the change request. ";
    case VAR_lifecycleState:
      return "Every Data artifact is in exactly one lifecycle state at any moment. ";
    case VAR_intentState:
      return "Captures the consumer's raw intent \u2014 what they asked for in their own terms. ";
    case VAR_requestedState:
      return "Represents a complete, validated, provider-ready declaration of desired state. ";
    case VAR_realizedState:
      return "Must be a complete representation of the provisioned resource in DCM unified format \u2014 not a status code, but a full state description. ";
    case VAR_aapOrganizationIdChange:
      return "The Ansible Automation Platform ID of the organization. ";
    case VAR_jobTemplateResourceChange:
      return "The unique authorization resource for the job template for multi-tenancy";
    case VAR_jobTemplateIdChange:
      return "The ID of the job template in DCM. ";
    case VAR_aapTemplateIdChange:
      return "The template ID in Ansible Automation Platform. ";
    case VAR_checkNameChange:
      return "The name of the host check (may only contain letters, numbers, periods, colons, and dashes). ";
    case VAR_checkIdChange:
      return "The ID of the host check in DCM. ";
    case VAR_checkResourceChange:
      return "The unique authorization resource for the host check for multi-tenancy";
    case VAR_checkDescriptionChange:
      return "The descrition of the host check. ";
    case VAR_checkNamespaceChange:
      return "The namespace of the host check. ";
    case VAR_checkCommandChange:
      return "The bash command to run during the check. ";
    case VAR_checkIntervalChange:
      return "The check interval in seconds. ";
    case VAR_checkPublishedChange:
      return "When disabled the check will not be executed unless explicitly queued. ";
    case VAR_eventSubscriptionsChange:
      return "The list of event subscriptions the host check subscribes to. ";
    case VAR_eventHandlersChange:
      return "The list of event handlers the host subscribes to. ";
      default:
        return HostCheck.descriptionHostCheck(var);
    }
  }

  public static String classSimpleNameHostCheckCR(String var) {
    switch(var) {
    case VAR_changeRequestId:
      return "String";
    case VAR_createdByEmail:
      return "String";
    case VAR_createdByUserId:
      return "String";
    case VAR_createdByFullName:
      return "String";
    case VAR_createdVia:
      return "String";
    case VAR_ownedByEmail:
      return "String";
    case VAR_ownedByUserId:
      return "String";
    case VAR_ownedByDisplayName:
      return "String";
    case VAR_lifecycleState:
      return "String";
    case VAR_intentState:
      return "String";
    case VAR_requestedState:
      return "String";
    case VAR_realizedState:
      return "String";
    case VAR_aapOrganizationIdChange:
      return "Long";
    case VAR_jobTemplateResourceChange:
      return "String";
    case VAR_jobTemplateIdChange:
      return "String";
    case VAR_aapTemplateIdChange:
      return "Long";
    case VAR_checkNameChange:
      return "String";
    case VAR_checkIdChange:
      return "String";
    case VAR_checkResourceChange:
      return "String";
    case VAR_checkDescriptionChange:
      return "String";
    case VAR_checkNamespaceChange:
      return "String";
    case VAR_checkCommandChange:
      return "String";
    case VAR_checkIntervalChange:
      return "Integer";
    case VAR_checkPublishedChange:
      return "Boolean";
    case VAR_eventSubscriptionsChange:
      return "List";
    case VAR_eventHandlersChange:
      return "List";
      default:
        return HostCheck.classSimpleNameHostCheck(var);
    }
  }

  public static Integer htmColumnHostCheckCR(String var) {
    switch(var) {
    case VAR_jobTemplateResourceChange:
      return 0;
    case VAR_checkNameChange:
      return 1;
    case VAR_checkDescriptionChange:
      return 2;
    case VAR_eventSubscriptionsChange:
      return 3;
    case VAR_eventHandlersChange:
      return 4;
      default:
        return HostCheck.htmColumnHostCheck(var);
    }
  }

  public static Integer htmRowHostCheckCR(String var) {
    switch(var) {
    case VAR_changeRequestId:
      return 9;
    case VAR_createdByEmail:
      return 10;
    case VAR_createdVia:
      return 10;
    case VAR_ownedByEmail:
      return 11;
    case VAR_lifecycleState:
      return 12;
    case VAR_intentState:
      return 12;
    case VAR_requestedState:
      return 12;
    case VAR_realizedState:
      return 12;
    case VAR_jobTemplateResourceChange:
      return 14;
    case VAR_checkNameChange:
      return 15;
    case VAR_checkDescriptionChange:
      return 15;
    case VAR_checkNamespaceChange:
      return 15;
    case VAR_checkCommandChange:
      return 15;
    case VAR_checkIntervalChange:
      return 15;
    case VAR_checkPublishedChange:
      return 15;
    case VAR_eventSubscriptionsChange:
      return 15;
    case VAR_eventHandlersChange:
      return 15;
      default:
        return HostCheck.htmRowHostCheck(var);
    }
  }

  public static Integer htmCellHostCheckCR(String var) {
    switch(var) {
    case VAR_changeRequestId:
      return 0;
    case VAR_createdByEmail:
      return 0;
    case VAR_createdVia:
      return 0;
    case VAR_ownedByEmail:
      return 0;
    case VAR_lifecycleState:
      return 0;
    case VAR_intentState:
      return 0;
    case VAR_requestedState:
      return 0;
    case VAR_realizedState:
      return 0;
    case VAR_jobTemplateResourceChange:
      return 0;
    case VAR_checkNameChange:
      return 0;
    case VAR_checkDescriptionChange:
      return 1;
    case VAR_checkNamespaceChange:
      return 2;
    case VAR_checkCommandChange:
      return 3;
    case VAR_checkIntervalChange:
      return 4;
    case VAR_checkPublishedChange:
      return 6;
    case VAR_eventSubscriptionsChange:
      return 7;
    case VAR_eventHandlersChange:
      return 8;
      default:
        return HostCheck.htmCellHostCheck(var);
    }
  }

  public static Integer lengthMinHostCheckCR(String var) {
    switch(var) {
      default:
        return HostCheck.lengthMinHostCheck(var);
    }
  }

  public static Integer lengthMaxHostCheckCR(String var) {
    switch(var) {
      default:
        return HostCheck.lengthMaxHostCheck(var);
    }
  }

  public static Integer maxHostCheckCR(String var) {
    switch(var) {
      default:
        return HostCheck.maxHostCheck(var);
    }
  }

  public static Integer minHostCheckCR(String var) {
    switch(var) {
      default:
        return HostCheck.minHostCheck(var);
    }
  }
}
