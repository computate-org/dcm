
package org.computate.dcm.config;

import org.computate.vertx.config.ComputateConfigKeys;

/**
 * Keyword: classSimpleNameConfigKeys
 */
public class ConfigKeys extends ComputateConfigKeys {

  public static final String SENSU_HOST_NAME = "SENSU_HOST_NAME";
  public static final String SENSU_SSL = "SENSU_SSL";
  public static final String SENSU_PORT = "SENSU_PORT";
  public static final String SENSU_OPENSHIFT_SECRET = "SENSU_OPENSHIFT_SECRET";
  public static final String SENSU_USER_NAME = "SENSU_USER_NAME";
  public static final String SENSU_PASSWORD = "SENSU_PASSWORD";
  public static final String SENSU_BACKEND_WEBSOCKET_URL = "SENSU_BACKEND_WEBSOCKET_URL";

  public static final String AAP_HOST_NAME = "AAP_HOST_NAME";
  public static final String AAP_SSL = "AAP_SSL";
  public static final String AAP_PORT = "AAP_PORT";
  public static final String AAP_OPENSHIFT_SECRET = "AAP_OPENSHIFT_SECRET";
  public static final String AAP_USER_NAME = "AAP_USER_NAME";
  public static final String AAP_PASSWORD = "AAP_PASSWORD";

  public static final String KAFKA_TOPIC_SENSU_EVENT = "KAFKA_TOPIC_SENSU_EVENT";
}
