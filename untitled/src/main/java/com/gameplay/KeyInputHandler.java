package com.gameplay;

import com.gameplay.inventory.player.CharacterInventoryScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import org.lwjgl.glfw.GLFW;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(modid = "untitled", value = Dist.CLIENT)
public class KeyInputHandler {

    public static final KeyBinding OPEN_INVENTORY_KEY = new KeyBinding(
            "key.untitled.open_inventory",
            GLFW.GLFW_KEY_TAB,
            "key.categories.inventory"
    );

    public static void register() {
        ClientRegistry.registerKeyBinding(OPEN_INVENTORY_KEY);
    }

    @SubscribeEvent
    public static void onKeyInput(InputEvent.KeyInputEvent event) {
        if (OPEN_INVENTORY_KEY.consumeClick()) {
            Minecraft mc = Minecraft.getInstance();
            if (mc.player != null && mc.screen == null) {
                mc.setScreen(new CharacterInventoryScreen());
            }
        }
    }
}