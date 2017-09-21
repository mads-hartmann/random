#!/usr/bin/env bash
#
# Expriment that uses Here Strings as input to a function.

set -euo pipefail

function save_to_file {
    local file=$1
    echo "$(< /dev/stdin)" > ${file}
}


save_to_file test.json <<EOF
{
    "key1": {
        "value": "foobar"
    }
}
EOF
