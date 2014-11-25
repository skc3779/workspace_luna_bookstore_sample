package com.bookstore.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bookstore.entities.Book;
import com.bookstore.entities.BookStatus;
import com.bookstore.services.BookService;

@Controller
@RequestMapping(value="/books")
public class BookStore2Controller {

	final static String ADD_HTML = "/add/html";
	final static String ADD_AJAX = "/add/ajax";	
	final static String LIST_HTML = "/list/html";
	final static String LIST_AJAX = "/list/ajax";

	@Autowired
	private BookService bookService;

	@RequestMapping(value=ADD_HTML, method=RequestMethod.GET)
	public String addHtml() throws Exception {
		return "/add/html";
	}

	@RequestMapping(value=ADD_HTML, method=RequestMethod.POST)
	public String addHtmlSave(@ModelAttribute("book") Book book) throws Exception {
		book.setBookStatus(BookStatus.CANRENT);
		bookService.setSave(book);		
		return "redirect:/books/add/html";
	}	

}
