FROM ubuntu:18.04.6 AS build

RUN apt-get update

FROM maven:3.6.3-jdk-11 AS build
RUN mkdir -p /workspace
WORKDIR /workspace
COPY pom.xml /workspace
COPY src /workspace/src

RUN mvn -f pom.xml clean package

FROM openjdk:11-jdk
COPY --from=build /workspace/target/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]









