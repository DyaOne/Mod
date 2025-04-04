package com.events;

import com.libs.ScreenResizeWatcher;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = "untitled", value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientSetup {

    public static void onClientSetup(final FMLClientSetupEvent event) {
        InputEvents.registerKeys();
        MinecraftForge.EVENT_BUS.register(new InputEvents());
        MinecraftForge.EVENT_BUS.register(new RenderEvents());
        MinecraftForge.EVENT_BUS.register(ScreenResizeWatcher.class);

    }
}
