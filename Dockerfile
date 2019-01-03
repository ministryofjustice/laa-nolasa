FROM openjdk:8-jdk-alpine

VOLUME /tmp
ADD build/libs/nolasa-0.1.0.jar app.jar

ENTRYPOINT ["java","-jar","app.jar"]
