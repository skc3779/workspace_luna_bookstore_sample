package test.com.bookstore.bshibernate.services;

import java.util.ArrayList;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.bookstore.bshibernate.services.BookDslService;

@ContextConfiguration("classpath:applicationContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
public class BookDslServiceTest {

	@Autowired BookDslService bookDslService;

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
		
	}

	@Test
	public void testLineup() {
		ArrayList<Map<String,Object>> bookList  = bookDslService.listup();		
		for(Map<String,Object> b: bookList) {
			System.out.println("Lineup Name : " + b.get("name"));
		}
	}

}
