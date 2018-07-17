package com.company.springdemo.common.listenter;

import com.company.springdemo.common.config.PropertiesListenerConfig;
import org.springframework.boot.context.event.ApplicationPreparedEvent;
import org.springframework.context.ApplicationListener;


/**
 * 配置文件监听器，用来加载自定义配置文件
 *
 * @author xuys
 * @date 2018/5/15
 */

public class PropertiesListener implements ApplicationListener<ApplicationPreparedEvent> {

    private String[] propertyFileName;

    public PropertiesListener(String[] propertyFileName) {
        this.propertyFileName = propertyFileName;
    }

    @Override
    public void onApplicationEvent(ApplicationPreparedEvent event) {
        PropertiesListenerConfig.loadAllProperties(propertyFileName);
    }
}
