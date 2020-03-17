package com.ywl.study.springsecurity.datasource;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;


/**
 * 配置多数据源
 */
@Configuration
public class DynamicDataSourceConfig {

    @Bean
    @ConfigurationProperties("spring.datasource.druid.first")
    public DataSource firstDataSource(){
        return  DruidDataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties("spring.datasource.druid.second")
    public DataSource secondDataSource(){
        return  DruidDataSourceBuilder.create().build();
    }

    @Primary
    @Bean
    public DynamicDataSource dataSource(DataSource firstDataSource,DataSource secondDataSource){
       Map<Object,Object> targetDataSource=new HashMap<>(5);
       targetDataSource.put(DataSourceNames.FIRST,firstDataSource);
       targetDataSource.put(DataSourceNames.SECOND,secondDataSource);
       return new DynamicDataSource(firstDataSource,targetDataSource);
    }

}
