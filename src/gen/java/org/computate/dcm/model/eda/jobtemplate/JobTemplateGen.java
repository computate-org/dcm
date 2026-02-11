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
import org.computate.dcm.model.eda.hostinventory.HostInventory;
import java.lang.Long;
import org.computate.search.wrap.Wrap;
import io.vertx.core.Promise;
import io.vertx.core.Future;
import io.vertx.core.json.JsonArray;
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
 * <h2>Order: 7</h2>
 * <p>This class contains a comment <b>"Order: 7"</b>, which means this class will be sorted by the given number 7 ascending when code that relates to multiple classes at the same time is generated. 
 * </p>
 * <h2>SqlOrder: 7</h2>
 * <p>This class contains a comment <b>"SqlOrder: 7"</b>, which means this class will be sorted by the given number 7 ascending when SQL code to create and drop the tables is generated. 
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
  public static final String ApiUriEditPage_enUS = "/en-us/edit/job-template/{jobTemplateName}";
  public static final String OfName_enUS = "of job template";
  public static final String ANameAdjective_enUS = "a job template";
  public static final String NameAdjectiveSingular_enUS = "job template";
  public static final String NameAdjectivePlural_enUS = "job templates";
  public static final String Search_enUS_OpenApiUri = "/en-us/api/job-template";
  public static final String Search_enUS_StringFormatUri = "/en-us/api/job-template";
  public static final String Search_enUS_StringFormatUrl = "%s/en-us/api/job-template";
  public static final String GET_enUS_OpenApiUri = "/en-us/api/job-template/{jobTemplateName}";
  public static final String GET_enUS_StringFormatUri = "/en-us/api/job-template/%s";
  public static final String GET_enUS_StringFormatUrl = "%s/en-us/api/job-template/%s";
  public static final String PATCH_enUS_OpenApiUri = "/en-us/api/job-template";
  public static final String PATCH_enUS_StringFormatUri = "/en-us/api/job-template";
  public static final String PATCH_enUS_StringFormatUrl = "%s/en-us/api/job-template";
  public static final String POST_enUS_OpenApiUri = "/en-us/api/job-template";
  public static final String POST_enUS_StringFormatUri = "/en-us/api/job-template";
  public static final String POST_enUS_StringFormatUrl = "%s/en-us/api/job-template";
  public static final String DELETE_enUS_OpenApiUri = "/en-us/api/job-template/{jobTemplateName}";
  public static final String DELETE_enUS_StringFormatUri = "/en-us/api/job-template/%s";
  public static final String DELETE_enUS_StringFormatUrl = "%s/en-us/api/job-template/%s";
  public static final String PUTImport_enUS_OpenApiUri = "/en-us/api/job-template-import";
  public static final String PUTImport_enUS_StringFormatUri = "/en-us/api/job-template-import";
  public static final String PUTImport_enUS_StringFormatUrl = "%s/en-us/api/job-template-import";
  public static final String SearchPage_enUS_OpenApiUri = "/en-us/search/job-template";
  public static final String SearchPage_enUS_StringFormatUri = "/en-us/search/job-template";
  public static final String SearchPage_enUS_StringFormatUrl = "%s/en-us/search/job-template";
  public static final String EditPage_enUS_OpenApiUri = "/en-us/edit/job-template/{jobTemplateName}";
  public static final String EditPage_enUS_StringFormatUri = "/en-us/edit/job-template/%s";
  public static final String EditPage_enUS_StringFormatUrl = "%s/en-us/edit/job-template/%s";
  public static final String UserPage_enUS_OpenApiUri = "/en-us/user/job-template/{jobTemplateName}";
  public static final String UserPage_enUS_StringFormatUri = "/en-us/user/job-template/%s";
  public static final String UserPage_enUS_StringFormatUrl = "%s/en-us/user/job-template/%s";
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

	//////////////////////
  // ansibleProjectId //
	//////////////////////


  /**
   *  The entity ansibleProjectId
   *	 is defined as null before being initialized. 
   */
  @JsonProperty
  @JsonSerialize(using = ToStringSerializer.class)
  @JsonInclude(Include.NON_NULL)
  protected Long ansibleProjectId;

  /**
   * <br> The entity ansibleProjectId
   *  is defined as null before being initialized. 
   * <br><a href="https://solr.apps-crc.testing/solr/#/computate/query?q=*:*&fq=partEstEntite_indexed_boolean:true&fq=classeNomCanonique_enUS_indexed_string:org.computate.dcm.model.eda.jobtemplate.JobTemplate&fq=entiteVar_enUS_indexed_string:ansibleProjectId">Find the entity ansibleProjectId in Solr</a>
   * <br>
   * @param w is for wrapping a value to assign to this entity during initialization. 
   **/
  protected abstract void _ansibleProjectId(Wrap<Long> w);

  public Long getAnsibleProjectId() {
    return ansibleProjectId;
  }

  public void setAnsibleProjectId(Long ansibleProjectId) {
    this.ansibleProjectId = ansibleProjectId;
  }
  @JsonIgnore
  public void setAnsibleProjectId(String o) {
    this.ansibleProjectId = JobTemplate.staticSetAnsibleProjectId(siteRequest_, o);
  }
  public static Long staticSetAnsibleProjectId(SiteRequest siteRequest_, String o) {
    if(NumberUtils.isParsable(o))
      return Long.parseLong(o);
    return null;
  }
  protected JobTemplate ansibleProjectIdInit() {
    Wrap<Long> ansibleProjectIdWrap = new Wrap<Long>().var("ansibleProjectId");
    if(ansibleProjectId == null) {
      _ansibleProjectId(ansibleProjectIdWrap);
      Optional.ofNullable(ansibleProjectIdWrap.getO()).ifPresent(o -> {
        setAnsibleProjectId(o);
      });
    }
    return (JobTemplate)this;
  }

  public static Long staticSearchAnsibleProjectId(SiteRequest siteRequest_, Long o) {
    return o;
  }

  public static String staticSearchStrAnsibleProjectId(SiteRequest siteRequest_, Long o) {
    return o == null ? null : o.toString();
  }

  public static String staticSearchFqAnsibleProjectId(SiteRequest siteRequest_, String o) {
    return JobTemplate.staticSearchAnsibleProjectId(siteRequest_, JobTemplate.staticSetAnsibleProjectId(siteRequest_, o)).toString();
  }

  public Long sqlAnsibleProjectId() {
    return ansibleProjectId;
  }

  public static String staticJsonAnsibleProjectId(Long ansibleProjectId) {
    return Optional.ofNullable(ansibleProjectId).map(v -> v.toString()).orElse(null);
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
        inventoryResourceInit();
        jobTemplateNameInit();
        jobTemplateDescriptionInit();
        jobTypeInit();
        ansibleProjectIdInit();
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
      case "inventoryResource":
        return oJobTemplate.inventoryResource;
      case "jobTemplateName":
        return oJobTemplate.jobTemplateName;
      case "jobTemplateDescription":
        return oJobTemplate.jobTemplateDescription;
      case "jobType":
        return oJobTemplate.jobType;
      case "ansibleProjectId":
        return oJobTemplate.ansibleProjectId;
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
    case "inventoryResource":
      return JobTemplate.staticSetInventoryResource(siteRequest_, v);
    case "jobTemplateName":
      return JobTemplate.staticSetJobTemplateName(siteRequest_, v);
    case "jobTemplateDescription":
      return JobTemplate.staticSetJobTemplateDescription(siteRequest_, v);
    case "jobType":
      return JobTemplate.staticSetJobType(siteRequest_, v);
    case "ansibleProjectId":
      return JobTemplate.staticSetAnsibleProjectId(siteRequest_, v);
      default:
        return BaseModel.staticSetBaseModel(entityVar,  siteRequest_, v, o);
    }
  }

  ////////////////
  // staticSearch //
  ////////////////

  public static Object staticSearchForClass(String entityVar, SiteRequest siteRequest_, Object o) {
    return staticSearchJobTemplate(entityVar,  siteRequest_, o);
  }
  public static Object staticSearchJobTemplate(String entityVar, SiteRequest siteRequest_, Object o) {
    switch(entityVar) {
    case "tenantResource":
      return JobTemplate.staticSearchTenantResource(siteRequest_, (String)o);
    case "inventoryResource":
      return JobTemplate.staticSearchInventoryResource(siteRequest_, (String)o);
    case "jobTemplateName":
      return JobTemplate.staticSearchJobTemplateName(siteRequest_, (String)o);
    case "jobTemplateDescription":
      return JobTemplate.staticSearchJobTemplateDescription(siteRequest_, (String)o);
    case "jobType":
      return JobTemplate.staticSearchJobType(siteRequest_, (String)o);
    case "ansibleProjectId":
      return JobTemplate.staticSearchAnsibleProjectId(siteRequest_, (Long)o);
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
    case "inventoryResource":
      return JobTemplate.staticSearchStrInventoryResource(siteRequest_, (String)o);
    case "jobTemplateName":
      return JobTemplate.staticSearchStrJobTemplateName(siteRequest_, (String)o);
    case "jobTemplateDescription":
      return JobTemplate.staticSearchStrJobTemplateDescription(siteRequest_, (String)o);
    case "jobType":
      return JobTemplate.staticSearchStrJobType(siteRequest_, (String)o);
    case "ansibleProjectId":
      return JobTemplate.staticSearchStrAnsibleProjectId(siteRequest_, (Long)o);
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
    case "inventoryResource":
      return JobTemplate.staticSearchFqInventoryResource(siteRequest_, o);
    case "jobTemplateName":
      return JobTemplate.staticSearchFqJobTemplateName(siteRequest_, o);
    case "jobTemplateDescription":
      return JobTemplate.staticSearchFqJobTemplateDescription(siteRequest_, o);
    case "jobType":
      return JobTemplate.staticSearchFqJobType(siteRequest_, o);
    case "ansibleProjectId":
      return JobTemplate.staticSearchFqAnsibleProjectId(siteRequest_, o);
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
      } else if("inventoryresource".equals(varLower)) {
        if(val instanceof String) {
          setInventoryResource((String)val);
        }
        saves.add("inventoryResource");
        return val;
      } else if("jobtemplatename".equals(varLower)) {
        if(val instanceof String) {
          setJobTemplateName((String)val);
        }
        saves.add("jobTemplateName");
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
      } else if("ansibleprojectid".equals(varLower)) {
        if(val instanceof Long) {
          setAnsibleProjectId((Long)val);
        } else {
          setAnsibleProjectId(val == null ? null : val.toString());
        }
        saves.add("ansibleProjectId");
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

      String inventoryResource = (String)doc.get("inventoryResource_docvalues_string");
      if(inventoryResource != null)
        oJobTemplate.setInventoryResource(inventoryResource);

      if(saves.contains("jobTemplateName")) {
        String jobTemplateName = (String)doc.get("jobTemplateName_docvalues_string");
        if(jobTemplateName != null)
          oJobTemplate.setJobTemplateName(jobTemplateName);
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

      if(saves.contains("ansibleProjectId")) {
        Long ansibleProjectId = (Long)doc.get("ansibleProjectId_docvalues_long");
        if(ansibleProjectId != null)
          oJobTemplate.setAnsibleProjectId(ansibleProjectId);
      }
    }

    super.populateBaseModel(doc);
  }

  public void indexJobTemplate(JsonObject doc) {
    if(tenantResource != null) {
      doc.put("tenantResource_docvalues_string", tenantResource);
    }
    if(inventoryResource != null) {
      doc.put("inventoryResource_docvalues_string", inventoryResource);
    }
    if(jobTemplateName != null) {
      doc.put("jobTemplateName_docvalues_string", jobTemplateName);
    }
    if(jobTemplateDescription != null) {
      doc.put("jobTemplateDescription_docvalues_string", jobTemplateDescription);
    }
    if(jobType != null) {
      doc.put("jobType_docvalues_string", jobType);
    }
    if(ansibleProjectId != null) {
      doc.put("ansibleProjectId_docvalues_long", ansibleProjectId);
    }
    super.indexBaseModel(doc);

	}

  public static String varStoredJobTemplate(String entityVar) {
    switch(entityVar) {
      case "tenantResource":
        return "tenantResource_docvalues_string";
      case "inventoryResource":
        return "inventoryResource_docvalues_string";
      case "jobTemplateName":
        return "jobTemplateName_docvalues_string";
      case "jobTemplateDescription":
        return "jobTemplateDescription_docvalues_string";
      case "jobType":
        return "jobType_docvalues_string";
      case "ansibleProjectId":
        return "ansibleProjectId_docvalues_long";
      default:
        return BaseModel.varStoredBaseModel(entityVar);
    }
  }

  public static String varIndexedJobTemplate(String entityVar) {
    switch(entityVar) {
      case "tenantResource":
        return "tenantResource_docvalues_string";
      case "inventoryResource":
        return "inventoryResource_docvalues_string";
      case "jobTemplateName":
        return "jobTemplateName_docvalues_string";
      case "jobTemplateDescription":
        return "jobTemplateDescription_docvalues_string";
      case "jobType":
        return "jobType_docvalues_string";
      case "ansibleProjectId":
        return "ansibleProjectId_docvalues_long";
      default:
        return BaseModel.varIndexedBaseModel(entityVar);
    }
  }

  public static String searchVarJobTemplate(String searchVar) {
    switch(searchVar) {
      case "tenantResource_docvalues_string":
        return "tenantResource";
      case "inventoryResource_docvalues_string":
        return "inventoryResource";
      case "jobTemplateName_docvalues_string":
        return "jobTemplateName";
      case "jobTemplateDescription_docvalues_string":
        return "jobTemplateDescription";
      case "jobType_docvalues_string":
        return "jobType";
      case "ansibleProjectId_docvalues_long":
        return "ansibleProjectId";
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
    oJobTemplate.setInventoryResource(Optional.ofNullable(doc.get("inventoryResource_docvalues_string")).map(v -> v.toString()).orElse(null));
    oJobTemplate.setJobTemplateName(Optional.ofNullable(doc.get("jobTemplateName_docvalues_string")).map(v -> v.toString()).orElse(null));
    oJobTemplate.setJobTemplateDescription(Optional.ofNullable(doc.get("jobTemplateDescription_docvalues_string")).map(v -> v.toString()).orElse(null));
    oJobTemplate.setJobType(Optional.ofNullable(doc.get("jobType_docvalues_string")).map(v -> v.toString()).orElse(null));
    oJobTemplate.setAnsibleProjectId(Optional.ofNullable(doc.get("ansibleProjectId_docvalues_long")).map(v -> v.toString()).orElse(null));

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
      if(!Objects.equals(inventoryResource, original.getInventoryResource()))
        apiRequest.addVars("inventoryResource");
      if(!Objects.equals(jobTemplateName, original.getJobTemplateName()))
        apiRequest.addVars("jobTemplateName");
      if(!Objects.equals(jobTemplateDescription, original.getJobTemplateDescription()))
        apiRequest.addVars("jobTemplateDescription");
      if(!Objects.equals(jobType, original.getJobType()))
        apiRequest.addVars("jobType");
      if(!Objects.equals(ansibleProjectId, original.getAnsibleProjectId()))
        apiRequest.addVars("ansibleProjectId");
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
    sb.append(Optional.ofNullable(inventoryResource).map(v -> "inventoryResource: \"" + v + "\"\n" ).orElse(""));
    sb.append(Optional.ofNullable(jobTemplateName).map(v -> "jobTemplateName: \"" + v + "\"\n" ).orElse(""));
    sb.append(Optional.ofNullable(jobTemplateDescription).map(v -> "jobTemplateDescription: \"" + v + "\"\n" ).orElse(""));
    sb.append(Optional.ofNullable(jobType).map(v -> "jobType: \"" + v + "\"\n" ).orElse(""));
    sb.append(Optional.ofNullable(ansibleProjectId).map(v -> "ansibleProjectId: " + v + "\n").orElse(""));
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
  public static final String VAR_inventoryResource = "inventoryResource";
  public static final String VAR_jobTemplateName = "jobTemplateName";
  public static final String VAR_jobTemplateDescription = "jobTemplateDescription";
  public static final String VAR_jobType = "jobType";
  public static final String VAR_ansibleProjectId = "ansibleProjectId";

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
  public static final String DISPLAY_NAME_inventoryResource = "inventory";
  public static final String DISPLAY_NAME_jobTemplateName = "job template name";
  public static final String DISPLAY_NAME_jobTemplateDescription = "job template description";
  public static final String DISPLAY_NAME_jobType = "job type";
  public static final String DISPLAY_NAME_ansibleProjectId = "Ansible project ID";

  @Override
  public String idForClass() {
    return jobTemplateName;
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

  @Override
  public String enUSStringFormatUrlDisplayPageForClass() {
    return null;
  }

  @Override
  public String enUSStringFormatUrlUserPageForClass() {
    return "%s/en-us/user/job-template/%s";
  }

  @Override
  public String enUSStringFormatUrlDownloadForClass() {
    return null;
  }

  public static String displayNameForClass(String var) {
    return JobTemplate.displayNameJobTemplate(var);
  }
  public static String displayNameJobTemplate(String var) {
    switch(var) {
    case VAR_tenantResource:
      return DISPLAY_NAME_tenantResource;
    case VAR_inventoryResource:
      return DISPLAY_NAME_inventoryResource;
    case VAR_jobTemplateName:
      return DISPLAY_NAME_jobTemplateName;
    case VAR_jobTemplateDescription:
      return DISPLAY_NAME_jobTemplateDescription;
    case VAR_jobType:
      return DISPLAY_NAME_jobType;
    case VAR_ansibleProjectId:
      return DISPLAY_NAME_ansibleProjectId;
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
    case VAR_inventoryResource:
      return "The unique authorization resource for the inventory for multi-tenancy";
    case VAR_jobTemplateName:
      return "The name of the job template (may only contain letters, numbers, periods, colons, and dashes). ";
    case VAR_jobTemplateDescription:
      return "The description of the job template. ";
    case VAR_jobType:
      return "The job type of the job template. ";
    case VAR_ansibleProjectId:
      return "The Ansible project ID in Ansible Automation Platform. ";
      default:
        return BaseModel.descriptionBaseModel(var);
    }
  }

  public static String classSimpleNameJobTemplate(String var) {
    switch(var) {
    case VAR_tenantResource:
      return "String";
    case VAR_inventoryResource:
      return "String";
    case VAR_jobTemplateName:
      return "String";
    case VAR_jobTemplateDescription:
      return "String";
    case VAR_jobType:
      return "String";
    case VAR_ansibleProjectId:
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
    case VAR_tenantResource:
      return 3;
    case VAR_inventoryResource:
      return 3;
    case VAR_jobTemplateName:
      return 3;
    case VAR_jobTemplateDescription:
      return 3;
    case VAR_jobType:
      return 3;
    case VAR_ansibleProjectId:
      return 3;
      default:
        return BaseModel.htmRowBaseModel(var);
    }
  }

  public static Integer htmCellJobTemplate(String var) {
    switch(var) {
    case VAR_tenantResource:
      return 0;
    case VAR_inventoryResource:
      return 1;
    case VAR_jobTemplateName:
      return 2;
    case VAR_jobTemplateDescription:
      return 3;
    case VAR_jobType:
      return 4;
    case VAR_ansibleProjectId:
      return 5;
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
