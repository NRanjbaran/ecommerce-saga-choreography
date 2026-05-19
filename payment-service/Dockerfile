FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

COPY target/payment-service-*.jar app.jar

EXPOSE 8046

ENTRYPOINT ["java", "-jar", "app.jar"]