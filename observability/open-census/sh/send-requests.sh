#!/usr/bin/env bash

set -euo pipefail

for i in {0..20}
do
    echo "Reuqest $i"
    curl http://localhost:8080
    sleep 1
done
