package test.com.bookstore.bshibernate.dao;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.Date;
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

import com.bookstore.bshibernate.config.HiberateApplicationConfiguration;
import com.bookstore.bshibernate.dao.BookDao;
import com.bookstore.bshibernate.entities.Book;
import com.bookstore.bshibernate.entities.BookStatus;

@ContextConfiguration(classes = HiberateApplicationConfiguration.class)
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@TransactionConfiguration(defaultRollback = false)
public class BookDaoImplTest {

	@Autowired BookDao bookDao;

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {	 
	}

	@Test
	public void testCount() {
		assertThat(bookDao.count(), is(3L));
	}
	
	public List<Book> getBooks(){
		Book book1 = new Book();
		//book1.setId(16);
		book1.setName("book name01");
		book1.setAuthor("autor name 01");
		book1.setComment("comment01");
		book1.setPublishDate(new Date());
		book1.setBookStatus(BookStatus.CANRENT);
		book1.setRentUser(null);

		Book book2 = new Book();
//		book2.setId(2);
		book2.setName("book name02");
		book2.setAuthor("autor name 02");
		book2.setComment("comment02");
		book2.setPublishDate(new Date());
		book2.setBookStatus(BookStatus.MISSING);
		book2.setRentUser(null);

		Book book3 = new Book();
//		book3.setId(3);
		book3.setName("book name03");
		book3.setAuthor("autor name 03");
		book3.setComment("comment03");
		book3.setPublishDate(new Date());
		book3.setBookStatus(BookStatus.RENTNOW);
		book3.setRentUser(null);

		List<Book> books = Arrays.asList(book1,book2,book3);
		return books;
	}
	
	@Test
	public void testDeletAllAndSave() throws Exception {	
		bookDao.deleteAll();		
		assertThat(bookDao.count(), is(0L));
		
		for(Book b : getBooks()) {
			bookDao.save(b);
		}
		
		assertThat(bookDao.count(), is(3L));
	}
	
	@Test	
	public void testFindOneAndFindByName() {
		List<Book> books = bookDao.findByName("book name01");
		for(Book b : books) {
			System.out.println("testGet b.getName : " + b.getName());
			assertThat(bookDao.findOne(b.getId()).getName(), is("book name01"));
		}
	}	

	@Test
	public void testUpdate() {
		List<Book> books = bookDao.findByName("book name01");
		for(Book b : books) {
			b.setAuthor("autor name 02 update");
			b.setComment("comment02 update");
			bookDao.update(b);
		}			
	}

	@Test
	public void testFindAll() {
		List<Book> books = bookDao.findAll();
		for(Book b : books) {
			System.out.println("testGet b.getName : " + b.getName());
		}		
	}
}
