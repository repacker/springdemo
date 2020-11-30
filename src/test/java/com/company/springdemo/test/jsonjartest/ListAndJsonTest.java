package com.company.springdemo.test.jsonjartest;

import com.company.springdemo.SpringDemoApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpringDemoApplication.class)
public class ListAndJsonTest {

    @Autowired
    private ListAndJson listAndJson;

    @Test
    public void getListAndJson() {
        listAndJson.getListAndJson();
    }

}