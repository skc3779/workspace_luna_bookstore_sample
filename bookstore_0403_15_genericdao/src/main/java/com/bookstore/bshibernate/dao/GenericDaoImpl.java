package com.bookstore.bshibernate.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public abstract class GenericDaoImpl<T, ID extends Serializable> 
	implements GenericDao<T, ID> {
    
	protected SessionFactory sessionFactory;
	
 	Class<T> type;
	
	public GenericDaoImpl(Class<T> type) {
		this.type= type; 
	}
	
	public void add(T entity) {
		Session session = sessionFactory.getCurrentSession();
		session.save(entity);
    }
    
    public void update(T entity) {
		Session session = sessionFactory.getCurrentSession();
		session.update(entity);    	
    }
   
    public void delete(T entity) {
    	Session session = sessionFactory.getCurrentSession();
    	session.delete(entity);
    }
    
    public void deleteAll() {
    	Session session = sessionFactory.getCurrentSession();
    	List<T> list = session.createCriteria(type).list();
    	for(T t : list) {
    		delete(t);
    	}
    }
    
 
    @SuppressWarnings("unchecked")
	public T findByID(Class<T> type, ID id) {
    	
    	T t = null;
    	Session session = sessionFactory.getCurrentSession();
    	t = (T) session.get(type, id);
    	return t;
    }
}


  