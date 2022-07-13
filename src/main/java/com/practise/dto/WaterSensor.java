package com.practise.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description
 * @Author ellie
 * @Date 2021/9/16 2:17 下午
 **/

@Data
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
@JsonPropertyOrder({"ac","id","ts"})
//@JsonPropertyOrder(alphabetic = true)
public class WaterSensor {
    private String id;
    @JsonProperty("name")
    private Long ac;
    private Long ts;


//    public WaterSensor(String id, Long ts, Long vc) {
//        this.id = id;
//        this.ts = ts;
//        this.vc = vc;
//    }
//
//    public static WaterSensor newInstance(String id, Long ts, Long vc) {
//        return new WaterSensor(id,ts,vc);
//    }


    public static void main(String[] args) throws JsonProcessingException {
        //GSON
        WaterSensor waterSensor = WaterSensor.of("1112",32L,54L);
        System.out.println((waterSensor));

        //JACKSON
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(waterSensor);
        System.out.println("json:"+json);
    }
}
