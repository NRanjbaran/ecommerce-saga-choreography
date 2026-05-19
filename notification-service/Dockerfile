FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

# Copy the JAR file
COPY target/notification-service-*.jar app.jar

# Expose port
EXPOSE 8048

# Run the service
ENTRYPOINT ["java", "-jar", "app.jar"]