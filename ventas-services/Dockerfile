FROM openjdk:17-jdk-slim
ARG JAR_FILE=target/ventas-services-0.0.1.jar
COPY ${JAR_FILE} app-productosv.jar
ENTRYPOINT ["java","-jar","app-productosv.jar"]