package com.practise.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Description
 * @Author dan.he
 * @Date 2022/7/28 17:02
 **/
@Component
public class OrderServiceImpl {
    @Autowired
    private Map<String,OrderService> map;

    public OrderService getOrderService(String beanName){
        return map.get(beanName);
    }
}
