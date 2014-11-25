package com.bookstore.entities.properties;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebBindingInitializer;
import org.springframework.web.context.request.WebRequest;

import com.bookstore.entities.BookStatus;
import com.bookstore.entities.SearchLevel;
import com.bookstore.entities.User;

public class BookStoreWebBindingInitializer implements WebBindingInitializer{

	@Override
	public void initBinder(WebDataBinder dataBinder, WebRequest request) {
		
		// Book2Controller - ModelAttribute PropertyEditor 
		dataBinder.registerCustomEditor(User.class, new UserPropertyEditor());
		dataBinder.registerCustomEditor(BookStatus.class, new BookStatusPropertyEditor());
		
		
		// SearchController -> @RequestParam PropertyEditor 
		dataBinder.registerCustomEditor(SearchLevel.class, new SearchLevelPropertyEditor());
		

	}

}
