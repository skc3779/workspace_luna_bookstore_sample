package com.bookstore.bshibernate.auditor;

import org.springframework.data.domain.AuditorAware;

public class AuditorAwareBean implements AuditorAware<String>	{
	
	public String getCurrentAuditor() {
		return "bookstore";
	}
	
}
