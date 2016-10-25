#!/bin/sh
#
# Copyright (c) 2012-2015 Andrea Selva
# Modified by Kevin Berendsen to Throne it's needs.
#

echo "Throne Broker"

cd "$(dirname "$0")"

# resolve links - $0 may be a softlink
PRG="$0"

while [ -h "$PRG" ]; do
  ls=`ls -ld "$PRG"`
  link=`expr "$ls" : '.*-> \(.*\)$'`
  if expr "$link" : '/.*' > /dev/null; then
    PRG="$link"
  else
    PRG=`dirname "$PRG"`/"$link"
  fi
done

# Get standard environment variables
PRGDIR=`dirname "$PRG"`

# Only set THRONE_HOME if not already set
[ -f "$THRONE_HOME"/bin/throne-broker.sh ] || THRONE_HOME=`cd "$PRGDIR/.." ; pwd`
export THRONE_HOME

# Set JavaHome if it exists
if [ -f "${JAVA_HOME}/bin/java" ]; then
   JAVA=${JAVA_HOME}/bin/java
else
   JAVA=java
fi
export JAVA

LOG_FILE=$THRONE_HOME/config/throne-log.properties
THRONE_PATH=$THRONE_HOME/
#LOG_CONSOLE_LEVEL=info
#LOG_FILE_LEVEL=fine
JAVA_OPTS_SCRIPT="-XX:+HeapDumpOnOutOfMemoryError -Djava.awt.headless=true"

$JAVA -server $JAVA_OPTS $JAVA_OPTS_SCRIPT -Dlog4j.configuration="file:$LOG_FILE" -Dmoquette.path="$THRONE_PATH" -cp "$THRONE_HOME/lib/*" nl.kevinberendsen.throne.EmbeddedServer
