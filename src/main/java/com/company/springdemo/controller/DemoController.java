package com.company.springdemo.controller;

import com.company.springdemo.model.DemoProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: whs
 * @Date: 2018/5/30 19:40
 * @Description: 测试配置文件注入的两种方式
 */
@RestController
public class DemoController {

    private final static Logger logger = LoggerFactory.getLogger(DemoController.class);

    @Autowired
    private Environment environment;

    @Autowired
    DemoProperties demoProperties;

    /**
     * @Description: properties注入的两种方式
     * @Param:
     * @return:
     */
    @ResponseBody
    @RequestMapping("/userName")
    public String userName() {
        logger.info("environment:" + environment.getProperty("userName") + ",age:" + environment.getProperty("age"));
        logger.info("userName:" + demoProperties.getUserName());
        return "userName:" + demoProperties.getUserName();
    }

    /**
     * @Description: 同上
     * @Param:
     * @return:
     */
    @ResponseBody
    @RequestMapping(value = "/age", produces = "application/json; charset=UTF-8")
    public String age() {
        System.out.println("age:" + demoProperties.getAge());
        return "age:" + demoProperties.getAge();
    }

    /**
     * @Description: ResponseBody注释、form-data、
     * x-www-form-urlencoded三种方式均可以获得userName
     * @Param:
     * @return:
     */
//    @ResponseBody
    @RequestMapping("/userNameJ")
    public String getUserName(String userName) {
        logger.info("userName:" + userName);
        return "userName:" + userName;
    }

    /**
     * @Description: 描述测试待定
     * @Param:
     * @return:
     */
    @ResponseBody
    @RequestMapping(value = "/userNameJ2", produces = "application/json; charset=UTF-8")
    public String getUserName2(String userName) {
        logger.info("userName:" + userName);
        return "userName:" + userName;
    }

}
