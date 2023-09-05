#
# Build stage
#
FROM maven:3.9.0-eclipse-temurin-17 AS BUILD
WORKDIR /app

# COPY . .
# RUN mvn clean package -DskipTests


# Copy the Maven project file(s) and download dependencies
COPY pom.xml .
RUN mvn clean verify --fail-never

# Copy the application source code and build the JAR
COPY src src
RUN mvn package -DskipTests
# mvn spring-boot:build-image

#
# Package stage
#
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app

# Copy the JAR from the "Build stage"
COPY --from=BUILD /app/target/*.jar app.jar

ENTRYPOINT ["java","-Dspring.profiles.active=production","-jar","/app.jar"]
EXPOSE 8080
