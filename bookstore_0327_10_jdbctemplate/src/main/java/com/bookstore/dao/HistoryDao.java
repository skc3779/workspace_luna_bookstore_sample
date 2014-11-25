package com.bookstore.dao;

import java.util.List;

import com.bookstore.entity.History;

public interface HistoryDao {
	
	void deletAll();
	int countAll();
	void add(final History history);
	List<History> getAll();
	List<History> getByUser(final int userId);
	List<History> getByBook(final int bookId);
}
