package com.company.springdemo.controller.init;

import com.company.springdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

/**
 * @Auther: whs
 * @Date: 2018/9/29 10:51
 * @Description:
 */
@Component
public class InitController {

    @Autowired
    private UserService userService;

    @Autowired
    private RedisTemplate redisTemplate;

    public InitController() {
        System.out.println("此时b还未被注入: userService = " + userService);
    }

    @PostConstruct
    private void init() {
        System.out.println("@PostConstruct将在依赖注入完成后被自动调用: userService = " + userService);
    }

//    @PostConstruct
    private void initRedisTest() {
        new Thread(() -> {
            ValueOperations<String, Integer> redis = redisTemplate.opsForValue();
            for (int i = 0; i < 100; i++) {
                int num = redis.get("num").intValue();
                num = num + 1;
                redis.set("num", num);
                System.out.println(redis.get("num"));
            }
        }).start();

        new Thread(() -> {
            ValueOperations<String, Integer> redis = redisTemplate.opsForValue();
            for (int i = 0; i < 100; i++) {
                int num = redis.get("num").intValue();
                num = num + 1;
                redis.set("num", num);
                System.out.println(redis.get("num"));
            }
        }).start();


    }

    @PostConstruct
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    //异常处理与回滚，方法不能为private
    public void init2() {
        Thread thread = new Thread(() -> {
//            while (true){
//                System.out.println("用事务处理业务逻辑，读取数据库、接收消息等，异常则回滚");
//            }
        });
        thread.start();
    }

}
