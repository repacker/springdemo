package com.company.springdemo;

import com.company.springdemo.common.listenter.PropertiesListener;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableScheduling
@MapperScan("com.company.springdemo.dao")
public class SpringDemoApplication {

    private final static Logger logger = LoggerFactory.getLogger(SpringDemoApplication.class);

    public static void main(String[] args) {
        logger.info("springDemo已启动");

        //=================== 启动应用程序 ===================//
        logger.info("启动应用程序容器...");
        SpringApplication application = new SpringApplication(SpringDemoApplication.class);
        //=================== 初始化配置信息 ===================//
        application.addListeners(new PropertiesListener(
                new String[]{"config/demo.properties"}
        ));
        ConfigurableApplicationContext context = application.run(args);

        logger.info("SpringDemoApplication.main 执行完毕！");

    }

}
