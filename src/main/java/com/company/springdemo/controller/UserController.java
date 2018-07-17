package com.company.springdemo.controller;

import com.alibaba.fastjson.JSONObject;
import com.company.springdemo.common.base.RespCode;
import com.company.springdemo.common.base.RespEntity;
import com.company.springdemo.model.UserDomain;
import com.company.springdemo.service.UserService;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

//@Controller  二者使用的包也不一样
@RestController
@RequestMapping(value = "/user")
public class UserController {

    private final static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private Environment environment;

    /** 
    * @Description: 返回类型必须为POJO类型才能自动解析成json格式
    * @Param: 
    * @return:
     */ 
    @RequestMapping("/add")
    public RespEntity addUser(@RequestBody UserDomain user){
        System.out.println("environment:" + environment.getProperty("userName") + ",age:" + environment.getProperty("age"));
        int result = userService.addUser(user);
        RespEntity respEntity;
        if(result == 1){
            respEntity = new RespEntity(RespCode.SUCCESS,JSONObject.toJSONString(user));
        }
        else {
            respEntity = new RespEntity(RespCode.INSERT_ERROR,JSONObject.toJSONString(user));
        }
        return respEntity;
    }

    @ResponseBody
    @RequestMapping("/delete")
    public RespEntity deleteById(int userId){
        logger.info("用户删除：" + userId);
        boolean  result = userService.deleteById(userId);
        JSONObject jsonpObject = new JSONObject();
        jsonpObject.put("userId","userId");
        RespEntity respEntity;
        if(result){
            respEntity = new RespEntity(RespCode.SUCCESS,JSONObject.toJSONString(jsonpObject));
        }
        else {
            respEntity = new RespEntity(RespCode.INSERT_ERROR,JSONObject.toJSONString(jsonpObject));
        }
        return respEntity;
    }

    @ResponseBody
    @RequestMapping("/all")
    public Object findAllUser(
            @RequestParam(name = "pageNum", required = false, defaultValue = "1")
                    int pageNum,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10")
                    int pageSize){
        logger.info("用户查询：");
        PageInfo<UserDomain>  list = userService.findAllUser(pageNum,pageSize);
        JSONObject jsonpObject = new JSONObject();
        jsonpObject.put("list",list.getList());
        jsonpObject.put("total",list.getTotal());
        RespEntity respEntity = new RespEntity(RespCode.SUCCESS,JSONObject.toJSONString(jsonpObject));
        return respEntity;
    }

}
