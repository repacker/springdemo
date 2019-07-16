package com.company.springdemo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.company.springdemo.common.base.RespCode;
import com.company.springdemo.common.base.RespEntity;
import com.company.springdemo.model.UserDomain;
import com.company.springdemo.service.UserService;
import com.github.pagehelper.PageSerializable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

//@Controller  二者使用的包也不一样
@RestController
@RequestMapping(value = "/user")
public class UserController {

    private final static Logger logger = LoggerFactory.getLogger(UserController.class);

    public int number = 2;

    @Autowired
    private UserService userService;

    /**
     * @Description: 返回类型必须为POJO类型才能自动解析成json格式
     * @Param:
     * @return:
     */
    @RequestMapping("/add")
    public RespEntity addUser(@RequestBody UserDomain user) {
        int result = userService.addUser(user);
        RespEntity respEntity;
        if (result == 1) {
            respEntity = new RespEntity(RespCode.SUCCESS, JSONObject.toJSONString(user));
        } else {
            respEntity = new RespEntity(RespCode.INSERT_ERROR, JSONObject.toJSONString(user));
        }
        return respEntity;
    }

    @ResponseBody
    @RequestMapping("/delete")
    public RespEntity deleteById(int userId) {
        logger.info("用户删除：" + userId);
        boolean result = userService.deleteById(userId);
        JSONObject jsonpData = new JSONObject();
        jsonpData.put("userId", "userId");
        RespEntity respEntity;
        if (result) {
            respEntity = new RespEntity(RespCode.SUCCESS, jsonpData);
        } else {
            respEntity = new RespEntity(RespCode.INSERT_ERROR, jsonpData);
        }
        return respEntity;
    }

    @ResponseBody
    @RequestMapping("/all")
    public Object findAllUser(
            @RequestParam(name = "pageNum", required = false, defaultValue = "1")
                    int pageNum,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10")
                    int pageSize) {
        number++;
        logger.info("请求参数：" + number);
        logger.info("请求service对象：" + userService);
        pageSize = number;
        logger.info("用户查询：");
        Random rand = new Random();
        try {
            int time = rand.nextInt(5000);

            logger.info("查询休息" + time + "毫秒！");
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//      这里也可以照着PageInfo实现一个MyPageInfo
//        PageInfo<UserDomain>  list = userService.findAllUser(pageNum,pageSize);
        PageSerializable<UserDomain> list = userService.findAllUser(pageNum, pageSize);
        JSONObject jsonpData = new JSONObject();
        jsonpData.put("list", JSON.toJSON(list.getList()));
        jsonpData.put("total", list.getTotal());
        RespEntity respEntity = new RespEntity(RespCode.SUCCESS, jsonpData);
        logger.info("请求参数：" + number);
        return respEntity;
    }

}
