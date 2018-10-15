FROM openjdk:8-jdk-alpine
VOLUME /tmp
EXPOSE 8088
ADD /build/libs/config-0.0.1-SNAPSHOT.jar config-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/config-0.0.1-SNAPSHOT.jar"]
