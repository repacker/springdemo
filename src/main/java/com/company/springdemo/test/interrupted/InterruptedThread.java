package com.company.springdemo.test.interrupted;

/**
 * @description: 该类 异常中断补偿处理或者向上抛出 测试
 * @author: whs
 * @date: 2019/09/04 14:16
 */
public class InterruptedThread extends Thread {

    @Override
    public void run() {
        while (true) {
            if (Thread.currentThread().isInterrupted()) {
                System.out.println("Someone interrupted me.");
            } else {
                System.out.println("Thread is Going...");
            }
        }
//        try {
//            Thread.sleep(20000);
//        } catch (InterruptedException e) {
//            Thread.currentThread().interrupt();
//            e.printStackTrace();
//            System.out.println("===" + Thread.currentThread().isInterrupted());
//            System.out.println("===" + Thread.currentThread().isInterrupted());
//            // must todo
//            // 1、取消阻塞并抛出 InterruptedException。
//            // 2、否则， interrupt() 只是设置线程B的中断状态。
//            // 在被中断线程B中运行的代码以后可以轮询isInterrupted()中断状态，看看它是否被请求停止正在做的事情
//        }
    }

    public static void main(String[] args) {
        InterruptedThread interruptedThread = new InterruptedThread();
        interruptedThread.start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
            // todo 异常中断补偿处理或者向上抛出。不可以什么也不做
            // throw new InterruptedException (ex);
        }
        interruptedThread.interrupt();
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//            // todo 异常中断补偿处理或者向上抛出。不可以什么也不做
//            // throw new InterruptedException (ex);
//        }
        System.out.println(interruptedThread.isInterrupted());
        System.out.println(interruptedThread.isInterrupted());
        System.out.println(interruptedThread.isInterrupted());
        System.out.println(interruptedThread.isInterrupted());

    }
}
