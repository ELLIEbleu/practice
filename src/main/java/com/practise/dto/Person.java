package com.practise.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description
 * @Author dan.he
 * @Date 2022/7/30 17:33
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person {
    private Long id;

    private String name;

    private Integer age;


}
