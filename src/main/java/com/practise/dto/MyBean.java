package com.practise.dto;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

/**
 * @Description
 * @Author dan.he
 * @Date 2023/2/24 10:42
 **/
@Component
public class MyBean implements FactoryBean {
    private String message;

    public MyBean(){
        this.message = "通过构造方法初始化实例";
    }
    @Override
    public Object getObject() throws Exception {
        MyBean myBean = new MyBean();
//        myBean.message = "通过getObject创建实例";
        System.out.println("通过getObject创建实例");
        return myBean;
    }

    @Override
    public Class<?> getObjectType() {
        return MyBean.class;
    }

    public String getMessage() {
        return message;
    }
}
