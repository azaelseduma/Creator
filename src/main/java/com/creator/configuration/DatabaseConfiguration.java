package com.creator.configuration;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;

/**
 * Created by Azael on 2017/08/28.
 */
@Configuration
public class DatabaseConfiguration {
    @Autowired
    private Environment environment;

    @Bean(name = "dataSource")
    @Primary
    public BasicDataSource dataSource() {
        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setDriverClassName("com.mysql.jdbc.Driver");
        basicDataSource.setUrl("jdbc:mysql://localhost:3306/app?useSSL=false");
        basicDataSource.setUsername("demo");
        basicDataSource.setPassword("xbox360");
        basicDataSource.setTestWhileIdle(true);
        basicDataSource.setValidationQuery("SELECT 1");
        return basicDataSource;
    }
}
