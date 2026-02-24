package org.computate.dcm.model.eda.ansibleproject;

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
import org.computate.dcm.model.eda.tenant.Tenant;
import java.lang.Long;
import org.computate.dcm.model.eda.jobtemplate.JobTemplate;
import io.vertx.core.json.JsonArray;
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
 * <li>You can add a class comment "{@inheritDoc}" if you wish to inherit the helpful inherited class comments from class AnsibleProjectGen into the class AnsibleProject. 
 * </li>
 * <h3>About the AnsibleProject class and it's generated class AnsibleProjectGen&lt;BaseModel&gt;: </h3>extends AnsibleProjectGen
 * <p>
 * This Java class extends a generated Java class AnsibleProjectGen built by the <a href="https://github.com/computate-org/computate">https://github.com/computate-org/computate</a> project. 
 * Whenever this Java class is modified or touched, the watch service installed as described in the README, indexes all the information about this Java class in a local Apache Solr Search Engine. 
 * If you are running the service, you can see the indexed data about this Java Class here: 
 * </p>
 * <p><a href="https://solr.apps-crc.testing/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.ansibleproject.AnsibleProject">Find the class AnsibleProject in Solr. </a></p>
 * <p>
 * The extended class ending with "Gen" did not exist at first, but was automatically created by the same watch service based on the data retrieved from the local Apache Server search engine. 
 * The extended class contains many generated fields, getters, setters, initialization code, and helper methods to help build a website and API fast, reactive, and scalable. 
 * </p>
 * extends AnsibleProjectGen<BaseModel>
 * <p>This <code>class AnsibleProject extends AnsibleProjectGen&lt;BaseModel&gt;</code>, which means it extends a newly generated AnsibleProjectGen. 
 * The generated <code>class AnsibleProjectGen extends BaseModel</code> which means that AnsibleProject extends AnsibleProjectGen which extends BaseModel. 
 * This generated inheritance is a powerful feature that allows a lot of boiler plate code to be created for you automatically while still preserving inheritance through the power of Java Generic classes. 
 * </p>
 * <h2>Api: true</h2>
 * <p>This class contains a comment <b>"Api: true"</b>, which means this class will have Java Vert.x API backend code generated for these objects. 
 * </p>
 * <h2>ApiTag.enUS: true</h2>
 * <p>This class contains a comment <b>"ApiTag: ansible projects"</b>, which groups all of the OpenAPIs for AnsibleProject objects under the tag "ansible projects". 
 * </p>
 * <h2>ApiUri.enUS: /en-us/api/ansible-project</h2>
 * <p>This class contains a comment <b>"ApiUri: /en-us/api/ansible-project"</b>, which defines the base API URI for AnsibleProject objects as "/en-us/api/ansible-project" in the OpenAPI spec. 
 * </p>
 * <h2>Color: null</h2>
 * <h2>Indexed: true</h2>
 * <p>This class contains a comment <b>"Indexed: true"</b>, which means this class will be indexed in the search engine. 
 * Every protected void method that begins with "_" that is marked to be searched with a comment like "Indexed: true", "Stored: true", or "DocValues: true" will be indexed in the search engine. 
 * </p>
 * <h2>{@inheritDoc}</h2>
 * <p>By adding a class comment "{@inheritDoc}", the AnsibleProject class will inherit the helpful inherited class comments from the super class AnsibleProjectGen. 
 * </p>
 * <h2>Rows: 100</h2>
 * <p>This class contains a comment <b>"Rows: 100"</b>, which means the AnsibleProject API will return a default of 100 records instead of 10 by default. 
 * Each API has built in pagination of the search records to ensure a user can query all the data a page at a time without running the application out of memory. 
 * </p>
 * <h2>Order: 5</h2>
 * <p>This class contains a comment <b>"Order: 5"</b>, which means this class will be sorted by the given number 5 ascending when code that relates to multiple classes at the same time is generated. 
 * </p>
 * <h2>SqlOrder: 5</h2>
 * <p>This class contains a comment <b>"SqlOrder: 5"</b>, which means this class will be sorted by the given number 5 ascending when SQL code to create and drop the tables is generated. 
 * </p>
 * <h2>Model: true</h2>
 * <p>This class contains a comment <b>"Model: true"</b>, which means this class will be stored in the database. 
 * Every protected void method that begins with "_" that contains a "Persist: true" comment will be a persisted field in the database table. 
 * </p>
 * <h2>Page: true</h2>
 * <p>This class contains a comment <b>"Page: true"</b>, which means this class will have webpage code generated for these objects. 
 * Java Vert.x backend API code, Handlebars HTML template frontend code, and JavaScript code will all generated and can be extended. 
 * This creates a new Java class org.computate.dcm.model.eda.ansibleproject.AnsibleProjectPage. 
 * </p>
 * <h2>SuperPage.enUS: PageLayout</h2>
 * <p>This class contains a comment <b>"SuperPage.enUS: PageLayout"</b>, which identifies the Java super class of the page code by it's class simple name "PageLayout". 
 * This means that the newly created class org.computate.dcm.model.eda.ansibleproject.AnsibleProjectPage extends org.computate.dcm.page.PageLayout. 
 * </p>
 * <h2>Promise: true</h2>
 * <p>
 *   This class contains a comment <b>"Promise: true"</b>
 *   Sometimes a Java class must be initialized asynchronously when it involves calling a blocking API. 
 *   This means that the AnsibleProject Java class has promiseDeep methods which must be initialized asynchronously as a Vert.x Promise  instead of initDeep methods which are a simple non-asynchronous method. 
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
 * <h2>AName.enUS: a ansible project</h2>
 * <p>This class contains a comment <b>"AName.enUS: a ansible project"</b>, which identifies the language context to describe a AnsibleProject as "a ansible project". 
 * </p>
 * <p>
 * Delete the class AnsibleProject in Solr: 
 * <pre>
 * curl -k 'https://solr.apps-crc.testing/solr/computate/update?commitWithin=1000&amp;overwrite=true&amp;wt=json' -X POST -H 'Content-type: text/xml' -u "admin:$(oc -n solr get secret/solr-solrcloud-security-bootstrap -o jsonpath={.data.admin} | base64 -d)" --data-raw '&lt;delete&gt;&lt;query&gt;classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.ansibleproject.AnsibleProject&lt;/query&gt;&lt;/delete&gt;'
 * </pre>
 * </p>
 * <p>
 * Delete  the package org.computate.dcm.model.eda.ansibleproject in Solr: 
 * <pre>
 * curl -k 'https://solr.apps-crc.testing/solr/computate/update?commitWithin=1000&amp;overwrite=true&amp;wt=json' -X POST -H 'Content-type: text/xml' -u "admin:$(oc -n solr get secret/solr-solrcloud-security-bootstrap -o jsonpath={.data.admin} | base64 -d)" --data-raw '&lt;delete&gt;&lt;query&gt;classeNomEnsemble_enUS_indexed_string:org.computate.dcm.model.eda.ansibleproject&lt;/query&gt;&lt;/delete&gt;'
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
public abstract class AnsibleProjectGen<DEV> extends BaseModel {
  protected static final Logger LOG = LoggerFactory.getLogger(AnsibleProject.class);

  public static final String Description_enUS = "A ansible project to be run on a computer in Ansible Automation Platform. ";
  public static final String AName_enUS = "a ansible project";
  public static final String This_enUS = "this ";
  public static final String ThisName_enUS = "this ansible project";
  public static final String A_enUS = "a ";
  public static final String TheName_enUS = "theansible project";
  public static final String SingularName_enUS = "ansible project";
  public static final String PluralName_enUS = "ansible projects";
  public static final String NameActual_enUS = "current ansible project";
  public static final String AllName_enUS = "all ansible projects";
  public static final String SearchAllNameBy_enUS = "search ansible projects by ";
  public static final String SearchAllName_enUS = "search ansible projects";
  public static final String Title_enUS = "ansible projects";
  public static final String ThePluralName_enUS = "the ansible projects";
  public static final String NoNameFound_enUS = "no ansible project found";
  public static final String ApiUri_enUS = "/en-us/api/ansible-project";
  public static final String ApiUriSearchPage_enUS = "/en-us/search/ansible-project";
  public static final String ApiUriEditPage_enUS = "/en-us/edit/ansible-project/{ansibleProjectResource}";
  public static final String OfName_enUS = "of ansible project";
  public static final String ANameAdjective_enUS = "an ansible project";
  public static final String NameAdjectiveSingular_enUS = "ansible project";
  public static final String NameAdjectivePlural_enUS = "ansible projects";
  public static final String Search_enUS_OpenApiUri = "/en-us/api/ansible-project";
  public static final String Search_enUS_StringFormatUri = "/en-us/api/ansible-project";
  public static final String Search_enUS_StringFormatUrl = "%s/en-us/api/ansible-project";
  public static final String GET_enUS_OpenApiUri = "/en-us/api/ansible-project/{ansibleProjectResource}";
  public static final String GET_enUS_StringFormatUri = "/en-us/api/ansible-project/%s";
  public static final String GET_enUS_StringFormatUrl = "%s/en-us/api/ansible-project/%s";
  public static final String PATCH_enUS_OpenApiUri = "/en-us/api/ansible-project";
  public static final String PATCH_enUS_StringFormatUri = "/en-us/api/ansible-project";
  public static final String PATCH_enUS_StringFormatUrl = "%s/en-us/api/ansible-project";
  public static final String POST_enUS_OpenApiUri = "/en-us/api/ansible-project";
  public static final String POST_enUS_StringFormatUri = "/en-us/api/ansible-project";
  public static final String POST_enUS_StringFormatUrl = "%s/en-us/api/ansible-project";
  public static final String DELETE_enUS_OpenApiUri = "/en-us/api/ansible-project/{ansibleProjectResource}";
  public static final String DELETE_enUS_StringFormatUri = "/en-us/api/ansible-project/%s";
  public static final String DELETE_enUS_StringFormatUrl = "%s/en-us/api/ansible-project/%s";
  public static final String PUTImport_enUS_OpenApiUri = "/en-us/api/ansible-project-import";
  public static final String PUTImport_enUS_StringFormatUri = "/en-us/api/ansible-project-import";
  public static final String PUTImport_enUS_StringFormatUrl = "%s/en-us/api/ansible-project-import";
  public static final String SearchPage_enUS_OpenApiUri = "/en-us/search/ansible-project";
  public static final String SearchPage_enUS_StringFormatUri = "/en-us/search/ansible-project";
  public static final String SearchPage_enUS_StringFormatUrl = "%s/en-us/search/ansible-project";
  public static final String EditPage_enUS_OpenApiUri = "/en-us/edit/ansible-project/{ansibleProjectResource}";
  public static final String EditPage_enUS_StringFormatUri = "/en-us/edit/ansible-project/%s";
  public static final String EditPage_enUS_StringFormatUrl = "%s/en-us/edit/ansible-project/%s";
  public static final String DELETEFilter_enUS_OpenApiUri = "/en-us/api/ansible-project";
  public static final String DELETEFilter_enUS_StringFormatUri = "/en-us/api/ansible-project";
  public static final String DELETEFilter_enUS_StringFormatUrl = "%s/en-us/api/ansible-project";

