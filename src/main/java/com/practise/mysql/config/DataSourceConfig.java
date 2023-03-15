package com.practise.mysql.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * @Description
 * @Author dan.he
 * @Date 2022/8/20 22:39
 **/
@Configuration
public class DataSourceConfig {
    /**
     * 关联主数据源配置 ds1数据源
     */
//    @Primary
//    @Bean(name = "ds1Properties")
//    @ConfigurationProperties(prefix = "spring.datasource.ds1")
//    public DataSourceProperties ds1DataSourceProperties() {
//         DataSourceProperties  dataSourceProperties =new DataSourceProperties();
//         dataSourceProperties.setDriverClassName("com.mysql.jdbc.Driver");
//         dataSourceProperties.setUrl("jdbc:mysql://127.0.0.1:3306/demo?useSSL=false&requireSSL=false&characterEncoding=UTF-8&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
//         dataSourceProperties.setUsername("root");
//         dataSourceProperties.setPassword("123456abcd");
//         return dataSourceProperties;
//        return new DataSourceProperties();
//    }

    /**
     * 初始化构建主数据源ds1数据源
     */
    @Primary
    @Bean(name = "ds1DataSource")
    @ConfigurationProperties(prefix = "spring.source.ds1")
    public DataSource ds1DataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "ds2DataSource")
    @ConfigurationProperties(prefix = "spring.source.ds2")
    public DataSource ds2DataSource(){
        return DataSourceBuilder.create().build();
    }
}
