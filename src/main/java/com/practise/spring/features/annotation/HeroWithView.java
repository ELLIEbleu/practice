package com.practise.spring.features.annotation;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @Description
 * @Author ellie
 * @Date 2021/9/17 3:01 下午
 **/
public class HeroWithView {
    @JsonView(Views.Public.class)
    private int id;
    @JsonView(Views.Public.class)
    private String name;
    @JsonView(Views.Internal.class)
    private String email;

    public static void main(String[] args) throws Exception {
        HeroWithView hero = new HeroWithView(1, "Jason", "jason@163.com");

        String publicResult = new ObjectMapper().writerWithView(Views.Public.class).writeValueAsString(hero);
        String internalResult = new ObjectMapper().writerWithView(Views.Internal.class).writeValueAsString(hero);
        System.out.println(publicResult);
        System.out.println(internalResult);
    }

    public HeroWithView(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }
}
