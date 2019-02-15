package com.company.springdemo.test.design.observer;

/**
 * @Auther: whs
 * @Date: 2019/2/15 16:53
 * @Description: 容器操作者：观察者容器的操作接口，观察者容器的注册、删除、通知
 */
public interface Subject {

    void registerObserver(Observer o);

    void removeObserver(Observer o);

    void notifyObserver();

}
