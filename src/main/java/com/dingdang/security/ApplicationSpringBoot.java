package com.dingdang.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages={"com.dingdang.security"})
public class ApplicationSpringBoot {
	public static void main(String[] args) {
	    SpringApplication.run(ApplicationSpringBoot.class, args);
	  }
}
