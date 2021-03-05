package com.nubiform.idus.config.datasource;

import com.zaxxer.hikari.HikariDataSource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Configuration
@MapperScan("com.nubiform.idus.api")
public class RoutingDataSourceConfig {

    @Bean
    @ConfigurationProperties(prefix = "idus.datasource.read-only")
    public IdusDataSourceProperty readOnlyDataSourceProperty() {
        return new IdusDataSourceProperty();
    }

    @Bean
    @ConfigurationProperties(prefix = "idus.datasource.write")
    public IdusDataSourceProperty writeDataSourceProperty() {
        return new IdusDataSourceProperty();
    }

    public DataSource createDataSource(IdusDataSourceProperty idusDataSourceProperty) {
        return DataSourceBuilder.create().type(HikariDataSource.class)
                .url(idusDataSourceProperty.getJdbcUrl())
                .username(idusDataSourceProperty.getUserName())
                .password(idusDataSourceProperty.getPassword())
                .build();
    }

    @Bean
    public DataSource routingDataSource(IdusDataSourceProperty readOnlyDataSourceProperty, IdusDataSourceProperty writeDataSourceProperty) {
        DataSource readOnlyDataSource = createDataSource(readOnlyDataSourceProperty);
        DataSource writeDataSource = createDataSource(writeDataSourceProperty);

        RoutingDataSource routingDataSource = new RoutingDataSource();

        Map<Object, Object> dataSourceMap = new HashMap<>();
        dataSourceMap.put("readOnly", readOnlyDataSource);
        dataSourceMap.put("write", writeDataSource);
        routingDataSource.setTargetDataSources(dataSourceMap);
        routingDataSource.setDefaultTargetDataSource(readOnlyDataSource);

        return routingDataSource;
    }

    @Primary
    @DependsOn({"routingDataSource"})
    @Bean
    public DataSource dataSource(DataSource routingDataSource) {
        return new LazyConnectionDataSourceProxy(routingDataSource);
    }
}
