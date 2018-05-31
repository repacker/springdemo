package com.company.springdemo.model;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @Auther: whs
 * @Date: 2018/5/30 20:07
 * @Description: 自定义配置类
 */
@Configuration
@ConfigurationProperties
@PropertySource(value = "classpath:demo.properties",encoding = "utf-8")//防止乱码问题
public class DemoProperties {

    private String userName;

    private String age;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
