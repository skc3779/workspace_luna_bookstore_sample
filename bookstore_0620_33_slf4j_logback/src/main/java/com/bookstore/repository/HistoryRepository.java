package com.bookstore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.bookstore.entities.Book;
import com.bookstore.entities.History;
import com.bookstore.entities.User;

public interface HistoryRepository extends JpaRepository<History, Integer>, QueryDslPredicateExecutor<History>  {
	List<History> findByUser(User user);
	List<History> findByBook(Book book);
	//List<History> findByUserUsingSort(Sort sort); 
}
