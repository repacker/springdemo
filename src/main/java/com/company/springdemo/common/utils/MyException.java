package com.company.springdemo.common.utils;

import lombok.Data;

/**
 * @Auther: whs
 * @Date: 2019/1/11 19:17
 * @Description:
 */
@Data
public class MyException extends RuntimeException {

    public MyException(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private String code;
    private String msg;

    // getter & setter
}
