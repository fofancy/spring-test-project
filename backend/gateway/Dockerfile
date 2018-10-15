FROM openjdk:8-jdk-alpine
VOLUME /tmp
EXPOSE 8085
ADD /target/gateway-0.0.1-SNAPSHOT.jar gateway-0.0.1-SNAPSHOT.jar
ADD start.sh /start.sh
RUN chmod +x /start.sh
CMD ["sh", "/start.sh"]
