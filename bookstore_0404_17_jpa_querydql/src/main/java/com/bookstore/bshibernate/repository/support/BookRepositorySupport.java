package com.bookstore.bshibernate.repository.support;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.data.jpa.repository.support.QueryDslRepositorySupport;
import org.springframework.stereotype.Repository;

import com.bookstore.bshibernate.entities.Book;
import com.bookstore.bshibernate.entities.QBook;

@Repository("bookRepositorySupport")
public class BookRepositorySupport extends QueryDslRepositorySupport  {

	@SuppressWarnings("unused")
	private EntityManager entityManager;
	
	public BookRepositorySupport(Class<?> domainClass) {
		super(domainClass);
	}
	
	@Override
	@PersistenceContext(unitName="defautEntity")
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	public List<Book> findAll() {
		QBook book = QBook.book;
		return from(book).list(book);
	}

}
