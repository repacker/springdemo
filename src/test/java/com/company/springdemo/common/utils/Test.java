package com.company.springdemo.common.utils;

import com.company.springdemo.SpringDemoApplication;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @Auther: whs
 * @Date: 2018/7/23 10:42
 * @Description:测试springboot启动注解先后顺序bug
 */
@ContextConfiguration(classes = SpringDemoApplication.class)
@EnableAutoConfiguration
@SpringBootTest(classes = SpringDemoApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("dev")//启动配置测试
public class Test extends AbstractTransactionalJUnit4SpringContextTests {
    @Before
    public void setUp() {
        System.out.println("==============================");
    }

    @org.junit.Test
    public void test(){
        System.out.println("1111");
    }
}
