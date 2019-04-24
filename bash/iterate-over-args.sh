#!/usr/bin/env bash
#
# Experiment to see if $1 changes value after each shift.
# it does.
#

while (($#)); do
    echo "\$1 is now $1"
    shift
done
