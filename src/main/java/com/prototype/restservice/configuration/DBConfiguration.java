package com.prototype.restservice.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

@Configuration
public class DBConfiguration {

    private final static String SCHEMA = "db/schema-h2.sql";
    private final static String DATA = "db/data-h2.sql";

    @Bean(name = "dataSource")
    public DriverManagerDataSource dataSource() {

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.h2.Driver");
        dataSource.setUrl("jdbc:h2:~/myDB;MV_STORE=false");
        dataSource.setUsername("sa");
        dataSource.setPassword("");

        Resource initSchema = new ClassPathResource(SCHEMA);
        Resource initData = new ClassPathResource(DATA);
        DatabasePopulator databasePopulator = new ResourceDatabasePopulator(initSchema, initData);
        DatabasePopulatorUtils.execute(databasePopulator, dataSource);

        return dataSource;
    }
}