  public static final String Icon = "<i class=\"fa-duotone fa-regular fa-excavator\"></i>";
  public static final Integer Rows = 100;

	////////////////////
  // tenantResource //
	////////////////////


  /**
   *  The entity tenantResource
   *	 is defined as null before being initialized. 
   */
  @JsonProperty
  @JsonInclude(Include.NON_NULL)
  protected String tenantResource;

  /**
   * <br> The entity tenantResource
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.ansibleproject.AnsibleProject&fq=entiteVar_enUS_indexed_string:tenantResource">Find the entity tenantResource in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _tenantResource(Wrap<String> w);

  public String getTenantResource() {
    return tenantResource;
  }
  public void setTenantResource(String o) {
    this.tenantResource = AnsibleProject.staticSetTenantResource(siteRequest_, o);
  }
  public static String staticSetTenantResource(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected AnsibleProject tenantResourceInit() {
    Wrap<String> tenantResourceWrap = new Wrap<String>().var("tenantResource");
    if(tenantResource == null) {
      _tenantResource(tenantResourceWrap);
      Optional.ofNullable(tenantResourceWrap.getO()).ifPresent(o -> {
        setTenantResource(o);
      });
    }
    return (AnsibleProject)this;
  }

  public static String staticSearchTenantResource(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrTenantResource(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqTenantResource(SiteRequest siteRequest_, String o) {
    return AnsibleProject.staticSearchTenantResource(siteRequest_, AnsibleProject.staticSetTenantResource(siteRequest_, o)).toString();
  }

  public String sqlTenantResource() {
    return tenantResource;
  }

  public static String staticJsonTenantResource(String tenantResource) {
    return tenantResource;
  }

	//////////////
  // tenantId //
	//////////////


  /**
   *  The entity tenantId
   *	 is defined as null before being initialized. 
   */
  @JsonProperty
  @JsonInclude(Include.NON_NULL)
  protected String tenantId;

  /**
   * <br> The entity tenantId
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.ansibleproject.AnsibleProject&fq=entiteVar_enUS_indexed_string:tenantId">Find the entity tenantId in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _tenantId(Wrap<String> w);

  public String getTenantId() {
    return tenantId;
  }
  public void setTenantId(String o) {
    this.tenantId = AnsibleProject.staticSetTenantId(siteRequest_, o);
  }
  public static String staticSetTenantId(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected AnsibleProject tenantIdInit() {
    Wrap<String> tenantIdWrap = new Wrap<String>().var("tenantId");
    if(tenantId == null) {
      _tenantId(tenantIdWrap);
      Optional.ofNullable(tenantIdWrap.getO()).ifPresent(o -> {
        setTenantId(o);
      });
    }
    return (AnsibleProject)this;
  }

  public static String staticSearchTenantId(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrTenantId(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqTenantId(SiteRequest siteRequest_, String o) {
    return AnsibleProject.staticSearchTenantId(siteRequest_, AnsibleProject.staticSetTenantId(siteRequest_, o)).toString();
  }

  public String sqlTenantId() {
    return tenantId;
  }

  public static String staticJsonTenantId(String tenantId) {
    return tenantId;
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
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.ansibleproject.AnsibleProject&fq=entiteVar_enUS_indexed_string:aapOrganizationId">Find the entity aapOrganizationId in Solr</a>
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
    this.aapOrganizationId = AnsibleProject.staticSetAapOrganizationId(siteRequest_, o);
  }
  public static Long staticSetAapOrganizationId(SiteRequest siteRequest_, String o) {
    if(NumberUtils.isParsable(o))
      return Long.parseLong(o);
    return null;
  }
  protected AnsibleProject aapOrganizationIdInit() {
    Wrap<Long> aapOrganizationIdWrap = new Wrap<Long>().var("aapOrganizationId");
    if(aapOrganizationId == null) {
      _aapOrganizationId(aapOrganizationIdWrap);
      Optional.ofNullable(aapOrganizationIdWrap.getO()).ifPresent(o -> {
        setAapOrganizationId(o);
      });
    }
    return (AnsibleProject)this;
  }

  public static Long staticSearchAapOrganizationId(SiteRequest siteRequest_, Long o) {
    return o;
  }

  public static String staticSearchStrAapOrganizationId(SiteRequest siteRequest_, Long o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqAapOrganizationId(SiteRequest siteRequest_, String o) {
    return AnsibleProject.staticSearchAapOrganizationId(siteRequest_, AnsibleProject.staticSetAapOrganizationId(siteRequest_, o)).toString();
  }

  public Long sqlAapOrganizationId() {
    return aapOrganizationId;
  }

  public static String staticJsonAapOrganizationId(Long aapOrganizationId) {
    return Optional.ofNullable(aapOrganizationId).map(v -> v.toString()).orElse(null);
  }

	///////////////////////
  // sourceControlType //
	///////////////////////


  /**
   *  The entity sourceControlType
   *	 is defined as null before being initialized. 
   */
  @JsonProperty
  @JsonInclude(Include.NON_NULL)
  protected String sourceControlType;

  /**
   * <br> The entity sourceControlType
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.ansibleproject.AnsibleProject&fq=entiteVar_enUS_indexed_string:sourceControlType">Find the entity sourceControlType in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _sourceControlType(Wrap<String> w);

  public String getSourceControlType() {
    return sourceControlType;
  }
  public void setSourceControlType(String o) {
    this.sourceControlType = AnsibleProject.staticSetSourceControlType(siteRequest_, o);
  }
  public static String staticSetSourceControlType(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected AnsibleProject sourceControlTypeInit() {
    Wrap<String> sourceControlTypeWrap = new Wrap<String>().var("sourceControlType");
    if(sourceControlType == null) {
      _sourceControlType(sourceControlTypeWrap);
      Optional.ofNullable(sourceControlTypeWrap.getO()).ifPresent(o -> {
        setSourceControlType(o);
      });
    }
    return (AnsibleProject)this;
  }

  public static String staticSearchSourceControlType(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrSourceControlType(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqSourceControlType(SiteRequest siteRequest_, String o) {
    return AnsibleProject.staticSearchSourceControlType(siteRequest_, AnsibleProject.staticSetSourceControlType(siteRequest_, o)).toString();
  }

  public String sqlSourceControlType() {
    return sourceControlType;
  }

  public static String staticJsonSourceControlType(String sourceControlType) {
    return sourceControlType;
  }

	//////////////////////
  // sourceControlUrl //
	//////////////////////


  /**
   *  The entity sourceControlUrl
   *	 is defined as null before being initialized. 
   */
  @JsonProperty
  @JsonInclude(Include.NON_NULL)
  protected String sourceControlUrl;

  /**
   * <br> The entity sourceControlUrl
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.ansibleproject.AnsibleProject&fq=entiteVar_enUS_indexed_string:sourceControlUrl">Find the entity sourceControlUrl in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _sourceControlUrl(Wrap<String> w);

  public String getSourceControlUrl() {
    return sourceControlUrl;
  }
  public void setSourceControlUrl(String o) {
    this.sourceControlUrl = AnsibleProject.staticSetSourceControlUrl(siteRequest_, o);
  }
  public static String staticSetSourceControlUrl(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected AnsibleProject sourceControlUrlInit() {
    Wrap<String> sourceControlUrlWrap = new Wrap<String>().var("sourceControlUrl");
    if(sourceControlUrl == null) {
      _sourceControlUrl(sourceControlUrlWrap);
      Optional.ofNullable(sourceControlUrlWrap.getO()).ifPresent(o -> {
        setSourceControlUrl(o);
      });
    }
    return (AnsibleProject)this;
  }

  public static String staticSearchSourceControlUrl(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrSourceControlUrl(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqSourceControlUrl(SiteRequest siteRequest_, String o) {
    return AnsibleProject.staticSearchSourceControlUrl(siteRequest_, AnsibleProject.staticSetSourceControlUrl(siteRequest_, o)).toString();
  }

  public String sqlSourceControlUrl() {
    return sourceControlUrl;
  }

  public static String staticJsonSourceControlUrl(String sourceControlUrl) {
    return sourceControlUrl;
  }

	/////////////////////////
  // sourceControlBranch //
	/////////////////////////


  /**
   *  The entity sourceControlBranch
   *	 is defined as null before being initialized. 
   */
  @JsonProperty
  @JsonInclude(Include.NON_NULL)
  protected String sourceControlBranch;

