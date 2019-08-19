package com.company.springdemo.test.bean.clone;

import org.junit.Test;

/**
 * @description: 该类 深克隆、浅克隆测试类
 * @author: whs
 * @date: 2019/08/19 09:31
 */
public class PersonTest {

    @Test
    public void testShallowCopy() throws Exception {
        Person p1 = new Person();
        p1.setAge(31);
        p1.setName("Peter");

        Person p2 = (Person) p1.clone();
        System.out.println(p1 == p2);//false
        p2.setName("Jacky");
        System.out.println("p1=" + p1);//p1=Person [name=Peter, age=31]
        System.out.println("p2=" + p2);//p2=Person [name=Jacky, age=31]
    }

    @Test
    public void testShallowCopy1() throws Exception {
        Address address = new Address();
        address.setType("Home");
        address.setValue("北京");

        Person p1 = new Person();
        p1.setAge(31);
        p1.setName("Peter");
        p1.setAddress(address);

        Person p2 = (Person) p1.clone();
        System.out.println(p1 == p2);//false

        p2.getAddress().setType("Office");
        System.out.println("p1=" + p1);
        System.out.println("p2=" + p2);
    }

}