package com.libs;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = "untitled", value = Dist.CLIENT)
public class CharacterInventoryRenderer {

    private static final Minecraft mc = Minecraft.getInstance();
    private static boolean isOpen = false;

    // Примерная текстура (можно заменить своей)
    private static final ResourceLocation TEXTURE = new ResourceLocation("minecraft", "textures/item/apple.png");

    public static void open() {
        isOpen = true;
    }

    public static void close() {
        isOpen = false;
    }

    public static boolean isOpen() {
        return isOpen;
    }

    @SubscribeEvent
    public static void onRenderGameOverlay(RenderGameOverlayEvent.Post event) {
        if (!isOpen || event.getType() != RenderGameOverlayEvent.ElementType.ALL) return;

        MatrixStack matrixStack = event.getMatrixStack();
        int xStart = mc.getWindow().getGuiScaledWidth() / 2 - 90;
        int yStart = mc.getWindow().getGuiScaledHeight() / 2 - 90;

        // Рисуем сетку 5x5
        mc.getTextureManager().bind(TEXTURE);
        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 5; col++) {
                int x = xStart + col * 20;
                int y = yStart + row * 20;
                AbstractGui.blit(matrixStack, x, y, 0, 0, 16, 16, 16, 16);
            }
        }
    }
}
