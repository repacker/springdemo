package com.company.springdemo.common.utils;

import com.company.springdemo.common.config.PropertiesListenerConfig;

/**
 * @Auther: whs
 * @Date: 2019/2/11 16:20
 * @Description: 这里如果是类(class)时，则会找不到该变量，待确认总结
 */
public interface PropertiesConstant {

    String sender = PropertiesListenerConfig.getProperty("sender");
    String receiver = PropertiesListenerConfig.getProperty("receiver");

}
