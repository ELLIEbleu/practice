package com.practise.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.NoArgsConstructor;

/**
 * @Description
 * @Author ellie
 * @Date 2021/9/17 2:25 下午
 **/
//@NoArgsConstructor
public class Player {
    private String name;   //无 getter setter 方法

    @JsonCreator
    public Player(@JsonProperty("name") String name) {
        this.name = name;
    }
}
