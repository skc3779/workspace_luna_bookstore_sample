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
import com.bookstore.bshibernate.entities.User;

@Repository("bookDao")
public class BookDaoImpl extends GenericDaoImpl<Book, Integer> implements BookDao {

	public BookDaoImpl() {
		super(Book.class);
	}
	
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public int countAll() {
		
		Session session = sessionFactory.getCurrentSession();
		//Integer.parseInt(sessionFactory.getCurrentSession().createQuery("select count(*) from Book").uniqueResult().toString());
		return Integer.parseInt(session.createCriteria(Book.class).
				setProjection(Projections.rowCount()).uniqueResult().toString());				
	}

/*	public void deleteAll() {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.createQuery("delete from Book").executeUpdate();		
	}
*/
	@SuppressWarnings("unchecked")
	public List<Book> getAll() {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();		
		return session.createQuery("from Book").list();
	}

	@SuppressWarnings("unchecked")
	public List<Book> search(String name) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();	
		return session.createCriteria(Book.class).add(Restrictions.ilike("name", name, MatchMode.START)).list();
	}
	

}
