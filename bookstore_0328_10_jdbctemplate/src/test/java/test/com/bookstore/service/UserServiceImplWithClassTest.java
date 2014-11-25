
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
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bookstore.configuration.DaoConfigWithClass;
import com.bookstore.configuration.ServiceConfigWithClass;
import com.bookstore.dao.BookDao;
import com.bookstore.dao.HistoryDao;
import com.bookstore.dao.UserDao;
import com.bookstore.entity.Book;
import com.bookstore.entity.BookStatus;
import com.bookstore.entity.History;
import com.bookstore.entity.User;
import com.bookstore.entity.UserLevel;
import com.bookstore.service.UserService;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DaoConfigWithClass.class, ServiceConfigWithClass.class})
public class UserServiceImplWithClassTest {
	
	@Autowired
	private UserDao userDao;
	@Autowired
	private BookDao bookDao;
	@Autowired
	private HistoryDao historyDao;
	@Autowired
	private UserService userService;

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
		// 전체 갯수가 맞은지 확인하기
		List<User> users = userService.listup();
		// 정렬해서 가져온 값
		int userId = 1;
		for (User user : users)
		{
			assertThat(user.getId(), is(userId));
			System.out.println("listup()의 순차적 Id값(1,2,3) : "+ user.getId());
			userId++;
		}
	}

	@Test
	public void getHistories()
	{	// 유저에 대해 기록 보기
		// 추가하는 순서는 user -> book -> history
		List<History> historys = userService.getHistories(1);
		// getId 값이 1인지 지속적 확인하면 된다.
		for (History history : historys)
		{
			assertThat(history.getUserId(), is(1));
			System.out.println("getHistories()의 userId는 무조건 (1) 결과 : "+ history.getUserId());
		}
	}

	@Test
	public void bookRent()
	{
		// 빌리기전에 빌릴수 없는 조건을 검사한다.
		boolean bookRentCheck = userService.rent(1, 3);
		assertThat(bookRentCheck, is(false));

		// 앞에서 모든 내용을 지우고 기본 셋팅하한 뒤에 실행이므로 값은 제대로 들어간다.
		boolean bookRentCheck1 = userService.rent(1, 1);
		assertThat(bookRentCheck1, is(true));
		// 빌렸으면 빌린상태인지 체크
		assertThat(bookDao.get(1).getBookStatus(), is(BookStatus.RENTNOW));
		// 빌린놈은 1번인지
		assertThat(bookDao.get(1).getRentUserId(), is(1));
		// 히스토리 테이블 전체 갯수를 파악해본다 1번놈의 기록인지
		getHistories();
	}
	
	@Test(expected=NullPointerException.class)
	@DirtiesContext
	public void bookRentWithException(){
		userService.setUserLevelRoleImpl(null);
		Book oldBook = bookDao.get(1);
		assertThat(oldBook.getRentUserId(), is((Object)null));
		System.out.println(oldBook.getRentUserId() +"   "+ (Object)null);
		try {				// userid, bookid
			userService.rent(1, oldBook.getId());
		}finally{
			Book updateBook = bookDao.get(oldBook.getId());
			System.out.println(updateBook.getRentUserId()+"\r\n"+updateBook.getBookStatus() +"\r\n"+ oldBook.getBookStatus());
			assertThat(updateBook.getBookStatus(), is(oldBook.getBookStatus()));
			assertThat(updateBook.getRentUserId(), is((Object)null));
		}
	}

	@Test
	public void bookReturn()
	{
		// 반납하기전에 반납할수 없는 조건을 실행한다.
		boolean bookRentCheck = userService.returnBook(1, 1);
		assertThat(bookRentCheck, is(false));

		// 일단 빌리는거 먼저 ㄱㄱ
		bookRent();
		boolean bookRentCheck1 = userService.returnBook(1, 1);
		assertThat(bookRentCheck1, is(true));

		// 빌릴수 있는 상태인지
		assertThat(bookDao.get(1).getBookStatus(), is(BookStatus.CANRENT));
		// 빌린놈은 1번인지 (??????????????? NULL 이여야할것 같은데)
		assertThat(bookDao.get(1).getRentUserId(), is((Object)null));
		// 히스토리 기록 살펴보기
		System.out.println("aasdaa"+bookDao.get(1).getRentUserId());
		getHistories();
	}
	
	@SuppressWarnings("null")
	@Test(expected=NullPointerException.class)
	@DirtiesContext
	public void bookReturnWithException(){
		bookRent();
		
		Book oldBook = bookDao.get(1);
		User oldUser = null;
		try {	// userid = Null로 넣어 주어 book 처음 업데이트후, bookid
			// 히스토리 때 난감하게 만들기
			userService.rent(oldUser.getId(), oldBook.getId());
		}finally{
			Book updateBook = bookDao.get(oldBook.getId());
			System.out.println(oldBook.getRentUserId()+"\r\n"+updateBook.getRentUserId()+"\r\n"+updateBook.getBookStatus() +"\r\n"+ oldBook.getBookStatus());
			assertThat(updateBook.getBookStatus(), is(oldBook.getBookStatus()));
			assertThat(updateBook.getRentUserId(), is(oldBook.getRentUserId()));
		}
	}

	@Test
	public void whileBookReturn()
	{// 반복문 써서 Rent(),Return() 반복해서 테스트 할꺼임!
		for(int i = 0; i < 50; i++)
		{
			bookReturn();
		}
	}
}
