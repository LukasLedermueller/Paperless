# Fetching latest version of Java
FROM amazoncorretto:17-alpine-jdk

# Set the working directory to /app
WORKDIR /app

# Install Maven (if not already installed)
RUN apk --no-cache add maven

# Copy the application source code into the container
COPY .. /app

# Build the application
RUN mvn package

# Expose the port that your application will run on (replace 8080 with your application's port if necessary)
EXPOSE 8081

# Define the command to run your Spring Boot application
CMD ["java", "-jar", "target/openapi-spring-1.0.jar"]