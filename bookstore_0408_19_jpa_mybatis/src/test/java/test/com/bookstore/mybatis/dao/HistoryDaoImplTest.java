package test.com.bookstore.mybatis.dao;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.bookstore.mybatis.dao.HistoryDao;
import com.bookstore.mybatis.entities.History;
import com.bookstore.mybatis.utils.EntityList;

@ContextConfiguration("classpath:applicationContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class HistoryDaoImplTest {

	@Autowired HistoryDao historyDao;
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testFindAll() {
		List<History> histories = historyDao.findAll();
		EntityList.list(histories);	
	}

}
