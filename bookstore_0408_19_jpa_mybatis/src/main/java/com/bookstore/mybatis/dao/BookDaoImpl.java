package com.bookstore.mybatis.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bookstore.mybatis.entities.Book;
import com.bookstore.mybatis.persistence.BookMapper;
import com.bookstore.mybatis.persistence.BookMapperAnnotation;

@Repository("bookDao")
public class BookDaoImpl implements BookDao {

	@Autowired
	private BookMapper bookMapper;
	
	@Autowired 
	private BookMapperAnnotation bookMapperAnnotation;
	
	@Override
	public List<Book> findAll() {
		List<Book> books = bookMapper.findAll();
		return books;
	}
	
	
	public List<Book> findAllAnnotation() {
		return bookMapperAnnotation.findAll();
	}
	
	
	@Override
	public List<Book> findAllHistories() {
		List<Book> books = bookMapper.findAllWithHistories();
		return books;
	}	

	@Override
	public Book findById(Integer id) {
		return bookMapper.findById(id);
	}

	@Override
	public void save(Book book) {
		bookMapper.insert(book);
	}

	@Override
	public void update(Book book) {
		bookMapper.update(book);
	}

	@Override
	public void delete(Integer id) {
		bookMapper.delete(id);
	}

}
