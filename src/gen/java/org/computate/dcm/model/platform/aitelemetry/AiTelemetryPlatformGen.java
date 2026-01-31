package org.computate.dcm.model.platform.aitelemetry;

import org.computate.dcm.request.SiteRequest;
import org.computate.dcm.result.BaseResult;
import org.computate.dcm.model.BaseModel;
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
import java.lang.Boolean;
import java.lang.String;
import java.lang.Integer;
import org.computate.vertx.search.list.SearchList;
import org.computate.dcm.model.platform.aitelemetry.AiTelemetryPlatform;
import io.vertx.core.json.JsonArray;
import org.computate.vertx.serialize.vertx.JsonArrayDeserializer;
import org.computate.search.wrap.Wrap;
import io.vertx.core.Promise;
import io.vertx.core.Future;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.computate.search.response.solr.SolrResponse;
import io.vertx.core.json.JsonObject;

/**
 * <ol>
<h3>Suggestions that can generate more code for you: </h3> * </ol>
 * <li>You can add a class comment "{@inheritDoc}" if you wish to inherit the helpful inherited class comments from class AiTelemetryPlatformGen into the class AiTelemetryPlatform. 
 * </li><li>You can add a class comment "Rows: 100" if you wish the AiTelemetryPlatform API to return more or less than 10 records by default. 
 * In this case, the API will return 100 records from the API instead of 10 by default. 
 * Each API has built in pagination of the search records to ensure a user can query all the data a page at a time without running the application out of memory. 
 * </li><li>You can add a class comment "Model: true" if you wish to persist these AiTelemetryPlatform objects in a relational PostgreSQL database transactionally in the RESTful API. 
 * The code to persist and query the AiTelemetryPlatformGen data in the database will then be automatically generated. 
 * </li>
 * <h3>About the AiTelemetryPlatform class and it's generated class AiTelemetryPlatformGen&lt;BaseResult&gt;: </h3>extends AiTelemetryPlatformGen
 * <p>
 * This Java class extends a generated Java class AiTelemetryPlatformGen built by the <a href="https://github.com/computate-org/computate">https://github.com/computate-org/computate</a> project. 
 * Whenever this Java class is modified or touched, the watch service installed as described in the README, indexes all the information about this Java class in a local Apache Solr Search Engine. 
 * If you are running the service, you can see the indexed data about this Java Class here: 
 * </p>
 * <p><a href="https://solr.apps-crc.testing/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.platform.aitelemetry.AiTelemetryPlatform">Find the class AiTelemetryPlatform in Solr. </a></p>
 * <p>
 * The extended class ending with "Gen" did not exist at first, but was automatically created by the same watch service based on the data retrieved from the local Apache Server search engine. 
 * The extended class contains many generated fields, getters, setters, initialization code, and helper methods to help build a website and API fast, reactive, and scalable. 
 * </p>
 * extends AiTelemetryPlatformGen<BaseResult>
 * <p>This <code>class AiTelemetryPlatform extends AiTelemetryPlatformGen&lt;BaseResult&gt;</code>, which means it extends a newly generated AiTelemetryPlatformGen. 
 * The generated <code>class AiTelemetryPlatformGen extends BaseResult</code> which means that AiTelemetryPlatform extends AiTelemetryPlatformGen which extends BaseResult. 
 * This generated inheritance is a powerful feature that allows a lot of boiler plate code to be created for you automatically while still preserving inheritance through the power of Java Generic classes. 
 * </p>
 * <h2>Api: true</h2>
 * <p>This class contains a comment <b>"Api: true"</b>, which means this class will have Java Vert.x API backend code generated for these objects. 
 * </p>
 * <h2>ApiTag.enUS: true</h2>
 * <p>This class contains a comment <b>"ApiTag: AI Telemetry developers"</b>, which groups all of the OpenAPIs for AiTelemetryPlatform objects under the tag "AI Telemetry developers". 
 * </p>
 * <h2>ApiUri.enUS: /en-us/api/ai-telemetry-platform</h2>
 * <p>This class contains a comment <b>"ApiUri: /en-us/api/ai-telemetry-platform"</b>, which defines the base API URI for AiTelemetryPlatform objects as "/en-us/api/ai-telemetry-platform" in the OpenAPI spec. 
 * </p>
 * <h2>Color: null</h2>
 * <h2>Indexed: true</h2>
 * <p>This class contains a comment <b>"Indexed: true"</b>, which means this class will be indexed in the search engine. 
 * Every protected void method that begins with "_" that is marked to be searched with a comment like "Indexed: true", "Stored: true", or "DocValues: true" will be indexed in the search engine. 
 * </p>
 * <h2>{@inheritDoc}</h2>
 * <p>By adding a class comment "{@inheritDoc}", the AiTelemetryPlatform class will inherit the helpful inherited class comments from the super class AiTelemetryPlatformGen. 
 * </p>
 * <h2>Rows: null</h2>
 * <h2>Order: 204</h2>
 * <p>This class contains a comment <b>"Order: 204"</b>, which means this class will be sorted by the given number 204 ascending when code that relates to multiple classes at the same time is generated. 
 * </p>
 * <h2>SqlOrder: 204</h2>
 * <p>This class contains a comment <b>"SqlOrder: 204"</b>, which means this class will be sorted by the given number 204 ascending when SQL code to create and drop the tables is generated. 
 * </p>
 * <h2>Model: true</h2>
 * <h2>Page: true</h2>
 * <p>This class contains a comment <b>"Page: true"</b>, which means this class will have webpage code generated for these objects. 
 * Java Vert.x backend API code, Handlebars HTML template frontend code, and JavaScript code will all generated and can be extended. 
 * This creates a new Java class org.computate.dcm.model.platform.aitelemetry.AiTelemetryPlatformPage. 
 * </p>
 * <h2>SuperPage.enUS: PageLayout</h2>
 * <p>This class contains a comment <b>"SuperPage.enUS: PageLayout"</b>, which identifies the Java super class of the page code by it's class simple name "PageLayout". 
 * This means that the newly created class org.computate.dcm.model.platform.aitelemetry.AiTelemetryPlatformPage extends org.computate.dcm.page.PageLayout. 
 * </p>
 * <h2>Promise: true</h2>
 * <p>
 *   This class contains a comment <b>"Promise: true"</b>
 *   Sometimes a Java class must be initialized asynchronously when it involves calling a blocking API. 
 *   This means that the AiTelemetryPlatform Java class has promiseDeep methods which must be initialized asynchronously as a Vert.x Promise  instead of initDeep methods which are a simple non-asynchronous method. 
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
 * <h2>AName.enUS: an AI Telemetry developer</h2>
 * <p>This class contains a comment <b>"AName.enUS: an AI Telemetry developer"</b>, which identifies the language context to describe a AiTelemetryPlatform as "an AI Telemetry developer". 
 * </p>
 * <p>
 * Delete the class AiTelemetryPlatform in Solr: 
 * curl -k 'https://solr.apps-crc.testing/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.platform.aitelemetry.AiTelemetryPlatform&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * <p>
 * Delete  the package org.computate.dcm.model.platform.aitelemetry in Solr: 
 * curl -k 'https://solr.apps-crc.testing/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;classeNomEnsemble_enUS_indexed_string:org.computate.dcm.model.platform.aitelemetry&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * <p>
 * Delete  the project dcm in Solr: 
 * curl -k 'https://solr.apps-crc.testing/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;siteNom_indexed_string:dcm&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * Generated: true
 **/
public abstract class AiTelemetryPlatformGen<DEV> extends BaseResult {
  protected static final Logger LOG = LoggerFactory.getLogger(AiTelemetryPlatform.class);

  public static final String Description_enUS = "Learn how to become an AI Telemetry platform developer — Providing access control to observability metrics for cloud environments, ACM hubs, OpenShift Clusters, virtual machines, bare metal hardware, and cloud projects. ";
  public static final String AName_enUS = "an AI Telemetry developer";
  public static final String This_enUS = "this ";
  public static final String ThisName_enUS = "this AI Telemetry developer";
  public static final String A_enUS = "a ";
  public static final String TheName_enUS = "theAI Telemetry developer";
  public static final String SingularName_enUS = "AI Telemetry developer";
  public static final String PluralName_enUS = "AI Telemetry developers";
  public static final String NameActual_enUS = "current AI Telemetry developer";
  public static final String AllName_enUS = "all AI Telemetry developers";
  public static final String SearchAllNameBy_enUS = "search AI Telemetry developers by ";
  public static final String SearchAllName_enUS = "search AI Telemetry developers";
  public static final String Title_enUS = "AI Telemetry developers";
  public static final String ThePluralName_enUS = "the AI Telemetry developers";
  public static final String NoNameFound_enUS = "no AI Telemetry developer found";
  public static final String ApiUri_enUS = "/en-us/api/ai-telemetry-platform";
  public static final String ApiUriSearchPage_enUS = "/en-us/search/ai-telemetry-platform";
  public static final String ApiUriEditPage_enUS = "/en-us/edit/ai-telemetry-platform/{pageId}";
  public static final String OfName_enUS = "of AI Telemetry developer";
  public static final String ANameAdjective_enUS = "an AI Telemetry developer";
  public static final String NameAdjectiveSingular_enUS = "AI Telemetry developer";
  public static final String NameAdjectivePlural_enUS = "AI Telemetry developers";
  public static final String Search_enUS_OpenApiUri = "/en-us/api/ai-telemetry-platform";
  public static final String Search_enUS_StringFormatUri = "/en-us/api/ai-telemetry-platform";
  public static final String Search_enUS_StringFormatUrl = "%s/en-us/api/ai-telemetry-platform";
  public static final String GET_enUS_OpenApiUri = "/en-us/api/ai-telemetry-platform/{pageId}";
  public static final String GET_enUS_StringFormatUri = "/en-us/api/ai-telemetry-platform/%s";
  public static final String GET_enUS_StringFormatUrl = "%s/en-us/api/ai-telemetry-platform/%s";
  public static final String PATCH_enUS_OpenApiUri = "/en-us/api/ai-telemetry-platform";
  public static final String PATCH_enUS_StringFormatUri = "/en-us/api/ai-telemetry-platform";
  public static final String PATCH_enUS_StringFormatUrl = "%s/en-us/api/ai-telemetry-platform";
  public static final String POST_enUS_OpenApiUri = "/en-us/api/ai-telemetry-platform";
  public static final String POST_enUS_StringFormatUri = "/en-us/api/ai-telemetry-platform";
  public static final String POST_enUS_StringFormatUrl = "%s/en-us/api/ai-telemetry-platform";
  public static final String DELETE_enUS_OpenApiUri = "/en-us/api/ai-telemetry-platform/{pageId}";
  public static final String DELETE_enUS_StringFormatUri = "/en-us/api/ai-telemetry-platform/%s";
  public static final String DELETE_enUS_StringFormatUrl = "%s/en-us/api/ai-telemetry-platform/%s";
  public static final String PUTImport_enUS_OpenApiUri = "/en-us/api/ai-telemetry-platform-import";
  public static final String PUTImport_enUS_StringFormatUri = "/en-us/api/ai-telemetry-platform-import";
  public static final String PUTImport_enUS_StringFormatUrl = "%s/en-us/api/ai-telemetry-platform-import";
  public static final String SearchPage_enUS_OpenApiUri = "/en-us/search/ai-telemetry-platform";
  public static final String SearchPage_enUS_StringFormatUri = "/en-us/search/ai-telemetry-platform";
  public static final String SearchPage_enUS_StringFormatUrl = "%s/en-us/search/ai-telemetry-platform";
  public static final String EditPage_enUS_OpenApiUri = "/en-us/edit/ai-telemetry-platform/{pageId}";
  public static final String EditPage_enUS_StringFormatUri = "/en-us/edit/ai-telemetry-platform/%s";
  public static final String EditPage_enUS_StringFormatUrl = "%s/en-us/edit/ai-telemetry-platform/%s";
  public static final String UserPage_enUS_OpenApiUri = "/en-us/ai-telemetry-platform/learn/{pageId}";
  public static final String UserPage_enUS_StringFormatUri = "/en-us/ai-telemetry-platform/learn/%s";
  public static final String UserPage_enUS_StringFormatUrl = "%s/en-us/ai-telemetry-platform/learn/%s";
  public static final String DELETEFilter_enUS_OpenApiUri = "/en-us/api/ai-telemetry-platform";
  public static final String DELETEFilter_enUS_StringFormatUri = "/en-us/api/ai-telemetry-platform";
  public static final String DELETEFilter_enUS_StringFormatUrl = "%s/en-us/api/ai-telemetry-platform";

  public static final String Icon = "<i class=\"fa-duotone fa-regular fa-chart-fft\"></i>";

	/////////////
  // article //
	/////////////


  /**
   *  The entity article
   *	 is defined as null before being initialized. 
   */
  @JsonProperty
  @JsonInclude(Include.NON_NULL)
  protected Boolean article;

  /**
   * <br> The entity article
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.platform.aitelemetry.AiTelemetryPlatform&fq=entiteVar_enUS_indexed_string:article">Find the entity article in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _article(Wrap<Boolean> w);

  public Boolean getArticle() {
    return article;
  }

  public void setArticle(Boolean article) {
    this.article = article;
  }
  @JsonIgnore
  public void setArticle(String o) {
    this.article = AiTelemetryPlatform.staticSetArticle(siteRequest_, o);
  }
  public static Boolean staticSetArticle(SiteRequest siteRequest_, String o) {
    return Boolean.parseBoolean(o);
  }
  protected AiTelemetryPlatform articleInit() {
    Wrap<Boolean> articleWrap = new Wrap<Boolean>().var("article");
    if(article == null) {
      _article(articleWrap);
      Optional.ofNullable(articleWrap.getO()).ifPresent(o -> {
        setArticle(o);
      });
    }
    return (AiTelemetryPlatform)this;
  }

  public static Boolean staticSearchArticle(SiteRequest siteRequest_, Boolean o) {
    return o;
  }

  public static String staticSearchStrArticle(SiteRequest siteRequest_, Boolean o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqArticle(SiteRequest siteRequest_, String o) {
    return AiTelemetryPlatform.staticSearchArticle(siteRequest_, AiTelemetryPlatform.staticSetArticle(siteRequest_, o)).toString();
  }

	//////////
  // name //
	//////////


  /**
   *  The entity name
   *	 is defined as null before being initialized. 
   */
  @JsonProperty
  @JsonInclude(Include.NON_NULL)
  protected String name;

