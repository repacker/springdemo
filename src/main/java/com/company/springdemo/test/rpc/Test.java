package com.company.springdemo.test.rpc;

import com.company.springdemo.test.rpc.client.RpcImporter;
import com.company.springdemo.test.rpc.exporter.RpcExporter;
import com.company.springdemo.test.rpc.common.EchoService;
import com.company.springdemo.test.rpc.server.EchoServiceImpl;

import java.net.InetSocketAddress;

/**
 * @Auther: whs
 * @Date: 2019/2/27 17:07
 * @Description: RPC的使用，这里启动了一个服务提供者，也模拟了一个客户端的消费者。
 * 但共同引入的server包下的类、接口
 * 在分布式下如何引入呢？暂时还不理解，不过其思想还是可以借鉴的
 */
public class Test {

    public static void main(String[] args) {

        //创建异步发布服务端的线程并启动，用于接受PRC客户端的请求，根据请求参数调用服务实现类，返回结果给客户端
        new Thread(() -> {
            try {
                RpcExporter.exporter("localhost", 8088);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

        //创建客户端服务代理类，构造RPC求情参数，发起RPC调用
        RpcImporter<EchoService> importer = new RpcImporter<>();
        EchoService echo = importer.importer(EchoServiceImpl.class, new InetSocketAddress("localhost", 8088));
        System.out.println(echo.echo("Are u ok?"));
    }

}
