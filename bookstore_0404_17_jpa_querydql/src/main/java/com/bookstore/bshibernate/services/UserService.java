package com.bookstore.bshibernate.services;

import java.util.List;

import com.bookstore.bshibernate.entities.History;
import com.bookstore.bshibernate.entities.User;

public interface UserService {
	// 1. user는 book을 빌릴수 있다.
	// 2. user가 book을 빌리면, point 10점씩 쌓인다.
	// # point가 100점 넘어가면 READER가 된다.
	// # point가 300점 넘어가면 MVP가 된다.
	// # point가 100점 이하인 경우, 일반유저(NORMAL)이다.
	// * book은 대출가능, 대출중, 분실의 3가지의 상태를 갖는다.
	public boolean rent(int userId, int bookId);
	// 1. user가 book을 반납
	public boolean returnBook(int userId, int bookId);
	// 1. user의 전체를 보여준다.
	public List<History> listupHistories(int userId);
	// 1. user는 자신이 지금까지 빌린 book들의 기록(대출,반납)을 최신순으로 볼 수 있다.
	public void setUserLevelRole(UserLevelRole userLevelRole);
}
