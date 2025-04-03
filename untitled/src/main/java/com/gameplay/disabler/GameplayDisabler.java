package com.gameplay.disabler;

import net.minecraft.client.gui.screen.IngameMenuScreen;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraft.entity.EntityType;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraft.client.gui.screen.inventory.InventoryScreen;

public class GameplayDisabler {

    @SubscribeEvent
    public void onRenderHUD(RenderGameOverlayEvent event) {
        if (event.getType() == RenderGameOverlayEvent.ElementType.HEALTH
                || event.getType() == RenderGameOverlayEvent.ElementType.ARMOR
                || event.getType() == RenderGameOverlayEvent.ElementType.EXPERIENCE
                || event.getType() == RenderGameOverlayEvent.ElementType.FOOD
                || event.getType() == RenderGameOverlayEvent.ElementType.HOTBAR
                || event.getType() == RenderGameOverlayEvent.ElementType.CROSSHAIRS) {
            event.setCanceled(true);
        }
    }


    @SubscribeEvent
    public void onRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
        event.setCanceled(true);
    }

    @SubscribeEvent
    public void onLeftClickBlock(PlayerInteractEvent.LeftClickBlock event) {
        event.setCanceled(true);
    }

    @SubscribeEvent
    public void onDamage(LivingAttackEvent event) {
        event.setCanceled(true);
    }

    @SubscribeEvent
    public void onEntityJoinWorld(EntityJoinWorldEvent event) {
        if (event.getEntity().getType() != EntityType.PLAYER) {
            event.setCanceled(true); // Отменяем спавн всего, кроме игрока
        }
    }

    // Блокируем прокачку рецептов (автодобавление)
    @SubscribeEvent
    public static void onCrafting(PlayerEvent.ItemCraftedEvent event) {
        event.setCanceled(true);
        event.getPlayer().sendMessage(new StringTextComponent("Крафт отключён"), event.getPlayer().getUUID());
    }


    @SubscribeEvent
    public void onGuiOpen(GuiOpenEvent event) {
        if (event.getGui() != null &&
                (event.getGui() instanceof InventoryScreen || event.getGui() instanceof IngameMenuScreen)) {
            event.setCanceled(true);
        }
    }
}