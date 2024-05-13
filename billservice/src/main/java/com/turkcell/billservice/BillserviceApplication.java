package com.turkcell.billservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class BillserviceApplication {

	// todo: After completing the authentication server setup, add Spring Security annotations and packages.

	public static void main(String[] args) {
		SpringApplication.run(BillserviceApplication.class, args);
	}

	// if needed add kafka listener to the main

}
