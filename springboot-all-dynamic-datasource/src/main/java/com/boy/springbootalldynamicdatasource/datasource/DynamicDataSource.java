package com.boy.springbootalldynamicdatasource.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.Map;

public class DynamicDataSource extends AbstractRoutingDataSource {
    
	@Override
	protected Object determineCurrentLookupKey() {
		return DynamicDataSourceContextHolder.getDataSourceType();
	}


	public void setTargetDataSources(Map<Object, Object> targetDataSources, DataSource defaultDatabase) {
		setTargetDataSources(targetDataSources);
		if (null != defaultDatabase) {
			setDefaultTargetDataSource(defaultDatabase);
		}
	}


}