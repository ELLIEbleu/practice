package com.practise.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Description
 * @Author ellie
 * @Date 2021/9/17 1:56 下午
 **/

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "newInstance")
public class PlayStar {
    private String name;
    private Integer age;
    private String[] hobbies;
    private List<String> friends;
    private Map<String, BigDecimal> salary;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Date birthday;

}
