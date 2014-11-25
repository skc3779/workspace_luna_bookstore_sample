package com.bookstore.mybatis.dao;

import java.util.List;

import com.bookstore.mybatis.entities.Book;

public interface BookDao {
	public List<Book> findAll();
	public List<Book> findAllAnnotation();
	public Book findById(Integer id);
	public void save(Book book);
	public void update(Book book);
	public void delete(Integer id);
	public List<Book> findAllHistories();
}