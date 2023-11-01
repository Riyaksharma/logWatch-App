package com.logWatch.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class LogWatchApplication {

	public static void main(String[] args) {
		SpringApplication.run(LogWatchApplication.class, args);
	}

}
