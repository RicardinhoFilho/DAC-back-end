#!/bin/bash

./mvnw clean package -DskipTests
cp target/bantads-conta-*.jar /app.jar
eval "$@"