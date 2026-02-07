FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app
COPY build/libs/*.jar mvest-asset.jar
EXPOSE 9002
CMD ["java", "-jar", "mvest-asset.jar"]