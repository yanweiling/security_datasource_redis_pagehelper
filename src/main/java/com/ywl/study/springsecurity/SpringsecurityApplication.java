package com.ywl.study.springsecurity;

import com.ywl.study.springsecurity.datasource.DynamicDataSource;
import com.ywl.study.springsecurity.datasource.DynamicDataSourceConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

/**
 * 因为数据源是自己生成的，所以要去掉原先springboot启动时候自动装配的数据源配置。
 */
//@MapperScan("com.ywl.study.springsecurity.mapper")  如果加上@Mapper，可以不用@MapperScan
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@Import({DynamicDataSourceConfig.class})
@EnableCaching
public class SpringsecurityApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringsecurityApplication.class, args);
    }

    private static final org.apache.commons.logging.Log logger1 = org.apache.commons.logging
            .LogFactory
            .getLog(SpringsecurityApplication.class);

    private static final org.slf4j.Logger logger2 = org.slf4j.LoggerFactory
            .getLogger(SpringsecurityApplication.class);

    private static final java.util.logging.Logger logger3 = java.util.logging.Logger
            .getLogger("SpringsecurityApplication");


    @Bean
    public CommandLineRunner loggerLineRunner() {
        return (args) -> {
            logger1.error("commons logging error...");

            logger1.info("commons logging info...");
            logger2.info("slf4j info...");
            logger3.info("java util logging info...");

            logger1.debug("commons logging debug...");
        };
    }
}
