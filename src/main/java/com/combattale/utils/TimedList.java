package com.combattale.utils;

import java.util.ArrayList;
import java.util.List;

public class TimedList<T> {
    private final List<Double> stops = new ArrayList<>();
    private final List<T> elements = new ArrayList<>();

    public boolean add(double startTime, T element) {
        stops.add(startTime);
        return elements.add(element);
    }

    public T get(double time) {
        int index = getIndex(time);
        return elements.get(index);
    }

    public int getIndex(double time) {
        for (int i = stops.size() - 1; i >= 0; i--) {
            if (stops.get(i) <= time)
                return i;
        }
        return -1;
    }
}
