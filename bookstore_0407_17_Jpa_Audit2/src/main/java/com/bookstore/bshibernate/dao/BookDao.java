package com.bookstore.bshibernate.dao;

import java.util.List;

import com.bookstore.bshibernate.entities.Book;


public interface BookDao {
	long count();
	void deleteAll();
	int save(Book book);
	void update(Book book);
	Book findOne(int id);
	List<Book> findAll();
	List<Book> findByName(String name);
}
