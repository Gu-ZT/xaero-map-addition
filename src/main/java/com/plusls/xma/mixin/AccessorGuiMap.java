package com.plusls.xma.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import top.hendrixshen.magiclib.dependency.api.annotation.Dependencies;
import top.hendrixshen.magiclib.dependency.api.annotation.Dependency;
import xaero.map.MapProcessor;
import xaero.map.gui.GuiMap;

@Dependencies(and = @Dependency("xaeroworldmap"))
@Mixin(value = GuiMap.class, remap = false)
public interface AccessorGuiMap {
    @Accessor
    int getMouseBlockPosX();

    @Accessor
    int getMouseBlockPosY();

    @Accessor
    int getMouseBlockPosZ();

    @Accessor
    MapProcessor getMapProcessor();
}
