package com.gameplay.inventory.player;

import com.gameplay.inventory.InventoryGrid;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.util.text.StringTextComponent;

public class PlayerInventoryUI extends Screen {

    private final InventoryGrid grid;

    public PlayerInventoryUI() {
        super(new StringTextComponent("Инвентарь"));
        this.grid = new InventoryGrid(6, 8); // 6 строк, 8 колонок
    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        super.render(matrixStack, mouseX, mouseY, partialTicks);

        // Рендер сетки
        grid.render(matrixStack, mouseX, mouseY);
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}