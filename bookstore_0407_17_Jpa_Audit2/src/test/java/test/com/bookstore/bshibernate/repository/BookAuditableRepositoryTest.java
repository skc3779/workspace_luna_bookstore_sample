package test.com.bookstore.bshibernate.repository;

import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.bookstore.bshibernate.entities.BookAudit;
import com.bookstore.bshibernate.entities.BookAuditable;
import com.bookstore.bshibernate.entities.BookStatus;
import com.bookstore.bshibernate.repository.BookAuditRepository;
import com.bookstore.bshibernate.repository.BookAuditableRepository;
/*
 * 오류발생 원인파악 못함 04/07
 */
@ContextConfiguration("classpath:applicationContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
@Transactional
public class BookAuditableRepositoryTest {

	@Autowired BookAuditableRepository bookAuditableRepository;
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {	 
	}
	
	private static <T> void listObject(List<T> l) {
		System.out.println("");
		System.out.println("Listing contacts without details:");
		for (T t: l) {
			try {
				for (PropertyDescriptor pd : Introspector.getBeanInfo(
						t.getClass()).getPropertyDescriptors()) {
					if ( !"class".equals(pd.getName()) )
						System.out.println(pd.getName() + " : " + pd.getReadMethod().invoke(t));
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}			
		}
	}

	@Test
	public void testSave() {		

		BookAudit book1 = new BookAudit();
		book1.setName("Book01");
		book1.setAuthor("autor name 01");
		book1.setComment("comment01");
		book1.setPublishDate(new Date());
		book1.setBookStatus(BookStatus.CANRENT);
		book1.setRentUser(1);
		
		// 등록
		System.out.println("Save ...");
		bookAuditableRepository.save(book1);
		
		assertThat(bookAuditableRepository.count() , not(Long.valueOf(0)));
		
		List<BookAudit> bookList = bookAuditableRepository.findByName("Book01");
		listObject(bookList);
		
		// 수정
		System.out.println("Update ...");		
		BookAudit book2 = bookList.get(0);
		book2.setAuthor("autor name 01 update");
		bookAuditableRepository.save(book1);
		
		List<BookAudit> bookList2 = bookAuditableRepository.findByName("Book01");
		listObject(bookList2);
		
		// 삭제
		//bookAuditRepository.deleteAll();

	}

}
