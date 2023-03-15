package com;

import com.practise.dto.Foo;

import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

/**
 * @Description
 * @Author dan.he
 * @Date 2023/2/25 11:16
 **/
public class MutliThreadPrintTest {
    //
    private static volatile boolean flag = false;

    //object notify
    public void print(){
        Foo foo = new Foo();
        for (int i = 0; i< 10; i++){
            new Thread(() -> {
                synchronized (foo) {
                    foo.printA();
//                    try {
//                        sleep(1 *10000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
                    foo.notify();
                }
            }).start();

            new Thread(() -> {
                synchronized (foo) {
                    foo.printB();

                    foo.notify();
                }
            }).start();
        }
    }

    //volatile
    public void print1(){

        new Thread(() -> {
            int i = 0;
            while (true) {
                if (flag) {
                    if (i % 2 == 0) {
                        System.out.println("printA..........");
                        flag = false;
                    }
                      ++i;
                }

                if (i == 10)
                    break;

            }
        }).start();

        Runnable runnable = new Runnable(){
            int i=0;
            @Override
            public void run() {
                while (true) {
                    if (!flag) {
                        if (i % 2 != 0) {
                            System.out.println("printB..........");
                            flag = true;
                        }
                          ++i;
                    }
                    if (i == 10) {
                        break;
                    }
                }
            }
        };

        new Thread(runnable).start();
    }

    //通过 condition 的 await/signal


    //通过 join 的方式
    public void print4(){
        Thread thread1 =new Thread(() ->{
            System.out.println("print4-printA.......");
        });
        thread1.start();

        Thread thread2 = new Thread(() -> {
            System.out.println("print4-printB.......");
        });
        try {
            thread2.start();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        MutliThreadPrintTest demo = new MutliThreadPrintTest();

//        demo.print();

//        demo.print1();

        demo.print4();

    }
}
