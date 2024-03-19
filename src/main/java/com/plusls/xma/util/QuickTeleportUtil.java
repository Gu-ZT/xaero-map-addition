package com.plusls.xma.util;

import com.plusls.xma.config.Configs;
import com.plusls.xma.mixin.AccessorGuiMap;
import fi.dy.masa.malilib.hotkeys.IKeybind;
import fi.dy.masa.malilib.hotkeys.KeyAction;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.sounds.SoundEvents;
import top.hendrixshen.magiclib.util.InfoUtil;
import xaero.map.gui.GuiMap;
import xaero.map.world.MapDimension;

public class QuickTeleportUtil {
    public static boolean teleport(KeyAction keyAction, IKeybind iKeybind) {
        if (!(Minecraft.getInstance().screen instanceof GuiMap)) {
            return false;
        }

        if (Minecraft.getInstance().player == null) {
            return false;
        }
        
        GuiMap guiMap = ((GuiMap) Minecraft.getInstance().screen);
        MapDimension currentDimension = ((AccessorGuiMap) guiMap).getMapProcessor().getMapWorld().getCurrentDimension();

        // Disable teleport feature in survival or current world un-writeable.
        if (Minecraft.getInstance().gameMode == null || Minecraft.getInstance().gameMode.canHurtPlayer()
                || currentDimension == null || !currentDimension.currentMultiworldWritable
        ) {
            return false;
        }

        int mouseBlockPosX = ((AccessorGuiMap) guiMap).getMouseBlockPosX();
        int mouseBlockPosY = ((AccessorGuiMap) guiMap).getMouseBlockPosY();
        int mouseBlockPosZ = ((AccessorGuiMap) guiMap).getMouseBlockPosZ();

        //#if MC > 11502
        // Disable teleport feature if xaero map denied or mouseBlockPos cannot get.
        if (!((AccessorGuiMap) guiMap).getMapProcessor().getMapWorld().isTeleportAllowed()) {
            return false;
        }
        //#endif
        InfoUtil.sendCommand(String.format("tp @s %s %s %s", mouseBlockPosX, mouseBlockPosY == 32767 ?
                Minecraft.getInstance().player.getBlockYCompat() : mouseBlockPosY, mouseBlockPosZ));
        Minecraft.getInstance().getSoundManager()
                .play(SimpleSoundInstance.forUI(SoundEvents.CHORUS_FRUIT_TELEPORT, 1.0F));

        if (Configs.closeMapAfterQuickTeleport) {
            guiMap.onClose();
            return true;
        }

        return false;
    }
}
