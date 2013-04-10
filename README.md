polarcoords
===========

my 3d-enhanced version of http://eq2.co.uk/minecraft/mods/modules/api8/module_polarcoords.jar

BUILDING:

- Modify the MCP_DIR and INSTALL_DIR paths in build.sh
  (INSTALL_DIR is probably right)

- Symlink the source into MCP_DIR/src
  eg. ln -sf $PWD/src/* ~/Projects/minecraft/mcp744/src/minecraft/

- Copy the module into MCP_DIR/libs and rename to .jar
  eg. cp $HOME/.minecraft/mods/mod_macros_0.9.8.2_for_1.5.1.litemod $MCP_DIR/libs/mod_macros.jar
