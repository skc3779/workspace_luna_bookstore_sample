package com.bookstore.bshibernate.dao;

import java.util.List;

import com.bookstore.bshibernate.entities.Book;


public interface BookDao {
	int countAll();
	void deleteAll();
	int add(Book book);
	void update(Book book);
	Book get(int id);
	List<Book> getAll();
	List<Book> search(String name);
}
