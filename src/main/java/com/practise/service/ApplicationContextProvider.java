package com.practise.service;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.lang.reflect.Field;
import java.lang.reflect.Method;


/**
 * @Description
 * @Author dan.he
 * @Date 2022/7/30 17:41
 **/
@Component
class ApplicationContextProvider implements ApplicationContextAware {
    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public void getBean() {
        for (Field field : UserService2.class.getFields()) {
            if (field.isAnnotationPresent(Autowired.class)) {
//                field.set()
            }
        }

        for (Method method : UserService2.class.getMethods()) {
            if (method.isAnnotationPresent(PostConstruct.class)) {
//                method.invoke();
            }
        }

        UserService2 userService2 = (UserService2) applicationContext.getBean("userService2");

        if (userService2 instanceof InitializingBean) {

        }

        userService2.test();
    }

}