package com.bookstore.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.bookstore.services.HelloSpring;

@Configuration
public class RootContextConfiguration {

	@Bean
	public static HelloSpring helloSpring() {
		return new HelloSpring();
	}
}
