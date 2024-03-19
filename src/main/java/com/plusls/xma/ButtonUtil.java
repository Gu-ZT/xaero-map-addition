package com.plusls.xma;

import com.plusls.ommc.feature.highlithtWaypoint.HighlightWaypointUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.ConfirmScreen;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import xaero.common.IXaeroMinimap;
import xaero.common.minimap.waypoints.Waypoint;
import xaero.common.minimap.waypoints.WaypointWorld;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.function.Supplier;

public class ButtonUtil {

    public static Button.OnPress getDirectDeleteButtonOnPress(
            Screen guiWaypoints, WaypointWorld displayedWorld, ConcurrentSkipListSet<Integer> selectedListSet,
            Supplier<ArrayList<Waypoint>> getSelectedWaypointsList, Runnable updateSortedList, IXaeroMinimap modMain) {
        return buttonWidget -> Objects.requireNonNull(Minecraft.getInstance()).setScreen(new ConfirmScreen(result -> {
            if (!result) {
                Minecraft.getInstance().setScreen(guiWaypoints);
                return;
            }
            for (Waypoint selected : getSelectedWaypointsList.get()) {
                displayedWorld.getCurrentSet().getList().remove(selected);
            }

            selectedListSet.clear();

            updateSortedList.run();

            try {
                modMain.getSettings().saveWaypoints(displayedWorld);
            } catch (IOException var5) {
                var5.printStackTrace();
            }
            Minecraft.getInstance().setScreen(guiWaypoints);
        }, ModInfo.translatable("gui.title.direct_delete"), ModInfo.translatable("gui.message.direct_delete")));
    }

    @Contract(pure = true)
    public static Button.@NotNull OnPress getHighlightButtonOnPress(Supplier<ArrayList<Waypoint>> getSelectedWaypointsList) {
        return buttonWidget -> {
            if (!getSelectedWaypointsList.get().isEmpty()) {
                Waypoint w = getSelectedWaypointsList.get().get(0);
                HighlightWaypointUtil.setHighlightPos(new BlockPos(w.getX(), w.getY(), w.getZ()), true);
            }
        };
    }
}
