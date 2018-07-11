package com.company.springdemo;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableScheduling
@MapperScan("com.company.springdemo.dao")
public class SpringdemoApplication{

    private final static Logger logger = LoggerFactory.getLogger(SpringdemoApplication.class);

    public static void main(String[] args) {
        logger.info("springDemo已启动");
        SpringApplication.run(SpringdemoApplication.class, args);
    }

}
