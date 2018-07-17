package com.company.springdemo.common.test;


import java.io.IOException;
import java.util.Properties;

/**
 * @Auther: whs
 * @Date: 2018/5/31 10:31
 * @Description:
 */
public class Test {

    public static void main(String[] args) throws IOException {
        Properties prop=new Properties();
//        prop.load(new InputStreamReader(SessionState.Client.class.getClassLoader().getResourceAsStream("demo.properties"), "UTF-8"));
        System.out.println(prop.getProperty("age"));
    }

}
