FROM ubuntu:latest AS build

RUN apt-get update
RUN apt-get install openjdk-17-jdk-y
COPY . .

RUN apt-get install maven -y
RUN mvn clean install

FROM maven:3.6.3-jdk-11 AS final
RUN mkdir -p /workspace
WORKDIR /workspace
COPY --from=build /workspace/target/*.jar /workspace/app.jar

FROM openjdk:11-jdk
COPY --from=final /workspace/app.jar /app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]
