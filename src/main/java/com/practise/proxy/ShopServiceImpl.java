package com.practise.proxy;

import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.context.annotation.ImportSelector;

/**
 * @Description
 * @Author dan.he
 * @Date 2022/8/3 16:32
 **/
public class ShopServiceImpl implements ShopService  {
    @Override
    public void findObject() {
        System.out.println("shopService findObject........");
    }
}
