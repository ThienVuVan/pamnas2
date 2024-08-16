#!/bin/sh
echo "Starting the container with Xmx=${MAX_HEAP} on env=${ENV}"
exec java \
-Denv=${ENV} \
-Xmx${MAX_HEAP} \
-jar /app/app.jar