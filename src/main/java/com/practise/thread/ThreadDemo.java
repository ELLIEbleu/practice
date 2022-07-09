package com.practise.thread;


import java.util.concurrent.*;

public class ThreadDemo {
    public static void test() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> cp = CompletableFuture.supplyAsync(() -> 4);
        System.out.println(cp.get());
        CompletableFuture<String> cp2 =CompletableFuture.supplyAsync(() ->{
            return "test";
        });

        Future<Integer> future = new FutureTask<Integer>(() ->{
            return 4;
        });


    }

    class MyCallable implements Callable<String>{

        @Override
        public String call() throws Exception {
            return null;
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ThreadDemo.test();
    }
}
