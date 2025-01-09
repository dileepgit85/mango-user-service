FROM adoptopenjdk/openjdk11:alpine-jre
WORKDIR /opt
COPY target/*.jar /opt/app.jar
LABEL authors="Dileep Mavidi"

ENTRYPOINT exec java $JAVA_OPTS -jar app.jar