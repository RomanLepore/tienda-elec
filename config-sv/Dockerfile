FROM openjdk:17-jdk-slim

WORKDIR /root

COPY ./pom.xml /root
COPY ../.mvn /root/.mvn
COPY ./mvnw /root

RUN ./mvnw dependency:go-offline

COPY ./src /root/src

RUN ./mvnw clean install -DskipTests


ENTRYPOINT ["java","-jar","/root/target/config-sv-0.0.1.jar"]