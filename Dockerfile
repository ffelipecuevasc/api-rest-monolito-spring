FROM alpine/java:21-jdk

COPY application/target/api-producto.application-0.0.1-SNAPSHOT.jar /api-producto.jar

ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=dev,infra_dev", "/api-producto.jar"]