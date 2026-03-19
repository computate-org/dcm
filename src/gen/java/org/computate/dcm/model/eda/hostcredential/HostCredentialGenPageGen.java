package org.computate.dcm.model.eda.hostcredential;

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
import org.computate.vertx.search.list.SearchList;
import org.computate.dcm.model.eda.hostcredential.HostCredential;
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
 * <li>You can add a class comment <b>"Api: true"</b> if you wish to GET, POST, PATCH or PUT these HostCredentialGenPage objects in a RESTful API. 
 * </li><li>You can add a class comment "{@inheritDoc}" if you wish to inherit the helpful inherited class comments from class HostCredentialGenPageGen into the class HostCredentialGenPage. 
 * </li>
 * <h3>About the HostCredentialGenPage class and it's generated class HostCredentialGenPageGen&lt;PageLayout&gt;: </h3>extends HostCredentialGenPageGen
 * <p>
 * This Java class extends a generated Java class HostCredentialGenPageGen built by the <a href="https://github.com/computate-org/computate">https://github.com/computate-org/computate</a> project. 
 * Whenever this Java class is modified or touched, the watch service installed as described in the README, indexes all the information about this Java class in a local Apache Solr Search Engine. 
 * If you are running the service, you can see the indexed data about this Java Class here: 
 * </p>
 * <p><a href="https://solr.apps-crc.testing/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.hostcredential.HostCredentialGenPage">Find the class HostCredentialGenPage in Solr. </a></p>
 * <p>
 * The extended class ending with "Gen" did not exist at first, but was automatically created by the same watch service based on the data retrieved from the local Apache Server search engine. 
 * The extended class contains many generated fields, getters, setters, initialization code, and helper methods to help build a website and API fast, reactive, and scalable. 
 * </p>
 * extends HostCredentialGenPageGen<PageLayout>
 * <p>This <code>class HostCredentialGenPage extends HostCredentialGenPageGen&lt;PageLayout&gt;</code>, which means it extends a newly generated HostCredentialGenPageGen. 
 * The generated <code>class HostCredentialGenPageGen extends PageLayout</code> which means that HostCredentialGenPage extends HostCredentialGenPageGen which extends PageLayout. 
 * This generated inheritance is a powerful feature that allows a lot of boiler plate code to be created for you automatically while still preserving inheritance through the power of Java Generic classes. 
 * </p>
 * <h2>Api: true</h2>
 * <h2>ApiTag.enUS: true</h2>
 * <h2>ApiUri.enUS: null</h2>
 * <h2>Color: null</h2>
 * <h2>Indexed: true</h2>
 * <h2>{@inheritDoc}</h2>
 * <p>By adding a class comment "{@inheritDoc}", the HostCredentialGenPage class will inherit the helpful inherited class comments from the super class HostCredentialGenPageGen. 
 * </p>
 * <h2>Rows: null</h2>
 * <h2>Model: true</h2>
 * <h2>Page: true</h2>
 * <h2>SuperPage.enUS: null</h2>
 * <h2>Promise: true</h2>
 * <p>
 *   This class contains a comment <b>"Promise: true"</b>
 *   Sometimes a Java class must be initialized asynchronously when it involves calling a blocking API. 
 *   This means that the HostCredentialGenPage Java class has promiseDeep methods which must be initialized asynchronously as a Vert.x Promise  instead of initDeep methods which are a simple non-asynchronous method. 
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
 * Delete the class HostCredentialGenPage in Solr: 
 * <pre>
 * curl -k 'https://solr.apps-crc.testing/solr/computate/update?commitWithin=1000&amp;overwrite=true&amp;wt=json' -X POST -H 'Content-type: text/xml' -u "admin:$(oc -n solr get secret/solr-solrcloud-security-bootstrap -o jsonpath={.data.admin} | base64 -d)" --data-raw '&lt;delete&gt;&lt;query&gt;classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.hostcredential.HostCredentialGenPage&lt;/query&gt;&lt;/delete&gt;'
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
public abstract class HostCredentialGenPageGen<DEV> extends PageLayout {
  protected static final Logger LOG = LoggerFactory.getLogger(HostCredentialGenPage.class);

