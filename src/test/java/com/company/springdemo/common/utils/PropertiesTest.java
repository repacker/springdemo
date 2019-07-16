package com.company.springdemo.common.utils;

import com.sun.deploy.util.SessionState;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * @Auther: whs
 * @Date: 2018/9/18 14:42
 * @Description:
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class PropertiesTest {

    @Test
    public void testProperties() throws IOException {
        Properties prop = new Properties();
        prop.load(new InputStreamReader(SessionState.Client.class.getClassLoader().getResourceAsStream("demo.properties"), "UTF-8"));
        System.out.println(prop.getProperty("age"));
    }

//    @Test
//    public void getRedisNode(){
//        ClusterConfigurationProperties clusterConfigurationProperties = new ClusterConfigurationProperties();
//        System.out.println(clusterConfigurationProperties.getNodes());
//    }
}
