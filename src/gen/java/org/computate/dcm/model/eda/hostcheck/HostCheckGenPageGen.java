package org.computate.dcm.model.eda.hostcheck;

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
import org.computate.dcm.model.eda.hostcheck.HostCheck;
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
 * <li>You can add a class comment <b>"Api: true"</b> if you wish to GET, POST, PATCH or PUT these HostCheckGenPage objects in a RESTful API. 
 * </li><li>You can add a class comment "{@inheritDoc}" if you wish to inherit the helpful inherited class comments from class HostCheckGenPageGen into the class HostCheckGenPage. 
 * </li>
 * <h3>About the HostCheckGenPage class and it's generated class HostCheckGenPageGen&lt;PageLayout&gt;: </h3>extends HostCheckGenPageGen
 * <p>
 * This Java class extends a generated Java class HostCheckGenPageGen built by the <a href="https://github.com/computate-org/computate">https://github.com/computate-org/computate</a> project. 
 * Whenever this Java class is modified or touched, the watch service installed as described in the README, indexes all the information about this Java class in a local Apache Solr Search Engine. 
 * If you are running the service, you can see the indexed data about this Java Class here: 
 * </p>
 * <p><a href="https://solr.apps-crc.testing/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.hostcheck.HostCheckGenPage">Find the class HostCheckGenPage in Solr. </a></p>
 * <p>
 * The extended class ending with "Gen" did not exist at first, but was automatically created by the same watch service based on the data retrieved from the local Apache Server search engine. 
 * The extended class contains many generated fields, getters, setters, initialization code, and helper methods to help build a website and API fast, reactive, and scalable. 
 * </p>
 * extends HostCheckGenPageGen<PageLayout>
 * <p>This <code>class HostCheckGenPage extends HostCheckGenPageGen&lt;PageLayout&gt;</code>, which means it extends a newly generated HostCheckGenPageGen. 
 * The generated <code>class HostCheckGenPageGen extends PageLayout</code> which means that HostCheckGenPage extends HostCheckGenPageGen which extends PageLayout. 
 * This generated inheritance is a powerful feature that allows a lot of boiler plate code to be created for you automatically while still preserving inheritance through the power of Java Generic classes. 
 * </p>
 * <h2>Api: true</h2>
 * <h2>ApiTag.enUS: true</h2>
 * <h2>ApiUri.enUS: null</h2>
 * <h2>Color: null</h2>
 * <h2>Indexed: true</h2>
 * <h2>{@inheritDoc}</h2>
 * <p>By adding a class comment "{@inheritDoc}", the HostCheckGenPage class will inherit the helpful inherited class comments from the super class HostCheckGenPageGen. 
 * </p>
 * <h2>Rows: null</h2>
 * <h2>Model: true</h2>
 * <h2>Page: true</h2>
 * <h2>SuperPage.enUS: null</h2>
 * <h2>Promise: true</h2>
 * <p>
 *   This class contains a comment <b>"Promise: true"</b>
 *   Sometimes a Java class must be initialized asynchronously when it involves calling a blocking API. 
 *   This means that the HostCheckGenPage Java class has promiseDeep methods which must be initialized asynchronously as a Vert.x Promise  instead of initDeep methods which are a simple non-asynchronous method. 
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
 * Delete the class HostCheckGenPage in Solr: 
 * <pre>
 * curl -k 'https://solr.apps-crc.testing/solr/computate/update?commitWithin=1000&amp;overwrite=true&amp;wt=json' -X POST -H 'Content-type: text/xml' -u "admin:$(oc -n solr get secret/solr-solrcloud-security-bootstrap -o jsonpath={.data.admin} | base64 -d)" --data-raw '&lt;delete&gt;&lt;query&gt;classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.hostcheck.HostCheckGenPage&lt;/query&gt;&lt;/delete&gt;'
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
public abstract class HostCheckGenPageGen<DEV> extends PageLayout {
  protected static final Logger LOG = LoggerFactory.getLogger(HostCheckGenPage.class);

	//////////////////////////
  // searchListHostCheck_ //
	//////////////////////////


