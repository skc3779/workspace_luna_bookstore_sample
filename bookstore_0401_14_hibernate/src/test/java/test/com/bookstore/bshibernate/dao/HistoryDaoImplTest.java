package test.com.bookstore.bshibernate.dao;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

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
import org.springframework.transaction.annotation.Transactional;

import com.bookstore.bshibernate.dao.BookDao;
import com.bookstore.bshibernate.dao.HistoryDao;
import com.bookstore.bshibernate.dao.UserDao;
import com.bookstore.bshibernate.entities.Book;
import com.bookstore.bshibernate.entities.BookStatus;
import com.bookstore.bshibernate.entities.History;
import com.bookstore.bshibernate.entities.HistoryActionType;
import com.bookstore.bshibernate.entities.User;
import com.bookstore.bshibernate.entities.UserLevel;

@ContextConfiguration("classpath:applicationContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class HistoryDaoImplTest {

	@Autowired BookDao bookDao;
	@Autowired HistoryDao historyDao;
	@Autowired UserDao userDao;
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	public List<History> getHistorys()
	{
		System.out.println("getHistory 시작");
		History history1 = new History();
		history1.setId(1);
		history1.setActionType(HistoryActionType.RENT);
		history1.setInsertDate(new Date());
		
		History history2 = new History();
		history2.setId(2);
		history2.setActionType(HistoryActionType.RETURN);
		history2.setInsertDate(new Date());
		
		List<History> historys = Arrays.asList(history1,history2);
		System.out.println("getHistory 종료");
		return historys;
	}
	
	public List<User> getUsers()
	{
		System.out.println("getUser 시작");
		User user1 = new User();
		user1.setId(1);
		user1.setName("User01");
		user1.setPassword("User01");
		user1.setPoint(5);
		user1.setLevel(UserLevel.NORMAL);
		
		User user2 = new User();
		user2.setId(2);
		user2.setName("User02");
		user2.setPassword("User02");
		user2.setPoint(10);
		user2.setLevel(UserLevel.READER);
		
		User user3 = new User();
		user3.setId(3);
		user3.setName("User03");
		user3.setPassword("User03");
		user3.setPoint(25);
		user3.setLevel(UserLevel.MVP);
		
		List<User> users = Arrays.asList(user1,user2,user3);
		System.out.println("getUser 종료");
		
		return users;
	}
	
	public List<Book> getBooks(){
		System.out.println("getBooks 시작");
		Book book1 = new Book();
		book1.setId(1);
		book1.setName("book name01");
		book1.setAuthor("autor name 01");
		book1.setComment("comment01");
		book1.setPublishDate(new Date());
		book1.setBookStatus(BookStatus.CANRENT);
		book1.setRentUserId(null);

		Book book2 = new Book();
		book2.setId(2);
		book2.setName("book name02");
		book2.setAuthor("autor name 02");
		book2.setComment("comment02");
		book2.setPublishDate(new Date());
		book2.setBookStatus(BookStatus.MISSING);
		book2.setRentUserId(null);

		Book book3 = new Book();
		book3.setId(3);
		book3.setName("book name03");
		book3.setAuthor("autor name 03");
		book3.setComment("comment03");
		book3.setPublishDate(new Date());
		book3.setBookStatus(BookStatus.RENTNOW);
		book3.setRentUserId(1);

		List<Book> books = Arrays.asList(book1,book2,book3);
		System.out.println("getBooks 종료");
		return books;
	}	
	
	@Test
	public void testDeleteAll() {
		historyDao.deleteAll();		
		assertThat(historyDao.countAll(), is(0));
	}

	@Test
	public void testCountAll() {
		historyDao.deleteAll();		
		assertThat(historyDao.countAll(), is(0));
	}

	@Test
	public void testAdd() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetAll() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetByUser() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetByBook() {
		fail("Not yet implemented");
	}

}
