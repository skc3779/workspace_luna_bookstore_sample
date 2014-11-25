package com.bookstore.bshibernate.services;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookstore.bshibernate.entities.Book;
import com.bookstore.bshibernate.entities.BookStatus;
import com.bookstore.bshibernate.entities.History;
import com.bookstore.bshibernate.entities.HistoryActionType;
import com.bookstore.bshibernate.entities.User;
import com.bookstore.bshibernate.repository.BookRepository;
import com.bookstore.bshibernate.repository.HistoryRepository;
import com.bookstore.bshibernate.repository.UserRepository;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired private HistoryRepository historyRepository;
	@Autowired private UserRepository userRepository;
	@Autowired private BookRepository bookRepository;
	@Autowired private UserLevelRole userLevelRole; 
	
	@Override
	public void setUserLevelRole(UserLevelRole userLevelRole) {
		// TODO Auto-generated method stub
		this.userLevelRole = userLevelRole;
	}
	
	@Override
	public boolean rent(final int userId, final int bookId) {
		
		Book book = bookRepository.findOne(bookId);
		User user = userRepository.findOne(userId);
		
		if(book.getBookStatus() == BookStatus.CANRENT){
			
			System.out.println("rent user name : " + user.getName());
			
			// 책대여로 변환
			book.setBookStatus(BookStatus.RENTNOW);			
			bookRepository.save(book);
			
			//유저 포인트 변환
			userLevelRole.updatePointAndLevel(user);
			
			//희스토리 기록
			History history = new History();
			history.setBook(book);
			history.setUser(user);
			history.setActionType(HistoryActionType.RENT);
			history.setInsertDate(new Date());
			historyRepository.save(history);
			
			return true;
			
		}else{
			System.out.println("다른손님이 빌려 갔습니다");
			return false;
		}
	}

	@Override
	public boolean returnBook(final int userId, final int bookId) {
		Book book = bookRepository.findOne(bookId);
		User user = userRepository.findOne(userId);
		
		if(book.getBookStatus() == BookStatus.RENTNOW){
			
			// 책대여가능으로 변환
			book.setBookStatus(BookStatus.CANRENT);			
			book.setRentUser(null);
			bookRepository.save(book);
			
			//희스토리 기록
			History history = new History();
			history.setBook(book);
			history.setUser(user);
			history.setActionType(HistoryActionType.RENT);
			history.setInsertDate(new Date());
			historyRepository.save(history);
			
			return true;
			
		}else{
			
			System.out.println("책이 이미 있습니다.");
			return false;
		}
	}

	@Override
	public List<History> listupHistories(int userId) {
		List<History> histories = historyRepository.findByUser(userRepository.findOne(userId));
		
		Collections.sort(histories, new Comparator<History>() {
			@Override
			public int compare(History arg0, History arg1) {
				return Long.compare(arg0.getInsertDate().getTime(), arg1.getInsertDate().getTime());
			}
		});
		
		return histories;
	}

}
