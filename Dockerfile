FROM openjdk:8-jdk-alpine
RUN apt-get install maven
RUN mvn -f pom.xml package
VOLUME /tmp
ADD target/tickets.jar target/tickets.jar
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=local","target/tickets.jar"]
