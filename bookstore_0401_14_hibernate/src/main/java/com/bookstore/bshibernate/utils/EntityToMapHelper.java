package com.bookstore.bshibernate.utils;

import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bookstore.bshibernate.contants.PropertyNames;
import com.bookstore.bshibernate.dao.BookDao;
import com.bookstore.bshibernate.entities.Book;

@Component("entityToMapHelper")
public class EntityToMapHelper {

	@Autowired BookDao bookDao;
	
	public <T> Map<String, Object> toMap(T target, Object...data) {
		if (target instanceof Book) {
			return convertFromBook((Book) target);
		} 
		return null;
	}
	
	public Map<String, Object> convertFromBook(Book target) {
		Book book = target;
		Map<String, Object> item = new Hashtable<String, Object>();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		item.put(PropertyNames.Id, Integer.valueOf(book.getId()));
		item.put(PropertyNames.Name, book.getName());
		
		item.put(PropertyNames.Author, book.getAuthor());
		item.put(PropertyNames.PublishDate, dateFormat.format(book.getPublishDate()));
		item.put(PropertyNames.Comment, book.getComment());
		item.put(PropertyNames.Bookstatus, Integer.valueOf(book.getBookStatus().intValue()));
		item.put(PropertyNames.RentuserId, (book.getRentUserId() != null ? Integer.valueOf(book.getRentUserId()) : "") );

/*
  id Integer AUTO_INCREMENT PRIMARY KEY,
  name varchar(255) NOT NULL,
  author varchar(50) NOT NULL,
  publishDate timestamp NOT NULL,
  comment varchar(255),
  status Integer NOT NULL,
  rentUserId Integer		
 */
		return item;
	}	
	
}
