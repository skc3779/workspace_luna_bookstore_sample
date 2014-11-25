package com.bookstore.mybatis.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.bookstore.mybatis.entities.Book;

public interface BookService {
	public ArrayList<Map<String,Object>> listup();	
	public List<Book> findAll();
	public List<Book> findById(Integer id);
	public Book save(Book book);
	public void update(Book book);
	public void delete(Book book);
	
}
