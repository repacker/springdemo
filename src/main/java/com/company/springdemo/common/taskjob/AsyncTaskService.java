package com.company.springdemo.common.taskjob;

import com.company.springdemo.controller.TaskController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @Auther: whs
 * @Date: 2019/2/7 23:32
 * @Description:通过@Async注解声明异步任务
 */
@Service("asyncTaskService")
public class AsyncTaskService {

    private final static Logger logger = LoggerFactory.getLogger(AsyncTaskService.class);

    @Async
    public void excuteAsyncTaskTest(String name) {
        for (int i = 0; i < 100; i++) {
            System.out.println("正在执行异步任务" + name + i);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    @Async
    public void testNoRespNoParamAsync() {
        logger.info("AsyncService begins to execute!");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            logger.error("AsyncService was interrupted!", e);
            return;
        }

        logger.info("AsyncService execution completed!");
    }

}
