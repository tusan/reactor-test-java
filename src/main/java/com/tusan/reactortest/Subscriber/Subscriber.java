package com.tusan.reactortest.Subscriber;

import com.tusan.reactortest.services.OutputObject;

import java.util.List;
import java.util.stream.Collectors;

public class Subscriber {
    public static void execute(List<OutputObject> flux) {
        flux.stream()
                .collect(Collectors.groupingBy(OutputObject::count, Collectors.summingInt(OutputObject::count)))
                .entrySet()
                .forEach(System.out::println);
    }
}
