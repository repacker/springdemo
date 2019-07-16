package com.company.springdemo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

/**
 * @Auther: whs
 * @Date: 2019/2/8 15:29
 * @Description:
 */
@RestController
public class SSEController {

    @RequestMapping(value = "/push", produces = "text/event-stream")
    public String push() {
        Random random = new Random();
        try {
            Thread.sleep(160000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "data:Testing 1,2,3" + random.nextInt() + "\n\n";
    }

}
