package test.com.bookstore.dao;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@ContextConfiguration("classpath:applicationContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class LoadApplicationContextText
{

	@Before
	public void setUp()	{
		System.out.println("setUp");		
	}
	
	@After
	public void setDown() {
		System.out.println("setDown");	
	}

	@Test
	public void test() {
		System.out.println("test...");
	}
}
