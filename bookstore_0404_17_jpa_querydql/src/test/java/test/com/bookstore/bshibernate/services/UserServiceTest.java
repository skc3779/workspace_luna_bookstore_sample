package test.com.bookstore.bshibernate.services;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.bookstore.bshibernate.entities.History;
import com.bookstore.bshibernate.services.BookService;
import com.bookstore.bshibernate.services.UserLevelRole;
import com.bookstore.bshibernate.services.UserService;


@ContextConfiguration("classpath:applicationContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
public class UserServiceTest {

	@Autowired UserService userService;
	@Autowired BookService bookService;
	@Autowired UserLevelRole useLevelRole;
	
	@Test
	public void testHistories() {

		List<History> histories = userService.listupHistories(1);
		for(History h : histories) {
			System.out.println("testHistories getBook  " + h.getBook().getName());
		}
	}
	
	@Test
	public void testRent() {
		// 3번 사용자가 1번 책을 대여
		assertThat(userService.rent(3, 1), is(true));
	}
	
	@Test
	public void testReturn() {
		// 3번 사용자가 2번 책을 반납
		assertThat(userService.returnBook(3, 2), is(true));
	}	

}
