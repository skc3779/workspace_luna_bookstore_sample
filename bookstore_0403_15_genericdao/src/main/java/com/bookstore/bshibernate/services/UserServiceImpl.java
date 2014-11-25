package com.bookstore.bshibernate.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookstore.bshibernate.dao.BookDao;
import com.bookstore.bshibernate.dao.HistoryDao;
import com.bookstore.bshibernate.dao.UserDao;
import com.bookstore.bshibernate.entities.Book;
import com.bookstore.bshibernate.entities.BookStatus;
import com.bookstore.bshibernate.entities.History;
import com.bookstore.bshibernate.entities.HistoryActionType;
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
	public List<History> getHistories(int userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setUserLevelRoleImpl(UserLevelRoleImpl userLevelRoleImpl) {
		// TODO Auto-generated method stub

	}
	
	@Override
	public boolean rent(final int userId, final int bookId) {
/*		Book book = bookDao.findByID(Book.class, bookId);
		User user = userDao.findByID(User.class, userId);
		if(book.getBookStatus() == BookStatus.CANRENT){
			// 책대여로 변환
			book.setRentUserId(user);
			book.setStatus(BookStatus.RENTNOW);
			bookDaoImpl.update(book);
			//유저 포인트 변환
			user.setPoint(user.getPoint());
			userDaoImpl.update(user);

			User tempUser = userDaoImpl.find(userId);
			userLevelRole.updatePointAndLevel(tempUser);
			//레별변환
			userDaoImpl.update(tempUser);
			//희스토리 기록
			History history = new History(user,book,HistoryActionType.RENT,new Date());
			historyDaoImpl.add(history);
			return true;
		}else{
			System.out.println("다른손님이 빌려 갔습니다");
			return false;
		}*/
		return false;
	}


	@Override
	public boolean returnBook(final int userId, final int bookId) {
/*		Book book = bookDaoImpl.find(bookId);
		User user = userDaoImpl.find(userId);
		if(book.getBookStatus() ==BookStatus.RENTNOW){
			book.setRentUserId(null);
			book.setStatus(BookStatus.CANRENT);
			bookDaoImpl.update(book);

			History history = new History(user,book,HistoryActionType.RETURN,new Date());
			historyDaoImpl.add(history);	
			return true;
		}else{
			System.out.println("책이 이미 있습니다.");
			return false;
		}*/
		return false;
	}

	@Override
	public List<User> listup() {
		//return userDao.listup();
		return null;
	}	

}
