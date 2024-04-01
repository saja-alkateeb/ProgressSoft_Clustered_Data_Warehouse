FROM openjdk:8-alpine
VOLUME /tmp
EXPOSE 8080
ARG JAR_FILE=target/clustered-data-warehouse-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]