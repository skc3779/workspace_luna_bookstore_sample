package test.com.bookstore.controllers;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import com.bookstore.config.ContextServletConfiguration;
import com.bookstore.config.RootContextConfiguration;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={RootContextConfiguration.class, ContextServletConfiguration.class})
@WebAppConfiguration
public class BookControllerTest {

	private MockMvc mvc;
	
	@Autowired WebApplicationContext context;

	@Before
	public void setUp() throws Exception {
		this.mvc = webAppContextSetup(this.context).build();		
	}


	@Test
	//@Ignore
	public void testListHtml() throws Exception {
		this.mvc.perform(get("/books/list/html"))
		.andExpect(status().isOk())
		.andExpect(view().name(containsString("/list/html")))		
		.andExpect(model().size(1));
	}
	
	@Test
	public void testListAjax() throws Exception {
		this.mvc.perform(get("/books/list/ajax"))
		.andExpect(status().isOk())
		.andExpect(view().name(containsString("/list/ajax")));		
	}		

}
