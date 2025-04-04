package com.libs.gui;

import java.util.ArrayList;
import java.util.List;

public class InventoryGrid {
    private final InventoryConfig config;
    private final List<InventoryItem> items = new ArrayList<>();

    public InventoryGrid(InventoryConfig config) {
        this.config = config;
    }

    public boolean canPlaceItem(InventoryItem item, int gridX, int gridY) {
        if (gridX + item.width > config.columns || gridY + item.height > config.rows) return false;

        for (InventoryItem existing : items) {
            if (rectsOverlap(gridX, gridY, item.width, item.height,
                    existing.gridX, existing.gridY, existing.width, existing.height)) {
                return false;
            }
        }

        return true;
    }

    public boolean placeItem(InventoryItem item, int gridX, int gridY) {
        if (!canPlaceItem(item, gridX, gridY)) return false;

        item.gridX = gridX;
        item.gridY = gridY;
        items.add(item);
        return true;
    }

    public List<InventoryItem> getItems() {
        return items;
    }

    public InventoryConfig getConfig() {
        return config;
    }

    private boolean rectsOverlap(int x1, int y1, int w1, int h1,
                                 int x2, int y2, int w2, int h2) {
        return x1 < x2 + w2 && x1 + w1 > x2 &&
                y1 < y2 + h2 && y1 + h1 > y2;
    }
}