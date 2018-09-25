package com.company.springdemo;

import com.company.springdemo.controller.CacheTestService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringdemoApplicationTests {

	@Autowired
	CacheTestService cacheTestService;

	@Autowired
	private RedisTemplate redisTemplate;

	@Test
	//直接使用redisTemplate存取字符串
	public void setAndGet() {
		redisTemplate.opsForValue().set("test:set", "testValue1");
		Assert.assertEquals("testValue1", redisTemplate.opsForValue().get("test:set"));
	}


	@Test
	public void cacheTest() {
		for (int i=0;i<5;i++){
			System.out.println(cacheTestService.getData());
			try{
				Thread.sleep(1000);
			}catch (Exception e){
				e.printStackTrace();
			}
		}
	}

	@Test
	public void contextLoads() {
		System.out.println("hello word!");
	}

}
