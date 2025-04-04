package com.libs.gui;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.util.text.StringTextComponent;

public class InventoryRenderer {

    public static void render(MatrixStack matrixStack, InventoryGrid grid) {
        InventoryConfig config = grid.getConfig();
        int startX = config.xOffset;
        int startY = config.yOffset;
        int size = config.tileSize;

        // Отрисовка сетки
        for (int x = 0; x < config.columns; x++) {
            for (int y = 0; y < config.rows; y++) {
                int px = startX + x * size;
                int py = startY + y * size;
                AbstractGui.fill(matrixStack, px, py, px + size, py + size, 0xFF444444); // тёмно-серая клетка
                AbstractGui.drawCenteredString(matrixStack, Minecraft.getInstance().font,
                        ".", px + size / 2, py + size / 2 - 4, 0xFFAAAAAA); // просто точка для теста
            }
        }

        // Отрисовка предметов
        for (InventoryItem item : grid.getItems()) {
            int px = startX + item.gridX * size;
            int py = startY + item.gridY * size;
            int w = item.width * size;
            int h = item.height * size;
            AbstractGui.fill(matrixStack, px, py, px + w, py + h, 0xFF2288FF); // голубой прямоугольник
            Minecraft.getInstance().font.draw(matrixStack, new StringTextComponent(item.id), px + 3, py + 3, 0xFFFFFFFF);
        }
    }
}