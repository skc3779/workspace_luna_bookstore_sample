package com.bookstore.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {
	@RequestMapping(value = "index", method = RequestMethod.GET)
    public String getIndex(ModelMap model) {
        model.addAttribute("data", "한글데이터");
        return "index";
    }
}
