package com.events;

import com.libs.gui.InventoryManager;
import com.libs.CharacterInventoryRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import org.lwjgl.glfw.GLFW;

public class InputEvents {

    public static KeyBinding OPEN_INVENTORY;
    public static KeyBinding ROTATE_ITEM;
    public static boolean showCharacterInventory = false;

    public static void registerKeys() {
        OPEN_INVENTORY = new KeyBinding("key.untitled.open_inventory", GLFW.GLFW_KEY_TAB, "key.categories.inventory");
        ROTATE_ITEM = new KeyBinding("key.untitled.rotate_item", GLFW.GLFW_KEY_R, "key.categories.inventory");
        ClientRegistry.registerKeyBinding(OPEN_INVENTORY);
        ClientRegistry.registerKeyBinding(ROTATE_ITEM);
    }

    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event) {
        if (OPEN_INVENTORY.isDown()) {
            showCharacterInventory = !showCharacterInventory;
            if (showCharacterInventory) {
                Minecraft.getInstance().mouseHandler.releaseMouse();
            } else {
                Minecraft.getInstance().mouseHandler.grabMouse();
            }
        }

        if (ROTATE_ITEM.isDown() && InventoryManager.getHeldItem() != null) {
            InventoryManager.getHeldItem().rotate();
        }
    }

    @SubscribeEvent
    public void onRenderOverlay(RenderGameOverlayEvent.Post event) {
        CharacterInventoryRenderer.renderIfOpen(showCharacterInventory);
    }
}
