package com.tusan.reactortest.services;

import java.util.Random;

public class CounterService {
    private static final Object LOCK = new Object();

    public static int getCount() {
        synchronized (LOCK) {
            return new Random()
                    .ints(0, 9)
                    .findFirst()
                    .orElse(0);
        }
    }
}
