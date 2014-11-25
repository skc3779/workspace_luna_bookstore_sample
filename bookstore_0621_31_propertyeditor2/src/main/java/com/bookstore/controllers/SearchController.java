package com.bookstore.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.bookstore.entities.SearchLevel;
import com.bookstore.entities.properties.SearchLevelPropertyEditor;

@Controller
public class SearchController {

	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		// @RequestParam PropertyEditor -> BookStoreWebBindingInitializer 이동
	}

	@RequestMapping(value="/searchlevel", method=RequestMethod.GET)
	public String searchLevel(@RequestParam("level") SearchLevel level, Model model) {
		model.addAttribute("level", level);
		return "/searchlevel";
	}
	
	@RequestMapping(value="/searchlevel2", method=RequestMethod.GET)
	public String searchLevel2(SearchLevel level, Model model) {
		model.addAttribute("level", level);
		return "/searchlevel2";
	}	
	
}
