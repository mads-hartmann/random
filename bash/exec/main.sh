#!/bin/bash

# Experiment to see how signal handling works when you exec.
# All the signals are sent to the exec'd program it seems.

set -euo pipefail

echo "Running main with PID $$"

trap "sigterm" TERM
trap "sigint" INT
trap "sighup" HUP

sighup() {
    echo "Handling sighup"
    exit 0
}

sigint() {
    echo "Handling sigint"
    exit 0
}

sigterm() {
    echo "Handling sigterm"
    exit 0
}

exec ./busy.sh
