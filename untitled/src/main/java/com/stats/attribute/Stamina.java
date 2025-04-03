package com.stats.attribute;

import com.attribute.IAttributeSource;
import com.stats.IStat;

public class Stamina implements IStat {
    private int current;
    private final IAttributeSource source;

    public Stamina(IAttributeSource source) {
        this.source = source;
        this.current = getMax();
    }

    @Override
    public String getName() {
        return "Стамина";
    }

    @Override
    public int getMax() {
        return source.getAgility().getTotal() * 20;
    }

    @Override
    public int getCurrent() {
        return current;
    }

    @Override
    public void setCurrent(int value) {
        this.current = Math.max(0, Math.min(value, getMax()));
    }

    @Override
    public void increase(int amount) {
        setCurrent(current + amount);
    }

    @Override
    public void decrease(int amount) {
        setCurrent(current - amount);
    }

    @Override
    public void regenerate() {
        increase(1);
    }
}