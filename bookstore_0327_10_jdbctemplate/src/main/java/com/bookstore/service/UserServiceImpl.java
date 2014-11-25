package com.bookstore.service;

import java.util.Date;
import java.util.List;

import lombok.Setter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bookstore.dao.BookDaoImpl;
import com.bookstore.dao.HistoryDaoImpl;
import com.bookstore.dao.UserDaoImpl;
import com.bookstore.entity.Book;
import com.bookstore.entity.BookStatus;
import com.bookstore.entity.History;
import com.bookstore.entity.HistoryActionType;
import com.bookstore.entity.User;

//트렌젝션을 썻을떄 객체를 한번더 덮게 되는 현상이 일어난다.
//그것이 AOP기능중 하나 이다.
//Class 에다가 @Transactional(readOnly=true) <-- 를 걸어주고 @Transactional <-- 이 필요한 public 메소드에다가 거는게 특징이다.
//서비스 클래스에는 무조건 @Transactional 걸어주는게 원칙으로 한다. 커넥션 풀 문제.

@Service
@Transactional
public class UserServiceImpl implements UserService
{
	@Autowired
	private HistoryDaoImpl historyDaoImplWithJdbcTemplate;
	@Autowired
	private UserDaoImpl userDaoImplWithJdbcTemplate;
	@Autowired
	private BookDaoImpl bookDaoImplWithJdbcTemplate;
	@Autowired
	private @Setter UserLevelRoleImpl userLevelRoleImpl; 
	// Null포인트 띄우려고 일부로 셋 넣어논거임!

	@Override
	public boolean rent(final int userId, final int bookId) {
		// 1. book에서 CANRENT <-- 상태인지 확인한다.
		// 2. book.update()로 RENTNOW 로 바꾼다.
		// 3. 빌렸으면 10점 추가해주기
		// 4. pointCheck로 레벨 변동 한번 시켜준다.
		// 5. history 테이블에 남긴다.
		Book book = bookDaoImplWithJdbcTemplate.get(bookId);
		User user = userDaoImplWithJdbcTemplate.get(userId);
		// 1번 작업진행
		if(book.getBookStatus() == BookStatus.CANRENT)
		{ // 빌릴수 있는 상태이냐
			book.setBookStatus(BookStatus.RENTNOW);
			book.setRentUserId((Integer)userId);

			// 트렌젝션 테스트를 위해 book을 먼저 업데이트 할 것이다.
			bookDaoImplWithJdbcTemplate.update(book);
			userLevelRoleImpl.updatePointAndLevel(user);
			userDaoImplWithJdbcTemplate.update(user);

			historyCreate(user, book, HistoryActionType.RENT);
			return true;
		}
		return false;
	}

	@Override
	public boolean returnBook(final int userId, final int bookId) {
		// 반납했을때의 처리
		Book book = bookDaoImplWithJdbcTemplate.get(bookId);
		// 1번 작업진행
		if(book.getBookStatus() == BookStatus.RENTNOW)
		{ // 반납가능하면
			book.setBookStatus(BookStatus.CANRENT);
			book.setRentUserId((Integer)null);
			System.out.println((Integer)null);
			// 2번 있다고 변경
			User user = userDaoImplWithJdbcTemplate.get(userId);
			bookDaoImplWithJdbcTemplate.update(book);

			historyCreate(user, book, HistoryActionType.RETURN);
			return true;
		}
		return false;
	}

	@Override
	//@Transactional(readOnly=true)
	public List<User> listup() {
		return userDaoImplWithJdbcTemplate.getAll();
	}

	@Override
	//@Transactional(readOnly=true)
	public List<History> getHistories(int userId) {
		return historyDaoImplWithJdbcTemplate.getByUser(userId);
		// userId로 검색을 하여 반환한다.
	}

	public void historyCreate(User user, Book book, HistoryActionType rent)
	{
		// 기록을 남겨준다.
		History history = new History();
		history.setUserId(user.getId());
		history.setBookId(book.getId());
		history.setInsertDate(new Date());

		if(rent.intValue() == HistoryActionType.RENT.intValue()) // 0이면 RENT
		{
			history.setActionType(HistoryActionType.RENT);
		}else // 0이 아니면 반환해준거임
		{
			history.setActionType(HistoryActionType.RETURN);
		}
		historyDaoImplWithJdbcTemplate.add(history);
	}
}
