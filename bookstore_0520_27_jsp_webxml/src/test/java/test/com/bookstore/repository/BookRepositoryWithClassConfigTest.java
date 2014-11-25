package test.com.bookstore.repository;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
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
import org.springframework.transaction.annotation.Transactional;

import com.bookstore.config.RootContextConfiguration;
import com.bookstore.entities.Book;
import com.bookstore.entities.BookStatus;
import com.bookstore.entities.History;
import com.bookstore.entities.HistoryActionType;
import com.bookstore.entities.User;
import com.bookstore.entities.UserLevel;
import com.bookstore.repository.BookRepository;
import com.bookstore.repository.HistoryRepository;
import com.bookstore.repository.UserRepository;

@ContextConfiguration(classes = RootContextConfiguration.class)
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class BookRepositoryWithClassConfigTest {

	@Autowired BookRepository bookRepository;
	@Autowired UserRepository userRepository;
	@Autowired HistoryRepository historyRepository;
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {	 
	}
	
	public List<User> getUsers()
	{
		System.out.println("getUser 시작");
		User user1 = new User();
		//user1.setId(1);
		user1.setName("User01");
		user1.setPassword("User01");
		user1.setPoint(5);
		user1.setLevel(UserLevel.NORMAL);
		
		User user2 = new User();
		//user2.setId(2);
		user2.setName("User02");
		user2.setPassword("User02");
		user2.setPoint(10);
		user2.setLevel(UserLevel.READER);
		
		User user3 = new User();
		//user3.setId(3);
		user3.setName("User03");
		user3.setPassword("User03");
		user3.setPoint(25);
		user3.setLevel(UserLevel.MVP);

		List<User> users = Arrays.asList(user1,user2,user3);
		System.out.println("getUser 종료");

		return users;
	}
	
	public List<Book> getBooks(){
		Book book1 = new Book();
		book1.setName("Book04");
		book1.setAuthor("autor name 01");
		book1.setComment("comment01");
		book1.setPublishDate(new Date());
		book1.setBookStatus(BookStatus.CANRENT);
		book1.setRentUser(userRepository.findByName("User02").get(0));

		Book book2 = new Book();
		book2.setName("Book05");
		book2.setAuthor("autor name 02");
		book2.setComment("comment02");
		book2.setPublishDate(new Date());
		book2.setBookStatus(BookStatus.MISSING);
		book2.setRentUser(userRepository.findByName("User02").get(0));

		Book book3 = new Book();
		book3.setName("Book06");
		book3.setAuthor("autor name 03");
		book3.setComment("comment03");
		book3.setPublishDate(new Date());
		book3.setBookStatus(BookStatus.RENTNOW);
		book3.setRentUser(userRepository.findByName("User02").get(0));

		List<Book> books = Arrays.asList(book1,book2,book3);
		return books;
	}	
	
	public List<History> getHistorys()
	{
		System.out.println("getHistory 시작");
		History history1 = new History();
		//history1.setId(1);
		history1.setUser(getUsers().get(0));
		history1.setBook(getBooks().get(0));
		history1.setActionType(HistoryActionType.RENT);
		history1.setInsertDate(new Date());
		
		History history2 = new History();
		history1.setUser(getUsers().get(1));
		history1.setBook(getBooks().get(1));
		history2.setActionType(HistoryActionType.RETURN);
		history2.setInsertDate(new Date());
		
		List<History> historys = Arrays.asList(history1,history2);
		System.out.println("getHistory 종료");
		return historys;
	}
	
	@Test
	public void testCountAll() {
		assertThat((int)(bookRepository.count()), not(0));
	}
	
	@Test
	public void testfindOne() {
		assertThat(bookRepository.findOne(1).getName(), is("Book01"));
	}	

	@Test
	public void testSave() {		

		Book book1 = new Book();
		book1.setId(4);
		book1.setName("Book04");
		book1.setAuthor("autor name 01");
		book1.setComment("comment01");
		book1.setPublishDate(new Date());
		book1.setBookStatus(BookStatus.CANRENT);
		
		User u = userRepository.findByName("User02").get(0);
		book1.setRentUser(u);
		bookRepository.save(book1);
		
		assertThat(bookRepository.count() , is(Long.valueOf(4)));
		
	}

	@Test
	public void testUpdate() {
		
		Book b = bookRepository.findByName("Book01").get(0);
		b.setAuthor("autor name 01");
		bookRepository.save(b);
		System.out.println("testUpdate " + bookRepository.findByName("Book01").get(0).getAuthor());
	}

	@Test
	public void testfindAll() {
		List<Book> books = bookRepository.findAll();
		for(Book b : books) {
			System.out.println("testGet b.getName : " + b.getName());
		}		
	}

}
