package com.company.springdemo.test.design.observer;

/**
 * @Auther: whs
 * @Date: 2019/2/15 17:03
 * @Description: 表现者1：将自己注入到观察者容器中，并实现表现者操作处理接口，对接收到的监控的信息进行处理
 */
public class CurrentConditionsDisplay implements Observer {

    public CurrentConditionsDisplay(Subject weatherData) {
        weatherData.registerObserver(this);
    }

    @Override
    public void update(float temp, float humidity, float pressure) {
        System.out.println("CurrentConditionsDisplay.update: " + temp + " " + humidity + " " + pressure);
    }

}