  /**
   *  The entity searchListHostCheck_
   *	 is defined as null before being initialized. 
   */
  @JsonIgnore
  @JsonInclude(Include.NON_NULL)
  protected SearchList<HostCheck> searchListHostCheck_;

  /**
   * <br> The entity searchListHostCheck_
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.hostcheck.HostCheckGenPage&fq=entiteVar_enUS_indexed_string:searchListHostCheck_">Find the entity searchListHostCheck_ in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _searchListHostCheck_(Wrap<SearchList<HostCheck>> w);

  public SearchList<HostCheck> getSearchListHostCheck_() {
    return searchListHostCheck_;
  }

  public void setSearchListHostCheck_(SearchList<HostCheck> searchListHostCheck_) {
    this.searchListHostCheck_ = searchListHostCheck_;
  }
  public static SearchList<HostCheck> staticSetSearchListHostCheck_(SiteRequest siteRequest_, String o) {
    return null;
  }
  protected HostCheckGenPage searchListHostCheck_Init() {
    Wrap<SearchList<HostCheck>> searchListHostCheck_Wrap = new Wrap<SearchList<HostCheck>>().var("searchListHostCheck_");
    if(searchListHostCheck_ == null) {
      _searchListHostCheck_(searchListHostCheck_Wrap);
      Optional.ofNullable(searchListHostCheck_Wrap.getO()).ifPresent(o -> {
        setSearchListHostCheck_(o);
      });
    }
    return (HostCheckGenPage)this;
  }

	///////////////////
  // listHostCheck //
	///////////////////


  /**
   *  The entity listHostCheck
   *	 It is constructed before being initialized with the constructor by default. 
   */
  @JsonProperty
  @JsonDeserialize(using = JsonArrayDeserializer.class)
  @JsonInclude(Include.NON_NULL)
  protected JsonArray listHostCheck = new JsonArray();

  /**
   * <br> The entity listHostCheck
   *  It is constructed before being initialized with the constructor by default. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.hostcheck.HostCheckGenPage&fq=entiteVar_enUS_indexed_string:listHostCheck">Find the entity listHostCheck in Solr</a>
   * <br>
   * @param l is the entity already constructed. 
   **/
  protected abstract void _listHostCheck(JsonArray l);

  public JsonArray getListHostCheck() {
    return listHostCheck;
  }

  public void setListHostCheck(JsonArray listHostCheck) {
    this.listHostCheck = listHostCheck;
  }
  @JsonIgnore
  public void setListHostCheck(String o) {
    this.listHostCheck = HostCheckGenPage.staticSetListHostCheck(siteRequest_, o);
  }
  public static JsonArray staticSetListHostCheck(SiteRequest siteRequest_, String o) {
    if(o != null) {
        return new JsonArray(o);
    }
    return null;
  }
  protected HostCheckGenPage listHostCheckInit() {
    _listHostCheck(listHostCheck);
    return (HostCheckGenPage)this;
  }

  public static String staticSearchListHostCheck(SiteRequest siteRequest_, JsonArray o) {
    return o.toString();
  }

