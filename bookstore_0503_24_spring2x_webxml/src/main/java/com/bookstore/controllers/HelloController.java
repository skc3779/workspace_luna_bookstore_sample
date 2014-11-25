package com.bookstore.controllers;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.bookstore.services.HelloSpring;

/**
 * Handles requests for the application home page. 2.0.x controller 방식 활용
 */
public class HelloController implements Controller {

	private static final Logger logger = LoggerFactory
			.getLogger(HelloController.class);

	private HelloSpring helloSpring;
	
	public void setHelloSpring(HelloSpring helloSpring) {
		this.helloSpring = helloSpring;
	}
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		ModelAndView mav = new ModelAndView();
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG,
				DateFormat.LONG, Locale.KOREA);
		String formattedDate = dateFormat.format(date);
		String name = request.getParameter("name");
		logger.debug("name : " + name);

		//HelloSpring helloSpring = new HelloSpring();
		
		System.out.println("name : " + name);
		System.out.println("formattedDate : " + formattedDate);
		System.out.println("helloSpring : " + helloSpring.sayHello(name));
		
		mav.addObject("serverTime", formattedDate);
		mav.addObject("helloSpring", helloSpring.sayHello(name));
		mav.setViewName("hello");
		return mav;
	}

}
