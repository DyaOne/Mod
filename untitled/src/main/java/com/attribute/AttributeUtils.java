package com.attribute;

import com.attribute.player.IPlayerAttributes;
import com.attribute.player.PlayerAttributes;
import com.attribute.player.PlayerAttributesCapability;
import com.stats.player.PlayerStats;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.common.util.LazyOptional;

public class AttributeUtils {

    public static PlayerAttributes getAttributes(PlayerEntity player) {
        LazyOptional<IPlayerAttributes> cap = player.getCapability(PlayerAttributesCapability.CAPABILITY);
        return cap.map(IPlayerAttributes::getAttributes).orElse(null);
    }
    public static PlayerStats getStats(PlayerEntity player) {
        return player.getCapability(PlayerAttributesCapability.CAPABILITY)
                .map(IPlayerAttributes::getStats)
                .orElse(null);
    }
}