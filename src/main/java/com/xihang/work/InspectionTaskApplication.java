package com.xihang.work;

import com.xihang.work.repositories.RepositoriesInitializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableAutoConfiguration
public class InspectionTaskApplication {
	@Autowired
	private RepositoriesInitializer initializer;
	/**
	 * 初始化数据库
	 *
	 * @return
	 */
	@Bean
	public CommandLineRunner initialize() {
		return strings -> {
			initializer.initialize();
		};
	}
	public static void main(String[] args) {
		SpringApplication.run(InspectionTaskApplication.class, args);
	}

}

