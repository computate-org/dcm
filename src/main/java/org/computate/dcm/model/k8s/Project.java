package org.computate.dcm.model.k8s;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import org.apache.commons.lang3.StringUtils;
import org.computate.search.tool.SearchTool;
import org.computate.search.wrap.Wrap;
import org.computate.vertx.config.ComputateConfigKeys;
import org.computate.vertx.search.list.SearchList;
import org.computate.dcm.model.BaseModel;

import io.vertx.core.Promise;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.pgclient.data.Path;
import io.vertx.pgclient.data.Point;
import io.vertx.pgclient.data.Polygon;

/**
 * Order: 10
 * Description: A research project using AI and GPUs
 * AName: an project
 * PluralName: projects
 * Icon: <i class="fa-regular fa-people-line"></i>
 * Rows: 100
 * 
 * SearchPageUri: /en-us/search/project
 * EditPageUri: /en-us/edit/project/{projectResource}
 * UserPageUri: /en-us/user/project/{projectResource}
 * ApiUri: /en-us/api/project
 * ApiMethod:
 *   Search:
 *   GET:
 *   PATCH:
 *   POST:
 *   DELETE:
 *   PUTImport:
 * 
 * AuthGroup:
 *   ProjectReader:
 *     GET:
 *   ProjectEditor:
 *     GET:
 *     POST:
 *     PATCH:
 *   Admin:
 *     GET:
 *     PUT:
 *     POST:
 *     PATCH:
 *     DELETE:
 *   SuperAdmin:
 *     GET:
 *     PUT:
 *     POST:
 *     PATCH:
 *     DELETE:
 *     Admin:
 *     SuperAdmin:
 */
public class Project extends ProjectGen<BaseModel> {

  /**
   * {@inheritDoc}
   * DocValues: true
   * Persist: true
   * DisplayName: project name
   * Description: The name of this project
   * HtmRowTitleOpen: project details
   * HtmRow: 3
   * HtmCell: 0
   * HtmColumn: 0
   * Facet: true
   * Unique: true
   **/
  protected void _projectName(Wrap<String> w) {}

  /**
   * {@inheritDoc}
   * DocValues: true
   * Persist: true
   * DisplayName: project auth resource
   * Description: The unique authorization resource for the project for multi-tenancy
   * Facet: true
   * AuthorizationResource: PROJECT
   * VarId: true
   **/
  protected void _projectResource(Wrap<String> w) {
    w.o(String.format("%s-%s", Project.CLASS_AUTH_RESOURCE, projectName));
  }

  /**
   * {@inheritDoc}
   * DocValues: true
   * DisplayName: project display name
   * Description: The display name of this project
   * Facet: true
   * VarName: true
   **/
  protected void _projectDisplayName(Wrap<String> w) {
    w.o(String.format("%s project", projectName));
  }

  /**
   * {@inheritDoc}
   * DocValues: true
   * Persist: true
   * DisplayName: description
   * Description: A description of this project
   * HtmRow: 3
   * HtmCell: 1
   * Facet: true
   * VarDescription: true
   * Multiline: true
   **/
  protected void _description(Wrap<String> w) {}

  /**
   * {@inheritDoc}
   * DocValues: true
   * Persist: true
   * Facet: true
   * DisplayName: GPU enabled
   * Description: Whether GPUs are enabled for this project. 
   * HtmRow: 3
   * HtmCell: 2
   **/
  protected void _gpuEnabled(Wrap<Boolean> w) {
    w.o(false);
  }

  /**
   * {@inheritDoc}
   * DocValues: true
   * Persist: true
   * Facet: true
   * DisplayName: pod restarts
   * Description: The number of pod restarts in this project. 
   * HtmRowTitleOpen: health checks
   * HtmRow: 4
   * HtmCell: 0
   **/
  protected void _podRestartCount(Wrap<Integer> w) {
  }

  /**
   * {@inheritDoc}
   * DocValues: true
   * Persist: true
   * Facet: true
   * DisplayName: pods restarting
   * Description: The names of the pods restarting in this project. 
   * HtmRow: 4
   * HtmCell: 1
   **/
  protected void _podsRestarting(List<String> l) {
  }

  /**
   * {@inheritDoc}
   * DocValues: true
   * Persist: true
   * Facet: true
   * DisplayName: pods terminating
   * Description: The number of pods terminating in this project. 
   * HtmRow: 4
   * HtmCell: 1
   **/
  protected void _podTerminatingCount(Wrap<Integer> w) {
  }

  /**
   * {@inheritDoc}
   * DocValues: true
   * Persist: true
   * Facet: true
   * DisplayName: pods terminating
   * Description: The names of the pods terminating in this project. 
   * HtmRow: 4
   * HtmCell: 2
   **/
  protected void _podsTerminating(List<String> l) {
  }

  /**
   * {@inheritDoc}
   * DocValues: true
   * Persist: true
   * Facet: true
   * DisplayName: Full PVCs count
   * Description: The number of persistent volume claims that are running out of disk space in this project. 
   * HtmRow: 4
   * HtmCell: 3
   **/
  protected void _fullPvcsCount(Wrap<Integer> w) {
  }

  /**
   * {@inheritDoc}
   * DocValues: true
   * Persist: true
   * Facet: true
   * DisplayName: pods restarting
   * Description: The names of the persistent volume claims that are running out of disk space in this project. 
   * HtmRow: 4
   * HtmCell: 4
   **/
  protected void _fullPvcs(List<String> l) {
  }

  /**
   * {@inheritDoc}
   * DocValues: true
   * Persist: true
   * Facet: true
   * DisplayName: namespace terminating
   * Description: Whether namespace is stuck in terminating status. 
   * HtmRow: 4
   * HtmCell: 5
   **/
  protected void _namespaceTerminating(Wrap<Boolean> w) {
    w.o(false);
  }
}
