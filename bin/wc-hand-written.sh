echo hand written
find \
  ~/.local/src/dcm/vars.yaml \
  ~/.local/src/dcm/README.md \
  ~/.local/src/dcm/src/main/java/org/computate/dcm/verticle/SiteRoutes.java \
      -type f -exec wc -l {} +
echo hand written
