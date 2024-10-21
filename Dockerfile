# Fase 1: Construcción
FROM maven:3.8.4-openjdk-17 AS build
WORKDIR /app
COPY ./pom.xml ./
COPY ./src ./src
RUN mvn clean package -DskipTests

# Fase 2: Imagen Final
FROM openjdk:17.0.2
WORKDIR /app
COPY --from=build /app/target/api-user-0.0.1-SNAPSHOT.jar ./api-user.jar
EXPOSE 8090
ENTRYPOINT ["java", "-jar", "api-user.jar"]
