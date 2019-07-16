package com.company.springdemo.test.design.observer;

/**
 * @Auther: whs
 * @Date: 2019/2/15 16:55
 * @Description: 表现者操作处理接口
 */
public interface Observer {

    void update(float temp, float humidity, float pressure);

}
