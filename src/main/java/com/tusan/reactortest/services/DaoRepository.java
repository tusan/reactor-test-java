package com.tusan.reactortest.services;

import reactor.core.publisher.Flux;

import java.util.Random;
import java.util.logging.Logger;

public class DaoRepository {
    private static final String DUMMY_VALUES = "stelvio,gavia,penice,pordoi,sella,gardena,san bernardino,pheeeega";

    private static final Logger logger = Logger.getLogger(DaoRepository.class.getName());

    public static Flux<FooDao> loadAllFromDb() {
        return Flux.fromArray(DUMMY_VALUES.split(",")).map(DaoRepository::getSingleDaoBlocking);
    }

    private static FooDao getSingleDaoBlocking(String value) {
        try {
            final int waitingTime = new Random()
                    .ints(50, 1000)
                    .findFirst()
                    .orElse(0);
            logger.info(String.format("Waiting in dao for %s, estimated %d ms ...", value, waitingTime));
            Thread.sleep(waitingTime);
        } catch (InterruptedException e) {
            logger.severe(String.format("Error in thread sleep. [%s]", e.getMessage()));
        }

        return FooDao.create(value);
    }
}
