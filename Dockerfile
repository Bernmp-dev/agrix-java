FROM maven:3.6.3-jdk-11-slim AS build-image
WORKDIR /to-build-app
COPY pom.xml .
COPY src/ src/
RUN mvn dependency:go-offline && mvn package && rm -rf /root/.m2
FROM openjdk:11-jre-slim

FROM openjdk:11-jre-slim
RUN addgroup appgroup && adduser -S appuser -G appgroup
WORKDIR /app
USER appuser
COPY --from=build-image /to-build-app/target/*.jar ./app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
