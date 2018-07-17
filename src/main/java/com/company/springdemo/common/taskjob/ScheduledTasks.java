package com.company.springdemo.common.taskjob;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @Auther: whs
 * @Date: 2018/5/30 11:07
 * @Description:构建执行定时任务
 */
@Component
public class ScheduledTasks {

    private final static Logger logger = LoggerFactory.getLogger(ScheduledTasks.class);

    private int fixedDelayCount = 1;
    private int fixedRateCount = 1;
    private int initialDelayCount = 1;
    private int cronCount = 1;

//    @Scheduled(fixedDelay = 5000)        //fixedDelay = 5000表示当前方法执行完毕5000ms后，Spring scheduling会再次调用该方法
//    public void testFixDelay() {
//        logger.info("===fixedDelay: 第{}次执行方法", fixedDelayCount++);
//    }
//
//    @Scheduled(fixedRate = 5000)        //fixedRate = 5000表示当前方法开始执行5000ms后，Spring scheduling会再次调用该方法
//    public void testFixedRate() {
//        logger.info("===fixedRate: 第{}次执行方法", fixedRateCount++);
//    }
//
//    @Scheduled(initialDelay = 1000, fixedRate = 5000)   //initialDelay = 1000表示延迟1000ms执行第一次任务
//    public void testInitialDelay() {
//        logger.info("===initialDelay: 第{}次执行方法", initialDelayCount++);
//    }

//    （秒 分 时 每月第几天 月 星期 年）其中年是可选字段。以下表示：每10秒执行一次
    @Scheduled(cron = "*/10 * * * * *")  //cron接受cron表达式，根据cron表达式确定定时规则
    public void testCron() {
//        logger.info("===initialDelay: 第{}次执行方法", cronCount++);
    }
}
