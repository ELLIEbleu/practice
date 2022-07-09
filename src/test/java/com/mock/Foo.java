package com.mock;

public class Foo {
    private String name;

    public String say(){
        System.out.println("In foo execute say ");
        return "say";
    }

    public String someMethod() {
        System.out.println("In Foo do someMethod func");
        return "some";
    }
}
