package com.company.springdemo.common.utils.redis;

import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author whs
 * @date 2019/4/29 00:15
 * @description：使用该线程，并发使用redis锁，处理业务逻辑
 */
public class ThreadB extends Thread {
    private MsService service;
    private RedisTemplate<String, Object> redisTemplate;
    private String key;

    public ThreadB(MsService service, RedisTemplate<String, Object> redisTemplate, String key) {
        this.service = service;
        this.redisTemplate = redisTemplate;
        this.key = key;
    }

    @Override
    public void run() {
        service.seckill(redisTemplate, key);
    }
}
