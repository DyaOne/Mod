package com.attribute.player;

import com.stats.player.PlayerStats;

public interface IPlayerAttributes {
    PlayerAttributes getAttributes();
    void setAttributes(PlayerAttributes attributes);
    PlayerStats getStats();
}