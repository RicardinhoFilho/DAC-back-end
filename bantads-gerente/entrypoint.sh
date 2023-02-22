#!/bin/bash

./mvnw clean package -DskipTests
cp target/bantads-gerente-*.jar /app.jar
eval "$@"