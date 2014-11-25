package test.com.bookstore.bshibernate.dao;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.bookstore.bshibernate.dao.UserDao;
import com.bookstore.bshibernate.entities.Book;
import com.bookstore.bshibernate.entities.User;
import com.bookstore.bshibernate.entities.UserLevel;

@ContextConfiguration("classpath:applicationContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class UserDaoImplTest {
	
	@Autowired UserDao userDao;
	
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
	
	@Test
	public void testCountAll() {
		userDao.deleteAll();	
		assertThat(userDao.countAll(), is(0));
		
		for(User user : getUsers()) {
			userDao.add(user);
		}
		
		assertThat(userDao.countAll(), is(3));
	}

	@Test
	public void testDeletAll() {
		userDao.deleteAll();	
		assertThat(userDao.countAll(), is(0));
		
		for(User user : getUsers()) {
			userDao.add(user);
		}
		
		assertThat(userDao.countAll(), is(3));
	}

	@Test
	public void testAdd() {
		userDao.deleteAll();		
		assertThat(userDao.countAll(), is(0));
		
		for(User user : getUsers()) {
			userDao.add(user);			
		}
		
		assertThat(userDao.countAll(), is(3));
	}

	@Test
	public void testUpdate() {
		List<User> users = userDao.Search("User01");
		User user = users.get(0);
		user.setName("User01");
		userDao.update(user);		
	}

	@Test
	public void testGet() {
		//fail("Not yet implemented");
	}

	@Test
	public void testGetAll() {
		List<User> users = userDao.getAll();
		for(User u : users) {
			System.out.println("testGet b.getName : " + u.getName());
		}
	}

}
