package com.bookstore.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.velocity.tools.view.VelocityView;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
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

@Configuration
@EnableWebMvc
@ComponentScan(basePackages="com.bookstore.controllers")
public class ContextServletConfiguration extends WebMvcConfigurerAdapter{

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}
	
	@Bean
	public VelocityConfigurer velocityConfig() {
		
		VelocityConfigurer result = new VelocityConfigurer();
		Map<String, Object> velocityPropertiesMap = new HashMap<String, Object>();
		velocityPropertiesMap.put("resource.loader", "webapp");
		velocityPropertiesMap.put("webapp.resource.loader.class", "org.apache.velocity.tools.view.WebappResourceLoader");
		velocityPropertiesMap.put("webapp.resource.loader.path", "/WEB-INF/vm/");
		velocityPropertiesMap.put("webapp.resource.loader.cache", "false");
		velocityPropertiesMap.put("input.encoding", "UTF-8");
		velocityPropertiesMap.put("output.encoding", "UTF-8");
		result.setVelocityPropertiesMap(velocityPropertiesMap);
		//result.setResourceLoaderPath("/WEB-INF/vm");
		return result;
	}
	
	@Bean
    public ViewResolver velocityViewResolver() {
        VelocityViewResolver viewResolver = new VelocityViewResolver();
        viewResolver.setCache(true);
        viewResolver.setViewClass(VelocityView.class);
        viewResolver.setSuffix(".vm");
        viewResolver.setContentType("text/html; charset=UTF-8");
        viewResolver.setOrder(2);
        return viewResolver;

    } 		
    
	@Bean
    public FreeMarkerConfigurer freemarkerConfig() {
        FreeMarkerConfigurer result = new FreeMarkerConfigurer();
        result.setTemplateLoaderPath("/WEB-INF/ftl/");
        result.setDefaultEncoding("UTF-8");
        return result;

    } 
    
    @Bean
    public ViewResolver freeMarkerViewResolver() {
        FreeMarkerViewResolver viewResolver = new FreeMarkerViewResolver();
        viewResolver.setCache(false);
        viewResolver.setViewClass(FreeMarkerView.class);
        viewResolver.setSuffix(".ftl");
        viewResolver.setContentType("text/html; charset=UTF-8");
        viewResolver.setOrder(1);
        return viewResolver;

    } 	
	
    @Bean
	public InternalResourceViewResolver internalResourceViewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class); 
		viewResolver.setPrefix("/WEB-INF/jsp/");
		viewResolver.setSuffix(".jsp");
		viewResolver.setContentType("text/html; charset=UTF-8");
		viewResolver.setOrder(3);
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
    
}
