# Этап сборки
FROM maven:3.8.4-openjdk-17 AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package

# Этап запуска
FROM eclipse-temurin:17-jdk-alpine
ARG jar_FILE=/home/app/target/*.jar
COPY --from=build /home/app/target/*jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]