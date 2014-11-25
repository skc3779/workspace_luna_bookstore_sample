package com.bookstore.mybatis.entities;

import lombok.Getter;
import lombok.Setter;

public class User {
	private @Getter @Setter Integer id;
	private @Getter @Setter String name;
	private @Getter @Setter String password;
	private @Getter @Setter Integer point;
	private @Getter @Setter UserLevel level;
}
