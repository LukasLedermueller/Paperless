# BUILD
FROM maven:3.9.4-amazoncorretto-17 AS build

# Copy the application source code and pom file into the container
COPY src /home/app/src
COPY pom.xml /home/app

# Build the application
RUN mvn -f /home/app/pom.xml clean package

# RUN
FROM amazoncorretto:17-alpine-jdk

# copy jar file
COPY --from=build /home/app/target/paperless-rest-1.0.jar /usr/local/lib/paperless-rest.jar

# Expose the port that your application will run on
EXPOSE 8080

# Define the command to run your Spring Boot application
ENTRYPOINT ["java", "-jar", "/usr/local/lib/paperless-rest.jar"]