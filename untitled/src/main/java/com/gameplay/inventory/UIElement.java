package com.gameplay.inventory;

import com.mojang.blaze3d.matrix.MatrixStack;

public abstract class UIElement {
    public int x, y, width, height;

    public abstract void render(MatrixStack stack, int mouseX, int mouseY, float partialTicks);
    public abstract void onClick(double mouseX, double mouseY);
    public abstract boolean isHovered(double mouseX, double mouseY);
}
