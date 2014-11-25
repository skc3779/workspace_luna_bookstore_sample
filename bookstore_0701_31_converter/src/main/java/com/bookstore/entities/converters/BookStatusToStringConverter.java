package com.bookstore.entities.converters;

import org.springframework.core.convert.converter.Converter;

import com.bookstore.entities.BookStatus;

public class BookStatusToStringConverter implements Converter<BookStatus, String> {

	@Override
	public String convert(BookStatus level) {
		return String.valueOf(level.intValue());
	}

}
