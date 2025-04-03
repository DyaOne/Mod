package com.stats.attribute;

import com.attribute.IAttributeSource;
import com.stats.IStat;

public class Health implements IStat {
    private int current;
    private final IAttributeSource source;

    public Health(IAttributeSource source) {
        this.source = source;
        this.current = getMax();
    }

    @Override
    public String getName() {
        return "Здоровье";
    }

    @Override
    public int getMax() {
        return source.getStrength().getTotal() * 20;
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