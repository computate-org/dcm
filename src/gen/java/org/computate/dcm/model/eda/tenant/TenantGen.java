package org.computate.dcm.model.eda.tenant;

import java.util.List;
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
import java.lang.Long;
import org.computate.dcm.model.eda.hostinventory.HostInventory;
import io.vertx.core.json.JsonArray;
import org.computate.dcm.model.eda.ansibleproject.AnsibleProject;
import org.computate.search.wrap.Wrap;
import io.vertx.core.Promise;
import io.vertx.core.Future;
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
 * </li><li>You can add a class comment "{@inheritDoc}" if you wish to inherit the helpful inherited class comments from class TenantGen into the class Tenant. 
 * </li><li>You can add a class comment "SqlOrder: " followed by an Integer to sort this class compared when generating the SQL code to create and drop tables. 
 * The Order comment allows you do define which order the SQL code is generated. 
 * </li>
 * <h3>About the Tenant class and it's generated class TenantGen&lt;BaseModel&gt;: </h3>extends TenantGen
 * <p>
 * This Java class extends a generated Java class TenantGen built by the <a href="https://github.com/computate-org/computate">https://github.com/computate-org/computate</a> project. 
 * Whenever this Java class is modified or touched, the watch service installed as described in the README, indexes all the information about this Java class in a local Apache Solr Search Engine. 
 * If you are running the service, you can see the indexed data about this Java Class here: 
 * </p>
 * <p><a href="https://solr.apps-crc.testing/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.tenant.Tenant">Find the class Tenant in Solr. </a></p>
 * <p>
 * The extended class ending with "Gen" did not exist at first, but was automatically created by the same watch service based on the data retrieved from the local Apache Server search engine. 
 * The extended class contains many generated fields, getters, setters, initialization code, and helper methods to help build a website and API fast, reactive, and scalable. 
 * </p>
 * extends TenantGen<BaseModel>
 * <p>This <code>class Tenant extends TenantGen&lt;BaseModel&gt;</code>, which means it extends a newly generated TenantGen. 
 * The generated <code>class TenantGen extends BaseModel</code> which means that Tenant extends TenantGen which extends BaseModel. 
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
 * <p>By adding a class comment "{@inheritDoc}", the Tenant class will inherit the helpful inherited class comments from the super class TenantGen. 
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
 * <p>This class contains a comment <kbd><b>Model: true</b></kbd>, which means this class will be stored in the database. 
 * Every protected void method that begins with "_" that contains a "Persist: true" comment will be a persisted field in the database table. 
 * </p>
 * <h2>Page: true</h2>
 * <h2>SuperPage.enUS: null</h2>
 * <h2>Promise: true</h2>
 * <p>
 *   This class contains a comment <kbd><b>Promise: true</b></kbd>
 *   Sometimes a Java class must be initialized asynchronously when it involves calling a blocking API. 
 *   This means that the Tenant Java class has promiseDeep methods which must be initialized asynchronously as a Vert.x Promise  instead of initDeep methods which are a simple non-asynchronous method. 
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
 * Delete the class Tenant in Solr: 
 * <pre>
 * curl -k 'https://solr.apps-crc.testing/solr/computate/update?commitWithin=1000&amp;overwrite=true&amp;wt=json' -X POST -H 'Content-type: text/xml' -u "admin:$(oc -n solr get secret/solr-solrcloud-security-bootstrap -o jsonpath={.data.admin} | base64 -d)" --data-raw '&lt;delete&gt;&lt;query&gt;classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.tenant.Tenant&lt;/query&gt;&lt;/delete&gt;'
 * </pre>
 * </p>
 * <p>
 * Delete  the package org.computate.dcm.model.eda.tenant in Solr: 
 * <pre>
 * curl -k 'https://solr.apps-crc.testing/solr/computate/update?commitWithin=1000&amp;overwrite=true&amp;wt=json' -X POST -H 'Content-type: text/xml' -u "admin:$(oc -n solr get secret/solr-solrcloud-security-bootstrap -o jsonpath={.data.admin} | base64 -d)" --data-raw '&lt;delete&gt;&lt;query&gt;classeNomEnsemble_enUS_indexed_string:org.computate.dcm.model.eda.tenant&lt;/query&gt;&lt;/delete&gt;'
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
public abstract class TenantGen<DEV> extends BaseModel {
  protected static final Logger LOG = LoggerFactory.getLogger(Tenant.class);

	///////////
  // hubId //
	///////////


  /**
   *  The entity hubId
   *	 is defined as null before being initialized. 
   */
  @JsonProperty
  @JsonInclude(Include.NON_NULL)
  protected String hubId;

