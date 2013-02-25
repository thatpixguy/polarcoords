#!/bin/bash
javac -source 1.4 -cp $HOME/.minecraft/mods/mod_macros_0.9.8_for_1.4.7.litemod:$HOME/.minecraft/bin/minecraft.jar $(find -type f -name "*.java")
jar cf $HOME/.minecraft/mods/macros/modules/module_polarcoords.jar $(find -type f -name "*.class")

