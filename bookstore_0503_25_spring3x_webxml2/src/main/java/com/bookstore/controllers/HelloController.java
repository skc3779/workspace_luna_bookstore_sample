package com.bookstore.controllers;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.bookstore.services.HelloSpring;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HelloController {
	
	private static final Logger logger = LoggerFactory.getLogger(HelloController.class);
	
	@Autowired HelloSpring helloSpring;
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "index", method = RequestMethod.GET)
	public String hello(@RequestParam(value="name", defaultValue="") String name, Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		String formattedDate = dateFormat.format(date); 
		model.addAttribute("serverTime", formattedDate );
		model.addAttribute("helloSpring", helloSpring.sayHello(name) );
		
		return "hello";
	}
	
}
