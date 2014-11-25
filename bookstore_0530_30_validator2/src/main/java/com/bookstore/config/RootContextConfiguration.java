package com.bookstore.config;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.jolbox.bonecp.BoneCPDataSource;

@Configuration
@EnableTransactionManagement
@ComponentScan({"com.bookstore.services","com.bookstore.utils", "com.bookstore.validators"})
@EnableJpaRepositories(basePackages={"com.bookstore.repository"}, entityManagerFactoryRef="entityManagerFactory")
@PropertySource("classpath:/com/bookstore/config/spring.properties")
public class RootContextConfiguration {
	@Autowired
	private Environment env;
	
	@Bean(destroyMethod="close")
	public DataSource dataSource(){
		BoneCPDataSource dataSource = new BoneCPDataSource();
		dataSource.setUsername(env.getProperty("connect.username"));
		dataSource.setPassword(env.getProperty("connect.password"));
		dataSource.setJdbcUrl(env.getProperty("connect.url"));
		dataSource.setDriverClass(env.getProperty("connect.driver"));
		dataSource.setIdleConnectionTestPeriodInMinutes(60);
		dataSource.setIdleMaxAgeInMinutes(240);
		dataSource.setMaxConnectionsPerPartition(30);
		dataSource.setMinConnectionsPerPartition(10);
		dataSource.setPartitionCount(3);
		dataSource.setAcquireIncrement(5);
		dataSource.setReleaseHelperThreads(3);
		return dataSource;
	}
	
	/*
	 * xml 파일을 가져 오기 의해 new ClassPathResource 를 사용 
	 */
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(){
		
		Map<String, String> jpaPropertyMap = new HashMap<String, String>();
		jpaPropertyMap.put("hibernate.dialect", "org.hibernate.dialect.MySQL5InnoDBDialect");
		LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactory.setDataSource(dataSource());		
		entityManagerFactory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		entityManagerFactory.setPackagesToScan("com.bookstore.entities");
		entityManagerFactory.setJpaPropertyMap(jpaPropertyMap);
		//entityManagerFactory.setPersistenceUnitName("defaultEntity");
		return entityManagerFactory;
	}
	
	// sessionFactoryFactory().getObject() FactoryFactory 이기 때문에 getObject() 받아야한다.
	@Bean
	public JpaTransactionManager transactionManager(){
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setDataSource(dataSource());
		transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
		return transactionManager;
	}
	
	// xml 를 클래스로 하용하기 위해서는 꼭 존재 해야한다.
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer(){
		return new PropertySourcesPlaceholderConfigurer();
	}

	
}