  /**
   * <br> The entity sourceControlBranch
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.ansibleproject.AnsibleProject&fq=entiteVar_enUS_indexed_string:sourceControlBranch">Find the entity sourceControlBranch in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _sourceControlBranch(Wrap<String> w);

  public String getSourceControlBranch() {
    return sourceControlBranch;
  }
  public void setSourceControlBranch(String o) {
    this.sourceControlBranch = AnsibleProject.staticSetSourceControlBranch(siteRequest_, o);
  }
  public static String staticSetSourceControlBranch(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected AnsibleProject sourceControlBranchInit() {
    Wrap<String> sourceControlBranchWrap = new Wrap<String>().var("sourceControlBranch");
    if(sourceControlBranch == null) {
      _sourceControlBranch(sourceControlBranchWrap);
      Optional.ofNullable(sourceControlBranchWrap.getO()).ifPresent(o -> {
        setSourceControlBranch(o);
      });
    }
    return (AnsibleProject)this;
  }

  public static String staticSearchSourceControlBranch(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrSourceControlBranch(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqSourceControlBranch(SiteRequest siteRequest_, String o) {
    return AnsibleProject.staticSearchSourceControlBranch(siteRequest_, AnsibleProject.staticSetSourceControlBranch(siteRequest_, o)).toString();
  }

  public String sqlSourceControlBranch() {
    return sourceControlBranch;
  }

  public static String staticJsonSourceControlBranch(String sourceControlBranch) {
    return sourceControlBranch;
  }

	////////////////////////
  // ansibleProjectName //
	////////////////////////


  /**
   *  The entity ansibleProjectName
   *	 is defined as null before being initialized. 
   */
  @JsonProperty
  @JsonInclude(Include.NON_NULL)
  protected String ansibleProjectName;

  /**
   * <br> The entity ansibleProjectName
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.ansibleproject.AnsibleProject&fq=entiteVar_enUS_indexed_string:ansibleProjectName">Find the entity ansibleProjectName in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _ansibleProjectName(Wrap<String> w);

  public String getAnsibleProjectName() {
    return ansibleProjectName;
  }
  public void setAnsibleProjectName(String o) {
    this.ansibleProjectName = AnsibleProject.staticSetAnsibleProjectName(siteRequest_, o);
  }
  public static String staticSetAnsibleProjectName(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected AnsibleProject ansibleProjectNameInit() {
    Wrap<String> ansibleProjectNameWrap = new Wrap<String>().var("ansibleProjectName");
    if(ansibleProjectName == null) {
      _ansibleProjectName(ansibleProjectNameWrap);
      Optional.ofNullable(ansibleProjectNameWrap.getO()).ifPresent(o -> {
        setAnsibleProjectName(o);
      });
    }
    return (AnsibleProject)this;
  }

  public static String staticSearchAnsibleProjectName(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrAnsibleProjectName(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqAnsibleProjectName(SiteRequest siteRequest_, String o) {
    return AnsibleProject.staticSearchAnsibleProjectName(siteRequest_, AnsibleProject.staticSetAnsibleProjectName(siteRequest_, o)).toString();
  }

  public String sqlAnsibleProjectName() {
    return ansibleProjectName;
  }

  public static String staticJsonAnsibleProjectName(String ansibleProjectName) {
    return ansibleProjectName;
  }

	//////////////////////
  // ansibleProjectId //
	//////////////////////


  /**
   *  The entity ansibleProjectId
   *	 is defined as null before being initialized. 
   */
  @JsonProperty
  @JsonInclude(Include.NON_NULL)
  protected String ansibleProjectId;

  /**
   * <br> The entity ansibleProjectId
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.ansibleproject.AnsibleProject&fq=entiteVar_enUS_indexed_string:ansibleProjectId">Find the entity ansibleProjectId in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _ansibleProjectId(Wrap<String> w);

  public String getAnsibleProjectId() {
    return ansibleProjectId;
  }
  public void setAnsibleProjectId(String o) {
    this.ansibleProjectId = AnsibleProject.staticSetAnsibleProjectId(siteRequest_, o);
  }
  public static String staticSetAnsibleProjectId(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected AnsibleProject ansibleProjectIdInit() {
    Wrap<String> ansibleProjectIdWrap = new Wrap<String>().var("ansibleProjectId");
    if(ansibleProjectId == null) {
      _ansibleProjectId(ansibleProjectIdWrap);
      Optional.ofNullable(ansibleProjectIdWrap.getO()).ifPresent(o -> {
        setAnsibleProjectId(o);
      });
    }
    return (AnsibleProject)this;
  }

  public static String staticSearchAnsibleProjectId(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrAnsibleProjectId(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqAnsibleProjectId(SiteRequest siteRequest_, String o) {
    return AnsibleProject.staticSearchAnsibleProjectId(siteRequest_, AnsibleProject.staticSetAnsibleProjectId(siteRequest_, o)).toString();
  }

  public String sqlAnsibleProjectId() {
    return ansibleProjectId;
  }

  public static String staticJsonAnsibleProjectId(String ansibleProjectId) {
    return ansibleProjectId;
  }

	////////////////////////////
  // ansibleProjectResource //
	////////////////////////////


  /**
   *  The entity ansibleProjectResource
   *	 is defined as null before being initialized. 
   */
  @JsonProperty
  @JsonInclude(Include.NON_NULL)
  protected String ansibleProjectResource;

  /**
   * <br> The entity ansibleProjectResource
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.ansibleproject.AnsibleProject&fq=entiteVar_enUS_indexed_string:ansibleProjectResource">Find the entity ansibleProjectResource in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _ansibleProjectResource(Wrap<String> w);

  public String getAnsibleProjectResource() {
    return ansibleProjectResource;
  }
  public void setAnsibleProjectResource(String o) {
    this.ansibleProjectResource = AnsibleProject.staticSetAnsibleProjectResource(siteRequest_, o);
  }
  public static String staticSetAnsibleProjectResource(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected AnsibleProject ansibleProjectResourceInit() {
    Wrap<String> ansibleProjectResourceWrap = new Wrap<String>().var("ansibleProjectResource");
    if(ansibleProjectResource == null) {
      _ansibleProjectResource(ansibleProjectResourceWrap);
      Optional.ofNullable(ansibleProjectResourceWrap.getO()).ifPresent(o -> {
        setAnsibleProjectResource(o);
      });
    }
    return (AnsibleProject)this;
  }

  public static String staticSearchAnsibleProjectResource(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrAnsibleProjectResource(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqAnsibleProjectResource(SiteRequest siteRequest_, String o) {
    return AnsibleProject.staticSearchAnsibleProjectResource(siteRequest_, AnsibleProject.staticSetAnsibleProjectResource(siteRequest_, o)).toString();
  }

  public String sqlAnsibleProjectResource() {
    return ansibleProjectResource;
  }

  public static String staticJsonAnsibleProjectResource(String ansibleProjectResource) {
    return ansibleProjectResource;
  }

	//////////////////
  // aapProjectId //
	//////////////////


  /**
   *  The entity aapProjectId
   *	 is defined as null before being initialized. 
   */
  @JsonProperty
  @JsonSerialize(using = ToStringSerializer.class)
  @JsonInclude(Include.NON_NULL)
  protected Long aapProjectId;

  /**
   * <br> The entity aapProjectId
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.ansibleproject.AnsibleProject&fq=entiteVar_enUS_indexed_string:aapProjectId">Find the entity aapProjectId in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _aapProjectId(Wrap<Long> w);

  public Long getAapProjectId() {
    return aapProjectId;
  }

  public void setAapProjectId(Long aapProjectId) {
    this.aapProjectId = aapProjectId;
  }
  @JsonIgnore
  public void setAapProjectId(String o) {
    this.aapProjectId = AnsibleProject.staticSetAapProjectId(siteRequest_, o);
  }
  public static Long staticSetAapProjectId(SiteRequest siteRequest_, String o) {
    if(NumberUtils.isParsable(o))
      return Long.parseLong(o);
    return null;
  }
  protected AnsibleProject aapProjectIdInit() {
    Wrap<Long> aapProjectIdWrap = new Wrap<Long>().var("aapProjectId");
    if(aapProjectId == null) {
      _aapProjectId(aapProjectIdWrap);
      Optional.ofNullable(aapProjectIdWrap.getO()).ifPresent(o -> {
        setAapProjectId(o);
      });
    }
    return (AnsibleProject)this;
  }

  public static Long staticSearchAapProjectId(SiteRequest siteRequest_, Long o) {
    return o;
  }

  public static String staticSearchStrAapProjectId(SiteRequest siteRequest_, Long o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqAapProjectId(SiteRequest siteRequest_, String o) {
    return AnsibleProject.staticSearchAapProjectId(siteRequest_, AnsibleProject.staticSetAapProjectId(siteRequest_, o)).toString();
  }

  public Long sqlAapProjectId() {
    return aapProjectId;
  }

  public static String staticJsonAapProjectId(Long aapProjectId) {
    return Optional.ofNullable(aapProjectId).map(v -> v.toString()).orElse(null);
  }

	///////////////////////////////
  // ansibleProjectDescription //
	///////////////////////////////


  /**
   *  The entity ansibleProjectDescription
   *	 is defined as null before being initialized. 
   */
  @JsonProperty
  @JsonInclude(Include.NON_NULL)
  protected String ansibleProjectDescription;

