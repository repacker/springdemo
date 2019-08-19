package com.company.springdemo.test.bean.clone;

import lombok.Data;

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

}
