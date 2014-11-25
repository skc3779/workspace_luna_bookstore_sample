package com.bookstore.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

import com.bookstore.entities.Book;

public interface BookService {
	public ArrayList<Map<String,Object>> listup(); //대출이 가능한 책 우선으로 Sort된다.	
	public List<Book> getAll();
	public void setSave(Book book);
	public Page<Book> findAll(int pageIndex, int pageSize);
}
