package com.practise.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

/**
 * @Description
 * @Author dan.he
 * @Date 2022/7/30 18:46
 **/
@EnableAspectJAutoProxy
@Component
@ComponentScan("com.practise")
//@EnableTransactionManagement
public class AppConfig {
}
