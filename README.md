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
    2.2 myibatis基本CURL