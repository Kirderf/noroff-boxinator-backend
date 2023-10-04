# Use the official OpenJDK 17 base image
FROM openjdk:17-jdk-slim
# Metadata (optional)

# Specify the working directory
WORKDIR /app
# Copy the built JAR from your Gradle build to the image
# Assume the jar file is in the build/libs directory and is named 'app.jar'
COPY build/libs/app.jar app.jar
# The application will listen on port 8080 by default for Spring Boot applications
EXPOSE 8080
# Command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]