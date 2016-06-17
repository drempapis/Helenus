#!/usr/bin/env bash

SPARK_HOME=../../../target/spark/spark-1.6.1-bin-hadoop2.6

if [ "$#" -ne 1 ]
then
    echo "Illegal number of parameters"
else
  if [ $1 == "start" ]
  then
    ${SPARK_HOME}/sbin/start-master.sh
    ${SPARK_HOME}/sbin/start-slaves.sh
  elif [ $1 == "stop" ]
  then
    ${SPARK_HOME}/sbin/stop-slaves.sh
    ${SPARK_HOME}/sbin/stop-master.sh
  else
    echo "Unknown option: $1"
    echo "Valid options are: start or stop"
  fi
fi
