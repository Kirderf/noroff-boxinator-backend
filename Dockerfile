FROM gradle:jdk17 as build
WORKDIR /app
COPY . .
RUN gradle bootJar

FROM openjdk:17 AS runtime
WORKDIR /app
ARG JAR_FILE=/app/build/libs/*.jar
COPY --from=build ${JAR_FILE} /app/app.jar
ENV HIBERNATE_DDL "none"
ENV DB_SEED "never"
ENV SPRING_PROFILE "prod"
ENV LOGGING_LEVEL "INFO"
ENV SHOW_SQL "false"
EXPOSE 8080


ENTRYPOINT ["java", "-jar", "app.jar"]
