package org.computate.dcm.model.eda.tenant.discovered;

import org.computate.dcm.model.eda.tenant.discovered.TenantDiscovered;
import java.util.List;
import java.lang.String;
import org.computate.dcm.page.PageLayout;
import org.computate.dcm.request.SiteRequest;
import org.computate.dcm.user.SiteUser;
import java.io.IOException;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;
import org.computate.vertx.search.list.SearchList;
import org.computate.search.wrap.Wrap;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.Duration;
import java.time.Instant;
import java.util.Locale;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.api.service.ServiceRequest;
import io.vertx.core.json.JsonArray;
import java.net.URLDecoder;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.commons.lang3.StringUtils;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;
import java.util.stream.Collectors;
import java.util.Arrays;
import javax.measure.Quantity;
import javax.measure.quantity.Angle;
import javax.measure.quantity.Length;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.math.MathContext;
import java.util.Objects;
import io.vertx.core.Promise;
import org.computate.dcm.config.ConfigKeys;
import org.computate.search.response.solr.SolrResponse;
import java.util.HashMap;
import org.computate.search.tool.TimeTool;
import org.computate.search.tool.SearchTool;
import java.time.ZoneId;
import io.vertx.pgclient.data.Point;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
import org.computate.vertx.search.list.SearchList;
import org.computate.dcm.model.eda.tenant.discovered.TenantDiscovered;
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
import io.vertx.core.json.JsonArray;
import org.computate.vertx.serialize.vertx.JsonArrayDeserializer;
import java.lang.Void;
import org.computate.search.wrap.Wrap;
import io.vertx.core.Promise;
import io.vertx.core.Future;
import org.computate.search.tool.SearchTool;

