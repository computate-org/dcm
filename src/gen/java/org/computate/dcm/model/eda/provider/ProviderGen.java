package org.computate.dcm.model.eda.provider;

import org.computate.search.wrap.Wrap;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Strings;
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
 * <li><p>
 *   You can add a class comment <kbd><b>Api: true</b></kbd> if you wish to GET, POST, PATCH or PUT these  objects in a RESTful API. 
 * </p>
 * </li><li>You can add a class comment "{@inheritDoc}" if you wish to inherit the helpful inherited class comments from class ProviderGen into the class Provider. 
 * </li>
 * <h3>About the Provider class and it's generated class ProviderGen&lt;BaseModel&gt;: </h3>extends ProviderGen
 * <p>
 * This Java class extends a generated Java class ProviderGen built by the <a href="https://github.com/computate-org/computate">https://github.com/computate-org/computate</a> project. 
 * Whenever this Java class is modified or touched, the watch service installed as described in the README, indexes all the information about this Java class in a local Apache Solr Search Engine. 
 * If you are running the service, you can see the indexed data about this Java Class here: 
 * </p>
 * <p><a href="https://solr.apps-crc.testing/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.provider.Provider">Find the class Provider in Solr. </a></p>
 * <p>
 * The extended class ending with "Gen" did not exist at first, but was automatically created by the same watch service based on the data retrieved from the local Apache Server search engine. 
 * The extended class contains many generated fields, getters, setters, initialization code, and helper methods to help build a website and API fast, reactive, and scalable. 
 * </p>
 * extends ProviderGen<BaseModel>
 * <p>This <code>class Provider extends ProviderGen&lt;BaseModel&gt;</code>, which means it extends a newly generated ProviderGen. 
 * The generated <code>class ProviderGen extends BaseModel</code> which means that Provider extends ProviderGen which extends BaseModel. 
 * This generated inheritance is a powerful feature that allows a lot of boiler plate code to be created for you automatically while still preserving inheritance through the power of Java Generic classes. 
 * </p>
 * <h2>
 *   Api: true
 * </h2>
 * <h2>ApiTag.enUS: true</h2>
 * <h2>ApiUri.enUS: null</h2>
 * <h2>Color: null</h2>
 * <h2>Indexed: true</h2>
 * <p>This class contains a comment <kbd><b>Indexed: true</b></kbd>, which means this class will be indexed in the search engine. 
 * Every protected void method that begins with "_" that is marked to be searched with a comment like "Indexed: true", "Stored: true", or "DocValues: true" will be indexed in the search engine. 
 * </p>
 * <h2>{@inheritDoc}</h2>
 * <p>By adding a class comment "{@inheritDoc}", the Provider class will inherit the helpful inherited class comments from the super class ProviderGen. 
 * </p>
 * <h2>
 *   Rows: 10
 * </h2>
 * <p>This class contains a comment <kbd><b>Rows: 10</b></kbd>, which means the provider API will return a default of 10 results instead of 10 by default. 
 * Each API has built in pagination of the search results to ensure a user can query all the data a page at a time without running the application out of memory. 
 * </p>
 * <p>
 *   You can add a class comment <kbd><b>Rows: 100</b></kbd> if you wish for the provider API to return more or less than 10 results by default. 
 *   In this case, the API will return 100 results from the API instead of 10 by default. 
 *   Each API has built in pagination of the search results to ensure a user can query all the data a page at a time without running the application out of memory. 
 * </p>
 * <h2>
 *   Order: 150
 * </h2>
 * <p>
 *   This class contains a comment <kbd><b>Order: 150</b></kbd>, 
 *   which means this class will be sorted by the given number 150 
 *   ascending when code that relates to multiple classes at the same time is generated. 
 * </p>
 * <h2>SqlOrder: 150</h2>
 * <p>This class contains a comment <kbd><b>SqlOrder: 150</b></kbd>, which means this class will be sorted by the given number 150 ascending when SQL code to create and drop the tables is generated. 
 * </p>
 * <h2>Model: true</h2>
 * <p>This class contains a comment <kbd><b>Model: true</b></kbd>, which means this class will be stored in the database. 
 * Every protected void method that begins with "_" that contains a "Persist: true" comment will be a persisted field in the database table. 
 * </p>
 * <h2>Page: true</h2>
 * <h2>SuperPage.enUS: null</h2>
 * <h2>Promise: true</h2>
 * <p>
 *   This class contains a comment <kbd><b>Promise: true</b></kbd>
 *   Sometimes a Java class must be initialized asynchronously when it involves calling a blocking API. 
 *   This means that the Provider Java class has promiseDeep methods which must be initialized asynchronously as a Vert.x Promise  instead of initDeep methods which are a simple non-asynchronous method. 
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
 * <h2>AName.enUS: a provider</h2>
 * <p>This class contains a comment <kbd><b>AName.enUS: a provider</b></kbd>, which identifies the language context to describe a Provider as "a provider". 
 * </p>
 * <p>
 * Delete the class Provider in Solr: 
 * <pre>
 * curl -k 'https://solr.apps-crc.testing/solr/computate/update?commitWithin=1000&amp;overwrite=true&amp;wt=json' -X POST -H 'Content-type: text/xml' -u "admin:$(oc -n solr get secret/solr-solrcloud-security-bootstrap -o jsonpath={.data.admin} | base64 -d)" --data-raw '&lt;delete&gt;&lt;query&gt;classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.provider.Provider&lt;/query&gt;&lt;/delete&gt;'
 * </pre>
 * </p>
 * <p>
 * Delete  the package org.computate.dcm.model.eda.provider in Solr: 
 * <pre>
 * curl -k 'https://solr.apps-crc.testing/solr/computate/update?commitWithin=1000&amp;overwrite=true&amp;wt=json' -X POST -H 'Content-type: text/xml' -u "admin:$(oc -n solr get secret/solr-solrcloud-security-bootstrap -o jsonpath={.data.admin} | base64 -d)" --data-raw '&lt;delete&gt;&lt;query&gt;classeNomEnsemble_enUS_indexed_string:org.computate.dcm.model.eda.provider&lt;/query&gt;&lt;/delete&gt;'
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
public abstract class ProviderGen<DEV> extends BaseModel {
  protected static final Logger LOG = LoggerFactory.getLogger(Provider.class);

