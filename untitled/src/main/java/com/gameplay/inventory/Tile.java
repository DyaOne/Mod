package com.gameplay.inventory;

import net.minecraft.item.ItemStack;

public class Tile {
    private ItemStack item;
    private int width, height;

    public Tile(ItemStack item, int width, int height) {
        this.item = item;
        this.width = width;
        this.height = height;
    }

    public ItemStack getItem() {
        return item;
    }

    public void setItem(ItemStack item) {
        this.item = item;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
