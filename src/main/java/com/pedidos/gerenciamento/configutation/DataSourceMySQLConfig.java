package com.pedidos.gerenciamento.configutation;

import javax.sql.DataSource;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class DataSourceMySQLConfig {
	
	@Bean
	@Profile(value = "prod")
	public DataSource getDataSource() {
		return DataSourceBuilder
				.create()
				.username("root")
				.password("pedido123")
				.url("jdbc:mysql://localhost:3306/pedido")
				.driverClassName("com.mysql.jdbc.Driver")
				.build();
	}

}
