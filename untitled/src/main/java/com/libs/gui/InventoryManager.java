package com.libs.gui;

import java.util.HashMap;
import java.util.Map;

public class InventoryManager {
    private static final Map<String, InventoryGrid> inventories = new HashMap<>();

    public static void registerInventory(String id, InventoryGrid grid) {
        inventories.put(id, grid);
    }

    public static InventoryGrid getInventory(String id) {
        return inventories.get(id);
    }

    public static boolean hasInventory(String id) {
        return inventories.containsKey(id);
    }

    public static void clearInventories() {
        inventories.clear();
    }

    public static Map<String, InventoryGrid> getAllInventories() {
        return inventories;
    }
}