	///////////////////////////////
  // searchListHostCredential_ //
	///////////////////////////////


  /**
   *  The entity searchListHostCredential_
   *	 is defined as null before being initialized. 
   */
  @JsonIgnore
  @JsonInclude(Include.NON_NULL)
  protected SearchList<HostCredential> searchListHostCredential_;

  /**
   * <br> The entity searchListHostCredential_
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.hostcredential.HostCredentialGenPage&fq=entiteVar_enUS_indexed_string:searchListHostCredential_">Find the entity searchListHostCredential_ in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _searchListHostCredential_(Wrap<SearchList<HostCredential>> w);

  public SearchList<HostCredential> getSearchListHostCredential_() {
    return searchListHostCredential_;
  }

  public void setSearchListHostCredential_(SearchList<HostCredential> searchListHostCredential_) {
    this.searchListHostCredential_ = searchListHostCredential_;
  }
  public static SearchList<HostCredential> staticSetSearchListHostCredential_(SiteRequest siteRequest_, String o) {
    return null;
  }
  protected HostCredentialGenPage searchListHostCredential_Init() {
    Wrap<SearchList<HostCredential>> searchListHostCredential_Wrap = new Wrap<SearchList<HostCredential>>().var("searchListHostCredential_");
    if(searchListHostCredential_ == null) {
      _searchListHostCredential_(searchListHostCredential_Wrap);
      Optional.ofNullable(searchListHostCredential_Wrap.getO()).ifPresent(o -> {
        setSearchListHostCredential_(o);
      });
    }
    return (HostCredentialGenPage)this;
  }

	////////////////////////
  // listHostCredential //
	////////////////////////


  /**
   *  The entity listHostCredential
   *	 It is constructed before being initialized with the constructor by default. 
   */
  @JsonProperty
  @JsonDeserialize(using = JsonArrayDeserializer.class)
  @JsonInclude(Include.NON_NULL)
  protected JsonArray listHostCredential = new JsonArray();

  /**
   * <br> The entity listHostCredential
   *  It is constructed before being initialized with the constructor by default. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.hostcredential.HostCredentialGenPage&fq=entiteVar_enUS_indexed_string:listHostCredential">Find the entity listHostCredential in Solr</a>
   * <br>
   * @param l is the entity already constructed. 
   **/
  protected abstract void _listHostCredential(JsonArray l);

  public JsonArray getListHostCredential() {
    return listHostCredential;
  }

  public void setListHostCredential(JsonArray listHostCredential) {
    this.listHostCredential = listHostCredential;
  }
  @JsonIgnore
  public void setListHostCredential(String o) {
    this.listHostCredential = HostCredentialGenPage.staticSetListHostCredential(siteRequest_, o);
  }
  public static JsonArray staticSetListHostCredential(SiteRequest siteRequest_, String o) {
    if(o != null) {
        return new JsonArray(o);
    }
    return null;
  }
  protected HostCredentialGenPage listHostCredentialInit() {
    _listHostCredential(listHostCredential);
    return (HostCredentialGenPage)this;
  }

  public static String staticSearchListHostCredential(SiteRequest siteRequest_, JsonArray o) {
    return o.toString();
  }