  /**
   * <br> The entity hubId
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.tenant.Tenant&fq=entiteVar_enUS_indexed_string:hubId">Find the entity hubId in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _hubId(Wrap<String> w);

  public String getHubId() {
    return hubId;
  }
  public void setHubId(String o) {
    this.hubId = Tenant.staticSetHubId(siteRequest_, o);
  }
  public static String staticSetHubId(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected Tenant hubIdInit() {
    Wrap<String> hubIdWrap = new Wrap<String>().var("hubId");
    if(hubId == null) {
      _hubId(hubIdWrap);
      Optional.ofNullable(hubIdWrap.getO()).ifPresent(o -> {
        setHubId(o);
      });
    }
    return (Tenant)this;
  }

  public static String staticSearchHubId(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrHubId(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqHubId(SiteRequest siteRequest_, String o) {
    return Tenant.staticSearchHubId(siteRequest_, Tenant.staticSetHubId(siteRequest_, o)).toString();
  }

  public String sqlHubId() {
    return hubId;
  }

  public static String staticJsonHubId(String hubId) {
    return hubId;
  }

	/////////////////
  // clusterName //
	/////////////////


  /**
   *  The entity clusterName
   *	 is defined as null before being initialized. 
   */
  @JsonProperty
  @JsonInclude(Include.NON_NULL)
  protected String clusterName;

  /**
   * <br> The entity clusterName
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.tenant.Tenant&fq=entiteVar_enUS_indexed_string:clusterName">Find the entity clusterName in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _clusterName(Wrap<String> w);

  public String getClusterName() {
    return clusterName;
  }
  public void setClusterName(String o) {
    this.clusterName = Tenant.staticSetClusterName(siteRequest_, o);
  }
  public static String staticSetClusterName(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected Tenant clusterNameInit() {
    Wrap<String> clusterNameWrap = new Wrap<String>().var("clusterName");
    if(clusterName == null) {
      _clusterName(clusterNameWrap);
      Optional.ofNullable(clusterNameWrap.getO()).ifPresent(o -> {
        setClusterName(o);
      });
    }
    return (Tenant)this;
  }

  public static String staticSearchClusterName(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrClusterName(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqClusterName(SiteRequest siteRequest_, String o) {
    return Tenant.staticSearchClusterName(siteRequest_, Tenant.staticSetClusterName(siteRequest_, o)).toString();
  }

  public String sqlClusterName() {
    return clusterName;
  }

  public static String staticJsonClusterName(String clusterName) {
    return clusterName;
  }

	///////////////////////
  // aapOrganizationId //
	///////////////////////


  /**
   *  The entity aapOrganizationId
   *	 is defined as null before being initialized. 
   */
  @JsonProperty
  @JsonSerialize(using = ToStringSerializer.class)
  @JsonInclude(Include.NON_NULL)
  protected Long aapOrganizationId;

  /**
   * <br> The entity aapOrganizationId
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.tenant.Tenant&fq=entiteVar_enUS_indexed_string:aapOrganizationId">Find the entity aapOrganizationId in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _aapOrganizationId(Wrap<Long> w);

  public Long getAapOrganizationId() {
    return aapOrganizationId;
  }

  public void setAapOrganizationId(Long aapOrganizationId) {
    this.aapOrganizationId = aapOrganizationId;
  }
  @JsonIgnore
  public void setAapOrganizationId(String o) {
    this.aapOrganizationId = Tenant.staticSetAapOrganizationId(siteRequest_, o);
  }
  public static Long staticSetAapOrganizationId(SiteRequest siteRequest_, String o) {
    if(NumberUtils.isParsable(o))
      return Long.parseLong(o);
    return null;
  }
  protected Tenant aapOrganizationIdInit() {
    Wrap<Long> aapOrganizationIdWrap = new Wrap<Long>().var("aapOrganizationId");
    if(aapOrganizationId == null) {
      _aapOrganizationId(aapOrganizationIdWrap);
      Optional.ofNullable(aapOrganizationIdWrap.getO()).ifPresent(o -> {
        setAapOrganizationId(o);
      });
    }
    return (Tenant)this;
  }

  public static Long staticSearchAapOrganizationId(SiteRequest siteRequest_, Long o) {
    return o;
  }

  public static String staticSearchStrAapOrganizationId(SiteRequest siteRequest_, Long o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqAapOrganizationId(SiteRequest siteRequest_, String o) {
    return Tenant.staticSearchAapOrganizationId(siteRequest_, Tenant.staticSetAapOrganizationId(siteRequest_, o)).toString();
  }

  public Long sqlAapOrganizationId() {
    return aapOrganizationId;
  }

  public static String staticJsonAapOrganizationId(Long aapOrganizationId) {
    return Optional.ofNullable(aapOrganizationId).map(v -> v.toString()).orElse(null);
  }

	//////////////////////
  // hostInventoryIds //
	//////////////////////


  /**
   *  The entity hostInventoryIds
   *	 It is constructed before being initialized with the constructor by default. 
   */
  @JsonProperty
  @JsonFormat(shape = JsonFormat.Shape.ARRAY)
  @JsonInclude(Include.NON_NULL)
  protected List<String> hostInventoryIds = new ArrayList<String>();

