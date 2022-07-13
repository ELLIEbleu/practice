package com.util;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.gson.Gson;
import com.practise.dto.PlayStar;
import com.practise.dto.Player;
import com.practise.dto.WaterSensor;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description
 * @Author ellie
 * @Date 2021/9/17 1:29 下午
 **/
public class JacksonServiceTest {

    @Test
    public void jsonPropertyOrderTest() throws JsonProcessingException {
        WaterSensor waterSensor = WaterSensor.of("1112",32L,54L);
        ObjectMapper mapper = new ObjectMapper();

        String jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(waterSensor);
        System.out.println("json:"+jsonString);
    }

    @Test
    public void testObject2JSON() throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        PlayStar playStar = mapper.readValue(new File("/Users/ELLIE/Study/new-demo/src/test/resources/player.json"),PlayStar.class);

        String json = mapper.writeValueAsString(playStar);

        System.out.println("json:"+json);

        PlayStar playStar1 = mapper.readValue(json,PlayStar.class);
        System.out.println("name:"+playStar1.getName());
    }

    @Test
    public void testUnknowProperties() throws JsonProcessingException {
        String json = "{\n" +
                "\"birthday\": \"2000-01-12\",\n" +
                "\"key:\":\"test\",\n"+
                "  \"name\": \"乔丹\",\n" +
                "  \"age\": 45,\n" +
                "  \"hobbies\": [\n" +
                "    \"高尔夫球\",\n" +
                "    \"棒球\"\n" +
                "  ],\n" +
                "  \"friends\": [\n" +
                "    \"kobe\",\n" +
                "    \"curry\",\n" +
                "    \"james\"\n" +
                "  ],\n" +
                "  \"salary\": {\n" +
                "    \"2000\": 10000000,\n" +
                "    \"2010\": 62000000,\n" +
                "    \"2020\": 112400000\n" +
                "  }\n" +
                "}";

        //
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        PlayStar playStar = mapper.readValue(json,PlayStar.class);
        System.out.println("name:"+playStar.getName());
        System.out.println("birthday:"+ playStar.getBirthday());

        Gson gson = new Gson();
        PlayStar playStar1 = gson.fromJson(json,PlayStar.class);
        System.out.println("name1:"+playStar1.getName());
    }

    @Test
    public void testEmptyBean() throws JsonProcessingException {
        Player player = new Player("test");

        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        String json = mapper.writeValueAsString(player);
        System.out.println("json:" + json);

        Gson gson = new Gson();
        String json1 = gson.toJson(player);
        System.out.println("json1:"+json1);
    }

    @Test
    public void testDateFormat() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Map temp = new HashMap();
        temp.put("now",new Date());
        String s = mapper.writeValueAsString(temp);
        System.out.println(s);


        mapper.disable(SerializationFeature.WRITE_DATE_KEYS_AS_TIMESTAMPS);
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
        String s1 = mapper.writeValueAsString(temp);
        System.out.println(s1);
    }
}
