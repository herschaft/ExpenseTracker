{
  description = "Expenses Tracker";

  inputs.nixpkgs.url = "github:NixOS/nixpkgs/nixpkgs-unstable";

  outputs = { nixpkgs, ... }:
    let
      system = "x86_64-linux";
      pkgs = nixpkgs.legacyPackages.${system};
    in {
      devShells.${system}.default = pkgs.mkShell {
        packages = [
          pkgs.jdk25
          pkgs.gradle_9
	        pkgs.git
          pkgs.gh
        ];
        shellHook = ''
          export JAVA_HOME="${pkgs.jdk25}/lib/openjdk"
          '';
      };
    };
}
