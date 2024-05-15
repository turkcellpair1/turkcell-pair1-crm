package com.turkcell.authserver;

import com.turkcell.core.annotations.EnableSecurity;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EnableSecurity
public class AuthserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthserverApplication.class, args);
	}

}
