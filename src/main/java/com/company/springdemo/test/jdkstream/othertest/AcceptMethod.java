package com.company.springdemo.test.jdkstream.othertest;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

/**
 * @description: 该类 5.0 JDK8 双冒号及借口实现方法用法
 * @author: whs
 * @date: 2019/07/14 15:59
 */
public class AcceptMethod {

    public static void printValue(String str) {
        System.out.println("print value : " + str);
    }

    public static void main(String[] args) {
        System.out.println("以前的代码一般是如此的：");
        List<String> al = Arrays.asList("a", "b", "c", "d");
        for (String a : al) {
            AcceptMethod.printValue(a);
        }
        //下面的for each循环和上面的循环是等价的
        al.forEach(x -> {
            AcceptMethod.printValue(x);
        });
        System.out.println("===现在JDK双冒号是:===");
        al.forEach(AcceptMethod::printValue);
        //下面的方法和上面等价的
        Consumer<String> methodParam = AcceptMethod::printValue; //方法参数
        al.forEach(x -> methodParam.accept(x));//方法执行accept

    }
}
