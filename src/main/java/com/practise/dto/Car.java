package com.practise.dto;

/**
 * @Description
 * @Author dan.he
 * @Date 2022/8/2 16:26
 **/
public class Car {
    public Car(){
        System.out.println("Car 无参构造函数");
    }

    public static void print(){
        System.out.println("Car static print");
    }

    static {
        System.out.println("Car 静态代码块");
    }
}
