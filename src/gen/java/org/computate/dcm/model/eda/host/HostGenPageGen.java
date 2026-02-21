package org.computate.dcm.model.eda.host;

import org.computate.dcm.request.SiteRequest;
import org.computate.dcm.page.PageLayout;
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
import org.computate.vertx.search.list.SearchList;
import org.computate.dcm.model.eda.host.Host;
import java.lang.String;
import org.computate.search.response.solr.SolrResponse.Stats;
import org.computate.search.response.solr.SolrResponse.FacetCounts;
import io.vertx.core.json.JsonObject;
import org.computate.vertx.serialize.vertx.JsonObjectDeserializer;
import java.lang.Integer;
import java.time.ZoneId;
import java.util.Locale;
import java.lang.Long;
import java.time.ZonedDateTime;
import java.time.ZoneOffset;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.time.format.DateTimeFormatter;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.math.BigDecimal;
import io.vertx.core.json.JsonArray;
import org.computate.vertx.serialize.vertx.JsonArrayDeserializer;
import java.lang.Void;
import org.computate.search.wrap.Wrap;
import io.vertx.core.Promise;
import io.vertx.core.Future;

/**
 * <ol>
<h3>Suggestions that can generate more code for you: </h3> * </ol>
 * <li>You can add a class comment <b>"Api: true"</b> if you wish to GET, POST, PATCH or PUT these HostGenPage objects in a RESTful API. 
 * </li><li>You can add a class comment "{@inheritDoc}" if you wish to inherit the helpful inherited class comments from class HostGenPageGen into the class HostGenPage. 
 * </li>
 * <h3>About the HostGenPage class and it's generated class HostGenPageGen&lt;PageLayout&gt;: </h3>extends HostGenPageGen
 * <p>
 * This Java class extends a generated Java class HostGenPageGen built by the <a href="https://github.com/computate-org/computate">https://github.com/computate-org/computate</a> project. 
 * Whenever this Java class is modified or touched, the watch service installed as described in the README, indexes all the information about this Java class in a local Apache Solr Search Engine. 
 * If you are running the service, you can see the indexed data about this Java Class here: 
 * </p>
 * <p><a href="https://solr.apps-crc.testing/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.host.HostGenPage">Find the class HostGenPage in Solr. </a></p>
 * <p>
 * The extended class ending with "Gen" did not exist at first, but was automatically created by the same watch service based on the data retrieved from the local Apache Server search engine. 
 * The extended class contains many generated fields, getters, setters, initialization code, and helper methods to help build a website and API fast, reactive, and scalable. 
 * </p>
 * extends HostGenPageGen<PageLayout>
 * <p>This <code>class HostGenPage extends HostGenPageGen&lt;PageLayout&gt;</code>, which means it extends a newly generated HostGenPageGen. 
 * The generated <code>class HostGenPageGen extends PageLayout</code> which means that HostGenPage extends HostGenPageGen which extends PageLayout. 
 * This generated inheritance is a powerful feature that allows a lot of boiler plate code to be created for you automatically while still preserving inheritance through the power of Java Generic classes. 
 * </p>
 * <h2>Api: true</h2>
 * <h2>ApiTag.enUS: true</h2>
 * <h2>ApiUri.enUS: null</h2>
 * <h2>Color: null</h2>
 * <h2>Indexed: true</h2>
 * <h2>{@inheritDoc}</h2>
 * <p>By adding a class comment "{@inheritDoc}", the HostGenPage class will inherit the helpful inherited class comments from the super class HostGenPageGen. 
 * </p>
 * <h2>Rows: null</h2>
 * <h2>Model: true</h2>
 * <h2>Page: true</h2>
 * <h2>SuperPage.enUS: null</h2>
 * <h2>Promise: true</h2>
 * <p>
 *   This class contains a comment <b>"Promise: true"</b>
 *   Sometimes a Java class must be initialized asynchronously when it involves calling a blocking API. 
 *   This means that the HostGenPage Java class has promiseDeep methods which must be initialized asynchronously as a Vert.x Promise  instead of initDeep methods which are a simple non-asynchronous method. 
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
 * <h2>AName.enUS: null</h2>
 * <p>
 * Delete the class HostGenPage in Solr: 
 * <pre>
 * curl -k 'https://solr.apps-crc.testing/solr/computate/update?commitWithin=1000&amp;overwrite=true&amp;wt=json' -X POST -H 'Content-type: text/xml' -u "admin:$(oc -n solr get secret/solr-solrcloud-security-bootstrap -o jsonpath={.data.admin} | base64 -d)" --data-raw '&lt;delete&gt;&lt;query&gt;classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.host.HostGenPage&lt;/query&gt;&lt;/delete&gt;'
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
public abstract class HostGenPageGen<DEV> extends PageLayout {
  protected static final Logger LOG = LoggerFactory.getLogger(HostGenPage.class);

	/////////////////////
  // searchListHost_ //
	/////////////////////


  /**
   *  The entity searchListHost_
   *	 is defined as null before being initialized. 
   */
  @JsonIgnore
  @JsonInclude(Include.NON_NULL)
  protected SearchList<Host> searchListHost_;

  /**
   * <br> The entity searchListHost_
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.host.HostGenPage&fq=entiteVar_enUS_indexed_string:searchListHost_">Find the entity searchListHost_ in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _searchListHost_(Wrap<SearchList<Host>> w);

  public SearchList<Host> getSearchListHost_() {
    return searchListHost_;
  }

  public void setSearchListHost_(SearchList<Host> searchListHost_) {
    this.searchListHost_ = searchListHost_;
  }
  public static SearchList<Host> staticSetSearchListHost_(SiteRequest siteRequest_, String o) {
    return null;
  }
  protected HostGenPage searchListHost_Init() {
    Wrap<SearchList<Host>> searchListHost_Wrap = new Wrap<SearchList<Host>>().var("searchListHost_");
    if(searchListHost_ == null) {
      _searchListHost_(searchListHost_Wrap);
      Optional.ofNullable(searchListHost_Wrap.getO()).ifPresent(o -> {
        setSearchListHost_(o);
      });
    }
    return (HostGenPage)this;
  }

	//////////////
  // listHost //
	//////////////


  /**
   *  The entity listHost
   *	 It is constructed before being initialized with the constructor by default. 
   */
  @JsonProperty
  @JsonDeserialize(using = JsonArrayDeserializer.class)
  @JsonInclude(Include.NON_NULL)
  protected JsonArray listHost = new JsonArray();

  /**
   * <br> The entity listHost
   *  It is constructed before being initialized with the constructor by default. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.host.HostGenPage&fq=entiteVar_enUS_indexed_string:listHost">Find the entity listHost in Solr</a>
   * <br>
   * @param l is the entity already constructed. 
   **/
  protected abstract void _listHost(JsonArray l);

  public JsonArray getListHost() {
    return listHost;
  }

  public void setListHost(JsonArray listHost) {
    this.listHost = listHost;
  }
  @JsonIgnore
  public void setListHost(String o) {
    this.listHost = HostGenPage.staticSetListHost(siteRequest_, o);
  }
  public static JsonArray staticSetListHost(SiteRequest siteRequest_, String o) {
    if(o != null) {
        return new JsonArray(o);
    }
    return null;
  }
  protected HostGenPage listHostInit() {
    _listHost(listHost);
    return (HostGenPage)this;
  }

  public static String staticSearchListHost(SiteRequest siteRequest_, JsonArray o) {
    return o.toString();
  }

  public static String staticSearchStrListHost(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqListHost(SiteRequest siteRequest_, String o) {
    return HostGenPage.staticSearchListHost(siteRequest_, HostGenPage.staticSetListHost(siteRequest_, o)).toString();
  }

	/////////////////
  // resultCount //
	/////////////////


  /**
   *  The entity resultCount
   *	 is defined as null before being initialized. 
   */
  @JsonProperty
  @JsonSerialize(using = ToStringSerializer.class)
  @JsonInclude(Include.NON_NULL)
  protected Integer resultCount;

  /**
   * <br> The entity resultCount
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.host.HostGenPage&fq=entiteVar_enUS_indexed_string:resultCount">Find the entity resultCount in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _resultCount(Wrap<Integer> w);

  public Integer getResultCount() {
    return resultCount;
  }

  public void setResultCount(Integer resultCount) {
    this.resultCount = resultCount;
  }
  @JsonIgnore
  public void setResultCount(String o) {
    this.resultCount = HostGenPage.staticSetResultCount(siteRequest_, o);
  }
  public static Integer staticSetResultCount(SiteRequest siteRequest_, String o) {
    if(NumberUtils.isParsable(o))
      return Integer.parseInt(o);
    return null;
  }
  protected HostGenPage resultCountInit() {
    Wrap<Integer> resultCountWrap = new Wrap<Integer>().var("resultCount");
    if(resultCount == null) {
      _resultCount(resultCountWrap);
      Optional.ofNullable(resultCountWrap.getO()).ifPresent(o -> {
        setResultCount(o);
      });
    }
    return (HostGenPage)this;
  }

  public static Integer staticSearchResultCount(SiteRequest siteRequest_, Integer o) {
    return o;
  }

  public static String staticSearchStrResultCount(SiteRequest siteRequest_, Integer o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqResultCount(SiteRequest siteRequest_, String o) {
    return HostGenPage.staticSearchResultCount(siteRequest_, HostGenPage.staticSetResultCount(siteRequest_, o)).toString();
  }

	////////////
  // result //
	////////////


  /**
   *  The entity result
   *	 is defined as null before being initialized. 
   */
  @JsonProperty
  @JsonInclude(Include.NON_NULL)
  protected Host result;

  /**
   * <br> The entity result
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.host.HostGenPage&fq=entiteVar_enUS_indexed_string:result">Find the entity result in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _result(Wrap<Host> w);

  public Host getResult() {
    return result;
  }

  public void setResult(Host result) {
    this.result = result;
  }
  public static Host staticSetResult(SiteRequest siteRequest_, String o) {
    return null;
  }
  protected HostGenPage resultInit() {
    Wrap<Host> resultWrap = new Wrap<Host>().var("result");
    if(result == null) {
      _result(resultWrap);
      Optional.ofNullable(resultWrap.getO()).ifPresent(o -> {
        setResult(o);
      });
    }
    return (HostGenPage)this;
  }

	////////
  // pk //
	////////


  /**
   *  The entity pk
   *	 is defined as null before being initialized. 
   */
  @JsonProperty
  @JsonSerialize(using = ToStringSerializer.class)
  @JsonInclude(Include.NON_NULL)
  protected Long pk;

  /**
   * <br> The entity pk
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.host.HostGenPage&fq=entiteVar_enUS_indexed_string:pk">Find the entity pk in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _pk(Wrap<Long> w);

  public Long getPk() {
    return pk;
  }

  public void setPk(Long pk) {
    this.pk = pk;
  }
  @JsonIgnore
  public void setPk(String o) {
    this.pk = HostGenPage.staticSetPk(siteRequest_, o);
  }
  public static Long staticSetPk(SiteRequest siteRequest_, String o) {
    if(NumberUtils.isParsable(o))
      return Long.parseLong(o);
    return null;
  }
  protected HostGenPage pkInit() {
    Wrap<Long> pkWrap = new Wrap<Long>().var("pk");
    if(pk == null) {
      _pk(pkWrap);
      Optional.ofNullable(pkWrap.getO()).ifPresent(o -> {
        setPk(o);
      });
    }
    return (HostGenPage)this;
  }

  public static Long staticSearchPk(SiteRequest siteRequest_, Long o) {
    return o;
  }

  public static String staticSearchStrPk(SiteRequest siteRequest_, Long o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqPk(SiteRequest siteRequest_, String o) {
    return HostGenPage.staticSearchPk(siteRequest_, HostGenPage.staticSetPk(siteRequest_, o)).toString();
  }

	////////////
  // solrId //
	////////////


  /**
   *  The entity solrId
   *	 is defined as null before being initialized. 
   */
  @JsonProperty
  @JsonInclude(Include.NON_NULL)
  protected String solrId;

  /**
   * <br> The entity solrId
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.host.HostGenPage&fq=entiteVar_enUS_indexed_string:solrId">Find the entity solrId in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _solrId(Wrap<String> w);

  public String getSolrId() {
    return solrId;
  }
  public void setSolrId(String o) {
    this.solrId = HostGenPage.staticSetSolrId(siteRequest_, o);
  }
  public static String staticSetSolrId(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected HostGenPage solrIdInit() {
    Wrap<String> solrIdWrap = new Wrap<String>().var("solrId");
    if(solrId == null) {
      _solrId(solrIdWrap);
      Optional.ofNullable(solrIdWrap.getO()).ifPresent(o -> {
        setSolrId(o);
      });
    }
    return (HostGenPage)this;
  }

  public static String staticSearchSolrId(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrSolrId(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqSolrId(SiteRequest siteRequest_, String o) {
    return HostGenPage.staticSearchSolrId(siteRequest_, HostGenPage.staticSetSolrId(siteRequest_, o)).toString();
  }

	/////////////////
  // pageUriHost //
	/////////////////


  /**
   *  The entity pageUriHost
   *	 is defined as null before being initialized. 
   */
  @JsonProperty
  @JsonInclude(Include.NON_NULL)
  protected String pageUriHost;

  /**
   * <br> The entity pageUriHost
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.host.HostGenPage&fq=entiteVar_enUS_indexed_string:pageUriHost">Find the entity pageUriHost in Solr</a>
   * <br>
   * @param c is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _pageUriHost(Wrap<String> c);

  public String getPageUriHost() {
    return pageUriHost;
  }
  public void setPageUriHost(String o) {
    this.pageUriHost = HostGenPage.staticSetPageUriHost(siteRequest_, o);
  }
  public static String staticSetPageUriHost(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected HostGenPage pageUriHostInit() {
    Wrap<String> pageUriHostWrap = new Wrap<String>().var("pageUriHost");
    if(pageUriHost == null) {
      _pageUriHost(pageUriHostWrap);
      Optional.ofNullable(pageUriHostWrap.getO()).ifPresent(o -> {
        setPageUriHost(o);
      });
    }
    return (HostGenPage)this;
  }

  public static String staticSearchPageUriHost(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrPageUriHost(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqPageUriHost(SiteRequest siteRequest_, String o) {
    return HostGenPage.staticSearchPageUriHost(siteRequest_, HostGenPage.staticSetPageUriHost(siteRequest_, o)).toString();
  }

  //////////////
  // initDeep //
  //////////////

  public Future<HostGenPageGen<DEV>> promiseDeepHostGenPage(SiteRequest siteRequest_) {
    setSiteRequest_(siteRequest_);
    return promiseDeepHostGenPage();
  }

  public Future<HostGenPageGen<DEV>> promiseDeepHostGenPage() {
    Promise<HostGenPageGen<DEV>> promise = Promise.promise();
    Promise<Void> promise2 = Promise.promise();
    promiseHostGenPage(promise2);
    promise2.future().onSuccess(a -> {
      super.promiseDeepPageLayout(siteRequest_).onSuccess(b -> {
        promise.complete(this);
      }).onFailure(ex -> {
        promise.fail(ex);
      });
    }).onFailure(ex -> {
      promise.fail(ex);
    });
    return promise.future();
  }

  public Future<Void> promiseHostGenPage(Promise<Void> promise) {
    Future.future(a -> a.complete()).compose(a -> {
      Promise<Void> promise2 = Promise.promise();
      try {
        searchListHost_Init();
        listHostInit();
        resultCountInit();
        resultInit();
        pkInit();
        solrIdInit();
        pageUriHostInit();
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

  @Override public Future<? extends HostGenPageGen<DEV>> promiseDeepForClass(SiteRequest siteRequest_) {
    return promiseDeepHostGenPage(siteRequest_);
  }

  /////////////////
  // siteRequest //
  /////////////////

  public void siteRequestHostGenPage(SiteRequest siteRequest_) {
      super.siteRequestPageLayout(siteRequest_);
  }

  public void siteRequestForClass(SiteRequest siteRequest_) {
    siteRequestHostGenPage(siteRequest_);
  }

  /////////////
  // obtain //
  /////////////

  @Override public Object obtainForClass(String var) {
    String[] vars = StringUtils.split(var, ".");
    Object o = null;
    for(String v : vars) {
      if(o == null)
        o = obtainHostGenPage(v);
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
  public Object obtainHostGenPage(String var) {
    HostGenPage oHostGenPage = (HostGenPage)this;
    switch(var) {
      case "searchListHost_":
        return oHostGenPage.searchListHost_;
      case "listHost":
        return oHostGenPage.listHost;
      case "resultCount":
        return oHostGenPage.resultCount;
      case "result":
        return oHostGenPage.result;
      case "pk":
        return oHostGenPage.pk;
      case "solrId":
        return oHostGenPage.solrId;
      case "pageUriHost":
        return oHostGenPage.pageUriHost;
      default:
        return super.obtainPageLayout(var);
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
        o = relateHostGenPage(v, val);
      else if(o instanceof BaseModel) {
        BaseModel baseModel = (BaseModel)o;
        o = baseModel.relateForClass(v, val);
      }
    }
    return o != null;
  }
  public Object relateHostGenPage(String var, Object val) {
    HostGenPage oHostGenPage = (HostGenPage)this;
    switch(var) {
      default:
        return super.relatePageLayout(var, val);
    }
  }

  ///////////////
  // staticSet //
  ///////////////

  public static Object staticSetForClass(String entityVar, SiteRequest siteRequest_, String v, HostGenPage o) {
    return staticSetHostGenPage(entityVar,  siteRequest_, v, o);
  }
  public static Object staticSetHostGenPage(String entityVar, SiteRequest siteRequest_, String v, HostGenPage o) {
    switch(entityVar) {
    case "listHost":
      return HostGenPage.staticSetListHost(siteRequest_, v);
    case "resultCount":
      return HostGenPage.staticSetResultCount(siteRequest_, v);
    case "pk":
      return HostGenPage.staticSetPk(siteRequest_, v);
    case "solrId":
      return HostGenPage.staticSetSolrId(siteRequest_, v);
    case "pageUriHost":
      return HostGenPage.staticSetPageUriHost(siteRequest_, v);
      default:
        return PageLayout.staticSetPageLayout(entityVar,  siteRequest_, v, o);
    }
  }

  //////////////////
  // staticSearch //
  //////////////////

  public static Object staticSearchForClass(String entityVar, SiteRequest siteRequest_, Object o) {
    return staticSearchHostGenPage(entityVar,  siteRequest_, o);
  }
  public static Object staticSearchHostGenPage(String entityVar, SiteRequest siteRequest_, Object o) {
    switch(entityVar) {
    case "listHost":
      return HostGenPage.staticSearchListHost(siteRequest_, (JsonArray)o);
    case "resultCount":
      return HostGenPage.staticSearchResultCount(siteRequest_, (Integer)o);
    case "pk":
      return HostGenPage.staticSearchPk(siteRequest_, (Long)o);
    case "solrId":
      return HostGenPage.staticSearchSolrId(siteRequest_, (String)o);
    case "pageUriHost":
      return HostGenPage.staticSearchPageUriHost(siteRequest_, (String)o);
      default:
        return PageLayout.staticSearchPageLayout(entityVar,  siteRequest_, o);
    }
  }

  ///////////////////
  // staticSearchStr //
  ///////////////////

  public static String staticSearchStrForClass(String entityVar, SiteRequest siteRequest_, Object o) {
    return staticSearchStrHostGenPage(entityVar,  siteRequest_, o);
  }
  public static String staticSearchStrHostGenPage(String entityVar, SiteRequest siteRequest_, Object o) {
    switch(entityVar) {
    case "listHost":
      return HostGenPage.staticSearchStrListHost(siteRequest_, (String)o);
    case "resultCount":
      return HostGenPage.staticSearchStrResultCount(siteRequest_, (Integer)o);
    case "pk":
      return HostGenPage.staticSearchStrPk(siteRequest_, (Long)o);
    case "solrId":
      return HostGenPage.staticSearchStrSolrId(siteRequest_, (String)o);
    case "pageUriHost":
      return HostGenPage.staticSearchStrPageUriHost(siteRequest_, (String)o);
      default:
        return PageLayout.staticSearchStrPageLayout(entityVar,  siteRequest_, o);
    }
  }

  //////////////////
  // staticSearchFq //
  //////////////////

  public static String staticSearchFqForClass(String entityVar, SiteRequest siteRequest_, String o) {
    return staticSearchFqHostGenPage(entityVar,  siteRequest_, o);
  }
  public static String staticSearchFqHostGenPage(String entityVar, SiteRequest siteRequest_, String o) {
    switch(entityVar) {
    case "listHost":
      return HostGenPage.staticSearchFqListHost(siteRequest_, o);
    case "resultCount":
      return HostGenPage.staticSearchFqResultCount(siteRequest_, o);
    case "pk":
      return HostGenPage.staticSearchFqPk(siteRequest_, o);
    case "solrId":
      return HostGenPage.staticSearchFqSolrId(siteRequest_, o);
    case "pageUriHost":
      return HostGenPage.staticSearchFqPageUriHost(siteRequest_, o);
      default:
        return PageLayout.staticSearchFqPageLayout(entityVar,  siteRequest_, o);
    }
  }

  //////////////
  // toString //
  //////////////

  @Override public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(super.toString());
    return sb.toString();
  }

  public static final String CLASS_SIMPLE_NAME = "HostGenPage";
  public static final String CLASS_CANONICAL_NAME = "org.computate.dcm.model.eda.host.HostGenPage";
  public static final String CLASS_AUTH_RESOURCE = "";
  public static final String VAR_searchListHost_ = "searchListHost_";
  public static final String VAR_listHost = "listHost";
  public static final String VAR_resultCount = "resultCount";
  public static final String VAR_result = "result";
  public static final String VAR_pk = "pk";
  public static final String VAR_solrId = "solrId";
  public static final String VAR_pageUriHost = "pageUriHost";

  public static final String DISPLAY_NAME_searchListHost_ = "";
  public static final String DISPLAY_NAME_listHost = "";
  public static final String DISPLAY_NAME_resultCount = "";
  public static final String DISPLAY_NAME_result = "";
  public static final String DISPLAY_NAME_pk = "";
  public static final String DISPLAY_NAME_solrId = "";
  public static final String DISPLAY_NAME_pageUriHost = "";

  @Override
  public String idForClass() {
    return null;
  }

  @Override
  public String titleForClass() {
    return null;
  }

  @Override
  public String nameForClass() {
    return null;
  }

  @Override
  public String classNameAdjectiveSingularForClass() {
    return null;
  }

  @Override
  public String descriptionForClass() {
    return null;
  }

  @Override
  public String enUSStringFormatUrlEditPageForClass() {
    return null;
  }

  @Override
  public String enUSStringFormatUrlDisplayPageForClass() {
    return null;
  }

  @Override
  public String enUSStringFormatUrlUserPageForClass() {
    return null;
  }

  @Override
  public String enUSStringFormatUrlDownloadForClass() {
    return null;
  }

  public static String displayNameForClass(String var) {
    return HostGenPage.displayNameHostGenPage(var);
  }
  public static String displayNameHostGenPage(String var) {
    switch(var) {
    case VAR_searchListHost_:
      return DISPLAY_NAME_searchListHost_;
    case VAR_listHost:
      return DISPLAY_NAME_listHost;
    case VAR_resultCount:
      return DISPLAY_NAME_resultCount;
    case VAR_result:
      return DISPLAY_NAME_result;
    case VAR_pk:
      return DISPLAY_NAME_pk;
    case VAR_solrId:
      return DISPLAY_NAME_solrId;
    case VAR_pageUriHost:
      return DISPLAY_NAME_pageUriHost;
    default:
      return PageLayout.displayNamePageLayout(var);
    }
  }

  public static String descriptionHostGenPage(String var) {
    if(var == null)
      return null;
    switch(var) {
      default:
        return PageLayout.descriptionPageLayout(var);
    }
  }

  public static String classSimpleNameHostGenPage(String var) {
    switch(var) {
    case VAR_searchListHost_:
      return "SearchList";
    case VAR_listHost:
      return "JsonArray";
    case VAR_resultCount:
      return "Integer";
    case VAR_result:
      return "Host";
    case VAR_pk:
      return "Long";
    case VAR_solrId:
      return "String";
    case VAR_pageUriHost:
      return "String";
      default:
        return PageLayout.classSimpleNamePageLayout(var);
    }
  }

  public static Integer htmColumnHostGenPage(String var) {
    switch(var) {
      default:
        return PageLayout.htmColumnPageLayout(var);
    }
  }

  public static Integer htmRowHostGenPage(String var) {
    switch(var) {
      default:
        return PageLayout.htmRowPageLayout(var);
    }
  }

  public static Integer htmCellHostGenPage(String var) {
    switch(var) {
      default:
        return PageLayout.htmCellPageLayout(var);
    }
  }

  public static Integer lengthMinHostGenPage(String var) {
    switch(var) {
      default:
        return PageLayout.lengthMinPageLayout(var);
    }
  }

  public static Integer lengthMaxHostGenPage(String var) {
    switch(var) {
      default:
        return PageLayout.lengthMaxPageLayout(var);
    }
  }

  public static Integer maxHostGenPage(String var) {
    switch(var) {
      default:
        return PageLayout.maxPageLayout(var);
    }
  }

  public static Integer minHostGenPage(String var) {
    switch(var) {
      default:
        return PageLayout.minPageLayout(var);
    }
  }
}
