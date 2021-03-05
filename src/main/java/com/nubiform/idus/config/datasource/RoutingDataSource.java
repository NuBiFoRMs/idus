package com.nubiform.idus.config.datasource;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@Slf4j
public class RoutingDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        log.debug("determineCurrentLookupKey");
        log.debug("isCurrentTransactionReadOnly() : {}", TransactionSynchronizationManager.isCurrentTransactionReadOnly());
        String dataSourceType = TransactionSynchronizationManager.isCurrentTransactionReadOnly() ? "readOnly" : "write";
        log.debug("dataSourceType : {}", dataSourceType);
        return dataSourceType;
    }
}
