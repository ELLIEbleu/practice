package com.practise.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

/**
 * @Description
 * @Author dan.he
 * @Date 2022/8/3 14:25
 **/

@Configuration
public class CacheConfig {

    @Bean("caffeineCacheManager")
    public CacheManager cacheManager(){
        CaffeineCacheManager cacheManager = new CaffeineCacheManager();
        cacheManager.setCaffeine(Caffeine.newBuilder()
                .expireAfterAccess(60, TimeUnit.SECONDS)
                .initialCapacity(100)
                .maximumSize(1000));
        return cacheManager;
    }



}
