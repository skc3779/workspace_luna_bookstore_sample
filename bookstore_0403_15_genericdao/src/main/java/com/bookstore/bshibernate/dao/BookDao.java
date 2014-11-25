package com.bookstore.bshibernate.dao;

import java.util.List;

import com.bookstore.bshibernate.entities.Book;


public interface BookDao extends GenericDao<Book, Integer> {
	int countAll();
	List<Book> getAll();
	List<Book> search(String name);
}
