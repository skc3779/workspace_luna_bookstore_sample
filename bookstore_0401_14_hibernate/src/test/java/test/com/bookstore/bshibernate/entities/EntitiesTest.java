package test.com.bookstore.bshibernate.entities;

import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bookstore.bshibernate.entities.Book;
import com.bookstore.bshibernate.entities.BookStatus;
import com.bookstore.bshibernate.entities.History;
import com.bookstore.bshibernate.entities.User;

@ContextConfiguration("classpath:applicationContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class EntitiesTest {

	@Autowired SessionFactory sessionFactory;
	
	Session session;
	
	@Before
	public void setUp() throws Exception {
		
		session = sessionFactory.openSession();
	}

	@After
	public void tearDown() throws Exception {
		
		session.close();
		
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testEntities() {
		List<Book> list;
		list = session.createCriteria(Book.class).setMaxResults(3).list();
		for(Book b : list){
			System.out.println("book name : " + b.getName() + " " +
					b.getBookStatus().intValue() + " " + (BookStatus.MISSING == b.getBookStatus() ? "Y" : "N"));
		}	
		
	}

	@Test
	public void testHistories() {
		selectTest(new History());
	}
	
	@Test
	public void testUsers() {
		selectTest(new User());
	}	
	
	private <T> void selectTest(T c) {
		@SuppressWarnings("unchecked")
		List<T> list = session.createCriteria(c.getClass()).setMaxResults(3).list();
		Boolean bClass = true;
		for (T t : list) {
			try {
				for (PropertyDescriptor pd : Introspector.getBeanInfo(
						c.getClass()).getPropertyDescriptors()) {					
					if("class".equals(pd.getName()) && bClass.equals(true) ) {
						bClass = false;
						System.out.println(pd.getReadMethod().invoke(t));
						System.out.println("00");
					} else {
						System.out.println(pd.getReadMethod().invoke(t));
					}
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}	

}
