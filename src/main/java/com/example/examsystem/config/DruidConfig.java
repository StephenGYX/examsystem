package com.example.examsystem.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * 配置德鲁伊数据库连接池
 */
@Configuration
public class DruidConfig
{
	@ConfigurationProperties(prefix = "spring.datasource")
	@Bean(destroyMethod = "close", initMethod = "init")
	public DataSource getDataSource()
	{
		return new DruidDataSource();
	}
}
