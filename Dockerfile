FROM gradle:jdk17 as build
WORKDIR /app
COPY . .
RUN gradle bootJar

FROM openjdk:17 AS runtime
WORKDIR /app
ARG JAR_FILE=build/libs/*.jar
COPY --from=build ${JAR_FILE} /app/app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]