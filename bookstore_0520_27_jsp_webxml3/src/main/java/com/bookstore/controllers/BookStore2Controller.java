package com.bookstore.controllers;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bookstore.services.BookService;

@Controller
@RequestMapping(value="/tiles")
public class BookStore2Controller {

	final static String LIST_JSTL = "list-j";
	final static String LIST_VELOCITY = "list-v";
	final static String LIST_FREEMARKER = "list-f";

	@Autowired
	private BookService bookService;
	
	@RequestMapping(value=LIST_JSTL, method=RequestMethod.GET)
	public ModelAndView getListJstl() throws Exception {
		ModelAndView mv = new ModelAndView();
		ArrayList<Map<String, Object>> books = bookService.listup();	
		mv.addObject("books", books);
		mv.setViewName("tile.list.jsp");
		return mv;
	}	
	
	@RequestMapping(value=LIST_FREEMARKER, method=RequestMethod.GET)
	public ModelAndView getListVeloctiy() throws Exception {
		ModelAndView mv = new ModelAndView();
		ArrayList<Map<String, Object>> books = bookService.listup();	
		mv.addObject("hello", "Hello World");
		mv.addObject("books", books);
		mv.setViewName("tile.list.ftl");
		return mv;
	}
	
}
