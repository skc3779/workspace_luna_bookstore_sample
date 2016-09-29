package com.bookstore.dao;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bookstore.dao.BookDao;
import com.bookstore.entity.Book;
import com.bookstore.entity.BookStatus;


//@Transactional // 테스트 코드에서 트렌젝션 걸고 싶을때
//디폴트로 무조건 rollBack 이다. <-- 실DB에 건드릴수 있는 상황이기 때문에 그걸 방지해준다.
//@TransactionConfiguration(defaultRollback = false)
//컨피그를 = false 를 사용하면 실 DB에 commit 된다.

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class BookDaoImplTest
{
	@Autowired
	private BookDao bookDao;

	public List<Book> getBooks(){
		System.out.println("getBooks 시작");
		Book book1 = new Book();
		book1.setId(1);
		book1.setName("book name01");
		book1.setAuthor("autor name 01");
		book1.setComment("comment01");
		book1.setPublishDate(new Date());
		book1.setBookStatus(BookStatus.CANRENT);
		book1.setRentUserId(null);

		Book book2 = new Book();
		book2.setId(2);
		book2.setName("book name02");
		book2.setAuthor("autor name 02");
		book2.setComment("comment02");
		book2.setPublishDate(new Date());
		book2.setBookStatus(BookStatus.MISSING);
		book2.setRentUserId(null);

		Book book3 = new Book();
		book3.setId(3);
		book3.setName("book name03");
		book3.setAuthor("autor name 03");
		book3.setComment("comment03");
		book3.setPublishDate(new Date());
		book3.setBookStatus(BookStatus.RENTNOW);
		book3.setRentUserId(1);

		List<Book> books = Arrays.asList(book1,book2,book3);
		System.out.println("getBooks 종료");
		return books;
	}
	

	private void compareBook(Book book)
	{
		System.out.println("compareBook 시작");
		Book dbBook = bookDao.get(book.getId());

		System.out.println("1.가져온값"+dbBook.getPublishDate());
		System.out.println("2.검수할값"+book.getPublishDate());

		assertThat(dbBook.getName(), is(book.getName()));
		assertThat(dbBook.getAuthor(), is(book.getAuthor()));
		assertThat(dbBook.getComment(), is(book.getComment()));
		assertThat(dbBook.getPublishDate().toString(), is(book.getPublishDate().toString()));
		assertThat(dbBook.getBookStatus(), is(book.getBookStatus()));
		assertThat(dbBook.getRentUserId(), is(book.getRentUserId()));
		System.out.println("compareBook 종료");
	}
	
	@Before
	public void setUp()
	{
		System.out.println("Book setUp시작");
		bookDao.deletAll(); // 지울때는 가운데
		
		System.out.println("Book setUp완료");
	}

	@Test
	public void addAndCount()
	{
		// 추가 순서는 users -> books -> histories
		System.out.println("addAndCount 시작");

		List<Book> books = getBooks();
		int count1 = 0;
		for (Book book : books)
		{
			bookDao.add(book);
			count1++;
			assertThat(bookDao.countAll(), is(count1));
		}

		System.out.println("addAndCount 종료");
	}

	@Test
	public void update()
	{
		System.out.println("update 시작");
		List<Book> books = getBooks();
		int count = 0;
		for(Book book : books)
		{
			bookDao.add(book);
			count++;
			assertThat(bookDao.countAll(), is(count));

			book.setName("changed name");
			book.setPublishDate(new Date());
			book.setAuthor("changed author");
			bookDao.update(book);
			System.out.println("Test update Date 가져오기"+ book.getPublishDate());
			compareBook(book);
		}
		System.out.println("update 종료");
	}

	@Test
	public void getAll()
	{
		System.out.println("getAll 시작");
		List<Book> books = getBooks();
		int count = 0;
		for(Book book : books)
		{
			bookDao.add(book);
			count ++;
			assertThat(bookDao.countAll(), is(count));
		}

		List<Book> books2 = bookDao.getAll();
		assertThat(books2.size(), is(books.size()));
		System.out.println("getAll 종료");
	}

	@Test
	public void search()
	{
		System.out.println("search 시작");
		List<Book> books = getBooks();
		int count = 0;
		for(Book book : books)
		{
			bookDao.add(book);
			count++;
			assertThat(bookDao.countAll(), is(count));
		}

		List<Book> searchedBooks = bookDao.search("01");
		assertThat(searchedBooks.size(), is(1));

		searchedBooks = bookDao.search("02");
		assertThat(searchedBooks.size(), is(1));

		searchedBooks = bookDao.search("03");
		assertThat(searchedBooks.size(), is(1));

		searchedBooks = bookDao.search("name");
		assertThat(searchedBooks.size(), is(3));
		System.out.println("search 종료");
	}

	@Test
	public void test() {
		System.out.println("test...");
	}
}
