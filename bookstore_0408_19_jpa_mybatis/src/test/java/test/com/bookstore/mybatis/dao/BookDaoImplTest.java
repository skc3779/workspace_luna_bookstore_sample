package test.com.bookstore.mybatis.dao;

import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.bookstore.mybatis.dao.BookDao;
import com.bookstore.mybatis.entities.Book;
import com.bookstore.mybatis.entities.BookStatus;
import com.bookstore.mybatis.entities.History;
import com.bookstore.mybatis.utils.EntityList;

@ContextConfiguration("classpath:applicationContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class BookDaoImplTest {

	@Autowired BookDao bookDao;
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testFindAll() {
		List<Book>  books = bookDao.findAll();
		EntityList.list(books);	
	}
	

	@Test
	public void testFindAllAnnotation() {
		System.out.println("testFindAllAnnotation...");
		List<Book>  books = bookDao.findAllAnnotation();
		EntityList.list(books);	
	}	
	
	@Test
	public void testfindAllWithHistories() {
		List<Book>  books = bookDao.findAllHistories();
		for(Book b: books) {
			List<History> list = b.getHistories();
			EntityList.list(list);	
		}

	}
	
	@Test
	public void testFindById() {
		Book book = bookDao.findById(1);
		
		if( book != null ) {
			System.out.println(" book name : " + book.getName());	
		}
	}
	
	@Test
//	@Ignore
	public void testInsert() {
		Book book1 = new Book();
		book1.setName("Book04");
		book1.setAuthor("Autor04");
		book1.setComment("comment04");
		book1.setPublishDate(new Date());
		book1.setBookStatus(BookStatus.CANRENT);
		book1.setRentUserId(1);
		bookDao.save(book1);
	}

	@Test
	public void testUpdate() {
		Book book = bookDao.findById(1);
		book.setAuthor("Autor04 update");
		book.setBookStatus(BookStatus.RENTNOW);
		bookDao.update(book);		
		book = bookDao.findById(1);		
		if( book != null ) {
			System.out.println(" book autor : " + book.getAuthor() + ", " + book.getBookStatus().intValue());	
		}		
	}

	@Test
	@Ignore
	public void testDelete() {
		bookDao.delete(1);
		Book book = bookDao.findById(1);
		assertThat(book, nullValue());
	}
	
}
