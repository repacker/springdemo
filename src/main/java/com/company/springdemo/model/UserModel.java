package com.company.springdemo.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @Auther: whs
 * @Date: 2018/8/3 20:25
 * @Description:
 */
@Setter
@Getter
@ToString
public class UserModel {

    private Integer userId;

    @Getter
    @Setter
    private String userName;

    private String password;

    private String phone;

}
