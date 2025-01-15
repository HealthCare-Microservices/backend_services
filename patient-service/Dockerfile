# Start with a base image with Java runtime
FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the JAR file into the container
COPY target/*.jar app.jar

# Expose the port on which the service runs
EXPOSE 8200

# Command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
