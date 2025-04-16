FROM eclipse-temurin:21-jdk-alpine AS build
WORKDIR /app

COPY . .

RUN ./gradlew build -x test --no-daemon

FROM eclipse-temurin:21-jdk-alpine AS runtime
WORKDIR /app

COPY --from=build /app/build/libs/*.jar app.jar

EXPOSE 8080

# Comando de entrada
ENTRYPOINT ["java", "-jar", "app.jar"]
