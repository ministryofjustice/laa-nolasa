FROM openjdk:8-jdk-alpine

VOLUME /tmp
ADD ./build/libs/nolasa.jar app.jar

ENTRYPOINT ["java","-jar","app.jar"]
