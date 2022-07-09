package com.practise.thread;

import java.util.concurrent.atomic.AtomicInteger;

public class TestAtomicService {
    public static AtomicInteger count = new AtomicInteger(100);

    public static class TestThread implements Runnable{
        @Override
        public void run() {
            System.out.println("thread name:"+Thread.currentThread().getName()+" count= "+count.addAndGet(1));
        }
    }
    public static void main(String[] args) {
        Thread thread1 = new Thread(new TestThread());
        Thread thread2 = new Thread(new TestThread());
        thread1.start();
        thread2.start();
    }
}
