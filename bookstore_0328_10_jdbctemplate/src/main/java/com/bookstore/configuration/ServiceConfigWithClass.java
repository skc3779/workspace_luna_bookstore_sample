package com.bookstore.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.bookstore.service.BookService;
import com.bookstore.service.BookServiceImpl;
import com.bookstore.service.UserLevelRole;
import com.bookstore.service.UserLevelRoleImpl;
import com.bookstore.service.UserService;
import com.bookstore.service.UserServiceImpl;

@Configuration
@ComponentScan(basePackages="com.bookstore.service")
public class ServiceConfigWithClass {

}
