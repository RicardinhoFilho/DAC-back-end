#!/bin/bash

./mvnw clean package -DskipTests
cp target/bantads-autenticacao-*.jar /app.jar
eval "$@"