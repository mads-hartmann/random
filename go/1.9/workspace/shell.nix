let
  nixpkgs = import (fetchTarball "https://github.com/NixOS/nixpkgs/archive/ee01de29d2f58d56b1be4ae24c24bd91c5380cea.tar.gz") { };
in
nixpkgs.mkShell {
  nativeBuildInputs = [
    nixpkgs.go_1_19
  ];

  shellHook = ''
  '';
}
