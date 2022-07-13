package com.practise.spring.features.config;


import com.practise.dto.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description
 * @Author dan.he
 * @Date 2022/6/20 16:22
 **/
@Configuration
public class FullAppConfig {

    @Bean
    public User user(){
        return new User();
    }

    @Bean
    public String name(User user){
        System.out.println("user.hashCode:"+user.hashCode());
        System.out.println("user().hashCode:"+user().hashCode());
        return "test123";
    }
}
