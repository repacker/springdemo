package com.company.springdemo.test.design.decorator;

/**
 * @description: 该类运行测试装饰着模式
 * @author: whs
 * @date: 2019/09/09 18:08
 */
public class PersonDemo {
    public static void main(String[] args) {
        PersonBefore p = new PersonBefore();
        //p.eat();//这个是需求1.0
        PersonNow sp = new PersonNow(p);
        sp.superChifan();
    }
}
