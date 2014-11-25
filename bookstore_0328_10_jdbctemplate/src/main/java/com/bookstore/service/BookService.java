package com.bookstore.service;

import java.util.List;

import com.bookstore.entity.Book;

public interface BookService {
	public List<Book> listup();	//대출이 가능한 책 우선으로 Sort된다.
}
