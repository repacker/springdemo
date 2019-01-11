package com.company.springdemo.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @author xuys
 * @Description: 拦截器配置
 * @date 2018/5/31
 */
@Component
public class WebAppConfig extends WebMvcConfigurationSupport {

    @Autowired
    UserInterceptor userInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
         //2.用户登录校验
        registry.addInterceptor(userInterceptor)
                .addPathPatterns("/**").excludePathPatterns("/**/api/**", "/**/error/**");
    }
}