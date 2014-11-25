package com.bookstore.mybatis.persistence;

import java.util.List;

import com.bookstore.mybatis.entities.User;

public interface UserMapper {
	
	public List<User> findAll();
	
	public User findById(Integer id);
	
	public void insert(User user);
	
	public void update(User user);
	
	public void delete(Integer id);
	
}
