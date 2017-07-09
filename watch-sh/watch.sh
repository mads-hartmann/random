#!/bin/bash
#
# Mainly for OCaml programs.
#
# Usage: watch.sh <BIN>
#

set -euo pipefail

PROG=$1

function start {
    $PROG &
    PID=$!
    echo "Startet ${PROG} ${PID}"
}

function stop {
    kill $PID
    echo "killed ${PID}"
}

trap "stop; exit" SIGINT SIGTERM

start

fswatch $PROG | while read line; do
    stop
    start
done
