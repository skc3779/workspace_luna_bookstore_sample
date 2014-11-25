package test.com.bookstore.mybatis.dao.xml;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.bookstore.mybatis.dao.xml.BookDao;
import com.bookstore.mybatis.dao.xml.BookDaoImpl;
import com.bookstore.mybatis.entities.Book;

public class BookDaoImplTest {

	@Before
	public void setUp() throws Exception {
		System.out.println("Before ..");
	}

	@After
	public void tearDown() throws Exception {
		System.out.println("After ..");		
	}
	
	@Test
	public void testFindById() {
		BookDao bookDao = new BookDaoImpl();
		Book book = bookDao.findById(1);
		System.out.println("book .. getAuthor : " + book.getAuthor());
	}

}
