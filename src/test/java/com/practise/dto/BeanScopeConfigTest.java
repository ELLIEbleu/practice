package com.practise.dto;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * @Description
 * @Author dan.he
 * @Date 2022/8/2 16:28
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class BeanScopeConfigTest {

    @Test
    public void test() {
        ApplicationContext context = new AnnotationConfigApplicationContext();
        boolean exists = context.containsBean("beanScopeConfig");
        System.out.println("exist:" + exists);
        exists = context.containsBean("car");
        System.out.println("exist:" + exists);

        BeanScopeConfig.print();
        Car.print();

        context = new AnnotationConfigApplicationContext(BeanScopeConfig.class);
        Object object = context.getBean("car");

        Car car = (Car) object;
        Flight flight = (Flight) context.getBean("flight");

        BeanScopeConfig beanScopeConfig = (BeanScopeConfig) context.getBean("beanScopeConfig");

    }
}