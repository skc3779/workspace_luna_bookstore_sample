package com.bookstore.bshibernate.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookstore.bshibernate.entities.Book;
import com.bookstore.bshibernate.entities.User;

public interface BookRepository extends JpaRepository<Book, Integer> {	
	List<Book>	findByName(String name);
}
