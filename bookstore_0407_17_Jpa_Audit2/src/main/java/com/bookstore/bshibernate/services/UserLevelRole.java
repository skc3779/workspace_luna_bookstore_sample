package com.bookstore.bshibernate.services;

import com.bookstore.bshibernate.entities.User;

public interface UserLevelRole {
	public void updatePointAndLevel(User user);
	// 업데이트를 이루는 서비스
}