  /**
   * <br> The entity name
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.platform.aitelemetry.AiTelemetryPlatform&fq=entiteVar_enUS_indexed_string:name">Find the entity name in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _name(Wrap<String> w);

  public String getName() {
    return name;
  }
  public void setName(String o) {
    this.name = AiTelemetryPlatform.staticSetName(siteRequest_, o);
  }
  public static String staticSetName(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected AiTelemetryPlatform nameInit() {
    Wrap<String> nameWrap = new Wrap<String>().var("name");
    if(name == null) {
      _name(nameWrap);
      Optional.ofNullable(nameWrap.getO()).ifPresent(o -> {
        setName(o);
      });
    }
    return (AiTelemetryPlatform)this;
  }

  public static String staticSearchName(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrName(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqName(SiteRequest siteRequest_, String o) {
    return AiTelemetryPlatform.staticSearchName(siteRequest_, AiTelemetryPlatform.staticSetName(siteRequest_, o)).toString();
  }

  public String sqlName() {
    return name;
  }

  public static String staticJsonName(String name) {
    return name;
  }

	/////////////////
  // description //
	/////////////////


  /**
   *  The entity description
   *	 is defined as null before being initialized. 
   */
  @JsonProperty
  @JsonInclude(Include.NON_NULL)
  protected String description;

  /**
   * <br> The entity description
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.platform.aitelemetry.AiTelemetryPlatform&fq=entiteVar_enUS_indexed_string:description">Find the entity description in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _description(Wrap<String> w);

  public String getDescription() {
    return description;
  }
  public void setDescription(String o) {
    this.description = AiTelemetryPlatform.staticSetDescription(siteRequest_, o);
  }
  public static String staticSetDescription(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected AiTelemetryPlatform descriptionInit() {
    Wrap<String> descriptionWrap = new Wrap<String>().var("description");
    if(description == null) {
      _description(descriptionWrap);
      Optional.ofNullable(descriptionWrap.getO()).ifPresent(o -> {
        setDescription(o);
      });
    }
    return (AiTelemetryPlatform)this;
  }

  public static String staticSearchDescription(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrDescription(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqDescription(SiteRequest siteRequest_, String o) {
    return AiTelemetryPlatform.staticSearchDescription(siteRequest_, AiTelemetryPlatform.staticSetDescription(siteRequest_, o)).toString();
  }

  public String sqlDescription() {
    return description;
  }

  public static String staticJsonDescription(String description) {
    return description;
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
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.platform.aitelemetry.AiTelemetryPlatform&fq=entiteVar_enUS_indexed_string:pageId">Find the entity pageId in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _pageId(Wrap<String> w);

  public String getPageId() {
    return pageId;
  }
  public void setPageId(String o) {
    this.pageId = AiTelemetryPlatform.staticSetPageId(siteRequest_, o);
  }
  public static String staticSetPageId(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected AiTelemetryPlatform pageIdInit() {
    Wrap<String> pageIdWrap = new Wrap<String>().var("pageId");
    if(pageId == null) {
      _pageId(pageIdWrap);
      Optional.ofNullable(pageIdWrap.getO()).ifPresent(o -> {
        setPageId(o);
      });
    }
    return (AiTelemetryPlatform)this;
  }

  public static String staticSearchPageId(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrPageId(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqPageId(SiteRequest siteRequest_, String o) {
    return AiTelemetryPlatform.staticSearchPageId(siteRequest_, AiTelemetryPlatform.staticSetPageId(siteRequest_, o)).toString();
  }

  public String sqlPageId() {
    return pageId;
  }

  public static String staticJsonPageId(String pageId) {
    return pageId;
  }

	/////////////////
  // hubResource //
	/////////////////


  /**
   *  The entity hubResource
   *	 is defined as null before being initialized. 
   */
  @JsonProperty
  @JsonInclude(Include.NON_NULL)
  protected String hubResource;

  /**
   * <br> The entity hubResource
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.platform.aitelemetry.AiTelemetryPlatform&fq=entiteVar_enUS_indexed_string:hubResource">Find the entity hubResource in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _hubResource(Wrap<String> w);

  public String getHubResource() {
    return hubResource;
  }
  public void setHubResource(String o) {
    this.hubResource = AiTelemetryPlatform.staticSetHubResource(siteRequest_, o);
  }
  public static String staticSetHubResource(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected AiTelemetryPlatform hubResourceInit() {
    Wrap<String> hubResourceWrap = new Wrap<String>().var("hubResource");
    if(hubResource == null) {
      _hubResource(hubResourceWrap);
      Optional.ofNullable(hubResourceWrap.getO()).ifPresent(o -> {
        setHubResource(o);
      });
    }
    return (AiTelemetryPlatform)this;
  }

  public static String staticSearchHubResource(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrHubResource(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqHubResource(SiteRequest siteRequest_, String o) {
    return AiTelemetryPlatform.staticSearchHubResource(siteRequest_, AiTelemetryPlatform.staticSetHubResource(siteRequest_, o)).toString();
  }

  public String sqlHubResource() {
    return hubResource;
  }

  public static String staticJsonHubResource(String hubResource) {
    return hubResource;
  }

	///////////////
  // courseNum //
	///////////////


  /**
   *  The entity courseNum
   *	 is defined as null before being initialized. 
   */
  @JsonProperty
  @JsonSerialize(using = ToStringSerializer.class)
  @JsonInclude(Include.NON_NULL)
  protected Integer courseNum;

  /**
   * <br> The entity courseNum
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.platform.aitelemetry.AiTelemetryPlatform&fq=entiteVar_enUS_indexed_string:courseNum">Find the entity courseNum in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _courseNum(Wrap<Integer> w);

  public Integer getCourseNum() {
    return courseNum;
  }

  public void setCourseNum(Integer courseNum) {
    this.courseNum = courseNum;
  }
  @JsonIgnore
  public void setCourseNum(String o) {
    this.courseNum = AiTelemetryPlatform.staticSetCourseNum(siteRequest_, o);
  }
  public static Integer staticSetCourseNum(SiteRequest siteRequest_, String o) {
    if(NumberUtils.isParsable(o))
      return Integer.parseInt(o);
    return null;
  }
  protected AiTelemetryPlatform courseNumInit() {
    Wrap<Integer> courseNumWrap = new Wrap<Integer>().var("courseNum");
    if(courseNum == null) {
      _courseNum(courseNumWrap);
      Optional.ofNullable(courseNumWrap.getO()).ifPresent(o -> {
        setCourseNum(o);
      });
    }
    return (AiTelemetryPlatform)this;
  }

  public static Integer staticSearchCourseNum(SiteRequest siteRequest_, Integer o) {
    return o;
  }

  public static String staticSearchStrCourseNum(SiteRequest siteRequest_, Integer o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqCourseNum(SiteRequest siteRequest_, String o) {
    return AiTelemetryPlatform.staticSearchCourseNum(siteRequest_, AiTelemetryPlatform.staticSetCourseNum(siteRequest_, o)).toString();
  }

  public Integer sqlCourseNum() {
    return courseNum;
  }

  public static String staticJsonCourseNum(Integer courseNum) {
    return Optional.ofNullable(courseNum).map(v -> v.toString()).orElse(null);
  }

	///////////////
  // lessonNum //
	///////////////


  /**
   *  The entity lessonNum
   *	 is defined as null before being initialized. 
   */
  @JsonProperty
  @JsonSerialize(using = ToStringSerializer.class)
  @JsonInclude(Include.NON_NULL)
  protected Integer lessonNum;

  /**
   * <br> The entity lessonNum
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.platform.aitelemetry.AiTelemetryPlatform&fq=entiteVar_enUS_indexed_string:lessonNum">Find the entity lessonNum in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _lessonNum(Wrap<Integer> w);

  public Integer getLessonNum() {
    return lessonNum;
  }

  public void setLessonNum(Integer lessonNum) {
    this.lessonNum = lessonNum;
  }
  @JsonIgnore
  public void setLessonNum(String o) {
    this.lessonNum = AiTelemetryPlatform.staticSetLessonNum(siteRequest_, o);
  }
  public static Integer staticSetLessonNum(SiteRequest siteRequest_, String o) {
    if(NumberUtils.isParsable(o))
      return Integer.parseInt(o);
    return null;
  }
  protected AiTelemetryPlatform lessonNumInit() {
    Wrap<Integer> lessonNumWrap = new Wrap<Integer>().var("lessonNum");
    if(lessonNum == null) {
      _lessonNum(lessonNumWrap);
      Optional.ofNullable(lessonNumWrap.getO()).ifPresent(o -> {
        setLessonNum(o);
      });
    }
    return (AiTelemetryPlatform)this;
  }

  public static Integer staticSearchLessonNum(SiteRequest siteRequest_, Integer o) {
    return o;
  }

  public static String staticSearchStrLessonNum(SiteRequest siteRequest_, Integer o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqLessonNum(SiteRequest siteRequest_, String o) {
    return AiTelemetryPlatform.staticSearchLessonNum(siteRequest_, AiTelemetryPlatform.staticSetLessonNum(siteRequest_, o)).toString();
  }

  public Integer sqlLessonNum() {
    return lessonNum;
  }

  public static String staticJsonLessonNum(Integer lessonNum) {
    return Optional.ofNullable(lessonNum).map(v -> v.toString()).orElse(null);
  }

	////////////////
  // authorName //
	////////////////


  /**
   *  The entity authorName
   *	 is defined as null before being initialized. 
   */
  @JsonProperty
  @JsonInclude(Include.NON_NULL)
  protected String authorName;

  /**
   * <br> The entity authorName
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.platform.aitelemetry.AiTelemetryPlatform&fq=entiteVar_enUS_indexed_string:authorName">Find the entity authorName in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _authorName(Wrap<String> w);

  public String getAuthorName() {
    return authorName;
  }
  public void setAuthorName(String o) {
    this.authorName = AiTelemetryPlatform.staticSetAuthorName(siteRequest_, o);
  }
  public static String staticSetAuthorName(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected AiTelemetryPlatform authorNameInit() {
    Wrap<String> authorNameWrap = new Wrap<String>().var("authorName");
    if(authorName == null) {
      _authorName(authorNameWrap);
      Optional.ofNullable(authorNameWrap.getO()).ifPresent(o -> {
        setAuthorName(o);
      });
    }
    return (AiTelemetryPlatform)this;
  }

  public static String staticSearchAuthorName(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrAuthorName(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqAuthorName(SiteRequest siteRequest_, String o) {
    return AiTelemetryPlatform.staticSearchAuthorName(siteRequest_, AiTelemetryPlatform.staticSetAuthorName(siteRequest_, o)).toString();
  }

  public String sqlAuthorName() {
    return authorName;
  }

  public static String staticJsonAuthorName(String authorName) {
    return authorName;
  }

	///////////////
  // authorUrl //
	///////////////


  /**
   *  The entity authorUrl
   *	 is defined as null before being initialized. 
   */
  @JsonProperty
  @JsonInclude(Include.NON_NULL)
  protected String authorUrl;

  /**
   * <br> The entity authorUrl
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.platform.aitelemetry.AiTelemetryPlatform&fq=entiteVar_enUS_indexed_string:authorUrl">Find the entity authorUrl in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _authorUrl(Wrap<String> w);

  public String getAuthorUrl() {
    return authorUrl;
  }
  public void setAuthorUrl(String o) {
    this.authorUrl = AiTelemetryPlatform.staticSetAuthorUrl(siteRequest_, o);
  }
  public static String staticSetAuthorUrl(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected AiTelemetryPlatform authorUrlInit() {
    Wrap<String> authorUrlWrap = new Wrap<String>().var("authorUrl");
    if(authorUrl == null) {
      _authorUrl(authorUrlWrap);
      Optional.ofNullable(authorUrlWrap.getO()).ifPresent(o -> {
        setAuthorUrl(o);
      });
    }
    return (AiTelemetryPlatform)this;
  }

  public static String staticSearchAuthorUrl(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrAuthorUrl(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqAuthorUrl(SiteRequest siteRequest_, String o) {
    return AiTelemetryPlatform.staticSearchAuthorUrl(siteRequest_, AiTelemetryPlatform.staticSetAuthorUrl(siteRequest_, o)).toString();
  }

  public String sqlAuthorUrl() {
    return authorUrl;
  }

  public static String staticJsonAuthorUrl(String authorUrl) {
    return authorUrl;
  }

	//////////////////
  // pageImageUri //
	//////////////////


  /**
   *  The entity pageImageUri
   *	 is defined as null before being initialized. 
   */
  @JsonProperty
  @JsonInclude(Include.NON_NULL)
  protected String pageImageUri;

  /**
   * <br> The entity pageImageUri
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.platform.aitelemetry.AiTelemetryPlatform&fq=entiteVar_enUS_indexed_string:pageImageUri">Find the entity pageImageUri in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _pageImageUri(Wrap<String> w);

  public String getPageImageUri() {
    return pageImageUri;
  }
  public void setPageImageUri(String o) {
    this.pageImageUri = AiTelemetryPlatform.staticSetPageImageUri(siteRequest_, o);
  }
  public static String staticSetPageImageUri(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected AiTelemetryPlatform pageImageUriInit() {
    Wrap<String> pageImageUriWrap = new Wrap<String>().var("pageImageUri");
    if(pageImageUri == null) {
      _pageImageUri(pageImageUriWrap);
      Optional.ofNullable(pageImageUriWrap.getO()).ifPresent(o -> {
        setPageImageUri(o);
      });
    }
    return (AiTelemetryPlatform)this;
  }

  public static String staticSearchPageImageUri(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrPageImageUri(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqPageImageUri(SiteRequest siteRequest_, String o) {
    return AiTelemetryPlatform.staticSearchPageImageUri(siteRequest_, AiTelemetryPlatform.staticSetPageImageUri(siteRequest_, o)).toString();
  }

  public String sqlPageImageUri() {
    return pageImageUri;
  }

  public static String staticJsonPageImageUri(String pageImageUri) {
    return pageImageUri;
  }

	////////////////////
  // pageImageWidth //
	////////////////////


  /**
   *  The entity pageImageWidth
   *	 is defined as null before being initialized. 
   */
  @JsonProperty
  @JsonSerialize(using = ToStringSerializer.class)
  @JsonInclude(Include.NON_NULL)
  protected Integer pageImageWidth;

