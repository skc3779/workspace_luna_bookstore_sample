package com.bookstore.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.FileSystemResource;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerView;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;
import org.springframework.web.servlet.view.velocity.VelocityConfigurer;
import org.springframework.web.servlet.view.velocity.VelocityViewResolver;

import com.bookstore.views.BookExcelView;
import com.bookstore.views.BookPdfView;
import com.daesung.kendo.configs.WebMvcWithKendoConfigureAdapter;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages="com.bookstore.controllers")
@PropertySource("classpath:/com/bookstore/config/spring.properties")
public class ContextServletConfiguration extends WebMvcWithKendoConfigureAdapter{

	@Autowired
	private Environment env;
	
    protected ContextServletConfiguration() {
        super("res");
    }
    
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}
	
    @Bean
	public InternalResourceViewResolver internalResourceViewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class); 
		viewResolver.setPrefix("/WEB-INF/jsp/");
		viewResolver.setSuffix(".jsp");
		viewResolver.setContentType("text/html; charset=UTF-8");
		viewResolver.setOrder(1);
		return viewResolver;
	}

    @Bean
    public TilesConfigurer tilesConfig() {
    	TilesConfigurer result = new TilesConfigurer();
    	result.setDefinitions(new String[] {"/WEB-INF/tiles.xml"});
    	return result;
    }
    
    @Bean
    public ViewResolver tilesViewresoulver() {
    	UrlBasedViewResolver viewResolver = new UrlBasedViewResolver();
    	viewResolver.setViewClass(TilesView.class);
    	viewResolver.setOrder(0);
        return viewResolver;

    } 	    
    
	// ExcelView Bean
	@Bean
	public BookExcelView bookExcelView() {
		return new BookExcelView();
	}
	
	// PdfView Bean
	@Bean
	public BookPdfView bookPdfView() {
		return new BookPdfView();
	}    
	
	// File Upload	
	@Bean
	public CommonsMultipartResolver multipartResolver() {
		CommonsMultipartResolver resolver = new CommonsMultipartResolver();
		resolver.setMaxUploadSize(200000000); // bytes
		return resolver;
	}
	
	@Bean
	public FileSystemResource uploadDirResource() {		
		return new FileSystemResource(env.getProperty("upload.filepath1"));
	}
	
	// Messages.properties 처리
	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasenames("/WEB-INF/messages");
		return messageSource;
	}
}
