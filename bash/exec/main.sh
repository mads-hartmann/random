#!/bin/bash

# Experiment to see how signal handling works when you exec.
# All the signals are sent to the exec'd program it seems.

set -euo pipefail

echo "Running main with PID $$"

trap handle_sigterm TERM

handle_sigterm() {
    echo "Handling TERM $@"
    exit 0
}

exec ./busy.sh
