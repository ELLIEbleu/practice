package com.practise.thread;

public class MyThread {
    public static void main(String[] args) {
        new MultiThread().run();
        new MultiThread().run();

        System.out.println("thread run end...");

        new MultiThread().start();
        new MultiThread().start();
    }

    public static class MultiThread extends Thread {
        private int n = 10;

        @Override
        public void run() {
            while (n > 0) {
                System.out.println("thread name:" + Thread.currentThread().getName() + " "
                        + n--);
            }
        }
    }

    public static class SampleThread extends Thread{
        @Override
        public void run(){
            System.out.println("thread name:"+ Thread.currentThread().getName()+" ");
            try {
                sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
