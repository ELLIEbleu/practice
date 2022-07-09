package com.practise.thread;


import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadService {

    public void testThread() {
        ExecutorService service = Executors.newFixedThreadPool(3);
        service.execute(new Runnable() {
            @Override
            public void run() {

            }
        });

        Executors.newCachedThreadPool();
        Executors.newFixedThreadPool(3);
        Executors.newScheduledThreadPool(1);
        Executors.newSingleThreadExecutor();
    }

    /**
     * executor  ThreadPoolExecutor : 自定义处理handler; default: RejectedExecutionHandler
     * @param args
     */

    public static void main(String[] args) {
        LinkedBlockingDeque deque = new LinkedBlockingDeque<>(5);
        ThreadPoolExecutor pool = new ThreadPoolExecutor(1,2,20, TimeUnit.SECONDS, deque ,new RejectDemo());

        Thread thread = new Thread(new MyThread.SampleThread());

//        for (int i = 0; i <Integer.MAX_VALUE ; i++) {
//            pool.execute(thread);
//        }

//        pool.execute(thread);
//        pool.execute(thread);
//        pool.execute(thread);
//        pool.execute(thread);
//        pool.execute(thread);

    }
}
