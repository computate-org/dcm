package org.computate.dcm.model.eda.jobtemplate;

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
import org.computate.dcm.model.eda.hostinventory.HostInventory;
import org.computate.dcm.model.eda.ansibleproject.AnsibleProject;
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
 * <li>You can add a class comment "{@inheritDoc}" if you wish to inherit the helpful inherited class comments from class JobTemplateGen into the class JobTemplate. 
 * </li>
 * <h3>About the JobTemplate class and it's generated class JobTemplateGen&lt;BaseModel&gt;: </h3>extends JobTemplateGen
 * <p>
 * This Java class extends a generated Java class JobTemplateGen built by the <a href="https://github.com/computate-org/computate">https://github.com/computate-org/computate</a> project. 
 * Whenever this Java class is modified or touched, the watch service installed as described in the README, indexes all the information about this Java class in a local Apache Solr Search Engine. 
 * If you are running the service, you can see the indexed data about this Java Class here: 
 * </p>
 * <p><a href="https://solr.apps-crc.testing/solr/computate/select?q=*:*&fq=partEstClasse_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.jobtemplate.JobTemplate">Find the class JobTemplate in Solr. </a></p>
 * <p>
 * The extended class ending with "Gen" did not exist at first, but was automatically created by the same watch service based on the data retrieved from the local Apache Server search engine. 
 * The extended class contains many generated fields, getters, setters, initialization code, and helper methods to help build a website and API fast, reactive, and scalable. 
 * </p>
 * extends JobTemplateGen<BaseModel>
 * <p>This <code>class JobTemplate extends JobTemplateGen&lt;BaseModel&gt;</code>, which means it extends a newly generated JobTemplateGen. 
 * The generated <code>class JobTemplateGen extends BaseModel</code> which means that JobTemplate extends JobTemplateGen which extends BaseModel. 
 * This generated inheritance is a powerful feature that allows a lot of boiler plate code to be created for you automatically while still preserving inheritance through the power of Java Generic classes. 
 * </p>
 * <h2>Api: true</h2>
 * <p>This class contains a comment <b>"Api: true"</b>, which means this class will have Java Vert.x API backend code generated for these objects. 
 * </p>
 * <h2>ApiTag.enUS: true</h2>
 * <p>This class contains a comment <b>"ApiTag: job templates"</b>, which groups all of the OpenAPIs for JobTemplate objects under the tag "job templates". 
 * </p>
 * <h2>ApiUri.enUS: /en-us/api/job-template</h2>
 * <p>This class contains a comment <b>"ApiUri: /en-us/api/job-template"</b>, which defines the base API URI for JobTemplate objects as "/en-us/api/job-template" in the OpenAPI spec. 
 * </p>
 * <h2>Color: null</h2>
 * <h2>Indexed: true</h2>
 * <p>This class contains a comment <b>"Indexed: true"</b>, which means this class will be indexed in the search engine. 
 * Every protected void method that begins with "_" that is marked to be searched with a comment like "Indexed: true", "Stored: true", or "DocValues: true" will be indexed in the search engine. 
 * </p>
 * <h2>{@inheritDoc}</h2>
 * <p>By adding a class comment "{@inheritDoc}", the JobTemplate class will inherit the helpful inherited class comments from the super class JobTemplateGen. 
 * </p>
 * <h2>Rows: 100</h2>
 * <p>This class contains a comment <b>"Rows: 100"</b>, which means the JobTemplate API will return a default of 100 records instead of 10 by default. 
 * Each API has built in pagination of the search records to ensure a user can query all the data a page at a time without running the application out of memory. 
 * </p>
 * <h2>Order: 6</h2>
 * <p>This class contains a comment <b>"Order: 6"</b>, which means this class will be sorted by the given number 6 ascending when code that relates to multiple classes at the same time is generated. 
 * </p>
 * <h2>SqlOrder: 6</h2>
 * <p>This class contains a comment <b>"SqlOrder: 6"</b>, which means this class will be sorted by the given number 6 ascending when SQL code to create and drop the tables is generated. 
 * </p>
 * <h2>Model: true</h2>
 * <p>This class contains a comment <b>"Model: true"</b>, which means this class will be stored in the database. 
 * Every protected void method that begins with "_" that contains a "Persist: true" comment will be a persisted field in the database table. 
 * </p>
 * <h2>Page: true</h2>
 * <p>This class contains a comment <b>"Page: true"</b>, which means this class will have webpage code generated for these objects. 
 * Java Vert.x backend API code, Handlebars HTML template frontend code, and JavaScript code will all generated and can be extended. 
 * This creates a new Java class org.computate.dcm.model.eda.jobtemplate.JobTemplatePage. 
 * </p>
 * <h2>SuperPage.enUS: PageLayout</h2>
 * <p>This class contains a comment <b>"SuperPage.enUS: PageLayout"</b>, which identifies the Java super class of the page code by it's class simple name "PageLayout". 
 * This means that the newly created class org.computate.dcm.model.eda.jobtemplate.JobTemplatePage extends org.computate.dcm.page.PageLayout. 
 * </p>
 * <h2>Promise: true</h2>
 * <p>
 *   This class contains a comment <b>"Promise: true"</b>
 *   Sometimes a Java class must be initialized asynchronously when it involves calling a blocking API. 
 *   This means that the JobTemplate Java class has promiseDeep methods which must be initialized asynchronously as a Vert.x Promise  instead of initDeep methods which are a simple non-asynchronous method. 
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
 * <h2>AName.enUS: a job template</h2>
 * <p>This class contains a comment <b>"AName.enUS: a job template"</b>, which identifies the language context to describe a JobTemplate as "a job template". 
 * </p>
 * <p>
 * Delete the class JobTemplate in Solr: 
 * <pre>
 * curl -k 'https://solr.apps-crc.testing/solr/computate/update?commitWithin=1000&amp;overwrite=true&amp;wt=json' -X POST -H 'Content-type: text/xml' -u "admin:$(oc -n solr get secret/solr-solrcloud-security-bootstrap -o jsonpath={.data.admin} | base64 -d)" --data-raw '&lt;delete&gt;&lt;query&gt;classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.jobtemplate.JobTemplate&lt;/query&gt;&lt;/delete&gt;'
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
public abstract class JobTemplateGen<DEV> extends BaseModel {
  protected static final Logger LOG = LoggerFactory.getLogger(JobTemplate.class);

  public static final String Description_enUS = "A job template to be run on a computer in Ansible Automation Platform. ";
  public static final String AName_enUS = "a job template";
  public static final String This_enUS = "this ";
  public static final String ThisName_enUS = "this job template";
  public static final String A_enUS = "a ";
  public static final String TheName_enUS = "the job template";
  public static final String SingularName_enUS = "job template";
  public static final String PluralName_enUS = "job templates";
  public static final String NameActual_enUS = "current job template";
  public static final String AllName_enUS = "all job templates";
  public static final String SearchAllNameBy_enUS = "search job templates by ";
  public static final String SearchAllName_enUS = "search job templates";
  public static final String Title_enUS = "job templates";
  public static final String ThePluralName_enUS = "the job templates";
  public static final String NoNameFound_enUS = "no job template found";
  public static final String ApiUri_enUS = "/en-us/api/job-template";
  public static final String ApiUriSearchPage_enUS = "/en-us/search/job-template";
  public static final String ApiUriEditPage_enUS = "/en-us/edit/job-template/{jobTemplateResource}";
  public static final String OfName_enUS = "of job template";
  public static final String ANameAdjective_enUS = "a job template";
  public static final String NameAdjectiveSingular_enUS = "job template";
  public static final String NameAdjectivePlural_enUS = "job templates";
  public static final String Search_enUS_OpenApiUri = "/en-us/api/job-template";
  public static final String Search_enUS_StringFormatUri = "/en-us/api/job-template";
  public static final String Search_enUS_StringFormatUrl = "%s/en-us/api/job-template";
  public static final String GET_enUS_OpenApiUri = "/en-us/api/job-template/{jobTemplateResource}";
  public static final String GET_enUS_StringFormatUri = "/en-us/api/job-template/%s";
  public static final String GET_enUS_StringFormatUrl = "%s/en-us/api/job-template/%s";
  public static final String PATCH_enUS_OpenApiUri = "/en-us/api/job-template";
  public static final String PATCH_enUS_StringFormatUri = "/en-us/api/job-template";
  public static final String PATCH_enUS_StringFormatUrl = "%s/en-us/api/job-template";
  public static final String POST_enUS_OpenApiUri = "/en-us/api/job-template";
  public static final String POST_enUS_StringFormatUri = "/en-us/api/job-template";
  public static final String POST_enUS_StringFormatUrl = "%s/en-us/api/job-template";
  public static final String DELETE_enUS_OpenApiUri = "/en-us/api/job-template/{jobTemplateResource}";
  public static final String DELETE_enUS_StringFormatUri = "/en-us/api/job-template/%s";
  public static final String DELETE_enUS_StringFormatUrl = "%s/en-us/api/job-template/%s";
  public static final String PUTImport_enUS_OpenApiUri = "/en-us/api/job-template-import";
  public static final String PUTImport_enUS_StringFormatUri = "/en-us/api/job-template-import";
  public static final String PUTImport_enUS_StringFormatUrl = "%s/en-us/api/job-template-import";
  public static final String SearchPage_enUS_OpenApiUri = "/en-us/search/job-template";
  public static final String SearchPage_enUS_StringFormatUri = "/en-us/search/job-template";
  public static final String SearchPage_enUS_StringFormatUrl = "%s/en-us/search/job-template";
  public static final String EditPage_enUS_OpenApiUri = "/en-us/edit/job-template/{jobTemplateResource}";
  public static final String EditPage_enUS_StringFormatUri = "/en-us/edit/job-template/%s";
  public static final String EditPage_enUS_StringFormatUrl = "%s/en-us/edit/job-template/%s";
  public static final String DELETEFilter_enUS_OpenApiUri = "/en-us/api/job-template";
  public static final String DELETEFilter_enUS_StringFormatUri = "/en-us/api/job-template";
  public static final String DELETEFilter_enUS_StringFormatUrl = "%s/en-us/api/job-template";

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
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.jobtemplate.JobTemplate&fq=entiteVar_enUS_indexed_string:tenantResource">Find the entity tenantResource in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _tenantResource(Wrap<String> w);

