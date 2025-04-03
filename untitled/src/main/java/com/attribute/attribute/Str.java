package com.attribute.attribute;

import com.attribute.IAttribute;

public class Str implements IAttribute {
    private int base;
    private int bonus;

    public Str(int base) {
        this.base = base;
        this.bonus = 0;
    }

    @Override
    public String getName() {
        return "Сила";
    }

    @Override
    public int getBase() {
        return base;
    }

    @Override
    public void setBase(int value) {
        this.base = value;
    }

    @Override
    public int getBaseValue() {
        return 0;
    }

    @Override
    public void setBaseValue(int value) {

    }

    @Override
    public void addBonus(int amount) {
        this.bonus += amount;
    }

    @Override
    public int getTotalValue() {
        return 0;
    }

    @Override
    public int getBonus() {
        return bonus;
    }

    @Override
    public int getTotal() {
        return base + bonus;
    }
}