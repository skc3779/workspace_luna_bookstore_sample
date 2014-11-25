package com.bookstore.repository.support;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.data.jpa.repository.support.QueryDslRepositorySupport;
import org.springframework.stereotype.Repository;

import com.bookstore.entities.QBook;
import com.bookstore.entities.Book;

@Repository("bookRepositorySupport")
public class BookRepositorySupport extends QueryDslRepositorySupport  {

	@SuppressWarnings("unused")
	private EntityManager entityManager;
	
	public BookRepositorySupport(Class<?> domainClass) {
		super(domainClass);
	}
	
	@Override
	@PersistenceContext
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	public List<Book> findAll() {
		QBook book = QBook.book;
		return from(book).list(book);
	}

}
