package com.bookstore.mybatis.persistence;

import java.util.List;

import com.bookstore.mybatis.entities.Book;

public interface BookMapper  {
	
	public List<Book> findAll();
		
	public Book findById(Integer id);
	
	public void insert(Book book);
	
	public void update(Book book);
	
	public void delete(Integer id);
	
	public List<Book> findAllWithHistories();
}
