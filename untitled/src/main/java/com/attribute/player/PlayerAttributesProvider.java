package com.attribute.player;

import com.stats.player.PlayerStats;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraft.util.Direction;
import net.minecraft.nbt.CompoundNBT;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class PlayerAttributesProvider implements IPlayerAttributes, ICapabilitySerializable<CompoundNBT> {

    private final PlayerAttributes attributes;
    private final LazyOptional<IPlayerAttributes> optional = LazyOptional.of(() -> this);
    private final PlayerStats stats;

    public PlayerAttributesProvider() {
        this.attributes = new PlayerAttributes(5, 5, 5);
        this.stats = new PlayerStats(attributes);
    }
    public PlayerStats getStats() {
        return stats;
    }

    @Override
    public PlayerAttributes getAttributes() {
        return attributes;
    }

    @Override
    public void setAttributes(PlayerAttributes attributes) {
        // можно реализовать при необходимости
    }

    @Override
    public CompoundNBT serializeNBT() {
        CompoundNBT tag = new CompoundNBT();
        tag.putInt("Str", attributes.getStrength().getBase());
        tag.putInt("StrBonus", attributes.getStrength().getBonus());
        tag.putInt("Int", attributes.getIntellect().getBase());
        tag.putInt("IntBonus", attributes.getIntellect().getBonus());
        tag.putInt("Ag", attributes.getAgility().getBase());
        tag.putInt("AgBonus", attributes.getAgility().getBonus());
        tag.putInt("Health", stats.getHealth().getCurrent());
        tag.putInt("Mana", stats.getMana().getCurrent());
        tag.putInt("Stamina", stats.getStamina().getCurrent());
        return tag;
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) {
        attributes.getStrength().setBase(nbt.getInt("Str"));
        attributes.getStrength().addBonus(nbt.getInt("StrBonus"));

        attributes.getIntellect().setBase(nbt.getInt("Int"));
        attributes.getIntellect().addBonus(nbt.getInt("IntBonus"));

        attributes.getAgility().setBase(nbt.getInt("Ag"));
        attributes.getAgility().addBonus(nbt.getInt("AgBonus"));

        stats.getHealth().setCurrent(nbt.getInt("Health"));
        stats.getMana().setCurrent(nbt.getInt("Mana"));
        stats.getStamina().setCurrent(nbt.getInt("Stamina"));
    }

    @Override
    @Nonnull
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        return cap == PlayerAttributesCapability.CAPABILITY ? optional.cast() : LazyOptional.empty();
    }
}