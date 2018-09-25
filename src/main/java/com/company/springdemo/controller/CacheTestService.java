package com.company.springdemo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @Auther: whs
 * @Date: 2018/9/25 11:36
 * @Description:
 */
@Service
public class CacheTestService {

    private final static Logger logger = LoggerFactory.getLogger(CacheTestService.class);

    @Cacheable(value = "getData")
    public String getData() {
        System.out.println("=====看见这句话，表示你没有读取缓存数据====");
        return "当前系统时间搓" + System.currentTimeMillis();
    }

}
