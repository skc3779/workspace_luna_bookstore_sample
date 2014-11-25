package test.com.bookstore.service;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bookstore.configuration.DaoConfigWithClass;
import com.bookstore.configuration.ServiceConfigWithClass;
import com.bookstore.dao.BookDao;
import com.bookstore.dao.HistoryDao;
import com.bookstore.dao.UserDao;
import com.bookstore.entity.Book;
import com.bookstore.entity.BookStatus;
import com.bookstore.entity.User;
import com.bookstore.entity.UserLevel;
import com.bookstore.service.BookService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
public class BookServiceImplTest
{
	@Autowired
	private UserDao userDao;
	@Autowired
	private BookDao bookDao;
	@Autowired
	private HistoryDao historyDao;
	@Autowired
	private BookService bookService;
	
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
		System.out.println("BookServiceImplTest Setup() 시작");
		
		
		
		// delete from histories
		// Book 객체를 deleteAll() -> add()일정값 -> listup() =  add 했던 List<Book> 과 lustup 했던 List<Book>과 비교하기
		historyDao.deletAll(); // 지울땐 첫번째
		bookDao.deletAll(); // 지울때는 가운데
		userDao.deletAll(); // 지울때는 마지막

		assertThat(historyDao.countAll(), is(0));
		assertThat(bookDao.countAll(), is(0));
		assertThat(userDao.countAll(), is(0));

		userBasicSetting();
		bookBasicSetting();
		System.out.println("BookServiceImplTest Setup() 종료");
	}

	@Test
	public void listup()
	{	
		// status 값을 2,1,0 의 순서로 넣었다. 첫번째 값의 status가 0 부터 인지 확인하여야한다.
		List<Book> books = bookService.listup();
		// 정렬해서 가져온 값
		int bookStatus = 0;
		for (Book book : books)
		{
			assertThat(book.getBookStatus().intValue(), is(bookStatus));
			System.out.println("listup()의 순차적 값(0,1,2) : "+ book.getBookStatus().intValue());
			bookStatus++;
		}
	}
}
