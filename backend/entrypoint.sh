#!/bin/sh
set -e
: "${PORT:=10000}"
echo "Starting PetStore backend on port ${PORT}"
exec java -Dserver.address=0.0.0.0 -Dserver.port="${PORT}" -jar /app/app.jar
