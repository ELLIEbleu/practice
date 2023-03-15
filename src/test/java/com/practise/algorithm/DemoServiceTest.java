package com.practise.algorithm;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Description
 * @Author dan.he
 * @Date 2022/7/15 16:44
 **/
public class DemoServiceTest {
    public static void main(String[] args) {
        System.out.println("count:"+ThreadLocalRandom.current().nextInt(4));
    }
}