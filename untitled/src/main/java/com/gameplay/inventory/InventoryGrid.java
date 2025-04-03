package com.gameplay.inventory;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.item.ItemStack;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.util.math.vector.Matrix4f;
import org.lwjgl.opengl.GL11;

import java.util.ArrayList;
import java.util.List;


public class InventoryGrid extends UIElement {
    private List<Tile> tiles = new ArrayList<>();

    public void addTile(Tile tile) {
        tiles.add(tile);
    }

    @Override
    public void render(MatrixStack stack, int mouseX, int mouseY, float partialTicks) {
        for (Tile tile : tiles) {
            tile.render(stack, mouseX, mouseY, partialTicks);
        }
    }

    @Override
    public void onClick(double mouseX, double mouseY) {
        for (Tile tile : tiles) {
            if (tile.isHovered(mouseX, mouseY)) {
                tile.onClick(mouseX, mouseY);
                break;
            }
        }
    }

    @Override
    public boolean isHovered(double mouseX, double mouseY) {
        return true;
    }
}
