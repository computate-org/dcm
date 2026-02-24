package org.computate.dcm.model.eda.jobtemplate;

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
import org.computate.dcm.model.eda.jobtemplate.JobTemplate;
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
 * <li>You can add a class comment <b>"Api: true"</b> if you wish to GET, POST, PATCH or PUT these JobTemplateGenPage objects in a RESTful API. 
 * </li><li>You can add a class comment "{@inheritDoc}" if you wish to inherit the helpful inherited class comments from class JobTemplateGenPageGen into the class JobTemplateGenPage. 
 * </li>
 * <h3>About the JobTemplateGenPage class and it's generated class JobTemplateGenPageGen&lt;PageLayout&gt;: </h3>extends JobTemplateGenPageGen
 * <p>
 * This Java class extends a generated Java class JobTemplateGenPageGen built by the <a href="https://github.com/computate-org/computate">https://github.com/computate-org/computate</a> project. 
 * Whenever this Java class is modified or touched, the watch service installed as described in the README, indexes all the information about this Java class in a local Apache Solr Search Engine. 
 * If you are running the service, you can see the indexed data about this Java Class here: 
 * </p>
 * <p><a href="https://solr.apps-crc.testing/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.jobtemplate.JobTemplateGenPage">Find the class JobTemplateGenPage in Solr. </a></p>
 * <p>
 * The extended class ending with "Gen" did not exist at first, but was automatically created by the same watch service based on the data retrieved from the local Apache Server search engine. 
 * The extended class contains many generated fields, getters, setters, initialization code, and helper methods to help build a website and API fast, reactive, and scalable. 
 * </p>
 * extends JobTemplateGenPageGen<PageLayout>
 * <p>This <code>class JobTemplateGenPage extends JobTemplateGenPageGen&lt;PageLayout&gt;</code>, which means it extends a newly generated JobTemplateGenPageGen. 
 * The generated <code>class JobTemplateGenPageGen extends PageLayout</code> which means that JobTemplateGenPage extends JobTemplateGenPageGen which extends PageLayout. 
 * This generated inheritance is a powerful feature that allows a lot of boiler plate code to be created for you automatically while still preserving inheritance through the power of Java Generic classes. 
 * </p>
 * <h2>Api: true</h2>
 * <h2>ApiTag.enUS: true</h2>
 * <h2>ApiUri.enUS: null</h2>
 * <h2>Color: null</h2>
 * <h2>Indexed: true</h2>
 * <h2>{@inheritDoc}</h2>
 * <p>By adding a class comment "{@inheritDoc}", the JobTemplateGenPage class will inherit the helpful inherited class comments from the super class JobTemplateGenPageGen. 
 * </p>
 * <h2>Rows: null</h2>
 * <h2>Model: true</h2>
 * <h2>Page: true</h2>
 * <h2>SuperPage.enUS: null</h2>
 * <h2>Promise: true</h2>
 * <p>
 *   This class contains a comment <b>"Promise: true"</b>
 *   Sometimes a Java class must be initialized asynchronously when it involves calling a blocking API. 
 *   This means that the JobTemplateGenPage Java class has promiseDeep methods which must be initialized asynchronously as a Vert.x Promise  instead of initDeep methods which are a simple non-asynchronous method. 
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
 * Delete the class JobTemplateGenPage in Solr: 
 * <pre>
 * curl -k 'https://solr.apps-crc.testing/solr/computate/update?commitWithin=1000&amp;overwrite=true&amp;wt=json' -X POST -H 'Content-type: text/xml' -u "admin:$(oc -n solr get secret/solr-solrcloud-security-bootstrap -o jsonpath={.data.admin} | base64 -d)" --data-raw '&lt;delete&gt;&lt;query&gt;classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.jobtemplate.JobTemplateGenPage&lt;/query&gt;&lt;/delete&gt;'
 * </pre>
 * </p>
 * <p>
 * Delete  the package org.computate.dcm.model.eda.jobtemplate in Solr: 
 * <pre>
 * curl -k 'https://solr.apps-crc.testing/solr/computate/update?commitWithin=1000&amp;overwrite=true&amp;wt=json' -X POST -H 'Content-type: text/xml' -u "admin:$(oc -n solr get secret/solr-solrcloud-security-bootstrap -o jsonpath={.data.admin} | base64 -d)" --data-raw '&lt;delete&gt;&lt;query&gt;classeNomEnsemble_enUS_indexed_string:org.computate.dcm.model.eda.jobtemplate&lt;/query&gt;&lt;/delete&gt;'
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
public abstract class JobTemplateGenPageGen<DEV> extends PageLayout {
  protected static final Logger LOG = LoggerFactory.getLogger(JobTemplateGenPage.class);