  public static final String Description_enUS = "A provider for requesting other DCM models. ";
  public static final String AName_enUS = "a provider";
  public static final String This_enUS = "this ";
  public static final String ThisName_enUS = "this provider";
  public static final String A_enUS = "a ";
  public static final String TheName_enUS = "the provider";
  public static final String SingularName_enUS = "provider";
  public static final String PluralName_enUS = "providers";
  public static final String NameActual_enUS = "current provider";
  public static final String AllName_enUS = "all providers";
  public static final String SearchAllNameBy_enUS = "search providers by ";
  public static final String SearchAllName_enUS = "search providers";
  public static final String Title_enUS = "providers";
  public static final String ThePluralName_enUS = "the providers";
  public static final String NoNameFound_enUS = "no provider found";
  public static final String OfName_enUS = "of provider";
  public static final String ANameAdjective_enUS = "a provider";
  public static final String NameAdjectiveSingular_enUS = "provider";
  public static final String NameAdjectivePlural_enUS = "providers";

  public static final String Icon = "<i class=\"{{ FONTAWESOME_STYLE }} fa-person-dolly\"></i>";

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
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.provider.Provider&fq=entiteVar_enUS_indexed_string:providerName">Find the entity providerName in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _providerName(Wrap<String> w);

  public String getProviderName() {
    return providerName;
  }
  public void setProviderName(String o) {
    this.providerName = Provider.staticSetProviderName(siteRequest_, o);
  }
  public static String staticSetProviderName(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected Provider providerNameInit() {
    Wrap<String> providerNameWrap = new Wrap<String>().var("providerName");
    if(providerName == null) {
      _providerName(providerNameWrap);
      Optional.ofNullable(providerNameWrap.getO()).ifPresent(o -> {
        setProviderName(o);
      });
    }
    return (Provider)this;
  }

  public static String staticSearchProviderName(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrProviderName(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqProviderName(SiteRequest siteRequest_, String o) {
    return Provider.staticSearchProviderName(siteRequest_, Provider.staticSetProviderName(siteRequest_, o)).toString();
  }

  public String sqlProviderName() {
    return providerName;
  }

  public static String staticJsonProviderName(String providerName) {
    return providerName;
  }

	///////////////////////
  // requestedClientId //
	///////////////////////


  /**
   *  The entity requestedClientId
   *	 is defined as null before being initialized. 
   */
  @JsonProperty
  @JsonInclude(Include.NON_NULL)
  protected String requestedClientId;

  /**
   * <br> The entity requestedClientId
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.provider.Provider&fq=entiteVar_enUS_indexed_string:requestedClientId">Find the entity requestedClientId in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _requestedClientId(Wrap<String> w);

  public String getRequestedClientId() {
    return requestedClientId;
  }
  public void setRequestedClientId(String o) {
    this.requestedClientId = Provider.staticSetRequestedClientId(siteRequest_, o);
  }
  public static String staticSetRequestedClientId(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected Provider requestedClientIdInit() {
    Wrap<String> requestedClientIdWrap = new Wrap<String>().var("requestedClientId");
    if(requestedClientId == null) {
      _requestedClientId(requestedClientIdWrap);
      Optional.ofNullable(requestedClientIdWrap.getO()).ifPresent(o -> {
        setRequestedClientId(o);
      });
    }
    return (Provider)this;
  }

  public static String staticSearchRequestedClientId(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrRequestedClientId(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqRequestedClientId(SiteRequest siteRequest_, String o) {
    return Provider.staticSearchRequestedClientId(siteRequest_, Provider.staticSetRequestedClientId(siteRequest_, o)).toString();
  }

  public String sqlRequestedClientId() {
    return requestedClientId;
  }

  public static String staticJsonRequestedClientId(String requestedClientId) {
    return requestedClientId;
  }

	//////////////////////////////////
  // requestedEnvironmentVariable //
	//////////////////////////////////


  /**
   *  The entity requestedEnvironmentVariable
   *	 is defined as null before being initialized. 
   */
  @JsonProperty
  @JsonInclude(Include.NON_NULL)
  protected String requestedEnvironmentVariable;

  /**
   * <br> The entity requestedEnvironmentVariable
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.provider.Provider&fq=entiteVar_enUS_indexed_string:requestedEnvironmentVariable">Find the entity requestedEnvironmentVariable in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _requestedEnvironmentVariable(Wrap<String> w);

  public String getRequestedEnvironmentVariable() {
    return requestedEnvironmentVariable;
  }
  public void setRequestedEnvironmentVariable(String o) {
    this.requestedEnvironmentVariable = Provider.staticSetRequestedEnvironmentVariable(siteRequest_, o);
  }
  public static String staticSetRequestedEnvironmentVariable(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected Provider requestedEnvironmentVariableInit() {
    Wrap<String> requestedEnvironmentVariableWrap = new Wrap<String>().var("requestedEnvironmentVariable");
    if(requestedEnvironmentVariable == null) {
      _requestedEnvironmentVariable(requestedEnvironmentVariableWrap);
      Optional.ofNullable(requestedEnvironmentVariableWrap.getO()).ifPresent(o -> {
        setRequestedEnvironmentVariable(o);
      });
    }
    return (Provider)this;
  }

  public static String staticSearchRequestedEnvironmentVariable(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrRequestedEnvironmentVariable(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqRequestedEnvironmentVariable(SiteRequest siteRequest_, String o) {
    return Provider.staticSearchRequestedEnvironmentVariable(siteRequest_, Provider.staticSetRequestedEnvironmentVariable(siteRequest_, o)).toString();
  }

  public String sqlRequestedEnvironmentVariable() {
    return requestedEnvironmentVariable;
  }

  public static String staticJsonRequestedEnvironmentVariable(String requestedEnvironmentVariable) {
    return requestedEnvironmentVariable;
  }

	/////////////////////////////////
  // providerRequestInstructions //
	/////////////////////////////////


  /**
   *  The entity providerRequestInstructions
   *	 is defined as null before being initialized. 
   */
  @JsonProperty
  @JsonInclude(Include.NON_NULL)
  protected String providerRequestInstructions;

  /**
   * <br> The entity providerRequestInstructions
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.provider.Provider&fq=entiteVar_enUS_indexed_string:providerRequestInstructions">Find the entity providerRequestInstructions in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _providerRequestInstructions(Wrap<String> w);

  public String getProviderRequestInstructions() {
    return providerRequestInstructions;
  }
  public void setProviderRequestInstructions(String o) {
    this.providerRequestInstructions = Provider.staticSetProviderRequestInstructions(siteRequest_, o);
  }
  public static String staticSetProviderRequestInstructions(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected Provider providerRequestInstructionsInit() {
    Wrap<String> providerRequestInstructionsWrap = new Wrap<String>().var("providerRequestInstructions");
    if(providerRequestInstructions == null) {
      _providerRequestInstructions(providerRequestInstructionsWrap);
      Optional.ofNullable(providerRequestInstructionsWrap.getO()).ifPresent(o -> {
        setProviderRequestInstructions(o);
      });
    }
    return (Provider)this;
  }

  public static String staticSearchProviderRequestInstructions(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrProviderRequestInstructions(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqProviderRequestInstructions(SiteRequest siteRequest_, String o) {
    return Provider.staticSearchProviderRequestInstructions(siteRequest_, Provider.staticSetProviderRequestInstructions(siteRequest_, o)).toString();
  }

  public String sqlProviderRequestInstructions() {
    return providerRequestInstructions;
  }

  public static String staticJsonProviderRequestInstructions(String providerRequestInstructions) {
    return providerRequestInstructions;
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
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.provider.Provider&fq=entiteVar_enUS_indexed_string:providerUrl">Find the entity providerUrl in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _providerUrl(Wrap<String> w);

  public String getProviderUrl() {
    return providerUrl;
  }
  public void setProviderUrl(String o) {
    this.providerUrl = Provider.staticSetProviderUrl(siteRequest_, o);
  }
  public static String staticSetProviderUrl(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected Provider providerUrlInit() {
    Wrap<String> providerUrlWrap = new Wrap<String>().var("providerUrl");
    if(providerUrl == null) {
      _providerUrl(providerUrlWrap);
      Optional.ofNullable(providerUrlWrap.getO()).ifPresent(o -> {
        setProviderUrl(o);
      });
    }
    return (Provider)this;
  }

  public static String staticSearchProviderUrl(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrProviderUrl(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqProviderUrl(SiteRequest siteRequest_, String o) {
    return Provider.staticSearchProviderUrl(siteRequest_, Provider.staticSetProviderUrl(siteRequest_, o)).toString();
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

  public Future<ProviderGen<DEV>> promiseDeepProvider(SiteRequest siteRequest_) {
    if(this.siteRequest_ == null)
      setSiteRequest_(siteRequest_);
    return promiseDeepProvider();
  }

  public Future<ProviderGen<DEV>> promiseDeepProvider() {
    Promise<ProviderGen<DEV>> promise = Promise.promise();
    Promise<Void> promise2 = Promise.promise();
    promiseProvider(promise2);
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

  public Future<Void> promiseProvider(Promise<Void> promise) {
    Future.future(a -> a.complete()).compose(a -> {
      Promise<Void> promise2 = Promise.promise();
      try {
        providerNameInit();
        requestedClientIdInit();
        requestedEnvironmentVariableInit();
        providerRequestInstructionsInit();
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

  @Override public Future<? extends ProviderGen<DEV>> promiseDeepForClass(SiteRequest siteRequest_) {
    return promiseDeepProvider(siteRequest_);
  }

  /////////////////
  // siteRequest //
  /////////////////

  public void siteRequestProvider(SiteRequest siteRequest_) {
      super.siteRequestBaseModel(siteRequest_);
  }

  public void siteRequestForClass(SiteRequest siteRequest_) {
    siteRequestProvider(siteRequest_);
  }

  /////////////
  // obtain //
  /////////////

  @Override public Object obtainForClass(String var) {
    String[] vars = StringUtils.split(var, ".");
    Object o = null;
    for(String v : vars) {
      if(o == null)
        o = obtainProvider(v);
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
  public Object obtainProvider(String var) {
    Provider oProvider = (Provider)this;
    switch(var) {
      case "providerName":
        return oProvider.providerName;
      case "requestedClientId":
        return oProvider.requestedClientId;
      case "requestedEnvironmentVariable":
        return oProvider.requestedEnvironmentVariable;
      case "providerRequestInstructions":
        return oProvider.providerRequestInstructions;
      case "providerUrl":
        return oProvider.providerUrl;
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
        o = relateProvider(v, val);
      else if(o instanceof BaseModel) {
        BaseModel baseModel = (BaseModel)o;
        o = baseModel.relateForClass(v, val);
      }
    }
    return o != null;
  }
  public Object relateProvider(String var, Object val) {
    Provider oProvider = (Provider)this;
    switch(var) {
      default:
        return super.relateBaseModel(var, val);
    }
  }

  ///////////////
  // staticSet //
  ///////////////

  public static Object staticSetForClass(String entityVar, SiteRequest siteRequest_, String v, Provider o) {
    return staticSetProvider(entityVar,  siteRequest_, v, o);
  }
  public static Object staticSetProvider(String entityVar, SiteRequest siteRequest_, String v, Provider o) {
    switch(entityVar) {
    case "providerName":
      return Provider.staticSetProviderName(siteRequest_, v);
    case "requestedClientId":
      return Provider.staticSetRequestedClientId(siteRequest_, v);
    case "requestedEnvironmentVariable":
      return Provider.staticSetRequestedEnvironmentVariable(siteRequest_, v);
    case "providerRequestInstructions":
      return Provider.staticSetProviderRequestInstructions(siteRequest_, v);
    case "providerUrl":
      return Provider.staticSetProviderUrl(siteRequest_, v);
      default:
        return BaseModel.staticSetBaseModel(entityVar,  siteRequest_, v, o);
    }
  }

  //////////////////
  // staticSearch //
  //////////////////

  public static Future<Provider> fqProvider(SiteRequest siteRequest, String var, Object val) {
    Promise<Provider> promise = Promise.promise();
    try {
      if(val == null) {
        promise.complete();
      } else {
        SearchList<Provider> searchList = new SearchList<Provider>();
        searchList.setStore(true);
        searchList.q("*:*");
        searchList.setC(Provider.class);
        searchList.fq(String.format("%s:", Provider.varIndexedProvider(var)) + SearchTool.escapeQueryChars(val.toString()));
        searchList.promiseDeepForClass(siteRequest).onSuccess(a -> {
          try {
            promise.complete(searchList.getList().stream().findFirst().orElse(null));
          } catch(Throwable ex) {
            LOG.error("Error while querying the provider", ex);
            promise.fail(ex);
          }
        }).onFailure(ex -> {
          LOG.error("Error while querying the provider", ex);
          promise.fail(ex);
        });
      }
    } catch(Throwable ex) {
      LOG.error("Error while querying the provider", ex);
      promise.fail(ex);
    }
    return promise.future();
  }

  public static Object staticSearchForClass(String entityVar, SiteRequest siteRequest_, Object o) {
    return staticSearchProvider(entityVar,  siteRequest_, o);
  }
  public static Object staticSearchProvider(String entityVar, SiteRequest siteRequest_, Object o) {
    switch(entityVar) {
    case "providerName":
      return Provider.staticSearchProviderName(siteRequest_, (String)o);
    case "requestedClientId":
      return Provider.staticSearchRequestedClientId(siteRequest_, (String)o);
    case "requestedEnvironmentVariable":
      return Provider.staticSearchRequestedEnvironmentVariable(siteRequest_, (String)o);
    case "providerRequestInstructions":
      return Provider.staticSearchProviderRequestInstructions(siteRequest_, (String)o);
    case "providerUrl":
      return Provider.staticSearchProviderUrl(siteRequest_, (String)o);
      default:
        return BaseModel.staticSearchBaseModel(entityVar,  siteRequest_, o);
    }
  }

  ///////////////////
  // staticSearchStr //
  ///////////////////

  public static String staticSearchStrForClass(String entityVar, SiteRequest siteRequest_, Object o) {
    return staticSearchStrProvider(entityVar,  siteRequest_, o);
  }
  public static String staticSearchStrProvider(String entityVar, SiteRequest siteRequest_, Object o) {
    switch(entityVar) {
    case "providerName":
      return Provider.staticSearchStrProviderName(siteRequest_, (String)o);
    case "requestedClientId":
      return Provider.staticSearchStrRequestedClientId(siteRequest_, (String)o);
    case "requestedEnvironmentVariable":
      return Provider.staticSearchStrRequestedEnvironmentVariable(siteRequest_, (String)o);
    case "providerRequestInstructions":
      return Provider.staticSearchStrProviderRequestInstructions(siteRequest_, (String)o);
    case "providerUrl":
      return Provider.staticSearchStrProviderUrl(siteRequest_, (String)o);
      default:
        return BaseModel.staticSearchStrBaseModel(entityVar,  siteRequest_, o);
    }
  }

  //////////////////
  // staticSearchFq //
  //////////////////

  public static String staticSearchFqForClass(String entityVar, SiteRequest siteRequest_, String o) {
    return staticSearchFqProvider(entityVar,  siteRequest_, o);
  }
  public static String staticSearchFqProvider(String entityVar, SiteRequest siteRequest_, String o) {
    switch(entityVar) {
    case "providerName":
      return Provider.staticSearchFqProviderName(siteRequest_, o);
    case "requestedClientId":
      return Provider.staticSearchFqRequestedClientId(siteRequest_, o);
    case "requestedEnvironmentVariable":
      return Provider.staticSearchFqRequestedEnvironmentVariable(siteRequest_, o);
    case "providerRequestInstructions":
      return Provider.staticSearchFqProviderRequestInstructions(siteRequest_, o);
    case "providerUrl":
      return Provider.staticSearchFqProviderUrl(siteRequest_, o);
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
          o = persistProvider(v, val);
        else if(o instanceof BaseModel) {
          BaseModel oBaseModel = (BaseModel)o;
          o = oBaseModel.persistForClass(v, val);
        }
      }
    }
    return o != null;
  }
  public Object persistProvider(String var, Object val) {
    String varLower = var.toLowerCase();
      if("providername".equals(varLower)) {
        if(val instanceof String) {
          setProviderName((String)val);
        }
        saves.add("providerName");
        return val;
      } else if("requestedclientid".equals(varLower)) {
        if(val instanceof String) {
          setRequestedClientId((String)val);
        }
        saves.add("requestedClientId");
        return val;
      } else if("requestedenvironmentvariable".equals(varLower)) {
        if(val instanceof String) {
          setRequestedEnvironmentVariable((String)val);
        }
        saves.add("requestedEnvironmentVariable");
        return val;
      } else if("providerrequestinstructions".equals(varLower)) {
        if(val instanceof String) {
          setProviderRequestInstructions((String)val);
        }
        saves.add("providerRequestInstructions");
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
    populateProvider(doc);
  }
  public void populateProvider(SolrResponse.Doc doc) {
    Provider oProvider = (Provider)this;
    saves = Optional.ofNullable((ArrayList<String>)doc.get("saves_docvalues_strings")).orElse(new ArrayList<String>());
    if(saves != null) {

      if(saves.contains("providerName")) {
        String providerName = (String)doc.get("providerName_docvalues_string");
        if(providerName != null)
          oProvider.setProviderName(providerName);
      }

      if(saves.contains("requestedClientId")) {
        String requestedClientId = (String)doc.get("requestedClientId_docvalues_string");
        if(requestedClientId != null)
          oProvider.setRequestedClientId(requestedClientId);
      }

      if(saves.contains("requestedEnvironmentVariable")) {
        String requestedEnvironmentVariable = (String)doc.get("requestedEnvironmentVariable_docvalues_string");
        if(requestedEnvironmentVariable != null)
          oProvider.setRequestedEnvironmentVariable(requestedEnvironmentVariable);
      }

      if(saves.contains("providerRequestInstructions")) {
        String providerRequestInstructions = (String)doc.get("providerRequestInstructions_docvalues_string");
        if(providerRequestInstructions != null)
          oProvider.setProviderRequestInstructions(providerRequestInstructions);
      }

      if(saves.contains("providerUrl")) {
        String providerUrl = (String)doc.get("providerUrl_docvalues_string");
        if(providerUrl != null)
          oProvider.setProviderUrl(providerUrl);
      }
    }

    super.populateBaseModel(doc);
  }

  public void indexProvider(JsonObject doc) {
    if(providerName != null) {
      doc.put("providerName_docvalues_string", providerName);
    }
    if(requestedClientId != null) {
      doc.put("requestedClientId_docvalues_string", requestedClientId);
    }
    if(requestedEnvironmentVariable != null) {
      doc.put("requestedEnvironmentVariable_docvalues_string", requestedEnvironmentVariable);
    }
    if(providerRequestInstructions != null) {
      doc.put("providerRequestInstructions_docvalues_string", providerRequestInstructions);
    }
    if(providerUrl != null) {
      doc.put("providerUrl_docvalues_string", providerUrl);
    }
    super.indexBaseModel(doc);

	}

  public static String varStoredProvider(String entityVar) {
    switch(entityVar) {
      case "providerName":
        return "providerName_docvalues_string";
      case "requestedClientId":
        return "requestedClientId_docvalues_string";
      case "requestedEnvironmentVariable":
        return "requestedEnvironmentVariable_docvalues_string";
      case "providerRequestInstructions":
        return "providerRequestInstructions_docvalues_string";
      case "providerUrl":
        return "providerUrl_docvalues_string";
      default:
        return BaseModel.varStoredBaseModel(entityVar);
    }
  }

  public static String varIndexedProvider(String entityVar) {
    switch(entityVar) {
      case "providerName":
        return "providerName_docvalues_string";
      case "requestedClientId":
        return "requestedClientId_docvalues_string";
      case "requestedEnvironmentVariable":
        return "requestedEnvironmentVariable_docvalues_string";
      case "providerRequestInstructions":
        return "providerRequestInstructions_docvalues_string";
      case "providerUrl":
        return "providerUrl_docvalues_string";
      default:
        return BaseModel.varIndexedBaseModel(entityVar);
    }
  }

  public static String searchVarProvider(String searchVar) {
    switch(searchVar) {
      case "providerName_docvalues_string":
        return "providerName";
      case "requestedClientId_docvalues_string":
        return "requestedClientId";
      case "requestedEnvironmentVariable_docvalues_string":
        return "requestedEnvironmentVariable";
      case "providerRequestInstructions_docvalues_string":
        return "providerRequestInstructions";
      case "providerUrl_docvalues_string":
        return "providerUrl";
      default:
        return BaseModel.searchVarBaseModel(searchVar);
    }
  }

  public static String varSearchProvider(String entityVar) {
    switch(entityVar) {
      default:
        return BaseModel.varSearchBaseModel(entityVar);
    }
  }

  public static String varSuggestedProvider(String entityVar) {
    switch(entityVar) {
      default:
        return BaseModel.varSuggestedBaseModel(entityVar);
    }
  }

  /////////////
  // store //
  /////////////

  @Override public void storeForClass(SolrResponse.Doc doc) {
    storeProvider(doc);
  }
  public void storeProvider(SolrResponse.Doc doc) {
    Provider oProvider = (Provider)this;
    SiteRequest siteRequest = oProvider.getSiteRequest_();

    oProvider.setProviderName(Optional.ofNullable(doc.get("providerName_docvalues_string")).map(v -> v.toString()).orElse(null));
    oProvider.setRequestedClientId(Optional.ofNullable(doc.get("requestedClientId_docvalues_string")).map(v -> v.toString()).orElse(null));
    oProvider.setRequestedEnvironmentVariable(Optional.ofNullable(doc.get("requestedEnvironmentVariable_docvalues_string")).map(v -> v.toString()).orElse(null));
    oProvider.setProviderRequestInstructions(Optional.ofNullable(doc.get("providerRequestInstructions_docvalues_string")).map(v -> v.toString()).orElse(null));
    oProvider.setProviderUrl(Optional.ofNullable(doc.get("providerUrl_docvalues_string")).map(v -> v.toString()).orElse(null));

    super.storeBaseModel(doc);
  }

  //////////////////
  // apiRequest //
  //////////////////

  public void apiRequestProvider() {
    ApiRequest apiRequest = Optional.ofNullable(siteRequest_).map(r -> r.getApiRequest_()).orElse(null);
    Object o = Optional.ofNullable(apiRequest).map(ApiRequest::getOriginal).orElse(null);
    if(o != null && o instanceof Provider) {
      Provider original = (Provider)o;
      if(!Objects.equals(providerName, original.getProviderName()))
        apiRequest.addVars("providerName");
      if(!Objects.equals(requestedClientId, original.getRequestedClientId()))
        apiRequest.addVars("requestedClientId");
      if(!Objects.equals(requestedEnvironmentVariable, original.getRequestedEnvironmentVariable()))
        apiRequest.addVars("requestedEnvironmentVariable");
      if(!Objects.equals(providerRequestInstructions, original.getProviderRequestInstructions()))
        apiRequest.addVars("providerRequestInstructions");
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
    sb.append(Optional.ofNullable(requestedClientId).map(v -> "requestedClientId: \"" + v + "\"\n" ).orElse(""));
    sb.append(Optional.ofNullable(requestedEnvironmentVariable).map(v -> "requestedEnvironmentVariable: \"" + v + "\"\n" ).orElse(""));
    sb.append(Optional.ofNullable(providerRequestInstructions).map(v -> "providerRequestInstructions: \"" + v + "\"\n" ).orElse(""));
    sb.append(Optional.ofNullable(providerUrl).map(v -> "providerUrl: \"" + v + "\"\n" ).orElse(""));
    return sb.toString();
  }

  public static final String CLASS_SIMPLE_NAME = "Provider";
  public static final String CLASS_CANONICAL_NAME = "org.computate.dcm.model.eda.provider.Provider";
  public static final String CLASS_AUTH_RESOURCE = "PROVIDER";
  public static final String VAR_providerName = "providerName";
  public static final String SET_providerName = "setProviderName";
  public static final String VAR_requestedClientId = "requestedClientId";
  public static final String SET_requestedClientId = "setRequestedClientId";
  public static final String VAR_requestedEnvironmentVariable = "requestedEnvironmentVariable";
  public static final String SET_requestedEnvironmentVariable = "setRequestedEnvironmentVariable";
  public static final String VAR_providerRequestInstructions = "providerRequestInstructions";
  public static final String SET_providerRequestInstructions = "setProviderRequestInstructions";
  public static final String VAR_providerUrl = "providerUrl";
  public static final String SET_providerUrl = "setProviderUrl";

  public static List<String> varsQForClass() {
    return Provider.varsQProvider(new ArrayList<String>());
  }
  public static List<String> varsQProvider(List<String> vars) {
    BaseModel.varsQBaseModel(vars);
    return vars;
  }

  public static List<String> varsFqForClass() {
    return Provider.varsFqProvider(new ArrayList<String>());
  }
  public static List<String> varsFqProvider(List<String> vars) {
    vars.add(VAR_providerName);
    BaseModel.varsFqBaseModel(vars);
    return vars;
  }

  public static List<String> varsRangeForClass() {
    return Provider.varsRangeProvider(new ArrayList<String>());
  }
  public static List<String> varsRangeProvider(List<String> vars) {
    BaseModel.varsRangeBaseModel(vars);
    return vars;
  }

  public static final String DISPLAY_NAME_providerName = "provider name";
  public static final String DISPLAY_NAME_requestedClientId = "requested client ID";
  public static final String DISPLAY_NAME_requestedEnvironmentVariable = "client secret environment variable";
  public static final String DISPLAY_NAME_providerRequestInstructions = "provider request instructions";
  public static final String DISPLAY_NAME_providerUrl = "provider URL";

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
    return Provider.NameAdjectiveSingular_enUS;
  }

  public static String varJson(String var, Boolean patch) {
    return Provider.varJsonProvider(var, patch);
  }
  public static String varJsonProvider(String var, Boolean patch) {
    switch(var) {
    case VAR_providerName:
      return patch ? SET_providerName : VAR_providerName;
    case VAR_requestedClientId:
      return patch ? SET_requestedClientId : VAR_requestedClientId;
    case VAR_requestedEnvironmentVariable:
      return patch ? SET_requestedEnvironmentVariable : VAR_requestedEnvironmentVariable;
    case VAR_providerRequestInstructions:
      return patch ? SET_providerRequestInstructions : VAR_providerRequestInstructions;
    case VAR_providerUrl:
      return patch ? SET_providerUrl : VAR_providerUrl;
    default:
      return BaseModel.varJsonBaseModel(var, patch);
    }
  }

  public static String displayNameForClass(String var) {
    return Provider.displayNameProvider(var);
  }
  public static String displayNameProvider(String var) {
    switch(var) {
    case VAR_providerName:
      return DISPLAY_NAME_providerName;
    case VAR_requestedClientId:
      return DISPLAY_NAME_requestedClientId;
    case VAR_requestedEnvironmentVariable:
      return DISPLAY_NAME_requestedEnvironmentVariable;
    case VAR_providerRequestInstructions:
      return DISPLAY_NAME_providerRequestInstructions;
    case VAR_providerUrl:
      return DISPLAY_NAME_providerUrl;
    default:
      return BaseModel.displayNameBaseModel(var);
    }
  }

  public static String descriptionProvider(String var) {
    if(var == null)
      return null;
    switch(var) {
    case VAR_providerName:
      return "The name of this provider";
    case VAR_requestedClientId:
      return "The client ID you would like to request from the provider Keycloak service. ";
    case VAR_requestedEnvironmentVariable:
      return "The name of the environment variable that should contain the client secret for this provider. ";
    case VAR_providerRequestInstructions:
      return "Required steps that must be done before requesting this provider. ";
    case VAR_providerUrl:
      return "The URL to the remote DCM provider application. ";
      default:
        return BaseModel.descriptionBaseModel(var);
    }
  }

  public static String classSimpleNameProvider(String var) {
    switch(var) {
    case VAR_providerName:
      return "String";
    case VAR_requestedClientId:
      return "String";
    case VAR_requestedEnvironmentVariable:
      return "String";
    case VAR_providerRequestInstructions:
      return "String";
    case VAR_providerUrl:
      return "String";
      default:
        return BaseModel.classSimpleNameBaseModel(var);
    }
  }

  public static Integer htmColumnProvider(String var) {
    switch(var) {
    case VAR_providerName:
      return 1;
      default:
        return BaseModel.htmColumnBaseModel(var);
    }
  }

  public static Integer htmRowProvider(String var) {
    switch(var) {
    case VAR_providerName:
      return 20;
    case VAR_requestedClientId:
      return 21;
    case VAR_requestedEnvironmentVariable:
      return 21;
    case VAR_providerRequestInstructions:
      return 21;
    case VAR_providerUrl:
      return 21;
      default:
        return BaseModel.htmRowBaseModel(var);
    }
  }

  public static Integer htmCellProvider(String var) {
    switch(var) {
    case VAR_providerName:
      return 1;
    case VAR_requestedClientId:
      return 0;
    case VAR_requestedEnvironmentVariable:
      return 0;
    case VAR_providerRequestInstructions:
      return 0;
    case VAR_providerUrl:
      return 0;
      default:
        return BaseModel.htmCellBaseModel(var);
    }
  }

  public static Integer lengthMinProvider(String var) {
    switch(var) {
      default:
        return BaseModel.lengthMinBaseModel(var);
    }
  }

  public static Integer lengthMaxProvider(String var) {
    switch(var) {
      default:
        return BaseModel.lengthMaxBaseModel(var);
    }
  }

  public static Integer maxProvider(String var) {
    switch(var) {
      default:
        return BaseModel.maxBaseModel(var);
    }
  }

  public static Integer minProvider(String var) {
    switch(var) {
      default:
        return BaseModel.minBaseModel(var);
    }
  }
}
