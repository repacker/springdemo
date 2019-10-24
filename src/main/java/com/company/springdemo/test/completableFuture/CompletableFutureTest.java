package com.company.springdemo.test.completableFuture;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @Auther: whs
 * @Date: 2019/2/13 16:01
 * @Description: 如下程序主要是通过CompletableFuture的
 * thenAcceptAsync方法实现异步回调处理相关的http外部请求，
 * 并根据回调结果处理相应的返回信息
 */
@Slf4j
public class CompletableFutureTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        HttpTask task = new HttpTask();
        System.out.println("main thread starts in thread id: " + Thread.currentThread().getId());
        // 新建一个Future
        CompletableFuture futureNonBlocking = CompletableFuture.supplyAsync(() -> {
            try {
                return task.doHttp("https://guazi.com");
            } catch (Exception e) {
                log.error("http请求异常", e);
                e.printStackTrace();
            }
            return "error";
        });

        // 新建一个Future
        CompletableFuture futureBlocking = CompletableFuture.supplyAsync(() -> {
            try {
                return task.doHttp("http://guazi.com");
            } catch (Exception e) {
                log.error("http请求异常", e);
                e.printStackTrace();
            }
            return "error";
        });

        // 非阻塞
        System.out.println("-----------------非阻塞位置1----------------------\n");
        // future.thenAcceptAsync() 方法不阻塞本线程，http请求成功后执行回调函数
        futureNonBlocking.thenAcceptAsync(result -> System.out.println("\nfrom non blocking future:" + result + "\n"));
        System.out.println("-----------------非阻塞位置2----------------------\n");
        // 阻塞
        System.out.println("\nfrom blocking future:" + futureBlocking.get() +"\n");
        // future.get() 方法阻塞本线程，直到http请求成功
        System.out.println("-----------------阻塞位置1----------------------\n");
        System.out.println("main thread ends");

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
