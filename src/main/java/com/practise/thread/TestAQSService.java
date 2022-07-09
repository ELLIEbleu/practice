package com.practise.thread;

import java.util.concurrent.*;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class TestAQSService {

    public static void main(String[] args) {
        //AQS

        ReentrantLock lock = new ReentrantLock();
        CountDownLatch latch = new CountDownLatch(3);
        FutureTask<String> futureTask ;
        Semaphore semaphore ;
        ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();

        ThreadPoolExecutor threadPoolExecutor;


        Executors.newScheduledThreadPool(3);
        Executors.newFixedThreadPool(3);
        Executors.newCachedThreadPool();
    }
}
