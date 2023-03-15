package com.practise.cache;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

/**
 * @Description
 * @Author dan.he
 * @Date 2022/8/3 15:26
 **/
@Slf4j
@Component
public class CaffeineCache {

    @Cacheable(value = "caffineTestOne")
    public String getKey(String key){
        log.info("从本次从逻辑获取 key=[{}]",key);
        return key;
    }
}
