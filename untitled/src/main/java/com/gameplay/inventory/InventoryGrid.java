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


public class InventoryGrid {

    private final int rows;
    private final int columns;
    private Tile[][] grid;

    public InventoryGrid(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.grid = new Tile[columns][rows];

        // Инициализируем сетку пустыми ячейками
        for (int x = 0; x < columns; x++) {
            for (int y = 0; y < rows; y++) {
                grid[x][y] = new Tile(null, 1, 1); // Пустые ячейки с размером 1x1
            }
        }
    }

    // Отрисовка сетки
    public void render(MatrixStack matrices, int mouseX, int mouseY) {
        int cellSize = 20; // Размер ячейки

        for (int x = 0; x < columns; x++) {
            for (int y = 0; y < rows; y++) {
                Tile tile = grid[x][y];
                int xPos = x * cellSize + 10;
                int yPos = y * cellSize + 10;

                drawCell(matrices, xPos, yPos, cellSize, cellSize, 0xFFAAAAAA);

                if (tile.getItem() != null) {
                    Minecraft.getInstance()
                            .getItemRenderer()
                            .renderGuiItem(tile.getItem(), xPos + 2, yPos + 2);
                }
            }
        }
    }
    private void drawCell(MatrixStack matrices, int x, int y, int width, int height, int color) {
        Matrix4f matrix = matrices.last().pose();
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder buffer = tessellator.getBuilder();

        int a = (color >> 24) & 0xFF;
        int r = (color >> 16) & 0xFF;
        int g = (color >> 8) & 0xFF;
        int b = color & 0xFF;

        RenderSystem.disableTexture();
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();

        buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_COLOR);
        buffer.vertex(matrix, x, y + height, 0).color(r, g, b, a).endVertex();
        buffer.vertex(matrix, x + width, y + height, 0).color(r, g, b, a).endVertex();
        buffer.vertex(matrix, x + width, y, 0).color(r, g, b, a).endVertex();
        buffer.vertex(matrix, x, y, 0).color(r, g, b, a).endVertex();
        tessellator.end();

        RenderSystem.disableBlend();
        RenderSystem.enableTexture();
    }


    // Добавление предмета в ячейку
    public void addItem(ItemStack item, int x, int y) {
        if (x < 0 || y < 0 || x >= columns || y >= rows) return;
        grid[x][y] = new Tile(item, 1, 1);
    }

    // Получение предмета из ячейки
    public ItemStack getItem(int x, int y) {
        if (x < 0 || y < 0 || x >= columns || y >= rows) return ItemStack.EMPTY;
        return grid[x][y].getItem();
    }

    // Обработка кликов мыши
    public boolean handleClick(double mouseX, double mouseY) {
        int cellSize = 20;
        int gridX = (int) (mouseX - 10) / cellSize;
        int gridY = (int) (mouseY - 10) / cellSize;

        if (gridX < 0 || gridY < 0 || gridX >= columns || gridY >= rows) {
            return false; // Выход за пределы сетки
        }

        Tile clickedTile = grid[gridX][gridY];
        if (clickedTile.getItem() != null) {
            System.out.println("Item clicked: " + clickedTile.getItem().toString());
            return true;
        }
        return false;
    }
}
