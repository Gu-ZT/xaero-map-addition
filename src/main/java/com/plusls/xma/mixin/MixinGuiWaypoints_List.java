package com.plusls.xma.mixin;

import com.plusls.ommc.feature.highlithtWaypoint.HighlightWaypointUtil;
import com.plusls.xma.RenderWaypointUtil;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xaero.common.minimap.waypoints.Waypoint;

@Mixin(targets = "xaero.common.gui.GuiWaypoints$List", remap = false)
public class MixinGuiWaypoints_List {
    @Inject(method = "drawWaypointSlot", at = @At(value = "RETURN"))
    private void drawHighlightWaypoint(MatrixStack matrixStack, Waypoint w, int x, int y, CallbackInfo ci) {
        if (w == null) {
            return;
        }
        BlockPos pos = new BlockPos(w.getX(), w.getY(), w.getZ());
        if (pos.equals(HighlightWaypointUtil.highlightPos)) {
            matrixStack.push();
            matrixStack.translate(x + 200, y + 7, 1.0D);
            RenderWaypointUtil.drawHighlightWaypointPTC(matrixStack);
            matrixStack.pop();
        }
    }
}
