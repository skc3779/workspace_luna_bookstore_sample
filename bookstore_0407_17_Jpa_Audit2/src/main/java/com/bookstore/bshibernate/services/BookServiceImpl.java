package com.bookstore.bshibernate.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bookstore.bshibernate.entities.Book;
import com.bookstore.bshibernate.repository.BookRepository;
import com.bookstore.bshibernate.utils.EntityToMapHelper;

@Service("bookService")
public class BookServiceImpl implements BookService {

	@Autowired BookRepository bookRepository;
	@Autowired EntityToMapHelper entityToMapHelper;
	
	@Override
	public ArrayList<Map<String,Object>> listup() {

		List<Book> books = bookRepository.findAll();
		
		Collections.sort(books, new Comparator<Book>(){
			@Override
			public int compare(Book arg0, Book arg1) {
				return Integer.compare(arg0.getBookStatus().intValue(), arg1.getBookStatus().intValue());
			}
		});		
		
		ArrayList<Map<String,Object>> bookList = new ArrayList<Map<String, Object>>();
		
		for(Book book : books) {
			Map<String,Object> item = entityToMapHelper.toMap(book);
			if(item != null) bookList.add(item);
		}
		
		return bookList;
	}

}