  /**
   * <br> The entity hostInventoryIds
   *  It is constructed before being initialized with the constructor by default. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.tenant.Tenant&fq=entiteVar_enUS_indexed_string:hostInventoryIds">Find the entity hostInventoryIds in Solr</a>
   * <br>
   * @param l is the entity already constructed. 
   **/
  protected abstract void _hostInventoryIds(List<String> l);

  public List<String> getHostInventoryIds() {
    return hostInventoryIds;
  }

  public void setHostInventoryIds(List<String> hostInventoryIds) {
    this.hostInventoryIds = hostInventoryIds;
  }
  @JsonIgnore
  public void setHostInventoryIds(String o) {
    String l = Tenant.staticSetHostInventoryIds(siteRequest_, o);
    if(l != null)
      addHostInventoryIds(l);
  }
  public static String staticSetHostInventoryIds(SiteRequest siteRequest_, String o) {
    return o;
  }
  public Tenant addHostInventoryIds(String...objects) {
    for(String o : objects) {
      addHostInventoryIds(o);
    }
    return (Tenant)this;
  }
  public Tenant addHostInventoryIds(String o) {
    if(o != null)
      this.hostInventoryIds.add(o);
    return (Tenant)this;
  }
  @JsonIgnore
  public void setHostInventoryIds(JsonArray objects) {
    hostInventoryIds.clear();
    if(objects == null)
      return;
    for(int i = 0; i < objects.size(); i++) {
      String o = objects.getString(i);
      addHostInventoryIds(o);
    }
  }
  protected Tenant hostInventoryIdsInit() {
    _hostInventoryIds(hostInventoryIds);
    return (Tenant)this;
  }

