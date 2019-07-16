package com.company.springdemo.test;

import org.junit.Test;

/**
 * @Auther: whs
 * @Date: 2019/2/20 23:23
 * @Description: 
 */
public class TestDemo {

    @Test
    public void demoTest() {
        String str1 = "234";
        String str2 = "";
        System.out.println(str1.substring(0, 0) == str2);
    }

}
