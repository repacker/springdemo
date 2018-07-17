package com.company.springdemo.common.config;

import org.springframework.beans.BeansException;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 读取系统配置信息
 *
 * @author xuys
 * @date 2018/5/15
 */
public class PropertiesListenerConfig {
    public static Map<String, String> propertiesMap = new HashMap<>();

    private static void processProperties(Properties props) throws BeansException {
        for (Object key : props.keySet()) {
            String keyStr = key.toString();
            try {
                // PropertiesLoaderUtils的默认编码是ISO-8859-1,在这里转码一下
                propertiesMap.put(keyStr, new String(props.getProperty(keyStr).getBytes("ISO-8859-1"), "utf-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void loadAllProperties(String[] propertyFileNames) {
        try {
            Properties properties = null;
            for (String propertyFileName : propertyFileNames) {
                properties = PropertiesLoaderUtils.loadAllProperties(propertyFileName);
                processProperties(properties);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getProperty(String name) {
        return propertiesMap.get(name).toString();
    }

    public static Map<String, String> getAllProperty() {
        return propertiesMap;
    }
}
