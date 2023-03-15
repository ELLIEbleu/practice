package com.practise.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @Description
 * @Author dan.he
 * @Date 2022/8/3 16:33
 **/
public class ShopHandler implements InvocationHandler {
    private ShopService shopService;

    public ShopHandler(ShopService shopService){
        this.shopService = shopService;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        before();
        method.invoke(shopService,null);
        after();
        return null;
    }

    public void before(){
        System.out.println("shopHandler before");
    }

    public void after(){
        System.out.println("shopHandler after");
    }
}
