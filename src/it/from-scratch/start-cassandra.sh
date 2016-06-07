#!/usr/bin/env bash

set -e

red="\033[01;31m"
green="\033[01;32m"
yellow="\033[01;35m"
normal="\033[00;00m"


banner() {
    color=\$${1:-normal}
    # activate color passed as argument
    echo -ne "$(eval echo ${color})"
    echo -n "$2"
    echo -e "${normal}"
}

# Starts a Cassandra DB.

banner green "Starting Cassandra"

export CASSANDRA_ADDRESS="127.0.0.1"
CASSANDRA_DATA_DIR="cassandra-data"
mkdir -p "${CASSANDRA_DATA_DIR}"
CASSANDRA_PORT=9042
java  -Dcassandra.config="file:///${PWD}/src/main/resources/conf/cassandra.yaml" \
    -Dcassandra.storagedir="${CASSANDRA_DATA_DIR}" -cp 'target/Helenus-From-Scratch-1.0-SNAPSHOT-jar-with-dependencies.jar:*' \
    org.apache.cassandra.service.CassandraDaemon &
echo $! > "cassandra.pid"

#WAIT FOR CASSANDRA
while ! nc -z ${CASSANDRA_ADDRESS} ${CASSANDRA_PORT}; do
  sleep 1
  banner yellow "Waiting for Cassandra to Start ..."
done

java -cp 'target/Helenus-From-Scratch-1.0-SNAPSHOT-jar-with-dependencies.jar:*' \
   com.proemion.test.Main "${CASSANDRA_PORT}"
