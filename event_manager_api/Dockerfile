# Docker Build Maven Stage
FROM maven:3-openjdk-17 AS build
WORKDIR /opt/app
COPY ./ /opt/app
RUN mvn clean install -DskipTests

# Run spring boot in Docker
FROM openjdk:17-jdk
COPY --from=build /opt/app/target/event_manager_api-0.0.1-SNAPSHOT.jar event_manger_api.jar
ENV PORT 8080
EXPOSE $PORT
ENTRYPOINT ["java","-jar","-Dserver.port=${PORT}","event_manger_api.jar"]