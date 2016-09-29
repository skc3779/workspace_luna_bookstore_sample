package com.bookstore;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bookstore.entity.Book;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class BookAppTest {
    @Autowired
    private ApplicationContext context;
    @Autowired
    private BookApp bookApp;
	
    @Before
	public void setUp() throws Exception {
    	//bookApp = (BookApp)context.getBean("bookApp");
    	bookApp.deleteAll();
    	System.out.println(bookApp);
	}
    private List<Book> getBooks() {
        Book book1 = new Book();
        book1.setId(1);
        book1.setName("book name01");
        book1.setAuthor("autor name 01");
        book1.setComment("comment01");
        book1.setPublishDate(new Date());

        Book book2 = new Book();
        book2.setId(2);
        book2.setName("book name02");
        book2.setAuthor("autor name 02");
        book2.setComment("comment02");
        book2.setPublishDate(new Date());

        Book book3 = new Book();
        book3.setId(3);
        book3.setName("book name03");
        book3.setAuthor("autor name 03");
        book3.setComment("comment03");
        book3.setPublishDate(new Date());

        List<Book> books = Arrays.asList(book1, book2, book3);
        return books;
    }
    @Test
    public void getAll() throws Exception {
        List<Book> books = getBooks();
        int count = 0;
        for(Book book : books) {
            bookApp.add(book);
            count++;
            assertThat(bookApp.countAll(), is(count));
        }

        List<Book> books2 = bookApp.getAll();
        assertThat(books2.size(), is(books.size()));
    }

}
