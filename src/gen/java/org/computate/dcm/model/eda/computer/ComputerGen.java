package org.computate.dcm.model.eda.computer;

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
import java.lang.String;
import java.lang.Long;
import io.vertx.core.json.JsonArray;
import org.computate.search.wrap.Wrap;
import io.vertx.core.Promise;
import io.vertx.core.Future;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.computate.search.response.solr.SolrResponse;

/**
 * <ol>
<h3>Suggestions that can generate more code for you: </h3> * </ol>
 * <li>You can add a class comment <b>"Api: true"</b> if you wish to GET, POST, PATCH or PUT these Computer objects in a RESTful API. 
 * </li><li>You can add a class comment "{@inheritDoc}" if you wish to inherit the helpful inherited class comments from class ComputerGen into the class Computer. 
 * </li>
 * <h3>About the Computer class and it's generated class ComputerGen&lt;BaseModel&gt;: </h3>extends ComputerGen
 * <p>
 * This Java class extends a generated Java class ComputerGen built by the <a href="https://github.com/computate-org/computate">https://github.com/computate-org/computate</a> project. 
 * Whenever this Java class is modified or touched, the watch service installed as described in the README, indexes all the information about this Java class in a local Apache Solr Search Engine. 
 * If you are running the service, you can see the indexed data about this Java Class here: 
 * </p>
 * <p><a href="https://solr.apps-crc.testing/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.computer.Computer">Find the class Computer in Solr. </a></p>
 * <p>
 * The extended class ending with "Gen" did not exist at first, but was automatically created by the same watch service based on the data retrieved from the local Apache Server search engine. 
 * The extended class contains many generated fields, getters, setters, initialization code, and helper methods to help build a website and API fast, reactive, and scalable. 
 * </p>
 * extends ComputerGen<BaseModel>
 * <p>This <code>class Computer extends ComputerGen&lt;BaseModel&gt;</code>, which means it extends a newly generated ComputerGen. 
 * The generated <code>class ComputerGen extends BaseModel</code> which means that Computer extends ComputerGen which extends BaseModel. 
 * This generated inheritance is a powerful feature that allows a lot of boiler plate code to be created for you automatically while still preserving inheritance through the power of Java Generic classes. 
 * </p>
 * <h2>Api: true</h2>
 * <h2>ApiTag.enUS: true</h2>
 * <h2>ApiUri.enUS: null</h2>
 * <h2>Color: null</h2>
 * <h2>Indexed: true</h2>
 * <p>This class contains a comment <b>"Indexed: true"</b>, which means this class will be indexed in the search engine. 
 * Every protected void method that begins with "_" that is marked to be searched with a comment like "Indexed: true", "Stored: true", or "DocValues: true" will be indexed in the search engine. 
 * </p>
 * <h2>{@inheritDoc}</h2>
 * <p>By adding a class comment "{@inheritDoc}", the Computer class will inherit the helpful inherited class comments from the super class ComputerGen. 
 * </p>
 * <h2>Rows: 100</h2>
 * <p>This class contains a comment <b>"Rows: 100"</b>, which means the Computer API will return a default of 100 records instead of 10 by default. 
 * Each API has built in pagination of the search records to ensure a user can query all the data a page at a time without running the application out of memory. 
 * </p>
 * <h2>Order: 3</h2>
 * <p>This class contains a comment <b>"Order: 3"</b>, which means this class will be sorted by the given number 3 ascending when code that relates to multiple classes at the same time is generated. 
 * </p>
 * <h2>SqlOrder: 3</h2>
 * <p>This class contains a comment <b>"SqlOrder: 3"</b>, which means this class will be sorted by the given number 3 ascending when SQL code to create and drop the tables is generated. 
 * </p>
 * <h2>Model: true</h2>
 * <p>This class contains a comment <b>"Model: true"</b>, which means this class will be stored in the database. 
 * Every protected void method that begins with "_" that contains a "Persist: true" comment will be a persisted field in the database table. 
 * </p>
 * <h2>Page: true</h2>
 * <p>This class contains a comment <b>"Page: true"</b>, which means this class will have webpage code generated for these objects. 
 * Java Vert.x backend API code, Handlebars HTML template frontend code, and JavaScript code will all generated and can be extended. 
 * This creates a new Java class org.computate.dcm.model.eda.computer.ComputerPage. 
 * </p>
 * <h2>SuperPage.enUS: PageLayout</h2>
 * <p>This class contains a comment <b>"SuperPage.enUS: PageLayout"</b>, which identifies the Java super class of the page code by it's class simple name "PageLayout". 
 * This means that the newly created class org.computate.dcm.model.eda.computer.ComputerPage extends org.computate.dcm.page.PageLayout. 
 * </p>
 * <h2>Promise: true</h2>
 * <p>
 *   This class contains a comment <b>"Promise: true"</b>
 *   Sometimes a Java class must be initialized asynchronously when it involves calling a blocking API. 
 *   This means that the Computer Java class has promiseDeep methods which must be initialized asynchronously as a Vert.x Promise  instead of initDeep methods which are a simple non-asynchronous method. 
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
 * <h2>AName.enUS: a computer</h2>
 * <p>This class contains a comment <b>"AName.enUS: a computer"</b>, which identifies the language context to describe a Computer as "a computer". 
 * </p>
 * <p>
 * Delete the class Computer in Solr: 
 * curl -k 'https://solr.apps-crc.testing/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.computer.Computer&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * <p>
 * Delete  the package org.computate.dcm.model.eda.computer in Solr: 
 * curl -k 'https://solr.apps-crc.testing/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;classeNomEnsemble_enUS_indexed_string:org.computate.dcm.model.eda.computer&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * <p>
 * Delete  the project dcm in Solr: 
 * curl -k 'https://solr.apps-crc.testing/update?commitWithin=1000&overwrite=true&wt=json' -X POST -H 'Content-type: text/xml' --data-raw '&lt;add&gt;&lt;delete&gt;&lt;query&gt;siteNom_indexed_string:dcm&lt;/query&gt;&lt;/delete&gt;&lt;/add&gt;'
 * </p>
 * Generated: true
 **/
