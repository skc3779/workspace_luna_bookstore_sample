package com.bookstore.entities.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.bookstore.entities.BookStatus;

@Component("bookStatusConverter")
public class StringToBookStatusConverter implements Converter<String, BookStatus> {

	@Override
	public BookStatus convert(String text) {
		return BookStatus.valueOf(Integer.parseInt(text.trim()));
	}

}
