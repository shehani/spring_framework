package com.example;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.XADataSourceAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(exclude = {XADataSourceAutoConfiguration.class})
@EnableJpaRepositories("com.example.repository")
@EntityScan("com.example.model")
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
public class ChurchApplication {

	public static void main(String[] args) {
		org.springframework.boot.SpringApplication.run(ChurchApplication.class, args);
	}

}
