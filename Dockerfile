FROM openjdk:18-jdk-alpine
ARG JAR_FILE=build/libs/microservices-0.0.1.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]