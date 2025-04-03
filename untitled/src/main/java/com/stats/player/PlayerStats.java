package com.stats.player;

import com.stats.attribute.Health;
import com.stats.attribute.Mana;
import com.attribute.IAttributeSource;
import com.stats.attribute.Stamina;

public class PlayerStats {
    private final Health health;
    private final Mana mana;
    private final Stamina stamina;

    public PlayerStats(IAttributeSource source) {
        this.health = new Health(source);
        this.mana = new Mana(source);
        this.stamina = new Stamina(source);
    }

    public Health getHealth() {
        return health;
    }

    public Mana getMana() {
        return mana;
    }

    public Stamina getStamina() {
        return stamina;
    }

    public void regenerateAll() {
        health.regenerate();
        mana.regenerate();
        stamina.regenerate();
    }
}