package com;

import com.practise.SampleApplication;
import com.practise.dto.MyBean;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Description
 * @Author dan.he
 * @Date 2023/2/24 10:45
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SampleApplication.class)
public class FactoryBeanTest {
    @Autowired
    private ApplicationContext context;

    @Test
    public void test(){
        MyBean myBean = (MyBean) context.getBean("myBean");
        System.out.println("myBean = "+ myBean.getMessage());
        MyBean myBean1 = (MyBean) context.getBean("&myBean");
        System.out.println("myBean1 = "+ myBean1.getMessage());
        System.out.println("myBean.equals(myBean1) ="+ myBean.equals(myBean1));
        System.out.println("****"+ myBean);
        System.out.println("****"+ myBean1);
    }
}