  /**
   * <br> The entity ansibleProjectDescription
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.ansibleproject.AnsibleProject&fq=entiteVar_enUS_indexed_string:ansibleProjectDescription">Find the entity ansibleProjectDescription in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _ansibleProjectDescription(Wrap<String> w);

  public String getAnsibleProjectDescription() {
    return ansibleProjectDescription;
  }
  public void setAnsibleProjectDescription(String o) {
    this.ansibleProjectDescription = AnsibleProject.staticSetAnsibleProjectDescription(siteRequest_, o);
  }
  public static String staticSetAnsibleProjectDescription(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected AnsibleProject ansibleProjectDescriptionInit() {
    Wrap<String> ansibleProjectDescriptionWrap = new Wrap<String>().var("ansibleProjectDescription");
    if(ansibleProjectDescription == null) {
      _ansibleProjectDescription(ansibleProjectDescriptionWrap);
      Optional.ofNullable(ansibleProjectDescriptionWrap.getO()).ifPresent(o -> {
        setAnsibleProjectDescription(o);
      });
    }
    return (AnsibleProject)this;
  }

  public static String staticSearchAnsibleProjectDescription(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrAnsibleProjectDescription(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqAnsibleProjectDescription(SiteRequest siteRequest_, String o) {
    return AnsibleProject.staticSearchAnsibleProjectDescription(siteRequest_, AnsibleProject.staticSetAnsibleProjectDescription(siteRequest_, o)).toString();
  }

  public String sqlAnsibleProjectDescription() {
    return ansibleProjectDescription;
  }

  public static String staticJsonAnsibleProjectDescription(String ansibleProjectDescription) {
    return ansibleProjectDescription;
  }

	//////////////////////////
  // jobTemplateResources //
	//////////////////////////


  /**
   *  The entity jobTemplateResources
   *	 It is constructed before being initialized with the constructor by default. 
   */
  @JsonProperty
  @JsonFormat(shape = JsonFormat.Shape.ARRAY)
  @JsonInclude(Include.NON_NULL)
  protected List<String> jobTemplateResources = new ArrayList<String>();

  /**
   * <br> The entity jobTemplateResources
   *  It is constructed before being initialized with the constructor by default. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.ansibleproject.AnsibleProject&fq=entiteVar_enUS_indexed_string:jobTemplateResources">Find the entity jobTemplateResources in Solr</a>
   * <br>
   * @param l is the entity already constructed. 
   **/
  protected abstract void _jobTemplateResources(List<String> l);

  public List<String> getJobTemplateResources() {
    return jobTemplateResources;
  }

  public void setJobTemplateResources(List<String> jobTemplateResources) {
    this.jobTemplateResources = jobTemplateResources;
  }
  @JsonIgnore
  public void setJobTemplateResources(String o) {
    String l = AnsibleProject.staticSetJobTemplateResources(siteRequest_, o);
    if(l != null)
      addJobTemplateResources(l);
  }
  public static String staticSetJobTemplateResources(SiteRequest siteRequest_, String o) {
    return o;
  }
  public AnsibleProject addJobTemplateResources(String...objects) {
    for(String o : objects) {
      addJobTemplateResources(o);
    }
    return (AnsibleProject)this;
  }
  public AnsibleProject addJobTemplateResources(String o) {
    if(o != null)
      this.jobTemplateResources.add(o);
    return (AnsibleProject)this;
  }
  @JsonIgnore
  public void setJobTemplateResources(JsonArray objects) {
    jobTemplateResources.clear();
    if(objects == null)
      return;
    for(int i = 0; i < objects.size(); i++) {
      String o = objects.getString(i);
      addJobTemplateResources(o);
    }
  }
  protected AnsibleProject jobTemplateResourcesInit() {
    _jobTemplateResources(jobTemplateResources);
    return (AnsibleProject)this;
  }

