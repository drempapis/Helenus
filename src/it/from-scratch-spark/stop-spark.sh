#!/usr/bin/env bash

set -x

# stop Spark
./spark.sh stop

kill -kill $(cat cassandra.pid)
