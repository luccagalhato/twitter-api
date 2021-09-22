FROM openjdk:8-jdk-alpine
COPY target/twitter-api-1.0.0.jar twitter-api-1.0.0.jar
ENTRYPOINT ["java","-jar","/twitter-api-1.0.0.jar"]