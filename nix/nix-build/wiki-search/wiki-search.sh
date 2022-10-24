#!/usr/bin/env bash
set -euo pipefail

url="https://en.wikipedia.org/w/rest.php/v1/search/page?q=${1}&limit=1"
curl "${url}" --silent | jq '.'
