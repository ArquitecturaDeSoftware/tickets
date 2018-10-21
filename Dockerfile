FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD target/tickets.jar target/tickets.jar
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=local","target/tickets.jar"]
