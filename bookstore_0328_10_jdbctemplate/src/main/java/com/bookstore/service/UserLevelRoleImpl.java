package com.bookstore.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.bookstore.entity.User;
import com.bookstore.entity.UserLevel;

@Service
public class UserLevelRoleImpl implements UserLevelRole{
	
    @Value("${userLevelRole.addRentPoint}")
	private int addRentPoint;		// 한번 빌릴때 마다 더할 포인트값 (현재는 10단위로 더해줌)
	@Value("${userLevelRole.readerThreashold}")
	private int readerThreashold;  // 리더의 조건 외부 셋팅함 100
	@Value("${userLevelRole.mvpThreashold}")
	private int mvpThreashold;    // MVP의 조건 외부에서 셋팅함 300

/*
    @Value("10")
	private int addRentPoint;		// 한번 빌릴때 마다 더할 포인트값 (현재는 10단위로 더해줌)
	@Value("100")
	private int readerThreashold;  // 리더의 조건 외부 셋팅함 100
	@Value("300")
	private int mvpThreashold;    // MVP의 조건 외부에서 셋팅함 300
*/
	
	@Override  // 여기 메소드로 User의 레벨을 변동하여 준다.
	public void updatePointAndLevel(User user) {
		user.setPoint(user.getPoint() + addRentPoint);
		if(user.getPoint() >= mvpThreashold){
			user.setLevel(UserLevel.MVP);
		}else if(user.getPoint() >= readerThreashold){
			user.setLevel(UserLevel.READER);
		}else{
			user.setLevel(UserLevel.NORMAL);
		}
	}
}
