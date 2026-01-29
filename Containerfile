FROM registry.access.redhat.com/ubi9/openjdk-21:latest

USER root

RUN microdnf install -y git

RUN install -d /home/default/dcm
COPY . /home/default/dcm

RUN git clone https://github.com/computate-org/computate-base.git /home/default/computate-base
RUN git clone https://github.com/computate-org/computate-search.git /home/default/computate-search
RUN git clone https://github.com/computate-org/computate-vertx.git /home/default/computate-vertx
RUN git clone https://github.com/computate-org/dcm-static.git /home/default/dcm-static

WORKDIR /home/default/computate-base
RUN mvn clean install -DskipTests
WORKDIR /home/default/computate-search
RUN mvn clean install -DskipTests
WORKDIR /home/default/computate-vertx
RUN mvn clean install -DskipTests
WORKDIR $HOME/dcm
RUN mvn clean install -DskipTests

WORKDIR /home/default/dcm
RUN mvn clean install -DskipTests
RUN mvn dependency:build-classpath -Dmdep.outputFile=/home/default/dcm/cp.txt -q
CMD java -cp "$(cat /home/default/dcm/cp.txt):/home/default/dcm/target/classes" org.computate.dcm.verticle.MainVerticle