  /**
   * <br> The entity pageImageWidth
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.platform.aitelemetry.AiTelemetryPlatform&fq=entiteVar_enUS_indexed_string:pageImageWidth">Find the entity pageImageWidth in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _pageImageWidth(Wrap<Integer> w);

  public Integer getPageImageWidth() {
    return pageImageWidth;
  }

  public void setPageImageWidth(Integer pageImageWidth) {
    this.pageImageWidth = pageImageWidth;
  }
  @JsonIgnore
  public void setPageImageWidth(String o) {
    this.pageImageWidth = AiTelemetryPlatform.staticSetPageImageWidth(siteRequest_, o);
  }
  public static Integer staticSetPageImageWidth(SiteRequest siteRequest_, String o) {
    if(NumberUtils.isParsable(o))
      return Integer.parseInt(o);
    return null;
  }
  protected AiTelemetryPlatform pageImageWidthInit() {
    Wrap<Integer> pageImageWidthWrap = new Wrap<Integer>().var("pageImageWidth");
    if(pageImageWidth == null) {
      _pageImageWidth(pageImageWidthWrap);
      Optional.ofNullable(pageImageWidthWrap.getO()).ifPresent(o -> {
        setPageImageWidth(o);
      });
    }
    return (AiTelemetryPlatform)this;
  }

  public static Integer staticSearchPageImageWidth(SiteRequest siteRequest_, Integer o) {
    return o;
  }

  public static String staticSearchStrPageImageWidth(SiteRequest siteRequest_, Integer o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqPageImageWidth(SiteRequest siteRequest_, String o) {
    return AiTelemetryPlatform.staticSearchPageImageWidth(siteRequest_, AiTelemetryPlatform.staticSetPageImageWidth(siteRequest_, o)).toString();
  }

	/////////////////////
  // pageImageHeight //
	/////////////////////


  /**
   *  The entity pageImageHeight
   *	 is defined as null before being initialized. 
   */
  @JsonProperty
  @JsonSerialize(using = ToStringSerializer.class)
  @JsonInclude(Include.NON_NULL)
  protected Integer pageImageHeight;

  /**
   * <br> The entity pageImageHeight
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.platform.aitelemetry.AiTelemetryPlatform&fq=entiteVar_enUS_indexed_string:pageImageHeight">Find the entity pageImageHeight in Solr</a>
   * <br>
   * @param c is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _pageImageHeight(Wrap<Integer> c);

  public Integer getPageImageHeight() {
    return pageImageHeight;
  }

  public void setPageImageHeight(Integer pageImageHeight) {
    this.pageImageHeight = pageImageHeight;
  }
  @JsonIgnore
  public void setPageImageHeight(String o) {
    this.pageImageHeight = AiTelemetryPlatform.staticSetPageImageHeight(siteRequest_, o);
  }
  public static Integer staticSetPageImageHeight(SiteRequest siteRequest_, String o) {
    if(NumberUtils.isParsable(o))
      return Integer.parseInt(o);
    return null;
  }
  protected AiTelemetryPlatform pageImageHeightInit() {
    Wrap<Integer> pageImageHeightWrap = new Wrap<Integer>().var("pageImageHeight");
    if(pageImageHeight == null) {
      _pageImageHeight(pageImageHeightWrap);
      Optional.ofNullable(pageImageHeightWrap.getO()).ifPresent(o -> {
        setPageImageHeight(o);
      });
    }
    return (AiTelemetryPlatform)this;
  }

  public static Integer staticSearchPageImageHeight(SiteRequest siteRequest_, Integer o) {
    return o;
  }

  public static String staticSearchStrPageImageHeight(SiteRequest siteRequest_, Integer o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqPageImageHeight(SiteRequest siteRequest_, String o) {
    return AiTelemetryPlatform.staticSearchPageImageHeight(siteRequest_, AiTelemetryPlatform.staticSetPageImageHeight(siteRequest_, o)).toString();
  }

	///////////////////
  // pageImageType //
	///////////////////


  /**
   *  The entity pageImageType
   *	 is defined as null before being initialized. 
   */
  @JsonProperty
  @JsonInclude(Include.NON_NULL)
  protected String pageImageType;

  /**
   * <br> The entity pageImageType
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.platform.aitelemetry.AiTelemetryPlatform&fq=entiteVar_enUS_indexed_string:pageImageType">Find the entity pageImageType in Solr</a>
   * <br>
   * @param c is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _pageImageType(Wrap<String> c);

  public String getPageImageType() {
    return pageImageType;
  }
  public void setPageImageType(String o) {
    this.pageImageType = AiTelemetryPlatform.staticSetPageImageType(siteRequest_, o);
  }
  public static String staticSetPageImageType(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected AiTelemetryPlatform pageImageTypeInit() {
    Wrap<String> pageImageTypeWrap = new Wrap<String>().var("pageImageType");
    if(pageImageType == null) {
      _pageImageType(pageImageTypeWrap);
      Optional.ofNullable(pageImageTypeWrap.getO()).ifPresent(o -> {
        setPageImageType(o);
      });
    }
    return (AiTelemetryPlatform)this;
  }

  public static String staticSearchPageImageType(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrPageImageType(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqPageImageType(SiteRequest siteRequest_, String o) {
    return AiTelemetryPlatform.staticSearchPageImageType(siteRequest_, AiTelemetryPlatform.staticSetPageImageType(siteRequest_, o)).toString();
  }

	//////////////////
  // pageImageAlt //
	//////////////////


  /**
   *  The entity pageImageAlt
   *	 is defined as null before being initialized. 
   */
  @JsonProperty
  @JsonInclude(Include.NON_NULL)
  protected String pageImageAlt;

  /**
   * <br> The entity pageImageAlt
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.platform.aitelemetry.AiTelemetryPlatform&fq=entiteVar_enUS_indexed_string:pageImageAlt">Find the entity pageImageAlt in Solr</a>
   * <br>
   * @param c is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _pageImageAlt(Wrap<String> c);

  public String getPageImageAlt() {
    return pageImageAlt;
  }
  public void setPageImageAlt(String o) {
    this.pageImageAlt = AiTelemetryPlatform.staticSetPageImageAlt(siteRequest_, o);
  }
  public static String staticSetPageImageAlt(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected AiTelemetryPlatform pageImageAltInit() {
    Wrap<String> pageImageAltWrap = new Wrap<String>().var("pageImageAlt");
    if(pageImageAlt == null) {
      _pageImageAlt(pageImageAltWrap);
      Optional.ofNullable(pageImageAltWrap.getO()).ifPresent(o -> {
        setPageImageAlt(o);
      });
    }
    return (AiTelemetryPlatform)this;
  }

  public static String staticSearchPageImageAlt(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrPageImageAlt(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqPageImageAlt(SiteRequest siteRequest_, String o) {
    return AiTelemetryPlatform.staticSearchPageImageAlt(siteRequest_, AiTelemetryPlatform.staticSetPageImageAlt(siteRequest_, o)).toString();
  }

  public String sqlPageImageAlt() {
    return pageImageAlt;
  }

  public static String staticJsonPageImageAlt(String pageImageAlt) {
    return pageImageAlt;
  }

	////////////////////////////
  // prerequisiteArticleIds //
	////////////////////////////


  /**
   *  The entity prerequisiteArticleIds
   *	 is defined as null before being initialized. 
   */
  @JsonProperty
  @JsonInclude(Include.NON_NULL)
  protected String prerequisiteArticleIds;

  /**
   * <br> The entity prerequisiteArticleIds
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.platform.aitelemetry.AiTelemetryPlatform&fq=entiteVar_enUS_indexed_string:prerequisiteArticleIds">Find the entity prerequisiteArticleIds in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _prerequisiteArticleIds(Wrap<String> w);

  public String getPrerequisiteArticleIds() {
    return prerequisiteArticleIds;
  }
  public void setPrerequisiteArticleIds(String o) {
    this.prerequisiteArticleIds = AiTelemetryPlatform.staticSetPrerequisiteArticleIds(siteRequest_, o);
  }
  public static String staticSetPrerequisiteArticleIds(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected AiTelemetryPlatform prerequisiteArticleIdsInit() {
    Wrap<String> prerequisiteArticleIdsWrap = new Wrap<String>().var("prerequisiteArticleIds");
    if(prerequisiteArticleIds == null) {
      _prerequisiteArticleIds(prerequisiteArticleIdsWrap);
      Optional.ofNullable(prerequisiteArticleIdsWrap.getO()).ifPresent(o -> {
        setPrerequisiteArticleIds(o);
      });
    }
    return (AiTelemetryPlatform)this;
  }

  public static String staticSearchPrerequisiteArticleIds(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrPrerequisiteArticleIds(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqPrerequisiteArticleIds(SiteRequest siteRequest_, String o) {
    return AiTelemetryPlatform.staticSearchPrerequisiteArticleIds(siteRequest_, AiTelemetryPlatform.staticSetPrerequisiteArticleIds(siteRequest_, o)).toString();
  }

  public String sqlPrerequisiteArticleIds() {
    return prerequisiteArticleIds;
  }

  public static String staticJsonPrerequisiteArticleIds(String prerequisiteArticleIds) {
    return prerequisiteArticleIds;
  }

	///////////////////////////////
  // prerequisiteArticleSearch //
	///////////////////////////////


  /**
   *  The entity prerequisiteArticleSearch
   *	 is defined as null before being initialized. 
   */
  @JsonIgnore
  @JsonInclude(Include.NON_NULL)
  protected SearchList<AiTelemetryPlatform> prerequisiteArticleSearch;

  /**
   * <br> The entity prerequisiteArticleSearch
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.platform.aitelemetry.AiTelemetryPlatform&fq=entiteVar_enUS_indexed_string:prerequisiteArticleSearch">Find the entity prerequisiteArticleSearch in Solr</a>
   * <br>
   * @param promise is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _prerequisiteArticleSearch(Promise<SearchList<AiTelemetryPlatform>> promise);

  public SearchList<AiTelemetryPlatform> getPrerequisiteArticleSearch() {
    return prerequisiteArticleSearch;
  }

  public void setPrerequisiteArticleSearch(SearchList<AiTelemetryPlatform> prerequisiteArticleSearch) {
    this.prerequisiteArticleSearch = prerequisiteArticleSearch;
  }
  public static SearchList<AiTelemetryPlatform> staticSetPrerequisiteArticleSearch(SiteRequest siteRequest_, String o) {
    return null;
  }
  protected Future<SearchList<AiTelemetryPlatform>> prerequisiteArticleSearchPromise() {
    Promise<SearchList<AiTelemetryPlatform>> promise = Promise.promise();
    Promise<SearchList<AiTelemetryPlatform>> promise2 = Promise.promise();
    _prerequisiteArticleSearch(promise2);
    promise2.future().onSuccess(o -> {
      if(o != null && prerequisiteArticleSearch == null) {
        o.promiseDeepForClass(siteRequest_).onSuccess(a -> {
          setPrerequisiteArticleSearch(o);
          promise.complete(o);
        }).onFailure(ex -> {
          promise.fail(ex);
        });
      } else {
        promise.complete(o);
      }
    }).onFailure(ex -> {
      promise.fail(ex);
    });
    return promise.future();
  }

	//////////////////////////
  // prerequisiteArticles //
	//////////////////////////


  /**
   *  The entity prerequisiteArticles
   *	 is defined as null before being initialized. 
   */
  @JsonProperty
  @JsonDeserialize(using = JsonArrayDeserializer.class)
  @JsonInclude(Include.NON_NULL)
  protected JsonArray prerequisiteArticles;

  /**
   * <br> The entity prerequisiteArticles
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.platform.aitelemetry.AiTelemetryPlatform&fq=entiteVar_enUS_indexed_string:prerequisiteArticles">Find the entity prerequisiteArticles in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _prerequisiteArticles(Wrap<JsonArray> w);

  public JsonArray getPrerequisiteArticles() {
    return prerequisiteArticles;
  }

  public void setPrerequisiteArticles(JsonArray prerequisiteArticles) {
    this.prerequisiteArticles = prerequisiteArticles;
  }
  @JsonIgnore
  public void setPrerequisiteArticles(String o) {
    this.prerequisiteArticles = AiTelemetryPlatform.staticSetPrerequisiteArticles(siteRequest_, o);
  }
  public static JsonArray staticSetPrerequisiteArticles(SiteRequest siteRequest_, String o) {
    if(o != null) {
        return new JsonArray(o);
    }
    return null;
  }
  protected AiTelemetryPlatform prerequisiteArticlesInit() {
    Wrap<JsonArray> prerequisiteArticlesWrap = new Wrap<JsonArray>().var("prerequisiteArticles");
    if(prerequisiteArticles == null) {
      _prerequisiteArticles(prerequisiteArticlesWrap);
      Optional.ofNullable(prerequisiteArticlesWrap.getO()).ifPresent(o -> {
        setPrerequisiteArticles(o);
      });
    }
    return (AiTelemetryPlatform)this;
  }

  public static String staticSearchPrerequisiteArticles(SiteRequest siteRequest_, JsonArray o) {
    return o.toString();
  }

  public static String staticSearchStrPrerequisiteArticles(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqPrerequisiteArticles(SiteRequest siteRequest_, String o) {
    return AiTelemetryPlatform.staticSearchPrerequisiteArticles(siteRequest_, AiTelemetryPlatform.staticSetPrerequisiteArticles(siteRequest_, o)).toString();
  }

	////////////////////
  // nextArticleIds //
	////////////////////


  /**
   *  The entity nextArticleIds
   *	 is defined as null before being initialized. 
   */
  @JsonProperty
  @JsonInclude(Include.NON_NULL)
  protected String nextArticleIds;

  /**
   * <br> The entity nextArticleIds
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.platform.aitelemetry.AiTelemetryPlatform&fq=entiteVar_enUS_indexed_string:nextArticleIds">Find the entity nextArticleIds in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _nextArticleIds(Wrap<String> w);

  public String getNextArticleIds() {
    return nextArticleIds;
  }
  public void setNextArticleIds(String o) {
    this.nextArticleIds = AiTelemetryPlatform.staticSetNextArticleIds(siteRequest_, o);
  }
  public static String staticSetNextArticleIds(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected AiTelemetryPlatform nextArticleIdsInit() {
    Wrap<String> nextArticleIdsWrap = new Wrap<String>().var("nextArticleIds");
    if(nextArticleIds == null) {
      _nextArticleIds(nextArticleIdsWrap);
      Optional.ofNullable(nextArticleIdsWrap.getO()).ifPresent(o -> {
        setNextArticleIds(o);
      });
    }
    return (AiTelemetryPlatform)this;
  }

  public static String staticSearchNextArticleIds(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrNextArticleIds(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqNextArticleIds(SiteRequest siteRequest_, String o) {
    return AiTelemetryPlatform.staticSearchNextArticleIds(siteRequest_, AiTelemetryPlatform.staticSetNextArticleIds(siteRequest_, o)).toString();
  }

  public String sqlNextArticleIds() {
    return nextArticleIds;
  }

  public static String staticJsonNextArticleIds(String nextArticleIds) {
    return nextArticleIds;
  }

	///////////////////////
  // nextArticleSearch //
	///////////////////////


  /**
   *  The entity nextArticleSearch
   *	 is defined as null before being initialized. 
   */
  @JsonIgnore
  @JsonInclude(Include.NON_NULL)
  protected SearchList<AiTelemetryPlatform> nextArticleSearch;

