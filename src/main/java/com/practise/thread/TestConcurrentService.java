package com.practise.thread;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class TestConcurrentService {
    private static ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();
    private static ConcurrentHashMap<String, AtomicInteger> concurrentHashMap = new ConcurrentHashMap<>();

    public static class ThreadTest implements Runnable {
        @Override
        public void run() {
            Integer val = map.get("test");
            val += 1;
            map.put("test", val);
            System.out.println("In ThreadTest thread name:" + Thread.currentThread().getName() + "  val=" + map.get("test"));
        }
    }

    public static class ThreadNew implements Runnable {
        @Override
        public void run() {
            AtomicInteger val = concurrentHashMap.get("test");
            val =  new AtomicInteger(val.addAndGet(1));
            concurrentHashMap.put("test",val);
            System.out.println("In ThreadNew thread name:"+ Thread.currentThread().getName()+"  val="+ concurrentHashMap.get("test"));
        }
    }

    public static void main(String[] args) {
        map.put("test", 100);
        Thread threadA = new Thread(new ThreadTest());
        Thread threadB = new Thread(new ThreadTest());
        threadA.start();
        threadB.start();

        concurrentHashMap.put("test",new AtomicInteger(100));
        Thread threadC = new Thread(new ThreadNew());
        Thread threadD = new Thread(new ThreadNew());
        threadC.start();
        threadD.start();
    }
}
