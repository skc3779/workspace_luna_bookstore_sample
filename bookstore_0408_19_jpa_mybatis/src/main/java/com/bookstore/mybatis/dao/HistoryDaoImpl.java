package com.bookstore.mybatis.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bookstore.mybatis.entities.History;
import com.bookstore.mybatis.persistence.HistoryMapper;

@Repository("historyDao")
public class HistoryDaoImpl implements HistoryDao {

	@Autowired HistoryMapper historyMapper;
	
	@Override
	public List<History> findAll() {
		// TODO Auto-generated method stub
		return historyMapper.findAll();
	}

	@Override
	public History findById(Integer id) {
		// TODO Auto-generated method stub
		return historyMapper.findById(id);
	}

	@Override
	public void insert(History history) {
		// TODO Auto-generated method stub
		historyMapper.insert(history);	
	}

	@Override
	public void update(History history) {
		// TODO Auto-generated method stub
		historyMapper.update(history);
	}

	@Override
	public void delete(History id) {
		// TODO Auto-generated method stub
		historyMapper.delete(id);
	}

}
