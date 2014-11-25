package com.bookstore.mybatis.entities;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

public class History {
	private @Getter @Setter Integer id;
	private @Getter @Setter Integer userId;
	private @Getter @Setter Integer bookId;
	private @Getter @Setter HistoryActionType actionType;
	private @Getter @Setter Date insertDate;
}
