# Use a base image that contains OpenJDK
FROM maven:3.9.4-eclipse-temurin-21-alpine

# Set the working directory in the container
WORKDIR /app

# Copy the JAR file into the container
COPY target/application-0.0.1-SNAPSHOT.jar .

# Expose the port the application will run on
EXPOSE 8080

# Command to run the Spring Boot application
ENTRYPOINT ["java", "-jar", "application-0.0.1-SNAPSHOT.jar"]