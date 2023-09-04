#
# Build stage
#
FROM maven:3.9.0-eclipse-temurin-17 AS build
WORKDIR /app
# mvn spring-boot:build-image
COPY . .
RUN mvn clean package -DskipTests

#
# Package stage
#
# FROM eclipse-temurin:17-jdk-alpine
# COPY target/*.jar app.jar
# ENTRYPOINT ["java","-Dspring.profiles.active=production","-jar","/app.jar"]
# EXPOSE 8080
