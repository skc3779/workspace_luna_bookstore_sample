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
import com.bookstore.bshibernate.entities.User;

@Repository("userDao")

public class UserDaoImpl extends GenericDaoImpl<User, Integer> implements UserDao {

	public UserDaoImpl() {
		super(User.class);
	}
	
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public int countAll() {
		Session session = sessionFactory.getCurrentSession();
		//Integer.parseInt(sessionFactory.getCurrentSession().createQuery("select count(*) from Book").uniqueResult().toString());
		return Integer.parseInt(session.createCriteria(User.class).
				setProjection(Projections.rowCount()).uniqueResult().toString());	
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.createQuery("delete from User").executeUpdate();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getAll() {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();		
		return session.createQuery("from User").list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<User> Search(String name) {
		Session session = sessionFactory.getCurrentSession();	
		return session.createCriteria(User.class).add(Restrictions.ilike("name", name, MatchMode.START)).list();
	}	

}
