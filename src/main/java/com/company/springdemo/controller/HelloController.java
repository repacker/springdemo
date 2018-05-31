package com.company.springdemo.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @RequestMapping("/home")
    String home() {
        return "Hello World! Welcome to home!!!";
    }

    @RequestMapping("/hello/{myName}")
    String index(@PathVariable String myName) {
        return "Hello " + myName + "!!!";
    }

    @RequestMapping("/hello/getData")
    public JSONPObject getData(){
        return null;
    }
}