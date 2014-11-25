package com.bookstore.entities.properties;

import java.beans.PropertyEditorSupport;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import com.bookstore.entities.BookStatus;

@Component
@Scope("prototype")
public class BookStatusPropertyEditor extends PropertyEditorSupport {

	public String getAsText() {
		return String.valueOf(((BookStatus)this.getValue()).intValue()); 
	}

	public void setAsText(String text) throws IllegalArgumentException {
		this.setValue(BookStatus.valueOf(Integer.parseInt(text.trim())));
	}
	
}