  public static String staticSearchStrListHostCheck(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqListHostCheck(SiteRequest siteRequest_, String o) {
    return HostCheckGenPage.staticSearchListHostCheck(siteRequest_, HostCheckGenPage.staticSetListHostCheck(siteRequest_, o)).toString();
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
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.hostcheck.HostCheckGenPage&fq=entiteVar_enUS_indexed_string:resultCount">Find the entity resultCount in Solr</a>
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
    this.resultCount = HostCheckGenPage.staticSetResultCount(siteRequest_, o);
  }
  public static Integer staticSetResultCount(SiteRequest siteRequest_, String o) {
    if(NumberUtils.isParsable(o))
      return Integer.parseInt(o);
    return null;
  }
  protected HostCheckGenPage resultCountInit() {
    Wrap<Integer> resultCountWrap = new Wrap<Integer>().var("resultCount");
    if(resultCount == null) {
      _resultCount(resultCountWrap);
      Optional.ofNullable(resultCountWrap.getO()).ifPresent(o -> {
        setResultCount(o);
      });
    }
    return (HostCheckGenPage)this;
  }

  public static Integer staticSearchResultCount(SiteRequest siteRequest_, Integer o) {
    return o;
  }

  public static String staticSearchStrResultCount(SiteRequest siteRequest_, Integer o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqResultCount(SiteRequest siteRequest_, String o) {
    return HostCheckGenPage.staticSearchResultCount(siteRequest_, HostCheckGenPage.staticSetResultCount(siteRequest_, o)).toString();
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
  protected HostCheck result;

  /**
   * <br> The entity result
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.hostcheck.HostCheckGenPage&fq=entiteVar_enUS_indexed_string:result">Find the entity result in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _result(Wrap<HostCheck> w);

  public HostCheck getResult() {
    return result;
  }

  public void setResult(HostCheck result) {
    this.result = result;
  }
  public static HostCheck staticSetResult(SiteRequest siteRequest_, String o) {
    return null;
  }
  protected HostCheckGenPage resultInit() {
    Wrap<HostCheck> resultWrap = new Wrap<HostCheck>().var("result");
    if(result == null) {
      _result(resultWrap);
      Optional.ofNullable(resultWrap.getO()).ifPresent(o -> {
        setResult(o);
      });
    }
    return (HostCheckGenPage)this;
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
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.hostcheck.HostCheckGenPage&fq=entiteVar_enUS_indexed_string:pk">Find the entity pk in Solr</a>
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
    this.pk = HostCheckGenPage.staticSetPk(siteRequest_, o);
  }
  public static Long staticSetPk(SiteRequest siteRequest_, String o) {
    if(NumberUtils.isParsable(o))
      return Long.parseLong(o);
    return null;
  }
  protected HostCheckGenPage pkInit() {
    Wrap<Long> pkWrap = new Wrap<Long>().var("pk");
    if(pk == null) {
      _pk(pkWrap);
      Optional.ofNullable(pkWrap.getO()).ifPresent(o -> {
        setPk(o);
      });
    }
    return (HostCheckGenPage)this;
  }

  public static Long staticSearchPk(SiteRequest siteRequest_, Long o) {
    return o;
  }

  public static String staticSearchStrPk(SiteRequest siteRequest_, Long o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqPk(SiteRequest siteRequest_, String o) {
    return HostCheckGenPage.staticSearchPk(siteRequest_, HostCheckGenPage.staticSetPk(siteRequest_, o)).toString();
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
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.hostcheck.HostCheckGenPage&fq=entiteVar_enUS_indexed_string:solrId">Find the entity solrId in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _solrId(Wrap<String> w);

  public String getSolrId() {
    return solrId;
  }
  public void setSolrId(String o) {
    this.solrId = HostCheckGenPage.staticSetSolrId(siteRequest_, o);
  }
  public static String staticSetSolrId(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected HostCheckGenPage solrIdInit() {
    Wrap<String> solrIdWrap = new Wrap<String>().var("solrId");
    if(solrId == null) {
      _solrId(solrIdWrap);
      Optional.ofNullable(solrIdWrap.getO()).ifPresent(o -> {
        setSolrId(o);
      });
    }
    return (HostCheckGenPage)this;
  }

  public static String staticSearchSolrId(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrSolrId(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqSolrId(SiteRequest siteRequest_, String o) {
    return HostCheckGenPage.staticSearchSolrId(siteRequest_, HostCheckGenPage.staticSetSolrId(siteRequest_, o)).toString();
  }

	//////////////////////
  // pageUriHostCheck //
	//////////////////////


  /**
   *  The entity pageUriHostCheck
   *	 is defined as null before being initialized. 
   */
  @JsonProperty
  @JsonInclude(Include.NON_NULL)
  protected String pageUriHostCheck;

  /**
   * <br> The entity pageUriHostCheck
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.hostcheck.HostCheckGenPage&fq=entiteVar_enUS_indexed_string:pageUriHostCheck">Find the entity pageUriHostCheck in Solr</a>
   * <br>
   * @param c is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _pageUriHostCheck(Wrap<String> c);

  public String getPageUriHostCheck() {
    return pageUriHostCheck;
  }
  public void setPageUriHostCheck(String o) {
    this.pageUriHostCheck = HostCheckGenPage.staticSetPageUriHostCheck(siteRequest_, o);
  }
  public static String staticSetPageUriHostCheck(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected HostCheckGenPage pageUriHostCheckInit() {
    Wrap<String> pageUriHostCheckWrap = new Wrap<String>().var("pageUriHostCheck");
    if(pageUriHostCheck == null) {
      _pageUriHostCheck(pageUriHostCheckWrap);
      Optional.ofNullable(pageUriHostCheckWrap.getO()).ifPresent(o -> {
        setPageUriHostCheck(o);
      });
    }
    return (HostCheckGenPage)this;
  }

  public static String staticSearchPageUriHostCheck(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrPageUriHostCheck(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqPageUriHostCheck(SiteRequest siteRequest_, String o) {
    return HostCheckGenPage.staticSearchPageUriHostCheck(siteRequest_, HostCheckGenPage.staticSetPageUriHostCheck(siteRequest_, o)).toString();
  }

  //////////////
  // initDeep //
  //////////////

  public Future<HostCheckGenPageGen<DEV>> promiseDeepHostCheckGenPage(SiteRequest siteRequest_) {
    setSiteRequest_(siteRequest_);
    return promiseDeepHostCheckGenPage();
  }

  public Future<HostCheckGenPageGen<DEV>> promiseDeepHostCheckGenPage() {
    Promise<HostCheckGenPageGen<DEV>> promise = Promise.promise();
    Promise<Void> promise2 = Promise.promise();
    promiseHostCheckGenPage(promise2);
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

  public Future<Void> promiseHostCheckGenPage(Promise<Void> promise) {
    Future.future(a -> a.complete()).compose(a -> {
      Promise<Void> promise2 = Promise.promise();
      try {
        searchListHostCheck_Init();
        listHostCheckInit();
        resultCountInit();
        resultInit();
        pkInit();
        solrIdInit();
        pageUriHostCheckInit();
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

  @Override public Future<? extends HostCheckGenPageGen<DEV>> promiseDeepForClass(SiteRequest siteRequest_) {
    return promiseDeepHostCheckGenPage(siteRequest_);
  }

  /////////////////
  // siteRequest //
  /////////////////

  public void siteRequestHostCheckGenPage(SiteRequest siteRequest_) {
      super.siteRequestPageLayout(siteRequest_);
  }

  public void siteRequestForClass(SiteRequest siteRequest_) {
    siteRequestHostCheckGenPage(siteRequest_);
  }

  /////////////
  // obtain //
  /////////////

  @Override public Object obtainForClass(String var) {
    String[] vars = StringUtils.split(var, ".");
    Object o = null;
    for(String v : vars) {
      if(o == null)
        o = obtainHostCheckGenPage(v);
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
  public Object obtainHostCheckGenPage(String var) {
    HostCheckGenPage oHostCheckGenPage = (HostCheckGenPage)this;
    switch(var) {
      case "searchListHostCheck_":
        return oHostCheckGenPage.searchListHostCheck_;
      case "listHostCheck":
        return oHostCheckGenPage.listHostCheck;
      case "resultCount":
        return oHostCheckGenPage.resultCount;
      case "result":
        return oHostCheckGenPage.result;
      case "pk":
        return oHostCheckGenPage.pk;
      case "solrId":
        return oHostCheckGenPage.solrId;
      case "pageUriHostCheck":
        return oHostCheckGenPage.pageUriHostCheck;
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
        o = relateHostCheckGenPage(v, val);
      else if(o instanceof BaseModel) {
        BaseModel baseModel = (BaseModel)o;
        o = baseModel.relateForClass(v, val);
      }
    }
    return o != null;
  }
  public Object relateHostCheckGenPage(String var, Object val) {
    HostCheckGenPage oHostCheckGenPage = (HostCheckGenPage)this;
    switch(var) {
      default:
        return super.relatePageLayout(var, val);
    }
  }

  ///////////////
  // staticSet //
  ///////////////

  public static Object staticSetForClass(String entityVar, SiteRequest siteRequest_, String v, HostCheckGenPage o) {
    return staticSetHostCheckGenPage(entityVar,  siteRequest_, v, o);
  }
  public static Object staticSetHostCheckGenPage(String entityVar, SiteRequest siteRequest_, String v, HostCheckGenPage o) {
    switch(entityVar) {
    case "listHostCheck":
      return HostCheckGenPage.staticSetListHostCheck(siteRequest_, v);
    case "resultCount":
      return HostCheckGenPage.staticSetResultCount(siteRequest_, v);
    case "pk":
      return HostCheckGenPage.staticSetPk(siteRequest_, v);
    case "solrId":
      return HostCheckGenPage.staticSetSolrId(siteRequest_, v);
    case "pageUriHostCheck":
      return HostCheckGenPage.staticSetPageUriHostCheck(siteRequest_, v);
      default:
        return PageLayout.staticSetPageLayout(entityVar,  siteRequest_, v, o);
    }
  }

  ////////////////
  // staticSearch //
  ////////////////

  public static Object staticSearchForClass(String entityVar, SiteRequest siteRequest_, Object o) {
    return staticSearchHostCheckGenPage(entityVar,  siteRequest_, o);
  }
  public static Object staticSearchHostCheckGenPage(String entityVar, SiteRequest siteRequest_, Object o) {
    switch(entityVar) {
    case "listHostCheck":
      return HostCheckGenPage.staticSearchListHostCheck(siteRequest_, (JsonArray)o);
    case "resultCount":
      return HostCheckGenPage.staticSearchResultCount(siteRequest_, (Integer)o);
    case "pk":
      return HostCheckGenPage.staticSearchPk(siteRequest_, (Long)o);
    case "solrId":
      return HostCheckGenPage.staticSearchSolrId(siteRequest_, (String)o);
    case "pageUriHostCheck":
      return HostCheckGenPage.staticSearchPageUriHostCheck(siteRequest_, (String)o);
      default:
        return PageLayout.staticSearchPageLayout(entityVar,  siteRequest_, o);
    }
  }

  ///////////////////
  // staticSearchStr //
  ///////////////////

  public static String staticSearchStrForClass(String entityVar, SiteRequest siteRequest_, Object o) {
    return staticSearchStrHostCheckGenPage(entityVar,  siteRequest_, o);
  }
  public static String staticSearchStrHostCheckGenPage(String entityVar, SiteRequest siteRequest_, Object o) {
    switch(entityVar) {
    case "listHostCheck":
      return HostCheckGenPage.staticSearchStrListHostCheck(siteRequest_, (String)o);
    case "resultCount":
      return HostCheckGenPage.staticSearchStrResultCount(siteRequest_, (Integer)o);
    case "pk":
      return HostCheckGenPage.staticSearchStrPk(siteRequest_, (Long)o);
    case "solrId":
      return HostCheckGenPage.staticSearchStrSolrId(siteRequest_, (String)o);
    case "pageUriHostCheck":
      return HostCheckGenPage.staticSearchStrPageUriHostCheck(siteRequest_, (String)o);
      default:
        return PageLayout.staticSearchStrPageLayout(entityVar,  siteRequest_, o);
    }
  }

  //////////////////
  // staticSearchFq //
  //////////////////

  public static String staticSearchFqForClass(String entityVar, SiteRequest siteRequest_, String o) {
    return staticSearchFqHostCheckGenPage(entityVar,  siteRequest_, o);
  }
  public static String staticSearchFqHostCheckGenPage(String entityVar, SiteRequest siteRequest_, String o) {
    switch(entityVar) {
    case "listHostCheck":
      return HostCheckGenPage.staticSearchFqListHostCheck(siteRequest_, o);
    case "resultCount":
      return HostCheckGenPage.staticSearchFqResultCount(siteRequest_, o);
    case "pk":
      return HostCheckGenPage.staticSearchFqPk(siteRequest_, o);
    case "solrId":
      return HostCheckGenPage.staticSearchFqSolrId(siteRequest_, o);
    case "pageUriHostCheck":
      return HostCheckGenPage.staticSearchFqPageUriHostCheck(siteRequest_, o);
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

  public static final String CLASS_SIMPLE_NAME = "HostCheckGenPage";
  public static final String CLASS_CANONICAL_NAME = "org.computate.dcm.model.eda.hostcheck.HostCheckGenPage";
  public static final String CLASS_AUTH_RESOURCE = "";
  public static final String VAR_searchListHostCheck_ = "searchListHostCheck_";
  public static final String VAR_listHostCheck = "listHostCheck";
  public static final String VAR_resultCount = "resultCount";
  public static final String VAR_result = "result";
  public static final String VAR_pk = "pk";
  public static final String VAR_solrId = "solrId";
  public static final String VAR_pageUriHostCheck = "pageUriHostCheck";

  public static final String DISPLAY_NAME_searchListHostCheck_ = "";
  public static final String DISPLAY_NAME_listHostCheck = "";
  public static final String DISPLAY_NAME_resultCount = "";
  public static final String DISPLAY_NAME_result = "";
  public static final String DISPLAY_NAME_pk = "";
  public static final String DISPLAY_NAME_solrId = "";
  public static final String DISPLAY_NAME_pageUriHostCheck = "";

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
    return HostCheckGenPage.displayNameHostCheckGenPage(var);
  }
  public static String displayNameHostCheckGenPage(String var) {
    switch(var) {
    case VAR_searchListHostCheck_:
      return DISPLAY_NAME_searchListHostCheck_;
    case VAR_listHostCheck:
      return DISPLAY_NAME_listHostCheck;
    case VAR_resultCount:
      return DISPLAY_NAME_resultCount;
    case VAR_result:
      return DISPLAY_NAME_result;
    case VAR_pk:
      return DISPLAY_NAME_pk;
    case VAR_solrId:
      return DISPLAY_NAME_solrId;
    case VAR_pageUriHostCheck:
      return DISPLAY_NAME_pageUriHostCheck;
    default:
      return PageLayout.displayNamePageLayout(var);
    }
  }

  public static String descriptionHostCheckGenPage(String var) {
    if(var == null)
      return null;
    switch(var) {
      default:
        return PageLayout.descriptionPageLayout(var);
    }
  }

  public static String classSimpleNameHostCheckGenPage(String var) {
    switch(var) {
    case VAR_searchListHostCheck_:
      return "SearchList";
    case VAR_listHostCheck:
      return "JsonArray";
    case VAR_resultCount:
      return "Integer";
    case VAR_result:
      return "HostCheck";
    case VAR_pk:
      return "Long";
    case VAR_solrId:
      return "String";
    case VAR_pageUriHostCheck:
      return "String";
      default:
        return PageLayout.classSimpleNamePageLayout(var);
    }
  }

  public static Integer htmColumnHostCheckGenPage(String var) {
    switch(var) {
      default:
        return PageLayout.htmColumnPageLayout(var);
    }
  }

  public static Integer htmRowHostCheckGenPage(String var) {
    switch(var) {
      default:
        return PageLayout.htmRowPageLayout(var);
    }
  }

  public static Integer htmCellHostCheckGenPage(String var) {
    switch(var) {
      default:
        return PageLayout.htmCellPageLayout(var);
    }
  }

  public static Integer lengthMinHostCheckGenPage(String var) {
    switch(var) {
      default:
        return PageLayout.lengthMinPageLayout(var);
    }
  }

  public static Integer lengthMaxHostCheckGenPage(String var) {
    switch(var) {
      default:
        return PageLayout.lengthMaxPageLayout(var);
    }
  }

  public static Integer maxHostCheckGenPage(String var) {
    switch(var) {
      default:
        return PageLayout.maxPageLayout(var);
    }
  }

  public static Integer minHostCheckGenPage(String var) {
    switch(var) {
      default:
        return PageLayout.minPageLayout(var);
    }
  }
}
