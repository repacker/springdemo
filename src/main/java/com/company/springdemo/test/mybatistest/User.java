package com.company.springdemo.test.mybatistest;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author whs
 * @date 2019/5/13 00:00
 * @description
 */
@Data
@AllArgsConstructor
public class User {

    private Integer id;
    private String name;
    private int age;

}
