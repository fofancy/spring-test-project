FROM openjdk:8-jdk-alpine
VOLUME /tmp
EXPOSE 9090
ADD /build/libs/pictures-0.0.1-SNAPSHOT.jar pictures-0.0.1-SNAPSHOT.jar
ADD start.sh /start.sh
RUN chmod +x /start.sh
CMD ["sh", "/start.sh"]
