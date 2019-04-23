package com.jsc.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

/*用于指定当前类是一个spring配置类，创建容器时会从该类上加载注解
获取容器时使用AnnotationApplicationContext加载*/
@Configuration
/*定义注解扫描位置*/
@ComponentScan("com.jsc")
/*定义Properties文件路径*/
@PropertySource("classpath:db.properties")
public class SpringConfig {

    //给成员变量注入参数
    @Value("${driverClassName}")
    private String driver;

    @Value("${url}")
    private String url;

    @Value("${username1}")
    private String username;

    @Value("${password}")
    private String password;

    //创建容器时会生成一个dataSource的bean对象
    @Bean("dataSource")
    public DataSource getDs() throws PropertyVetoException {
        ComboPooledDataSource dataSource=new ComboPooledDataSource();
        dataSource.setDriverClass(driver);
        dataSource.setJdbcUrl(url);
        dataSource.setUser(username);
        dataSource.setPassword(password);
        return dataSource;
    }

    //获取一个数据库查询工具
    @Bean("queryRunner")
    public QueryRunner getQueryRunner(DataSource dataSource){
        return new QueryRunner(dataSource);
    }

}
