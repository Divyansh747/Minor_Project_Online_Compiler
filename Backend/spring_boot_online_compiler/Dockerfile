FROM openjdk:8-jre-alpine
RUN apk update && apk add bash
#RUN apk add openjdk8
RUN apk add --update docker openrc
RUN rc-update add docker boot
VOLUME /tmp
WORKDIR /opt
ADD . .
#ARG JAR_FILE=target/spring_boot_online_compiler-0.0.1-SNAPSHOT.jar
#ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","target/spring_boot_online_compiler-0.0.1-SNAPSHOT.jar"]
