package com.company.springdemo.dao.tianping;

import com.company.springdemo.model.tianping.CheckFieldConfig;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 对账任务列配置表 Mapper 接口
 * </p>
 *
 * @author whs
 * @since 2019-05-14
 */
@Mapper
@Component
public interface CheckFieldConfigMapper extends BaseMapper<CheckFieldConfig> {

}
