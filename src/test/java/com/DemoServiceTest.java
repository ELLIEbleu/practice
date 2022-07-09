package com;

import com.service.FooClient;

public class DemoServiceTest {

    public void test(){
        String str = null;
        int a;

        for (int i = 0; i <3 ; i++) {
            str.concat(String.valueOf(i));
            a= i;
        }

    }

    public static void main(String[] args) {
        Class<?>[] additionalInterfaces = FooClient.class.getInterfaces();
        for (Class<?> additionalInterface : additionalInterfaces) {
            System.out.println(additionalInterface);
        }

//        System.arraycopy()
    }
}