public abstract class ComputerGen<DEV> extends BaseModel {
  protected static final Logger LOG = LoggerFactory.getLogger(Computer.class);

  public static final String Description_enUS = "A computer record for managed computers";
  public static final String AName_enUS = "a computer";
  public static final String This_enUS = "this ";
  public static final String ThisName_enUS = "this computer";
  public static final String A_enUS = "a ";
  public static final String TheName_enUS = "the computer";
  public static final String SingularName_enUS = "computer";
  public static final String PluralName_enUS = "computers";
  public static final String NameActual_enUS = "current computer";
  public static final String AllName_enUS = "all computers";
  public static final String SearchAllNameBy_enUS = "search computers by ";
  public static final String SearchAllName_enUS = "search computers";
  public static final String Title_enUS = "computers";
  public static final String ThePluralName_enUS = "the computers";
  public static final String NoNameFound_enUS = "no computer found";
  public static final String OfName_enUS = "of computer";
  public static final String ANameAdjective_enUS = "a computer";
  public static final String NameAdjectiveSingular_enUS = "computer";
  public static final String NameAdjectivePlural_enUS = "computers";

  public static final String Icon = "<i class=\"fa-duotone fa-regular fa-server\"></i>";
  public static final Integer Rows = 100;

	//////////////////
  // computerName //
	//////////////////


  /**
   *  The entity computerName
   *	 is defined as null before being initialized. 
   */
  @JsonProperty
  @JsonInclude(Include.NON_NULL)
  protected String computerName;

