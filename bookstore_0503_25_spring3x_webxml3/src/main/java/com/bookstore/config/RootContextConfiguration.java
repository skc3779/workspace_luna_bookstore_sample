package com.bookstore.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.bookstore.services.HelloSpring;

@Configuration
@PropertySource("classpath:/WEB-INF/spring/spring.properties")
public class RootContextConfiguration {

	@Bean
	public static HelloSpring helloSpring() {
		return new HelloSpring();
	}
}
