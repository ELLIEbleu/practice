package com.practise.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * @Description
 * @Author dan.he
 * @Date 2022/7/30 18:43
 **/

@Aspect
@Component
public class UserServiceAspect {

    @Before("execution(public void com.practise.service.UserService2.test())")
    public void before(){
        System.out.println("userServiceAspect before");
    }
}
