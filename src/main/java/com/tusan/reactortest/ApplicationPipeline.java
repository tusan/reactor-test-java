package com.tusan.reactortest;

import com.tusan.reactortest.Subscriber.Subscriber;
import com.tusan.reactortest.services.CounterService;
import com.tusan.reactortest.services.CounterVolumeMatcher;
import com.tusan.reactortest.services.DaoRepository;
import com.tusan.reactortest.services.OutputObject;
import reactor.core.publisher.Flux;

import java.util.stream.Collectors;

public class ApplicationPipeline {
    public static void main(String... args) {
        long timeStart = System.currentTimeMillis();
        Flux.from(DaoRepository.loadAllFromDb())
                .map(value -> OutputObject.create(value.getContent(), 0))
                .map(value -> OutputObject.create(value.name(), CounterService.getCount()))
                .doOnNext(CounterVolumeMatcher::count)
                .collect(Collectors.toList())
                .subscribe(Subscriber::execute);

        System.out.println(String.format("\n***** Completed in %d ms *****", System.currentTimeMillis() - timeStart));
        System.out.println(String.format("\nOutput: %s", CounterVolumeMatcher.getMap()));
    }
}