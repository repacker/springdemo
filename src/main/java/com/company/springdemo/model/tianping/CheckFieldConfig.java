package com.company.springdemo.model.tianping;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * <p>
 * 对账任务列配置表
 * </p>
 *
 * @author whs
 * @since 2019-05-14
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class CheckFieldConfig extends MybatisPlusBaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 任务配置UUID
     */
    private String taskId;

    /**
     * 字段所属数据类别: 1:A边,2:B边
     */
    private Integer inputType;

    /**
     * CSV格式下,字段在A边或者B边的序号
     */
    private Integer fieldSrcIdx;

    /**
     * JSON格式下,字段在A边或者B边的名称
     */
    private String fieldSrcName;

    /**
     * 字段对应到对账输入的字段:field1 到 field20
     */
    private String fieldDstName;

    /**
     * 导出接口中Excel的列名
     */
    private String fieldExportName;

    /**
     * 是否导出:1:是, 0:否
     */
    private Integer isExport;

    /**
     * 字段导出顺序序号
     */
    private Integer fieldExportOrder;

    /**
     * 字段在对账处理中的类别: 1:对比的key, 2:对比的value, 3:说明的列，不参与对账
     */
    private Integer fieldType;

    /**
     * 字段值类型: 0:字符串, 1:数值类型
     */
    private Integer valueType;

    /**
     * CSV格式,字段描述
     */
    private String fieldDesc;

    /**
     * 创建时间
     */
    private LocalDateTime createdAt;

    /**
     * 更新时间
     */
    private LocalDateTime updatedAt;


}
