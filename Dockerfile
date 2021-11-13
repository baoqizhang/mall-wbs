FROM openjdk:11-jdk-slim

ARG jarName
COPY $jarName /opt/app.jar
COPY docker-entrypoint.sh /opt/docker-entrypoint.sh

EXPOSE 8080

ENTRYPOINT ["/bin/sh", "/opt/docker-entrypoint.sh"]
