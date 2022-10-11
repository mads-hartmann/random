{ pkgs ? import (fetchTarball "https://github.com/NixOS/nixpkgs/archive/e179d1e57ad07f1294dcc29ad5283b214a6ae21e.tar.gz") {}
}:
pkgs.stdenv.mkDerivation rec {
  pname = "wiki-search";
  version = "0.0.1";

  buildInputs = [
    pkgs.jq
  ];

  configurePhase = ''
  '';

  buildPhase = ''
  '';

  installPhase = ''
    mkdir -p $out/bin
    mv wiki-search.sh $out/bin/wiki-search
  '';
}
