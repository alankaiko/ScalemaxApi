FROM ubuntu:latest AS build

RUN apt-get update
RUN apt-get install openjdk-17-jdk-y
COPY . .

RUN apt-get install maven -y
RUN mvn clean install

FROM maven:3.6.3-jdk-11 AS build
RUN mkdir -p /workspace
WORKDIR /workspace
COPY pom.xml /workspace
COPY src /workspace/src

RUN mvn -f pom.xml clean package
FROM openjdk:11-jdk
COPY --from=build /workspace/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]









