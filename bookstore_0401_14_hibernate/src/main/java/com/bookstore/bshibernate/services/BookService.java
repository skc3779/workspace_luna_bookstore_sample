package com.bookstore.bshibernate.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.bookstore.bshibernate.entities.Book;

public interface BookService {
	public ArrayList<Map<String,Object>> listup();	//대출이 가능한 책 우선으로 Sort된다.
}
