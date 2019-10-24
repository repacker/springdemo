package com.company.springdemo.test.rpc.common;

/**
 * @Auther: whs
 * @Date: 2019/2/27 16:56
 * @Description:这个类是client、server需要引入的公共接口
 */
public interface EchoService {

    String echo(String ping);

}
