package com.practise.service;

import com.practise.dto.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * @Description
 * @Author dan.he
 * @Date 2022/7/30 17:31
 **/

@Service
public class UserService {

    @Autowired
    private OrderServiceAImpl orderServiceA;

    private Person person;

    //初始化前
    @PostConstruct
    public void initPerson(){
        Person person = new Person();
        person.setName("test");
        person.setAge(19);
        this.person = person;
    }
}
