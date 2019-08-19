package com.company.springdemo.common.utils.customAnnotation;

import java.lang.annotation.*;

/**
 * @description: 该类自定义注解接口
 * @author: whs
 * @date: 2019/08/18 20:45
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited()
public @interface LogAnnotation {
    String desc();
}
