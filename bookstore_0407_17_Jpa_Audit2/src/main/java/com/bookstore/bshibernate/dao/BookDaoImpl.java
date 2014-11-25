package com.bookstore.bshibernate.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bookstore.bshibernate.entities.Book;
import com.bookstore.bshibernate.entities.QBook;
import com.mysema.query.jpa.hibernate.HibernateDeleteClause;
import com.mysema.query.jpa.hibernate.HibernateQuery;
import com.mysema.query.sql.dml.SQLInsertClause;

@Repository("bookDao")
public class BookDaoImpl implements BookDao {

	@Autowired SessionFactory sessionFactory;
	
	public long count() {
		HibernateQuery query = new HibernateQuery(sessionFactory.getCurrentSession());
		QBook qBook = QBook.book;
		Long count = query.from(qBook).uniqueResult(qBook.count());
		return count;
	}

	public void deleteAll() {
		HibernateQuery query = new HibernateQuery();
		QBook qBook = QBook.book;
		// delete all Book;
		new HibernateDeleteClause(sessionFactory.getCurrentSession(), qBook).execute();
	}

	public int save(Book book) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.save(book);		
		return (int)book.getId();
	}

	public void update(Book book) {		
		Session session = sessionFactory.getCurrentSession();
		session.update(book);
	}

	public Book findOne(int id) {		
		HibernateQuery query = new HibernateQuery(sessionFactory.getCurrentSession());
		QBook qBook = QBook.book;
		return query.from(qBook).where(qBook.id.eq(id)).uniqueResult(qBook);
	}

	public List<Book> findAll() {
		HibernateQuery query = new HibernateQuery(sessionFactory.getCurrentSession());
		QBook qBook = QBook.book;
		return query.from(qBook).list(qBook);
	}

	public List<Book> findByName(String name) {		
		HibernateQuery query = new HibernateQuery(sessionFactory.getCurrentSession());
		QBook qBook = QBook.book;
		return query.from(qBook).where(qBook.name.eq(name)).list(qBook);
	}
	

}
