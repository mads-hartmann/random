let
  nixpkgs = import <nixpkgs> {};
in
nixpkgs.mkShell {
  nativeBuildInputs = [];

  shellHook = ''
    echo "Welcome to an empty shell"
  '';
}
