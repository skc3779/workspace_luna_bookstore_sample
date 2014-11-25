package com.bookstore.bshibernate.dao;

import java.util.List;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bookstore.bshibernate.entities.User;

@Repository("userDao")

public class UserDaoImpl implements UserDao {

	@Autowired SessionFactory sessionFactory;
	
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

	@Override
	public int add(User user) {
		Session session = sessionFactory.getCurrentSession();
		session.save(user);		
		return (int)user.getId();
	}

	@Override
	public void update(User user) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.update(user);
	}

	@Override
	public User get(int id) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();		
		return (User)session.get(User.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getAll() {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();		
		return session.createQuery("from User").list();
	}

}
