package com.company.springdemo.controller;

import com.company.springdemo.model.UserDomain;
import com.company.springdemo.service.UserService;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

//@Controller  二者使用的包也不一样
@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @ResponseBody
    @PostMapping("/add")
    public int addUser(UserDomain user){
        System.out.println(user.getPhone()+"---------------------"+user.getPassword()+"++"+user.getUserName());
        return userService.addUser(user);
    }

    @ResponseBody
    @PostMapping("/delete")
    public JSONPObject deleteById(int userId){
        System.out.println("++++++++++++++++++++++"+ userId);
        boolean  result = userService.deleteById(userId);
        JSONPObject jsonpObject = null;

        return jsonpObject;
    }

    @ResponseBody
    @GetMapping("/all")
    public Object findAllUser(
            @RequestParam(name = "pageNum", required = false, defaultValue = "1")
                    int pageNum,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10")
                    int pageSize){
        System.out.println("++++++++++++++++++++++");
        return userService.findAllUser(pageNum,pageSize);
    }

}
