package com.bookstore.entities.properties;

import java.beans.PropertyEditorSupport;

import com.bookstore.entities.SearchLevel;

public class SearchLevelPropertyEditor extends PropertyEditorSupport {
	public String getAsText() {
		return String.valueOf(((SearchLevel)this.getValue()).intValue()); 
	}

	public void setAsText(String text) throws IllegalArgumentException {
		this.setValue(SearchLevel.valueOf(Integer.parseInt(text.trim())));
	}
	
}
