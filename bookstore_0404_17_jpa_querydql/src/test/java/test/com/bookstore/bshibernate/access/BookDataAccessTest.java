package test.com.bookstore.bshibernate.access;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.bookstore.bshibernate.config.HiberateApplicationConfiguration;
import com.bookstore.bshibernate.config.JpaApplicationConfiguration;
import com.bookstore.bshibernate.entities.Book;
import com.bookstore.bshibernate.entities.BookStatus;
import com.bookstore.bshibernate.entities.History;
import com.bookstore.bshibernate.entities.HistoryActionType;
import com.bookstore.bshibernate.entities.User;
import com.bookstore.bshibernate.entities.UserLevel;
import com.bookstore.bshibernate.repository.BookRepository;
import com.bookstore.bshibernate.repository.HistoryRepository;
import com.bookstore.bshibernate.repository.UserRepository;

@ContextConfiguration(classes = JpaApplicationConfiguration.class)
@RunWith(SpringJUnit4ClassRunner.class)
//@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
public class BookDataAccessTest {

	@Autowired
	private EntityManagerFactory entityManagerFactory;
	
	@PersistenceContext(type = PersistenceContextType.TRANSACTION)
	private EntityManager entityManager;
	
	@Before
	public void setUp() throws Exception {
	}
	
	public List<Book> getBooks(){
		Book book1 = new Book();
		book1.setName("Book01");
		book1.setAuthor("autor name 01");
		book1.setComment("comment01");
		book1.setPublishDate(new Date());
		book1.setBookStatus(BookStatus.CANRENT);
		book1.setRentUser(null);

		Book book2 = new Book();
		book2.setName("Book02");
		book2.setAuthor("autor name 02");
		book2.setComment("comment02");
		book2.setPublishDate(new Date());
		book2.setBookStatus(BookStatus.MISSING);
		book2.setRentUser(null);

		Book book3 = new Book();
		book3.setName("Book03");
		book3.setAuthor("autor name 03");
		book3.setComment("comment03");
		book3.setPublishDate(new Date());
		book3.setBookStatus(BookStatus.RENTNOW);
		book3.setRentUser(null);

		List<Book> books = Arrays.asList(book1,book2,book3);
		return books;
	}	
	
	@Test
	@Ignore
	public void testBookCount_entityManagerFactory() {
		EntityManager em = entityManagerFactory.createEntityManager();

		// transaction을 별도처리 해야함.
		em.getTransaction().begin();
		
		for(Book book : getBooks()) {
			em.persist(book);	
		}
		
		em.getTransaction().commit();
		
		Long count = em.createQuery("select count(b) from Book b", Long.class).getSingleResult();
		
		System.out.println(String.format("book count : %s", count));
		
	}
	
	@Test
	public void testBookCount_entityManager() {
		for(Book book : getBooks()) {
			entityManager.persist(book);	
		}
		
		Long count = entityManager.createQuery("select count(b) from Book b", Long.class).getSingleResult();
		
		System.out.println(String.format("book count : %s", count));
	}


}
