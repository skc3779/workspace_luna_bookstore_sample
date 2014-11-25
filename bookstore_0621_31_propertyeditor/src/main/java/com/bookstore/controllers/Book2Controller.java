package com.bookstore.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.bookstore.entities.Book;
import com.bookstore.entities.BookStatus;
import com.bookstore.entities.User;
import com.bookstore.entities.properties.BookStatusPropertyEditor;
import com.bookstore.entities.properties.UserPropertyEditor;
import com.bookstore.services.BookService;
import com.bookstore.validators.BookValidator;

@Controller
@SessionAttributes("book")
public class Book2Controller {

	@Autowired
	private BookService bookService;
	
	@Autowired
	private BookValidator bookValidator;

	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		
		// ModelAttribute PropertyEditor 
		dataBinder.registerCustomEditor(User.class, new UserPropertyEditor());
		dataBinder.registerCustomEditor(BookStatus.class, new BookStatusPropertyEditor());
		
		// Validator 2번째 방법
		// Validate를 하는 객체들은 @InitBinder의 WebDataBinder.setValidator validate 객체를 사용
		dataBinder.setValidator(bookValidator);
		// 수정불가 금지필드 목록
		// dataBinder.setDisallowedFields("id", "bookStatus", "rentUser");

	}
	
	@ModelAttribute("book")
	public Book getBook() {
		return new Book();
	}	
	
	@RequestMapping(value="/book2/add", method=RequestMethod.POST)
	public String addHtmlSave(@Valid @ModelAttribute("book") Book book, 
			BindingResult bindingResult) throws Exception {
		if(bindingResult.hasErrors()) {
			return "/book2/add";
		}
		bookService.setSave(book);		
		return "redirect:/book2/list";
	}	
	
}
