with import <nixpkgs> {};
let 
  my-name = "wiki-search";
  my-src = builtins.readFile ./wiki-search.sh;
  my-script = (pkgs.writeScriptBin my-name my-src).overrideAttrs(old: {
    buildCommand = "${old.buildCommand}\n patchShebangs $out";
  });
  my-build-inputs = with pkgs; [ jq curl ];
in pkgs.symlinkJoin {
  name = my-name;
  paths = [ my-script ] ++ my-build-inputs;
  buildInputs = [
    makeWrapper
  ];
  postBuild = "wrapProgram $out/bin/${my-name} --prefix PATH : $out/bin";
}