	////////////////////////////
  // searchListJobTemplate_ //
	////////////////////////////


  /**
   *  The entity searchListJobTemplate_
   *	 is defined as null before being initialized. 
   */
  @JsonIgnore
  @JsonInclude(Include.NON_NULL)
  protected SearchList<JobTemplate> searchListJobTemplate_;

  /**
   * <br> The entity searchListJobTemplate_
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.jobtemplate.JobTemplateGenPage&fq=entiteVar_enUS_indexed_string:searchListJobTemplate_">Find the entity searchListJobTemplate_ in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _searchListJobTemplate_(Wrap<SearchList<JobTemplate>> w);

  public SearchList<JobTemplate> getSearchListJobTemplate_() {
    return searchListJobTemplate_;
  }

  public void setSearchListJobTemplate_(SearchList<JobTemplate> searchListJobTemplate_) {
    this.searchListJobTemplate_ = searchListJobTemplate_;
  }
  public static SearchList<JobTemplate> staticSetSearchListJobTemplate_(SiteRequest siteRequest_, String o) {
    return null;
  }
  protected JobTemplateGenPage searchListJobTemplate_Init() {
    Wrap<SearchList<JobTemplate>> searchListJobTemplate_Wrap = new Wrap<SearchList<JobTemplate>>().var("searchListJobTemplate_");
    if(searchListJobTemplate_ == null) {
      _searchListJobTemplate_(searchListJobTemplate_Wrap);
      Optional.ofNullable(searchListJobTemplate_Wrap.getO()).ifPresent(o -> {
        setSearchListJobTemplate_(o);
      });
    }
    return (JobTemplateGenPage)this;
  }

	/////////////////////
  // listJobTemplate //
	/////////////////////


  /**
   *  The entity listJobTemplate
   *	 It is constructed before being initialized with the constructor by default. 
   */
  @JsonProperty
  @JsonDeserialize(using = JsonArrayDeserializer.class)
  @JsonInclude(Include.NON_NULL)
  protected JsonArray listJobTemplate = new JsonArray();

  /**
   * <br> The entity listJobTemplate
   *  It is constructed before being initialized with the constructor by default. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.jobtemplate.JobTemplateGenPage&fq=entiteVar_enUS_indexed_string:listJobTemplate">Find the entity listJobTemplate in Solr</a>
   * <br>
   * @param l is the entity already constructed. 
   **/
  protected abstract void _listJobTemplate(JsonArray l);

  public JsonArray getListJobTemplate() {
    return listJobTemplate;
  }

  public void setListJobTemplate(JsonArray listJobTemplate) {
    this.listJobTemplate = listJobTemplate;
  }
  @JsonIgnore
  public void setListJobTemplate(String o) {
    this.listJobTemplate = JobTemplateGenPage.staticSetListJobTemplate(siteRequest_, o);
  }
  public static JsonArray staticSetListJobTemplate(SiteRequest siteRequest_, String o) {
    if(o != null) {
        return new JsonArray(o);
    }
    return null;
  }
  protected JobTemplateGenPage listJobTemplateInit() {
    _listJobTemplate(listJobTemplate);
    return (JobTemplateGenPage)this;
  }

  public static String staticSearchListJobTemplate(SiteRequest siteRequest_, JsonArray o) {
    return o.toString();
  }

