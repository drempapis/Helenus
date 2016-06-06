#!/usr/bin/env bash

set -e
set -x

# Starts a Cassandra DB.

export CASSANDRA_ADDRESS="127.0.0.1"
CASSANDRA_DATA_DIR="cassandra-data"
mkdir -p "${CASSANDRA_DATA_DIR}"
CASSANDRA_PORT=9042
java  -Dcassandra.config="file://${PWD}/../conf/cassandra.yaml" \
    -Dcassandra.storagedir="${CASSANDRA_DATA_DIR}" -cp '../../../target/pds-it-1.0.0-SNAPSHOT-jar-with-dependencies.jar:*' \
    org.apache.cassandra.service.CassandraDaemon &
echo $! > "cassandra.pid"

#WAIT FOR CASSANDRA
while ! nc -z ${CASSANDRA_ADDRESS} ${CASSANDRA_PORT}; do
  sleep 0.1
done
