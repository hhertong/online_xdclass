package net.xdclass.online_xdclass.utils;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
@Data
public class BaseCache {

    private Cache<String,Object> tenMinuteCache = CacheBuilder.newBuilder()

            //设置缓存初始大小，应该合理设置，后续会扩容
            .initialCapacity(10)
            //最大值
            .maximumSize(100)
            //并发数设置
            .concurrencyLevel(5)

            //缓存过期时间，写入后10分钟过期
            .expireAfterWrite(600,TimeUnit.SECONDS)

            //统计缓存命中率
            .recordStats()

            .build();

    private Cache<String,Object> oneHourCache = CacheBuilder.newBuilder()

            //设置缓存初始大小，应该合理设置，后续会扩容
            .initialCapacity(10)
            //最大值
            .maximumSize(100)
            //并发数设置
            .concurrencyLevel(5)

            //缓存过期时间，写入后60分钟过期
            .expireAfterWrite(3600,TimeUnit.SECONDS)

            //统计缓存命中率
            .recordStats()

            .build();



}
