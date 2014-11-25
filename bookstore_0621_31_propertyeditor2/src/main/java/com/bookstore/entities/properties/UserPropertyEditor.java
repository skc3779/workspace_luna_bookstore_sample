package com.bookstore.entities.properties;

import java.beans.PropertyEditorSupport;

import com.bookstore.entities.User;

public class UserPropertyEditor extends PropertyEditorSupport {
	public String getAsText() {
		return String.valueOf(((User)this.getValue()).getId()); 
	}

	public void setAsText(String text) throws IllegalArgumentException {
		User user = new User();
		user.setId(Integer.parseInt(text));
		this.setValue(user);
	}
	
}
