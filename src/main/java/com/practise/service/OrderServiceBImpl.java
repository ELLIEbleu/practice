package com.practise.service;

import org.springframework.stereotype.Service;

/**
 * @Description
 * @Author dan.he
 * @Date 2022/7/28 17:00
 **/
@Service("orderB")
public class OrderServiceBImpl implements OrderService{
    @Override
    public void queryList() {

        System.out.println("orderServiceBImpl .........");
    }
}
