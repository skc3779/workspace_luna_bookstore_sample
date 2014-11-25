package com.bookstore.bshibernate.dao;

import java.util.List;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bookstore.bshibernate.entities.Book;
import com.bookstore.bshibernate.entities.History;

@Repository("historyDao")
public class HistoryDaoImpl implements HistoryDao {

	@Autowired SessionFactory sessionFactory;
	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.createQuery("delete from History").executeUpdate();	
	}

	@Override
	public int countAll() {
		Session session = sessionFactory.getCurrentSession();
		//Integer.parseInt(sessionFactory.getCurrentSession().createQuery("select count(*) from Book").uniqueResult().toString());
		return Integer.parseInt(session.createCriteria(History.class).
				setProjection(Projections.rowCount()).uniqueResult().toString());	
	}

	@Override
	public int add(History history) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.save(history);		
		return (int)history.getId();

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<History> getAll() {
		Session session = sessionFactory.getCurrentSession();		
		return session.createQuery("from History").list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<History> getByUser(int userId) {
		Session session = sessionFactory.getCurrentSession();	
		return session.createCriteria(History.class).add(Restrictions.eq("userId", userId)).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<History> getByBook(int bookId) {
		Session session = sessionFactory.getCurrentSession();	
		return session.createCriteria(History.class).add(Restrictions.eq("bookId", bookId)).list();

	}

}
