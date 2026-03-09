# Use lightweight JRE runtime
FROM eclipse-temurin:17-jre-alpine

# Set working directory
WORKDIR /app

# Copy jar file
COPY target/Worker-0.0.1-SNAPSHOT.jar app.jar

# Expose application port
EXPOSE 8080

# Run the application (Corrected syntax)
ENTRYPOINT ["java", "-jar", "app.jar"]
