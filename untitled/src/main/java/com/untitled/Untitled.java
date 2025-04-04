package com.untitled;

import com.attribute.player.PlayerAttributesCapability;
import com.attribute.player.PlayerCapabilityEvents;
import com.events.ClientSetup;
import com.gameplay.disabler.GameplayDisabler;
import com.gameplay.disabler.RecipeDisabler;
import net.minecraft.block.Block;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod("untitled")
public class Untitled {

    private static final Logger LOGGER = LogManager.getLogger();

    public Untitled() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onClientSetup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(PlayerAttributesCapability::register);

        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(new GameplayDisabler());
        MinecraftForge.EVENT_BUS.register(new RecipeDisabler());
        MinecraftForge.EVENT_BUS.register(PlayerCapabilityEvents.class);
    }

    private void onClientSetup(final FMLClientSetupEvent event) {
        ClientSetup.onClientSetup(event);
    }

    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {
        @SubscribeEvent
        public static void onBlocksRegistry(final RegistryEvent.Register<Block> blockRegistryEvent) {
            LOGGER.info("Registering blocks...");
        }
    }

    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {
        LOGGER.info("Server is starting");
    }
}
