package com.concurrent;


import org.junit.Test;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description
 * @Author ellie
 * @Date 2021/5/19 9:59 AM
 **/


public class SimpleDateFormatTest {
    // 创建 DateTimeFormatter 对象
    private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("mm:ss");

    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");


    // SimpleDateFormat 线程不安全
    @Test
    public void test() {
        ExecutorService threadPool = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    Date date = new Date(finalI * 1000);
                    String result = simpleDateFormat.format(date);
                    System.out.println(result);
                }
            });
        }
    }


    //将 SimpleDateFormat 定义为局部变量
    @Test
    public void test1() {
        ExecutorService threadPool = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    SimpleDateFormat localDateFormat = new SimpleDateFormat("mm:ss");
                    Date date = new Date(finalI * 1000);
                    String result = localDateFormat.format(date);
                    System.out.println(result);
                }
            });
        }
    }


    //使用 synchronized 加锁执行；
    @Test
    public void test2() {
        ExecutorService threadPool = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    Date date = new Date(finalI * 1000);
                    String result = null;
                    synchronized (simpleDateFormat) {
                        result = simpleDateFormat.format(date);
                    }
                    System.out.println(result);
                }
            });
        }
    }


    //使用 Lock 加锁
    @Test
    public void test3() {
        ExecutorService threadPool = Executors.newFixedThreadPool(10);
        Lock lock = new ReentrantLock();
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    Date date = new Date(finalI * 1000);
                    String result = null;
                    lock.lock();
                    try {
                        result = simpleDateFormat.format(date);
                    } finally {
                        lock.unlock();
                    }
                    System.out.println(result);
                }
            });
        }
    }

    //使用 ThreadLocal
    @Test
    public void test4() {
        ThreadLocal<SimpleDateFormat> threadLocal = ThreadLocal.withInitial(() -> new SimpleDateFormat("mm:ss"));

        ExecutorService threadPool = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    Date date = new Date(finalI * 1000);
                    String result = threadLocal.get().format(date);
                    System.out.println(result);
                }
            });
        }
    }


    // 使用 JDK 8 中提供的 DateTimeFormat
    @Test
    public void test5() {
        // 创建线程池
        ExecutorService threadPool = Executors.newFixedThreadPool(10);
        // 执行 10 次时间格式化
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            // 线程池执行任务
            threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    // 创建时间对象
                    Date date = new Date(finalI * 1000);
                    // 将 Date 转换成 JDK 8 中的时间类型 LocalDateTime
                    LocalDateTime localDateTime =
                            LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
                    // 时间格式化
                    String result = dateTimeFormatter.format(localDateTime);
                    // 打印结果
                    System.out.println(result);
                }
            });
        }
        // 任务执行完之后关闭线程池
        threadPool.shutdown();
    }

    @Test
    public void test6() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
//        simpleDateFormat.format()
    }

}
