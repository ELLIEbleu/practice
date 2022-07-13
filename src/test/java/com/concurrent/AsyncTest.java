package com.concurrent;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Description
 * @Author ellie
 * @Date 2021/5/10 10:48 AM
 **/

public class AsyncTest {
    // 创建 ThreadLocal 对象，并设置默认值（new SimpleDateFormat）
    private static ThreadLocal<SimpleDateFormat> threadLocal =
            ThreadLocal.withInitial(() -> new SimpleDateFormat("mm:ss"));

    @Test
    public void test1() {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        List<Integer> list = Lists.newArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11);
        List<String> ret = list.stream().map(a -> CompletableFuture.supplyAsync(() -> {
            String val = a + "test";
            System.out.println("supply async thread name is " + Thread.currentThread().getName() + " val:" + val);
            return val;
        }, executorService)).collect(Collectors.toList()).stream().map(CompletableFuture::join).collect(Collectors.toList());

        System.out.println("main thread name is " + Thread.currentThread().getName() + " ret:" + JSON.toJSON(ret));
    }

    @Test
    public void test2() {
        // 同一个线程 main
        Runnable runnable = () -> print();
        runnable.run();
        Runnable runnable1 = () -> print();
        runnable1.run();

        // 两个新的线程
        ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.submit(runnable1);
        executor.submit(runnable);

        // 两个新的线程
        new Thread(runnable).start();
        new Thread(runnable1).start();

        // 默认线程池
        CompletableFuture.runAsync(runnable);
        Integer ret = CompletableFuture.supplyAsync(() -> {
            print();
            return 3;
        }).join();
        System.out.println("thread name is " + Thread.currentThread().getName() + " ret:" + ret);

        //创建新的线程池
        ExecutorService newExecutor = Executors.newFixedThreadPool(3);
        // 可以指定线程池
        CompletableFuture.runAsync(runnable, newExecutor);
        Integer res = CompletableFuture.supplyAsync(() -> {
            print();
            return 4;
        }, newExecutor).join();
        System.out.println("thread name is " + Thread.currentThread().getName() + " res :" + res);

    }


    public void print() {
        System.out.println("execute print thread name is " + Thread.currentThread().getName());
    }

    public void test3() {
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("");
        classPathXmlApplicationContext.getBean("");
    }


    @Test
    public void test4() throws ExecutionException, InterruptedException {


        CompletableFuture<Boolean> run2 = CompletableFuture.supplyAsync(() -> run2());
        int d = run4();
        CompletableFuture<Boolean> run1 = CompletableFuture.supplyAsync(() -> run1(d));
//        CompletableFuture allof = CompletableFuture.allOf(run2,run1);
//
//        System.out.println("阻塞....");
//        allof.join();
//        System.out.println("阻塞结束....");
//
//        boolean ret1 = run1.get();
//        boolean ret2 = run2.get();

        List<Boolean> rets= Stream.of(run1, run2)
                .map(CompletableFuture::join)
                .collect(Collectors.toList());
        boolean ret1 = rets.get(0);
        boolean ret2 = rets.get(1);
        if( ret1 && ret2){
            System.out.println("都执行成功");
        }else {
            System.out.println("有执行失败的");
        }

    }

    private int run4() {
        int d =0;
        for (d = 0; d < 4; d++) {
            System.out.println("thread name is "+Thread.currentThread().getName()+" d="+d);
        }
        return d;
    }

    public void thenCombine(){
        CompletableFuture<String> completableFuture
                = CompletableFuture.supplyAsync(() -> "Hello")
                .thenCombine(CompletableFuture.supplyAsync(
                        () -> " World"), (s1, s2) -> s1 + s2);
    }

    public boolean run1(int d) {
        int k =0;
        for ( ; k < 5; k++) {
            System.out.println("thread name is" + Thread.currentThread().getName() + " k=" + k);
        }
        System.out.println("d="+d+ " k="+k);
        int ret = d +k;
        System.out.println("d+k="+ d+k+ " ret="+ret);
        return true;
    }

    public boolean run2() {
        for (int j = 0; j < 6; j++) {
            System.out.println("thread name is" + Thread.currentThread().getName() + " j=" + j);
        }
        return true;
    }

    @Test
    public void test5(){
       test6();
        System.out.println("test5**********************");
    }


    public void test6(){
        CompletableFuture.runAsync(() -> run2());

        System.out.println("thread name is" + Thread.currentThread().getName() +" **************");
    }

}
