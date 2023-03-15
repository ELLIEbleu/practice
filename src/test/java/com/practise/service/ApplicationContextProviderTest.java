package com.practise.service;

import com.practise.config.AppConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Description
 * @Author dan.he
 * @Date 2022/7/30 18:13
 **/

@SpringBootTest
@RunWith(SpringRunner.class)
public class ApplicationContextProviderTest {
    @Autowired
    private ApplicationContextProvider provider;

    @Test
    public void testGetBean(){
        provider.getBean();;
    }

    @Test
    public void test(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        UserService2 userService2 = (UserService2) context.getBean("userService2");
        userService2.test();
    }
}