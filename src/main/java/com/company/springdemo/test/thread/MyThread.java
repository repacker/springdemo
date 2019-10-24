package com.company.springdemo.test.thread;

/**
 * @description: 该类 经典测试线程安全的例子，隐藏static、synchronized的使用案例
 * 1、该类线程不安全，如果static换成字符串类型，则可以保证线程安全。具体见笔记why？
 * @author: whs
 * @date: 2019/09/10 16:45
 */
public class MyThread implements Runnable {

    static Integer i = 0;

    @Override
    public void run() {
        while (true) {
            synchronized (i) {
                if (i < 300) {
                    i++;
                    String currentThreadName = Thread.currentThread().getName();
                    System.out.println(currentThreadName + " i = " + i);
                } else {
                    break;
                }
            }
        }

    }

    public static void main(String[] args) {
        Thread t1 = new Thread(new MyThread());
        Thread t2 = new Thread(new MyThread());
        t1.start();
        t2.start();
    }

}