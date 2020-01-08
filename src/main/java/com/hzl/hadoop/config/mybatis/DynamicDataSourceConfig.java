package com.hzl.hadoop.config.mybatis;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.hzl.hadoop.constant.DBTypeEnum;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * description
 *
 * @author hzl 2020/01/07 9:54 PM
 */

@Configuration
@Component
public class DynamicDataSourceConfig {

	@Bean
	@ConfigurationProperties("spring.datasource.master")
	@Primary
	public DataSource masterDataSource() {
		return DruidDataSourceBuilder.create().build();
	}

	@Bean
	@ConfigurationProperties("spring.datasource.slave1")
	public DataSource slaveDataSource() {
		return DruidDataSourceBuilder.create().build();
	}

	@Bean
	public DynamicDataSource dataSource(@Qualifier("masterDataSource") DataSource masterDataSource, @Qualifier("slaveDataSource") DataSource slaveDataSource) {
		Map<Object, Object> targetDataSources = new HashMap<>();
		targetDataSources.put(DBTypeEnum.MASTER.value(), masterDataSource);
		if(slaveDataSource!=null){
			targetDataSources.put(DBTypeEnum.SLAVE1.value(), slaveDataSource);
		}
		return new DynamicDataSource(masterDataSource, targetDataSources);
	}

}
