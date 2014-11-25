package com.bookstore.dao;

import java.util.List;

import com.bookstore.entity.User;

public interface UserDao {
	
	public int countAll();
	public void deletAll();
	public void add(final User user);
	public void update(final User user);
	public User get(final int id);
	public List<User> getAll();
}
