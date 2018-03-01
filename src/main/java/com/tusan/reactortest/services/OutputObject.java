package com.tusan.reactortest.services;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class OutputObject {
    public abstract String name();

    public abstract int count();

    public static OutputObject create(String name, int count) {
        return new AutoValue_OutputObject(name, count);
    }
}
