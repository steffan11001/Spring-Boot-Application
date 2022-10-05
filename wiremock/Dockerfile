FROM anapsix/alpine-java:8
ARG JAR_FILE=*.jar
ADD mappings mappings
ADD __files __files
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]