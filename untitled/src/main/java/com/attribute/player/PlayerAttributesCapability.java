package com.attribute.player;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod.EventBusSubscriber
public class PlayerAttributesCapability {

    public static final ResourceLocation ID = new ResourceLocation("untitled", "player_attributes");

    @CapabilityInject(IPlayerAttributes.class)
    public static Capability<IPlayerAttributes> CAPABILITY = null;

    public static void register(FMLCommonSetupEvent event) {
        CapabilityManager.INSTANCE.register(
                IPlayerAttributes.class,
                new Capability.IStorage<IPlayerAttributes>() {
                    @Override
                    public INBT writeNBT(Capability<IPlayerAttributes> capability, IPlayerAttributes instance, Direction side) {
                        return ((PlayerAttributesProvider) instance).serializeNBT();
                    }

                    @Override
                    public void readNBT(Capability<IPlayerAttributes> capability, IPlayerAttributes instance, Direction side, INBT nbt) {
                        ((PlayerAttributesProvider) instance).deserializeNBT((CompoundNBT) nbt);
                    }
                },
                PlayerAttributesProvider::new
        );
    }
}