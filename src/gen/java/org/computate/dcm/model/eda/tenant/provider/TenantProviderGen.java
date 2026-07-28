package org.computate.dcm.model.eda.tenant.provider;

import org.computate.search.wrap.Wrap;
import org.computate.dcm.model.BaseModel;
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
import io.vertx.core.json.JsonArray;
import org.computate.vertx.search.list.SearchList;
import org.computate.search.tool.SearchTool;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.computate.search.response.solr.SolrResponse;

/**
 * <ol>
<h3>Suggestions that can generate more code for you: </h3> * </ol>
 * <li>You can add a class comment "{@inheritDoc}" if you wish to inherit the helpful inherited class comments from class TenantProviderGen into the class TenantProvider. 
 * </li><li><p>
 *   You can add a class comment <kbd><b>Rows: 100</b></kbd> if you wish for the tenant intent API to return more or less than 10 results by default. 
 *   In this case, the API will return 100 results from the API instead of 10 by default. 
 *   Each API has built in pagination of the search results to ensure a user can query all the data a page at a time without running the application out of memory. 
 * </p>
 * </li>
 * <h3>About the TenantProvider class and it's generated class TenantProviderGen&lt;BaseModel&gt;: </h3>extends TenantProviderGen
 * <p>
 * This Java class extends a generated Java class TenantProviderGen built by the <a href="https://github.com/computate-org/computate">https://github.com/computate-org/computate</a> project. 
 * Whenever this Java class is modified or touched, the watch service installed as described in the README, indexes all the information about this Java class in a local Apache Solr Search Engine. 
 * If you are running the service, you can see the indexed data about this Java Class here: 
 * </p>
 * <p><a href="https://solr.apps-crc.testing/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.tenant.provider.TenantProvider">Find the class TenantProvider in Solr. </a></p>
 * <p>
 * The extended class ending with "Gen" did not exist at first, but was automatically created by the same watch service based on the data retrieved from the local Apache Server search engine. 
 * The extended class contains many generated fields, getters, setters, initialization code, and helper methods to help build a website and API fast, reactive, and scalable. 
 * </p>
 * extends TenantProviderGen<BaseModel>
 * <p>This <code>class TenantProvider extends TenantProviderGen&lt;BaseModel&gt;</code>, which means it extends a newly generated TenantProviderGen. 
 * The generated <code>class TenantProviderGen extends BaseModel</code> which means that TenantProvider extends TenantProviderGen which extends BaseModel. 
 * This generated inheritance is a powerful feature that allows a lot of boiler plate code to be created for you automatically while still preserving inheritance through the power of Java Generic classes. 
 * </p>
 * <h2>
 *   Api: true
 * </h2>
 * <p>
 *   This class contains a comment <kbd><b>Api: true</b></kbd>, which means this class will have Java Vert.x API backend code generated for these objects. 
 * </p>
 * <h2>ApiTag.enUS: true</h2>
 * <p>This class contains a comment <kbd><b>ApiTag: tenant intents</b></kbd>, which groups all of the OpenAPIs for TenantProvider objects under the tag "tenant intents". 
 * </p>
 * <h2>ApiUri.enUS: /en-us/api/provider/tenant</h2>
 * <p>This class contains a comment <kbd><b>ApiUri: /en-us/api/provider/tenant</b></kbd>, which defines the base API URI for TenantProvider objects as "/en-us/api/provider/tenant" in the OpenAPI spec. 
 * </p>
 * <h2>Color: null</h2>
 * <h2>Indexed: true</h2>
 * <p>This class contains a comment <kbd><b>Indexed: true</b></kbd>, which means this class will be indexed in the search engine. 
 * Every protected void method that begins with "_" that is marked to be searched with a comment like "Indexed: true", "Stored: true", or "DocValues: true" will be indexed in the search engine. 
 * </p>
 * <h2>{@inheritDoc}</h2>
 * <p>By adding a class comment "{@inheritDoc}", the TenantProvider class will inherit the helpful inherited class comments from the super class TenantProviderGen. 
 * </p>
 * <h2>
 *   Rows: 10
 * </h2>
 * <p>This class contains a comment <kbd><b>Rows: 10</b></kbd>, which means the tenant intent API will return a default of 10 results instead of 10 by default. 
 * Each API has built in pagination of the search results to ensure a user can query all the data a page at a time without running the application out of memory. 
 * </p>
 * <p>
 *   You can add a class comment <kbd><b>Rows: 100</b></kbd> if you wish for the tenant intent API to return more or less than 10 results by default. 
 *   In this case, the API will return 100 results from the API instead of 10 by default. 
 *   Each API has built in pagination of the search results to ensure a user can query all the data a page at a time without running the application out of memory. 
 * </p>
 * <h2>
 *   Order: 146
 * </h2>
 * <p>
 *   This class contains a comment <kbd><b>Order: 146</b></kbd>, 
 *   which means this class will be sorted by the given number 146 
 *   ascending when code that relates to multiple classes at the same time is generated. 
 * </p>
 * <h2>SqlOrder: 146</h2>
 * <p>This class contains a comment <kbd><b>SqlOrder: 146</b></kbd>, which means this class will be sorted by the given number 146 ascending when SQL code to create and drop the tables is generated. 
 * </p>
 * <h2>Model: true</h2>
 * <p>This class contains a comment <kbd><b>Model: true</b></kbd>, which means this class will be stored in the database. 
 * Every protected void method that begins with "_" that contains a "Persist: true" comment will be a persisted field in the database table. 
 * </p>
 * <h2>Page: true</h2>
 * <p>This class contains a comment <kbd><b>Page: true</b></kbd>, which means this class will have webpage code generated for these objects. 
 * Java Vert.x backend API code, Handlebars HTML template frontend code, and JavaScript code will all generated and can be extended. 
 * This creates a new Java class org.computate.dcm.model.eda.tenant.provider.TenantProviderPage. 
 * </p>
 * <h2>SuperPage.enUS: PageLayout</h2>
 * <p>This class contains a comment <kbd><b>SuperPage.enUS: PageLayout</b></kbd>, which identifies the Java super class of the page code by it's class simple name "PageLayout". 
 * This means that the newly created class org.computate.dcm.model.eda.tenant.provider.TenantProviderPage extends org.computate.dcm.page.PageLayout. 
 * </p>
 * <h2>Promise: true</h2>
 * <p>
 *   This class contains a comment <kbd><b>Promise: true</b></kbd>
 *   Sometimes a Java class must be initialized asynchronously when it involves calling a blocking API. 
 *   This means that the TenantProvider Java class has promiseDeep methods which must be initialized asynchronously as a Vert.x Promise  instead of initDeep methods which are a simple non-asynchronous method. 
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
 * <h2>AName.enUS: a tenant intent</h2>
 * <p>This class contains a comment <kbd><b>AName.enUS: a tenant intent</b></kbd>, which identifies the language context to describe a TenantProvider as "a tenant intent". 
 * </p>
 * <p>
 * Delete the class TenantProvider in Solr: 
 * <pre>
 * curl -k 'https://solr.apps-crc.testing/solr/computate/update?commitWithin=1000&amp;overwrite=true&amp;wt=json' -X POST -H 'Content-type: text/xml' -u "admin:$(oc -n solr get secret/solr-solrcloud-security-bootstrap -o jsonpath={.data.admin} | base64 -d)" --data-raw '&lt;delete&gt;&lt;query&gt;classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.tenant.provider.TenantProvider&lt;/query&gt;&lt;/delete&gt;'
 * </pre>
 * </p>
 * <p>
 * Delete  the package org.computate.dcm.model.eda.tenant.provider in Solr: 
 * <pre>
 * curl -k 'https://solr.apps-crc.testing/solr/computate/update?commitWithin=1000&amp;overwrite=true&amp;wt=json' -X POST -H 'Content-type: text/xml' -u "admin:$(oc -n solr get secret/solr-solrcloud-security-bootstrap -o jsonpath={.data.admin} | base64 -d)" --data-raw '&lt;delete&gt;&lt;query&gt;classeNomEnsemble_enUS_indexed_string:org.computate.dcm.model.eda.tenant.provider&lt;/query&gt;&lt;/delete&gt;'
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
public abstract class TenantProviderGen<DEV> extends BaseModel {
  protected static final Logger LOG = LoggerFactory.getLogger(TenantProvider.class);

  public static final String Description_enUS = "A provider for requesting a TenantIntent to be realized. ";
  public static final String AName_enUS = "a tenant intent";
  public static final String This_enUS = "this ";
  public static final String ThisName_enUS = "this tenant intent";
  public static final String A_enUS = "a ";
  public static final String TheName_enUS = "the tenant intent";
  public static final String SingularName_enUS = "tenant intent";
  public static final String PluralName_enUS = "tenant intents";
  public static final String NameActual_enUS = "current tenant intent";
  public static final String AllName_enUS = "all tenant intents";
  public static final String SearchAllNameBy_enUS = "search tenant intents by ";
  public static final String SearchAllName_enUS = "search tenant intents";
  public static final String Title_enUS = "tenant intents";
  public static final String ThePluralName_enUS = "the tenant intents";
  public static final String NoNameFound_enUS = "no tenant intent found";
  public static final String ApiUri_enUS = "/en-us/api/provider/tenant";
  public static final String ApiUriSearchPage_enUS = "/en-us/search/provider/tenant";
  public static final String ApiUriEditPage_enUS = "/en-us/edit/provider/tenant/{tenantResource}";
  public static final String OfName_enUS = "of tenant intent";
  public static final String ANameAdjective_enUS = "a tenant intent";
  public static final String NameAdjectiveSingular_enUS = "tenant intent";
  public static final String NameAdjectivePlural_enUS = "tenant intents";
  public static final String Search_enUS_OpenApiUri = "/en-us/api/provider/tenant";
  public static final String Search_enUS_StringFormatUri = "/en-us/api/provider/tenant";
  public static final String Search_enUS_StringFormatUrl = "%s/en-us/api/provider/tenant";
  public static final String GET_enUS_OpenApiUri = "/en-us/api/provider/tenant/{providerId}";
  public static final String GET_enUS_StringFormatUri = "/en-us/api/provider/tenant/%s";
  public static final String GET_enUS_StringFormatUrl = "%s/en-us/api/provider/tenant/%s";
  public static final String PATCH_enUS_OpenApiUri = "/en-us/api/provider/tenant";
  public static final String PATCH_enUS_StringFormatUri = "/en-us/api/provider/tenant";
  public static final String PATCH_enUS_StringFormatUrl = "%s/en-us/api/provider/tenant";
  public static final String POST_enUS_OpenApiUri = "/en-us/api/provider/tenant";
  public static final String POST_enUS_StringFormatUri = "/en-us/api/provider/tenant";
  public static final String POST_enUS_StringFormatUrl = "%s/en-us/api/provider/tenant";
  public static final String DELETE_enUS_OpenApiUri = "/en-us/api/provider/tenant/{providerId}";
  public static final String DELETE_enUS_StringFormatUri = "/en-us/api/provider/tenant/%s";
  public static final String DELETE_enUS_StringFormatUrl = "%s/en-us/api/provider/tenant/%s";
  public static final String PUTImport_enUS_OpenApiUri = "/en-us/api/provider/tenant-import";
  public static final String PUTImport_enUS_StringFormatUri = "/en-us/api/provider/tenant-import";
  public static final String PUTImport_enUS_StringFormatUrl = "%s/en-us/api/provider/tenant-import";
  public static final String SearchPage_enUS_OpenApiUri = "/en-us/search/provider/tenant";
  public static final String SearchPage_enUS_StringFormatUri = "/en-us/search/provider/tenant";
  public static final String SearchPage_enUS_StringFormatUrl = "%s/en-us/search/provider/tenant";
  public static final String EditPage_enUS_OpenApiUri = "/en-us/edit/provider/tenant/{tenantResource}";
  public static final String EditPage_enUS_StringFormatUri = "/en-us/edit/provider/tenant/%s";
  public static final String EditPage_enUS_StringFormatUrl = "%s/en-us/edit/provider/tenant/%s";
  public static final String DELETEFilter_enUS_OpenApiUri = "/en-us/api/provider/tenant";
  public static final String DELETEFilter_enUS_StringFormatUri = "/en-us/api/provider/tenant";
  public static final String DELETEFilter_enUS_StringFormatUrl = "%s/en-us/api/provider/tenant";

  public static final String Icon = "<i class=\"{{ FONTAWESOME_STYLE }} fa-buildings\"></i>";

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
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.tenant.provider.TenantProvider&fq=entiteVar_enUS_indexed_string:providerName">Find the entity providerName in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _providerName(Wrap<String> w);

  public String getProviderName() {
    return providerName;
  }
  public void setProviderName(String o) {
    this.providerName = TenantProvider.staticSetProviderName(siteRequest_, o);
  }
  public static String staticSetProviderName(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected TenantProvider providerNameInit() {
    Wrap<String> providerNameWrap = new Wrap<String>().var("providerName");
    if(providerName == null) {
      _providerName(providerNameWrap);
      Optional.ofNullable(providerNameWrap.getO()).ifPresent(o -> {
        setProviderName(o);
      });
    }
    return (TenantProvider)this;
  }

  public static String staticSearchProviderName(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrProviderName(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqProviderName(SiteRequest siteRequest_, String o) {
    return TenantProvider.staticSearchProviderName(siteRequest_, TenantProvider.staticSetProviderName(siteRequest_, o)).toString();
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
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.tenant.provider.TenantProvider&fq=entiteVar_enUS_indexed_string:providerId">Find the entity providerId in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _providerId(Wrap<String> w);

  public String getProviderId() {
    return providerId;
  }
  public void setProviderId(String o) {
    this.providerId = TenantProvider.staticSetProviderId(siteRequest_, o);
  }
  public static String staticSetProviderId(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected TenantProvider providerIdInit() {
    Wrap<String> providerIdWrap = new Wrap<String>().var("providerId");
    if(providerId == null) {
      _providerId(providerIdWrap);
      Optional.ofNullable(providerIdWrap.getO()).ifPresent(o -> {
        setProviderId(o);
      });
    }
    return (TenantProvider)this;
  }

  public static String staticSearchProviderId(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrProviderId(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqProviderId(SiteRequest siteRequest_, String o) {
    return TenantProvider.staticSearchProviderId(siteRequest_, TenantProvider.staticSetProviderId(siteRequest_, o)).toString();
  }

  public String sqlProviderId() {
    return providerId;
  }

  public static String staticJsonProviderId(String providerId) {
    return providerId;
  }

	/////////////////
  // providerUrl //
	/////////////////


  /**
   *  The entity providerUrl
   *	 is defined as null before being initialized. 
   */
  @JsonProperty
  @JsonInclude(Include.NON_NULL)
  protected String providerUrl;

  /**
   * <br> The entity providerUrl
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.tenant.provider.TenantProvider&fq=entiteVar_enUS_indexed_string:providerUrl">Find the entity providerUrl in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _providerUrl(Wrap<String> w);

  public String getProviderUrl() {
    return providerUrl;
  }
  public void setProviderUrl(String o) {
    this.providerUrl = TenantProvider.staticSetProviderUrl(siteRequest_, o);
  }
  public static String staticSetProviderUrl(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected TenantProvider providerUrlInit() {
    Wrap<String> providerUrlWrap = new Wrap<String>().var("providerUrl");
    if(providerUrl == null) {
      _providerUrl(providerUrlWrap);
      Optional.ofNullable(providerUrlWrap.getO()).ifPresent(o -> {
        setProviderUrl(o);
      });
    }
    return (TenantProvider)this;
  }

  public static String staticSearchProviderUrl(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrProviderUrl(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqProviderUrl(SiteRequest siteRequest_, String o) {
    return TenantProvider.staticSearchProviderUrl(siteRequest_, TenantProvider.staticSetProviderUrl(siteRequest_, o)).toString();
  }

  public String sqlProviderUrl() {
    return providerUrl;
  }

  public static String staticJsonProviderUrl(String providerUrl) {
    return providerUrl;
  }

  //////////////
  // initDeep //
  //////////////

  public Future<TenantProviderGen<DEV>> promiseDeepTenantProvider(SiteRequest siteRequest_) {
    if(this.siteRequest_ == null)
      setSiteRequest_(siteRequest_);
    return promiseDeepTenantProvider();
  }

  public Future<TenantProviderGen<DEV>> promiseDeepTenantProvider() {
    Promise<TenantProviderGen<DEV>> promise = Promise.promise();
    Promise<Void> promise2 = Promise.promise();
    promiseTenantProvider(promise2);
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

  public Future<Void> promiseTenantProvider(Promise<Void> promise) {
    Future.future(a -> a.complete()).compose(a -> {
      Promise<Void> promise2 = Promise.promise();
      try {
        providerNameInit();
        providerIdInit();
        providerUrlInit();
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

  @Override public Future<? extends TenantProviderGen<DEV>> promiseDeepForClass(SiteRequest siteRequest_) {
    return promiseDeepTenantProvider(siteRequest_);
  }

  /////////////////
  // siteRequest //
  /////////////////

  public void siteRequestTenantProvider(SiteRequest siteRequest_) {
      super.siteRequestBaseModel(siteRequest_);
  }

  public void siteRequestForClass(SiteRequest siteRequest_) {
    siteRequestTenantProvider(siteRequest_);
  }

  /////////////
  // obtain //
  /////////////

  @Override public Object obtainForClass(String var) {
    String[] vars = StringUtils.split(var, ".");
    Object o = null;
    for(String v : vars) {
      if(o == null)
        o = obtainTenantProvider(v);
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
  public Object obtainTenantProvider(String var) {
    TenantProvider oTenantProvider = (TenantProvider)this;
    switch(var) {
      case "providerName":
        return oTenantProvider.providerName;
      case "providerId":
        return oTenantProvider.providerId;
      case "providerUrl":
        return oTenantProvider.providerUrl;
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
        o = relateTenantProvider(v, val);
      else if(o instanceof BaseModel) {
        BaseModel baseModel = (BaseModel)o;
        o = baseModel.relateForClass(v, val);
      }
    }
    return o != null;
  }
  public Object relateTenantProvider(String var, Object val) {
    TenantProvider oTenantProvider = (TenantProvider)this;
    switch(var) {
      default:
        return super.relateBaseModel(var, val);
    }
  }

  ///////////////
  // staticSet //
  ///////////////

  public static Object staticSetForClass(String entityVar, SiteRequest siteRequest_, String v, TenantProvider o) {
    return staticSetTenantProvider(entityVar,  siteRequest_, v, o);
  }
  public static Object staticSetTenantProvider(String entityVar, SiteRequest siteRequest_, String v, TenantProvider o) {
    switch(entityVar) {
    case "providerName":
      return TenantProvider.staticSetProviderName(siteRequest_, v);
    case "providerId":
      return TenantProvider.staticSetProviderId(siteRequest_, v);
    case "providerUrl":
      return TenantProvider.staticSetProviderUrl(siteRequest_, v);
      default:
        return BaseModel.staticSetBaseModel(entityVar,  siteRequest_, v, o);
    }
  }

  //////////////////
  // authorization //
  //////////////////

  public static Future<AsyncResult<HttpResponse<Buffer>>> authorizationTenantProvider(SiteRequest siteRequest, WebClient webClient, Boolean classPublicRead, String classApiMethod, String classApiMethodMethod, String scope) {
    Promise<AsyncResult<HttpResponse<Buffer>>> promise = Promise.promise();
    try {
      JsonObject config = siteRequest.getConfig();
      SiteRequest siteRequest2 = siteRequest.copy();
      String providerId = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("providerId");
      String TENANTPROVIDER = siteRequest.getServiceRequest().getParams().getJsonObject("path").getString("TENANTPROVIDER");
      List<String> groups = Optional.ofNullable(siteRequest.getGroups()).orElse(new ArrayList<>());
      MultiMap form = MultiMap.caseInsensitiveMultiMap();
      form.add("grant_type", "urn:ietf:params:oauth:grant-type:uma-ticket");
      form.add("audience", config.getString(ComputateConfigKeys.AUTH_CLIENT));
      form.add("response_mode", "permissions");
      form.add("permission", String.format("%s#%s", TenantProvider.CLASS_AUTH_RESOURCE, "GET"));
      form.add("permission", String.format("%s#%s", TenantProvider.CLASS_AUTH_RESOURCE, "POST"));
      form.add("permission", String.format("%s#%s", TenantProvider.CLASS_AUTH_RESOURCE, "PATCH"));
      form.add("permission", String.format("%s#%s", TenantProvider.CLASS_AUTH_RESOURCE, "DELETE"));
      form.add("permission", String.format("%s#%s", TenantProvider.CLASS_AUTH_RESOURCE, "Admin"));
      form.add("permission", String.format("%s#%s", TenantProvider.CLASS_AUTH_RESOURCE, "SuperAdmin"));
      if(providerId != null)
        form.add("permission", String.format("%s-%s#%s", TenantProvider.CLASS_AUTH_RESOURCE, providerId, scope));
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
      LOG.error("Error while querying the tenant intent", ex);
      promise.fail(ex);
    }
    return promise.future();
  }

  public static Future<SiteRequest> authorizationScopesTenantProvider(AsyncResult<HttpResponse<Buffer>> authorizationDecisionResponse, SiteRequest siteRequest2, WebClient webClient, Boolean classPublicRead, String classApiMethod, String classApiMethodMethod, String scope) {
    Promise<SiteRequest> promise = Promise.promise();
    try {
      siteRequest2.initDeepSiteRequest();
      HttpResponse<Buffer> authorizationDecision = authorizationDecisionResponse.result();
      JsonArray authorizationDecisionBody = authorizationDecisionResponse.failed() ? new JsonArray() : authorizationDecision.bodyAsJsonArray();
      JsonArray scopes = authorizationDecisionBody.stream().map(o -> (JsonObject)o).filter(o -> "TENANTPROVIDER".equals(o.getString("rsname"))).findFirst().map(decision -> ((JsonObject)decision).getJsonArray("scopes")).orElse(new JsonArray());
    } catch(Throwable ex) {
      LOG.error("Error while querying the tenant intent", ex);
      promise.fail(ex);
    }
    return promise.future();
  }

  //////////////////
  // staticSearch //
  //////////////////

  public static Future<TenantProvider> fqTenantProvider(SiteRequest siteRequest, String var, Object val) {
    Promise<TenantProvider> promise = Promise.promise();
    try {
      if(val == null) {
        promise.complete();
      } else {
        SearchList<TenantProvider> searchList = new SearchList<TenantProvider>();
        searchList.setStore(true);
        searchList.q("*:*");
        searchList.setC(TenantProvider.class);
        searchList.fq(String.format("%s:", TenantProvider.varIndexedTenantProvider(var)) + SearchTool.escapeQueryChars(val.toString()));
        searchList.promiseDeepForClass(siteRequest).onSuccess(a -> {
          try {
            promise.complete(searchList.getList().stream().findFirst().orElse(null));
          } catch(Throwable ex) {
            LOG.error("Error while querying the tenant intent", ex);
            promise.fail(ex);
          }
        }).onFailure(ex -> {
          LOG.error("Error while querying the tenant intent", ex);
          promise.fail(ex);
        });
      }
    } catch(Throwable ex) {
      LOG.error("Error while querying the tenant intent", ex);
      promise.fail(ex);
    }
    return promise.future();
  }

  public static Object staticSearchForClass(String entityVar, SiteRequest siteRequest_, Object o) {
    return staticSearchTenantProvider(entityVar,  siteRequest_, o);
  }
  public static Object staticSearchTenantProvider(String entityVar, SiteRequest siteRequest_, Object o) {
    switch(entityVar) {
    case "providerName":
      return TenantProvider.staticSearchProviderName(siteRequest_, (String)o);
    case "providerId":
      return TenantProvider.staticSearchProviderId(siteRequest_, (String)o);
    case "providerUrl":
      return TenantProvider.staticSearchProviderUrl(siteRequest_, (String)o);
      default:
        return BaseModel.staticSearchBaseModel(entityVar,  siteRequest_, o);
    }
  }

  ///////////////////
  // staticSearchStr //
  ///////////////////

  public static String staticSearchStrForClass(String entityVar, SiteRequest siteRequest_, Object o) {
    return staticSearchStrTenantProvider(entityVar,  siteRequest_, o);
  }
  public static String staticSearchStrTenantProvider(String entityVar, SiteRequest siteRequest_, Object o) {
    switch(entityVar) {
    case "providerName":
      return TenantProvider.staticSearchStrProviderName(siteRequest_, (String)o);
    case "providerId":
      return TenantProvider.staticSearchStrProviderId(siteRequest_, (String)o);
    case "providerUrl":
      return TenantProvider.staticSearchStrProviderUrl(siteRequest_, (String)o);
      default:
        return BaseModel.staticSearchStrBaseModel(entityVar,  siteRequest_, o);
    }
  }

  //////////////////
  // staticSearchFq //
  //////////////////

  public static String staticSearchFqForClass(String entityVar, SiteRequest siteRequest_, String o) {
    return staticSearchFqTenantProvider(entityVar,  siteRequest_, o);
  }
  public static String staticSearchFqTenantProvider(String entityVar, SiteRequest siteRequest_, String o) {
    switch(entityVar) {
    case "providerName":
      return TenantProvider.staticSearchFqProviderName(siteRequest_, o);
    case "providerId":
      return TenantProvider.staticSearchFqProviderId(siteRequest_, o);
    case "providerUrl":
      return TenantProvider.staticSearchFqProviderUrl(siteRequest_, o);
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
          o = persistTenantProvider(v, val);
        else if(o instanceof BaseModel) {
          BaseModel oBaseModel = (BaseModel)o;
          o = oBaseModel.persistForClass(v, val);
        }
      }
    }
    return o != null;
  }
  public Object persistTenantProvider(String var, Object val) {
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
      } else if("providerurl".equals(varLower)) {
        if(val instanceof String) {
          setProviderUrl((String)val);
        }
        saves.add("providerUrl");
        return val;
    } else {
      return super.persistBaseModel(var, val);
    }
  }

  /////////////
  // populate //
  /////////////

  @Override public void populateForClass(SolrResponse.Doc doc) {
    populateTenantProvider(doc);
  }
  public void populateTenantProvider(SolrResponse.Doc doc) {
    TenantProvider oTenantProvider = (TenantProvider)this;
    saves = Optional.ofNullable((ArrayList<String>)doc.get("saves_docvalues_strings")).orElse(new ArrayList<String>());
    if(saves != null) {

      if(saves.contains("providerName")) {
        String providerName = (String)doc.get("providerName_docvalues_string");
        if(providerName != null)
          oTenantProvider.setProviderName(providerName);
      }

      if(saves.contains("providerId")) {
        String providerId = (String)doc.get("providerId_docvalues_string");
        if(providerId != null)
          oTenantProvider.setProviderId(providerId);
      }

      if(saves.contains("providerUrl")) {
        String providerUrl = (String)doc.get("providerUrl_docvalues_string");
        if(providerUrl != null)
          oTenantProvider.setProviderUrl(providerUrl);
      }
    }

    super.populateBaseModel(doc);
  }

  public void indexTenantProvider(JsonObject doc) {
    if(providerName != null) {
      doc.put("providerName_docvalues_string", providerName);
    }
    if(providerId != null) {
      doc.put("providerId_docvalues_string", providerId);
    }
    if(providerUrl != null) {
      doc.put("providerUrl_docvalues_string", providerUrl);
    }
    super.indexBaseModel(doc);

	}

  public static String varStoredTenantProvider(String entityVar) {
    switch(entityVar) {
      case "providerName":
        return "providerName_docvalues_string";
      case "providerId":
        return "providerId_docvalues_string";
      case "providerUrl":
        return "providerUrl_docvalues_string";
      default:
        return BaseModel.varStoredBaseModel(entityVar);
    }
  }

  public static String varIndexedTenantProvider(String entityVar) {
    switch(entityVar) {
      case "providerName":
        return "providerName_docvalues_string";
      case "providerId":
        return "providerId_docvalues_string";
      case "providerUrl":
        return "providerUrl_docvalues_string";
      default:
        return BaseModel.varIndexedBaseModel(entityVar);
    }
  }

  public static String searchVarTenantProvider(String searchVar) {
    switch(searchVar) {
      case "providerName_docvalues_string":
        return "providerName";
      case "providerId_docvalues_string":
        return "providerId";
      case "providerUrl_docvalues_string":
        return "providerUrl";
      default:
        return BaseModel.searchVarBaseModel(searchVar);
    }
  }

  public static String varSearchTenantProvider(String entityVar) {
    switch(entityVar) {
      default:
        return BaseModel.varSearchBaseModel(entityVar);
    }
  }

  public static String varSuggestedTenantProvider(String entityVar) {
    switch(entityVar) {
      default:
        return BaseModel.varSuggestedBaseModel(entityVar);
    }
  }

  /////////////
  // store //
  /////////////

  @Override public void storeForClass(SolrResponse.Doc doc) {
    storeTenantProvider(doc);
  }
  public void storeTenantProvider(SolrResponse.Doc doc) {
    TenantProvider oTenantProvider = (TenantProvider)this;
    SiteRequest siteRequest = oTenantProvider.getSiteRequest_();

    oTenantProvider.setProviderName(Optional.ofNullable(doc.get("providerName_docvalues_string")).map(v -> v.toString()).orElse(null));
    oTenantProvider.setProviderId(Optional.ofNullable(doc.get("providerId_docvalues_string")).map(v -> v.toString()).orElse(null));
    oTenantProvider.setProviderUrl(Optional.ofNullable(doc.get("providerUrl_docvalues_string")).map(v -> v.toString()).orElse(null));

    super.storeBaseModel(doc);
  }

  //////////////////
  // apiRequest //
  //////////////////

  public void apiRequestTenantProvider() {
    ApiRequest apiRequest = Optional.ofNullable(siteRequest_).map(r -> r.getApiRequest_()).orElse(null);
    Object o = Optional.ofNullable(apiRequest).map(ApiRequest::getOriginal).orElse(null);
    if(o != null && o instanceof TenantProvider) {
      TenantProvider original = (TenantProvider)o;
      if(!Objects.equals(providerName, original.getProviderName()))
        apiRequest.addVars("providerName");
      if(!Objects.equals(providerId, original.getProviderId()))
        apiRequest.addVars("providerId");
      if(!Objects.equals(providerUrl, original.getProviderUrl()))
        apiRequest.addVars("providerUrl");
      super.apiRequestBaseModel();
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
    sb.append(Optional.ofNullable(providerUrl).map(v -> "providerUrl: \"" + v + "\"\n" ).orElse(""));
    return sb.toString();
  }

  public static final String CLASS_SIMPLE_NAME = "TenantProvider";
  public static final String CLASS_CANONICAL_NAME = "org.computate.dcm.model.eda.tenant.provider.TenantProvider";
  public static final String CLASS_AUTH_RESOURCE = "TENANTPROVIDER";
  public static final String CLASS_API_ADDRESS_TenantProvider = "dcm-enUS-TenantProvider";
  public static String getClassApiAddress() {
    return CLASS_API_ADDRESS_TenantProvider;
  }
  public static final String VAR_providerName = "providerName";
  public static final String SET_providerName = "setProviderName";
  public static final String VAR_providerId = "providerId";
  public static final String SET_providerId = "setProviderId";
  public static final String VAR_providerUrl = "providerUrl";
  public static final String SET_providerUrl = "setProviderUrl";

  public static List<String> varsQForClass() {
    return TenantProvider.varsQTenantProvider(new ArrayList<String>());
  }
  public static List<String> varsQTenantProvider(List<String> vars) {
    BaseModel.varsQBaseModel(vars);
    return vars;
  }

  public static List<String> varsFqForClass() {
    return TenantProvider.varsFqTenantProvider(new ArrayList<String>());
  }
  public static List<String> varsFqTenantProvider(List<String> vars) {
    vars.add(VAR_providerName);
    vars.add(VAR_providerId);
    BaseModel.varsFqBaseModel(vars);
    return vars;
  }

  public static List<String> varsRangeForClass() {
    return TenantProvider.varsRangeTenantProvider(new ArrayList<String>());
  }
  public static List<String> varsRangeTenantProvider(List<String> vars) {
    BaseModel.varsRangeBaseModel(vars);
    return vars;
  }

  public static final String DISPLAY_NAME_providerName = "tenant provider name";
  public static final String DISPLAY_NAME_providerId = "tenant provider ID";
  public static final String DISPLAY_NAME_providerUrl = "provider URL";

  @Override
  public String idForClass() {
    return providerId;
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
    return TenantProvider.NameAdjectiveSingular_enUS;
  }

  @Override
  public String enUSStringFormatUrlEditPageForClass() {
    return "%s/en-us/edit/provider/tenant/%s";
  }

  public static String varJson(String var, Boolean patch) {
    return TenantProvider.varJsonTenantProvider(var, patch);
  }
  public static String varJsonTenantProvider(String var, Boolean patch) {
    switch(var) {
    case VAR_providerName:
      return patch ? SET_providerName : VAR_providerName;
    case VAR_providerId:
      return patch ? SET_providerId : VAR_providerId;
    case VAR_providerUrl:
      return patch ? SET_providerUrl : VAR_providerUrl;
    default:
      return BaseModel.varJsonBaseModel(var, patch);
    }
  }

  public static String displayNameForClass(String var) {
    return TenantProvider.displayNameTenantProvider(var);
  }
  public static String displayNameTenantProvider(String var) {
    switch(var) {
    case VAR_providerName:
      return DISPLAY_NAME_providerName;
    case VAR_providerId:
      return DISPLAY_NAME_providerId;
    case VAR_providerUrl:
      return DISPLAY_NAME_providerUrl;
    default:
      return BaseModel.displayNameBaseModel(var);
    }
  }

  public static String descriptionTenantProvider(String var) {
    if(var == null)
      return null;
    switch(var) {
    case VAR_providerName:
      return "The name of this tenant provider";
    case VAR_providerId:
      return "The ID of this tenant provider. By default, this will be auto-generated based on the tenant provider name, converting non-alphanumeric characters to hyphens, all lowercase. ";
    case VAR_providerUrl:
      return "The URL to the DCM provider application. ";
      default:
        return BaseModel.descriptionBaseModel(var);
    }
  }

  public static String classSimpleNameTenantProvider(String var) {
    switch(var) {
    case VAR_providerName:
      return "String";
    case VAR_providerId:
      return "String";
    case VAR_providerUrl:
      return "String";
      default:
        return BaseModel.classSimpleNameBaseModel(var);
    }
  }

  public static Integer htmColumnTenantProvider(String var) {
    switch(var) {
    case VAR_providerName:
      return 1;
      default:
        return BaseModel.htmColumnBaseModel(var);
    }
  }

  public static Integer htmRowTenantProvider(String var) {
    switch(var) {
    case VAR_providerName:
      return 10;
      default:
        return BaseModel.htmRowBaseModel(var);
    }
  }

  public static Integer htmCellTenantProvider(String var) {
    switch(var) {
    case VAR_providerName:
      return 0;
      default:
        return BaseModel.htmCellBaseModel(var);
    }
  }

  public static Integer lengthMinTenantProvider(String var) {
    switch(var) {
      default:
        return BaseModel.lengthMinBaseModel(var);
    }
  }

  public static Integer lengthMaxTenantProvider(String var) {
    switch(var) {
      default:
        return BaseModel.lengthMaxBaseModel(var);
    }
  }

  public static Integer maxTenantProvider(String var) {
    switch(var) {
      default:
        return BaseModel.maxBaseModel(var);
    }
  }

  public static Integer minTenantProvider(String var) {
    switch(var) {
      default:
        return BaseModel.minBaseModel(var);
    }
  }
}
