package test.com.bookstore.bshibernate.querydsl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.bookstore.bshibernate.entities.*;

@ContextConfiguration("classpath:applicationContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class QueryDslTest {

	@Test
	public void test() {
		//fail("Not yet implemented");
		//QBook book = null;
	}

}
