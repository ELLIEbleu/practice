package com.concurrent;

import org.apache.commons.lang3.concurrent.*;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;

import java.util.concurrent.TimeUnit;

public class CircuitBreakerTest {

    public static void main(String[] args) throws ConcurrentException {
        EventCountCircuitBreaker breaker = new EventCountCircuitBreaker(1000, 1, TimeUnit.MINUTES, 800);
        ThresholdCircuitBreaker thresholdCircuitBreaker = new ThresholdCircuitBreaker(10L);

        Memoizer<String,String> memoizer;

        ConcurrentInitializer initializer ;

        ConstantInitializer constantInitializer = new ConstantInitializer(100);
        System.out.println(constantInitializer.get());
    }
}
