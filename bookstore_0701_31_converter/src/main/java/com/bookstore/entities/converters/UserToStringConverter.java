package com.bookstore.entities.converters;

import org.springframework.core.convert.converter.Converter;

import com.bookstore.entities.User;

public class UserToStringConverter implements Converter<User, String> {

	@Override
	public String convert(User user) {
		return String.valueOf(user.getId()); 
	}

}
