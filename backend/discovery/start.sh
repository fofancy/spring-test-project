#!/bin/bash
while ! nc -z config 8088; do sleep 3; done

java -Djava.security.egd=file:/dev/./urandom -jar /discovery-0.0.1-SNAPSHOT.jar
