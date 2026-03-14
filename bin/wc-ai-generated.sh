echo AI generated
find \
  ~/.local/src/dcm/pom.xml \
  ~/.local/src/dcm/Containerfile \
  ~/.local/src/dcm/pom.xml \
  ~/.local/src/dcm/.gitignore \
  ~/.local/src/dcm/bin/ \
  ~/.local/src/dcm/src/main/java/org/computate/dcm/config/ \
  ~/.local/src/dcm/src/main/java/org/computate/dcm/model/BaseModel.java \
  ~/.local/src/dcm/src/main/java/org/computate/dcm/page/ \
  ~/.local/src/dcm/src/main/java/org/computate/dcm/request/ \
  ~/.local/src/dcm/src/main/java/org/computate/dcm/result/ \
  ~/.local/src/dcm/src/main/java/org/computate/dcm/user/ \
  ~/.local/src/dcm/src/main/java/org/computate/dcm/verticle/MainVerticle.java \
  ~/.local/src/dcm/src/main/java/org/computate/dcm/verticle/WorkerVerticle.java \
  ~/.local/src/dcm/src/main/resources/ \
  ~/.local/src/dcm/src/gen/ \
  ~/.local/src/dcm-static/webawesome/css/ \
  ~/.local/src/dcm-static/webawesome/fiware/ \
  ~/.local/src/dcm-static/webawesome/js/ \
  -type f -exec wc -l {} +
echo AI generated
