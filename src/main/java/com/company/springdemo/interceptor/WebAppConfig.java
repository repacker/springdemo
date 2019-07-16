package com.company.springdemo.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @author whs
 * @Description: 拦截器配置
 * @date 2018/5/31
 */
@Component
public class WebAppConfig extends WebMvcConfigurationSupport {

    @Autowired
    UserInterceptor userInterceptor;
    @Autowired
    RequestCheckInterceptor requestCheckInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //1.非法字符校验
        registry.addInterceptor(requestCheckInterceptor)
                .addPathPatterns("/**").excludePathPatterns("/**/api/**", "/**/error/**");
        //2.用户登录校验
        registry.addInterceptor(userInterceptor)
                .addPathPatterns("/**").excludePathPatterns("/**/api/**", "/**/error/**");
        //3.对外api
        // 这里处理验签等逻辑

    }
}