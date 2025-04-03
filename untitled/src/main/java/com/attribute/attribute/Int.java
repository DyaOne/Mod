package com.attribute.attribute;

import com.attribute.IAttribute;

public class Int implements IAttribute {
    private int base;
    private int bonus;

    public Int(int base) {
        this.base = base;
        this.bonus = 0;
    }

    @Override
    public String getName() {
        return "Интеллект";
    }

    @Override
    public int getBase() {
        return base;
    }

    @Override
    public int getBonus() {
        return bonus;
    }

    @Override
    public int getTotal() {
        return base + bonus;
    }

    @Override
    public void setBase(int base) {
        this.base = base;
    }

    @Override
    public int getBaseValue() {
        return 0;
    }

    @Override
    public void setBaseValue(int value) {

    }

    @Override
    public void addBonus(int bonus) {
        this.bonus += bonus;
    }

    @Override
    public int getTotalValue() {
        return 0;
    }
}