#!/usr/bin/env bash
#
# Expriment that uses Here Strings as input to a function.

set -euo pipefail

function save_to_file {
    local file=$1
    echo "Saving contents to file ${file}"
    echo "$(< /dev/stdin)" > ${file}
}

example=$(cat <<EOF
You can assign heredoc texts to variables by using cat in a sub-shell.
Sometimes that can be convenient.
EOF
)

echo ${example}

save_to_file test.json <<EOF
{
    "key1": {
        "value": "foobar"
    }
}
EOF
