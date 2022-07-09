package com;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.*;


@BenchmarkMode(Mode.Throughput)
@State(Scope.Thread)
public class BenchmarkServiceTest {

    @Benchmark
    public void testStringAdd(){
        String a="";
        for (int i = 0; i < 10; i++) {
            a += i;
        }
        System.out.println(a);
    }

    public static void main(String[] args) throws RunnerException, ExecutionException, InterruptedException {
        Options options = new OptionsBuilder()
                .include(BenchmarkServiceTest.class.getSimpleName())
                .forks(2)
                .warmupIterations(3)
                .measurementIterations(3)
                .build();
        new Runner(options).run();    //todo  fix ERROR: Unable to find the resource: /META-INF/BenchmarkList
        Executors.newFixedThreadPool(1);
        Executors.newCachedThreadPool();
        Executors.newScheduledThreadPool(1);
        Executors.newSingleThreadExecutor();
        Executors.newWorkStealingPool();

        new CompletableFuture<>().handle((res,err) ->{
            return 0;
        }).get();
    }
}
