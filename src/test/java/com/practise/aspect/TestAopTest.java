package com.practise.aspect;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * @Description
 * @Author dan.he
 * @Date 2022/8/1 18:00
 **/

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestAopTest {
    @Autowired
    private TestAop testAop;

    @Test
    public void testAop() {
        testAop.aop();
    }
}