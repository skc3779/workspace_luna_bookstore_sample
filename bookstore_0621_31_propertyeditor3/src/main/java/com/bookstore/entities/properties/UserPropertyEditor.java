package com.bookstore.entities.properties;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.bookstore.entities.User;
import com.bookstore.repository.UserRepository;

@Component
@Scope("prototype")
public class UserPropertyEditor extends PropertyEditorSupport {
	
	@Autowired UserRepository userRepository;
	
	public String getAsText() {
		return String.valueOf(((User)this.getValue()).getId()); 
	}

	public void setAsText(String text) throws IllegalArgumentException {
		this.setValue(this.userRepository.findOne(Integer.parseInt(text)));
	}
	
}
