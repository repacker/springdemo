package com.company.springdemo.test.mybatistest;

/**
 * @author whs
 * @date 2019/5/13 00:10
 * @description
 */
public class Test {
    public static void main(String[] args) {
        MapperProxyTest proxy = new MapperProxyTest();
        UserMapper mapper = proxy.newInstance(UserMapper.class);
        User user = mapper.getUserById(1001);
        System.out.println("ID:" + user.getId());
        System.out.println(mapper.toString());
    }
}
