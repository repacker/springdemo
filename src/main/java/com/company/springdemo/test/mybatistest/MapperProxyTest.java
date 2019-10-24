package com.company.springdemo.test.mybatistest;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author whs
 * @date 2019/5/13 00:04
 * @description
 */
public class MapperProxyTest implements InvocationHandler {

    public <T> T newInstance(Class<T> clz) {
        return (T) Proxy.newProxyInstance(clz.getClassLoader(), new Class[]{clz}, this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (Object.class.equals(method.getDeclaringClass())) {
            return method.invoke(this, args);
        }
        // 投鞭断流
        return new User((Integer) args[0], "whs", 18);
    }
}
