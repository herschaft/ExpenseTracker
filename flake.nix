{
  description = "Expenses Tracker";

  inputs.nixpkgs.url = "github:NixOS/nixpkgs/nixpkgs-unstable";

  outputs = { nixpkgs, ... }:
    let
      system = "x86_64-linux";
      pkgs = nixpkgs.legacyPackages.${system};
    in {
      devShells.${system}.default = pkgs.mkShell {
        packages = with pkgs; [
          jdk25
          gradle_9
          git
          gh
        ];

        JAVA_HOME = "${pkgs.jdk25}/";

        shellHook = ''
          export PATH="$JAVA_HOME/bin:$PATH"
        '';
      };
    };
}
