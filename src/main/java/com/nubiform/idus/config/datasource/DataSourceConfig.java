package com.nubiform.idus.config.datasource;

import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Slf4j
@Configuration
public class DataSourceConfig {

    @Primary
    @Bean
    @ConfigurationProperties(prefix = "idus.datasource.read-only")
    public DataSource readOnlyDataSource() {
        log.debug("readOnlyDataSource");
        return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }

    @Bean
    @ConfigurationProperties(prefix = "idus.datasource.write")
    public DataSource writeDataSource() {
        log.debug("writeDataSource");
        return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }
}
