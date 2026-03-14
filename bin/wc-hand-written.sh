echo hand written
find \
  ~/.local/src/dcm/vars.yaml \
  ~/.local/src/dcm/README.md \
  ~/.local/src/dcm/src/main/java/org/computate/dcm/verticle/SiteRoutes.java \
  ~/.local/src/dcm/src/main/java/org/computate/dcm/timezone/TimeZone.java \
  ~/.local/src/dcm/src/main/java/org/computate/dcm/timezone/TimeZonePage.java \
  ~/.local/src/dcm/src/main/java/org/computate/dcm/timezone/TimeZoneEnUSApiServiceImpl.java \
  ~/.local/src/dcm/src/main/java/org/computate/dcm/model/eda/tenant/Tenant.java \
  ~/.local/src/dcm/src/main/java/org/computate/dcm/model/eda/tenant/TenantPage.java \
  ~/.local/src/dcm/src/main/java/org/computate/dcm/model/eda/tenant/TenantEnUSApiServiceImpl.java \
  ~/.local/src/dcm/src/main/java/org/computate/dcm/page/SitePage.java \
  ~/.local/src/dcm/src/main/java/org/computate/dcm/page/SitePagePage.java \
  ~/.local/src/dcm/src/main/java/org/computate/dcm/page/SitePageEnUSApiServiceImpl.java \
  ~/.local/src/dcm/src/main/java/org/computate/dcm/model/eda/ansibleproject/AnsibleProject.java \
  ~/.local/src/dcm/src/main/java/org/computate/dcm/model/eda/ansibleproject/AnsibleProjectPage.java \
  ~/.local/src/dcm/src/main/java/org/computate/dcm/model/eda/ansibleproject/AnsibleProjectEnUSApiServiceImpl.java \
  ~/.local/src/dcm/src/main/java/org/computate/dcm/model/eda/jobtemplate/JobTemplate.java \
  ~/.local/src/dcm/src/main/java/org/computate/dcm/model/eda/jobtemplate/JobTemplatePage.java \
  ~/.local/src/dcm/src/main/java/org/computate/dcm/model/eda/jobtemplate/JobTemplateEnUSApiServiceImpl.java \
  ~/.local/src/dcm/src/main/java/org/computate/dcm/model/eda/hostinventory/HostInventory.java \
  ~/.local/src/dcm/src/main/java/org/computate/dcm/model/eda/hostinventory/HostInventoryPage.java \
  ~/.local/src/dcm/src/main/java/org/computate/dcm/model/eda/hostinventory/HostInventoryEnUSApiServiceImpl.java \
  ~/.local/src/dcm/src/main/java/org/computate/dcm/model/eda/host/Host.java \
  ~/.local/src/dcm/src/main/java/org/computate/dcm/model/eda/host/HostPage.java \
  ~/.local/src/dcm/src/main/java/org/computate/dcm/model/eda/host/HostEnUSApiServiceImpl.java \
  ~/.local/src/dcm/src/main/java/org/computate/dcm/model/eda/hostcheck/HostCheck.java \
  ~/.local/src/dcm/src/main/java/org/computate/dcm/model/eda/hostcheck/HostCheckPage.java \
  ~/.local/src/dcm/src/main/java/org/computate/dcm/model/eda/hostcheck/HostCheckEnUSApiServiceImpl.java \
  ~/.local/src/dcm/src/main/java/org/computate/dcm/model/k8s/Project.java \
  ~/.local/src/dcm/src/main/java/org/computate/dcm/model/k8s/ProjectPage.java \
  ~/.local/src/dcm/src/main/java/org/computate/dcm/model/k8s/ProjectEnUSApiServiceImpl.java \
  ~/.local/src/dcm/src/main/java/org/computate/dcm/model/platform/aitelemetry/AiTelemetryPlatform.java \
  ~/.local/src/dcm/src/main/java/org/computate/dcm/model/platform/aitelemetry/AiTelemetryPlatformPage.java \
  ~/.local/src/dcm/src/main/java/org/computate/dcm/model/platform/aitelemetry/AiTelemetryPlatformEnUSApiServiceImpl.java \
      -type f -exec wc -l {} +
echo hand written
