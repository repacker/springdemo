package com.company.springdemo.test.jdkstream.forkjoin;

import java.util.concurrent.RecursiveTask;

/**
 * @description: 该类Java--8--新特性--串并行流与ForkJoin框架 测试
 * @author: whs
 * @date: 2019/7/12
 */
public class ForkJoinWork extends RecursiveTask<Long> {

    private Long start;//起始值
    private Long end;//结束值
    public static final Long critical = 100000L;//临界值，十万级别

    public ForkJoinWork(Long start, Long end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        //判断是否是拆分完毕
        Long lenth = end - start;
        if (lenth <= critical) {
            //如果拆分完毕就相加
            Long sum = 0L;
            for (Long i = start; i <= end; i++) {
                sum += i;
            }
            return sum;
        } else {
            //没有拆分完毕就开始拆分
            Long middle = (end + start) / 2;//计算的两个值的中间值
            ForkJoinWork right = new ForkJoinWork(start, middle);
            right.fork();//拆分，并压入线程队列
            ForkJoinWork left = new ForkJoinWork(middle + 1, end);
            left.fork();//拆分，并压入线程队列
            System.out.println("递归次数开始" + start + "，递归次数结束" + middle);

            //合并
            return right.join() + left.join();
        }
    }

}