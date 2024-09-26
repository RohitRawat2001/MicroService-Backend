package com.user.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class UserSeviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserSeviceApplication.class, args);
	}

}