  public static String staticSearchStrListHostCredential(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqListHostCredential(SiteRequest siteRequest_, String o) {
    return HostCredentialGenPage.staticSearchListHostCredential(siteRequest_, HostCredentialGenPage.staticSetListHostCredential(siteRequest_, o)).toString();
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
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.hostcredential.HostCredentialGenPage&fq=entiteVar_enUS_indexed_string:resultCount">Find the entity resultCount in Solr</a>
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
    this.resultCount = HostCredentialGenPage.staticSetResultCount(siteRequest_, o);
  }
  public static Integer staticSetResultCount(SiteRequest siteRequest_, String o) {
    if(NumberUtils.isParsable(o))
      return Integer.parseInt(o);
    return null;
  }
  protected HostCredentialGenPage resultCountInit() {
    Wrap<Integer> resultCountWrap = new Wrap<Integer>().var("resultCount");
    if(resultCount == null) {
      _resultCount(resultCountWrap);
      Optional.ofNullable(resultCountWrap.getO()).ifPresent(o -> {
        setResultCount(o);
      });
    }
    return (HostCredentialGenPage)this;
  }

  public static Integer staticSearchResultCount(SiteRequest siteRequest_, Integer o) {
    return o;
  }

  public static String staticSearchStrResultCount(SiteRequest siteRequest_, Integer o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqResultCount(SiteRequest siteRequest_, String o) {
    return HostCredentialGenPage.staticSearchResultCount(siteRequest_, HostCredentialGenPage.staticSetResultCount(siteRequest_, o)).toString();
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
  protected HostCredential result;

  /**
   * <br> The entity result
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.hostcredential.HostCredentialGenPage&fq=entiteVar_enUS_indexed_string:result">Find the entity result in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _result(Wrap<HostCredential> w);

  public HostCredential getResult() {
    return result;
  }

  public void setResult(HostCredential result) {
    this.result = result;
  }
  public static HostCredential staticSetResult(SiteRequest siteRequest_, String o) {
    return null;
  }
  protected HostCredentialGenPage resultInit() {
    Wrap<HostCredential> resultWrap = new Wrap<HostCredential>().var("result");
    if(result == null) {
      _result(resultWrap);
      Optional.ofNullable(resultWrap.getO()).ifPresent(o -> {
        setResult(o);
      });
    }
    return (HostCredentialGenPage)this;
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
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.hostcredential.HostCredentialGenPage&fq=entiteVar_enUS_indexed_string:pk">Find the entity pk in Solr</a>
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
    this.pk = HostCredentialGenPage.staticSetPk(siteRequest_, o);
  }
  public static Long staticSetPk(SiteRequest siteRequest_, String o) {
    if(NumberUtils.isParsable(o))
      return Long.parseLong(o);
    return null;
  }
  protected HostCredentialGenPage pkInit() {
    Wrap<Long> pkWrap = new Wrap<Long>().var("pk");
    if(pk == null) {
      _pk(pkWrap);
      Optional.ofNullable(pkWrap.getO()).ifPresent(o -> {
        setPk(o);
      });
    }
    return (HostCredentialGenPage)this;
  }

  public static Long staticSearchPk(SiteRequest siteRequest_, Long o) {
    return o;
  }

  public static String staticSearchStrPk(SiteRequest siteRequest_, Long o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqPk(SiteRequest siteRequest_, String o) {
    return HostCredentialGenPage.staticSearchPk(siteRequest_, HostCredentialGenPage.staticSetPk(siteRequest_, o)).toString();
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
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.hostcredential.HostCredentialGenPage&fq=entiteVar_enUS_indexed_string:solrId">Find the entity solrId in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _solrId(Wrap<String> w);

  public String getSolrId() {
    return solrId;
  }
  public void setSolrId(String o) {
    this.solrId = HostCredentialGenPage.staticSetSolrId(siteRequest_, o);
  }
  public static String staticSetSolrId(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected HostCredentialGenPage solrIdInit() {
    Wrap<String> solrIdWrap = new Wrap<String>().var("solrId");
    if(solrId == null) {
      _solrId(solrIdWrap);
      Optional.ofNullable(solrIdWrap.getO()).ifPresent(o -> {
        setSolrId(o);
      });
    }
    return (HostCredentialGenPage)this;
  }

  public static String staticSearchSolrId(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrSolrId(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqSolrId(SiteRequest siteRequest_, String o) {
    return HostCredentialGenPage.staticSearchSolrId(siteRequest_, HostCredentialGenPage.staticSetSolrId(siteRequest_, o)).toString();
  }

	///////////////////////////
  // pageUriHostCredential //
	///////////////////////////


  /**
   *  The entity pageUriHostCredential
   *	 is defined as null before being initialized. 
   */
  @JsonProperty
  @JsonInclude(Include.NON_NULL)
  protected String pageUriHostCredential;

  /**
   * <br> The entity pageUriHostCredential
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.hostcredential.HostCredentialGenPage&fq=entiteVar_enUS_indexed_string:pageUriHostCredential">Find the entity pageUriHostCredential in Solr</a>
   * <br>
   * @param c is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _pageUriHostCredential(Wrap<String> c);

  public String getPageUriHostCredential() {
    return pageUriHostCredential;
  }
  public void setPageUriHostCredential(String o) {
    this.pageUriHostCredential = HostCredentialGenPage.staticSetPageUriHostCredential(siteRequest_, o);
  }
  public static String staticSetPageUriHostCredential(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected HostCredentialGenPage pageUriHostCredentialInit() {
    Wrap<String> pageUriHostCredentialWrap = new Wrap<String>().var("pageUriHostCredential");
    if(pageUriHostCredential == null) {
      _pageUriHostCredential(pageUriHostCredentialWrap);
      Optional.ofNullable(pageUriHostCredentialWrap.getO()).ifPresent(o -> {
        setPageUriHostCredential(o);
      });
    }
    return (HostCredentialGenPage)this;
  }

  public static String staticSearchPageUriHostCredential(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrPageUriHostCredential(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqPageUriHostCredential(SiteRequest siteRequest_, String o) {
    return HostCredentialGenPage.staticSearchPageUriHostCredential(siteRequest_, HostCredentialGenPage.staticSetPageUriHostCredential(siteRequest_, o)).toString();
  }

  //////////////
  // initDeep //
  //////////////

  public Future<HostCredentialGenPageGen<DEV>> promiseDeepHostCredentialGenPage(SiteRequest siteRequest_) {
    setSiteRequest_(siteRequest_);
    return promiseDeepHostCredentialGenPage();
  }

  public Future<HostCredentialGenPageGen<DEV>> promiseDeepHostCredentialGenPage() {
    Promise<HostCredentialGenPageGen<DEV>> promise = Promise.promise();
    Promise<Void> promise2 = Promise.promise();
    promiseHostCredentialGenPage(promise2);
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

  public Future<Void> promiseHostCredentialGenPage(Promise<Void> promise) {
    Future.future(a -> a.complete()).compose(a -> {
      Promise<Void> promise2 = Promise.promise();
      try {
        searchListHostCredential_Init();
        listHostCredentialInit();
        resultCountInit();
        resultInit();
        pkInit();
        solrIdInit();
        pageUriHostCredentialInit();
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

  @Override public Future<? extends HostCredentialGenPageGen<DEV>> promiseDeepForClass(SiteRequest siteRequest_) {
    return promiseDeepHostCredentialGenPage(siteRequest_);
  }

  /////////////////
  // siteRequest //
  /////////////////

  public void siteRequestHostCredentialGenPage(SiteRequest siteRequest_) {
      super.siteRequestPageLayout(siteRequest_);
  }

  public void siteRequestForClass(SiteRequest siteRequest_) {
    siteRequestHostCredentialGenPage(siteRequest_);
  }

  /////////////
  // obtain //
  /////////////

  @Override public Object obtainForClass(String var) {
    String[] vars = StringUtils.split(var, ".");
    Object o = null;
    for(String v : vars) {
      if(o == null)
        o = obtainHostCredentialGenPage(v);
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
  public Object obtainHostCredentialGenPage(String var) {
    HostCredentialGenPage oHostCredentialGenPage = (HostCredentialGenPage)this;
    switch(var) {
      case "searchListHostCredential_":
        return oHostCredentialGenPage.searchListHostCredential_;
      case "listHostCredential":
        return oHostCredentialGenPage.listHostCredential;
      case "resultCount":
        return oHostCredentialGenPage.resultCount;
      case "result":
        return oHostCredentialGenPage.result;
      case "pk":
        return oHostCredentialGenPage.pk;
      case "solrId":
        return oHostCredentialGenPage.solrId;
      case "pageUriHostCredential":
        return oHostCredentialGenPage.pageUriHostCredential;
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
        o = relateHostCredentialGenPage(v, val);
      else if(o instanceof BaseModel) {
        BaseModel baseModel = (BaseModel)o;
        o = baseModel.relateForClass(v, val);
      }
    }
    return o != null;
  }
  public Object relateHostCredentialGenPage(String var, Object val) {
    HostCredentialGenPage oHostCredentialGenPage = (HostCredentialGenPage)this;
    switch(var) {
      default:
        return super.relatePageLayout(var, val);
    }
  }

  ///////////////
  // staticSet //
  ///////////////

  public static Object staticSetForClass(String entityVar, SiteRequest siteRequest_, String v, HostCredentialGenPage o) {
    return staticSetHostCredentialGenPage(entityVar,  siteRequest_, v, o);
  }
  public static Object staticSetHostCredentialGenPage(String entityVar, SiteRequest siteRequest_, String v, HostCredentialGenPage o) {
    switch(entityVar) {
    case "listHostCredential":
      return HostCredentialGenPage.staticSetListHostCredential(siteRequest_, v);
    case "resultCount":
      return HostCredentialGenPage.staticSetResultCount(siteRequest_, v);
    case "pk":
      return HostCredentialGenPage.staticSetPk(siteRequest_, v);
    case "solrId":
      return HostCredentialGenPage.staticSetSolrId(siteRequest_, v);
    case "pageUriHostCredential":
      return HostCredentialGenPage.staticSetPageUriHostCredential(siteRequest_, v);
      default:
        return PageLayout.staticSetPageLayout(entityVar,  siteRequest_, v, o);
    }
  }

  //////////////////
  // staticSearch //
  //////////////////

  public static Object staticSearchForClass(String entityVar, SiteRequest siteRequest_, Object o) {
    return staticSearchHostCredentialGenPage(entityVar,  siteRequest_, o);
  }
  public static Object staticSearchHostCredentialGenPage(String entityVar, SiteRequest siteRequest_, Object o) {
    switch(entityVar) {
    case "listHostCredential":
      return HostCredentialGenPage.staticSearchListHostCredential(siteRequest_, (JsonArray)o);
    case "resultCount":
      return HostCredentialGenPage.staticSearchResultCount(siteRequest_, (Integer)o);
    case "pk":
      return HostCredentialGenPage.staticSearchPk(siteRequest_, (Long)o);
    case "solrId":
      return HostCredentialGenPage.staticSearchSolrId(siteRequest_, (String)o);
    case "pageUriHostCredential":
      return HostCredentialGenPage.staticSearchPageUriHostCredential(siteRequest_, (String)o);
      default:
        return PageLayout.staticSearchPageLayout(entityVar,  siteRequest_, o);
    }
  }

  ///////////////////
  // staticSearchStr //
  ///////////////////

  public static String staticSearchStrForClass(String entityVar, SiteRequest siteRequest_, Object o) {
    return staticSearchStrHostCredentialGenPage(entityVar,  siteRequest_, o);
  }
  public static String staticSearchStrHostCredentialGenPage(String entityVar, SiteRequest siteRequest_, Object o) {
    switch(entityVar) {
    case "listHostCredential":
      return HostCredentialGenPage.staticSearchStrListHostCredential(siteRequest_, (String)o);
    case "resultCount":
      return HostCredentialGenPage.staticSearchStrResultCount(siteRequest_, (Integer)o);
    case "pk":
      return HostCredentialGenPage.staticSearchStrPk(siteRequest_, (Long)o);
    case "solrId":
      return HostCredentialGenPage.staticSearchStrSolrId(siteRequest_, (String)o);
    case "pageUriHostCredential":
      return HostCredentialGenPage.staticSearchStrPageUriHostCredential(siteRequest_, (String)o);
      default:
        return PageLayout.staticSearchStrPageLayout(entityVar,  siteRequest_, o);
    }
  }

  //////////////////
  // staticSearchFq //
  //////////////////

  public static String staticSearchFqForClass(String entityVar, SiteRequest siteRequest_, String o) {
    return staticSearchFqHostCredentialGenPage(entityVar,  siteRequest_, o);
  }
  public static String staticSearchFqHostCredentialGenPage(String entityVar, SiteRequest siteRequest_, String o) {
    switch(entityVar) {
    case "listHostCredential":
      return HostCredentialGenPage.staticSearchFqListHostCredential(siteRequest_, o);
    case "resultCount":
      return HostCredentialGenPage.staticSearchFqResultCount(siteRequest_, o);
    case "pk":
      return HostCredentialGenPage.staticSearchFqPk(siteRequest_, o);
    case "solrId":
      return HostCredentialGenPage.staticSearchFqSolrId(siteRequest_, o);
    case "pageUriHostCredential":
      return HostCredentialGenPage.staticSearchFqPageUriHostCredential(siteRequest_, o);
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

  public static final String CLASS_SIMPLE_NAME = "HostCredentialGenPage";
  public static final String CLASS_CANONICAL_NAME = "org.computate.dcm.model.eda.hostcredential.HostCredentialGenPage";
  public static final String CLASS_AUTH_RESOURCE = "";
  public static final String VAR_searchListHostCredential_ = "searchListHostCredential_";
  public static final String SET_searchListHostCredential_ = "setSearchListHostCredential_";
  public static final String VAR_listHostCredential = "listHostCredential";
  public static final String SET_listHostCredential = "setListHostCredential";
  public static final String VAR_resultCount = "resultCount";
  public static final String SET_resultCount = "setResultCount";
  public static final String VAR_result = "result";
  public static final String SET_result = "setResult";
  public static final String VAR_pk = "pk";
  public static final String SET_pk = "setPk";
  public static final String VAR_solrId = "solrId";
  public static final String SET_solrId = "setSolrId";
  public static final String VAR_pageUriHostCredential = "pageUriHostCredential";
  public static final String SET_pageUriHostCredential = "setPageUriHostCredential";

  public static final String DISPLAY_NAME_searchListHostCredential_ = "";
  public static final String DISPLAY_NAME_listHostCredential = "";
  public static final String DISPLAY_NAME_resultCount = "";
  public static final String DISPLAY_NAME_result = "";
  public static final String DISPLAY_NAME_pk = "";
  public static final String DISPLAY_NAME_solrId = "";
  public static final String DISPLAY_NAME_pageUriHostCredential = "";

  public static String displayNameForClass(String var) {
    return HostCredentialGenPage.displayNameHostCredentialGenPage(var);
  }
  public static String displayNameHostCredentialGenPage(String var) {
    switch(var) {
    case VAR_searchListHostCredential_:
      return DISPLAY_NAME_searchListHostCredential_;
    case VAR_listHostCredential:
      return DISPLAY_NAME_listHostCredential;
    case VAR_resultCount:
      return DISPLAY_NAME_resultCount;
    case VAR_result:
      return DISPLAY_NAME_result;
    case VAR_pk:
      return DISPLAY_NAME_pk;
    case VAR_solrId:
      return DISPLAY_NAME_solrId;
    case VAR_pageUriHostCredential:
      return DISPLAY_NAME_pageUriHostCredential;
    default:
      return PageLayout.displayNamePageLayout(var);
    }
  }
}
