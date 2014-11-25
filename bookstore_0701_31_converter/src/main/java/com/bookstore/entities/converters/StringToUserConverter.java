package com.bookstore.entities.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.bookstore.entities.User;

@Component("userConverter")
public class StringToUserConverter implements Converter<String, User> {

	@Override
	public User convert(String text) {
		User user = new User();
		user.setId(Integer.parseInt(text.trim()));
		return user;
	}

}
