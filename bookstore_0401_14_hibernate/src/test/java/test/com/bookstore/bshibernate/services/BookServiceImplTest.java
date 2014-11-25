package test.com.bookstore.bshibernate.services;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bookstore.bshibernate.services.BookService;

@ContextConfiguration("classpath:applicationContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class BookServiceImplTest {

	@Autowired BookService bookService;

	@Before
	public void setUp() throws Exception {

	}

	@After
	public void tearDown() throws Exception {

	}

	@Test
	public void testListup() {
		ArrayList<Map<String,Object>> bookList = bookService.listup();	
		System.out.println(" count : " + bookList.size());
		for(Map<String,Object> item : bookList) {
			System.out.println("testListup item name " + item.get("name"));
		}
	}
}
