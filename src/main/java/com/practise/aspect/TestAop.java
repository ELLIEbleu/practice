package com.practise.aspect;

import org.springframework.stereotype.Component;

/**
 * @Description
 * @Author dan.he
 * @Date 2022/8/1 17:58
 **/

@Component
public class TestAop {

    @Aop2
    @Aop1
    public void aop(){
        System.out.println("testAop");

    }
}
