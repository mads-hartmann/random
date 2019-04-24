#!/usr/bin/env bash

set -euo pipefail

WHAT="what"

function finish() {
    echo "I'm trapping an ERR"
    exit 42
}

trap "finish" ERR

git asdfaf

echo "Variable exists: $WHAT"
echo "$NO_EXIST"


echo "Im done now"
