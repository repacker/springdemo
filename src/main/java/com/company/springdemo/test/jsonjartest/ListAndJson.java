package com.company.springdemo.test.jsonjartest;

import com.alibaba.fastjson.JSON;
import com.company.springdemo.model.DemoProperties;
import com.company.springdemo.model.UserModel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: whs
 * @Date: 2019/2/14 18:27
 * @Description:
 */
@Component
public class ListAndJson {

    /**
     * @Description: 该方法fastjson与List的相互转换使用
     * @Param:
     * @return:
     */
    public void getListAndJson() {
        DemoProperties demoProperties1 = new DemoProperties();
        demoProperties1.setAge("12");
        DemoProperties demoProperties2 = new DemoProperties();
        demoProperties2.setUserName("whs");
        DemoProperties demoProperties3 = new DemoProperties();
        List list = new ArrayList();
        list.add(demoProperties1);
        list.add(demoProperties2);
        list.add(demoProperties3);
        System.out.println(JSON.toJSONString(list));

        UserModel userModel1 = new UserModel();
        UserModel userModel2 = new UserModel();
        UserModel userModel3 = new UserModel();

        List list1 = new ArrayList();
        list1.add(userModel1);
        list1.add(userModel2);
        list1.add(userModel3);
        System.out.println(JSON.toJSONString(list1));

        List<DemoProperties> list3 = JSON.parseArray(JSON.toJSONString(list), DemoProperties.class);
        System.out.println(list3.get(0).getAge());
    }




}
