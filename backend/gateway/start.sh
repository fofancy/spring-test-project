#!/bin/bash
while ! nc -z discovery 8061; do sleep 3; done

java -Djava.security.egd=file:/dev/./urandom -jar /gateway-0.0.1-SNAPSHOT.jar
