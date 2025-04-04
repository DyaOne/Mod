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

        // Полупрозрачный фон инвентаря
        AbstractGui.fill(matrixStack, startX - 2, startY - 2,
                startX + config.columns * size + 2, startY + config.rows * size + 2,
                0xAA000000); // тёмный фон с прозрачностью

        // Отрисовка ячеек
        for (int x = 0; x < config.columns; x++) {
            for (int y = 0; y < config.rows; y++) {
                int px = startX + x * size;
                int py = startY + y * size;

                // Серая рамка
                AbstractGui.fill(matrixStack, px, py, px + size, py + size, 0xFF222222); // внутренняя
                AbstractGui.fill(matrixStack, px + 1, py + 1, px + size - 1, py + size - 1, 0xFF444444); // внутренняя чуть светлее
            }
        }

        // Отрисовка предметов
        for (InventoryItem item : grid.getItems()) {
            int px = startX + item.gridX * size;
            int py = startY + item.gridY * size;
            int w = item.width * size;
            int h = item.height * size;

            AbstractGui.fill(matrixStack, px, py, px + w, py + h, 0xFF2288FF); // голубой предмет
            Minecraft.getInstance().font.draw(matrixStack, new StringTextComponent(item.id), px + 3, py + 3, 0xFFFFFFFF);
        }
    }

    public static void renderHeldItem(MatrixStack matrixStack, InventoryItem item, int mouseX, int mouseY, int tileSize) {
        if (item == null) return;

        int width = item.getWidth() * tileSize;   // Или item.getWidth()
        int height = item.getHeight() * tileSize; // Или item.getHeight()

        AbstractGui.fill(matrixStack, mouseX, mouseY, mouseX + width, mouseY + height, 0xAA00FF00); // зелёный прямоугольник
        Minecraft.getInstance().font.draw(matrixStack, new StringTextComponent(item.id), mouseX + 3, mouseY + 3, 0xFFFFFFFF);
    }
}