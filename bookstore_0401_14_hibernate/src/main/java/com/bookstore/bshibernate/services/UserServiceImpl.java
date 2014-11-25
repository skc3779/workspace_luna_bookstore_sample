package com.bookstore.bshibernate.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookstore.bshibernate.dao.BookDao;
import com.bookstore.bshibernate.dao.HistoryDao;
import com.bookstore.bshibernate.dao.UserDao;
import com.bookstore.bshibernate.entities.History;
import com.bookstore.bshibernate.entities.User;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private HistoryDao historyDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private BookDao bookDao;
	@Autowired
	private UserLevelRole userLevelRole; 
	// Null포인트 띄우려고 일부로 셋 넣어논거임!
	
	@Override
	public boolean rent(int userId, int bookId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean returnBook(int userId, int bookId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<User> listup() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<History> getHistories(int userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setUserLevelRoleImpl(UserLevelRoleImpl userLevelRoleImpl) {
		// TODO Auto-generated method stub

	}

}
