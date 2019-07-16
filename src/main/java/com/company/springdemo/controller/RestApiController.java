package com.company.springdemo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.company.springdemo.common.base.RespCode;
import com.company.springdemo.common.base.RespEntity;
import com.company.springdemo.model.UserDomain;
import com.company.springdemo.service.UserService;
import com.github.pagehelper.PageSerializable;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: whs
 * @Date: 2018/8/6 19:23
 * @Description:
 */
@RestController
@RequestMapping(value = "/swagger")
@Api(value = "RestApiController|一个用来测试swagger注解的控制器")
@Slf4j
public class RestApiController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "用户信息查询API", notes = "Swagger的使用")
    @ResponseBody
    @RequestMapping("/all")
    public Object findAllUser(
            @RequestParam(name = "pageNum", required = false, defaultValue = "1")
                    int pageNum,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10")
                    int pageSize){
        log.info("请求service对象：" + userService);
        log.info("用户查询：");
//      这里也可以照着PageInfo实现一个MyPageInfo
//        PageInfo<UserDomain>  list = userService.findAllUser(pageNum,pageSize);
        PageSerializable<UserDomain> list = userService.findAllUser(pageNum,pageSize);
        JSONObject jsonpData = new JSONObject();
        jsonpData.put("list", JSON.toJSON(list.getList()));
        jsonpData.put("total",list.getTotal());
        RespEntity respEntity = new RespEntity(RespCode.SUCCESS, jsonpData);
        return respEntity;
    }

}
