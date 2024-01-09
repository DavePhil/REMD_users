FROM openjdk:17-alpine
WORKDIR /app

# Install Spring Boot buildpacks
RUN apk add spring-boot-buildpacks

# Build application
RUN spring-boot:build-image --build-arg JAR_FILE=users.jar

# Expose port
EXPOSE 9003

# Run application
CMD ["java", "-jar", "users.jar"]