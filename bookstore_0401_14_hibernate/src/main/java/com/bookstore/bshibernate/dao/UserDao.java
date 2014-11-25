package com.bookstore.bshibernate.dao;

import java.util.List;

import com.bookstore.bshibernate.entities.User;

public interface UserDao {
	public int countAll();
	public void deleteAll();
	public int add(final User user);
	public void update(final User user);
	public User get(final int id);
	public List<User> getAll();
}
