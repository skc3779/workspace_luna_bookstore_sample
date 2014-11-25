package com.bookstore.bshibernate.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bookstore.bshibernate.entities.Book;

@Repository("bookDao")
public class BookDaoImpl implements BookDao {

	@Autowired SessionFactory sessionFactory;
	
	public int countAll() {
		
		Session session = sessionFactory.getCurrentSession();
		//Integer.parseInt(sessionFactory.getCurrentSession().createQuery("select count(*) from Book").uniqueResult().toString());
		return Integer.parseInt(session.createCriteria(Book.class).
				setProjection(Projections.rowCount()).uniqueResult().toString());				
	}

	public void deleteAll() {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.createQuery("delete from Book").executeUpdate();		
	}

	public int add(Book book) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.save(book);		
		return (int)book.getId();
	}

	public void update(Book book) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.update(book);

	}

	public Book get(int id) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();		
		return (Book)session.get(Book.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Book> getAll() {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();		
		return session.createQuery("from Book").list();
	}

	public List<Book> search(String name) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();	
		return session.createCriteria(Book.class).add(Restrictions.ilike("name", name, MatchMode.START)).list();
	}
	

}
