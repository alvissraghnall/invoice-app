#
# Build stage
#
FROM maven:3.8.2-eclipse-temurin-17 AS build
VOLUME /tmp
COPY . .
RUN mvn clean package -Pprod -DskipTests

#
# Package stage
#
FROM eclipse-temurin:17-jdk-alpine
COPY target/*.jar app.jar
ENTRYPOINT ["java","-Dspring.profiles.active=production","-jar","/app.jar"]
EXPOSE 8080
