package com.practise.spring.features.config;

import com.practise.dto.User;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Description
 * @Author dan.he
 * @Date 2022/7/11 14:41
 **/
public class FullAppConfigTest {
    @Test
    public void fullTest(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(FullAppConfig.class);
        User user = context.getBean(User.class);
        System.out.println("hashCode:"+user.hashCode());
        context.close();
    }

    @Test
    public void liteTest(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(LiteAppConfig.class);
        User user = context.getBean(User.class);
        System.out.println(user.hashCode());
        context.close();
    }
}