with import <nixpkgs> {};
let 
  script = writeShellScriptBin "wiki-search" ''
    set -euo pipefail
    query="$${1}"
    ${curl}/bin/curl "https://en.wikipedia.org/w/rest.php/v1/search/page?q=$${query}&limit=1" --silent \
    | ${jq}/bin/jq '.'
  '';
in stdenv.mkDerivation rec {
  name = "wiki-search";
  buildInputs = [
    script
  ];
}
