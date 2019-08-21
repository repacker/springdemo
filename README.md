# springdemo
maven、spring boot、spring mvc后台流程
1、热部署的添加

    option + command + shift + ／容易忘记这最后一步
2、基本架构的搭建

    2.1 数据库的搭建
        用户表demo：        
            drop TABLE if EXISTS t_user;
            CREATE TABLE t_user(
              userId INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
              userName VARCHAR(255) NOT NULL ,
              password VARCHAR(255) NOT NULL ,
              phone VARCHAR(255) NOT NULL
            ) ENGINE=INNODB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT "用户信息表";
    2.2 myibatis基本CURL,分页查找
    2.3 Druid web ui地址
        http://localhost:8080/druid/login.html
        用户名admin 密码admin    
    2.4 Spring boot定时任务，已添加
    2.5 Spring boot日志，默认使用Logback日志
    2.6 Spring boot自定义配置DemoProperties.java、demo.properties配置文件，已完成
    2.6.1 Spring boot自定义配置demo.properties配置文件方式二   
    2.7 收取邮件，已完成，解析附件
    2.8 myibatis自动化生成xml、model、dao 
    2.9 返回格式RespEntity的定义与使用 
    3.0 添加生产、测试环境配置
    3.1 测试lombok的使用
    3.2 生产接口Swagger2的使用
    3.3 redis数据库
    3.4 PostConstruct的使用
    3.5 注解的使用
    3.6 spring boot 使用TaskExecutor实现异步任务
    3.7 spring boot SSE服务器推送技术
    3.8 spring-boot mail邮件服务
    3.9 CompletableFuture的使用
    4.0 并发框架Disruptor的使用
    4.1 okhttp3请求访问接口工具类
    4.2 Json与List的相互转换
    4.3 jdk动态代理、cglib动态代理
    4.4 观察者模式的拆分与讲解
    4.5 单例，枚举
    4.6 算法：判断是否有环、大数据重复数字、二叉树的遍历、合并两个有序链表、LRU最近最少使用算法、快排算法、堆排序、
        找出链表的第一个公共结点、
    4.7 拦截器的使用，sql注解的过滤
    4.8 删除mybaties plus、增加文件导出流
    4.9 jdk1.8 stream函数式编程练习demo
    5.0 JDK8 双冒号及借口实现方法用法
    5.1 自定义注解的使用
    
3、问题定位
    
    3.1 解决Mybatis-plus与spring boot版本不一致，不能正常启动的问题