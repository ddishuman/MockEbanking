FROM openjdk:17-alpine
ADD target/payment-0.0.1-SNAPSHOT.jar payment-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "payment-0.0.1-SNAPSHOT.jar"]