package com.company.springdemo.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @Auther: whs
 * @Date: 2018/7/17 18:02
 * @Description:打印输出加载扫描注射类信息
 */
@Component
public class WebAC implements ApplicationContextAware {

    private final static Logger log = LoggerFactory.getLogger(WebAC.class);

    private static ApplicationContext webApplicationContext;

    @Override
    public void setApplicationContext(ApplicationContext arg0) throws BeansException {

        webApplicationContext = arg0;
        log.info("##@@ WebAC - 容器执行了回调方法，此容器中共有bean：" + webApplicationContext.getBeanDefinitionCount());
        System.out.println("##@@ WebACl 打印：WebApplicationContext中共有bean : " + webApplicationContext.getBeanDefinitionCount());
        for (int i = 0; i < webApplicationContext.getBeanDefinitionCount(); i++) {
            String className = webApplicationContext.getBean(webApplicationContext.getBeanDefinitionNames()[i]).getClass().getName();
            if (className.startsWith("com.company") || className.startsWith("com.sun.proxy"))
                System.out.println("##@@ " + webApplicationContext.getBeanDefinitionNames()[i] + " = " + className);
        }
    }

    public static ApplicationContext getWebApplicationContext() {
        return webApplicationContext;
    }

    public static Object getBean(String beanName) {
        return webApplicationContext.getBean(beanName);
    }

    public static <T> T getBean(Class<T> className) {
        return webApplicationContext.getBean(className);
    }

    public WebAC() {
        log.info("##@@ WebAC created!...");
    }

}
