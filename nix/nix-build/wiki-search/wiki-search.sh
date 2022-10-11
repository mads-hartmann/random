#!/usr/bin/env bash

set -euo pipefail
query="${1}"
curl "https://en.wikipedia.org/w/rest.php/v1/search/page?q=${query}&limit=1" --silent \
| jq '.'
