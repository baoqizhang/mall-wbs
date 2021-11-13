FROM openjdk:11-jdk-slim

ADD ./build/libs/mall-wbs-1.0.0.jar /opt/app.jar
COPY docker-entrypoint.sh /opt/docker-entrypoint.sh

EXPOSE 8080

ENTRYPOINT ["/bin/sh", "/opt/docker-entrypoint.sh"]
