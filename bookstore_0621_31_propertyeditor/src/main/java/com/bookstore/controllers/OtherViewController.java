package com.bookstore.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bookstore.entities.Book;
import com.bookstore.services.BookService;
import com.bookstore.views.BookExcelView;
import com.bookstore.views.BookPdfView;

@Controller
@RequestMapping(value="/tiles")
public class OtherViewController {

	final static String LIST = "list";
	final static String LIST_EXCEL = "listexcel";
	final static String LIST_PDF = "listpdf";
	final static String LIST_JSON1 = "listjson1";
	final static String LIST_JSON2 = "listjson2";
//	final static String LIST_VELOCITY = "list-v";
//	final static String LIST_FREEMARKER = "list-f";

	@Autowired
	private BookService bookService;
	@Autowired
	private BookExcelView bookExcelView;
	
	@Autowired
	private BookPdfView bookPdfView;
	
	@RequestMapping(value=LIST, method=RequestMethod.GET)
	public ModelAndView getList() throws Exception {
		ModelAndView mv = new ModelAndView();
		ArrayList<Map<String, Object>> books = bookService.listup();	
		mv.addObject("books", books);
		mv.setViewName("tile.list.jsp");
		return mv;
	}
	
	@RequestMapping(value=LIST_EXCEL, method=RequestMethod.GET)
	public ModelAndView getListExcel() throws Exception {
		ModelAndView mv = new ModelAndView();
		ArrayList<Map<String, Object>> books = bookService.listup();
		mv.addObject("books", books);
		mv.setView(bookExcelView);
		return mv;
	}
	
	@RequestMapping(value=LIST_PDF, method=RequestMethod.GET)
	public ModelAndView getListPdf() throws Exception {
		ModelAndView mv = new ModelAndView();
		ArrayList<Map<String, Object>> books = bookService.listup();
		mv.addObject("books", books);
		mv.setView(bookPdfView);
		return mv;
	}	
	
	@RequestMapping(value=LIST_JSON1, method=RequestMethod.GET)
	@ResponseBody
	public Object getListJson1() throws Exception {
		ArrayList<Map<String, Object>> books = bookService.listup();
		return books;
	}
	
	@RequestMapping(value=LIST_JSON2, method=RequestMethod.GET)
	@ResponseBody
	public Object getListJson2() throws Exception {
		return bookService.getAll();
	}	
	
}
