package com.bookstore.dao;

import java.util.List;

import com.bookstore.entity.Book;
// Book interface 객체

public interface BookDao  {
	
	int countAll();
	void deletAll();
	void add(Book book);
	void update(Book book);
	Book get(int id);
	List<Book> getAll();
	List<Book> search(String name);
}
