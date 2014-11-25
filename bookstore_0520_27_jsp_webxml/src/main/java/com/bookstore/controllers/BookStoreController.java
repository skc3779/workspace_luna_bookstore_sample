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
@RequestMapping(value="/books")
public class BookStoreController {

	final static String LIST_JSTL = "list-j";
	final static String LIST_VELOCITY = "list-v";
	final static String LIST_FREEMARKER = "list-f";
	final static String TEST1 = "test1";
	final static String TEST2 = "test2";

	@Autowired
	private BookService bookService;
	
	@RequestMapping(value=LIST_JSTL, method=RequestMethod.GET)
	public ModelAndView getListJstl() throws Exception {
		ModelAndView mv = new ModelAndView();
		ArrayList<Map<String, Object>> books = bookService.listup();	
		mv.addObject("books", books);
		mv.setViewName("books/list-j");
		return mv;
	}	
	
	@RequestMapping(value=LIST_VELOCITY, method=RequestMethod.GET)
	public ModelAndView getListVeloctiy() throws Exception {
		ModelAndView mv = new ModelAndView();
		ArrayList<Map<String, Object>> books = bookService.listup();	
		mv.addObject("hello", "Hello World");
		mv.addObject("books", books);
		mv.setViewName("books/list-v");
		return mv;
	}
	
	@RequestMapping(value=LIST_FREEMARKER, method=RequestMethod.GET)
	public ModelAndView getListFreeMaker() throws Exception {
		ModelAndView mv = new ModelAndView();
		ArrayList<Map<String, Object>> books = bookService.listup();	
		mv.addObject("hello", "Hello World");
		mv.addObject("books", books);
		mv.setViewName("books/list-f");
		return mv;
	}
	
	@RequestMapping(value=TEST1, method=RequestMethod.GET)
	public @ResponseBody String getTest1(@RequestParam String name) throws Exception {
		return "Test name : " + name;
	}		
	
	@RequestMapping(value=TEST2, method=RequestMethod.GET)
	public ModelAndView getTest2(@RequestParam String name) throws Exception {
		ModelAndView mv = new ModelAndView();
		mv.addObject("hello", "Hello World");
		mv.setViewName("test/test");
		return mv;
	}
	
}
