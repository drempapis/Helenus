#!/usr/bin/env bash

set -x

kill -kill $(cat cassandra.pid)
