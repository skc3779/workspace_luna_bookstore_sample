package com.bookstore.config;

import javax.servlet.Filter;

import org.springframework.security.config.BeanIds;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;


public class WebXml32Configuration extends AbstractAnnotationConfigDispatcherServletInitializer {
	
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[] { RootContextConfiguration.class, SecurityContextConfiguration.class};
		//, SecurityContextConfiguration.class 
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[] { ServletContextConfiguration.class };
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] {"/"};
	}
	
	@Override
	protected boolean isAsyncSupported () {
		return true;
	}
	
	@Override 
	protected Filter[] getServletFilters() {
		
		CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
		encodingFilter.setEncoding("UTF-8");
		encodingFilter.setForceEncoding(true);
		
        DelegatingFilterProxy delegateProxyFilter = new DelegatingFilterProxy();
        delegateProxyFilter.setTargetBeanName(BeanIds.SPRING_SECURITY_FILTER_CHAIN);
		
		return new Filter[] { encodingFilter, delegateProxyFilter };
	}
}
