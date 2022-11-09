package com.greatLearning.employeeService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableWebSecurity
public class EmployeeService {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeService.class, args);
	}
}
