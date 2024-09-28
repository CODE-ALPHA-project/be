FROM eclipse-temurin:21-jdk-alpine

# Set the working directory inside the container
WORKDIR /app

# Copy the Gradle build output (the JAR file) into the container
# Assuming that your JAR file is located at 'build/libs/'
ARG JAR_FILE=build/libs/resoft_be-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar

# # Copy application properties files into the container
# COPY src/main/resources/application.yml /app/
# COPY src/main/resources/application-prod.yml /app/
# COPY src/main/resources/application-local.yml /app/

# Expose the port that your Spring Boot application will run on
EXPOSE 8080

# Run the JAR file
ENTRYPOINT ["java", "-jar", "/app/app.jar"]