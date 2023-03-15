package com.practise.service;


import org.springframework.stereotype.Service;

/**
 * @Description
 * @Author dan.he
 * @Date 2022/7/28 16:59
 **/

@Service("orderA")
public class OrderServiceAImpl implements OrderService{
    @Override
    public void queryList() {
        System.out.println("orderServiceAImpl ....");
    }
}
