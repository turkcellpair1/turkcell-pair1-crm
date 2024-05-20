package com.turkcell.customerservice;

import com.turkcell.core.annotations.EnableSecurity;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EnableSecurity
public class CustomerserviceApplication {

	// todo: After completing the authentication server setup, add Spring Security annotations and packages.

	public static void main(String[] args) {
		SpringApplication.run(CustomerserviceApplication.class, args);
	}

	// if needed add kafka listener to the main
}
