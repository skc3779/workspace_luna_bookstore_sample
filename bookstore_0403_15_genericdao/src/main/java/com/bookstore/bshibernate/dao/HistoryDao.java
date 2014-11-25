package com.bookstore.bshibernate.dao;

import java.util.List;

import com.bookstore.bshibernate.entities.History;

public interface HistoryDao extends GenericDao<History, Integer>{

	void deleteAll();
	int countAll();
	List<History> getAll();
	List<History> getByUser(final int userId);
	List<History> getByBook(final int bookId);
	
}
