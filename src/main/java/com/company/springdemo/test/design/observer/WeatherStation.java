package com.company.springdemo.test.design.observer;

/**
 * @Auther: whs
 * @Date: 2019/2/15 17:04
 * @Description: 观察站：建立观察者容器，并接收表现者的注入。同时对表现者关注的内容进行通知
 */
public class WeatherStation {

    public static void main(String[] args) {
        WeatherData weatherData = new WeatherData(); // 观察者容器
        CurrentConditionsDisplay currentConditionsDisplay = new CurrentConditionsDisplay(weatherData); // 表现者1注册到观察者容器
        StatisticsDisplay statisticsDisplay = new StatisticsDisplay(weatherData); // 表现者2注册到观察者容器

        weatherData.setMeasurements(0, 0, 0);   // 将观察数据通知给表现者1、表现者2，表现者1、表现者2并进行相应操作
        weatherData.setMeasurements(1, 1, 1);   // 将观察数据通知给表现者1、表现者2，表现者1、表现者2并进行相应操作
    }

}
