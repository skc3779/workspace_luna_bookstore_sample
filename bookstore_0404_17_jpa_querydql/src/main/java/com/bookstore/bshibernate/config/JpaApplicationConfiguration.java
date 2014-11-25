package com.bookstore.bshibernate.config;

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

/*
 * xml Bean을 class로 구현
 * @Configuration
 * @ComponentScan - 은 xml에서 해당 @Service @Component @Repository 등을 읽어오는 부분
 * @EnableTransactionManagement - <tx:annotation-driven/> 
 * @PropertySource("properties") <context:property-placeholder location="properties" /> - 괄호안은 파일이름이 들어간다.
 * 
 * Environment env.getProperty("connect.username") 을 통해서 properties 파일에서 값들을 읽어온다 .
 * 
 * 
 * 	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer(){
		return new PropertySourcesPlaceholderConfigurer();
	}

	프로퍼티 파일을 읽어오기 위해서 필요
 * 
 */
@Configuration
@ComponentScan({"com.bookstore.bshibernate.services","com.bookstore.bshibernate.utils"})
@EnableJpaRepositories(basePackages={"com.bookstore.bshibernate.repository"}, entityManagerFactoryRef="entityManagerFactory")
@EnableTransactionManagement
@PropertySource("spring.properties")
public class JpaApplicationConfiguration {
	
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
		entityManagerFactory.setPackagesToScan("com.bookstore.bshibernate.entities");
		entityManagerFactory.setJpaPropertyMap(jpaPropertyMap);
		entityManagerFactory.setPersistenceUnitName("defaultEntity");
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
