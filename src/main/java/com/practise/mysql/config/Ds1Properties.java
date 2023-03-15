package com.practise.mysql.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Description
 * @Author dan.he
 * @Date 2022/8/20 22:37
 **/
//@ConfigurationProperties(prefix = "spring.datasource.ds1")
//@Component
//@Data
public class Ds1Properties {

    private String url;

    private String username;

    private String password;

    private String driverClassName;

}