package com.bookstore.bshibernate.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.bookstore.bshibernate.entities.Book;
import com.bookstore.bshibernate.entities.History;
import com.bookstore.bshibernate.entities.User;

public interface HistoryRepository extends JpaRepository<History, Integer>, QueryDslPredicateExecutor<History>  {
	List<History> findByUser(User user);
	List<History> findByBook(Book book);
	//List<History> findByUserUsingSort(Sort sort); 
}
