package com.company.springdemo.test.bean.clone;

import lombok.Data;

/**
 * @description: 该类
 * @author: whs
 * @date: 2019/07/24 19:58
 */
@Data
public class Address implements Cloneable {
    private String type;
    private String value;

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}


