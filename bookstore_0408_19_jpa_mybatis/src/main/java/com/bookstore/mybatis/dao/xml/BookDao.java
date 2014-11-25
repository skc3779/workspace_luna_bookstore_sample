package com.bookstore.mybatis.dao.xml;

import com.bookstore.mybatis.entities.Book;

public interface BookDao {
	public Book findById(Integer id);
}