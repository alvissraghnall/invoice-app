#
# Build stage
#
FROM maven:3.9.0-eclipse-temurin-17 AS BUILD
WORKDIR /app
# COPY . .
# RUN mvn clean package -DskipTests
# Copy the Maven project file(s) and download dependencies
COPY pom.xml .
# Copy the application source code and build the JAR
COPY src src
RUN mvn clean package -DskipTests
#RUN mvn package -DskipTests
# mvn spring-boot:build-image

#
# Package stage
#
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
# Copy the JAR from the "Build stage"
COPY --from=BUILD /app/target/*.jar app.jar
# Expose port 8080
EXPOSE 8080
ENTRYPOINT ["java","-Dspring.profiles.active=production","-jar","app.jar"]

