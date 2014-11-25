package com.bookstore.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bookstore.entities.Book;
import com.bookstore.entities.BookStatus;
import com.bookstore.services.BookService;

@Controller
@RequestMapping(value="/books")
public class BookController {

	final static String ADD_HTML = "/add/html";
	final static String ADD_AJAX = "/add/ajax";	
	final static String LIST_HTML = "/list/html";
	final static String LIST_AJAX = "/list/ajax";
	final static String LIST_AJAXHTML = "/list/ajaxhtml";
	final static String LIST_AJAXJSON = "/list/ajaxjson";

	@Autowired
	private BookService bookService;

	@RequestMapping(value=ADD_HTML, method=RequestMethod.GET)
	public String addHtml() throws Exception {
		return "/add/html";
	}

	@RequestMapping(value=ADD_HTML, method=RequestMethod.POST)
	public String addHtmlSave(@ModelAttribute("bookForm") Book book) throws Exception {
		book.setBookStatus(BookStatus.CANRENT);
		bookService.setSave(book);		
		return "redirect:/books/add/html";
	}

	@RequestMapping(value=ADD_AJAX, method=RequestMethod.GET)
	public String addAjax() throws Exception {
		return "/add/ajax";
	}

	@RequestMapping(value=ADD_AJAX, method=RequestMethod.POST)
	@ResponseBody
	public String addAjaxSave(@ModelAttribute Book book) throws Exception {
		book.setBookStatus(BookStatus.CANRENT);
		bookService.setSave(book);
		return "{\"isOk\":true}";
	}	
	
	@RequestMapping(value=LIST_HTML, method=RequestMethod.GET)
	public ModelAndView listHtml(@RequestParam(defaultValue="0") int pageIndex,
			@RequestParam(defaultValue="5") int pageSize) throws Exception {
		ModelAndView mv = new ModelAndView();
		Page<Book> page = bookService.findAll(pageIndex, pageSize);		
		mv.addObject("books", page.getContent());
		mv.setViewName("/list/html");
		return mv;
	}
	
	@RequestMapping(value=LIST_AJAX, method=RequestMethod.GET)
	public String listAjax() throws Exception {
		return "/list/ajax";
	}

	@RequestMapping(value=LIST_AJAXHTML, method=RequestMethod.GET)
	public ModelAndView listAjax(@RequestParam(defaultValue="0") int pageIndex,
			@RequestParam(defaultValue="5") int pageSize) throws Exception {
		ModelAndView mv = new ModelAndView();
		Page<Book> page = bookService.findAll(pageIndex, pageSize);		
		mv.addObject("books", page.getContent());
		mv.setViewName("/books/list-ajaxhtml");
		return mv;
	}	
	
}
