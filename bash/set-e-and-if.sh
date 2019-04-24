#!/usr/bin/env bash

set -e

echo "file-a" >> a.txt
echo "file-b" >> b.txt

if ! diff a.txt b.txt; then
    echo "They're different!"
    exit 1
fi

echo "done"
exit 0
