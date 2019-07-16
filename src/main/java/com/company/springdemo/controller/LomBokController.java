package com.company.springdemo.controller;

import com.company.springdemo.model.UserModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

/**
 * @Auther: whs
 * @Date: 2018/8/3 20:27
 * @Description: 测试lombok的使用
 */
@RestController
public class LomBokController {

    private final static Logger logger = LoggerFactory.getLogger(DemoController.class);

    @ResponseBody
    @RequestMapping("/testLomBokUse")
    public UserModel testLomBokUse(@RequestBody UserModel userModel) {
        logger.info("lomBok的使用:" + userModel.getPassword());
        return userModel;
    }

}
