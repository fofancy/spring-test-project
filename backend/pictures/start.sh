#!/bin/bash
while ! nc -z gateway 8085; do sleep 3; done

java -Djava.security.egd=file:/dev/./urandom -jar /pictures-0.0.1-SNAPSHOT.jar
