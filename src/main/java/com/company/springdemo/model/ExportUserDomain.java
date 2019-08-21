package com.company.springdemo.model;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;

/**
 * @description: 该类
 * @author: whs
 * @date: 2019/08/21 11:15
 */
@Data
public class ExportUserDomain extends BaseRowModel {

    @ExcelProperty(value = "学号", index = 0)
    private Integer userId;

    @ExcelProperty(value = "姓名", index = 1)
    private String userName;

    @ExcelProperty(value = "密码", index = 2)
    private String password;

    @ExcelProperty(value = "电话", index = 3)
    private String phone;
}
