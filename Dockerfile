FROM amazoncorretto:21-alpine

VOLUME /tmp
ADD ./build/libs/nolasa.jar app.jar

ENTRYPOINT ["java","-jar","app.jar"]
