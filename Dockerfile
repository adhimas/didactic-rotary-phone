# TODO: explore optimizations
FROM eclipse-temurin:21
WORKDIR /app
COPY . .
RUN ./gradlew bootJar --no-daemon
EXPOSE 8080
CMD ["java", "-jar", "/app/build/libs/demo-0.0.1-SNAPSHOT.jar"]
