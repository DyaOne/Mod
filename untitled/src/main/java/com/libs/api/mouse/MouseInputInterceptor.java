package com.libs.api.mouse;

import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.lwjgl.glfw.GLFW;

@Mod.EventBusSubscriber(value = Dist.CLIENT)
public class MouseInputInterceptor {

    @SubscribeEvent
    public static void onMouseClick(InputEvent.MouseInputEvent event) {
        if (event.getButton() == 0) {
            MouseAPI.setLeftPressed(event.getAction() == GLFW.GLFW_PRESS);
        }
    }

    @SubscribeEvent
    public static void onRawMouseMove(InputEvent.RawMouseEvent event) {
        double x = Minecraft.getInstance().mouseHandler.xpos() * Minecraft.getInstance().getWindow().getGuiScaledWidth()
                / (double) Minecraft.getInstance().getWindow().getScreenWidth();
        double y = Minecraft.getInstance().mouseHandler.ypos() * Minecraft.getInstance().getWindow().getGuiScaledHeight()
                / (double) Minecraft.getInstance().getWindow().getScreenHeight();
        MouseAPI.updateMouse(x, y);
    }
}
