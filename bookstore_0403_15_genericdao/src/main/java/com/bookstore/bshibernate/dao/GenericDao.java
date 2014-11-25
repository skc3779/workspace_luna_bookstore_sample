package com.bookstore.bshibernate.dao;

import java.io.Serializable;

public interface GenericDao<T, ID extends Serializable> {

    public void add(T entity);
    
    public void update(T entity);   
   
    public void delete(T entity); 
 
    public T findByID(Class<T> type, ID id);
    
	public void deleteAll();    
}
