package com.company.springdemo.test.completableFuture;

import com.company.springdemo.common.utils.HttpUtil;

import java.io.IOException;

/**
 * @Auther: whs
 * @Date: 2019/2/13 16:03
 * @Description: http rest请求外部接口
 */
public class HttpTask {

    public String doHttp(String url) throws IOException, InterruptedException {
        long threadId = Thread.currentThread().getId();
//        OkHttpClient client = new OkHttpClient();
        System.out.println("HttpTask http io starts in thread id: " + threadId);

        String result  = HttpUtil.httpGet(url);

        // mock delay
        for (int i = 1; i <= 3; ++i) {
            // 阻塞本线程1秒
            Thread.sleep(1000);
            System.out.println("delayed " + i + " seconds in thread id: " + threadId);
        }

        return "******** thread id " + threadId + " return ***********";
    }
}
