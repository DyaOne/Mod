package com.gameplay.inventory.player;

import com.gameplay.inventory.InventoryGrid;
import com.gameplay.inventory.Tile;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.StringTextComponent;

public class CharacterInventoryScreen extends Screen {
    private InventoryGrid grid;

    public CharacterInventoryScreen() {
        super(new StringTextComponent("Inventory"));
    }

    @Override
    protected void init() {
        super.init();

        this.grid = new InventoryGrid();

        int startX = this.width / 2 - 90;
        int startY = this.height / 2 - 90;
        int tileSize = 18;

        // Пример генерации 5x5 инвентаря
        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 5; col++) {
                int x = startX + col * (tileSize + 2);
                int y = startY + row * (tileSize + 2);
                ResourceLocation icon = new ResourceLocation("minecraft", "textures/item/apple.png");
                grid.addTile(new Tile(x, y, tileSize, tileSize, icon));
            }
        }
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    @Override
    public void render(MatrixStack stack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(stack);
        grid.render(stack, mouseX, mouseY, partialTicks);
        super.render(stack, mouseX, mouseY, partialTicks);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        grid.onClick(mouseX, mouseY);
        return super.mouseClicked(mouseX, mouseY, button);
    }
}
