package com.libs;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(value = Dist.CLIENT)
public class ScreenResizeWatcher {

    private static int lastWidth = -1;
    private static int lastHeight = -1;

    @SubscribeEvent
    public static void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;

        int width = net.minecraft.client.Minecraft.getInstance().getWindow().getGuiScaledWidth();
        int height = net.minecraft.client.Minecraft.getInstance().getWindow().getGuiScaledHeight();

        if (width != lastWidth || height != lastHeight) {
            lastWidth = width;
            lastHeight = height;

            CharacterInventoryRenderer.updateLayout(); // Обновляем layout
        }
    }
}