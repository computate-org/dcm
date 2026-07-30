package org.computate.dcm.model.eda.provider.requested;

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
import org.computate.dcm.model.eda.provider.intent.ProviderIntent;
import java.lang.Integer;
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
 * <li>You can add a class comment "{@inheritDoc}" if you wish to inherit the helpful inherited class comments from class ProviderRequestedGen into the class ProviderRequested. 
 * </li><li><p>
 *   You can add a class comment <kbd><b>Rows: 100</b></kbd> if you wish for the provider requested API to return more or less than 10 results by default. 
 *   In this case, the API will return 100 results from the API instead of 10 by default. 
 *   Each API has built in pagination of the search results to ensure a user can query all the data a page at a time without running the application out of memory. 
 * </p>
 * </li>
 * <h3>About the ProviderRequested class and it's generated class ProviderRequestedGen&lt;Provider&gt;: </h3>extends ProviderRequestedGen
 * <p>
 * This Java class extends a generated Java class ProviderRequestedGen built by the <a href="https://github.com/computate-org/computate">https://github.com/computate-org/computate</a> project. 
 * Whenever this Java class is modified or touched, the watch service installed as described in the README, indexes all the information about this Java class in a local Apache Solr Search Engine. 
 * If you are running the service, you can see the indexed data about this Java Class here: 
 * </p>
 * <p><a href="https://solr.apps-crc.testing/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.provider.requested.ProviderRequested">Find the class ProviderRequested in Solr. </a></p>
 * <p>
 * The extended class ending with "Gen" did not exist at first, but was automatically created by the same watch service based on the data retrieved from the local Apache Server search engine. 
 * The extended class contains many generated fields, getters, setters, initialization code, and helper methods to help build a website and API fast, reactive, and scalable. 
 * </p>
 * extends ProviderRequestedGen<Provider>
 * <p>This <code>class ProviderRequested extends ProviderRequestedGen&lt;Provider&gt;</code>, which means it extends a newly generated ProviderRequestedGen. 
 * The generated <code>class ProviderRequestedGen extends Provider</code> which means that ProviderRequested extends ProviderRequestedGen which extends Provider. 
 * This generated inheritance is a powerful feature that allows a lot of boiler plate code to be created for you automatically while still preserving inheritance through the power of Java Generic classes. 
 * </p>
 * <h2>
 *   Api: true
 * </h2>
 * <p>
 *   This class contains a comment <kbd><b>Api: true</b></kbd>, which means this class will have Java Vert.x API backend code generated for these objects. 
 * </p>
 * <h2>ApiTag.enUS: true</h2>
 * <p>This class contains a comment <kbd><b>ApiTag: provider requesteds</b></kbd>, which groups all of the OpenAPIs for ProviderRequested objects under the tag "provider requesteds". 
 * </p>
 * <h2>ApiUri.enUS: /en-us/api/requested/provider</h2>
 * <p>This class contains a comment <kbd><b>ApiUri: /en-us/api/requested/provider</b></kbd>, which defines the base API URI for ProviderRequested objects as "/en-us/api/requested/provider" in the OpenAPI spec. 
 * </p>
 * <h2>Color: null</h2>
 * <h2>Indexed: true</h2>
 * <p>This class contains a comment <kbd><b>Indexed: true</b></kbd>, which means this class will be indexed in the search engine. 
 * Every protected void method that begins with "_" that is marked to be searched with a comment like "Indexed: true", "Stored: true", or "DocValues: true" will be indexed in the search engine. 
 * </p>
 * <h2>{@inheritDoc}</h2>
 * <p>By adding a class comment "{@inheritDoc}", the ProviderRequested class will inherit the helpful inherited class comments from the super class ProviderRequestedGen. 
 * </p>
 * <h2>
 *   Rows: 10
 * </h2>
 * <p>This class contains a comment <kbd><b>Rows: 10</b></kbd>, which means the provider requested API will return a default of 10 results instead of 10 by default. 
 * Each API has built in pagination of the search results to ensure a user can query all the data a page at a time without running the application out of memory. 
 * </p>
 * <p>
 *   You can add a class comment <kbd><b>Rows: 100</b></kbd> if you wish for the provider requested API to return more or less than 10 results by default. 
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
 * This creates a new Java class org.computate.dcm.model.eda.provider.requested.ProviderRequestedPage. 
 * </p>
 * <h2>SuperPage.enUS: PageLayout</h2>
 * <p>This class contains a comment <kbd><b>SuperPage.enUS: PageLayout</b></kbd>, which identifies the Java super class of the page code by it's class simple name "PageLayout". 
 * This means that the newly created class org.computate.dcm.model.eda.provider.requested.ProviderRequestedPage extends org.computate.dcm.page.PageLayout. 
 * </p>
 * <h2>Promise: true</h2>
 * <p>
 *   This class contains a comment <kbd><b>Promise: true</b></kbd>
 *   Sometimes a Java class must be initialized asynchronously when it involves calling a blocking API. 
 *   This means that the ProviderRequested Java class has promiseDeep methods which must be initialized asynchronously as a Vert.x Promise  instead of initDeep methods which are a simple non-asynchronous method. 
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
 * <h2>AName.enUS: a provider requested</h2>
 * <p>This class contains a comment <kbd><b>AName.enUS: a provider requested</b></kbd>, which identifies the language context to describe a ProviderRequested as "a provider requested". 
 * </p>
 * <p>
 * Delete the class ProviderRequested in Solr: 
 * <pre>
 * curl -k 'https://solr.apps-crc.testing/solr/computate/update?commitWithin=1000&amp;overwrite=true&amp;wt=json' -X POST -H 'Content-type: text/xml' -u "admin:$(oc -n solr get secret/solr-solrcloud-security-bootstrap -o jsonpath={.data.admin} | base64 -d)" --data-raw '&lt;delete&gt;&lt;query&gt;classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.provider.requested.ProviderRequested&lt;/query&gt;&lt;/delete&gt;'
 * </pre>
 * </p>
 * <p>
 * Delete  the package org.computate.dcm.model.eda.provider.requested in Solr: 
 * <pre>
 * curl -k 'https://solr.apps-crc.testing/solr/computate/update?commitWithin=1000&amp;overwrite=true&amp;wt=json' -X POST -H 'Content-type: text/xml' -u "admin:$(oc -n solr get secret/solr-solrcloud-security-bootstrap -o jsonpath={.data.admin} | base64 -d)" --data-raw '&lt;delete&gt;&lt;query&gt;classeNomEnsemble_enUS_indexed_string:org.computate.dcm.model.eda.provider.requested&lt;/query&gt;&lt;/delete&gt;'
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
public abstract class ProviderRequestedGen<DEV> extends Provider {
  protected static final Logger LOG = LoggerFactory.getLogger(ProviderRequested.class);

  public static final String Description_enUS = "A provider for requesting other DCM models. ";
  public static final String AName_enUS = "a provider requested";
  public static final String This_enUS = "this ";
  public static final String ThisName_enUS = "this provider requested";
  public static final String A_enUS = "a ";
  public static final String TheName_enUS = "the provider requested";
  public static final String SingularName_enUS = "provider requested";
  public static final String PluralName_enUS = "provider requesteds";
  public static final String NameActual_enUS = "current provider requested";
  public static final String AllName_enUS = "all provider requesteds";
  public static final String SearchAllNameBy_enUS = "search provider requesteds by ";
  public static final String SearchAllName_enUS = "search provider requesteds";
  public static final String Title_enUS = "provider requesteds";
  public static final String ThePluralName_enUS = "the provider requesteds";
  public static final String NoNameFound_enUS = "no provider requested found";
  public static final String ApiUri_enUS = "/en-us/api/requested/provider";
  public static final String ApiUriSearchPage_enUS = "/en-us/search/requested/provider";
  public static final String ApiUriEditPage_enUS = "/en-us/edit/requested/provider/{providerResource}";
  public static final String OfName_enUS = "of provider requested";
  public static final String ANameAdjective_enUS = "a provider requested";
  public static final String NameAdjectiveSingular_enUS = "provider requested";
  public static final String NameAdjectivePlural_enUS = "provider requesteds";
  public static final String Search_enUS_OpenApiUri = "/en-us/api/requested/provider";
  public static final String Search_enUS_StringFormatUri = "/en-us/api/requested/provider";
  public static final String Search_enUS_StringFormatUrl = "%s/en-us/api/requested/provider";
  public static final String GET_enUS_OpenApiUri = "/en-us/api/requested/provider/{requestedId}";
  public static final String GET_enUS_StringFormatUri = "/en-us/api/requested/provider/%s";
  public static final String GET_enUS_StringFormatUrl = "%s/en-us/api/requested/provider/%s";
  public static final String PATCH_enUS_OpenApiUri = "/en-us/api/requested/provider";
  public static final String PATCH_enUS_StringFormatUri = "/en-us/api/requested/provider";
  public static final String PATCH_enUS_StringFormatUrl = "%s/en-us/api/requested/provider";
  public static final String POST_enUS_OpenApiUri = "/en-us/api/requested/provider";
  public static final String POST_enUS_StringFormatUri = "/en-us/api/requested/provider";
  public static final String POST_enUS_StringFormatUrl = "%s/en-us/api/requested/provider";
  public static final String DELETE_enUS_OpenApiUri = "/en-us/api/requested/provider/{requestedId}";
  public static final String DELETE_enUS_StringFormatUri = "/en-us/api/requested/provider/%s";
  public static final String DELETE_enUS_StringFormatUrl = "%s/en-us/api/requested/provider/%s";
  public static final String PUTImport_enUS_OpenApiUri = "/en-us/api/requested/provider-import";
  public static final String PUTImport_enUS_StringFormatUri = "/en-us/api/requested/provider-import";
  public static final String PUTImport_enUS_StringFormatUrl = "%s/en-us/api/requested/provider-import";
  public static final String SearchPage_enUS_OpenApiUri = "/en-us/search/requested/provider";
  public static final String SearchPage_enUS_StringFormatUri = "/en-us/search/requested/provider";
  public static final String SearchPage_enUS_StringFormatUrl = "%s/en-us/search/requested/provider";
  public static final String EditPage_enUS_OpenApiUri = "/en-us/edit/requested/provider/{providerResource}";
  public static final String EditPage_enUS_StringFormatUri = "/en-us/edit/requested/provider/%s";
  public static final String EditPage_enUS_StringFormatUrl = "%s/en-us/edit/requested/provider/%s";
  public static final String DELETEFilter_enUS_OpenApiUri = "/en-us/api/requested/provider";
  public static final String DELETEFilter_enUS_StringFormatUri = "/en-us/api/requested/provider";
  public static final String DELETEFilter_enUS_StringFormatUrl = "%s/en-us/api/requested/provider";

