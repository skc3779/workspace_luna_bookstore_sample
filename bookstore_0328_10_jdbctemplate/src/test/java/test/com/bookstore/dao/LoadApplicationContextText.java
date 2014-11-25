package test.com.bookstore.dao;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.bookstore.dao.BookDao;

@ContextConfiguration("classpath:/applicationContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class LoadApplicationContextText
{
	@Autowired BookDao bookDao;
	
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
