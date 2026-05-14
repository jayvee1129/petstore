#!/bin/sh
set -e
: "${PORT:=10000}"
exec java -Dserver.address=0.0.0.0 -Dserver.port="${PORT}" -jar /app/app.jar
