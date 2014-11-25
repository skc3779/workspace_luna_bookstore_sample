package com.bookstore.service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookstore.dao.BookDao;
import com.bookstore.dao.BookDaoImpl;
import com.bookstore.entity.Book;

@Service
public class BookServiceImpl implements BookService
{
	@Autowired
	private BookDao bookDao;

	@Override // 코드로 정렬을 한다.
	public List<Book> listup() {
		List<Book> books = bookDao.getAll();
		Collections.sort(books, new Comparator<Book>(){
			@Override
			public int compare(Book arg0, Book arg1) {
				return Integer.compare(arg0.getBookStatus().intValue(), arg1.getBookStatus().intValue());
			}
		});
		return books;
	}
}
