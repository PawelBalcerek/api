FROM openjdk:11-jdk-slim as BUILD

WORKDIR /build
COPY . /build
RUN chmod +x ./gradlew \
    ; ./gradlew bootJar

FROM openjdk:11-jre-slim

WORKDIR /app
COPY --from=build /build/build/libs/*.jar /app/app.jar
USER 1001

ENTRYPOINT ["java", "-jar", "app.jar"]