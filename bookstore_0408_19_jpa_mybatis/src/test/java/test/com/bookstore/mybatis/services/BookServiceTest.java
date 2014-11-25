package test.com.bookstore.mybatis.services;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.bookstore.mybatis.entities.Book;
import com.bookstore.mybatis.services.BookService;

@ContextConfiguration("classpath:applicationContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
public class BookServiceTest {

	@Autowired BookService bookService;
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testfindAll() {
		// Find all Book
		List<Book>  books = bookService.findAll();
		listContacts(books);	
	}
	
	private static void listContacts(List<Book> books) {
		System.out.println("Listing books :");		
		for (Book book: books) {
			System.out.println(" " + book.getName());
		}
	}
}
