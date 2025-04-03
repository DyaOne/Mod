package com.attribute;

public interface IAttribute {
    String getName();
    int getBase();
    int getBonus();
    int getTotal();
    void setBase(int base);

    int getBaseValue();

    void setBaseValue(int value);

    void addBonus(int bonus);

    int getTotalValue();
}