  public static String staticSearchStrListJobTemplate(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqListJobTemplate(SiteRequest siteRequest_, String o) {
    return JobTemplateGenPage.staticSearchListJobTemplate(siteRequest_, JobTemplateGenPage.staticSetListJobTemplate(siteRequest_, o)).toString();
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
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.jobtemplate.JobTemplateGenPage&fq=entiteVar_enUS_indexed_string:resultCount">Find the entity resultCount in Solr</a>
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
    this.resultCount = JobTemplateGenPage.staticSetResultCount(siteRequest_, o);
  }
  public static Integer staticSetResultCount(SiteRequest siteRequest_, String o) {
    if(NumberUtils.isParsable(o))
      return Integer.parseInt(o);
    return null;
  }
  protected JobTemplateGenPage resultCountInit() {
    Wrap<Integer> resultCountWrap = new Wrap<Integer>().var("resultCount");
    if(resultCount == null) {
      _resultCount(resultCountWrap);
      Optional.ofNullable(resultCountWrap.getO()).ifPresent(o -> {
        setResultCount(o);
      });
    }
    return (JobTemplateGenPage)this;
  }

  public static Integer staticSearchResultCount(SiteRequest siteRequest_, Integer o) {
    return o;
  }

  public static String staticSearchStrResultCount(SiteRequest siteRequest_, Integer o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqResultCount(SiteRequest siteRequest_, String o) {
    return JobTemplateGenPage.staticSearchResultCount(siteRequest_, JobTemplateGenPage.staticSetResultCount(siteRequest_, o)).toString();
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
  protected JobTemplate result;

  /**
   * <br> The entity result
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.jobtemplate.JobTemplateGenPage&fq=entiteVar_enUS_indexed_string:result">Find the entity result in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _result(Wrap<JobTemplate> w);

  public JobTemplate getResult() {
    return result;
  }

  public void setResult(JobTemplate result) {
    this.result = result;
  }
  public static JobTemplate staticSetResult(SiteRequest siteRequest_, String o) {
    return null;
  }
  protected JobTemplateGenPage resultInit() {
    Wrap<JobTemplate> resultWrap = new Wrap<JobTemplate>().var("result");
    if(result == null) {
      _result(resultWrap);
      Optional.ofNullable(resultWrap.getO()).ifPresent(o -> {
        setResult(o);
      });
    }
    return (JobTemplateGenPage)this;
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
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.jobtemplate.JobTemplateGenPage&fq=entiteVar_enUS_indexed_string:pk">Find the entity pk in Solr</a>
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
    this.pk = JobTemplateGenPage.staticSetPk(siteRequest_, o);
  }
  public static Long staticSetPk(SiteRequest siteRequest_, String o) {
    if(NumberUtils.isParsable(o))
      return Long.parseLong(o);
    return null;
  }
  protected JobTemplateGenPage pkInit() {
    Wrap<Long> pkWrap = new Wrap<Long>().var("pk");
    if(pk == null) {
      _pk(pkWrap);
      Optional.ofNullable(pkWrap.getO()).ifPresent(o -> {
        setPk(o);
      });
    }
    return (JobTemplateGenPage)this;
  }

  public static Long staticSearchPk(SiteRequest siteRequest_, Long o) {
    return o;
  }

  public static String staticSearchStrPk(SiteRequest siteRequest_, Long o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqPk(SiteRequest siteRequest_, String o) {
    return JobTemplateGenPage.staticSearchPk(siteRequest_, JobTemplateGenPage.staticSetPk(siteRequest_, o)).toString();
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
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.jobtemplate.JobTemplateGenPage&fq=entiteVar_enUS_indexed_string:solrId">Find the entity solrId in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _solrId(Wrap<String> w);

  public String getSolrId() {
    return solrId;
  }
  public void setSolrId(String o) {
    this.solrId = JobTemplateGenPage.staticSetSolrId(siteRequest_, o);
  }
  public static String staticSetSolrId(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected JobTemplateGenPage solrIdInit() {
    Wrap<String> solrIdWrap = new Wrap<String>().var("solrId");
    if(solrId == null) {
      _solrId(solrIdWrap);
      Optional.ofNullable(solrIdWrap.getO()).ifPresent(o -> {
        setSolrId(o);
      });
    }
    return (JobTemplateGenPage)this;
  }

  public static String staticSearchSolrId(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrSolrId(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqSolrId(SiteRequest siteRequest_, String o) {
    return JobTemplateGenPage.staticSearchSolrId(siteRequest_, JobTemplateGenPage.staticSetSolrId(siteRequest_, o)).toString();
  }

	////////////////////////
  // pageUriJobTemplate //
	////////////////////////


  /**
   *  The entity pageUriJobTemplate
   *	 is defined as null before being initialized. 
   */
  @JsonProperty
  @JsonInclude(Include.NON_NULL)
  protected String pageUriJobTemplate;

  /**
   * <br> The entity pageUriJobTemplate
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.jobtemplate.JobTemplateGenPage&fq=entiteVar_enUS_indexed_string:pageUriJobTemplate">Find the entity pageUriJobTemplate in Solr</a>
   * <br>
   * @param c is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _pageUriJobTemplate(Wrap<String> c);

  public String getPageUriJobTemplate() {
    return pageUriJobTemplate;
  }
  public void setPageUriJobTemplate(String o) {
    this.pageUriJobTemplate = JobTemplateGenPage.staticSetPageUriJobTemplate(siteRequest_, o);
  }
  public static String staticSetPageUriJobTemplate(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected JobTemplateGenPage pageUriJobTemplateInit() {
    Wrap<String> pageUriJobTemplateWrap = new Wrap<String>().var("pageUriJobTemplate");
    if(pageUriJobTemplate == null) {
      _pageUriJobTemplate(pageUriJobTemplateWrap);
      Optional.ofNullable(pageUriJobTemplateWrap.getO()).ifPresent(o -> {
        setPageUriJobTemplate(o);
      });
    }
    return (JobTemplateGenPage)this;
  }

  public static String staticSearchPageUriJobTemplate(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrPageUriJobTemplate(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqPageUriJobTemplate(SiteRequest siteRequest_, String o) {
    return JobTemplateGenPage.staticSearchPageUriJobTemplate(siteRequest_, JobTemplateGenPage.staticSetPageUriJobTemplate(siteRequest_, o)).toString();
  }

  //////////////
  // initDeep //
  //////////////

  public Future<JobTemplateGenPageGen<DEV>> promiseDeepJobTemplateGenPage(SiteRequest siteRequest_) {
    setSiteRequest_(siteRequest_);
    return promiseDeepJobTemplateGenPage();
  }

  public Future<JobTemplateGenPageGen<DEV>> promiseDeepJobTemplateGenPage() {
    Promise<JobTemplateGenPageGen<DEV>> promise = Promise.promise();
    Promise<Void> promise2 = Promise.promise();
    promiseJobTemplateGenPage(promise2);
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

  public Future<Void> promiseJobTemplateGenPage(Promise<Void> promise) {
    Future.future(a -> a.complete()).compose(a -> {
      Promise<Void> promise2 = Promise.promise();
      try {
        searchListJobTemplate_Init();
        listJobTemplateInit();
        resultCountInit();
        resultInit();
        pkInit();
        solrIdInit();
        pageUriJobTemplateInit();
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

  @Override public Future<? extends JobTemplateGenPageGen<DEV>> promiseDeepForClass(SiteRequest siteRequest_) {
    return promiseDeepJobTemplateGenPage(siteRequest_);
  }

  /////////////////
  // siteRequest //
  /////////////////

  public void siteRequestJobTemplateGenPage(SiteRequest siteRequest_) {
      super.siteRequestPageLayout(siteRequest_);
  }

  public void siteRequestForClass(SiteRequest siteRequest_) {
    siteRequestJobTemplateGenPage(siteRequest_);
  }

  /////////////
  // obtain //
  /////////////

  @Override public Object obtainForClass(String var) {
    String[] vars = StringUtils.split(var, ".");
    Object o = null;
    for(String v : vars) {
      if(o == null)
        o = obtainJobTemplateGenPage(v);
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
  public Object obtainJobTemplateGenPage(String var) {
    JobTemplateGenPage oJobTemplateGenPage = (JobTemplateGenPage)this;
    switch(var) {
      case "searchListJobTemplate_":
        return oJobTemplateGenPage.searchListJobTemplate_;
      case "listJobTemplate":
        return oJobTemplateGenPage.listJobTemplate;
      case "resultCount":
        return oJobTemplateGenPage.resultCount;
      case "result":
        return oJobTemplateGenPage.result;
      case "pk":
        return oJobTemplateGenPage.pk;
      case "solrId":
        return oJobTemplateGenPage.solrId;
      case "pageUriJobTemplate":
        return oJobTemplateGenPage.pageUriJobTemplate;
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
        o = relateJobTemplateGenPage(v, val);
      else if(o instanceof BaseModel) {
        BaseModel baseModel = (BaseModel)o;
        o = baseModel.relateForClass(v, val);
      }
    }
    return o != null;
  }
  public Object relateJobTemplateGenPage(String var, Object val) {
    JobTemplateGenPage oJobTemplateGenPage = (JobTemplateGenPage)this;
    switch(var) {
      default:
        return super.relatePageLayout(var, val);
    }
  }

  ///////////////
  // staticSet //
  ///////////////

  public static Object staticSetForClass(String entityVar, SiteRequest siteRequest_, String v, JobTemplateGenPage o) {
    return staticSetJobTemplateGenPage(entityVar,  siteRequest_, v, o);
  }
  public static Object staticSetJobTemplateGenPage(String entityVar, SiteRequest siteRequest_, String v, JobTemplateGenPage o) {
    switch(entityVar) {
    case "listJobTemplate":
      return JobTemplateGenPage.staticSetListJobTemplate(siteRequest_, v);
    case "resultCount":
      return JobTemplateGenPage.staticSetResultCount(siteRequest_, v);
    case "pk":
      return JobTemplateGenPage.staticSetPk(siteRequest_, v);
    case "solrId":
      return JobTemplateGenPage.staticSetSolrId(siteRequest_, v);
    case "pageUriJobTemplate":
      return JobTemplateGenPage.staticSetPageUriJobTemplate(siteRequest_, v);
      default:
        return PageLayout.staticSetPageLayout(entityVar,  siteRequest_, v, o);
    }
  }

  //////////////////
  // staticSearch //
  //////////////////

  public static Object staticSearchForClass(String entityVar, SiteRequest siteRequest_, Object o) {
    return staticSearchJobTemplateGenPage(entityVar,  siteRequest_, o);
  }
  public static Object staticSearchJobTemplateGenPage(String entityVar, SiteRequest siteRequest_, Object o) {
    switch(entityVar) {
    case "listJobTemplate":
      return JobTemplateGenPage.staticSearchListJobTemplate(siteRequest_, (JsonArray)o);
    case "resultCount":
      return JobTemplateGenPage.staticSearchResultCount(siteRequest_, (Integer)o);
    case "pk":
      return JobTemplateGenPage.staticSearchPk(siteRequest_, (Long)o);
    case "solrId":
      return JobTemplateGenPage.staticSearchSolrId(siteRequest_, (String)o);
    case "pageUriJobTemplate":
      return JobTemplateGenPage.staticSearchPageUriJobTemplate(siteRequest_, (String)o);
      default:
        return PageLayout.staticSearchPageLayout(entityVar,  siteRequest_, o);
    }
  }

  ///////////////////
  // staticSearchStr //
  ///////////////////

  public static String staticSearchStrForClass(String entityVar, SiteRequest siteRequest_, Object o) {
    return staticSearchStrJobTemplateGenPage(entityVar,  siteRequest_, o);
  }
  public static String staticSearchStrJobTemplateGenPage(String entityVar, SiteRequest siteRequest_, Object o) {
    switch(entityVar) {
    case "listJobTemplate":
      return JobTemplateGenPage.staticSearchStrListJobTemplate(siteRequest_, (String)o);
    case "resultCount":
      return JobTemplateGenPage.staticSearchStrResultCount(siteRequest_, (Integer)o);
    case "pk":
      return JobTemplateGenPage.staticSearchStrPk(siteRequest_, (Long)o);
    case "solrId":
      return JobTemplateGenPage.staticSearchStrSolrId(siteRequest_, (String)o);
    case "pageUriJobTemplate":
      return JobTemplateGenPage.staticSearchStrPageUriJobTemplate(siteRequest_, (String)o);
      default:
        return PageLayout.staticSearchStrPageLayout(entityVar,  siteRequest_, o);
    }
  }

  //////////////////
  // staticSearchFq //
  //////////////////

  public static String staticSearchFqForClass(String entityVar, SiteRequest siteRequest_, String o) {
    return staticSearchFqJobTemplateGenPage(entityVar,  siteRequest_, o);
  }
  public static String staticSearchFqJobTemplateGenPage(String entityVar, SiteRequest siteRequest_, String o) {
    switch(entityVar) {
    case "listJobTemplate":
      return JobTemplateGenPage.staticSearchFqListJobTemplate(siteRequest_, o);
    case "resultCount":
      return JobTemplateGenPage.staticSearchFqResultCount(siteRequest_, o);
    case "pk":
      return JobTemplateGenPage.staticSearchFqPk(siteRequest_, o);
    case "solrId":
      return JobTemplateGenPage.staticSearchFqSolrId(siteRequest_, o);
    case "pageUriJobTemplate":
      return JobTemplateGenPage.staticSearchFqPageUriJobTemplate(siteRequest_, o);
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

  public static final String CLASS_SIMPLE_NAME = "JobTemplateGenPage";
  public static final String CLASS_CANONICAL_NAME = "org.computate.dcm.model.eda.jobtemplate.JobTemplateGenPage";
  public static final String CLASS_AUTH_RESOURCE = "";
  public static final String VAR_searchListJobTemplate_ = "searchListJobTemplate_";
  public static final String SET_searchListJobTemplate_ = "setSearchListJobTemplate_";
  public static final String VAR_listJobTemplate = "listJobTemplate";
  public static final String SET_listJobTemplate = "setListJobTemplate";
  public static final String VAR_resultCount = "resultCount";
  public static final String SET_resultCount = "setResultCount";
  public static final String VAR_result = "result";
  public static final String SET_result = "setResult";
  public static final String VAR_pk = "pk";
  public static final String SET_pk = "setPk";
  public static final String VAR_solrId = "solrId";
  public static final String SET_solrId = "setSolrId";
  public static final String VAR_pageUriJobTemplate = "pageUriJobTemplate";
  public static final String SET_pageUriJobTemplate = "setPageUriJobTemplate";

  public static final String DISPLAY_NAME_searchListJobTemplate_ = "";
  public static final String DISPLAY_NAME_listJobTemplate = "";
  public static final String DISPLAY_NAME_resultCount = "";
  public static final String DISPLAY_NAME_result = "";
  public static final String DISPLAY_NAME_pk = "";
  public static final String DISPLAY_NAME_solrId = "";
  public static final String DISPLAY_NAME_pageUriJobTemplate = "";

  public static String displayNameForClass(String var) {
    return JobTemplateGenPage.displayNameJobTemplateGenPage(var);
  }
  public static String displayNameJobTemplateGenPage(String var) {
    switch(var) {
    case VAR_searchListJobTemplate_:
      return DISPLAY_NAME_searchListJobTemplate_;
    case VAR_listJobTemplate:
      return DISPLAY_NAME_listJobTemplate;
    case VAR_resultCount:
      return DISPLAY_NAME_resultCount;
    case VAR_result:
      return DISPLAY_NAME_result;
    case VAR_pk:
      return DISPLAY_NAME_pk;
    case VAR_solrId:
      return DISPLAY_NAME_solrId;
    case VAR_pageUriJobTemplate:
      return DISPLAY_NAME_pageUriJobTemplate;
    default:
      return PageLayout.displayNamePageLayout(var);
    }
  }
}
