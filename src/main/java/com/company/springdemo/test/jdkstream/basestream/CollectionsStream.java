package com.company.springdemo.test.jdkstream.basestream;

import org.slf4j.MDC;

/**
 * @description: 该类操作详见单侧test
 * @author: whs
 * @date: 2020/11/17 19:17
 */
public class CollectionsStream {
    public static void main(String[] args) {
        MDC.put("whs", "whs");
    }
}
