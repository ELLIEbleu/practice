package com.practise.thread;


public class TestService {
    private static volatile int a=0;

   public static class MyThreadA implements Runnable{
        @Override
        public void run() {
            if(a==1){
                System.out.println("threadA a= "+1);
            }
        }
    }

    public static class MyThreadB implements Runnable{

        @Override
        public void run() {
            if(a==2){
                System.out.println("threadA a= "+1);
            }
        }
    }

   public static class MyThreadC implements Runnable{
        @Override
        public void run() {
            if(a==3){
                System.out.println("threadA a= "+1);
            }
        }
    }

    public static void main(String[] args) {


        for (int i = 1; i <=3 ; i++) {
            a =i;
            if(a==3){
                i = 1;
            }
        }
        Thread threadA = new Thread(new MyThreadA());
        Thread threadB = new Thread(new MyThreadB());
        Thread threadC = new Thread(new MyThreadC());
        threadA.start();
        threadB.start();
        threadC.start();

//        CompletableFuture<> completableFuture = new CompletableFuture();
//        completableFuture.thenRun()


    }
}
