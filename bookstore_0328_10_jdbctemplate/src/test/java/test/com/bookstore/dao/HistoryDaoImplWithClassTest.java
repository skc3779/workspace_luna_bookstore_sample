package test.com.bookstore.dao;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bookstore.configuration.DaoConfigWithClass;
import com.bookstore.dao.BookDao;
import com.bookstore.dao.HistoryDao;
import com.bookstore.dao.UserDao;
import com.bookstore.entity.Book;
import com.bookstore.entity.BookStatus;
import com.bookstore.entity.History;
import com.bookstore.entity.HistoryActionType;
import com.bookstore.entity.User;
import com.bookstore.entity.UserLevel;

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("test")
@ContextConfiguration(classes = DaoConfigWithClass.class)
public class HistoryDaoImplWithClassTest {
	@Autowired
	private UserDao userDao;
	@Autowired
	private BookDao bookDao;
	@Autowired
	private HistoryDao historyDao;
	
	public List<History> getHistory()
	{
		System.out.println("getHistory 시작");
		History history1 = new History();
		history1.setId(1);
		history1.setUserId(1);
		history1.setBookId(1);
		history1.setActionType(HistoryActionType.RENT);
		history1.setInsertDate(new Date());
		
		History history2 = new History();
		history2.setId(2);
		history2.setUserId(2);
		history2.setBookId(2);
		history2.setActionType(HistoryActionType.RETURN);
		history2.setInsertDate(new Date());
		
		List<History> historys = Arrays.asList(history1,history2);
		System.out.println("getHistory 종료");
		return historys;
	}
	
	public List<User> getUser()
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
	
	public void userBasicSetting()
	{
		// User를 추가한다.
		List<User> users = getUser();
		int count = 0;
		for (User user : users)
		{
			userDao.add(user);
			count++;
			assertThat(userDao.countAll(), is(count));
		}
	}

	public void bookBasicSetting()
	{
		// Book을 추가한다.
		List<Book> books = getBooks();
		int count1 = 0;
		for (Book book : books)
		{
			bookDao.add(book);
			count1++;
			assertThat(bookDao.countAll(), is(count1));
		}
		// User와 Book의 기본데이터를 심은 뒤에 listup을 한다.
	}
	
	@Before
	public void setUp()
	{
		System.out.println("setUp시작");
		
		historyDao.deletAll(); // 지울땐 첫번째
		bookDao.deletAll(); // 지울때는 가운데
		userDao.deletAll(); // 지울때는 마지막
		
		assertThat(historyDao.countAll(), is(0));
		assertThat(bookDao.countAll(), is(0));
		assertThat(userDao.countAll(), is(0));
		
		userBasicSetting();
		bookBasicSetting();
		System.out.println("setUp완료");
	}
	
	@Test
	public void addAndCount()
	{
		System.out.println("addAndCount 시작");
		// 바로 추가 불가 books, users 테이블의 해당 1. userId, 2. bookId 가 들어가 있어야함
		
		List<History> historys = getHistory();
		int count3 = 0;
		for (History history : historys)
		{
			historyDao.add(history);
			count3++;
			assertThat(historyDao.countAll(), is(count3));
		}
		System.out.println("addAndCount 종료");
	}

	@Test
	public void getAll()
	{
		System.out.println("getAll 시작");
		List<History> historys = getHistory();
		int count = 0;
		for (History history : historys)
		{
			historyDao.add(history);
			count++;
			assertThat(historyDao.countAll(), is(count));
		}
		
		List<History> historys2 = historyDao.getAll();
		assertThat(historys2.size(), is(historys.size()));
		System.out.println("getAll 종료");
	}
	
	@Test
	public void getByUser()
	{
		System.out.println("getByUser 시작");
		List<History> historys = getHistory();
		int count = 0;
		for (History history : historys)
		{
			historyDao.add(history);
			count++;
			assertThat(historyDao.countAll(), is(count));
		}
		
		List<History> historys2 = historyDao.getByUser(1);
		for (History history : historys2)
		{
			assertThat(history.getUserId(),is(1));
		}
		System.out.println("getByUser 종료");
	}
	
	@Test
	public void getByBook()
	{
		System.out.println("getByUser 시작");
		List<History> historys = getHistory();
		int count = 0;
		for (History history : historys)
		{
			historyDao.add(history);
			count++;
			assertThat(historyDao.countAll(), is(count));
		}
		
		List<History> historys2 = historyDao.getByBook(1);
		for (History history : historys2)
		{
			assertThat(history.getBookId(),is(1));
		}
		System.out.println("getByUser 종료");
	}
}
