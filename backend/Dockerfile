# Use a Maven builder image to compile the Spring Boot app
FROM maven:3.9.8-eclipse-temurin-17 AS build
WORKDIR /workspace

COPY pom.xml .
COPY .mvn .mvn
COPY src ./src

RUN mvn -B package -DskipTests

# Runtime image
FROM eclipse-temurin:17-jre-jammy
WORKDIR /app

COPY --from=build /workspace/target/petstore-backend-0.0.1-SNAPSHOT.jar app.jar
ENV PORT=10000
EXPOSE 10000
ENTRYPOINT ["sh", "-c", "exec java -Dserver.address=0.0.0.0 -Dserver.port=${PORT} -jar /app/app.jar"]
