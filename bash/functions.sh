#!/usr/bin/env bash

set -euo pipefail

function invoke-unknown-command {
    local filename="$1"
    iconv -f ISO-8859-1 -t UTF-8 /media/ebs/data/MF6892/upload/${filename} | dos2unix | base64
}

function main {
    for f in a b c; do
        echo "Iteration ${f}"
        contents=$(invoke-unknown-command ${f})
        echo ${contents}
    done
}

main $@