  /**
   * <br> The entity computerName
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.computer.Computer&fq=entiteVar_enUS_indexed_string:computerName">Find the entity computerName in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _computerName(Wrap<String> w);

  public String getComputerName() {
    return computerName;
  }
  public void setComputerName(String o) {
    this.computerName = Computer.staticSetComputerName(siteRequest_, o);
  }
  public static String staticSetComputerName(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected Computer computerNameInit() {
    Wrap<String> computerNameWrap = new Wrap<String>().var("computerName");
    if(computerName == null) {
      _computerName(computerNameWrap);
      Optional.ofNullable(computerNameWrap.getO()).ifPresent(o -> {
        setComputerName(o);
      });
    }
    return (Computer)this;
  }

  public static String staticSearchComputerName(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrComputerName(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqComputerName(SiteRequest siteRequest_, String o) {
    return Computer.staticSearchComputerName(siteRequest_, Computer.staticSetComputerName(siteRequest_, o)).toString();
  }

  public String sqlComputerName() {
    return computerName;
  }

  public static String staticJsonComputerName(String computerName) {
    return computerName;
  }

	/////////////
  // rulePks //
	/////////////


  /**
   *  The entity rulePks
   *	 It is constructed before being initialized with the constructor by default. 
   */
  @JsonProperty
  @JsonFormat(shape = JsonFormat.Shape.ARRAY)
  @JsonInclude(Include.NON_NULL)
  protected List<Long> rulePks = new ArrayList<Long>();

  /**
   * <br> The entity rulePks
   *  It is constructed before being initialized with the constructor by default. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.computer.Computer&fq=entiteVar_enUS_indexed_string:rulePks">Find the entity rulePks in Solr</a>
   * <br>
   * @param w is the entity already constructed. 
   **/
  protected abstract void _rulePks(List<Long> w);

  public List<Long> getRulePks() {
    return rulePks;
  }

  public void setRulePks(List<Long> rulePks) {
    this.rulePks = rulePks;
  }
  @JsonIgnore
  public void setRulePks(String o) {
    Long l = Computer.staticSetRulePks(siteRequest_, o);
    if(l != null)
      addRulePks(l);
  }
  public static Long staticSetRulePks(SiteRequest siteRequest_, String o) {
    if(NumberUtils.isParsable(o))
      return Long.parseLong(o);
    return null;
  }
  public Computer addRulePks(Long...objects) {
    for(Long o : objects) {
      addRulePks(o);
    }
    return (Computer)this;
  }
  public Computer addRulePks(Long o) {
    if(o != null)
      this.rulePks.add(o);
    return (Computer)this;
  }
  @JsonIgnore
  public void setRulePks(JsonArray objects) {
    rulePks.clear();
    if(objects == null)
      return;
    for(int i = 0; i < objects.size(); i++) {
      String o = objects.getString(i);
      setRulePks(o);
    }
  }
  public Computer addRulePks(String o) {
    if(NumberUtils.isParsable(o)) {
      Long p = Long.parseLong(o);
      addRulePks(p);
    }
    return (Computer)this;
  }
  protected Computer rulePksInit() {
    _rulePks(rulePks);
    return (Computer)this;
  }

  public static Long staticSearchRulePks(SiteRequest siteRequest_, Long o) {
    return o;
  }

