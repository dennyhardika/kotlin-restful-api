FROM openjdk:21-jdk

COPY build/libs/kotlin-restful-api-0.0.1-SNAPSHOT.jar /app/application.jar

EXPOSE 8080

CMD ["java", "-jar", "/app/application.jar"]