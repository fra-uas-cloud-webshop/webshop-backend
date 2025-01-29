# Use a base image with Java
FROM maven:3.9.0-eclipse-temurin-17 AS build

WORKDIR /app

# Copy the Maven project files
COPY pom.xml ./
COPY src ./src

# Build the Spring Boot application
RUN mvn clean package -DskipTests

# Production stage: Run the application
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app

# Copy the built JAR from the build stage
COPY --from=build /app/target/*.jar app.jar

# Expose the application port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]