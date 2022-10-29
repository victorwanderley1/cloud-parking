FROM maven:3.8.6-eclipse-temurin-11 AS builder
WORKDIR /application
COPY pom.xml /application/pom.xml
RUN mvn dependency:go-offline

COPY src /application/src
COPY system.properties /application/system.properties
RUN mvn install -Dmaven.test.skip=true

ENTRYPOINT ["mvn", "spring-boot:run"]