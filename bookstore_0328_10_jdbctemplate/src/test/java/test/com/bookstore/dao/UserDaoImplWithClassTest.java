package test.com.bookstore.dao;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bookstore.configuration.DaoConfigWithClass;
import com.bookstore.dao.BookDao;
import com.bookstore.dao.HistoryDao;
import com.bookstore.dao.UserDao;
import com.bookstore.entity.User;
import com.bookstore.entity.UserLevel;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DaoConfigWithClass.class)
public class UserDaoImplWithClassTest
{
	@Autowired
	private UserDao userDao;
	@Autowired
	private BookDao bookDaoe;
	@Autowired
	private HistoryDao historyDao;
	
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
	
	private void compareUser(final User user)
	{
		System.out.println("compareUser 시작");
		User dbUser = userDao.get(user.getId());
		assertThat(dbUser.getName(), is(user.getName()));
		assertThat(dbUser.getPassword(), is(user.getPassword()));
		assertThat(dbUser.getPoint().toString(), is(user.getPoint().toString()));
		assertThat(dbUser.getLevel().intValue(), is(user.getLevel().intValue()));
		System.out.println("compareUser 종료");
	}
	
	@Before
	public void setUp()
	{
		System.out.println("User setUp시작");
		
		historyDao.deletAll(); // 지울땐 첫번째
		bookDaoe.deletAll(); // 지울때는 가운데
		userDao.deletAll(); // 지울때는 마지막
		
		assertThat(historyDao.countAll(), is(0));
		assertThat(bookDaoe.countAll(), is(0));
		assertThat(userDao.countAll(), is(0));
		System.out.println("User setUp완료");
	}
	
	@Test
	public void addAndCount()
	{
		System.out.println("addAndCount 시작");
		List<User> users = getUser();
		int count = 0;
		for (User user : users)
		{
			userDao.add(user);
			count++;
			assertThat(userDao.countAll(), is(count));
		}
		System.out.println("addAndCount 종료");
	}
	
	@Test
	public void update()
	{
		System.out.println("update 시작");
		List<User> users = getUser();
		int count = 0;
		for(User user : users)
		{
			userDao.add(user);
			count++;
			assertThat(userDao.countAll(), is(count));
			
			user.setName("changed");
			user.setPassword("changpass");
			user.setPoint(100);
			user.setLevel(UserLevel.MVP);
			userDao.update(user);
			
			compareUser(user);
		}
		System.out.println("update 종료");
	}
	
	@Test
	public void getAll()
	{
		System.out.println("getAll 시작");
		List<User> users = getUser();
		int count = 0;
		for(User user : users)
		{
			userDao.add(user);
			count ++;
			assertThat(userDao.countAll(), is(count));
		}
		
		List<User> users2 = userDao.getAll();
		assertThat(users2.size(), is(users.size()));
		System.out.println("getAll 종료");
	}
}