  public static final String Icon = "<i class=\" fa-person-dolly\"></i>";

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
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.provider.requested.ProviderRequested&fq=entiteVar_enUS_indexed_string:providerId">Find the entity providerId in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _providerId(Wrap<String> w);

  public String getProviderId() {
    return providerId;
  }
  public void setProviderId(String o) {
    this.providerId = ProviderRequested.staticSetProviderId(siteRequest_, o);
  }
  public static String staticSetProviderId(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected ProviderRequested providerIdInit() {
    Wrap<String> providerIdWrap = new Wrap<String>().var("providerId");
    if(providerId == null) {
      _providerId(providerIdWrap);
      Optional.ofNullable(providerIdWrap.getO()).ifPresent(o -> {
        setProviderId(o);
      });
    }
    return (ProviderRequested)this;
  }

  public static String staticSearchProviderId(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrProviderId(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqProviderId(SiteRequest siteRequest_, String o) {
    return ProviderRequested.staticSearchProviderId(siteRequest_, ProviderRequested.staticSetProviderId(siteRequest_, o)).toString();
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
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.provider.requested.ProviderRequested&fq=entiteVar_enUS_indexed_string:providerResource">Find the entity providerResource in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _providerResource(Wrap<String> w);

  public String getProviderResource() {
    return providerResource;
  }
  public void setProviderResource(String o) {
    this.providerResource = ProviderRequested.staticSetProviderResource(siteRequest_, o);
  }
  public static String staticSetProviderResource(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected ProviderRequested providerResourceInit() {
    Wrap<String> providerResourceWrap = new Wrap<String>().var("providerResource");
    if(providerResource == null) {
      _providerResource(providerResourceWrap);
      Optional.ofNullable(providerResourceWrap.getO()).ifPresent(o -> {
        setProviderResource(o);
      });
    }
    return (ProviderRequested)this;
  }

  public static String staticSearchProviderResource(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrProviderResource(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqProviderResource(SiteRequest siteRequest_, String o) {
    return ProviderRequested.staticSearchProviderResource(siteRequest_, ProviderRequested.staticSetProviderResource(siteRequest_, o)).toString();
  }

  public String sqlProviderResource() {
    return providerResource;
  }

  public static String staticJsonProviderResource(String providerResource) {
    return providerResource;
  }

	/////////////////////
  // requestedNumber //
	/////////////////////


  /**
   *  The entity requestedNumber
   *	 is defined as null before being initialized. 
   */
  @JsonProperty
  @JsonSerialize(using = ToStringSerializer.class)
  @JsonInclude(Include.NON_NULL)
  protected Integer requestedNumber;

  /**
   * <br> The entity requestedNumber
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.provider.requested.ProviderRequested&fq=entiteVar_enUS_indexed_string:requestedNumber">Find the entity requestedNumber in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _requestedNumber(Wrap<Integer> w);

  public Integer getRequestedNumber() {
    return requestedNumber;
  }

  public void setRequestedNumber(Integer requestedNumber) {
    this.requestedNumber = requestedNumber;
  }
  @JsonIgnore
  public void setRequestedNumber(String o) {
    this.requestedNumber = ProviderRequested.staticSetRequestedNumber(siteRequest_, o);
  }
  public static Integer staticSetRequestedNumber(SiteRequest siteRequest_, String o) {
    if(NumberUtils.isParsable(o))
      return Integer.parseInt(o);
    return null;
  }
  protected ProviderRequested requestedNumberInit() {
    Wrap<Integer> requestedNumberWrap = new Wrap<Integer>().var("requestedNumber");
    if(requestedNumber == null) {
      _requestedNumber(requestedNumberWrap);
      Optional.ofNullable(requestedNumberWrap.getO()).ifPresent(o -> {
        setRequestedNumber(o);
      });
    }
    return (ProviderRequested)this;
  }

  public static Integer staticSearchRequestedNumber(SiteRequest siteRequest_, Integer o) {
    return o;
  }

  public static String staticSearchStrRequestedNumber(SiteRequest siteRequest_, Integer o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqRequestedNumber(SiteRequest siteRequest_, String o) {
    return ProviderRequested.staticSearchRequestedNumber(siteRequest_, ProviderRequested.staticSetRequestedNumber(siteRequest_, o)).toString();
  }

  public Integer sqlRequestedNumber() {
    return requestedNumber;
  }

  public static String staticJsonRequestedNumber(Integer requestedNumber) {
    return Optional.ofNullable(requestedNumber).map(v -> v.toString()).orElse(null);
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
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.provider.requested.ProviderRequested&fq=entiteVar_enUS_indexed_string:requestedId">Find the entity requestedId in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _requestedId(Wrap<String> w);

  public String getRequestedId() {
    return requestedId;
  }
  public void setRequestedId(String o) {
    this.requestedId = ProviderRequested.staticSetRequestedId(siteRequest_, o);
  }
  public static String staticSetRequestedId(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected ProviderRequested requestedIdInit() {
    Wrap<String> requestedIdWrap = new Wrap<String>().var("requestedId");
    if(requestedId == null) {
      _requestedId(requestedIdWrap);
      Optional.ofNullable(requestedIdWrap.getO()).ifPresent(o -> {
        setRequestedId(o);
      });
    }
    return (ProviderRequested)this;
  }

  public static String staticSearchRequestedId(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrRequestedId(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqRequestedId(SiteRequest siteRequest_, String o) {
    return ProviderRequested.staticSearchRequestedId(siteRequest_, ProviderRequested.staticSetRequestedId(siteRequest_, o)).toString();
  }

  public String sqlRequestedId() {
    return requestedId;
  }

  public static String staticJsonRequestedId(String requestedId) {
    return requestedId;
  }

	///////////////////
  // requestedName //
	///////////////////


  /**
   *  The entity requestedName
   *	 is defined as null before being initialized. 
   */
  @JsonProperty
  @JsonInclude(Include.NON_NULL)
  protected String requestedName;

  /**
   * <br> The entity requestedName
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.provider.requested.ProviderRequested&fq=entiteVar_enUS_indexed_string:requestedName">Find the entity requestedName in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _requestedName(Wrap<String> w);

  public String getRequestedName() {
    return requestedName;
  }
  public void setRequestedName(String o) {
    this.requestedName = ProviderRequested.staticSetRequestedName(siteRequest_, o);
  }
  public static String staticSetRequestedName(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected ProviderRequested requestedNameInit() {
    Wrap<String> requestedNameWrap = new Wrap<String>().var("requestedName");
    if(requestedName == null) {
      _requestedName(requestedNameWrap);
      Optional.ofNullable(requestedNameWrap.getO()).ifPresent(o -> {
        setRequestedName(o);
      });
    }
    return (ProviderRequested)this;
  }

  public static String staticSearchRequestedName(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrRequestedName(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqRequestedName(SiteRequest siteRequest_, String o) {
    return ProviderRequested.staticSearchRequestedName(siteRequest_, ProviderRequested.staticSetRequestedName(siteRequest_, o)).toString();
  }

  public String sqlRequestedName() {
    return requestedName;
  }

  public static String staticJsonRequestedName(String requestedName) {
    return requestedName;
  }

	//////////////////////
  // requestApprovals //
	//////////////////////


  /**
   *  The entity requestApprovals
   *	 It is constructed before being initialized with the constructor by default. 
   */
  @JsonProperty
  @JsonFormat(shape = JsonFormat.Shape.ARRAY)
  @JsonInclude(Include.NON_NULL)
  protected List<String> requestApprovals = new ArrayList<String>();

  /**
   * <br> The entity requestApprovals
   *  It is constructed before being initialized with the constructor by default. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.provider.requested.ProviderRequested&fq=entiteVar_enUS_indexed_string:requestApprovals">Find the entity requestApprovals in Solr</a>
   * <br>
   * @param w is the entity already constructed. 
   **/
  protected abstract void _requestApprovals(List<String> w);

  public List<String> getRequestApprovals() {
    return requestApprovals;
  }

  public void setRequestApprovals(List<String> requestApprovals) {
    this.requestApprovals = requestApprovals;
  }
  @JsonIgnore
  public void setRequestApprovals(String o) {
    String l = ProviderRequested.staticSetRequestApprovals(siteRequest_, o);
    if(l != null)
      addRequestApprovals(l);
  }
  public static String staticSetRequestApprovals(SiteRequest siteRequest_, String o) {
    return o;
  }
  public ProviderRequested addRequestApprovals(String...objects) {
    for(String o : objects) {
      addRequestApprovals(o);
    }
    return (ProviderRequested)this;
  }
  public ProviderRequested addRequestApprovals(String o) {
    if(o != null)
      this.requestApprovals.add(o);
    return (ProviderRequested)this;
  }
  @JsonIgnore
  public void setRequestApprovals(JsonArray objects) {
    requestApprovals.clear();
    if(objects == null)
      return;
    for(int i = 0; i < objects.size(); i++) {
      String o = objects.getString(i);
      addRequestApprovals(o);
    }
  }
  protected ProviderRequested requestApprovalsInit() {
    _requestApprovals(requestApprovals);
    return (ProviderRequested)this;
  }

  public static String staticSearchRequestApprovals(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrRequestApprovals(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqRequestApprovals(SiteRequest siteRequest_, String o) {
    return ProviderRequested.staticSearchRequestApprovals(siteRequest_, ProviderRequested.staticSetRequestApprovals(siteRequest_, o)).toString();
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
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.provider.requested.ProviderRequested&fq=entiteVar_enUS_indexed_string:createdByEmail">Find the entity createdByEmail in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _createdByEmail(Wrap<String> w);

  public String getCreatedByEmail() {
    return createdByEmail;
  }
  public void setCreatedByEmail(String o) {
    this.createdByEmail = ProviderRequested.staticSetCreatedByEmail(siteRequest_, o);
  }
  public static String staticSetCreatedByEmail(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected ProviderRequested createdByEmailInit() {
    Wrap<String> createdByEmailWrap = new Wrap<String>().var("createdByEmail");
    if(createdByEmail == null) {
      _createdByEmail(createdByEmailWrap);
      Optional.ofNullable(createdByEmailWrap.getO()).ifPresent(o -> {
        setCreatedByEmail(o);
      });
    }
    return (ProviderRequested)this;
  }

  public static String staticSearchCreatedByEmail(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrCreatedByEmail(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqCreatedByEmail(SiteRequest siteRequest_, String o) {
    return ProviderRequested.staticSearchCreatedByEmail(siteRequest_, ProviderRequested.staticSetCreatedByEmail(siteRequest_, o)).toString();
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
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.provider.requested.ProviderRequested&fq=entiteVar_enUS_indexed_string:createdByUserId">Find the entity createdByUserId in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _createdByUserId(Wrap<String> w);

  public String getCreatedByUserId() {
    return createdByUserId;
  }
  public void setCreatedByUserId(String o) {
    this.createdByUserId = ProviderRequested.staticSetCreatedByUserId(siteRequest_, o);
  }
  public static String staticSetCreatedByUserId(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected ProviderRequested createdByUserIdInit() {
    Wrap<String> createdByUserIdWrap = new Wrap<String>().var("createdByUserId");
    if(createdByUserId == null) {
      _createdByUserId(createdByUserIdWrap);
      Optional.ofNullable(createdByUserIdWrap.getO()).ifPresent(o -> {
        setCreatedByUserId(o);
      });
    }
    return (ProviderRequested)this;
  }

  public static String staticSearchCreatedByUserId(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrCreatedByUserId(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqCreatedByUserId(SiteRequest siteRequest_, String o) {
    return ProviderRequested.staticSearchCreatedByUserId(siteRequest_, ProviderRequested.staticSetCreatedByUserId(siteRequest_, o)).toString();
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
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.provider.requested.ProviderRequested&fq=entiteVar_enUS_indexed_string:createdByFullName">Find the entity createdByFullName in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _createdByFullName(Wrap<String> w);

  public String getCreatedByFullName() {
    return createdByFullName;
  }
  public void setCreatedByFullName(String o) {
    this.createdByFullName = ProviderRequested.staticSetCreatedByFullName(siteRequest_, o);
  }
  public static String staticSetCreatedByFullName(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected ProviderRequested createdByFullNameInit() {
    Wrap<String> createdByFullNameWrap = new Wrap<String>().var("createdByFullName");
    if(createdByFullName == null) {
      _createdByFullName(createdByFullNameWrap);
      Optional.ofNullable(createdByFullNameWrap.getO()).ifPresent(o -> {
        setCreatedByFullName(o);
      });
    }
    return (ProviderRequested)this;
  }

  public static String staticSearchCreatedByFullName(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrCreatedByFullName(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqCreatedByFullName(SiteRequest siteRequest_, String o) {
    return ProviderRequested.staticSearchCreatedByFullName(siteRequest_, ProviderRequested.staticSetCreatedByFullName(siteRequest_, o)).toString();
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
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.provider.requested.ProviderRequested&fq=entiteVar_enUS_indexed_string:createdVia">Find the entity createdVia in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _createdVia(Wrap<String> w);

  public String getCreatedVia() {
    return createdVia;
  }
  public void setCreatedVia(String o) {
    this.createdVia = ProviderRequested.staticSetCreatedVia(siteRequest_, o);
  }
  public static String staticSetCreatedVia(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected ProviderRequested createdViaInit() {
    Wrap<String> createdViaWrap = new Wrap<String>().var("createdVia");
    if(createdVia == null) {
      _createdVia(createdViaWrap);
      Optional.ofNullable(createdViaWrap.getO()).ifPresent(o -> {
        setCreatedVia(o);
      });
    }
    return (ProviderRequested)this;
  }

  public static String staticSearchCreatedVia(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrCreatedVia(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqCreatedVia(SiteRequest siteRequest_, String o) {
    return ProviderRequested.staticSearchCreatedVia(siteRequest_, ProviderRequested.staticSetCreatedVia(siteRequest_, o)).toString();
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
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.provider.requested.ProviderRequested&fq=entiteVar_enUS_indexed_string:intentState">Find the entity intentState in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _intentState(Wrap<String> w);

  public String getIntentState() {
    return intentState;
  }
  public void setIntentState(String o) {
    this.intentState = ProviderRequested.staticSetIntentState(siteRequest_, o);
  }
  public static String staticSetIntentState(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected ProviderRequested intentStateInit() {
    Wrap<String> intentStateWrap = new Wrap<String>().var("intentState");
    if(intentState == null) {
      _intentState(intentStateWrap);
      Optional.ofNullable(intentStateWrap.getO()).ifPresent(o -> {
        setIntentState(o);
      });
    }
    return (ProviderRequested)this;
  }

  public static String staticSearchIntentState(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrIntentState(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqIntentState(SiteRequest siteRequest_, String o) {
    return ProviderRequested.staticSearchIntentState(siteRequest_, ProviderRequested.staticSetIntentState(siteRequest_, o)).toString();
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
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.provider.requested.ProviderRequested&fq=entiteVar_enUS_indexed_string:requestedState">Find the entity requestedState in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _requestedState(Wrap<String> w);

  public String getRequestedState() {
    return requestedState;
  }
  public void setRequestedState(String o) {
    this.requestedState = ProviderRequested.staticSetRequestedState(siteRequest_, o);
  }
  public static String staticSetRequestedState(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected ProviderRequested requestedStateInit() {
    Wrap<String> requestedStateWrap = new Wrap<String>().var("requestedState");
    if(requestedState == null) {
      _requestedState(requestedStateWrap);
      Optional.ofNullable(requestedStateWrap.getO()).ifPresent(o -> {
        setRequestedState(o);
      });
    }
    return (ProviderRequested)this;
  }

  public static String staticSearchRequestedState(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrRequestedState(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqRequestedState(SiteRequest siteRequest_, String o) {
    return ProviderRequested.staticSearchRequestedState(siteRequest_, ProviderRequested.staticSetRequestedState(siteRequest_, o)).toString();
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
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.provider.requested.ProviderRequested&fq=entiteVar_enUS_indexed_string:realizedState">Find the entity realizedState in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _realizedState(Wrap<String> w);

  public String getRealizedState() {
    return realizedState;
  }
  public void setRealizedState(String o) {
    this.realizedState = ProviderRequested.staticSetRealizedState(siteRequest_, o);
  }
  public static String staticSetRealizedState(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected ProviderRequested realizedStateInit() {
    Wrap<String> realizedStateWrap = new Wrap<String>().var("realizedState");
    if(realizedState == null) {
      _realizedState(realizedStateWrap);
      Optional.ofNullable(realizedStateWrap.getO()).ifPresent(o -> {
        setRealizedState(o);
      });
    }
    return (ProviderRequested)this;
  }

  public static String staticSearchRealizedState(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrRealizedState(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqRealizedState(SiteRequest siteRequest_, String o) {
    return ProviderRequested.staticSearchRealizedState(siteRequest_, ProviderRequested.staticSetRealizedState(siteRequest_, o)).toString();
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
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.provider.requested.ProviderRequested&fq=entiteVar_enUS_indexed_string:description">Find the entity description in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _description(Wrap<String> w);

  public String getDescription() {
    return description;
  }
  public void setDescription(String o) {
    this.description = ProviderRequested.staticSetDescription(siteRequest_, o);
  }
  public static String staticSetDescription(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected ProviderRequested descriptionInit() {
    Wrap<String> descriptionWrap = new Wrap<String>().var("description");
    if(description == null) {
      _description(descriptionWrap);
      Optional.ofNullable(descriptionWrap.getO()).ifPresent(o -> {
        setDescription(o);
      });
    }
    return (ProviderRequested)this;
  }

  public static String staticSearchDescription(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrDescription(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqDescription(SiteRequest siteRequest_, String o) {
    return ProviderRequested.staticSearchDescription(siteRequest_, ProviderRequested.staticSetDescription(siteRequest_, o)).toString();
  }

  public String sqlDescription() {
    return description;
  }

  public static String staticJsonDescription(String description) {
    return description;
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
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.provider.requested.ProviderRequested&fq=entiteVar_enUS_indexed_string:locked">Find the entity locked in Solr</a>
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
    this.locked = ProviderRequested.staticSetLocked(siteRequest_, o);
  }
  public static Boolean staticSetLocked(SiteRequest siteRequest_, String o) {
    return Boolean.parseBoolean(o);
  }
  protected ProviderRequested lockedInit() {
    Wrap<Boolean> lockedWrap = new Wrap<Boolean>().var("locked");
    if(locked == null) {
      _locked(lockedWrap);
      Optional.ofNullable(lockedWrap.getO()).ifPresent(o -> {
        setLocked(o);
      });
    }
    return (ProviderRequested)this;
  }

  public static Boolean staticSearchLocked(SiteRequest siteRequest_, Boolean o) {
    return o;
  }

  public static String staticSearchStrLocked(SiteRequest siteRequest_, Boolean o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqLocked(SiteRequest siteRequest_, String o) {
    return ProviderRequested.staticSearchLocked(siteRequest_, ProviderRequested.staticSetLocked(siteRequest_, o)).toString();
  }

  public Boolean sqlLocked() {
    return locked;
  }

  public static Boolean staticJsonLocked(Boolean locked) {
    return locked;
  }

	////////////////////////
  // providerDiscovered //
	////////////////////////


  /**
   *  The entity providerDiscovered
   *	 It is constructed before being initialized with the constructor by default. 
   */
  @JsonProperty
  @JsonFormat(shape = JsonFormat.Shape.ARRAY)
  @JsonInclude(Include.NON_NULL)
  protected List<String> providerDiscovered = new ArrayList<String>();

  /**
   * <br> The entity providerDiscovered
   *  It is constructed before being initialized with the constructor by default. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.provider.requested.ProviderRequested&fq=entiteVar_enUS_indexed_string:providerDiscovered">Find the entity providerDiscovered in Solr</a>
   * <br>
   * @param l is the entity already constructed. 
   **/
  protected abstract void _providerDiscovered(List<String> l);

  public List<String> getProviderDiscovered() {
    return providerDiscovered;
  }

  public void setProviderDiscovered(List<String> providerDiscovered) {
    this.providerDiscovered = providerDiscovered;
  }
  @JsonIgnore
  public void setProviderDiscovered(String o) {
    String l = ProviderRequested.staticSetProviderDiscovered(siteRequest_, o);
    if(l != null)
      addProviderDiscovered(l);
  }
  public static String staticSetProviderDiscovered(SiteRequest siteRequest_, String o) {
    return o;
  }
  public ProviderRequested addProviderDiscovered(String...objects) {
    for(String o : objects) {
      addProviderDiscovered(o);
    }
    return (ProviderRequested)this;
  }
  public ProviderRequested addProviderDiscovered(String o) {
    if(o != null)
      this.providerDiscovered.add(o);
    return (ProviderRequested)this;
  }
  @JsonIgnore
  public void setProviderDiscovered(JsonArray objects) {
    providerDiscovered.clear();
    if(objects == null)
      return;
    for(int i = 0; i < objects.size(); i++) {
      String o = objects.getString(i);
      addProviderDiscovered(o);
    }
  }
  protected ProviderRequested providerDiscoveredInit() {
    _providerDiscovered(providerDiscovered);
    return (ProviderRequested)this;
  }

  public static String staticSearchProviderDiscovered(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrProviderDiscovered(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqProviderDiscovered(SiteRequest siteRequest_, String o) {
    return ProviderRequested.staticSearchProviderDiscovered(siteRequest_, ProviderRequested.staticSetProviderDiscovered(siteRequest_, o)).toString();
  }

	//////////////////////
  // providerRealized //
	//////////////////////


  /**
   *  The entity providerRealized
   *	 It is constructed before being initialized with the constructor by default. 
   */
  @JsonProperty
  @JsonFormat(shape = JsonFormat.Shape.ARRAY)
  @JsonInclude(Include.NON_NULL)
  protected List<String> providerRealized = new ArrayList<String>();

  /**
   * <br> The entity providerRealized
   *  It is constructed before being initialized with the constructor by default. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.provider.requested.ProviderRequested&fq=entiteVar_enUS_indexed_string:providerRealized">Find the entity providerRealized in Solr</a>
   * <br>
   * @param l is the entity already constructed. 
   **/
  protected abstract void _providerRealized(List<String> l);

  public List<String> getProviderRealized() {
    return providerRealized;
  }

  public void setProviderRealized(List<String> providerRealized) {
    this.providerRealized = providerRealized;
  }
  @JsonIgnore
  public void setProviderRealized(String o) {
    String l = ProviderRequested.staticSetProviderRealized(siteRequest_, o);
    if(l != null)
      addProviderRealized(l);
  }
  public static String staticSetProviderRealized(SiteRequest siteRequest_, String o) {
    return o;
  }
  public ProviderRequested addProviderRealized(String...objects) {
    for(String o : objects) {
      addProviderRealized(o);
    }
    return (ProviderRequested)this;
  }
  public ProviderRequested addProviderRealized(String o) {
    if(o != null)
      this.providerRealized.add(o);
    return (ProviderRequested)this;
  }
  @JsonIgnore
  public void setProviderRealized(JsonArray objects) {
    providerRealized.clear();
    if(objects == null)
      return;
    for(int i = 0; i < objects.size(); i++) {
      String o = objects.getString(i);
      addProviderRealized(o);
    }
  }
  protected ProviderRequested providerRealizedInit() {
    _providerRealized(providerRealized);
    return (ProviderRequested)this;
  }

  public static String staticSearchProviderRealized(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrProviderRealized(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqProviderRealized(SiteRequest siteRequest_, String o) {
    return ProviderRequested.staticSearchProviderRealized(siteRequest_, ProviderRequested.staticSetProviderRealized(siteRequest_, o)).toString();
  }

  //////////////
  // initDeep //
  //////////////

  public Future<ProviderRequestedGen<DEV>> promiseDeepProviderRequested(SiteRequest siteRequest_) {
    if(this.siteRequest_ == null)
      setSiteRequest_(siteRequest_);
    return promiseDeepProviderRequested();
  }

  public Future<ProviderRequestedGen<DEV>> promiseDeepProviderRequested() {
    Promise<ProviderRequestedGen<DEV>> promise = Promise.promise();
    Promise<Void> promise2 = Promise.promise();
    promiseProviderRequested(promise2);
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

  public Future<Void> promiseProviderRequested(Promise<Void> promise) {
    Future.future(a -> a.complete()).compose(a -> {
      Promise<Void> promise2 = Promise.promise();
      try {
        providerIdInit();
        providerResourceInit();
        requestedNumberInit();
        requestedIdInit();
        requestedNameInit();
        requestApprovalsInit();
        createdByEmailInit();
        createdByUserIdInit();
        createdByFullNameInit();
        createdViaInit();
        intentStateInit();
        requestedStateInit();
        realizedStateInit();
        descriptionInit();
        lockedInit();
        providerDiscoveredInit();
        providerRealizedInit();
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

  @Override public Future<? extends ProviderRequestedGen<DEV>> promiseDeepForClass(SiteRequest siteRequest_) {
    return promiseDeepProviderRequested(siteRequest_);
  }

  /////////////////
  // siteRequest //
  /////////////////

  public void siteRequestProviderRequested(SiteRequest siteRequest_) {
      super.siteRequestProvider(siteRequest_);
  }

  public void siteRequestForClass(SiteRequest siteRequest_) {
    siteRequestProviderRequested(siteRequest_);
  }

  /////////////
  // obtain //
  /////////////

  @Override public Object obtainForClass(String var) {
    String[] vars = StringUtils.split(var, ".");
    Object o = null;
    for(String v : vars) {
      if(o == null)
        o = obtainProviderRequested(v);
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
  public Object obtainProviderRequested(String var) {
    ProviderRequested oProviderRequested = (ProviderRequested)this;
    switch(var) {
      case "providerId":
        return oProviderRequested.providerId;
      case "providerResource":
        return oProviderRequested.providerResource;
      case "requestedNumber":
        return oProviderRequested.requestedNumber;
      case "requestedId":
        return oProviderRequested.requestedId;
      case "requestedName":
        return oProviderRequested.requestedName;
      case "requestApprovals":
        return oProviderRequested.requestApprovals;
      case "createdByEmail":
        return oProviderRequested.createdByEmail;
      case "createdByUserId":
        return oProviderRequested.createdByUserId;
      case "createdByFullName":
        return oProviderRequested.createdByFullName;
      case "createdVia":
        return oProviderRequested.createdVia;
      case "intentState":
        return oProviderRequested.intentState;
      case "requestedState":
        return oProviderRequested.requestedState;
      case "realizedState":
        return oProviderRequested.realizedState;
      case "description":
        return oProviderRequested.description;
      case "locked":
        return oProviderRequested.locked;
      case "providerDiscovered":
        return oProviderRequested.providerDiscovered;
      case "providerRealized":
        return oProviderRequested.providerRealized;
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
        o = relateProviderRequested(v, val);
      else if(o instanceof BaseModel) {
        BaseModel baseModel = (BaseModel)o;
        o = baseModel.relateForClass(v, val);
      }
    }
    return o != null;
  }
  public Object relateProviderRequested(String var, Object val) {
    ProviderRequested oProviderRequested = (ProviderRequested)this;
    switch(var) {
      case "providerResource":
        if(oProviderRequested.getProviderResource() == null)
          oProviderRequested.setProviderResource(Optional.ofNullable(val).map(v -> v.toString()).orElse(null));
        if(!saves.contains("providerResource"))
          saves.add("providerResource");
        return val;
      default:
        return super.relateProvider(var, val);
    }
  }

  ///////////////
  // staticSet //
  ///////////////

  public static Object staticSetForClass(String entityVar, SiteRequest siteRequest_, String v, ProviderRequested o) {
    return staticSetProviderRequested(entityVar,  siteRequest_, v, o);
  }
  public static Object staticSetProviderRequested(String entityVar, SiteRequest siteRequest_, String v, ProviderRequested o) {
    switch(entityVar) {
    case "providerId":
      return ProviderRequested.staticSetProviderId(siteRequest_, v);
    case "providerResource":
      return ProviderRequested.staticSetProviderResource(siteRequest_, v);
    case "requestedNumber":
      return ProviderRequested.staticSetRequestedNumber(siteRequest_, v);
    case "requestedId":
      return ProviderRequested.staticSetRequestedId(siteRequest_, v);
    case "requestedName":
      return ProviderRequested.staticSetRequestedName(siteRequest_, v);
    case "requestApprovals":
      return ProviderRequested.staticSetRequestApprovals(siteRequest_, v);
    case "createdByEmail":
      return ProviderRequested.staticSetCreatedByEmail(siteRequest_, v);
    case "createdByUserId":
      return ProviderRequested.staticSetCreatedByUserId(siteRequest_, v);
    case "createdByFullName":
      return ProviderRequested.staticSetCreatedByFullName(siteRequest_, v);
    case "createdVia":
      return ProviderRequested.staticSetCreatedVia(siteRequest_, v);
    case "intentState":
      return ProviderRequested.staticSetIntentState(siteRequest_, v);
    case "requestedState":
      return ProviderRequested.staticSetRequestedState(siteRequest_, v);
    case "realizedState":
      return ProviderRequested.staticSetRealizedState(siteRequest_, v);
    case "description":
      return ProviderRequested.staticSetDescription(siteRequest_, v);
    case "locked":
      return ProviderRequested.staticSetLocked(siteRequest_, v);
    case "providerDiscovered":
      return ProviderRequested.staticSetProviderDiscovered(siteRequest_, v);
    case "providerRealized":
      return ProviderRequested.staticSetProviderRealized(siteRequest_, v);
      default:
        return Provider.staticSetProvider(entityVar,  siteRequest_, v, o);
    }
  }

  //////////////////
  // authorization //
  //////////////////

  public static Future<AsyncResult<HttpResponse<Buffer>>> authorizationProviderRequested(SiteRequest siteRequest, WebClient webClient, Boolean classPublicRead, String classApiMethod, String classApiMethodMethod, String scope) {
    Promise<AsyncResult<HttpResponse<Buffer>>> promise = Promise.promise();
    try {
      JsonObject config = siteRequest.getConfig();
      SiteRequest siteRequest2 = siteRequest.copy();
      String requestedId = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("requestedId");
      String PROVIDERREQUESTED = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("PROVIDERREQUESTED");
      List<String> groups = Optional.ofNullable(siteRequest.getGroups()).orElse(new ArrayList<>());
      MultiMap form = MultiMap.caseInsensitiveMultiMap();
      form.add("grant_type", "urn:ietf:params:oauth:grant-type:uma-ticket");
      form.add("audience", config.getString(ComputateConfigKeys.AUTH_CLIENT));
      form.add("response_mode", "permissions");
      form.add("permission", String.format("%s#%s", ProviderRequested.CLASS_AUTH_RESOURCE, "POST"));
      form.add("permission", String.format("%s#%s", ProviderRequested.CLASS_AUTH_RESOURCE, "PATCH"));
      form.add("permission", String.format("%s#%s", ProviderRequested.CLASS_AUTH_RESOURCE, "GET"));
      form.add("permission", String.format("%s#%s", ProviderRequested.CLASS_AUTH_RESOURCE, "DELETE"));
      form.add("permission", String.format("%s#%s", ProviderRequested.CLASS_AUTH_RESOURCE, "Admin"));
      form.add("permission", String.format("%s#%s", ProviderRequested.CLASS_AUTH_RESOURCE, "SuperAdmin"));
      if(requestedId != null)
        form.add("permission", String.format("%s-%s#%s", ProviderRequested.CLASS_AUTH_RESOURCE, requestedId, scope));
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
      LOG.error("Error while querying the provider requested", ex);
      promise.fail(ex);
    }
    return promise.future();
  }

  public static Future<SiteRequest> authorizationScopesProviderRequested(AsyncResult<HttpResponse<Buffer>> authorizationDecisionResponse, SiteRequest siteRequest2, WebClient webClient, Boolean classPublicRead, String classApiMethod, String classApiMethodMethod, String scope) {
    Promise<SiteRequest> promise = Promise.promise();
    try {
      siteRequest2.initDeepSiteRequest();
      HttpResponse<Buffer> authorizationDecision = authorizationDecisionResponse.result();
      JsonArray authorizationDecisionBody = authorizationDecisionResponse.failed() ? new JsonArray() : authorizationDecision.bodyAsJsonArray();
      JsonArray scopes = authorizationDecisionBody.stream().map(o -> (JsonObject)o).filter(o -> "PROVIDERREQUESTED".equals(o.getString("rsname"))).findFirst().map(decision -> ((JsonObject)decision).getJsonArray("scopes")).orElse(new JsonArray());
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
      LOG.error("Error while querying the provider requested", ex);
      promise.fail(ex);
    }
    return promise.future();
  }

  //////////////////
  // staticSearch //
  //////////////////

  public static Future<ProviderRequested> fqProviderRequested(SiteRequest siteRequest, String var, Object val) {
    Promise<ProviderRequested> promise = Promise.promise();
    try {
      if(val == null) {
        promise.complete();
      } else {
        SearchList<ProviderRequested> searchList = new SearchList<ProviderRequested>();
        searchList.setStore(true);
        searchList.q("*:*");
        searchList.setC(ProviderRequested.class);
        searchList.fq(String.format("%s:", ProviderRequested.varIndexedProviderRequested(var)) + SearchTool.escapeQueryChars(val.toString()));
        searchList.promiseDeepForClass(siteRequest).onSuccess(a -> {
          try {
            promise.complete(searchList.getList().stream().findFirst().orElse(null));
          } catch(Throwable ex) {
            LOG.error("Error while querying the provider requested", ex);
            promise.fail(ex);
          }
        }).onFailure(ex -> {
          LOG.error("Error while querying the provider requested", ex);
          promise.fail(ex);
        });
      }
    } catch(Throwable ex) {
      LOG.error("Error while querying the provider requested", ex);
      promise.fail(ex);
    }
    return promise.future();
  }

  public static Object staticSearchForClass(String entityVar, SiteRequest siteRequest_, Object o) {
    return staticSearchProviderRequested(entityVar,  siteRequest_, o);
  }
  public static Object staticSearchProviderRequested(String entityVar, SiteRequest siteRequest_, Object o) {
    switch(entityVar) {
    case "providerId":
      return ProviderRequested.staticSearchProviderId(siteRequest_, (String)o);
    case "providerResource":
      return ProviderRequested.staticSearchProviderResource(siteRequest_, (String)o);
    case "requestedNumber":
      return ProviderRequested.staticSearchRequestedNumber(siteRequest_, (Integer)o);
    case "requestedId":
      return ProviderRequested.staticSearchRequestedId(siteRequest_, (String)o);
    case "requestedName":
      return ProviderRequested.staticSearchRequestedName(siteRequest_, (String)o);
    case "requestApprovals":
      return ProviderRequested.staticSearchRequestApprovals(siteRequest_, (String)o);
    case "createdByEmail":
      return ProviderRequested.staticSearchCreatedByEmail(siteRequest_, (String)o);
    case "createdByUserId":
      return ProviderRequested.staticSearchCreatedByUserId(siteRequest_, (String)o);
    case "createdByFullName":
      return ProviderRequested.staticSearchCreatedByFullName(siteRequest_, (String)o);
    case "createdVia":
      return ProviderRequested.staticSearchCreatedVia(siteRequest_, (String)o);
    case "intentState":
      return ProviderRequested.staticSearchIntentState(siteRequest_, (String)o);
    case "requestedState":
      return ProviderRequested.staticSearchRequestedState(siteRequest_, (String)o);
    case "realizedState":
      return ProviderRequested.staticSearchRealizedState(siteRequest_, (String)o);
    case "description":
      return ProviderRequested.staticSearchDescription(siteRequest_, (String)o);
    case "locked":
      return ProviderRequested.staticSearchLocked(siteRequest_, (Boolean)o);
    case "providerDiscovered":
      return ProviderRequested.staticSearchProviderDiscovered(siteRequest_, (String)o);
    case "providerRealized":
      return ProviderRequested.staticSearchProviderRealized(siteRequest_, (String)o);
      default:
        return Provider.staticSearchProvider(entityVar,  siteRequest_, o);
    }
  }

  ///////////////////
  // staticSearchStr //
  ///////////////////

  public static String staticSearchStrForClass(String entityVar, SiteRequest siteRequest_, Object o) {
    return staticSearchStrProviderRequested(entityVar,  siteRequest_, o);
  }
  public static String staticSearchStrProviderRequested(String entityVar, SiteRequest siteRequest_, Object o) {
    switch(entityVar) {
    case "providerId":
      return ProviderRequested.staticSearchStrProviderId(siteRequest_, (String)o);
    case "providerResource":
      return ProviderRequested.staticSearchStrProviderResource(siteRequest_, (String)o);
    case "requestedNumber":
      return ProviderRequested.staticSearchStrRequestedNumber(siteRequest_, (Integer)o);
    case "requestedId":
      return ProviderRequested.staticSearchStrRequestedId(siteRequest_, (String)o);
    case "requestedName":
      return ProviderRequested.staticSearchStrRequestedName(siteRequest_, (String)o);
    case "requestApprovals":
      return ProviderRequested.staticSearchStrRequestApprovals(siteRequest_, (String)o);
    case "createdByEmail":
      return ProviderRequested.staticSearchStrCreatedByEmail(siteRequest_, (String)o);
    case "createdByUserId":
      return ProviderRequested.staticSearchStrCreatedByUserId(siteRequest_, (String)o);
    case "createdByFullName":
      return ProviderRequested.staticSearchStrCreatedByFullName(siteRequest_, (String)o);
    case "createdVia":
      return ProviderRequested.staticSearchStrCreatedVia(siteRequest_, (String)o);
    case "intentState":
      return ProviderRequested.staticSearchStrIntentState(siteRequest_, (String)o);
    case "requestedState":
      return ProviderRequested.staticSearchStrRequestedState(siteRequest_, (String)o);
    case "realizedState":
      return ProviderRequested.staticSearchStrRealizedState(siteRequest_, (String)o);
    case "description":
      return ProviderRequested.staticSearchStrDescription(siteRequest_, (String)o);
    case "locked":
      return ProviderRequested.staticSearchStrLocked(siteRequest_, (Boolean)o);
    case "providerDiscovered":
      return ProviderRequested.staticSearchStrProviderDiscovered(siteRequest_, (String)o);
    case "providerRealized":
      return ProviderRequested.staticSearchStrProviderRealized(siteRequest_, (String)o);
      default:
        return Provider.staticSearchStrProvider(entityVar,  siteRequest_, o);
    }
  }

  //////////////////
  // staticSearchFq //
  //////////////////

  public static String staticSearchFqForClass(String entityVar, SiteRequest siteRequest_, String o) {
    return staticSearchFqProviderRequested(entityVar,  siteRequest_, o);
  }
  public static String staticSearchFqProviderRequested(String entityVar, SiteRequest siteRequest_, String o) {
    switch(entityVar) {
    case "providerId":
      return ProviderRequested.staticSearchFqProviderId(siteRequest_, o);
    case "providerResource":
      return ProviderRequested.staticSearchFqProviderResource(siteRequest_, o);
    case "requestedNumber":
      return ProviderRequested.staticSearchFqRequestedNumber(siteRequest_, o);
    case "requestedId":
      return ProviderRequested.staticSearchFqRequestedId(siteRequest_, o);
    case "requestedName":
      return ProviderRequested.staticSearchFqRequestedName(siteRequest_, o);
    case "requestApprovals":
      return ProviderRequested.staticSearchFqRequestApprovals(siteRequest_, o);
    case "createdByEmail":
      return ProviderRequested.staticSearchFqCreatedByEmail(siteRequest_, o);
    case "createdByUserId":
      return ProviderRequested.staticSearchFqCreatedByUserId(siteRequest_, o);
    case "createdByFullName":
      return ProviderRequested.staticSearchFqCreatedByFullName(siteRequest_, o);
    case "createdVia":
      return ProviderRequested.staticSearchFqCreatedVia(siteRequest_, o);
    case "intentState":
      return ProviderRequested.staticSearchFqIntentState(siteRequest_, o);
    case "requestedState":
      return ProviderRequested.staticSearchFqRequestedState(siteRequest_, o);
    case "realizedState":
      return ProviderRequested.staticSearchFqRealizedState(siteRequest_, o);
    case "description":
      return ProviderRequested.staticSearchFqDescription(siteRequest_, o);
    case "locked":
      return ProviderRequested.staticSearchFqLocked(siteRequest_, o);
    case "providerDiscovered":
      return ProviderRequested.staticSearchFqProviderDiscovered(siteRequest_, o);
    case "providerRealized":
      return ProviderRequested.staticSearchFqProviderRealized(siteRequest_, o);
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
          o = persistProviderRequested(v, val);
        else if(o instanceof BaseModel) {
          BaseModel oBaseModel = (BaseModel)o;
          o = oBaseModel.persistForClass(v, val);
        }
      }
    }
    return o != null;
  }
  public Object persistProviderRequested(String var, Object val) {
    String varLower = var.toLowerCase();
      if("providerid".equals(varLower)) {
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
      } else if("requestednumber".equals(varLower)) {
        if(val instanceof Integer) {
          setRequestedNumber((Integer)val);
        } else {
          setRequestedNumber(val == null ? null : val.toString());
        }
        saves.add("requestedNumber");
        return val;
      } else if("requestedid".equals(varLower)) {
        if(val instanceof String) {
          setRequestedId((String)val);
        }
        saves.add("requestedId");
        return val;
      } else if("requestedname".equals(varLower)) {
        if(val instanceof String) {
          setRequestedName((String)val);
        }
        saves.add("requestedName");
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
    populateProviderRequested(doc);
  }
  public void populateProviderRequested(SolrResponse.Doc doc) {
    ProviderRequested oProviderRequested = (ProviderRequested)this;
    saves = Optional.ofNullable((ArrayList<String>)doc.get("saves_docvalues_strings")).orElse(new ArrayList<String>());
    if(saves != null) {

      if(saves.contains("providerId")) {
        String providerId = (String)doc.get("providerId_docvalues_string");
        if(providerId != null)
          oProviderRequested.setProviderId(providerId);
      }

      String providerResource = (String)doc.get("providerResource_docvalues_string");
      if(providerResource != null)
        oProviderRequested.setProviderResource(providerResource);

      if(saves.contains("requestedNumber")) {
        Integer requestedNumber = (Integer)doc.get("requestedNumber_docvalues_int");
        if(requestedNumber != null)
          oProviderRequested.setRequestedNumber(requestedNumber);
      }

      if(saves.contains("requestedId")) {
        String requestedId = (String)doc.get("requestedId_docvalues_string");
        if(requestedId != null)
          oProviderRequested.setRequestedId(requestedId);
      }

      if(saves.contains("requestedName")) {
        String requestedName = (String)doc.get("requestedName_docvalues_string");
        if(requestedName != null)
          oProviderRequested.setRequestedName(requestedName);
      }

      if(saves.contains("requestApprovals")) {
        List<String> requestApprovals = (List<String>)doc.get("requestApprovals_docvalues_strings");
        if(requestApprovals != null) {
          requestApprovals.stream().forEach( v -> {
            oProviderRequested.requestApprovals.add(ProviderRequested.staticSetRequestApprovals(siteRequest_, v));
          });
        }
      }

      if(saves.contains("createdByEmail")) {
        String createdByEmail = (String)doc.get("createdByEmail_docvalues_string");
        if(createdByEmail != null)
          oProviderRequested.setCreatedByEmail(createdByEmail);
      }

      if(saves.contains("createdByUserId")) {
        String createdByUserId = (String)doc.get("createdByUserId_docvalues_string");
        if(createdByUserId != null)
          oProviderRequested.setCreatedByUserId(createdByUserId);
      }

      if(saves.contains("createdByFullName")) {
        String createdByFullName = (String)doc.get("createdByFullName_docvalues_string");
        if(createdByFullName != null)
          oProviderRequested.setCreatedByFullName(createdByFullName);
      }

      if(saves.contains("createdVia")) {
        String createdVia = (String)doc.get("createdVia_docvalues_string");
        if(createdVia != null)
          oProviderRequested.setCreatedVia(createdVia);
      }

      if(saves.contains("intentState")) {
        String intentState = (String)doc.get("intentState_docvalues_string");
        if(intentState != null)
          oProviderRequested.setIntentState(intentState);
      }

      if(saves.contains("requestedState")) {
        String requestedState = (String)doc.get("requestedState_docvalues_string");
        if(requestedState != null)
          oProviderRequested.setRequestedState(requestedState);
      }

      if(saves.contains("realizedState")) {
        String realizedState = (String)doc.get("realizedState_docvalues_string");
        if(realizedState != null)
          oProviderRequested.setRealizedState(realizedState);
      }

      if(saves.contains("description")) {
        String description = (String)doc.get("description_docvalues_string");
        if(description != null)
          oProviderRequested.setDescription(description);
      }

      if(saves.contains("locked")) {
        Boolean locked = (Boolean)doc.get("locked_docvalues_boolean");
        if(locked != null)
          oProviderRequested.setLocked(locked);
      }

      if(saves.contains("providerDiscovered")) {
        List<String> providerDiscovered = (List<String>)doc.get("providerDiscovered_docvalues_strings");
        if(providerDiscovered != null) {
          providerDiscovered.stream().forEach( v -> {
            oProviderRequested.providerDiscovered.add(ProviderRequested.staticSetProviderDiscovered(siteRequest_, v));
          });
        }
      }

      if(saves.contains("providerRealized")) {
        List<String> providerRealized = (List<String>)doc.get("providerRealized_docvalues_strings");
        if(providerRealized != null) {
          providerRealized.stream().forEach( v -> {
            oProviderRequested.providerRealized.add(ProviderRequested.staticSetProviderRealized(siteRequest_, v));
          });
        }
      }
    }

    super.populateProvider(doc);
  }

  public void indexProviderRequested(JsonObject doc) {
    if(providerId != null) {
      doc.put("providerId_docvalues_string", providerId);
    }
    if(providerResource != null) {
      doc.put("providerResource_docvalues_string", providerResource);
    }
    if(requestedNumber != null) {
      doc.put("requestedNumber_docvalues_int", requestedNumber);
    }
    if(requestedId != null) {
      doc.put("requestedId_docvalues_string", requestedId);
    }
    if(requestedName != null) {
      doc.put("requestedName_docvalues_string", requestedName);
    }
    if(requestApprovals != null) {
      JsonArray l = new JsonArray();
      doc.put("requestApprovals_docvalues_strings", l);
      for(String o : requestApprovals) {
        l.add(ProviderRequested.staticSearchRequestApprovals(siteRequest_, o));
      }
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
    if(locked != null) {
      doc.put("locked_docvalues_boolean", locked);
    }
    if(providerDiscovered != null) {
      JsonArray l = new JsonArray();
      doc.put("providerDiscovered_docvalues_strings", l);
      for(String o : providerDiscovered) {
        l.add(ProviderRequested.staticSearchProviderDiscovered(siteRequest_, o));
      }
    }
    if(providerRealized != null) {
      JsonArray l = new JsonArray();
      doc.put("providerRealized_docvalues_strings", l);
      for(String o : providerRealized) {
        l.add(ProviderRequested.staticSearchProviderRealized(siteRequest_, o));
      }
    }
    super.indexProvider(doc);

	}

  public static String varStoredProviderRequested(String entityVar) {
    switch(entityVar) {
      case "providerId":
        return "providerId_docvalues_string";
      case "providerResource":
        return "providerResource_docvalues_string";
      case "requestedNumber":
        return "requestedNumber_docvalues_int";
      case "requestedId":
        return "requestedId_docvalues_string";
      case "requestedName":
        return "requestedName_docvalues_string";
      case "requestApprovals":
        return "requestApprovals_docvalues_strings";
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
      case "locked":
        return "locked_docvalues_boolean";
      case "providerDiscovered":
        return "providerDiscovered_docvalues_strings";
      case "providerRealized":
        return "providerRealized_docvalues_strings";
      default:
        return Provider.varStoredProvider(entityVar);
    }
  }

  public static String varIndexedProviderRequested(String entityVar) {
    switch(entityVar) {
      case "providerId":
        return "providerId_docvalues_string";
      case "providerResource":
        return "providerResource_docvalues_string";
      case "requestedNumber":
        return "requestedNumber_docvalues_int";
      case "requestedId":
        return "requestedId_docvalues_string";
      case "requestedName":
        return "requestedName_docvalues_string";
      case "requestApprovals":
        return "requestApprovals_docvalues_strings";
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
      case "locked":
        return "locked_docvalues_boolean";
      case "providerDiscovered":
        return "providerDiscovered_docvalues_strings";
      case "providerRealized":
        return "providerRealized_docvalues_strings";
      default:
        return Provider.varIndexedProvider(entityVar);
    }
  }

  public static String searchVarProviderRequested(String searchVar) {
    switch(searchVar) {
      case "providerId_docvalues_string":
        return "providerId";
      case "providerResource_docvalues_string":
        return "providerResource";
      case "requestedNumber_docvalues_int":
        return "requestedNumber";
      case "requestedId_docvalues_string":
        return "requestedId";
      case "requestedName_docvalues_string":
        return "requestedName";
      case "requestApprovals_docvalues_strings":
        return "requestApprovals";
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
      case "locked_docvalues_boolean":
        return "locked";
      case "providerDiscovered_docvalues_strings":
        return "providerDiscovered";
      case "providerRealized_docvalues_strings":
        return "providerRealized";
      default:
        return Provider.searchVarProvider(searchVar);
    }
  }

  public static String varSearchProviderRequested(String entityVar) {
    switch(entityVar) {
      default:
        return Provider.varSearchProvider(entityVar);
    }
  }

  public static String varSuggestedProviderRequested(String entityVar) {
    switch(entityVar) {
      default:
        return Provider.varSuggestedProvider(entityVar);
    }
  }

  /////////////
  // store //
  /////////////

  @Override public void storeForClass(SolrResponse.Doc doc) {
    storeProviderRequested(doc);
  }
  public void storeProviderRequested(SolrResponse.Doc doc) {
    ProviderRequested oProviderRequested = (ProviderRequested)this;
    SiteRequest siteRequest = oProviderRequested.getSiteRequest_();

    oProviderRequested.setProviderId(Optional.ofNullable(doc.get("providerId_docvalues_string")).map(v -> v.toString()).orElse(null));
    oProviderRequested.setProviderResource(Optional.ofNullable(doc.get("providerResource_docvalues_string")).map(v -> v.toString()).orElse(null));
    oProviderRequested.setRequestedNumber(Optional.ofNullable(doc.get("requestedNumber_docvalues_int")).map(v -> v.toString()).orElse(null));
    oProviderRequested.setRequestedId(Optional.ofNullable(doc.get("requestedId_docvalues_string")).map(v -> v.toString()).orElse(null));
    oProviderRequested.setRequestedName(Optional.ofNullable(doc.get("requestedName_docvalues_string")).map(v -> v.toString()).orElse(null));
    Optional.ofNullable((List<?>)doc.get("requestApprovals_docvalues_strings")).orElse(Arrays.asList()).stream().filter(v -> v != null).forEach(v -> {
      oProviderRequested.addRequestApprovals(ProviderRequested.staticSetRequestApprovals(siteRequest, v.toString()));
    });
    oProviderRequested.setCreatedByEmail(Optional.ofNullable(doc.get("createdByEmail_docvalues_string")).map(v -> v.toString()).orElse(null));
    oProviderRequested.setCreatedByUserId(Optional.ofNullable(doc.get("createdByUserId_docvalues_string")).map(v -> v.toString()).orElse(null));
    oProviderRequested.setCreatedByFullName(Optional.ofNullable(doc.get("createdByFullName_docvalues_string")).map(v -> v.toString()).orElse(null));
    oProviderRequested.setCreatedVia(Optional.ofNullable(doc.get("createdVia_docvalues_string")).map(v -> v.toString()).orElse(null));
    oProviderRequested.setIntentState(Optional.ofNullable(doc.get("intentState_docvalues_string")).map(v -> v.toString()).orElse(null));
    oProviderRequested.setRequestedState(Optional.ofNullable(doc.get("requestedState_docvalues_string")).map(v -> v.toString()).orElse(null));
    oProviderRequested.setRealizedState(Optional.ofNullable(doc.get("realizedState_docvalues_string")).map(v -> v.toString()).orElse(null));
    oProviderRequested.setDescription(Optional.ofNullable(doc.get("description_docvalues_string")).map(v -> v.toString()).orElse(null));
    oProviderRequested.setLocked(Optional.ofNullable(doc.get("locked_docvalues_boolean")).map(v -> v.toString()).orElse(null));
    Optional.ofNullable((List<?>)doc.get("providerDiscovered_docvalues_strings")).orElse(Arrays.asList()).stream().filter(v -> v != null).forEach(v -> {
      oProviderRequested.addProviderDiscovered(ProviderRequested.staticSetProviderDiscovered(siteRequest, v.toString()));
    });
    Optional.ofNullable((List<?>)doc.get("providerRealized_docvalues_strings")).orElse(Arrays.asList()).stream().filter(v -> v != null).forEach(v -> {
      oProviderRequested.addProviderRealized(ProviderRequested.staticSetProviderRealized(siteRequest, v.toString()));
    });

    super.storeProvider(doc);
  }

  //////////////////
  // apiRequest //
  //////////////////

  public void apiRequestProviderRequested() {
    ApiRequest apiRequest = Optional.ofNullable(siteRequest_).map(r -> r.getApiRequest_()).orElse(null);
    Object o = Optional.ofNullable(apiRequest).map(ApiRequest::getOriginal).orElse(null);
    if(o != null && o instanceof ProviderRequested) {
      ProviderRequested original = (ProviderRequested)o;
      if(!Objects.equals(providerId, original.getProviderId()))
        apiRequest.addVars("providerId");
      if(!Objects.equals(providerResource, original.getProviderResource()))
        apiRequest.addVars("providerResource");
      if(!Objects.equals(requestedNumber, original.getRequestedNumber()))
        apiRequest.addVars("requestedNumber");
      if(!Objects.equals(requestedId, original.getRequestedId()))
        apiRequest.addVars("requestedId");
      if(!Objects.equals(requestedName, original.getRequestedName()))
        apiRequest.addVars("requestedName");
      if(!Objects.equals(requestApprovals, original.getRequestApprovals()))
        apiRequest.addVars("requestApprovals");
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
      if(!Objects.equals(locked, original.getLocked()))
        apiRequest.addVars("locked");
      if(!Objects.equals(providerDiscovered, original.getProviderDiscovered()))
        apiRequest.addVars("providerDiscovered");
      if(!Objects.equals(providerRealized, original.getProviderRealized()))
        apiRequest.addVars("providerRealized");
      super.apiRequestProvider();
    }
  }

  //////////////
  // toString //
  //////////////

  @Override public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(super.toString());
    sb.append(Optional.ofNullable(providerId).map(v -> "providerId: \"" + v + "\"\n" ).orElse(""));
    sb.append(Optional.ofNullable(providerResource).map(v -> "providerResource: \"" + v + "\"\n" ).orElse(""));
    sb.append(Optional.ofNullable(requestedNumber).map(v -> "requestedNumber: " + v + "\n").orElse(""));
    sb.append(Optional.ofNullable(requestedId).map(v -> "requestedId: \"" + v + "\"\n" ).orElse(""));
    sb.append(Optional.ofNullable(requestedName).map(v -> "requestedName: \"" + v + "\"\n" ).orElse(""));
    sb.append(Optional.ofNullable(requestApprovals).map(v -> "requestApprovals: " + v + "\n").orElse(""));
    sb.append(Optional.ofNullable(createdByEmail).map(v -> "createdByEmail: \"" + v + "\"\n" ).orElse(""));
    sb.append(Optional.ofNullable(createdByUserId).map(v -> "createdByUserId: \"" + v + "\"\n" ).orElse(""));
    sb.append(Optional.ofNullable(createdByFullName).map(v -> "createdByFullName: \"" + v + "\"\n" ).orElse(""));
    sb.append(Optional.ofNullable(createdVia).map(v -> "createdVia: \"" + v + "\"\n" ).orElse(""));
    sb.append(Optional.ofNullable(intentState).map(v -> "intentState: \"" + v + "\"\n" ).orElse(""));
    sb.append(Optional.ofNullable(requestedState).map(v -> "requestedState: \"" + v + "\"\n" ).orElse(""));
    sb.append(Optional.ofNullable(realizedState).map(v -> "realizedState: \"" + v + "\"\n" ).orElse(""));
    sb.append(Optional.ofNullable(description).map(v -> "description: \"" + v + "\"\n" ).orElse(""));
    sb.append(Optional.ofNullable(locked).map(v -> "locked: " + v + "\n").orElse(""));
    sb.append(Optional.ofNullable(providerDiscovered).map(v -> "providerDiscovered: " + v + "\n").orElse(""));
    sb.append(Optional.ofNullable(providerRealized).map(v -> "providerRealized: " + v + "\n").orElse(""));
    return sb.toString();
  }

  public static final String CLASS_SIMPLE_NAME = "ProviderRequested";
  public static final String CLASS_CANONICAL_NAME = "org.computate.dcm.model.eda.provider.requested.ProviderRequested";
  public static final String CLASS_AUTH_RESOURCE = "PROVIDERREQUESTED";
  public static final String CLASS_API_ADDRESS_ProviderRequested = "dcm-enUS-ProviderRequested";
  public static String getClassApiAddress() {
    return CLASS_API_ADDRESS_ProviderRequested;
  }
  public static final String VAR_providerId = "providerId";
  public static final String SET_providerId = "setProviderId";
  public static final String VAR_providerResource = "providerResource";
  public static final String SET_providerResource = "setProviderResource";
  public static final String VAR_requestedNumber = "requestedNumber";
  public static final String SET_requestedNumber = "setRequestedNumber";
  public static final String VAR_requestedId = "requestedId";
  public static final String SET_requestedId = "setRequestedId";
  public static final String VAR_requestedName = "requestedName";
  public static final String SET_requestedName = "setRequestedName";
  public static final String VAR_requestApprovals = "requestApprovals";
  public static final String SET_requestApprovals = "setRequestApprovals";
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
  public static final String VAR_locked = "locked";
  public static final String SET_locked = "setLocked";
  public static final String VAR_providerDiscovered = "providerDiscovered";
  public static final String SET_providerDiscovered = "setProviderDiscovered";
  public static final String VAR_providerRealized = "providerRealized";
  public static final String SET_providerRealized = "setProviderRealized";

  public static List<String> varsQForClass() {
    return ProviderRequested.varsQProviderRequested(new ArrayList<String>());
  }
  public static List<String> varsQProviderRequested(List<String> vars) {
    Provider.varsQProvider(vars);
    return vars;
  }

  public static List<String> varsFqForClass() {
    return ProviderRequested.varsFqProviderRequested(new ArrayList<String>());
  }
  public static List<String> varsFqProviderRequested(List<String> vars) {
    vars.add(VAR_providerId);
    vars.add(VAR_providerResource);
    vars.add(VAR_requestedNumber);
    vars.add(VAR_requestedId);
    vars.add(VAR_requestedName);
    vars.add(VAR_description);
    vars.add(VAR_locked);
    Provider.varsFqProvider(vars);
    return vars;
  }

  public static List<String> varsRangeForClass() {
    return ProviderRequested.varsRangeProviderRequested(new ArrayList<String>());
  }
  public static List<String> varsRangeProviderRequested(List<String> vars) {
    vars.add(VAR_requestedNumber);
    Provider.varsRangeProvider(vars);
    return vars;
  }

  public static final String DISPLAY_NAME_providerId = "provider ID";
  public static final String DISPLAY_NAME_providerResource = "provider auth resource";
  public static final String DISPLAY_NAME_requestedNumber = "provider requested number";
  public static final String DISPLAY_NAME_requestedId = "provider requested ID";
  public static final String DISPLAY_NAME_requestedName = "provider requested name";
  public static final String DISPLAY_NAME_requestApprovals = "provider approvals";
  public static final String DISPLAY_NAME_createdByEmail = "created by user email";
  public static final String DISPLAY_NAME_createdByUserId = "created by user ID";
  public static final String DISPLAY_NAME_createdByFullName = "created by user full name";
  public static final String DISPLAY_NAME_createdVia = "created via";
  public static final String DISPLAY_NAME_intentState = "requested state";
  public static final String DISPLAY_NAME_requestedState = "requested state";
  public static final String DISPLAY_NAME_realizedState = "realized state";
  public static final String DISPLAY_NAME_description = "description";
  public static final String DISPLAY_NAME_locked = "locked";
  public static final String DISPLAY_NAME_providerDiscovered = "provider discovered";
  public static final String DISPLAY_NAME_providerRealized = "provider realized";

  @Override
  public String idForClass() {
    return requestedId;
  }

  @Override
  public String titleForClass() {
    return objectTitle;
  }

  @Override
  public String nameForClass() {
    return requestedName;
  }

  @Override
  public String classNameAdjectiveSingularForClass() {
    return ProviderRequested.NameAdjectiveSingular_enUS;
  }

  @Override
  public String descriptionForClass() {
    return description;
  }

  @Override
  public String enUSStringFormatUrlEditPageForClass() {
    return "%s/en-us/edit/requested/provider/%s";
  }

  public static String varJson(String var, Boolean patch) {
    return ProviderRequested.varJsonProviderRequested(var, patch);
  }
  public static String varJsonProviderRequested(String var, Boolean patch) {
    switch(var) {
    case VAR_providerId:
      return patch ? SET_providerId : VAR_providerId;
    case VAR_providerResource:
      return patch ? SET_providerResource : VAR_providerResource;
    case VAR_requestedNumber:
      return patch ? SET_requestedNumber : VAR_requestedNumber;
    case VAR_requestedId:
      return patch ? SET_requestedId : VAR_requestedId;
    case VAR_requestedName:
      return patch ? SET_requestedName : VAR_requestedName;
    case VAR_requestApprovals:
      return patch ? SET_requestApprovals : VAR_requestApprovals;
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
    case VAR_locked:
      return patch ? SET_locked : VAR_locked;
    case VAR_providerDiscovered:
      return patch ? SET_providerDiscovered : VAR_providerDiscovered;
    case VAR_providerRealized:
      return patch ? SET_providerRealized : VAR_providerRealized;
    default:
      return Provider.varJsonProvider(var, patch);
    }
  }

  public static String displayNameForClass(String var) {
    return ProviderRequested.displayNameProviderRequested(var);
  }
  public static String displayNameProviderRequested(String var) {
    switch(var) {
    case VAR_providerId:
      return DISPLAY_NAME_providerId;
    case VAR_providerResource:
      return DISPLAY_NAME_providerResource;
    case VAR_requestedNumber:
      return DISPLAY_NAME_requestedNumber;
    case VAR_requestedId:
      return DISPLAY_NAME_requestedId;
    case VAR_requestedName:
      return DISPLAY_NAME_requestedName;
    case VAR_requestApprovals:
      return DISPLAY_NAME_requestApprovals;
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
    case VAR_locked:
      return DISPLAY_NAME_locked;
    case VAR_providerDiscovered:
      return DISPLAY_NAME_providerDiscovered;
    case VAR_providerRealized:
      return DISPLAY_NAME_providerRealized;
    default:
      return Provider.displayNameProvider(var);
    }
  }

  public static String descriptionProviderRequested(String var) {
    if(var == null)
      return null;
    switch(var) {
    case VAR_providerId:
      return "The ID of this provider. By default, this will be auto-generated based on the provider name, converting non-alphanumeric characters to hyphens, all lowercase. ";
    case VAR_providerResource:
      return "The unique authorization resource for the provider for multi-tenancy";
    case VAR_requestedNumber:
      return "A unique number for this change to this provider. ";
    case VAR_requestedId:
      return "The unique ID for this provider requested. ";
    case VAR_requestedName:
      return "The name of this provider requested model";
    case VAR_requestApprovals:
      return "A list of provider approvals for this request. ";
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
    case VAR_locked:
      return "A provider requested gets locked after creating the first provider request. ";
    case VAR_providerDiscovered:
      return "Each time the provider was discovered for this provider intent. ";
    case VAR_providerRealized:
      return "Each time the provider was realized for this provider requested. ";
      default:
        return Provider.descriptionProvider(var);
    }
  }

  public static String classSimpleNameProviderRequested(String var) {
    switch(var) {
    case VAR_providerId:
      return "String";
    case VAR_providerResource:
      return "String";
    case VAR_requestedNumber:
      return "Integer";
    case VAR_requestedId:
      return "String";
    case VAR_requestedName:
      return "String";
    case VAR_requestApprovals:
      return "List";
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
    case VAR_locked:
      return "Boolean";
    case VAR_providerDiscovered:
      return "List";
    case VAR_providerRealized:
      return "List";
      default:
        return Provider.classSimpleNameProvider(var);
    }
  }

  public static Integer htmColumnProviderRequested(String var) {
    switch(var) {
    case VAR_requestedId:
      return 0;
    case VAR_requestedName:
      return 1;
    case VAR_description:
      return 3;
      default:
        return Provider.htmColumnProvider(var);
    }
  }

  public static Integer htmRowProviderRequested(String var) {
    switch(var) {
    case VAR_providerResource:
      return 5;
    case VAR_requestApprovals:
      return 8;
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
    case VAR_locked:
      return 21;
    case VAR_providerDiscovered:
      return 22;
    case VAR_providerRealized:
      return 23;
      default:
        return Provider.htmRowProvider(var);
    }
  }

  public static Integer htmCellProviderRequested(String var) {
    switch(var) {
    case VAR_providerResource:
      return 0;
    case VAR_requestApprovals:
      return 0;
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
    case VAR_locked:
      return 0;
    case VAR_providerDiscovered:
      return 0;
    case VAR_providerRealized:
      return 0;
      default:
        return Provider.htmCellProvider(var);
    }
  }

  public static Integer lengthMinProviderRequested(String var) {
    switch(var) {
      default:
        return Provider.lengthMinProvider(var);
    }
  }

  public static Integer lengthMaxProviderRequested(String var) {
    switch(var) {
      default:
        return Provider.lengthMaxProvider(var);
    }
  }

  public static Integer maxProviderRequested(String var) {
    switch(var) {
      default:
        return Provider.maxProvider(var);
    }
  }

  public static Integer minProviderRequested(String var) {
    switch(var) {
      default:
        return Provider.minProvider(var);
    }
  }
}
