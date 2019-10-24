package com.company.springdemo.test.completableFuture;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * @description: 该类 测试验证CompletionService获取执行结果的无序性
 * @author: whs
 * @date: 2019/09/09 14:41
 */
@Slf4j
public class CompletionServiceTest {
    public static void main(String[] args) {
        //初始化线程池
        ExecutorService threadPool = Executors.newFixedThreadPool(10);
        CompletionService<Integer> completionService = new ExecutorCompletionService<Integer>(threadPool);
        for (int j = 1; j <= 5; j++) {
            final int index = j;
            completionService.submit(new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {
                    //第三个线程睡眠等待
                    if (index == 3) {
                        java.lang.Thread.sleep(3000l);
                    }
                    return index;
                }
            });
        }
        threadPool.shutdown();

        for (int i = 0; i < 5; i++) {
            try {
                Future future = completionService.take();
                System.out.println("线程:" + future.get() + " 任务执行结束:");
            } catch (InterruptedException e) {
                e.printStackTrace();
                log.error("线程中止：", e);
                // 如果发生中断异常则重新设置线程的中断状态
                // 这样做可以让wait中的线程唤醒
                Thread.currentThread().interrupt();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }
}
