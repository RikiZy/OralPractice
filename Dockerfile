# Use the official Gradle image with Java 17 to create a build artifact
FROM gradle:7.6-jdk17-alpine as builder

# Copy local code to the container image
WORKDIR /app
COPY build.gradle.kts .
COPY settings.gradle.kts .
COPY gradle.properties* ./
COPY src ./src

# Build a release artifact
RUN gradle build -x test --no-daemon

# Use a smaller runtime image
FROM openjdk:17-jdk-alpine

# Copy the built jar from builder stage
COPY --from=builder /app/build/libs/*.jar /app/oralpractice.jar

# Expose port 80 for cloud hosting
EXPOSE 80

# Run the web service on container startup
CMD ["java","-Djava.security.egd=file:/dev/./urandom","-Dserver.port=80","-jar","/app/oralpractice.jar"]

