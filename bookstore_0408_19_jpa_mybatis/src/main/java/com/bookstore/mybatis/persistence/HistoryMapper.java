package com.bookstore.mybatis.persistence;

import java.util.List;

import com.bookstore.mybatis.entities.History;

public interface HistoryMapper {
	public List<History> findAll();
	
	public History findById(Integer id);
	
	public void insert(History history);
	
	public void update(History history);
	
	public void delete(History id);
}
