package com.bookstore.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.EnableWebSecurity;
import org.springframework.security.config.annotation.web.HttpConfiguration;
import org.springframework.security.config.annotation.web.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
/*
 * WebSecurityConfigurerAdapter 사용을 위해서는 spring 3.2.3 이상버전을 사용
 */
public class SecurityContextConfiguration extends WebSecurityConfigurerAdapter {
	
	@Override
    protected void configure(HttpConfiguration http) throws Exception {
        http.formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .loginUrl("/login")
                .failureUrl("/loginfail")
                .usernameParameter("username")
                .passwordParameter("password")
             .and()
             .logout()
	             .logoutUrl("/logout")
	             .logoutSuccessUrl("/login")
             .and()
             .authorizeUrls()
             	.antMatchers("/**").permitAll();
    }	

    @Override
    protected void registerAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
        .withUser("user").password("password").roles("USER");
    }
}
