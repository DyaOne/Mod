package com.stats;

public interface IStat {
    String getName();
    int getMax();
    int getCurrent();
    void setCurrent(int value);

    void increase(int amount);
    void decrease(int amount);
    void regenerate(); // простая регенерация по 1 очку
}
