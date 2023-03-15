package com.practise.proxy;

import java.lang.reflect.Proxy;

/**
 * @Description
 * @Author dan.he
 * @Date 2022/8/3 16:36
 **/
public class ShopMain {
    public static void main(String[] args) {
        ShopService shopService = new ShopServiceImpl();
        ShopHandler shopHandler = new ShopHandler(shopService);
        ShopService service = (ShopService) Proxy.newProxyInstance(shopService.getClass().getClassLoader(),
                new Class[]{ShopService.class},shopHandler);
        System.out.println("class name:"+ service.getClass().getName());
        service.findObject();

    }
}
