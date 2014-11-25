package com.bookstore.validators;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.bookstore.entities.Book;

@Component
public class BookValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Book.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Book book = (Book)target;
		
		if(book.getName() == null || book.getName().length() < 2) {
			errors.rejectValue("name", "name.required");
		}
		
		if(book.getAuthor() == null || book.getAuthor().length() < 2) {
			errors.rejectValue("author", "name.required");
		}
		
		Date date = null;
		
		try {
			date = new SimpleDateFormat("yyyy-MM-dd").parse("1970-01-01");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(book.getPublishDate().getTime() < date.getTime() ) {
			errors.rejectValue("publishDate", "name.required");
		}
	}

}
