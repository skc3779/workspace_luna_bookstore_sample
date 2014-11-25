package com.bookstore.bshibernate.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookstore.bshibernate.entities.Book;
import com.bookstore.bshibernate.entities.History;
import com.bookstore.bshibernate.entities.User;

public interface HistoryRepository extends JpaRepository<History, Integer> {
	List<History> findByUser(User user);
	List<History> findByBook(Book book);
}
