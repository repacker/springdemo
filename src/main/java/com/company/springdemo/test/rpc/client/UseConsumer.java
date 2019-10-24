package com.company.springdemo.test.rpc.client;

import com.company.springdemo.test.rpc.common.EchoService;
import com.company.springdemo.test.rpc.server.EchoServiceImpl;

import java.net.InetSocketAddress;

/**
 * @Auther: whs
 * @Date: 2019/2/27 17:07
 * @Description:
 * 该框架由四部分组成
 * 1、server端：实现接口代码逻辑，启动生产将实现者放入socket通道中，在该通道中解析类的二进制文件，反射，调用相应的方法
 * 2、client端：使用者创建消费者，消费者将消费的实现类文件注入到通道中，获取生产者的代理对象
 * 3、common端：公共接口
 * 注意：
 * 1、这里有个问题需要注意，common端的公共接口没有序列化的唯一标示，所以有些问题
 * 2、client端在获取代理对象时输入参数已经获取到了server端的实现者类文件，这时再传入也有些多此一举
 * 总之还是有些参考意义的
 */
public class UseConsumer {

    public static void main(String[] args) {

        //创建客户端服务代理类，构造RPC求情参数，发起RPC调用
        RpcConsumer<EchoService> importer = new RpcConsumer<>();
        EchoService echo = importer.importer(EchoServiceImpl.class, new InetSocketAddress("localhost", 8088));
        System.out.println(echo.echo("Are u ok?"));
    }

}
