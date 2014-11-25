package com.bookstore.mybatis.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bookstore.mybatis.entities.Book;
import com.bookstore.mybatis.persistence.BookMapper;

@Service("bookService")
@Repository
@Transactional
public class BookServiceImpl implements BookService {

	private Log log = LogFactory.getLog(BookServiceImpl.class);	
	
	@Autowired
	private BookMapper bookMapper;
	
	@Override
	public ArrayList<Map<String, Object>> listup() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Book> findAll() {
		// TODO Auto-generated method stub
		List<Book> books = bookMapper.findAll();
		return books;
	}

	@Override
	public List<Book> findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Book save(Book book) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Book book) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Book book) {
		// TODO Auto-generated method stub

	}

}
