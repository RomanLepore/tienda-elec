FROM openjdk:17-jdk-slim
ARG JAR_FILE=target/config-sv-0.0.1.jar
COPY ${JAR_FILE} app-configsv.jar
ENTRYPOINT ["java","-jar","app-configsv.jar"]