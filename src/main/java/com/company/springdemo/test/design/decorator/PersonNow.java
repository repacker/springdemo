package com.company.springdemo.test.design.decorator;

/**
 * @description: 该类 装饰类
 *  (要去增强目标对象的类)
 *  装饰类通常会通过构造方法接收被装饰的对象。
 *  并基于被装饰的对象的功能，提供更强的功能。
 * @author: whs
 * @date: 2019/09/09 18:06
 */
public class PersonNow {
    private PersonBefore p;

    PersonNow(PersonBefore p) {
        this.p = p;
    }

    public void superChifan() {
        //简单扩展
        System.out.println("开胃酒");
        p.eat();
        System.out.println("甜点");
        System.out.println("来一根烟");
    }
}
