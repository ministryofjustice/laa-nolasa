FROM openjdk:8-jdk-alpine

VOLUME /tmp
ADD build/libs/laa-nolasa-0.0.1-SNAPSHOT.war app.war

ENTRYPOINT ["java","-jar","app.war"]