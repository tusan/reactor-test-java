package com.tusan.reactortest.services;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class FooDao {
    public abstract String getContent();

    public static FooDao create(String newContent) {
        return new AutoValue_FooDao(newContent);
    }

}
