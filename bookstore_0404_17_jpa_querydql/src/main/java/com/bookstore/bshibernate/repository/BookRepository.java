package com.bookstore.bshibernate.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.bookstore.bshibernate.entities.Book;
/*
 * Spring data Jpa 와 QueryDsl 연결
 * QueryDslPredicateExecutor
 */
public interface BookRepository extends JpaRepository<Book, Integer>, QueryDslPredicateExecutor<Book> {	
	List<Book> findByName(String name);
	//List<Book> findByNameUsingSort(Sort sort); 
}
