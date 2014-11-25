package test.com.bookstore.service;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

public class PropertySourceTest {
	
	@Test
	public void testPropertySource()
	{
		String name="a";
		assertThat(name, is("a"));
		
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext();
		ac.register(PropertySourceConfig.class);
		ac.refresh();		
		assertThat(ac.getBean(PropertySourceConfig.class).connectDriver, is("com.mysql.jdbc.Driver"));
		ac.close();
	}
	
	@Configuration
	@PropertySource(name="DB Settings", value="spring.properties")
	public static class PropertySourceConfig
	{
		@Value("${connect.driver}") String connectDriver;	

		/*
		 * @ComponentScan(basePackages = {"com.bookstore.dao"})
		 * 프로퍼티 소스로부터 가져오는 값을 @Value 필드에 주입하는 기능ㄹ을 제공해 주기위해서는
		 * 아래와 같이 PropertySourcesPlaceholderConfigurer 클래스를 빈드올 등록해줘야 한다.
		 */		
		@Bean
		public static PropertySourcesPlaceholderConfigurer placeholderConfigurer() {
			return new PropertySourcesPlaceholderConfigurer();
		}		
	
	}

}