  public static String staticSearchHostInventoryIds(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrHostInventoryIds(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqHostInventoryIds(SiteRequest siteRequest_, String o) {
    return Tenant.staticSearchHostInventoryIds(siteRequest_, Tenant.staticSetHostInventoryIds(siteRequest_, o)).toString();
  }

  public String[] sqlHostInventoryIds() {
    return hostInventoryIds.stream().map(v -> (String)v).toArray(String[]::new);
  }

  public static JsonArray staticJsonHostInventoryIds(List<String> hostInventoryIds) {
    JsonArray a = new JsonArray();
    hostInventoryIds.stream().forEach(v -> a.add(v.toString()));
    return a;
  }

	///////////////////////
  // ansibleProjectIds //
	///////////////////////


  /**
   *  The entity ansibleProjectIds
   *	 It is constructed before being initialized with the constructor by default. 
   */
  @JsonProperty
  @JsonFormat(shape = JsonFormat.Shape.ARRAY)
  @JsonInclude(Include.NON_NULL)
  protected List<String> ansibleProjectIds = new ArrayList<String>();

  /**
   * <br> The entity ansibleProjectIds
   *  It is constructed before being initialized with the constructor by default. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.tenant.Tenant&fq=entiteVar_enUS_indexed_string:ansibleProjectIds">Find the entity ansibleProjectIds in Solr</a>
   * <br>
   * @param l is the entity already constructed. 
   **/
  protected abstract void _ansibleProjectIds(List<String> l);

  public List<String> getAnsibleProjectIds() {
    return ansibleProjectIds;
  }

  public void setAnsibleProjectIds(List<String> ansibleProjectIds) {
    this.ansibleProjectIds = ansibleProjectIds;
  }
  @JsonIgnore
  public void setAnsibleProjectIds(String o) {
    String l = Tenant.staticSetAnsibleProjectIds(siteRequest_, o);
    if(l != null)
      addAnsibleProjectIds(l);
  }
  public static String staticSetAnsibleProjectIds(SiteRequest siteRequest_, String o) {
    return o;
  }
  public Tenant addAnsibleProjectIds(String...objects) {
    for(String o : objects) {
      addAnsibleProjectIds(o);
    }
    return (Tenant)this;
  }
  public Tenant addAnsibleProjectIds(String o) {
    if(o != null)
      this.ansibleProjectIds.add(o);
    return (Tenant)this;
  }
  @JsonIgnore
  public void setAnsibleProjectIds(JsonArray objects) {
    ansibleProjectIds.clear();
    if(objects == null)
      return;
    for(int i = 0; i < objects.size(); i++) {
      String o = objects.getString(i);
      addAnsibleProjectIds(o);
    }
  }
  protected Tenant ansibleProjectIdsInit() {
    _ansibleProjectIds(ansibleProjectIds);
    return (Tenant)this;
  }

  public static String staticSearchAnsibleProjectIds(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrAnsibleProjectIds(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqAnsibleProjectIds(SiteRequest siteRequest_, String o) {
    return Tenant.staticSearchAnsibleProjectIds(siteRequest_, Tenant.staticSetAnsibleProjectIds(siteRequest_, o)).toString();
  }

  public String[] sqlAnsibleProjectIds() {
    return ansibleProjectIds.stream().map(v -> (String)v).toArray(String[]::new);
  }

  public static JsonArray staticJsonAnsibleProjectIds(List<String> ansibleProjectIds) {
    JsonArray a = new JsonArray();
    ansibleProjectIds.stream().forEach(v -> a.add(v.toString()));
    return a;
  }

  //////////////
  // initDeep //
  //////////////

  public Future<TenantGen<DEV>> promiseDeepTenant(SiteRequest siteRequest_) {
    if(this.siteRequest_ == null)
      setSiteRequest_(siteRequest_);
    return promiseDeepTenant();
  }

  public Future<TenantGen<DEV>> promiseDeepTenant() {
    Promise<TenantGen<DEV>> promise = Promise.promise();
    Promise<Void> promise2 = Promise.promise();
    promiseTenant(promise2);
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

  public Future<Void> promiseTenant(Promise<Void> promise) {
    Future.future(a -> a.complete()).compose(a -> {
      Promise<Void> promise2 = Promise.promise();
      try {
        hubIdInit();
        clusterNameInit();
        aapOrganizationIdInit();
        hostInventoryIdsInit();
        ansibleProjectIdsInit();
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

  @Override public Future<? extends TenantGen<DEV>> promiseDeepForClass(SiteRequest siteRequest_) {
    return promiseDeepTenant(siteRequest_);
  }

  /////////////////
  // siteRequest //
  /////////////////

  public void siteRequestTenant(SiteRequest siteRequest_) {
      super.siteRequestBaseModel(siteRequest_);
  }

  public void siteRequestForClass(SiteRequest siteRequest_) {
    siteRequestTenant(siteRequest_);
  }

  /////////////
  // obtain //
  /////////////

  @Override public Object obtainForClass(String var) {
    String[] vars = StringUtils.split(var, ".");
    Object o = null;
    for(String v : vars) {
      if(o == null)
        o = obtainTenant(v);
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
  public Object obtainTenant(String var) {
    Tenant oTenant = (Tenant)this;
    switch(var) {
      case "hubId":
        return oTenant.hubId;
      case "clusterName":
        return oTenant.clusterName;
      case "aapOrganizationId":
        return oTenant.aapOrganizationId;
      case "hostInventoryIds":
        return oTenant.hostInventoryIds;
      case "ansibleProjectIds":
        return oTenant.ansibleProjectIds;
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
        o = relateTenant(v, val);
      else if(o instanceof BaseModel) {
        BaseModel baseModel = (BaseModel)o;
        o = baseModel.relateForClass(v, val);
      }
    }
    return o != null;
  }
  public Object relateTenant(String var, Object val) {
    Tenant oTenant = (Tenant)this;
    switch(var) {
      case "hostInventoryIds":
        oTenant.addHostInventoryIds((String)val);
        if(!saves.contains("hostInventoryIds"))
          saves.add("hostInventoryIds");
        return val;
      case "ansibleProjectIds":
        oTenant.addAnsibleProjectIds((String)val);
        if(!saves.contains("ansibleProjectIds"))
          saves.add("ansibleProjectIds");
        return val;
      default:
        return super.relateBaseModel(var, val);
    }
  }

  ///////////////
  // staticSet //
  ///////////////

  public static Object staticSetForClass(String entityVar, SiteRequest siteRequest_, String v, Tenant o) {
    return staticSetTenant(entityVar,  siteRequest_, v, o);
  }
  public static Object staticSetTenant(String entityVar, SiteRequest siteRequest_, String v, Tenant o) {
    switch(entityVar) {
    case "hubId":
      return Tenant.staticSetHubId(siteRequest_, v);
    case "clusterName":
      return Tenant.staticSetClusterName(siteRequest_, v);
    case "aapOrganizationId":
      return Tenant.staticSetAapOrganizationId(siteRequest_, v);
    case "hostInventoryIds":
      return Tenant.staticSetHostInventoryIds(siteRequest_, v);
    case "ansibleProjectIds":
      return Tenant.staticSetAnsibleProjectIds(siteRequest_, v);
      default:
        return BaseModel.staticSetBaseModel(entityVar,  siteRequest_, v, o);
    }
  }

  //////////////////
  // staticSearch //
  //////////////////

  public static Future<Tenant> fqTenant(SiteRequest siteRequest, String var, Object val) {
    Promise<Tenant> promise = Promise.promise();
    try {
      if(val == null) {
        promise.complete();
      } else {
        SearchList<Tenant> searchList = new SearchList<Tenant>();
        searchList.setStore(true);
        searchList.q("*:*");
        searchList.setC(Tenant.class);
        searchList.fq(String.format("%s:", Tenant.varIndexedTenant(var)) + SearchTool.escapeQueryChars(val.toString()));
        searchList.promiseDeepForClass(siteRequest).onSuccess(a -> {
          try {
            promise.complete(searchList.getList().stream().findFirst().orElse(null));
          } catch(Throwable ex) {
            LOG.error("Error while querying null", ex);
            promise.fail(ex);
          }
        }).onFailure(ex -> {
          LOG.error("Error while querying null", ex);
          promise.fail(ex);
        });
      }
    } catch(Throwable ex) {
      LOG.error("Error while querying null", ex);
      promise.fail(ex);
    }
    return promise.future();
  }

  public static Object staticSearchForClass(String entityVar, SiteRequest siteRequest_, Object o) {
    return staticSearchTenant(entityVar,  siteRequest_, o);
  }
  public static Object staticSearchTenant(String entityVar, SiteRequest siteRequest_, Object o) {
    switch(entityVar) {
    case "hubId":
      return Tenant.staticSearchHubId(siteRequest_, (String)o);
    case "clusterName":
      return Tenant.staticSearchClusterName(siteRequest_, (String)o);
    case "aapOrganizationId":
      return Tenant.staticSearchAapOrganizationId(siteRequest_, (Long)o);
    case "hostInventoryIds":
      return Tenant.staticSearchHostInventoryIds(siteRequest_, (String)o);
    case "ansibleProjectIds":
      return Tenant.staticSearchAnsibleProjectIds(siteRequest_, (String)o);
      default:
        return BaseModel.staticSearchBaseModel(entityVar,  siteRequest_, o);
    }
  }

  ///////////////////
  // staticSearchStr //
  ///////////////////

  public static String staticSearchStrForClass(String entityVar, SiteRequest siteRequest_, Object o) {
    return staticSearchStrTenant(entityVar,  siteRequest_, o);
  }
  public static String staticSearchStrTenant(String entityVar, SiteRequest siteRequest_, Object o) {
    switch(entityVar) {
    case "hubId":
      return Tenant.staticSearchStrHubId(siteRequest_, (String)o);
    case "clusterName":
      return Tenant.staticSearchStrClusterName(siteRequest_, (String)o);
    case "aapOrganizationId":
      return Tenant.staticSearchStrAapOrganizationId(siteRequest_, (Long)o);
    case "hostInventoryIds":
      return Tenant.staticSearchStrHostInventoryIds(siteRequest_, (String)o);
    case "ansibleProjectIds":
      return Tenant.staticSearchStrAnsibleProjectIds(siteRequest_, (String)o);
      default:
        return BaseModel.staticSearchStrBaseModel(entityVar,  siteRequest_, o);
    }
  }

  //////////////////
  // staticSearchFq //
  //////////////////

  public static String staticSearchFqForClass(String entityVar, SiteRequest siteRequest_, String o) {
    return staticSearchFqTenant(entityVar,  siteRequest_, o);
  }
  public static String staticSearchFqTenant(String entityVar, SiteRequest siteRequest_, String o) {
    switch(entityVar) {
    case "hubId":
      return Tenant.staticSearchFqHubId(siteRequest_, o);
    case "clusterName":
      return Tenant.staticSearchFqClusterName(siteRequest_, o);
    case "aapOrganizationId":
      return Tenant.staticSearchFqAapOrganizationId(siteRequest_, o);
    case "hostInventoryIds":
      return Tenant.staticSearchFqHostInventoryIds(siteRequest_, o);
    case "ansibleProjectIds":
      return Tenant.staticSearchFqAnsibleProjectIds(siteRequest_, o);
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
          o = persistTenant(v, val);
        else if(o instanceof BaseModel) {
          BaseModel oBaseModel = (BaseModel)o;
          o = oBaseModel.persistForClass(v, val);
        }
      }
    }
    return o != null;
  }
  public Object persistTenant(String var, Object val) {
    String varLower = var.toLowerCase();
      if("hubid".equals(varLower)) {
        if(val instanceof String) {
          setHubId((String)val);
        }
        saves.add("hubId");
        return val;
      } else if("clustername".equals(varLower)) {
        if(val instanceof String) {
          setClusterName((String)val);
        }
        saves.add("clusterName");
        return val;
      } else if("aaporganizationid".equals(varLower)) {
        if(val instanceof Long) {
          setAapOrganizationId((Long)val);
        } else {
          setAapOrganizationId(val == null ? null : val.toString());
        }
        saves.add("aapOrganizationId");
        return val;
    } else {
      return super.persistBaseModel(var, val);
    }
  }

  /////////////
  // populate //
  /////////////

  @Override public void populateForClass(SolrResponse.Doc doc) {
    populateTenant(doc);
  }
  public void populateTenant(SolrResponse.Doc doc) {
    Tenant oTenant = (Tenant)this;
    saves = Optional.ofNullable((ArrayList<String>)doc.get("saves_docvalues_strings")).orElse(new ArrayList<String>());
    if(saves != null) {

      if(saves.contains("hubId")) {
        String hubId = (String)doc.get("hubId_docvalues_string");
        if(hubId != null)
          oTenant.setHubId(hubId);
      }

      if(saves.contains("clusterName")) {
        String clusterName = (String)doc.get("clusterName_docvalues_string");
        if(clusterName != null)
          oTenant.setClusterName(clusterName);
      }

      if(saves.contains("aapOrganizationId")) {
        Long aapOrganizationId = (Long)doc.get("aapOrganizationId_docvalues_long");
        if(aapOrganizationId != null)
          oTenant.setAapOrganizationId(aapOrganizationId);
      }

      List<String> hostInventoryIds = (List<String>)doc.get("hostInventoryIds_docvalues_strings");
      if(hostInventoryIds != null)
        oTenant.hostInventoryIds.addAll(hostInventoryIds);

      List<String> ansibleProjectIds = (List<String>)doc.get("ansibleProjectIds_docvalues_strings");
      if(ansibleProjectIds != null)
        oTenant.ansibleProjectIds.addAll(ansibleProjectIds);
    }

    super.populateBaseModel(doc);
  }

  public void indexTenant(JsonObject doc) {
    if(hubId != null) {
      doc.put("hubId_docvalues_string", hubId);
    }
    if(clusterName != null) {
      doc.put("clusterName_docvalues_string", clusterName);
    }
    if(aapOrganizationId != null) {
      doc.put("aapOrganizationId_docvalues_long", aapOrganizationId);
    }
    if(hostInventoryIds != null) {
      JsonArray l = new JsonArray();
      doc.put("hostInventoryIds_docvalues_strings", l);
      for(String o : hostInventoryIds) {
        l.add(Tenant.staticSearchHostInventoryIds(siteRequest_, o));
      }
    }
    if(ansibleProjectIds != null) {
      JsonArray l = new JsonArray();
      doc.put("ansibleProjectIds_docvalues_strings", l);
      for(String o : ansibleProjectIds) {
        l.add(Tenant.staticSearchAnsibleProjectIds(siteRequest_, o));
      }
    }
    super.indexBaseModel(doc);

	}

  public static String varStoredTenant(String entityVar) {
    switch(entityVar) {
      case "hubId":
        return "hubId_docvalues_string";
      case "clusterName":
        return "clusterName_docvalues_string";
      case "aapOrganizationId":
        return "aapOrganizationId_docvalues_long";
      case "hostInventoryIds":
        return "hostInventoryIds_docvalues_strings";
      case "ansibleProjectIds":
        return "ansibleProjectIds_docvalues_strings";
      default:
        return BaseModel.varStoredBaseModel(entityVar);
    }
  }

  public static String varIndexedTenant(String entityVar) {
    switch(entityVar) {
      case "hubId":
        return "hubId_docvalues_string";
      case "clusterName":
        return "clusterName_docvalues_string";
      case "aapOrganizationId":
        return "aapOrganizationId_docvalues_long";
      case "hostInventoryIds":
        return "hostInventoryIds_docvalues_strings";
      case "ansibleProjectIds":
        return "ansibleProjectIds_docvalues_strings";
      default:
        return BaseModel.varIndexedBaseModel(entityVar);
    }
  }

  public static String searchVarTenant(String searchVar) {
    switch(searchVar) {
      case "hubId_docvalues_string":
        return "hubId";
      case "clusterName_docvalues_string":
        return "clusterName";
      case "aapOrganizationId_docvalues_long":
        return "aapOrganizationId";
      case "hostInventoryIds_docvalues_strings":
        return "hostInventoryIds";
      case "ansibleProjectIds_docvalues_strings":
        return "ansibleProjectIds";
      default:
        return BaseModel.searchVarBaseModel(searchVar);
    }
  }

  public static String varSearchTenant(String entityVar) {
    switch(entityVar) {
      default:
        return BaseModel.varSearchBaseModel(entityVar);
    }
  }

  public static String varSuggestedTenant(String entityVar) {
    switch(entityVar) {
      default:
        return BaseModel.varSuggestedBaseModel(entityVar);
    }
  }

  /////////////
  // store //
  /////////////

  @Override public void storeForClass(SolrResponse.Doc doc) {
    storeTenant(doc);
  }
  public void storeTenant(SolrResponse.Doc doc) {
    Tenant oTenant = (Tenant)this;
    SiteRequest siteRequest = oTenant.getSiteRequest_();

    oTenant.setHubId(Optional.ofNullable(doc.get("hubId_docvalues_string")).map(v -> v.toString()).orElse(null));
    oTenant.setClusterName(Optional.ofNullable(doc.get("clusterName_docvalues_string")).map(v -> v.toString()).orElse(null));
    oTenant.setAapOrganizationId(Optional.ofNullable(doc.get("aapOrganizationId_docvalues_long")).map(v -> v.toString()).orElse(null));
    Optional.ofNullable((List<?>)doc.get("hostInventoryIds_docvalues_strings")).orElse(Arrays.asList()).stream().filter(v -> v != null).forEach(v -> {
      oTenant.addHostInventoryIds(Tenant.staticSetHostInventoryIds(siteRequest, v.toString()));
    });
    Optional.ofNullable((List<?>)doc.get("ansibleProjectIds_docvalues_strings")).orElse(Arrays.asList()).stream().filter(v -> v != null).forEach(v -> {
      oTenant.addAnsibleProjectIds(Tenant.staticSetAnsibleProjectIds(siteRequest, v.toString()));
    });

    super.storeBaseModel(doc);
  }

  //////////////////
  // apiRequest //
  //////////////////

  public void apiRequestTenant() {
    ApiRequest apiRequest = Optional.ofNullable(siteRequest_).map(r -> r.getApiRequest_()).orElse(null);
    Object o = Optional.ofNullable(apiRequest).map(ApiRequest::getOriginal).orElse(null);
    if(o != null && o instanceof Tenant) {
      Tenant original = (Tenant)o;
      if(!Objects.equals(hubId, original.getHubId()))
        apiRequest.addVars("hubId");
      if(!Objects.equals(clusterName, original.getClusterName()))
        apiRequest.addVars("clusterName");
      if(!Objects.equals(aapOrganizationId, original.getAapOrganizationId()))
        apiRequest.addVars("aapOrganizationId");
      if(!Objects.equals(hostInventoryIds, original.getHostInventoryIds()))
        apiRequest.addVars("hostInventoryIds");
      if(!Objects.equals(ansibleProjectIds, original.getAnsibleProjectIds()))
        apiRequest.addVars("ansibleProjectIds");
      super.apiRequestBaseModel();
    }
  }

  //////////////
  // toString //
  //////////////

  @Override public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(super.toString());
    sb.append(Optional.ofNullable(hubId).map(v -> "hubId: \"" + v + "\"\n" ).orElse(""));
    sb.append(Optional.ofNullable(clusterName).map(v -> "clusterName: \"" + v + "\"\n" ).orElse(""));
    sb.append(Optional.ofNullable(aapOrganizationId).map(v -> "aapOrganizationId: " + v + "\n").orElse(""));
    sb.append(Optional.ofNullable(hostInventoryIds).map(v -> "hostInventoryIds: " + v + "\n").orElse(""));
    sb.append(Optional.ofNullable(ansibleProjectIds).map(v -> "ansibleProjectIds: " + v + "\n").orElse(""));
    return sb.toString();
  }

  public static final String CLASS_SIMPLE_NAME = "Tenant";
  public static final String CLASS_CANONICAL_NAME = "org.computate.dcm.model.eda.tenant.Tenant";
  public static final String CLASS_AUTH_RESOURCE = "";
  public static final String VAR_hubId = "hubId";
  public static final String SET_hubId = "setHubId";
  public static final String VAR_clusterName = "clusterName";
  public static final String SET_clusterName = "setClusterName";
  public static final String VAR_aapOrganizationId = "aapOrganizationId";
  public static final String SET_aapOrganizationId = "setAapOrganizationId";
  public static final String VAR_hostInventoryIds = "hostInventoryIds";
  public static final String SET_hostInventoryIds = "setHostInventoryIds";
  public static final String VAR_ansibleProjectIds = "ansibleProjectIds";
  public static final String SET_ansibleProjectIds = "setAnsibleProjectIds";

  public static List<String> varsQForClass() {
    return Tenant.varsQTenant(new ArrayList<String>());
  }
  public static List<String> varsQTenant(List<String> vars) {
    BaseModel.varsQBaseModel(vars);
    return vars;
  }

  public static List<String> varsFqForClass() {
    return Tenant.varsFqTenant(new ArrayList<String>());
  }
  public static List<String> varsFqTenant(List<String> vars) {
    vars.add(VAR_hubId);
    vars.add(VAR_clusterName);
    BaseModel.varsFqBaseModel(vars);
    return vars;
  }

  public static List<String> varsRangeForClass() {
    return Tenant.varsRangeTenant(new ArrayList<String>());
  }
  public static List<String> varsRangeTenant(List<String> vars) {
    BaseModel.varsRangeBaseModel(vars);
    return vars;
  }

  public static final String DISPLAY_NAME_hubId = "ACM Hub";
  public static final String DISPLAY_NAME_clusterName = "cluster name";
  public static final String DISPLAY_NAME_aapOrganizationId = "AAP ID";
  public static final String DISPLAY_NAME_hostInventoryIds = "host inventories";
  public static final String DISPLAY_NAME_ansibleProjectIds = "Ansible projects";

  @Override
  public String titleForClass() {
    return objectTitle;
  }

  public static String varJson(String var, Boolean patch) {
    return Tenant.varJsonTenant(var, patch);
  }
  public static String varJsonTenant(String var, Boolean patch) {
    switch(var) {
    case VAR_hubId:
      return patch ? SET_hubId : VAR_hubId;
    case VAR_clusterName:
      return patch ? SET_clusterName : VAR_clusterName;
    case VAR_aapOrganizationId:
      return patch ? SET_aapOrganizationId : VAR_aapOrganizationId;
    case VAR_hostInventoryIds:
      return patch ? SET_hostInventoryIds : VAR_hostInventoryIds;
    case VAR_ansibleProjectIds:
      return patch ? SET_ansibleProjectIds : VAR_ansibleProjectIds;
    default:
      return BaseModel.varJsonBaseModel(var, patch);
    }
  }

  public static String displayNameForClass(String var) {
    return Tenant.displayNameTenant(var);
  }
  public static String displayNameTenant(String var) {
    switch(var) {
    case VAR_hubId:
      return DISPLAY_NAME_hubId;
    case VAR_clusterName:
      return DISPLAY_NAME_clusterName;
    case VAR_aapOrganizationId:
      return DISPLAY_NAME_aapOrganizationId;
    case VAR_hostInventoryIds:
      return DISPLAY_NAME_hostInventoryIds;
    case VAR_ansibleProjectIds:
      return DISPLAY_NAME_ansibleProjectIds;
    default:
      return BaseModel.displayNameBaseModel(var);
    }
  }

  public static String descriptionTenant(String var) {
    if(var == null)
      return null;
    switch(var) {
    case VAR_hubId:
      return "The ID of the ACM Hub for this cluster in Prometheus Keycloak Proxy. ";
    case VAR_clusterName:
      return "The name of this cluster";
    case VAR_aapOrganizationId:
      return "The Ansible Automation Platform ID of the organization. ";
    case VAR_hostInventoryIds:
      return "The related host inventories for this tenant. ";
    case VAR_ansibleProjectIds:
      return "The related Ansible projects for this tenant. ";
      default:
        return BaseModel.descriptionBaseModel(var);
    }
  }

  public static String classSimpleNameTenant(String var) {
    switch(var) {
    case VAR_hubId:
      return "String";
    case VAR_clusterName:
      return "String";
    case VAR_aapOrganizationId:
      return "Long";
    case VAR_hostInventoryIds:
      return "List";
    case VAR_ansibleProjectIds:
      return "List";
      default:
        return BaseModel.classSimpleNameBaseModel(var);
    }
  }

  public static Integer htmColumnTenant(String var) {
    switch(var) {
      default:
        return BaseModel.htmColumnBaseModel(var);
    }
  }

  public static Integer htmRowTenant(String var) {
    switch(var) {
    case VAR_hostInventoryIds:
      return 24;
    case VAR_ansibleProjectIds:
      return 24;
      default:
        return BaseModel.htmRowBaseModel(var);
    }
  }

  public static Integer htmCellTenant(String var) {
    switch(var) {
    case VAR_hostInventoryIds:
      return 0;
    case VAR_ansibleProjectIds:
      return 0;
      default:
        return BaseModel.htmCellBaseModel(var);
    }
  }

  public static Integer lengthMinTenant(String var) {
    switch(var) {
      default:
        return BaseModel.lengthMinBaseModel(var);
    }
  }

  public static Integer lengthMaxTenant(String var) {
    switch(var) {
      default:
        return BaseModel.lengthMaxBaseModel(var);
    }
  }

  public static Integer maxTenant(String var) {
    switch(var) {
      default:
        return BaseModel.maxBaseModel(var);
    }
  }

  public static Integer minTenant(String var) {
    switch(var) {
      default:
        return BaseModel.minBaseModel(var);
    }
  }
}
