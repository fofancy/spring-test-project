FROM openjdk:8-jdk-alpine
VOLUME /tmp
EXPOSE 8061
ADD /target/discovery-0.0.1-SNAPSHOT.jar discovery-0.0.1-SNAPSHOT.jar
ADD start.sh /start.sh
RUN chmod +x /start.sh
CMD ["sh", "/start.sh"]
