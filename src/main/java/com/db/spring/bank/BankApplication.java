package com.db.spring.bank;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.context.annotation.ComponentScan;
//
//import com.db.spring.controller.UserController;
//
//@ComponentScan(basePackageClasses = UserController.class)

@SpringBootApplication
public class BankApplication {
	
	private static final Logger LOG = LoggerFactory.getLogger(BankApplication.class);

	public static void main(String[] args) {
		LOG.info("Start");
		SpringApplication.run(BankApplication.class, args);
		LOG.info("Started");
	}

}
