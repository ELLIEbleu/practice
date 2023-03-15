package com.practise.aspect;

/**
 * @Description
 * @Author dan.he
 * @Date 2022/8/1 16:35
 **/

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Aop2 {
}
