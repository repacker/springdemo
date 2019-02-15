package com.company.springdemo.test.jdkproxy;

/**
 * @Auther: whs
 * @Date: 2019/2/15 15:02
 * @Description:
 * 被代理的类
 * 目标对象类
 * 实现目标接口.
 * 继而实现目标方法。
 */
public class TargetObject implements TargetInterface {

    /**
     * 目标方法(即目标操作)
     */
    @Override
    public void business() {
        System.out.println("business");
    }

}
