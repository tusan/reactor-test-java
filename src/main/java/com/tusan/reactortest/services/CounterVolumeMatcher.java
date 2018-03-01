package com.tusan.reactortest.services;

import com.google.auto.value.AutoValue;
import com.google.common.collect.ImmutableMap;

import java.util.HashMap;
import java.util.Map;

public class CounterVolumeMatcher {
    private static final Map<Integer, CounterVolumeEntry> map = new HashMap<>();

    public static void count(OutputObject out) {
        CounterVolumeEntry oldValue = map.get(out.count());
        if (oldValue == null) {
            oldValue = CounterVolumeEntry.create(out, 0);
        }

        map.put(out.count(), CounterVolumeEntry.create(oldValue.out(),
                oldValue.counter() + out.count()));
    }

    public static Map<Integer, CounterVolumeEntry> getMap() {
        return ImmutableMap.copyOf(map);
    }
}

@AutoValue
abstract class CounterVolumeEntry {
    public abstract OutputObject out();

    public abstract int counter();

    public static CounterVolumeEntry create(OutputObject out, int counter) {
        return new AutoValue_CounterVolumeEntry(out, counter);
    }

}