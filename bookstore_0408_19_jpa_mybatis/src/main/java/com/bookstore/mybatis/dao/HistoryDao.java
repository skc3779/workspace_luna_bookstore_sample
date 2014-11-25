package com.bookstore.mybatis.dao;

import java.util.List;

import com.bookstore.mybatis.entities.History;

public interface HistoryDao {
	public List<History> findAll();
	public History findById(Integer id);
	public void insert(History history);
	public void update(History history);
	public void delete(History id);
}
