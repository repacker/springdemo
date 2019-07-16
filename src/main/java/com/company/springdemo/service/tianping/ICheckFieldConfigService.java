package com.company.springdemo.service.tianping;

import com.baomidou.mybatisplus.extension.service.IService;
import com.company.springdemo.model.tianping.CheckFieldConfig;

/**
 * <p>
 * 对账任务列配置表 服务类
 * </p>
 *
 * @author whs
 * @since 2019-05-14
 */
public interface ICheckFieldConfigService extends IService<CheckFieldConfig> {

    Integer selectCount(String requestNo);

    Integer updateByRequestNo(CheckFieldConfig checkFieldConfig, String requestNo);

}
