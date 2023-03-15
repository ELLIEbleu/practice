package com.practise.service;


import com.practise.util.SpringContextUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Description
 * @Author dan.he
 * @Date 2022/7/28 17:19
 **/
@SpringBootTest
@RunWith(SpringRunner.class)
public class SpringContextTest {

    @Autowired
    private OrderServiceImpl orderService;

    @Test
    public void testGetOrderService(){
        OrderService service = orderService.getOrderService("orderA");
        service.queryList();
        OrderService serviceB = SpringContextUtil.getBean("orderB");
        serviceB.queryList();
    }
}