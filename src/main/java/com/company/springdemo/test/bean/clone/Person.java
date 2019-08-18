package com.company.springdemo.test.bean.clone;

import lombok.Data;
import org.junit.jupiter.api.Test;

import java.io.*;

/**
 * @description: 该类
 * @author: whs
 * @date: 2019/07/24 19:56
 */
@Data
public class Person implements Cloneable {
    private String name;
    private Integer age;
    private Address address;

    public Person deepClone() {
        Person p2 = null;
        Person p1 = this;
        PipedOutputStream out = new PipedOutputStream();
        PipedInputStream in = new PipedInputStream();
        try {
            in.connect(out);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (ObjectOutputStream bo = new ObjectOutputStream(out);
             ObjectInputStream bi = new ObjectInputStream(in)) {
            bo.writeObject(p1);
            p2 = (Person) bi.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return p2;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Object obj = super.clone();
        Address a = ((Person) obj).getAddress();
        ((Person) obj).setAddress((Address) a.clone());
        return obj;
    }

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
