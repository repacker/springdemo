package com.company.springdemo.model.tianping;

/**
 * @author whs
 * @date 2019/5/14 16:45
 * @description
 */

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * mybatis-plus 父类
 * 用于定义Id
 */
@Data
public class MybatisPlusBaseEntity implements Serializable {

    @TableId(type = IdType.AUTO)
    private Integer id;

}
