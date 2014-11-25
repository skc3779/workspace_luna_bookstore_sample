package com.bookstore.bshibernate.dao;

import java.util.List;

import com.bookstore.bshibernate.entities.History;

public interface HistoryDao {

	void deleteAll();
	int countAll();
	int add(final History history);
	List<History> getAll();
	List<History> getByUser(final int userId);
	List<History> getByBook(final int bookId);
	
}
