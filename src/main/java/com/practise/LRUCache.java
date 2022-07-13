package com.practise;


import java.util.LinkedHashMap;

/**
 * @Description
 * @Author dan.he
 * @Date 2022/4/11 3:42 下午
 **/
public class LRUCache {
    private LinkedHashMap<Integer,Integer> cache = new LinkedHashMap<>();
    public LRUCache(int capacity) {

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
        LRUCache lruCache = new LRUCache(2);
        lruCache.put(1, 1);
        lruCache.print();

        lruCache.put(2, 2);
        lruCache.print();

        int ret = lruCache.get(1);
        System.out.println("ret:" + ret);

        lruCache.put(3, 3);
        lruCache.print();

        ret = lruCache.get(2);
        System.out.println("ret:" + ret);

        lruCache.put(4, 4);
        lruCache.print();

        ret = lruCache.get(1);
        System.out.println("ret:" + ret);

        ret = lruCache.get(3);
        System.out.println("ret:" + ret);

        ret = lruCache.get(4);
        System.out.println("ret:" + ret);
    }
}
