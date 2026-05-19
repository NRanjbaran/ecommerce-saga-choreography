FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

# Copy the JAR file
COPY target/order-service-*.jar app.jar

# Expose port
EXPOSE 8045

# Run the service
ENTRYPOINT ["java", "-jar", "app.jar"]