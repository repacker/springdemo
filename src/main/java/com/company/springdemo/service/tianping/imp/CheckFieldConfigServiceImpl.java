package com.company.springdemo.service.tianping.imp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.company.springdemo.model.tianping.CheckFieldConfig;
import com.company.springdemo.dao.tianping.CheckFieldConfigMapper;
import com.company.springdemo.service.tianping.ICheckFieldConfigService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 对账任务列配置表 服务实现类
 * </p>
 *
 * @author whs
 * @since 2019-05-14
 */
@Service
public class CheckFieldConfigServiceImpl extends ServiceImpl<CheckFieldConfigMapper, CheckFieldConfig> implements ICheckFieldConfigService {

    @Autowired
    private CheckFieldConfigMapper checkFieldConfigMapper;


    @Override
    public Integer selectCount(String requestNo) {
        QueryWrapper<CheckFieldConfig> wrapper = new QueryWrapper<>();
        wrapper.lambda()
                .eq(CheckFieldConfig::getFieldDstName, requestNo);
        Integer row = checkFieldConfigMapper.selectCount(wrapper);
        return row;
    }

    @Override
    public Integer updateByRequestNo(CheckFieldConfig checkFieldConfig, String requestNo) {
        QueryWrapper<CheckFieldConfig> wrapper = new QueryWrapper<>();
        wrapper.lambda()
                .eq(CheckFieldConfig::getFieldDstName, requestNo);
        Integer row = checkFieldConfigMapper.update(checkFieldConfig, wrapper);
        return row;
    }
}
