package com;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Test {


    public static void main(String[] args) {

            List<Integer>  list  = new ArrayList<Integer>(){
            {
                add(1);
                add(12);
                add(3);
                add(5);
                add(6);
                add(84);
                add(78);
                add(1);
                add(5);
                add(47);
                add(567);
                add(74);
                add(76);
                add(47);
                add(67);
                add(47);
                add(567);
                add(74);
                add(457);
                add(4567);
                add(7456);
                add(457);
                add(745);
                add(567);
                add(7);
                add(67);
                add(73);
                add(72);
                add(71);
                add(73);
                add(72);
                add(17);
                add(72);
                add(72);
                add(72);
                add(72);
                add(772);
                add(72);
                add(7524);
                add(72);
                add(72);
                add(72);
                add(762);
                add(752);
                add(72);
                add(742);
                add(72);
                add(72);
                add(732);
                add(712);
            }
        };

        Thread thread1 = new Thread(new sortRunner(list));
        Thread thread2 = new Thread(new sortRunner(list));
        Thread thread3 = new Thread(new sortRunner(list));
        Thread thread4 = new Thread(new sortRunner(list));
        Thread thread5 = new Thread(new sortRunner(list));
        Thread thread6 = new Thread(new sortRunner(list));
        Thread thread7 = new Thread(new sortRunner(list));

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();
        thread6.start();
        thread7.start();



    }

    static class sortRunner implements Runnable{

        public sortRunner(List<Integer> list) {
            this.list = list;
            System.out.println("list:size"+list.size());
        }

        List<Integer> list ;

        @Override
        public void run() {

//            Collections.sort(list);

//            list.stream().sorted(Comparator.comparingInt(Integer::intValue));
            list.sort(Comparator.comparingInt(Integer::intValue));
            System.out.println(Thread.currentThread().getName() + " 执行完毕" );

        }
    }


}
