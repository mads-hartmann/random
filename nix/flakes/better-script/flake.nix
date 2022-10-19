{
  description = "A better version";

  outputs = { self, nixpkgs }: {
    defaultPackage.x86_64-linux = self.packages.x86_64-linux.my-script;

    packages.x86_64-linux.my-script =
      let
        pkgs = import nixpkgs { system = "x86_64-linux"; };

        my-name = "my-script";
        my-script = pkgs.writeShellScriptBin my-name ''
          DATE=$(ddate +'the %e of %B%, %Y')
          cowsay Hello, world! Today is $DATE.
        '';
        my-buildInputs = with pkgs; [ cowsay ddate ];
      in pkgs.symlinkJoin {
        name = my-name;
        paths = [ my-script ] ++ my-buildInputs;
        buildInputs = [ pkgs.makeWrapper ];
        postBuild = "wrapProgram $out/bin/${my-name} --prefix PATH : $out/bin";
      };
  };
}
