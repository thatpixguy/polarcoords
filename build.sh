#!/bin/bash
INSTALL_DIR=$HOME/.minecraft/mods/macros/modules
MCP_DIR=$HOME/Projects/minecraft/mcp744

( 
  cd $MCP_DIR
  ./recompile.sh --client && 
  ./reobfuscate.sh --client && 
  jar cvf $INSTALL_DIR/module_polarcoords_api9.jar -C reobf/minecraft .
)
