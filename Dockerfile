# Start with a base image containing Java runtime
FROM openjdk:17-jdk-slim

# Add the application's JAR file to the container
ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} app.jar

# Run the JAR file 
ENTRYPOINT ["java","-jar","/app.jar"]
