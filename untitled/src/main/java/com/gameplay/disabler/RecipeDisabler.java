package com.gameplay.disabler;

import net.minecraftforge.event.world.WorldEvent;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.item.crafting.RecipeManager;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import java.lang.reflect.Field;
import java.util.Map;

public class RecipeDisabler {
    @SubscribeEvent
    public void onWorldLoad(WorldEvent.Load event) {
        if (event.getWorld() instanceof ServerWorld) {
            ServerWorld world = (ServerWorld) event.getWorld();
            RecipeManager manager = world.getServer().getRecipeManager();

            try {
                Field recipesField = RecipeManager.class.getDeclaredField("recipes");
                recipesField.setAccessible(true);
                ((Map<?, ?>) recipesField.get(manager)).clear();
            } catch (Exception e) {
                System.out.println("Не удалось очистить рецепты: " + e.getMessage());
            }
        }
    }
}
