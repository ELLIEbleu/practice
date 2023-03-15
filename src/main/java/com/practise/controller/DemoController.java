package com.practise.controller;

import com.practise.service.OrderServiceImpl;
import com.practise.service.StuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

/**
 * @Description
 * @Author dan.he
 * @Date 2022/7/28 17:12
 **/
@RestController
public class DemoController {
    @Autowired
    private OrderServiceImpl orderService;
    @Autowired
    private StuService stuService;

    @GetMapping("/demo")
    public void test(){
        orderService.getOrderService("");
    }

    @PostMapping("/stu")
    public void saveStu() throws SQLException {
        stuService.saveStu();
    }
}
