Note: Should probably move to https://github.com/mads-hartmann/notion

Tiny script for fetching all unique sites stored in my URIs database.

```sh
nix develop
go run main.go | sort -n > sites.txt
```

## The data

To get the `data/uris-all.csv` file go to [URIs](https://www.notion.so/mads-hartmann/bfcfd0bbfc2e4c18a315cc9be3665019?v=7f35edafc11442d2bed36eaadfb1536b) and click the top-right '...' button and select export 'Markdown & CSV'