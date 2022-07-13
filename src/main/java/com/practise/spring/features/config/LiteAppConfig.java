package com.practise.spring.features.config;

import com.practise.dto.User;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @Description
 * @Author dan.he
 * @Date 2022/6/20 16:47
 **/
@Component
public class LiteAppConfig {

    @Bean
    private User user(){
        System.out.println("uer() 方法执行");
        return new User();
    }

    @Bean
    public String name(User user){
        System.out.println("name(User user)方法执行");
        System.out.println(user.hashCode());
        System.out.println("再次调用user()方法:"+ user().hashCode());
        return "test123";
    }
}