  /**
   * <br> The entity nextArticleSearch
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.platform.aitelemetry.AiTelemetryPlatform&fq=entiteVar_enUS_indexed_string:nextArticleSearch">Find the entity nextArticleSearch in Solr</a>
   * <br>
   * @param promise is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _nextArticleSearch(Promise<SearchList<AiTelemetryPlatform>> promise);

  public SearchList<AiTelemetryPlatform> getNextArticleSearch() {
    return nextArticleSearch;
  }

  public void setNextArticleSearch(SearchList<AiTelemetryPlatform> nextArticleSearch) {
    this.nextArticleSearch = nextArticleSearch;
  }
  public static SearchList<AiTelemetryPlatform> staticSetNextArticleSearch(SiteRequest siteRequest_, String o) {
    return null;
  }
  protected Future<SearchList<AiTelemetryPlatform>> nextArticleSearchPromise() {
    Promise<SearchList<AiTelemetryPlatform>> promise = Promise.promise();
    Promise<SearchList<AiTelemetryPlatform>> promise2 = Promise.promise();
    _nextArticleSearch(promise2);
    promise2.future().onSuccess(o -> {
      if(o != null && nextArticleSearch == null) {
        o.promiseDeepForClass(siteRequest_).onSuccess(a -> {
          setNextArticleSearch(o);
          promise.complete(o);
        }).onFailure(ex -> {
          promise.fail(ex);
        });
      } else {
        promise.complete(o);
      }
    }).onFailure(ex -> {
      promise.fail(ex);
    });
    return promise.future();
  }

	//////////////////
  // nextArticles //
	//////////////////


  /**
   *  The entity nextArticles
   *	 is defined as null before being initialized. 
   */
  @JsonProperty
  @JsonDeserialize(using = JsonArrayDeserializer.class)
  @JsonInclude(Include.NON_NULL)
  protected JsonArray nextArticles;

  /**
   * <br> The entity nextArticles
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.platform.aitelemetry.AiTelemetryPlatform&fq=entiteVar_enUS_indexed_string:nextArticles">Find the entity nextArticles in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _nextArticles(Wrap<JsonArray> w);

  public JsonArray getNextArticles() {
    return nextArticles;
  }

  public void setNextArticles(JsonArray nextArticles) {
    this.nextArticles = nextArticles;
  }
  @JsonIgnore
  public void setNextArticles(String o) {
    this.nextArticles = AiTelemetryPlatform.staticSetNextArticles(siteRequest_, o);
  }
  public static JsonArray staticSetNextArticles(SiteRequest siteRequest_, String o) {
    if(o != null) {
        return new JsonArray(o);
    }
    return null;
  }
  protected AiTelemetryPlatform nextArticlesInit() {
    Wrap<JsonArray> nextArticlesWrap = new Wrap<JsonArray>().var("nextArticles");
    if(nextArticles == null) {
      _nextArticles(nextArticlesWrap);
      Optional.ofNullable(nextArticlesWrap.getO()).ifPresent(o -> {
        setNextArticles(o);
      });
    }
    return (AiTelemetryPlatform)this;
  }

  public static String staticSearchNextArticles(SiteRequest siteRequest_, JsonArray o) {
    return o.toString();
  }

  public static String staticSearchStrNextArticles(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqNextArticles(SiteRequest siteRequest_, String o) {
    return AiTelemetryPlatform.staticSearchNextArticles(siteRequest_, AiTelemetryPlatform.staticSetNextArticles(siteRequest_, o)).toString();
  }

	//////////////////
  // labelsString //
	//////////////////


  /**
   *  The entity labelsString
   *	 is defined as null before being initialized. 
   */
  @JsonProperty
  @JsonInclude(Include.NON_NULL)
  protected String labelsString;

  /**
   * <br> The entity labelsString
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.platform.aitelemetry.AiTelemetryPlatform&fq=entiteVar_enUS_indexed_string:labelsString">Find the entity labelsString in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _labelsString(Wrap<String> w);

  public String getLabelsString() {
    return labelsString;
  }
  public void setLabelsString(String o) {
    this.labelsString = AiTelemetryPlatform.staticSetLabelsString(siteRequest_, o);
  }
  public static String staticSetLabelsString(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected AiTelemetryPlatform labelsStringInit() {
    Wrap<String> labelsStringWrap = new Wrap<String>().var("labelsString");
    if(labelsString == null) {
      _labelsString(labelsStringWrap);
      Optional.ofNullable(labelsStringWrap.getO()).ifPresent(o -> {
        setLabelsString(o);
      });
    }
    return (AiTelemetryPlatform)this;
  }

  public static String staticSearchLabelsString(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrLabelsString(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqLabelsString(SiteRequest siteRequest_, String o) {
    return AiTelemetryPlatform.staticSearchLabelsString(siteRequest_, AiTelemetryPlatform.staticSetLabelsString(siteRequest_, o)).toString();
  }

  public String sqlLabelsString() {
    return labelsString;
  }

  public static String staticJsonLabelsString(String labelsString) {
    return labelsString;
  }

	////////////
  // labels //
	////////////


  /**
   *  The entity labels
   *	 It is constructed before being initialized with the constructor by default. 
   */
  @JsonProperty
  @JsonFormat(shape = JsonFormat.Shape.ARRAY)
  @JsonInclude(Include.NON_NULL)
  protected List<String> labels = new ArrayList<String>();

  /**
   * <br> The entity labels
   *  It is constructed before being initialized with the constructor by default. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.platform.aitelemetry.AiTelemetryPlatform&fq=entiteVar_enUS_indexed_string:labels">Find the entity labels in Solr</a>
   * <br>
   * @param l is the entity already constructed. 
   **/
  protected abstract void _labels(List<String> l);

  public List<String> getLabels() {
    return labels;
  }

  public void setLabels(List<String> labels) {
    this.labels = labels;
  }
  @JsonIgnore
  public void setLabels(String o) {
    String l = AiTelemetryPlatform.staticSetLabels(siteRequest_, o);
    if(l != null)
      addLabels(l);
  }
  public static String staticSetLabels(SiteRequest siteRequest_, String o) {
    return o;
  }
  public AiTelemetryPlatform addLabels(String...objects) {
    for(String o : objects) {
      addLabels(o);
    }
    return (AiTelemetryPlatform)this;
  }
  public AiTelemetryPlatform addLabels(String o) {
    if(o != null)
      this.labels.add(o);
    return (AiTelemetryPlatform)this;
  }
  @JsonIgnore
  public void setLabels(JsonArray objects) {
    labels.clear();
    if(objects == null)
      return;
    for(int i = 0; i < objects.size(); i++) {
      String o = objects.getString(i);
      addLabels(o);
    }
  }
  protected AiTelemetryPlatform labelsInit() {
    _labels(labels);
    return (AiTelemetryPlatform)this;
  }

