package org.computate.dcm.model.eda.hostcheck;

import org.computate.dcm.request.SiteRequest;
import org.computate.dcm.model.eda.hostcheck.HostCheckGenPage;
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
import org.computate.search.wrap.Wrap;
import io.vertx.core.Promise;
import io.vertx.core.Future;
import io.vertx.core.json.JsonArray;

/**
 * <ol>
<h3>Suggestions that can generate more code for you: </h3> * </ol>
 * <li>You can add a class comment <b>"Api: true"</b> if you wish to GET, POST, PATCH or PUT these HostCheckPage objects in a RESTful API. 
 * </li><li>You can add a class comment "{@inheritDoc}" if you wish to inherit the helpful inherited class comments from class HostCheckPageGen into the class HostCheckPage. 
 * </li>
 * <h3>About the HostCheckPage class and it's generated class HostCheckPageGen&lt;HostCheckGenPage&gt;: </h3>extends HostCheckPageGen
 * <p>
 * This Java class extends a generated Java class HostCheckPageGen built by the <a href="https://github.com/computate-org/computate">https://github.com/computate-org/computate</a> project. 
 * Whenever this Java class is modified or touched, the watch service installed as described in the README, indexes all the information about this Java class in a local Apache Solr Search Engine. 
 * If you are running the service, you can see the indexed data about this Java Class here: 
 * </p>
 * <p><a href="https://solr.apps-crc.testing/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.hostcheck.HostCheckPage">Find the class HostCheckPage in Solr. </a></p>
 * <p>
 * The extended class ending with "Gen" did not exist at first, but was automatically created by the same watch service based on the data retrieved from the local Apache Server search engine. 
 * The extended class contains many generated fields, getters, setters, initialization code, and helper methods to help build a website and API fast, reactive, and scalable. 
 * </p>
 * extends HostCheckPageGen<HostCheckGenPage>
 * <p>This <code>class HostCheckPage extends HostCheckPageGen&lt;HostCheckGenPage&gt;</code>, which means it extends a newly generated HostCheckPageGen. 
 * The generated <code>class HostCheckPageGen extends HostCheckGenPage</code> which means that HostCheckPage extends HostCheckPageGen which extends HostCheckGenPage. 
 * This generated inheritance is a powerful feature that allows a lot of boiler plate code to be created for you automatically while still preserving inheritance through the power of Java Generic classes. 
 * </p>
 * <h2>Api: true</h2>
 * <h2>ApiTag.enUS: true</h2>
 * <h2>ApiUri.enUS: null</h2>
 * <h2>Color: null</h2>
 * <h2>Indexed: true</h2>
 * <h2>{@inheritDoc}</h2>
 * <p>By adding a class comment "{@inheritDoc}", the HostCheckPage class will inherit the helpful inherited class comments from the super class HostCheckPageGen. 
 * </p>
 * <h2>Rows: null</h2>
 * <h2>Model: true</h2>
 * <h2>Page: true</h2>
 * <h2>SuperPage.enUS: null</h2>
 * <h2>Promise: true</h2>
 * <p>
 *   This class contains a comment <b>"Promise: true"</b>
 *   Sometimes a Java class must be initialized asynchronously when it involves calling a blocking API. 
 *   This means that the HostCheckPage Java class has promiseDeep methods which must be initialized asynchronously as a Vert.x Promise  instead of initDeep methods which are a simple non-asynchronous method. 
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
 * Delete the class HostCheckPage in Solr: 
 * <pre>
 * curl -k 'https://solr.apps-crc.testing/solr/computate/update?commitWithin=1000&amp;overwrite=true&amp;wt=json' -X POST -H 'Content-type: text/xml' -u "admin:$(oc -n solr get secret/solr-solrcloud-security-bootstrap -o jsonpath={.data.admin} | base64 -d)" --data-raw '&lt;delete&gt;&lt;query&gt;classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.hostcheck.HostCheckPage&lt;/query&gt;&lt;/delete&gt;'
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
public abstract class HostCheckPageGen<DEV> extends HostCheckGenPage {
  protected static final Logger LOG = LoggerFactory.getLogger(HostCheckPage.class);

  //////////////
  // initDeep //
  //////////////

  public Future<HostCheckPageGen<DEV>> promiseDeepHostCheckPage(SiteRequest siteRequest_) {
    setSiteRequest_(siteRequest_);
    return promiseDeepHostCheckPage();
  }

  public Future<HostCheckPageGen<DEV>> promiseDeepHostCheckPage() {
    Promise<HostCheckPageGen<DEV>> promise = Promise.promise();
    Promise<Void> promise2 = Promise.promise();
    promiseHostCheckPage(promise2);
    promise2.future().onSuccess(a -> {
      super.promiseDeepHostCheckGenPage(siteRequest_).onSuccess(b -> {
        promise.complete(this);
      }).onFailure(ex -> {
        promise.fail(ex);
      });
    }).onFailure(ex -> {
      promise.fail(ex);
    });
    return promise.future();
  }

  public Future<Void> promiseHostCheckPage(Promise<Void> promise) {
    Future.future(a -> a.complete()).compose(a -> {
      Promise<Void> promise2 = Promise.promise();
      try {
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

  @Override public Future<? extends HostCheckPageGen<DEV>> promiseDeepForClass(SiteRequest siteRequest_) {
    return promiseDeepHostCheckPage(siteRequest_);
  }

  /////////////////
  // siteRequest //
  /////////////////

  public void siteRequestHostCheckPage(SiteRequest siteRequest_) {
      super.siteRequestHostCheckGenPage(siteRequest_);
  }

  public void siteRequestForClass(SiteRequest siteRequest_) {
    siteRequestHostCheckPage(siteRequest_);
  }

  /////////////
  // obtain //
  /////////////

  @Override public Object obtainForClass(String var) {
    String[] vars = StringUtils.split(var, ".");
    Object o = null;
    for(String v : vars) {
      if(o == null)
        o = obtainHostCheckPage(v);
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
  public Object obtainHostCheckPage(String var) {
    HostCheckPage oHostCheckPage = (HostCheckPage)this;
    switch(var) {
      default:
        return super.obtainHostCheckGenPage(var);
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
        o = relateHostCheckPage(v, val);
      else if(o instanceof BaseModel) {
        BaseModel baseModel = (BaseModel)o;
        o = baseModel.relateForClass(v, val);
      }
    }
    return o != null;
  }
  public Object relateHostCheckPage(String var, Object val) {
    HostCheckPage oHostCheckPage = (HostCheckPage)this;
    switch(var) {
      default:
        return super.relateHostCheckGenPage(var, val);
    }
  }

  ///////////////
  // staticSet //
  ///////////////

  public static Object staticSetForClass(String entityVar, SiteRequest siteRequest_, String v, HostCheckPage o) {
    return staticSetHostCheckPage(entityVar,  siteRequest_, v, o);
  }
  public static Object staticSetHostCheckPage(String entityVar, SiteRequest siteRequest_, String v, HostCheckPage o) {
    switch(entityVar) {
      default:
        return HostCheckGenPage.staticSetHostCheckGenPage(entityVar,  siteRequest_, v, o);
    }
  }

  ////////////////
  // staticSearch //
  ////////////////

  public static Object staticSearchForClass(String entityVar, SiteRequest siteRequest_, Object o) {
    return staticSearchHostCheckPage(entityVar,  siteRequest_, o);
  }
  public static Object staticSearchHostCheckPage(String entityVar, SiteRequest siteRequest_, Object o) {
    switch(entityVar) {
      default:
        return HostCheckGenPage.staticSearchHostCheckGenPage(entityVar,  siteRequest_, o);
    }
  }

  ///////////////////
  // staticSearchStr //
  ///////////////////

  public static String staticSearchStrForClass(String entityVar, SiteRequest siteRequest_, Object o) {
    return staticSearchStrHostCheckPage(entityVar,  siteRequest_, o);
  }
  public static String staticSearchStrHostCheckPage(String entityVar, SiteRequest siteRequest_, Object o) {
    switch(entityVar) {
      default:
        return HostCheckGenPage.staticSearchStrHostCheckGenPage(entityVar,  siteRequest_, o);
    }
  }

  //////////////////
  // staticSearchFq //
  //////////////////

  public static String staticSearchFqForClass(String entityVar, SiteRequest siteRequest_, String o) {
    return staticSearchFqHostCheckPage(entityVar,  siteRequest_, o);
  }
  public static String staticSearchFqHostCheckPage(String entityVar, SiteRequest siteRequest_, String o) {
    switch(entityVar) {
      default:
        return HostCheckGenPage.staticSearchFqHostCheckGenPage(entityVar,  siteRequest_, o);
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

  public static final String CLASS_SIMPLE_NAME = "HostCheckPage";
  public static final String CLASS_CANONICAL_NAME = "org.computate.dcm.model.eda.hostcheck.HostCheckPage";
  public static final String CLASS_AUTH_RESOURCE = "";


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
    return HostCheckPage.displayNameHostCheckPage(var);
  }
  public static String displayNameHostCheckPage(String var) {
    switch(var) {
    default:
      return HostCheckGenPage.displayNameHostCheckGenPage(var);
    }
  }

  public static String descriptionHostCheckPage(String var) {
    if(var == null)
      return null;
    switch(var) {
      default:
        return HostCheckGenPage.descriptionHostCheckGenPage(var);
    }
  }

  public static String classSimpleNameHostCheckPage(String var) {
    switch(var) {
      default:
        return HostCheckGenPage.classSimpleNameHostCheckGenPage(var);
    }
  }

  public static Integer htmColumnHostCheckPage(String var) {
    switch(var) {
      default:
        return HostCheckGenPage.htmColumnHostCheckGenPage(var);
    }
  }

  public static Integer htmRowHostCheckPage(String var) {
    switch(var) {
      default:
        return HostCheckGenPage.htmRowHostCheckGenPage(var);
    }
  }

  public static Integer htmCellHostCheckPage(String var) {
    switch(var) {
      default:
        return HostCheckGenPage.htmCellHostCheckGenPage(var);
    }
  }

  public static Integer lengthMinHostCheckPage(String var) {
    switch(var) {
      default:
        return HostCheckGenPage.lengthMinHostCheckGenPage(var);
    }
  }

  public static Integer lengthMaxHostCheckPage(String var) {
    switch(var) {
      default:
        return HostCheckGenPage.lengthMaxHostCheckGenPage(var);
    }
  }

  public static Integer maxHostCheckPage(String var) {
    switch(var) {
      default:
        return HostCheckGenPage.maxHostCheckGenPage(var);
    }
  }

  public static Integer minHostCheckPage(String var) {
    switch(var) {
      default:
        return HostCheckGenPage.minHostCheckGenPage(var);
    }
  }
}
