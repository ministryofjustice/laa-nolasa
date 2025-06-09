FROM openjdk:21-jdk

VOLUME /tmp
ADD ./build/libs/nolasa.jar app.jar

ENTRYPOINT ["java","-jar","app.jar"]
