package com.libs.gui;

public class InventoryConfig {
    public int columns;
    public int rows;
    public int tileSize;
    public int xOffset;
    public int yOffset;

    public InventoryConfig(int columns, int rows, int tileSize, int xOffset, int yOffset) {
        this.columns = columns;
        this.rows = rows;
        this.tileSize = tileSize;
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }
}