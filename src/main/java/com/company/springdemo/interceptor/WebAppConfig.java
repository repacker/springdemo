package com.company.springdemo.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author xuys
 * @Description: 拦截器配置
 * @date 2018/5/31
 */
@Component
public class WebAppConfig implements WebMvcConfigurer {

    @Autowired
    UserInterceptor userInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
         //2.用户登录校验
        registry.addInterceptor(userInterceptor)
                .addPathPatterns("/**").excludePathPatterns("/**/api/**", "/**/error/**");
    }
}