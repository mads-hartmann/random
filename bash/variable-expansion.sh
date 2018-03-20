#!/usr/bin/env bash

set -euo pipefail

if [[ -z "${1: }" ]]; then
    echo "case 1"
fi

if [[ -z "${1:- }" ]];
then
    echo "case 2"
fi

if [[ -z "${1:0}" ]]; then
    echo "case 2"
fi
