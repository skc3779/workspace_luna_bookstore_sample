package com.bookstore.bshibernate.dao;

import java.util.List;

import com.bookstore.bshibernate.entities.History;
import com.bookstore.bshibernate.entities.User;

public interface UserDao extends GenericDao<User, Integer> {
	public int countAll();
	public void deleteAll();
	public List<User> getAll();
	public List<User> Search(String name);
}
