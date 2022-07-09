package com.practise.thread;

import java.util.concurrent.LinkedBlockingDeque;

public class ThreadProducerConsumer {
    private static LinkedBlockingDeque<Thread> deque = new LinkedBlockingDeque<>();

    static class Producer implements Runnable {
        @Override
        public void run() {
            System.out.println("this thread name is " + Thread.currentThread().getName());
            deque.add(Thread.currentThread());
        }
    }

    static class Consumer implements Runnable {
        @Override
        public void run() {
            while (true) {
                if (!deque.isEmpty()) {
                    Thread record = deque.pop();
                    System.out.println("deque get thread name is " + record.getName());
                }
            }
        }
    }


    public static void main(String[] args) {
        Thread threadA = new Thread(new Producer());
        Thread threadB = new Thread(new Producer());
        threadA.start();

        try {
            threadB.interrupted();
//            threadB.start();
            threadB.sleep(10 * 1000);     //
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Thread thread = new Thread(new Producer());
        thread.start();
        threadB.start();

        Thread threadC = new Thread(new Consumer());
        threadC.start();
    }
}
