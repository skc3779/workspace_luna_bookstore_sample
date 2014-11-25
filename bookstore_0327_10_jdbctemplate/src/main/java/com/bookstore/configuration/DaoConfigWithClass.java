package com.bookstore.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.jolbox.bonecp.BoneCPDataSource;
import com.mysql.jdbc.Driver;

@Configuration
@EnableTransactionManagement
@PropertySource("classpath:/spring.properties")
@ComponentScan(basePackages = {"com.bookstore.dao"})
public class DaoConfigWithClass {

	//@Value("${connect.driver}") Class<? extends Driver> driverClass;
	@Value("${connect.driver}") String driverClass;
	@Value("jdbc:mysql://localhost:3306/bookstore") String url;
	@Value("bookstore") String username;
	@Value("bookstore") String password;
	
	/**
	 * DB연결과 트랜잭션
	 */
	
	
	@Bean
	public DataSource dataSource() {
		BoneCPDataSource ds = new BoneCPDataSource();
		ds.setDriverClass(driverClass);
		ds.setJdbcUrl(url);
		ds.setUsername(username);
		ds.setPassword(password);
		return ds;
/*		
		SimpleDriverDataSource ds = new SimpleDriverDataSource();
		ds.setDriverClass(this.driverClass);
		ds.setUrl(url);
		ds.setUsername(username);
		ds.setPassword(password);
		return (DataSource) ds;
		*/
	}
	
	/*
	 * @ComponentScan(basePackages = {"com.bookstore.dao"})
	 * 프로퍼티 소스로부터 가져오는 값을 @Value 필드에 주입하는 기능ㄹ을 제공해 주기위해서는
	 * 아래와 같이 PropertySourcesPlaceholderConfigurer 클래스를 빈드올 등록해줘야 한다.
	 */
	
	@Bean
	public static PropertySourcesPlaceholderConfigurer placeholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	@Bean
	public PlatformTransactionManager transactionManager() {
		DataSourceTransactionManager tm = new DataSourceTransactionManager();
		tm.setDataSource((javax.sql.DataSource) dataSource());
		return tm;
	}	
	
	@Bean 
	public JdbcTemplate jdbcTemplate() {
		JdbcTemplate jdbc = new JdbcTemplate();
		jdbc.setDataSource(dataSource());
		return jdbc;
	}

/*
 	@Autowired Environment env;
	
	@SuppressWarnings("unchecked")
	@Bean
	public DataSource dataSource() {
		SimpleDriverDataSource ds = new SimpleDriverDataSource();
		try {
			ds.setDriverClass((Class<? extends java.sql.Driver>)Class.forName(env.getProperty("connect.driver")));
		}
		catch(ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
		ds.setUrl(env.getProperty("connect.url"));
		ds.setUsername(env.getProperty("connect.username"));
		ds.setPassword(env.getProperty("connect.password"));
		return (DataSource) ds;
	}	
*/
}
