package com.company.springdemo.common.config;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Auther: whs
 * @Date: 2018/6/7 18:02
 * @Description: Cors的跨域处理，前端访问暂时不可用
 */
//@Configuration
public class DomainWebAppConfigurer implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**");
    }

}