/**
 * <ol>
<h3>Suggestions that can generate more code for you: </h3> * </ol>
 * <li><p>
 *   You can add a class comment <kbd><b>Api: true</b></kbd> if you wish to GET, POST, PATCH or PUT these  objects in a RESTful API. 
 * </p>
 * </li><li>You can add a class comment "{@inheritDoc}" if you wish to inherit the helpful inherited class comments from class TenantDiscoveredGenPageGen into the class TenantDiscoveredGenPage. 
 * </li>
 * <h3>About the TenantDiscoveredGenPage class and it's generated class TenantDiscoveredGenPageGen&lt;PageLayout&gt;: </h3>extends TenantDiscoveredGenPageGen
 * <p>
 * This Java class extends a generated Java class TenantDiscoveredGenPageGen built by the <a href="https://github.com/computate-org/computate">https://github.com/computate-org/computate</a> project. 
 * Whenever this Java class is modified or touched, the watch service installed as described in the README, indexes all the information about this Java class in a local Apache Solr Search Engine. 
 * If you are running the service, you can see the indexed data about this Java Class here: 
 * </p>
 * <p><a href="https://solr.apps-crc.testing/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.tenant.discovered.TenantDiscoveredGenPage">Find the class TenantDiscoveredGenPage in Solr. </a></p>
 * <p>
 * The extended class ending with "Gen" did not exist at first, but was automatically created by the same watch service based on the data retrieved from the local Apache Server search engine. 
 * The extended class contains many generated fields, getters, setters, initialization code, and helper methods to help build a website and API fast, reactive, and scalable. 
 * </p>
 * extends TenantDiscoveredGenPageGen<PageLayout>
 * <p>This <code>class TenantDiscoveredGenPage extends TenantDiscoveredGenPageGen&lt;PageLayout&gt;</code>, which means it extends a newly generated TenantDiscoveredGenPageGen. 
 * The generated <code>class TenantDiscoveredGenPageGen extends PageLayout</code> which means that TenantDiscoveredGenPage extends TenantDiscoveredGenPageGen which extends PageLayout. 
 * This generated inheritance is a powerful feature that allows a lot of boiler plate code to be created for you automatically while still preserving inheritance through the power of Java Generic classes. 
 * </p>
 * <h2>
 *   Api: true
 * </h2>
 * <h2>ApiTag.enUS: true</h2>
 * <h2>ApiUri.enUS: null</h2>
 * <h2>Color: null</h2>
 * <h2>Indexed: true</h2>
 * <h2>{@inheritDoc}</h2>
 * <p>By adding a class comment "{@inheritDoc}", the TenantDiscoveredGenPage class will inherit the helpful inherited class comments from the super class TenantDiscoveredGenPageGen. 
 * </p>
 * <h2>
 *   Rows: 10
 * </h2>
 * <p>This class contains a comment <kbd><b>Rows: 10</b></kbd>, which means the  API will return a default of 10 results instead of 10 by default. 
 * Each API has built in pagination of the search results to ensure a user can query all the data a page at a time without running the application out of memory. 
 * </p>
 * <p>
 *   You can add a class comment <kbd><b>Rows: 100</b></kbd> if you wish for the  API to return more or less than 10 results by default. 
 *   In this case, the API will return 100 results from the API instead of 10 by default. 
 *   Each API has built in pagination of the search results to ensure a user can query all the data a page at a time without running the application out of memory. 
 * </p>
 * <h2>
 *   Order: 1
 * </h2>
 * <p>
 *   This class contains a comment <kbd><b>Order: 1</b></kbd>, 
 *   which means this class will be sorted by the given number 1 
 *   ascending when code that relates to multiple classes at the same time is generated. 
 * </p>
 * <p>
 *   You can add a class comment <kbd><b>Order: </b></kbd>, followed by an Integer to sort this class compared to other classes in the project. 
 *   There is code that is generated that queries several classes and writes code for each class in a sequence. 
 *   The <kbd><b>Order</b></kbd> comment allows you to define which order the class code is generated. 
 * </p>
 * <h2>Model: true</h2>
 * <h2>Page: true</h2>
 * <h2>SuperPage.enUS: null</h2>
 * <h2>Promise: true</h2>
 * <p>
 *   This class contains a comment <kbd><b>Promise: true</b></kbd>
 *   Sometimes a Java class must be initialized asynchronously when it involves calling a blocking API. 
 *   This means that the TenantDiscoveredGenPage Java class has promiseDeep methods which must be initialized asynchronously as a Vert.x Promise  instead of initDeep methods which are a simple non-asynchronous method. 
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
 * Delete the class TenantDiscoveredGenPage in Solr: 
 * <pre>
 * curl -k 'https://solr.apps-crc.testing/solr/computate/update?commitWithin=1000&amp;overwrite=true&amp;wt=json' -X POST -H 'Content-type: text/xml' -u "admin:$(oc -n solr get secret/solr-solrcloud-security-bootstrap -o jsonpath={.data.admin} | base64 -d)" --data-raw '&lt;delete&gt;&lt;query&gt;classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.tenant.discovered.TenantDiscoveredGenPage&lt;/query&gt;&lt;/delete&gt;'
 * </pre>
 * </p>
 * <p>
 * Delete  the package org.computate.dcm.model.eda.tenant.discovered in Solr: 
 * <pre>
 * curl -k 'https://solr.apps-crc.testing/solr/computate/update?commitWithin=1000&amp;overwrite=true&amp;wt=json' -X POST -H 'Content-type: text/xml' -u "admin:$(oc -n solr get secret/solr-solrcloud-security-bootstrap -o jsonpath={.data.admin} | base64 -d)" --data-raw '&lt;delete&gt;&lt;query&gt;classeNomEnsemble_enUS_indexed_string:org.computate.dcm.model.eda.tenant.discovered&lt;/query&gt;&lt;/delete&gt;'
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
public abstract class TenantDiscoveredGenPageGen<DEV> extends PageLayout {
  protected static final Logger LOG = LoggerFactory.getLogger(TenantDiscoveredGenPage.class);

	/////////////////////////////////
  // searchListTenantDiscovered_ //
	/////////////////////////////////


  /**
   *  The entity searchListTenantDiscovered_
   *	 is defined as null before being initialized. 
   */
  @JsonIgnore
  @JsonInclude(Include.NON_NULL)
  protected SearchList<TenantDiscovered> searchListTenantDiscovered_;

  /**
   * <br> The entity searchListTenantDiscovered_
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.tenant.discovered.TenantDiscoveredGenPage&fq=entiteVar_enUS_indexed_string:searchListTenantDiscovered_">Find the entity searchListTenantDiscovered_ in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _searchListTenantDiscovered_(Wrap<SearchList<TenantDiscovered>> w);

  public SearchList<TenantDiscovered> getSearchListTenantDiscovered_() {
    return searchListTenantDiscovered_;
  }

  public void setSearchListTenantDiscovered_(SearchList<TenantDiscovered> searchListTenantDiscovered_) {
    this.searchListTenantDiscovered_ = searchListTenantDiscovered_;
  }
  public static SearchList<TenantDiscovered> staticSetSearchListTenantDiscovered_(SiteRequest siteRequest_, String o) {
    return null;
  }
  protected TenantDiscoveredGenPage searchListTenantDiscovered_Init() {
    Wrap<SearchList<TenantDiscovered>> searchListTenantDiscovered_Wrap = new Wrap<SearchList<TenantDiscovered>>().var("searchListTenantDiscovered_");
    if(searchListTenantDiscovered_ == null) {
      _searchListTenantDiscovered_(searchListTenantDiscovered_Wrap);
      Optional.ofNullable(searchListTenantDiscovered_Wrap.getO()).ifPresent(o -> {
        setSearchListTenantDiscovered_(o);
      });
    }
    return (TenantDiscoveredGenPage)this;
  }

	//////////////////////////
  // listTenantDiscovered //
	//////////////////////////


  /**
   *  The entity listTenantDiscovered
   *	 It is constructed before being initialized with the constructor by default. 
   */
  @JsonProperty
  @JsonDeserialize(using = JsonArrayDeserializer.class)
  @JsonInclude(Include.NON_NULL)
  protected JsonArray listTenantDiscovered = new JsonArray();

  /**
   * <br> The entity listTenantDiscovered
   *  It is constructed before being initialized with the constructor by default. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.tenant.discovered.TenantDiscoveredGenPage&fq=entiteVar_enUS_indexed_string:listTenantDiscovered">Find the entity listTenantDiscovered in Solr</a>
   * <br>
   * @param l is the entity already constructed. 
   **/
  protected abstract void _listTenantDiscovered(JsonArray l);

  public JsonArray getListTenantDiscovered() {
    return listTenantDiscovered;
  }

  public void setListTenantDiscovered(JsonArray listTenantDiscovered) {
    this.listTenantDiscovered = listTenantDiscovered;
  }
  @JsonIgnore
  public void setListTenantDiscovered(String o) {
    this.listTenantDiscovered = TenantDiscoveredGenPage.staticSetListTenantDiscovered(siteRequest_, o);
  }
  public static JsonArray staticSetListTenantDiscovered(SiteRequest siteRequest_, String o) {
    if(o != null) {
        return new JsonArray(o);
    }
    return null;
  }
  protected TenantDiscoveredGenPage listTenantDiscoveredInit() {
    _listTenantDiscovered(listTenantDiscovered);
    return (TenantDiscoveredGenPage)this;
  }

  public static String staticSearchListTenantDiscovered(SiteRequest siteRequest_, JsonArray o) {
    return o.toString();
  }

  public static String staticSearchStrListTenantDiscovered(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqListTenantDiscovered(SiteRequest siteRequest_, String o) {
    return TenantDiscoveredGenPage.staticSearchListTenantDiscovered(siteRequest_, TenantDiscoveredGenPage.staticSetListTenantDiscovered(siteRequest_, o)).toString();
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
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.tenant.discovered.TenantDiscoveredGenPage&fq=entiteVar_enUS_indexed_string:resultCount">Find the entity resultCount in Solr</a>
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
    this.resultCount = TenantDiscoveredGenPage.staticSetResultCount(siteRequest_, o);
  }
  public static Integer staticSetResultCount(SiteRequest siteRequest_, String o) {
    if(NumberUtils.isParsable(o))
      return Integer.parseInt(o);
    return null;
  }
  protected TenantDiscoveredGenPage resultCountInit() {
    Wrap<Integer> resultCountWrap = new Wrap<Integer>().var("resultCount");
    if(resultCount == null) {
      _resultCount(resultCountWrap);
      Optional.ofNullable(resultCountWrap.getO()).ifPresent(o -> {
        setResultCount(o);
      });
    }
    return (TenantDiscoveredGenPage)this;
  }

  public static Integer staticSearchResultCount(SiteRequest siteRequest_, Integer o) {
    return o;
  }

  public static String staticSearchStrResultCount(SiteRequest siteRequest_, Integer o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqResultCount(SiteRequest siteRequest_, String o) {
    return TenantDiscoveredGenPage.staticSearchResultCount(siteRequest_, TenantDiscoveredGenPage.staticSetResultCount(siteRequest_, o)).toString();
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
  protected TenantDiscovered result;

  /**
   * <br> The entity result
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.tenant.discovered.TenantDiscoveredGenPage&fq=entiteVar_enUS_indexed_string:result">Find the entity result in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _result(Wrap<TenantDiscovered> w);

  public TenantDiscovered getResult() {
    return result;
  }

  public void setResult(TenantDiscovered result) {
    this.result = result;
  }
  public static TenantDiscovered staticSetResult(SiteRequest siteRequest_, String o) {
    return null;
  }
  protected TenantDiscoveredGenPage resultInit() {
    Wrap<TenantDiscovered> resultWrap = new Wrap<TenantDiscovered>().var("result");
    if(result == null) {
      _result(resultWrap);
      Optional.ofNullable(resultWrap.getO()).ifPresent(o -> {
        setResult(o);
      });
    }
    return (TenantDiscoveredGenPage)this;
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
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.tenant.discovered.TenantDiscoveredGenPage&fq=entiteVar_enUS_indexed_string:pk">Find the entity pk in Solr</a>
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
    this.pk = TenantDiscoveredGenPage.staticSetPk(siteRequest_, o);
  }
  public static Long staticSetPk(SiteRequest siteRequest_, String o) {
    if(NumberUtils.isParsable(o))
      return Long.parseLong(o);
    return null;
  }
  protected TenantDiscoveredGenPage pkInit() {
    Wrap<Long> pkWrap = new Wrap<Long>().var("pk");
    if(pk == null) {
      _pk(pkWrap);
      Optional.ofNullable(pkWrap.getO()).ifPresent(o -> {
        setPk(o);
      });
    }
    return (TenantDiscoveredGenPage)this;
  }

  public static Long staticSearchPk(SiteRequest siteRequest_, Long o) {
    return o;
  }

  public static String staticSearchStrPk(SiteRequest siteRequest_, Long o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqPk(SiteRequest siteRequest_, String o) {
    return TenantDiscoveredGenPage.staticSearchPk(siteRequest_, TenantDiscoveredGenPage.staticSetPk(siteRequest_, o)).toString();
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
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.tenant.discovered.TenantDiscoveredGenPage&fq=entiteVar_enUS_indexed_string:solrId">Find the entity solrId in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _solrId(Wrap<String> w);

  public String getSolrId() {
    return solrId;
  }
  public void setSolrId(String o) {
    this.solrId = TenantDiscoveredGenPage.staticSetSolrId(siteRequest_, o);
  }
  public static String staticSetSolrId(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected TenantDiscoveredGenPage solrIdInit() {
    Wrap<String> solrIdWrap = new Wrap<String>().var("solrId");
    if(solrId == null) {
      _solrId(solrIdWrap);
      Optional.ofNullable(solrIdWrap.getO()).ifPresent(o -> {
        setSolrId(o);
      });
    }
    return (TenantDiscoveredGenPage)this;
  }

  public static String staticSearchSolrId(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrSolrId(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqSolrId(SiteRequest siteRequest_, String o) {
    return TenantDiscoveredGenPage.staticSearchSolrId(siteRequest_, TenantDiscoveredGenPage.staticSetSolrId(siteRequest_, o)).toString();
  }

	/////////////////////////////
  // pageUriTenantDiscovered //
	/////////////////////////////


  /**
   *  The entity pageUriTenantDiscovered
   *	 is defined as null before being initialized. 
   */
  @JsonProperty
  @JsonInclude(Include.NON_NULL)
  protected String pageUriTenantDiscovered;

  /**
   * <br> The entity pageUriTenantDiscovered
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.tenant.discovered.TenantDiscoveredGenPage&fq=entiteVar_enUS_indexed_string:pageUriTenantDiscovered">Find the entity pageUriTenantDiscovered in Solr</a>
   * <br>
   * @param c is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _pageUriTenantDiscovered(Wrap<String> c);

  public String getPageUriTenantDiscovered() {
    return pageUriTenantDiscovered;
  }
  public void setPageUriTenantDiscovered(String o) {
    this.pageUriTenantDiscovered = TenantDiscoveredGenPage.staticSetPageUriTenantDiscovered(siteRequest_, o);
  }
  public static String staticSetPageUriTenantDiscovered(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected TenantDiscoveredGenPage pageUriTenantDiscoveredInit() {
    Wrap<String> pageUriTenantDiscoveredWrap = new Wrap<String>().var("pageUriTenantDiscovered");
    if(pageUriTenantDiscovered == null) {
      _pageUriTenantDiscovered(pageUriTenantDiscoveredWrap);
      Optional.ofNullable(pageUriTenantDiscoveredWrap.getO()).ifPresent(o -> {
        setPageUriTenantDiscovered(o);
      });
    }
    return (TenantDiscoveredGenPage)this;
  }

  public static String staticSearchPageUriTenantDiscovered(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrPageUriTenantDiscovered(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqPageUriTenantDiscovered(SiteRequest siteRequest_, String o) {
    return TenantDiscoveredGenPage.staticSearchPageUriTenantDiscovered(siteRequest_, TenantDiscoveredGenPage.staticSetPageUriTenantDiscovered(siteRequest_, o)).toString();
  }

  //////////////
  // initDeep //
  //////////////

  public Future<TenantDiscoveredGenPageGen<DEV>> promiseDeepTenantDiscoveredGenPage(SiteRequest siteRequest_) {
    if(this.siteRequest_ == null)
      setSiteRequest_(siteRequest_);
    return promiseDeepTenantDiscoveredGenPage();
  }

  public Future<TenantDiscoveredGenPageGen<DEV>> promiseDeepTenantDiscoveredGenPage() {
    Promise<TenantDiscoveredGenPageGen<DEV>> promise = Promise.promise();
    Promise<Void> promise2 = Promise.promise();
    promiseTenantDiscoveredGenPage(promise2);
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

  public Future<Void> promiseTenantDiscoveredGenPage(Promise<Void> promise) {
    Future.future(a -> a.complete()).compose(a -> {
      Promise<Void> promise2 = Promise.promise();
      try {
        searchListTenantDiscovered_Init();
        listTenantDiscoveredInit();
        resultCountInit();
        resultInit();
        pkInit();
        solrIdInit();
        pageUriTenantDiscoveredInit();
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

  @Override public Future<? extends TenantDiscoveredGenPageGen<DEV>> promiseDeepForClass(SiteRequest siteRequest_) {
    return promiseDeepTenantDiscoveredGenPage(siteRequest_);
  }

  /////////////////
  // siteRequest //
  /////////////////

  public void siteRequestTenantDiscoveredGenPage(SiteRequest siteRequest_) {
      super.siteRequestPageLayout(siteRequest_);
  }

  public void siteRequestForClass(SiteRequest siteRequest_) {
    siteRequestTenantDiscoveredGenPage(siteRequest_);
  }

  /////////////
  // obtain //
  /////////////

  @Override public Object obtainForClass(String var) {
    String[] vars = StringUtils.split(var, ".");
    Object o = null;
    for(String v : vars) {
      if(o == null)
        o = obtainTenantDiscoveredGenPage(v);
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
  public Object obtainTenantDiscoveredGenPage(String var) {
    TenantDiscoveredGenPage oTenantDiscoveredGenPage = (TenantDiscoveredGenPage)this;
    switch(var) {
      case "searchListTenantDiscovered_":
        return oTenantDiscoveredGenPage.searchListTenantDiscovered_;
      case "listTenantDiscovered":
        return oTenantDiscoveredGenPage.listTenantDiscovered;
      case "resultCount":
        return oTenantDiscoveredGenPage.resultCount;
      case "result":
        return oTenantDiscoveredGenPage.result;
      case "pk":
        return oTenantDiscoveredGenPage.pk;
      case "solrId":
        return oTenantDiscoveredGenPage.solrId;
      case "pageUriTenantDiscovered":
        return oTenantDiscoveredGenPage.pageUriTenantDiscovered;
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
        o = relateTenantDiscoveredGenPage(v, val);
      else if(o instanceof BaseModel) {
        BaseModel baseModel = (BaseModel)o;
        o = baseModel.relateForClass(v, val);
      }
    }
    return o != null;
  }
  public Object relateTenantDiscoveredGenPage(String var, Object val) {
    TenantDiscoveredGenPage oTenantDiscoveredGenPage = (TenantDiscoveredGenPage)this;
    switch(var) {
      default:
        return super.relatePageLayout(var, val);
    }
  }

  ///////////////
  // staticSet //
  ///////////////

  public static Object staticSetForClass(String entityVar, SiteRequest siteRequest_, String v, TenantDiscoveredGenPage o) {
    return staticSetTenantDiscoveredGenPage(entityVar,  siteRequest_, v, o);
  }
  public static Object staticSetTenantDiscoveredGenPage(String entityVar, SiteRequest siteRequest_, String v, TenantDiscoveredGenPage o) {
    switch(entityVar) {
    case "listTenantDiscovered":
      return TenantDiscoveredGenPage.staticSetListTenantDiscovered(siteRequest_, v);
    case "resultCount":
      return TenantDiscoveredGenPage.staticSetResultCount(siteRequest_, v);
    case "pk":
      return TenantDiscoveredGenPage.staticSetPk(siteRequest_, v);
    case "solrId":
      return TenantDiscoveredGenPage.staticSetSolrId(siteRequest_, v);
    case "pageUriTenantDiscovered":
      return TenantDiscoveredGenPage.staticSetPageUriTenantDiscovered(siteRequest_, v);
      default:
        return PageLayout.staticSetPageLayout(entityVar,  siteRequest_, v, o);
    }
  }

  //////////////////
  // staticSearch //
  //////////////////

  public static Object staticSearchForClass(String entityVar, SiteRequest siteRequest_, Object o) {
    return staticSearchTenantDiscoveredGenPage(entityVar,  siteRequest_, o);
  }
  public static Object staticSearchTenantDiscoveredGenPage(String entityVar, SiteRequest siteRequest_, Object o) {
    switch(entityVar) {
    case "listTenantDiscovered":
      return TenantDiscoveredGenPage.staticSearchListTenantDiscovered(siteRequest_, (JsonArray)o);
    case "resultCount":
      return TenantDiscoveredGenPage.staticSearchResultCount(siteRequest_, (Integer)o);
    case "pk":
      return TenantDiscoveredGenPage.staticSearchPk(siteRequest_, (Long)o);
    case "solrId":
      return TenantDiscoveredGenPage.staticSearchSolrId(siteRequest_, (String)o);
    case "pageUriTenantDiscovered":
      return TenantDiscoveredGenPage.staticSearchPageUriTenantDiscovered(siteRequest_, (String)o);
      default:
        return PageLayout.staticSearchPageLayout(entityVar,  siteRequest_, o);
    }
  }

  ///////////////////
  // staticSearchStr //
  ///////////////////

  public static String staticSearchStrForClass(String entityVar, SiteRequest siteRequest_, Object o) {
    return staticSearchStrTenantDiscoveredGenPage(entityVar,  siteRequest_, o);
  }
  public static String staticSearchStrTenantDiscoveredGenPage(String entityVar, SiteRequest siteRequest_, Object o) {
    switch(entityVar) {
    case "listTenantDiscovered":
      return TenantDiscoveredGenPage.staticSearchStrListTenantDiscovered(siteRequest_, (String)o);
    case "resultCount":
      return TenantDiscoveredGenPage.staticSearchStrResultCount(siteRequest_, (Integer)o);
    case "pk":
      return TenantDiscoveredGenPage.staticSearchStrPk(siteRequest_, (Long)o);
    case "solrId":
      return TenantDiscoveredGenPage.staticSearchStrSolrId(siteRequest_, (String)o);
    case "pageUriTenantDiscovered":
      return TenantDiscoveredGenPage.staticSearchStrPageUriTenantDiscovered(siteRequest_, (String)o);
      default:
        return PageLayout.staticSearchStrPageLayout(entityVar,  siteRequest_, o);
    }
  }

  //////////////////
  // staticSearchFq //
  //////////////////

  public static String staticSearchFqForClass(String entityVar, SiteRequest siteRequest_, String o) {
    return staticSearchFqTenantDiscoveredGenPage(entityVar,  siteRequest_, o);
  }
  public static String staticSearchFqTenantDiscoveredGenPage(String entityVar, SiteRequest siteRequest_, String o) {
    switch(entityVar) {
    case "listTenantDiscovered":
      return TenantDiscoveredGenPage.staticSearchFqListTenantDiscovered(siteRequest_, o);
    case "resultCount":
      return TenantDiscoveredGenPage.staticSearchFqResultCount(siteRequest_, o);
    case "pk":
      return TenantDiscoveredGenPage.staticSearchFqPk(siteRequest_, o);
    case "solrId":
      return TenantDiscoveredGenPage.staticSearchFqSolrId(siteRequest_, o);
    case "pageUriTenantDiscovered":
      return TenantDiscoveredGenPage.staticSearchFqPageUriTenantDiscovered(siteRequest_, o);
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

  public static final String CLASS_SIMPLE_NAME = "TenantDiscoveredGenPage";
  public static final String CLASS_CANONICAL_NAME = "org.computate.dcm.model.eda.tenant.discovered.TenantDiscoveredGenPage";
  public static final String CLASS_AUTH_RESOURCE = "";
  public static final String VAR_searchListTenantDiscovered_ = "searchListTenantDiscovered_";
  public static final String SET_searchListTenantDiscovered_ = "setSearchListTenantDiscovered_";
  public static final String VAR_listTenantDiscovered = "listTenantDiscovered";
  public static final String SET_listTenantDiscovered = "setListTenantDiscovered";
  public static final String VAR_resultCount = "resultCount";
  public static final String SET_resultCount = "setResultCount";
  public static final String VAR_result = "result";
  public static final String SET_result = "setResult";
  public static final String VAR_pk = "pk";
  public static final String SET_pk = "setPk";
  public static final String VAR_solrId = "solrId";
  public static final String SET_solrId = "setSolrId";
  public static final String VAR_pageUriTenantDiscovered = "pageUriTenantDiscovered";
  public static final String SET_pageUriTenantDiscovered = "setPageUriTenantDiscovered";

  public static final String DISPLAY_NAME_searchListTenantDiscovered_ = "";
  public static final String DISPLAY_NAME_listTenantDiscovered = "";
  public static final String DISPLAY_NAME_resultCount = "";
  public static final String DISPLAY_NAME_result = "";
  public static final String DISPLAY_NAME_pk = "";
  public static final String DISPLAY_NAME_solrId = "";
  public static final String DISPLAY_NAME_pageUriTenantDiscovered = "";

  public static String displayNameForClass(String var) {
    return TenantDiscoveredGenPage.displayNameTenantDiscoveredGenPage(var);
  }
  public static String displayNameTenantDiscoveredGenPage(String var) {
    switch(var) {
    case VAR_searchListTenantDiscovered_:
      return DISPLAY_NAME_searchListTenantDiscovered_;
    case VAR_listTenantDiscovered:
      return DISPLAY_NAME_listTenantDiscovered;
    case VAR_resultCount:
      return DISPLAY_NAME_resultCount;
    case VAR_result:
      return DISPLAY_NAME_result;
    case VAR_pk:
      return DISPLAY_NAME_pk;
    case VAR_solrId:
      return DISPLAY_NAME_solrId;
    case VAR_pageUriTenantDiscovered:
      return DISPLAY_NAME_pageUriTenantDiscovered;
    default:
      return PageLayout.displayNamePageLayout(var);
    }
  }
}
