package com.company.springdemo.controller;

import com.company.springdemo.common.taskjob.AsyncTaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: whs
 * @Date: 2019/2/8 00:02
 * @Description:
 */
@RestController
@RequestMapping("/task")
public class TaskController {

    private final static Logger logger = LoggerFactory.getLogger(TaskController.class);

    @Autowired
    private TaskExecutor taskExecutor;

    @Autowired
    private AsyncTaskService asyncService;

    @RequestMapping("/testAsync")
    public String testAsync() {
        logger.info("TestAsync begins to execute!");
        long startTime = System.currentTimeMillis();

        asyncService.testNoRespNoParamAsync();
        logger.info("TestAsync execution completed, use time: {}!", (System.currentTimeMillis() - startTime) / 1000);

        return "End!";
    }

    @RequestMapping("/testTaskExecutor")
    public String testTaskExecutor() {
        logger.info("TestTaskExecutor function begin to execute!");

        taskExecutor.execute(() -> {
            logger.info("Real thread begin to execute!");

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                logger.error("Real thread was interrupted!", e);
                return;
            }

            logger.info("Real thread has been executed!");
        });

        logger.info("TestTaskExecutor function has been executed!");

        return "Succeed!";
    }
}