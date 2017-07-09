#!/bin/bash

set -u
set -e
set -o pipefail

function is-even {
    if [[ $(($1 % 2)) -gt 0 ]]
    then return 1
    else return 0
    fi
}

function epoch {
    date +"%s"
}

if is-even $(epoch)
then echo "Even epoch"
else echo "Odd epoch"
fi