  public static String staticSearchLabels(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrLabels(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqLabels(SiteRequest siteRequest_, String o) {
    return AiTelemetryPlatform.staticSearchLabels(siteRequest_, AiTelemetryPlatform.staticSetLabels(siteRequest_, o)).toString();
  }

  public String[] sqlLabels() {
    return labels.stream().map(v -> (String)v).toArray(String[]::new);
  }

  public static JsonArray staticJsonLabels(List<String> labels) {
    JsonArray a = new JsonArray();
    labels.stream().forEach(v -> a.add(v.toString()));
    return a;
  }

	///////////////////////
  // relatedArticleIds //
	///////////////////////


  /**
   *  The entity relatedArticleIds
   *	 is defined as null before being initialized. 
   */
  @JsonProperty
  @JsonInclude(Include.NON_NULL)
  protected String relatedArticleIds;

  /**
   * <br> The entity relatedArticleIds
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.platform.aitelemetry.AiTelemetryPlatform&fq=entiteVar_enUS_indexed_string:relatedArticleIds">Find the entity relatedArticleIds in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _relatedArticleIds(Wrap<String> w);

  public String getRelatedArticleIds() {
    return relatedArticleIds;
  }
  public void setRelatedArticleIds(String o) {
    this.relatedArticleIds = AiTelemetryPlatform.staticSetRelatedArticleIds(siteRequest_, o);
  }
  public static String staticSetRelatedArticleIds(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected AiTelemetryPlatform relatedArticleIdsInit() {
    Wrap<String> relatedArticleIdsWrap = new Wrap<String>().var("relatedArticleIds");
    if(relatedArticleIds == null) {
      _relatedArticleIds(relatedArticleIdsWrap);
      Optional.ofNullable(relatedArticleIdsWrap.getO()).ifPresent(o -> {
        setRelatedArticleIds(o);
      });
    }
    return (AiTelemetryPlatform)this;
  }

  public static String staticSearchRelatedArticleIds(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrRelatedArticleIds(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqRelatedArticleIds(SiteRequest siteRequest_, String o) {
    return AiTelemetryPlatform.staticSearchRelatedArticleIds(siteRequest_, AiTelemetryPlatform.staticSetRelatedArticleIds(siteRequest_, o)).toString();
  }

  public String sqlRelatedArticleIds() {
    return relatedArticleIds;
  }

  public static String staticJsonRelatedArticleIds(String relatedArticleIds) {
    return relatedArticleIds;
  }

	//////////////////////////
  // relatedArticleSearch //
	//////////////////////////


  /**
   *  The entity relatedArticleSearch
   *	 is defined as null before being initialized. 
   */
  @JsonIgnore
  @JsonInclude(Include.NON_NULL)
  protected SearchList<AiTelemetryPlatform> relatedArticleSearch;

  /**
   * <br> The entity relatedArticleSearch
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.platform.aitelemetry.AiTelemetryPlatform&fq=entiteVar_enUS_indexed_string:relatedArticleSearch">Find the entity relatedArticleSearch in Solr</a>
   * <br>
   * @param promise is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _relatedArticleSearch(Promise<SearchList<AiTelemetryPlatform>> promise);

  public SearchList<AiTelemetryPlatform> getRelatedArticleSearch() {
    return relatedArticleSearch;
  }

  public void setRelatedArticleSearch(SearchList<AiTelemetryPlatform> relatedArticleSearch) {
    this.relatedArticleSearch = relatedArticleSearch;
  }
  public static SearchList<AiTelemetryPlatform> staticSetRelatedArticleSearch(SiteRequest siteRequest_, String o) {
    return null;
  }
  protected Future<SearchList<AiTelemetryPlatform>> relatedArticleSearchPromise() {
    Promise<SearchList<AiTelemetryPlatform>> promise = Promise.promise();
    Promise<SearchList<AiTelemetryPlatform>> promise2 = Promise.promise();
    _relatedArticleSearch(promise2);
    promise2.future().onSuccess(o -> {
      if(o != null && relatedArticleSearch == null) {
        o.promiseDeepForClass(siteRequest_).onSuccess(a -> {
          setRelatedArticleSearch(o);
          promise.complete(o);
        }).onFailure(ex -> {
          promise.fail(ex);
        });
      } else {
        promise.complete(o);
      }
    }).onFailure(ex -> {
      promise.fail(ex);
    });
    return promise.future();
  }

	/////////////////////
  // relatedArticles //
	/////////////////////


  /**
   *  The entity relatedArticles
   *	 is defined as null before being initialized. 
   */
  @JsonProperty
  @JsonDeserialize(using = JsonArrayDeserializer.class)
  @JsonInclude(Include.NON_NULL)
  protected JsonArray relatedArticles;

  /**
   * <br> The entity relatedArticles
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.platform.aitelemetry.AiTelemetryPlatform&fq=entiteVar_enUS_indexed_string:relatedArticles">Find the entity relatedArticles in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _relatedArticles(Wrap<JsonArray> w);

  public JsonArray getRelatedArticles() {
    return relatedArticles;
  }

  public void setRelatedArticles(JsonArray relatedArticles) {
    this.relatedArticles = relatedArticles;
  }
  @JsonIgnore
  public void setRelatedArticles(String o) {
    this.relatedArticles = AiTelemetryPlatform.staticSetRelatedArticles(siteRequest_, o);
  }
  public static JsonArray staticSetRelatedArticles(SiteRequest siteRequest_, String o) {
    if(o != null) {
        return new JsonArray(o);
    }
    return null;
  }
  protected AiTelemetryPlatform relatedArticlesInit() {
    Wrap<JsonArray> relatedArticlesWrap = new Wrap<JsonArray>().var("relatedArticles");
    if(relatedArticles == null) {
      _relatedArticles(relatedArticlesWrap);
      Optional.ofNullable(relatedArticlesWrap.getO()).ifPresent(o -> {
        setRelatedArticles(o);
      });
    }
    return (AiTelemetryPlatform)this;
  }

  public static String staticSearchRelatedArticles(SiteRequest siteRequest_, JsonArray o) {
    return o.toString();
  }

  public static String staticSearchStrRelatedArticles(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqRelatedArticles(SiteRequest siteRequest_, String o) {
    return AiTelemetryPlatform.staticSearchRelatedArticles(siteRequest_, AiTelemetryPlatform.staticSetRelatedArticles(siteRequest_, o)).toString();
  }

  //////////////
  // initDeep //
  //////////////

  public Future<AiTelemetryPlatformGen<DEV>> promiseDeepAiTelemetryPlatform(SiteRequest siteRequest_) {
    setSiteRequest_(siteRequest_);
    return promiseDeepAiTelemetryPlatform();
  }

  public Future<AiTelemetryPlatformGen<DEV>> promiseDeepAiTelemetryPlatform() {
    Promise<AiTelemetryPlatformGen<DEV>> promise = Promise.promise();
    Promise<Void> promise2 = Promise.promise();
    promiseAiTelemetryPlatform(promise2);
    promise2.future().onSuccess(a -> {
      super.promiseDeepBaseResult(siteRequest_).onSuccess(b -> {
        promise.complete(this);
      }).onFailure(ex -> {
        promise.fail(ex);
      });
    }).onFailure(ex -> {
      promise.fail(ex);
    });
    return promise.future();
  }

  public Future<Void> promiseAiTelemetryPlatform(Promise<Void> promise) {
    Future.future(a -> a.complete()).compose(a -> {
      Promise<Void> promise2 = Promise.promise();
      try {
        articleInit();
        nameInit();
        descriptionInit();
        pageIdInit();
        hubResourceInit();
        courseNumInit();
        lessonNumInit();
        authorNameInit();
        authorUrlInit();
        pageImageUriInit();
        pageImageWidthInit();
        pageImageHeightInit();
        pageImageTypeInit();
        pageImageAltInit();
        prerequisiteArticleIdsInit();
        promise2.complete();
      } catch(Exception ex) {
        promise2.fail(ex);
      }
      return promise2.future();
    }).compose(a -> {
      Promise<Void> promise2 = Promise.promise();
      prerequisiteArticleSearchPromise().onSuccess(prerequisiteArticleSearch -> {
        promise2.complete();
      }).onFailure(ex -> {
        promise2.fail(ex);
      });
      return promise2.future();
    }).compose(a -> {
      Promise<Void> promise2 = Promise.promise();
      try {
        prerequisiteArticlesInit();
        nextArticleIdsInit();
        promise2.complete();
      } catch(Exception ex) {
        promise2.fail(ex);
      }
      return promise2.future();
    }).compose(a -> {
      Promise<Void> promise2 = Promise.promise();
      nextArticleSearchPromise().onSuccess(nextArticleSearch -> {
        promise2.complete();
      }).onFailure(ex -> {
        promise2.fail(ex);
      });
      return promise2.future();
    }).compose(a -> {
      Promise<Void> promise2 = Promise.promise();
      try {
        nextArticlesInit();
        labelsStringInit();
        labelsInit();
        relatedArticleIdsInit();
        promise2.complete();
      } catch(Exception ex) {
        promise2.fail(ex);
      }
      return promise2.future();
    }).compose(a -> {
      Promise<Void> promise2 = Promise.promise();
      relatedArticleSearchPromise().onSuccess(relatedArticleSearch -> {
        promise2.complete();
      }).onFailure(ex -> {
        promise2.fail(ex);
      });
      return promise2.future();
    }).compose(a -> {
      Promise<Void> promise2 = Promise.promise();
      try {
        relatedArticlesInit();
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

  @Override public Future<? extends AiTelemetryPlatformGen<DEV>> promiseDeepForClass(SiteRequest siteRequest_) {
    return promiseDeepAiTelemetryPlatform(siteRequest_);
  }

  /////////////////
  // siteRequest //
  /////////////////

  public void siteRequestAiTelemetryPlatform(SiteRequest siteRequest_) {
      super.siteRequestBaseResult(siteRequest_);
    if(prerequisiteArticleSearch != null)
      prerequisiteArticleSearch.setSiteRequest_(siteRequest_);
    if(nextArticleSearch != null)
      nextArticleSearch.setSiteRequest_(siteRequest_);
    if(relatedArticleSearch != null)
      relatedArticleSearch.setSiteRequest_(siteRequest_);
  }

  public void siteRequestForClass(SiteRequest siteRequest_) {
    siteRequestAiTelemetryPlatform(siteRequest_);
  }

  /////////////
  // obtain //
  /////////////

  @Override public Object obtainForClass(String var) {
    String[] vars = StringUtils.split(var, ".");
    Object o = null;
    for(String v : vars) {
      if(o == null)
        o = obtainAiTelemetryPlatform(v);
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
  public Object obtainAiTelemetryPlatform(String var) {
    AiTelemetryPlatform oAiTelemetryPlatform = (AiTelemetryPlatform)this;
    switch(var) {
      case "article":
        return oAiTelemetryPlatform.article;
      case "name":
        return oAiTelemetryPlatform.name;
      case "description":
        return oAiTelemetryPlatform.description;
      case "pageId":
        return oAiTelemetryPlatform.pageId;
      case "hubResource":
        return oAiTelemetryPlatform.hubResource;
      case "courseNum":
        return oAiTelemetryPlatform.courseNum;
      case "lessonNum":
        return oAiTelemetryPlatform.lessonNum;
      case "authorName":
        return oAiTelemetryPlatform.authorName;
      case "authorUrl":
        return oAiTelemetryPlatform.authorUrl;
      case "pageImageUri":
        return oAiTelemetryPlatform.pageImageUri;
      case "pageImageWidth":
        return oAiTelemetryPlatform.pageImageWidth;
      case "pageImageHeight":
        return oAiTelemetryPlatform.pageImageHeight;
      case "pageImageType":
        return oAiTelemetryPlatform.pageImageType;
      case "pageImageAlt":
        return oAiTelemetryPlatform.pageImageAlt;
      case "prerequisiteArticleIds":
        return oAiTelemetryPlatform.prerequisiteArticleIds;
      case "prerequisiteArticleSearch":
        return oAiTelemetryPlatform.prerequisiteArticleSearch;
      case "prerequisiteArticles":
        return oAiTelemetryPlatform.prerequisiteArticles;
      case "nextArticleIds":
        return oAiTelemetryPlatform.nextArticleIds;
      case "nextArticleSearch":
        return oAiTelemetryPlatform.nextArticleSearch;
      case "nextArticles":
        return oAiTelemetryPlatform.nextArticles;
      case "labelsString":
        return oAiTelemetryPlatform.labelsString;
      case "labels":
        return oAiTelemetryPlatform.labels;
      case "relatedArticleIds":
        return oAiTelemetryPlatform.relatedArticleIds;
      case "relatedArticleSearch":
        return oAiTelemetryPlatform.relatedArticleSearch;
      case "relatedArticles":
        return oAiTelemetryPlatform.relatedArticles;
      default:
        return super.obtainBaseResult(var);
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
        o = relateAiTelemetryPlatform(v, val);
      else if(o instanceof BaseModel) {
        BaseModel baseModel = (BaseModel)o;
        o = baseModel.relateForClass(v, val);
      }
    }
    return o != null;
  }
  public Object relateAiTelemetryPlatform(String var, Object val) {
    AiTelemetryPlatform oAiTelemetryPlatform = (AiTelemetryPlatform)this;
    switch(var) {
      default:
        return super.relateBaseResult(var, val);
    }
  }

  ///////////////
  // staticSet //
  ///////////////

  public static Object staticSetForClass(String entityVar, SiteRequest siteRequest_, String v, AiTelemetryPlatform o) {
    return staticSetAiTelemetryPlatform(entityVar,  siteRequest_, v, o);
  }
  public static Object staticSetAiTelemetryPlatform(String entityVar, SiteRequest siteRequest_, String v, AiTelemetryPlatform o) {
    switch(entityVar) {
    case "article":
      return AiTelemetryPlatform.staticSetArticle(siteRequest_, v);
    case "name":
      return AiTelemetryPlatform.staticSetName(siteRequest_, v);
    case "description":
      return AiTelemetryPlatform.staticSetDescription(siteRequest_, v);
    case "pageId":
      return AiTelemetryPlatform.staticSetPageId(siteRequest_, v);
    case "hubResource":
      return AiTelemetryPlatform.staticSetHubResource(siteRequest_, v);
    case "courseNum":
      return AiTelemetryPlatform.staticSetCourseNum(siteRequest_, v);
    case "lessonNum":
      return AiTelemetryPlatform.staticSetLessonNum(siteRequest_, v);
    case "authorName":
      return AiTelemetryPlatform.staticSetAuthorName(siteRequest_, v);
    case "authorUrl":
      return AiTelemetryPlatform.staticSetAuthorUrl(siteRequest_, v);
    case "pageImageUri":
      return AiTelemetryPlatform.staticSetPageImageUri(siteRequest_, v);
    case "pageImageWidth":
      return AiTelemetryPlatform.staticSetPageImageWidth(siteRequest_, v);
    case "pageImageHeight":
      return AiTelemetryPlatform.staticSetPageImageHeight(siteRequest_, v);
    case "pageImageType":
      return AiTelemetryPlatform.staticSetPageImageType(siteRequest_, v);
    case "pageImageAlt":
      return AiTelemetryPlatform.staticSetPageImageAlt(siteRequest_, v);
    case "prerequisiteArticleIds":
      return AiTelemetryPlatform.staticSetPrerequisiteArticleIds(siteRequest_, v);
    case "prerequisiteArticles":
      return AiTelemetryPlatform.staticSetPrerequisiteArticles(siteRequest_, v);
    case "nextArticleIds":
      return AiTelemetryPlatform.staticSetNextArticleIds(siteRequest_, v);
    case "nextArticles":
      return AiTelemetryPlatform.staticSetNextArticles(siteRequest_, v);
    case "labelsString":
      return AiTelemetryPlatform.staticSetLabelsString(siteRequest_, v);
    case "labels":
      return AiTelemetryPlatform.staticSetLabels(siteRequest_, v);
    case "relatedArticleIds":
      return AiTelemetryPlatform.staticSetRelatedArticleIds(siteRequest_, v);
    case "relatedArticles":
      return AiTelemetryPlatform.staticSetRelatedArticles(siteRequest_, v);
      default:
        return BaseResult.staticSetBaseResult(entityVar,  siteRequest_, v, o);
    }
  }

  ////////////////
  // staticSearch //
  ////////////////

  public static Object staticSearchForClass(String entityVar, SiteRequest siteRequest_, Object o) {
    return staticSearchAiTelemetryPlatform(entityVar,  siteRequest_, o);
  }
  public static Object staticSearchAiTelemetryPlatform(String entityVar, SiteRequest siteRequest_, Object o) {
    switch(entityVar) {
    case "article":
      return AiTelemetryPlatform.staticSearchArticle(siteRequest_, (Boolean)o);
    case "name":
      return AiTelemetryPlatform.staticSearchName(siteRequest_, (String)o);
    case "description":
      return AiTelemetryPlatform.staticSearchDescription(siteRequest_, (String)o);
    case "pageId":
      return AiTelemetryPlatform.staticSearchPageId(siteRequest_, (String)o);
    case "hubResource":
      return AiTelemetryPlatform.staticSearchHubResource(siteRequest_, (String)o);
    case "courseNum":
      return AiTelemetryPlatform.staticSearchCourseNum(siteRequest_, (Integer)o);
    case "lessonNum":
      return AiTelemetryPlatform.staticSearchLessonNum(siteRequest_, (Integer)o);
    case "authorName":
      return AiTelemetryPlatform.staticSearchAuthorName(siteRequest_, (String)o);
    case "authorUrl":
      return AiTelemetryPlatform.staticSearchAuthorUrl(siteRequest_, (String)o);
    case "pageImageUri":
      return AiTelemetryPlatform.staticSearchPageImageUri(siteRequest_, (String)o);
    case "pageImageWidth":
      return AiTelemetryPlatform.staticSearchPageImageWidth(siteRequest_, (Integer)o);
    case "pageImageHeight":
      return AiTelemetryPlatform.staticSearchPageImageHeight(siteRequest_, (Integer)o);
    case "pageImageType":
      return AiTelemetryPlatform.staticSearchPageImageType(siteRequest_, (String)o);
    case "pageImageAlt":
      return AiTelemetryPlatform.staticSearchPageImageAlt(siteRequest_, (String)o);
    case "prerequisiteArticleIds":
      return AiTelemetryPlatform.staticSearchPrerequisiteArticleIds(siteRequest_, (String)o);
    case "prerequisiteArticles":
      return AiTelemetryPlatform.staticSearchPrerequisiteArticles(siteRequest_, (JsonArray)o);
    case "nextArticleIds":
      return AiTelemetryPlatform.staticSearchNextArticleIds(siteRequest_, (String)o);
    case "nextArticles":
      return AiTelemetryPlatform.staticSearchNextArticles(siteRequest_, (JsonArray)o);
    case "labelsString":
      return AiTelemetryPlatform.staticSearchLabelsString(siteRequest_, (String)o);
    case "labels":
      return AiTelemetryPlatform.staticSearchLabels(siteRequest_, (String)o);
    case "relatedArticleIds":
      return AiTelemetryPlatform.staticSearchRelatedArticleIds(siteRequest_, (String)o);
    case "relatedArticles":
      return AiTelemetryPlatform.staticSearchRelatedArticles(siteRequest_, (JsonArray)o);
      default:
        return BaseResult.staticSearchBaseResult(entityVar,  siteRequest_, o);
    }
  }

  ///////////////////
  // staticSearchStr //
  ///////////////////

  public static String staticSearchStrForClass(String entityVar, SiteRequest siteRequest_, Object o) {
    return staticSearchStrAiTelemetryPlatform(entityVar,  siteRequest_, o);
  }
  public static String staticSearchStrAiTelemetryPlatform(String entityVar, SiteRequest siteRequest_, Object o) {
    switch(entityVar) {
    case "article":
      return AiTelemetryPlatform.staticSearchStrArticle(siteRequest_, (Boolean)o);
    case "name":
      return AiTelemetryPlatform.staticSearchStrName(siteRequest_, (String)o);
    case "description":
      return AiTelemetryPlatform.staticSearchStrDescription(siteRequest_, (String)o);
    case "pageId":
      return AiTelemetryPlatform.staticSearchStrPageId(siteRequest_, (String)o);
    case "hubResource":
      return AiTelemetryPlatform.staticSearchStrHubResource(siteRequest_, (String)o);
    case "courseNum":
      return AiTelemetryPlatform.staticSearchStrCourseNum(siteRequest_, (Integer)o);
    case "lessonNum":
      return AiTelemetryPlatform.staticSearchStrLessonNum(siteRequest_, (Integer)o);
    case "authorName":
      return AiTelemetryPlatform.staticSearchStrAuthorName(siteRequest_, (String)o);
    case "authorUrl":
      return AiTelemetryPlatform.staticSearchStrAuthorUrl(siteRequest_, (String)o);
    case "pageImageUri":
      return AiTelemetryPlatform.staticSearchStrPageImageUri(siteRequest_, (String)o);
    case "pageImageWidth":
      return AiTelemetryPlatform.staticSearchStrPageImageWidth(siteRequest_, (Integer)o);
    case "pageImageHeight":
      return AiTelemetryPlatform.staticSearchStrPageImageHeight(siteRequest_, (Integer)o);
    case "pageImageType":
      return AiTelemetryPlatform.staticSearchStrPageImageType(siteRequest_, (String)o);
    case "pageImageAlt":
      return AiTelemetryPlatform.staticSearchStrPageImageAlt(siteRequest_, (String)o);
    case "prerequisiteArticleIds":
      return AiTelemetryPlatform.staticSearchStrPrerequisiteArticleIds(siteRequest_, (String)o);
    case "prerequisiteArticles":
      return AiTelemetryPlatform.staticSearchStrPrerequisiteArticles(siteRequest_, (String)o);
    case "nextArticleIds":
      return AiTelemetryPlatform.staticSearchStrNextArticleIds(siteRequest_, (String)o);
    case "nextArticles":
      return AiTelemetryPlatform.staticSearchStrNextArticles(siteRequest_, (String)o);
    case "labelsString":
      return AiTelemetryPlatform.staticSearchStrLabelsString(siteRequest_, (String)o);
    case "labels":
      return AiTelemetryPlatform.staticSearchStrLabels(siteRequest_, (String)o);
    case "relatedArticleIds":
      return AiTelemetryPlatform.staticSearchStrRelatedArticleIds(siteRequest_, (String)o);
    case "relatedArticles":
      return AiTelemetryPlatform.staticSearchStrRelatedArticles(siteRequest_, (String)o);
      default:
        return BaseResult.staticSearchStrBaseResult(entityVar,  siteRequest_, o);
    }
  }

  //////////////////
  // staticSearchFq //
  //////////////////

  public static String staticSearchFqForClass(String entityVar, SiteRequest siteRequest_, String o) {
    return staticSearchFqAiTelemetryPlatform(entityVar,  siteRequest_, o);
  }
  public static String staticSearchFqAiTelemetryPlatform(String entityVar, SiteRequest siteRequest_, String o) {
    switch(entityVar) {
    case "article":
      return AiTelemetryPlatform.staticSearchFqArticle(siteRequest_, o);
    case "name":
      return AiTelemetryPlatform.staticSearchFqName(siteRequest_, o);
    case "description":
      return AiTelemetryPlatform.staticSearchFqDescription(siteRequest_, o);
    case "pageId":
      return AiTelemetryPlatform.staticSearchFqPageId(siteRequest_, o);
    case "hubResource":
      return AiTelemetryPlatform.staticSearchFqHubResource(siteRequest_, o);
    case "courseNum":
      return AiTelemetryPlatform.staticSearchFqCourseNum(siteRequest_, o);
    case "lessonNum":
      return AiTelemetryPlatform.staticSearchFqLessonNum(siteRequest_, o);
    case "authorName":
      return AiTelemetryPlatform.staticSearchFqAuthorName(siteRequest_, o);
    case "authorUrl":
      return AiTelemetryPlatform.staticSearchFqAuthorUrl(siteRequest_, o);
    case "pageImageUri":
      return AiTelemetryPlatform.staticSearchFqPageImageUri(siteRequest_, o);
    case "pageImageWidth":
      return AiTelemetryPlatform.staticSearchFqPageImageWidth(siteRequest_, o);
    case "pageImageHeight":
      return AiTelemetryPlatform.staticSearchFqPageImageHeight(siteRequest_, o);
    case "pageImageType":
      return AiTelemetryPlatform.staticSearchFqPageImageType(siteRequest_, o);
    case "pageImageAlt":
      return AiTelemetryPlatform.staticSearchFqPageImageAlt(siteRequest_, o);
    case "prerequisiteArticleIds":
      return AiTelemetryPlatform.staticSearchFqPrerequisiteArticleIds(siteRequest_, o);
    case "prerequisiteArticles":
      return AiTelemetryPlatform.staticSearchFqPrerequisiteArticles(siteRequest_, o);
    case "nextArticleIds":
      return AiTelemetryPlatform.staticSearchFqNextArticleIds(siteRequest_, o);
    case "nextArticles":
      return AiTelemetryPlatform.staticSearchFqNextArticles(siteRequest_, o);
    case "labelsString":
      return AiTelemetryPlatform.staticSearchFqLabelsString(siteRequest_, o);
    case "labels":
      return AiTelemetryPlatform.staticSearchFqLabels(siteRequest_, o);
    case "relatedArticleIds":
      return AiTelemetryPlatform.staticSearchFqRelatedArticleIds(siteRequest_, o);
    case "relatedArticles":
      return AiTelemetryPlatform.staticSearchFqRelatedArticles(siteRequest_, o);
      default:
        return BaseResult.staticSearchFqBaseResult(entityVar,  siteRequest_, o);
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
          o = persistAiTelemetryPlatform(v, val);
        else if(o instanceof BaseModel) {
          BaseModel oBaseModel = (BaseModel)o;
          o = oBaseModel.persistForClass(v, val);
        }
      }
    }
    return o != null;
  }
  public Object persistAiTelemetryPlatform(String var, Object val) {
    String varLower = var.toLowerCase();
      if("name".equals(varLower)) {
        if(val instanceof String) {
          setName((String)val);
        }
        saves.add("name");
        return val;
      } else if("description".equals(varLower)) {
        if(val instanceof String) {
          setDescription((String)val);
        }
        saves.add("description");
        return val;
      } else if("pageid".equals(varLower)) {
        if(val instanceof String) {
          setPageId((String)val);
        }
        saves.add("pageId");
        return val;
      } else if("hubresource".equals(varLower)) {
        if(val instanceof String) {
          setHubResource((String)val);
        }
        saves.add("hubResource");
        return val;
      } else if("coursenum".equals(varLower)) {
        if(val instanceof Integer) {
          setCourseNum((Integer)val);
        } else {
          setCourseNum(val == null ? null : val.toString());
        }
        saves.add("courseNum");
        return val;
      } else if("lessonnum".equals(varLower)) {
        if(val instanceof Integer) {
          setLessonNum((Integer)val);
        } else {
          setLessonNum(val == null ? null : val.toString());
        }
        saves.add("lessonNum");
        return val;
      } else if("authorname".equals(varLower)) {
        if(val instanceof String) {
          setAuthorName((String)val);
        }
        saves.add("authorName");
        return val;
      } else if("authorurl".equals(varLower)) {
        if(val instanceof String) {
          setAuthorUrl((String)val);
        }
        saves.add("authorUrl");
        return val;
      } else if("pageimageuri".equals(varLower)) {
        if(val instanceof String) {
          setPageImageUri((String)val);
        }
        saves.add("pageImageUri");
        return val;
      } else if("pageimagealt".equals(varLower)) {
        if(val instanceof String) {
          setPageImageAlt((String)val);
        }
        saves.add("pageImageAlt");
        return val;
      } else if("prerequisitearticleids".equals(varLower)) {
        if(val instanceof String) {
          setPrerequisiteArticleIds((String)val);
        }
        saves.add("prerequisiteArticleIds");
        return val;
      } else if("nextarticleids".equals(varLower)) {
        if(val instanceof String) {
          setNextArticleIds((String)val);
        }
        saves.add("nextArticleIds");
        return val;
      } else if("labelsstring".equals(varLower)) {
        if(val instanceof String) {
          setLabelsString((String)val);
        }
        saves.add("labelsString");
        return val;
      } else if("labels".equals(varLower)) {
        if(val instanceof List<?>) {
          ((List<String>)val).stream().forEach(v -> addLabels(v));
        } else if(val instanceof String[]) {
          Arrays.asList((String[])val).stream().forEach(v -> addLabels((String)v));
        } else if(val instanceof JsonArray) {
          ((JsonArray)val).stream().forEach(v -> addLabels(staticSetLabels(siteRequest_, v.toString())));
        }
        if(!saves.contains("labels")) {
          saves.add("labels");
        }
        return val;
      } else if("relatedarticleids".equals(varLower)) {
        if(val instanceof String) {
          setRelatedArticleIds((String)val);
        }
        saves.add("relatedArticleIds");
        return val;
    } else {
      return super.persistBaseResult(var, val);
    }
  }

  /////////////
  // populate //
  /////////////

  @Override public void populateForClass(SolrResponse.Doc doc) {
    populateAiTelemetryPlatform(doc);
  }
  public void populateAiTelemetryPlatform(SolrResponse.Doc doc) {
    AiTelemetryPlatform oAiTelemetryPlatform = (AiTelemetryPlatform)this;
    saves = Optional.ofNullable((ArrayList<String>)doc.get("saves_docvalues_strings")).orElse(new ArrayList<String>());
    if(saves != null) {

      if(saves.contains("name")) {
        String name = (String)doc.get("name_docvalues_string");
        if(name != null)
          oAiTelemetryPlatform.setName(name);
      }

      if(saves.contains("description")) {
        String description = (String)doc.get("description_docvalues_string");
        if(description != null)
          oAiTelemetryPlatform.setDescription(description);
      }

      if(saves.contains("pageId")) {
        String pageId = (String)doc.get("pageId_docvalues_string");
        if(pageId != null)
          oAiTelemetryPlatform.setPageId(pageId);
      }

      if(saves.contains("hubResource")) {
        String hubResource = (String)doc.get("hubResource_docvalues_string");
        if(hubResource != null)
          oAiTelemetryPlatform.setHubResource(hubResource);
      }

      if(saves.contains("courseNum")) {
        Integer courseNum = (Integer)doc.get("courseNum_docvalues_int");
        if(courseNum != null)
          oAiTelemetryPlatform.setCourseNum(courseNum);
      }

      if(saves.contains("lessonNum")) {
        Integer lessonNum = (Integer)doc.get("lessonNum_docvalues_int");
        if(lessonNum != null)
          oAiTelemetryPlatform.setLessonNum(lessonNum);
      }

      if(saves.contains("authorName")) {
        String authorName = (String)doc.get("authorName_docvalues_string");
        if(authorName != null)
          oAiTelemetryPlatform.setAuthorName(authorName);
      }

      if(saves.contains("authorUrl")) {
        String authorUrl = (String)doc.get("authorUrl_docvalues_string");
        if(authorUrl != null)
          oAiTelemetryPlatform.setAuthorUrl(authorUrl);
      }

      if(saves.contains("pageImageUri")) {
        String pageImageUri = (String)doc.get("pageImageUri_docvalues_string");
        if(pageImageUri != null)
          oAiTelemetryPlatform.setPageImageUri(pageImageUri);
      }

      if(saves.contains("pageImageWidth")) {
        Integer pageImageWidth = (Integer)doc.get("pageImageWidth_docvalues_int");
        if(pageImageWidth != null)
          oAiTelemetryPlatform.setPageImageWidth(pageImageWidth);
      }

      if(saves.contains("pageImageHeight")) {
        Integer pageImageHeight = (Integer)doc.get("pageImageHeight_docvalues_int");
        if(pageImageHeight != null)
          oAiTelemetryPlatform.setPageImageHeight(pageImageHeight);
      }

      if(saves.contains("pageImageType")) {
        String pageImageType = (String)doc.get("pageImageType_docvalues_string");
        if(pageImageType != null)
          oAiTelemetryPlatform.setPageImageType(pageImageType);
      }

      if(saves.contains("pageImageAlt")) {
        String pageImageAlt = (String)doc.get("pageImageAlt_docvalues_string");
        if(pageImageAlt != null)
          oAiTelemetryPlatform.setPageImageAlt(pageImageAlt);
      }

      if(saves.contains("prerequisiteArticleIds")) {
        String prerequisiteArticleIds = (String)doc.get("prerequisiteArticleIds_docvalues_string");
        if(prerequisiteArticleIds != null)
          oAiTelemetryPlatform.setPrerequisiteArticleIds(prerequisiteArticleIds);
      }

      if(saves.contains("prerequisiteArticles")) {
        String prerequisiteArticles = (String)doc.get("prerequisiteArticles_stored_string");
        if(prerequisiteArticles != null)
          oAiTelemetryPlatform.setPrerequisiteArticles(prerequisiteArticles);
      }

      if(saves.contains("nextArticleIds")) {
        String nextArticleIds = (String)doc.get("nextArticleIds_docvalues_string");
        if(nextArticleIds != null)
          oAiTelemetryPlatform.setNextArticleIds(nextArticleIds);
      }

      if(saves.contains("nextArticles")) {
        String nextArticles = (String)doc.get("nextArticles_stored_string");
        if(nextArticles != null)
          oAiTelemetryPlatform.setNextArticles(nextArticles);
      }

      if(saves.contains("labelsString")) {
        String labelsString = (String)doc.get("labelsString_docvalues_string");
        if(labelsString != null)
          oAiTelemetryPlatform.setLabelsString(labelsString);
      }

      if(saves.contains("labels")) {
        List<String> labels = (List<String>)doc.get("labels_docvalues_strings");
        if(labels != null) {
          labels.stream().forEach( v -> {
            oAiTelemetryPlatform.labels.add(AiTelemetryPlatform.staticSetLabels(siteRequest_, v));
          });
        }
      }

      if(saves.contains("relatedArticleIds")) {
        String relatedArticleIds = (String)doc.get("relatedArticleIds_docvalues_string");
        if(relatedArticleIds != null)
          oAiTelemetryPlatform.setRelatedArticleIds(relatedArticleIds);
      }

      if(saves.contains("relatedArticles")) {
        String relatedArticles = (String)doc.get("relatedArticles_stored_string");
        if(relatedArticles != null)
          oAiTelemetryPlatform.setRelatedArticles(relatedArticles);
      }
    }

    super.populateBaseResult(doc);
  }

  public void indexAiTelemetryPlatform(JsonObject doc) {
    if(name != null) {
      doc.put("name_docvalues_string", name);
    }
    if(description != null) {
      doc.put("description_docvalues_string", description);
    }
    if(pageId != null) {
      doc.put("pageId_docvalues_string", pageId);
    }
    if(hubResource != null) {
      doc.put("hubResource_docvalues_string", hubResource);
    }
    if(courseNum != null) {
      doc.put("courseNum_docvalues_int", courseNum);
    }
    if(lessonNum != null) {
      doc.put("lessonNum_docvalues_int", lessonNum);
    }
    if(authorName != null) {
      doc.put("authorName_docvalues_string", authorName);
    }
    if(authorUrl != null) {
      doc.put("authorUrl_docvalues_string", authorUrl);
    }
    if(pageImageUri != null) {
      doc.put("pageImageUri_docvalues_string", pageImageUri);
    }
    if(pageImageWidth != null) {
      doc.put("pageImageWidth_docvalues_int", pageImageWidth);
    }
    if(pageImageHeight != null) {
      doc.put("pageImageHeight_docvalues_int", pageImageHeight);
    }
    if(pageImageType != null) {
      doc.put("pageImageType_docvalues_string", pageImageType);
    }
    if(pageImageAlt != null) {
      doc.put("pageImageAlt_docvalues_string", pageImageAlt);
    }
    if(prerequisiteArticleIds != null) {
      doc.put("prerequisiteArticleIds_docvalues_string", prerequisiteArticleIds);
    }
    if(prerequisiteArticles != null) {
      doc.put("prerequisiteArticles_stored_string", prerequisiteArticles.toString());
    }
    if(nextArticleIds != null) {
      doc.put("nextArticleIds_docvalues_string", nextArticleIds);
    }
    if(nextArticles != null) {
      doc.put("nextArticles_stored_string", nextArticles.toString());
    }
    if(labelsString != null) {
      doc.put("labelsString_docvalues_string", labelsString);
    }
    if(labels != null) {
      JsonArray l = new JsonArray();
      doc.put("labels_docvalues_strings", l);
      for(String o : labels) {
        l.add(AiTelemetryPlatform.staticSearchLabels(siteRequest_, o));
      }
    }
    if(relatedArticleIds != null) {
      doc.put("relatedArticleIds_docvalues_string", relatedArticleIds);
    }
    if(relatedArticles != null) {
      doc.put("relatedArticles_stored_string", relatedArticles.toString());
    }
    super.indexBaseResult(doc);

	}

  public static String varStoredAiTelemetryPlatform(String entityVar) {
    switch(entityVar) {
      case "name":
        return "name_docvalues_string";
      case "description":
        return "description_docvalues_string";
      case "pageId":
        return "pageId_docvalues_string";
      case "hubResource":
        return "hubResource_docvalues_string";
      case "courseNum":
        return "courseNum_docvalues_int";
      case "lessonNum":
        return "lessonNum_docvalues_int";
      case "authorName":
        return "authorName_docvalues_string";
      case "authorUrl":
        return "authorUrl_docvalues_string";
      case "pageImageUri":
        return "pageImageUri_docvalues_string";
      case "pageImageWidth":
        return "pageImageWidth_docvalues_int";
      case "pageImageHeight":
        return "pageImageHeight_docvalues_int";
      case "pageImageType":
        return "pageImageType_docvalues_string";
      case "pageImageAlt":
        return "pageImageAlt_docvalues_string";
      case "prerequisiteArticleIds":
        return "prerequisiteArticleIds_docvalues_string";
      case "prerequisiteArticles":
        return "prerequisiteArticles_stored_string";
      case "nextArticleIds":
        return "nextArticleIds_docvalues_string";
      case "nextArticles":
        return "nextArticles_stored_string";
      case "labelsString":
        return "labelsString_docvalues_string";
      case "labels":
        return "labels_docvalues_strings";
      case "relatedArticleIds":
        return "relatedArticleIds_docvalues_string";
      case "relatedArticles":
        return "relatedArticles_stored_string";
      default:
        return BaseResult.varStoredBaseResult(entityVar);
    }
  }

  public static String varIndexedAiTelemetryPlatform(String entityVar) {
    switch(entityVar) {
      case "name":
        return "name_docvalues_string";
      case "description":
        return "description_docvalues_string";
      case "pageId":
        return "pageId_docvalues_string";
      case "hubResource":
        return "hubResource_docvalues_string";
      case "courseNum":
        return "courseNum_docvalues_int";
      case "lessonNum":
        return "lessonNum_docvalues_int";
      case "authorName":
        return "authorName_docvalues_string";
      case "authorUrl":
        return "authorUrl_docvalues_string";
      case "pageImageUri":
        return "pageImageUri_docvalues_string";
      case "pageImageWidth":
        return "pageImageWidth_docvalues_int";
      case "pageImageHeight":
        return "pageImageHeight_docvalues_int";
      case "pageImageType":
        return "pageImageType_docvalues_string";
      case "pageImageAlt":
        return "pageImageAlt_docvalues_string";
      case "prerequisiteArticleIds":
        return "prerequisiteArticleIds_docvalues_string";
      case "nextArticleIds":
        return "nextArticleIds_docvalues_string";
      case "labelsString":
        return "labelsString_docvalues_string";
      case "labels":
        return "labels_docvalues_strings";
      case "relatedArticleIds":
        return "relatedArticleIds_docvalues_string";
      default:
        return BaseResult.varIndexedBaseResult(entityVar);
    }
  }

  public static String searchVarAiTelemetryPlatform(String searchVar) {
    switch(searchVar) {
      case "name_docvalues_string":
        return "name";
      case "description_docvalues_string":
        return "description";
      case "pageId_docvalues_string":
        return "pageId";
      case "hubResource_docvalues_string":
        return "hubResource";
      case "courseNum_docvalues_int":
        return "courseNum";
      case "lessonNum_docvalues_int":
        return "lessonNum";
      case "authorName_docvalues_string":
        return "authorName";
      case "authorUrl_docvalues_string":
        return "authorUrl";
      case "pageImageUri_docvalues_string":
        return "pageImageUri";
      case "pageImageWidth_docvalues_int":
        return "pageImageWidth";
      case "pageImageHeight_docvalues_int":
        return "pageImageHeight";
      case "pageImageType_docvalues_string":
        return "pageImageType";
      case "pageImageAlt_docvalues_string":
        return "pageImageAlt";
      case "prerequisiteArticleIds_docvalues_string":
        return "prerequisiteArticleIds";
      case "nextArticleIds_docvalues_string":
        return "nextArticleIds";
      case "labelsString_docvalues_string":
        return "labelsString";
      case "labels_docvalues_strings":
        return "labels";
      case "relatedArticleIds_docvalues_string":
        return "relatedArticleIds";
      default:
        return BaseResult.searchVarBaseResult(searchVar);
    }
  }

  public static String varSearchAiTelemetryPlatform(String entityVar) {
    switch(entityVar) {
      default:
        return BaseResult.varSearchBaseResult(entityVar);
    }
  }

  public static String varSuggestedAiTelemetryPlatform(String entityVar) {
    switch(entityVar) {
      default:
        return BaseResult.varSuggestedBaseResult(entityVar);
    }
  }

  /////////////
  // store //
  /////////////

  @Override public void storeForClass(SolrResponse.Doc doc) {
    storeAiTelemetryPlatform(doc);
  }
  public void storeAiTelemetryPlatform(SolrResponse.Doc doc) {
    AiTelemetryPlatform oAiTelemetryPlatform = (AiTelemetryPlatform)this;
    SiteRequest siteRequest = oAiTelemetryPlatform.getSiteRequest_();

    oAiTelemetryPlatform.setName(Optional.ofNullable(doc.get("name_docvalues_string")).map(v -> v.toString()).orElse(null));
    oAiTelemetryPlatform.setDescription(Optional.ofNullable(doc.get("description_docvalues_string")).map(v -> v.toString()).orElse(null));
    oAiTelemetryPlatform.setPageId(Optional.ofNullable(doc.get("pageId_docvalues_string")).map(v -> v.toString()).orElse(null));
    oAiTelemetryPlatform.setHubResource(Optional.ofNullable(doc.get("hubResource_docvalues_string")).map(v -> v.toString()).orElse(null));
    oAiTelemetryPlatform.setCourseNum(Optional.ofNullable(doc.get("courseNum_docvalues_int")).map(v -> v.toString()).orElse(null));
    oAiTelemetryPlatform.setLessonNum(Optional.ofNullable(doc.get("lessonNum_docvalues_int")).map(v -> v.toString()).orElse(null));
    oAiTelemetryPlatform.setAuthorName(Optional.ofNullable(doc.get("authorName_docvalues_string")).map(v -> v.toString()).orElse(null));
    oAiTelemetryPlatform.setAuthorUrl(Optional.ofNullable(doc.get("authorUrl_docvalues_string")).map(v -> v.toString()).orElse(null));
    oAiTelemetryPlatform.setPageImageUri(Optional.ofNullable(doc.get("pageImageUri_docvalues_string")).map(v -> v.toString()).orElse(null));
    oAiTelemetryPlatform.setPageImageWidth(Optional.ofNullable(doc.get("pageImageWidth_docvalues_int")).map(v -> v.toString()).orElse(null));
    oAiTelemetryPlatform.setPageImageHeight(Optional.ofNullable(doc.get("pageImageHeight_docvalues_int")).map(v -> v.toString()).orElse(null));
    oAiTelemetryPlatform.setPageImageType(Optional.ofNullable(doc.get("pageImageType_docvalues_string")).map(v -> v.toString()).orElse(null));
    oAiTelemetryPlatform.setPageImageAlt(Optional.ofNullable(doc.get("pageImageAlt_docvalues_string")).map(v -> v.toString()).orElse(null));
    oAiTelemetryPlatform.setPrerequisiteArticleIds(Optional.ofNullable(doc.get("prerequisiteArticleIds_docvalues_string")).map(v -> v.toString()).orElse(null));
    oAiTelemetryPlatform.setPrerequisiteArticles(Optional.ofNullable(doc.get("prerequisiteArticles_stored_string")).map(v -> v.toString()).orElse(null));
    oAiTelemetryPlatform.setNextArticleIds(Optional.ofNullable(doc.get("nextArticleIds_docvalues_string")).map(v -> v.toString()).orElse(null));
    oAiTelemetryPlatform.setNextArticles(Optional.ofNullable(doc.get("nextArticles_stored_string")).map(v -> v.toString()).orElse(null));
    oAiTelemetryPlatform.setLabelsString(Optional.ofNullable(doc.get("labelsString_docvalues_string")).map(v -> v.toString()).orElse(null));
    Optional.ofNullable((List<?>)doc.get("labels_docvalues_strings")).orElse(Arrays.asList()).stream().filter(v -> v != null).forEach(v -> {
      oAiTelemetryPlatform.addLabels(AiTelemetryPlatform.staticSetLabels(siteRequest, v.toString()));
    });
    oAiTelemetryPlatform.setRelatedArticleIds(Optional.ofNullable(doc.get("relatedArticleIds_docvalues_string")).map(v -> v.toString()).orElse(null));
    oAiTelemetryPlatform.setRelatedArticles(Optional.ofNullable(doc.get("relatedArticles_stored_string")).map(v -> v.toString()).orElse(null));

    super.storeBaseResult(doc);
  }

  //////////////////
  // apiRequest //
  //////////////////

  public void apiRequestAiTelemetryPlatform() {
    ApiRequest apiRequest = Optional.ofNullable(siteRequest_).map(r -> r.getApiRequest_()).orElse(null);
    Object o = Optional.ofNullable(apiRequest).map(ApiRequest::getOriginal).orElse(null);
    if(o != null && o instanceof AiTelemetryPlatform) {
      AiTelemetryPlatform original = (AiTelemetryPlatform)o;
      if(!Objects.equals(name, original.getName()))
        apiRequest.addVars("name");
      if(!Objects.equals(description, original.getDescription()))
        apiRequest.addVars("description");
      if(!Objects.equals(pageId, original.getPageId()))
        apiRequest.addVars("pageId");
      if(!Objects.equals(hubResource, original.getHubResource()))
        apiRequest.addVars("hubResource");
      if(!Objects.equals(courseNum, original.getCourseNum()))
        apiRequest.addVars("courseNum");
      if(!Objects.equals(lessonNum, original.getLessonNum()))
        apiRequest.addVars("lessonNum");
      if(!Objects.equals(authorName, original.getAuthorName()))
        apiRequest.addVars("authorName");
      if(!Objects.equals(authorUrl, original.getAuthorUrl()))
        apiRequest.addVars("authorUrl");
      if(!Objects.equals(pageImageUri, original.getPageImageUri()))
        apiRequest.addVars("pageImageUri");
      if(!Objects.equals(pageImageWidth, original.getPageImageWidth()))
        apiRequest.addVars("pageImageWidth");
      if(!Objects.equals(pageImageHeight, original.getPageImageHeight()))
        apiRequest.addVars("pageImageHeight");
      if(!Objects.equals(pageImageType, original.getPageImageType()))
        apiRequest.addVars("pageImageType");
      if(!Objects.equals(pageImageAlt, original.getPageImageAlt()))
        apiRequest.addVars("pageImageAlt");
      if(!Objects.equals(prerequisiteArticleIds, original.getPrerequisiteArticleIds()))
        apiRequest.addVars("prerequisiteArticleIds");
      if(!Objects.equals(prerequisiteArticles, original.getPrerequisiteArticles()))
        apiRequest.addVars("prerequisiteArticles");
      if(!Objects.equals(nextArticleIds, original.getNextArticleIds()))
        apiRequest.addVars("nextArticleIds");
      if(!Objects.equals(nextArticles, original.getNextArticles()))
        apiRequest.addVars("nextArticles");
      if(!Objects.equals(labelsString, original.getLabelsString()))
        apiRequest.addVars("labelsString");
      if(!Objects.equals(labels, original.getLabels()))
        apiRequest.addVars("labels");
      if(!Objects.equals(relatedArticleIds, original.getRelatedArticleIds()))
        apiRequest.addVars("relatedArticleIds");
      if(!Objects.equals(relatedArticles, original.getRelatedArticles()))
        apiRequest.addVars("relatedArticles");
      super.apiRequestBaseResult();
    }
  }

  //////////////
  // toString //
  //////////////

  @Override public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(super.toString());
    sb.append(Optional.ofNullable(name).map(v -> "name: \"" + v + "\"\n" ).orElse(""));
    sb.append(Optional.ofNullable(description).map(v -> "description: \"" + v + "\"\n" ).orElse(""));
    sb.append(Optional.ofNullable(pageId).map(v -> "pageId: \"" + v + "\"\n" ).orElse(""));
    sb.append(Optional.ofNullable(hubResource).map(v -> "hubResource: \"" + v + "\"\n" ).orElse(""));
    sb.append(Optional.ofNullable(courseNum).map(v -> "courseNum: " + v + "\n").orElse(""));
    sb.append(Optional.ofNullable(lessonNum).map(v -> "lessonNum: " + v + "\n").orElse(""));
    sb.append(Optional.ofNullable(authorName).map(v -> "authorName: \"" + v + "\"\n" ).orElse(""));
    sb.append(Optional.ofNullable(authorUrl).map(v -> "authorUrl: \"" + v + "\"\n" ).orElse(""));
    sb.append(Optional.ofNullable(pageImageUri).map(v -> "pageImageUri: \"" + v + "\"\n" ).orElse(""));
    sb.append(Optional.ofNullable(pageImageWidth).map(v -> "pageImageWidth: " + v + "\n").orElse(""));
    sb.append(Optional.ofNullable(pageImageHeight).map(v -> "pageImageHeight: " + v + "\n").orElse(""));
    sb.append(Optional.ofNullable(pageImageType).map(v -> "pageImageType: \"" + v + "\"\n" ).orElse(""));
    sb.append(Optional.ofNullable(pageImageAlt).map(v -> "pageImageAlt: \"" + v + "\"\n" ).orElse(""));
    sb.append(Optional.ofNullable(prerequisiteArticleIds).map(v -> "prerequisiteArticleIds: \"" + v + "\"\n" ).orElse(""));
    sb.append(Optional.ofNullable(prerequisiteArticles).map(v -> "prerequisiteArticles: " + v + "\n").orElse(""));
    sb.append(Optional.ofNullable(nextArticleIds).map(v -> "nextArticleIds: \"" + v + "\"\n" ).orElse(""));
    sb.append(Optional.ofNullable(nextArticles).map(v -> "nextArticles: " + v + "\n").orElse(""));
    sb.append(Optional.ofNullable(labelsString).map(v -> "labelsString: \"" + v + "\"\n" ).orElse(""));
    sb.append(Optional.ofNullable(labels).map(v -> "labels: " + v + "\n").orElse(""));
    sb.append(Optional.ofNullable(relatedArticleIds).map(v -> "relatedArticleIds: \"" + v + "\"\n" ).orElse(""));
    sb.append(Optional.ofNullable(relatedArticles).map(v -> "relatedArticles: " + v + "\n").orElse(""));
    return sb.toString();
  }

  public static final String CLASS_SIMPLE_NAME = "AiTelemetryPlatform";
  public static final String CLASS_CANONICAL_NAME = "org.computate.dcm.model.platform.aitelemetry.AiTelemetryPlatform";
  public static final String CLASS_AUTH_RESOURCE = "AITELEMETRYPLATFORM";
  public static final String CLASS_API_ADDRESS_AiTelemetryPlatform = "dcm-enUS-AiTelemetryPlatform";
  public static String getClassApiAddress() {
    return CLASS_API_ADDRESS_AiTelemetryPlatform;
  }
  public static final String VAR_article = "article";
  public static final String VAR_name = "name";
  public static final String VAR_description = "description";
  public static final String VAR_pageId = "pageId";
  public static final String VAR_hubResource = "hubResource";
  public static final String VAR_courseNum = "courseNum";
  public static final String VAR_lessonNum = "lessonNum";
  public static final String VAR_authorName = "authorName";
  public static final String VAR_authorUrl = "authorUrl";
  public static final String VAR_pageImageUri = "pageImageUri";
  public static final String VAR_pageImageWidth = "pageImageWidth";
  public static final String VAR_pageImageHeight = "pageImageHeight";
  public static final String VAR_pageImageType = "pageImageType";
  public static final String VAR_pageImageAlt = "pageImageAlt";
  public static final String VAR_prerequisiteArticleIds = "prerequisiteArticleIds";
  public static final String VAR_prerequisiteArticleSearch = "prerequisiteArticleSearch";
  public static final String VAR_prerequisiteArticles = "prerequisiteArticles";
  public static final String VAR_nextArticleIds = "nextArticleIds";
  public static final String VAR_nextArticleSearch = "nextArticleSearch";
  public static final String VAR_nextArticles = "nextArticles";
  public static final String VAR_labelsString = "labelsString";
  public static final String VAR_labels = "labels";
  public static final String VAR_relatedArticleIds = "relatedArticleIds";
  public static final String VAR_relatedArticleSearch = "relatedArticleSearch";
  public static final String VAR_relatedArticles = "relatedArticles";

  public static List<String> varsQForClass() {
    return AiTelemetryPlatform.varsQAiTelemetryPlatform(new ArrayList<String>());
  }
  public static List<String> varsQAiTelemetryPlatform(List<String> vars) {
    BaseResult.varsQBaseResult(vars);
    return vars;
  }

  public static List<String> varsFqForClass() {
    return AiTelemetryPlatform.varsFqAiTelemetryPlatform(new ArrayList<String>());
  }
  public static List<String> varsFqAiTelemetryPlatform(List<String> vars) {
    vars.add(VAR_name);
    vars.add(VAR_description);
    vars.add(VAR_pageId);
    vars.add(VAR_hubResource);
    vars.add(VAR_courseNum);
    vars.add(VAR_lessonNum);
    vars.add(VAR_authorName);
    vars.add(VAR_authorUrl);
    vars.add(VAR_pageImageUri);
    BaseResult.varsFqBaseResult(vars);
    return vars;
  }

  public static List<String> varsRangeForClass() {
    return AiTelemetryPlatform.varsRangeAiTelemetryPlatform(new ArrayList<String>());
  }
  public static List<String> varsRangeAiTelemetryPlatform(List<String> vars) {
    vars.add(VAR_courseNum);
    vars.add(VAR_lessonNum);
    BaseResult.varsRangeBaseResult(vars);
    return vars;
  }

  public static final String DISPLAY_NAME_article = "";
  public static final String DISPLAY_NAME_name = "course name";
  public static final String DISPLAY_NAME_description = "course description";
  public static final String DISPLAY_NAME_pageId = "page ID";
  public static final String DISPLAY_NAME_hubResource = "AI Telemetry Platform auth resource";
  public static final String DISPLAY_NAME_courseNum = "course number";
  public static final String DISPLAY_NAME_lessonNum = "lesson number";
  public static final String DISPLAY_NAME_authorName = "author name";
  public static final String DISPLAY_NAME_authorUrl = "author URL";
  public static final String DISPLAY_NAME_pageImageUri = "imageUri";
  public static final String DISPLAY_NAME_pageImageWidth = "";
  public static final String DISPLAY_NAME_pageImageHeight = "";
  public static final String DISPLAY_NAME_pageImageType = "";
  public static final String DISPLAY_NAME_pageImageAlt = "";
  public static final String DISPLAY_NAME_prerequisiteArticleIds = "prerequisite article IDs";
  public static final String DISPLAY_NAME_prerequisiteArticleSearch = "";
  public static final String DISPLAY_NAME_prerequisiteArticles = "prerequisite articles";
  public static final String DISPLAY_NAME_nextArticleIds = "next article IDs";
  public static final String DISPLAY_NAME_nextArticleSearch = "";
  public static final String DISPLAY_NAME_nextArticles = "next articles";
  public static final String DISPLAY_NAME_labelsString = "labels string";
  public static final String DISPLAY_NAME_labels = "labels";
  public static final String DISPLAY_NAME_relatedArticleIds = "related article IDs";
  public static final String DISPLAY_NAME_relatedArticleSearch = "";
  public static final String DISPLAY_NAME_relatedArticles = "related articles";

  @Override
  public String idForClass() {
    return pageId;
  }

  @Override
  public String titleForClass() {
    return objectTitle;
  }

  @Override
  public String nameForClass() {
    return name;
  }

  @Override
  public String classNameAdjectiveSingularForClass() {
    return AiTelemetryPlatform.NameAdjectiveSingular_enUS;
  }

  @Override
  public String descriptionForClass() {
    return description;
  }

  @Override
  public String enUSStringFormatUrlEditPageForClass() {
    return "%s/en-us/edit/ai-telemetry-platform/%s";
  }

  @Override
  public String enUSStringFormatUrlDisplayPageForClass() {
    return null;
  }

  @Override
  public String enUSStringFormatUrlUserPageForClass() {
    return "%s/en-us/ai-telemetry-platform/learn/%s";
  }

  @Override
  public String enUSStringFormatUrlDownloadForClass() {
    return null;
  }

  public static String displayNameForClass(String var) {
    return AiTelemetryPlatform.displayNameAiTelemetryPlatform(var);
  }
  public static String displayNameAiTelemetryPlatform(String var) {
    switch(var) {
    case VAR_article:
      return DISPLAY_NAME_article;
    case VAR_name:
      return DISPLAY_NAME_name;
    case VAR_description:
      return DISPLAY_NAME_description;
    case VAR_pageId:
      return DISPLAY_NAME_pageId;
    case VAR_hubResource:
      return DISPLAY_NAME_hubResource;
    case VAR_courseNum:
      return DISPLAY_NAME_courseNum;
    case VAR_lessonNum:
      return DISPLAY_NAME_lessonNum;
    case VAR_authorName:
      return DISPLAY_NAME_authorName;
    case VAR_authorUrl:
      return DISPLAY_NAME_authorUrl;
    case VAR_pageImageUri:
      return DISPLAY_NAME_pageImageUri;
    case VAR_pageImageWidth:
      return DISPLAY_NAME_pageImageWidth;
    case VAR_pageImageHeight:
      return DISPLAY_NAME_pageImageHeight;
    case VAR_pageImageType:
      return DISPLAY_NAME_pageImageType;
    case VAR_pageImageAlt:
      return DISPLAY_NAME_pageImageAlt;
    case VAR_prerequisiteArticleIds:
      return DISPLAY_NAME_prerequisiteArticleIds;
    case VAR_prerequisiteArticleSearch:
      return DISPLAY_NAME_prerequisiteArticleSearch;
    case VAR_prerequisiteArticles:
      return DISPLAY_NAME_prerequisiteArticles;
    case VAR_nextArticleIds:
      return DISPLAY_NAME_nextArticleIds;
    case VAR_nextArticleSearch:
      return DISPLAY_NAME_nextArticleSearch;
    case VAR_nextArticles:
      return DISPLAY_NAME_nextArticles;
    case VAR_labelsString:
      return DISPLAY_NAME_labelsString;
    case VAR_labels:
      return DISPLAY_NAME_labels;
    case VAR_relatedArticleIds:
      return DISPLAY_NAME_relatedArticleIds;
    case VAR_relatedArticleSearch:
      return DISPLAY_NAME_relatedArticleSearch;
    case VAR_relatedArticles:
      return DISPLAY_NAME_relatedArticles;
    default:
      return BaseResult.displayNameBaseResult(var);
    }
  }

  public static String descriptionAiTelemetryPlatform(String var) {
    if(var == null)
      return null;
    switch(var) {
    case VAR_name:
      return "The course name. ";
    case VAR_description:
      return "The course description. ";
    case VAR_pageId:
      return "The ID for this page. ";
    case VAR_hubResource:
      return "The unique authorization resource for the AiTelemetryPlatform for multi-tenancy";
    case VAR_courseNum:
      return "The course number for this page. ";
    case VAR_lessonNum:
      return "The lesson number for this page. ";
    case VAR_authorName:
      return "The author name";
    case VAR_authorUrl:
      return "The author URL";
    case VAR_pageImageUri:
      return "The page image URI";
    case VAR_pageImageWidth:
      return "The image width";
    case VAR_pageImageHeight:
      return "The image height";
    case VAR_pageImageType:
      return "The image height";
    case VAR_pageImageAlt:
      return "The image accessibility text. ";
    case VAR_prerequisiteArticleIds:
      return "The prerequisite article IDs comma-separated. ";
    case VAR_prerequisiteArticles:
      return "A JSON array of prerequisite articles. ";
    case VAR_nextArticleIds:
      return "The next article IDs comma-separated. ";
    case VAR_nextArticles:
      return "A JSON array of next articles. ";
    case VAR_labelsString:
      return "The labels String for this article comma-separated. ";
    case VAR_labels:
      return "The labels for this article. ";
    case VAR_relatedArticleIds:
      return "The related article IDs comma-separated. ";
    case VAR_relatedArticles:
      return "A JSON array of related articles. ";
      default:
        return BaseResult.descriptionBaseResult(var);
    }
  }

  public static String classSimpleNameAiTelemetryPlatform(String var) {
    switch(var) {
    case VAR_article:
      return "Boolean";
    case VAR_name:
      return "String";
    case VAR_description:
      return "String";
    case VAR_pageId:
      return "String";
    case VAR_hubResource:
      return "String";
    case VAR_courseNum:
      return "Integer";
    case VAR_lessonNum:
      return "Integer";
    case VAR_authorName:
      return "String";
    case VAR_authorUrl:
      return "String";
    case VAR_pageImageUri:
      return "String";
    case VAR_pageImageWidth:
      return "Integer";
    case VAR_pageImageHeight:
      return "Integer";
    case VAR_pageImageType:
      return "String";
    case VAR_pageImageAlt:
      return "String";
    case VAR_prerequisiteArticleIds:
      return "String";
    case VAR_prerequisiteArticleSearch:
      return "SearchList";
    case VAR_prerequisiteArticles:
      return "JsonArray";
    case VAR_nextArticleIds:
      return "String";
    case VAR_nextArticleSearch:
      return "SearchList";
    case VAR_nextArticles:
      return "JsonArray";
    case VAR_labelsString:
      return "String";
    case VAR_labels:
      return "List";
    case VAR_relatedArticleIds:
      return "String";
    case VAR_relatedArticleSearch:
      return "SearchList";
    case VAR_relatedArticles:
      return "JsonArray";
      default:
        return BaseResult.classSimpleNameBaseResult(var);
    }
  }

  public static Integer htmColumnAiTelemetryPlatform(String var) {
    switch(var) {
    case VAR_name:
      return 0;
    case VAR_description:
      return 1;
      default:
        return BaseResult.htmColumnBaseResult(var);
    }
  }

  public static Integer htmRowAiTelemetryPlatform(String var) {
    switch(var) {
    case VAR_name:
      return 3;
    case VAR_description:
      return 3;
    case VAR_pageId:
      return 99;
    case VAR_authorName:
      return 3;
    case VAR_authorUrl:
      return 3;
    case VAR_pageImageUri:
      return 4;
      default:
        return BaseResult.htmRowBaseResult(var);
    }
  }

  public static Integer htmCellAiTelemetryPlatform(String var) {
    switch(var) {
    case VAR_name:
      return 1;
    case VAR_description:
      return 2;
    case VAR_pageId:
      return 1;
    case VAR_authorName:
      return 3;
    case VAR_authorUrl:
      return 3;
    case VAR_pageImageUri:
      return 1;
      default:
        return BaseResult.htmCellBaseResult(var);
    }
  }

  public static Integer lengthMinAiTelemetryPlatform(String var) {
    switch(var) {
      default:
        return BaseResult.lengthMinBaseResult(var);
    }
  }

  public static Integer lengthMaxAiTelemetryPlatform(String var) {
    switch(var) {
      default:
        return BaseResult.lengthMaxBaseResult(var);
    }
  }

  public static Integer maxAiTelemetryPlatform(String var) {
    switch(var) {
      default:
        return BaseResult.maxBaseResult(var);
    }
  }

  public static Integer minAiTelemetryPlatform(String var) {
    switch(var) {
      default:
        return BaseResult.minBaseResult(var);
    }
  }
}
