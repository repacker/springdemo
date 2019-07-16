package com.company.springdemo.controller.tianping;


import com.company.springdemo.model.tianping.CheckFieldConfig;
import com.company.springdemo.service.tianping.ICheckFieldConfigService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 对账任务列配置表 前端控制器
 * </p>
 *
 * @author whs
 * @since 2019-05-14
 */
@RestController
@RequestMapping("/check-field-config")
@Slf4j
public class CheckFieldConfigController {

    @Autowired
    private ICheckFieldConfigService iCheckFieldConfigService;

    @ResponseBody
    @RequestMapping("/myPlusTest")
    public String userName() {

        String requestNo = "";
        iCheckFieldConfigService.selectCount(requestNo);
        CheckFieldConfig checkFieldConfig = new CheckFieldConfig();

        iCheckFieldConfigService.updateByRequestNo(checkFieldConfig,requestNo);
        log.info("userName:");
        return "userName:";

    }

}
