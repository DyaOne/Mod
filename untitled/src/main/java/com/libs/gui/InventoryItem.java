package com.libs.gui;

public class InventoryItem {
    public final String id;
    public final int width;
    public final int height;
    public int gridX;
    public int gridY;
    private int x;
    private int y;

    public InventoryItem(String id, int x, int y, int width, int height) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public boolean occupies(int x, int y) {
        return x >= gridX && x < gridX + width && y >= gridY && y < gridY + height;
    }
}
