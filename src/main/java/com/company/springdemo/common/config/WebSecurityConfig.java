package com.company.springdemo.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author whs
 * @date 2019/4/6 21:05
 * @description: anonymous设置可以允许用户已匿名的方式，不用admin账户登录即可访问接口
 * 参考博客：https://blog.csdn.net/yakson/article/details/80876955
 * actuator安全认证后返回403,处理
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // Configure HttpSecurity as needed (e.g. enable http basic).
//        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.NEVER);
//        http.csrf().disable();
        //注意：为了可以使用 http://${user}:${password}@${host}:${port}/eureka/ 这种方式登录,所以必须是httpBasic,
        // 如果是form方式,不能使用url格式登录
//        http.authorizeRequests().anyRequest().authenticated().and().httpBasic();
        http.authorizeRequests().anyRequest().anonymous();

        // 或者这种方式也可以允许访问信息
//        http.requestMatchers().anyRequest().and().authorizeRequests().
//                antMatchers("/oauth/*").permitAll();

    }

}
