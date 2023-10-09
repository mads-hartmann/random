{
  inputs = {
    nixpkgs.url = "github:nixos/nixpkgs";
    flake-utils.url = "github:numtide/flake-utils";
  };

  outputs = { self, nixpkgs, flake-utils }:
    flake-utils.lib.eachDefaultSystem (system:
      let pkgs = nixpkgs.legacyPackages.${system};
      in {
        # TODO: Can I make the default a no-op?
        packages.default = pkgs.hello;
        devShell = pkgs.mkShell { 
            buildInputs = [ 
                pkgs.go
                pkgs.gopls
                pkgs.gotools
                pkgs.go-tools
                # TODO: Remove these
                pkgs.hello
            ]; 
        };
      });
}