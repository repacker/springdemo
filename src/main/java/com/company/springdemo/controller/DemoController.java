package com.company.springdemo.controller;

import com.company.springdemo.model.DemoProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: whs
 * @Date: 2018/5/30 19:40
 * @Description:
 */
@RestController
public class DemoController {

    @Autowired
    DemoProperties demoProperties;

    @ResponseBody
    @RequestMapping("/userName")
    public String userName(){
        System.out.println("userName:" + demoProperties.getUserName());
        return "userName:" + demoProperties.getUserName();
    }

    @ResponseBody
    @RequestMapping(value = "/age", produces = "application/json; charset=UTF-8")
    public String age(){
        System.out.println("age:" + demoProperties.getAge());
        return "age:" + demoProperties.getAge();
    }

}
