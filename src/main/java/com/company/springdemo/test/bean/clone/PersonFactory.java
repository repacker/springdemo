package com.company.springdemo.test.bean.clone;

/**
 * @description: 该类
 * @author: whs
 * @date: 2019/07/25 17:22
 */
public class PersonFactory {
    public static Person newPrototypeInstance() {
        Address address = new Address();
        address.setType("Home");
        address.setValue("北京");

        Person p1 = new Person();
        p1.setAddress(address);
        p1.setAge(31);
        p1.setName("Peter");
        return p1;
    }
}
