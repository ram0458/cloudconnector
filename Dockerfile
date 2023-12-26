FROM maven:3.6.3-jdk-11-slim AS MAVEN_BUILD
MAINTAINER Phaneendra Satyavolu
COPY pom.xml /app/
COPY src /app/src
# Install the local JAR into your local Maven repository

COPY lib /app/lib

RUN mvn install:install-file -Dfile=/app/lib/json-ordered-1.0.jar -DgroupId=com.json -DartifactId=json-ordered -Dversion=1.0 -Dpackaging=jar
RUN mvn -f /app/pom.xml clean package -e -DskipTests

FROM openjdk:11-jre-slim
COPY --from=MAVEN_BUILD /app/target/cloud-connector-0.0.1-SNAPSHOT.jar /app/
RUN apt-get update
RUN apt-get install net-tools
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=dev", "app/cloud-connector-0.0.1-SNAPSHOT.jar"]
EXPOSE 7000
