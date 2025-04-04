package com.libs;

import com.libs.api.mouse.MouseAPI;
import com.libs.gui.InventoryConfig;
import com.libs.gui.InventoryGrid;
import com.libs.gui.InventoryManager;
import com.libs.gui.InventoryRenderer;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class CharacterInventoryRenderer {

    private static final Minecraft mc = Minecraft.getInstance();

    private static InventoryConfig config;
    private static InventoryGrid playerInventory;

    public static void updateLayout() {


        int screenWidth = mc.getWindow().getGuiScaledWidth();
        int screenHeight = mc.getWindow().getGuiScaledHeight();

        int columns = 10;
        int rows = 6;

        int margin = 40;
        int maxGridWidth = screenWidth - margin * 2;
        int maxGridHeight = screenHeight - margin * 2;

        int tileSizeByWidth = maxGridWidth / columns;
        int tileSizeByHeight = maxGridHeight / rows;

        int tileSize = Math.min(tileSizeByWidth, tileSizeByHeight);
        tileSize = Math.min(tileSize, 32); // 🔒 максимум 32px
        tileSize = Math.max(tileSize, 12); // 🔒 минимум 12px

        int gridWidth = tileSize * columns;
        int gridHeight = tileSize * rows;

        int xOffset = (screenWidth - gridWidth) / 6;
        int yOffset = (screenHeight - gridHeight) / 6;

        // Обновляем конфигурацию и инвентарь
        config = new InventoryConfig(columns, rows, xOffset, yOffset, tileSize);
        playerInventory = new InventoryGrid(config);
    }

    public static void renderIfOpen(boolean isOpen) {
        if (!isOpen) return;

        if (playerInventory == null) {
            updateLayout(); // инициализируем при первом рендере
        }

        MatrixStack matrixStack = new MatrixStack();

        // Рендер сетки
        InventoryRenderer.render(matrixStack, playerInventory);

        // Блокировка управления
        if (mc.player != null && mc.player.input != null) {
            mc.player.input.forwardImpulse = 0;
            mc.player.input.leftImpulse = 0;
            mc.player.input.jumping = false;
            mc.player.input.shiftKeyDown = false;
        }
        if (MouseAPI.isMouseLocked()) {
            double x = MouseAPI.getMouseX();
            double y = MouseAPI.getMouseY();

            if (MouseAPI.isLeftPressed()) {
                // Обработка клика
            }
        }
        // Рендер предмета под курсором
        if (InventoryManager.getHeldItem() != null) {
            int mouseX = (int) MouseAPI.getMouseX();
            int mouseY = (int) MouseAPI.getMouseY();

            InventoryRenderer.renderHeldItem(matrixStack, InventoryManager.getHeldItem(), mouseX, mouseY, config.tileSize);
        }
    }

    public static InventoryGrid getInventory() {
        return playerInventory;
    }
}