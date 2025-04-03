package com.attribute.player;

import com.attribute.IAttributeSource;
import com.attribute.attribute.Ag;
import com.attribute.attribute.Int;
import com.attribute.attribute.Str;

public class PlayerAttributes implements IAttributeSource {
    private final Str strength;
    private final Int intellect;
    private final Ag agility;

    public PlayerAttributes(int str, int intellect, int ag) {
        this.strength = new Str(str);
        this.intellect = new Int(intellect);
        this.agility = new Ag(ag);
    }

    public Str getStrength() {
        return strength;
    }

    public Int getIntellect() {
        return intellect;
    }

    public Ag getAgility() {
        return agility;
    }

    public int getTotalStatValue() {
        return strength.getTotal() + intellect.getTotal() + agility.getTotal();
    }
}