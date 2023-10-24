FROM eclipse-temurin:17-jdk-jammy as build-image
WORKDIR /app

COPY .mvn/ .mvn
COPY ./src/main/ ./src/main/
COPY mvnw pom.xml ./

FROM openjdk:11-jre-slim

FROM eclipse-temurin:17-jre-jammy

COPY --from=build-image /app/target/*.jar /app/app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
