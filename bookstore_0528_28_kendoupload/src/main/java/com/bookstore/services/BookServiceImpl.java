package com.bookstore.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bookstore.entities.Book;
import com.bookstore.repository.BookRepository;
import com.bookstore.utils.EntityToMapHelper;

@Service("bookService")
@Transactional
public class BookServiceImpl implements BookService {

	@Autowired
	BookRepository bookRepository;
	@Autowired
	EntityToMapHelper entityToMapHelper;

	@Override
	@Transactional(readOnly=true)
	public ArrayList<Map<String, Object>> listup() {

		List<Book> books = bookRepository.findAll();

		Collections.sort(books, new Comparator<Book>() {
			@Override
			public int compare(Book arg0, Book arg1) {
				return Integer.compare(arg0.getBookStatus().intValue(), arg1
						.getBookStatus().intValue());
			}
		});

		ArrayList<Map<String, Object>> bookList = new ArrayList<Map<String, Object>>();

		for (Book book : books) {
			Map<String, Object> item = entityToMapHelper.toMap(book);
			if (item != null)
				bookList.add(item);
		}

		return bookList;
	}

	@Override
	@Transactional(readOnly=true)
	public List<Book> getAll() {
		return bookRepository.findAll();
	}

	@Override
	public void setSave(Book book) {
		bookRepository.save(book);
	}

	@Override
	public List<Book> findAll(int pageIndex, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
