
let
  nixpkgs = import <nixpkgs> {};
  repo = builtins.fetchGit {
    url="https://github.com/mads-hartmann/random.git";
    rev="74f52ea4ec592cfacc09fed7d205bcf1567a1bae";
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
