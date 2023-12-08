FROM openjdk:17-jdk-slim as builder

ARG JAR_FILE=../target/*.jar
COPY ${JAR_FILE} recipe-api.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/recipe-api.jar"]