package com.attribute.player;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Map;
import java.util.WeakHashMap;

@Mod.EventBusSubscriber
public class PlayerCapabilityEvents {

    private static final Map<ServerPlayerEntity, Integer> tickCounters = new WeakHashMap<>();



    @SubscribeEvent
    public static void attachCapabilityToPlayer(AttachCapabilitiesEvent<net.minecraft.entity.Entity> event) {
        if (event.getObject() instanceof PlayerEntity) {
            PlayerAttributesProvider provider = new PlayerAttributesProvider();
            event.addCapability(PlayerAttributesCapability.ID, provider);
        }
    }

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase != TickEvent.Phase.END || event.player.level.isClientSide) return;

        ServerPlayerEntity player = (ServerPlayerEntity) event.player;
        int ticks = tickCounters.getOrDefault(player, 0) + 1;

        if (ticks >= 20) { // Каждые 20 тиков (1 секунда)
            tickCounters.put(player, 0);

            player.getCapability(PlayerAttributesCapability.CAPABILITY).ifPresent(cap -> {
                cap.getStats().regenerateAll();
            });

        } else {
            tickCounters.put(player, ticks);
        }
    }
}