package com.practise.dto;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;

/**
 * @Description
 * @Author dan.he
 * @Date 2022/8/2 16:23
 **/
public class BeanScopeConfig {
    @Scope(value = "prototype")
    @Bean
    public Car car(){
        return new Car();
    }

    @Bean
    public Train train(){
        return new Train();
    }

    @Lazy
    @Bean
    public Flight flight(){
        return new Flight();
    }

    public static void print(){
        System.out.println("BeanScopeConfig static print");
    }

    static {
        System.out.println("BeanScopeConfig 静态代码块");
    }
}
