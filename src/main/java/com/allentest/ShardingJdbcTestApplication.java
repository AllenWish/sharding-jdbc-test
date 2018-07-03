package com.allentest;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.allentest.dao")
public class ShardingJdbcTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShardingJdbcTestApplication.class, args);
	}
}
