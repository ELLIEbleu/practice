package com.practise.cache;

import com.github.benmanes.caffeine.cache.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @Description
 * @Author dan.he
 * @Date 2022/8/3 14:55
 **/

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class CacheTest {
    @Autowired
    private CaffeineCache cache;

    /**
     * 缓存填充：手动
     */
    @Test
    public void test(){
        Cache<String,String> cache = Caffeine.newBuilder()
                .maximumSize(100)
                .expireAfterAccess(100L, TimeUnit.SECONDS)
                .build();

        cache.put("c1","c1");
        //获取缓存值，如果为空，返回null
        String k = "test";
        log.info("cacheTest present： [{}] -> [{}]", k, cache.getIfPresent(k));
        //获取返回值，如果为空，则运行后面表达式，存入该缓存
        log.info("cacheTest default： [{}] -> [{}]", k, cache.get(k, this::buildLoader));
        log.info("cacheTest present： [{}] -> [{}]", k, cache.getIfPresent(k));
        //清除缓存
        cache.invalidate(k);
        log.info("cacheTest present： [{}] -> [{}]", k, cache.getIfPresent(k));

    }

    public String buildLoader(String key){
        return key +"default";
    }

    /**
     * 缓存加载：同步
     * 使用CacheLoader进行缓存存储管理
     * 默认情况下，getAll()将会对缓存中没有值的key分别调用CacheLoader.load方法来构建缓存的值（build中的表达式）
     * CacheLoader.load()
     */
    @Test
    public void loadingCacheTest(){
        LoadingCache<String,String> loadingCache = Caffeine.newBuilder()
                .maximumSize(100)
                .expireAfterAccess(100L,TimeUnit.SECONDS)
                .build(this::buildLoader);
        loadingCache.put("c1","c1");
        String k = "demo";
        log.info("loadingCacheTest get： [{}] -> [{}]", k, loadingCache.get(k));
        //获取缓存值，如果为空，返回null
        log.info("loadingCacheTest present： [{}] -> [{}]", k, loadingCache.getIfPresent(k));
        //获取返回值，如果为空，则运行后面表达式，存入该缓存
        log.info("loadingCacheTest default： [{}] -> [{}]", k, loadingCache.get(k, this::buildLoader));
        log.info("loadingCacheTest present： [{}] -> [{}]", k, loadingCache.getIfPresent(k));
        loadingCache.invalidate(k);
        log.info("loadingCacheTest present： [{}] -> [{}]", k, loadingCache.getIfPresent(k));
    }

    /**
     * 缓存加载：自动异步
     */
    @Test
    public void asyncLoadingCacheTest() throws ExecutionException, InterruptedException {
        AsyncLoadingCache<String,String> asyncLoadingCache = Caffeine.newBuilder()
                .maximumSize(100)
                .expireAfterAccess(100L,TimeUnit.SECONDS)
                .buildAsync(s -> this.buildLoaderAsync("123").get());

        String k = "demo";
        log.info("asyncLoadingCacheTest get： [{}] -> [{}]", k, asyncLoadingCache.get(k).get());
        //获取返回值，如果为空，则运行后面表达式，存入该缓存
        log.info("asyncLoadingCacheTest default： [{}] -> [{}]", k, asyncLoadingCache.get(k, this::buildLoader).get());
    }

    private CompletableFuture<String> buildLoaderAsync(String key) {
        return CompletableFuture.supplyAsync(() -> key+"async");
    }

    /**
     * 缓存加载：手动异步加载
     */
    @Test
    public void asyncManualCacheTest() throws ExecutionException, InterruptedException {
        //异步加载使用Executor去调用方法并返回一个CompletableFuture。异步加载缓存使用了响应式编程模型。
        //
        //如果要以同步方式调用时，应提供CacheLoader。要以异步表示时，应该提供一个AsyncCacheLoader，并返回一个CompletableFuture。
        AsyncCache<String, String> asyncCache = Caffeine.newBuilder()
                .maximumSize(100)
                .expireAfterAccess(100L, TimeUnit.SECONDS)
                .buildAsync();
        //获取返回值，如果为空，则运行后面表达式，存入该缓存
        String k = "test";
        log.info("asyncManualCacheTest default： [{}] -> [{}]", k, asyncCache.get(k, this::buildLoader).get());
    }

    @Test
    public void testGetKey(){
        String key = cache.getKey("test");
        System.out.println("key:"+ key);
    }
}
