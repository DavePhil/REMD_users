FROM openjdk:17-alpine
WORKDIR /app
COPY target/remd_users.jar /app
CMD ["java","-jar","remd_users.jar"]