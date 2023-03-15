package com.practise.cache;


import java.util.LinkedHashMap;

/**
 * @Description
 * @Author dan.he
 * @Date 2022/4/11 3:42 下午
 **/
public class CustomizeCache {
    private LinkedHashMap<Integer,Integer> cache = new LinkedHashMap<>();
    public CustomizeCache(int capacity) {

    }

    public int get(int key) {
        //todo
        return 0;
    }

    public void put(int key, int value) {

    }

    public void print() {

    }

    public static void main(String[] args) {
        CustomizeCache customizeCache = new CustomizeCache(2);
        customizeCache.put(1, 1);
        customizeCache.print();

        customizeCache.put(2, 2);
        customizeCache.print();

        int ret = customizeCache.get(1);
        System.out.println("ret:" + ret);

        customizeCache.put(3, 3);
        customizeCache.print();

        ret = customizeCache.get(2);
        System.out.println("ret:" + ret);

        customizeCache.put(4, 4);
        customizeCache.print();

        ret = customizeCache.get(1);
        System.out.println("ret:" + ret);

        ret = customizeCache.get(3);
        System.out.println("ret:" + ret);

        ret = customizeCache.get(4);
        System.out.println("ret:" + ret);
    }
}
