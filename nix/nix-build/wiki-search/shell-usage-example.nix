
let
  nixpkgs = import <nixpkgs> {};
  repo = builtins.fetchGit {
    url="https://github.com/mads-hartmann/random.git";
    rev="89ba6f5ca09981be392335648171436758b77e22";
    shallow = true;
  };
  wiki-search = import "${repo}/nix/nix-build/wiki-search/wiki-search.nix";
in
nixpkgs.mkShell {
  nativeBuildInputs = [
    wiki-search
  ];

  shellHook = ''
  '';
}
