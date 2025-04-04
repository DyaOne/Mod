package com.events;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import org.lwjgl.glfw.GLFW;
import com.libs.CharacterInventoryRenderer;

@Mod.EventBusSubscriber(modid = "untitled", value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientEvents {

    public static KeyBinding OPEN_INVENTORY;
    public static boolean showCharacterInventory = false;

    public static void onClientSetup(final FMLClientSetupEvent event) {
        OPEN_INVENTORY = new KeyBinding(
                "key.untitled.open_inventory",
                GLFW.GLFW_KEY_TAB,
                "key.categories.inventory"
        );
        ClientRegistry.registerKeyBinding(OPEN_INVENTORY);
        MinecraftForge.EVENT_BUS.register(new ClientEvents());
    }

    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event) {
        if (OPEN_INVENTORY.isDown()) {
            showCharacterInventory = !showCharacterInventory;
        }
    }

    @SubscribeEvent
    public void onRenderOverlay(RenderGameOverlayEvent.Post event) {
        if (showCharacterInventory) {
            CharacterInventoryRenderer.open();
        }
    }
}
