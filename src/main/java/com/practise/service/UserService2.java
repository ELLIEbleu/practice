package com.practise.service;

import com.practise.dto.Person;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * @Description
 * @Author dan.he
 * @Date 2022/7/30 17:31
 **/

@Service
public class UserService2 implements InitializingBean {

    @Autowired
    private OrderServiceAImpl orderServiceA;

    private Person person;


    //初始化
    @Override
    public void afterPropertiesSet() throws Exception {
        Person person = new Person();
        person.setName("test");
        person.setAge(19);
        this.person = person;
    }

    public void test(){
        System.out.println("userService2 test ......");
        System.out.println("person:"+person);
        System.out.println("orderserviceA:"+ orderServiceA);
    }

    public static void main(String[] args) {
        String str= "\"";
        str = str.replace("''","'");
        System.out.println("str :" +str);

        str= "''";
        str = str.replace("''","'");
        System.out.println("str :" +str);
    }
}
