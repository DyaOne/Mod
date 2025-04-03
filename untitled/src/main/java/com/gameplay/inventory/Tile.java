package com.gameplay.inventory;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.util.ResourceLocation;


public class Tile extends UIElement {
    private ResourceLocation icon;
    private boolean hovered;

    public Tile(int x, int y, int w, int h, ResourceLocation icon) {
        this.x = x; this.y = y;
        this.width = w; this.height = h;
        this.icon = icon;
    }

    @Override
    public void render(MatrixStack stack, int mouseX, int mouseY, float partialTicks) {
        hovered = isHovered(mouseX, mouseY);
        Minecraft.getInstance().getTextureManager().bind(icon);
        AbstractGui.blit(stack, x, y, 0, 0, width, height, width, height);
        if (hovered) {
            // Отрисовать рамку
            AbstractGui.fill(stack, x, y, x + width, y + height, 0x80FFFFFF);
        }
    }

    @Override
    public void onClick(double mouseX, double mouseY) {
        System.out.println("Нажата плитка");
    }

    @Override
    public boolean isHovered(double mouseX, double mouseY) {
        return mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + height;
    }
}
