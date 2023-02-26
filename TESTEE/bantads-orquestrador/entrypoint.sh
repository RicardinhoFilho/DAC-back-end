#!/bin/bash

./mvnw clean package -DskipTests
cp target/bantads-orquestrador-*.jar /app.jar
eval "$@"