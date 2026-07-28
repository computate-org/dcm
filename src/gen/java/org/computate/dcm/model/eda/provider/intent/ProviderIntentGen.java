package org.computate.dcm.model.eda.provider.intent;

import java.util.List;
import org.computate.search.wrap.Wrap;
import org.computate.dcm.model.eda.provider.Provider;
import org.computate.dcm.request.SiteRequest;
import org.computate.dcm.model.eda.provider.Provider;
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
import io.vertx.core.json.JsonArray;
import java.lang.Boolean;
import org.computate.search.wrap.Wrap;
import io.vertx.ext.web.client.WebClient;
import io.vertx.core.MultiMap;
import io.vertx.core.http.HttpResponseExpectation;
import io.vertx.ext.web.client.HttpResponse;
import io.vertx.core.AsyncResult;
import io.vertx.core.buffer.Buffer;
import org.computate.vertx.config.ComputateConfigKeys;
import java.util.stream.Collectors;
import io.vertx.core.Promise;
import io.vertx.core.Future;
import org.computate.vertx.search.list.SearchList;
import org.computate.search.tool.SearchTool;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.computate.search.response.solr.SolrResponse;

/**
 * <ol>
<h3>Suggestions that can generate more code for you: </h3> * </ol>
 * <li>You can add a class comment "{@inheritDoc}" if you wish to inherit the helpful inherited class comments from class ProviderIntentGen into the class ProviderIntent. 
 * </li><li><p>
 *   You can add a class comment <kbd><b>Rows: 100</b></kbd> if you wish for the provider intent API to return more or less than 10 results by default. 
 *   In this case, the API will return 100 results from the API instead of 10 by default. 
 *   Each API has built in pagination of the search results to ensure a user can query all the data a page at a time without running the application out of memory. 
 * </p>
 * </li>
 * <h3>About the ProviderIntent class and it's generated class ProviderIntentGen&lt;Provider&gt;: </h3>extends ProviderIntentGen
 * <p>
 * This Java class extends a generated Java class ProviderIntentGen built by the <a href="https://github.com/computate-org/computate">https://github.com/computate-org/computate</a> project. 
 * Whenever this Java class is modified or touched, the watch service installed as described in the README, indexes all the information about this Java class in a local Apache Solr Search Engine. 
 * If you are running the service, you can see the indexed data about this Java Class here: 
 * </p>
 * <p><a href="https://solr.apps-crc.testing/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.provider.intent.ProviderIntent">Find the class ProviderIntent in Solr. </a></p>
 * <p>
 * The extended class ending with "Gen" did not exist at first, but was automatically created by the same watch service based on the data retrieved from the local Apache Server search engine. 
 * The extended class contains many generated fields, getters, setters, initialization code, and helper methods to help build a website and API fast, reactive, and scalable. 
 * </p>
 * extends ProviderIntentGen<Provider>
 * <p>This <code>class ProviderIntent extends ProviderIntentGen&lt;Provider&gt;</code>, which means it extends a newly generated ProviderIntentGen. 
 * The generated <code>class ProviderIntentGen extends Provider</code> which means that ProviderIntent extends ProviderIntentGen which extends Provider. 
 * This generated inheritance is a powerful feature that allows a lot of boiler plate code to be created for you automatically while still preserving inheritance through the power of Java Generic classes. 
 * </p>
 * <h2>
 *   Api: true
 * </h2>
 * <p>
 *   This class contains a comment <kbd><b>Api: true</b></kbd>, which means this class will have Java Vert.x API backend code generated for these objects. 
 * </p>
 * <h2>ApiTag.enUS: true</h2>
 * <p>This class contains a comment <kbd><b>ApiTag: provider intents</b></kbd>, which groups all of the OpenAPIs for ProviderIntent objects under the tag "provider intents". 
 * </p>
 * <h2>ApiUri.enUS: /en-us/api/intent/provider</h2>
 * <p>This class contains a comment <kbd><b>ApiUri: /en-us/api/intent/provider</b></kbd>, which defines the base API URI for ProviderIntent objects as "/en-us/api/intent/provider" in the OpenAPI spec. 
 * </p>
 * <h2>Color: null</h2>
 * <h2>Indexed: true</h2>
 * <p>This class contains a comment <kbd><b>Indexed: true</b></kbd>, which means this class will be indexed in the search engine. 
 * Every protected void method that begins with "_" that is marked to be searched with a comment like "Indexed: true", "Stored: true", or "DocValues: true" will be indexed in the search engine. 
 * </p>
 * <h2>{@inheritDoc}</h2>
 * <p>By adding a class comment "{@inheritDoc}", the ProviderIntent class will inherit the helpful inherited class comments from the super class ProviderIntentGen. 
 * </p>
 * <h2>
 *   Rows: 10
 * </h2>
 * <p>This class contains a comment <kbd><b>Rows: 10</b></kbd>, which means the provider intent API will return a default of 10 results instead of 10 by default. 
 * Each API has built in pagination of the search results to ensure a user can query all the data a page at a time without running the application out of memory. 
 * </p>
 * <p>
 *   You can add a class comment <kbd><b>Rows: 100</b></kbd> if you wish for the provider intent API to return more or less than 10 results by default. 
 *   In this case, the API will return 100 results from the API instead of 10 by default. 
 *   Each API has built in pagination of the search results to ensure a user can query all the data a page at a time without running the application out of memory. 
 * </p>
 * <h2>
 *   Order: 151
 * </h2>
 * <p>
 *   This class contains a comment <kbd><b>Order: 151</b></kbd>, 
 *   which means this class will be sorted by the given number 151 
 *   ascending when code that relates to multiple classes at the same time is generated. 
 * </p>
 * <h2>SqlOrder: 151</h2>
 * <p>This class contains a comment <kbd><b>SqlOrder: 151</b></kbd>, which means this class will be sorted by the given number 151 ascending when SQL code to create and drop the tables is generated. 
 * </p>
 * <h2>Model: true</h2>
 * <p>This class contains a comment <kbd><b>Model: true</b></kbd>, which means this class will be stored in the database. 
 * Every protected void method that begins with "_" that contains a "Persist: true" comment will be a persisted field in the database table. 
 * </p>
 * <h2>Page: true</h2>
 * <p>This class contains a comment <kbd><b>Page: true</b></kbd>, which means this class will have webpage code generated for these objects. 
 * Java Vert.x backend API code, Handlebars HTML template frontend code, and JavaScript code will all generated and can be extended. 
 * This creates a new Java class org.computate.dcm.model.eda.provider.intent.ProviderIntentPage. 
 * </p>
 * <h2>SuperPage.enUS: PageLayout</h2>
 * <p>This class contains a comment <kbd><b>SuperPage.enUS: PageLayout</b></kbd>, which identifies the Java super class of the page code by it's class simple name "PageLayout". 
 * This means that the newly created class org.computate.dcm.model.eda.provider.intent.ProviderIntentPage extends org.computate.dcm.page.PageLayout. 
 * </p>
 * <h2>Promise: true</h2>
 * <p>
 *   This class contains a comment <kbd><b>Promise: true</b></kbd>
 *   Sometimes a Java class must be initialized asynchronously when it involves calling a blocking API. 
 *   This means that the ProviderIntent Java class has promiseDeep methods which must be initialized asynchronously as a Vert.x Promise  instead of initDeep methods which are a simple non-asynchronous method. 
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
 * <h2>AName.enUS: a provider intent</h2>
 * <p>This class contains a comment <kbd><b>AName.enUS: a provider intent</b></kbd>, which identifies the language context to describe a ProviderIntent as "a provider intent". 
 * </p>
 * <p>
 * Delete the class ProviderIntent in Solr: 
 * <pre>
 * curl -k 'https://solr.apps-crc.testing/solr/computate/update?commitWithin=1000&amp;overwrite=true&amp;wt=json' -X POST -H 'Content-type: text/xml' -u "admin:$(oc -n solr get secret/solr-solrcloud-security-bootstrap -o jsonpath={.data.admin} | base64 -d)" --data-raw '&lt;delete&gt;&lt;query&gt;classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.provider.intent.ProviderIntent&lt;/query&gt;&lt;/delete&gt;'
 * </pre>
 * </p>
 * <p>
 * Delete  the package org.computate.dcm.model.eda.provider.intent in Solr: 
 * <pre>
 * curl -k 'https://solr.apps-crc.testing/solr/computate/update?commitWithin=1000&amp;overwrite=true&amp;wt=json' -X POST -H 'Content-type: text/xml' -u "admin:$(oc -n solr get secret/solr-solrcloud-security-bootstrap -o jsonpath={.data.admin} | base64 -d)" --data-raw '&lt;delete&gt;&lt;query&gt;classeNomEnsemble_enUS_indexed_string:org.computate.dcm.model.eda.provider.intent&lt;/query&gt;&lt;/delete&gt;'
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
public abstract class ProviderIntentGen<DEV> extends Provider {
  protected static final Logger LOG = LoggerFactory.getLogger(ProviderIntent.class);

  public static final String Description_enUS = "A provider for requesting other DCM models. ";
  public static final String AName_enUS = "a provider intent";
  public static final String This_enUS = "this ";
  public static final String ThisName_enUS = "this provider intent";
  public static final String A_enUS = "a ";
  public static final String TheName_enUS = "the provider intent";
  public static final String SingularName_enUS = "provider intent";
  public static final String PluralName_enUS = "provider intents";
  public static final String NameActual_enUS = "current provider intent";
  public static final String AllName_enUS = "all provider intents";
  public static final String SearchAllNameBy_enUS = "search provider intents by ";
  public static final String SearchAllName_enUS = "search provider intents";
  public static final String Title_enUS = "provider intents";
  public static final String ThePluralName_enUS = "the provider intents";
  public static final String NoNameFound_enUS = "no provider intent found";
  public static final String ApiUri_enUS = "/en-us/api/intent/provider";
  public static final String ApiUriSearchPage_enUS = "/en-us/search/intent/provider";
  public static final String ApiUriEditPage_enUS = "/en-us/edit/intent/provider/{providerResource}";
  public static final String OfName_enUS = "of provider intent";
  public static final String ANameAdjective_enUS = "a provider intent";
  public static final String NameAdjectiveSingular_enUS = "provider intent";
  public static final String NameAdjectivePlural_enUS = "provider intents";
  public static final String Search_enUS_OpenApiUri = "/en-us/api/intent/provider";
  public static final String Search_enUS_StringFormatUri = "/en-us/api/intent/provider";
  public static final String Search_enUS_StringFormatUrl = "%s/en-us/api/intent/provider";
  public static final String GET_enUS_OpenApiUri = "/en-us/api/intent/provider/{providerResource}";
  public static final String GET_enUS_StringFormatUri = "/en-us/api/intent/provider/%s";
  public static final String GET_enUS_StringFormatUrl = "%s/en-us/api/intent/provider/%s";
  public static final String PATCH_enUS_OpenApiUri = "/en-us/api/intent/provider";
  public static final String PATCH_enUS_StringFormatUri = "/en-us/api/intent/provider";
  public static final String PATCH_enUS_StringFormatUrl = "%s/en-us/api/intent/provider";
  public static final String POST_enUS_OpenApiUri = "/en-us/api/intent/provider";
  public static final String POST_enUS_StringFormatUri = "/en-us/api/intent/provider";
  public static final String POST_enUS_StringFormatUrl = "%s/en-us/api/intent/provider";
  public static final String DELETE_enUS_OpenApiUri = "/en-us/api/intent/provider/{providerResource}";
  public static final String DELETE_enUS_StringFormatUri = "/en-us/api/intent/provider/%s";
  public static final String DELETE_enUS_StringFormatUrl = "%s/en-us/api/intent/provider/%s";
  public static final String PUTImport_enUS_OpenApiUri = "/en-us/api/intent/provider-import";
  public static final String PUTImport_enUS_StringFormatUri = "/en-us/api/intent/provider-import";
  public static final String PUTImport_enUS_StringFormatUrl = "%s/en-us/api/intent/provider-import";
  public static final String SearchPage_enUS_OpenApiUri = "/en-us/search/intent/provider";
  public static final String SearchPage_enUS_StringFormatUri = "/en-us/search/intent/provider";
  public static final String SearchPage_enUS_StringFormatUrl = "%s/en-us/search/intent/provider";
  public static final String EditPage_enUS_OpenApiUri = "/en-us/edit/intent/provider/{providerResource}";
  public static final String EditPage_enUS_StringFormatUri = "/en-us/edit/intent/provider/%s";
  public static final String EditPage_enUS_StringFormatUrl = "%s/en-us/edit/intent/provider/%s";
  public static final String DELETEFilter_enUS_OpenApiUri = "/en-us/api/intent/provider";
  public static final String DELETEFilter_enUS_StringFormatUri = "/en-us/api/intent/provider";
  public static final String DELETEFilter_enUS_StringFormatUrl = "%s/en-us/api/intent/provider";

  public static final String Icon = "<i class=\" fa-person-dolly\"></i>";

	//////////////////
  // providerName //
	//////////////////


  /**
   *  The entity providerName
   *	 is defined as null before being initialized. 
   */
  @JsonProperty
  @JsonInclude(Include.NON_NULL)
  protected String providerName;

  /**
   * <br> The entity providerName
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.provider.intent.ProviderIntent&fq=entiteVar_enUS_indexed_string:providerName">Find the entity providerName in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _providerName(Wrap<String> w);

  public String getProviderName() {
    return providerName;
  }
  public void setProviderName(String o) {
    this.providerName = ProviderIntent.staticSetProviderName(siteRequest_, o);
  }
  public static String staticSetProviderName(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected ProviderIntent providerNameInit() {
    Wrap<String> providerNameWrap = new Wrap<String>().var("providerName");
    if(providerName == null) {
      _providerName(providerNameWrap);
      Optional.ofNullable(providerNameWrap.getO()).ifPresent(o -> {
        setProviderName(o);
      });
    }
    return (ProviderIntent)this;
  }

  public static String staticSearchProviderName(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrProviderName(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqProviderName(SiteRequest siteRequest_, String o) {
    return ProviderIntent.staticSearchProviderName(siteRequest_, ProviderIntent.staticSetProviderName(siteRequest_, o)).toString();
  }

  public String sqlProviderName() {
    return providerName;
  }

  public static String staticJsonProviderName(String providerName) {
    return providerName;
  }

	////////////////
  // providerId //
	////////////////


  /**
   *  The entity providerId
   *	 is defined as null before being initialized. 
   */
  @JsonProperty
  @JsonInclude(Include.NON_NULL)
  protected String providerId;

  /**
   * <br> The entity providerId
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.provider.intent.ProviderIntent&fq=entiteVar_enUS_indexed_string:providerId">Find the entity providerId in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _providerId(Wrap<String> w);

  public String getProviderId() {
    return providerId;
  }
  public void setProviderId(String o) {
    this.providerId = ProviderIntent.staticSetProviderId(siteRequest_, o);
  }
  public static String staticSetProviderId(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected ProviderIntent providerIdInit() {
    Wrap<String> providerIdWrap = new Wrap<String>().var("providerId");
    if(providerId == null) {
      _providerId(providerIdWrap);
      Optional.ofNullable(providerIdWrap.getO()).ifPresent(o -> {
        setProviderId(o);
      });
    }
    return (ProviderIntent)this;
  }

  public static String staticSearchProviderId(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrProviderId(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqProviderId(SiteRequest siteRequest_, String o) {
    return ProviderIntent.staticSearchProviderId(siteRequest_, ProviderIntent.staticSetProviderId(siteRequest_, o)).toString();
  }

  public String sqlProviderId() {
    return providerId;
  }

  public static String staticJsonProviderId(String providerId) {
    return providerId;
  }

	//////////////////////
  // providerResource //
	//////////////////////


  /**
   *  The entity providerResource
   *	 is defined as null before being initialized. 
   */
  @JsonProperty
  @JsonInclude(Include.NON_NULL)
  protected String providerResource;

  /**
   * <br> The entity providerResource
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.provider.intent.ProviderIntent&fq=entiteVar_enUS_indexed_string:providerResource">Find the entity providerResource in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _providerResource(Wrap<String> w);

  public String getProviderResource() {
    return providerResource;
  }
  public void setProviderResource(String o) {
    this.providerResource = ProviderIntent.staticSetProviderResource(siteRequest_, o);
  }
  public static String staticSetProviderResource(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected ProviderIntent providerResourceInit() {
    Wrap<String> providerResourceWrap = new Wrap<String>().var("providerResource");
    if(providerResource == null) {
      _providerResource(providerResourceWrap);
      Optional.ofNullable(providerResourceWrap.getO()).ifPresent(o -> {
        setProviderResource(o);
      });
    }
    return (ProviderIntent)this;
  }

  public static String staticSearchProviderResource(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrProviderResource(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqProviderResource(SiteRequest siteRequest_, String o) {
    return ProviderIntent.staticSearchProviderResource(siteRequest_, ProviderIntent.staticSetProviderResource(siteRequest_, o)).toString();
  }

  public String sqlProviderResource() {
    return providerResource;
  }

  public static String staticJsonProviderResource(String providerResource) {
    return providerResource;
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
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.provider.intent.ProviderIntent&fq=entiteVar_enUS_indexed_string:createdByEmail">Find the entity createdByEmail in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _createdByEmail(Wrap<String> w);

  public String getCreatedByEmail() {
    return createdByEmail;
  }
  public void setCreatedByEmail(String o) {
    this.createdByEmail = ProviderIntent.staticSetCreatedByEmail(siteRequest_, o);
  }
  public static String staticSetCreatedByEmail(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected ProviderIntent createdByEmailInit() {
    Wrap<String> createdByEmailWrap = new Wrap<String>().var("createdByEmail");
    if(createdByEmail == null) {
      _createdByEmail(createdByEmailWrap);
      Optional.ofNullable(createdByEmailWrap.getO()).ifPresent(o -> {
        setCreatedByEmail(o);
      });
    }
    return (ProviderIntent)this;
  }

  public static String staticSearchCreatedByEmail(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrCreatedByEmail(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqCreatedByEmail(SiteRequest siteRequest_, String o) {
    return ProviderIntent.staticSearchCreatedByEmail(siteRequest_, ProviderIntent.staticSetCreatedByEmail(siteRequest_, o)).toString();
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
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.provider.intent.ProviderIntent&fq=entiteVar_enUS_indexed_string:createdByUserId">Find the entity createdByUserId in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _createdByUserId(Wrap<String> w);

  public String getCreatedByUserId() {
    return createdByUserId;
  }
  public void setCreatedByUserId(String o) {
    this.createdByUserId = ProviderIntent.staticSetCreatedByUserId(siteRequest_, o);
  }
  public static String staticSetCreatedByUserId(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected ProviderIntent createdByUserIdInit() {
    Wrap<String> createdByUserIdWrap = new Wrap<String>().var("createdByUserId");
    if(createdByUserId == null) {
      _createdByUserId(createdByUserIdWrap);
      Optional.ofNullable(createdByUserIdWrap.getO()).ifPresent(o -> {
        setCreatedByUserId(o);
      });
    }
    return (ProviderIntent)this;
  }

  public static String staticSearchCreatedByUserId(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrCreatedByUserId(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqCreatedByUserId(SiteRequest siteRequest_, String o) {
    return ProviderIntent.staticSearchCreatedByUserId(siteRequest_, ProviderIntent.staticSetCreatedByUserId(siteRequest_, o)).toString();
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
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.provider.intent.ProviderIntent&fq=entiteVar_enUS_indexed_string:createdByFullName">Find the entity createdByFullName in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _createdByFullName(Wrap<String> w);

  public String getCreatedByFullName() {
    return createdByFullName;
  }
  public void setCreatedByFullName(String o) {
    this.createdByFullName = ProviderIntent.staticSetCreatedByFullName(siteRequest_, o);
  }
  public static String staticSetCreatedByFullName(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected ProviderIntent createdByFullNameInit() {
    Wrap<String> createdByFullNameWrap = new Wrap<String>().var("createdByFullName");
    if(createdByFullName == null) {
      _createdByFullName(createdByFullNameWrap);
      Optional.ofNullable(createdByFullNameWrap.getO()).ifPresent(o -> {
        setCreatedByFullName(o);
      });
    }
    return (ProviderIntent)this;
  }

  public static String staticSearchCreatedByFullName(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrCreatedByFullName(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqCreatedByFullName(SiteRequest siteRequest_, String o) {
    return ProviderIntent.staticSearchCreatedByFullName(siteRequest_, ProviderIntent.staticSetCreatedByFullName(siteRequest_, o)).toString();
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
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.provider.intent.ProviderIntent&fq=entiteVar_enUS_indexed_string:createdVia">Find the entity createdVia in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _createdVia(Wrap<String> w);

  public String getCreatedVia() {
    return createdVia;
  }
  public void setCreatedVia(String o) {
    this.createdVia = ProviderIntent.staticSetCreatedVia(siteRequest_, o);
  }
  public static String staticSetCreatedVia(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected ProviderIntent createdViaInit() {
    Wrap<String> createdViaWrap = new Wrap<String>().var("createdVia");
    if(createdVia == null) {
      _createdVia(createdViaWrap);
      Optional.ofNullable(createdViaWrap.getO()).ifPresent(o -> {
        setCreatedVia(o);
      });
    }
    return (ProviderIntent)this;
  }

  public static String staticSearchCreatedVia(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrCreatedVia(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqCreatedVia(SiteRequest siteRequest_, String o) {
    return ProviderIntent.staticSearchCreatedVia(siteRequest_, ProviderIntent.staticSetCreatedVia(siteRequest_, o)).toString();
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
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.provider.intent.ProviderIntent&fq=entiteVar_enUS_indexed_string:intentState">Find the entity intentState in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _intentState(Wrap<String> w);

  public String getIntentState() {
    return intentState;
  }
  public void setIntentState(String o) {
    this.intentState = ProviderIntent.staticSetIntentState(siteRequest_, o);
  }
  public static String staticSetIntentState(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected ProviderIntent intentStateInit() {
    Wrap<String> intentStateWrap = new Wrap<String>().var("intentState");
    if(intentState == null) {
      _intentState(intentStateWrap);
      Optional.ofNullable(intentStateWrap.getO()).ifPresent(o -> {
        setIntentState(o);
      });
    }
    return (ProviderIntent)this;
  }

  public static String staticSearchIntentState(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrIntentState(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqIntentState(SiteRequest siteRequest_, String o) {
    return ProviderIntent.staticSearchIntentState(siteRequest_, ProviderIntent.staticSetIntentState(siteRequest_, o)).toString();
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
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.provider.intent.ProviderIntent&fq=entiteVar_enUS_indexed_string:requestedState">Find the entity requestedState in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _requestedState(Wrap<String> w);

  public String getRequestedState() {
    return requestedState;
  }
  public void setRequestedState(String o) {
    this.requestedState = ProviderIntent.staticSetRequestedState(siteRequest_, o);
  }
  public static String staticSetRequestedState(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected ProviderIntent requestedStateInit() {
    Wrap<String> requestedStateWrap = new Wrap<String>().var("requestedState");
    if(requestedState == null) {
      _requestedState(requestedStateWrap);
      Optional.ofNullable(requestedStateWrap.getO()).ifPresent(o -> {
        setRequestedState(o);
      });
    }
    return (ProviderIntent)this;
  }

  public static String staticSearchRequestedState(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrRequestedState(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqRequestedState(SiteRequest siteRequest_, String o) {
    return ProviderIntent.staticSearchRequestedState(siteRequest_, ProviderIntent.staticSetRequestedState(siteRequest_, o)).toString();
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
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.provider.intent.ProviderIntent&fq=entiteVar_enUS_indexed_string:realizedState">Find the entity realizedState in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _realizedState(Wrap<String> w);

  public String getRealizedState() {
    return realizedState;
  }
  public void setRealizedState(String o) {
    this.realizedState = ProviderIntent.staticSetRealizedState(siteRequest_, o);
  }
  public static String staticSetRealizedState(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected ProviderIntent realizedStateInit() {
    Wrap<String> realizedStateWrap = new Wrap<String>().var("realizedState");
    if(realizedState == null) {
      _realizedState(realizedStateWrap);
      Optional.ofNullable(realizedStateWrap.getO()).ifPresent(o -> {
        setRealizedState(o);
      });
    }
    return (ProviderIntent)this;
  }

  public static String staticSearchRealizedState(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrRealizedState(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqRealizedState(SiteRequest siteRequest_, String o) {
    return ProviderIntent.staticSearchRealizedState(siteRequest_, ProviderIntent.staticSetRealizedState(siteRequest_, o)).toString();
  }

  public String sqlRealizedState() {
    return realizedState;
  }

  public static String staticJsonRealizedState(String realizedState) {
    return realizedState;
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
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.provider.intent.ProviderIntent&fq=entiteVar_enUS_indexed_string:description">Find the entity description in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _description(Wrap<String> w);

  public String getDescription() {
    return description;
  }
  public void setDescription(String o) {
    this.description = ProviderIntent.staticSetDescription(siteRequest_, o);
  }
  public static String staticSetDescription(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected ProviderIntent descriptionInit() {
    Wrap<String> descriptionWrap = new Wrap<String>().var("description");
    if(description == null) {
      _description(descriptionWrap);
      Optional.ofNullable(descriptionWrap.getO()).ifPresent(o -> {
        setDescription(o);
      });
    }
    return (ProviderIntent)this;
  }

  public static String staticSearchDescription(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrDescription(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqDescription(SiteRequest siteRequest_, String o) {
    return ProviderIntent.staticSearchDescription(siteRequest_, ProviderIntent.staticSetDescription(siteRequest_, o)).toString();
  }

  public String sqlDescription() {
    return description;
  }

  public static String staticJsonDescription(String description) {
    return description;
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
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.provider.intent.ProviderIntent&fq=entiteVar_enUS_indexed_string:requested">Find the entity requested in Solr</a>
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
    String l = ProviderIntent.staticSetRequested(siteRequest_, o);
    if(l != null)
      addRequested(l);
  }
  public static String staticSetRequested(SiteRequest siteRequest_, String o) {
    return o;
  }
  public ProviderIntent addRequested(String...objects) {
    for(String o : objects) {
      addRequested(o);
    }
    return (ProviderIntent)this;
  }
  public ProviderIntent addRequested(String o) {
    if(o != null)
      this.requested.add(o);
    return (ProviderIntent)this;
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
  protected ProviderIntent requestedInit() {
    _requested(requested);
    return (ProviderIntent)this;
  }

  public static String staticSearchRequested(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrRequested(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqRequested(SiteRequest siteRequest_, String o) {
    return ProviderIntent.staticSearchRequested(siteRequest_, ProviderIntent.staticSetRequested(siteRequest_, o)).toString();
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
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.provider.intent.ProviderIntent&fq=entiteVar_enUS_indexed_string:locked">Find the entity locked in Solr</a>
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
    this.locked = ProviderIntent.staticSetLocked(siteRequest_, o);
  }
  public static Boolean staticSetLocked(SiteRequest siteRequest_, String o) {
    return Boolean.parseBoolean(o);
  }
  protected ProviderIntent lockedInit() {
    Wrap<Boolean> lockedWrap = new Wrap<Boolean>().var("locked");
    if(locked == null) {
      _locked(lockedWrap);
      Optional.ofNullable(lockedWrap.getO()).ifPresent(o -> {
        setLocked(o);
      });
    }
    return (ProviderIntent)this;
  }

  public static Boolean staticSearchLocked(SiteRequest siteRequest_, Boolean o) {
    return o;
  }

  public static String staticSearchStrLocked(SiteRequest siteRequest_, Boolean o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqLocked(SiteRequest siteRequest_, String o) {
    return ProviderIntent.staticSearchLocked(siteRequest_, ProviderIntent.staticSetLocked(siteRequest_, o)).toString();
  }

  public Boolean sqlLocked() {
    return locked;
  }

  public static Boolean staticJsonLocked(Boolean locked) {
    return locked;
  }

	///////////////////
  // dcmDiscovered //
	///////////////////


  /**
   *  The entity dcmDiscovered
   *	 It is constructed before being initialized with the constructor by default. 
   */
  @JsonProperty
  @JsonFormat(shape = JsonFormat.Shape.ARRAY)
  @JsonInclude(Include.NON_NULL)
  protected List<String> dcmDiscovered = new ArrayList<String>();

  /**
   * <br> The entity dcmDiscovered
   *  It is constructed before being initialized with the constructor by default. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.provider.intent.ProviderIntent&fq=entiteVar_enUS_indexed_string:dcmDiscovered">Find the entity dcmDiscovered in Solr</a>
   * <br>
   * @param l is the entity already constructed. 
   **/
  protected abstract void _dcmDiscovered(List<String> l);

  public List<String> getDcmDiscovered() {
    return dcmDiscovered;
  }

  public void setDcmDiscovered(List<String> dcmDiscovered) {
    this.dcmDiscovered = dcmDiscovered;
  }
  @JsonIgnore
  public void setDcmDiscovered(String o) {
    String l = ProviderIntent.staticSetDcmDiscovered(siteRequest_, o);
    if(l != null)
      addDcmDiscovered(l);
  }
  public static String staticSetDcmDiscovered(SiteRequest siteRequest_, String o) {
    return o;
  }
  public ProviderIntent addDcmDiscovered(String...objects) {
    for(String o : objects) {
      addDcmDiscovered(o);
    }
    return (ProviderIntent)this;
  }
  public ProviderIntent addDcmDiscovered(String o) {
    if(o != null)
      this.dcmDiscovered.add(o);
    return (ProviderIntent)this;
  }
  @JsonIgnore
  public void setDcmDiscovered(JsonArray objects) {
    dcmDiscovered.clear();
    if(objects == null)
      return;
    for(int i = 0; i < objects.size(); i++) {
      String o = objects.getString(i);
      addDcmDiscovered(o);
    }
  }
  protected ProviderIntent dcmDiscoveredInit() {
    _dcmDiscovered(dcmDiscovered);
    return (ProviderIntent)this;
  }

  public static String staticSearchDcmDiscovered(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrDcmDiscovered(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqDcmDiscovered(SiteRequest siteRequest_, String o) {
    return ProviderIntent.staticSearchDcmDiscovered(siteRequest_, ProviderIntent.staticSetDcmDiscovered(siteRequest_, o)).toString();
  }

	/////////////////
  // dcmRealized //
	/////////////////


  /**
   *  The entity dcmRealized
   *	 It is constructed before being initialized with the constructor by default. 
   */
  @JsonProperty
  @JsonFormat(shape = JsonFormat.Shape.ARRAY)
  @JsonInclude(Include.NON_NULL)
  protected List<String> dcmRealized = new ArrayList<String>();

  /**
   * <br> The entity dcmRealized
   *  It is constructed before being initialized with the constructor by default. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.provider.intent.ProviderIntent&fq=entiteVar_enUS_indexed_string:dcmRealized">Find the entity dcmRealized in Solr</a>
   * <br>
   * @param l is the entity already constructed. 
   **/
  protected abstract void _dcmRealized(List<String> l);

  public List<String> getDcmRealized() {
    return dcmRealized;
  }

  public void setDcmRealized(List<String> dcmRealized) {
    this.dcmRealized = dcmRealized;
  }
  @JsonIgnore
  public void setDcmRealized(String o) {
    String l = ProviderIntent.staticSetDcmRealized(siteRequest_, o);
    if(l != null)
      addDcmRealized(l);
  }
  public static String staticSetDcmRealized(SiteRequest siteRequest_, String o) {
    return o;
  }
  public ProviderIntent addDcmRealized(String...objects) {
    for(String o : objects) {
      addDcmRealized(o);
    }
    return (ProviderIntent)this;
  }
  public ProviderIntent addDcmRealized(String o) {
    if(o != null)
      this.dcmRealized.add(o);
    return (ProviderIntent)this;
  }
  @JsonIgnore
  public void setDcmRealized(JsonArray objects) {
    dcmRealized.clear();
    if(objects == null)
      return;
    for(int i = 0; i < objects.size(); i++) {
      String o = objects.getString(i);
      addDcmRealized(o);
    }
  }
  protected ProviderIntent dcmRealizedInit() {
    _dcmRealized(dcmRealized);
    return (ProviderIntent)this;
  }

  public static String staticSearchDcmRealized(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrDcmRealized(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqDcmRealized(SiteRequest siteRequest_, String o) {
    return ProviderIntent.staticSearchDcmRealized(siteRequest_, ProviderIntent.staticSetDcmRealized(siteRequest_, o)).toString();
  }

  //////////////
  // initDeep //
  //////////////

  public Future<ProviderIntentGen<DEV>> promiseDeepProviderIntent(SiteRequest siteRequest_) {
    if(this.siteRequest_ == null)
      setSiteRequest_(siteRequest_);
    return promiseDeepProviderIntent();
  }

  public Future<ProviderIntentGen<DEV>> promiseDeepProviderIntent() {
    Promise<ProviderIntentGen<DEV>> promise = Promise.promise();
    Promise<Void> promise2 = Promise.promise();
    promiseProviderIntent(promise2);
    promise2.future().onSuccess(a -> {
      super.promiseDeepProvider(siteRequest_).onSuccess(b -> {
        promise.complete(this);
      }).onFailure(ex -> {
        promise.fail(ex);
      });
    }).onFailure(ex -> {
      promise.fail(ex);
    });
    return promise.future();
  }

  public Future<Void> promiseProviderIntent(Promise<Void> promise) {
    Future.future(a -> a.complete()).compose(a -> {
      Promise<Void> promise2 = Promise.promise();
      try {
        providerNameInit();
        providerIdInit();
        providerResourceInit();
        createdByEmailInit();
        createdByUserIdInit();
        createdByFullNameInit();
        createdViaInit();
        intentStateInit();
        requestedStateInit();
        realizedStateInit();
        descriptionInit();
        requestedInit();
        lockedInit();
        dcmDiscoveredInit();
        dcmRealizedInit();
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

  @Override public Future<? extends ProviderIntentGen<DEV>> promiseDeepForClass(SiteRequest siteRequest_) {
    return promiseDeepProviderIntent(siteRequest_);
  }

  /////////////////
  // siteRequest //
  /////////////////

  public void siteRequestProviderIntent(SiteRequest siteRequest_) {
      super.siteRequestProvider(siteRequest_);
  }

  public void siteRequestForClass(SiteRequest siteRequest_) {
    siteRequestProviderIntent(siteRequest_);
  }

  /////////////
  // obtain //
  /////////////

  @Override public Object obtainForClass(String var) {
    String[] vars = StringUtils.split(var, ".");
    Object o = null;
    for(String v : vars) {
      if(o == null)
        o = obtainProviderIntent(v);
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
  public Object obtainProviderIntent(String var) {
    ProviderIntent oProviderIntent = (ProviderIntent)this;
    switch(var) {
      case "providerName":
        return oProviderIntent.providerName;
      case "providerId":
        return oProviderIntent.providerId;
      case "providerResource":
        return oProviderIntent.providerResource;
      case "createdByEmail":
        return oProviderIntent.createdByEmail;
      case "createdByUserId":
        return oProviderIntent.createdByUserId;
      case "createdByFullName":
        return oProviderIntent.createdByFullName;
      case "createdVia":
        return oProviderIntent.createdVia;
      case "intentState":
        return oProviderIntent.intentState;
      case "requestedState":
        return oProviderIntent.requestedState;
      case "realizedState":
        return oProviderIntent.realizedState;
      case "description":
        return oProviderIntent.description;
      case "requested":
        return oProviderIntent.requested;
      case "locked":
        return oProviderIntent.locked;
      case "dcmDiscovered":
        return oProviderIntent.dcmDiscovered;
      case "dcmRealized":
        return oProviderIntent.dcmRealized;
      default:
        return super.obtainProvider(var);
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
        o = relateProviderIntent(v, val);
      else if(o instanceof BaseModel) {
        BaseModel baseModel = (BaseModel)o;
        o = baseModel.relateForClass(v, val);
      }
    }
    return o != null;
  }
  public Object relateProviderIntent(String var, Object val) {
    ProviderIntent oProviderIntent = (ProviderIntent)this;
    switch(var) {
      default:
        return super.relateProvider(var, val);
    }
  }

  ///////////////
  // staticSet //
  ///////////////

  public static Object staticSetForClass(String entityVar, SiteRequest siteRequest_, String v, ProviderIntent o) {
    return staticSetProviderIntent(entityVar,  siteRequest_, v, o);
  }
  public static Object staticSetProviderIntent(String entityVar, SiteRequest siteRequest_, String v, ProviderIntent o) {
    switch(entityVar) {
    case "providerName":
      return ProviderIntent.staticSetProviderName(siteRequest_, v);
    case "providerId":
      return ProviderIntent.staticSetProviderId(siteRequest_, v);
    case "providerResource":
      return ProviderIntent.staticSetProviderResource(siteRequest_, v);
    case "createdByEmail":
      return ProviderIntent.staticSetCreatedByEmail(siteRequest_, v);
    case "createdByUserId":
      return ProviderIntent.staticSetCreatedByUserId(siteRequest_, v);
    case "createdByFullName":
      return ProviderIntent.staticSetCreatedByFullName(siteRequest_, v);
    case "createdVia":
      return ProviderIntent.staticSetCreatedVia(siteRequest_, v);
    case "intentState":
      return ProviderIntent.staticSetIntentState(siteRequest_, v);
    case "requestedState":
      return ProviderIntent.staticSetRequestedState(siteRequest_, v);
    case "realizedState":
      return ProviderIntent.staticSetRealizedState(siteRequest_, v);
    case "description":
      return ProviderIntent.staticSetDescription(siteRequest_, v);
    case "requested":
      return ProviderIntent.staticSetRequested(siteRequest_, v);
    case "locked":
      return ProviderIntent.staticSetLocked(siteRequest_, v);
    case "dcmDiscovered":
      return ProviderIntent.staticSetDcmDiscovered(siteRequest_, v);
    case "dcmRealized":
      return ProviderIntent.staticSetDcmRealized(siteRequest_, v);
      default:
        return Provider.staticSetProvider(entityVar,  siteRequest_, v, o);
    }
  }

  //////////////////
  // authorization //
  //////////////////

  public static Future<AsyncResult<HttpResponse<Buffer>>> authorizationProviderIntent(SiteRequest siteRequest, WebClient webClient, Boolean classPublicRead, String classApiMethod, String classApiMethodMethod, String scope) {
    Promise<AsyncResult<HttpResponse<Buffer>>> promise = Promise.promise();
    try {
      JsonObject config = siteRequest.getConfig();
      SiteRequest siteRequest2 = siteRequest.copy();
      String providerResource = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("providerResource");
      String PROVIDER = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("PROVIDER");
      List<String> groups = Optional.ofNullable(siteRequest.getGroups()).orElse(new ArrayList<>());
      MultiMap form = MultiMap.caseInsensitiveMultiMap();
      form.add("grant_type", "urn:ietf:params:oauth:grant-type:uma-ticket");
      form.add("audience", config.getString(ComputateConfigKeys.AUTH_CLIENT));
      form.add("response_mode", "permissions");
      form.add("permission", String.format("%s#%s", ProviderIntent.CLASS_AUTH_RESOURCE, "GET"));
      form.add("permission", String.format("%s#%s", ProviderIntent.CLASS_AUTH_RESOURCE, "POST"));
      form.add("permission", String.format("%s#%s", ProviderIntent.CLASS_AUTH_RESOURCE, "PATCH"));
      form.add("permission", String.format("%s#%s", ProviderIntent.CLASS_AUTH_RESOURCE, "DELETE"));
      form.add("permission", String.format("%s#%s", ProviderIntent.CLASS_AUTH_RESOURCE, "Admin"));
      form.add("permission", String.format("%s#%s", ProviderIntent.CLASS_AUTH_RESOURCE, "SuperAdmin"));
      if(providerResource != null)
        form.add("permission", String.format("%s#%s", providerResource, scope));
      groups.stream().map(group -> {
            Matcher mPermission = Pattern.compile("^/(.*-?PROVIDER-([a-z0-9\\-]+))-(\\w+)$").matcher(group);
            return mPermission.find() ? mPermission : null;
          }).filter(v -> v != null).forEach(mPermission -> {
            form.add("permission", String.format("%s#%s", mPermission.group(1), mPermission.group(3)));
          });
      webClient.post(
          config.getInteger(ComputateConfigKeys.AUTH_PORT)
            , config.getString(ComputateConfigKeys.AUTH_HOST_NAME)
            , config.getString(ComputateConfigKeys.AUTH_TOKEN_URI)
            )
            .ssl(config.getBoolean(ComputateConfigKeys.AUTH_SSL))
            .putHeader("Authorization", String.format("Bearer %s", Optional.ofNullable(siteRequest.getUser()).map(u -> u.principal().getString("access_token")).orElse("")))
            .sendForm(form)
            .expecting(HttpResponseExpectation.SC_OK)
      .onComplete(authorizationDecisionResponse -> {
        promise.complete(authorizationDecisionResponse);
      });
    } catch(Throwable ex) {
      LOG.error("Error while querying the provider intent", ex);
      promise.fail(ex);
    }
    return promise.future();
  }

  public static Future<SiteRequest> authorizationScopesProviderIntent(AsyncResult<HttpResponse<Buffer>> authorizationDecisionResponse, SiteRequest siteRequest2, WebClient webClient, Boolean classPublicRead, String classApiMethod, String classApiMethodMethod, String scope) {
    Promise<SiteRequest> promise = Promise.promise();
    try {
      siteRequest2.initDeepSiteRequest();
      HttpResponse<Buffer> authorizationDecision = authorizationDecisionResponse.result();
      JsonArray authorizationDecisionBody = authorizationDecisionResponse.failed() ? new JsonArray() : authorizationDecision.bodyAsJsonArray();
      JsonArray scopes = authorizationDecisionBody.stream().map(o -> (JsonObject)o).filter(o -> "PROVIDER".equals(o.getString("rsname"))).findFirst().map(decision -> ((JsonObject)decision).getJsonArray("scopes")).orElse(new JsonArray());
      if(!scopes.contains(scope)) {
        List<String> fqs = new ArrayList<>();
        authorizationDecisionBody.stream().map(o -> (JsonObject)o).filter(permission -> {
              Matcher mPermission = Pattern.compile("^(.*-?PROVIDER-([a-z0-9\\-]+))$").matcher(permission.getString("rsname"));
              return permission.getJsonArray("scopes").contains(scope)
                  && mPermission.find();
            }).forEach(permission -> {
              fqs.add(String.format("%s:%s", "providerResource", permission.getString("rsname")));
              permission.getJsonArray("scopes").stream().map(s -> (String)s).forEach(scope2 -> {
                if(!scopes.contains(scope2))
                  scopes.add(scope2);
              });
            });
        if(!"GET".equals(classApiMethodMethod) || !classPublicRead) {
          JsonObject authParams = siteRequest2.getServiceRequest().getParams();
          JsonObject authQuery = authParams.getJsonObject("query");
          if(authQuery == null) {
            authQuery = new JsonObject();
            authParams.put("query", authQuery);
          }
          JsonArray fq = authQuery.getJsonArray("fq");
          if(fq == null) {
            fq = new JsonArray();
            authQuery.put("fq", fq);
          }
          if(fqs.size() > 0) {
            fq.add(fqs.stream().collect(Collectors.joining(" OR ")));
            if(!scopes.contains(scope))
              scopes.add(scope);
            siteRequest2.setFilteredScope(true);
          }
        }
      }
      if("GET".equals(classApiMethod) && !"GET".equals(classApiMethod)) {
        siteRequest2.setScopes(scopes.stream().map(o -> o.toString()).collect(Collectors.toList()));
        promise.complete(siteRequest2);
      } else if("GET".equals(classApiMethod) && authorizationDecisionResponse.failed() && !scopes.contains(classApiMethodMethod)
           || authorizationDecisionResponse.failed() || !scopes.contains(classApiMethodMethod)) {
        promise.complete(siteRequest2);
      } else {
        siteRequest2.setScopes(scopes.stream().map(o -> o.toString()).collect(Collectors.toList()));
        promise.complete(siteRequest2);
      }
    } catch(Throwable ex) {
      LOG.error("Error while querying the provider intent", ex);
      promise.fail(ex);
    }
    return promise.future();
  }

  //////////////////
  // staticSearch //
  //////////////////

  public static Future<ProviderIntent> fqProviderIntent(SiteRequest siteRequest, String var, Object val) {
    Promise<ProviderIntent> promise = Promise.promise();
    try {
      if(val == null) {
        promise.complete();
      } else {
        SearchList<ProviderIntent> searchList = new SearchList<ProviderIntent>();
        searchList.setStore(true);
        searchList.q("*:*");
        searchList.setC(ProviderIntent.class);
        searchList.fq(String.format("%s:", ProviderIntent.varIndexedProviderIntent(var)) + SearchTool.escapeQueryChars(val.toString()));
        searchList.promiseDeepForClass(siteRequest).onSuccess(a -> {
          try {
            promise.complete(searchList.getList().stream().findFirst().orElse(null));
          } catch(Throwable ex) {
            LOG.error("Error while querying the provider intent", ex);
            promise.fail(ex);
          }
        }).onFailure(ex -> {
          LOG.error("Error while querying the provider intent", ex);
          promise.fail(ex);
        });
      }
    } catch(Throwable ex) {
      LOG.error("Error while querying the provider intent", ex);
      promise.fail(ex);
    }
    return promise.future();
  }

  public static Object staticSearchForClass(String entityVar, SiteRequest siteRequest_, Object o) {
    return staticSearchProviderIntent(entityVar,  siteRequest_, o);
  }
  public static Object staticSearchProviderIntent(String entityVar, SiteRequest siteRequest_, Object o) {
    switch(entityVar) {
    case "providerName":
      return ProviderIntent.staticSearchProviderName(siteRequest_, (String)o);
    case "providerId":
      return ProviderIntent.staticSearchProviderId(siteRequest_, (String)o);
    case "providerResource":
      return ProviderIntent.staticSearchProviderResource(siteRequest_, (String)o);
    case "createdByEmail":
      return ProviderIntent.staticSearchCreatedByEmail(siteRequest_, (String)o);
    case "createdByUserId":
      return ProviderIntent.staticSearchCreatedByUserId(siteRequest_, (String)o);
    case "createdByFullName":
      return ProviderIntent.staticSearchCreatedByFullName(siteRequest_, (String)o);
    case "createdVia":
      return ProviderIntent.staticSearchCreatedVia(siteRequest_, (String)o);
    case "intentState":
      return ProviderIntent.staticSearchIntentState(siteRequest_, (String)o);
    case "requestedState":
      return ProviderIntent.staticSearchRequestedState(siteRequest_, (String)o);
    case "realizedState":
      return ProviderIntent.staticSearchRealizedState(siteRequest_, (String)o);
    case "description":
      return ProviderIntent.staticSearchDescription(siteRequest_, (String)o);
    case "requested":
      return ProviderIntent.staticSearchRequested(siteRequest_, (String)o);
    case "locked":
      return ProviderIntent.staticSearchLocked(siteRequest_, (Boolean)o);
    case "dcmDiscovered":
      return ProviderIntent.staticSearchDcmDiscovered(siteRequest_, (String)o);
    case "dcmRealized":
      return ProviderIntent.staticSearchDcmRealized(siteRequest_, (String)o);
      default:
        return Provider.staticSearchProvider(entityVar,  siteRequest_, o);
    }
  }

  ///////////////////
  // staticSearchStr //
  ///////////////////

  public static String staticSearchStrForClass(String entityVar, SiteRequest siteRequest_, Object o) {
    return staticSearchStrProviderIntent(entityVar,  siteRequest_, o);
  }
  public static String staticSearchStrProviderIntent(String entityVar, SiteRequest siteRequest_, Object o) {
    switch(entityVar) {
    case "providerName":
      return ProviderIntent.staticSearchStrProviderName(siteRequest_, (String)o);
    case "providerId":
      return ProviderIntent.staticSearchStrProviderId(siteRequest_, (String)o);
    case "providerResource":
      return ProviderIntent.staticSearchStrProviderResource(siteRequest_, (String)o);
    case "createdByEmail":
      return ProviderIntent.staticSearchStrCreatedByEmail(siteRequest_, (String)o);
    case "createdByUserId":
      return ProviderIntent.staticSearchStrCreatedByUserId(siteRequest_, (String)o);
    case "createdByFullName":
      return ProviderIntent.staticSearchStrCreatedByFullName(siteRequest_, (String)o);
    case "createdVia":
      return ProviderIntent.staticSearchStrCreatedVia(siteRequest_, (String)o);
    case "intentState":
      return ProviderIntent.staticSearchStrIntentState(siteRequest_, (String)o);
    case "requestedState":
      return ProviderIntent.staticSearchStrRequestedState(siteRequest_, (String)o);
    case "realizedState":
      return ProviderIntent.staticSearchStrRealizedState(siteRequest_, (String)o);
    case "description":
      return ProviderIntent.staticSearchStrDescription(siteRequest_, (String)o);
    case "requested":
      return ProviderIntent.staticSearchStrRequested(siteRequest_, (String)o);
    case "locked":
      return ProviderIntent.staticSearchStrLocked(siteRequest_, (Boolean)o);
    case "dcmDiscovered":
      return ProviderIntent.staticSearchStrDcmDiscovered(siteRequest_, (String)o);
    case "dcmRealized":
      return ProviderIntent.staticSearchStrDcmRealized(siteRequest_, (String)o);
      default:
        return Provider.staticSearchStrProvider(entityVar,  siteRequest_, o);
    }
  }

  //////////////////
  // staticSearchFq //
  //////////////////

  public static String staticSearchFqForClass(String entityVar, SiteRequest siteRequest_, String o) {
    return staticSearchFqProviderIntent(entityVar,  siteRequest_, o);
  }
  public static String staticSearchFqProviderIntent(String entityVar, SiteRequest siteRequest_, String o) {
    switch(entityVar) {
    case "providerName":
      return ProviderIntent.staticSearchFqProviderName(siteRequest_, o);
    case "providerId":
      return ProviderIntent.staticSearchFqProviderId(siteRequest_, o);
    case "providerResource":
      return ProviderIntent.staticSearchFqProviderResource(siteRequest_, o);
    case "createdByEmail":
      return ProviderIntent.staticSearchFqCreatedByEmail(siteRequest_, o);
    case "createdByUserId":
      return ProviderIntent.staticSearchFqCreatedByUserId(siteRequest_, o);
    case "createdByFullName":
      return ProviderIntent.staticSearchFqCreatedByFullName(siteRequest_, o);
    case "createdVia":
      return ProviderIntent.staticSearchFqCreatedVia(siteRequest_, o);
    case "intentState":
      return ProviderIntent.staticSearchFqIntentState(siteRequest_, o);
    case "requestedState":
      return ProviderIntent.staticSearchFqRequestedState(siteRequest_, o);
    case "realizedState":
      return ProviderIntent.staticSearchFqRealizedState(siteRequest_, o);
    case "description":
      return ProviderIntent.staticSearchFqDescription(siteRequest_, o);
    case "requested":
      return ProviderIntent.staticSearchFqRequested(siteRequest_, o);
    case "locked":
      return ProviderIntent.staticSearchFqLocked(siteRequest_, o);
    case "dcmDiscovered":
      return ProviderIntent.staticSearchFqDcmDiscovered(siteRequest_, o);
    case "dcmRealized":
      return ProviderIntent.staticSearchFqDcmRealized(siteRequest_, o);
      default:
        return Provider.staticSearchFqProvider(entityVar,  siteRequest_, o);
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
          o = persistProviderIntent(v, val);
        else if(o instanceof BaseModel) {
          BaseModel oBaseModel = (BaseModel)o;
          o = oBaseModel.persistForClass(v, val);
        }
      }
    }
    return o != null;
  }
  public Object persistProviderIntent(String var, Object val) {
    String varLower = var.toLowerCase();
      if("providername".equals(varLower)) {
        if(val instanceof String) {
          setProviderName((String)val);
        }
        saves.add("providerName");
        return val;
      } else if("providerid".equals(varLower)) {
        if(val instanceof String) {
          setProviderId((String)val);
        }
        saves.add("providerId");
        return val;
      } else if("providerresource".equals(varLower)) {
        if(val instanceof String) {
          setProviderResource((String)val);
        }
        saves.add("providerResource");
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
      } else if("description".equals(varLower)) {
        if(val instanceof String) {
          setDescription((String)val);
        }
        saves.add("description");
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
      return super.persistProvider(var, val);
    }
  }

  /////////////
  // populate //
  /////////////

  @Override public void populateForClass(SolrResponse.Doc doc) {
    populateProviderIntent(doc);
  }
  public void populateProviderIntent(SolrResponse.Doc doc) {
    ProviderIntent oProviderIntent = (ProviderIntent)this;
    saves = Optional.ofNullable((ArrayList<String>)doc.get("saves_docvalues_strings")).orElse(new ArrayList<String>());
    if(saves != null) {

      if(saves.contains("providerName")) {
        String providerName = (String)doc.get("providerName_docvalues_string");
        if(providerName != null)
          oProviderIntent.setProviderName(providerName);
      }

      if(saves.contains("providerId")) {
        String providerId = (String)doc.get("providerId_docvalues_string");
        if(providerId != null)
          oProviderIntent.setProviderId(providerId);
      }

      if(saves.contains("providerResource")) {
        String providerResource = (String)doc.get("providerResource_docvalues_string");
        if(providerResource != null)
          oProviderIntent.setProviderResource(providerResource);
      }

      if(saves.contains("createdByEmail")) {
        String createdByEmail = (String)doc.get("createdByEmail_docvalues_string");
        if(createdByEmail != null)
          oProviderIntent.setCreatedByEmail(createdByEmail);
      }

      if(saves.contains("createdByUserId")) {
        String createdByUserId = (String)doc.get("createdByUserId_docvalues_string");
        if(createdByUserId != null)
          oProviderIntent.setCreatedByUserId(createdByUserId);
      }

      if(saves.contains("createdByFullName")) {
        String createdByFullName = (String)doc.get("createdByFullName_docvalues_string");
        if(createdByFullName != null)
          oProviderIntent.setCreatedByFullName(createdByFullName);
      }

      if(saves.contains("createdVia")) {
        String createdVia = (String)doc.get("createdVia_docvalues_string");
        if(createdVia != null)
          oProviderIntent.setCreatedVia(createdVia);
      }

      if(saves.contains("intentState")) {
        String intentState = (String)doc.get("intentState_docvalues_string");
        if(intentState != null)
          oProviderIntent.setIntentState(intentState);
      }

      if(saves.contains("requestedState")) {
        String requestedState = (String)doc.get("requestedState_docvalues_string");
        if(requestedState != null)
          oProviderIntent.setRequestedState(requestedState);
      }

      if(saves.contains("realizedState")) {
        String realizedState = (String)doc.get("realizedState_docvalues_string");
        if(realizedState != null)
          oProviderIntent.setRealizedState(realizedState);
      }

      if(saves.contains("description")) {
        String description = (String)doc.get("description_docvalues_string");
        if(description != null)
          oProviderIntent.setDescription(description);
      }

      if(saves.contains("requested")) {
        List<String> requested = (List<String>)doc.get("requested_docvalues_strings");
        if(requested != null) {
          requested.stream().forEach( v -> {
            oProviderIntent.requested.add(ProviderIntent.staticSetRequested(siteRequest_, v));
          });
        }
      }

      if(saves.contains("locked")) {
        Boolean locked = (Boolean)doc.get("locked_docvalues_boolean");
        if(locked != null)
          oProviderIntent.setLocked(locked);
      }

      if(saves.contains("dcmDiscovered")) {
        List<String> dcmDiscovered = (List<String>)doc.get("dcmDiscovered_docvalues_strings");
        if(dcmDiscovered != null) {
          dcmDiscovered.stream().forEach( v -> {
            oProviderIntent.dcmDiscovered.add(ProviderIntent.staticSetDcmDiscovered(siteRequest_, v));
          });
        }
      }

      if(saves.contains("dcmRealized")) {
        List<String> dcmRealized = (List<String>)doc.get("dcmRealized_docvalues_strings");
        if(dcmRealized != null) {
          dcmRealized.stream().forEach( v -> {
            oProviderIntent.dcmRealized.add(ProviderIntent.staticSetDcmRealized(siteRequest_, v));
          });
        }
      }
    }

    super.populateProvider(doc);
  }

  public void indexProviderIntent(JsonObject doc) {
    if(providerName != null) {
      doc.put("providerName_docvalues_string", providerName);
    }
    if(providerId != null) {
      doc.put("providerId_docvalues_string", providerId);
    }
    if(providerResource != null) {
      doc.put("providerResource_docvalues_string", providerResource);
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
    if(description != null) {
      doc.put("description_docvalues_string", description);
    }
    if(requested != null) {
      JsonArray l = new JsonArray();
      doc.put("requested_docvalues_strings", l);
      for(String o : requested) {
        l.add(ProviderIntent.staticSearchRequested(siteRequest_, o));
      }
    }
    if(locked != null) {
      doc.put("locked_docvalues_boolean", locked);
    }
    if(dcmDiscovered != null) {
      JsonArray l = new JsonArray();
      doc.put("dcmDiscovered_docvalues_strings", l);
      for(String o : dcmDiscovered) {
        l.add(ProviderIntent.staticSearchDcmDiscovered(siteRequest_, o));
      }
    }
    if(dcmRealized != null) {
      JsonArray l = new JsonArray();
      doc.put("dcmRealized_docvalues_strings", l);
      for(String o : dcmRealized) {
        l.add(ProviderIntent.staticSearchDcmRealized(siteRequest_, o));
      }
    }
    super.indexProvider(doc);

	}

  public static String varStoredProviderIntent(String entityVar) {
    switch(entityVar) {
      case "providerName":
        return "providerName_docvalues_string";
      case "providerId":
        return "providerId_docvalues_string";
      case "providerResource":
        return "providerResource_docvalues_string";
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
      case "description":
        return "description_docvalues_string";
      case "requested":
        return "requested_docvalues_strings";
      case "locked":
        return "locked_docvalues_boolean";
      case "dcmDiscovered":
        return "dcmDiscovered_docvalues_strings";
      case "dcmRealized":
        return "dcmRealized_docvalues_strings";
      default:
        return Provider.varStoredProvider(entityVar);
    }
  }

  public static String varIndexedProviderIntent(String entityVar) {
    switch(entityVar) {
      case "providerName":
        return "providerName_docvalues_string";
      case "providerId":
        return "providerId_docvalues_string";
      case "providerResource":
        return "providerResource_docvalues_string";
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
      case "description":
        return "description_docvalues_string";
      case "requested":
        return "requested_docvalues_strings";
      case "locked":
        return "locked_docvalues_boolean";
      case "dcmDiscovered":
        return "dcmDiscovered_docvalues_strings";
      case "dcmRealized":
        return "dcmRealized_docvalues_strings";
      default:
        return Provider.varIndexedProvider(entityVar);
    }
  }

  public static String searchVarProviderIntent(String searchVar) {
    switch(searchVar) {
      case "providerName_docvalues_string":
        return "providerName";
      case "providerId_docvalues_string":
        return "providerId";
      case "providerResource_docvalues_string":
        return "providerResource";
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
      case "description_docvalues_string":
        return "description";
      case "requested_docvalues_strings":
        return "requested";
      case "locked_docvalues_boolean":
        return "locked";
      case "dcmDiscovered_docvalues_strings":
        return "dcmDiscovered";
      case "dcmRealized_docvalues_strings":
        return "dcmRealized";
      default:
        return Provider.searchVarProvider(searchVar);
    }
  }

  public static String varSearchProviderIntent(String entityVar) {
    switch(entityVar) {
      default:
        return Provider.varSearchProvider(entityVar);
    }
  }

  public static String varSuggestedProviderIntent(String entityVar) {
    switch(entityVar) {
      default:
        return Provider.varSuggestedProvider(entityVar);
    }
  }

  /////////////
  // store //
  /////////////

  @Override public void storeForClass(SolrResponse.Doc doc) {
    storeProviderIntent(doc);
  }
  public void storeProviderIntent(SolrResponse.Doc doc) {
    ProviderIntent oProviderIntent = (ProviderIntent)this;
    SiteRequest siteRequest = oProviderIntent.getSiteRequest_();

    oProviderIntent.setProviderName(Optional.ofNullable(doc.get("providerName_docvalues_string")).map(v -> v.toString()).orElse(null));
    oProviderIntent.setProviderId(Optional.ofNullable(doc.get("providerId_docvalues_string")).map(v -> v.toString()).orElse(null));
    oProviderIntent.setProviderResource(Optional.ofNullable(doc.get("providerResource_docvalues_string")).map(v -> v.toString()).orElse(null));
    oProviderIntent.setCreatedByEmail(Optional.ofNullable(doc.get("createdByEmail_docvalues_string")).map(v -> v.toString()).orElse(null));
    oProviderIntent.setCreatedByUserId(Optional.ofNullable(doc.get("createdByUserId_docvalues_string")).map(v -> v.toString()).orElse(null));
    oProviderIntent.setCreatedByFullName(Optional.ofNullable(doc.get("createdByFullName_docvalues_string")).map(v -> v.toString()).orElse(null));
    oProviderIntent.setCreatedVia(Optional.ofNullable(doc.get("createdVia_docvalues_string")).map(v -> v.toString()).orElse(null));
    oProviderIntent.setIntentState(Optional.ofNullable(doc.get("intentState_docvalues_string")).map(v -> v.toString()).orElse(null));
    oProviderIntent.setRequestedState(Optional.ofNullable(doc.get("requestedState_docvalues_string")).map(v -> v.toString()).orElse(null));
    oProviderIntent.setRealizedState(Optional.ofNullable(doc.get("realizedState_docvalues_string")).map(v -> v.toString()).orElse(null));
    oProviderIntent.setDescription(Optional.ofNullable(doc.get("description_docvalues_string")).map(v -> v.toString()).orElse(null));
    Optional.ofNullable((List<?>)doc.get("requested_docvalues_strings")).orElse(Arrays.asList()).stream().filter(v -> v != null).forEach(v -> {
      oProviderIntent.addRequested(ProviderIntent.staticSetRequested(siteRequest, v.toString()));
    });
    oProviderIntent.setLocked(Optional.ofNullable(doc.get("locked_docvalues_boolean")).map(v -> v.toString()).orElse(null));
    Optional.ofNullable((List<?>)doc.get("dcmDiscovered_docvalues_strings")).orElse(Arrays.asList()).stream().filter(v -> v != null).forEach(v -> {
      oProviderIntent.addDcmDiscovered(ProviderIntent.staticSetDcmDiscovered(siteRequest, v.toString()));
    });
    Optional.ofNullable((List<?>)doc.get("dcmRealized_docvalues_strings")).orElse(Arrays.asList()).stream().filter(v -> v != null).forEach(v -> {
      oProviderIntent.addDcmRealized(ProviderIntent.staticSetDcmRealized(siteRequest, v.toString()));
    });

    super.storeProvider(doc);
  }

  //////////////////
  // apiRequest //
  //////////////////

  public void apiRequestProviderIntent() {
    ApiRequest apiRequest = Optional.ofNullable(siteRequest_).map(r -> r.getApiRequest_()).orElse(null);
    Object o = Optional.ofNullable(apiRequest).map(ApiRequest::getOriginal).orElse(null);
    if(o != null && o instanceof ProviderIntent) {
      ProviderIntent original = (ProviderIntent)o;
      if(!Objects.equals(providerName, original.getProviderName()))
        apiRequest.addVars("providerName");
      if(!Objects.equals(providerId, original.getProviderId()))
        apiRequest.addVars("providerId");
      if(!Objects.equals(providerResource, original.getProviderResource()))
        apiRequest.addVars("providerResource");
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
      if(!Objects.equals(description, original.getDescription()))
        apiRequest.addVars("description");
      if(!Objects.equals(requested, original.getRequested()))
        apiRequest.addVars("requested");
      if(!Objects.equals(locked, original.getLocked()))
        apiRequest.addVars("locked");
      if(!Objects.equals(dcmDiscovered, original.getDcmDiscovered()))
        apiRequest.addVars("dcmDiscovered");
      if(!Objects.equals(dcmRealized, original.getDcmRealized()))
        apiRequest.addVars("dcmRealized");
      super.apiRequestProvider();
    }
  }

  //////////////
  // toString //
  //////////////

  @Override public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(super.toString());
    sb.append(Optional.ofNullable(providerName).map(v -> "providerName: \"" + v + "\"\n" ).orElse(""));
    sb.append(Optional.ofNullable(providerId).map(v -> "providerId: \"" + v + "\"\n" ).orElse(""));
    sb.append(Optional.ofNullable(providerResource).map(v -> "providerResource: \"" + v + "\"\n" ).orElse(""));
    sb.append(Optional.ofNullable(createdByEmail).map(v -> "createdByEmail: \"" + v + "\"\n" ).orElse(""));
    sb.append(Optional.ofNullable(createdByUserId).map(v -> "createdByUserId: \"" + v + "\"\n" ).orElse(""));
    sb.append(Optional.ofNullable(createdByFullName).map(v -> "createdByFullName: \"" + v + "\"\n" ).orElse(""));
    sb.append(Optional.ofNullable(createdVia).map(v -> "createdVia: \"" + v + "\"\n" ).orElse(""));
    sb.append(Optional.ofNullable(intentState).map(v -> "intentState: \"" + v + "\"\n" ).orElse(""));
    sb.append(Optional.ofNullable(requestedState).map(v -> "requestedState: \"" + v + "\"\n" ).orElse(""));
    sb.append(Optional.ofNullable(realizedState).map(v -> "realizedState: \"" + v + "\"\n" ).orElse(""));
    sb.append(Optional.ofNullable(description).map(v -> "description: \"" + v + "\"\n" ).orElse(""));
    sb.append(Optional.ofNullable(requested).map(v -> "requested: " + v + "\n").orElse(""));
    sb.append(Optional.ofNullable(locked).map(v -> "locked: " + v + "\n").orElse(""));
    sb.append(Optional.ofNullable(dcmDiscovered).map(v -> "dcmDiscovered: " + v + "\n").orElse(""));
    sb.append(Optional.ofNullable(dcmRealized).map(v -> "dcmRealized: " + v + "\n").orElse(""));
    return sb.toString();
  }

  public static final String CLASS_SIMPLE_NAME = "ProviderIntent";
  public static final String CLASS_CANONICAL_NAME = "org.computate.dcm.model.eda.provider.intent.ProviderIntent";
  public static final String CLASS_AUTH_RESOURCE = "PROVIDER";
  public static final String CLASS_API_ADDRESS_ProviderIntent = "dcm-enUS-ProviderIntent";
  public static String getClassApiAddress() {
    return CLASS_API_ADDRESS_ProviderIntent;
  }
  public static final String VAR_providerName = "providerName";
  public static final String SET_providerName = "setProviderName";
  public static final String VAR_providerId = "providerId";
  public static final String SET_providerId = "setProviderId";
  public static final String VAR_providerResource = "providerResource";
  public static final String SET_providerResource = "setProviderResource";
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
  public static final String VAR_description = "description";
  public static final String SET_description = "setDescription";
  public static final String VAR_requested = "requested";
  public static final String SET_requested = "setRequested";
  public static final String VAR_locked = "locked";
  public static final String SET_locked = "setLocked";
  public static final String VAR_dcmDiscovered = "dcmDiscovered";
  public static final String SET_dcmDiscovered = "setDcmDiscovered";
  public static final String VAR_dcmRealized = "dcmRealized";
  public static final String SET_dcmRealized = "setDcmRealized";

  public static List<String> varsQForClass() {
    return ProviderIntent.varsQProviderIntent(new ArrayList<String>());
  }
  public static List<String> varsQProviderIntent(List<String> vars) {
    Provider.varsQProvider(vars);
    return vars;
  }

  public static List<String> varsFqForClass() {
    return ProviderIntent.varsFqProviderIntent(new ArrayList<String>());
  }
  public static List<String> varsFqProviderIntent(List<String> vars) {
    vars.add(VAR_providerName);
    vars.add(VAR_providerId);
    vars.add(VAR_providerResource);
    vars.add(VAR_description);
    vars.add(VAR_locked);
    Provider.varsFqProvider(vars);
    return vars;
  }

  public static List<String> varsRangeForClass() {
    return ProviderIntent.varsRangeProviderIntent(new ArrayList<String>());
  }
  public static List<String> varsRangeProviderIntent(List<String> vars) {
    Provider.varsRangeProvider(vars);
    return vars;
  }

  public static final String DISPLAY_NAME_providerName = "provider name";
  public static final String DISPLAY_NAME_providerId = "provider ID";
  public static final String DISPLAY_NAME_providerResource = "provider auth resource";
  public static final String DISPLAY_NAME_createdByEmail = "created by user email";
  public static final String DISPLAY_NAME_createdByUserId = "created by user ID";
  public static final String DISPLAY_NAME_createdByFullName = "created by user full name";
  public static final String DISPLAY_NAME_createdVia = "created via";
  public static final String DISPLAY_NAME_intentState = "intent state";
  public static final String DISPLAY_NAME_requestedState = "requested state";
  public static final String DISPLAY_NAME_realizedState = "realized state";
  public static final String DISPLAY_NAME_description = "description";
  public static final String DISPLAY_NAME_requested = "provider requested";
  public static final String DISPLAY_NAME_locked = "locked";
  public static final String DISPLAY_NAME_dcmDiscovered = "provider discovered";
  public static final String DISPLAY_NAME_dcmRealized = "provider realized";

  @Override
  public String idForClass() {
    return providerResource;
  }

  @Override
  public String titleForClass() {
    return objectTitle;
  }

  @Override
  public String nameForClass() {
    return providerName;
  }

  @Override
  public String classNameAdjectiveSingularForClass() {
    return ProviderIntent.NameAdjectiveSingular_enUS;
  }

  @Override
  public String descriptionForClass() {
    return description;
  }

  @Override
  public String enUSStringFormatUrlEditPageForClass() {
    return "%s/en-us/edit/intent/provider/%s";
  }

  public static String varJson(String var, Boolean patch) {
    return ProviderIntent.varJsonProviderIntent(var, patch);
  }
  public static String varJsonProviderIntent(String var, Boolean patch) {
    switch(var) {
    case VAR_providerName:
      return patch ? SET_providerName : VAR_providerName;
    case VAR_providerId:
      return patch ? SET_providerId : VAR_providerId;
    case VAR_providerResource:
      return patch ? SET_providerResource : VAR_providerResource;
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
    case VAR_description:
      return patch ? SET_description : VAR_description;
    case VAR_requested:
      return patch ? SET_requested : VAR_requested;
    case VAR_locked:
      return patch ? SET_locked : VAR_locked;
    case VAR_dcmDiscovered:
      return patch ? SET_dcmDiscovered : VAR_dcmDiscovered;
    case VAR_dcmRealized:
      return patch ? SET_dcmRealized : VAR_dcmRealized;
    default:
      return Provider.varJsonProvider(var, patch);
    }
  }

  public static String displayNameForClass(String var) {
    return ProviderIntent.displayNameProviderIntent(var);
  }
  public static String displayNameProviderIntent(String var) {
    switch(var) {
    case VAR_providerName:
      return DISPLAY_NAME_providerName;
    case VAR_providerId:
      return DISPLAY_NAME_providerId;
    case VAR_providerResource:
      return DISPLAY_NAME_providerResource;
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
    case VAR_description:
      return DISPLAY_NAME_description;
    case VAR_requested:
      return DISPLAY_NAME_requested;
    case VAR_locked:
      return DISPLAY_NAME_locked;
    case VAR_dcmDiscovered:
      return DISPLAY_NAME_dcmDiscovered;
    case VAR_dcmRealized:
      return DISPLAY_NAME_dcmRealized;
    default:
      return Provider.displayNameProvider(var);
    }
  }

  public static String descriptionProviderIntent(String var) {
    if(var == null)
      return null;
    switch(var) {
    case VAR_providerName:
      return "The name of this provider";
    case VAR_providerId:
      return "The ID of this provider. By default, this will be auto-generated based on the provider name, converting non-alphanumeric characters to hyphens, all lowercase. ";
    case VAR_providerResource:
      return "The unique authorization resource for the provider for multi-tenancy";
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
    case VAR_description:
      return "A description of this provider";
    case VAR_requested:
      return "The related provider requests for this provider intent. ";
    case VAR_locked:
      return "A provider intent gets locked after creating the first provider request. ";
    case VAR_dcmDiscovered:
      return "Each time the provider was discovered for this provider intent. ";
    case VAR_dcmRealized:
      return "Each time the provider was realized for this provider intent. ";
      default:
        return Provider.descriptionProvider(var);
    }
  }

  public static String classSimpleNameProviderIntent(String var) {
    switch(var) {
    case VAR_providerName:
      return "String";
    case VAR_providerId:
      return "String";
    case VAR_providerResource:
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
    case VAR_description:
      return "String";
    case VAR_requested:
      return "List";
    case VAR_locked:
      return "Boolean";
    case VAR_dcmDiscovered:
      return "List";
    case VAR_dcmRealized:
      return "List";
      default:
        return Provider.classSimpleNameProvider(var);
    }
  }

  public static Integer htmColumnProviderIntent(String var) {
    switch(var) {
    case VAR_providerName:
      return 1;
    case VAR_description:
      return 3;
      default:
        return Provider.htmColumnProvider(var);
    }
  }

  public static Integer htmRowProviderIntent(String var) {
    switch(var) {
    case VAR_providerName:
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
    case VAR_description:
      return 20;
    case VAR_requested:
      return 21;
    case VAR_locked:
      return 21;
    case VAR_dcmDiscovered:
      return 22;
    case VAR_dcmRealized:
      return 23;
      default:
        return Provider.htmRowProvider(var);
    }
  }

  public static Integer htmCellProviderIntent(String var) {
    switch(var) {
    case VAR_providerName:
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
    case VAR_description:
      return 4;
    case VAR_requested:
      return 0;
    case VAR_locked:
      return 0;
    case VAR_dcmDiscovered:
      return 0;
    case VAR_dcmRealized:
      return 0;
      default:
        return Provider.htmCellProvider(var);
    }
  }

  public static Integer lengthMinProviderIntent(String var) {
    switch(var) {
      default:
        return Provider.lengthMinProvider(var);
    }
  }

  public static Integer lengthMaxProviderIntent(String var) {
    switch(var) {
      default:
        return Provider.lengthMaxProvider(var);
    }
  }

  public static Integer maxProviderIntent(String var) {
    switch(var) {
      default:
        return Provider.maxProvider(var);
    }
  }

  public static Integer minProviderIntent(String var) {
    switch(var) {
      default:
        return Provider.minProvider(var);
    }
  }
}
