package com.practise.lock;

import java.util.Arrays;
import java.util.BitSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.LongAccumulator;
import java.util.concurrent.locks.StampedLock;

/**
 * @Description
 * @Author ellie
 * @Date 2022/2/12 1:46 下午
 **/
public class StampedLockService {
    private double x;
    private double y;

    public StampedLockService(double x, double y) {
        this.x = x;
        this.y = y;
    }

    private final StampedLock sl = new StampedLock();

    public void move(double deltaX, double deltaY) {
        long stamp = sl.writeLock();
        try {
            x += deltaX;
            y += deltaY;
        } finally {
            sl.unlockWrite(stamp);
        }
    }

    public double distanceFromOrigin() {
        long stamp = sl.tryOptimisticRead();
        double currentX = x;
        double currentY = y;
        if (!sl.validate(stamp)) {
            stamp = sl.readLock();
            try {
                currentX = x;
                currentY = y;
            } finally {
                sl.unlockRead(stamp);
            }
        }
        return Math.sqrt(currentX * currentX + currentY * currentY);
    }

    public void moveIfAtOrigin(double newX, double newY) {
        long stamp = sl.readLock();
        try {
            while (x == 0.0 && y == 0.0) {
                long ws = sl.tryConvertToWriteLock(stamp);
                if (ws != 0L) {
                    stamp = ws;
                    x = newX;
                    y = newY;
                    break;
                } else {
                    sl.unlockRead(stamp);
                    stamp = sl.writeLock();
                }
            }
        } finally {
            sl.unlock(stamp);
        }
    }

    public void demo() {
        StampedLock lock = new StampedLock();
        Balance b = new Balance(10000);
        Runnable w = () -> {
            long stamp = lock.writeLock();
            b.setAccount(b.getAccount() + 1000);
            System.out.println("Write: "+b.getAccount());
            lock.unlockWrite(stamp);
        };

        Runnable r = () ->{
          long stamp = lock.tryOptimisticRead();
          if( !lock.validate(stamp)){
              stamp = lock.readLock();
              try{
                  System.out.println("Read: "+ b.getAccount());
              }finally {
                  lock.unlockRead(stamp);
              }
          }else {
              System.out.println("Optimistic read fails");
          }
        };

        ExecutorService executor = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 50; i++) {
            executor.submit(w);
            executor.submit(r);
        }
    }

    static class Balance {
        double account;

        public Balance(double account) {
            this.account = account;
        }

        public double getAccount() {
            return account;
        }

        public void setAccount(double account) {
            this.account = account;
        }
    }

    public void accumulator() throws InterruptedException {
        LongAccumulator balance = new LongAccumulator(Long::sum,10000L);
        Runnable w = () -> balance.accumulate(1000L);

        ExecutorService executor = Executors.newFixedThreadPool(50);
        for (int i = 0; i < 50; i++) {
            executor.submit(w);
        }

        executor.shutdown();
        if( executor.awaitTermination(1000L, TimeUnit.MILLISECONDS)){
            System.out.println("Balance: "+ balance.get());
        }
        assert balance.get() == 60000L;
    }

    public void arrayBinarySearch(){
        int[] x = new int[]{1,2,4,8};
        int index = Arrays.binarySearch(x,3);
        System.out.println("index:"+ index);
        System.out.println("~index:"+ ~index);

        index = Arrays.binarySearch(x, 4);
        System.out.println("index:"+ index);

        index = Arrays.binarySearch(x,5);
        System.out.println("index:"+ index);
        System.out.println("~index:"+ ~index);

    }

    public void bitSet(){
        BitSet bs1 = new BitSet();
        bs1.set(0);
        bs1.set(2);
        bs1.set(4);
        System.out.println("bs1 :"+bs1);

        BitSet bs2 = new BitSet();
        bs2.set(1);
        bs2.set(2);
        bs2.set(3);
        System.out.println("bs2:"+ bs2);

        bs2.xor(bs1);
        System.out.println("xor:"+ bs2);
        bs2.flip(2);
        System.out.println("flip:"+ bs2);
    }

    public void phaser(){
        Phaser phaser = new Phaser(50);
        Runnable r = () ->{
            System.out.println("phase-0");
            phaser.arriveAndAwaitAdvance();
            System.out.println("phase-1");
            phaser.arriveAndAwaitAdvance();
            System.out.println("phase-2");
            phaser.arriveAndDeregister();
        };

        ExecutorService executor = Executors.newFixedThreadPool(50);
        for (int i = 0; i < 50; i++) {
            executor.submit(r);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        StampedLockService stampedLockService = new StampedLockService(1.2, 3.4);
        stampedLockService.move(-1.2, -3.4);
        System.out.println("x:" + stampedLockService.x + " y:" + stampedLockService.y);

        stampedLockService.moveIfAtOrigin(6.6, 9.9);
        System.out.println("x:" + stampedLockService.x + " y:" + stampedLockService.y);

        double distanceFromOrigin = stampedLockService.distanceFromOrigin();
        System.out.println("distanceFromOrigin:" + distanceFromOrigin);

        System.out.println("start execute ----------------------");
//        stampedLockService.demo();

        stampedLockService.accumulator();
        stampedLockService.arrayBinarySearch();
        stampedLockService.bitSet();
        stampedLockService.phaser();
    }
}
