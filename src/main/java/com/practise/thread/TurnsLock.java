package com.practise.thread;

import java.util.concurrent.locks.ReentrantLock;

public class TurnsLock {
    static class Inner implements Runnable{
        private ReentrantLock lock;
        private static int num = 1;
        private int end = 75;
        private int threadId;

        public Inner(int threadId,ReentrantLock lock) {
            this.threadId = threadId;
            this.lock=lock;

        }
        @Override
        public void run() {
            while(num<end){
                lock.lock();
                if(num%4==threadId){
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("线程" + threadId + ":" + num++);
                    if(num ==4){
                        num =1;
                    }
                }
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        ReentrantLock lock=new ReentrantLock();
        new Thread(new Inner(1,lock)).start();
        new Thread(new Inner(2,lock)).start();
        new Thread(new Inner(3,lock)).start();
    }
}
