package com.dyq.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;

@SpringBootApplication
public class TestFileServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestFileServerApplication.class, args);
	}
}
