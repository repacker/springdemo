package com.company.springdemo.test.rpc.server;

import com.company.springdemo.test.rpc.common.EchoService;

/**
 * @Auther: whs
 * @Date: 2019/2/27 16:57
 * @Description:server端引入公共接口的实现逻辑
 */
public class EchoServiceImpl implements EchoService {

    @Override
    public String echo(String ping) {
        return ping != null ? ping + " --> EchoServiceImpl，I am ok." : " EchoServiceImpl，I am bad.";
    }

}
