package com.hzl.hadoop.config.mybatis;

import com.hzl.hadoop.constant.DBTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.lang.Nullable;

import javax.sql.DataSource;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * description
 * 动态切换数据源类
 *
 * @author hzl 2020/01/07 9:56 PM
 */
@Slf4j
public class DynamicDataSource extends AbstractRoutingDataSource {


	public DynamicDataSource(DataSource defaultTargetDataSource, Map<Object, Object> targetDataSources) {
		super.setDefaultTargetDataSource(defaultTargetDataSource);
		super.setTargetDataSources(targetDataSources);
		super.afterPropertiesSet();
	}

	@Nullable
	@Override
	protected Object determineCurrentLookupKey() {
		log.info("当前线程获取的数据源"+DBContextHolder.getDataSource());
		return DBContextHolder.getDataSource();
	}


}
