package com.bookstore.entities.properties;

import java.beans.PropertyEditorSupport;

import com.bookstore.entities.BookStatus;

public class BookStatusPropertyEditor extends PropertyEditorSupport {
	public String getAsText() {
		return String.valueOf(((BookStatus)this.getValue()).intValue()); 
	}

	public void setAsText(String text) throws IllegalArgumentException {
		this.setValue(BookStatus.valueOf(Integer.parseInt(text.trim())));
	}
	
}
