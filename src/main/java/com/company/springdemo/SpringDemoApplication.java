package com.company.springdemo;

import com.company.springdemo.common.listenter.PropertiesListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableScheduling
@EnableSwagger2
//@MapperScan("com.company.springdemo.dao")     //  如果注释，则该包下所有的dao均需加  @Mapper   注解
public class SpringDemoApplication {

    private final static Logger logger = LoggerFactory.getLogger(SpringDemoApplication.class);

    private static String active_environment;

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
        //环境信息
        active_environment = context.getEnvironment().getProperty("spring.profiles.active");

        if ("pro".equals(active_environment)) {
            logger.info("线上环境运行...");
        } else if ("dev".equals(active_environment)) {
            logger.info("开发环境运行...");
        }

        logger.info("SpringDemoApplication.main 执行完毕！");

    }

}
