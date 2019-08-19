package com.company.springdemo.common.utils.customAnnotation;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 该类使用自定义注解
 * @author: whs
 * @date: 2019/08/18 21:00
 */
@RestController
public class TestController{
    // 防止乱码
    @LogAnnotation(desc="我的自定义注解方法参数传入信息")
    @RequestMapping(value="/annotation/hello", produces = "text/html;charset=UTF-8")
    public String hello() {
        return "我的自定义注解使用类";
    }
}