  public static String staticSearchStrRulePks(SiteRequest siteRequest_, Long o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqRulePks(SiteRequest siteRequest_, String o) {
    return Computer.staticSearchRulePks(siteRequest_, Computer.staticSetRulePks(siteRequest_, o)).toString();
  }

  //////////////
  // initDeep //
  //////////////

  public Future<ComputerGen<DEV>> promiseDeepComputer(SiteRequest siteRequest_) {
    setSiteRequest_(siteRequest_);
    return promiseDeepComputer();
  }

  public Future<ComputerGen<DEV>> promiseDeepComputer() {
    Promise<ComputerGen<DEV>> promise = Promise.promise();
    Promise<Void> promise2 = Promise.promise();
    promiseComputer(promise2);
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

  public Future<Void> promiseComputer(Promise<Void> promise) {
    Future.future(a -> a.complete()).compose(a -> {
      Promise<Void> promise2 = Promise.promise();
      try {
        computerNameInit();
        rulePksInit();
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

  @Override public Future<? extends ComputerGen<DEV>> promiseDeepForClass(SiteRequest siteRequest_) {
    return promiseDeepComputer(siteRequest_);
  }

  /////////////////
  // siteRequest //
  /////////////////

  public void siteRequestComputer(SiteRequest siteRequest_) {
      super.siteRequestBaseModel(siteRequest_);
  }

  public void siteRequestForClass(SiteRequest siteRequest_) {
    siteRequestComputer(siteRequest_);
  }

  /////////////
  // obtain //
  /////////////

  @Override public Object obtainForClass(String var) {
    String[] vars = StringUtils.split(var, ".");
    Object o = null;
    for(String v : vars) {
      if(o == null)
        o = obtainComputer(v);
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
  public Object obtainComputer(String var) {
    Computer oComputer = (Computer)this;
    switch(var) {
      case "computerName":
        return oComputer.computerName;
      case "rulePks":
        return oComputer.rulePks;
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
        o = relateComputer(v, val);
      else if(o instanceof BaseModel) {
        BaseModel baseModel = (BaseModel)o;
        o = baseModel.relateForClass(v, val);
      }
    }
    return o != null;
  }
  public Object relateComputer(String var, Object val) {
    Computer oComputer = (Computer)this;
    switch(var) {
      default:
        return super.relateBaseModel(var, val);
    }
  }

  ///////////////
  // staticSet //
  ///////////////

  public static Object staticSetForClass(String entityVar, SiteRequest siteRequest_, String v, Computer o) {
    return staticSetComputer(entityVar,  siteRequest_, v, o);
  }
  public static Object staticSetComputer(String entityVar, SiteRequest siteRequest_, String v, Computer o) {
    switch(entityVar) {
    case "computerName":
      return Computer.staticSetComputerName(siteRequest_, v);
    case "rulePks":
      return Computer.staticSetRulePks(siteRequest_, v);
      default:
        return BaseModel.staticSetBaseModel(entityVar,  siteRequest_, v, o);
    }
  }

  ////////////////
  // staticSearch //
  ////////////////

  public static Object staticSearchForClass(String entityVar, SiteRequest siteRequest_, Object o) {
    return staticSearchComputer(entityVar,  siteRequest_, o);
  }
  public static Object staticSearchComputer(String entityVar, SiteRequest siteRequest_, Object o) {
    switch(entityVar) {
    case "computerName":
      return Computer.staticSearchComputerName(siteRequest_, (String)o);
    case "rulePks":
      return Computer.staticSearchRulePks(siteRequest_, (Long)o);
      default:
        return BaseModel.staticSearchBaseModel(entityVar,  siteRequest_, o);
    }
  }

  ///////////////////
  // staticSearchStr //
  ///////////////////

  public static String staticSearchStrForClass(String entityVar, SiteRequest siteRequest_, Object o) {
    return staticSearchStrComputer(entityVar,  siteRequest_, o);
  }
  public static String staticSearchStrComputer(String entityVar, SiteRequest siteRequest_, Object o) {
    switch(entityVar) {
    case "computerName":
      return Computer.staticSearchStrComputerName(siteRequest_, (String)o);
    case "rulePks":
      return Computer.staticSearchStrRulePks(siteRequest_, (Long)o);
      default:
        return BaseModel.staticSearchStrBaseModel(entityVar,  siteRequest_, o);
    }
  }

  //////////////////
  // staticSearchFq //
  //////////////////

  public static String staticSearchFqForClass(String entityVar, SiteRequest siteRequest_, String o) {
    return staticSearchFqComputer(entityVar,  siteRequest_, o);
  }
  public static String staticSearchFqComputer(String entityVar, SiteRequest siteRequest_, String o) {
    switch(entityVar) {
    case "computerName":
      return Computer.staticSearchFqComputerName(siteRequest_, o);
    case "rulePks":
      return Computer.staticSearchFqRulePks(siteRequest_, o);
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
          o = persistComputer(v, val);
        else if(o instanceof BaseModel) {
          BaseModel oBaseModel = (BaseModel)o;
          o = oBaseModel.persistForClass(v, val);
        }
      }
    }
    return o != null;
  }
  public Object persistComputer(String var, Object val) {
    String varLower = var.toLowerCase();
      if("computername".equals(varLower)) {
        if(val instanceof String) {
          setComputerName((String)val);
        }
        saves.add("computerName");
        return val;
    } else {
      return super.persistBaseModel(var, val);
    }
  }

  /////////////
  // populate //
  /////////////

  @Override public void populateForClass(SolrResponse.Doc doc) {
    populateComputer(doc);
  }
  public void populateComputer(SolrResponse.Doc doc) {
    Computer oComputer = (Computer)this;
    saves = Optional.ofNullable((ArrayList<String>)doc.get("saves_docvalues_strings")).orElse(new ArrayList<String>());
    if(saves != null) {

      if(saves.contains("computerName")) {
        String computerName = (String)doc.get("computerName_docvalues_string");
        if(computerName != null)
          oComputer.setComputerName(computerName);
      }

      if(saves.contains("rulePks")) {
        List<Long> rulePks = (List<Long>)doc.get("rulePks_docvalues_longs");
        if(rulePks != null) {
          rulePks.stream().forEach( v -> {
            oComputer.rulePks.add(v);
          });
        }
      }
    }

    super.populateBaseModel(doc);
  }

  public void indexComputer(JsonObject doc) {
    if(computerName != null) {
      doc.put("computerName_docvalues_string", computerName);
    }
    if(rulePks != null) {
      JsonArray l = new JsonArray();
      doc.put("rulePks_docvalues_longs", l);
      for(Long o : rulePks) {
        l.add(Computer.staticSearchRulePks(siteRequest_, o));
      }
    }
    super.indexBaseModel(doc);

	}

  public static String varStoredComputer(String entityVar) {
    switch(entityVar) {
      case "computerName":
        return "computerName_docvalues_string";
      case "rulePks":
        return "rulePks_docvalues_longs";
      default:
        return BaseModel.varStoredBaseModel(entityVar);
    }
  }

  public static String varIndexedComputer(String entityVar) {
    switch(entityVar) {
      case "computerName":
        return "computerName_docvalues_string";
      case "rulePks":
        return "rulePks_docvalues_longs";
      default:
        return BaseModel.varIndexedBaseModel(entityVar);
    }
  }

  public static String searchVarComputer(String searchVar) {
    switch(searchVar) {
      case "computerName_docvalues_string":
        return "computerName";
      case "rulePks_docvalues_longs":
        return "rulePks";
      default:
        return BaseModel.searchVarBaseModel(searchVar);
    }
  }

  public static String varSearchComputer(String entityVar) {
    switch(entityVar) {
      default:
        return BaseModel.varSearchBaseModel(entityVar);
    }
  }

  public static String varSuggestedComputer(String entityVar) {
    switch(entityVar) {
      default:
        return BaseModel.varSuggestedBaseModel(entityVar);
    }
  }

  /////////////
  // store //
  /////////////

  @Override public void storeForClass(SolrResponse.Doc doc) {
    storeComputer(doc);
  }
  public void storeComputer(SolrResponse.Doc doc) {
    Computer oComputer = (Computer)this;
    SiteRequest siteRequest = oComputer.getSiteRequest_();

    oComputer.setComputerName(Optional.ofNullable(doc.get("computerName_docvalues_string")).map(v -> v.toString()).orElse(null));
    Optional.ofNullable((List<?>)doc.get("rulePks_docvalues_longs")).orElse(Arrays.asList()).stream().filter(v -> v != null).forEach(v -> {
      oComputer.addRulePks(Computer.staticSetRulePks(siteRequest, v.toString()));
    });

    super.storeBaseModel(doc);
  }

  //////////////////
  // apiRequest //
  //////////////////

  public void apiRequestComputer() {
    ApiRequest apiRequest = Optional.ofNullable(siteRequest_).map(r -> r.getApiRequest_()).orElse(null);
    Object o = Optional.ofNullable(apiRequest).map(ApiRequest::getOriginal).orElse(null);
    if(o != null && o instanceof Computer) {
      Computer original = (Computer)o;
      if(!Objects.equals(computerName, original.getComputerName()))
        apiRequest.addVars("computerName");
      if(!Objects.equals(rulePks, original.getRulePks()))
        apiRequest.addVars("rulePks");
      super.apiRequestBaseModel();
    }
  }

  //////////////
  // toString //
  //////////////

  @Override public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(super.toString());
    sb.append(Optional.ofNullable(computerName).map(v -> "computerName: \"" + v + "\"\n" ).orElse(""));
    sb.append(Optional.ofNullable(rulePks).map(v -> "rulePks: " + v + "\n").orElse(""));
    return sb.toString();
  }

  public static final String CLASS_SIMPLE_NAME = "Computer";
  public static final String CLASS_CANONICAL_NAME = "org.computate.dcm.model.eda.computer.Computer";
  public static final String CLASS_AUTH_RESOURCE = "";
  public static final String VAR_computerName = "computerName";
  public static final String VAR_rulePks = "rulePks";

  public static List<String> varsQForClass() {
    return Computer.varsQComputer(new ArrayList<String>());
  }
  public static List<String> varsQComputer(List<String> vars) {
    BaseModel.varsQBaseModel(vars);
    return vars;
  }

  public static List<String> varsFqForClass() {
    return Computer.varsFqComputer(new ArrayList<String>());
  }
  public static List<String> varsFqComputer(List<String> vars) {
    vars.add(VAR_computerName);
    BaseModel.varsFqBaseModel(vars);
    return vars;
  }

  public static List<String> varsRangeForClass() {
    return Computer.varsRangeComputer(new ArrayList<String>());
  }
  public static List<String> varsRangeComputer(List<String> vars) {
    BaseModel.varsRangeBaseModel(vars);
    return vars;
  }

  public static final String DISPLAY_NAME_computerName = "Fully Qualified Domain Name";
  public static final String DISPLAY_NAME_rulePks = "Rules";

  @Override
  public String idForClass() {
    return null;
  }

  @Override
  public String titleForClass() {
    return objectTitle;
  }

  @Override
  public String nameForClass() {
    return null;
  }

  @Override
  public String classNameAdjectiveSingularForClass() {
    return Computer.NameAdjectiveSingular_enUS;
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
    return Computer.displayNameComputer(var);
  }
  public static String displayNameComputer(String var) {
    switch(var) {
    case VAR_computerName:
      return DISPLAY_NAME_computerName;
    case VAR_rulePks:
      return DISPLAY_NAME_rulePks;
    default:
      return BaseModel.displayNameBaseModel(var);
    }
  }

  public static String descriptionComputer(String var) {
    if(var == null)
      return null;
    switch(var) {
    case VAR_computerName:
      return "The computer fully qualified domain name";
    case VAR_rulePks:
      return "The list of related rule primary keys";
      default:
        return BaseModel.descriptionBaseModel(var);
    }
  }

  public static String classSimpleNameComputer(String var) {
    switch(var) {
    case VAR_computerName:
      return "String";
    case VAR_rulePks:
      return "List";
      default:
        return BaseModel.classSimpleNameBaseModel(var);
    }
  }

  public static Integer htmColumnComputer(String var) {
    switch(var) {
      default:
        return BaseModel.htmColumnBaseModel(var);
    }
  }

  public static Integer htmRowComputer(String var) {
    switch(var) {
      default:
        return BaseModel.htmRowBaseModel(var);
    }
  }

  public static Integer htmCellComputer(String var) {
    switch(var) {
      default:
        return BaseModel.htmCellBaseModel(var);
    }
  }

  public static Integer lengthMinComputer(String var) {
    switch(var) {
      default:
        return BaseModel.lengthMinBaseModel(var);
    }
  }

  public static Integer lengthMaxComputer(String var) {
    switch(var) {
      default:
        return BaseModel.lengthMaxBaseModel(var);
    }
  }

  public static Integer maxComputer(String var) {
    switch(var) {
      default:
        return BaseModel.maxBaseModel(var);
    }
  }

  public static Integer minComputer(String var) {
    switch(var) {
      default:
        return BaseModel.minBaseModel(var);
    }
  }
}