  public String getTenantResource() {
    return tenantResource;
  }
  public void setTenantResource(String o) {
    this.tenantResource = JobTemplate.staticSetTenantResource(siteRequest_, o);
  }
  public static String staticSetTenantResource(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected JobTemplate tenantResourceInit() {
    Wrap<String> tenantResourceWrap = new Wrap<String>().var("tenantResource");
    if(tenantResource == null) {
      _tenantResource(tenantResourceWrap);
      Optional.ofNullable(tenantResourceWrap.getO()).ifPresent(o -> {
        setTenantResource(o);
      });
    }
    return (JobTemplate)this;
  }

  public static String staticSearchTenantResource(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrTenantResource(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqTenantResource(SiteRequest siteRequest_, String o) {
    return JobTemplate.staticSearchTenantResource(siteRequest_, JobTemplate.staticSetTenantResource(siteRequest_, o)).toString();
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
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.jobtemplate.JobTemplate&fq=entiteVar_enUS_indexed_string:tenantId">Find the entity tenantId in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _tenantId(Wrap<String> w);

  public String getTenantId() {
    return tenantId;
  }
  public void setTenantId(String o) {
    this.tenantId = JobTemplate.staticSetTenantId(siteRequest_, o);
  }
  public static String staticSetTenantId(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected JobTemplate tenantIdInit() {
    Wrap<String> tenantIdWrap = new Wrap<String>().var("tenantId");
    if(tenantId == null) {
      _tenantId(tenantIdWrap);
      Optional.ofNullable(tenantIdWrap.getO()).ifPresent(o -> {
        setTenantId(o);
      });
    }
    return (JobTemplate)this;
  }

  public static String staticSearchTenantId(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrTenantId(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqTenantId(SiteRequest siteRequest_, String o) {
    return JobTemplate.staticSearchTenantId(siteRequest_, JobTemplate.staticSetTenantId(siteRequest_, o)).toString();
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
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.jobtemplate.JobTemplate&fq=entiteVar_enUS_indexed_string:aapOrganizationId">Find the entity aapOrganizationId in Solr</a>
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
    this.aapOrganizationId = JobTemplate.staticSetAapOrganizationId(siteRequest_, o);
  }
  public static Long staticSetAapOrganizationId(SiteRequest siteRequest_, String o) {
    if(NumberUtils.isParsable(o))
      return Long.parseLong(o);
    return null;
  }
  protected JobTemplate aapOrganizationIdInit() {
    Wrap<Long> aapOrganizationIdWrap = new Wrap<Long>().var("aapOrganizationId");
    if(aapOrganizationId == null) {
      _aapOrganizationId(aapOrganizationIdWrap);
      Optional.ofNullable(aapOrganizationIdWrap.getO()).ifPresent(o -> {
        setAapOrganizationId(o);
      });
    }
    return (JobTemplate)this;
  }

  public static Long staticSearchAapOrganizationId(SiteRequest siteRequest_, Long o) {
    return o;
  }

  public static String staticSearchStrAapOrganizationId(SiteRequest siteRequest_, Long o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqAapOrganizationId(SiteRequest siteRequest_, String o) {
    return JobTemplate.staticSearchAapOrganizationId(siteRequest_, JobTemplate.staticSetAapOrganizationId(siteRequest_, o)).toString();
  }

  public Long sqlAapOrganizationId() {
    return aapOrganizationId;
  }

  public static String staticJsonAapOrganizationId(Long aapOrganizationId) {
    return Optional.ofNullable(aapOrganizationId).map(v -> v.toString()).orElse(null);
  }

	///////////////////////
  // inventoryResource //
	///////////////////////


  /**
   *  The entity inventoryResource
   *	 is defined as null before being initialized. 
   */
  @JsonProperty
  @JsonInclude(Include.NON_NULL)
  protected String inventoryResource;

  /**
   * <br> The entity inventoryResource
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.jobtemplate.JobTemplate&fq=entiteVar_enUS_indexed_string:inventoryResource">Find the entity inventoryResource in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _inventoryResource(Wrap<String> w);

  public String getInventoryResource() {
    return inventoryResource;
  }
  public void setInventoryResource(String o) {
    this.inventoryResource = JobTemplate.staticSetInventoryResource(siteRequest_, o);
  }
  public static String staticSetInventoryResource(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected JobTemplate inventoryResourceInit() {
    Wrap<String> inventoryResourceWrap = new Wrap<String>().var("inventoryResource");
    if(inventoryResource == null) {
      _inventoryResource(inventoryResourceWrap);
      Optional.ofNullable(inventoryResourceWrap.getO()).ifPresent(o -> {
        setInventoryResource(o);
      });
    }
    return (JobTemplate)this;
  }

  public static String staticSearchInventoryResource(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrInventoryResource(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqInventoryResource(SiteRequest siteRequest_, String o) {
    return JobTemplate.staticSearchInventoryResource(siteRequest_, JobTemplate.staticSetInventoryResource(siteRequest_, o)).toString();
  }

  public String sqlInventoryResource() {
    return inventoryResource;
  }

  public static String staticJsonInventoryResource(String inventoryResource) {
    return inventoryResource;
  }

	////////////////////
  // aapInventoryId //
	////////////////////


  /**
   *  The entity aapInventoryId
   *	 is defined as null before being initialized. 
   */
  @JsonProperty
  @JsonSerialize(using = ToStringSerializer.class)
  @JsonInclude(Include.NON_NULL)
  protected Long aapInventoryId;

  /**
   * <br> The entity aapInventoryId
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.jobtemplate.JobTemplate&fq=entiteVar_enUS_indexed_string:aapInventoryId">Find the entity aapInventoryId in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _aapInventoryId(Wrap<Long> w);

  public Long getAapInventoryId() {
    return aapInventoryId;
  }

  public void setAapInventoryId(Long aapInventoryId) {
    this.aapInventoryId = aapInventoryId;
  }
  @JsonIgnore
  public void setAapInventoryId(String o) {
    this.aapInventoryId = JobTemplate.staticSetAapInventoryId(siteRequest_, o);
  }
  public static Long staticSetAapInventoryId(SiteRequest siteRequest_, String o) {
    if(NumberUtils.isParsable(o))
      return Long.parseLong(o);
    return null;
  }
  protected JobTemplate aapInventoryIdInit() {
    Wrap<Long> aapInventoryIdWrap = new Wrap<Long>().var("aapInventoryId");
    if(aapInventoryId == null) {
      _aapInventoryId(aapInventoryIdWrap);
      Optional.ofNullable(aapInventoryIdWrap.getO()).ifPresent(o -> {
        setAapInventoryId(o);
      });
    }
    return (JobTemplate)this;
  }

  public static Long staticSearchAapInventoryId(SiteRequest siteRequest_, Long o) {
    return o;
  }

  public static String staticSearchStrAapInventoryId(SiteRequest siteRequest_, Long o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqAapInventoryId(SiteRequest siteRequest_, String o) {
    return JobTemplate.staticSearchAapInventoryId(siteRequest_, JobTemplate.staticSetAapInventoryId(siteRequest_, o)).toString();
  }

  public Long sqlAapInventoryId() {
    return aapInventoryId;
  }

  public static String staticJsonAapInventoryId(Long aapInventoryId) {
    return Optional.ofNullable(aapInventoryId).map(v -> v.toString()).orElse(null);
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
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.jobtemplate.JobTemplate&fq=entiteVar_enUS_indexed_string:ansibleProjectResource">Find the entity ansibleProjectResource in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _ansibleProjectResource(Wrap<String> w);

  public String getAnsibleProjectResource() {
    return ansibleProjectResource;
  }
  public void setAnsibleProjectResource(String o) {
    this.ansibleProjectResource = JobTemplate.staticSetAnsibleProjectResource(siteRequest_, o);
  }
  public static String staticSetAnsibleProjectResource(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected JobTemplate ansibleProjectResourceInit() {
    Wrap<String> ansibleProjectResourceWrap = new Wrap<String>().var("ansibleProjectResource");
    if(ansibleProjectResource == null) {
      _ansibleProjectResource(ansibleProjectResourceWrap);
      Optional.ofNullable(ansibleProjectResourceWrap.getO()).ifPresent(o -> {
        setAnsibleProjectResource(o);
      });
    }
    return (JobTemplate)this;
  }

  public static String staticSearchAnsibleProjectResource(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrAnsibleProjectResource(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqAnsibleProjectResource(SiteRequest siteRequest_, String o) {
    return JobTemplate.staticSearchAnsibleProjectResource(siteRequest_, JobTemplate.staticSetAnsibleProjectResource(siteRequest_, o)).toString();
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
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.jobtemplate.JobTemplate&fq=entiteVar_enUS_indexed_string:aapProjectId">Find the entity aapProjectId in Solr</a>
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
    this.aapProjectId = JobTemplate.staticSetAapProjectId(siteRequest_, o);
  }
  public static Long staticSetAapProjectId(SiteRequest siteRequest_, String o) {
    if(NumberUtils.isParsable(o))
      return Long.parseLong(o);
    return null;
  }
  protected JobTemplate aapProjectIdInit() {
    Wrap<Long> aapProjectIdWrap = new Wrap<Long>().var("aapProjectId");
    if(aapProjectId == null) {
      _aapProjectId(aapProjectIdWrap);
      Optional.ofNullable(aapProjectIdWrap.getO()).ifPresent(o -> {
        setAapProjectId(o);
      });
    }
    return (JobTemplate)this;
  }

  public static Long staticSearchAapProjectId(SiteRequest siteRequest_, Long o) {
    return o;
  }

  public static String staticSearchStrAapProjectId(SiteRequest siteRequest_, Long o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqAapProjectId(SiteRequest siteRequest_, String o) {
    return JobTemplate.staticSearchAapProjectId(siteRequest_, JobTemplate.staticSetAapProjectId(siteRequest_, o)).toString();
  }

  public Long sqlAapProjectId() {
    return aapProjectId;
  }

  public static String staticJsonAapProjectId(Long aapProjectId) {
    return Optional.ofNullable(aapProjectId).map(v -> v.toString()).orElse(null);
  }

	/////////////////////
  // ansiblePlaybook //
	/////////////////////


  /**
   *  The entity ansiblePlaybook
   *	 is defined as null before being initialized. 
   */
  @JsonProperty
  @JsonInclude(Include.NON_NULL)
  protected String ansiblePlaybook;

  /**
   * <br> The entity ansiblePlaybook
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.jobtemplate.JobTemplate&fq=entiteVar_enUS_indexed_string:ansiblePlaybook">Find the entity ansiblePlaybook in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _ansiblePlaybook(Wrap<String> w);

  public String getAnsiblePlaybook() {
    return ansiblePlaybook;
  }
  public void setAnsiblePlaybook(String o) {
    this.ansiblePlaybook = JobTemplate.staticSetAnsiblePlaybook(siteRequest_, o);
  }
  public static String staticSetAnsiblePlaybook(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected JobTemplate ansiblePlaybookInit() {
    Wrap<String> ansiblePlaybookWrap = new Wrap<String>().var("ansiblePlaybook");
    if(ansiblePlaybook == null) {
      _ansiblePlaybook(ansiblePlaybookWrap);
      Optional.ofNullable(ansiblePlaybookWrap.getO()).ifPresent(o -> {
        setAnsiblePlaybook(o);
      });
    }
    return (JobTemplate)this;
  }

  public static String staticSearchAnsiblePlaybook(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrAnsiblePlaybook(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqAnsiblePlaybook(SiteRequest siteRequest_, String o) {
    return JobTemplate.staticSearchAnsiblePlaybook(siteRequest_, JobTemplate.staticSetAnsiblePlaybook(siteRequest_, o)).toString();
  }

  public String sqlAnsiblePlaybook() {
    return ansiblePlaybook;
  }

  public static String staticJsonAnsiblePlaybook(String ansiblePlaybook) {
    return ansiblePlaybook;
  }

	/////////////////////
  // jobTemplateName //
	/////////////////////


  /**
   *  The entity jobTemplateName
   *	 is defined as null before being initialized. 
   */
  @JsonProperty
  @JsonInclude(Include.NON_NULL)
  protected String jobTemplateName;

  /**
   * <br> The entity jobTemplateName
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.jobtemplate.JobTemplate&fq=entiteVar_enUS_indexed_string:jobTemplateName">Find the entity jobTemplateName in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _jobTemplateName(Wrap<String> w);

  public String getJobTemplateName() {
    return jobTemplateName;
  }
  public void setJobTemplateName(String o) {
    this.jobTemplateName = JobTemplate.staticSetJobTemplateName(siteRequest_, o);
  }
  public static String staticSetJobTemplateName(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected JobTemplate jobTemplateNameInit() {
    Wrap<String> jobTemplateNameWrap = new Wrap<String>().var("jobTemplateName");
    if(jobTemplateName == null) {
      _jobTemplateName(jobTemplateNameWrap);
      Optional.ofNullable(jobTemplateNameWrap.getO()).ifPresent(o -> {
        setJobTemplateName(o);
      });
    }
    return (JobTemplate)this;
  }

  public static String staticSearchJobTemplateName(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrJobTemplateName(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqJobTemplateName(SiteRequest siteRequest_, String o) {
    return JobTemplate.staticSearchJobTemplateName(siteRequest_, JobTemplate.staticSetJobTemplateName(siteRequest_, o)).toString();
  }

  public String sqlJobTemplateName() {
    return jobTemplateName;
  }

  public static String staticJsonJobTemplateName(String jobTemplateName) {
    return jobTemplateName;
  }

	///////////////////
  // jobTemplateId //
	///////////////////


  /**
   *  The entity jobTemplateId
   *	 is defined as null before being initialized. 
   */
  @JsonProperty
  @JsonInclude(Include.NON_NULL)
  protected String jobTemplateId;

  /**
   * <br> The entity jobTemplateId
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.jobtemplate.JobTemplate&fq=entiteVar_enUS_indexed_string:jobTemplateId">Find the entity jobTemplateId in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _jobTemplateId(Wrap<String> w);

  public String getJobTemplateId() {
    return jobTemplateId;
  }
  public void setJobTemplateId(String o) {
    this.jobTemplateId = JobTemplate.staticSetJobTemplateId(siteRequest_, o);
  }
  public static String staticSetJobTemplateId(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected JobTemplate jobTemplateIdInit() {
    Wrap<String> jobTemplateIdWrap = new Wrap<String>().var("jobTemplateId");
    if(jobTemplateId == null) {
      _jobTemplateId(jobTemplateIdWrap);
      Optional.ofNullable(jobTemplateIdWrap.getO()).ifPresent(o -> {
        setJobTemplateId(o);
      });
    }
    return (JobTemplate)this;
  }

  public static String staticSearchJobTemplateId(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrJobTemplateId(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqJobTemplateId(SiteRequest siteRequest_, String o) {
    return JobTemplate.staticSearchJobTemplateId(siteRequest_, JobTemplate.staticSetJobTemplateId(siteRequest_, o)).toString();
  }

  public String sqlJobTemplateId() {
    return jobTemplateId;
  }

  public static String staticJsonJobTemplateId(String jobTemplateId) {
    return jobTemplateId;
  }

	/////////////////////////
  // jobTemplateResource //
	/////////////////////////


  /**
   *  The entity jobTemplateResource
   *	 is defined as null before being initialized. 
   */
  @JsonProperty
  @JsonInclude(Include.NON_NULL)
  protected String jobTemplateResource;

  /**
   * <br> The entity jobTemplateResource
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.jobtemplate.JobTemplate&fq=entiteVar_enUS_indexed_string:jobTemplateResource">Find the entity jobTemplateResource in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _jobTemplateResource(Wrap<String> w);

  public String getJobTemplateResource() {
    return jobTemplateResource;
  }
  public void setJobTemplateResource(String o) {
    this.jobTemplateResource = JobTemplate.staticSetJobTemplateResource(siteRequest_, o);
  }
  public static String staticSetJobTemplateResource(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected JobTemplate jobTemplateResourceInit() {
    Wrap<String> jobTemplateResourceWrap = new Wrap<String>().var("jobTemplateResource");
    if(jobTemplateResource == null) {
      _jobTemplateResource(jobTemplateResourceWrap);
      Optional.ofNullable(jobTemplateResourceWrap.getO()).ifPresent(o -> {
        setJobTemplateResource(o);
      });
    }
    return (JobTemplate)this;
  }

  public static String staticSearchJobTemplateResource(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrJobTemplateResource(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqJobTemplateResource(SiteRequest siteRequest_, String o) {
    return JobTemplate.staticSearchJobTemplateResource(siteRequest_, JobTemplate.staticSetJobTemplateResource(siteRequest_, o)).toString();
  }

  public String sqlJobTemplateResource() {
    return jobTemplateResource;
  }

  public static String staticJsonJobTemplateResource(String jobTemplateResource) {
    return jobTemplateResource;
  }

	////////////////////////////
  // jobTemplateDescription //
	////////////////////////////


  /**
   *  The entity jobTemplateDescription
   *	 is defined as null before being initialized. 
   */
  @JsonProperty
  @JsonInclude(Include.NON_NULL)
  protected String jobTemplateDescription;

  /**
   * <br> The entity jobTemplateDescription
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.jobtemplate.JobTemplate&fq=entiteVar_enUS_indexed_string:jobTemplateDescription">Find the entity jobTemplateDescription in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _jobTemplateDescription(Wrap<String> w);

  public String getJobTemplateDescription() {
    return jobTemplateDescription;
  }
  public void setJobTemplateDescription(String o) {
    this.jobTemplateDescription = JobTemplate.staticSetJobTemplateDescription(siteRequest_, o);
  }
  public static String staticSetJobTemplateDescription(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected JobTemplate jobTemplateDescriptionInit() {
    Wrap<String> jobTemplateDescriptionWrap = new Wrap<String>().var("jobTemplateDescription");
    if(jobTemplateDescription == null) {
      _jobTemplateDescription(jobTemplateDescriptionWrap);
      Optional.ofNullable(jobTemplateDescriptionWrap.getO()).ifPresent(o -> {
        setJobTemplateDescription(o);
      });
    }
    return (JobTemplate)this;
  }

  public static String staticSearchJobTemplateDescription(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrJobTemplateDescription(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqJobTemplateDescription(SiteRequest siteRequest_, String o) {
    return JobTemplate.staticSearchJobTemplateDescription(siteRequest_, JobTemplate.staticSetJobTemplateDescription(siteRequest_, o)).toString();
  }

  public String sqlJobTemplateDescription() {
    return jobTemplateDescription;
  }

  public static String staticJsonJobTemplateDescription(String jobTemplateDescription) {
    return jobTemplateDescription;
  }

	/////////////
  // jobType //
	/////////////


  /**
   *  The entity jobType
   *	 is defined as null before being initialized. 
   */
  @JsonProperty
  @JsonInclude(Include.NON_NULL)
  protected String jobType;

  /**
   * <br> The entity jobType
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.jobtemplate.JobTemplate&fq=entiteVar_enUS_indexed_string:jobType">Find the entity jobType in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _jobType(Wrap<String> w);

  public String getJobType() {
    return jobType;
  }
  public void setJobType(String o) {
    this.jobType = JobTemplate.staticSetJobType(siteRequest_, o);
  }
  public static String staticSetJobType(SiteRequest siteRequest_, String o) {
    return o;
  }
  protected JobTemplate jobTypeInit() {
    Wrap<String> jobTypeWrap = new Wrap<String>().var("jobType");
    if(jobType == null) {
      _jobType(jobTypeWrap);
      Optional.ofNullable(jobTypeWrap.getO()).ifPresent(o -> {
        setJobType(o);
      });
    }
    return (JobTemplate)this;
  }

  public static String staticSearchJobType(SiteRequest siteRequest_, String o) {
    return o;
  }

  public static String staticSearchStrJobType(SiteRequest siteRequest_, String o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqJobType(SiteRequest siteRequest_, String o) {
    return JobTemplate.staticSearchJobType(siteRequest_, JobTemplate.staticSetJobType(siteRequest_, o)).toString();
  }

  public String sqlJobType() {
    return jobType;
  }

  public static String staticJsonJobType(String jobType) {
    return jobType;
  }

	///////////////////
  // aapTemplateId //
	///////////////////


  /**
   *  The entity aapTemplateId
   *	 is defined as null before being initialized. 
   */
  @JsonProperty
  @JsonSerialize(using = ToStringSerializer.class)
  @JsonInclude(Include.NON_NULL)
  protected Long aapTemplateId;

  /**
   * <br> The entity aapTemplateId
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.jobtemplate.JobTemplate&fq=entiteVar_enUS_indexed_string:aapTemplateId">Find the entity aapTemplateId in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _aapTemplateId(Wrap<Long> w);

  public Long getAapTemplateId() {
    return aapTemplateId;
  }

  public void setAapTemplateId(Long aapTemplateId) {
    this.aapTemplateId = aapTemplateId;
  }
  @JsonIgnore
  public void setAapTemplateId(String o) {
    this.aapTemplateId = JobTemplate.staticSetAapTemplateId(siteRequest_, o);
  }
  public static Long staticSetAapTemplateId(SiteRequest siteRequest_, String o) {
    if(NumberUtils.isParsable(o))
      return Long.parseLong(o);
    return null;
  }
  protected JobTemplate aapTemplateIdInit() {
    Wrap<Long> aapTemplateIdWrap = new Wrap<Long>().var("aapTemplateId");
    if(aapTemplateId == null) {
      _aapTemplateId(aapTemplateIdWrap);
      Optional.ofNullable(aapTemplateIdWrap.getO()).ifPresent(o -> {
        setAapTemplateId(o);
      });
    }
    return (JobTemplate)this;
  }

  public static Long staticSearchAapTemplateId(SiteRequest siteRequest_, Long o) {
    return o;
  }

  public static String staticSearchStrAapTemplateId(SiteRequest siteRequest_, Long o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqAapTemplateId(SiteRequest siteRequest_, String o) {
    return JobTemplate.staticSearchAapTemplateId(siteRequest_, JobTemplate.staticSetAapTemplateId(siteRequest_, o)).toString();
  }

  public Long sqlAapTemplateId() {
    return aapTemplateId;
  }

  public static String staticJsonAapTemplateId(Long aapTemplateId) {
    return Optional.ofNullable(aapTemplateId).map(v -> v.toString()).orElse(null);
  }

  //////////////
  // initDeep //
  //////////////

  public Future<JobTemplateGen<DEV>> promiseDeepJobTemplate(SiteRequest siteRequest_) {
    setSiteRequest_(siteRequest_);
    return promiseDeepJobTemplate();
  }

  public Future<JobTemplateGen<DEV>> promiseDeepJobTemplate() {
    Promise<JobTemplateGen<DEV>> promise = Promise.promise();
    Promise<Void> promise2 = Promise.promise();
    promiseJobTemplate(promise2);
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

  public Future<Void> promiseJobTemplate(Promise<Void> promise) {
    Future.future(a -> a.complete()).compose(a -> {
      Promise<Void> promise2 = Promise.promise();
      try {
        tenantResourceInit();
        tenantIdInit();
        aapOrganizationIdInit();
        inventoryResourceInit();
        aapInventoryIdInit();
        ansibleProjectResourceInit();
        aapProjectIdInit();
        ansiblePlaybookInit();
        jobTemplateNameInit();
        jobTemplateIdInit();
        jobTemplateResourceInit();
        jobTemplateDescriptionInit();
        jobTypeInit();
        aapTemplateIdInit();
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

  @Override public Future<? extends JobTemplateGen<DEV>> promiseDeepForClass(SiteRequest siteRequest_) {
    return promiseDeepJobTemplate(siteRequest_);
  }

  /////////////////
  // siteRequest //
  /////////////////

  public void siteRequestJobTemplate(SiteRequest siteRequest_) {
      super.siteRequestBaseModel(siteRequest_);
  }

  public void siteRequestForClass(SiteRequest siteRequest_) {
    siteRequestJobTemplate(siteRequest_);
  }

  /////////////
  // obtain //
  /////////////

  @Override public Object obtainForClass(String var) {
    String[] vars = StringUtils.split(var, ".");
    Object o = null;
    for(String v : vars) {
      if(o == null)
        o = obtainJobTemplate(v);
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
  public Object obtainJobTemplate(String var) {
    JobTemplate oJobTemplate = (JobTemplate)this;
    switch(var) {
      case "tenantResource":
        return oJobTemplate.tenantResource;
      case "tenantId":
        return oJobTemplate.tenantId;
      case "aapOrganizationId":
        return oJobTemplate.aapOrganizationId;
      case "inventoryResource":
        return oJobTemplate.inventoryResource;
      case "aapInventoryId":
        return oJobTemplate.aapInventoryId;
      case "ansibleProjectResource":
        return oJobTemplate.ansibleProjectResource;
      case "aapProjectId":
        return oJobTemplate.aapProjectId;
      case "ansiblePlaybook":
        return oJobTemplate.ansiblePlaybook;
      case "jobTemplateName":
        return oJobTemplate.jobTemplateName;
      case "jobTemplateId":
        return oJobTemplate.jobTemplateId;
      case "jobTemplateResource":
        return oJobTemplate.jobTemplateResource;
      case "jobTemplateDescription":
        return oJobTemplate.jobTemplateDescription;
      case "jobType":
        return oJobTemplate.jobType;
      case "aapTemplateId":
        return oJobTemplate.aapTemplateId;
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
        o = relateJobTemplate(v, val);
      else if(o instanceof BaseModel) {
        BaseModel baseModel = (BaseModel)o;
        o = baseModel.relateForClass(v, val);
      }
    }
    return o != null;
  }
  public Object relateJobTemplate(String var, Object val) {
    JobTemplate oJobTemplate = (JobTemplate)this;
    switch(var) {
      case "tenantResource":
        if(oJobTemplate.getTenantResource() == null)
          oJobTemplate.setTenantResource(Optional.ofNullable(val).map(v -> v.toString()).orElse(null));
        if(!saves.contains("tenantResource"))
          saves.add("tenantResource");
        return val;
      case "inventoryResource":
        if(oJobTemplate.getInventoryResource() == null)
          oJobTemplate.setInventoryResource(Optional.ofNullable(val).map(v -> v.toString()).orElse(null));
        if(!saves.contains("inventoryResource"))
          saves.add("inventoryResource");
        return val;
      case "ansibleProjectResource":
        if(oJobTemplate.getAnsibleProjectResource() == null)
          oJobTemplate.setAnsibleProjectResource(Optional.ofNullable(val).map(v -> v.toString()).orElse(null));
        if(!saves.contains("ansibleProjectResource"))
          saves.add("ansibleProjectResource");
        return val;
      default:
        return super.relateBaseModel(var, val);
    }
  }

  ///////////////
  // staticSet //
  ///////////////

  public static Object staticSetForClass(String entityVar, SiteRequest siteRequest_, String v, JobTemplate o) {
    return staticSetJobTemplate(entityVar,  siteRequest_, v, o);
  }
  public static Object staticSetJobTemplate(String entityVar, SiteRequest siteRequest_, String v, JobTemplate o) {
    switch(entityVar) {
    case "tenantResource":
      return JobTemplate.staticSetTenantResource(siteRequest_, v);
    case "tenantId":
      return JobTemplate.staticSetTenantId(siteRequest_, v);
    case "aapOrganizationId":
      return JobTemplate.staticSetAapOrganizationId(siteRequest_, v);
    case "inventoryResource":
      return JobTemplate.staticSetInventoryResource(siteRequest_, v);
    case "aapInventoryId":
      return JobTemplate.staticSetAapInventoryId(siteRequest_, v);
    case "ansibleProjectResource":
      return JobTemplate.staticSetAnsibleProjectResource(siteRequest_, v);
    case "aapProjectId":
      return JobTemplate.staticSetAapProjectId(siteRequest_, v);
    case "ansiblePlaybook":
      return JobTemplate.staticSetAnsiblePlaybook(siteRequest_, v);
    case "jobTemplateName":
      return JobTemplate.staticSetJobTemplateName(siteRequest_, v);
    case "jobTemplateId":
      return JobTemplate.staticSetJobTemplateId(siteRequest_, v);
    case "jobTemplateResource":
      return JobTemplate.staticSetJobTemplateResource(siteRequest_, v);
    case "jobTemplateDescription":
      return JobTemplate.staticSetJobTemplateDescription(siteRequest_, v);
    case "jobType":
      return JobTemplate.staticSetJobType(siteRequest_, v);
    case "aapTemplateId":
      return JobTemplate.staticSetAapTemplateId(siteRequest_, v);
      default:
        return BaseModel.staticSetBaseModel(entityVar,  siteRequest_, v, o);
    }
  }

  //////////////////
  // staticSearch //
  //////////////////

  public static Future<JobTemplate> fqJobTemplate(SiteRequest siteRequest, String var, Object val) {
    Promise<JobTemplate> promise = Promise.promise();
    try {
      if(val == null) {
        promise.complete();
      } else {
        SearchList<JobTemplate> searchList = new SearchList<JobTemplate>();
        searchList.setStore(true);
        searchList.q("*:*");
        searchList.setC(JobTemplate.class);
        searchList.fq(String.format("%s:", JobTemplate.varIndexedJobTemplate(var)) + SearchTool.escapeQueryChars(val.toString()));
        searchList.promiseDeepForClass(siteRequest).onSuccess(a -> {
          try {
            promise.complete(searchList.getList().stream().findFirst().orElse(null));
          } catch(Throwable ex) {
            LOG.error("Error while querying the job template", ex);
            promise.fail(ex);
          }
        }).onFailure(ex -> {
          LOG.error("Error while querying the job template", ex);
          promise.fail(ex);
        });
      }
    } catch(Throwable ex) {
      LOG.error("Error while querying the job template", ex);
      promise.fail(ex);
    }
    return promise.future();
  }

  public static Object staticSearchForClass(String entityVar, SiteRequest siteRequest_, Object o) {
    return staticSearchJobTemplate(entityVar,  siteRequest_, o);
  }
  public static Object staticSearchJobTemplate(String entityVar, SiteRequest siteRequest_, Object o) {
    switch(entityVar) {
    case "tenantResource":
      return JobTemplate.staticSearchTenantResource(siteRequest_, (String)o);
    case "tenantId":
      return JobTemplate.staticSearchTenantId(siteRequest_, (String)o);
    case "aapOrganizationId":
      return JobTemplate.staticSearchAapOrganizationId(siteRequest_, (Long)o);
    case "inventoryResource":
      return JobTemplate.staticSearchInventoryResource(siteRequest_, (String)o);
    case "aapInventoryId":
      return JobTemplate.staticSearchAapInventoryId(siteRequest_, (Long)o);
    case "ansibleProjectResource":
      return JobTemplate.staticSearchAnsibleProjectResource(siteRequest_, (String)o);
    case "aapProjectId":
      return JobTemplate.staticSearchAapProjectId(siteRequest_, (Long)o);
    case "ansiblePlaybook":
      return JobTemplate.staticSearchAnsiblePlaybook(siteRequest_, (String)o);
    case "jobTemplateName":
      return JobTemplate.staticSearchJobTemplateName(siteRequest_, (String)o);
    case "jobTemplateId":
      return JobTemplate.staticSearchJobTemplateId(siteRequest_, (String)o);
    case "jobTemplateResource":
      return JobTemplate.staticSearchJobTemplateResource(siteRequest_, (String)o);
    case "jobTemplateDescription":
      return JobTemplate.staticSearchJobTemplateDescription(siteRequest_, (String)o);
    case "jobType":
      return JobTemplate.staticSearchJobType(siteRequest_, (String)o);
    case "aapTemplateId":
      return JobTemplate.staticSearchAapTemplateId(siteRequest_, (Long)o);
      default:
        return BaseModel.staticSearchBaseModel(entityVar,  siteRequest_, o);
    }
  }

  ///////////////////
  // staticSearchStr //
  ///////////////////

  public static String staticSearchStrForClass(String entityVar, SiteRequest siteRequest_, Object o) {
    return staticSearchStrJobTemplate(entityVar,  siteRequest_, o);
  }
  public static String staticSearchStrJobTemplate(String entityVar, SiteRequest siteRequest_, Object o) {
    switch(entityVar) {
    case "tenantResource":
      return JobTemplate.staticSearchStrTenantResource(siteRequest_, (String)o);
    case "tenantId":
      return JobTemplate.staticSearchStrTenantId(siteRequest_, (String)o);
    case "aapOrganizationId":
      return JobTemplate.staticSearchStrAapOrganizationId(siteRequest_, (Long)o);
    case "inventoryResource":
      return JobTemplate.staticSearchStrInventoryResource(siteRequest_, (String)o);
    case "aapInventoryId":
      return JobTemplate.staticSearchStrAapInventoryId(siteRequest_, (Long)o);
    case "ansibleProjectResource":
      return JobTemplate.staticSearchStrAnsibleProjectResource(siteRequest_, (String)o);
    case "aapProjectId":
      return JobTemplate.staticSearchStrAapProjectId(siteRequest_, (Long)o);
    case "ansiblePlaybook":
      return JobTemplate.staticSearchStrAnsiblePlaybook(siteRequest_, (String)o);
    case "jobTemplateName":
      return JobTemplate.staticSearchStrJobTemplateName(siteRequest_, (String)o);
    case "jobTemplateId":
      return JobTemplate.staticSearchStrJobTemplateId(siteRequest_, (String)o);
    case "jobTemplateResource":
      return JobTemplate.staticSearchStrJobTemplateResource(siteRequest_, (String)o);
    case "jobTemplateDescription":
      return JobTemplate.staticSearchStrJobTemplateDescription(siteRequest_, (String)o);
    case "jobType":
      return JobTemplate.staticSearchStrJobType(siteRequest_, (String)o);
    case "aapTemplateId":
      return JobTemplate.staticSearchStrAapTemplateId(siteRequest_, (Long)o);
      default:
        return BaseModel.staticSearchStrBaseModel(entityVar,  siteRequest_, o);
    }
  }

  //////////////////
  // staticSearchFq //
  //////////////////

  public static String staticSearchFqForClass(String entityVar, SiteRequest siteRequest_, String o) {
    return staticSearchFqJobTemplate(entityVar,  siteRequest_, o);
  }
  public static String staticSearchFqJobTemplate(String entityVar, SiteRequest siteRequest_, String o) {
    switch(entityVar) {
    case "tenantResource":
      return JobTemplate.staticSearchFqTenantResource(siteRequest_, o);
    case "tenantId":
      return JobTemplate.staticSearchFqTenantId(siteRequest_, o);
    case "aapOrganizationId":
      return JobTemplate.staticSearchFqAapOrganizationId(siteRequest_, o);
    case "inventoryResource":
      return JobTemplate.staticSearchFqInventoryResource(siteRequest_, o);
    case "aapInventoryId":
      return JobTemplate.staticSearchFqAapInventoryId(siteRequest_, o);
    case "ansibleProjectResource":
      return JobTemplate.staticSearchFqAnsibleProjectResource(siteRequest_, o);
    case "aapProjectId":
      return JobTemplate.staticSearchFqAapProjectId(siteRequest_, o);
    case "ansiblePlaybook":
      return JobTemplate.staticSearchFqAnsiblePlaybook(siteRequest_, o);
    case "jobTemplateName":
      return JobTemplate.staticSearchFqJobTemplateName(siteRequest_, o);
    case "jobTemplateId":
      return JobTemplate.staticSearchFqJobTemplateId(siteRequest_, o);
    case "jobTemplateResource":
      return JobTemplate.staticSearchFqJobTemplateResource(siteRequest_, o);
    case "jobTemplateDescription":
      return JobTemplate.staticSearchFqJobTemplateDescription(siteRequest_, o);
    case "jobType":
      return JobTemplate.staticSearchFqJobType(siteRequest_, o);
    case "aapTemplateId":
      return JobTemplate.staticSearchFqAapTemplateId(siteRequest_, o);
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
          o = persistJobTemplate(v, val);
        else if(o instanceof BaseModel) {
          BaseModel oBaseModel = (BaseModel)o;
          o = oBaseModel.persistForClass(v, val);
        }
      }
    }
    return o != null;
  }
  public Object persistJobTemplate(String var, Object val) {
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
      } else if("inventoryresource".equals(varLower)) {
        if(val instanceof String) {
          setInventoryResource((String)val);
        }
        saves.add("inventoryResource");
        return val;
      } else if("aapinventoryid".equals(varLower)) {
        if(val instanceof Long) {
          setAapInventoryId((Long)val);
        } else {
          setAapInventoryId(val == null ? null : val.toString());
        }
        saves.add("aapInventoryId");
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
      } else if("ansibleplaybook".equals(varLower)) {
        if(val instanceof String) {
          setAnsiblePlaybook((String)val);
        }
        saves.add("ansiblePlaybook");
        return val;
      } else if("jobtemplatename".equals(varLower)) {
        if(val instanceof String) {
          setJobTemplateName((String)val);
        }
        saves.add("jobTemplateName");
        return val;
      } else if("jobtemplateid".equals(varLower)) {
        if(val instanceof String) {
          setJobTemplateId((String)val);
        }
        saves.add("jobTemplateId");
        return val;
      } else if("jobtemplateresource".equals(varLower)) {
        if(val instanceof String) {
          setJobTemplateResource((String)val);
        }
        saves.add("jobTemplateResource");
        return val;
      } else if("jobtemplatedescription".equals(varLower)) {
        if(val instanceof String) {
          setJobTemplateDescription((String)val);
        }
        saves.add("jobTemplateDescription");
        return val;
      } else if("jobtype".equals(varLower)) {
        if(val instanceof String) {
          setJobType((String)val);
        }
        saves.add("jobType");
        return val;
      } else if("aaptemplateid".equals(varLower)) {
        if(val instanceof Long) {
          setAapTemplateId((Long)val);
        } else {
          setAapTemplateId(val == null ? null : val.toString());
        }
        saves.add("aapTemplateId");
        return val;
    } else {
      return super.persistBaseModel(var, val);
    }
  }

  /////////////
  // populate //
  /////////////

  @Override public void populateForClass(SolrResponse.Doc doc) {
    populateJobTemplate(doc);
  }
  public void populateJobTemplate(SolrResponse.Doc doc) {
    JobTemplate oJobTemplate = (JobTemplate)this;
    saves = Optional.ofNullable((ArrayList<String>)doc.get("saves_docvalues_strings")).orElse(new ArrayList<String>());
    if(saves != null) {

      String tenantResource = (String)doc.get("tenantResource_docvalues_string");
      if(tenantResource != null)
        oJobTemplate.setTenantResource(tenantResource);

      if(saves.contains("tenantId")) {
        String tenantId = (String)doc.get("tenantId_docvalues_string");
        if(tenantId != null)
          oJobTemplate.setTenantId(tenantId);
      }

      if(saves.contains("aapOrganizationId")) {
        Long aapOrganizationId = (Long)doc.get("aapOrganizationId_docvalues_long");
        if(aapOrganizationId != null)
          oJobTemplate.setAapOrganizationId(aapOrganizationId);
      }

      String inventoryResource = (String)doc.get("inventoryResource_docvalues_string");
      if(inventoryResource != null)
        oJobTemplate.setInventoryResource(inventoryResource);

      if(saves.contains("aapInventoryId")) {
        Long aapInventoryId = (Long)doc.get("aapInventoryId_docvalues_long");
        if(aapInventoryId != null)
          oJobTemplate.setAapInventoryId(aapInventoryId);
      }

      String ansibleProjectResource = (String)doc.get("ansibleProjectResource_docvalues_string");
      if(ansibleProjectResource != null)
        oJobTemplate.setAnsibleProjectResource(ansibleProjectResource);

      if(saves.contains("aapProjectId")) {
        Long aapProjectId = (Long)doc.get("aapProjectId_docvalues_long");
        if(aapProjectId != null)
          oJobTemplate.setAapProjectId(aapProjectId);
      }

      if(saves.contains("ansiblePlaybook")) {
        String ansiblePlaybook = (String)doc.get("ansiblePlaybook_docvalues_string");
        if(ansiblePlaybook != null)
          oJobTemplate.setAnsiblePlaybook(ansiblePlaybook);
      }

      if(saves.contains("jobTemplateName")) {
        String jobTemplateName = (String)doc.get("jobTemplateName_docvalues_string");
        if(jobTemplateName != null)
          oJobTemplate.setJobTemplateName(jobTemplateName);
      }

      if(saves.contains("jobTemplateId")) {
        String jobTemplateId = (String)doc.get("jobTemplateId_docvalues_string");
        if(jobTemplateId != null)
          oJobTemplate.setJobTemplateId(jobTemplateId);
      }

      if(saves.contains("jobTemplateResource")) {
        String jobTemplateResource = (String)doc.get("jobTemplateResource_docvalues_string");
        if(jobTemplateResource != null)
          oJobTemplate.setJobTemplateResource(jobTemplateResource);
      }

      if(saves.contains("jobTemplateDescription")) {
        String jobTemplateDescription = (String)doc.get("jobTemplateDescription_docvalues_string");
        if(jobTemplateDescription != null)
          oJobTemplate.setJobTemplateDescription(jobTemplateDescription);
      }

      if(saves.contains("jobType")) {
        String jobType = (String)doc.get("jobType_docvalues_string");
        if(jobType != null)
          oJobTemplate.setJobType(jobType);
      }

      if(saves.contains("aapTemplateId")) {
        Long aapTemplateId = (Long)doc.get("aapTemplateId_docvalues_long");
        if(aapTemplateId != null)
          oJobTemplate.setAapTemplateId(aapTemplateId);
      }
    }

    super.populateBaseModel(doc);
  }

  public void indexJobTemplate(JsonObject doc) {
    if(tenantResource != null) {
      doc.put("tenantResource_docvalues_string", tenantResource);
    }
    if(tenantId != null) {
      doc.put("tenantId_docvalues_string", tenantId);
    }
    if(aapOrganizationId != null) {
      doc.put("aapOrganizationId_docvalues_long", aapOrganizationId);
    }
    if(inventoryResource != null) {
      doc.put("inventoryResource_docvalues_string", inventoryResource);
    }
    if(aapInventoryId != null) {
      doc.put("aapInventoryId_docvalues_long", aapInventoryId);
    }
    if(ansibleProjectResource != null) {
      doc.put("ansibleProjectResource_docvalues_string", ansibleProjectResource);
    }
    if(aapProjectId != null) {
      doc.put("aapProjectId_docvalues_long", aapProjectId);
    }
    if(ansiblePlaybook != null) {
      doc.put("ansiblePlaybook_docvalues_string", ansiblePlaybook);
    }
    if(jobTemplateName != null) {
      doc.put("jobTemplateName_docvalues_string", jobTemplateName);
    }
    if(jobTemplateId != null) {
      doc.put("jobTemplateId_docvalues_string", jobTemplateId);
    }
    if(jobTemplateResource != null) {
      doc.put("jobTemplateResource_docvalues_string", jobTemplateResource);
    }
    if(jobTemplateDescription != null) {
      doc.put("jobTemplateDescription_docvalues_string", jobTemplateDescription);
    }
    if(jobType != null) {
      doc.put("jobType_docvalues_string", jobType);
    }
    if(aapTemplateId != null) {
      doc.put("aapTemplateId_docvalues_long", aapTemplateId);
    }
    super.indexBaseModel(doc);

	}

  public static String varStoredJobTemplate(String entityVar) {
    switch(entityVar) {
      case "tenantResource":
        return "tenantResource_docvalues_string";
      case "tenantId":
        return "tenantId_docvalues_string";
      case "aapOrganizationId":
        return "aapOrganizationId_docvalues_long";
      case "inventoryResource":
        return "inventoryResource_docvalues_string";
      case "aapInventoryId":
        return "aapInventoryId_docvalues_long";
      case "ansibleProjectResource":
        return "ansibleProjectResource_docvalues_string";
      case "aapProjectId":
        return "aapProjectId_docvalues_long";
      case "ansiblePlaybook":
        return "ansiblePlaybook_docvalues_string";
      case "jobTemplateName":
        return "jobTemplateName_docvalues_string";
      case "jobTemplateId":
        return "jobTemplateId_docvalues_string";
      case "jobTemplateResource":
        return "jobTemplateResource_docvalues_string";
      case "jobTemplateDescription":
        return "jobTemplateDescription_docvalues_string";
      case "jobType":
        return "jobType_docvalues_string";
      case "aapTemplateId":
        return "aapTemplateId_docvalues_long";
      default:
        return BaseModel.varStoredBaseModel(entityVar);
    }
  }

  public static String varIndexedJobTemplate(String entityVar) {
    switch(entityVar) {
      case "tenantResource":
        return "tenantResource_docvalues_string";
      case "tenantId":
        return "tenantId_docvalues_string";
      case "aapOrganizationId":
        return "aapOrganizationId_docvalues_long";
      case "inventoryResource":
        return "inventoryResource_docvalues_string";
      case "aapInventoryId":
        return "aapInventoryId_docvalues_long";
      case "ansibleProjectResource":
        return "ansibleProjectResource_docvalues_string";
      case "aapProjectId":
        return "aapProjectId_docvalues_long";
      case "ansiblePlaybook":
        return "ansiblePlaybook_docvalues_string";
      case "jobTemplateName":
        return "jobTemplateName_docvalues_string";
      case "jobTemplateId":
        return "jobTemplateId_docvalues_string";
      case "jobTemplateResource":
        return "jobTemplateResource_docvalues_string";
      case "jobTemplateDescription":
        return "jobTemplateDescription_docvalues_string";
      case "jobType":
        return "jobType_docvalues_string";
      case "aapTemplateId":
        return "aapTemplateId_docvalues_long";
      default:
        return BaseModel.varIndexedBaseModel(entityVar);
    }
  }

  public static String searchVarJobTemplate(String searchVar) {
    switch(searchVar) {
      case "tenantResource_docvalues_string":
        return "tenantResource";
      case "tenantId_docvalues_string":
        return "tenantId";
      case "aapOrganizationId_docvalues_long":
        return "aapOrganizationId";
      case "inventoryResource_docvalues_string":
        return "inventoryResource";
      case "aapInventoryId_docvalues_long":
        return "aapInventoryId";
      case "ansibleProjectResource_docvalues_string":
        return "ansibleProjectResource";
      case "aapProjectId_docvalues_long":
        return "aapProjectId";
      case "ansiblePlaybook_docvalues_string":
        return "ansiblePlaybook";
      case "jobTemplateName_docvalues_string":
        return "jobTemplateName";
      case "jobTemplateId_docvalues_string":
        return "jobTemplateId";
      case "jobTemplateResource_docvalues_string":
        return "jobTemplateResource";
      case "jobTemplateDescription_docvalues_string":
        return "jobTemplateDescription";
      case "jobType_docvalues_string":
        return "jobType";
      case "aapTemplateId_docvalues_long":
        return "aapTemplateId";
      default:
        return BaseModel.searchVarBaseModel(searchVar);
    }
  }

  public static String varSearchJobTemplate(String entityVar) {
    switch(entityVar) {
      default:
        return BaseModel.varSearchBaseModel(entityVar);
    }
  }

  public static String varSuggestedJobTemplate(String entityVar) {
    switch(entityVar) {
      default:
        return BaseModel.varSuggestedBaseModel(entityVar);
    }
  }

  /////////////
  // store //
  /////////////

  @Override public void storeForClass(SolrResponse.Doc doc) {
    storeJobTemplate(doc);
  }
  public void storeJobTemplate(SolrResponse.Doc doc) {
    JobTemplate oJobTemplate = (JobTemplate)this;
    SiteRequest siteRequest = oJobTemplate.getSiteRequest_();

    oJobTemplate.setTenantResource(Optional.ofNullable(doc.get("tenantResource_docvalues_string")).map(v -> v.toString()).orElse(null));
    oJobTemplate.setTenantId(Optional.ofNullable(doc.get("tenantId_docvalues_string")).map(v -> v.toString()).orElse(null));
    oJobTemplate.setAapOrganizationId(Optional.ofNullable(doc.get("aapOrganizationId_docvalues_long")).map(v -> v.toString()).orElse(null));
    oJobTemplate.setInventoryResource(Optional.ofNullable(doc.get("inventoryResource_docvalues_string")).map(v -> v.toString()).orElse(null));
    oJobTemplate.setAapInventoryId(Optional.ofNullable(doc.get("aapInventoryId_docvalues_long")).map(v -> v.toString()).orElse(null));
    oJobTemplate.setAnsibleProjectResource(Optional.ofNullable(doc.get("ansibleProjectResource_docvalues_string")).map(v -> v.toString()).orElse(null));
    oJobTemplate.setAapProjectId(Optional.ofNullable(doc.get("aapProjectId_docvalues_long")).map(v -> v.toString()).orElse(null));
    oJobTemplate.setAnsiblePlaybook(Optional.ofNullable(doc.get("ansiblePlaybook_docvalues_string")).map(v -> v.toString()).orElse(null));
    oJobTemplate.setJobTemplateName(Optional.ofNullable(doc.get("jobTemplateName_docvalues_string")).map(v -> v.toString()).orElse(null));
    oJobTemplate.setJobTemplateId(Optional.ofNullable(doc.get("jobTemplateId_docvalues_string")).map(v -> v.toString()).orElse(null));
    oJobTemplate.setJobTemplateResource(Optional.ofNullable(doc.get("jobTemplateResource_docvalues_string")).map(v -> v.toString()).orElse(null));
    oJobTemplate.setJobTemplateDescription(Optional.ofNullable(doc.get("jobTemplateDescription_docvalues_string")).map(v -> v.toString()).orElse(null));
    oJobTemplate.setJobType(Optional.ofNullable(doc.get("jobType_docvalues_string")).map(v -> v.toString()).orElse(null));
    oJobTemplate.setAapTemplateId(Optional.ofNullable(doc.get("aapTemplateId_docvalues_long")).map(v -> v.toString()).orElse(null));

    super.storeBaseModel(doc);
  }

  //////////////////
  // apiRequest //
  //////////////////

  public void apiRequestJobTemplate() {
    ApiRequest apiRequest = Optional.ofNullable(siteRequest_).map(r -> r.getApiRequest_()).orElse(null);
    Object o = Optional.ofNullable(apiRequest).map(ApiRequest::getOriginal).orElse(null);
    if(o != null && o instanceof JobTemplate) {
      JobTemplate original = (JobTemplate)o;
      if(!Objects.equals(tenantResource, original.getTenantResource()))
        apiRequest.addVars("tenantResource");
      if(!Objects.equals(tenantId, original.getTenantId()))
        apiRequest.addVars("tenantId");
      if(!Objects.equals(aapOrganizationId, original.getAapOrganizationId()))
        apiRequest.addVars("aapOrganizationId");
      if(!Objects.equals(inventoryResource, original.getInventoryResource()))
        apiRequest.addVars("inventoryResource");
      if(!Objects.equals(aapInventoryId, original.getAapInventoryId()))
        apiRequest.addVars("aapInventoryId");
      if(!Objects.equals(ansibleProjectResource, original.getAnsibleProjectResource()))
        apiRequest.addVars("ansibleProjectResource");
      if(!Objects.equals(aapProjectId, original.getAapProjectId()))
        apiRequest.addVars("aapProjectId");
      if(!Objects.equals(ansiblePlaybook, original.getAnsiblePlaybook()))
        apiRequest.addVars("ansiblePlaybook");
      if(!Objects.equals(jobTemplateName, original.getJobTemplateName()))
        apiRequest.addVars("jobTemplateName");
      if(!Objects.equals(jobTemplateId, original.getJobTemplateId()))
        apiRequest.addVars("jobTemplateId");
      if(!Objects.equals(jobTemplateResource, original.getJobTemplateResource()))
        apiRequest.addVars("jobTemplateResource");
      if(!Objects.equals(jobTemplateDescription, original.getJobTemplateDescription()))
        apiRequest.addVars("jobTemplateDescription");
      if(!Objects.equals(jobType, original.getJobType()))
        apiRequest.addVars("jobType");
      if(!Objects.equals(aapTemplateId, original.getAapTemplateId()))
        apiRequest.addVars("aapTemplateId");
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
    sb.append(Optional.ofNullable(inventoryResource).map(v -> "inventoryResource: \"" + v + "\"\n" ).orElse(""));
    sb.append(Optional.ofNullable(aapInventoryId).map(v -> "aapInventoryId: " + v + "\n").orElse(""));
    sb.append(Optional.ofNullable(ansibleProjectResource).map(v -> "ansibleProjectResource: \"" + v + "\"\n" ).orElse(""));
    sb.append(Optional.ofNullable(aapProjectId).map(v -> "aapProjectId: " + v + "\n").orElse(""));
    sb.append(Optional.ofNullable(ansiblePlaybook).map(v -> "ansiblePlaybook: \"" + v + "\"\n" ).orElse(""));
    sb.append(Optional.ofNullable(jobTemplateName).map(v -> "jobTemplateName: \"" + v + "\"\n" ).orElse(""));
    sb.append(Optional.ofNullable(jobTemplateId).map(v -> "jobTemplateId: \"" + v + "\"\n" ).orElse(""));
    sb.append(Optional.ofNullable(jobTemplateResource).map(v -> "jobTemplateResource: \"" + v + "\"\n" ).orElse(""));
    sb.append(Optional.ofNullable(jobTemplateDescription).map(v -> "jobTemplateDescription: \"" + v + "\"\n" ).orElse(""));
    sb.append(Optional.ofNullable(jobType).map(v -> "jobType: \"" + v + "\"\n" ).orElse(""));
    sb.append(Optional.ofNullable(aapTemplateId).map(v -> "aapTemplateId: " + v + "\n").orElse(""));
    return sb.toString();
  }

  public static final String CLASS_SIMPLE_NAME = "JobTemplate";
  public static final String CLASS_CANONICAL_NAME = "org.computate.dcm.model.eda.jobtemplate.JobTemplate";
  public static final String CLASS_AUTH_RESOURCE = "JOBTEMPLATE";
  public static final String CLASS_API_ADDRESS_JobTemplate = "dcm-enUS-JobTemplate";
  public static String getClassApiAddress() {
    return CLASS_API_ADDRESS_JobTemplate;
  }
  public static final String VAR_tenantResource = "tenantResource";
  public static final String SET_tenantResource = "setTenantResource";
  public static final String VAR_tenantId = "tenantId";
  public static final String SET_tenantId = "setTenantId";
  public static final String VAR_aapOrganizationId = "aapOrganizationId";
  public static final String SET_aapOrganizationId = "setAapOrganizationId";
  public static final String VAR_inventoryResource = "inventoryResource";
  public static final String SET_inventoryResource = "setInventoryResource";
  public static final String VAR_aapInventoryId = "aapInventoryId";
  public static final String SET_aapInventoryId = "setAapInventoryId";
  public static final String VAR_ansibleProjectResource = "ansibleProjectResource";
  public static final String SET_ansibleProjectResource = "setAnsibleProjectResource";
  public static final String VAR_aapProjectId = "aapProjectId";
  public static final String SET_aapProjectId = "setAapProjectId";
  public static final String VAR_ansiblePlaybook = "ansiblePlaybook";
  public static final String SET_ansiblePlaybook = "setAnsiblePlaybook";
  public static final String VAR_jobTemplateName = "jobTemplateName";
  public static final String SET_jobTemplateName = "setJobTemplateName";
  public static final String VAR_jobTemplateId = "jobTemplateId";
  public static final String SET_jobTemplateId = "setJobTemplateId";
  public static final String VAR_jobTemplateResource = "jobTemplateResource";
  public static final String SET_jobTemplateResource = "setJobTemplateResource";
  public static final String VAR_jobTemplateDescription = "jobTemplateDescription";
  public static final String SET_jobTemplateDescription = "setJobTemplateDescription";
  public static final String VAR_jobType = "jobType";
  public static final String SET_jobType = "setJobType";
  public static final String VAR_aapTemplateId = "aapTemplateId";
  public static final String SET_aapTemplateId = "setAapTemplateId";

  public static List<String> varsQForClass() {
    return JobTemplate.varsQJobTemplate(new ArrayList<String>());
  }
  public static List<String> varsQJobTemplate(List<String> vars) {
    BaseModel.varsQBaseModel(vars);
    return vars;
  }

  public static List<String> varsFqForClass() {
    return JobTemplate.varsFqJobTemplate(new ArrayList<String>());
  }
  public static List<String> varsFqJobTemplate(List<String> vars) {
    BaseModel.varsFqBaseModel(vars);
    return vars;
  }

  public static List<String> varsRangeForClass() {
    return JobTemplate.varsRangeJobTemplate(new ArrayList<String>());
  }
  public static List<String> varsRangeJobTemplate(List<String> vars) {
    BaseModel.varsRangeBaseModel(vars);
    return vars;
  }

  public static final String DISPLAY_NAME_tenantResource = "tenant";
  public static final String DISPLAY_NAME_tenantId = "tenant ID";
  public static final String DISPLAY_NAME_aapOrganizationId = "AAP organization ID";
  public static final String DISPLAY_NAME_inventoryResource = "inventory";
  public static final String DISPLAY_NAME_aapInventoryId = "AAP inventory ID";
  public static final String DISPLAY_NAME_ansibleProjectResource = "Ansible project";
  public static final String DISPLAY_NAME_aapProjectId = "AAP project ID";
  public static final String DISPLAY_NAME_ansiblePlaybook = "Ansible playbook";
  public static final String DISPLAY_NAME_jobTemplateName = "job template name";
  public static final String DISPLAY_NAME_jobTemplateId = "job template ID";
  public static final String DISPLAY_NAME_jobTemplateResource = "job template resource";
  public static final String DISPLAY_NAME_jobTemplateDescription = "job template description";
  public static final String DISPLAY_NAME_jobType = "job type";
  public static final String DISPLAY_NAME_aapTemplateId = "AAP template ID";

  @Override
  public String idForClass() {
    return jobTemplateResource;
  }

  @Override
  public String titleForClass() {
    return objectTitle;
  }

  @Override
  public String nameForClass() {
    return jobTemplateName;
  }

  @Override
  public String classNameAdjectiveSingularForClass() {
    return JobTemplate.NameAdjectiveSingular_enUS;
  }

  @Override
  public String descriptionForClass() {
    return jobTemplateDescription;
  }

  @Override
  public String enUSStringFormatUrlEditPageForClass() {
    return "%s/en-us/edit/job-template/%s";
  }

  public static String varJsonForClass(String var, Boolean patch) {
    return JobTemplate.varJsonJobTemplate(var, patch);
  }
  public static String varJsonJobTemplate(String var, Boolean patch) {
    switch(var) {
    case VAR_tenantResource:
      return patch ? SET_tenantResource : VAR_tenantResource;
    case VAR_tenantId:
      return patch ? SET_tenantId : VAR_tenantId;
    case VAR_aapOrganizationId:
      return patch ? SET_aapOrganizationId : VAR_aapOrganizationId;
    case VAR_inventoryResource:
      return patch ? SET_inventoryResource : VAR_inventoryResource;
    case VAR_aapInventoryId:
      return patch ? SET_aapInventoryId : VAR_aapInventoryId;
    case VAR_ansibleProjectResource:
      return patch ? SET_ansibleProjectResource : VAR_ansibleProjectResource;
    case VAR_aapProjectId:
      return patch ? SET_aapProjectId : VAR_aapProjectId;
    case VAR_ansiblePlaybook:
      return patch ? SET_ansiblePlaybook : VAR_ansiblePlaybook;
    case VAR_jobTemplateName:
      return patch ? SET_jobTemplateName : VAR_jobTemplateName;
    case VAR_jobTemplateId:
      return patch ? SET_jobTemplateId : VAR_jobTemplateId;
    case VAR_jobTemplateResource:
      return patch ? SET_jobTemplateResource : VAR_jobTemplateResource;
    case VAR_jobTemplateDescription:
      return patch ? SET_jobTemplateDescription : VAR_jobTemplateDescription;
    case VAR_jobType:
      return patch ? SET_jobType : VAR_jobType;
    case VAR_aapTemplateId:
      return patch ? SET_aapTemplateId : VAR_aapTemplateId;
    default:
      return BaseModel.varJsonBaseModel(var, patch);
    }
  }

  public static String displayNameForClass(String var) {
    return JobTemplate.displayNameJobTemplate(var);
  }
  public static String displayNameJobTemplate(String var) {
    switch(var) {
    case VAR_tenantResource:
      return DISPLAY_NAME_tenantResource;
    case VAR_tenantId:
      return DISPLAY_NAME_tenantId;
    case VAR_aapOrganizationId:
      return DISPLAY_NAME_aapOrganizationId;
    case VAR_inventoryResource:
      return DISPLAY_NAME_inventoryResource;
    case VAR_aapInventoryId:
      return DISPLAY_NAME_aapInventoryId;
    case VAR_ansibleProjectResource:
      return DISPLAY_NAME_ansibleProjectResource;
    case VAR_aapProjectId:
      return DISPLAY_NAME_aapProjectId;
    case VAR_ansiblePlaybook:
      return DISPLAY_NAME_ansiblePlaybook;
    case VAR_jobTemplateName:
      return DISPLAY_NAME_jobTemplateName;
    case VAR_jobTemplateId:
      return DISPLAY_NAME_jobTemplateId;
    case VAR_jobTemplateResource:
      return DISPLAY_NAME_jobTemplateResource;
    case VAR_jobTemplateDescription:
      return DISPLAY_NAME_jobTemplateDescription;
    case VAR_jobType:
      return DISPLAY_NAME_jobType;
    case VAR_aapTemplateId:
      return DISPLAY_NAME_aapTemplateId;
    default:
      return BaseModel.displayNameBaseModel(var);
    }
  }

  public static String descriptionJobTemplate(String var) {
    if(var == null)
      return null;
    switch(var) {
    case VAR_tenantResource:
      return "The unique authorization resource for the tenant for multi-tenancy";
    case VAR_tenantId:
      return "The tenant ID and Sensu namespace for the tenant. ";
    case VAR_aapOrganizationId:
      return "The ID of the ansible organization in AAP. ";
    case VAR_inventoryResource:
      return "The unique authorization resource for the inventory for multi-tenancy";
    case VAR_aapInventoryId:
      return "The inventory ID in Ansible Automation Platform. ";
    case VAR_ansibleProjectResource:
      return "The Ansible project containing the playbook for this Job Template. ";
    case VAR_aapProjectId:
      return "The project ID in Ansible Automation Platform. ";
    case VAR_ansiblePlaybook:
      return "The Ansible playbook for this Job Template. ";
    case VAR_jobTemplateName:
      return "The name of the job template (may only contain letters, numbers, periods, colons, and dashes). ";
    case VAR_jobTemplateId:
      return "The ID of the job template in DCM. ";
    case VAR_jobTemplateResource:
      return "The unique authorization resource for the job template for multi-tenancy";
    case VAR_jobTemplateDescription:
      return "The description of the job template. ";
    case VAR_jobType:
      return "The job type of the job template. ";
    case VAR_aapTemplateId:
      return "The template ID in Ansible Automation Platform. ";
      default:
        return BaseModel.descriptionBaseModel(var);
    }
  }

  public static String classSimpleNameJobTemplate(String var) {
    switch(var) {
    case VAR_tenantResource:
      return "String";
    case VAR_tenantId:
      return "String";
    case VAR_aapOrganizationId:
      return "Long";
    case VAR_inventoryResource:
      return "String";
    case VAR_aapInventoryId:
      return "Long";
    case VAR_ansibleProjectResource:
      return "String";
    case VAR_aapProjectId:
      return "Long";
    case VAR_ansiblePlaybook:
      return "String";
    case VAR_jobTemplateName:
      return "String";
    case VAR_jobTemplateId:
      return "String";
    case VAR_jobTemplateResource:
      return "String";
    case VAR_jobTemplateDescription:
      return "String";
    case VAR_jobType:
      return "String";
    case VAR_aapTemplateId:
      return "Long";
      default:
        return BaseModel.classSimpleNameBaseModel(var);
    }
  }

  public static Integer htmColumnJobTemplate(String var) {
    switch(var) {
    case VAR_tenantResource:
      return 0;
    case VAR_jobTemplateName:
      return 1;
    case VAR_jobTemplateDescription:
      return 2;
      default:
        return BaseModel.htmColumnBaseModel(var);
    }
  }

  public static Integer htmRowJobTemplate(String var) {
    switch(var) {
    case VAR_inventoryResource:
      return 3;
    case VAR_ansibleProjectResource:
      return 4;
    case VAR_ansiblePlaybook:
      return 5;
    case VAR_jobTemplateName:
      return 5;
    case VAR_jobTemplateDescription:
      return 5;
    case VAR_jobType:
      return 5;
      default:
        return BaseModel.htmRowBaseModel(var);
    }
  }

  public static Integer htmCellJobTemplate(String var) {
    switch(var) {
    case VAR_inventoryResource:
      return 0;
    case VAR_ansibleProjectResource:
      return 0;
    case VAR_ansiblePlaybook:
      return 0;
    case VAR_jobTemplateName:
      return 1;
    case VAR_jobTemplateDescription:
      return 2;
    case VAR_jobType:
      return 3;
      default:
        return BaseModel.htmCellBaseModel(var);
    }
  }

  public static Integer lengthMinJobTemplate(String var) {
    switch(var) {
      default:
        return BaseModel.lengthMinBaseModel(var);
    }
  }

  public static Integer lengthMaxJobTemplate(String var) {
    switch(var) {
      default:
        return BaseModel.lengthMaxBaseModel(var);
    }
  }

  public static Integer maxJobTemplate(String var) {
    switch(var) {
      default:
        return BaseModel.maxBaseModel(var);
    }
  }

  public static Integer minJobTemplate(String var) {
    switch(var) {
      default:
        return BaseModel.minBaseModel(var);
    }
  }
}