  public static String staticSearchJobTemplateResources(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrJobTemplateResources(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqJobTemplateResources(SiteRequest siteRequest_, String o) {
    return AnsibleProject.staticSearchJobTemplateResources(siteRequest_, AnsibleProject.staticSetJobTemplateResources(siteRequest_, o)).toString();
  }

  public String[] sqlJobTemplateResources() {
    return jobTemplateResources.stream().map(v -> (String)v).toArray(String[]::new);
  }

  public static JsonArray staticJsonJobTemplateResources(List<String> jobTemplateResources) {
    JsonArray a = new JsonArray();
    jobTemplateResources.stream().forEach(v -> a.add(v.toString()));
    return a;
  }

  //////////////
  // initDeep //
  //////////////

  public Future<AnsibleProjectGen<DEV>> promiseDeepAnsibleProject(SiteRequest siteRequest_) {
    setSiteRequest_(siteRequest_);
    return promiseDeepAnsibleProject();
  }

  public Future<AnsibleProjectGen<DEV>> promiseDeepAnsibleProject() {
    Promise<AnsibleProjectGen<DEV>> promise = Promise.promise();
    Promise<Void> promise2 = Promise.promise();
    promiseAnsibleProject(promise2);
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

  public Future<Void> promiseAnsibleProject(Promise<Void> promise) {
    Future.future(a -> a.complete()).compose(a -> {
      Promise<Void> promise2 = Promise.promise();
      try {
        tenantResourceInit();
        tenantIdInit();
        aapOrganizationIdInit();
        sourceControlTypeInit();
        sourceControlUrlInit();
        sourceControlBranchInit();
        ansibleProjectNameInit();
        ansibleProjectIdInit();
        ansibleProjectResourceInit();
        aapProjectIdInit();
        ansibleProjectDescriptionInit();
        jobTemplateResourcesInit();
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

  @Override public Future<? extends AnsibleProjectGen<DEV>> promiseDeepForClass(SiteRequest siteRequest_) {
    return promiseDeepAnsibleProject(siteRequest_);
  }

  /////////////////
  // siteRequest //
  /////////////////

  public void siteRequestAnsibleProject(SiteRequest siteRequest_) {
      super.siteRequestBaseModel(siteRequest_);
  }

  public void siteRequestForClass(SiteRequest siteRequest_) {
    siteRequestAnsibleProject(siteRequest_);
  }

  /////////////
  // obtain //
  /////////////

  @Override public Object obtainForClass(String var) {
    String[] vars = StringUtils.split(var, ".");
    Object o = null;
    for(String v : vars) {
      if(o == null)
        o = obtainAnsibleProject(v);
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
  public Object obtainAnsibleProject(String var) {
    AnsibleProject oAnsibleProject = (AnsibleProject)this;
    switch(var) {
      case "tenantResource":
        return oAnsibleProject.tenantResource;
      case "tenantId":
        return oAnsibleProject.tenantId;
      case "aapOrganizationId":
        return oAnsibleProject.aapOrganizationId;
      case "sourceControlType":
        return oAnsibleProject.sourceControlType;
      case "sourceControlUrl":
        return oAnsibleProject.sourceControlUrl;
      case "sourceControlBranch":
        return oAnsibleProject.sourceControlBranch;
      case "ansibleProjectName":
        return oAnsibleProject.ansibleProjectName;
      case "ansibleProjectId":
        return oAnsibleProject.ansibleProjectId;
      case "ansibleProjectResource":
        return oAnsibleProject.ansibleProjectResource;
      case "aapProjectId":
        return oAnsibleProject.aapProjectId;
      case "ansibleProjectDescription":
        return oAnsibleProject.ansibleProjectDescription;
      case "jobTemplateResources":
        return oAnsibleProject.jobTemplateResources;
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
        o = relateAnsibleProject(v, val);
      else if(o instanceof BaseModel) {
        BaseModel baseModel = (BaseModel)o;
        o = baseModel.relateForClass(v, val);
      }
    }
    return o != null;
  }
  public Object relateAnsibleProject(String var, Object val) {
    AnsibleProject oAnsibleProject = (AnsibleProject)this;
    switch(var) {
      case "tenantResource":
        if(oAnsibleProject.getTenantResource() == null)
          oAnsibleProject.setTenantResource(Optional.ofNullable(val).map(v -> v.toString()).orElse(null));
        if(!saves.contains("tenantResource"))
          saves.add("tenantResource");
        return val;
      case "jobTemplateResources":
        oAnsibleProject.addJobTemplateResources((String)val);
        if(!saves.contains("jobTemplateResources"))
          saves.add("jobTemplateResources");
        return val;
      default:
        return super.relateBaseModel(var, val);
    }
  }

  ///////////////
  // staticSet //
  ///////////////

  public static Object staticSetForClass(String entityVar, SiteRequest siteRequest_, String v, AnsibleProject o) {
    return staticSetAnsibleProject(entityVar,  siteRequest_, v, o);
  }
  public static Object staticSetAnsibleProject(String entityVar, SiteRequest siteRequest_, String v, AnsibleProject o) {
    switch(entityVar) {
    case "tenantResource":
      return AnsibleProject.staticSetTenantResource(siteRequest_, v);
    case "tenantId":
      return AnsibleProject.staticSetTenantId(siteRequest_, v);
    case "aapOrganizationId":
      return AnsibleProject.staticSetAapOrganizationId(siteRequest_, v);
    case "sourceControlType":
      return AnsibleProject.staticSetSourceControlType(siteRequest_, v);
    case "sourceControlUrl":
      return AnsibleProject.staticSetSourceControlUrl(siteRequest_, v);
    case "sourceControlBranch":
      return AnsibleProject.staticSetSourceControlBranch(siteRequest_, v);
    case "ansibleProjectName":
      return AnsibleProject.staticSetAnsibleProjectName(siteRequest_, v);
    case "ansibleProjectId":
      return AnsibleProject.staticSetAnsibleProjectId(siteRequest_, v);
    case "ansibleProjectResource":
      return AnsibleProject.staticSetAnsibleProjectResource(siteRequest_, v);
    case "aapProjectId":
      return AnsibleProject.staticSetAapProjectId(siteRequest_, v);
    case "ansibleProjectDescription":
      return AnsibleProject.staticSetAnsibleProjectDescription(siteRequest_, v);
    case "jobTemplateResources":
      return AnsibleProject.staticSetJobTemplateResources(siteRequest_, v);
      default:
        return BaseModel.staticSetBaseModel(entityVar,  siteRequest_, v, o);
    }
  }

  //////////////////
  // staticSearch //
  //////////////////

  public static Future<AnsibleProject> fqAnsibleProject(SiteRequest siteRequest, String var, Object val) {
    Promise<AnsibleProject> promise = Promise.promise();
    try {
      if(val == null) {
        promise.complete();
      } else {
        SearchList<AnsibleProject> searchList = new SearchList<AnsibleProject>();
        searchList.setStore(true);
        searchList.q("*:*");
        searchList.setC(AnsibleProject.class);
        searchList.fq(String.format("%s:", AnsibleProject.varIndexedAnsibleProject(var)) + SearchTool.escapeQueryChars(val.toString()));
        searchList.promiseDeepForClass(siteRequest).onSuccess(a -> {
          try {
            promise.complete(searchList.getList().stream().findFirst().orElse(null));
          } catch(Throwable ex) {
            LOG.error("Error while querying theansible project", ex);
            promise.fail(ex);
          }
        }).onFailure(ex -> {
          LOG.error("Error while querying theansible project", ex);
          promise.fail(ex);
        });
      }
    } catch(Throwable ex) {
      LOG.error("Error while querying theansible project", ex);
      promise.fail(ex);
    }
    return promise.future();
  }

  public static Object staticSearchForClass(String entityVar, SiteRequest siteRequest_, Object o) {
    return staticSearchAnsibleProject(entityVar,  siteRequest_, o);
  }
  public static Object staticSearchAnsibleProject(String entityVar, SiteRequest siteRequest_, Object o) {
    switch(entityVar) {
    case "tenantResource":
      return AnsibleProject.staticSearchTenantResource(siteRequest_, (String)o);
    case "tenantId":
      return AnsibleProject.staticSearchTenantId(siteRequest_, (String)o);
    case "aapOrganizationId":
      return AnsibleProject.staticSearchAapOrganizationId(siteRequest_, (Long)o);
    case "sourceControlType":
      return AnsibleProject.staticSearchSourceControlType(siteRequest_, (String)o);
    case "sourceControlUrl":
      return AnsibleProject.staticSearchSourceControlUrl(siteRequest_, (String)o);
    case "sourceControlBranch":
      return AnsibleProject.staticSearchSourceControlBranch(siteRequest_, (String)o);
    case "ansibleProjectName":
      return AnsibleProject.staticSearchAnsibleProjectName(siteRequest_, (String)o);
    case "ansibleProjectId":
      return AnsibleProject.staticSearchAnsibleProjectId(siteRequest_, (String)o);
    case "ansibleProjectResource":
      return AnsibleProject.staticSearchAnsibleProjectResource(siteRequest_, (String)o);
    case "aapProjectId":
      return AnsibleProject.staticSearchAapProjectId(siteRequest_, (Long)o);
    case "ansibleProjectDescription":
      return AnsibleProject.staticSearchAnsibleProjectDescription(siteRequest_, (String)o);
    case "jobTemplateResources":
      return AnsibleProject.staticSearchJobTemplateResources(siteRequest_, (String)o);
      default:
        return BaseModel.staticSearchBaseModel(entityVar,  siteRequest_, o);
    }
  }

  ///////////////////
  // staticSearchStr //
  ///////////////////

  public static String staticSearchStrForClass(String entityVar, SiteRequest siteRequest_, Object o) {
    return staticSearchStrAnsibleProject(entityVar,  siteRequest_, o);
  }
  public static String staticSearchStrAnsibleProject(String entityVar, SiteRequest siteRequest_, Object o) {
    switch(entityVar) {
    case "tenantResource":
      return AnsibleProject.staticSearchStrTenantResource(siteRequest_, (String)o);
    case "tenantId":
      return AnsibleProject.staticSearchStrTenantId(siteRequest_, (String)o);
    case "aapOrganizationId":
      return AnsibleProject.staticSearchStrAapOrganizationId(siteRequest_, (Long)o);
    case "sourceControlType":
      return AnsibleProject.staticSearchStrSourceControlType(siteRequest_, (String)o);
    case "sourceControlUrl":
      return AnsibleProject.staticSearchStrSourceControlUrl(siteRequest_, (String)o);
    case "sourceControlBranch":
      return AnsibleProject.staticSearchStrSourceControlBranch(siteRequest_, (String)o);
    case "ansibleProjectName":
      return AnsibleProject.staticSearchStrAnsibleProjectName(siteRequest_, (String)o);
    case "ansibleProjectId":
      return AnsibleProject.staticSearchStrAnsibleProjectId(siteRequest_, (String)o);
    case "ansibleProjectResource":
      return AnsibleProject.staticSearchStrAnsibleProjectResource(siteRequest_, (String)o);
    case "aapProjectId":
      return AnsibleProject.staticSearchStrAapProjectId(siteRequest_, (Long)o);
    case "ansibleProjectDescription":
      return AnsibleProject.staticSearchStrAnsibleProjectDescription(siteRequest_, (String)o);
    case "jobTemplateResources":
      return AnsibleProject.staticSearchStrJobTemplateResources(siteRequest_, (String)o);
      default:
        return BaseModel.staticSearchStrBaseModel(entityVar,  siteRequest_, o);
    }
  }

  //////////////////
  // staticSearchFq //
  //////////////////

  public static String staticSearchFqForClass(String entityVar, SiteRequest siteRequest_, String o) {
    return staticSearchFqAnsibleProject(entityVar,  siteRequest_, o);
  }
  public static String staticSearchFqAnsibleProject(String entityVar, SiteRequest siteRequest_, String o) {
    switch(entityVar) {
    case "tenantResource":
      return AnsibleProject.staticSearchFqTenantResource(siteRequest_, o);
    case "tenantId":
      return AnsibleProject.staticSearchFqTenantId(siteRequest_, o);
    case "aapOrganizationId":
      return AnsibleProject.staticSearchFqAapOrganizationId(siteRequest_, o);
    case "sourceControlType":
      return AnsibleProject.staticSearchFqSourceControlType(siteRequest_, o);
    case "sourceControlUrl":
      return AnsibleProject.staticSearchFqSourceControlUrl(siteRequest_, o);
    case "sourceControlBranch":
      return AnsibleProject.staticSearchFqSourceControlBranch(siteRequest_, o);
    case "ansibleProjectName":
      return AnsibleProject.staticSearchFqAnsibleProjectName(siteRequest_, o);
    case "ansibleProjectId":
      return AnsibleProject.staticSearchFqAnsibleProjectId(siteRequest_, o);
    case "ansibleProjectResource":
      return AnsibleProject.staticSearchFqAnsibleProjectResource(siteRequest_, o);
    case "aapProjectId":
      return AnsibleProject.staticSearchFqAapProjectId(siteRequest_, o);
    case "ansibleProjectDescription":
      return AnsibleProject.staticSearchFqAnsibleProjectDescription(siteRequest_, o);
    case "jobTemplateResources":
      return AnsibleProject.staticSearchFqJobTemplateResources(siteRequest_, o);
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
          o = persistAnsibleProject(v, val);
        else if(o instanceof BaseModel) {
          BaseModel oBaseModel = (BaseModel)o;
          o = oBaseModel.persistForClass(v, val);
        }
      }
    }
    return o != null;
  }
  public Object persistAnsibleProject(String var, Object val) {
    String varLower = var.toLowerCase();
      if("tenantresource".equals(varLower)) {
        if(val instanceof String) {
          setTenantResource((String)val);
        }
        saves.add("tenantResource");
        return val;
      } else if("tenantid".equals(varLower)) {
        if(val instanceof String) {
          setTenantId((String)val);
        }
        saves.add("tenantId");
        return val;
      } else if("aaporganizationid".equals(varLower)) {
        if(val instanceof Long) {
          setAapOrganizationId((Long)val);
        } else {
          setAapOrganizationId(val == null ? null : val.toString());
        }
        saves.add("aapOrganizationId");
        return val;
      } else if("sourcecontroltype".equals(varLower)) {
        if(val instanceof String) {
          setSourceControlType((String)val);
        }
        saves.add("sourceControlType");
        return val;
      } else if("sourcecontrolurl".equals(varLower)) {
        if(val instanceof String) {
          setSourceControlUrl((String)val);
        }
        saves.add("sourceControlUrl");
        return val;
      } else if("sourcecontrolbranch".equals(varLower)) {
        if(val instanceof String) {
          setSourceControlBranch((String)val);
        }
        saves.add("sourceControlBranch");
        return val;
      } else if("ansibleprojectname".equals(varLower)) {
        if(val instanceof String) {
          setAnsibleProjectName((String)val);
        }
        saves.add("ansibleProjectName");
        return val;
      } else if("ansibleprojectid".equals(varLower)) {
        if(val instanceof String) {
          setAnsibleProjectId((String)val);
        }
        saves.add("ansibleProjectId");
        return val;
      } else if("ansibleprojectresource".equals(varLower)) {
        if(val instanceof String) {
          setAnsibleProjectResource((String)val);
        }
        saves.add("ansibleProjectResource");
        return val;
      } else if("aapprojectid".equals(varLower)) {
        if(val instanceof Long) {
          setAapProjectId((Long)val);
        } else {
          setAapProjectId(val == null ? null : val.toString());
        }
        saves.add("aapProjectId");
        return val;
      } else if("ansibleprojectdescription".equals(varLower)) {
        if(val instanceof String) {
          setAnsibleProjectDescription((String)val);
        }
        saves.add("ansibleProjectDescription");
        return val;
    } else {
      return super.persistBaseModel(var, val);
    }
  }

  /////////////
  // populate //
  /////////////

  @Override public void populateForClass(SolrResponse.Doc doc) {
    populateAnsibleProject(doc);
  }
  public void populateAnsibleProject(SolrResponse.Doc doc) {
    AnsibleProject oAnsibleProject = (AnsibleProject)this;
    saves = Optional.ofNullable((ArrayList<String>)doc.get("saves_docvalues_strings")).orElse(new ArrayList<String>());
    if(saves != null) {

      String tenantResource = (String)doc.get("tenantResource_docvalues_string");
      if(tenantResource != null)
        oAnsibleProject.setTenantResource(tenantResource);

      if(saves.contains("tenantId")) {
        String tenantId = (String)doc.get("tenantId_docvalues_string");
        if(tenantId != null)
          oAnsibleProject.setTenantId(tenantId);
      }

      if(saves.contains("aapOrganizationId")) {
        Long aapOrganizationId = (Long)doc.get("aapOrganizationId_docvalues_long");
        if(aapOrganizationId != null)
          oAnsibleProject.setAapOrganizationId(aapOrganizationId);
      }

      if(saves.contains("sourceControlType")) {
        String sourceControlType = (String)doc.get("sourceControlType_docvalues_string");
        if(sourceControlType != null)
          oAnsibleProject.setSourceControlType(sourceControlType);
      }

      if(saves.contains("sourceControlUrl")) {
        String sourceControlUrl = (String)doc.get("sourceControlUrl_docvalues_string");
        if(sourceControlUrl != null)
          oAnsibleProject.setSourceControlUrl(sourceControlUrl);
      }

      if(saves.contains("sourceControlBranch")) {
        String sourceControlBranch = (String)doc.get("sourceControlBranch_docvalues_string");
        if(sourceControlBranch != null)
          oAnsibleProject.setSourceControlBranch(sourceControlBranch);
      }

      if(saves.contains("ansibleProjectName")) {
        String ansibleProjectName = (String)doc.get("ansibleProjectName_docvalues_string");
        if(ansibleProjectName != null)
          oAnsibleProject.setAnsibleProjectName(ansibleProjectName);
      }

      if(saves.contains("ansibleProjectId")) {
        String ansibleProjectId = (String)doc.get("ansibleProjectId_docvalues_string");
        if(ansibleProjectId != null)
          oAnsibleProject.setAnsibleProjectId(ansibleProjectId);
      }

      if(saves.contains("ansibleProjectResource")) {
        String ansibleProjectResource = (String)doc.get("ansibleProjectResource_docvalues_string");
        if(ansibleProjectResource != null)
          oAnsibleProject.setAnsibleProjectResource(ansibleProjectResource);
      }

      if(saves.contains("aapProjectId")) {
        Long aapProjectId = (Long)doc.get("aapProjectId_docvalues_long");
        if(aapProjectId != null)
          oAnsibleProject.setAapProjectId(aapProjectId);
      }

      if(saves.contains("ansibleProjectDescription")) {
        String ansibleProjectDescription = (String)doc.get("ansibleProjectDescription_docvalues_string");
        if(ansibleProjectDescription != null)
          oAnsibleProject.setAnsibleProjectDescription(ansibleProjectDescription);
      }

      List<String> jobTemplateResources = (List<String>)doc.get("jobTemplateResources_docvalues_strings");
      if(jobTemplateResources != null)
        oAnsibleProject.jobTemplateResources.addAll(jobTemplateResources);
    }

    super.populateBaseModel(doc);
  }

  public void indexAnsibleProject(JsonObject doc) {
    if(tenantResource != null) {
      doc.put("tenantResource_docvalues_string", tenantResource);
    }
    if(tenantId != null) {
      doc.put("tenantId_docvalues_string", tenantId);
    }
    if(aapOrganizationId != null) {
      doc.put("aapOrganizationId_docvalues_long", aapOrganizationId);
    }
    if(sourceControlType != null) {
      doc.put("sourceControlType_docvalues_string", sourceControlType);
    }
    if(sourceControlUrl != null) {
      doc.put("sourceControlUrl_docvalues_string", sourceControlUrl);
    }
    if(sourceControlBranch != null) {
      doc.put("sourceControlBranch_docvalues_string", sourceControlBranch);
    }
    if(ansibleProjectName != null) {
      doc.put("ansibleProjectName_docvalues_string", ansibleProjectName);
    }
    if(ansibleProjectId != null) {
      doc.put("ansibleProjectId_docvalues_string", ansibleProjectId);
    }
    if(ansibleProjectResource != null) {
      doc.put("ansibleProjectResource_docvalues_string", ansibleProjectResource);
    }
    if(aapProjectId != null) {
      doc.put("aapProjectId_docvalues_long", aapProjectId);
    }
    if(ansibleProjectDescription != null) {
      doc.put("ansibleProjectDescription_docvalues_string", ansibleProjectDescription);
    }
    if(jobTemplateResources != null) {
      JsonArray l = new JsonArray();
      doc.put("jobTemplateResources_docvalues_strings", l);
      for(String o : jobTemplateResources) {
        l.add(AnsibleProject.staticSearchJobTemplateResources(siteRequest_, o));
      }
    }
    super.indexBaseModel(doc);

	}

  public static String varStoredAnsibleProject(String entityVar) {
    switch(entityVar) {
      case "tenantResource":
        return "tenantResource_docvalues_string";
      case "tenantId":
        return "tenantId_docvalues_string";
      case "aapOrganizationId":
        return "aapOrganizationId_docvalues_long";
      case "sourceControlType":
        return "sourceControlType_docvalues_string";
      case "sourceControlUrl":
        return "sourceControlUrl_docvalues_string";
      case "sourceControlBranch":
        return "sourceControlBranch_docvalues_string";
      case "ansibleProjectName":
        return "ansibleProjectName_docvalues_string";
      case "ansibleProjectId":
        return "ansibleProjectId_docvalues_string";
      case "ansibleProjectResource":
        return "ansibleProjectResource_docvalues_string";
      case "aapProjectId":
        return "aapProjectId_docvalues_long";
      case "ansibleProjectDescription":
        return "ansibleProjectDescription_docvalues_string";
      case "jobTemplateResources":
        return "jobTemplateResources_docvalues_strings";
      default:
        return BaseModel.varStoredBaseModel(entityVar);
    }
  }

  public static String varIndexedAnsibleProject(String entityVar) {
    switch(entityVar) {
      case "tenantResource":
        return "tenantResource_docvalues_string";
      case "tenantId":
        return "tenantId_docvalues_string";
      case "aapOrganizationId":
        return "aapOrganizationId_docvalues_long";
      case "sourceControlType":
        return "sourceControlType_docvalues_string";
      case "sourceControlUrl":
        return "sourceControlUrl_docvalues_string";
      case "sourceControlBranch":
        return "sourceControlBranch_docvalues_string";
      case "ansibleProjectName":
        return "ansibleProjectName_docvalues_string";
      case "ansibleProjectId":
        return "ansibleProjectId_docvalues_string";
      case "ansibleProjectResource":
        return "ansibleProjectResource_docvalues_string";
      case "aapProjectId":
        return "aapProjectId_docvalues_long";
      case "ansibleProjectDescription":
        return "ansibleProjectDescription_docvalues_string";
      case "jobTemplateResources":
        return "jobTemplateResources_docvalues_strings";
      default:
        return BaseModel.varIndexedBaseModel(entityVar);
    }
  }

  public static String searchVarAnsibleProject(String searchVar) {
    switch(searchVar) {
      case "tenantResource_docvalues_string":
        return "tenantResource";
      case "tenantId_docvalues_string":
        return "tenantId";
      case "aapOrganizationId_docvalues_long":
        return "aapOrganizationId";
      case "sourceControlType_docvalues_string":
        return "sourceControlType";
      case "sourceControlUrl_docvalues_string":
        return "sourceControlUrl";
      case "sourceControlBranch_docvalues_string":
        return "sourceControlBranch";
      case "ansibleProjectName_docvalues_string":
        return "ansibleProjectName";
      case "ansibleProjectId_docvalues_string":
        return "ansibleProjectId";
      case "ansibleProjectResource_docvalues_string":
        return "ansibleProjectResource";
      case "aapProjectId_docvalues_long":
        return "aapProjectId";
      case "ansibleProjectDescription_docvalues_string":
        return "ansibleProjectDescription";
      case "jobTemplateResources_docvalues_strings":
        return "jobTemplateResources";
      default:
        return BaseModel.searchVarBaseModel(searchVar);
    }
  }

  public static String varSearchAnsibleProject(String entityVar) {
    switch(entityVar) {
      default:
        return BaseModel.varSearchBaseModel(entityVar);
    }
  }

  public static String varSuggestedAnsibleProject(String entityVar) {
    switch(entityVar) {
      default:
        return BaseModel.varSuggestedBaseModel(entityVar);
    }
  }

  /////////////
  // store //
  /////////////

  @Override public void storeForClass(SolrResponse.Doc doc) {
    storeAnsibleProject(doc);
  }
  public void storeAnsibleProject(SolrResponse.Doc doc) {
    AnsibleProject oAnsibleProject = (AnsibleProject)this;
    SiteRequest siteRequest = oAnsibleProject.getSiteRequest_();

    oAnsibleProject.setTenantResource(Optional.ofNullable(doc.get("tenantResource_docvalues_string")).map(v -> v.toString()).orElse(null));
    oAnsibleProject.setTenantId(Optional.ofNullable(doc.get("tenantId_docvalues_string")).map(v -> v.toString()).orElse(null));
    oAnsibleProject.setAapOrganizationId(Optional.ofNullable(doc.get("aapOrganizationId_docvalues_long")).map(v -> v.toString()).orElse(null));
    oAnsibleProject.setSourceControlType(Optional.ofNullable(doc.get("sourceControlType_docvalues_string")).map(v -> v.toString()).orElse(null));
    oAnsibleProject.setSourceControlUrl(Optional.ofNullable(doc.get("sourceControlUrl_docvalues_string")).map(v -> v.toString()).orElse(null));
    oAnsibleProject.setSourceControlBranch(Optional.ofNullable(doc.get("sourceControlBranch_docvalues_string")).map(v -> v.toString()).orElse(null));
    oAnsibleProject.setAnsibleProjectName(Optional.ofNullable(doc.get("ansibleProjectName_docvalues_string")).map(v -> v.toString()).orElse(null));
    oAnsibleProject.setAnsibleProjectId(Optional.ofNullable(doc.get("ansibleProjectId_docvalues_string")).map(v -> v.toString()).orElse(null));
    oAnsibleProject.setAnsibleProjectResource(Optional.ofNullable(doc.get("ansibleProjectResource_docvalues_string")).map(v -> v.toString()).orElse(null));
    oAnsibleProject.setAapProjectId(Optional.ofNullable(doc.get("aapProjectId_docvalues_long")).map(v -> v.toString()).orElse(null));
    oAnsibleProject.setAnsibleProjectDescription(Optional.ofNullable(doc.get("ansibleProjectDescription_docvalues_string")).map(v -> v.toString()).orElse(null));
    Optional.ofNullable((List<?>)doc.get("jobTemplateResources_docvalues_strings")).orElse(Arrays.asList()).stream().filter(v -> v != null).forEach(v -> {
      oAnsibleProject.addJobTemplateResources(AnsibleProject.staticSetJobTemplateResources(siteRequest, v.toString()));
    });

    super.storeBaseModel(doc);
  }

  //////////////////
  // apiRequest //
  //////////////////

  public void apiRequestAnsibleProject() {
    ApiRequest apiRequest = Optional.ofNullable(siteRequest_).map(r -> r.getApiRequest_()).orElse(null);
    Object o = Optional.ofNullable(apiRequest).map(ApiRequest::getOriginal).orElse(null);
    if(o != null && o instanceof AnsibleProject) {
      AnsibleProject original = (AnsibleProject)o;
      if(!Objects.equals(tenantResource, original.getTenantResource()))
        apiRequest.addVars("tenantResource");
      if(!Objects.equals(tenantId, original.getTenantId()))
        apiRequest.addVars("tenantId");
      if(!Objects.equals(aapOrganizationId, original.getAapOrganizationId()))
        apiRequest.addVars("aapOrganizationId");
      if(!Objects.equals(sourceControlType, original.getSourceControlType()))
        apiRequest.addVars("sourceControlType");
      if(!Objects.equals(sourceControlUrl, original.getSourceControlUrl()))
        apiRequest.addVars("sourceControlUrl");
      if(!Objects.equals(sourceControlBranch, original.getSourceControlBranch()))
        apiRequest.addVars("sourceControlBranch");
      if(!Objects.equals(ansibleProjectName, original.getAnsibleProjectName()))
        apiRequest.addVars("ansibleProjectName");
      if(!Objects.equals(ansibleProjectId, original.getAnsibleProjectId()))
        apiRequest.addVars("ansibleProjectId");
      if(!Objects.equals(ansibleProjectResource, original.getAnsibleProjectResource()))
        apiRequest.addVars("ansibleProjectResource");
      if(!Objects.equals(aapProjectId, original.getAapProjectId()))
        apiRequest.addVars("aapProjectId");
      if(!Objects.equals(ansibleProjectDescription, original.getAnsibleProjectDescription()))
        apiRequest.addVars("ansibleProjectDescription");
      if(!Objects.equals(jobTemplateResources, original.getJobTemplateResources()))
        apiRequest.addVars("jobTemplateResources");
      super.apiRequestBaseModel();
    }
  }

  //////////////
  // toString //
  //////////////

  @Override public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(super.toString());
    sb.append(Optional.ofNullable(tenantResource).map(v -> "tenantResource: \"" + v + "\"\n" ).orElse(""));
    sb.append(Optional.ofNullable(tenantId).map(v -> "tenantId: \"" + v + "\"\n" ).orElse(""));
    sb.append(Optional.ofNullable(aapOrganizationId).map(v -> "aapOrganizationId: " + v + "\n").orElse(""));
    sb.append(Optional.ofNullable(sourceControlType).map(v -> "sourceControlType: \"" + v + "\"\n" ).orElse(""));
    sb.append(Optional.ofNullable(sourceControlUrl).map(v -> "sourceControlUrl: \"" + v + "\"\n" ).orElse(""));
    sb.append(Optional.ofNullable(sourceControlBranch).map(v -> "sourceControlBranch: \"" + v + "\"\n" ).orElse(""));
    sb.append(Optional.ofNullable(ansibleProjectName).map(v -> "ansibleProjectName: \"" + v + "\"\n" ).orElse(""));
    sb.append(Optional.ofNullable(ansibleProjectId).map(v -> "ansibleProjectId: \"" + v + "\"\n" ).orElse(""));
    sb.append(Optional.ofNullable(ansibleProjectResource).map(v -> "ansibleProjectResource: \"" + v + "\"\n" ).orElse(""));
    sb.append(Optional.ofNullable(aapProjectId).map(v -> "aapProjectId: " + v + "\n").orElse(""));
    sb.append(Optional.ofNullable(ansibleProjectDescription).map(v -> "ansibleProjectDescription: \"" + v + "\"\n" ).orElse(""));
    sb.append(Optional.ofNullable(jobTemplateResources).map(v -> "jobTemplateResources: " + v + "\n").orElse(""));
    return sb.toString();
  }

  public static final String CLASS_SIMPLE_NAME = "AnsibleProject";
  public static final String CLASS_CANONICAL_NAME = "org.computate.dcm.model.eda.ansibleproject.AnsibleProject";
  public static final String CLASS_AUTH_RESOURCE = "ANSIBLEPROJECT";
  public static final String CLASS_API_ADDRESS_AnsibleProject = "dcm-enUS-AnsibleProject";
  public static String getClassApiAddress() {
    return CLASS_API_ADDRESS_AnsibleProject;
  }
  public static final String VAR_tenantResource = "tenantResource";
  public static final String SET_tenantResource = "setTenantResource";
  public static final String VAR_tenantId = "tenantId";
  public static final String SET_tenantId = "setTenantId";
  public static final String VAR_aapOrganizationId = "aapOrganizationId";
  public static final String SET_aapOrganizationId = "setAapOrganizationId";
  public static final String VAR_sourceControlType = "sourceControlType";
  public static final String SET_sourceControlType = "setSourceControlType";
  public static final String VAR_sourceControlUrl = "sourceControlUrl";
  public static final String SET_sourceControlUrl = "setSourceControlUrl";
  public static final String VAR_sourceControlBranch = "sourceControlBranch";
  public static final String SET_sourceControlBranch = "setSourceControlBranch";
  public static final String VAR_ansibleProjectName = "ansibleProjectName";
  public static final String SET_ansibleProjectName = "setAnsibleProjectName";
  public static final String VAR_ansibleProjectId = "ansibleProjectId";
  public static final String SET_ansibleProjectId = "setAnsibleProjectId";
  public static final String VAR_ansibleProjectResource = "ansibleProjectResource";
  public static final String SET_ansibleProjectResource = "setAnsibleProjectResource";
  public static final String VAR_aapProjectId = "aapProjectId";
  public static final String SET_aapProjectId = "setAapProjectId";
  public static final String VAR_ansibleProjectDescription = "ansibleProjectDescription";
  public static final String SET_ansibleProjectDescription = "setAnsibleProjectDescription";
  public static final String VAR_jobTemplateResources = "jobTemplateResources";
  public static final String SET_jobTemplateResources = "setJobTemplateResources";

  public static List<String> varsQForClass() {
    return AnsibleProject.varsQAnsibleProject(new ArrayList<String>());
  }
  public static List<String> varsQAnsibleProject(List<String> vars) {
    BaseModel.varsQBaseModel(vars);
    return vars;
  }

  public static List<String> varsFqForClass() {
    return AnsibleProject.varsFqAnsibleProject(new ArrayList<String>());
  }
  public static List<String> varsFqAnsibleProject(List<String> vars) {
    vars.add(VAR_tenantId);
    BaseModel.varsFqBaseModel(vars);
    return vars;
  }

  public static List<String> varsRangeForClass() {
    return AnsibleProject.varsRangeAnsibleProject(new ArrayList<String>());
  }
  public static List<String> varsRangeAnsibleProject(List<String> vars) {
    BaseModel.varsRangeBaseModel(vars);
    return vars;
  }

  public static final String DISPLAY_NAME_tenantResource = "tenant";
  public static final String DISPLAY_NAME_tenantId = "tenant ID";
  public static final String DISPLAY_NAME_aapOrganizationId = "AAP organization ID";
  public static final String DISPLAY_NAME_sourceControlType = "source control type";
  public static final String DISPLAY_NAME_sourceControlUrl = "source control URL";
  public static final String DISPLAY_NAME_sourceControlBranch = "source control branch";
  public static final String DISPLAY_NAME_ansibleProjectName = "ansible project name";
  public static final String DISPLAY_NAME_ansibleProjectId = "Ansible project ID";
  public static final String DISPLAY_NAME_ansibleProjectResource = "project resource";
  public static final String DISPLAY_NAME_aapProjectId = "AAP project ID";
  public static final String DISPLAY_NAME_ansibleProjectDescription = "ansible project description";
  public static final String DISPLAY_NAME_jobTemplateResources = "job templates";

  @Override
  public String idForClass() {
    return ansibleProjectResource;
  }

  @Override
  public String titleForClass() {
    return objectTitle;
  }

  @Override
  public String nameForClass() {
    return ansibleProjectName;
  }

  @Override
  public String classNameAdjectiveSingularForClass() {
    return AnsibleProject.NameAdjectiveSingular_enUS;
  }

  @Override
  public String descriptionForClass() {
    return ansibleProjectDescription;
  }

  @Override
  public String enUSStringFormatUrlEditPageForClass() {
    return "%s/en-us/edit/ansible-project/%s";
  }

  public static String varJsonForClass(String var, Boolean patch) {
    return AnsibleProject.varJsonAnsibleProject(var, patch);
  }
  public static String varJsonAnsibleProject(String var, Boolean patch) {
    switch(var) {
    case VAR_tenantResource:
      return patch ? SET_tenantResource : VAR_tenantResource;
    case VAR_tenantId:
      return patch ? SET_tenantId : VAR_tenantId;
    case VAR_aapOrganizationId:
      return patch ? SET_aapOrganizationId : VAR_aapOrganizationId;
    case VAR_sourceControlType:
      return patch ? SET_sourceControlType : VAR_sourceControlType;
    case VAR_sourceControlUrl:
      return patch ? SET_sourceControlUrl : VAR_sourceControlUrl;
    case VAR_sourceControlBranch:
      return patch ? SET_sourceControlBranch : VAR_sourceControlBranch;
    case VAR_ansibleProjectName:
      return patch ? SET_ansibleProjectName : VAR_ansibleProjectName;
    case VAR_ansibleProjectId:
      return patch ? SET_ansibleProjectId : VAR_ansibleProjectId;
    case VAR_ansibleProjectResource:
      return patch ? SET_ansibleProjectResource : VAR_ansibleProjectResource;
    case VAR_aapProjectId:
      return patch ? SET_aapProjectId : VAR_aapProjectId;
    case VAR_ansibleProjectDescription:
      return patch ? SET_ansibleProjectDescription : VAR_ansibleProjectDescription;
    case VAR_jobTemplateResources:
      return patch ? SET_jobTemplateResources : VAR_jobTemplateResources;
    default:
      return BaseModel.varJsonBaseModel(var, patch);
    }
  }

  public static String displayNameForClass(String var) {
    return AnsibleProject.displayNameAnsibleProject(var);
  }
  public static String displayNameAnsibleProject(String var) {
    switch(var) {
    case VAR_tenantResource:
      return DISPLAY_NAME_tenantResource;
    case VAR_tenantId:
      return DISPLAY_NAME_tenantId;
    case VAR_aapOrganizationId:
      return DISPLAY_NAME_aapOrganizationId;
    case VAR_sourceControlType:
      return DISPLAY_NAME_sourceControlType;
    case VAR_sourceControlUrl:
      return DISPLAY_NAME_sourceControlUrl;
    case VAR_sourceControlBranch:
      return DISPLAY_NAME_sourceControlBranch;
    case VAR_ansibleProjectName:
      return DISPLAY_NAME_ansibleProjectName;
    case VAR_ansibleProjectId:
      return DISPLAY_NAME_ansibleProjectId;
    case VAR_ansibleProjectResource:
      return DISPLAY_NAME_ansibleProjectResource;
    case VAR_aapProjectId:
      return DISPLAY_NAME_aapProjectId;
    case VAR_ansibleProjectDescription:
      return DISPLAY_NAME_ansibleProjectDescription;
    case VAR_jobTemplateResources:
      return DISPLAY_NAME_jobTemplateResources;
    default:
      return BaseModel.displayNameBaseModel(var);
    }
  }

  public static String descriptionAnsibleProject(String var) {
    if(var == null)
      return null;
    switch(var) {
    case VAR_tenantResource:
      return "The unique authorization resource for the tenant for multi-tenancy";
    case VAR_tenantId:
      return "The ID of this tenant";
    case VAR_aapOrganizationId:
      return "The ID of the ansible organization in AAP. ";
    case VAR_sourceControlType:
      return "The type of source source control to use. ";
    case VAR_sourceControlUrl:
      return "The URL to the source control repository. ";
    case VAR_sourceControlBranch:
      return "The URL to the source control branch. ";
    case VAR_ansibleProjectName:
      return "The name of the ansible project (may only contain letters, numbers, periods, colons, and dashes). ";
    case VAR_ansibleProjectId:
      return "The ID of the Ansible project in DCM. ";
    case VAR_ansibleProjectResource:
      return "The unique authorization resource for the Ansible project for multi-tenancy";
    case VAR_aapProjectId:
      return "The Ansible project ID in Ansible Automation Platform. ";
    case VAR_ansibleProjectDescription:
      return "The description of the ansible project. ";
    case VAR_jobTemplateResources:
      return "The related job templates for this Ansible project. ";
      default:
        return BaseModel.descriptionBaseModel(var);
    }
  }

  public static String classSimpleNameAnsibleProject(String var) {
    switch(var) {
    case VAR_tenantResource:
      return "String";
    case VAR_tenantId:
      return "String";
    case VAR_aapOrganizationId:
      return "Long";
    case VAR_sourceControlType:
      return "String";
    case VAR_sourceControlUrl:
      return "String";
    case VAR_sourceControlBranch:
      return "String";
    case VAR_ansibleProjectName:
      return "String";
    case VAR_ansibleProjectId:
      return "String";
    case VAR_ansibleProjectResource:
      return "String";
    case VAR_aapProjectId:
      return "Long";
    case VAR_ansibleProjectDescription:
      return "String";
    case VAR_jobTemplateResources:
      return "List";
      default:
        return BaseModel.classSimpleNameBaseModel(var);
    }
  }

  public static Integer htmColumnAnsibleProject(String var) {
    switch(var) {
    case VAR_tenantResource:
      return 0;
    case VAR_ansibleProjectName:
      return 1;
    case VAR_ansibleProjectDescription:
      return 2;
      default:
        return BaseModel.htmColumnBaseModel(var);
    }
  }

  public static Integer htmRowAnsibleProject(String var) {
    switch(var) {
    case VAR_tenantResource:
      return 3;
    case VAR_sourceControlType:
      return 4;
    case VAR_sourceControlUrl:
      return 4;
    case VAR_sourceControlBranch:
      return 4;
    case VAR_ansibleProjectName:
      return 5;
    case VAR_ansibleProjectDescription:
      return 4;
    case VAR_jobTemplateResources:
      return 4;
      default:
        return BaseModel.htmRowBaseModel(var);
    }
  }

  public static Integer htmCellAnsibleProject(String var) {
    switch(var) {
    case VAR_tenantResource:
      return 0;
    case VAR_sourceControlType:
      return 0;
    case VAR_sourceControlUrl:
      return 1;
    case VAR_sourceControlBranch:
      return 2;
    case VAR_ansibleProjectName:
      return 0;
    case VAR_ansibleProjectDescription:
      return 1;
    case VAR_jobTemplateResources:
      return 2;
      default:
        return BaseModel.htmCellBaseModel(var);
    }
  }

  public static Integer lengthMinAnsibleProject(String var) {
    switch(var) {
      default:
        return BaseModel.lengthMinBaseModel(var);
    }
  }

  public static Integer lengthMaxAnsibleProject(String var) {
    switch(var) {
      default:
        return BaseModel.lengthMaxBaseModel(var);
    }
  }

  public static Integer maxAnsibleProject(String var) {
    switch(var) {
      default:
        return BaseModel.maxBaseModel(var);
    }
  }

  public static Integer minAnsibleProject(String var) {
    switch(var) {
      default:
        return BaseModel.minBaseModel(var);
    }
  }
}
