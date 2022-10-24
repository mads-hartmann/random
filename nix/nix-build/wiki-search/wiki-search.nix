with import <nixpkgs> {};
let 
  script = rec {
    name = "wiki-search";
    src = builtins.readFile ./wiki-search.sh;
    runtimeInputs = [ pkgs.jq pkgs.curl ];
    bin = (pkgs.writeScriptBin name src).overrideAttrs(old: {
      buildCommand = "${old.buildCommand}\n patchShebangs $out";
    });
  };
in pkgs.symlinkJoin {
  name = script.name;
  paths = [ script.bin ] ++ script.runtimeInputs;
  buildInputs = [ makeWrapper ];
  postBuild = "wrapProgram $out/bin/${script.name} --prefix PATH : $out/bin";
}
