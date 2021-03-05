package com.nubiform.idus.config.datasource;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class IdusDataSourceProperty {
    @NotNull
    private String jdbcUrl;
    @NotNull
    private String userName;
    @NotNull
    private String password;
}
