package com.pedidos.gerenciamento.configutation;

import javax.sql.DataSource;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class DataSourceHSQLDConfig {
	
	@Bean
	@Profile(value = "test")
	public DataSource getDataSource() {
		return DataSourceBuilder
				.create()
				.username("admin")
				.password("hsqldb123")
				.url("jdbc:hsqldb:file:database/hsqldb/hsqldb")
				.driverClassName("org.hsqldb.jdbc.JDBCDriver")
				.build();
	}

}
